/**
 *
 */
package com.netease.is.mail.domainspf;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 *
 * @author hzliuzhujie@corp.netease.com
 * @date 2017年02月16日
 */
public class RefineSubjectOld extends UDF{
	static String cnStr = "[\u4e00-\u9fa5]";
	static String jpStr = "[\u0800-\u4e00]";
	static String cnjpStr = "[\u0800-\u9fa5]";
	static String unCnjpStr = "[^\u0800-\u9fa5]";
	static String symbolStr = "[\\d`~!@#$%^&*()+=|{}':;',\\[\\]<>/~！@#￥%……&*（）——+|{}【】「」‘；：\"”“’。，、？\\\\]";
	static String symbolStr2 = "[\\pP\\s\\d‘’“”]+";
	static String symbolTrimStr = "^[\\pP\\s\\d‘’“”]+|[\\pP\\s\\d‘’“”]+$";
	static String amazonStr = "(Your\\s+)?Amazon\\..*order\\s*of|(主题\\:\\s*)?(您的)?亚马逊\\s*订单";
	static String alibabaStr = "阿里巴巴\\s*提醒您|Alibaba.com\\s*提醒您";
	static Pattern symbolPattern = Pattern.compile(symbolStr);
	static Pattern symbolTrimPattern = Pattern.compile(symbolTrimStr);
	static Pattern amazonPattern = Pattern.compile(amazonStr);
	static Pattern alibabaPattern = Pattern.compile(alibabaStr);
	

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	
	public static boolean isCnJp(String str) {
		if (str.matches(cnjpStr+".+"))
			return true;
		return false;
	}
	
	public static int calculateChinese(String str) {
		int count= 0;
		Matcher chineseMatcher = Pattern.compile(cnStr).matcher(str);
		while (chineseMatcher.find()){
			//System.out.println("[中文]" +chineseMatcher.group());
			count++;
		}
		return count;
	}
	
	public static int calculateJpanese(String str) {
		int count= 0;
		Matcher chineseMatcher = Pattern.compile(jpStr).matcher(str);
		while (chineseMatcher.find()){
			//System.out.println("[日文]" + chineseMatcher.group());
			count++;
		}
		return count;
	}

	/**
	 * 取包含关键字的最短子串
	 * @param subject
	 * @param keywordList
	 * @param domain
	 * @return shortestSubject
	 */
	private String shortestKeywordHandle(String subject,
			ArrayList<String> keywordList, String domain) {
		int startIdx=subject.length();
		int endIdx=0;
		int tmpStartIdx=0,tmpEndIdx=0;  
		ArrayList<String> keywordUnCnList = new ArrayList<String>();
		ArrayList<String> keywordCnList = new ArrayList<String>();
		ArrayList<String> keywordList2 = (ArrayList<String>) keywordList.clone();
		
		if (domain !=null && !domain.trim().isEmpty()) {
			keywordList2.add(domain);
			String[] domains = domain.split("\\.");
			if (domains.length==3)
				keywordList2.add(domains[1]+"."+domains[2]);
		}
		
		for (String keyword : keywordList2) {
			keyword = keyword.toLowerCase();
			tmpStartIdx = subject.toLowerCase().indexOf(keyword);
			tmpEndIdx = subject.toLowerCase().lastIndexOf(keyword);
			if (tmpStartIdx!=-1) {
				if (isCnJp(keyword))
					keywordCnList.add(keyword);
				else if (!keyword.contains(".")) //排除domain
					keywordUnCnList.add(keyword);
				   
				startIdx = tmpStartIdx<startIdx?tmpStartIdx:startIdx;
				endIdx= (tmpEndIdx+keyword.length())>endIdx ?tmpEndIdx+keyword.length()  :endIdx;
			}
		}
		
		if (!keywordUnCnList.isEmpty()&&!keywordCnList.isEmpty()) { // 包含中文与非中文关键字，只计算中文关键字
			keywordList.clear();
			keywordList.addAll(keywordCnList); //返回匹配的keyword,过滤非中文
			return shortestKeywordHandle(subject,keywordCnList,domain);
		}

		Matcher matcher = symbolPattern.matcher(subject);
		
		boolean startFlag=true;
		boolean endFlag=true;
		tmpStartIdx=0;
		while (matcher.find()) {
			if (matcher.end()>startIdx && startFlag) {
				startIdx = tmpStartIdx;
				startFlag=false;
			}
			if (matcher.start()>=endIdx && endFlag) {
				endIdx = matcher.start();
				if (endIdx-startIdx > 3) 
					endFlag=false;
			}
			tmpStartIdx=matcher.end();
		 }
		if (endFlag) 
			endIdx=subject.length();
		if (startFlag)
			startIdx=tmpStartIdx;
		
		if (startIdx >= endIdx || startIdx==-1 || endIdx==-1) 
			return subject;
		String tmpSubject = subject.substring(startIdx, endIdx).replaceAll(symbolTrimStr, "");
		keywordList.clear();
		keywordList.addAll(keywordCnList); //返回匹配的keyword,中文+非中文
		keywordList.addAll(keywordUnCnList);
		return tmpSubject.length() > 3 ? tmpSubject:subject;
	}

	/**
	 * 特殊标题处理
	 * @param subject
	 * @param domain
	 * @return
	 */
	private String specialHandle(String subject, String domain) {
		// 处理amazon订单
		Matcher amazonMatcher = amazonPattern.matcher(subject);
		if (amazonMatcher.find() && amazonMatcher.start()<3) {
			return amazonMatcher.group();
		}
		// 处理alibaba提醒
		Matcher alibabaMatcher = alibabaPattern.matcher(subject);
		if (alibabaMatcher.find() && alibabaMatcher.start()<3) {
			return alibabaMatcher.group();
		}
		// ctrip.com
		if (domain.equals("ctrip.com") && subject.contains("暂存订单") && subject.endsWith("提醒")) {
			return "暂存订单;;提醒";
		}
		
		// 处理agiso自动发货系统
		if (domain.equals("agiso.com") && subject.contains("自动发货")) {
			return "自动发货";
		}
		// 处理招聘
		if (domain.equals("info.ourats.com") && subject.startsWith("确认信")) {
			return "确认信";
		}
		
		if (domain.equals("alert-ci.oneapm.com") && subject.startsWith("【Cloudinsight 报警通知】")) {
			return "【Cloudinsight 报警通知】";
		}

		return null;
	}

	/**
	 * 替换乱码以及非中文字符
	 * @param subject
	 * @param keywordList
	 * @return replaceSubject
	 */
	private String replaceHandle(String subject, ArrayList<String> keywordList) {
		int len = subject.length();
		String replaceSubject=subject;
		// 处理乱码，相对于GBK
		for (int i=0; i<len;i++) {
			String charStr = replaceSubject.substring(i,i+1);
			if (!CodeUtils.isGBK(charStr)) {
				replaceSubject = replaceSubject.replaceFirst(charStr, ";;");
				len=replaceSubject.length();
				i++;
			}	
		}
		
		// 处理中其他文混杂
		int cnLen = calculateChinese(replaceSubject);
		int jpLen = calculateJpanese(replaceSubject);
		int otherLen = len-cnLen-jpLen;
		boolean isCnjp = (keywordList !=null && !keywordList.isEmpty() && isCnJp(keywordList.get(0))) ? true : false;
		if (isCnjp  && len > 18 && (cnLen+jpLen) > 5 && otherLen > len/2) {
			System.out.println(replaceSubject);
			replaceSubject = replaceSubject.replaceAll(unCnjpStr+"+", ";;");
			System.out.println(replaceSubject);
		}
		
		// 处理? 因为可能是乱码,不能匹配
		replaceSubject = replaceSubject.replaceAll("\\?", ";;");

		// 处理中文数字中文的问题，例如：采购订单3小时内盖章回传;;正式订单
		Pattern digitPattern = Pattern.compile(cnStr+"([\\d]+)"+cnStr);
		while (true) {
			Matcher digitMatcher = digitPattern.matcher(replaceSubject);
			if (digitMatcher.find()) {
				int startIdx = digitMatcher.start(1);
				int endIdx = digitMatcher.end(1);
				replaceSubject = replaceSubject.substring(0, startIdx) + ";;" + replaceSubject.substring(endIdx, replaceSubject.length());
			}
			else 
				break;
		}
		
		// 处理keyword只有0-1个字符
		// 处理;;的前后空格
		replaceSubject = replaceSubject.replaceAll("[\\s：:!！?？；;\\-\\d\\|]{3,}", ";;"); 
		replaceSubject = replaceSubject.replaceAll("(;;)[\\S\\s](;;)|(;;)[\\S\\s]$|^[\\S\\s](;;)", ";;"); 
		
		// 处理最后的前后符号
		replaceSubject = replaceSubject.replaceAll(symbolTrimStr, "");
		
		return replaceSubject;
	}
	
	public String evaluate(String subject) {
		try{
			if (subject==null || subject.trim().isEmpty()) {
				return "";
			}
			
			if(subject.length()<8)
				return subject;

			int index=subject.indexOf("         ");
			if(index>5)
				return subject.substring(0,index);
			
			char[] chs=subject.toCharArray();
			int endUnCnIndex=0;
			int startCnIndex=0;
			int unCnCount=0,cnCount=0;
			boolean flag1=false,flag3=false;
			for(int i=0;i<chs.length;i++)
			{
				if(isChinese(chs[i]))
				{
					if(!flag3)
					{
						startCnIndex=i; // 中文起始idx
						flag3=true;
					}
					flag1=false;
					unCnCount=0;
					cnCount++; //中文个数
				}
				else if(!flag1) // 前一个是中文
				{
					flag1=true;
					unCnCount++;
					endUnCnIndex=i; //最后一个中文后为非中文的idx
				}
				else if(flag1) //前一个非中文
				{
					unCnCount++; //最后一个中文后存在非中文个数
				}
			}
			if(cnCount<4)
				return subject;
			else if(endUnCnIndex>=5 && unCnCount > 3) // 中文+非中文的组合，排除中非中
				return subject.substring(startCnIndex,endUnCnIndex);
			else
				return subject.substring(startCnIndex);
		}catch(Exception e){
			return subject.trim();
		}
	}
	
	public String evaluate(String subject, ArrayList<String> keywordList, String domain) {
		try {
			if (subject==null) 
				return "";
			subject=subject.trim();
			if(subject.length()<5)
				return subject;
			
			// 特殊处理
			String specialSubject = specialHandle(subject, domain);
			if (specialSubject!=null)
				return specialSubject;
			
			// 包含关键字的最短子串
			String shortestSubject = shortestKeywordHandle(subject, keywordList, domain);
			
			// 替换乱码以及非中文字符
			String replaceSubject = replaceHandle(shortestSubject, keywordList);
			return replaceSubject;
		} catch (Exception e) {
			return subject;
		}
	}
	


	public static void main(String[] args) {
		RefineSubjectOld subject= new RefineSubjectOld();
		ArrayList<String> kws = new ArrayList<String>();
		try {
			File kwFile = new File("kw.txt");
			BufferedReader reader = new BufferedReader(new FileReader(kwFile));
			String line = null;
			while ((line = reader.readLine()) != null) {
				kws.add(line);
			}
			reader.close();

			File inputFile = new File("ttt.txt");
			reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter("res.txt",false));
			while ((line = reader.readLine()) != null) {
				ArrayList<String> kws2=(ArrayList<String>) kws.clone();

				//System.out.println(subject.evaluate("中国园林绿化网--中国园林绿化行业交流第一品牌用户注册邮件验证码", kws2, "agiso.com"));
				//kws2=(ArrayList<String>) kws.clone();
				
				String line1 = line.split("\t")[2];
				String line2 = line.split("\t")[0];
				writer.write(line2
						+ "\t" + line.split("\t")[1]
						+ "\t" + subject.evaluate(line1, kws2, line2)
						+ "\n");
			}
			reader.close();
			writer.flush();
			writer.close();

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
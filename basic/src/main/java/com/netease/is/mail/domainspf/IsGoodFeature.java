
package com.netease.is.mail.domainspf;

import org.apache.hadoop.hive.ql.exec.UDF;
/**
 * @author hzliuzhujie
 *
 */
public class IsGoodFeature extends UDF{
	
	public static boolean PhraseMatch(String s,String[] phrase) {
		if (s==null || s.trim().isEmpty()) {
			return false;
		}
		String subject=(" " +s).toLowerCase();
		for(int i=0;i<phrase.length;i++) {
			if (phrase[i]==null || phrase[i].trim().isEmpty()) {
				continue;
			}
			String phraseOne=phrase[i].trim().toLowerCase();
			if (phraseOne.matches("^[^\u4e00-\u9fa5]+$")) {//~chinese
				phraseOne = " " + phraseOne;
			} 
			if (subject.contains(phraseOne)) {
				return true;
			}
		}
		return false;
	}	
	
	
	//Global
	public final String[] importKeywordsN = {"密码","密碼","验证码","邮箱","账户","帳戶","账号","论坛","論壇","通行证","口令",
			"account","code","email","e-mail","password"};
	public final String[] importKeywordsV = {"確認","确认","找回","取回","重置","重设","重新设置","忘记",
			"更改","變更","变更","修改","绑定","验证","驗證","激活","注册",
			"activate","certif","change","confirm","recovery","reset","validat","verif"};
	
	public boolean evaluate(String subject) {
		boolean res = false;
		try {
			if (subject == null || subject.length() < 4) {
			} else if (PhraseMatch(subject, importKeywordsN) && PhraseMatch(subject, importKeywordsV)) {
				res = true;
			}
		} catch (Exception e) {
		}
		
		return res;
	}

	public static void main(String[] args) {
		IsGoodFeature isGoodFeature = new IsGoodFeature();
		System.out.println(isGoodFeature.evaluate("Activate your Hailin account"));
		System.out.println(isGoodFeature.evaluate("Password Reset for Libratone"));
		System.out.println(isGoodFeature.evaluate("你好验证电子邮箱"));
		System.out.println(isGoodFeature.evaluate("verify your e-mail address"));
		System.out.println(isGoodFeature.evaluate("Alert: Security Code Validation"));
	}
}
/**
 * 
 */
package com.java.learn.pinyin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author hzliuzhujie
 *
 */
public class Pinyin {

    //分词正则表达式
    public static String regEx  = "[^aoeiuv]?h?[iuv]?(ai|ei|ao|ou|er|ang?|eng?|ong|a|o|e|i|u|ng|n)?";
    public static String pinRegExString = "(a[io]?|ou?|e[inr]?|ang?|ng|[bmp](a[io]?|[aei]ng?|ei|ie?|ia[no]|o|u)|pou|me|m[io]u|[fw](a|[ae]ng?|ei|o|u)|fou|wai|[dt](a[io]?|an|e|[aeio]ng|ie?|ia[no]|ou|u[ino]?|uan)|dei|diu|[nl](a[io]?|ei?|[eio]ng|i[eu]?|i?ang?|iao|in|ou|u[eo]?|ve?|uan)|nen|lia|lun|[ghk](a[io]?|[ae]ng?|e|ong|ou|u[aino]?|uai|uang?)|[gh]ei|[jqx](i(ao?|ang?|e|ng?|ong|u)?|u[en]?|uan)|([csz]h?|r)([ae]ng?|ao|e|i|ou|u[ino]?|uan)|[csz](ai?|ong)|[csz]h(ai?|uai|uang)|zei|[sz]hua|([cz]h|r)ong|y(ao?|[ai]ng?|e|i|ong|ou|u[en]?|uan))";
    

    static boolean isMixAlpha(String word) {
    	word = word.toLowerCase();
    	return word.equals("g") || word.equals("r") || word.endsWith("n");
    }
    public static String split(String input) {
    	
        int tag = 0;
        List<String> pinList = new ArrayList<String>();
        String formatted = "";
        List<String> tokenResult = new ArrayList<String>();
        for (int i = input.length(); i > 0; i = i - tag) {
            Pattern pat = Pattern.compile(regEx);
            Matcher matcher = pat.matcher(input);
            if ( matcher.find()) {
            	String pin = matcher.group();
            	if (!pin.matches(pinRegExString) 
            			&& !tokenResult.isEmpty() && isMixAlpha(tokenResult.get(tokenResult.size()-1))) {
            		String pinNew = tokenResult.get(tokenResult.size()-1)+pin; 
            		if (pinNew.matches(pinRegExString)) {
            			pin=pinNew;
            			String prePin = pinList.get(pinList.size()-1);
            			pinList.remove(pinList.size()-1);
            			prePin=prePin.substring(0,prePin.length()-1);
            			pinList.add(prePin);
            			
            			tokenResult.remove(tokenResult.size()-1);
            			tokenResult.add(prePin.substring(prePin.length()-1,prePin.length()));
            		}
            	}
            	else if (!pin.matches(pinRegExString)) {
            		// 吧 pin拆分
            		pinList.add(pin.substring(0,1));
		            tag = 1;
		            tokenResult.add(pin.substring(0,1));
		            input = input.substring(tag);
            	}
            	
            	else if (pin.matches(pinRegExString)) {
	            	pinList.add(pin);
		            tag = matcher.end() - matcher.start();
		            tokenResult.add(input.substring(matcher.end()-1,matcher.end()));
		            input = input.substring(tag);
            	}
            }
        }
       
        if (!pinList.isEmpty())
        	formatted= StringUtils.join(pinList, "'");
        return formatted;
    }


    public static void main(String[] args) {
    	System.out.println("you408406284kongn:" + Pinyin.split("you408406284kongn"));
    	System.out.println("ke824601jiaoshi:" + Pinyin.split("ke824601jiaoshi"));
    	System.out.println("gu7831739517yaxie:" + Pinyin.split("gu7831739517yaxie"));
    	System.out.println("huaijiu810792:" + Pinyin.split("huaijiu810792"));
    	System.out.println("gexianb:" + Pinyin.split("gexianb"));
    	
        System.out.println("ljyvnfdw:" + Pinyin.split("ljyvnfdw"));
        System.out.println("menguai13999:" + Pinyin.split("menguai13999"));
    }
}
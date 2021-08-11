package com.java.learn.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MatcherTest {
	public static void main(String[] args) {
		// Pattern.split VS String.split
		String[] split1 = (Pattern.compile("\\d").split("20304"));
		String[] split2 = "20304".split("\\d");
		
		// Matcher.matches VS Pattern.matches VS String.matches
		boolean matches1 = Pattern.compile("\\d+").matcher("234").matches();
		boolean matches2 = Pattern.matches("\\d+", "234");
		boolean matches3 = "234".matches("\\d+");
		
		// Matcher.matches VS Matcher.lookingAt VS Matcher.find
		Matcher matcher1 = Pattern.compile("([a-z])\\d").matcher("a9r4y7");		
		System.out.println(matcher1.lookingAt());
		System.out.println(matcher1.matches());
		System.out.println(matcher1.find());

		// Matcher.start VS Matcher.end VS Matcher.group
		while (matcher1.find()) {
			System.out.println(matcher1.start(1));
			System.out.println(matcher1.end(1));
			System.out.println(matcher1.group(1));
		}
		
		// Matcher.replaceAll  VS String.replaceAll
		// Matcher.replaceFirst  VS String.replace
		Matcher matcher2 = Pattern.compile("\\d").matcher("我的QQ是:456456");
		System.out.println(matcher2.replaceFirst("*"));
		System.out.println("我的QQ是:456456".replace("Q", "*"));
		
		System.out.println(matcher2.replaceAll("*"));
		System.out.println("我的QQ是:456456".replaceAll("\\d", "*"));
		System.out.println("。。。。hfhhfh你好".replaceAll("^[^\u4e00-\u9fa5]+", ""));
		

    	String tt = "10";
    	System.out.println(tt.getClass().getName());
    	
    	String y = null;
    	y += "4";
    	System.out.println(y);
    	
    	String sss = "ddd||sss";
    	String []ss = sss.split("\\|\\|");
    	System.out.println(ss[0]);
    	System.out.println(ss[1]);
    	
    	
    	String url="http://musikpank.dk/home/alibaba/alibaba/index.php";
    	String urlDomain = "musikpank.dk";
    	Matcher matcher = Pattern.compile("((http|ftp|https)://)?(www\\.)?(.*"+urlDomain+ ")", Pattern.CASE_INSENSITIVE).matcher(url);
    	
    	if (matcher.find()) {
    		System.out.println(matcher.groupCount());
    		System.out.println(matcher.group());
    		System.out.println(matcher.group(0));
    		System.out.println(matcher.group(1));
    		System.out.println(matcher.group(2));
    		System.out.println(matcher.start());
    		System.out.println(matcher.start(2));
    		System.out.println(matcher.group());
    		System.out.println(matcher.group(0));
    		System.out.println(matcher.group(1));
    		System.out.println(matcher.group(2));
    		System.out.println(matcher.group(3));
    		System.out.println(matcher.group(4));
    	}
    	
	}

}

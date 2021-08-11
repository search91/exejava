package com.netease.is.mail.domainspf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class SubjectContainCheck extends UDF {
	public int evaluate(String subject, String goodKeyWord) {
		if ((subject == null) || ("".equals(subject))) {
			return 0;
		}
		if ((goodKeyWord == null) || ("".equals(goodKeyWord))) {
			return 0;
		}
		subject=subject.toLowerCase();
		goodKeyWord=goodKeyWord.toLowerCase();
		return subject.contains(goodKeyWord) ? 1 : 0;
	}
	
	public static void main(String[] args) {
		SubjectContainCheck subconCheck = new SubjectContainCheck();
		System.out.println(subconCheck.evaluate("con", "Con"));
	}
}

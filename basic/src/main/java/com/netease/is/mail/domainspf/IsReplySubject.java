/**
 * 
 */
package com.netease.is.mail.domainspf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;

public class IsReplySubject extends UDF {
	static String regex="^\\W?((R[Ee])|(F[Ww])|(答复)|(回复)|(转发)|(回覆)|(撤回)|(退信\\w{0,2})|(自动回复)|(自动转发)|(自动答复))\\W";
	static Pattern pattern = Pattern.compile(regex);
	public boolean evaluate(String subject) {
		boolean res = false;
		if (subject != null && !subject.trim().isEmpty()) {
			Matcher matter = pattern.matcher(subject.trim());
			if (matter.find()) {
				res = true;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		IsReplySubject isReplySubject = new IsReplySubject();
		System.out.println(isReplySubject.evaluate("Re: 用身份编码确认你的身份"));
		System.out.println(isReplySubject.evaluate("Fw:jhdgdjalh"));
		System.out.println(isReplySubject.evaluate("RE：jhdgdjalh"));
		System.out.println(isReplySubject.evaluate("退信提醒]: 您好,此邮箱不接收业务沟"));
	}
}
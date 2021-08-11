package com.netease.is.mail.domainspf;

import java.util.ArrayList;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 *
 * @author hzliuzhujie@corp.netease.com
 * @date 2017年02月16日
 */
public class SubjectContainChecker extends UDF {
    public ArrayList<String> evaluate(String subject, ArrayList<String> goodKeyWords) {
        ArrayList<String> res = null;
        if (subject == null || subject.trim().isEmpty()) {
            return null;
        }
        if (goodKeyWords==null || goodKeyWords.isEmpty()) {
            return null;
        }
        subject=" " + subject.toLowerCase();

        for (String goodKeyWord: goodKeyWords) {
            goodKeyWord=goodKeyWord.toLowerCase().trim();
            if (goodKeyWord.matches("^[^\u4e00-\u9fa5]+$")) {//~chinese
            	//goodKeyWord = " " + goodKeyWord; //标题没空格问题
			} 
            if (subject.contains(goodKeyWord)) {
                if (res==null) {
                    res = new ArrayList<String>();
                }
                res.add(goodKeyWord.trim());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubjectContainChecker subconCheck = new SubjectContainChecker();

        ArrayList<String> goodkws = new ArrayList<String>();
        goodkws.add("验证");
        goodkws.add("Vertify");
        goodkws.add("e-mail");

        System.out.println(subconCheck.evaluate("验证 邮箱", goodkws));
        System.out.println(subconCheck.evaluate("e-mail vertify", goodkws));
        System.out.println(subconCheck.evaluate("e-mailvertify", goodkws));
    }
}

/**
 *
 */
package com.netease.is.mail.domainspf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 *
 * @author hzliuzhujie@corp.netease.com
 * @date 2017年02月16日
 */
public class IsADSubject extends UDF {
    public boolean evaluate(String subject) {
        boolean res = false;
        if (subject != null && !subject.isEmpty()) {
            Pattern pattern = Pattern.compile("\\W+((AD)|(广告))\\W+$");
            Matcher matter = pattern.matcher(subject);
            if (matter.find()) {
                res = true;
            }

            pattern = Pattern.compile("^\\W?((AD)|(广告))\\W+");
            matter = pattern.matcher(subject);
            if (matter.find()) {
                res = true;
            }


        }
        return res;
    }

    public static void main(String[] args) {
        IsADSubject isADSubject = new IsADSubject();
        System.out.println(isADSubject.evaluate("jfhfjhjf(AD)"));
        System.out.println(isADSubject.evaluate("[AD]kfjdd"));
        System.out.println(isADSubject.evaluate("AD:jfhjfhd"));
        System.out.println(isADSubject.evaluate("(广告)fffss"));
        System.out.println(isADSubject.evaluate("ddddd<广告>"));
    }
}
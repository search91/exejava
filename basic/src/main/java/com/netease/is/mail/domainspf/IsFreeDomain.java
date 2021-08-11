package com.netease.is.mail.domainspf;

import java.util.ArrayList;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 *
 * @author hzliuzhujie@corp.netease.com
 * @date 2017年02月16日
 */
public class IsFreeDomain extends UDF {
    public boolean evaluate(String domain, ArrayList<String> freeDomains) {
        if (domain == null || domain.trim().isEmpty()) {
            return false;
        }
        if (freeDomains == null || freeDomains.isEmpty()) {
            return false;
        }
        domain = domain.trim();

        for (String freeDomain : freeDomains) {
            freeDomain = freeDomain.trim();
            if (domain.equalsIgnoreCase(freeDomain)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IsFreeDomain isFreeDomain = new IsFreeDomain();

        ArrayList<String> freedms = new ArrayList<String>();
        freedms.add("163.com");
        freedms.add("188.cn");

        System.out.println(isFreeDomain.evaluate("152.com", freedms));
    }
}

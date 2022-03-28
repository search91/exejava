package com.learn.leetcode.easy;

/**
 * 168. Excel表列名称
 *
 * @author hzliuzhujie
 * @date 2022-01-23
 **/
public class ConvertToTitle {

    public String convertToTitle(int columnNumber) {
        StringBuilder sb=new StringBuilder();
        while(columnNumber>0) {
            int zimu = columnNumber%26;
            columnNumber /= 26;
            char u = (char)(zimu+'A'-1);
            if(zimu==0) {
                u='Z';
                columnNumber--;
            }
            sb.insert(0, u);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ConvertToTitle convertToTitle= new ConvertToTitle();
        // FXSHRXW
         // System.out.println(convertToTitle.convertToTitle(2147483647));

        // ZY
        System.out.println(convertToTitle.convertToTitle(701));

    }
}

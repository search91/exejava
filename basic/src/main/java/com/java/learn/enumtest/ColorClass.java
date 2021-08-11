package com.java.learn.enumtest;

public class ColorClass {
    public enum Color {
        RED("红色", 1), 
        GREEN("绿色", 2), 
        BLANK("白色", 3), 
        YELLO("黄色", 4);//定义 enum 类型时候，如果是简单类型，那么最后一个枚举值后不用跟任何一个符号；但如果有定制方法，那么最后一个枚举值与后面代码要用分号';'隔开，不能用逗号或空格。
        // 成员变量
        private String name;
        private int index;

        // enum的构造方法，一定要private 或 friendly
        private Color(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.index + "_" + this.name;
        }
    }

    public static void main(String[] args) {
        System.out.println(Color.RED.toString());
    }
}

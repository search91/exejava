package com.java.learn.extend;

public class SonCase extends ParentCase {
    String printStr = "";
    
    @Override
    public String print() {
        printStr = super.print();
        return printStr;
    }
    
    public String getPrintStr() {
        return printStr;
    }
    
    public static void main(String[] args) {
        SonCase sonCase = new SonCase();
        CallParent callParent = new CallParent();
        callParent.setParentCase(sonCase);
        callParent.callPrint();
        SonCase parentCase = (SonCase)callParent.getParentCase();
        System.out.println("finally:" + parentCase.getPrintStr());
    }
}


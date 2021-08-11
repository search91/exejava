package com.java.learn.extend;

public class CallParent {
    
    private ParentCase parentCase;
    
    public void setParentCase(ParentCase parentCase) {
        this.parentCase = parentCase;
    }
    
    public ParentCase getParentCase() {
        return parentCase;
    }
    
    public void callPrint() {
        parentCase.print();
    }

}

package com.java.learn.object;
   
public class ObjectTest  
{  
    public static void main(String[] args)  
    {  
        Object to=new Object(); 
        to = true;
        //1.反射  
        System.out.println("to的类型:"+to.getClass().getSimpleName());  
        System.out.println(float.class.getSimpleName());  
        System.out.println(Float.class.getSimpleName());  
        //但是对于一个不确定类型的基本数据类型变量我们没法用反射来获取其类型。  
        System.out.println("----------------------");  
          
        //2.instanceof  
        if(to instanceof Float){ System.out.println("to是Float类型的");}  
        //但是这种办法貌似也没法确定基本数据类型  
        System.out.println("----------------------");  
          
    }  
}  


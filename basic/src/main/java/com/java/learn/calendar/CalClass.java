package com.java.learn.calendar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CalClass {
	public void passPara(Set<String> cc){
		cc.add("LZJ");
	}

	
	public static void main(String args[]){
		
		CalClass instanceCalClass = new CalClass();
		Set<String> cc = new HashSet<String>();
		instanceCalClass.passPara(cc);
		for (String str : cc) {    
		    System.out.println(str);    
		}  
			
		
		Calendar cal = Calendar.getInstance();
		
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		long curTime = cal.getTimeInMillis();
		cal.add(Calendar.MINUTE, 30);
		System.out.println(cal.getTime());
		cal.add(Calendar.DAY_OF_YEAR, -1);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		//��ʽ�����  
		
		System.out.println(df.format(curTime));  
		System.out.println(df.format(cal.getTimeInMillis()));
		
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_MONTH, -4);
    	Calendar calFirstDayInThisWeek = (Calendar) calendar.clone();
    	calFirstDayInThisWeek.add(Calendar.DAY_OF_MONTH, -1);
    	
    	Date date = calendar.getTime();
    	String yesterday = df.format(date);
    	System.out.println("the statistics day:" + yesterday);
    	
    	
    	int dayOfWeek = calFirstDayInThisWeek.get(Calendar.DAY_OF_WEEK);
    	System.out.println(dayOfWeek);
    	System.out.println(calFirstDayInThisWeek.getActualMinimum(Calendar.DAY_OF_WEEK));
    	calFirstDayInThisWeek.add(Calendar.DATE, calFirstDayInThisWeek.getActualMinimum(Calendar.DAY_OF_WEEK) - dayOfWeek + 1);
    	Date monDate = calFirstDayInThisWeek.getTime();
    	System.out.println("mon day:" + df.format(monDate));
    	try {
			String ddbMID = URLEncoder.encode("xtbBygDV41O+lOXsXAAAsQ", "UTF-8");
			System.out.println(ddbMID);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
	}

}

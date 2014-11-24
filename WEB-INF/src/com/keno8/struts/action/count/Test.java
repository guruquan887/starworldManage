package com.keno8.struts.action.count;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	
	public static void main(String[] args) {
		
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		//System.out.println(simpledate.format(date));
		//System.out.println("获得系统时间"+date.getTime());
		//System.out.println(time1);
		//System.out.println(simpledate.format(time1));
		
		 System.out.println(simpledate.format(date));   
         Calendar   d=Calendar.getInstance();   
         d.setTime(date);   
         int time = 20;
         d.add(Calendar.DAY_OF_MONTH,-time);   
         System.out.println(simpledate.format(d.getTime()));   
		}   

	}

package com.wiiy.struts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Class {
	
	static Random r = new Random();
	
	public static void main(String[] args) {
        // 日期的加减
        /*
         * java中对日期的加减操作 gc.add(1,-1)表示年份减一. gc.add(2,-1)表示月份减一.
         * gc.add(3.-1)表示周减一. gc.add(5,-1)表示天减一. GregorianCalendar类的add(int
         * field,int amount)方法表示年月日加减. field参数表示年,月.日等. amount参数表示要加减的数量.
         * 
         */
        try {
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                GregorianCalendar gc = new GregorianCalendar();
                System.out.println(df.format(gc.getTime()));
                String today = df.format(gc.getTime());
                gc.setTime(date);
                gc.add(5, -1);
                System.out.println(df.format(gc.getTime()));
        	} catch (Exception ex) {
        } 

/*			 int x = r.nextInt(999999); 
			 if(x > 100000) {
			  System.out.println(x);
			  return;
			 }*/
	}
			 
	}

package com.manage.struts.video;

import java.util.Random;

public class Test {
	
	public static void main(String[] args) {
		Random random = new Random();
		int vlength = random.nextInt(3)+1;
	   /* if(vlength <= 3200){
	    	vlength = vlength+3600;
	    } 
	    if(vlength > 3200 && vlength <= 3600){
	    	vlength = vlength + 3000;
	    }*/
	    
	    System.out.println(vlength);
	}

}

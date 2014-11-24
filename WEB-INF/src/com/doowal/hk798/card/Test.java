package com.doowal.hk798.card;

public class Test {
	
	public static void main(String[] args) {
		String strInfo = "";
		strInfo = "123456789";
		String rValue = "";
		for(int i=0;i<9;i++){
			int iss = GetRand(1,strInfo.length());
			if(iss>strInfo.length()){
				iss = strInfo.length()-2;
			}
			rValue = rValue+strInfo.substring(iss,iss+1);
		}
		
		System.out.println(rValue);
	}
	
	public static int GetRand(int min,int max){
		int rValue = 0;
		rValue = ((int)((max - min + 1) * Math.random()) + min);
		System.out.println("GetRand;Rvalue:"+rValue);
		return rValue;
		
	}

}

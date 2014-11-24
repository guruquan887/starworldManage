package com.manage.struts.video;

import java.util.Arrays;
import java.util.Random;

public class RanNumber {
	
    public static void main(String[] args) {   
		
//   	   String[] s = {"kp1","kp2","kp3","kp4","kp5"}; //开牌
//
//   	   String[] d = {"dd1","dd2","dd3","dd4"}; //等待
//    	
//	   sortArray(s,d,50);
//	   
//		Random random = new Random();
//	    int vlength = random.nextInt(7200);
//	    if(vlength <3600){
//	    	vlength = vlength+3600;
//	    }
//	    
//	    
//	    
//	    int count = 23;  //某一房间类型的视频个数
//	    int sumVlength = 854 ; //视频总长度秒数
//	    
//	    int avg = sumVlength / count;
//	    System.out.println("avg:"+avg);
    }
	
	
	//数组随机排序
	public synchronized static String[] sortArray (String[] s,String[] d, int vLength ){  //通过DAO传值随机排序
		
	 //   String[] s = {"kp1","kp2","kp3","kp4","kp5"}; //开牌

	 //   String[] d = {"dd1","dd2","dd3","dd4"}; //等待
		
	    int len = vLength;
	    
	    String[] result= new String[len*2];
	    
	    s = sort_Array( sort_Array(s, vLength) );
	    
	    d = sort_Array( sort_Array(d, vLength) );
//	    System.out.println("s-result:" + Arrays.toString(s));
//	    System.out.println("d-result:" + Arrays.toString(d));
        
	    for(int i = 0 ; i < result.length ; i++){
	    	
	    	if((i+1)%2 != 0){
	    		result[i] = s[len-1];
	    		len = len -1;
//	    		System.out.println("len1="+len1);
	    	}else{
	    		result[i] = d[len];
//	    		System.out.println("len2:"+len2);
	    	}
	    }
	    System.out.println("result:" + Arrays.toString(result));
	    
	    
	    return result ;
	   
	}



	public synchronized static String[] sort_Array (String [] s ){ //所有的数据打乱排序
		
        int len = s.length; 
        
        String[] result= new String[len];
	    
		 Random random = new Random();
		    
		 for (int i = 0; i < len; i++) {    

			 int r = random.nextInt(len - i);    

			 result[i] = s[r];    

			 s[r] = s[len - 1 - i];    
		  }     
		
	    return result ;
	}
	
	public synchronized static String[] sort_Array (String [] s , int vlength ){  //填充造型
		
        int len = s.length; 
        
        String[] result= new String[vlength];
	    
		int count = 1 ;
		
		int last = vlength;
//        System.out.println(">>>>>lastLength:"+last);		
		 
		 while( last > 0 ){
			 
			 if( last >= len ){
			 
				 for (int i = 0; i < len; i++) {    
		
					 result[i+(count-1)*len] = s[i];    
		
				  }
				 
			 }else{
				 
				 for (int i = 0; i < last; i++) {    
						
					 result[i+(count-1)*len] = s[i];    
		
				  }
			 }
			 
			 last = vlength - len * count;
			 count ++;
//			 System.out.println(">>lastLength:"+last);
		 }
	    return result ;
	}
	

}

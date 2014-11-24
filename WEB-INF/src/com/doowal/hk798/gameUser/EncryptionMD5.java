package com.doowal.hk798.gameUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

public class EncryptionMD5 {

	private static Logger logger = Logger.getLogger(EncryptionMD5.class.getName());
	
//	public EncryptionMD5(){
//		
//	}
//	
//	 public  String encryption_MD5(String normalStr){
//	     String encryptedStr = ""; 
//		 try{
//	        logger.info("����ǰ�ַ�"+normalStr);
//	        MessageDigest md5=MessageDigest.getInstance("MD5");
//	        BASE64Encoder base64en = new BASE64Encoder();
//	        encryptedStr=base64en.encode(md5.digest(normalStr.getBytes("utf-8")));
//	        logger.debug("���ܺ��ַ� "+encryptedStr);
//	      }
//		  catch(Exception exception){
//	          exception.printStackTrace();
//	      }
//	        return encryptedStr;
//
//	     }
	 
	    private synchronized static byte[] encode(String origin) {
	        byte[] hash = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.reset();
	            hash = md.digest(origin.getBytes());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return hash;
	    }

	    public synchronized static String encryption_MD5(String origin) {
	        String result = "";
	        logger.info("����ǰ�ַ� :"+origin);
	        byte[] hash = EncryptionMD5.encode(origin);
	        for (int i = 0; i < hash.length; i++) {
	            int itemp = hash[i]&0xFF;
	            if(itemp<16) result += "0";
//	            result += Integer.toString(itemp, 16).toUpperCase();//��д
	            result += Integer.toString(itemp, 16);
	        }
	        logger.info("���ܺ��ַ� :"+result);
	        return result;
	    }

	    public synchronized static boolean isPassword(String origin, String result) {
	        if (EncryptionMD5.encryption_MD5(origin).equals(result)) {
	            return true;
	        }
	        return false;
	    }
	    public static void main(String[] args){
	        String result = "";
	        result = EncryptionMD5.encryption_MD5("WJ^_^520");
	        System.out.println(result+" Length:"+result.length());
	    }
}

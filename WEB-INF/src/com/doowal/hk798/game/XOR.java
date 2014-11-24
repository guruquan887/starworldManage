package com.doowal.hk798.game;

import java.io.ByteArrayOutputStream;   
import java.io.IOException;   
import java.io.UnsupportedEncodingException;
  
public class XOR {   
  
    public static String encode(String str, String key) {   
        String result = null;   
        byte[] strBuf = str.getBytes();   
        byte[] keyBuf = key.getBytes();   
        int c = 0;   
        int z = keyBuf.length;   
        ByteArrayOutputStream baos = new ByteArrayOutputStream(strBuf.length);   
        for (int i = 0; i < strBuf.length; i++) {   
            byte bS = strBuf[i];   
            byte bK = keyBuf[c];   
            byte bO = (byte) (bS ^ bK);   
            if (c < z - 1) {   
                c++;   
            } else {   
                c = 0;   
            }   
            baos.write(bO);   
        }   
        try {   
            baos.flush();   
            result = baos.toString();   
            baos.close();   
            baos = null;   
        } catch (IOException io) {   
            io.getStackTrace();   
        }   
        return result;   
    }   
  
    public static void main(String[] args) {   
        String result = XOR.encode("sa","fds");   
        try {
			result = new String(result.getBytes("ISO_8859_1"),"UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        System.out.println(result);   
        String s = XOR.encode("sa", "fds");   
        System.out.println(s);   
    }   
  
}  


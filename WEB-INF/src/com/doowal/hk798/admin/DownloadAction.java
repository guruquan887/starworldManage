// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package com.doowal.hk798.admin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * 
 */
public class DownloadAction extends Action {

	// --------------------------------------------------------- Instance
	// Variables
	protected static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	// --------------------------------------------------------- Methods

	/**
	 * Method execute
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String action = "download";
		if (request.getParameter("action") != null)
			action = request.getParameter("action");

		
		
		if("download".equals(action)){
			String filename="";
			if(request.getParameter("filename")!=null){
				filename=request.getParameter("filename");
				filename=new String(filename.getBytes("ISO_8859_1"),"GBK");
				System.out.println("fileName:"+filename);
				//EncodeURI.(filename);
			}
			if(filename.trim().length()>0){
				
				File f = new File("E:\\dbbackup\\"+filename);
				FileInputStream fis = null;
				fis = new FileInputStream(f);
				long filesize=fis.available();
				System.out.println("filename:"+filename+",filesize:"+filesize);
				String contentType = "application/x-msdownload";
				try {
						response.reset();
						response.setContentType(contentType);
						response.setContentLength((int)filesize);
						response.setHeader("Content-Disposition","attachment;filename="+toUtf8String(filename));
						copy(fis, response.getOutputStream());
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally {
					if (fis != null) {
						fis.close();
					}
				}
			}
			
		}
		
		
		
		
		return null;
	}
	
	

	protected int getBufferSize() {
		return DEFAULT_BUFFER_SIZE;
	}

	public InputStream getInputStream(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		return bis;
	}

	/**
	 * Copy bytes from an <code>InputStream</code> to an
	 * <code>OutputStream</code>.
	 * 
	 * @param input
	 *            The <code>InputStream</code> to read from.
	 * @param output
	 *            The <code>OutputStream</code> to write to.
	 * 
	 * @return the number of bytes copied
	 * 
	 * @throws IOException
	 *             In case of an I/O problem
	 */
	public int copy(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[getBufferSize()];
		int count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	/**
	 * ���ļ����еĺ���תΪUTF8����Ĵ�,�Ա�����ʱ����ȷ��ʾ�����ļ���.
	 * 
	 * @param s
	 *            ԭ�ļ���
	 * @return ���±������ļ���
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}

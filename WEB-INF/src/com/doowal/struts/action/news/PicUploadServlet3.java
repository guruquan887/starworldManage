package com.doowal.struts.action.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class PicUploadServlet3 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PicUploadServlet3() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SmartUpload su = new SmartUpload();
			PrintWriter pt=response.getWriter();
			ServletConfig config = getServletConfig();
			su.initialize(config, request, response);
			su.setMaxFileSize(1024 * 1024);
			// su.setTotalMaxFileSize(5*1024*1024);
			su.setAllowedFilesList("jpg,gif,bmp");
			su.upload();
			
			Request myRequest=su.getRequest();
			SimpleDateFormat smt=new SimpleDateFormat("yyyyMMddHH");
			File file = su.getFiles().getFile(0);
			String docname=null,picpath=null;
			if(!file.isMissing()){
				//String fileName=file.getFieldName();
				String fileName=file.getFileName();
				String filePathName=file.getFilePathName();
				String fileExtName=file.getFileExt();
				Date dt=new Date(System.currentTimeMillis());
				picpath=smt.format(dt)+"."+fileExtName;
				docname="/images/"+fileName;
				//docname="/pic/"+filePathName+"."+fileExtName;
			}
			file.saveAs(docname,File.SAVEAS_VIRTUAL);
			pt.println("�ϴ��ɹ�");
			response.sendRedirect("news/imgOk.jsp?docname="+docname);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

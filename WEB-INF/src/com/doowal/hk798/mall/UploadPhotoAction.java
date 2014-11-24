package com.doowal.hk798.mall;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



import ahxu.commons.upload.DiskFileUploadEx;

/**
 * @author xye
 */
public class UploadPhotoAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") == null)
		{
			request.setAttribute("msg","您还没登录，或者登录已经超时");
			return mapping.findForward("managemsg");
		}
		

		String fileName = "";

		String fileType = "";
		String newFileName = "";

		long size;

		List fileItemList = null;
		/* ������� */
		DiskFileUploadEx fu = new DiskFileUploadEx();
		/* ���������ϴ������ֵ�������ֵ���������ϴ� */
		fu.setSizeMax(1*1024*1024);
		/* �����������ϴ����ļ����� */
		fu.setAllowField(true);
		String tempPath =servlet.getServletContext().getRealPath("incoming/");
		//String tempPath //= request.getContextPath() + "temp";
		String tempPath1 = request.getContextPath();
		System.out.println(">>>>>>>>>>>>>>"+tempPath);
		System.out.println(">>>>>>>>>>>>>>"+tempPath1);
		File td = new File(tempPath);
		if (!td.exists()) {
			System.out.println("No DIR:" + tempPath);
			td.mkdirs();
		}
		fu.setRepositoryPath(tempPath);
		fu.setAllowFiles(".jpg,.gif,.tiff,.bmp,.npg,.psd,.png");
		// fu.setAllowField(true);
		fu.setSizeThreshold(2*1024*1024);
		String no="";
		try {
			/* ִ���ϴ���̣����õ��ϴ����ļ��б� */
			fileItemList = fu.parseRequestEx(request);
			Iterator fileItemListIte = fileItemList.iterator();

			while (fileItemListIte.hasNext()) {
				FileItem file = (FileItem) fileItemListIte.next();
				if (!file.isFormField()) {
					fileName = format_charset(file.getName());
					size = file.getSize();

					if ((fileName == null || fileName.equals("")) && size == 0)
						continue;
					fileName = fileName.replace('\\', '/');
					String[] split = fileName.split("/");
					fileName = split[split.length - 1];
					for (int i = fileName.lastIndexOf('.') + 1; i < fileName
							.length(); i++)
						fileType += fileName.charAt(i);
					fileType = fileType.toLowerCase();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
					String time=sdf.format(Calendar.getInstance().getTime());
					//if(doctype.length()>14)
					//doctype=doctype.substring(0,14);
					newFileName=time+"."+fileType;
					 File target = new File(tempPath + "\\"+newFileName);
					 file.write(target);

				}
				else if ("no".equals(file.getFieldName())) {
					no = format_charset(file.getString());
				}
				// System.out.println(file.getString() + "<br>");
			}

			request.setAttribute("photourl",newFileName);
			request.setAttribute("message", "添加成功！");
			System.out.println("添加成功！");
			

		} catch (Exception e) {
			System.out.println("上传失败<br>");
			request.setAttribute("message", "添加失败！");
			e.printStackTrace();
			if (e instanceof DiskFileUploadEx.InvalidFileUploadException) {
				System.out.println("文件类型不被允许");
				
				request.setAttribute("message", "文件类型不被允许");
			}
		} finally {
			fu.dispose();
		}
		no=request.getParameter("no");
		return mapping.findForward("success"+no);
	}

	private String format_charset(String source) {
		byte[] charbuffer;
		if (source == null || source.trim().equals(""))
			return null;
		charbuffer = source.getBytes();
		try {
			return new String(charbuffer, "GBK");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}

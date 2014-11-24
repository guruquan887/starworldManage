package com.keno8.struts.action.datemanage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.OperateDTO;

public class DataManageAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "list";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		if("countList".equals(action)){
			
			DataSource ds = this.getDataSource(request, "Keno8");
			File file_dir = new File("E:\\dbbackup\\");
			if (!file_dir.exists()) {
				file_dir.mkdir();
			}
			SimpleDateFormat f1 = new SimpleDateFormat("(yyyy年MM月dd日HH_mm_ss)");
			Date d = Calendar.getInstance().getTime();
			DataManageDAO dao = new DataManageDAO(ds);
			String targetName = "kaileba数据库" + f1.format(d) + ".bak";
			System.out.println(targetName);
			String filename = file_dir + "kaileba数据库"+ f1.format(d) + ".bak";
			dao.backup(targetName);
			request.setAttribute("msg", "备份成功!");
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"备份名为"+targetName+"的数据库";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			
			return mapping.findForward("countList");
			
		}
		else if("list".equals(action)){
			File file_dir = new File("E:\\dbbackup\\");
			if (!file_dir.exists()) {
				file_dir.mkdir();
			}
			String[] dir = new java.io.File("E:\\dbbackup\\").list(); 
			List<FileDTO> list=new ArrayList<FileDTO>();
			for(int i=0;i<dir.length;i++){
				FileDTO dto=new FileDTO();
				dto.setFileName(dir[i]);
				dto.setEncodeFileName(URLEncoder.encode(dir[i]));
				File f = new File("E:\\dbbackup\\"+dir[i]);
				
				if (f.exists()) {
					 FileInputStream fis = null;
	
				     fis = new FileInputStream(f);
				     dto.setFileSize(fis.available());
				     //dir[i]=dir[i]+"("+fis.available()+ " bytes)";
				     //System.out.println("File has " + fis.available()+ " bytes");
				     fis.close();
				} else {
				    System.out.println("文件不存在");
				}
				list.add(dto);
			}
			
			/* 写入日志记录 */
			DataSource ds = this.getDataSource(request, "Keno8");
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入数据管理页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			
			request.setAttribute("files", list);

			return mapping.findForward("datamanage");
		}
		else if("delete".equals(action)){
			String filename="";
			if(request.getParameter("filename")!=null){
				filename=request.getParameter("filename");
				filename=new String(filename.getBytes("ISO_8859_1"),"GBK");
				System.out.println("fileName:"+filename);
				//EncodeURI.(filename);
			}
			boolean success =(new File("E:\\dbbackup\\"+filename)).delete();   
			if(success){
				request.setAttribute("msg", filename+"删除成功!");
			}
			else{   
	             request.setAttribute("msg", filename+"删除失败!");    
	        }
			/* 写入日志记录 */
			DataSource ds = this.getDataSource(request, "Keno8");
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"删除数据名为"+filename+"的文件";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */

			return mapping.findForward("countList");
		}
		else if("deleteList".equals(action)){
			int time = Integer.parseInt(request.getParameter("time"));
			SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Calendar d=Calendar.getInstance(); 
			d.setTime(date);   
	        d.add(Calendar.DAY_OF_MONTH,-time); 
	        String time2 = simpledate.format(d.getTime());
	        System.out.println(">>>>>>>>>>>>"+time2);
	        request.setAttribute("time2", time2);
			/* 写入日志记录 */
			DataSource ds = this.getDataSource(request, "Keno8");
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"查找到数据库"+time2+"之前的数据";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			return mapping.findForward("deleteRecord");
			
		}
		
		else if("deleteRecord".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			DataManageDAO dao = new DataManageDAO(ds);
			int time = Integer.parseInt(request.getParameter("time"));
			SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Calendar d=Calendar.getInstance(); 
			d.setTime(date);   
	        d.add(Calendar.DAY_OF_MONTH,-time); 
	        String time2 = simpledate.format(d.getTime());
	        System.out.println(">>>>>>>>>>>>"+time2);
			int r = dao.delete(time2);
			if(r ==1){
				request.setAttribute("msg", "恭喜，操作成功!");
			}
			else{
				request.setAttribute("msg", "服务器出现异常，请联系管理员!");
			}
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"删除数据库"+time2+"之前的数据";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			return mapping.findForward("deleteRecord");
		}
		return mapping.findForward(null);
	}
	
	

}

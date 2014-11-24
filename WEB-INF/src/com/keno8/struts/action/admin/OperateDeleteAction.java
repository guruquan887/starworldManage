package com.keno8.struts.action.admin;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.action.datemanage.DataManageDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.OperateDTO;

public class OperateDeleteAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		if("deleteList".equals(action)){
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
			String operateDetails = username+"查找到数据库日志"+time2+"之前的数据";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			return mapping.findForward("deleteRecord");
		}
		
		else if("deleteLogs".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			OperateLogsDAO dao = new OperateLogsDAO(ds);
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
			return mapping.findForward("deleteLogs");
		}
		return mapping.findForward(null);
	}
	
	

}

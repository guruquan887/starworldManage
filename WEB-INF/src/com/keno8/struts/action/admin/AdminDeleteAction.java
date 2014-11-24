package com.keno8.struts.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.AdminDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.AdminDTO;
import com.keno8.struts.dto.OperateDTO;

public class AdminDeleteAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		DataSource ds = this.getDataSource(request,"Keno8");
		AdminDAO dao = new AdminDAO(ds);
	    int id = Integer.parseInt(request.getParameter("id"));
		AdminDTO dto = dao.getById(id);
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"删除<font color='red'>"+dto.getUserName()+"</font>后台用户";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		if(id!=0){
			dao.delete(id);
		}
		forward = "success";
		return mapping.findForward(forward);

	}

}

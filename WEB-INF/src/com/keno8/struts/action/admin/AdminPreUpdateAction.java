package com.keno8.struts.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.AdminDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dao.RoleDAO;
import com.keno8.struts.dto.AdminDTO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.RoleDTO;

public class AdminPreUpdateAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		HttpSession s=request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		DataSource ds = this.getDataSource(request,"Keno8");
		AdminDAO dao = new AdminDAO(ds);
		AdminDTO admin = dao.getById(id);
		request.setAttribute("ADMIN", admin);
		
		RoleDAO dao1 = new RoleDAO(ds);
		List<RoleDTO> rol=dao1.select();
		request.setAttribute("ROL", rol);
		
		request.getSession().setAttribute("updateName", admin.getUserName());
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = "进入修改"+admin.getUserName()+"基本信息页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		forward = "success";
		return mapping.findForward(forward);
	}
}

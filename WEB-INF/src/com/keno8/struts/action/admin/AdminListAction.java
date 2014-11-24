package com.keno8.struts.action.admin;

import java.util.List;

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

public class AdminListAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		DataSource ds = this.getDataSource(request, "Keno8");
		String username = (String)request.getSession().getAttribute("username");
		AdminDAO dao = new AdminDAO(ds);
		List<AdminDTO> list = dao.findAll(username);
		request.setAttribute("admin", list);
		request.setAttribute("username", request.getSession().getAttribute("username"));
		
		/* 写入日志  */
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = "进入后台管理账户列表";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*        */
		forward = "success";
		return mapping.findForward(forward);
	}
}

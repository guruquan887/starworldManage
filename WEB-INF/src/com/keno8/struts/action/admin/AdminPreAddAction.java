package com.keno8.struts.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dao.RoleDAO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.RoleDTO;


public class AdminPreAddAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		DataSource ds=this.getDataSource(request,"Keno8");
		RoleDAO dao = new RoleDAO(ds);
		List<RoleDTO> rol=dao.select();
		request.setAttribute("ROL", rol);
		forward = "success";
		
		/* 写入日志  */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入增加后台账户页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*        */
		return mapping.findForward(forward);
	}
	
	

}

package com.keno8.struts.action.admin;


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

public class AdminAddAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		String userName=request.getParameter("userName");
		
		String password=request.getParameter("password");
		String roleId=request.getParameter("roleId");
		String realName = request.getParameter("realName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String loginIP = request.getParameter("loginIP");
		String loginTime = request.getParameter("loginTime");
		HttpSession session=request.getSession();
		AdminDTO admin=(AdminDTO)session.getAttribute("USER");
		if(admin==null){
			return mapping.findForward("index");
		}
		
		AdminDTO dto = new AdminDTO();
		dto.setUserName(userName);
		dto.setPassword(password);
		dto.setRoleId(Integer.parseInt(roleId));
		dto.setRealName(realName);
		dto.setPhone(phone);
		dto.setAddress(address);
		dto.setLoginTime(loginTime);
		dto.setLoginIP(loginIP);
		DataSource ds = this.getDataSource(request,"Keno8");
		AdminDAO dao = new AdminDAO(ds);
		if(dao.checkExist(userName)){
			request.setAttribute("msg", "添加失败，用户名已经存在");
		}
		else{
			dao.add(dto,admin.getId(),admin.getParents()+"<"+admin.getId()+">");
			AdminDTO at=dao.checkUserName(userName, password);
			int t_userid=at.getId();
			int t_roleId=at.getRoleId();
			RoleDAO rd=new RoleDAO(ds);
			rd.copyuthority(t_userid, t_roleId);
			forward = "success";
			
			/* 写入日志  */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"增加一个账户,信息为：用户名=<font color='red'>"+userName+"</font>;密码="+password+";真实姓名=<font color='red'>"+realName+"</font>;电话=<font color='red'>"+phone+"</font>;地址=<font color='red'>"+address+"</font>";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*        */
		}
		return mapping.findForward(forward);
	}

}

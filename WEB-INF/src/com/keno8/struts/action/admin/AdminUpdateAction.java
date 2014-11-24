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

public class AdminUpdateAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		String action="";
		if(request.getParameter("action")!=null){
			action=request.getParameter("action");
		}
		DataSource ds = this.getDataSource(request,"Keno8");
		if("bdmachine".equals(action)){
			
			AdminDAO dao = new AdminDAO(ds);
			//String userName=(String)request.getSession().getAttribute("username");
			String userName = "";
			if(request.getParameter("username")!=null){
				userName = request.getParameter("username");
			}
			
			/* 写入日志记录 */
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = userName+"点击了绑定机器码操作";
			operateDto.setOperateName(userName);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			request.setAttribute("msg", dao.bdMachine(userName));
			return mapping.findForward("success");
			
			
		}
		else if("qxmachine".equals(action)){
			
			AdminDAO dao = new AdminDAO(ds);
			//String userName=(String)request.getSession().getAttribute("username");
			String userName = "";
			if(request.getParameter("username")!=null){
				userName = request.getParameter("username");
			}
			/* 写入日志记录 */
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = userName+"点击了取消机器码操作";
			operateDto.setOperateName(userName);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			request.setAttribute("msg", dao.qxMachine(userName));
		}
		else{
			
			String id = request.getParameter("id");
			String password=request.getParameter("password");
			String roleId=request.getParameter("roleId");
			System.out.println(roleId);
			String realName = request.getParameter("realName");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			
			AdminDTO dto = new AdminDTO();
			dto.setId(Integer.parseInt(id));
			dto.setPassword(password);
			dto.setRoleId(Integer.parseInt(roleId));
			dto.setRealName(realName);
			dto.setPhone(phone);
			dto.setAddress(address);
			
			AdminDAO dao = new AdminDAO(ds);
			if(dao.update(dto)){
				request.setAttribute("msg", "修改成功");
			}
			else{
				request.setAttribute("msg", "数据库错误，修改失敗!");
			}
			
			String updateName = (String)request.getSession().getAttribute("updateName");
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = "修改"+updateName+"的基本信息为：密码="+password+";realName="+realName+";phone="+phone+";address="+address;
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			
		}
		forward = "success";
		return mapping.findForward(forward);
	}
}

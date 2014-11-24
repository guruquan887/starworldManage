package com.keno8.struts.action.admin;

import java.sql.Connection;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beartool.MD5;
import com.keno8.struts.dao.AdminDAO;
import com.keno8.struts.dao.AuthorityMenuViewDAO;
import com.keno8.struts.dto.AdminDTO;
import com.keno8.struts.dto.AuthorityMenuViewDTO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dao.OperateLogsDAO;

public class LoginAction extends Action {

	Timer timer = new Timer();
	HttpServletRequest request;
	HttpServletResponse response;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		this.request = request;
		this.response = response;
		String title = "keno8后台管理中心";
		request.getSession().setAttribute("title", title);
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		String forward = "failure";
		String username = request.getParameter("username");
/*		String mid1="";
		String mid2="";
		if(request.getParameter("mid1")!=null){
			mid1=request.getParameter("mid1");
		}
		if(request.getParameter("mid2")!=null){
			mid2=request.getParameter("mid2");
		}
		System.out.println("mid1:"+mid1+",mid2:"+mid2+"用户名:"+username);*/
		String password = request.getParameter("password");
		MD5 md5=new MD5();
		//String machineSerial=md5.getMD5ofStr(mid1+mid2);
		String machineSerial = "";
		if(request.getParameter("ms")!=null){
			machineSerial=request.getParameter("ms");
		}
		String machineSerial1=md5.getMD5ofStr("");
		if(machineSerial.equals(machineSerial1)){
			machineSerial="";
		}
		System.out.println("machineSerial:"+machineSerial+",machineSerial1:"+machineSerial1);
		Connection con=null;
		DataSource ds = this.getDataSource(request, "Keno8");
		con = this.getDataSource(request, "Keno8").getConnection();
		AdminDAO dao = new AdminDAO(ds);
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String loginIP=request.getRemoteAddr();
		AdminDTO dto = dao.checkUser(username, password,machineSerial);
		HttpSession session=request.getSession();
		if (dto != null) {
			AuthorityMenuViewDAO adao = new AuthorityMenuViewDAO(con);
			String ip = request.getRemoteAddr();
			adao.update(ip,username);
			List<AuthorityMenuViewDTO> topmenu = adao.select(dto.getId(), 0);
			session.setAttribute("topmenu", topmenu);
			for (AuthorityMenuViewDTO topmenuitem: topmenu) {
				List<AuthorityMenuViewDTO> submenu = adao.select(dto.getId(), topmenuitem.getMenuid());
				session.setAttribute("submenu"+topmenuitem.getMenuid(), submenu);
			}
			request.getSession().setAttribute("USER", dto);
			String username1 = dto.getUserName();
			String password1 = dto.getPassword();
			System.out.println("用户名"+username1+"密码"+password1);
			request.getSession().setAttribute("username",username1);
			request.getSession().setAttribute("password",password1);
			
			/* 写入日志记录 */
			request.getSession().setAttribute("ip", ip);
			String operateDetails = "用户登录,停留在信息统计页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*    */
			forward = "success";
		}
		else{
			request.setAttribute("msg","用户名或密码不正确,或者你的机器与账号绑带的机器码不符!");
		}
		if(con!=null)
			con.close();
		System.out.println(forward);
		return mapping.findForward(forward);
	}
}

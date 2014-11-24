package com.doowal.hk798.login;

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

import com.doowal.hk798.gameUser.EncryptionMD5;
import com.doowal.hk798.admin.NetCSDAO;
import com.doowal.hk798.admin.NetCSDTO;

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
		String title = "后台管理中心";
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String forward = "failure";

		String username = request.getParameter("username");
		System.out.println(username);
		String password = "";
		if(request.getParameter("password")!=null){
			password = EncryptionMD5.encryption_MD5(request.getParameter("password").trim());
		}
		
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		AdminDAO dao = new AdminDAO(ds);
		AdminDTO dto = dao.checkUser(username, password);
		NetCSDAO csdao = new NetCSDAO(ds);
		NetCSDTO csdto = csdao.getQPAdminSiteInfo();
		request.getSession().setAttribute("title", csdto.getSiteName()); //获得后台管理公告
		request.getSession().setAttribute("copyRight", csdto.getCopyRight()); //获得版权信息
		HttpSession session=request.getSession();
		if (dto != null) {
			AuthorityMenuViewDAO adao = new AuthorityMenuViewDAO(ds);
			String ip = request.getRemoteAddr();
			dao.updateTime(ip,username);
			List<AuthorityMenuViewDTO> topmenu = adao.select(dto.getId(), 0);
			session.setAttribute("topmenu", topmenu);
			for (AuthorityMenuViewDTO topmenuitem: topmenu) {
				List<AuthorityMenuViewDTO> submenu = adao.select(dto.getId(), topmenuitem.getMenuid());
				session.setAttribute("submenu"+topmenuitem.getMenuid(), submenu);
			}
			request.getSession().setAttribute("username",dto.getUserName());
			request.getSession().setAttribute("roleId", dto.getRoleId());
			System.out.println("获取roleId为："+dto.getRoleId());
			request.getSession().setAttribute("ip", ip);
			request.getSession().setAttribute("USER", dto);
			request.getSession().setAttribute("Id", dto.getId());
			forward = "success";
		}
		else{
			request.setAttribute("msg", "用户名或密码不正确！");
		}
		System.out.println(forward);
		return mapping.findForward(forward);
	}

}

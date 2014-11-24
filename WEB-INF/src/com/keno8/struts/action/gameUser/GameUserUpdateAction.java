package com.keno8.struts.action.gameUser;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.GameUserDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.GameUserDTO;
import com.keno8.struts.dto.OperateDTO;


public class GameUserUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		int userID = Integer.parseInt(request.getParameter("userID"));
		String accounts = request.getParameter("accounts");
		String nickName = request.getParameter("nickName");
		String loginPass = request.getParameter("loginPass");
		int sex = 0;
		if(request.getParameter("sex")!=null){
			sex = Integer.parseInt(request.getParameter("sex"));
		}
	    String email = request.getParameter("email");
	    String telphone = request.getParameter("telphone");
	    
		GameUserDTO dto = new GameUserDTO();
		dto.setUserID(userID);
		dto.setAccounts(accounts);
		dto.setNickName(nickName);
		dto.setSex(sex);
		dto.setEmail(email);
		dto.setTelphone(telphone);
		dto.setLoginPass(loginPass);
		
		DataSource ds = this.getDataSource(request, "Keno8");
		GameUserDAO dao = new GameUserDAO(ds);
		dao.update(dto);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"修改"+accounts+"用户信息：登录密码=<font color='red'>"+loginPass+"</font>昵称=<font color='red'>"+nickName+"</font>;电子邮箱=<font color='red'>"+email+"</font>;电话=<font color='red'>"+telphone+"</font>";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		request.setAttribute("msg", "恭喜，用户信息修改成功!");
		forward = "success";
		return mapping.findForward(forward);
	}
}

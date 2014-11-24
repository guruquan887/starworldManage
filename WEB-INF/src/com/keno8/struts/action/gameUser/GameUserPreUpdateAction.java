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

public class GameUserPreUpdateAction extends Action {

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
		GameUserDTO dto = new GameUserDTO();
		dto.setUserID(userID);
		int type = 0;
		String dataSource = "";
		if(request.getParameter("type")!=null){
			type = Integer.parseInt(request.getParameter("type"));
		}
		if(type==1){
			dataSource = "Keno8";
		}
		else{
			dataSource = "JifenKeno8";
		}
		DataSource ds = this.getDataSource(request, dataSource);
		GameUserDAO dao = new GameUserDAO(ds);
		GameUserDTO dto1 = dao.getById(userID);
		request.setAttribute("user", dto1);
		request.setAttribute("type", type);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入查看<font color='red'>"+dto1.getAccounts()+"</font>用户详情";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		forward = "success";
		return mapping.findForward(forward);
	}
}

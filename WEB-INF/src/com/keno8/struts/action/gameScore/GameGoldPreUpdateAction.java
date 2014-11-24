package com.keno8.struts.action.gameScore;


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


public class GameGoldPreUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "failure";
		int userID = Integer.parseInt(request.getParameter("userID"));
		GameUserDTO dto = new GameUserDTO();
		dto.setUserID(userID);
		DataSource ds = this.getDataSource(request, "Keno8");
		GameUserDAO dao = new GameUserDAO(ds);
		GameUserDTO dto1 = dao.getById(userID);
		request.setAttribute("gamegold", dto1);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入修改<font color='red'>"+dto1.getAccounts()+"</font>银子页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		forward = "success";
		return mapping.findForward(forward);
	}
}

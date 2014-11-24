package com.keno8.struts.action.gameScore;

import java.sql.Connection;

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


public class GameGoldUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String forward = "failure";
		String userID = request.getParameter("userID");
	
		double score = Double.parseDouble(request.getParameter("score"));
		DataSource ds = this.getDataSource(request, "Keno8");
		GameUserDTO dto = new GameUserDTO();
		dto.setUserID(Integer.parseInt(userID));
		dto.setScore(score);
		GameUserDAO dao = new GameUserDAO(ds);
		GameUserDTO dto1 = dao.getById(Integer.parseInt(userID));
		dao.updateGold(dto);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"修改<font color='red'>"+dto1.getAccounts()+"</font>用户银子<font color='red'>score="+score+"</font>";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		forward = "success";
		return mapping.findForward(forward);
	}
}

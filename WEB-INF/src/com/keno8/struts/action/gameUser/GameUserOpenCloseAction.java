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


public class GameUserOpenCloseAction extends Action {

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
		int type = Integer.parseInt(request.getParameter("type"));
		String typeName = "";
	    if(type==1){
	    	typeName = "关闭";
	    }
	    else{
	    	typeName = "开启";
	    }
	    DataSource ds = this.getDataSource(request, "Keno8");
		GameUserDAO dao = new GameUserDAO(ds);
		GameUserDTO dto1 = dao.getById(userID);
	    GameUserDTO dto = new GameUserDTO();
		dto.setUserID(userID);
		dao.openclose(userID,type);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"更改"+dto1.getAccounts()+"用户状态："+typeName;
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		request.setAttribute("msg", "恭喜，操作已成功!"+dto1.getAccounts()+"已"+typeName);
		forward = "success";
		return mapping.findForward(forward);
	}
}

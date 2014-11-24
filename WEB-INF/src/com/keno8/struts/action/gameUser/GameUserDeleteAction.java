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


public class GameUserDeleteAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String forward = "failure";
		int type = 0;
		DataSource ds = null;
		if(request.getParameter("type")!=null){
			type = Integer.parseInt(request.getParameter("type"));
		}
		if(type==1){
			ds = this.getDataSource(request, "Keno8");
		}
		else{
			ds = this.getDataSource(request, "JifenKeno8");
		}
		
		int  gameUserID =0;
		if(request.getParameter("gameUserID")!=null){
			gameUserID=Integer.parseInt(request.getParameter("gameUserID"));
		}
		GameUserDAO dao = new GameUserDAO(ds);
		if(gameUserID!=0){
			dao.DeleteGameUser(gameUserID);
			/* 写入日志记录 */
			GameUserDTO dto = dao.getById(gameUserID);
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"删除<font color='red'>"+dto.getAccounts()+"</font>用户";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			request.setAttribute("msg", "删除成功！");
		}
		else{
			request.setAttribute("msg", "删除失败，ID不存在！");
		}
		forward = "success";
		return mapping.findForward(forward);
	}
}

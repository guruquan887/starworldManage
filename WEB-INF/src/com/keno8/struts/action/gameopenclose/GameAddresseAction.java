package com.keno8.struts.action.gameopenclose;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.OperateDTO;

public class GameAddresseAction extends Action {

	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		
		if("roomAddressList".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			GameOpenCloseDAO dao = new GameOpenCloseDAO(ds);
			List<GameOpenCloseDTO> list = dao.queryRoomID();
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"点击进入游戏状态管理页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			request.setAttribute("roomList", list);
			return mapping.findForward("roomAddressList");
		}
		
		else if("changeAddress".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			GameOpenCloseDAO dao = new GameOpenCloseDAO(ds);
			int gameID = 0;
			String dataInter = "";
			if(request.getParameter("gameID")!=null){
				gameID = Integer.parseInt(request.getParameter("gameID"));
			}
			if(request.getParameter("dataInter")!=null){
				dataInter = request.getParameter("dataInter");
			}
			String roomName = dao.queryRoomName(gameID);
			if(gameID!=0){
				
					dao.updateAddress(gameID, dataInter);
					
					/* 写入日志记录 */
					String username = (String)request.getSession().getAttribute("username");
					String ip = (String)request.getSession().getAttribute("ip");
					OperateLogsDAO operateDao = new OperateLogsDAO(ds);
					OperateDTO operateDto = new OperateDTO();
					String operateDetails = username+"更新了"+roomName+"游戏的数据地址为"+dataInter;
					operateDto.setOperateName(username);
					operateDto.setOperateDetails(operateDetails);
					operateDto.setOperateIP(ip);
					operateDao.addLogs(operateDto);
					/*           */
					request.setAttribute("msg", "更新游戏地址成功!");
				}
			else{
				request.setAttribute("msg", "对不起，游戏不存在！");
			}
			
			return mapping.findForward("changeAddress");
		}
		return mapping.findForward(null);
	}
	
}

package com.keno8.struts.action.gameopenclose;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GameDataChangeAction extends Action {

	
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
		
		if("roomList".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			GameOpenCloseDAO dao = new GameOpenCloseDAO(ds);
			List<GameOpenCloseDTO> list = dao.queryDataRoomID();
			request.setAttribute("roomList", list);
			return mapping.findForward("roomList");
		}
		
		else if("changeData".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			GameOpenCloseDAO dao = new GameOpenCloseDAO(ds);
			int gameID = 0;
			int handicap = 0;
			if(request.getParameter("gameID")!=null){
				gameID = Integer.parseInt(request.getParameter("gameID"));
			}
			if(request.getParameter("handicap")!=null){
				handicap = Integer.parseInt(request.getParameter("handicap"));
			}
			if(gameID!=0){
				if(handicap==0){
					dao.ChangeData(gameID, 1);
					request.setAttribute("msg", "切换游戏后台录入获取成功！");
				}
				else if(handicap==1){
					dao.ChangeData(gameID, 0);
					request.setAttribute("msg", "切换服务后台自动获取成功！");
				}else{
					request.setAttribute("msg", "对不起，您请求的操作不存在！");
				}
			}
			else{
				request.setAttribute("msg", "对不起，游戏不存在！");
			}
			request.setAttribute("handicap", handicap);
			return mapping.findForward("changeData");
		}
		return mapping.findForward(null);
	}
	
}

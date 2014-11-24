package com.keno8.struts.action.gameUser.mark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GameUserTotalScoreAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}

		DataSource ds = this.getDataSource(request, "Keno8");
		GameUserBBTJDAO dao = new GameUserBBTJDAO(ds);
        //补差额
		dao.setByTotalScore();
		
		return new ActionForward("/gameuser/bbtj.do?action=all");
	}

	
}

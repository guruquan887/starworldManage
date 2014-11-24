package com.keno8.struts.action.article;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class QuestionTypePreUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		int id = Integer.parseInt(request.getParameter("id"));
		NewsTypeDTO dto = new NewsTypeDTO();
		dto.setId(id);
		Connection con = this.getDataSource(request, "Keno8").getConnection();
		NewsTypeDAO dao = new NewsTypeDAO(con);
		NewsTypeDTO dto1 = dao.getById(id);
		request.setAttribute("questiontype", dto1);
		con.close();
		forward = "success";
		return mapping.findForward(forward);
	}
}

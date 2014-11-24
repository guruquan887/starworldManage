package com.doowal.struts.action.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class PageOnePreUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		int id = Integer.parseInt(request.getParameter("id"));
		NewsDTO dto = new NewsDTO();
		dto.setId(id);
		DataSource ds = this.getDataSource(request, "NNGameManage");
		NewsDAO dao = new NewsDAO(ds);
		NewsDTO news = dao.getById(id);
		request.setAttribute("NEWS", news);
		
		ClassDAO dao1 = new ClassDAO(ds);
		List<ClassDTO> list = dao1.select();
		request.setAttribute("class", list);
		forward = "success";
		return mapping.findForward(forward);
	}
	
	
	
	

}

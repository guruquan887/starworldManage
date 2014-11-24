package com.doowal.struts.action.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class NewsPreUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		int newsID = Integer.parseInt(request.getParameter("newsID"));
		NewsDTO dto = new NewsDTO();
		dto.setNewsID(newsID);
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");
		NewsDAO dao = new NewsDAO(ds);
		NewsDTO news = dao.getById(newsID);
		request.setAttribute("NEWS", news);
		
		forward = "success";
		return mapping.findForward(forward);
	}
	
	
	
	

}

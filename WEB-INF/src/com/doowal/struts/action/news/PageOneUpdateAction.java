package com.doowal.struts.action.news;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PageOneUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		String id = request.getParameter("id");
		String newstitle = request.getParameter("newstitle");
		String classcode = request.getParameter("classcode");
		String newsinfo = request.getParameter("content");
		
		NewsDTO dto = new NewsDTO();
		System.out.println(id);
		dto.setId(Integer.parseInt(id));
		dto.setNewstitle(newstitle);
		dto.setClasscode(classcode);
		dto.setNewsinfo(newsinfo);
		DataSource ds = this.getDataSource(request, "NNGameManage");
		NewsDAO dao = new NewsDAO(ds);
		dao.update(dto);
		forward = "success";
		return mapping.findForward(forward);
	}
}

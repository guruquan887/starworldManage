package com.doowal.struts.action.news;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NewsDeleteAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");

		NewsDAO dao = new NewsDAO(ds);
		String [] ids = request.getParameterValues("checkbox");
		for(int i=0;i<ids.length;i++){
			dao.DeleteNews(ids[i]);
			System.out.println(ids[i]);
		}
		forward = "success";
		return mapping.findForward(forward);
	}
}

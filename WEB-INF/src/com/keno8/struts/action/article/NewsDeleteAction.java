package com.keno8.struts.action.article;

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
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}

		String forward = "failure";
		DataSource ds = this.getDataSource(request,"Keno8");

		String action = request.getParameter("action");

		int newsid = 0;
		if (request.getParameter("newsid") != null) {
			newsid = Integer.parseInt(request.getParameter("newsid"));
		}
		NewsDAO dao = new NewsDAO(ds);
		if (newsid != 0) {
			dao.DeleteNews(newsid);
			request.setAttribute("msg", "删除成功！");

		} else {
			request.setAttribute("msg", "删除失败，ID不存在！");
		}
		forward = "success";
		return mapping.findForward(forward);
	}
}

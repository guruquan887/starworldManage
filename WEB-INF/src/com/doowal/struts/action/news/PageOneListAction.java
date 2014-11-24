package com.doowal.struts.action.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class PageOneListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		DataSource ds = this.getDataSource(request, "NNGameManage");
		NewsDAO dao = new NewsDAO(ds);
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}
		if (curPage < 1)
			curPage = 1;
		int pageSize=10;
		String where=" classcode in(7,8,9,10,11)";
		
		List<NewsDTO> list = dao.GetRecordByPage(curPage,pageSize,where);
		int totalPage = dao.getTotalPage();
		request.setAttribute("newslist", list);
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		
		PageDTO pdto = new PageDTO();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		forward = "success";
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward(forward);
	}
}

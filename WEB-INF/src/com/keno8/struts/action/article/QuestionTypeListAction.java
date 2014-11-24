package com.keno8.struts.action.article;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dto.PageDTO;

public class QuestionTypeListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		Connection con = this.getDataSource(request, "Keno8").getConnection();
		NewsTypeDAO dao = new NewsTypeDAO(con);
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
		
		List<NewsTypeDTO> list = dao.GetRecordByPage(curPage,pageSize);
	
		con.close();
		
		
		int totalPage = dao.getTotalPage();
		request.setAttribute("newstypelist", list);
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

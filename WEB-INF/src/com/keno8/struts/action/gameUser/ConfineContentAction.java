package com.keno8.struts.action.gameUser;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.keno8.struts.dao.ConfineContentDAO;
import com.keno8.struts.dto.ConfineContentDTO;
import com.keno8.struts.dto.PageDTO;



public class ConfineContentAction extends DispatchAction {

	
	public ActionForward showlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "Keno8");
		ConfineContentDAO dao = new ConfineContentDAO(ds);
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
		
		List<ConfineContentDTO> list = dao.GetRecordByPage(curPage, pageSize);
		int totalPage = dao.getTotalPage();
		request.setAttribute("qulist", list);
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		PageDTO pdto = new PageDTO();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRecordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("contentList");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String string = request.getParameter("string");
		ConfineContentDTO dto = new ConfineContentDTO();
		dto.setString(string);
		DataSource ds = this.getDataSource(request, "Keno8");
		ConfineContentDAO dao = new ConfineContentDAO(ds);
		dao.add(dto);
		return mapping.findForward("adduser");
	}
	
	public ActionForward delUserAppeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		DataSource ds = this.getDataSource(request, "Keno8");
		int  id = 0;
		if(request.getParameter("id")!=null){
			id=Integer.parseInt(request.getParameter("id"));
		}
		ConfineContentDAO dao = new ConfineContentDAO(ds);
		if(id!=0){
			dao.DeleteGameScore(id);
			request.setAttribute("msg", "删除成功！");
			
		}
		else{
			request.setAttribute("msg", "删除失败，ID不存在！");
		}
		
		return mapping.findForward("delappeal");
	}

}

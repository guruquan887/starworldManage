package com.doowal.struts.action.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class GameRulesListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
		    System.out.println(">>>>>>>>>>>termWord:"+termWord);
		}
		String forward = "failure";
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");
		IssueDAO dao = new IssueDAO(ds);
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
		int Nullity=0;
		try {
			if(request.getParameter("nullity")!=null){
				Nullity=Integer.parseInt(request.getParameter("nullity"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		String where="Nullity="+Nullity;
		
		if(!"".equals(termWord)){
			where = where + " and kindName like '%"+termWord+"%'";
		}
		List<IssueDTO> list = dao.GetRecordGamerulesByPage(curPage,pageSize,where);
		if(list.size()!=0){
			int totalPage = dao.getTotalPage();
			request.setAttribute("gameRuleslist", list);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRcordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
		}
		
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
		}
		forward = "success";
		return mapping.findForward(forward);
	}
}

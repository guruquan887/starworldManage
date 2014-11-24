package com.doowal.hk798.card;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;

public class CardUserSelectAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		String termId = request.getParameter("selectOne");
		System.out.println(termId);
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
			//termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<"+termWord);
		DataSource ds = this.getDataSource(request, "C");
		
		CardDAO dao=new CardDAO(ds);
		List<CardViewDTO> list = null;
		String where = "username like '%"+termWord+"%'";
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}
		
		if (curPage < 1)
			curPage = 1;
		int pageSize=30;
		
		if("dealUser".equals(termId)){
			System.out.println("termId=1");
			list = dao.GetRecordByPageDealUser(curPage, pageSize, where);
		}

		if("jsUser".equals(termId)){
			String where1 = "Accounts like '%"+termWord+"%'";
			System.out.println("termId=2");
			list = dao.GetRecordByPagejsUser(curPage,pageSize,where1);
		}
		
		if("输入关键字"==termWord||"".equals(termWord)||"".equals(termId)){
			String where2= "";
			list = dao.GetRecordByPage(curPage, pageSize,where2);
		}
		if(list.size()!=0){
			request.setAttribute("cardlist", list);
			int totalPage = dao.getTotalPage();
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRcordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			forward = "success";
		}
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
			forward = "failure";
		}
		return mapping.findForward(forward);
	}
}

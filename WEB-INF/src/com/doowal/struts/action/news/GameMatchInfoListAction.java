package com.doowal.struts.action.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class GameMatchInfoListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");
		GameFeedMatchDAO dao = new GameFeedMatchDAO(ds);
		String action = "";
		if(request.getParameter("action")!=null){
			action=request.getParameter("action");
		}
		if(action.equals("gameMatchList")){
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			    System.out.println(">>>>>>>>>>>termWord:"+termWord);
			}
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
			where = where + " and MatchTitle like '%"+termWord+"%'";
		}
		List<GameFeedMatchDTO> list = dao.GetRecordGameMatchByPage(curPage,pageSize,where);
		if(list.size()!=0){
			int totalPage = dao.getTotalPage();
			request.setAttribute("gameMatchlist", list);
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
			return mapping.findForward("gameMatchList");
		}
		
		
		if(action.equals("gameFeedBackList")){
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			    System.out.println(">>>>>>>>>>>termWord:"+termWord);
			}
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
			where = where + " and FeedBackTitle like '%"+termWord+"%'";
		}
		List<GameFeedMatchDTO> list = dao.GetRecordGameFeedBackByPage(curPage,pageSize,where);
		if(list.size()!=0){
			int totalPage = dao.getTotalPage();
			request.setAttribute("gameFeedBacklist", list);
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
			return mapping.findForward("gameFeedBackList");
		}
		
		
		if(action.equals("noticeList")){
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			    System.out.println(">>>>>>>>>>>termWord:"+termWord);
			}
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
			where = where + " and Subject like '%"+termWord+"%'";
		}
		List<GameFeedMatchDTO> list = dao.GetRecordNoticeByPage(curPage,pageSize,where);
		if(list.size()!=0){
			int totalPage = dao.getTotalPage();
			request.setAttribute("noticeList", list);
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
			return mapping.findForward("noticeList");
		}
		return super.execute(mapping, form, request, response);
	}
}


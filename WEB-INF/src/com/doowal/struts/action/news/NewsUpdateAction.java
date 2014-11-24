package com.doowal.struts.action.news;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NewsUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		int classID = Integer.parseInt(request.getParameter("classID"));
		String newstitle = request.getParameter("newstitle");
		String content =  request.getParameter("content");
		int newsID = Integer.parseInt(request.getParameter("newsID"));
		int onTop = 0;
		if(request.getParameter("in_OnTop")!=null){
			onTop = Integer.parseInt(request.getParameter("in_OnTop"));
		}
		int onTopAll = 0;
		if(request.getParameter("in_OnTopAll")!=null){
			onTopAll = Integer.parseInt(request.getParameter("in_OnTopAll"));
		}
		int isElite = 0;
		if(request.getParameter("in_IsElite")!=null){
			isElite = Integer.parseInt(request.getParameter("in_IsElite"));
		}
		int isHot = 0;
		if(request.getParameter("in_IsHot")!=null){
			isHot = Integer.parseInt(request.getParameter("in_IsHot"));
		}
		int isLock = 0;
		if(request.getParameter("in_IsLock")!=null){
			isLock = Integer.parseInt(request.getParameter("in_IsLock"));
		}
		int isLinks = 0;
		String linkUrl = "";
		if(request.getParameter("in_IsLinks")!=null){
			isLinks = Integer.parseInt(request.getParameter("in_IsLinks"));
			linkUrl = request.getParameter("in_LinkUrl");
		}
		
		NewsDTO dto = new NewsDTO();
		dto.setNewsID(newsID);
		dto.setClassID(classID);
		dto.setSubject(newstitle);
		dto.setBody(content);
		dto.setIsElite(isElite);
		dto.setIsHot(isHot);
		dto.setIsLinks(isLinks);
		dto.setIsLock(isLock);
		dto.setOnTop(onTop);
		dto.setOnTopAll(onTopAll);
		dto.setLinkUrl(linkUrl);
		dto.setIssueIP(request.getRemoteAddr());
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");
		NewsDAO dao = new NewsDAO(ds);
		dao.update(dto);
		forward = "success";
		return mapping.findForward(forward);
	}
}

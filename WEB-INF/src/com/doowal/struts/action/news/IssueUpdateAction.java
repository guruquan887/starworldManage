package com.doowal.struts.action.news;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class IssueUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		int issueID = Integer.parseInt(request.getParameter("issueID"));
		int nullity = Integer.parseInt(request.getParameter("nullity"));
		String issuetitle = request.getParameter("issuetitle");
		String content =  request.getParameter("content");
		
		IssueDTO dto = new IssueDTO();
		dto.setIssueID(issueID);
		dto.setIssueTitle(issuetitle);
		dto.setIssueContent(content);
		dto.setNullity(nullity);
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");
		IssueDAO dao = new IssueDAO(ds);
		dao.update(dto);
		forward = "success";
		return mapping.findForward(forward);
	}
}

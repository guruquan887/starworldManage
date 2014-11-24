package com.doowal.struts.action.news;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class IssuePreUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		int issueID = Integer.parseInt(request.getParameter("issueID"));
		IssueDTO dto = new IssueDTO();
		dto.setIssueID(issueID);
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");
		IssueDAO dao = new IssueDAO(ds);
		IssueDTO issue = dao.getById(issueID);
		request.setAttribute("issue", issue);
		
		forward = "success";
		return mapping.findForward(forward);
	}
	
	
	
	

}

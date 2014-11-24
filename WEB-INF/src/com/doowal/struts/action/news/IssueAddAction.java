package com.doowal.struts.action.news;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class IssueAddAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";

		int nullity = Integer.parseInt(request.getParameter("nullity"));
		String issuetitle = request.getParameter("issuetitle");
		String content =  request.getParameter("content");
		
		
		IssueDTO dto = new IssueDTO();
		dto.setIssueTitle(issuetitle);
		dto.setIssueContent(content);
		dto.setNullity(nullity);
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");
		IssueDAO dao = new IssueDAO(ds);

		dao.add(dto);
		if (dto != null) {
			request.setAttribute("msg", "添加成功!");
			forward = "success";
		}
		else{
			request.setAttribute("msg", "添加失败");
			forward = "failure";
		}
		return mapping.findForward(forward);
	}

}

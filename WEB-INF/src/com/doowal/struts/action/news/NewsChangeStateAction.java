package com.doowal.struts.action.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class NewsChangeStateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		DataSource ds = this.getDataSource(request, "QPNativeWebDB");
		String action=request.getParameter("action");
		String [] ids = request.getParameterValues("checkbox");
		NewsDAO dao = new NewsDAO(ds);
			if("pub".equals(action)){
				for(int i=0;i<ids.length;i++){
				dao.ChangeState(ids[i], 1);
				}
				request.setAttribute("msg", "发布成功！");
			}
			else if("unpub".equals(action)){
				for(int i=0;i<ids.length;i++){
					dao.ChangeState(ids[i], 0);
					}
				request.setAttribute("msg", "撤销成功！");
			}else{
				request.setAttribute("msg", "对不起，您请求的操作不存在！");
			}
		forward = "success";
		return mapping.findForward(forward);
	}
	

}

package com.doowal.staworld.advertisement;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdvertisementDeleteAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		String[] ids = request.getParameterValues("checkbox");
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		AdvertisementDAO dao = new AdvertisementDAO(ds);
		for (int i = 0; i < ids.length; i++) {
			dao.delete(Integer.parseInt(ids[i]));
		}
		forward = "success";
		return mapping.findForward(forward);
	}
}

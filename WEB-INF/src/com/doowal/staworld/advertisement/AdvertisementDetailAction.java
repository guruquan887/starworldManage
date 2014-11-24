package com.doowal.staworld.advertisement;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdvertisementDetailAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		int id = Integer.parseInt(request.getParameter("id"));
		AdvertisementDTO dto = new AdvertisementDTO();
		dto.setId(id);
		DataSource ds = this.getDataSource(request, "E");
		AdvertisementDAO dao = new AdvertisementDAO(ds);
		AdvertisementDTO adto = dao.getDetail(id);
		request.setAttribute("adto", adto);
		forward = "success";
		return mapping.findForward(forward);
	}
}

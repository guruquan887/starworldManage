package com.doowal.staworld.advertisement;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdvertisementUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		String id = request.getParameter("id");
		String adTitle = request.getParameter("adTitle");
		String adLink = request.getParameter("adLink");
		String adSynopsis = request.getParameter("adSynopsis");
		String adTypeId = request.getParameter("adTypeId");
		String imagePath = "";
		if(request.getParameter("photourl")!=null){
			imagePath = request.getParameter("photourl");
		}
		AdvertisementDTO dto = new AdvertisementDTO();
		dto.setId(Integer.parseInt(id));
		dto.setAdLink(adLink);
		dto.setAdTitle(adTitle);
		dto.setAdImage(imagePath);
		dto.setAdSynopsis(adSynopsis);
		dto.setAdTypeId(Integer.parseInt(adTypeId));
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		AdvertisementDAO dao = new AdvertisementDAO(ds);
		dao.update(dto);
		forward = "success";
		return mapping.findForward(forward);
	}
}

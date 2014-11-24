package com.doowal.staworld.advertisement;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdvertisementAddAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		
		String adTitle = request.getParameter("adTitle");
		String adSynopsis = request.getParameter("adSynopsis");
		String adLink = request.getParameter("adLink");
		String adTypeId = request.getParameter("adTypeId");
		
		String imagePath = "";
		if(request.getParameter("photourl")!=null){
			imagePath = request.getParameter("photourl");
		}
		System.out.println(">>>>>>>>>>>>>上传的图片为："+imagePath);
		
		AdvertisementDTO dto = new AdvertisementDTO();
		dto.setAdTitle(adTitle);
		dto.setAdSynopsis(adSynopsis);
		dto.setAdLink(adLink);
		dto.setAdImage(imagePath);
		dto.setAdTypeId(Integer.parseInt(adTypeId));
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		AdvertisementDAO dao = new AdvertisementDAO(ds);
		dao.add(dto);
		if(dto!=null){
			forward = "success";
		}
		return mapping.findForward(forward);
	}
}

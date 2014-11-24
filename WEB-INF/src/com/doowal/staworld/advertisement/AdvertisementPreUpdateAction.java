package com.doowal.staworld.advertisement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdvertisementPreUpdateAction extends Action {

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
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		AdvertisementDAO dao = new AdvertisementDAO(ds);
		AdvertisementDTO ad = dao.getById(id);
		request.setAttribute("AD", ad);
		
		AdvertisementTypeDAO dao1 = new AdvertisementTypeDAO(ds);
		List<AdvertisementTypeDTO> adType=dao1.select();
		request.setAttribute("adType", adType);
		forward = "success";
		return mapping.findForward(forward);
		
	}
	
	

}

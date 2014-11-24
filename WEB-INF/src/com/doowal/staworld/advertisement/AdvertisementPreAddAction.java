package com.doowal.staworld.advertisement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdvertisementPreAddAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		AdvertisementTypeDAO dao = new AdvertisementTypeDAO(ds);
		List<AdvertisementTypeDTO> list = dao.select();
		request.setAttribute("adType", list);
		forward = "success";
		return mapping.findForward(forward);
	}
}

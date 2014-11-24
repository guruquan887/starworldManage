package com.doowal.staworld.advertisement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdvertisementListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "failure";
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		AdvertisementDAO dao = new AdvertisementDAO(ds);
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}
		
		if (curPage < 1)
			curPage = 1;
		int pageSize=10;
		int state = 0;
		int adTypeId = 0;
		try {
			if(request.getParameter("adTypeId")!=null){
				adTypeId=Integer.parseInt(request.getParameter("adTypeId"));
			}
			if(request.getParameter("state")!=null){
				state=Integer.parseInt(request.getParameter("state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String where="state="+state;
		if(adTypeId>0){
			where+=" and adTypeId="+adTypeId;
		}
		List<AdvertisementDTO> list = dao.GetRecordByPage(curPage,pageSize,where);
		AdvertisementTypeDAO dao1 = new AdvertisementTypeDAO(ds);
		List<AdvertisementTypeDTO> list1 = dao1.select();
		
		int totalPage = dao.getTotalPage();
		request.setAttribute("adlist", list);
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		PageDTO pdto = new PageDTO();
		System.out.println("totalpage:"+totalPage);
		pdto.setCurPage(curPage);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		forward = "success";
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("class", list1);
		return mapping.findForward(forward);
	}
}

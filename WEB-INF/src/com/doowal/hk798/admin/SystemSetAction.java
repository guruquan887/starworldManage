package com.doowal.hk798.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class SystemSetAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPAccountsDB");
		NetCSDAO dao = new NetCSDAO(ds);
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if("preUpdate".equals(action)){
			NetCSDTO dto = new NetCSDTO();
			dto = dao.getQPAdminSiteInfo();
			request.setAttribute("dto", dto);
			return mapping.findForward("systemSet");
		}
		
		if("update".equals(action)){
			NetCSDTO dto = new NetCSDTO();
			String siteName = "";
			if(request.getParameter("siteName")!=null){
				siteName = request.getParameter("siteName");
			}
			String copyRight = "";
			if(request.getParameter("copyRight")!=null){
				copyRight = request.getParameter("copyRight");
			}
			String wwwName = "";
			if(request.getParameter("wwwName")!=null){
				wwwName = request.getParameter("wwwName");
			}
			String adsMsg = "";
			if(request.getParameter("adsMsg")!=null){
				adsMsg = request.getParameter("adsMsg");
			}
			String gameMsg = "";
			if(request.getParameter("gameMsg")!=null){
				gameMsg = request.getParameter("gameMsg");
			}
			int siteID = 0;
			if(request.getParameter("siteID")!=null){
				siteID = Integer.parseInt(request.getParameter("siteID"));
			}
			dto.setSiteID(siteID);
			dto.setSiteName(siteName);
			dto.setCopyRight(copyRight);
			dto.setWwwName(wwwName);
			dto.setAdsMsg(adsMsg);
			dto.setGameMsg(gameMsg);
			if(dao.updateQPAdminSiteInfo(dto)){
				request.setAttribute("msg", "修改成功！");
			}
			else{
				request.setAttribute("msg", "数据库错误，修改失败！");
			}
			request.setAttribute("dto", dto);
			return mapping.findForward("update");
		}
		
		
		return null;
	}
}

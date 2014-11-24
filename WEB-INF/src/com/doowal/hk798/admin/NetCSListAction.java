package com.doowal.hk798.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class NetCSListAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		NetCSDAO dao = new NetCSDAO(ds);
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if("preUpdate".equals(action)){
			NetCSDTO dto = new NetCSDTO();
			String systemParam = "AllScoreIn57Game";
			if(request.getParameter("systemParam")!=null){
				systemParam = request.getParameter("systemParam");
			}
			dto = dao.getSystemSet(systemParam);
			request.setAttribute("dto", dto);
			return mapping.findForward("preUpdate");
		}
		
		if("update".equals(action)){
			NetCSDTO dto = new NetCSDTO();
			String StatusName = "";
			if(request.getParameter("StatusName")!=null){
				StatusName = request.getParameter("StatusName");
			}
			long StatusValue = 0;
			if(request.getParameter("StatusValue")!=null){
				StatusValue = Long.parseLong(request.getParameter("StatusValue"));
			}
			String StatusTip = "";
			if(request.getParameter("StatusTip")!=null){
				StatusTip = request.getParameter("StatusTip");
			}
			String StatusString = "";
			if(request.getParameter("StatusString")!=null){
				StatusString = request.getParameter("StatusString");
			}
			String StatusDescription = "";
			if(request.getParameter("StatusDescription")!=null){
				StatusDescription = request.getParameter("StatusDescription");
			}
			dto.setStatusName(StatusName);
			dto.setStatusValue(StatusValue);
			dto.setStatusString(StatusString);
			dto.setStatusTip(StatusTip);
			dto.setStatusDescription(StatusDescription);

			if(dao.updateset(dto)){
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

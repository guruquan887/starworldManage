package com.wiiy.spreader;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SpreNetsAction extends DispatchAction {

	public ActionForward PreNets(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception  {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		DataSource ds = this.getDataSource(request,"Keno8");
		SpreaderNetsDAO dao = new SpreaderNetsDAO(ds);
		SpreaderNetsDTO dto = dao.getById(userID);
		request.setAttribute("dto", dto);
		return mapping.findForward("Presuccess");
	}
	
	public ActionForward UpdateNets(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception  {
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		float gameTax = Float.parseFloat(request.getParameter("gameTax"));
//		long uponLineTimeGold = Long.parseLong(request.getParameter("uponLineTimeGold"));
//		int onLineTime = Integer.parseInt(request.getParameter("onLineTime"));
//		float ssfh = Float.parseFloat(request.getParameter("ssfh"));
		String accounts = request.getParameter("accounts");
		
		SpreaderNetsDTO dto = new SpreaderNetsDTO();
		dto.setGameTax(gameTax);
//		dto.setOnLineTime(onLineTime);
//		dto.setUponLineTimeGold(uponLineTimeGold);
//		dto.setSsfh(ssfh);
		dto.setUserID(userID);
		dto.setAccounts(accounts);
		
		DataSource ds = this.getDataSource(request,"Keno8");
		SpreaderNetsDAO dao = new SpreaderNetsDAO(ds);
		if(dao.updateUserGameTax(dto)){
			request.setAttribute("msg", "修改成功！");
		}
		else{
			request.setAttribute("msg", "修改失败！");
		}
		request.setAttribute("dto", dto);
		return mapping.findForward("UpdateSuccess");
		
	}
	
	

}

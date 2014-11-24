package com.wiiy.spreader;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.keno8.struts.dao.GameUserDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.GameUserDTO;
import com.keno8.struts.dto.OperateDTO;

public class SpreaderDispatchAction extends DispatchAction {
	
	public ActionForward pageSpreader(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String forward = "failure";
		
		String accounts = null;
		String search = null;
		String type = null;
		String where = "";
		if(!"".equals(request.getParameter("accounts")) && null != request.getParameter("accounts")){
			accounts = request.getParameter("accounts");
//			where ="SpreaderName='"+accounts+"'";
			where ="topAccounts='"+accounts+"' and ";
		}
		System.out.println("request.getParameter(termOne):"+request.getParameter("termOne"));
		if(null != request.getParameter("termOne")){
			search= request.getParameter("termOne").trim();
			search = new String(search.getBytes("ISO_8859_1"),"UTF-8");
			where +=" accounts like '%"+search+"%'";
		}
		if(null != request.getParameter("type")){
			type = request.getParameter("type");
		}
		System.out.println("where:"+where);
		
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}		
		if (curPage < 1)
			curPage = 1;
		int pageSize=30;
		
		DataSource ds = this.getDataSource(request, "Keno8");
		SpreaderDao dao = new SpreaderDao(ds);
		List<Spreader> list=null;
		
		
		if(null != accounts && !"".equals(accounts)){			
			list=dao.pageSpreaderOfXX(where, curPage, pageSize);
			forward = "details";
		}else if("down".equals(type)){			
			list=dao.pageSpreaderOfXX(where, curPage, pageSize);
			forward = "details";
		}else{
			list = dao.pageSpreader(where,curPage,pageSize);
			forward = "spreadlist";
		}				
		if(list.size()>0){
			int totalPage = dao.getTotalPage();
			request.setAttribute("spreaderList", list);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			Page page = new Page();
			page.setCurPage(curPage);
			page.setTotalPage(totalPage);
			page.setTotalRecord(dao.getRecordCount());
			page.setPageSize(pageSize);
			request.setAttribute("page", page);		
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入推广系统查看页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			
		}else{
			request.setAttribute("returnInfo", "没有你要查询的记录");
		}
		return mapping.findForward(forward);
	}
	
	public ActionForward showInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception  {
		
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String forward = "failure";
		
		String accounts = null;
		String search = null;
		String where = "";
		if(!"".equals(request.getParameter("accounts")) && null != request.getParameter("accounts")){
			accounts = request.getParameter("accounts");
			where ="topAccounts='"+accounts+"'";
		}
		System.out.println("request.getParameter(termOne):"+request.getParameter("termOne"));
		if(null != request.getParameter("termOne")){
			search= request.getParameter("termOne").trim();
			search = new String(search.getBytes("ISO_8859_1"),"UTF-8");
			where +=" and accounts like '%"+search+"%'";
		}
		System.out.println("where:"+where);
		
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}		
		if (curPage < 1)
			curPage = 1;
		int pageSize=30;
		
		DataSource ds = this.getDataSource(request, "Keno8");
		SpreaderDao dao = new SpreaderDao(ds);
		List<Spreader> list=null;
		
		
		if(null != accounts && !"".equals(accounts)){			
			list=dao.pageSpreaderXXDetailRecord(where, curPage, pageSize);
			forward = "detailsInfo";
		}				
		if(list.size()>0){
			int totalPage = dao.getTotalPage();
			request.setAttribute("spreaderList", list);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			Page page = new Page();
			page.setCurPage(curPage);
			page.setTotalPage(totalPage);
			page.setTotalRecord(dao.getRecordCount());
			page.setPageSize(pageSize);
			request.setAttribute("page", page);		
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入推广系统查看页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			
		}else{
			request.setAttribute("returnInfo", "没有你要查询的记录");
		}
		return mapping.findForward(forward);
		
	}
	
	
	public ActionForward DeleteDownLine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception  {
		
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		DataSource ds = this.getDataSource(request, "Keno8");
		Spreader dto = new Spreader();
		GameUserDAO dao11 = new GameUserDAO(ds);
		GameUserDTO dto11 = dao11.getById(userID);
		SpreaderNetsDAO dao = new SpreaderNetsDAO(ds);
		dto = dao.getDataById(userID);
		if(dao.updateDownLineTcRecord(dto)){
			if(dao.DeleteDownLine(dto)&&dao.DeleteDownLineRecord(dto)){
				request.setAttribute("msg", "删除成功!");
				/* 写入日志记录 */
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				OperateDTO operateDto = new OperateDTO();
				String operateDetails = username+"删除下线名为<font color='red'>"+dto11.getAccounts()+"</font>的用户";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				/*           */
				
			}
			else{
				request.setAttribute("msg", "删除失败!");
			}
		}
		request.setAttribute("dto", dto);
		return mapping.findForward("DeleteSuccess");
		
	}
	


}

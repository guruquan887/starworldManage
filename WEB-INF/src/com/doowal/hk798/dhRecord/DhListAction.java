package com.doowal.hk798.dhRecord;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;

public class DhListAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "DhListReocrd";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		
		DataSource ds = this.getDataSource(request, "QPRecordDB");	
		DhRecordDAO dao = new DhRecordDAO(ds);
		if("dhRecordList".equals(action)){
			
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}

		if (curPage < 1)
			curPage = 1;
		int pageSize = 10;
		int state = 0;
		if(request.getParameter("state")!=null){
			state = Integer.parseInt(request.getParameter("state"));
		}
		String where = "state="+state;
		if(state==0){
			where = "state="+state;
		}
		else{
			where = "state!=0";
		}
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
		    where = where + " and Accounts like '%"+termWord+"%'";
		}
		List<DhRecordDTO> list = dao.GetRecordByPage(curPage, pageSize,where);
		int totalPage = dao.getTotalPage();
		request.setAttribute("dhRecordlist", list);
		if (curPage > dao.getTotalPage())
			curPage = dao.getTotalPage();
		PageDTO pdto = new PageDTO();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("dhRecordlist");
		
		}
		
		else if("deleteDhRecord".equals(action)){
			String express_ID = "";
			int userID = 0;
			
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			
			if(request.getParameter("express_ID")!=null){
				express_ID = request.getParameter("express_ID");
			}
			
			String msg = dao.deleteDhRecord(userID,express_ID);
			request.setAttribute(msg, msg);
			return mapping.findForward("deleteDhRecord");
		}
		
		else if("queryDhRecord".equals(action)){
			
			String userID = "";
			if(request.getParameter("userID")!=null){	
				userID = request.getParameter("userID");
			}
			String express_ID = "";
			if(request.getParameter("express_ID")!=null){
				express_ID = request.getParameter("express_ID"); 
			}
			DhRecordDTO dto = new DhRecordDTO();
			dto = dao.queryDhRecord(userID,express_ID);
			request.setAttribute("dto", dto);
			return mapping.findForward("preDhRecord");
		}
		
		else if("updateDhRecord".equals(action)){
			
			String userID = "";
			if(request.getParameter("userID")!=null){	
				userID = request.getParameter("userID");
			}
			String express_ID = "";
			if(request.getParameter("express_ID")!=null){
				express_ID = request.getParameter("express_ID"); 
			}
			String beizhu = "";
			if(request.getParameter("beizhu")!=null){
				beizhu = request.getParameter("beizhu");
			}
			String msg = dao.updateDhRecord(beizhu,userID,express_ID);
			request.setAttribute("msg", msg);
			return mapping.findForward("updateDhRecord");
		}
		
		else if("cancelDhRecord".equals(action)){
			
			String express_ID = request.getParameter("express_ID");
			int userID = 0;
			
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			
			if(request.getParameter("express_ID")!=null){
				express_ID = request.getParameter("express_ID");
			}
			
			int cancelType = dao.queryCancelType(userID,express_ID);
			
			if(cancelType==1){
				request.setAttribute("msg", "对不起，您已经取消，不能重复取消!");
				return mapping.findForward("cancelDhRecord");
			}
			else if(cancelType==2){
				request.setAttribute("msg", "对不起，系统已经提交到审核状态，不能执行取消!");
				return mapping.findForward("cancelDhRecord");
			}
			else if(cancelType==3){
				request.setAttribute("msg", "对不起，系统已经向您打款成功，不能执行取消!");
				return mapping.findForward("cancelDhRecord");
			}
			else{
				dao.cancel(express_ID);
				request.setAttribute("msg", "取消成功!");
				return mapping.findForward("cancelDhRecord");
			}
		}
		
		
		if("dhRecordConvertPresent".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}

			if (curPage < 1)
				curPage = 1;
			int pageSize = 10;
			String where = "1=1";
			String termWord ="";
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				//startTime = startTime + " 00:00:00";
				where = where + " and collectDate>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				//endTime = endTime + " 23:59:59";
				where = where + " and collectDate<='"+endTime+"'";
			}
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			    where = where + " and Accounts like '%"+termWord+"%'";
			}
			List<DhRecordDTO> list = dao.GetRecordPresentByPage(curPage, pageSize,where);
			if(list.size()>0){
				
			int totalPage = dao.getTotalPage();
			request.setAttribute("dhRecordlist", list);
			if (curPage > dao.getTotalPage())
				curPage = dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRcordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			return mapping.findForward("dhRecordConvertPresent");
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				return mapping.findForward("dhRecordConvertPresent");
			}
			}
		
		if("dhRecordConvertMedal".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}

			if (curPage < 1)
				curPage = 1;
			int pageSize = 10;
			String where = "1=1";
			String termWord ="";
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				//startTime = startTime + " 00:00:00";
				where = where + " and collectDate>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				//endTime = endTime + " 23:59:59";
				where = where + " and collectDate<='"+endTime+"'";
			}
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			    where = where + " and Accounts like '%"+termWord+"%'";
			}
			List<DhRecordDTO> list = dao.GetRecordUserMedalByPage(curPage, pageSize,where);
			if(list.size()>0){
				
			int totalPage = dao.getTotalPage();
			request.setAttribute("dhRecordlist", list);
			if (curPage > dao.getTotalPage())
				curPage = dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRcordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			return mapping.findForward("dhRecordConvertMedal");
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				return mapping.findForward("dhRecordConvertMedal");
			}
			}
		
		return null;
	}

}

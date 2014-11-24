package com.doowal.hk798.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.OperateDTO;
import com.doowal.hk798.admin.OperateLogsDAO;
import com.doowal.hk798.admin.PageDTO;

public class WebContentAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "list";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		
		if("webList".equals(action)){
			
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			
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
			WebContentDAO dao = new WebContentDAO(ds);
			List<WebContentDTO> list = dao.GetRecordByPage(curPage,pageSize,where);
			
			
			int totalPage = dao.getTotalPage();
			request.setAttribute("adlist", list);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRcordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"查看内容管理页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			
			return mapping.findForward("webList");
			
		}
		return mapping.findForward("");
	}
	
	

}

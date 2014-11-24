package com.keno8.struts.action.article;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.OperateDTO;


public class NewsUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String forward = "failure";
		String id = request.getParameter("id");
		String newstitle = request.getParameter("newstitle");
		String classcode = request.getParameter("classcode");
		String newsinfo = request.getParameter("content");
		String newscu = "";
		if(request.getParameter("newscu")!=null){
			newscu = request.getParameter("newscu");
		}
		
		NewsDTO dto = new NewsDTO();
		dto.setId(Integer.parseInt(id));
		dto.setNewstitle(newstitle);
		dto.setClasscode(classcode);
		dto.setNewscu(newscu);
		dto.setNewsinfo(newsinfo);
		DataSource ds = this.getDataSource(request,"Keno8");
		NewsDAO dao = new NewsDAO(ds);
		dao.update(dto);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"修改新闻，信息为:新闻标题为<font color='red'>"+newstitle+"</font>;新闻描述为<font color='red'>"+newsinfo+"</font>";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		forward = "success";
		return mapping.findForward(forward);
	}
}

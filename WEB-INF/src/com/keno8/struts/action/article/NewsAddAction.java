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


public class NewsAddAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}

		String forward = "failure";

		String classcode = request.getParameter("classcode");
		String newstitle = request.getParameter("newstitle");
		String newscu = "";
		if(request.getParameter("newscu")!=null){
			newscu = request.getParameter("newscu");
		}
		String content =  request.getParameter("content");

		NewsDTO dto = new NewsDTO();
		dto.setClasscode(classcode);
		dto.setNewstitle(newstitle);
		dto.setNewsinfo(content);
		dto.setNewscu(newscu);
		

		DataSource ds = this.getDataSource(request,"Keno8");
		NewsDAO dao = new NewsDAO(ds);

		dao.add(dto);
		if (dto != null) {
			forward = "success";
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"增加一条文章内容，信息为：文章标题为<font color='red'>"+newstitle+"</font>;文章内容为：<font color='red'>"+content+"</font>";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
		}
		return mapping.findForward(forward);
	}

}

package com.keno8.struts.action.article;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.OperateDTO;

public class NewsPreUpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		int id = Integer.parseInt(request.getParameter("id"));
		NewsDTO dto = new NewsDTO();
		dto.setId(id);
		DataSource ds = this.getDataSource(request,"Keno8");
		NewsDAO dao = new NewsDAO(ds);
		NewsDTO news = dao.getById(id);
		request.setAttribute("NEWS", news);
		
		ClassDAO dao1 = new ClassDAO(ds);
		List<ClassDTO> list = dao1.select();
		request.setAttribute("class", list);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入修改新闻页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		forward = "success";
		return mapping.findForward(forward);
	}
	
	
	
	

}

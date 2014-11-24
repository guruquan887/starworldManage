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
import com.keno8.struts.dto.PageDTO;

public class NewsListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		DataSource ds = this.getDataSource(request,"Keno8");
		NewsDAO dao = new NewsDAO(ds);
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
		int state=1;
		int classcode=0;
		try {
			if(request.getParameter("classcode")!=null){
				classcode=Integer.parseInt(request.getParameter("classcode"));
			}
			if(request.getParameter("state")!=null){
				state=Integer.parseInt(request.getParameter("state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String where="state="+state;
		if(classcode>0){
			where+=" and classcode="+classcode;
		}
		List<NewsDTO> list = dao.GetRecordByPage(curPage,pageSize,where);
		ClassDAO dao1 = new ClassDAO(ds);
		List<ClassDTO> list1 = dao1.select();
		int totalPage = dao.getTotalPage();
		request.setAttribute("newslist", list);
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		
		PageDTO pdto = new PageDTO();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		forward = "success";
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("class", list1);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入文章管理列表页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		return mapping.findForward(forward);
	}
}

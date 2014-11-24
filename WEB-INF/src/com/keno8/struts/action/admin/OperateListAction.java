package com.keno8.struts.action.admin;

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

public class OperateListAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String forward = "";
		DataSource ds = this.getDataSource(request, "Keno8");
		OperateLogsDAO dao = new OperateLogsDAO(ds);
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}
		if (curPage < 1)
			curPage = 1;
		int pageSize=100;
		
		if(request.getParameter("pageSize")!=null){
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String orderby="operateTime";
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
		}
		String termWord ="";
		String keyWord = "";
		String createTimeStart = "";
		String where = "";
		if(request.getParameter("termOne")!=null&&request.getParameter("termOne")!=""){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
		    where = "operateName = '"+termWord+"'";
			System.out.println(termWord);
		}
		
		if(request.getParameter("keyWord")!=null&&request.getParameter("keyWord")!=""&&request.getParameter("termOne")!=null&&request.getParameter("termOne")!=""){
				where +=  " and";
		}
		
		if(request.getParameter("keyWord")!=null&&request.getParameter("keyWord")!=""){
			keyWord = request.getParameter("keyWord");
			keyWord = new String(keyWord.getBytes("ISO_8859_1"),"UTF-8");
			System.out.println("keyWord="+keyWord);
			if(request.getParameter("termOne")!=null&&request.getParameter("termOne")!=""){
			where = where + " operateDetails like '%"+keyWord+"%'";
			}
			else{
				where = where + "operateDetails like '%"+keyWord+"%'";
			}
		}
		
		if(request.getParameter("keyWord")!=null&&request.getParameter("keyWord")!=""||request.getParameter("termOne")!=null&&request.getParameter("termOne")!=""){
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
			where +=  " and";
			}
		}
		
		if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
			createTimeStart = request.getParameter("startTime");
			System.out.println("选择date:"+createTimeStart);
			where = where + " DATEDIFF(day, operateTime, '"+createTimeStart+"')= 0 ";
		}
		
		request.setAttribute("startTime", createTimeStart);
		List<OperateDTO> list = dao.GetRecordByPage(curPage,pageSize,where,orderby);
		if(list.size()!=0){
		request.setAttribute("operatelist", list);
		PageDTO pdto = new PageDTO();
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(dao.getTotalPage());
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		}
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
			forward = "gameuserlist";
		}
		forward = "success";
		return mapping.findForward(forward);
	}
}

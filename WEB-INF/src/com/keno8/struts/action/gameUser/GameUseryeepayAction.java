package com.keno8.struts.action.gameUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.GameYeepayDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.GameYeepayDTO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.PageDTO;

public class GameUseryeepayAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		DataSource ds = this.getDataSource(request, "Keno8");
		GameYeepayDAO dao = new GameYeepayDAO(ds);
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
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		int state = 2;
		try{
			if(request.getParameter("state")!=null)
				state = Integer.parseInt(request.getParameter("state"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		String where = "";
		if(state!=2){
			where = "state = "+state;
		}
		
		List<GameYeepayDTO> list = dao.GetRecordByPage(curPage,pageSize,where);
		request.setAttribute("state", state);
		request.setAttribute("useryeepay", list);
		PageDTO pdto = new PageDTO();
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(dao.getTotalPage());
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
		String operateDetails = username+"进入查看用户存款记录";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		
		return mapping.findForward("gameyeepay");
	}
	
	

}

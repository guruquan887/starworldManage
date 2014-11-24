package com.keno8.struts.action.gameScore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.GameScoreInfoDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.GameUserDTO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.PageDTO;



public class GameGoldListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		DataSource ds = this.getDataSource(request, "Keno8");
		GameScoreInfoDAO dao = new GameScoreInfoDAO(ds);
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
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String orderby="score";
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
		}
			List<GameUserDTO> list = dao.GetRecordByPage(curPage,pageSize,orderby);
			request.setAttribute("userscorelist", list);
		
		int totalPage = dao.getTotalPage();
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		PageDTO pdto = new PageDTO();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRecordCount());
		
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"点击进入用户银子管理页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		forward = "success";
		return mapping.findForward(forward);
		
	}
}

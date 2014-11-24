package com.keno8.struts.action.gameScore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.GameDhScoreDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.GameDhScoreDTO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.PageDTO;

public class GameDhDetailsListAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String forward = "failure";
		
		String accounts  = "";
		if(request.getParameter("accounts")!=null){
			accounts = request.getParameter("accounts");
		}
		int userID  = 0;
		if(request.getParameter("userID")!=null){
			userID = Integer.parseInt(request.getParameter("userID"));
		}
		
		DataSource ds = this.getDataSource(request, "Keno8");
		GameDhScoreDAO dao = new GameDhScoreDAO(ds);
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
		
		String orderby="diffMoney";
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
		}
		
		if(request.getParameter("pageSize")!=null){
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<GameDhScoreDTO> dto = null;
		String where = "type=1 and userID="+userID;
		dto = dao.GetRecordDetailsByPage(curPage, pageSize,orderby,where);
		if(dto.size()!=0){
			request.setAttribute("userscorelist", dto);
			request.setAttribute("accounts", accounts);
			request.setAttribute("userID", userID);
			int totalPage = dao.getTotalPage();
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
			String operateDetails = username+"查询<font color='red'>"+accounts+"</font>用户兑换银子记录";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			
			forward = "success";
		}
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
			forward = "success";
		}
		return mapping.findForward(forward);
		
	}
	
	

}

package com.keno8.struts.action.gameRecord;

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
import com.doowal.staworld.advertisement.PageDTO;


public class GameRecordAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPTreasureDB");
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
		if("gameRecordList".equals(action)){
		
	    //获得游戏类型ID
		int kindID = 0;
		if(request.getParameter("kindID")!=null){
			kindID = Integer.parseInt(request.getParameter("kindID"));
		}
		GameReocrdDAO dao = new GameReocrdDAO(ds);
		List<GameRecordDTO> list = null;
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
		String orderby="generateTime";
		String where = "1=1 and kindID="+kindID;
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
		}
		
		String termId = request.getParameter("selectOne");
		String termWord ="";
		String startTime = "";
		String endTime = "";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			System.out.println(termWord);
		}
		
		if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
			startTime = request.getParameter("startTime");
			where = where + " and generateTime>='"+startTime+"'";
		}
		if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
			endTime = request.getParameter("endTime");
			where = where + " and generateTime<='"+endTime+"'";
		}
		if("accounts".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=1");
			System.out.println("进入搜索用户的判断");
			
			where = where + " and Accounts like '%"+termWord+"%'";
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("betSerial".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=2");
			where = where + " and betSerial ='"+termWord+"'";
			System.out.println("进入搜索昵称的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("".equals(termId)||"".equals(termWord)){
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		if(list.size()!=0){
		request.setAttribute("username", request.getSession().getAttribute("username"));
		request.setAttribute("userlist", list);
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
		String operateDetails = username+"进入查看真人游戏记录";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		return mapping.findForward("gameRecordList");
		}
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
			return mapping.findForward("gameRecordList");
			}
		}
		
		return mapping.findForward("index");
	}

}

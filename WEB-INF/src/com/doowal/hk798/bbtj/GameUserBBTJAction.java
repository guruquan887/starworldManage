package com.doowal.hk798.bbtj;

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

public class GameUserBBTJAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPTreasureDB");//DataSource.QPTreasureDB
		GameUserBBTJDAO dao = new GameUserBBTJDAO(ds);
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
		if("gameUserBBTJ".equals(action)){
		List<GameUserBBTJDTO> list = null;
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
		String orderby="totalScore";
		if(request.getParameter("orderby")!=null){
			orderby = request.getParameter("orderby");
		}
		String where = "";
		String termId = request.getParameter("selectOne");
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			System.out.println(termWord+"termId:"+termId);
		}
		
		if("accounts".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=1");
			System.out.println("进入搜索用户的判断");
			where = "Accounts like '%"+termWord+"%'";
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		
		if("gameID".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=2");
			System.out.println("进入搜索游戏编号的判断");
			where = "gameID ="+termWord+"";
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("".equals(termId)||"".equals(termWord)){
			System.out.println("termId=4");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		GameUserBBTJDTO dto = dao.sum(); //统计报表统计
		
		System.out.println("--------------where:"+where);
		if(list.size()!=0){
			request.setAttribute("sum", dto);
			request.setAttribute("username", request.getSession().getAttribute("username"));
			request.setAttribute("userbbtj", list);
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
			String operateDetails = username+"进入查看用报表统计列表";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			return mapping.findForward("bbtjSuccess");
		/*           */
				}
			}
		
		
		else if("usergameRecord".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			String where = " userID="+userID;
			String startTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				where = where + "and insertTime>='"+startTime+"'";
			}
			String endTime = "";
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + "and insertTime<='"+endTime+"'";
			}
			List<GameUserBBTJDTO> list = null;
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
			String orderby="insertTime";
			list = dao.GetUserRecordByPage(curPage, pageSize,orderby,where);
			if(list.size()!=0){
				request.setAttribute("usergameRecord", list);
				request.setAttribute("accounts", list.get(0).getAccounts());
				request.setAttribute("userID", list.get(0).getUserID());
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
			return mapping.findForward("usergameRecord");
		}
		
		else if("userzsRecord".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			String where = " userID="+userID;
			String startTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				where = where + "and collectDate>='"+startTime+"'";
			}
			String endTime = "";
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + "and collectDate<='"+endTime+"'";
			}
			List<GameUserBBTJDTO> list = null;
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
			String orderby="collectDate";
			list = dao.GetzsRecordByPage(curPage, pageSize,orderby,where);
			if(list.size()!=0){
				request.setAttribute("userzsRecord", list);
				request.setAttribute("accounts", list.get(0).getAccounts());
				request.setAttribute("userID", list.get(0).getUserID());
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
			return mapping.findForward("userzsRecord");
		}
		
		else if("userOLRecord".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			String where = " userID="+userID;
			String startTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				where = where + "and ApplyDate>='"+startTime+"'";
			}
			String endTime = "";
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + "and ApplyDate<='"+endTime+"'";
			}
			List<GameUserBBTJDTO> list = null;
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
			String orderby="ApplyDate";
			list = dao.GetolRecordByPage(curPage, pageSize,orderby,where);
			if(list.size()!=0){
				request.setAttribute("userOLRecord", list);
				request.setAttribute("accounts", list.get(0).getAccounts());
				request.setAttribute("userID", list.get(0).getUserID());
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
			return mapping.findForward("userOLRecord");
		}
		
		else if("userZZRecord".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			String where = " SourceUserID="+userID + " or targetUserID="+userID;
			String startTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				where = where + "and CollectDate>='"+startTime+"'";
			}
			String endTime = "";
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + "and CollectDate<='"+endTime+"'";
			}
			List<GameUserBBTJDTO> list = null;
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
			String orderby="CollectDate";
			list = dao.GetZZRecordByPage(curPage, pageSize,orderby,where);
			if(list.size()!=0){
				request.setAttribute("userZZRecord", list);
				request.setAttribute("accounts", list.get(0).getAccounts());
				request.setAttribute("userID", list.get(0).getUserID());
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
			return mapping.findForward("userZZRecord");
		}
		
		
		else if("SpreaderInfoTCRecord".equals(action)){
			int userID = 3;
			
			
			String where = " typeID in(10)";
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
				where = where + " and userID="+userID;
			}
			String createTimeStart = "";
			String createTimeEnd = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择createTimeStart:"+createTimeStart);
				where += " and collectDate >'"+createTimeStart+"'";
			}
			if(request.getParameter("endTime")!=""&&request.getParameter("endTime")!=null){
				createTimeEnd = request.getParameter("endTime");
				System.out.println("选择createTimeEnd:"+createTimeEnd);
				where += " and collectDate <'"+createTimeEnd+"' ";
				System.out.println("时间where:"+where);
			}else{
				System.out.println("-------------");
				if(request.getParameter("checkTime")!= null){
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			    if("bweek".equals(cheekValue)){//上周
				    where += " and DATEDIFF(week,collectDate,getdate())=1 "; 	
				}else if("cweek".equals(cheekValue)){//本周
			    	where += " and DATEDIFF(week,collectDate,getdate())=0 ";
			    }else if("bmonth".equals(cheekValue)){//上月
			    	where += " and DATEDIFF(month,collectDate,getdate())=1 ";
			    }else if("cmonth".equals(cheekValue)){//本月
			    	where += " and DATEDIFF(month,collectDate,getdate())=0 ";
			    }else if("all".equals(cheekValue)){//全部
			    	where += "";
			    }
			  }else{
					where = where + "";//默认查询
					request.setAttribute("checkTime", "all");
				}
				System.out.println("checkTime-where:"+where);
			}
			List<GameUserBBTJDTO> list = null;
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
			String orderby="score";
			
			GameUserBBTJDTO dto = new GameUserBBTJDTO();
			String termId = request.getParameter("selectOne");
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne").trim();
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			    System.out.println("接受过来的搜索用户名:"+termWord);
			}
			if("accounts".equals(termId)&&!"".equals(termWord)){ 
				where += " and accounts like '%"+termWord+"%'";
				System.out.println(where+",termId=1");
				list = dao.GetSpreaderRecordByPage(curPage, pageSize,orderby,where);
				dto = dao.sumSpreaderTCRecord(curPage, pageSize,orderby,where);
			}
			if("gameID".equals(termId)&&!"".equals(termWord)){
			    where += " and gameID ="+termWord;
				System.out.println("termId=2");
				list = dao.GetSpreaderRecordByPage(curPage, pageSize,orderby,where);
				dto = dao.sumSpreaderTCRecord(curPage, pageSize,orderby,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				list = dao.GetSpreaderRecordByPage(curPage, pageSize,orderby,where);
				dto = dao.sumSpreaderTCRecord(curPage, pageSize,orderby,where);
			}
			int totalPage = 0;
			if(list.size()!=0){
				request.setAttribute("userSpreaderRecord", list);
				request.setAttribute("sumNo", dto);
				System.out.println("pageSize:"+pageSize);
				totalPage = dao.getTotalPage();
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				return mapping.findForward("SpreaderInfoTCRecord");
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				return mapping.findForward("SpreaderInfoTCRecord");
			}
		}
		
		else if("SpreaderDeatailTCRecord".equals(action)){
			int userID = 3;
			
			
			String where = " typeID in(10)";
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
				where = where + " and userID="+userID;
			}
			String createTimeStart = "";
			String createTimeEnd = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择createTimeStart:"+createTimeStart);
				where += " and collectDate >'"+createTimeStart+"'";
			}
			if(request.getParameter("endTime")!=""&&request.getParameter("endTime")!=null){
				createTimeEnd = request.getParameter("endTime");
				System.out.println("选择createTimeEnd:"+createTimeEnd);
				where += " and collectDate <'"+createTimeEnd+"' ";
				System.out.println("时间where:"+where);
			}else{
				System.out.println("-------------");
				if(request.getParameter("checkTime")!= null){
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			    if("bweek".equals(cheekValue)){//上周
				    where += " and DATEDIFF(week,collectDate,getdate())=1 "; 	
				}else if("cweek".equals(cheekValue)){//本周
			    	where += " and DATEDIFF(week,collectDate,getdate())=0 ";
			    }else if("bmonth".equals(cheekValue)){//上月
			    	where += " and DATEDIFF(month,collectDate,getdate())=1 ";
			    }else if("cmonth".equals(cheekValue)){//本月
			    	where += " and DATEDIFF(month,collectDate,getdate())=0 ";
			    }else if("all".equals(cheekValue)){//全部
			    	where += "";
			    }
			  }else{
					where = where + "";//默认查询
					request.setAttribute("checkTime", "all");
				}
				System.out.println("checkTime-where:"+where);
			}
			List<GameUserBBTJDTO> list = null;
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
			String orderby="score";
			
			String termId = request.getParameter("selectOne");
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne").trim();
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			    System.out.println("接受过来的搜索用户名:"+termWord);
			}
			if("accounts".equals(termId)&&!"".equals(termWord)){ 
				where += " and accounts like '%"+termWord+"%'";
				System.out.println(where+",termId=1");
				list = dao.GetSpreaderDetailByPage(curPage, pageSize,orderby,where);
			}
			if("gameID".equals(termId)&&!"".equals(termWord)){
			    where += " and gameID ="+termWord;
				System.out.println("termId=2");
				list = dao.GetSpreaderDetailByPage(curPage, pageSize,orderby,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				list = dao.GetSpreaderDetailByPage(curPage, pageSize,orderby,where);
			}
			int totalPage = 0;
			if(list.size()!=0){
				request.setAttribute("userSpreaderRecord", list);
				System.out.println("pageSize:"+pageSize);
				totalPage = dao.getTotalPage();
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				return mapping.findForward("SpreaderDetailTCRecord");
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				return mapping.findForward("SpreaderDetailTCRecord");
			}
		}
		return mapping.findForward("bbtjSuccess");
		}
	}

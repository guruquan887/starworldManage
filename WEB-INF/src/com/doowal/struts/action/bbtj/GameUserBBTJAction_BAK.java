package com.doowal.struts.action.bbtj;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;


public class GameUserBBTJAction_BAK extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String forward = "failure";
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
        String termId = request.getParameter("selectOne");
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne").trim();
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
		    System.out.println("接受过来的搜索用户名:"+termWord);
		}
		
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
		int agentID = 0;
		if(request.getParameter("userID")!= null){
			agentID = Integer.parseInt(request.getParameter("userID"));
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		int totalPage = 0;
		DataSource ds = this.getDataSource(request, "QPTreasureDB");
		String orderby = "proxyAccounts";
		GameUserBBTJDAO dao = new GameUserBBTJDAO(ds);
		String typeName = "";
		if(request.getParameter("typeName")!=null){
			typeName = request.getParameter("typeName");
		}
		if ("search".equals(action)){
			
			if(request.getParameter("selectOrderBy")!=null){
				orderby = request.getParameter("selectOrderBy");
				System.out.println("接受过来的排序方式:"+orderby);
			}
		    String startdateString = "";
		    String enddateString = "";
			String where = "levelID=1 ";
			int levelID = 1;
			if(request.getParameter("levelID")!=null){
				levelID = Integer.parseInt(request.getParameter("levelID"));
				where = "levelID="+levelID;
			}
			if(agentID != 0){
				where = where + " and prevProxy = "+agentID;
			}
			
			String where1 = "";
			String createTimeStart = "";
			String createTimeEnd = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择createTimeStart:"+createTimeStart);
				where1 = " AND convert(datetime,cast(days as varchar(8)),120) >'"+createTimeStart+"'";
			}
			if(request.getParameter("endTime")!=""&&request.getParameter("endTime")!=null){
				createTimeEnd = request.getParameter("endTime");
				System.out.println("选择createTimeEnd:"+createTimeEnd);
				where1 = " AND convert(datetime,cast(days as varchar(8)),120) <'"+createTimeEnd+"' ";
				System.out.println("时间where1:"+where1);
			}
				System.out.println("-------------");
				if(request.getParameter("checkTime")!= null){
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			     Date date=new Date();
			     Date date1=new Date();
			     Calendar calendar = new GregorianCalendar();
			     Calendar calendar1 = new GregorianCalendar();
			     if("today".equals(cheekValue)){   //今日
			    	 where1 = " AND DATEDIFF(day,convert(datetime,cast(days as varchar(8)),120),getdate())=0 ";
			    	 
			    	 calendar.add(calendar.DATE,0);
				     date=calendar.getTime();
				     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				     startdateString = formatter.format(date);
				     enddateString = formatter.format(date);
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			     }
			     else if("yestoday".equals(cheekValue)){
			    	 where1 = " AND DATEDIFF(day,convert(datetime,cast(days as varchar(8)),120),getdate())=1 "; 
			    	 
			    	 calendar.add(calendar.DATE,-1);
				     date=calendar.getTime();
				     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				     startdateString = formatter.format(date);
				     enddateString = formatter.format(date);
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			     }   //昨日
			     else if("bweek".equals(cheekValue)){//上周
				    where1 = " AND DATEDIFF(week,convert(datetime,cast(days as varchar(8)),120),getdate())=1 "; 
				     calendar.add(calendar.DATE,0);
				     calendar1.add(calendar1.DATE,-7);
				     date=calendar.getTime();
				     date1=calendar1.getTime();
				     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				     startdateString = formatter.format(date1);
				     enddateString = formatter.format(date);
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
				}else if("cweek".equals(cheekValue)){//本周
			    	where1 = " AND DATEDIFF(week,convert(datetime,cast(days as varchar(8)),120),getdate())=0 ";
				     calendar.add(calendar.DATE,0);
				     calendar1.add(calendar1.DATE,7);
				     date=calendar.getTime();
				     date1=calendar1.getTime();
				     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				     startdateString = formatter.format(date);
				     enddateString = formatter.format(date1);
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			    }else if("bmonth".equals(cheekValue)){//上月
			    	where1 = " AND DATEDIFF(month,convert(datetime,cast(days as varchar(8)),120),getdate())=1 ";
			    	calendar.add(calendar.DATE,0);
				     calendar1.add(calendar1.DATE,-30);
				     date=calendar.getTime();
				     date1=calendar1.getTime();
				     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				     startdateString = formatter.format(date1);
				     enddateString = formatter.format(date);
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			    }else if("cmonth".equals(cheekValue)){//本月
			    	where1 = " AND DATEDIFF(month,convert(datetime,cast(days as varchar(8)),120),getdate())=0 ";
			    	calendar.add(calendar.DATE,0);
				     calendar1.add(calendar1.DATE,30);
				     date=calendar.getTime();
				     date1=calendar1.getTime();
				     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				     startdateString = formatter.format(date);
				     enddateString = formatter.format(date1);
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			    }else if("all".equals(cheekValue)){//全部
			    	where1 = "";
			    	request.setAttribute("startTime", startdateString);
				    request.setAttribute("endTime", enddateString);
			    }
			  }
				System.out.println("checkTime-where1:"+where1);
			
			List<GameUserBBTJDTO> dto = null;
			List<GameUserBBTJDTO> dto1 = null;
			List<GameUserBBTJDTO> brlDTO = null;
			List<GameUserBBTJDTO> jjlDTO = null;
			GameUserBBTJDTO mybrlDTO = new GameUserBBTJDTO(); 
			GameUserBBTJDTO myjjlDTO = new GameUserBBTJDTO(); 
			GameUserBBTJDTO bdto = new GameUserBBTJDTO();
			GameUserBBTJDTO jdto = new GameUserBBTJDTO();
			
			if(where1!=""){
				
				if("accounts".equals(termId)&&!"".equals(termWord)){ 
					where = where+ " and proxyaccounts like '%"+termWord+"%'";
					dto = dao.GetByTimeBRL(where, orderby);
					dto1 = dao.GetByTimeJJL(where, orderby);
					brlDTO = dao.GetBaoBBRLTimeList(where1,dto);
					jjlDTO = dao.GetBaoBJJLTimeList(where1,dto1);
					 
					bdto = dao.GetBaoBSUMBRLList(agentID,where);
					jdto = dao.GetBaoBSUMJJLList(where);
					
				}
				if("".equals(termId)||"".equals(termWord)){
					dto = dao.GetByTimeBRL(where, orderby);
					dto1 = dao.GetByTimeJJL(where, orderby);
					brlDTO = dao.GetBaoBBRLTimeList(where1,dto);
					jjlDTO = dao.GetBaoBJJLTimeList(where1,dto1);
					
					bdto = dao.GetBaoBSUMBRLList(agentID,where);
					jdto = dao.GetBaoBSUMJJLList(where);
					
				}
				
				if(brlDTO.size()!=0){
					request.setAttribute("userlist", brlDTO);
					request.setAttribute("userlist1", jjlDTO);
					request.setAttribute("bdto", bdto);
					request.setAttribute("jdto", jdto);
					PageDTO pdto = new PageDTO();
					pdto.setCurPage(1);
					pdto.setTotalPage(1);
					pdto.setTotalRecord(dto.size());
					pdto.setPageSize(1);
					request.setAttribute("page", pdto);
					request.setAttribute("pageIndex", 1);
					request.setAttribute("pageSize", 100);
					request.setAttribute("startTime", startdateString);
				    request.setAttribute("endTime", enddateString);
				    if(typeName.equals("brg")){
				    	forward = "baobAgentBrg";
				    }
				    else{
				    	forward = "baobAgentJjg";
				    }
					return mapping.findForward(forward);
				}
				else{
					request.setAttribute("returnInfo", dao.returnInfo());
					request.setAttribute("startTime", startdateString);
				    request.setAttribute("endTime", enddateString);
				    if(typeName.equals("brg")){
				    	forward = "baobAgentBrg";
				    }
				    else{
				    	forward = "baobAgentJjg";
				    }
					return mapping.findForward(forward);
				}
			}
			else{
			/*if("accounts".equals(termId)&&!"".equals(termWord)){ 
				where = where+ " and proxyaccounts like '%"+termWord+"%'";
				dto = dao.GetBaoBBRLList(where, orderby);
				bdto = dao.GetBaoBSUMBRLList(where, where1);
				dto1 = dao.GetBaoBJJLList(where, orderby);
				
				jdto = dao.GetBaoBSUMJJLList(where, where1);
			}
			if("".equals(termId)||"".equals(termWord)){
				
				bdto = dao.GetBaoBSUMBRLList(where, where1);
				dto = dao.GetBaoBBRLList(where, orderby);
				dto1 = dao.GetBaoBJJLList(where, orderby);
				jdto = dao.GetBaoBSUMJJLList(where, where1);
			}*/
			
			if(dto.size()!=0){
				request.setAttribute("userlist", dto);
				request.setAttribute("userlist1", dto1);
				request.setAttribute("bdto", bdto);
				request.setAttribute("jdto", jdto);
				request.setAttribute("mybdto", mybrlDTO);
				request.setAttribute("myjdto", myjjlDTO);
				totalPage = dao.getTotalPage();
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRecordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("startTime", startdateString);
			    request.setAttribute("endTime", enddateString);
			    if(typeName.equals("brg")){
			    	forward = "baobAgentBrg";
			    }
			    else{
			    	forward = "baobAgentJjg";
			    }
				
				return mapping.findForward(forward);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				request.setAttribute("startTime", startdateString);
			    request.setAttribute("endTime", enddateString);
			    if(typeName.equals("brg")){
			    	forward = "baobAgentBrg";
			    }
			    else{
			    	forward = "baobAgentJjg";
			    }
				return mapping.findForward(forward);
				}
			}
			
	}
		
		if("baobDel".equals(action)){
			
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null){
				startTime = request.getParameter("startTime");
				
			}
			if(request.getParameter("endTime")!=null){
				endTime = request.getParameter("endTime");
				
			}
			String msg  = dao.delBaob(startTime,endTime);
			request.setAttribute("msg", msg);
			return mapping.findForward("baobDel");
		}
	
		return mapping.findForward(forward);
	}
}

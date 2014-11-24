package com.doowal.struts.action.bbtj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;


public class GameUserBBTJAction extends Action {

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
		//初始第一次进来是公司管理下面第一级，进入判断后根据查询的proxyID
		int agentID = 1;
		if(request.getParameter("userID")!= null&&request.getParameter("userID")!=""){
			agentID = Integer.parseInt(request.getParameter("userID"));
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>agentID："+agentID);
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
			String where = " 1=1 ";
			int levelID = 1;
			if(request.getParameter("levelID")!=null){
				levelID = Integer.parseInt(request.getParameter("levelID"));
				//where = " levelID="+levelID;
			}
/*			if(agentID != 0){
				where = where + " and prevProxy = "+agentID;
			}*/
			
			int kindId = 0;
			if(request.getParameter("kindId")!=null){
				kindId = Integer.parseInt(request.getParameter("kindId"));
				where += " AND kindId = "+kindId;
			}
			
			int utype = 0;
			if(request.getParameter("utype")!=null){
				utype = Integer.parseInt(request.getParameter("utype"));
			}
			String createTimeStart = "";
			String createTimeEnd = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择createTimeStart:"+createTimeStart);
				where += " AND PlayTime>"+DataTest.ToTime(createTimeStart);
				request.setAttribute("startTime", createTimeStart);
			   
			}
			if(request.getParameter("endTime")!=""&&request.getParameter("endTime")!=null){
				createTimeEnd = request.getParameter("endTime");
				System.out.println("选择createTimeEnd:"+createTimeEnd);
				where += " AND PlayTime<"+DataTest.ToTime(createTimeEnd);
				request.setAttribute("endTime", createTimeEnd);
				System.out.println("时间where:"+where);
			}
				System.out.println("-------------");
					if(request.getParameter("checkTime")!= null){
			
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			     if("today".equals(cheekValue)){   //今日
			    	 where += " AND PlayTime>="+DataTest.FromToday()+" AND PlayTime<"+DataTest.ToToday();
			    	 
				     //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				     startdateString = DataTest.FormatTimeStamp("", DataTest.FromToday());
				     enddateString = DataTest.FormatTimeStamp("", DataTest.ToToday());
				     System.out.println("今日开始时间："+startdateString+";今日结束时间："+enddateString);
				     request.setAttribute("startTime", DataTest.FormatTimeStamp("", DataTest.FromToday()));
				     request.setAttribute("endTime", DataTest.FormatTimeStamp("", DataTest.ToToday()));
			     }
			     else if("yestoday".equals(cheekValue)){ //昨日
			    	 where += " AND PlayTime>="+DataTest.FromYesterday()+" AND PlayTime<"+DataTest.ToYesterday(); 
			    	 
				     startdateString = DataTest.FormatTimeStamp("", DataTest.FromYesterday());
				     enddateString = DataTest.FormatTimeStamp("", DataTest.ToYesterday());
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			     }   //昨日
			     else if("bweek".equals(cheekValue)){//上周
				    where += " AND PlayTime>="+DataTest.FromYesWeek()+" AND PlayTime<"+DataTest.ToYesWeek(); 
				     startdateString = DataTest.FormatTimeStamp("", DataTest.FromYesWeek());
				     enddateString = DataTest.FormatTimeStamp("", DataTest.ToYesWeek());
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
				}else if("cweek".equals(cheekValue)){//本周
			    	where += " AND PlayTime>="+DataTest.FromWeek()+" AND PlayTime<"+DataTest.ToWeek(); 
				     startdateString = DataTest.FormatTimeStamp("", DataTest.FromWeek());
				     enddateString = DataTest.FormatTimeStamp("", DataTest.ToWeek());
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			    }else if("bmonth".equals(cheekValue)){//上月
			    	where += " AND PlayTime>="+DataTest.FromYesMonth()+" AND PlayTime<"+DataTest.ToYesMonth(); 
				     startdateString = DataTest.FormatTimeStamp("", DataTest.FromYesMonth());
				     enddateString = DataTest.FormatTimeStamp("", DataTest.ToYesMonth());
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			    }else if("cmonth".equals(cheekValue)){//本月
			    	where += " AND PlayTime>="+DataTest.FromMonth()+" AND PlayTime<"+DataTest.ToMonth(); 
				     startdateString = DataTest.FormatTimeStamp("", DataTest.FromMonth());
				     enddateString = DataTest.FormatTimeStamp("", DataTest.ToMonth());
				     request.setAttribute("startTime", startdateString);
				     request.setAttribute("endTime", enddateString);
			    }else if("all".equals(cheekValue)){//全部
			    	where += "";
			    	request.setAttribute("startTime", startdateString);
				    request.setAttribute("endTime", enddateString);
			    }
			  }
				System.out.println("checkTime-where:"+where);
			
			List<GameUserBBTJDTO> dto = null;
			List<GameUserBBTJDTO> dto1 = null;
			List<GameUserBBTJDTO> brlDTO = null;
			List<GameUserBBTJDTO> jjlDTO = null;
			GameUserBBTJDTO mybrlDTO = new GameUserBBTJDTO(); 
			List<GameUserBBTJDTO> myMemberbrlDTO = new ArrayList<GameUserBBTJDTO>(); 
			GameUserBBTJDTO myjjlDTO = new GameUserBBTJDTO(); 
			GameUserBBTJDTO sumbdto = new GameUserBBTJDTO();
			GameUserBBTJDTO sumjdto = new GameUserBBTJDTO();
			
			if("accounts".equals(termId)&&!"".equals(termWord)){ 
				mybrlDTO = dao.getMemberBB(where,agentID,levelID);
				where = where+ " and proxyaccounts like '%"+termWord+"%'";
				dto = dao.GetBaoBBRLList(where, orderby,agentID,levelID);
				sumbdto = dao.GetBaoBSUMBRLList(agentID,where);
				if(utype==2){
					myMemberbrlDTO = dao.getMemberInfoBB(where,agentID,levelID);
				}
				
				//dto1 = dao.GetBaoBJJLList(where, orderby);
				//sumjdto = dao.GetBaoBSUMJJLList(where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
				sumbdto = dao.GetBaoBSUMBRLList(agentID,where);
				dto = dao.GetBaoBBRLList(where, orderby,agentID,levelID);
				mybrlDTO = dao.getMemberBB(where,agentID,levelID);
				if(utype==2){
					myMemberbrlDTO = dao.getMemberInfoBB(where,agentID,levelID);
				}
				//dto1 = dao.GetBaoBJJLList(where, orderby);
				//sumjdto = dao.GetBaoBSUMJJLList(where);
			}
			
			if(dto.size()!=0){
				request.setAttribute("userlist", dto);
				request.setAttribute("userlist1", dto1);
				request.setAttribute("sumbdto", sumbdto);
				request.setAttribute("sumjdto", sumjdto);
				request.setAttribute("mybdto", mybrlDTO);
				request.setAttribute("myjdto", myjjlDTO);
				request.setAttribute("myMemberbrlDTO", myMemberbrlDTO);
				request.setAttribute("utype", utype);
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

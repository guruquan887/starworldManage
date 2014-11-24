package com.doowal.struts.action.bbtj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;


public class GameScoreInfoDetailAction extends Action {
	

	@Override
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
		int userID = -1;
		if(request.getParameter("userID")!="-1"||request.getParameter("userID")!=""||request.getParameter("userID")!=null){
			userID = Integer.parseInt(request.getParameter("userID"));
		}
		
		DataSource ds = this.getDataSource(request, "QPTreasureDB");
		GameUserBBTJDAO dao = new GameUserBBTJDAO(ds);
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}
		
		if (curPage < 1)
			curPage = 1;
		int pageSize=50;
		if("date".equals(action)){
			String where = "";
			String createTimeStart = "";
			String createTimeEnd = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择createTimeStart:"+createTimeStart);
				where = "generateTime >'"+createTimeStart+"'";
			}
			if(request.getParameter("endTime")!=""&&request.getParameter("endTime")!=null){
				createTimeEnd = request.getParameter("endTime");
				System.out.println("选择createTimeEnd:"+createTimeEnd);
				where += "and generateTime <'"+createTimeEnd+"' and";
				System.out.println("时间where:"+where);
			}else{
				System.out.println("-----进入checkTime-");
				if(request.getParameter("checkTime")!= null){
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			    if("bweek".equals(cheekValue)){//上周
				    where = "DATEDIFF(week,generateTime,getdate())=1 and"; 	
				}else if("cweek".equals(cheekValue)){//本周
			    	where = "DATEDIFF(week,generateTime,getdate())=0 and";
			    }else if("bmonth".equals(cheekValue)){//上月
			    	where = "DATEDIFF(month,generateTime,getdate())=1 and";
			    }else if("cmonth".equals(cheekValue)){//本月
			    	where = "DATEDIFF(month,generateTime,getdate())=0 and";
			    }else if("all".equals(cheekValue)){//全部
			    	where = "";
			    }
			  }else{
					where = "";//默认查询
					request.setAttribute("checkTime", "all");
				}
				System.out.println("checkTime-where:"+where);
			}
			
			String orderby = "generateTime";
			where += " userID="+userID;
			System.out.println(" 最后 where:"+where);
			
			int totalPage = 0;
			List<GameRecordDTO> list = null;
		    System.out.println("<<<<<<<<太阳城date 输赢详细记录");
			list = dao.GetDetailOfGameRecordByPage(curPage, pageSize, where, orderby);
			totalPage = dao.getTotalPage();
	        request.setAttribute("userlist", list);
	        
	        if(list.size()!=0){
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRecordCount());
				
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				
				forward = "success_gameRecord";
				return mapping.findForward(forward);
	        }
			else{
				
				request.setAttribute("returnInfo", dao.returnInfo());
				forward = "success_gameRecord";
				return mapping.findForward(forward);
			}
        }
		else if("day".equals(action)){
        	String where = "";
			String createTimeStart = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择date:"+createTimeStart);
				where = "DATEDIFF(day, generateTime, '"+createTimeStart+"')= 0 and";
			}else{
				if(request.getParameter("checkTime")!= null){
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			    if("bday".equals(cheekValue)){//昨日
				    where = "DATEDIFF(day,generateTime,getdate())=1 and"; 	
				   
				}else if("cday".equals(cheekValue)){//当日
			    	where = "DATEDIFF(day,generateTime,getdate())=0 and";
			    }
			  }else{
					where = "DATEDIFF(day, generateTime, getdate())= 0 and";//默认查询
					request.setAttribute("checkTime", "cday");
					System.out.println("22where:"+where);
				}
			}
			
			String orderby = "generateTime";
			where += " userID="+userID;
			System.out.println(" 最后 where:"+where);
			
			int totalPage = 0;
			List<GameRecordDTO> list = null;
		    System.out.println("<<<<<<<<太阳城day输赢详细记录");
			list = dao.GetDetailOfGameRecordByPage(curPage, pageSize, where, orderby);
			totalPage = dao.getTotalPage();
	        request.setAttribute("userlist", list);
	        
	        if(list.size()!=0){
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRecordCount());
				
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				
				forward = "success_gameRecordOfDate";
				return new ActionForward("/bbtj/gamescoreinfodetailofdate_gameRecord.jsp");
	        }
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				forward = "success_gameRecordOfDate";
				return new ActionForward("/bbtj/gamescoreinfodetailofdate_gameRecord.jsp");
			}
        }
		return mapping.findForward(forward);
	}
	
}

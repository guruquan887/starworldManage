package com.keno8.struts.action.gameUser.mark;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dto.PageDTO;

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
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		int totalPage = 0;
		DataSource ds = this.getDataSource(request, "Keno8");
		String orderby = "totalGoldXZ";
		GameUserBBTJDAO dao = new GameUserBBTJDAO(ds);
		if ("search".equals(action)){
			System.out.println("----查询、跳页");
			
			if(request.getParameter("selectOrderBy")!=null){
				orderby = request.getParameter("selectOrderBy");
				System.out.println("接受过来的排序方式:"+orderby);
			}
			String where = "";
			String createTimeStart = "";
			String createTimeEnd = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择createTimeStart:"+createTimeStart);
				where = "createTime >'"+createTimeStart+"'";
			}
			if(request.getParameter("endTime")!=""&&request.getParameter("endTime")!=null){
				createTimeEnd = request.getParameter("endTime");
				System.out.println("选择createTimeEnd:"+createTimeEnd);
				where += "and createTime <'"+createTimeEnd+"' ";
				System.out.println("时间where:"+where);
			}else{
				System.out.println("-------------");
				if(request.getParameter("checkTime")!= null){
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			    if("bweek".equals(cheekValue)){//上周
				    where = "DATEDIFF(week,createTime,getdate())=1 "; 	
				}else if("cweek".equals(cheekValue)){//本周
			    	where = "DATEDIFF(week,createTime,getdate())=0 ";
			    }else if("bmonth".equals(cheekValue)){//上月
			    	where = "DATEDIFF(month,createTime,getdate())=1 ";
			    }else if("cmonth".equals(cheekValue)){//本月
			    	where = "DATEDIFF(month,createTime,getdate())=0 ";
			    }else if("all".equals(cheekValue)){//全部
			    	where = "";
			    }
			  }else{
					where = "";//默认查询
					request.setAttribute("checkTime", "all");
				}
				System.out.println("checkTime-where:"+where);
			}
			List<GameUserBBTJDTO> dto = null;
			List<GameUserBBTJDTO> dto11 = dao.queryScore();
			GameUserBBTJDTO dto2 = null;
			
			if(where!=""){
				
				if("accounts".equals(termId)&&!"".equals(termWord)){ 
					where += " and accounts like '%"+termWord+"%'";
					System.out.println(where+",termId=1");
					double oneScore = dao.GetScore(termWord);
					dto = dao.GetByTime(curPage, pageSize, where+" and totalGoldXZ <>0",orderby,dto11);
					dto2 = dao.GetBySum(curPage, pageSize, where, orderby,oneScore);
				}
				if("userId".equals(termId)&&!"".equals(termWord)){
				    where += " and userId ="+termWord;
					System.out.println("termId=2");
					double oneScore = 0;
					try{
					 oneScore = dao.GetScoreByID(Integer.parseInt(termWord));
					}catch(Exception e) {
						System.out.println("输入错误，输入不是数字");
						where = " userId ="+0;
					}
					dto	= dao.GetByTime(curPage, pageSize, where+" and totalGoldXZ <>0",orderby,dto11);
					dto2 = dao.GetBySum(curPage, pageSize, where, orderby,oneScore);
				}
				if("".equals(termId)||"".equals(termWord)){
					double sumScore = dao.GetBySum().getScore();
				    dto = dao.GetByTime(curPage, pageSize, where+" and totalGoldXZ <>0",orderby,dto11);
				    dto2 = dao.GetBySum(curPage, pageSize, where, orderby,sumScore);
				}
				
				if(dto.size()!=0){
					request.setAttribute("userlist", dto);
					PageDTO pdto = new PageDTO();
					pdto.setCurPage(1);
					pdto.setTotalPage(1);
					pdto.setTotalRecord(dto.size());
					pdto.setPageSize(1);
					request.setAttribute("page", pdto);
					request.setAttribute("pageIndex", 1);
					request.setAttribute("pageSize", 100);
					request.setAttribute("sumNo", dto2);
					forward = "success";
					return mapping.findForward(forward);
				}
				else{
					request.setAttribute("returnInfo", dao.returnInfo());
					forward = "success";
					return mapping.findForward(forward);
				}
			}else{
			where = " accounts like '%"+termWord+"%'";
			if("accounts".equals(termId)&&!"".equals(termWord)){ 
				System.out.println(where+",termId=1");
				double oneScore = dao.GetScore(termWord);
				dto = dao.GetByPage(curPage, pageSize, where+" and totalGoldXZ <>0",orderby);
				dto2 = dao.GetBySum(curPage, pageSize, where, orderby,oneScore);
			}
			if("userId".equals(termId)&&!"".equals(termWord)){
			    where = " userId ="+termWord;
				System.out.println("termId=2");
				double oneScore = 0;
				try{
				 oneScore = dao.GetScoreByID(Integer.parseInt(termWord));
				}catch(Exception e) {
					System.out.println("输入错误，输入不是数字");
					where = "userId ="+0;
				}
				dto	= dao.GetByPage(curPage, pageSize, where+" and totalGoldXZ <>0",orderby);
				dto2 = dao.GetBySum(curPage, pageSize, where, orderby,oneScore);
			}
			if("".equals(termId)||"".equals(termWord)){
				double sumScore = dao.GetBySum().getScore();
			    dto = dao.GetByPage(curPage, pageSize, ""+" totalGoldXZ <>0",orderby);
			    dto2 = dao.GetBySum(curPage, pageSize, where, orderby,sumScore);
			}
			
			if(dto.size()!=0){
				request.setAttribute("userlist", dto);
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
				request.setAttribute("sumNo", dto2);
				forward = "success";
				return mapping.findForward(forward);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				forward = "success";
				return mapping.findForward(forward);
			}
			}
		}
			
		//-----------
	    System.out.println("----汇总");
		List<GameUserBBTJDTO> list = dao.GetByPage(curPage, pageSize, ""+" totalGoldXZ <>0", orderby);
		GameUserBBTJDTO	dto2 = dao.GetBySum();
		totalPage = dao.getTotalPage();
		request.setAttribute("userlist", list);
		request.setAttribute("checkTime", "all");
			
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		PageDTO pdto = new PageDTO();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRecordCount());
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("sumNo", dto2);
		forward = "success";
		return mapping.findForward(forward);
	}
	
	

}

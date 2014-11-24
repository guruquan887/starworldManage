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

public class GameUserBBTJ_DayAction extends Action {

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
		int pageSize = 200;
		if(request.getParameter("pageSize")!=null){
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int totalPage = 0;
		DataSource ds = this.getDataSource(request, "Keno8");
		GameUserBBTJDAO dao = new GameUserBBTJDAO(ds);
		System.out.println("curPage:"+curPage);
		if("search".equals(action)){
			String where = "";
			String createTimeStart = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择date:"+createTimeStart);
				where = "DATEDIFF(day, createTime, '"+createTimeStart+"')= 0 ";
			}else{
				if(request.getParameter("checkTime")!= null){
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			    if("bday".equals(cheekValue)){//昨日
				    where = "DATEDIFF(day,createTime,getdate())=1"; 	
				}else if("cday".equals(cheekValue)){//当日
			    	where = "DATEDIFF(day,createTime,getdate())=0";
			    }
			  }else{
					where = "DATEDIFF(day, createTime, getdate())= 0 ";//默认查询
//					request.setAttribute("startTime", new Date());
					request.setAttribute("checkTime", "cday");
					System.out.println("22where:"+where);
				}
			}
//			long time = 60000;
//			if(request.getParameter("showtime")!=""&&request.getParameter("showtime")!=null){
//				time = Long.parseLong(request.getParameter("showtime"));
//				System.out.println("选择time:"+time);
//				request.setAttribute("time", time);
//			}
			String orderby = "totalGoldXZ";
			if(request.getParameter("selectOrderBy")!=null){
				orderby = request.getParameter("selectOrderBy");
				System.out.println("接受过来的排序方式:"+orderby);
			}
			List<GameUserBBTJDTO> dto = null;
			GameUserBBTJDTO dto2 = null;
			List<GameUserBBTJDTO> dto11 = null;
			
			if("accounts".equals(termId)&&!"".equals(termWord)){
				where =" accounts like '%"+termWord+"%' and "+where;
				System.out.println("termId=1,where:"+where);
				double oneScore = dao.GetScore(termWord);
				dto11 = dao.GetListOfScoreByYesterday(where);
				System.out.println(">>>>>>>>>>>>"+dto11.size());
				dto = dao.GetDayByPage(curPage, pageSize, where+" and totalGoldXZ is not null",orderby,dto11);
				dto2 = dao.GetBySumOfDay(curPage, pageSize, where, orderby,oneScore);
			}
			if("userId".equals(termId)&&!"".equals(termWord)){
			    where = " userId ="+termWord+" and "+where;
				System.out.println("termId=2,where:"+where);
				double oneScore = 0;
				try{
				 oneScore = dao.GetScoreByID(Integer.parseInt(termWord));
				}catch(Exception e) {
					System.out.println("BBTJ_DayAction 输入错误，输入不是数字");
					where = " userId ="+0;
				}
				dto11 = dao.GetListOfScoreByYesterday(where);
				System.out.println(">>>>>>>>>>>>"+dto11.size());
				dto	= dao.GetDayByPage(curPage, pageSize, where+" and totalGoldXZ is not null",orderby,dto11);
				dto2 = dao.GetBySumOfDay(curPage, pageSize, where, orderby,oneScore);
			}
			if("".equals(termId)||"".equals(termWord)){
				System.out.println("where:"+where);
				double oneScore = dao.GetSumOfScoreByDay(where);
				dto11 = dao.GetListOfScoreByYesterday(where);
			    dto = dao.GetDayByPage(curPage, pageSize, where+" and totalGoldXZ is not null",orderby,dto11);
			    dto2 = dao.GetBySumOfDay(curPage, pageSize, where, orderby,oneScore);
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
				//return mapping.findForward(forward);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				forward = "success";
				//return mapping.findForward(forward);
			}
		}
		int type = 0;
		if(request.getParameter("type")!=null){
			type = Integer.parseInt(request.getParameter("type"));
		}
		request.setAttribute("type", type);
        if(type==2){
        	forward = "userSuccess";
        }
        else{
        	forward = "success";
        }
		
		return mapping.findForward(forward); 
     }
}

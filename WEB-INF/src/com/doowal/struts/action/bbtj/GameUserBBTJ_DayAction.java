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
		DataSource ds = this.getDataSource(request, "DataSource.QPTreasureDB");
		GameUserBBTJDAO dao = new GameUserBBTJDAO(ds);
		System.out.println("curPage:"+curPage);
		if("search".equals(action)){
			String where = "";
			String createTimeStart = "";
			String cheekValue = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				System.out.println("选择date:"+createTimeStart);
				where = "DATEDIFF(day, generateTime, '"+createTimeStart+"')= 0 ";
			}else{
				if(request.getParameter("checkTime")!= null){
			     cheekValue = request.getParameter("checkTime");
			     request.setAttribute("checkTime", cheekValue);
			     System.out.println("选择date-checkTime:"+cheekValue);
			    if("bday".equals(cheekValue)){//昨日
				    where = "DATEDIFF(day,generateTime,getdate())=1"; 	
				   
				}else if("cday".equals(cheekValue)){//当日
			    	where = "DATEDIFF(day,generateTime,getdate())=0";
			    }
			  }else{
					where = "DATEDIFF(day, generateTime, getdate())= 0 ";//默认查询
					request.setAttribute("checkTime", "cday");
					System.out.println("22where:"+where);
				}
			}
			String orderby = "betRecord";
			if(request.getParameter("selectOrderBy")!=null){
				orderby = request.getParameter("selectOrderBy");
				System.out.println("接受过来的排序方式:"+orderby);
			}
			List<GameUserBBTJDTO> dto = null;
			GameUserBBTJDTO dto2 = null;
			List<GameUserBBTJDTO> dto11 = null;
			dto11 = dao.GetListOfScoreByYesterday(where);  //获得往天的银子
			if("accounts".equals(termId)&&!"".equals(termWord)){
				where =" accounts like '%"+termWord+"%' and "+where;
				long oneScore = dao.GetScore(termWord);
				dto = dao.GetDayByPage(curPage, pageSize,where,orderby,dto11);
				dto2 = dao.GetByTimeSum(where,orderby,oneScore);
				System.out.println("termId=1,where:"+where);
			}
			if("userId".equals(termId)&&!"".equals(termWord)){
			    where = " userId ="+termWord+" and "+where;
				System.out.println("termId=2,where:"+where);
				long oneScore = 0;
				oneScore = dao.GetScoreByID(Integer.parseInt(termWord));
				dto = dao.GetDayByPage(curPage, pageSize,where,orderby,dto11);
				dto2 = dao.GetByTimeSum(where,orderby,oneScore);
			}
			if("".equals(termId)||"".equals(termWord)){
				long sumScore = dao.GetBySum().getScore();
				dto = dao.GetDayByPage(curPage, pageSize,where,orderby,dto11);
				dto2 = dao.GetByTimeSum(where,orderby,sumScore);
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
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				forward = "success";
			}
		}
        	forward = "success";
		return mapping.findForward(forward); 
     }
}

package com.keno8.struts.action.count;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CountRecordAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		if("countList".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			int roomId = 1;
			if(request.getParameter("roomId")!=null){
				roomId = Integer.parseInt(request.getParameter("roomId"));
			}
			String where = "";
			String createTimeStart = "";
			String createTimeEnd = "";
			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime");
				where = " and createTime>'"+createTimeStart+"'";
			}
			if(request.getParameter("endTime")!=""&&request.getParameter("endTime")!=null){
				createTimeEnd = request.getParameter("endTime");
				where = where + " and createTime<'"+createTimeEnd+"'";
			}
			CountRecordDAO dao = new CountRecordDAO(ds);
			List<CountRecordDTO> list1 = dao.queryRoomID();
			request.setAttribute("roomType", list1);
			long sumGoldBet = 0;
			if(roomId==16||roomId==17){
				sumGoldBet = dao.count3DRecord(roomId, where);
			}
			else{
				sumGoldBet = dao.countRecord(roomId, where);
			}
			
			request.setAttribute("roomId", roomId);
			request.setAttribute("startTime", createTimeStart);
			request.setAttribute("endTime", createTimeEnd);
			request.setAttribute("sumGoldBet", sumGoldBet);
			return mapping.findForward("countList");
		}

		
		return mapping.findForward("");
	}
	
	

}

package com.keno8.struts.action.single;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.PageDTO;

public class NoteSingleAction extends Action {

	
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
		
		if("noteSingleList".equals(action)){
			
			DataSource ds = this.getDataSource(request, "Keno8");
			NoteDAO dao = new NoteDAO(ds);
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
			String where = " roomId = 1";
			int roomId = 1;
			String peroidnum = "";
			String orderBy = "createTime";
			int num = 1000;
			if(request.getParameter("roomId")!=null){
				roomId = Integer.parseInt(request.getParameter("roomId"));
				if(roomId!=0){
					where = "roomId = "+roomId;
					if(roomId==21){
						orderBy = "createTime";
						//System.out.println("澳洲游戏排序");
					}
					else{
						orderBy = "peroidnum";
						//System.out.println("其他游戏排序");
					}
				}
				else{
					where = where +" ";
				}
			}
			
			String createTimeStart = "";
			String createTimeEnd = "";
			
//			if(request.getParameter("startTime")!=""&&request.getParameter("startTime")!=null){
//				createTimeStart = request.getParameter("startTime");      //统计的开始时间
//				request.setAttribute("startTime", createTimeStart);
//				where = where + " and createTime>'"+createTimeStart+"'";
//			}
//			if(request.getParameter("endTime")!=""&&request.getParameter("endTime")!=null){
//				createTimeEnd = request.getParameter("endTime");
//				request.setAttribute("endTime", createTimeEnd);
//				where = where + " and createTime<'"+createTimeEnd+"'";    //统计的结束时间
//			}
			
			
			if(!"".equals(request.getParameter("peroidnum"))&&request.getParameter("peroidnum")!=null){
				peroidnum = request.getParameter("peroidnum");
				where = where + " and peroidnum = '"+peroidnum+"'";
			}
			if(request.getParameter("num")!=null){
				num = Integer.parseInt(request.getParameter("num"));
			}
			System.out.println("where条件为："+where);
			List<NoteDTO> list = dao.GetRecordByPage(curPage,pageSize,where,num,orderBy);
			List<NoteDTO> list1 = dao.queryRoomID();
			PageDTO pdto = new PageDTO();
			if(list.size()!=0){
				request.setAttribute("noteSingleList", list);
				request.setAttribute("rooms", list1);
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(dao.getTotalPage());
				pdto.setTotalRecord(dao.getRecordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("num", num);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				
				/* 写入日志记录 */
				String roomName = dao.queryRoomName(roomId);
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				OperateDTO operateDto = new OperateDTO();
				String operateDetails = "";
				if(peroidnum==""){
					operateDetails = username+"进入查看游戏即时注单页面";
				}
				else{
					operateDetails = username + "查询<font color='red'>"+roomName+"</font>游戏，期数为<font color='red'>"+peroidnum+"</font>的即时注单";
				}
				
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				
				/*           */
			
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				request.setAttribute("rooms", list1);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			return mapping.findForward("noteSingleList");
		}
		
		else if("note3DsingleList".equals(action)){
			
			DataSource ds = this.getDataSource(request, "Keno8");
			NoteDAO dao = new NoteDAO(ds);
			
			int roomId = 16;
			if(request.getParameter("roomId")!=null){
				roomId = Integer.parseInt(request.getParameter("roomId"));
			}
			String peroidnum = dao.queryMaxPeroidnum(roomId);
	/*		if(request.getParameter("peroidnum1")!=null){
				peroidnum = request.getParameter("peroidnum1");
				System.out.println("搜索的期数为："+peroidnum);
			}
			else{
				
			}*/
			if(request.getParameter("peroidnum")!=null){
				peroidnum = request.getParameter("peroidnum");
				//System.out.println("+++++++++++++++++");
			}
			int num = 1000;
			if(request.getParameter("num")!=null){
				num = Integer.parseInt(request.getParameter("num"));
			}
			NoteDTO dto = dao.GetAll3DRecord(roomId, peroidnum,num);
			List<NoteDTO> list1 = dao.queryRoomID();
			List<NoteDTO> list2 = dao.queryPeroidnum(roomId);
			request.setAttribute("num", num);
			request.setAttribute("peroidnum", peroidnum);
			request.setAttribute("note3D", dto);
			request.setAttribute("rooms", list1);
			request.setAttribute("peroidnums", list2);
			return mapping.findForward("3DnotesingleList");
		}
		
		
		else if("noteDetails".equals(action)){
			
			DataSource ds = this.getDataSource(request, "Keno8");
			NoteDAO dao = new NoteDAO(ds);
			int roomId = Integer.parseInt(request.getParameter("roomId"));
			String peroidnum = request.getParameter("peroidnum");
			
			String roomName = dao.queryRoomID(roomId);
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
			String where = "roomId="+roomId +" and peroidnum="+peroidnum;
			List<NoteDTO> list = dao.GetRecordDetails(curPage, pageSize, where);
			request.setAttribute("roomId", roomId);
			request.setAttribute("roomName", roomName);
			request.setAttribute("peroidnum", peroidnum);
			
			PageDTO pdto = new PageDTO();
			if(list.size()!=0){
				request.setAttribute("noteDetailsList", list);
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(dao.getTotalPage());
				pdto.setTotalRecord(dao.getRecordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				
				/* 写入日志记录 */
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				OperateDTO operateDto = new OperateDTO();
				String operateDetails = username+"查看<font color='red'>"+roomName+"</font>游戏"+"期数<font color='red'>"+peroidnum+"</font>的即时注单详情";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				
				/*           */
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			return mapping.findForward("noteDetails");
		}
		
		request.setAttribute("msg", "管理列表打开失败，请联系管理员!");
		return mapping.findForward(null);
	}
	
	

}

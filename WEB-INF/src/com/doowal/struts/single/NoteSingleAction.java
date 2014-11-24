package com.doowal.struts.single;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.roomManage.GameRoomDAO;
import com.doowal.hk798.roomManage.GameRoomDTO;
import com.doowal.staworld.advertisement.PageDTO;


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
			
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			NoteDAO dao = new NoteDAO(ds);
			GameRoomDAO dao1 = new GameRoomDAO(ds);
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
			//String where = " kindID = 1001";//百家乐游戏
			String where = " 1=1";
			int roomID = 281;
			if(request.getParameter("roomID")!=null){
				roomID = Integer.parseInt(request.getParameter("roomID"));
				where +=" and serverID="+roomID;
			}
			String peroidnum = "";
			String orderby = "bureauID";
			if(request.getParameter("orderby")!=null){
				orderby=request.getParameter("orderby");

			}
			if(request.getParameter("pageSize")!=null){
				pageSize=Integer.parseInt(request.getParameter("pageSize"));

			}
/*			if(request.getParameter("roomId")!=null){
				roomId = Integer.parseInt(request.getParameter("roomId"));
				if(roomId!=0){
					where = "kindID = "+roomId;
				}
				else{
					where = where +" ";
				}
			}*/
			
			String accounts = "";
			if(!"".equals(request.getParameter("accounts"))&&request.getParameter("accounts")!=null){
				accounts = request.getParameter("accounts").trim();
				where += " and accounts='"+accounts+"'";
			}
//			String createTimeEnd = "";
			/*if(!"".equals(request.getParameter("startTime"))&&request.getParameter("startTime")!=null){
				createTimeStart = request.getParameter("startTime").trim();
				where += " and DATEDIFF(day,generateTime,'"+createTimeStart+"')=0 ";
			}
			
			if(!"".equals(request.getParameter("peroidnum"))&&request.getParameter("peroidnum")!=null){
				peroidnum = request.getParameter("peroidnum").trim();
				where = where + " and betSerial ='"+peroidnum+"'";
			}
			if(request.getParameter("num")!=null){
				num = Integer.parseInt(request.getParameter("num"));
			}*/
			System.out.println("where条件为："+where);
			List<NoteDTO> list = dao.GetRecordPetRealTimeByPage(curPage,pageSize,orderby,where);
			PageDTO pdto = new PageDTO();
			List<GameRoomDTO> roomList = dao1.getGameRoomList();
			request.setAttribute("roomList", roomList);
			if(list.size()!=0){
				request.setAttribute("noteSingleList", list);
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
				String operateDetails = "";
				operateDetails = username+"进入查看游戏即时注单页面";
				operateDetails = username +"查询游戏的即时注单";
				operateDetails = username + "查询游戏，期数为"+peroidnum+"的即时注单";
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
			return mapping.findForward("noteSingleList");
		}
		
		else if("noteDetails".equals(action)){
			
			DataSource ds = this.getDataSource(request, "DataSource.QPTreasureDB");
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
			String where = "kindID="+roomId +" and betSerial="+peroidnum;
			List<NoteDTO> list = dao.GetRecordDetails(curPage, pageSize, where);
			if(list.size() > 2){
			int j = 0;
			String sameNameColor = "";
			for(int i = 1; i < list.size(); i++){
				if(j == 0 && (list.get(i-1).getAccounts()).equals(list.get(i).getAccounts())){
					sameNameColor = "#CC3300";
					list.get(i-1).setSameNameColor(sameNameColor);
					j =0;
				}
				else if(j == 1 && (list.get(i-1).getAccounts()).equals(list.get(i).getAccounts())){
					sameNameColor = "green";
					list.get(i-1).setSameNameColor(sameNameColor);
					j =1;
				}
				else if(j == 2 && (list.get(i-1).getAccounts()).equals(list.get(i).getAccounts())){
					sameNameColor = "yellow";
					list.get(i-1).setSameNameColor(sameNameColor);
					j =2;
				}
				else{
					if(j < 2){
						list.get(i-1).setSameNameColor(sameNameColor);
						j++;
					}else{
						list.get(i-1).setSameNameColor(sameNameColor);
						j = 0;
					}
				}
				if( i == list.size()-1 ){//setLastOne
					list.get(i).setSameNameColor(sameNameColor);
					}
				}
			}
			
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
				String operateDetails = username+"查看"+roomName+"游戏"+"期数"+peroidnum+"的即时注单详情";
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

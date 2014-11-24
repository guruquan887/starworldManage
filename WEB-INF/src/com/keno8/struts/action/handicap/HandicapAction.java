package com.keno8.struts.action.handicap;

import java.util.Date;
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

public class HandicapAction extends Action {    //盘口设定

	@SuppressWarnings({ "deprecation", "unchecked" })
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
		if("handicapList".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			HandicapDAO dao = new HandicapDAO(ds);
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
			String pperoidnum = "";
			String orderBy = "peroidnum";
			String where = " roomId = 1";
			int roomId = 1;
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
			
			if(request.getParameter("pperoidnum")!=null&&request.getParameter("pperoidnum")!=""){
				pperoidnum = request.getParameter("pperoidnum");
				System.out.println("期数为："+pperoidnum);
				where = where + " and peroidnum = '"+pperoidnum+"'";
			}

			List<HandicapDTO> list = dao.GetRecordByPage(curPage,pageSize,where,orderBy);
			List<HandicapDTO> list1 = dao.queryRoomID();
			request.setAttribute("preodinumList", list);
			
			String shishiLe = "";
			if(list.size()!=0){
				if(list.get(0).getPeroidnum().contains("-")){
					shishiLe = list.get(0).getPeroidnum();
					String shishiFirst = shishiLe.substring(0,9);
					String shishiLeLast = shishiLe.substring(9,11);
					int shishiLeLast1 = Integer.parseInt(shishiLeLast)+1;
					System.out.println("设定的结果为："+shishiLeLast1);
					shishiLe = shishiFirst + shishiLeLast1;
					System.out.println("时时乐开奖号码："+shishiLe);
					request.setAttribute("peroidnum", shishiLe);
				}
				else{
					long peroidnum1 = Long.parseLong(list.get(0).getPeroidnum())+1;
					request.setAttribute("peroidnum", peroidnum1);
				}
			}
			
			int totalPage = dao.getTotalPage();
			request.setAttribute("rooms", list1);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRecordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			
			
			/* 写入日志  */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入盘口设置页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*        */
			return mapping.findForward("handicapList");
		}
		
		else if("addhandicap".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			int roomId = Integer.parseInt(request.getParameter("roomId"));
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String hour= request.getParameter("hour");
			String minute = request.getParameter("minute");
			String second = request.getParameter("second");
			String openTime = year + "-"+month + "-"+day+" "+hour+":"+minute+":"+second;
			String peroidnum = request.getParameter("peroidnum");
			
			String year1 = request.getParameter("year1");
			String month1 = request.getParameter("month1");
			String day1 = request.getParameter("day1");
			String hour1= request.getParameter("hour1");
			String minute1 = request.getParameter("minute1");
			String second1 = request.getParameter("second1");
			String openLotteryTime = year1 + "-"+month1 + "-"+day1+" "+hour1+":"+minute1+":"+second1;
			//long openLotteryTime1 = Long.parseLong(openLotteryTime);
			
			@SuppressWarnings("unused")
			Date myDate = new Date();
			
			//long dd = myDate.getTime();
			/*if(openTime1<dd){
				request.setAttribute("msg", "对不起，此时间已过期!");
				return mapping.findForward("failure");
			}
			if(openLotteryTime1<dd){
				request.setAttribute("msg", "对不起，此时间已过期!");
				return mapping.findForward("failure");
			}*/
		/*	if(openLotteryTime1<(openTime1+180000)){
				request.setAttribute("msg", "对不起，开盘时间与开奖时间至少相隔3分钟!");
				return mapping.findForward("failure");
			}*/
			System.out.println(openTime);
			HandicapDAO dao = new HandicapDAO(ds);
			HandicapDTO dto = new HandicapDTO();
			if(dao.checkExist(peroidnum,roomId)){
				request.setAttribute("msg", "添加失败，期数已经存在！");
				return mapping.findForward("failure");
				}
			else{
				dto.setRoomId(roomId);
				dto.setOpenHandTime(openTime);
				dto.setPeroidnum(peroidnum);
				dto.setOpenLotteryTime(openLotteryTime);
				dao.add(dto);
				
				/* 写入日志  */
				String roomName = dao.queryRoomName(roomId);
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				OperateDTO operateDto = new OperateDTO();
				String operateDetails = username+"增加<font color='red'>"+roomName+"</font>盘口<font color='red'>"+peroidnum+"</font>开奖时间=<font color='red'>"+openLotteryTime+"</font>";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				/*        */
				request.setAttribute("msg", "恭喜，添加成功！");
				return mapping.findForward("addhandicap");
			}
		}
		
		else if("delete".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			int  roomId =0;
			String peroidnum = "";
			if(request.getParameter("roomId")!=null){
				roomId=Integer.parseInt(request.getParameter("roomId"));
			}
			if(request.getParameter("peroidnum")!=null){
				peroidnum=request.getParameter("peroidnum");
			}
			HandicapDAO dao = new HandicapDAO(ds);
			
			if(peroidnum!=""){
				dao.Delete(roomId,peroidnum);
				
				/* 写入日志  */
				String roomName = dao.queryRoomName(roomId);
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				OperateDTO operateDto = new OperateDTO();
				String operateDetails = username+"删除<font color='red'>"+roomName+"</font>盘口<font color='red'>"+peroidnum+"</font>期";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				/*        */
				request.setAttribute("msg", "删除成功!");
			}
			else{
				request.setAttribute("msg", "删除失败，期数不存在！");
			}
			return mapping.findForward("delete");
		}
		
		return mapping.findForward("");
	}
	
	

}

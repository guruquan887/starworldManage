package com.doowal.hk798.gameUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.OperateDTO;
import com.doowal.hk798.admin.OperateLogsDAO;
import com.doowal.hk798.admin.PageDTO;

public class GameJQUserAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPAccountsDB");//DataSource.QPGameUserDB
		GameUserDAO dao = new GameUserDAO(ds);
		String action = "";
		
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		int roleId = 0;
		if(request.getSession().getAttribute("roleId")!=null){
			roleId = (Integer)request.getSession().getAttribute("roleId");
		}
		
		if("gameUserList".equals(action)){
			
		List<GameUserDTO> list = null;
		//统计机器人人数和银子
		GameUserDTO sumDTO = null;
		sumDTO = dao.sumAndroid();
		
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
		int serverID = 0;
		if(request.getParameter("serverID")!=null){
			serverID = Integer.parseInt(request.getParameter("serverID"));
		}
		int gameTypeID = 1;
		if(request.getParameter("gameTypeID")!=null){
			gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
		}
		
		if(request.getParameter("pageSize")!=null){
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String orderby="gameID"+" asc";
		String where = "1=1 ";
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
			/*if(orderby.equals("onLine")){
				orderby = "totalScore"+" desc";
				where =  where + " and kindID is not null";
			}*/
		}
		if(serverID==-1){
			where = where;
		}
		if(serverID!=0&&serverID!=-1){
			where = where + " and serverID="+serverID;
			orderby = "CreateDate"+" desc";
		}
		if(serverID==0){
			where = where + " and userID in (select userID from QPTreasureDB.dbo.AndroidManager) or userID in (select userID from QPGameScoreDB.dbo.AndroidManager)";
		}
		String termId = request.getParameter("selectOne");
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			System.out.println(termWord+"termId:"+termId);
		}
		
		int compareType = 0;
		int compareScore = 0;
		if(request.getParameter("compareType")!=null){
			compareType = Integer.parseInt(request.getParameter("compareType"));
		}
		if(request.getParameter("compareScore")!=null){
			compareScore = Integer.parseInt(request.getParameter("compareScore"));
		}
		
		if("accounts".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=1");
			System.out.println("进入搜索用户的判断");
			where = where + " and Accounts like '%"+termWord+"%' ";
			/*if(serverID!=0){
				where = where + " and serverID="+serverID;
			}
			if(serverID==0){
				where = where + " and isAndroid = 1 and userID not in (select userID from QPTreasureDB.dbo.AndroidManager)";
			}*/
			list = dao.GetJQRecordByPage(curPage, pageSize,orderby,where,gameTypeID);
		}
		if(compareType==0&&compareScore!=0){
			where = where + " and score>="+compareScore;
			/*if(serverID!=0){
				where = where + " and serverID="+serverID;
			}
			if(serverID==0){
				where = where + " and isAndroid = 1 and userID not in (select userID from QPTreasureDB.dbo.AndroidManager)";
			}*/
			System.out.println("进入大于银子数:"+compareScore);
			list = dao.GetJQRecordByPage(curPage, pageSize,orderby,where,gameTypeID);
		}
		if(compareType==1&&compareScore!=1){
			where = where + " and score<="+compareScore;
			/*if(serverID!=0){
				where = where + " and serverID="+serverID;
			}
			if(serverID==0){
				where = where + " and isAndroid = 1 and userID not in (select userID from QPTreasureDB.dbo.AndroidManager)";
			}*/
			System.out.println("进入小于银子数:"+compareScore);
			list = dao.GetJQRecordByPage(curPage, pageSize,orderby,where,gameTypeID);
		}
		
		
		if("gameID".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=2");
			where = where + " and gameID ="+termWord+"";
			/*if(serverID!=0){
				where = where + " and serverID="+serverID;
			}
			if(serverID==0){
				where = where + " and isAndroid = 1 and userID not in (select userID from QPTreasureDB.dbo.AndroidManager)";
			}*/
			System.out.println("进入搜索游戏编号的判断");
			list = dao.GetJQRecordByPage(curPage, pageSize,orderby,where,gameTypeID);
		}
		
		if("".equals(termId)||"".equals(termWord)){
			list = dao.GetJQRecordByPage(curPage, pageSize,orderby,where,gameTypeID);
		}
		List<GameRoomInfo> roomlist = dao.getServerRoom();
		request.setAttribute("roomlist", roomlist);
		System.out.println("--------------where:"+where);
		if(list.size()!=0){
		request.setAttribute("username", request.getSession().getAttribute("username"));
		request.setAttribute("userlist", list);
		PageDTO pdto = new PageDTO();
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(dao.getTotalPage());
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("sumDTO", sumDTO);
		request.setAttribute("gameTypeID", gameTypeID);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入查看机器人列表";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
			return mapping.findForward("jquserList");
		}
		else{	
			request.setAttribute("returnInfo", dao.returnInfo());
			return mapping.findForward("jquserList");
			}
		}
		
		
		if("zsScore".equals(action)){  //赠送机器人银子数
			
			String ss = request.getParameter("ss");
			String [] ids = ss.split(",");
			long score = 0;
			if(request.getParameter("score")!=null){
				score = Long.parseLong(request.getParameter("score"));
			}
			int serverID = 0;
			if(request.getParameter("serverID")!=null){
				serverID = Integer.parseInt(request.getParameter("serverID"));
			}
			int gameTypeID = 1;
			if(request.getParameter("gameTypeID")!=null){
			    gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
			}
			System.out.println(">>>>>>>>>>>>>>>>>>ids:"+ids);
			for(int i=0;i<ids.length;i++){
				dao.zsjqScore(ids[i],score);
				System.out.println("java单独值："+ids[i]);
			}
			request.setAttribute("gameTypeID", gameTypeID);
			request.setAttribute("serverID", serverID);
			request.setAttribute("msg", "赠送成功!");
			return mapping.findForward("zsScore");
			
		}
		
		if("zsJifen".equals(action)){  //赠送机器人积分数
			
			String ss = request.getParameter("ss");
			String [] ids = ss.split(",");
			long score = 0;
			if(request.getParameter("score")!=null){
				score = Long.parseLong(request.getParameter("score"));
			}
			int gameTypeID = 1;
			if(request.getParameter("gameTypeID")!=null){
			    gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
			}
			System.out.println(">>>>>>>>>>>>>>>>>>ids:"+ids);
			for(int i=0;i<ids.length;i++){
				dao.zsjqJifen(ids[i],score);
				System.out.println("java单独值："+ids[i]);
			}
			request.setAttribute("gameTypeID", gameTypeID);
			request.setAttribute("msg", "赠送成功!");
			return mapping.findForward("zsJifen");
			
		}
		
		if("zsExperience".equals(action)){  //赠送经验
			
			String ss = request.getParameter("ss");
			String [] ids = ss.split(",");
			int adminID = (Integer)request.getSession().getAttribute("Id");
			String Ip = request.getRemoteAddr();
			long score = 0;
			if(request.getParameter("score")!=null){
				score = Long.parseLong(request.getParameter("score"));
			}
			String reason = "";
			if(request.getParameter("in_Reason")!=null){
				reason = request.getParameter("in_Reason");
			}
			System.out.println(">>>>>>>>>>>>>>>>>>ids:"+ids);
			for(int i=0;i<ids.length;i++){
				String[] params = new String[] { String.valueOf(adminID),Ip,ids[i],String.valueOf(score),reason};
				dao.zsJqExperience(params);
				System.out.println("java单独值："+ids[i]);
			}
			request.setAttribute("msg", "赠送成功!");
			return mapping.findForward("zsExperience");
		}
		
		if("delAll".equals(action)){  //删除机器人
			
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.delAll(ids[i]);
				System.out.println(ids[i]);
			}
			int gameTypeID = 1;
			if(request.getParameter("gameTypeID")!=null){
			    gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
			}
			request.setAttribute("gameTypeID", gameTypeID);
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("delAll");
		}
		
		if("qxJuser".equals(action)){  //取消机器人
			
			String [] ids = request.getParameterValues("checkbox");
			int gameTypeID = 1;
			if(request.getParameter("gameTypeID")!=null){
			    gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
			}
			int count = 0;
			for(int i=0;i<ids.length;i++){
				dao.qxJuser(ids[i],gameTypeID);
				++count;
			}
			
			request.setAttribute("gameTypeID", gameTypeID);
			request.setAttribute("msg", "取消"+count+"个机器人成功!");
			return mapping.findForward("qxJuser");
		}
		
		if("OxNEW".equals(action)){  //牛牛特殊
			
			String [] ids = request.getParameterValues("checkbox");
			int gameTypeID = 1;
			if(request.getParameter("gameTypeID")!=null){
			    gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
			}
			int count = 0;
			for(int i=0;i<ids.length;i++){
				dao.oxNew(ids[i],gameTypeID);
				++count;
			}
			
			request.setAttribute("gameTypeID", gameTypeID);
			request.setAttribute("msg", "设定"+count+"个机器人成功!");
			return mapping.findForward("OxNEW");
		}
		
		if("RedLine".equals(action)){  //五张特殊
			
			String [] ids = request.getParameterValues("checkbox");
			int gameTypeID = 1;
			if(request.getParameter("gameTypeID")!=null){
			    gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
			}
			int count = 0;
			for(int i=0;i<ids.length;i++){
				dao.redLine(ids[i],gameTypeID);
				++count;
			}
			
			request.setAttribute("gameTypeID", gameTypeID);
			request.setAttribute("msg", "设定"+count+"个机器人成功!");
			return mapping.findForward("RedLine");
		}
		
		return null;
	}
}

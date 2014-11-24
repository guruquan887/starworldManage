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

public class GameGoldListAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		//DataSource ds = this.getDataSource(request, "QPGameUserDB");//DataSource.QPGameUserDB
		DataSource ds = this.getDataSource(request, "QPTreasureDB");
		GameUserDAO dao = new GameUserDAO(ds);
		GameScoreInfoDAO scoreDao = new GameScoreInfoDAO(ds);
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
		/*================================用户银子列表==================================*/
		if("gameGoldList".equals(action)){
			
		List<GameUserDTO> list = null;
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
		
		if(request.getParameter("pageSize")!=null){
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String orderby="score";
		String where = " IsAndroid!=1 and userID not in (select userID from QPTreasureDB.dbo.AndroidManager) and userID not in (select userID from QPGameScoreDB.dbo.AndroidManager)";
		String where1 = "";
		//where1 = " and interType = 0";
		
		if(request.getParameter("orderby")!=null){
			orderby = request.getParameter("orderby");
			System.out.println(orderby);
		}
		
		String termId = request.getParameter("selectOne");
		int interRoom = 0;
		if(request.getParameter("interRoom")!=null){
			interRoom = Integer.parseInt(request.getParameter("interRoom"));
		}
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			System.out.println(termWord);
		}
		
		if("accounts".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=1");
			System.out.println("进入搜索用户的判断");
			where = where + " and Accounts like '%"+termWord+"%'";
			list = dao.GetGoldRecordByPage(curPage, pageSize,orderby,where);
		}
		
		
		if("gameID".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=2");
			where = where + " and gameID ="+termWord+"";
			System.out.println("进入搜索游戏编号的判断");
			list = dao.GetGoldRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if(interRoom==1){
			where1 = " and userID in(select userID from QPTreasureDB.dbo.GameScoreLocker)";
			where = where + where1;
			list = dao.GetGoldRecordByPage(curPage, pageSize,orderby,where);
			
		}
		
		if("".equals(termId)||"".equals(termWord)){
			where = where + where1;
			list = dao.GetGoldRecordByPage(curPage, pageSize,orderby,where);
		}
		System.out.println("--------------where:"+where);
		if(list.size()!=0){
		request.setAttribute("username", request.getSession().getAttribute("username"));
		request.setAttribute("cordon", dao.queryCordon());//获得警戒线标志
		request.setAttribute("userlist", list);
		PageDTO pdto = new PageDTO();
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(dao.getTotalPage());
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入查看用户银子管理列表";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		}
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
			}
		
		return mapping.findForward("gameGoldList");
		}
		
		
		/*================================用户积分列表==================================*/
		if("gameScoreList".equals(action)){
			
		List<GameUserDTO> list = null;
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
		
		if(request.getParameter("pageSize")!=null){
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String orderby="score";
		String where = " IsAndroid!=1 and userID not in (select userID from QPTreasureDB.dbo.AndroidManager) and userID not in (select userID from QPGameScoreDB.dbo.AndroidManager)";
		String where1 = "";
		//where1 = " and interType = 0";
		
		if(request.getParameter("orderby")!=null){
			orderby = request.getParameter("orderby");
			System.out.println(orderby);
		}
		
		String termId = request.getParameter("selectOne");
		int interRoom = 0;
		if(request.getParameter("interRoom")!=null){
			interRoom = Integer.parseInt(request.getParameter("interRoom"));
		}
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			System.out.println(termWord);
		}
		
		if("accounts".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=1");
			System.out.println("进入搜索用户的判断");
			where = where + " and Accounts like '%"+termWord+"%'";
			list = dao.GetScoreRecordByPage(curPage, pageSize,orderby,where);
		}
		
		
		if("gameID".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=2");
			where = where + " and gameID ="+termWord+"";
			System.out.println("进入搜索游戏编号的判断");
			list = dao.GetScoreRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if(interRoom==1){
			where1 = " and userID in(select userID from QPGameScoreDB.dbo.GameScoreLocker)";
			where = where + where1;
			list = dao.GetScoreRecordByPage(curPage, pageSize,orderby,where);
			
		}
		
		if("".equals(termId)||"".equals(termWord)){
			where = where + where1;
			list = dao.GetScoreRecordByPage(curPage, pageSize,orderby,where);
		}
		System.out.println("--------------where:"+where);
		if(list.size()!=0){
		request.setAttribute("username", request.getSession().getAttribute("username"));
		request.setAttribute("cordon", dao.queryCordon());//获得警戒线标志
		request.setAttribute("userlist", list);
		PageDTO pdto = new PageDTO();
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(dao.getTotalPage());
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入查看用户积分管理列表";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		}
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
			}
		
		return mapping.findForward("gameScoreList");
		}
		
		/*=================================游戏记录==========================*/
		if("gamerecordList".equals(action)){  
			int userID = -1;
			if(request.getParameter("userID")!="-1"||request.getParameter("userID")!=""||request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
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
			int pageSize=30;
			String where = " userID ="+userID;
			String startTime = "";
			if(request.getParameter("startTime")!=null&&!"".equals(request.getParameter("startTime"))){
				startTime = request.getParameter("startTime");
				where = where + " and insertTime>='"+startTime+"'";
			}
			String endTime = "";
			if(request.getParameter("endTime")!=null&&!"".equals(request.getParameter("endTime"))){
				endTime = request.getParameter("endTime");
				where = where + " and insertTime<='"+endTime+"'";
			}
			//GameScoreInfoDTO dto1 = scoreDao.getById(userID);//获取输赢的总明细
			System.out.println(">>>>>>>>>>>>>>>>>>>where:"+where);
			List<GameScoreInfoDTO> list = scoreDao.GetRecordDetailByPage(curPage, pageSize,where);
			long totalRecordScore = scoreDao.sum(where);
			if(list.size()!=0){
				request.setAttribute("userlist", list);
				request.setAttribute("accounts", list.get(0).getAccounts());
				request.setAttribute("userID", list.get(0).getUserID());
				//request.setAttribute("dto", dto1);
				PageDTO pdto = new PageDTO();
				if(curPage>scoreDao.getTotalPage())curPage=scoreDao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(scoreDao.getTotalPage());
				pdto.setTotalRecord(scoreDao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("totalScore", totalRecordScore);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			else{
				request.setAttribute("returnInfo", scoreDao.returnInfo());
				} 
			
			return mapping.findForward("gamerecordList");
		}
		
		
		/*=================================代理新钱庄记录==========================*/
		if("gameproxybankRecord".equals(action)){
			
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
			List<GameBankRecordDTO> list = null;
			String where = " 1=1 and sourceAccounts is not null";
			String orderby = " collectDate";
			String termWord ="";
/*			int userID = 0;
			if(request.getParameter("userID")!=null||request.getParameter("userID")!="0"){
				    userID = Integer.parseInt(request.getParameter("userID"));
				    where = where + " and targetUserID="+userID+" or sourceUserID="+userID;
				}*/
			
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord);
			}
			if(request.getParameter("pageSize")!=null){
				try {
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				//startTime = startTime + " 00:00:00";
				where = where + " and collectDate>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				//endTime = endTime + " 23:59:59";
				where = where + " and collectDate<='"+endTime+"'";
			}
			
			String termId = request.getParameter("selectOne");
			if("sourceAccounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索汇款人的判断");
				where = where + " and SourceAccounts like '%"+termWord.trim()+"%'";
				list = scoreDao.GetProxyBankRecordByPage(curPage, pageSize,orderby,where);
			}
			
			if("targetAccounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=2");
				where = where + " and targetAccounts like '%"+termWord.trim()+"%'";
				System.out.println("进入搜索收款人的判断");
				list = scoreDao.GetProxyBankRecordByPage(curPage, pageSize,orderby,where);
			}
			
			if("".equals(termId)||"".equals(termWord)){
				list = scoreDao.GetProxyBankRecordByPage(curPage, pageSize,orderby,where);
			}
			System.out.println("where:"+where);
			if(list.size()!=0){
				request.setAttribute("userbankList", list);
				PageDTO pdto = new PageDTO();
				if(curPage>scoreDao.getTotalPage())curPage=scoreDao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(scoreDao.getTotalPage());
				pdto.setTotalRecord(scoreDao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("startTime", startTime);
			    request.setAttribute("endTime", endTime);
			}
			else{
				request.setAttribute("returnInfo", scoreDao.returnInfo());
				} 
			return mapping.findForward("gamebankRecord");
		}
		
		   //会员钱庄记录
		if("gamebankRecord".equals(action)){
			
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
			List<GameBankRecordDTO> list = null;
			String where = " 1=1 and sourceAccounts is not null";
			String orderby = " collectDate";
			String termWord ="";
/*			int userID = 0;
			if(request.getParameter("userID")!=null||request.getParameter("userID")!="0"){
				    userID = Integer.parseInt(request.getParameter("userID"));
				    where = where + " and targetUserID="+userID+" or sourceUserID="+userID;
				}*/
			
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord);
			}
			if(request.getParameter("pageSize")!=null){
				try {
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				//startTime = startTime + " 00:00:00";
				where = where + " and collectDate>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				//endTime = endTime + " 23:59:59";
				where = where + " and collectDate<='"+endTime+"'";
			}
			
			String termId = request.getParameter("selectOne");
			if("sourceAccounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索汇款人的判断");
				where = where + " and SourceAccounts like '%"+termWord.trim()+"%'";
				list = scoreDao.GetBankRecordByPage(curPage, pageSize,orderby,where);
			}
			
			if("targetAccounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=2");
				where = where + " and targetAccounts like '%"+termWord.trim()+"%'";
				System.out.println("进入搜索收款人的判断");
				list = scoreDao.GetBankRecordByPage(curPage, pageSize,orderby,where);
			}
			
			if("".equals(termId)||"".equals(termWord)){
				list = scoreDao.GetBankRecordByPage(curPage, pageSize,orderby,where);
			}
			System.out.println("where:"+where);
			if(list.size()!=0){
				request.setAttribute("userbankList", list);
				PageDTO pdto = new PageDTO();
				if(curPage>scoreDao.getTotalPage())curPage=scoreDao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(scoreDao.getTotalPage());
				pdto.setTotalRecord(scoreDao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("startTime", startTime);
			    request.setAttribute("endTime", endTime);
			}
			else{
				request.setAttribute("returnInfo", scoreDao.returnInfo());
				} 
			return mapping.findForward("gamebankRecord");
		}
		
		else if("rechargeRecord".equals(action)){
			int userID = -1;
			if(request.getParameter("userID")!="-1"||request.getParameter("userID")!=""||request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
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
			int pageSize=30;
			List<GameBankRecordDTO> list = null;
			String where = "winUserId="+userID+" and RecordTypeID=3";
			
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				startTime = startTime + " 00:00:00";
				where = "winUserId="+userID+" and recordTypeID=3 and generateTime>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				endTime = endTime + " 23:59:59";
				where = where + " and recordTypeID=3 and generateTime<='"+endTime+"'";
			}
			System.out.println("userID:"+userID+"where:"+where);
			String orderby = "generateTime";
			list = scoreDao.GetRechargeRecordByPage(curPage, pageSize,orderby,where,userID);
			if(list.size()!=0){
				request.setAttribute("userRechargeList", list);
				PageDTO pdto = new PageDTO();
				if(curPage>scoreDao.getTotalPage())curPage=scoreDao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(scoreDao.getTotalPage());
				pdto.setTotalRecord(scoreDao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			else{
				request.setAttribute("returnInfo", scoreDao.returnInfo());
				} 
			return mapping.findForward("rechargeRecord");
			
		}
		
		/*z游戏进出记录*/
		if("gameUserInOutRecord".equals(action)){
			
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
			List<GameBankRecordDTO> list = null;
			String where = " 1=1";
			
			int userID = 0;
			if(request.getParameter("userID")!=null&&request.getParameter("userID")!="0"){
				    userID = Integer.parseInt(request.getParameter("userID"));
				    where = where + " and userID="+userID;
				}
			String orderby = " enterTime";

			if(request.getParameter("pageSize")!=null){
				try {
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				//startTime = startTime + " 00:00:00";
				where = where + " and enterTime>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				//endTime = endTime + " 23:59:59";
				where = where + " and enterTime<='"+endTime+"'";
			}
			list = scoreDao.GetUserInOutRecordByPage(curPage, pageSize,orderby,where);
			System.out.println("where:"+where);
			if(list.size()!=0){
				request.setAttribute("userInOutList", list);
				PageDTO pdto = new PageDTO();
				if(curPage>scoreDao.getTotalPage())curPage=scoreDao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(scoreDao.getTotalPage());
				pdto.setTotalRecord(scoreDao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("startTime", startTime);
			    request.setAttribute("endTime", endTime);
			}
			else{
				request.setAttribute("returnInfo", scoreDao.returnInfo());
				} 
			return mapping.findForward("gameUserInOutRecord");
		}
		
		
		/*z游戏记录*/
		if("gameUserDrawRecord".equals(action)){
			
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
			List<GameBankRecordDTO> list = null;
			String where = " 1=1";
			
/*			int userID = 0;
			if(request.getParameter("userID")!=null&&request.getParameter("userID")!="0"){
				    userID = Integer.parseInt(request.getParameter("userID"));
				    where = where + " and userID="+userID;
				}*/
			String orderby = " insertTime";

			if(request.getParameter("pageSize")!=null){
				try {
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				where = where + " and insertTime>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + " and insertTime<='"+endTime+"'";
			}
			list = scoreDao.GetUserDrawRecordByPage(curPage, pageSize,orderby,where);
			System.out.println("where:"+where);
			if(list.size()!=0){
				request.setAttribute("userDrawRecordList", list);
				PageDTO pdto = new PageDTO();
				if(curPage>scoreDao.getTotalPage())curPage=scoreDao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(scoreDao.getTotalPage());
				pdto.setTotalRecord(scoreDao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("startTime", startTime);
			    request.setAttribute("endTime", endTime);
			}
			else{
				request.setAttribute("returnInfo", scoreDao.returnInfo());
				} 
			return mapping.findForward("gameUserDrawRecord");
		}
		
		//玩家输赢记录
		if("gameUserInfoRecord".equals(action)){
			
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
			List<GameBankRecordDTO> list = null;
			String where = " 1=1";
			
			int drawID = 0;
			if(request.getParameter("drawID")!=null&&request.getParameter("drawID")!="0"){
				drawID = Integer.parseInt(request.getParameter("drawID"));
				    where = where + " and drawID="+drawID;
				}
/*			int bureauID = 0;
			if(request.getParameter("bureauID")!=null&&request.getParameter("bureauID")!="0"){
				bureauID = Integer.parseInt(request.getParameter("bureauID"));
				    where = where + " and bureauID="+drawID;
				}*/
			String accounts = "";
			if(request.getParameter("accounts")!=null&&request.getParameter("accounts")!=""){
				accounts = request.getParameter("accounts");
				where += " and accounts='"+accounts+"'";
			}
			
			String orderby = " insertTime";

			if(request.getParameter("pageSize")!=null){
				try {
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String startTime = "";
			String endTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				where = where + " and insertTime>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + " and insertTime<='"+endTime+"'";
			}
			list = scoreDao.GetUserScoreRecordByPage(curPage, pageSize,orderby,where);
			System.out.println("where:"+where);
			if(list.size()!=0){
				request.setAttribute("userDrawRecordList", list);
				PageDTO pdto = new PageDTO();
				if(curPage>scoreDao.getTotalPage())curPage=scoreDao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(scoreDao.getTotalPage());
				pdto.setTotalRecord(scoreDao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("startTime", startTime);
			    request.setAttribute("endTime", endTime);
			}
			else{
				request.setAttribute("returnInfo", scoreDao.returnInfo());
				} 
			return mapping.findForward("gameUserInfoRecord");
		}
		return mapping.findForward("index");
	}

}

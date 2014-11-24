package com.doowal.hk798.gameUser;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class GameUserListAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPAccountsDB");//DataSource.QPGameUserDB
		DataSource ds1 = this.getDataSource(request, "QPTreasureDB");//DataSource.QPGameUserDB
		GameUserDAO dao = new GameUserDAO(ds);
		GameUserDAO dao1 = new GameUserDAO(ds1);
		String action = "";
		int type = 5;
		
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if(request.getParameter("type")!=null){
			type = Integer.parseInt(request.getParameter("type"));
		}
		int roleId = 0;
		if(request.getSession().getAttribute("roleId")!=null){
			roleId = (Integer)request.getSession().getAttribute("roleId");
		}
		
		if("gameUserList".equals(action)){
			
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
		int interRoom = 0;
		if(request.getParameter("interRoom")!=null){
			interRoom = Integer.parseInt(request.getParameter("interRoom"));
		}
		String orderby="totalScore";
		String where = "1=1 and UserID<>1 and UserID<>2";
		int spreaderID = 0;
		int proxyID = 0;
		if(request.getParameter("spreaderID")!=null){
			spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
			where = where + " and spreaderID="+spreaderID;
		}
		if(request.getParameter("proxyID")!=null){
			proxyID = Integer.parseInt(request.getParameter("proxyID"));
			where = where + " and proxyID="+proxyID;
		}
		int accountType = 0;
		if(request.getParameter("accountType")!=null){
			accountType = Integer.parseInt(request.getParameter("accountType"));
			where = where + " and accountType="+accountType;
		}
/*		if(request.getParameter("interType")!=null){
			type = Integer.parseInt(request.getParameter("interType"));
		}*/
		if(type==0){  //普通用户
			where = where + " and IsAndroid = 0";
		}
		else if(type==1){ //机器人
			where = where +" and IsAndroid = 1";
		}
		else if(type==2){ //内部账号
			where = where +" and IsAndroid = 2";
		}
		else if(type==3){ //优质账号
			where = where +" and IsAndroid = 3";
		}
		else{   
			where = where +" and IsAndroid in(0,2,3,-1)";
		}
		String where1 = "";
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
			/*if(orderby.equals("onLine")){
				orderby = "totalScore";
				where =  where + " and kindID is not null";
			}*/
		}
		
	/*	if(request.getParameter("spreaderID")!=null&&request.getParameter("spreaderID")!=""){
			spreaderID = request.getParameter("spreaderID");
			where = where + " and spreaderID='"+spreaderID+"'";
		}*/
		
		String termId = request.getParameter("selectOne");
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8").trim();
			System.out.println(termWord+"termId:"+termId);
		}
		
		int compareType = -1;
		int compareScore = 100000;
		if(request.getParameter("compareType")!=null){
			compareType = Integer.parseInt(request.getParameter("compareType"));
		}
		if(request.getParameter("compareScore")!=null){
			compareScore = Integer.parseInt(request.getParameter("compareScore"));
		}
		/*if(request.getParameter("interType")!=null){
			interType = Integer.parseInt(request.getParameter("interType"));
			where = where + " and IsAndroid ="+interType;
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}*/
		if("accounts".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=1");
			System.out.println("进入搜索用户的判断");
			where = " Accounts like '%"+termWord+"%' and IsAndroid in(0,2,3,-1)";
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		if(compareType==0&&compareScore>=0){
			where = where + " and (score+insureScore)>="+compareScore;
			System.out.println("进入大于银子数:"+compareScore);
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		if(compareType==1&&compareScore>=0){
			where = where + " and (score+insureScore)<="+compareScore;
			System.out.println("进入小于银子数:"+compareScore);
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("gameID".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=2");
			where = " gameID ="+termWord+" and IsAndroid in(0,2,3,-1)";
			System.out.println("进入搜索游戏编号的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("RegisterIP".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=3");
			where = where + " and RegisterIP ='"+termWord+"' ";
			System.out.println("进入搜索注册IP的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("LastLogonIP".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=4");
			where = where + " and LastLogonIP ='"+termWord+"'";
			System.out.println("进入搜索登录IP的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("RegisterMachine".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=5");
			where = where + " and RegisterMachine ='"+termWord+"'";
			System.out.println("进入搜索注册机器的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("LastLogonMachine".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=6");
			where = where + " and LastLogonMachine ='"+termWord+"'";
			System.out.println("进入搜索登录机器的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("tyAccounts".equals(termId)){
			System.out.println("termId=3");
			where1 = " and Nullity = 1";
			where = where + where1;
			System.out.println("进入停用账号的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("xxAccounts".equals(termId)){
			System.out.println("termId=3");
			where1 = " and userID in (select spreaderID from AccountsInfo)";
			where = where + where1;
			System.out.println("进入推广用户的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if(interRoom==1){  //判断是否在线
			where1 = " and userID in(select userID from QPTreasureDB.dbo.GameScoreLocker)";
			where = where + where1;
			System.out.println("进入在线用户的判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if(interRoom==2){
			where1 = " and RegisterMobile <> ''";
			where = where + where1;
			System.out.println("进入是否有电话判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if(interRoom==3){
			where1 = " and SpecialRight = 1";
			where = where + where1;
			System.out.println("进入特殊账号判断");
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
		
		if("".equals(termId)||"".equals(termWord)){
			System.out.println("termId=4");
			where = where + where1;
			list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		}
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
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("roleId", roleId);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入查看用真人用户列表";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		if(type==1){
			return mapping.findForward("gameUserList");
		}
		/*else if(type==1){
			return mapping.findForward("agentList");
		}*/
		else{
			return mapping.findForward("gameUserList");
		}
		}
		else{	
			request.setAttribute("returnInfo", dao.returnInfo());
			if(type==1){
				return mapping.findForward("gameUserList");
			}
			/*else if(type==1){
				return mapping.findForward("agentList");
			}*/
			else{
				return mapping.findForward("gameUserList");
			}
			}
		}
		
		
		if("MemberPreCun".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			int spreaderID = 0;
			if(request.getParameter("spreaderID")!=null){
				spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
			}
			long insureScore = dao.queryInsureScore(spreaderID); //上线的钱庄银子
			if(spreaderID==3){
			}
			else{
				request.setAttribute("insureScore", insureScore);
			}
			GameUserDTO dto = new GameUserDTO();
			dto = dao.getById(userID);
			request.setAttribute("spreaderID", spreaderID);
			request.setAttribute("dto", dto);
			return mapping.findForward("MemberPreCun");
			
		}
		
		if("MemberCun".equals(action)){
			int userID = 0;
			int xxuserID = 0;
			int spreaderID = 0;
			long gold = 0;
			long insureGold = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			if(request.getParameter("gold")!=null){
				gold = Long.parseLong(request.getParameter("gold"));
			}
			if(request.getParameter("insureGold")!=null){
				insureGold = Long.parseLong(request.getParameter("insureGold"));
			}
			if(request.getParameter("spreaderID")!=null){
				spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
			}
			if(spreaderID==3){
				spreaderID=0;
			}
			dao.updateAddGold(userID,spreaderID,insureGold,request.getRemoteAddr());
			request.setAttribute("msg", "恭喜，操作成功!");
			return mapping.findForward("MemberCun");
		}
		
		if("MemberPreQu".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			GameUserDTO dto = new GameUserDTO();
			dto = dao.getById(userID);
			request.setAttribute("dto", dto);
			return mapping.findForward("MemberPreQu");
			
		}
		
		if("MemberQu".equals(action)){
			int userID = 0;
			int gameID = 0;
			int spreaderID = 0;
			long gold = 0;
			long insureGold = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			if(request.getParameter("spreaderID")!=null){
				spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
			}
			if(spreaderID==3){
				spreaderID=0;
			}
			if(request.getParameter("gold")!=null){
				gold = Long.parseLong(request.getParameter("gold"));
			}
			if(request.getParameter("insureGold")!=null){
				insureGold = Long.parseLong(request.getParameter("insureGold"));
			}
			
			dao.updateUpGold(spreaderID,userID,insureGold,gold,request.getRemoteAddr());
			request.setAttribute("msg", "恭喜，操作成功!");
			return mapping.findForward("MemberQu");
		}
		
		if("zhFrozen".equals(action)){//账号冻结,停用
			int userID = 0;
			int userState = 0;
			int state = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			if(request.getParameter("userState")!=null){
				userState = Integer.parseInt(request.getParameter("userState"));
			}
			if(userState==0){
				state = 1;
			}
			if(userState==1){
				state = 0;
			}
			if(userState==2){
				state = 0;
			}
			if(userState==3){
				state = 2;
			}
			
			if(dao.zhFrozenUser(userID,state)){
				request.setAttribute("msg", "恭喜，操作成功!");
			}
			else{
				request.setAttribute("msg", "操作失败，请联系客服!");
			}
			return mapping.findForward("zhFrozen");
		}
		
		if("zhFrozenUser".equals(action)){//账号冻结
			int userID = 0;
			int userState = 0;
			int state = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			if(request.getParameter("userState")!=null){
				userState = Integer.parseInt(request.getParameter("userState"));
			}
			if(userState==0){
				state = 1;
			}
			if(userState==1){
				state = 0;
			}
			
			if(dao.zhFrozenUser(userID,state)){
				request.setAttribute("msg", "恭喜，操作成功!");
			}
			else{
				request.setAttribute("msg", "操作失败，请联系客服!");
			}
			return mapping.findForward("zhFrozenUser");
		}
		
		if("prezsGameID".equals(action)){  //赠送靓号
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			GameUserDTO dto = dao.getMemId(userID);
			List<GameUserDTO> list = dao.getRandomGameID();
			request.setAttribute("gameIDlist", list);
			request.setAttribute("gameID1", list.get(0).getGameID());
			request.setAttribute("user", dto);
			return mapping.findForward("prezsGameID");
		}
		
		if("zsGameID".equals(action)){  //赠送靓号
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			int adminID = (Integer)request.getSession().getAttribute("Id");
			String Ip = request.getRemoteAddr();
			int gameID = 0;
			if(request.getParameter("in_ReGameID")!=null){
				gameID = Integer.parseInt(request.getParameter("in_ReGameID"));
			}
			String in_Reason = "";
			if(request.getParameter("in_Reason")!=null){
				in_Reason = request.getParameter("in_Reason");
			}
			String result[] = {};
			String[] params = new String[] { String.valueOf(adminID),String.valueOf(userID),String.valueOf(gameID),Ip,in_Reason};
			result = dao.zsGameID(params);
			System.out.println("result:"+result);
			if (!result[0].equals("") && result[0] != null) {
				request.setAttribute("msg", result[0]);
			}
			else{
				request.setAttribute("msg", "赠送靓号成功!");
			}
			return mapping.findForward("zsGameID");
		}
		
		if("preUpdate".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			GameUserDTO dto = dao.getById(userID);
			request.setAttribute("user", dto);
			//System.out.println(">>>>>>>>userRight:"+dto.getUserRight());
			if(String.valueOf(dto.getUserRight())!=""){
				request.setAttribute("userRight", dto.getUserRight());
			}
			if(String.valueOf(dto.getMasterRight())!=""){
				request.setAttribute("masterRight", dto.getMasterRight());
			}
			return mapping.findForward("preUpdate");
		}
		
		if("update".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			String ysAccounts = dao.selectAccounts(userID);
			String ysNickName = dao.selectNickName(userID);
			String accounts = request.getParameter("accounts"); //用户名
			String regAccounts = request.getParameter("regAccounts"); //昵称
			String compellation = "";
			
			if(request.getParameter("compellation")!=null){//真实姓名
				compellation = request.getParameter("compellation");
			}
			int specialRight = 0;
			if(request.getParameter("specialRight")!=null){
				specialRight = Integer.parseInt(request.getParameter("specialRight"));  //特殊必赢账号
			}
			int winRate = 0;
			if(request.getParameter("winRate")!=null){
				winRate = Integer.parseInt(request.getParameter("winRate")); //胜率
			}
			long giftScore = 0;
			long giftScoreAgent = 0;
			int giftMinute = 0;
			int giftMinuteAgent = 0;
			if(request.getParameter("giftScore")!=null){
				giftScore = Long.parseLong(request.getParameter("giftScore"));
			}
			if(request.getParameter("giftScoreAgent")!=null){
				giftScoreAgent = Long.parseLong(request.getParameter("giftScoreAgent"));
			}
			if(request.getParameter("giftMinute")!=null){
				giftMinute = Integer.parseInt(request.getParameter("giftMinute"));
			}
			if(request.getParameter("giftMinuteAgent")!=null){
				giftMinuteAgent = Integer.parseInt(request.getParameter("giftMinuteAgent"));
			}
			String logonPass = "";
			GameUserDTO dto = new GameUserDTO();
			dto.setLogonPass("");
			dto.setSpecialRight(specialRight);
			dto.setWinRate(winRate);
			if(request.getParameter("logonPass")!=null&&request.getParameter("logonPass")!=""){
				logonPass = request.getParameter("logonPass").trim();
				dto.setLogonPass(EncryptionMD5.encryption_MD5(logonPass).toUpperCase());
			}
			String insurePass = "";
			dto.setInsurePass("");
			if(request.getParameter("insurePass")!=null&&request.getParameter("insurePass")!=""){
				insurePass = request.getParameter("insurePass").trim();
				dto.setInsurePass(EncryptionMD5.encryption_MD5(insurePass).toUpperCase());
			}
			
			String [] ids = {};
			int ss = 0;
			if(request.getParameterValues("in_UserRight")!=null){
				ids = request.getParameterValues("in_UserRight");
				for(int i=0;i<ids.length;i++){
					ss = ss | Integer.parseInt(ids[i]);
				}
			}
			
			String [] idss = {};
			int sss = 0;
			if(request.getParameterValues("in_MasterRight")!=null){
				idss = request.getParameterValues("in_MasterRight");
				for(int j=0;j<idss.length;j++){
					sss = sss | Integer.parseInt(idss[j]);
				}
			}
			int masterOrder = 0;
			if(request.getParameter("in_MasterOrder")!=null){
				masterOrder = Integer.parseInt(request.getParameter("in_MasterOrder"));
			}
			
			System.out.println("登陆密码："+logonPass+" 钱庄密码："+insurePass);
			int gender = Integer.parseInt(request.getParameter("gender"));
			String underWrite = request.getParameter("underWrite");
			int memberOrder = Integer.parseInt(request.getParameter("memberOrder"));
			System.out.println("会员等级："+memberOrder);
			dto.setUserID(userID);
			dto.setGender(gender);
			int count = dao.queryAccounts(accounts);
			dto.setMemberOrder(memberOrder);
			dto.setUnderWrite(underWrite);
			dto.setCompellation(compellation);
			dto.setMasterOrder(masterOrder);
			dto.setUserRight(ss);
			dto.setMasterRight(sss);
			dto.setGiftMinute(giftMinute);
			dto.setGiftMinuteAgent(giftMinuteAgent);
			dto.setGiftScore(giftScore);
			dto.setGiftScoreAgent(giftScoreAgent);
			
			System.out.println(">>>>>>>>>>>>>>>>>>ysAccounts:"+ysAccounts+"accounts:"+accounts);
			if(accounts.equals(ysAccounts)){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
				dto.setAccounts(accounts);
				dto.setRegAccounts(regAccounts);
				String msg = "";
				if(!regAccounts.equals(ysNickName)){
					if(dao.queryNickName(regAccounts)!=0){
						request.setAttribute("msg", "昵称已存在!");
						return mapping.findForward("updateFailure");
					}
					else{
						msg = dao.update(dto);
						if(msg!=null){
							request.setAttribute("msg", "修改用户信息成功!");
						}
						else{
							request.setAttribute("msg", "系统出错,请联系管理员!");
						}
					}
				}
				else{
					msg = dao.update(dto);
					if(msg!=null){
						request.setAttribute("msg", "修改用户信息成功!");
					}
					else{
						request.setAttribute("msg", "系统出错,请联系管理员!");
					}
				}
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"修改用户名："+dto.getAccounts()+"改后密码为："+logonPass;
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			}
			else if(regAccounts.equals(ysNickName)){
				
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
				dto.setAccounts(accounts);
				dto.setRegAccounts(regAccounts);
				String msg = "";
				if(!accounts.equals(ysAccounts)){
					if(count!=0){
						request.setAttribute("msg", "用户名已存在!");
						return mapping.findForward("updateFailure");
					}
					else{
						msg = dao.update(dto);
						if(msg!=null){
							request.setAttribute("msg", "修改用户信息成功!");
						}
						else{
							request.setAttribute("msg", "系统出错,请联系管理员!");
						}
					}
				}
				else{
					msg = dao.update(dto);
					if(msg!=null){
						request.setAttribute("msg", "修改用户信息成功!");
					}
					else{
						request.setAttribute("msg", "系统出错,请联系管理员!");
					}
				}
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"修改用户名："+dto.getAccounts()+"改后密码为："+logonPass;
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
				
			}
			else{
				
				if(count!=0){
					request.setAttribute("msg", "用户名已存在!");
					return mapping.findForward("updateFailure");
				}
				else{
					if(dao.queryNickName(regAccounts)!=0){
						request.setAttribute("msg", "昵称已存在!");
						return mapping.findForward("updateFailure");
					}
					else{
						dto.setAccounts(accounts);
						dto.setRegAccounts(regAccounts);
						String msg = dao.update(dto);
						if(msg!=null){
							request.setAttribute("msg", "修改用户信息成功!");
						}
						else{
							request.setAttribute("msg", "系统出错,请联系管理员!");
						}
					/* 写入日志记录 */
					String username = (String)request.getSession().getAttribute("username");
					String ip = (String)request.getSession().getAttribute("ip");
					OperateLogsDAO operateDao = new OperateLogsDAO(ds);
					OperateDTO operateDto = new OperateDTO();
					String operateDetails = username+"修改用户名："+dto.getAccounts()+"改后密码为："+logonPass;
					operateDto.setOperateName(username);
					operateDto.setOperateDetails(operateDetails);
					operateDto.setOperateIP(ip);
					operateDao.addLogs(operateDto);
					}
				}
			}
			return mapping.findForward("update");
		}
		
		if("deleteUser".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			
			if(dao.deleteUser(userID)){
				request.setAttribute("msg", "恭喜，删除成功!");
			}
			else{
				request.setAttribute("msg", "删除失败，请联系客服!");
			}
			return mapping.findForward("deleteUser");
		}
		
		
		if("userLogs".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			
			String accounts = dao.selectAccounts(userID);
			
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
			String orderby = "operateTime";
			String where = " userID="+userID;
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
				where = where + " and operateTime>='"+startTime+"'";
			}
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + " and operateTime<='"+endTime+"'";
			}
			List<GameUserDTO> list = null;
			list = dao.GetUserLogsByPage(curPage, pageSize,orderby,where);
			request.setAttribute("accounts", accounts);
			if(list.size()!=0){
				request.setAttribute("username", request.getSession().getAttribute("username"));
				request.setAttribute("operatelist", list);
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
				String operateDetails = username+"进入查看用用户活动日志";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
			}
			return mapping.findForward("userLogs");
		}
		
		
		if("delLogs".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			if(dao.DeleteLogs(userID)){
				request.setAttribute("msg", "删除成功!");
			}
			else{
				request.setAttribute("msg", "删除失败!");
			}
			return mapping.findForward("delLogs");
		}
		
		if("delAll".equals(action)){
			
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.delAll(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("delAll");
			
		}
		
		if("qxSpecialRight".equals(action)){
			
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.qxSpecialRight(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "取消成功!");
			return mapping.findForward("qxSpecialRight");
			
		}
		
		if("interType".equals(action)){  //设定为内部账号
			
			String [] ids = request.getParameterValues("checkbox");
			if(ids!=null&&ids.length>0){
				for(int i=0;i<ids.length;i++){
					dao.interType(ids[i]);
					System.out.println(ids[i]);
				}
			}
			request.setAttribute("msg", "设定成功!");
			return mapping.findForward("interType");
		}
		
		if("qxinterType".equals(action)){  //取消内部账号
			String [] ids = request.getParameterValues("checkbox");
			if(ids!=null&&ids.length>0){
				for(int i=0;i<ids.length;i++){
					dao.qxinterType(ids[i]);
					System.out.println(ids[i]);
				}
			}
			request.setAttribute("msg", "取消成功!");
			return mapping.findForward("interType");
		}
		
		if("vipType".equals(action)){  //设定为优质账号
			
			String [] ids = request.getParameterValues("checkbox");
			if(ids!=null&&ids.length>0){
				for(int i=0;i<ids.length;i++){
					dao.vipType(ids[i]);
					System.out.println(ids[i]);
				}
			}
			request.setAttribute("msg", "设定成功!");
			return mapping.findForward("interType");
		}
		
		if("qxvipType".equals(action)){  //取消优质账号
			
			String [] ids = request.getParameterValues("checkbox");
			if(ids!=null&&ids.length>0){
				for(int i=0;i<ids.length;i++){
					dao.qxvipType(ids[i]);
					System.out.println(ids[i]);
				}
			}
			request.setAttribute("msg", "取消成功!");
			return mapping.findForward("interType");
		}
		
		if("interUser".equals(action)){
			String userID = "0";
			if(request.getParameter("userID")!=null){
				userID = request.getParameter("userID");
			}
			
			if(dao.interType(userID)){
				request.setAttribute("msg", "恭喜，设定成功!");
			}
			else{
				request.setAttribute("msg", "设定失败，请联系客服!");
			}
			return mapping.findForward("interType");
		}
		
		if("qxinterUser".equals(action)){
			String userID = "0";
			if(request.getParameter("userID")!=null){
				userID = request.getParameter("userID");
			}
			
			if(dao.qxinterType(userID)){
				request.setAttribute("msg", "恭喜，取消成功!");
			}
			else{
				request.setAttribute("msg", "设定失败，请联系客服!");
			}
			return mapping.findForward("interType");
		}
		
		if("Freeze".equals(action)){  //冻结
			int date = 0;
			if(request.getParameter("date")!=null){
				date = Integer.parseInt(request.getParameter("date"));
			}
			int minute = 0;
			if(request.getParameter("minute")!=null){
				minute = Integer.parseInt(request.getParameter("minute"));
			}
			
			String ss = request.getParameter("ss");
			String [] ids = ss.split(",");
			
			System.out.println("ids:"+ids);
			for(int i=0;i<ids.length;i++){
				if(minute!=0){
					dao.Freeze(ids[i],date,minute);
				}
				else{
					dao.Freeze(ids[i],date,minute);
				}
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "冻结成功!");
			return mapping.findForward("freeze");
			
		}
		
		if("UnFreeze".equals(action)){  //解冻
			
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.UnFreeze(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "解冻成功!");
			return mapping.findForward("UnFreeze");
			
		}
		
/*		if("prezsScore".equals(action)){
			
			String [] ids = request.getParameterValues("checkbox");
			String ss = "";
			for(int i=0;i<ids.length;i++){
				ss = ids[i]+",";
				ss = ss.substring(0, ss.length()-1);
			}
			return mapping.findForward("prezsScore");
		}*/
		
		if("zsScore".equals(action)){  //赠送银子数
			
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
			if(ids!=null&&ids.length>0){
				for(int i=0;i<ids.length;i++){
					String[] params = new String[] { String.valueOf(adminID),Ip,ids[i],String.valueOf(score),reason};
					dao.zsScore(params);
					System.out.println("java单独值："+ids[i]);
				}
			}
			request.setAttribute("msg", "赠送成功!");
			return mapping.findForward("zsScore");
			
		}
		
		if("zsJifen".equals(action)){  //赠送积分
			
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
				String[] params = new String[] { String.valueOf(adminID),Ip,ids[i],"0",String.valueOf(score),reason};
				dao.zsJifen(params);
				System.out.println("java单独值："+ids[i]);
			}
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
				dao.zsExperience(params);
				System.out.println("java单独值："+ids[i]);
			}
			request.setAttribute("msg", "赠送成功!");
			return mapping.findForward("zsExperience");
		}
		
		
		if("clearScore".equals(action)){  //清零积分
			
			String [] ids = request.getParameterValues("checkbox");
			int adminID = (Integer)request.getSession().getAttribute("Id");
			String Ip = request.getRemoteAddr();
			int kindId = 0;
			String reason = "负分清零";
			String[] result = {};
			if(ids!=null&&ids.length>0){
				for(int i=0;i<ids.length;i++){
					String[] params = new String[] { String.valueOf(adminID),Ip,ids[i],String.valueOf(kindId),reason};
					result = dao.clearScore(params);
					System.out.println(ids[i]);
				}
			}
			System.out.println("result:"+result);
			if (!result[0].equals("") && result[0] != null) {
				request.setAttribute("msg", result[0]);
			}
			else{
				request.setAttribute("msg", "清零成功!");
			}
			return mapping.findForward("clearScore");
		}
		
		if("clearFlee".equals(action)){  //清零逃率
			String [] ids = request.getParameterValues("checkbox");
			int adminID = (Integer)request.getSession().getAttribute("Id");
			String Ip = request.getRemoteAddr();
			int kindId = 0;
			String reason = "逃率清零";
			String[] result = {};
			if(ids!=null&&ids.length>0){
				for(int i=0;i<ids.length;i++){
					String[] params = new String[] { String.valueOf(adminID),Ip,ids[i],String.valueOf(kindId),reason};
					result = dao.clearFlee(params);
					System.out.println(ids[i]);
				}
			}
			System.out.println("result:"+result);
			if (!result[0].equals("") && result[0] != null) {
				request.setAttribute("msg", result[0]);
			}
			else{
				request.setAttribute("msg", "清零成功!");
			}
			return mapping.findForward("clearScore");
		}
		
		if("setupAndroid".equals(action)){  //设置机器人
			
			String [] ids = request.getParameterValues("checkbox");
			int count = 0;
			for(int i=0;i<ids.length;i++){
				dao.setAndroid(ids[i]);
				++count;
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "恭喜,成功添加"+count+"个机器人!");
			return mapping.findForward("setupAndroid");
			
		}
		
		if("presetupJuser".equals(action)){  //预设置机器人
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				if(dao.queryAndroidCount(Integer.parseInt(ids[i]))==1){
					request.setAttribute("msg", "对不起，你选中的用户数据已经被设置过房间!");
					return mapping.findForward("presetupJuserFailure");
				}
			}
			List<GameRoomInfo> roomlist = dao.getServerRoom();
			request.setAttribute("roomlist", roomlist);
			return mapping.findForward("presetupJuser");
			
		}
		
		if("setupJuser".equals(action)){  //设置机器人
			
			String mm = request.getParameter("ss");
			String [] userID = mm.split(",");
			int gameTypeID = 1;
			if(request.getParameter("gameTypeID")!=null){
				gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
			}
			int serverID = 0;
			if(request.getParameter("serverID")!=null){
				serverID = Integer.parseInt(request.getParameter("serverID"));
			}
			int minPlayDraw = 0;
			if(request.getParameter("MinPlayDraw")!=null){
				minPlayDraw = Integer.parseInt(request.getParameter("MinPlayDraw"));
			}
			int maxPlayDraw = 0;
			if(request.getParameter("MaxPlayDraw")!=null){
				maxPlayDraw = Integer.parseInt(request.getParameter("MaxPlayDraw"));
			}
			long minTakeScore = 0;
			if(request.getParameter("MinTakeScore")!=null){
				minTakeScore = Long.parseLong(request.getParameter("MinTakeScore"));
			}
			long maxTakeScore = 0;
			if(request.getParameter("MaxTakeScore")!=null){
				maxTakeScore = Long.parseLong(request.getParameter("MaxTakeScore"));
			}
			int minReposeTime = 0;
			if(request.getParameter("MinReposeTime")!=null){
				minReposeTime = Integer.parseInt(request.getParameter("MinReposeTime"));
			}
			int maxReposeTime = 0;
			if(request.getParameter("MaxReposeTime")!=null){
				maxReposeTime = Integer.parseInt(request.getParameter("MaxReposeTime"));
			}
			
			String androidNote = "";
			if(request.getParameter("AndroidNote")!=null&&request.getParameter("AndroidNote")!=""){
				androidNote = request.getParameter("androidNote");
			}
			int nullity = 0;
			if(request.getParameter("in_Nullity")!=null){
				nullity = Integer.parseInt(request.getParameter("in_Nullity"));
			}
			
			String [] ids = request.getParameterValues("ServiceGender");
			int ss = 0;
			for(int i=0;i<ids.length;i++){
				ss = ss | Integer.parseInt(ids[i]);
			}
			System.out.println("ss:"+ss);
			String [] idss = request.getParameterValues("ServiceTime");
			int sss = 0;
			for(int i=0;i<idss.length;i++){
				sss = sss | Integer.parseInt(idss[i]);
			}
			System.out.println(">>>>>>>>>sss:"+sss);
			GameRoomInfo dto = new GameRoomInfo();
			dto.setServerID(serverID);
			dto.setMinPlayDraw(minPlayDraw);
			dto.setMaxPlayDraw(maxPlayDraw);
			dto.setMinTakeScore(minTakeScore);
			dto.setMaxTakeScore(maxTakeScore);
			dto.setMinReposeTime(minReposeTime);
			dto.setMaxReposeTime(maxReposeTime);
			dto.setServiceGender(ss);
			dto.setServiceTime(sss);
			dto.setAndroidNote(androidNote);
			dto.setNullity(nullity);
			int count = 0;
			for(int i=0;i<userID.length;i++){
				dto.setUserID(Integer.parseInt(userID[i]));
				System.out.println(">>>>>>>>>>>>>>>>>>>>userID:"+userID[i]);
				++count;
				dao.setupJuser(dto,gameTypeID);
			}
			request.setAttribute("msg", "恭喜,成功设置房间里"+count+"个机器人!");
			return mapping.findForward("setupJuser");
		}

		if("preAdd".equals(action)){
			
			int userID = 0;
			int spreaderID = 0;
			String accounts = "";
			if(request.getParameter("spreaderID")!=null){
				spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
			}
			if(request.getParameter("accounts")!=null){
				accounts = request.getParameter("accounts");
				accounts = new String(accounts.getBytes("ISO_8859_1"),"UTF-8");
			}
			userID = dao.selectUserID(spreaderID);
			GameUserDTO dto = new GameUserDTO();
			dto.setUserID(userID);
			dto.setAccounts(accounts);
			dto.setSpreaderID(String.valueOf(spreaderID));
			//List<GoldXhDTO> list = dao.getLimit(userID);
			request.setAttribute("dto", dto);
			request.setAttribute("accounts", accounts);
			//request.setAttribute("xhList", list);//限红筹码类型列表
			return mapping.findForward("preAdd");
		}
		
		if("addUser".equals(action)){
			String sxAccounts = "";
			String accounts = "";
			String loginPWD = "";
			String bankPWD = "";
			String regAccounts = "";
			String userType = "0";
			String spreaderID = "0";
			if(request.getParameter("sxAccounts")!=null){
				sxAccounts = request.getParameter("sxAccounts");
			}
			System.out.println("sxAccounts:"+sxAccounts);
			if(request.getParameter("accounts")!=null){
				accounts = request.getParameter("accounts");
			}
			if(request.getParameter("password1")!=null){
				loginPWD = request.getParameter("password1");
			}
			if(request.getParameter("bankpass2")!=null){
				bankPWD = request.getParameter("bankpass2");
			}
			if(request.getParameter("regAccounts")!=null){
				regAccounts = request.getParameter("regAccounts");
			}
			/*if(request.getParameter("userType")!=null){
				userType = request.getParameter("userType");
			}*/
			if(request.getParameter("spreaderID")!=null){
				spreaderID = request.getParameter("spreaderID");
			}
			//GameUserDTO dto11 = dao.getById(sxAccounts);
			String loginPass = EncryptionMD5.encryption_MD5(loginPWD);
			String bankpass = EncryptionMD5.encryption_MD5(bankPWD);
			String ip = request.getRemoteAddr();
			/*double bjlZC = 0;
			double bjlYJ = 0;
			double tax = 0;
			String levelID = "0";
			if(request.getParameter("bjlZC")!=null){
				bjlZC = Double.parseDouble(request.getParameter("bjlZC"));
			}
			if(request.getParameter("bjlYJ")!=null){
				bjlYJ = Double.parseDouble(request.getParameter("bjlYJ"));
			}
			if(request.getParameter("tax")!=null){
				tax = Double.parseDouble(request.getParameter("tax"));
			}*/
			
			if(dao.checkExit(accounts)!=0){
				request.setAttribute("msg", "账号已被注册，请重新输入!");
				
			}
			else{
			/*String[] params = new String[] { accounts,regAccounts,loginPWD,bankPWD,loginPass, bankpass,
					userType,ip,spreaderID,levelID,String.valueOf(bjlZC),String.valueOf(bjlYJ),String.valueOf(tax)};*/
				String[] params = new String[] { accounts, regAccounts ,loginPass.toUpperCase() ,bankpass.toUpperCase() , "0" ,
						"0" ,String.valueOf(dao.selectProxyID(sxAccounts)),spreaderID, "" , "" , "" , ip,""};
			String[] result = dao.regAccounts(params);
			if (!result.equals("") && result != null) {
				/*if (result[0].equals("0")) {
					request.setAttribute("msg", result[1]);
					System.out.println("----------------------------------"+result[1]);
					return mapping.findForward("failure");
				} else {
					int m = dao.queryMaxID();//15
					final int mm = m;

					String qq = "";
					for(int i = 0;i < mm ;i++){
		            	if( "on".equals(request.getParameter("XH_"+m+"_manage")) ){
		            		qq = qq + m+"," ;
		            		m--;
		            	}
		            	else{
		            		m--;
		            	}
		            }
		            qq = qq.substring(0,qq.length()-1);
		            System.out.println("修改后的限红管理数值："+qq);
		            dao.addLimitRelation(accounts,qq);
					
					int userID = dao.selectUserID(accounts);
					dao.updateAccount(userID);
					List<RuleDTO> listDTO = dao.getRuleList();
					for(int i = 0; i < listDTO.size(); i++){
						dao.addAdminValues(userID,i+1);
					}
				}*/
					/*long regGold = dao.queryRegGold();
					long regScore = dao.queryRegScore();
					dao.addGoldByUserID(userID,regGold,regScore);*/
					//request.getSession().setAttribute("UserId", userID);
					request.getSession().setAttribute("accounts", accounts);
					request.getSession().setAttribute("GameId", result[1]);
					/*request.setAttribute("msg", result[1]
							+ " 您的GameID号为:[ " + result[2] + " ]");*/
					request.setAttribute("msg"," 您的GameID号为:[ " + result[1] + " ]");
					System.out.println("ddddddddddddddddddddddd"+result[1]);
					
					return mapping.findForward("addUser");
				
				}
			}
			return mapping.findForward("addUser");
		}
		
		else if("checkMemName".equals(action)){
			
			String accounts = "";
			if(request.getParameter("accounts")!=null){
				accounts = request.getParameter("accounts");
				accounts = new String(accounts.getBytes("ISO_8859_1"),"UTF-8");
			}
			System.out.println("account:"+accounts);
			int count = dao.checkMemName(accounts);
			System.out.println(count);
			PrintWriter out;
			out = response.getWriter();
			if (count != 0) {
				out.print("-1");
			} else {
				out.print("0");
			}
		}
		
		else if("preauthRelation".equals(action)){
			
			int userID = Integer.parseInt(request.getParameter("userID"));
			int [] adminValues = dao.getAdmin(userID); // ordery by id;
			List<RuleDTO> listDTO = dao.getRuleList();
			for(int i = 0; i < listDTO.size(); i++){
				for(int j = 0; j < adminValues.length; j++){
				if( i+1 == adminValues[j] ){
					listDTO.get(i).setRuleState(1);
					break;
				}else{
					listDTO.get(i).setRuleState(0);
					}
			   }
		    }
			request.setAttribute("ruleList", listDTO);
			return mapping.findForward("preauthRelation");
		}
		
		else if("updateauthRelation".equals(action)){
			String msg = "";
			int userID = Integer.parseInt(request.getParameter("userID"));
			String accounts = request.getParameter("accounts");
			
			int [] adminValues = dao.getAdmin(userID); // ordery by id ;
			List<RuleDTO> listDTO = dao.getRuleList();
			String [] checkValues = new String[listDTO.size()];
			int m = 0;
			for(int k =0; k < adminValues.length; k++){
				if(adminValues[k]==0){
					m =k;break;
				}
			}
//			System.out.println("------------------------m:"+m);
			if(m == 0){
				//直接写入 选中项
				for(int i =0; i < checkValues.length; i++){ 
					checkValues[i] = request.getParameter("GridView1_"+(i+1)+"_checkboxname"); 
//					System.out.println(i+1+":"+checkValues[i]);
					if("on".equals(checkValues[i])){
						if(dao.addAdminValues(userID,i+1) == 0){
							msg = "设定失败";
							request.setAttribute("msg", msg);
							return mapping.findForward("updateauthRelation");
						}
					}
				}
			}else{
				for(int i =0; i < checkValues.length; i++){
					checkValues[i] = request.getParameter("GridView1_"+(i+1)+"_checkboxname");
//					System.out.println(i+1+":"+checkValues[i]);
					if("on".equals(checkValues[i])){
						int j = 0;
						 while(true){
							 if(i+1 != adminValues[j]){
								 j++;
							 }else{
								 System.out.println("------");
								 break;
							 }
							 if( m == j){
								//写入记录
									if(dao.addAdminValues(userID,i+1) == 0){
										msg = "设定失败";
										request.setAttribute("msg", msg);
										return mapping.findForward("updateauthRelation");
									}else{
										System.out.println(i+1+"---------------------项,写入记录");
									}
								 break;
							 }
						  }
					}
					else{
						int j = 0;
						 while(j <= m){
							 if(i+1 != adminValues[j]){
								 j++;
							 }else{
								//删除记录
									if(dao.delAdminValues(userID,i+1) == 0){
										msg = "设定失败";
										request.setAttribute("msg", msg);
										return mapping.findForward("updateauthRelationFail");
									}else{
										System.out.println(i+1+"---------------------项,删除记录");
									}
								 break;
							 }
						  }
					}
				}
			}	
			request.setAttribute("msg", "设定成功");
			return mapping.findForward("updateauthRelation");
		}
		
		if("addsx".equals(action)){  //设定上线
			
			String ss = request.getParameter("ss");
			String [] ids = ss.split(",");
			int sxGameID = 0;
			if(request.getParameter("sxGameID")!=null){
				sxGameID = Integer.parseInt(request.getParameter("sxGameID"));
			}
			System.out.println(">>>>>>>>>>>>>>>>>>ids:"+ids);
			
			if(dao.queryUserID(sxGameID)>0){
				for(int i=0;i<ids.length;i++){
					dao.addsx(ids[i],dao.queryUserID(sxGameID));
				}
				request.setAttribute("msg", "设定成功!");
			}
			else{
				request.setAttribute("msg", "所填的游戏ID用户不存在!");
			}
			
			return mapping.findForward("addsx");
		}
		
		
		if("giftScore".equals(action)){
			String ss = request.getParameter("ss");
			String [] ids = ss.split(",");
			long giftScore = 0;
			long giftScoreAgent = 0;
			int giftMinute = 0;
			int giftMinuteAgent = 0;
			if(request.getParameter("giftScore")!=null){
				giftScore = Long.parseLong(request.getParameter("giftScore"));
			}
			if(request.getParameter("giftScoreAgent")!=null){
				giftScoreAgent = Long.parseLong(request.getParameter("giftScoreAgent"));
			}
			if(request.getParameter("giftMinute")!=null){
				giftMinute = Integer.parseInt(request.getParameter("giftMinute"));
			}
			if(request.getParameter("giftMinuteAgent")!=null){
				giftMinuteAgent = Integer.parseInt(request.getParameter("giftMinuteAgent"));
			}
			for(int i=0;i<ids.length;i++){
				dao.giftScore(ids[i],giftScore,giftScoreAgent,giftMinute,giftMinuteAgent);
			}
			request.setAttribute("msg", "设定成功!");
			
			
			return mapping.findForward("giftScore");
		}
		
		if("ToExcel".equals(action)){
			String where = " RegisterMobile <>''";
			File file_dir = new File("E:\\card\\");
			if (!file_dir.exists()){
				file_dir.mkdir();
			}
			
			SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
			SimpleDateFormat f1=new SimpleDateFormat("(yyyy年MM月dd日HH_mm_ss)");
			Date d=Calendar.getInstance().getTime();
			
			String targetName="充值卡统计"+f1.format(d)+".xls";
			String filename=file_dir+"\\"+f1.format(d)+".xls";
			dao.excel(filename,where);
			dao.download(filename, response,targetName);			
			return null;
		}
		
		//推广管理
		if("spreaderSet".equals(action)){
			
			GameUserDTO dto = new GameUserDTO();
			dto = dao.getSpreaderSet();
			request.setAttribute("dto", dto);
			
			
			return mapping.findForward("spreaderSet");
		}
		
		if("spreaderSetUpdate".equals(action)){
			int RegisterGrantScore = 0 ;
			if(request.getParameter("RegisterGrantScore")!=null){
				RegisterGrantScore = Integer.parseInt(request.getParameter("RegisterGrantScore"));
			}
			int PlayTimeCount = 0 ;
			if(request.getParameter("PlayTimeCount")!=null){
				PlayTimeCount = Integer.parseInt(request.getParameter("PlayTimeCount"));
			}
			int PlayTimeGrantScore = 0 ;
			if(request.getParameter("PlayTimeGrantScore")!=null){
				PlayTimeGrantScore = Integer.parseInt(request.getParameter("PlayTimeGrantScore"));
			}
			double FillGrantRate = 0 ;
			if(request.getParameter("FillGrantRate")!=null){
				FillGrantRate = Double.parseDouble(request.getParameter("FillGrantRate"));
			}
			double BalanceRate = 0 ;
			if(request.getParameter("BalanceRate")!=null){
				BalanceRate = Double.parseDouble(request.getParameter("BalanceRate"));
			}
			int MinBalanceScore = 0 ;
			if(request.getParameter("MinBalanceScore")!=null){
				MinBalanceScore = Integer.parseInt(request.getParameter("MinBalanceScore"));
			}
			GameUserDTO dto = new GameUserDTO();
			dto.setRegisterGrantScore(RegisterGrantScore);
			dto.setPlayTimeCount(PlayTimeCount);
			dto.setPlayTimeGrantScore(PlayTimeGrantScore);
			dto.setFillGrantRate(FillGrantRate);
			dto.setBalanceRate(BalanceRate);
			dto.setMinBalanceScore(MinBalanceScore);
			dao.updateSpreader(dto);
			request.setAttribute("msg", "保存成功!");
			
			return mapping.findForward("spreaderSetUpdate");
		}
		
		
		if("SpreaderUserList".equals(action)){
			
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
			
			String termId = request.getParameter("selectOne");
			String termWord ="";
			String orderby = "collectDate";
			String where = "";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8").trim();
				System.out.println(termWord+"termId:"+termId);
			}
			if("accounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索用户的判断");
				where = " Accounts like '%"+termWord+"%'";
				list = dao1.getSpreaderInfo(curPage, pageSize,orderby,where);
			}
			
			if("".equals(termId)||"".equals(termWord)){
				System.out.println("termId=4");
				where = where ;
				list = dao1.getSpreaderInfo(curPage, pageSize,orderby,where);
			}
			
			if(list.size()!=0){
			request.setAttribute("userlist", list);
			PageDTO pdto = new PageDTO();
			if(curPage>dao1.getTotalPage())curPage=dao1.getTotalPage();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(dao1.getTotalPage());
			pdto.setTotalRecord(dao1.getRcordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("roleId", roleId);
			return mapping.findForward("SpreaderUserList");
			
				}
			}
		return super.execute(mapping, form, request, response);
	}
	
	

}

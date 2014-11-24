package com.doowal.hk798.gameUser;

import java.io.PrintWriter;
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
import com.doowal.hk798.login.AdminDTO;

public class GameAgentListAction extends Action {

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
		int type = 1;
		int levelID = 1;
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if(request.getParameter("type")!=null){
			type = Integer.parseInt(request.getParameter("type"));
		}
		if(request.getParameter("levelID")!=null){
			levelID = Integer.parseInt(request.getParameter("levelID"));
		}
		if("gameAgentList".equals(action)){
			
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
		String orderby="createTime";
		String where = "AccountType=1";
		if(levelID==1){  //公司
			where += " and levelID=1";
		}
		
		else{ //股东，总代理，代理
			where += " and levelID="+levelID;
		}
		if(request.getParameter("prevproxy")!=null){
			where +=  " and prevproxy="+request.getParameter("prevproxy");
		}
		System.out.println("where:"+where);
		int isFreeze = -1;
		if(request.getParameter("isFreeze")!=null){
			if(!request.getParameter("isFreeze").equals("-1")){
				isFreeze = Integer.parseInt(request.getParameter("isFreeze"));
				where += " and isFreeze="+isFreeze;
			}
		}
			
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
		}
		String termId = request.getParameter("selectOne");
		String termWord ="";
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8").trim();
			System.out.println(termWord+"termId:"+termId);
		}

		if("accounts".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=1");
			System.out.println("进入搜索用户的判断");
			where += " and proxyAccounts like '%"+termWord+"%'";
			list = dao.GetAgentByPage(curPage, pageSize,orderby,where);
		}
		
		if("nickName".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=2");
			where += " and nickName like '%"+termWord+"%'";;
			System.out.println("进入搜索昵称的判断");
			list = dao.GetAgentByPage(curPage, pageSize,orderby,where);
		}
		
		if("".equals(termId)||"".equals(termWord)){
			System.out.println("termId=4");
			list = dao.GetAgentByPage(curPage, pageSize,orderby,where);
		}
		System.out.println("--------------where:"+where);
		if(list.size()!=0){
		request.setAttribute("username", request.getSession().getAttribute("username"));
		request.setAttribute("userlist", list);
		request.setAttribute("levelID", levelID);
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
		String operateDetails = username+"进入查看代理列表";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		return mapping.findForward("gameAgentList");
		}
		else{	
			request.setAttribute("returnInfo", dao.returnInfo());
			return mapping.findForward("gameAgentList");
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
			GameUserDTO dto = new GameUserDTO();
			GameUserDTO dto1 = new GameUserDTO();
			dto = dao.getAgentById(userID);
			dto1 = dao.getAgentById(spreaderID);
			request.setAttribute("insureScore", dto1.getGold());
			
			request.setAttribute("spreaderID", spreaderID);
			request.setAttribute("dto", dto);
			return mapping.findForward("MemberPreCun");
			
		}
		
		if("MemberCun".equals(action)){
			int userID = 0;
			int spreaderID = 0;
			long insureGold = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			if(request.getParameter("insureGold")!=null){
				insureGold = Long.parseLong(request.getParameter("insureGold"));
			}
			if(request.getParameter("spreaderID")!=null){
				spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
			}
/*			if(spreaderID==3){
				spreaderID=0;
			}*/
			
			if(dao.insertInsureCunRecord(userID,spreaderID,insureGold,request.getRemoteAddr())){
				request.setAttribute("msg", "恭喜，存入操作成功!");
			}
			else{
				request.setAttribute("msg", "操作失败，请联系客服!");
			}
			
			return mapping.findForward("MemberCun");
		}
		
		if("MemberPreQu".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			GameUserDTO dto = new GameUserDTO();
			dto = dao.getAgentById(userID);
			request.setAttribute("dto", dto);
			return mapping.findForward("MemberPreQu");
			
		}
		
		if("MemberQu".equals(action)){
			int userID = 0;
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
/*			if(spreaderID==1){
				spreaderID=0;
			}*/
			if(dao.insertInsureQuRecord(spreaderID,userID,insureGold,gold,request.getRemoteAddr())){
				request.setAttribute("msg", "恭喜，取出操作成功!");
			}
			else{
				request.setAttribute("msg", "操作失败，请联系客服!");
			}
			return mapping.findForward("MemberQu");
		}
		
		if("zhFrozen".equals(action)){//账号/启用/停用/冻结
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
				if(dao.zhFrozen(userID,state)){
					request.setAttribute("msg", "恭喜，操作成功!");
				}
				else{
					request.setAttribute("msg", "操作失败，请联系客服!");
				}
			}
			if(userState==1){
				state = 0;
				if(dao.zhUnFrozen(userID,state)){
					request.setAttribute("msg", "恭喜，操作成功!");
				}
				else{
					request.setAttribute("msg", "操作失败，请联系客服!");
				}
			}
			if(userState==2){
				state = 0;
				if(dao.zhUnFrozen(userID,state)){
					request.setAttribute("msg", "恭喜，操作成功!");
				}
				else{
					request.setAttribute("msg", "操作失败，请联系客服!");
				}
			}
			if(userState==3){
				state = 2;
				if(dao.zhFrozen(userID,state)){
					request.setAttribute("msg", "恭喜，操作成功!");
				}
				else{
					request.setAttribute("msg", "操作失败，请联系客服!");
				}
			}
			
			return mapping.findForward("zhFrozen");
		}
		
		
		if("preAgentUpdate".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			String account = "";
			if(request.getParameter("account")!=null){
				account = request.getParameter("account");
			}
			GameUserDTO dto = dao.getAgentById(userID);
			GameUserDTO dto1 = dao.getAgentById(dto.getPrevProxy());
			GameUserDTO dto2 = dao.getById(account);
			request.setAttribute("user", dto);
			request.setAttribute("dto2", dto2);
			request.setAttribute("sxbrokerage", dto1.getBrokerage());
			request.setAttribute("sxwinner", dto1.getWinner());
			request.setAttribute("sxtaxRate", dto1.getTaxRate());
			return mapping.findForward("preAgentUpdate");
		}
		
		
		if("updateAgent".equals(action)){
			int userID = 0;
			if(request.getParameter("proxyID")!=null){
				userID = Integer.parseInt(request.getParameter("proxyID"));
			}
			String nickName = "";
			if(request.getParameter("nickName")!=null){
				nickName = request.getParameter("nickName");
			}
			
			String proxyAccounts = "";
			if(request.getParameter("proxyAccounts")!=null){
				proxyAccounts = request.getParameter("proxyAccounts");
			}
			
			String logonPass = "";
			String bankPass = "";
			GameUserDTO dto = new GameUserDTO();
			dto.setNickName(nickName);
			dto.setProxyAccounts(proxyAccounts);
			dto.setLogonPass("");
			dto.setBankPass("");
			if(request.getParameter("password")!=null&&request.getParameter("password")!=""){
				logonPass = request.getParameter("password").trim();
				dto.setLogonPass(EncryptionMD5.encryption_MD5(logonPass).toLowerCase());
			}
			if(request.getParameter("bankPass")!=null&&request.getParameter("bankPass")!=""){
				bankPass = request.getParameter("bankPass").trim();
				dto.setBankPass(EncryptionMD5.encryption_MD5(bankPass).toLowerCase());
			}
			
			System.out.println("登陆密码："+logonPass+"&钱庄密码："+bankPass);
			double winner = 0;
			if(request.getParameter("winner")!=null&&request.getParameter("winner")!=""){
				winner = Double.parseDouble(request.getParameter("winner"));
			}
			double brokerage = 0;
			if(request.getParameter("brokerage")!=null&&request.getParameter("brokerage")!=""){
				brokerage = Double.parseDouble(request.getParameter("brokerage"));
			}
			double taxRate = 0;
			if(request.getParameter("taxRate")!=null&&request.getParameter("taxRate")!=""){
				taxRate = Double.parseDouble(request.getParameter("taxRate"));
			}
			dto.setUserID(userID);
			dto.setBrokerage(brokerage);
			dto.setWinner(winner);
			dto.setTaxRate(taxRate);
			String msg = "";
					msg = dao.updateAgent(dto);
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
			
			String accounts = dao.selectAgentAccounts(userID);
			
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
			String where = " proxyID="+userID;
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
			/*int date = 0;
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
			}*/
			
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.FreezeAgent(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "冻结成功!");
			return mapping.findForward("freeze");
			
		}
		
		if("UnFreeze".equals(action)){  //解冻
			
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.UnFreezeAgent(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "解冻成功!");
			return mapping.findForward("UnFreeze");
			
		}
		
	if("preAdd".equals(action)){
			
			int userID = 0;
			int spreaderID = 0;
			int proxyID = 0;
			String accounts = "";
			if(request.getParameter("spreaderID")!=null){
				spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
			}
			if(request.getParameter("proxyID")!=null){
				proxyID = Integer.parseInt(request.getParameter("proxyID"));
			}
			if(request.getParameter("accounts")!=null){
				accounts = request.getParameter("accounts");
				accounts = new String(accounts.getBytes("ISO_8859_1"),"UTF-8");
			}
			GameUserDTO dto = new GameUserDTO();
			//userID = dao.selectUserID(spreaderID);
			AdminDTO dto1 = dao.getByProxyId(proxyID);
			request.setAttribute("sxwinner", dto1.getWinner());
			request.setAttribute("sxbrokerage", dto1.getBrokerage());
			request.setAttribute("sxtaxRate", dto1.getTaxRate());
			request.setAttribute("levelID", levelID);
			dto.setUserID(userID);
			dto.setAccounts(accounts);
			dto.setSpreaderID(String.valueOf(proxyID));
			//List<GoldXhDTO> list = dao.getLimit(spreaderID);
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
		String spreaderID = "0";
		String levelID1 = "1";
		if(request.getParameter("sxAccounts")!=null){
			sxAccounts = request.getParameter("sxAccounts");
		}
		if(request.getParameter("accounts")!=null){
			accounts = request.getParameter("accounts");
		}
		if(request.getParameter("password1")!=null){
			loginPWD = request.getParameter("password1");
		}
		if(request.getParameter("bankPass")!=null){
			bankPWD = request.getParameter("bankPass");
		}
		if(request.getParameter("regAccounts")!=null){
			regAccounts = request.getParameter("regAccounts");
		}
		if(request.getParameter("spreaderID")!=null){
			spreaderID = request.getParameter("spreaderID");
		}
		//GameUserDTO dto11 = dao.getById(sxAccounts);
		if(request.getParameter("levelID")!=null){
			levelID1 = request.getParameter("levelID");
		}
		String loginPass = EncryptionMD5.encryption_MD5(loginPWD);
		String bankPass = EncryptionMD5.encryption_MD5(bankPWD);
		String ip = request.getRemoteAddr();
		String telPhone = "";
		double winner = 0;
		double brokerage = 0;
		double taxRate = 0;
		int isFreeze = 0;
		if(request.getParameter("winner")!=null){
			winner = Double.parseDouble(request.getParameter("winner"));
		}
		if(request.getParameter("brokerage")!=null){
			brokerage = Double.parseDouble(request.getParameter("brokerage"));
		}
		if(request.getParameter("taxRate")!=null){
			taxRate = Double.parseDouble(request.getParameter("taxRate"));
		}
		if(request.getParameter("isFreeze")!=null){
			isFreeze = Integer.parseInt(request.getParameter("isFreeze"));
		}
		if(request.getParameter("telphone")!=null){
			telPhone = request.getParameter("telphone");
		}
		AdminDTO dto = new AdminDTO();
		dto.setProxyAccounts(accounts);
		dto.setNickName(regAccounts);
		dto.setPassword(loginPass);
		dto.setBankPass(bankPass);
		dto.setPrevProxy(Integer.parseInt(spreaderID));
		dto.setWinner(winner);
		dto.setBrokerage(brokerage);
		dto.setTaxRate(taxRate);
		dto.setIsFreeze(isFreeze);
		dto.setLevelID(Integer.parseInt(levelID1));
		if(dao.checkExit(accounts)!=0){
			request.setAttribute("msg", "账号已被注册，请重新输入!");
			return mapping.findForward("failure");
		}
		
		else{
			System.out.println("spreaderID:"+spreaderID);
			String[] params = new String[] { accounts, regAccounts ,loginPass ,"", spreaderID,String.valueOf(isFreeze),
					String.valueOf(brokerage),String.valueOf(taxRate),String.valueOf(winner),"0","1",telPhone,levelID1};
			String[] result = dao.regAgent(params);
			request.setAttribute("msg",result[1]);
			int userID = dao.queryUserID(accounts);
			dao.addMenuID(userID);
			System.out.println(">>>>>>>>>>result:"+result[1]);
				
			/* 写入日志记录 */
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = "增加代理：'"+accounts+"'用户";
			operateDto.setUserID(Integer.parseInt(spreaderID));
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addAgentLogs(operateDto);
			return mapping.findForward("addUser");
			
/*			String[] params = new String[] { accounts, regAccounts ,loginPass.toUpperCase() ,bankPass.toUpperCase() , "0" ,
					"0" ,spreaderID,String.valueOf(dto11.getGameID()), "" , "" , "" , ip,""};
			dao.regAccounts(params);--代理商的信息注册在Accounts表里*/
		}
	}
		
/*		if("preAdd".equals(action)){
			
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
			GameUserDTO dto = new GameUserDTO();
			//userID = dao.selectUserID(spreaderID);
			GameUserDTO dto1 = dao.getById(spreaderID);
			request.setAttribute("sxbjlZC", dto1.getBjlZC());
			request.setAttribute("sxbjlYJ", dto1.getBjlYJ());
			request.setAttribute("sxtax", dto1.getTax());
			request.setAttribute("levelID", dto1.getLevelID());
			dto.setUserID(userID);
			dto.setAccounts(accounts);
			dto.setSpreaderID(String.valueOf(spreaderID));
			//List<GoldXhDTO> list = dao.getLimit(spreaderID);
			request.setAttribute("dto", dto);
			request.setAttribute("accounts", accounts);
			//request.setAttribute("xhList", list);//限红筹码类型列表
			return mapping.findForward("preAdd");
		}*/
		
	/*	if("addUser".equals(action)){
			String accounts = "";
			String loginPWD = "";
			String bankPWD = "";
			String regAccounts = "";
			String userType = "0";
			String spreaderID = "0";
			if(request.getParameter("accounts")!=null){
				accounts = request.getParameter("accounts");
			}
			if(request.getParameter("password1")!=null){
				loginPWD = request.getParameter("password1");
			}
			if(request.getParameter("bankpass1")!=null){
				bankPWD = request.getParameter("bankpass1");
			}
			if(request.getParameter("regAccounts")!=null){
				regAccounts = request.getParameter("regAccounts");
			}
			if(request.getParameter("userType")!=null){
				userType = request.getParameter("userType");
			}
			if(request.getParameter("spreaderID")!=null){
				spreaderID = request.getParameter("spreaderID");
			}
			String loginPass = EncryptionMD5.encryption_MD5(loginPWD).toUpperCase();
			String bankpass = EncryptionMD5.encryption_MD5(bankPWD).toUpperCase();
			String ip = request.getRemoteAddr();
			double bjlZC = 0;
			double bjlYJ = 0;
			double tax = 0;
			int xxlevelID = 0;
			if(request.getParameter("bjlZC")!=null){
				bjlZC = Double.parseDouble(request.getParameter("bjlZC"));
			}
			if(request.getParameter("bjlYJ")!=null){
				bjlYJ = Double.parseDouble(request.getParameter("bjlYJ"));
			}
			if(request.getParameter("tax")!=null){
				tax = Double.parseDouble(request.getParameter("tax"));
			}
			if(request.getParameter("xxlevelID")!=null){
				xxlevelID = Integer.parseInt(request.getParameter("xxlevelID"));
			}
			
			if(dao.checkExit(accounts)!=0){
				request.setAttribute("msg", "账号已被注册，请重新输入!");
				
			}
			else{
				String[] params = new String[] { accounts, accounts ,loginPass ,bankpass , "1" ,
						"0" ,spreaderID, "" , "" , "" , ip,String.valueOf(xxlevelID),String.valueOf(bjlZC),String.valueOf(bjlYJ),String.valueOf(tax)};
			String[] result = dao.reg(params);
			if (!result.equals("") && result != null) {
				if (!result[0].equals("0")) {
					request.setAttribute("msg", result[1]);
					System.out.println("----------------------------------"+result[1]);
					return mapping.findForward("failure");
				} else {
					
					//----------------------------------------------添加筹码类型
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
		          //----------------------------------------------添加筹码类型
		            
		            int userID = dao.selectUserID(accounts);
					dao.updateAccount(userID);
					List<RuleDTO> listDTO = dao.getRuleList();
					for(int i = 0; i < listDTO.size(); i++){
						dao.addAdminValues(userID,i+1);
					}
					request.getSession().setAttribute("accounts", accounts);
					int gameID = dao.selectGameID(accounts);
					int userID = dao.selectUserID(accounts);
					List<AdminDTO> list = new ArrayList<AdminDTO>();
					list = dao.getAgentMenu();
					if(list.size()>0){
						for(int i=0;i<list.size();i++){
							dao.InsertAgentAuthority(userID,list.get(i).getId());
						}
					}
					else{
						request.setAttribute("msg", "获取菜单失败!");
					}
					request.setAttribute("msg","注册成功,您的GameID号为:[ " + gameID + " ]");
					
					return mapping.findForward("addUser");
					}
				}
			}
			return mapping.findForward("addUser");
		}*/
		
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
			/*int [] adminValues = dao.getAdmin(userID); // ordery by id;
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
			request.setAttribute("ruleList", listDTO);*/
			return mapping.findForward("preauthRelation");
		}
		
		else if("updateauthRelation".equals(action)){
			String msg = "";
			/*int userID = Integer.parseInt(request.getParameter("userID"));
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
			}	*/
			request.setAttribute("msg", "设定成功");
			return mapping.findForward("updateauthRelation");
		}
		return super.execute(mapping, form, request, response);
	}

}

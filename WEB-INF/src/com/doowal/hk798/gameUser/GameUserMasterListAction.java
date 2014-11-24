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

public class GameUserMasterListAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		DataSource ds = this.getDataSource(request, "QPGameUserDB");
		//DataSource ds1 = this.getDataSource(request, "QPTreasureDB");
		
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		GameUserDAO dao = new GameUserDAO(ds);
		
		if("gameMasterList".equals(action)){
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize = 30;

			if (request.getParameter("pageSize") != null) {
				try {
					pageSize = Integer.parseInt(request
							.getParameter("pageSize"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			String orderby = "GameID";
			if (request.getParameter("orderby") != null) {
				orderby = request.getParameter("orderby");
			}

			String termId = request.getParameter("selectOne");

			String termWord = "";

			if (request.getParameter("termOne") != null) {
				termWord = request.getParameter("termOne");
				termWord = new String(termWord.getBytes("ISO_8859_1"), "UTF-8");
				System.out.println(termWord);
			}

			String where = "MasterRight > 0";
			List<GameUserDTO> list = null;
			if("accounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索用户的判断");
				where = where + " and Accounts like '%"+termWord+"%'";
				list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
			}
			
			
			if("gameID".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=2");
				where = where + " and gameID ="+termWord+"";
				System.out.println("进入搜索游戏编号的判断");
				list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
			}
			
			if("tyAccounts".equals(termId)){
				System.out.println("termId=3");
				where = where + " and zhState =1";
				System.out.println("进入停用账号的判断");
				list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
			}
			
			if("".equals(termId)||"".equals(termWord)){
				list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
			}

			if (list != null) {
				request.setAttribute("userlist", list);
			} else {
				request.setAttribute("returnInfo", "没有符合条件的结果！");
			}
			PageDTO pdto = new PageDTO();
			if (curPage > dao.getTotalPage())
				curPage = dao.getTotalPage();
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
			String operateDetails = username+"进入查看用管理用户列表";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			return mapping.findForward("gameMasterList");
		}
		
		if("zhFrozen".equals(action)){//管理账号冻结
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
			
			if(dao.zhFrozen(userID,state)){
				request.setAttribute("msg", "恭喜，操作成功!");
			}
			else{
				request.setAttribute("msg", "操作失败，请联系客服!");
			}
			return mapping.findForward("zhFrozen");
		}
		
		if("szbs".equals(action)){
			int uid=0;
			if(request.getParameter("uid")!=null){
				try {
					uid = Integer.parseInt(request.getParameter("uid"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(uid>0){
				Boolean r=dao.UpdateMasterRight(uid,268435457);//268435456+1
				if(r){
					request.setAttribute("msg", "比赛帐号设置成功！");
				}
				else {
					request.setAttribute("msg", "比赛帐号设置失败！");
				}
			}else{
				request.setAttribute("msg", "ID为"+uid+"的用户不存在，无法设置为比赛帐号！");
				
			}
			return mapping.findForward("szbs");
		}
		if("qxbs".equals(action)){
				int uid=0;
				if(request.getParameter("uid")!=null){
					try {
						uid = Integer.parseInt(request.getParameter("uid"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(uid>0){
					Boolean r=dao.UpdateMasterRight(uid,0);//0
					if(r){
						request.setAttribute("msg", "比赛帐号取消成功！");
					}
					else {
						request.setAttribute("msg", "比赛帐号取消失败！");
					}
				}else{
					request.setAttribute("msg", "ID为"+uid+"的用户不存在，无法取消比赛帐号！");
					
				}	
				return mapping.findForward("qxbs");
		}
		
		if("readright".equals(action)){
			int uid=0;
			if(request.getParameter("uid")!=null){
				try {
					uid = Integer.parseInt(request.getParameter("uid"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(uid>0){
				GameMasterRightDTO dto=dao.GetMasterRightInfo(uid);
				if(dto!=null){
					request.setAttribute("master", dto);
				}else{
					request.setAttribute("msg", "用户信息不存在，无法设置权限！");
				}
			}else{
				request.setAttribute("msg", "ID为"+uid+"的用户不存在，无法设置权限！");
			}	
			return mapping.findForward("readright");
		}
		else if("saveright".equals(action)){
			int right=0;
			//String master_CAN_LIMIT_PLAY="0";
			if(request.getParameter("master_CAN_LIMIT_PLAY")!=null){
				//master_CAN_LIMIT_PLAY="1";
				right+=1;
			}
			//String master_CAN_LIMIT_LOOKON="0";
			if(request.getParameter("master_CAN_LIMIT_LOOKON")!=null){
				//master_CAN_LIMIT_LOOKON="1";
				right+=2;
			}
			//String master_CAN_CAN_LIMIT_WISPER="0";
			if(request.getParameter("master_CAN_CAN_LIMIT_WISPER")!=null){
				//master_CAN_CAN_LIMIT_WISPER="1";
				right+=4;
			}
			//String master_CAN_LIMIT_ROOM_CHAT="0";
			if(request.getParameter("master_CAN_LIMIT_ROOM_CHAT")!=null){
				//master_CAN_LIMIT_ROOM_CHAT="1";
				right+=8;
			}
			//String master_CAN_LIMIT_GAME_CHAT="0";
			if(request.getParameter("master_CAN_LIMIT_GAME_CHAT")!=null){
				//master_CAN_LIMIT_GAME_CHAT="1";
				right+=16;
			}
			//String master_CAN_CUT_USER="0";
			if(request.getParameter("master_CAN_CUT_USER")!=null){
				//master_CAN_CUT_USER="1";
				right+=32;
			}
			//String master_CAN_FORBID_ACCOUNTS="0";
			if(request.getParameter("master_CAN_FORBID_ACCOUNTS")!=null){
				//master_CAN_FORBID_ACCOUNTS="1";
				right+=64;
			}
			//String master_CAN_CONFINE_IP="0";
			if(request.getParameter("master_CAN_CONFINE_IP")!=null){
				//master_CAN_CONFINE_IP="1";
				right+=128;
			}
			//String master_CAN_SEE_USER_IP="0";
			if(request.getParameter("master_CAN_SEE_USER_IP")!=null){
				//master_CAN_SEE_USER_IP="1";
				right+=256;
			}
			//String master_CAN_SEND_WARNING="0";
			if(request.getParameter("master_CAN_SEND_WARNING")!=null){
				//master_CAN_SEND_WARNING="1";
				right+=512;
			}
			//String master_CAN_ISSUE_MESSAGE="0";
			if(request.getParameter("master_CAN_ISSUE_MESSAGE")!=null){
				//master_CAN_ISSUE_MESSAGE="1";
				right+=1024;
			}
			//String master_CAN_BIND_GAME="0";
			if(request.getParameter("master_CAN_BIND_GAME")!=null){
				//master_CAN_BIND_GAME="1";
				right+=2048;
			}
			//String master_CAN_BIND_GLOBAL="0";
			if(request.getParameter("master_CAN_BIND_GLOBAL")!=null){
				//master_CAN_BIND_GLOBAL="1";
				right+=4096;
			}
			//String master_CAN_SERVER_OPTION="0";
			if(request.getParameter("master_CAN_SERVER_OPTION")!=null){
				//master_CAN_SERVER_OPTION="1";
				right+=8192;
			}
			int uid=0;
			if(request.getParameter("userID")!=null){
				try {
					uid = Integer.parseInt(request.getParameter("userID"));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			String accounts="";
			if(request.getParameter("accounts")!=null){
				accounts=request.getParameter("accounts");
			}
			if(uid>0){
				GameMasterRightDTO dto=dao.SaveMasterRight(uid, right);
				if(dto!=null){
					request.setAttribute("msg", accounts+"游戏管理员权限设置成功！");
					request.setAttribute("master", dto);
				}else{
					request.setAttribute("msg", "权限设置失败,系统异常！");
				}
				
			}
			else{
				request.setAttribute("msg", "用户编号为"+uid+",设置权限失败！");
			}

			return mapping.findForward("saveright");
		}
		
		return mapping.findForward("index");
	}
}

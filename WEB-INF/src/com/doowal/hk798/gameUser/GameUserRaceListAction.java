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

public class GameUserRaceListAction extends Action {

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
		GameUserDAO dao = new GameUserDAO(ds);
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if("userRaceList".equals(action)){
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
			String orderby="GameID";
			if(request.getParameter("orderby")!=null){
				orderby=request.getParameter("orderby");
			}
			
			String termId = request.getParameter("selectOne");
			
			String termWord ="";
			
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
				termWord = new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord);
			}		
			
			String where = "userRight > 0";
			
			if("accounts".equals(termId)&&!"".equals(termWord)){
				where += " and Accounts like '%"+termWord+"%'";
			}
			if("gameID".equals(termId)&&!"".equals(termWord)){
				where += " and GameID like '%"+termWord+"%'";
			}
			
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
			if(list!=null){
			request.setAttribute("userlist", list);
			}
			else{
				request.setAttribute("returnInfo", "没有符合条件的结果！");
			}
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
			String operateDetails = username+"进入查看用比赛用户列表";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			return mapping.findForward("userRaceList");
		}
		
		if("savegroup".equals(action)){
			int uid=0;
			int groupid=0;
			if(request.getParameter("uid")!=null){
				try {
					uid = Integer.parseInt(request.getParameter("uid"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(request.getParameter("groupid")!=null){
				try {
					groupid = Integer.parseInt(request.getParameter("groupid"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(uid>0){
				Boolean r=dao.UpdateGroupID(uid,groupid);//0
				if(r){
					request.setAttribute("msg", "帐号["+uid+"]，加入组["+groupid+"]成功！");
				}
				else {
					request.setAttribute("msg", "帐号["+uid+"]，加入组["+groupid+"]失败！");
				}
			}else{
				request.setAttribute("msg", "ID为"+uid+"的用户不存在，保存分组！");
				
			}
			return mapping.findForward("savegroup");
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
		
		return mapping.findForward("index");
	}
}

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

public class GameUserConfineListAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		DataSource ds = this.getDataSource(request, "QPAccountsDB");
		
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		GameUserDAO dao = new GameUserDAO(ds);
		
		if("gameConfineList".equals(action)){
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			
			if (curPage < 1)
				curPage = 1;
			int pageSize=10;
			
			List<GameUserDTO> list = dao.GetConfineRecordByPage(curPage, pageSize);
			int totalPage = dao.getTotalPage();
			request.setAttribute("confineList", list);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
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
			String operateDetails = username+"进入查看保留用户名列表";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			return mapping.findForward("gameConfineList");
		}
		
		if("delConfine".equals(action)){
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			if(dao.deleteConfine(userID)){
				request.setAttribute("msg", "恭喜,删除成功!");
			}
			else{
				request.setAttribute("msg", "遗憾,删除失败!");
			}
			return mapping.findForward("delConfine");
		}
		
		if("addConfine".equals(action)){
			
			String accounts = request.getParameter("accounts");
			String password = "qq123123";
			String password1 = "qq123123@@!!";
			String loginPass = EncryptionMD5.encryption_MD5(password);
			String bankpass = EncryptionMD5.encryption_MD5(password1);
			String[] params = new String[] { accounts, password,password1,loginPass, bankpass,
					"0","0", "127.0.0.1", "", "", "", "","","0","","30"};
			
			int count = dao.checkExit(accounts);
			if(count!=0){
				request.setAttribute("msg", "用户名已存在!");
			}
			else{
				String[] result = dao.reg(params);
				if (!result.equals("") && result != null) {
					if (result[0].equals("0")) {
						request.setAttribute("msg", result[1]);
						System.out.println("----------------------------------"+result[1]);
					} else {
						request.setAttribute("msg", result[1]+ " 您的GameID号为:[ " + result[2] + " ]");
					}
				}
				int userID = dao.selectUserID(accounts);
				if(dao.addConfine(userID)){
					request.setAttribute("msg", "添加成功!");
				}
				else{
					request.setAttribute("msg", "添加失败!");
				}
			}
			return mapping.findForward("addConfine");
		}
		
		if("delete".equals(action)){
			System.out.println(">>>>>>>>>>>数据库："+request.getParameter("checkbox"));
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.deleteConfine(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("delete");
		}
		
		return mapping.findForward("index");
	}
}

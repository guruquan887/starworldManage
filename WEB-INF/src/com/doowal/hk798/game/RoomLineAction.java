package com.doowal.hk798.game;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;
import com.doowal.hk798.gameUser.GameUserDAO;
import com.doowal.hk798.gameUser.GameUserDTO;


public class RoomLineAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPAccountsDB");
		GameUserDAO dao = new GameUserDAO(ds);
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if("list".equals(action)){
			
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
			String where = "";
			String orderby = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameUserDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("accounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索用户的判断");
				where = " Accounts like '%"+termWord+"%'";
				list = dao.GetRecordByPage1(curPage, pageSize,where);
			}
			if("gameID".equals(termId)&&!"".equals(termWord)){
	
				System.out.println("termId=2");
				where = " gameID ="+termWord+"";
				System.out.println("进入搜索游戏编号的判断");
				list = dao.GetRecordByPage1(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordByPage1(curPage,pageSize,where);
			}
			if(list.size()!=0){
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
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
			}
			return mapping.findForward("list");
		}
		
		if("delAll".equals(action)){
			if(dao.Delete()){
				request.setAttribute("msg", "删除成功!");
			}
			else{
				request.setAttribute("msg", "删除失败，请重新尝试!");
			}
			return mapping.findForward("delAccounts");
			
		}
		
		if("delAccounts".equals(action)){
			int  userID =0;
			if(request.getParameter("userID")!=null){
				userID=Integer.parseInt(request.getParameter("userID"));
			}
			if(userID!=0){
				dao.DeleteUserLocker(userID);
				request.setAttribute("msg", "删除成功！");
			}
			else{
				request.setAttribute("msg", "删除失败，ID不存在！");
			}
			return mapping.findForward("delAccounts");
		}
		
		
		return null;
	}
}

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

public class GameSpecialListAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		DataSource ds = this.getDataSource(request, "QPGameUserDB");
		
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		GameUserDAO dao = new GameUserDAO(ds);
		String orderby="gameID";
		String where = " userID in (2,3,4)";
		if("gameSpecialList".equals(action)){
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
			
			String termId = request.getParameter("selectOne");
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
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
			
			if("".equals(termId)||"".equals(termWord)){
				System.out.println("termId=4");
				list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
			}
			
			int totalPage = dao.getTotalPage();
			request.setAttribute("specialList", list);
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
			return mapping.findForward("gameSpecialList");
		}
		
		return mapping.findForward("index");
	}
}

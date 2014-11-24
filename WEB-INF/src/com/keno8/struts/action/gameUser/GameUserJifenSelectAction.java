package com.keno8.struts.action.gameUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.GameUserDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.GameUserDTO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.PageDTO;


public class GameUserJifenSelectAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String forward = "failure";
		String termId = request.getParameter("selectOne");
		
		String termWord ="";
		
		if(request.getParameter("termOne")!=null){
			termWord=request.getParameter("termOne");
		    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			System.out.println(termWord);
		}
	
		DataSource ds = this.getDataSource(request, "JifenKeno8");
		GameUserDAO dao = new GameUserDAO(ds);
		List<GameUserDTO> dto = null;
		String where = "Accounts like '%"+termWord+"%'";
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
		String orderby="RegisterDate";
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
		}
		
		if(request.getParameter("pageSize")!=null){
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		if("accounts".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=1");
			System.out.println("进入搜索用户的判断");
			dto = dao.selectByName(curPage, pageSize, where,orderby);
		}
		
		if("nickName".equals(termId)&&!"".equals(termWord)){
			System.out.println("termId=2");
			String where1 = "nickName like '%"+termWord+"%'";
			System.out.println("进入搜索昵称的判断");
			dto = dao.selectByName(curPage, pageSize, where1,orderby);
		}
		
		if("".equals(termId)||"".equals(termWord)){
			dto = dao.GetRecordByPage(curPage, pageSize,orderby);
		}

		if(dto.size()!=0){
			request.setAttribute("userlist", dto);
			int totalPage = dao.getTotalPage();
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
			String operateDetails = username+"查询积分用户<font color='red'>"+termWord+"</font>的信息";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			forward = "gameuserlist";
		}
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
			forward = "gameuserlist";
		}
		return mapping.findForward(forward);
	}
}

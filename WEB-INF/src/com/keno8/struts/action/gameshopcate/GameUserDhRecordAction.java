package com.keno8.struts.action.gameshopcate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.UserDhRecordDAO;
import com.keno8.struts.dto.PageDTO;
import com.keno8.struts.dto.UserDhRecordDTO;

public class GameUserDhRecordAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		if("dhRecordList".equals(action)){   //用户兑奖的总记录
			DataSource ds = this.getDataSource(request, "Keno8");
			UserDhRecordDAO dao = new UserDhRecordDAO(ds);
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}

			if (curPage < 1)
				curPage = 1;
			int pageSize = 10;
			int state = 0;
			String orderby = "dhTime";
			try {
				if (request.getParameter("state") != null) {
					state = Integer.parseInt(request.getParameter("state"));
				}
				if(request.getParameter("orderby")!=null){
					orderby = request.getParameter("orderby");
				}
				if(request.getParameter("pageSize")!=null){
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(">>>>>>>>>>>>>>"+orderby);
			String where = " state=" + state;
			List<UserDhRecordDTO> list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
			int totalPage = dao.getTotalPage();
			request.setAttribute("userDhRecord", list);
			if (curPage > dao.getTotalPage())
				curPage = dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRecordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			
			return mapping.findForward("dhRecordList");
		}
		
		else if("userdhSelect".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			UserDhRecordDAO dao = new UserDhRecordDAO(ds);
			String termId = request.getParameter("selectOne");
			
			String termWord ="";
			termWord=request.getParameter("termOne");
			if(request.getParameter("termOne")!=null){
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord);
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
			int pageSize = 10;
			int state = 0;
			String orderby = "dhTime";
			try {
				if (request.getParameter("state") != null) {
					state = Integer.parseInt(request.getParameter("state"));
					System.out.println(">>>>>>>>>>>>>");
				}
				if(request.getParameter("orderby")!=null){
					orderby = request.getParameter("orderby");
				}
				if(request.getParameter("pageSize")!=null){
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("订单的状态为"+state);
			List<UserDhRecordDTO> dto = null;
			String where = "Account like '%"+termWord+"%'"+" and state = "+state;
			if("account".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				dto = dao.GetRecordByPage(curPage, pageSize,orderby,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				String where1 = " state = "+state;
				dto = dao.GetRecordByPage(curPage, pageSize,orderby,where1);
			}
			if(dto.size()!=0){
				request.setAttribute("userDhRecord", dto);
				int totalPage = dao.getTotalPage();
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRecordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				return mapping.findForward("userdhSelect");
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				return mapping.findForward("userdhSelect");
			}
		}
		
		else if("userdhDetails".equals(action)){
			
			DataSource ds = this.getDataSource(request, "Keno8");
			UserDhRecordDAO dao = new UserDhRecordDAO(ds);
			int userID = Integer.parseInt(request.getParameter("userID"));
			String express_ID = request.getParameter("express_ID");
			UserDhRecordDTO dto = dao.getDetails(userID,express_ID);
			request.setAttribute("adto", dto);
			
			return mapping.findForward("userdhDetails");
		}
		
		else if("sendMall".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			UserDhRecordDAO dao = new UserDhRecordDAO(ds);
			int userID = Integer.parseInt(request.getParameter("userID"));
			String express_ID = request.getParameter("express_ID");
			String express_IDNO = request.getParameter("express_IDNO");
			String express_Name = request.getParameter("express_Name");
			String express_Beizhu = "";
			if(request.getParameter("express_beizhu")!=null){
				express_Beizhu = request.getParameter("express_beizhu");
			}
			UserDhRecordDTO dto = new UserDhRecordDTO();
			dto.setUserID(userID);
			dto.setExpress_ID(express_ID);
			dto.setExpress_IDNO(express_IDNO);
			dto.setExpress_Name(express_Name);
			dto.setExpress_BeiZhu(express_Beizhu);
			dao.update(userID,express_ID,dto);
			request.setAttribute("msg", "发货成功!");
			return mapping.findForward("sendMall");
		}
		
		else if("deleteUserDhRecord".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			UserDhRecordDAO dao = new UserDhRecordDAO(ds);
			int userID = Integer.parseInt(request.getParameter("userID"));
			String express_ID = request.getParameter("express_ID");
			dao.delete(userID,express_ID);
			return mapping.findForward("deleteUserDhRecord");
		}
		return mapping.findForward(null);
	}
}
	


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

public class GameUserDrawAmountAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //取款表单
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		String action = "";
		DataSource ds = this.getDataSource(request, "QPTreasureDB");//DataSource.QPGameUserDB
		DrawAmountDAO dao = new DrawAmountDAO(ds);
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if("showDrawList".equals(action)){
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
			String orderby="applydate";
			if(request.getParameter("orderby")!=null){
				orderby=request.getParameter("orderby");
			}
			String where = "";
			int userID = 0;
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
				where = "userID="+userID;
			}
			String termId = request.getParameter("selectOne");
			System.out.println(termId);
			List<DrawAmountDTO> list = null;
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
				termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord);
			}
			if("accounts".equals(termId)&&!"".equals(termWord)){
				where = where +" Accounts like '%"+termWord+"%'";
				list = dao.GetRecordByPage(curPage, pageSize, where,orderby);
			}
			if("gameID".equals(termId)&&!"".equals(termWord)){
					where = where + " gameID="+termWord;
					System.out.println("termId=2");
					list = dao.GetRecordByPage(curPage,pageSize,where,orderby);
				}
			if("".equals(termWord)||"".equals(termId)){
				list = dao.GetRecordByPage(curPage, pageSize,where,orderby);
			}
			if(list.size()!=0){
			request.setAttribute("drawuserlist", list);
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
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入查看用户取款记录列表";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			return mapping.findForward("drawList");
		}
		
		if("dealDraw".equals(action)){
			int  userID =0;
			String express_ID = "";
			if(request.getParameter("userID")!=null){
				userID=Integer.parseInt(request.getParameter("userID"));
			}
			if(request.getParameter("express_ID")!=null){
				express_ID=request.getParameter("express_ID");
			}
			String msg = dao.updateDealDraw(userID,express_ID);
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"对订单号"+express_ID+"打款操作成功";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			request.setAttribute("msg", msg);
			return mapping.findForward("dealDraw");
			
		}
		
		if("dealCancel".equals(action)){
			
			String express_ID = "";
			int userID = 0;
			if(request.getParameter("express_ID")!=null){
				express_ID = request.getParameter("express_ID");
			}
			
			if(request.getParameter("userID")!=null){
				userID = Integer.parseInt(request.getParameter("userID"));
			}
			int cancelType = dao.queryCancelType(userID,express_ID);
			if(cancelType!=0){
				request.setAttribute("msg", "对不起，该用户已经取消，不能重复取消!");
				return mapping.findForward("dealCancel");
			}
			else{
				dao.dealCancel(express_ID,userID);
				/* 写入日志记录 */
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				OperateDTO operateDto = new OperateDTO();
				String operateDetails = username+"对订单号"+express_ID+"用户进行取消该订单";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				/*           */
				request.setAttribute("msg", "取消成功!");
			}
			
			return mapping.findForward("dealCancel");
		}
			return mapping.findForward("index");
		}
	

}

package com.keno8.struts.action.gameUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.keno8.struts.dao.DrawAmountDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.DrawAmountDTO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.PageDTO;

public class GameUserDrawAmountAction extends DispatchAction {

	public ActionForward showDrawList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //取款表单
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		DataSource ds = this.getDataSource(request, "Keno8");
		DrawAmountDAO dao = new DrawAmountDAO(ds);
		
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
		String orderby="drawdate";
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
		}
		int applytype = 3;
		if(request.getAttribute("applytype")!=null){
			applytype = (Integer)(request.getAttribute("applytype"));
		}
		String where = " applytype = "+applytype;
		List<DrawAmountDTO> list = dao.GetRecordByPage(curPage, pageSize,where,orderby);
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
		request.setAttribute("applytype", 3);
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
		return mapping.findForward("showlist");
	}
	
	public ActionForward gameuserdrawSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //取款记录查询
		
			String forward = "failure";
			String termId = request.getParameter("selectOne");
			System.out.println(termId);
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
				termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord);
			}
			DataSource ds = this.getDataSource(request, "Keno8");
			DrawAmountDAO dao = new DrawAmountDAO(ds);
			List<DrawAmountDTO> dto = null;
			
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
			int applytype = 0;
			String orderby="drawdate";
			String where = "";
			if(request.getParameter("orderby")!=null){
				orderby=request.getParameter("orderby");
			}
			
			if (Integer.parseInt(request.getParameter("applytype")) != 0) {
				applytype = Integer.parseInt(request.getParameter("applytype"));
				where = " applytype = " + applytype;
			}
			
			if(request.getParameter("pageSize")!=null){
				try {
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if("".equals(termWord)||"".equals(termId)){
					dto = dao.GetRecordByPage(curPage, pageSize,where,orderby);
			}
			
			if("accounts".equals(termId)&&!"".equals(termWord)){
				if(Integer.parseInt(request.getParameter("applytype")) != 0){
					where = where +" and Accounts like '%"+termWord+"%'";
				}
				else{
					where = where +"Accounts like '%"+termWord+"%'";
				}
					dto = dao.selectByName(curPage, pageSize, where,orderby);
			}
			if("gameID".equals(termId)&&!"".equals(termWord)){
				String where1 = "";
				if(Integer.parseInt(request.getParameter("applytype")) != 0){
					where1 = where + " and nickName like '%"+termWord+"%'";
				}
				else{
					where1 = where + "nickName like '%"+termWord+"%'";
				}
					System.out.println("termId=2");
					dto = dao.selectByName(curPage,pageSize,where1,orderby);
				}
			
			if(dto.size()!=0){
				request.setAttribute("drawuserlist", dto);
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
				String operateDetails = username+"查询<font color='red'>"+termWord+"</font>取款记录";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				
				/*           */
				forward = "gameuserdraw";
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				forward = "gameuserdraw";
			}
		
		return mapping.findForward(forward);
	}
	
	public ActionForward deleteDrawRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //删除取款记录
		
		DataSource ds = this.getDataSource(request, "Keno8");
		int  userID =0;
		String express_ID = "";
		if(request.getParameter("userID")!=null){
			userID=Integer.parseInt(request.getParameter("userID"));
		}
		if(request.getParameter("express_ID")!=null){
			express_ID=request.getParameter("express_ID");
		}
		DrawAmountDAO dao = new DrawAmountDAO(ds);
		if(userID!=0){
			dao.DeleteDrawRecord(userID,express_ID);
			request.setAttribute("msg", "删除成功！");
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"删除取款订单号为<font color='red'>"+express_ID+"</font>";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
		}
		else{
			request.setAttribute("msg", "删除失败，ID不存在！");
		}
		return mapping.findForward("deleteSuccess");
	}
	
	//取款审核状态
	public ActionForward dealSH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //提交到审核状态
		DataSource ds = this.getDataSource(request, "Keno8");
		String express_ID = "";
		if(request.getParameter("express_ID")!=null){
			express_ID=request.getParameter("express_ID");
		}
		DrawAmountDAO dao = new DrawAmountDAO(ds);
		dao.dealSH(express_ID);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"审核订单号为<font color='red'>"+express_ID+"</font>";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		request.setAttribute("applytype", 2);
		/*           */
		request.setAttribute("msg", "审核成功!");
		return mapping.findForward("dealSH");
	}
	
	public ActionForward dealDraw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //预打款操作
		
		DataSource ds = this.getDataSource(request, "Keno8");
		int  userID =0;
		String express_ID = "";
		if(request.getParameter("userID")!=null){
			userID=Integer.parseInt(request.getParameter("userID"));
		}
		if(request.getParameter("express_ID")!=null){
			express_ID=request.getParameter("express_ID");
		}
		
		DrawAmountDAO dao = new DrawAmountDAO(ds);
		DrawAmountDTO dto = dao.getDealDraw(userID,express_ID);
		request.setAttribute("adto", dto);
		request.setAttribute("applytype", 1);
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入订单号"+express_ID+"预打款操作页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		return mapping.findForward("dealDraw");
	}
	
	public ActionForward dealDrawTo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //执行打款操作
		
		DataSource ds = this.getDataSource(request, "Keno8");
		int  userID =0;
		String express_ID = "";
		String drawBeizhu = "";
		if(request.getParameter("userID")!=null){
			userID=Integer.parseInt(request.getParameter("userID"));
		}
		if(request.getParameter("express_ID")!=null){
			express_ID=request.getParameter("express_ID");
		}
		if(request.getParameter("drawBeizhu")!=null){
			drawBeizhu=request.getParameter("drawBeizhu");
		}
		DrawAmountDAO dao = new DrawAmountDAO(ds);
		dao.updateDealDraw(drawBeizhu,userID,express_ID);
		request.setAttribute("applytype", 1);
		
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
		request.setAttribute("msg", "打款成功!");
		return mapping.findForward("dealDrawTo");
	}
	
	public ActionForward dealTuihuan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //执行审核不成功-退款操作
		
		DataSource ds = this.getDataSource(request, "Keno8");
		int  userID =0;
		String express_ID = "";
		String drawBeizhu = "";
		if(request.getParameter("userID")!=null){
			userID=Integer.parseInt(request.getParameter("userID"));
		}
		if(request.getParameter("express_ID")!=null){
			express_ID=request.getParameter("express_ID");
		}
		if(request.getParameter("drawBeizhu")!=null){
			drawBeizhu=request.getParameter("drawBeizhu");
		}
		
		DrawAmountDAO dao = new DrawAmountDAO(ds);
		dao.updateTuihuanDraw(drawBeizhu,userID,express_ID);
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"对订单号"+express_ID+"审核不成功，退款成功";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*           */
		request.setAttribute("msg", "退款成功!");
		return mapping.findForward("dealDrawTo");
	}
	
	public ActionForward dealCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {   //后台用户取消取款
		
		DataSource ds = this.getDataSource(request, "Keno8");

		String express_ID = "";
		int userID = 0;
		if(request.getParameter("express_ID")!=null){
			express_ID = request.getParameter("express_ID");
		}
		
		if(request.getParameter("userID")!=null){
			userID = Integer.parseInt(request.getParameter("userID"));
		}
		DrawAmountDAO dao = new DrawAmountDAO(ds);
		int cancelType = dao.queryCancelType(userID,express_ID);
		if(cancelType==1){
			request.setAttribute("msg", "对不起，该用户已经取消，不能重复取消!");
			return mapping.findForward("cancelFailure");
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

}

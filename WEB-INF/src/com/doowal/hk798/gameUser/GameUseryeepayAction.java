package com.doowal.hk798.gameUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class GameUseryeepayAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		DataSource ds = this.getDataSource(request, "QPTreasureDB");//DataSource.QPGameUserDB
		GameYeepayDAO dao = new GameYeepayDAO(ds);
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
		if("showYeepayList".equals(action)){
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
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			int state = 2;
			try{
				if(request.getParameter("state")!=null)
					state = Integer.parseInt(request.getParameter("state"));
			}catch (Exception e) {
				e.printStackTrace();
			}
			String where = "";
			String termId = request.getParameter("selectOne");
			System.out.println(termId);
			if(state!=2){
				where = "state = "+state;
			}
			List<GameYeepayDTO> list = null;
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
				termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord);
			}
			if("accounts".equals(termId)&&!"".equals(termWord)){
				where = where +" Accounts like '%"+termWord+"%'";
				list = dao.GetRecordByPage(curPage, pageSize, where);
			}
			if("gameID".equals(termId)&&!"".equals(termWord)){
					where = where + " gameID="+termWord;
					System.out.println("termId=2");
					list = dao.GetRecordByPage(curPage,pageSize,where);
				}
			if("".equals(termWord)||"".equals(termId)){
				list = dao.GetRecordByPage(curPage, pageSize,where);
			}
			request.setAttribute("state", state);
			request.setAttribute("useryeepay", list);
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
			String operateDetails = username+"进入查看用户存款记录";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*           */
			
			return mapping.findForward("showYeepayList");
		}
		
		if("preAddYeepay".equals(action)){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date today = Calendar.getInstance().getTime();
			String currenttime = sdf.format(today);
			String dh1="A";
			String dh2=currenttime.substring(0,8);
			String dh3=currenttime.substring(12,currenttime.length());
			String dh = dh1+dh2+dh3;
			request.setAttribute("dh", dh);
			/* 写入日志记录 */
			/*String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入补填订单页面，订单号为：<font color='red'>"+dh+"</font>";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);*/
			return mapping.findForward("preAddYeepay");
		}
		
		if("addYeaapy".equals(action)){
			
			String dh = request.getParameter("dh");
			String accounts = request.getParameter("accounts");
			long r3_Amt = Long.parseLong(request.getParameter("r3_Amt"));
			if(dao.checkExist(accounts)){
				int userid = dao.queryUserID(accounts);
				int r = dao.add(dh,userid,r3_Amt);
				if(r==1){
					request.setAttribute("msg", "恭喜，订单增加成功!");
					
					/* 写入日志记录 */
					String username = (String)request.getSession().getAttribute("username");
					String ip = (String)request.getSession().getAttribute("ip");
					OperateLogsDAO operateDao = new OperateLogsDAO(ds);
					OperateDTO operateDto = new OperateDTO();
					String operateDetails = username+"增加订单号为<font color='red'>"+dh+"</font>用户<font color='red'>"+accounts+"</font>增加银子<font color='red'>"+r3_Amt+"</font>";
					operateDto.setOperateName(username);
					operateDto.setOperateDetails(operateDetails);
					operateDto.setOperateIP(ip);
					operateDao.addLogs(operateDto);
					/*           */
				}
				else{
					request.setAttribute("msg", "对不起，订单增加失败!");
				}
			}
			else{
				request.setAttribute("msg", "用户不存在，请查证!");
				return mapping.findForward("failure");
			}
			
			return mapping.findForward("addYeaapy");
		}
		
		if("delYeepay".equals(action)){
			String dh = request.getParameter("dh");
			if(dao.deleteyeepay(dh)){
				request.setAttribute("msg", "恭喜,删除记录成功!");
			}
			else{
				request.setAttribute("msg", "操作失败，请联系管理员!");
			}
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"删除订单号为<font color='red'>"+dh+"</font>的充值记录";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			return mapping.findForward("delYeepay");
		}
		
		if("updateYeepay".equals(action)){
			
			String dh = request.getParameter("dh");
			long r3_Amt = Long.parseLong(request.getParameter("r3_Amt"));
			if(dao.updateyeepay(dh,r3_Amt)){
				request.setAttribute("msg", "手动充值成功!");
			}
			else{
				request.setAttribute("msg", "操作失败，请联系管理员!");
			}
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"对订单号为<font color='red'>"+dh+"</font>金额为<font color='red'>"+r3_Amt+"</font>进行了手动充值";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			return mapping.findForward("updateYeepay");
		}
		return mapping.findForward("index");
	}
	
	

}

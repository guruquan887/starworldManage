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

public class GameUserXZListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		DataSource ds = this.getDataSource(request, "QPAccountsDB");//DataSource.QPGameUserDB
		GameXZUserDAO dao = new GameXZUserDAO(ds);
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if("gameUserXZ".equals(action)){
			List<GameXZUserDTO> list = null;
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
			String orderby="CollectDate";
			String where = "";
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8").trim();
			    where = where + " and Accounts like '%"+termWord+"%'";
			}
			
			if("".equals(termWord)){
				System.out.println("termId=4");
				list = dao.GetConfineRecordByPage(curPage, pageSize,orderby,where);
			}
			
			if(list.size()!=0){
				request.setAttribute("username", request.getSession().getAttribute("username"));
				request.setAttribute("confineList", list);
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
				String operateDetails = username+"进入查看限制用户列表";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
			}
			return mapping.findForward("gameUserXZ");
		}
		
		else if("addgameUserXZ".equals(action)){
			String in_string = "";
			if(request.getParameter("in_string")!=null){
				in_string = request.getParameter("in_string");
			}
			String in_enjoinOverDate = "";
			if(request.getParameter("in_enjoinOverDate")!=null){
				in_enjoinOverDate = request.getParameter("in_enjoinOverDate");
			}
			int count = dao.queryString(in_string);
			if(count!=0){
				request.setAttribute("msg", "用户名已经存在！");
				return mapping.findForward("preaddgameUserXZ");
			}
			else{
				dao.addgameUserXZ(in_string,in_enjoinOverDate);
			}
			return mapping.findForward("addgameUserXZ");
		}
		
		else if("delAll".equals(action)){
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.delAll(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("delAll");
		}
		
		if("confineAddress".equals(action)){
			List<GameXZUserDTO> list = null;
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
			String orderby="CollectDate";
			String where = "";
			String termWord ="";
			if(request.getParameter("termOne")!=null&&request.getParameter("termOne")!=""){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8").trim();
			    where = where + " AddrString = '"+termWord+"'";
			}
			
			list = dao.GetAddressRecordByPage(curPage, pageSize,orderby,where);
			
			if(list.size()!=0){
				request.setAttribute("username", request.getSession().getAttribute("username"));
				request.setAttribute("confineList", list);
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
				String operateDetails = username+"进入查看限制地址列表";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
			}
			return mapping.findForward("confineAddress");
		}
		
		else if("addconfineAddress".equals(action)){
			String addrString = "";
			if(request.getParameter("addrString")!=null){
				addrString = request.getParameter("addrString");
			}
			String in_enjoinOverDate = "";
			if(request.getParameter("in_enjoinOverDate")!=null){
				in_enjoinOverDate = request.getParameter("in_enjoinOverDate");
			}
			int in_EnjoinLogon = 0;
			if(request.getParameter("in_EnjoinLogon")!=null){
				in_EnjoinLogon = Integer.parseInt(request.getParameter("in_EnjoinLogon"));
			}
			int in_EnjoinRegister = 0;
			if(request.getParameter("in_EnjoinRegister")!=null){
				in_EnjoinRegister = Integer.parseInt(request.getParameter("in_EnjoinRegister"));
			}
			String in_CollectNote = "";
			if(request.getParameter("in_CollectNote")!=null){
				in_CollectNote = request.getParameter("in_CollectNote");
			}
			int count = dao.queryAddress(addrString);
			if(count!=0){
				request.setAttribute("msg", "地址已经存在！");
				return mapping.findForward("preaddconfineAddress");
			}
			else{
				dao.addconfineAddress(addrString,in_EnjoinLogon,in_EnjoinRegister,in_enjoinOverDate,in_CollectNote);
			}
			return mapping.findForward("addconfineAddress");
		}
		
		else if("delAllAddress".equals(action)){
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.delAllAddress(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("delAllAddress");
		}
		
		if("confineMachine".equals(action)){
			List<GameXZUserDTO> list = null;
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
			String orderby="CollectDate";
			String where = "";
			String termWord ="";
			if(request.getParameter("termOne")!=null&&request.getParameter("termOne")!=""){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8").trim();
			    where = where + " MachineSerial = '"+termWord+"'";
			}
			
			list = dao.GetMachineRecordByPage(curPage, pageSize,orderby,where);
			
			if(list.size()!=0){
				request.setAttribute("username", request.getSession().getAttribute("username"));
				request.setAttribute("confineList", list);
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
				String operateDetails = username+"进入查看限制地址列表";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
			}
			return mapping.findForward("confineMachine");
		}
		
		else if("addconfineMachine".equals(action)){
			String addrString = "";
			if(request.getParameter("addrString")!=null){
				addrString = request.getParameter("addrString");
			}
			String in_enjoinOverDate = "";
			if(request.getParameter("in_enjoinOverDate")!=null){
				in_enjoinOverDate = request.getParameter("in_enjoinOverDate");
			}
			int in_EnjoinLogon = 0;
			if(request.getParameter("in_EnjoinLogon")!=null){
				in_EnjoinLogon = Integer.parseInt(request.getParameter("in_EnjoinLogon"));
			}
			int in_EnjoinRegister = 0;
			if(request.getParameter("in_EnjoinRegister")!=null){
				in_EnjoinRegister = Integer.parseInt(request.getParameter("in_EnjoinRegister"));
			}
			String in_CollectNote = "";
			if(request.getParameter("in_CollectNote")!=null){
				in_CollectNote = request.getParameter("in_CollectNote");
			}
			int count = dao.queryMachine(addrString);
			if(count!=0){
				request.setAttribute("msg", "机器已经存在！");
				return mapping.findForward("preaddconfineMachine");
			}
			else{
				dao.addconfineMachine(addrString,in_EnjoinLogon,in_EnjoinRegister,in_enjoinOverDate,in_CollectNote);
			}
			return mapping.findForward("addconfineMachine");
		}
		
		else if("delAllMachine".equals(action)){
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.delAllMachine(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("delAllMachine");
		}
		
		return mapping.findForward("");
	}
	
}

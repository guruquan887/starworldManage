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


public class GameSystemAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPPlatformDB");
		DataSource ds1 = this.getDataSource(request, "QPTreasureDB");
		GameSystemDAO dao = new GameSystemDAO(ds);
		GameSystemDAO dao1 = new GameSystemDAO(ds1);
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if("machine".equals(action)){
			
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
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("machine".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索机器");
				where = " Information like '%"+termWord+"%'";
				list = dao.GetRecordByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("machinelist", list);
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
			return mapping.findForward("machine");
		}
		
		if("addMachine".equals(action)){
			String info = "";
			if(request.getParameter("Information")!=null){
				info = request.getParameter("Information");
			}
			String DBAddr = "";
			if(request.getParameter("DBAddr")!=null){
				DBAddr = request.getParameter("DBAddr");
			}
			int DBPort = 0;
			if(request.getParameter("DBPort")!=null){
				DBPort = Integer.parseInt(request.getParameter("DBPort"));
			}
			String DBUser = "";
			if(request.getParameter("DBUser")!=null){
				DBUser = request.getParameter("DBUser");
			}
			String DBPassword = "";
			if(request.getParameter("DBPassword")!=null){
				DBPassword = request.getParameter("DBPassword");
			}
			String MachineID = "";
			if(request.getParameter("MachineID")!=null){
				MachineID = request.getParameter("MachineID");
			}
			GameSystemDTO dto = new GameSystemDTO();
			dto.setInformation(info);
			dto.setDbAddr(DBAddr);
			dto.setDbPassword(XOR.encode(DBPassword,"sdf"));
			dto.setDbPort(DBPort);
			dto.setDbUser(XOR.encode(DBUser, "sdf"));
			dto.setMachineID(MachineID);
			dao.add(dto);
			request.setAttribute("msg", "增加成功!");
			return mapping.findForward("addMachine");
		}
		
		if("delAll".equals(action)){
			
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.delAll(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("delAll");
			
		}
		
		if("gameGameItem".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=20;
			String where = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("gameName".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索模块名称");
				where = " gameName like '%"+termWord+"%'";
				list = dao.GetRecordGameGameItemByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordGameGameItemByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("gameGamelist", list);
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
			return mapping.findForward("gameGameItem");
		}
		
		if("preUpdategameGameItem".equals(action)){
			
			int param = 0;
			if(request.getParameter("param")!=null){
				param = Integer.parseInt(request.getParameter("param"));
			}
			GameSystemDTO dto = dao.GetGameItemById(param);
			request.setAttribute("dto", dto);
			return mapping.findForward("preUpdategameGameItem");
		}
		
		
		if("gameTypeItem".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=20;
			String where = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("gameName".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索模块名称");
				where = " gameName like '%"+termWord+"%'";
				list = dao.GetRecordGameTypeItemByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordGameTypeItemByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("gameTypelist", list);
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
			return mapping.findForward("gameTypeItem");
		}
		
		if("gameKindItem".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=20;
			String where = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("gameName".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索模块名称");
				where = " gameName like '%"+termWord+"%'";
				list = dao.GetRecordGameKindItemByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordGameKindItemByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("gameKindlist", list);
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
			return mapping.findForward("gameKindItem");
		}
		
		if("gameNodeItem".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=20;
			String where = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("gameName".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索模块名称");
				where = " gameName like '%"+termWord+"%'";
				list = dao.GetRecordGameNodeItemByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordGameNodeItemByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("gameNodelist", list);
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
			return mapping.findForward("gameNodeItem");
		}
		
		if("gamePageItem".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=20;
			String where = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("gameName".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索模块名称");
				where = " gameName like '%"+termWord+"%'";
				list = dao.GetRecordGamePageItemByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordGamePageItemByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("gamePagelist", list);
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
			return mapping.findForward("gamePageItem");
		}
		
		
		if("gameProperty".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=20;
			String where = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("gameName".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索模块名称");
				where = " gameName like '%"+termWord+"%'";
				list = dao1.GetRecordgamePropertyByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao1.GetRecordgamePropertyByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("gamePropertylist", list);
				PageDTO pdto = new PageDTO();
				if(curPage>dao1.getTotalPage())curPage=dao1.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(dao1.getTotalPage());
				pdto.setTotalRecord(dao1.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
			}
			return mapping.findForward("gameProperty");
		}
		
		
		if("SystemMessage".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=20;
			String where = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("gameName".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索模块名称");
				where = " gameName like '%"+termWord+"%'";
				list = dao.GetRecordSystemMessageByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordSystemMessageByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("SystemMessagelist", list);
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
			return mapping.findForward("SystemMessage");
		}
		
		if("GameRoomInfo".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=20;
			String where = "";
			String termId = request.getParameter("selectOne");
			String termWord ="";
			List<GameSystemDTO> list = null;
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if("serverName".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索房间名称");
				where = " serverName like '%"+termWord+"%'";
				list = dao.GetRecordGameRoomByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				
			    list = dao.GetRecordGameRoomByPage(curPage,pageSize,where);
			}
			if(list.size()!=0){
				request.setAttribute("GameRoomInfolist", list);
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
			return mapping.findForward("GameRoomInfo");
		}
		
		
		
		if("globalPlayPresent".equals(action)){
			
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
			List<GameSystemDTO> list = null;
				
			list = dao.GetRecordGlobalPlayByPage(curPage,pageSize,where);
			if(list.size()!=0){
				request.setAttribute("globalPlayPresentlist", list);
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
			return mapping.findForward("globalPlayPresent");
		}
		
		return null;
	}
}

package com.manage.struts.videoFileNameResult;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.manage.struts.video.PageDTO;
import com.manage.struts.video.RoomDTO;
import com.manage.struts.video.SBVideoDAO;
import com.manage.struts.video.SBVideoDTO;
import com.manage.struts.video.SBVideoPlayListDAO;
import com.manage.struts.video.VideoPlayListDAO;

public class SBGameResultManageAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "gameResultManageList";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		
		int pageIndex = 1;
		if (request.getParameter("pageIndex") != null) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
				if (pageIndex < 1)
					pageIndex = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int curPage = 1;
		if (curPage < 1)
			curPage = 1;
		int pageSize = 200;
		if (request.getParameter("pageSize") != null) {
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
				if (pageSize < 1)
					pageSize = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//=================结果控制器=====================
		if("gameResultManageList".equals(action)){
		if (request.getParameter("pageIndex") != null) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
				if (pageIndex < 1)pageIndex = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (curPage < 1)
			curPage = 1;
		if (request.getParameter("pageSize") != null) {
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
				if (pageSize < 1)pageSize = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String where = "";
		int roomID = 5;
		String openCount = "";
		where = "roomID="+roomID;
		if(request.getParameter("roomId")!=null&&request.getParameter("roomId")!=""){
			roomID = Integer.parseInt(request.getParameter("roomId"));
			where = " roomID="+roomID;
		}
		if(request.getParameter("openCount")!=null&&request.getParameter("openCount")!=""){
			openCount = request.getParameter("openCount");
			where = where + " and openCount='"+openCount+"'";
		}

		String orderBy = "xh";
		DataSource ds=this.getDataSource(request, "SunCityManage");
		SBGameResultManageDAO dao = new SBGameResultManageDAO(ds);
		List<GameResultManageDTO> list = dao.ListByPage(where, pageIndex, pageSize, orderBy);
		List<RoomDTO> roomList = dao.getRoom();
		request.setAttribute("videoList", list);
		request.setAttribute("roomId", roomID);
		request.setAttribute("roomList", roomList);
		int totalPage = dao.getTotalPage();
		if (pageIndex > dao.getTotalPage())
			pageIndex = dao.getTotalPage();
		PageDTO pdto = new PageDTO();
		pdto.setCurPage(pageIndex);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("gameResultManageList");
		
		}
		
		else if ("preSetOrder".equals(action)) {// 进入替播页面
			DataSource ds = this.getDataSource(request, "SunCityManage");
			VideoPlayListDAO dao = new VideoPlayListDAO(ds);
			String hgName = "";
			int roomID = 1;
			if (request.getParameter("hgName") != null) {
				hgName = request.getParameter("hgName").trim();
				hgName = new String(hgName.getBytes("ISO_8859_1"),"UTF-8");
			}
			int beforeXh = 0;
			if (request.getParameter("beforeXh") != null) {
				beforeXh = Integer.parseInt(request.getParameter("beforeXh"));
			}
			if(request.getSession().getAttribute("beforeXh")!=null){
				beforeXh = (Integer) (request.getSession().getAttribute("beforeXh"));
			}
			if(request.getParameter("roomID")!=null&&request.getParameter("roomID")!=""){
				roomID = Integer.parseInt(request.getParameter("roomID"));
				
			}
			request.setAttribute("roomID", roomID);
			request.setAttribute("beforeXh", beforeXh);
			String resultOfWinLost = "";
			if(request.getParameter("select_resultOfWinLost") != null){
				resultOfWinLost = request.getParameter("select_resultOfWinLost");
				System.out.println(resultOfWinLost+"<<<输赢结果resultWinLost");
			}
			String where = " hgName ='"+hgName+"'";
			if("".equals(hgName)){
				where = "";
			    if(request.getParameter("select_resultOfWinLost") != null ){
			    	if("DAN".equals(resultOfWinLost)){
			    		where += " result%2=0";
			    	}
			    	else if("SHUANG".equals(resultOfWinLost)){
			    		where += "result%2<>0";
			    	}
			    	else if("DA".equals(resultOfWinLost)){
			    		where += "result>10";
			    	}
			    	else if("XIAO".equals(resultOfWinLost)){
			    		where += "result<=10";
			    	}
			    }
			}else{
				if(request.getParameter("select_resultOfWinLost") != null ){
			    	if("DAN".equals(resultOfWinLost)){
			    		where += " and result%2<>=0";
			    	}
			    	else if("SHUANG".equals(resultOfWinLost)){
			    		where += " and result%2=0";
			    	}
			    	else if("DA".equals(resultOfWinLost)){
			    		where += " and result>10";
			    	}
			    	else if("XIAO".equals(resultOfWinLost)){
			    		where += " and result<=10";
			    	}
				}
			}
			System.out.println(where+"<<<<where条件");
			SBVideoDAO dao1 = new SBVideoDAO(ds);
			List<SBVideoDTO> list =dao1.ListByPage(where, pageIndex, pageSize);
			
			if(list.size() > 0){
				request.setAttribute("videoList", list);
				int totalPage = dao1.getTotalPage();
				if(pageIndex>dao1.getTotalPage())pageIndex=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(pageIndex);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao1.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", pageIndex);
				request.setAttribute("pageSize", pageSize);
				
//				request.setAttribute("beforeXh", beforeXh); //插入前 序号 [排序位置]
				return new ActionForward("/bjl/sb_video_setOrder.jsp");
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				return new ActionForward("/bjl/sb_video_setOrder.jsp");
			}
		 } 
		
		else if("setOrder".equals(action)){ //确定替播
			DataSource ds = this.getDataSource(request, "SunCityManage");
			SBVideoPlayListDAO dao = new SBVideoPlayListDAO(ds);
		    int beforeXh = 0;
		    int roomID = 0;
		    if (request.getParameter("roomID") != null) {
		    	roomID = Integer.parseInt(request.getParameter("roomID"));
			}
			if (request.getParameter("beforeXh") != null) {
				beforeXh = Integer.parseInt(request.getParameter("beforeXh"));
			}
			System.out.println("要被更新的播放列表ID为："+beforeXh);
			int videoID = 0;
			if(request.getParameter("radio") != null){
				videoID = Integer.parseInt(request.getParameter("radio"));
				System.out.println(videoID+"<<<替播视频的videoID");
			}
			if(dao.updatePlay(videoID, beforeXh,roomID) > 0){
				request.setAttribute("msg", "替播成功!");
			} else {
				request.setAttribute("msg", "替播失败!");
			}
	 }
		
		if ("deletePlayVideo".equals(action)) {// 删除
			
			DataSource ds = this.getDataSource(request, "SunCityManage");
			SBVideoPlayListDAO dao = new SBVideoPlayListDAO(ds);
			int id = 0;
			if (request.getParameter("id") != null) {
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (dao.delete(id) > 0) {
				request.setAttribute("msg", "删除成功!");
			} else {
				request.setAttribute("msg", "删除失败!");
			}
		} 
		
/*		else if("updateResult".equals(action)){
			int serverID = 0;
			int orderType = 0;
			int playID = 0;
			String betSerial = "";
			String hgName = "";
			String videofileName = "";
			if(request.getParameter("serverID")!=null&&request.getParameter("serverID")!=""){
				serverID = Integer.parseInt(request.getParameter("serverID"));
			}
			if(request.getParameter("orderType")!=null&&request.getParameter("orderType")!=""){
				orderType = Integer.parseInt(request.getParameter("orderType"));
			}
			if(request.getParameter("playID")!=null&&request.getParameter("playID")!=""){
				playID = Integer.parseInt(request.getParameter("playID"));
			}
			if(request.getParameter("betSerial")!=null&&request.getParameter("betSerial")!=""){
				betSerial = request.getParameter("betSerial");
			}
			if(request.getParameter("hgName")!=null&&request.getParameter("hgName")!=""){
				hgName = request.getParameter("hgName");
				hgName=new String(hgName.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println("荷官名称："+hgName);
			}
			if(request.getParameter("videofileName")!=null&&request.getParameter("videofileName")!=""){
				videofileName = request.getParameter("videofileName");
			}
			DataSource ds=this.getDataSource(request, "DataSource.SunCityManage");
			GameResultManageDAO dao = new GameResultManageDAO(ds);
			String msg = dao.updateResult(serverID,orderType,playID,betSerial,hgName,videofileName);
			request.setAttribute("serverID", serverID);
			if(msg!=""){
				request.setAttribute("msg", msg);
			}
			else{
				request.setAttribute("msg", "对不起，修改结果失败，请联系技术人员!");
			}
				
			return mapping.findForward("updateResult");
		}*/
		return mapping.findForward("updateResult");
	}
}
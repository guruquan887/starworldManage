package com.manage.struts.video;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class SBVideoPlayListAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (request.getSession().getAttribute("USER") == null) {
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}

		String forward = "failure";
		String action = request.getParameter("action");

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
		int pageSize = 50;
		if (request.getParameter("pageSize") != null) {
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
				if (pageSize < 1)
					pageSize = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		DataSource ds = this.getDataSource(request, "SunCityManage");
		SBVideoPlayListDAO dao = new SBVideoPlayListDAO(ds);

		if ("deletePlayVideo".equals(action)) {// 删除
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
		
		else if ("preSetOrder".equals(action)) {// 进入插播页面
			String hgName = "";
			if (request.getParameter("hgName") != null) {
				hgName = request.getParameter("hgName").trim();
				hgName = new String(hgName.getBytes("ISO_8859_1"),"UTF-8");
			}
			int beforeXh = 0;
			if (request.getParameter("beforeXh") != null) {
				beforeXh = Integer.parseInt(request.getParameter("beforeXh"));
				System.out.println(beforeXh+">>>>>>>>beforeXh");
			}

			String where = " hgName ='"+hgName+"'";
			System.out.println(where+"<<<<where条件");
			VideoDAO dao1 = new VideoDAO(ds);
			List<VideoDTO> list =dao1.ListByPage(where, pageIndex, pageSize);
			
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
		
		else if("setOrder".equals(action)){ //确定插播
			    int beforeXh = 0;
				if (request.getParameter("beforeXh") != null) {
					beforeXh = Integer.parseInt(request.getParameter("beforeXh"));
				}
				int videoID = 0;
				if(request.getParameter("radio") != null){
					videoID = Integer.parseInt(request.getParameter("radio"));
					System.out.println(videoID+"<<<被插播视频的videoID");
				}
				if(dao.addXH(videoID, beforeXh) > 0){
					request.setAttribute("msg", "插播成功!");
				} else {
					request.setAttribute("msg", "插播失败!");
				}
		 }
		
		
		 else if("createlist".equals(action)){   //骰宝生成播放列表
			 String msg = dao.createList();
			 System.out.println("------------------");
			 request.setAttribute("msg", msg);
			 return mapping.findForward("bfsuccess");
		 }
		
		List<VideoPlayListDTO> list = dao.ListByPage("", pageIndex, pageSize);
		request.setAttribute("videoPlayList", list);
		
		int stateNo = 0;
        //stateNo = dao.getNum();
		int pageIndexOfPlay = (stateNo+pageSize)/pageSize;
	    request.setAttribute("pageIndexOfPlay", pageIndexOfPlay);
		//System.out.println(pageIndexOfPlay+"<<<<正在播放视频所在页数");

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

		forward = "success";
		return mapping.findForward(forward);
	}

}

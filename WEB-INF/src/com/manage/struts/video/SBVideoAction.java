package com.manage.struts.video;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class SBVideoAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		String action = request.getParameter("action");
		
		int pageIndex = 1;
		if (request.getParameter("pageIndex") != null) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
				if (pageIndex < 1)pageIndex = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int pageSize = 50;
		int curPage = 1;
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
		DataSource ds=this.getDataSource(request, "SunCityManage");
		SBVideoDAO dao = new SBVideoDAO(ds);

		 
		if ("videoAdd".equals(action)) {// 添加骰宝视频文件
			    String fileName = "";
				if (request.getParameter("fileName") != null) {
					fileName = request.getParameter("fileName");
					System.out.println(fileName+":文件名称");
				}
				if(!dao.checkFileIsExist(fileName)){
					String videoName ="";
					String hgName = "";
					String hg2Name = "";
					int videolength = 0;   //视频长度
					String result1 = "";   //结果1
					String result2 = "";
					String result3 = "";
					int resultAnd = 0;
					int resultTime = 0;   //结果时间
					int startTime = 0;
					int endTime = 0;
					
					videolength =Integer.parseInt(request.getParameter("videolength")); //视频时间长度
					videoName = request.getParameter("videoName");
					hgName = request.getParameter("hgName");
					hg2Name = request.getParameter("hg2Name");
					startTime = Integer.parseInt(request.getParameter("startTime"));
					endTime = Integer.parseInt(request.getParameter("endTime"));
					resultTime = Integer.parseInt(request.getParameter("resultTime"));
					result1 = request.getParameter("result1");
					result2 = request.getParameter("result2");
					result3 = request.getParameter("result3");
					resultAnd = Integer.parseInt(result1) + Integer.parseInt(result2) + Integer.parseInt(result3);
					SBVideoDTO dto = new SBVideoDTO();
					
					dto.setVideoName(videoName);
					dto.setFileName(fileName);
					dto.setHgName(hgName);
					dto.setHg2Name(hg2Name);
					dto.setStartTime(startTime);
					dto.setEndTime(endTime);
					dto.setResultTime(resultTime);
					dto.setVideolength(videolength);
					dto.setResult1(result1);
					dto.setResult2(result2);
					dto.setResult3(result3);
					dto.setResultAnd(resultAnd);
					
					if (dao.add(dto)>0) {
						request.setAttribute("msg", "文件名["+fileName+"]添加成功!");
					} else {
						request.setAttribute("msg", "文件名["+fileName+"]添加失败!");
						return new ActionForward("/bjl/sb_video_add.jsp");
					}
				}
				else{
					request.setAttribute("msg", "文件名[ "+fileName+"] 已经存在，添加失败!请更换文件名称!");
					return new ActionForward("/bjl/sb_video_add.jsp");
				}
		 }
		 else if("deleteVideo".equals(action)){//删除
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
		 else if("preUpdateVideo".equals(action)){//预修改
			 SBVideoDTO dto = new SBVideoDTO();
			 int id = 0;
				if (request.getParameter("id") != null) {
					try {
						id = Integer.parseInt(request.getParameter("id"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 dto = dao.getVideoByID(id);
			 request.setAttribute("sbvideoDTO", dto);
			 return new ActionForward("/bjl/sb_video_update.jsp");
		 }
		 else if("updateVideo".equals(action)){//修改
			 int id = 0;
				if (request.getParameter("videoID") != null) {
					id = Integer.parseInt(request.getParameter("videoID"));
					System.out.println(id+"<<<文件ID");
				}
				if(dao.checkFileIsExistByID(id)){
					String fileName ="";
					String videoName ="";
					String hgName = "";
					String hg2Name = "";
					int videolength = 0;
					int resultAnd = 0;
					String result1 = "";   //
					String result2 = "";
					String result3 = "";
					int resultTime = 0;   //1[秒]
					int startTime = 0;
					int endTime = 0;
					
					videolength =Integer.parseInt(request.getParameter("videolength")); //视频时间长度
					fileName = request.getParameter("fileName");
					videoName = request.getParameter("videoName");
					hgName = request.getParameter("hgName");
					hg2Name = request.getParameter("hg2Name");
					result1 = request.getParameter("result1");
					result2 = request.getParameter("result2");
					result3 = request.getParameter("result3");
					resultAnd = Integer.parseInt(result1) + Integer.parseInt(result2) + Integer.parseInt(result3);
					resultTime = Integer.parseInt(request.getParameter("resultTime"));
					startTime = Integer.parseInt(request.getParameter("startTime"));
					endTime = Integer.parseInt(request.getParameter("endTime"));
					
					SBVideoDTO dto = new SBVideoDTO();
					
					dto.setVideoID(id);
					dto.setVideoName(videoName);
					dto.setFileName(fileName);
					dto.setHgName(hgName);
					dto.setHg2Name(hg2Name);
					dto.setVideolength(videolength);
					dto.setResult1(result1);
					dto.setResult2(result2);
					dto.setResult3(result3);
					dto.setResultAnd(resultAnd);
					dto.setResultTime(resultTime);
					dto.setStartTime(startTime);
					dto.setEndTime(endTime);
					
					if (dao.update(dto) > 0) {
						request.setAttribute("msg", "ID为[ "+id+" ]的视频文件修改成功!");
					} else {
						request.setAttribute("msg", "ID为[ "+id+" ]的视频文件修改失败!");
						return new ActionForward("/bjl/sb_video_update.jsp");
					}
				}
				else{
					request.setAttribute("msg", "修改失败! ID为[ "+id+"]的视频文件已经不存在,请更换确定数据库是否连接中!");
				}
		  }
		 
		 List<SBVideoDTO> list = dao.ListByPage("", pageIndex, pageSize);
		 request.setAttribute("videoList", list);
		 
		 int totalPage = dao.getTotalPage();
		 
		 if(pageIndex>dao.getTotalPage())pageIndex=dao.getTotalPage();
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
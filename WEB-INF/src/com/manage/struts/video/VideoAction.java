package com.manage.struts.video;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class VideoAction extends Action {

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
		DataSource ds=this.getDataSource(request, "QPPlatformManagerDB");
		VideoDAO dao = new VideoDAO(ds);
		
		if("videoPreAdd".equals(action)){
			
			List<VideoDTO> video=dao.select();
			request.setAttribute("roomList", video);
			return mapping.findForward("videoPreAdd");
			
		}
		 
		else if ("videoAdd".equals(action)) {// 添加视频文件
			    String fileName = "";
				if (request.getParameter("bjl_fileName") != null) {
					fileName = request.getParameter("bjl_fileName");
					System.out.println(fileName+"文件名称");
				}
				if(!dao.checkFileIsExist(fileName)){
					String videoName ="";
					int videoType = 0;
					String hgName = "";
					String hg2Name = "";
					int videolength = 0;   //视频长度
					String result = "";    //开牌结果
					String result1 = "";   //开牌1
					String result2 = "";
					String result3 = "";
					String result4 = "";
					String result5 = "";
					String result6 = "";
					int result1Time = 0;   //开牌1 [秒]时间
					int result2Time = 0;
					int result3Time = 0;
					int result4Time = 0;
					int result5Time = 0;
					int result6Time = 0;
					String resultOfWinLost = "";
					int roomType = 0;
					
					
					String selectresult1 = "";
					selectresult1 = request.getParameter("selectresult1");
					String selectresult2 = "";
					selectresult2 = request.getParameter("selectresult2");
					String selectresult3 = "";
					selectresult3 = request.getParameter("selectresult3");
					String selectresult4 = "";
					selectresult4 = request.getParameter("selectresult4");
					String selectresult5 = "";
					selectresult5 = request.getParameter("selectresult5");
					String selectresult6 = "";
					selectresult6 = request.getParameter("selectresult6");
					
					String selectresult1Type = "";
					selectresult1Type = request.getParameter("selectresult1Type");
					String selectresult2Type = "";
					selectresult2Type = request.getParameter("selectresult2Type");
					String selectresult3Type = "";
					selectresult3Type = request.getParameter("selectresult3Type");
					String selectresult4Type = "";
					selectresult4Type = request.getParameter("selectresult4Type");
					String selectresult5Type = "";
					selectresult5Type = request.getParameter("selectresult5Type");
					String selectresult6Type = "";
					selectresult6Type = request.getParameter("selectresult6Type");
					
					String selectresult1A = "";
					selectresult1A = request.getParameter("selectresult1A");
					String selectresult2A = "";
					selectresult2A = request.getParameter("selectresult2A");
					String selectresult3A = "";
					selectresult3A = request.getParameter("selectresult3A");
					String selectresult4A = "";
					selectresult4A = request.getParameter("selectresult4A");
					String selectresult5A = "";
					selectresult5A = request.getParameter("selectresult5A");
					String selectresult6A = "";
					selectresult6A = request.getParameter("selectresult6A");
					
					videolength =Integer.parseInt(request.getParameter("bjl_videolength")); //视频时间长度
					videoName = request.getParameter("bjl_videoName");
					videoType = Integer.parseInt(request.getParameter("bjl_videoType"));
					hgName = request.getParameter("bjl_hgName");
					hg2Name = request.getParameter("bjl_hg2Name");
					result = request.getParameter("bjl_result");
					result1 = selectresult1Type + selectresult1 + selectresult1A;
					result2 = selectresult2Type + selectresult2 + selectresult2A;
					result3 = selectresult3Type + selectresult3 + selectresult3A;
					result4 = selectresult4Type + selectresult4 + selectresult4A;
					result5 = selectresult5Type + selectresult5 + selectresult5A;
					result6 = selectresult6Type + selectresult6 + selectresult6A;
					result1Time = Integer.parseInt(request.getParameter("bjl_result1Time"));
					result2Time = Integer.parseInt(request.getParameter("bjl_result2Time"));
					result3Time = Integer.parseInt(request.getParameter("bjl_result3Time"));
					result4Time = Integer.parseInt(request.getParameter("bjl_result4Time"));
					result5Time = Integer.parseInt(request.getParameter("bjl_result5Time"));
					result6Time = Integer.parseInt(request.getParameter("bjl_result6Time"));
					resultOfWinLost = request.getParameter("select_resultOfWinLost");
					roomType = Integer.parseInt(request.getParameter("roomType"));
					
					VideoDTO dto = new VideoDTO();
					
					dto.setVideoName(videoName);
					dto.setFileName(fileName);
					dto.setVideoType(videoType);
					dto.setHgName(hgName);
					dto.setHg2Name(hg2Name);
					dto.setVideolength(videolength);
					dto.setResult(result);
					dto.setResult1(result1);
					dto.setResult2(result2);
					dto.setResult3(result3);
					dto.setResult4(result4);
					dto.setResult5(result5);
					dto.setResult6(result6);
					dto.setResult1Time(result1Time);
					dto.setResult2Time(result2Time);
					dto.setResult3Time(result3Time);
					dto.setResult4Time(result4Time);
					dto.setResult5Time(result5Time);
					dto.setResult6Time(result6Time);
					dto.setResultOfWinLost(resultOfWinLost);
					dto.setRoomType(roomType);
					
					if (dao.add(dto)>0) {
						request.setAttribute("msg", "文件名["+fileName+"]添加成功!");
					} else {
						request.setAttribute("msg", "文件名["+fileName+"]添加失败!");
						return new ActionForward("/bjl/bjl_video_add.jsp");
					}
				}
				else{
					request.setAttribute("msg", "文件名[ "+fileName+"] 已经存在，添加失败!请更换文件名称!");
					return new ActionForward("/bjl/bjl_video_add.jsp");
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
			 VideoDTO dto = new VideoDTO();
			 int id = 0;
				if (request.getParameter("id") != null) {
					try {
						id = Integer.parseInt(request.getParameter("id"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 dto = dao.getVideoByID(id);
			 request.setAttribute("videoDTO", dto);
			 
			 List<VideoDTO> video=dao.select();
			 request.setAttribute("roomList", video);
			 return new ActionForward("/bjl/bjl_video_update.jsp");
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
					int videoType = 0;
					String hgName = "";
					String hg2Name = "";
					int videolength = 0;
					String result = "";    //开牌结果
					String result1 = "";   //开牌1
					String result2 = "";
					String result3 = "";
					String result4 = "";
					String result5 = "";
					String result6 = "";
					int result1Time = 0;   //开牌1 [秒]时间
					int result2Time = 0;
					int result3Time = 0;
					int result4Time = 0;
					int result5Time = 0;
					int result6Time = 0;
					String resultOfWinLost = "";
					
					String selectresult1 = "";
					selectresult1 = request.getParameter("selectresult1");
					String selectresult2 = "";
					selectresult2 = request.getParameter("selectresult2");
					String selectresult3 = "";
					selectresult3 = request.getParameter("selectresult3");
					String selectresult4 = "";
					selectresult4 = request.getParameter("selectresult4");
					String selectresult5 = "";
					selectresult5 = request.getParameter("selectresult5");
					String selectresult6 = "";
					selectresult6 = request.getParameter("selectresult6");
					
					String selectresult1Type = "";
					selectresult1Type = request.getParameter("selectresult1Type");
					String selectresult2Type = "";
					selectresult2Type = request.getParameter("selectresult2Type");
					String selectresult3Type = "";
					selectresult3Type = request.getParameter("selectresult3Type");
					String selectresult4Type = "";
					selectresult4Type = request.getParameter("selectresult4Type");
					String selectresult5Type = "";
					selectresult5Type = request.getParameter("selectresult5Type");
					String selectresult6Type = "";
					selectresult6Type = request.getParameter("selectresult6Type");
					
					String selectresult1A = "";
					selectresult1A = request.getParameter("selectresult1A");
					String selectresult2A = "";
					selectresult2A = request.getParameter("selectresult2A");
					String selectresult3A = "";
					selectresult3A = request.getParameter("selectresult3A");
					String selectresult4A = "";
					selectresult4A = request.getParameter("selectresult4A");
					String selectresult5A = "";
					selectresult5A = request.getParameter("selectresult5A");
					String selectresult6A = "";
					selectresult6A = request.getParameter("selectresult6A");
					
					videolength =Integer.parseInt(request.getParameter("bjl_videolength")); //视频时间长度
					fileName = request.getParameter("bjl_fileName");
					videoName = request.getParameter("bjl_videoName");
					videoType = Integer.parseInt(request.getParameter("bjl_videoType"));
					hgName = request.getParameter("bjl_hgName");
					hg2Name = request.getParameter("bjl_hg2Name");
					result = request.getParameter("bjl_result");
					result1 = selectresult1Type + selectresult1 + selectresult1A;
					result2 = selectresult2Type + selectresult2 + selectresult2A;
					result3 = selectresult3Type + selectresult3 + selectresult3A;
					result4 = selectresult4Type + selectresult4 + selectresult4A;
					result5 = selectresult5Type + selectresult5 + selectresult5A;
					result6 = selectresult6Type + selectresult6 + selectresult6A;
					result1Time = Integer.parseInt(request.getParameter("bjl_result1Time"));
					result2Time = Integer.parseInt(request.getParameter("bjl_result2Time"));
					result3Time = Integer.parseInt(request.getParameter("bjl_result3Time"));
					result4Time = Integer.parseInt(request.getParameter("bjl_result4Time"));
					result5Time = Integer.parseInt(request.getParameter("bjl_result5Time"));
					result6Time = Integer.parseInt(request.getParameter("bjl_result6Time"));
					resultOfWinLost = request.getParameter("select_resultOfWinLost");
					
					VideoDTO dto = new VideoDTO();
					
					dto.setVideoID(id);
					dto.setVideoName(videoName);
					dto.setFileName(fileName);
					dto.setVideoType(videoType);
					dto.setHgName(hgName);
					dto.setHg2Name(hg2Name);
					dto.setVideolength(videolength);
					dto.setResult(result);
					dto.setResult1(result1);
					dto.setResult2(result2);
					dto.setResult3(result3);
					dto.setResult4(result4);
					dto.setResult5(result5);
					dto.setResult6(result6);
					dto.setResult1Time(result1Time);
					dto.setResult2Time(result2Time);
					dto.setResult3Time(result3Time);
					dto.setResult4Time(result4Time);
					dto.setResult5Time(result5Time);
					dto.setResult6Time(result6Time);
					dto.setResultOfWinLost(resultOfWinLost);
					
					if (dao.update(dto) > 0) {
						request.setAttribute("msg", "ID为[ "+id+" ]的视频文件修改成功!");
					} else {
						request.setAttribute("msg", "ID为[ "+id+" ]的视频文件修改失败!");
						return new ActionForward("/bjl/bjl_video_update.jsp");
					}
				}
				else{
					request.setAttribute("msg", "修改失败! ID为[ "+id+"]的视频文件已经不存在,请更换确定数据库是否连接中!");
				}
		  }
		 
		 List<VideoDTO> list = dao.ListByPage("", pageIndex, pageSize);
		 request.setAttribute("videoList", list);
		 
//		 VideoPlayListDAO dao1 = new VideoPlayListDAO(ds);
//		 int stateNo = 0;
//	         stateNo = dao1.getNum();
//	     int pageIndexOfPlay = (stateNo+pageSize)/pageSize;
//	     request.setAttribute("pageIndexOfPlay", pageIndexOfPlay);
	     
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
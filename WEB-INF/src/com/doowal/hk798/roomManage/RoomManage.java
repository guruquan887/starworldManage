package com.doowal.hk798.roomManage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;

public class RoomManage extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		DataSource ds = this.getDataSource(request, "QPTreasureDB");
		GameRoomDAO dao = new GameRoomDAO(ds);
		String action = "";
		if(request.getParameter("action") != null && !"".equals(request.getParameter("action"))){
			action = request.getParameter("action");
		}
		String orderby ="serverID";
		char typeNumber;
		if(request.getParameter("orderby")!=null && !"".equals(request.getParameter("orderby"))){
			orderby = request.getParameter("orderby");
			//System.out.println("------------orderby:"+orderby);
		}
		if(request.getParameter("typeNumber")!=null && !"".equals(request.getParameter("typeNumber"))){
			typeNumber = request.getParameter("typeNumber").charAt(0);
			//System.out.println("------------typeNumber:"+typeNumber);
			switch(typeNumber){
				case 'a' :request.setAttribute("androidUserScore_", 1); break;
				case 'b' :request.setAttribute("androidUserScore_", -1); break;
				case 'c' :request.setAttribute("maximum_", 1); break;
				case 'd' :request.setAttribute("maximum_", -1); break;
				case 'e' :request.setAttribute("tax_", 1); break;
				case 'f' :request.setAttribute("tax_", -1); break;
			}
		}
		String androidUserScore_ = null;
		String maximum_ = null;
		String tax_ = null;
		if(request.getParameter("androidUserScore_") != null && !"".equals(request.getParameter("androidUserScore_"))){
			androidUserScore_ = request.getParameter("androidUserScore_");
			request.setAttribute("androidUserScore_", Integer.parseInt(androidUserScore_));
			orderby = (androidUserScore_.equals("1"))? "androidUserScore desc" :"androidUserScore ";
		}
		if(request.getParameter("maximum_") != null && !"".equals(request.getParameter("maximum_"))){
			maximum_ = request.getParameter("maximum_");
			request.setAttribute("maximum_", Integer.parseInt(maximum_));
			orderby = (maximum_.equals("1"))? "maximum desc" :"maximum ";
		}
		if(request.getParameter("tax_") != null && !"".equals(request.getParameter("tax_"))){
			tax_ = request.getParameter("tax_");
			request.setAttribute("tax_", Integer.parseInt(tax_));
			orderby = (tax_.equals("1"))? "tax desc" :"tax ";
		}
		//System.out.println(">>>>>>>>>>>>>>>>>orderby:"+orderby);
		//---
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null || curPagestr == "") {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}
		if (curPage < 1)
			curPage = 1;
		int pageSize = 30;
		//---
		if("rList".equals(action)){
			
			String where = "";
			if(request.getParameter("roomName")!=null && !"".equals(request.getParameter("roomName"))){
				where = " roomName like '%"+java.net.URLDecoder.decode(request.getParameter("roomName").trim(),"UTF-8")+"%'";
			}
			if(request.getParameter("pageSize")!=null && !"".equals(request.getParameter("pageSize"))){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			System.out.println("---pageSize:"+pageSize+"------where:"+where);
			List<GameRoomDTO> roomlist = dao.getGameRoomDTOList(curPage, pageSize, where,orderby);
			
			int totalPage = dao.getPageCount();
			if (curPage > dao.getPageCount())
				curPage = dao.getPageCount();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRecordCount());
			if (roomlist.size() != 0) {
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("rtypeList", roomlist);
			} else {
				request.setAttribute("returnInfo", dao.returnInfo());
			}
			return new ActionForward("/game/gameroom.jsp");
			
		}

		else if("rOne".equals(action)){
			
			String bo = "";
			if(request.getParameter("bo") != null && !"".equals(request.getParameter("bo"))){
				bo = request.getParameter("bo");
				if("true".equals(bo)) {
					bo="修改成功!";
				}else {
					bo="修改失败!";
					}
			}
			String roomID = request.getParameter("rid");
			GameRoomDTO roomdto = dao.getGameRoomDTO(roomID);
			request.setAttribute("roomdto", roomdto);
			request.setAttribute("msg", bo);
			return new ActionForward("/roomOne.jsp");
			
		}
		
		else if("preUpdate".equals(action)){
			String roomID = "";
			if(request.getParameter("roomID")!=null){
				roomID = request.getParameter("roomID");
			}
			GameRoomDTO roomdto = dao.getGameRoomDTO(roomID);
			request.setAttribute("roomdto", roomdto);
			return new ActionForward("/game/updateroom.jsp");
			
		}
		
		else if("updateRoom".equals(action)){
			
			String roomID = request.getParameter("roomID");
			//String no = request.getParameter("no");
			String roomName = request.getParameter("roomName");
			String androidUserScore = request.getParameter("androidUserScore");
			//int roomState = Integer.parseInt(request.getParameter("roomState"));
			int maximum = Integer.parseInt(request.getParameter("maximum"));
			double tax = Double.parseDouble((request.getParameter("tax")));
			GameRoomDTO roomdto = new GameRoomDTO();
			roomdto.setRoomID(Integer.parseInt(roomID));
			roomdto.setRoomName(roomName);
			//roomdto.setRoomState(roomState);
			roomdto.setAndroidUserScore(androidUserScore);
			roomdto.setMaximum(maximum);
			roomdto.setTax(tax);
			
			if(dao.update(roomdto)){
				System.out.println("---------成功");
				return new ActionForward("/game/roomManage.do?action=rList");
			}else{
				System.out.println("---------失败");
				return new ActionForward("/game/roomManage.do?action=rList");
			}
		}
		
		else if("updateState".equals(action)){
			int roomID = Integer.parseInt(request.getParameter("roomID"));
			int roomState = Integer.parseInt(request.getParameter("roomState"));
			String msg = "";
			if(roomState==0){//0-开启中  1-关闭中
				msg = dao.updateState(roomID,1);
			}
			if(roomState==1){
				msg = dao.updateState(roomID,0);
			}
			request.setAttribute("msg", msg);
			return new ActionForward("/game/roomManage.do?action=rList");
			
		}
		
		return null;
	}
	
	
}

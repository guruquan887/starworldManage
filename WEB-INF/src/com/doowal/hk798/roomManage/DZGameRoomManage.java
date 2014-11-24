package com.doowal.hk798.roomManage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DZGameRoomManage extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		DataSource ds = this.getDataSource(request, "dbgame88");
		DZGameRoomDAO dao = new DZGameRoomDAO(ds);
		String action = "";
		if(request.getParameter("action") != null && !"".equals(request.getParameter("action"))){
			action = request.getParameter("action");
		}
		
		if("rList".equals(action)){
			
			String where = "";
			if(request.getParameter("roomName")!=null && !"".equals(request.getParameter("roomName"))){
				where = " roomName like '%"+java.net.URLDecoder.decode(request.getParameter("roomName").trim(),"UTF-8")+"%'";
			}
			List<DZGameRoomDTO> roomlist = dao.getGameRoomList();
			
			if (roomlist.size() != 0) {
				
				request.setAttribute("rList", roomlist);
			} else {
				request.setAttribute("returnInfo", dao.returnInfo());
			}
			return new ActionForward("/game/dzgameroom.jsp");
			
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
			DZGameRoomDTO roomdto = dao.getDZGameRoomDTO(roomID);
			request.setAttribute("roomdto", roomdto);
			request.setAttribute("msg", bo);
			return new ActionForward("/roomOne.jsp");
			
		}
		
		else if("preUpdate".equals(action)){
			String gametype = "";
			if(request.getParameter("gametype")!=null){
				gametype = request.getParameter("gametype");
			}
			DZGameRoomDTO roomdto = dao.getDZGameRoomDTO(gametype);
			request.setAttribute("roomdto", roomdto);
			return new ActionForward("/game/updatedzroom.jsp");
			
		}
		
		else if("updateRoom".equals(action)){
			
			String gametype = request.getParameter("gametype");
			String gamename = request.getParameter("gamename");
			String round_maxbet = request.getParameter("round_maxbet");
			String line_maxbet = request.getParameter("line_maxbet");
			String single_maxbet = request.getParameter("single_maxbet");
			String limit_minbet = request.getParameter("limit_minbet");
			String bonus_maxbet = request.getParameter("bonus_maxbet");
			String bonus_minbet = request.getParameter("bonus_minbet");
			DZGameRoomDTO dto = new DZGameRoomDTO();
			dto.setGametype(Integer.parseInt(gametype));
			dto.setGamename(gamename);
			dto.setRound_maxbet(Integer.parseInt(round_maxbet));
			dto.setLine_maxbet(Integer.parseInt(line_maxbet));
			dto.setSingle_maxbet(Integer.parseInt(single_maxbet));
			dto.setLimit_minbet(Integer.parseInt(limit_minbet));
			dto.setBonus_maxbet(Integer.parseInt(bonus_maxbet));
			dto.setBonus_minbet(Integer.parseInt(bonus_minbet));
			
			if(dao.update(dto)){
				System.out.println("---------成功");
				return new ActionForward("/game/dzroomManage.do?action=rList");
			}else{
				System.out.println("---------失败");
				return new ActionForward("/game/dzroomManage.do?action=rList");
			}
		}
		
		else if("updateState".equals(action)){
			int gametype = Integer.parseInt(request.getParameter("gametype"));
			int islock = Integer.parseInt(request.getParameter("islock"));
			String msg = "";
			if(islock==0){//0-开启中  2-关闭中
				msg = dao.updateState(gametype,2);
			}
			if(islock==1){
				msg = dao.updateState(gametype,0);
			}
			if(islock==2){
				msg = dao.updateState(gametype,0);
			}
			request.setAttribute("msg", msg);
			return new ActionForward("/game/dzroomManage.do?action=rList");
			
		}
		
		return null;
	}
	
	
}

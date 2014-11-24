package com.doowal.struts.single;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.staworld.advertisement.PageDTO;


public class Note2Action extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		
//		int curPage = 1;
//		String curPagestr = request.getParameter("curPage");
//		if (curPagestr == null) {
//			curPage = 1;
//		} else {
//			curPage = Integer.parseInt(curPagestr);
//		}
//		if (curPage < 1)
//			curPage = 1;
//		int pageSize=30;
		
		List<PlayDTO> plays = (List<PlayDTO>)request.getSession().getAttribute("playNumber");
		DataSource ds = this.getDataSource(request, "QPTreasureDB");
		Note2DAO dao = new Note2DAO(ds);
		
		String orderby = " ";
		int isZC = 1; //是否占成 0 全部，1 占成
		if(request.getParameter("zc")!=null && !"".equals(request.getParameter("zc"))){
			isZC = Integer.parseInt(request.getParameter("zc"));
			//System.out.println("--------------isZC:"+isZC);
		}
		
		if("list".equals(action)){
			
			List<Note2DTO> gameList = dao.getServerInfo();
			String [] serverIDStr = null;
			if(gameList.size()>0){
				serverIDStr = new String [gameList.size()];
				for(int i = 0; i < serverIDStr.length; i++ ){
					serverIDStr[i] = gameList.get(i).getServerID()+"";
				}
			}
			System.out.println("------serverIDStr:"+serverIDStr.length+"----"+Arrays.toString(serverIDStr));
			
			String [] betSerial = dao.getBetSerial(); //期号
			System.out.println("------betSerial:"+betSerial.length+"----"+Arrays.toString(betSerial));
			
			List<Note2DTO> betSerialAndID = dao.getBetSerialAndID();
			
			List<Note2DTO> showGameList = new ArrayList<Note2DTO>();
			for(int i = 0 ; i < betSerialAndID.size(); i++){
				if( i == 0) {
					showGameList.add(betSerialAndID.get(i));
					serverIDStr = NoteCommon.value(serverIDStr , betSerialAndID.get(i).getServerID());
				}
				if(i > 0 && NoteCommon.isExist(serverIDStr,betSerialAndID.get(i).getServerID())){
					showGameList.add(betSerialAndID.get(i));
					serverIDStr = NoteCommon.value(serverIDStr , betSerialAndID.get(i).getServerID());
				}
			}
//			System.out.println("!!!!!!------showGameList.size:"+showGameList.size());//有最新数据的项目和投注编号
			
			Map<String,Object> info = new HashMap<String, Object>();
			for(int i = 0 ; i < showGameList.size(); i++){
				if(isZC == 0){//全部
					//System.out.println("-----全部");
					info.put(showGameList.get(i).getServerID(), dao.getBetSerialInfo(showGameList.get(i).getBetSerial(),showGameList.get(i).getServerID()) );
				}
				else if(isZC == 1){//占成
					//System.out.println("-----占成");
					info.put(showGameList.get(i).getServerID(), dao.getBetSerialInfo_ZC(showGameList.get(i).getBetSerial(),showGameList.get(i).getServerID()) );
				}
			}
			gameList = NoteCommon.setGameList(gameList,info);
			
			request.setAttribute("gameList", gameList);
			
			return new ActionForward("/single/notelist.jsp");
			
		}
		
		else if("detailInfo".equals(action)){
			
			int total = 0;//总数
			String kindID = "";
			if(request.getParameter("kid") != null && !"".equals(request.getParameter("kid"))){
				kindID = request.getParameter("kid").trim();
			}
			kindID = kindID.substring(0,kindID.length()-1);// 208百家乐、209骰宝、301轮盘
			for(int i = 0; i < plays.size(); i++){
				if(Integer.parseInt(kindID) == plays.get(i).getKindID()){
					total = plays.get(i).getPlayTotalNumbers();
					System.out.println("~~~~"+plays.get(i).getKindID()+"---total:"+total);
				}
			}
			
			String betSerial = "";
			if(request.getParameter("betSerial") != null && !"".equals(request.getParameter("betSerial"))){
				betSerial = request.getParameter("betSerial").trim();
			}
			
			if("208".equals(kindID) || "209".equals(kindID)){//百家乐
			List<Note2DTO> playInfo = dao.getPlayInfo(betSerial,isZC);
			String totalGold = NoteCommon.DoubleFormat(dao.getTotalGold(betSerial));
			List<Note2DTO> oneList = dao.getPlayList(kindID,NoteCommon.pageStart(total, 1),NoteCommon.pageEnd(total, 1),playInfo);
			List<Note2DTO> twoList = dao.getPlayList(kindID,NoteCommon.pageStart(total, 2),NoteCommon.pageEnd(total, 2),playInfo);
			List<Note2DTO> threeList = dao.getPlayList(kindID,NoteCommon.pageStart(total, 3),NoteCommon.pageEnd(total, 3),playInfo);
			List<Note2DTO> fourList = dao.getPlayList(kindID,NoteCommon.pageStart(total, 4),NoteCommon.pageEnd(total, 4),playInfo);
			
				request.setAttribute("oneList", oneList);
				request.setAttribute("twoList", twoList);
				request.setAttribute("threeList", threeList);
				request.setAttribute("fourList", fourList);
				request.setAttribute("totalGold", totalGold);
				return new ActionForward("/single/note_info_list.jsp");
			}
			if("301".equals(kindID)){//轮盘
				
			    List<Note2DTO> playInfo = dao.getPlayInfo(betSerial,isZC);
				Note2DTO dto = NoteCommon.setLunPan(playInfo);
				request.setAttribute("dto", dto);
				return new ActionForward("/single/note_info_list_lunPan.jsp");
			}
			
		}
		
		else if("display_DetailInfo".equals(action)){
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null || curPagestr == "") {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize = 15;
			
			String where = "";
			String betSerial = "";
			if(request.getParameter("betSerial") != null && !"".equals(request.getParameter("betSerial")) ){
				betSerial = request.getParameter("betSerial");
				where = " betSerial ='"+betSerial+"' ";
				request.setAttribute("betSerial", betSerial);
			}
			String gameArea = "";
			if(request.getParameter("gameArea") != null && !"".equals(request.getParameter("gameArea")) ){
				gameArea = request.getParameter("gameArea");
				request.setAttribute("gameArea", gameArea);
				where += "and gameArea = "+ gameArea;
			}
			orderby = " generateTime desc ";
			
			System.out.println("---详细显示-----where:"+where);
			List<Note2DTO> infoList = dao.getRecordList(curPage,pageSize,where,orderby);
			
			int totalPage = dao.getTotalPage();
			if (curPage > dao.getTotalPage())
				curPage = dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRecordCount());
			if (infoList.size() != 0) {
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("infoList", infoList);
			}else {
				request.setAttribute("returnInfo", dao.returnInfo());
			}
			
			return new ActionForward("/single/note_DetailInfo.jsp");
		}
		
		
		
		return null;
	}
	
	

}

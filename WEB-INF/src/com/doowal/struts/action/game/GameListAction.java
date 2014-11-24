package com.doowal.struts.action.game;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;


public class GameListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String action = "";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		DataSource ds = this.getDataSource(request, "QPPlatformDB");
		GameListDAO dao = new GameListDAO(ds);
		if("gameList".equals(action)){
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			    System.out.println(">>>>>>>>>>>termWord:"+termWord);
			}
			String forward = "failure";
			
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=10;
			String where="";
			int gameType = 0;
			if(request.getParameter("gameType")!=null){
				gameType = Integer.parseInt(request.getParameter("gameType"));
				where += "gameType="+gameType;
			}
			List<GameListDTO> list = dao.GetRecordByPage(curPage,pageSize,where);
			int totalPage = dao.getTotalPage();
			request.setAttribute("gamelist", list);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRcordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			forward = "success";
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			return mapping.findForward(forward);
		}
		
		else if("gamePreUpdate".equals(action)){
			int id = 0;
			if(request.getParameter("id")!=null){
				id = Integer.parseInt(request.getParameter("id"));
			}
			GameListDTO dto = new GameListDTO();
			dto = dao.preUpdate(id);
			request.setAttribute("dto", dto);
			return mapping.findForward("preUpdate");
		}
		
		else if("gameUpdate".equals(action)){
			int id = 0;
			if(request.getParameter("id")!=null){
				id = Integer.parseInt(request.getParameter("id"));
			}
			String gameName = "";
			if(request.getParameter("gameName")!=null){
				gameName = request.getParameter("gameName");
			}
			String gameUrl= "";
			if(request.getParameter("gameUrl")!=null){
				gameUrl = request.getParameter("gameUrl");
			}
			String gameDes = "";
			if(request.getParameter("content")!=null){
				gameDes = request.getParameter("content");
			}
			String gamephoto2 = "";
			if(request.getParameter("photourl")!=null){
				gamephoto2 = request.getParameter("photourl");
			}
			int gameType = 0;
			if(request.getParameter("gameType")!=null){
				gameType = Integer.parseInt(request.getParameter("gameType"));
			}
			GameListDTO dto = new GameListDTO();
			dto.setId(id);
			dto.setGameName(gameName);
			dto.setGameDes(gameDes);
			dto.setGameUrl(gameUrl);
			dto.setGamephoto2(gamephoto2);
			dto.setGameType(gameType);
			dao.update(dto);
			return mapping.findForward("update");
		}
		
		
		else if("gameAdd".equals(action)){
			
			String gameName = "";
			if(request.getParameter("gameName")!=null){
				gameName = request.getParameter("gameName");
			}
			String gameUrl= "";
			if(request.getParameter("gameUrl")!=null){
				gameUrl = request.getParameter("gameUrl");
			}
			String gameDes = "";
			if(request.getParameter("content")!=null){
				gameDes = request.getParameter("content");
			}
			String gamephoto2 = "";
			if(request.getParameter("photourl")!=null){
				gamephoto2 = request.getParameter("photourl");
			}
			int gameType = 0;
			if(request.getParameter("gameType")!=null){
				gameType = Integer.parseInt(request.getParameter("gameType"));
			}
			GameListDTO dto = new GameListDTO();
			dto.setGameName(gameName);
			dto.setGameDes(gameDes);
			dto.setGameUrl(gameUrl);
			dto.setGamephoto2(gamephoto2);
			dto.setGameType(gameType);
			dao.add(dto);
			return mapping.findForward("gameAdd");
		}
		
		else if("gameDel".equals(action)){
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.delete(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("gameDel");
		}
		
		else if("isRec".equals(action)){
			int id = 0;
			if(request.getParameter("id")!=null){
				id = Integer.parseInt(request.getParameter("id"));
			}
			int isRec = 0;
			if(request.getParameter("isRec")!=null){
				isRec = Integer.parseInt(request.getParameter("isRec"));
			}
			if(isRec==0){
				isRec = 1;
			}
			else{
				isRec = 0;
			}
			String msg = dao.updateIsRec(id,isRec);
			request.setAttribute("msg", msg);
			return mapping.findForward("isRec");
		}
		
		else if("display_in".equals(action)){
					
			String id = request.getParameter("p_id");
			GameListDTO dto = dao.getProduceById(id);
			String title = dto.getGameDes();
			String width = "360px";
			width = dto.getGameDes().length()<100?"360px":"560px";
			width = dto.getGameDes().length()<30?"160px":width;
			request.setAttribute("dto", dto);
			request.setAttribute("msg", title);
			request.setAttribute("width", width);
			return new ActionForward("/web/display_in.jsp");
					
		}
		
		else if("display_in_image".equals(action)){
			
			String id = request.getParameter("p_id");
			GameListDTO dto = dao.getProduceById(id);
			request.setAttribute("dto", dto);
			return new ActionForward("/web/display_in_image.jsp");
			
		}
		return null;
	}
}

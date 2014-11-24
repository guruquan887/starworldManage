package com.keno8.struts.action.gameshopcate;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.GameShopCateTypeDAO;
import com.keno8.struts.dto.GameShopCateTypeDTO;
import com.keno8.struts.dto.PageDTO;


public class GameShopCateTypeListAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "typeList";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		
		if("typeList".equals(action)){
			
		DataSource ds = this.getDataSource(request, "Keno8");
		GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if(curPagestr == null){
			curPage = 1;
		}else{
			curPage = Integer.parseInt(curPagestr);
		}
		if(curPage<1)
			curPage = 1;
		int pageSize = 10;
		String where = "";
		List<GameShopCateTypeDTO> list = dao.GetRecordByPage(curPage, pageSize, where);
		
		int totalPage = dao.getTotalPage();
		if(curPage>dao.getTotalPage()) curPage = dao.getTotalPage();
		request.setAttribute("gameshoptypelist", list);
		PageDTO dto = new PageDTO();
		dto.setCurPage(curPage);
		dto.setTotalPage(totalPage);
		dto.setTotalRecord(dao.getRecordCount());
		dto.setPageSize(pageSize);
		request.setAttribute("page", dto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("typeList");
		}
		else if("addType".equals(action)){
			String shoptypename = request.getParameter("shoptypename");
			GameShopCateTypeDTO dto = new GameShopCateTypeDTO();
			
			dto.setTypeName(shoptypename);
			DataSource ds = this.getDataSource(request, "Keno8");
			GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
			dao.add(dto);
			if(dto!=null){
				return mapping.findForward("addType");
			}
			return mapping.findForward("failure");
		}
		else if("preUpdateType".equals(action)){
			int typeID = Integer.parseInt(request.getParameter("typeID"));
			GameShopCateTypeDTO dto = new GameShopCateTypeDTO();
			dto.setTypeID(typeID);
			DataSource ds = this.getDataSource(request, "Keno8");
			GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
			GameShopCateTypeDTO dto1 = dao.getById(typeID);
			request.setAttribute("gameshoptype", dto1);
			return mapping.findForward("preUpdate");
		}
		else if("updateType".equals(action)){
			int typeID = Integer.parseInt(request.getParameter("typeID"));
			String shoptypename = request.getParameter("shoptypename");
			String descript = request.getParameter("descript");
			
			GameShopCateTypeDTO dto = new GameShopCateTypeDTO();
			dto.setTypeID(typeID);
			dto.setTypeName(shoptypename);
			dto.setDescript(descript);
			DataSource ds = this.getDataSource(request, "Keno8");
			GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
			dao.update(dto);
			return mapping.findForward("updateType");
		}
		else if("deleteType".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			int typeID = 0;
			if(request.getParameter("gameshoptypeid")!=null){
				typeID = Integer.parseInt(request.getParameter("gameshoptypeid"));
			}
			GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
			if(typeID!=0){
				dao.DeleteGameshoptype(typeID);
				request.setAttribute("msg", "刪除成功！");
			}
			else{
				request.setAttribute("msg", "刪除失敗，ID不存在!");
			}
			return mapping.findForward("deleteType");
		}
		
		return null;
	}
}

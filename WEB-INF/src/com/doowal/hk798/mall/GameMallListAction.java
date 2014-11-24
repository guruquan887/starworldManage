package com.doowal.hk798.mall;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;

public class GameMallListAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String action = "mallItemsList";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		
		if("mallItemsList".equals(action)){
			
		DataSource ds = this.getDataSource(request, "QPTreasureDB");	
		GameShopCateDAO dao = new GameShopCateDAO(ds);
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}

		if (curPage < 1)
			curPage = 1;
		int pageSize = 10;
		int typeId = 0;
	
		try {
			if (request.getParameter("typeId") != null) {
				typeId = Integer.parseInt(request.getParameter("typeId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String where = "";
		if (typeId > 0) {
			where += "typeId=" + typeId;
		}
		List<GameShopCateDTO> list = dao.GetRecordByPage(curPage, pageSize,where);
		GameShopCateTypeDAO dao1 = new GameShopCateTypeDAO(ds);
		List<GameShopCateTypeDTO> list1 = dao1.select();
		int totalPage = dao.getTotalPage();
		request.setAttribute("gameshoplist", list);
		if (curPage > dao.getTotalPage())
			curPage = dao.getTotalPage();
		PageDTO pdto = new PageDTO();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(totalPage);
		pdto.setTotalRecord(dao.getRecordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("gameshoptype", list1);
		return mapping.findForward("mallItemsList");
		
		}
		
		else if("updateState".equals(action)){
			DataSource ds = this.getDataSource(request,"QPTreasureDB");
			GameShopCateDAO dao = new GameShopCateDAO(ds);
			int radio = 0;
			if(request.getParameter("radio")!=null){
				radio = Integer.parseInt(request.getParameter("radio"));
			}
			String [] ids = request.getParameterValues("checkbox");
			for(int i=0;i<ids.length;i++){
				dao.updateState(ids[i],radio);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "操作成功!");
			return mapping.findForward("updateState");
			
		}
		
		else if("preAddMallItems".equals(action)){
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
			List<GameShopCateTypeDTO> list = dao.select();
			request.setAttribute("gsType", list);
			return mapping.findForward("preAddMall");
			
		}
		
		else if("addMallItems".equals(action)){
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			GameShopCateDAO dao = new GameShopCateDAO(ds);
			String mallName = request.getParameter("mallName");
			double price_gold = 0;
			double price_score = 0;
			double vipPrice = 0;
			if(request.getParameter("price_gold")!=null){
				price_gold = Double.parseDouble(request.getParameter("price_gold"));
			}
			if(request.getParameter("price_score")!=null){
				price_score = Double.parseDouble(request.getParameter("price_score"));
			}
			if(request.getParameter("vipPrice")!=null){
				vipPrice = Double.parseDouble(request.getParameter("vipPrice"));
			}
			int count = Integer.parseInt(request.getParameter("count"));
			String imagePath = "";
			if(request.getParameter("photourl")!=null){
				imagePath = request.getParameter("photourl");
			}
			System.out.println(">>>>>>>>>>>>>上传的图片为："+imagePath);
			int typeID = Integer.parseInt(request.getParameter("typeId"));
			String descript = "";
			if(request.getParameter("cateNote")!=null||request.getParameter("cateNote")!=""){
				descript = request.getParameter("cateNote");
			}
			else{
				descript = " ";
			}
			
			if(descript!=null){
					descript=descript.replaceAll("'", "&#39;");
					descript=descript.replaceAll("\"", "&quot;");
					descript=descript.replaceAll(" ", "&nbsp;");
			}
			int checkName = dao.checkMall(mallName);
			System.out.println("检测的结果："+checkName);
			if(checkName!=0){
				request.setAttribute("msg", "商品已存在!");
				return mapping.findForward("addfailure");
			}
			else{
			GameShopCateDTO dto = new GameShopCateDTO();
			dto.setMallName(mallName);
			dto.setPrice_gold(price_gold);
			dto.setPrice_score(price_score);
			dto.setVipPrice(vipPrice);
			dto.setDescript(descript);
			dto.setImagePath(imagePath);
			dto.setCount(count);
			dto.setTypeID(typeID);
			dao.add(dto);
			request.setAttribute("msg", "增加商品成功!");
			if(dto!=null){
				return mapping.findForward("addMallItems");
				}
			}
		}
		else if("preUpdate".equals(action)){
			int id = Integer.parseInt(request.getParameter("id"));
			GameShopCateDTO dto = new GameShopCateDTO();
			dto.setId(id);
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			GameShopCateDAO dao = new GameShopCateDAO(ds);
			GameShopCateDTO dto1 = dao.getById(id);
			request.setAttribute("gameshop", dto1);
			GameShopCateTypeDAO dao1 = new GameShopCateTypeDAO(ds);
			List<GameShopCateTypeDTO> list = dao1.select();
			request.setAttribute("typelist", list);
			return mapping.findForward("preUpdateMall");
		}
		
		else if("updateMall".equals(action)){
			String id = request.getParameter("id");
			String mallName = request.getParameter("mallName");
			double price_gold = 0;
			double price_score = 0;
			double vipPrice = 0;
			if(request.getParameter("price_gold")!=null){
				price_gold = Double.parseDouble(request.getParameter("price_gold"));
			}
			if(request.getParameter("price_score")!=null){
				price_score = Double.parseDouble(request.getParameter("price_score"));
			}
			if(request.getParameter("vipPrice")!=null){
				vipPrice = Double.parseDouble(request.getParameter("vipPrice"));
			}
			String imagePath = request.getParameter("photourl");
			int typeId = Integer.parseInt(request.getParameter("typeId"));
			String descript = request.getParameter("cateNote");
			int count = Integer.parseInt(request.getParameter("count"));
			System.out.println(">>>>>>>>>>>>>>"+descript);

			GameShopCateDTO dto = new GameShopCateDTO();
			dto.setId(Integer.parseInt(id));
			dto.setMallName(mallName);
			dto.setPrice_gold(price_gold);
			dto.setVipPrice(vipPrice);
			dto.setDescript(descript);
			dto.setImagePath(imagePath);
			dto.setTypeID(typeId);
			dto.setCount(count);

			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			GameShopCateDAO dao = new GameShopCateDAO(ds);
			dao.update(dto);
			request.setAttribute("msg", "修改成功!");
			return mapping.findForward("updateMall");
		}
		else if("deleteMall".equals(action)){
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			int  gameshopid =0;
			if(request.getParameter("id")!=null){
				gameshopid=Integer.parseInt(request.getParameter("id"));
			}
			GameShopCateDAO dao = new GameShopCateDAO(ds);
			if(gameshopid!=0){
				dao.DeleteGameshop(gameshopid);
				request.setAttribute("msg", "删除成功！");
			}
			else{
				request.setAttribute("msg", "删除失败，ID不存在！");
			}
			return mapping.findForward("deleteMall");
		}
		
		else if("mallDetails".equals(action)){
			int id = Integer.parseInt(request.getParameter("id"));
			GameShopCateDTO dto = new GameShopCateDTO();
			dto.setId(id);
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			GameShopCateDAO dao = new GameShopCateDAO(ds);
			GameShopCateDTO adto = dao.getDetail(id);
			
			request.setAttribute("adto", adto);
			return mapping.findForward("mallDetails");
		}
		
		else if("mallType".equals(action)){
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			int parentsID = 0;
			if(request.getParameter("parentsID")!=null){
				parentsID = Integer.parseInt(request.getParameter("parentsID"));
			}
			GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
			List<GameShopCateTypeDTO> list = dao.select(parentsID);
			request.setAttribute("list", list);
			return mapping.findForward("mallType");
		}
		
		else if("preUpdateMallType".equals(action)){
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			int typeID = 0;
			if(request.getParameter("typeID")!=null){
				typeID = Integer.parseInt(request.getParameter("typeID"));
			}
			GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
			GameShopCateTypeDTO dto = dao.getById(typeID);
			request.setAttribute("dto", dto);
			return mapping.findForward("preupdatemallType");
		}
		
		else if("updateMallType".equals(action)){
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			int typeID = 0;
			if(request.getParameter("typeID")!=null){
				typeID = Integer.parseInt(request.getParameter("typeID"));
			}
			String typeName = "";
			if(request.getParameter("typeName")!=null){
				typeName = request.getParameter("typeName");
			}
			
			GameShopCateTypeDAO dao = new GameShopCateTypeDAO(ds);
			dao.updateTypeName(typeID,typeName);
			request.setAttribute("msg", "更新成功!");
			return mapping.findForward("updatemallType");
		}
		
		return null;
	}

}

package com.suncity.struts.betlimit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.OperateDTO;
import com.doowal.hk798.admin.OperateLogsDAO;
import com.doowal.staworld.advertisement.PageDTO;


public class GameUserLimitAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "SunCityManage");
		
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
		if("betLimitList".equals(action)){
		
		UserLimitDAO dao = new UserLimitDAO(ds);
		List<UserLimitDTO> list = null;
		int curPage = 1;
		String curPagestr = request.getParameter("curPage");
		if (curPagestr == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(curPagestr);
		}
		if (curPage < 1)
			curPage = 1;
		int pageSize=30;
		
		if(request.getParameter("pageSize")!=null){
			try {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String orderby="id";
		String where = "";
		if(request.getParameter("orderby")!=null){
			orderby=request.getParameter("orderby");
		}


		list = dao.GetRecordByPage(curPage, pageSize,orderby,where);
		
		if(list.size()!=0){
		request.setAttribute("username", request.getSession().getAttribute("username"));
		request.setAttribute("betLimitlist", list);
		PageDTO pdto = new PageDTO();
		if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
		pdto.setCurPage(curPage);
		pdto.setTotalPage(dao.getTotalPage());
		pdto.setTotalRecord(dao.getRcordCount());
		pdto.setPageSize(pageSize);
		request.setAttribute("page", pdto);
		request.setAttribute("pageIndex", curPage);
		request.setAttribute("pageSize", pageSize);
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入查看用户限红管理列表";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		
		return mapping.findForward("betLimitList");
		
		}
		else{
			request.setAttribute("returnInfo", dao.returnInfo());
			return mapping.findForward("betLimitList");
			}
		}
		
		else if("preAdd".equals(action)){
			UserLimitDAO dao = new UserLimitDAO(ds);
			
			List<UserLimitDTO> list = dao.select();
			request.setAttribute("limitType", list);
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入预添加限红界面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			return mapping.findForward("preAdd");
		}
		
		else if("addLimit".equals(action)){
			UserLimitDAO dao = new UserLimitDAO(ds);
			UserLimitDTO dto = new UserLimitDTO();
			int limit_Up = Integer.parseInt(request.getParameter("limit_Up"));
			int limit_Down = Integer.parseInt(request.getParameter("limit_Down"));
			int chip_1 = Integer.parseInt(request.getParameter("chipID1"));
			int chip_2 = Integer.parseInt(request.getParameter("chipID2"));
			
			int chip_3 = Integer.parseInt(request.getParameter("chipID3"));
			int chip_4 = Integer.parseInt(request.getParameter("chipID4"));
			int chip_5 = Integer.parseInt(request.getParameter("chipID5"));
			
			dto.setLimit_Up(limit_Up);
			dto.setLimit_Down(limit_Down);
			dto.setChip_1(chip_1);
			dto.setChip_2(chip_2);
			dto.setChip_3(chip_3);
			dto.setChip_4(chip_4);
			dto.setChip_5(chip_5);
			String msg = dao.add(dto);
			request.setAttribute("msg", msg);
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"添加限红";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			return mapping.findForward("addLimit");
		}
		
		else if("preUpdate".equals(action)){
			UserLimitDAO dao = new UserLimitDAO(ds);
			
			int id = Integer.parseInt(request.getParameter("id"));
			UserLimitDTO dto = dao.getById(id);
			request.setAttribute("limitDTO", dto);
			
			List<UserLimitDTO> list = dao.select();
			request.setAttribute("limitType", list);
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入预修改限红界面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			return mapping.findForward("preUpdate");
		}
		
		else if("updateLimit".equals(action)){
			UserLimitDAO dao = new UserLimitDAO(ds);
			UserLimitDTO dto = new UserLimitDTO();
			int id = Integer.parseInt(request.getParameter("id"));
			int limit_Up = Integer.parseInt(request.getParameter("limit_Up"));
			int limit_Down = Integer.parseInt(request.getParameter("limit_Down"));
			int chip_1 = Integer.parseInt(request.getParameter("chipID1"));
			int chip_2 = Integer.parseInt(request.getParameter("chipID2"));
			
			int chip_3 = Integer.parseInt(request.getParameter("chipID3"));
			int chip_4 = Integer.parseInt(request.getParameter("chipID4"));
			int chip_5 = Integer.parseInt(request.getParameter("chipID5"));
			
			dto.setLimit_Up(limit_Up);
			dto.setId(id);
			dto.setLimit_Down(limit_Down);
			dto.setChip_1(chip_1);
			dto.setChip_2(chip_2);
			dto.setChip_3(chip_3);
			dto.setChip_4(chip_4);
			dto.setChip_5(chip_5);
			System.out.println("取得id："+id+"上限:"+limit_Up+"下限:"+limit_Down+"筹码1:"+chip_1+"筹码2:"+chip_2+"筹码3:"+chip_3+"筹码4:"+chip_4+"筹码5:"+chip_5);
			String msg = dao.update(dto);
			request.setAttribute("msg", msg);
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			
			/*           */
			return mapping.findForward("updateLimit");
		}
		
		
		return mapping.findForward("index");
	}

}

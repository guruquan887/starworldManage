package com.wiiy.struts;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.OperateDTO;
import com.doowal.hk798.admin.OperateLogsDAO;


public class UpdateNetCSAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "";
		int id = Integer.parseInt(request.getParameter("id"));
		String szAdvertise = request.getParameter("szAdvertise");
		String szAdvertisePh = request.getParameter("szAdvertisePh");
		String szAdvertiseDl = request.getParameter("szAdvertiseDl");
		String szAdvertiseGl = request.getParameter("szAdvertiseGl");
		
		NetCSDTO dto = new NetCSDTO();
		
		dto.setId(id);
		dto.setSzAdvertise(szAdvertise);
		dto.setSzAdvertisePh(szAdvertisePh);
		dto.setSzAdvertiseDl(szAdvertiseDl);
		dto.setSzAdvertiseGl(szAdvertiseGl);
		DataSource ds = this.getDataSource(request, "QPTreasureDB");
		NetCSDAO dao = new NetCSDAO(ds);
		if(dao.update(dto)){
			
			request.setAttribute("msg", "参数修改成功!");
			/* 写入日志  */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"修改公告，信息为：游戏公告消息="+szAdvertise+";游戏代理公告="+szAdvertiseDl+";游戏管理公告="+szAdvertiseGl+";游戏排行="+szAdvertisePh;
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*        */
		}
		else{
			request.setAttribute("msg", "数据库错误，修改失败�");
		}
		request.setAttribute("dto", dto);
		forward = "success";
		return mapping.findForward(forward);
	}

}

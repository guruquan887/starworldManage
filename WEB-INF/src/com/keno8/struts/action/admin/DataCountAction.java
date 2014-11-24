package com.keno8.struts.action.admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.keno8.struts.dao.DataCountDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.DataCountDTO;
import com.keno8.struts.dto.OperateDTO;

public class DataCountAction extends DispatchAction{

	
	public ActionForward usercount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			 {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
			try {
				DataSource ds = this.getDataSource(request, "Keno8");
				DataCountDAO dao = new DataCountDAO(ds);
				DataCountDTO dto1 = new DataCountDTO();
				DataCountDTO dto = dao.countDate(dto1);
				request.setAttribute("countuser", dto);
				
				/* 写入日志记录 */
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				OperateDTO operateDto = new OperateDTO();
				String operateDetails = "进入信息统计页面";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				
				/*           */
			} catch (Exception e) {
				e.printStackTrace();
			}
		return mapping.findForward("countuser");
	}

}

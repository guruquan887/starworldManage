package com.keno8.struts.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.NetCSDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.NetCSDTO;
import com.keno8.struts.dto.OperateDTO;

public class PreUpdateNetCSAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		int id = 1;
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "Keno8");
		NetCSDAO dao = new NetCSDAO(ds);
		NetCSDTO dto = dao.getById(id);
		request.setAttribute("dto", dto);
		
		/* 写入日志  */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入修改系统综合设置页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*        */
		forward = "success";
		
		return mapping.findForward(forward);
	}

}

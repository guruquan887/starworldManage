package com.keno8.struts.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.GradeCSDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.GradeCSDTO;
import com.keno8.struts.dto.OperateDTO;

public class PreUpdateGradeCSAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		
		DataSource ds = this.getDataSource(request, "Keno8");
		GradeCSDAO dao = new GradeCSDAO(ds);
		List<GradeCSDTO> list = dao.getAll();
		request.setAttribute("dto", list);
		
		/* 写入日志  */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"进入修改会员等级参数页面";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		/*        */
		return mapping.findForward("success");
	}

}

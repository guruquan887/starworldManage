package com.keno8.struts.action.admin;


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

public class UpdateGradeCSAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		String forward = "";
		int grade = 0;
		if(request.getParameter("grade")!=null){
			grade = Integer.parseInt(request.getParameter("grade"));
		}
		long gradeExp = Long.parseLong(request.getParameter("gradeExp"));
		double rebateLV = Double.parseDouble(request.getParameter("rebateLV"));
		GradeCSDTO dto = new GradeCSDTO();
		dto.setGrade(grade);
		dto.setGradeExp(gradeExp);
		dto.setRebateLV(rebateLV);
		DataSource ds = this.getDataSource(request, "Keno8");
		GradeCSDAO dao = new GradeCSDAO(ds);
		if(dao.update(dto)){
			request.setAttribute("msg", "参数修改成功!");
			
			/* 写入日志  */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"修改等级为："+grade+",等级经验为："+gradeExp+",反水利率为："+rebateLV;
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

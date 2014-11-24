package com.keno8.struts.action.gameUser;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.dao.GameUserDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.OperateDTO;

public class UpdateGameUserYeepayAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		String forward = "failure";
		String dh = request.getParameter("dh");
		float r3_Amt = Float.parseFloat(request.getParameter("r3_Amt"));
		DataSource ds = this.getDataSource(request, "Keno8");
		GameUserDAO dao = new GameUserDAO(ds);
		dao.updateyeepay(dh,r3_Amt);
		request.setAttribute("msg", "手动充值成功!");
		
		/* 写入日志记录 */
		String username = (String)request.getSession().getAttribute("username");
		String ip = (String)request.getSession().getAttribute("ip");
		OperateLogsDAO operateDao = new OperateLogsDAO(ds);
		OperateDTO operateDto = new OperateDTO();
		String operateDetails = username+"对订单号为<font color='red'>"+dh+"</font>金额为<font color='red'>"+r3_Amt+"</font>进行了手动充值";
		operateDto.setOperateName(username);
		operateDto.setOperateDetails(operateDetails);
		operateDto.setOperateIP(ip);
		operateDao.addLogs(operateDto);
		
		/*           */
		forward = "success";
		return mapping.findForward(forward);
	}
}

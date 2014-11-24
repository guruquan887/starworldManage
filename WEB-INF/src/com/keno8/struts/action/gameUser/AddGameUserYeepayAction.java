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

public class AddGameUserYeepayAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		
		DataSource ds = this.getDataSource(request, "Keno8");
		GameUserDAO dao = new GameUserDAO(ds);
		String dh = request.getParameter("dh");
		String accounts = request.getParameter("accounts");
		float r3_Amt = Float.parseFloat(request.getParameter("r3_Amt"));
		if(dao.checkExist(accounts)){
			int userid = dao.queryUserID(accounts);
			int r = dao.add(dh,userid,r3_Amt);
			if(r==1){
				request.setAttribute("msg", "恭喜，订单增加成功!");
				
				/* 写入日志记录 */
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				OperateDTO operateDto = new OperateDTO();
				String operateDetails = username+"增加订单号为<font color='red'>"+dh+"</font>用户<font color='red'>"+accounts+"</font>增加银子<font color='red'>"+r3_Amt+"</font>";
				operateDto.setOperateName(username);
				operateDto.setOperateDetails(operateDetails);
				operateDto.setOperateIP(ip);
				operateDao.addLogs(operateDto);
				
				/*           */
			}
			else{
				request.setAttribute("msg", "对不起，订单增加失败!");
			}
		}
		else{
			request.setAttribute("msg", "用户不存在，请查证!");
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
}

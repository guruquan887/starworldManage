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
		String title = request.getParameter("title");
		String url = request.getParameter("url");
		long yeepayRate = Long.parseLong(request.getParameter("yeepayRate"));
		long regGold = Long.parseLong(request.getParameter("regGold"));
		long regScore = Long.parseLong(request.getParameter("regScore"));
		float gameTax = Float.parseFloat(request.getParameter("gameTax"));
		String adminEmail = request.getParameter("adminEmail");
		String linkMan = request.getParameter("linkMan");
		String recordInfo = request.getParameter("recordInfo");
		double beishu = Double.parseDouble(request.getParameter("beishu"));
		double fanben = Double.parseDouble(request.getParameter("fanben"));
		
		NetCSDTO dto = new NetCSDTO();
		dto.setTitle(title);
		dto.setUrl(url);
		dto.setYeepayRate(yeepayRate);
		dto.setRegGold(regGold);
		dto.setRegScore(regScore);
		dto.setGameTax(gameTax);
		dto.setAdminEmail(adminEmail);
		dto.setLinkMan(linkMan);
		dto.setRecordInfo(recordInfo);
		dto.setBeishu(beishu);
		dto.setFanben(fanben);
		dto.setId(id);
		DataSource ds = this.getDataSource(request, "Keno8");
		NetCSDAO dao = new NetCSDAO(ds);
		if(dao.update(dto)){
			request.setAttribute("msg", "参数修改成功!");
			
			/* 写入日志  */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"修改系统参数，信息为：网站名称="+title+";网站地址="+url+";新用户注册="+regGold+"银子，新用户注册="+regScore+"积分;网银充值设定="+yeepayRate+";上线充值提成="+gameTax+";管理员邮箱="+adminEmail+";联系人="+linkMan+";ICP 备案信息="+recordInfo;
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

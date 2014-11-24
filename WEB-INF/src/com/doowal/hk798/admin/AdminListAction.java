package com.doowal.hk798.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.gameUser.EncryptionMD5;
import com.doowal.hk798.login.AdminDAO;
import com.doowal.hk798.login.AdminDTO;

public class AdminListAction extends Action {
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		DataSource ds = this.getDataSource(request, "QPPlatformManagerDB");
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		if("list".equals(action)){
			AdminDAO dao = new AdminDAO(ds);
			int roleId = (Integer)request.getSession().getAttribute("roleId");
			int id = (Integer)request.getSession().getAttribute("Id");
			List list = dao.findAll(roleId,id);
			request.setAttribute("admin", list);
			return mapping.findForward("list");
		}
		
		if("preUpdate".equals(action)){
			
			HttpSession s=request.getSession();
			int id = Integer.parseInt(request.getParameter("id"));
			AdminDAO dao = new AdminDAO(ds);
			AdminDTO admin = dao.getById(id);
			request.setAttribute("ADMIN", admin);
			int roleId = (Integer)request.getSession().getAttribute("roleId");
			RoleDAO dao1 = new RoleDAO(ds);
			List<RoleDTO> rol=dao1.select(roleId);
			request.setAttribute("ROL", rol);
			
			request.getSession().setAttribute("updateName", admin.getUserName());
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = "进入修改"+admin.getUserName()+"基本信息页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			return mapping.findForward("preUpdate");
		}
		
		if("update".equals(action)){
			
			AdminDAO dao = new AdminDAO(ds);
			String id = request.getParameter("id"); 
			String password=request.getParameter("password");
			
			AdminDTO dto = new AdminDTO();
			dto.setId(Integer.parseInt(id));
			dto.setPassword(password);
			if(dao.update(dto)){
				request.setAttribute("msg", "恭喜，修改成功!,请重新登录!使用新的密码!");
			}
			
			/* 写入日志记录 */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = "修改密码为："+password;
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			return mapping.findForward("update");
		}
		
		if("preAdd".equals(action)){
			int roleId = (Integer)request.getSession().getAttribute("roleId");
			RoleDAO dao = new RoleDAO(ds);
			List<RoleDTO> rol=dao.select(roleId);
			request.setAttribute("ROL", rol);
			return mapping.findForward("preAdd");
		}
		
		if("add".equals(action)){
			String userName=request.getParameter("username");
			String password=request.getParameter("password");
			String roleId=request.getParameter("roleId");
			HttpSession session=request.getSession();
			AdminDTO admin=(AdminDTO)session.getAttribute("USER");
			if(admin==null){
				return mapping.findForward("index");
			}
			AdminDTO dto = new AdminDTO();
			dto.setUserName(userName);
			dto.setPassword(password);
			dto.setRoleId(Integer.parseInt(roleId));
			AdminDAO dao = new AdminDAO(ds);
			if(dao.checkExist(userName)){
				request.setAttribute("msg", "添加失败，用户名已经存在！");
			}
			else{
				dao.add(dto);
				AdminDTO at=dao.checkUser(userName, EncryptionMD5.encryption_MD5(password));
				int t_userid=at.getId();
				int t_roleId=at.getRoleId();
				RoleDAO rd=new RoleDAO(ds);
				rd.copyuthority(t_userid, t_roleId);
				request.setAttribute("msg", "恭喜，添加成功!");
			}
			return mapping.findForward("add");
		}
		
		if("del".equals(action)){
			AdminDAO dao = new AdminDAO(ds);
		    int id = Integer.parseInt(request.getParameter("id"));
			if(id!=0){
				dao.delete(id);
				request.setAttribute("msg", "恭喜，删除成功!");
			}
			return mapping.findForward("del");
		}
		
		if("logList".equals(action)){
			String operateName = "";
			if(request.getParameter("operateName")!=null){
				operateName = request.getParameter("operateName");
			}
			OperateLogsDAO dao = new OperateLogsDAO(ds);
			int curPage = 1;
			String where = "";
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=100;
			
			if(request.getParameter("pageSize")!=null){
				try {
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String orderby="operateTime";
			if(request.getParameter("orderby")!=null){
				orderby=request.getParameter("orderby");
			}
			if(operateName!=""){
				where =  "operateName = '"+operateName+"'";
			}
			
			List<OperateDTO> list = dao.GetRecordByPage(curPage,pageSize,where,orderby);
			if(list.size()!=0){
				request.setAttribute("operatelist", list);
				PageDTO pdto = new PageDTO();
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(dao.getTotalPage());
				pdto.setTotalRecord(dao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("operateName", operateName);
				}
				else{
					request.setAttribute("returnInfo", dao.returnInfo());
				}
			return mapping.findForward("logList");
		}
		
		if("delLog".equals(action)){
			String operateName = "";
			if(request.getParameter("operateName")!=null){
				operateName = request.getParameter("operateName");
			}
			OperateLogsDAO dao = new OperateLogsDAO(ds);
			if(dao.delete(operateName)==1){
				request.setAttribute("msg","删除成功!");
			}
			else{
				request.setAttribute("msg", "删除失败!");
			}
			return mapping.findForward("delLog");
			
		}
		
		if("preBundlingIP".equals(action)){
			AdminDAO dao = new AdminDAO(ds);
			String userID = "";
			userID = String.valueOf(request.getSession().getAttribute("Id"));
			String username = "";
			username = String.valueOf(request.getSession().getAttribute("username"));
			String ip = "";
			ip = request.getRemoteAddr();
			request.setAttribute("username", username);
			
			if(dao.getbangdingIP(userID).equals("")){
				request.setAttribute("ip", ip);
				
			}
			else{
				request.setAttribute("ip", dao.getbangdingIP(userID));
			}
			
			return mapping.findForward("preBundlingIP");
			
		}
		
		if("bundlingIP".equals(action)){
			String userID = "";
			userID = String.valueOf(request.getSession().getAttribute("Id"));
			String ip = "";
			ip = request.getParameter("ip");
			AdminDAO dao = new AdminDAO(ds);
			String msg = dao.bangdingIP(userID,ip);
			request.setAttribute("msg", msg);
			return mapping.findForward("bundlingIP");
			
		}
		
		if("qxbundlingIP".equals(action)){
			String userID = "";
			userID = String.valueOf(request.getSession().getAttribute("Id"));
			AdminDAO dao = new AdminDAO(ds);
			String msg = dao.qxbangdingIP(userID);
			request.setAttribute("msg", msg);
			return mapping.findForward("qxbundlingIP");
			
		}
		
		if("preBundlingMachine".equals(action)){
			AdminDAO dao = new AdminDAO(ds);
			String userID = "";
			userID = String.valueOf(request.getSession().getAttribute("Id"));
			String username = "";
			username = String.valueOf(request.getSession().getAttribute("username"));
			String machine = "";
			machine = dao.getMacAddr();
			request.setAttribute("username", username);
			
			if(dao.getbangdingMachine(userID).equals("")){
				request.setAttribute("machine", machine);
				
			}
			else{
				request.setAttribute("machine", dao.getbangdingMachine(userID));
			}
			
			return mapping.findForward("preBundlingMachine");
			
		}
		
		if("bundlingMachine".equals(action)){
			String userID = "";
			userID = String.valueOf(request.getSession().getAttribute("Id"));
			String machine = "";
			machine = request.getParameter("machine");
			AdminDAO dao = new AdminDAO(ds);
			String msg = dao.bangdingMachine(userID,machine);
			request.setAttribute("msg", msg);
			return mapping.findForward("bundlingMachine");
			
		}
		
		if("qxbundlingMachine".equals(action)){
			String userID = "";
			userID = String.valueOf(request.getSession().getAttribute("Id"));
			AdminDAO dao = new AdminDAO(ds);
			String msg = dao.qxbangdingMachine(userID);
			request.setAttribute("msg", msg);
			return mapping.findForward("qxbundlingMachine");
			
		}
		
		if("bundlingManMachine".equals(action)){
			String userID = "";
			userID = String.valueOf(request.getParameter("id"));
			String machine = "";
			AdminDAO dao = new AdminDAO(ds);
			machine = dao.getMacAddr();
			System.out.println("machine:"+machine);
			String msg = dao.bangdingMachine(userID,machine);
			request.setAttribute("msg", msg);
			return mapping.findForward("bundlingManMachine");
			
		}
		
		if("qxbundlingManMachine".equals(action)){
			String userID = "";
			userID = String.valueOf(request.getParameter("id"));
			AdminDAO dao = new AdminDAO(ds);
			String msg = dao.qxbangdingMachine(userID);
			request.setAttribute("msg", msg);
			return mapping.findForward("qxbundlingManMachine");
			
		}
		return null;
	}
}

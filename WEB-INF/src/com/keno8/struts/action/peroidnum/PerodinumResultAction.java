package com.keno8.struts.action.peroidnum;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.keno8.struts.action.handicap.HandicapDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.PageDTO;

public class PerodinumResultAction extends Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getSession().getAttribute("USER")==null){
			request.setAttribute("msg", "登录已超时,请重新登录!");
			return mapping.findForward("index");
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		String action = "";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		if("resultList".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			PerodinumResultDAO dao = new PerodinumResultDAO(ds);
			
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
			String where = "roomId = 1";
			int roomId = 1;
			int retroType = 1;
			String pperoidnum = "";
			String orderBy = "peroidnum";
			if(request.getParameter("roomId")!=null){
				roomId = Integer.parseInt(request.getParameter("roomId"));
				if(roomId==21){
					orderBy = "createTime";
					//System.out.println("澳洲游戏排序");
				}
				else{
					orderBy = "peroidnum";
					//System.out.println("其他游戏排序");
				}
					where = "roomId = "+roomId;
			}
			if(request.getParameter("pperoidnum")!=null&&request.getParameter("pperoidnum")!=""){
				pperoidnum = request.getParameter("pperoidnum");
				where = where + " and peroidnum ='"+pperoidnum+"'";
			}
			String peroidnum = "";
			if(request.getParameter("peroidnum")!=null){
				peroidnum = request.getParameter("peroidnum");
				System.out.println("选择的期号为："+peroidnum);
				request.setAttribute("peroidnum", peroidnum);
				System.out.println("选择的状态为："+retroType);
				request.setAttribute("retroType", retroType);
			}
			List<PeroidnumResultDTO> list = dao.GetRecordByPage(curPage,pageSize,where,orderBy);
			List<PeroidnumResultDTO> list1 = dao.queryRoomID();
			request.setAttribute("preodinumList", list);
			request.setAttribute("roomType", list1);
			int totalPage = dao.getTotalPage();
			request.setAttribute("rooms", list);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRecordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			
			/* 写入日志  */
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"进入号码补录页面";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*        */
			
			return mapping.findForward("resultList");
		}
		
		else if("addResult".equals(action)){
			
			DataSource ds = this.getDataSource(request, "Keno8");
			String num = "";
			int r = 1;
			String awardnum = "";
			String totalNum = "";
			String[] result = new String[20];
			for(int i=0;i < result.length; i++){
				num = request.getParameter("num"+r);
				awardnum +=  num + "," ;  //存入数据库的值
				totalNum +=  num;    //开奖号码的总和
				r++;
				result[i] = num;   //取出数组的数据
			}
			System.out.println(result[0]+","+result[1]+","+result[2]+","+result[3]+"总和值为："+totalNum+"存入的值为："+awardnum.subSequence(0, awardnum.lastIndexOf(",")));
			int roomId = Integer.parseInt(request.getParameter("roomId"));
			awardnum = awardnum.substring(0, awardnum.lastIndexOf(","));
			System.out.println("房间号为:"+roomId);
			String peroidnum = request.getParameter("peroidnum");
			System.out.println("期号为:"+peroidnum);
			PerodinumResultDAO dao = new PerodinumResultDAO(ds);
			String msg = dao.addResult(awardnum,result, peroidnum, roomId);
			
			/* 写入日志  */
			HandicapDAO dao11 = new HandicapDAO(ds);
			String roomName = dao11.queryRoomName(roomId);
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"增加<font color='red'>"+roomName+"</font>游戏,期数为：<font color='red'>"+peroidnum+"</font>补录号码为：<font color='red'>"+awardnum+"</font>";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*        */
			
			request.setAttribute("msg", msg);
			return mapping.findForward("addResult");
		}
		
		else if("updateResult".equals(action)){
			
			DataSource ds = this.getDataSource(request, "Keno8");
			String num = "";
			int r = 1;
			String awardnum = "";
			String totalNum = "";
			String[] result = new String[20];
			for(int i=0;i < result.length; i++){
				num = request.getParameter("_nnum"+r);
				awardnum +=  num + "," ;  //存入数据库的值
				totalNum +=  num;    //开奖号码的总和
				r++;
				result[i] = num;   //取出数组的数据
			}
			int roomId = Integer.parseInt(request.getParameter("roomId"));
			awardnum = awardnum.substring(0, awardnum.lastIndexOf(","));
			System.out.println("房间号为:"+roomId);
			String peroidnum = request.getParameter("peroidnum1");
			System.out.println("期号为:"+peroidnum);
			PerodinumResultDAO dao = new PerodinumResultDAO(ds);
			String msg = dao.updateResult(awardnum,result, peroidnum, roomId);
			
			/* 写入日志  */
			HandicapDAO dao11 = new HandicapDAO(ds);
			String roomName = dao11.queryRoomName(roomId);
			String username = (String)request.getSession().getAttribute("username");
			String ip = (String)request.getSession().getAttribute("ip");
			OperateLogsDAO operateDao = new OperateLogsDAO(ds);
			OperateDTO operateDto = new OperateDTO();
			String operateDetails = username+"修改<font color='red'>"+roomName+"</font>游戏,期数为：<font color='red'>"+peroidnum+"</font>修改号码为：<font color='red'>"+awardnum+"</font>";
			operateDto.setOperateName(username);
			operateDto.setOperateDetails(operateDetails);
			operateDto.setOperateIP(ip);
			operateDao.addLogs(operateDto);
			/*        */
			request.setAttribute("msg", msg);
			return mapping.findForward("updateResult");
		}
		
		else if("jiesuanResult".equals(action)){
			
			DataSource ds = this.getDataSource(request, "Keno8");
			//Connection con = ds.getConnection();
			PerodinumResultDAO dao = new PerodinumResultDAO(ds);
			int roomId = 0;
			String peroidnum = "";
			if(request.getParameter("roomId")!=null){
				roomId = Integer.parseInt(request.getParameter("roomId"));
			}
			if(request.getParameter("peroidnum")!=null){
				peroidnum = request.getParameter("peroidnum");
			}
			String result = dao.queryAwardnum(ds, roomId, peroidnum);
			if("?".equals(result)){
				request.setAttribute("msg", "请先补录此期数!");
			}
			else{
				String username = (String)request.getSession().getAttribute("username");
				String ip = (String)request.getSession().getAttribute("ip");
				dao.systemGetResults(ds,roomId, peroidnum,username,ip);
				/* 写入日志  */
				//HandicapDAO dao11 = new HandicapDAO(ds);
				//String roomName = dao11.queryRoomName(roomId);
				//OperateLogsDAO operateDao = new OperateLogsDAO(ds);
				//OperateDTO operateDto = new OperateDTO();
				//String operateDetails = username+"结算<font color='red'>"+roomName+"</font>游戏,期数为：<font color='red'>"+peroidnum+"</font>";
				//operateDto.setOperateName(username);
				//operateDto.setOperateDetails(operateDetails);
				//operateDto.setOperateIP(ip);
				//operateDao.addLogs(operateDto);
				/*        */
				request.setAttribute("msg", "恭喜，结算成功!");
				//con.close();
			}
			return mapping.findForward("jiesuanResult");
		}
		
		else if("result3DList".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			PerodinumResultDAO dao = new PerodinumResultDAO(ds);
			
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
			String where = "retroType =1 and roomId = 16";
			String where1 = "";
			int roomId = 1;
			int retroType = 1;
			if(request.getParameter("roomId")!=null){
				roomId = Integer.parseInt(request.getParameter("roomId"));
					where1 = "roomId = "+roomId;
			if(request.getParameter("retroType")!=null){
				retroType = Integer.parseInt(request.getParameter("retroType"));
					where = where1 + " and retroType = "+retroType;
					}
			if(request.getParameter("retroType1")!=null){
				retroType = Integer.parseInt(request.getParameter("retroType1"));
				where = where1 + " and retroType = "+retroType;
					}
			}
			String peroidnum = "";
			if(request.getParameter("peroidnum")!=null){
				peroidnum = request.getParameter("peroidnum");
				System.out.println("选择的期号为："+peroidnum);
				request.setAttribute("peroidnum", peroidnum);
				request.setAttribute("retroType", retroType);
				//request.setAttribute("msg", "操作成功!");
			}
			
			List<PeroidnumResultDTO> list = dao.Get3DShiShiLeRecordByPage(curPage,pageSize,where);
			List<PeroidnumResultDTO> list1 = dao.query3DRoomID();
			request.setAttribute("preodinumList", list);
			request.setAttribute("roomType", list1);
			int totalPage = dao.getTotalPage();
			request.setAttribute("rooms", list);
			if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
			PageDTO pdto = new PageDTO();
			pdto.setCurPage(curPage);
			pdto.setTotalPage(totalPage);
			pdto.setTotalRecord(dao.getRecordCount());
			pdto.setPageSize(pageSize);
			request.setAttribute("page", pdto);
			request.setAttribute("pageIndex", curPage);
			request.setAttribute("pageSize", pageSize);
			return mapping.findForward("result3DList");
		}
		
		else if("add3DResult".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			String num = "";
			int r = 1;
			String awardnum = "";
			String totalNum = "";
			String[] result = new String[3];
			for(int i=0;i < result.length; i++){
				num = request.getParameter("num"+r);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>"+num);
				awardnum +=  num + "," ;  //存入数据库的值
				totalNum +=  num;    //开奖号码的总和
				r++;
				result[i] = num;   //取出数组的数据
			}
			int roomId = Integer.parseInt(request.getParameter("roomId"));
			awardnum = awardnum.substring(0, awardnum.lastIndexOf(","));
			System.out.println("房间号为:"+roomId);
			String peroidnum = request.getParameter("peroidnum");
			System.out.println("期号为:"+peroidnum);
			PerodinumResultDAO dao = new PerodinumResultDAO(ds);
			String msg = dao.add3DResult(awardnum,result, peroidnum, roomId);
			request.setAttribute("msg", msg);
			return mapping.findForward("add3DResult");
		}
		
		
	    else if("jiesuan3DShiShiLeResult".equals(action)){
			DataSource ds = this.getDataSource(request, "Keno8");
			PerodinumResultDAO dao = new PerodinumResultDAO(ds);
			int roomId = 0;
			String peroidnum = "";
			if(request.getParameter("roomId")!=null){
				roomId = Integer.parseInt(request.getParameter("roomId"));
			}
			if(request.getParameter("peroidnum")!=null){
				peroidnum = request.getParameter("peroidnum");
			}
			String result = dao.queryAwardnum(ds, roomId, peroidnum);
			System.out.println("打印的3D福彩结果："+result);
			if("?".equals(result)){
				request.setAttribute("msg1", "请先补录此期数!");
			}
			else{
				dao.systemGamePickRecordResult(ds,roomId, peroidnum);
				request.setAttribute("msg1", "恭喜，结算成功!");
			}
			return mapping.findForward("jiesuan3DShiShiLeResult");
		}
		
		return mapping.findForward(null);
	}
	
	
}

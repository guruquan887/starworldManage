package com.doowal.hk798.card;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.doowal.hk798.admin.PageDTO;
import com.doowal.hk798.gameUser.EncryptionMD5;

public class CardAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String action = "";
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
		if(request.getParameter("action")!=null){
			action=request.getParameter("action");
		}
		DataSource ds = this.getDataSource(request, "QPTreasureDB");
		CardDAO c=new CardDAO(ds);
		if("preCreate".equals(action)){
			List<CardViewDTO> list = new ArrayList<CardViewDTO>();
			list = c.getCardType();
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			Date d=Calendar.getInstance().getTime();
			request.setAttribute("cardGold", list.get(0).getCardGold());
			request.setAttribute("memberDays", list.get(0).getMemberDays());
			request.setAttribute("cardPrice", list.get(0).getCardPrice());
			request.setAttribute("date", f.format(d));
			request.setAttribute("AdminName", request.getSession().getAttribute("username"));
			request.setAttribute("listtype", list);
			return mapping.findForward("preCreate");
		}
		
		if("create".equals(action)){
			String in_CardTypeID = request.getParameter("in_CardTypeID").trim();
			String in_CardPrice = "";
			if(request.getParameter("in_CardPrice")!=null){
				in_CardPrice = request.getParameter("in_CardPrice");
			}
			String in_Count = "";
			if(request.getParameter("in_Count")!=null){
				in_Count = request.getParameter("in_Count");
			}
			String in_CardGold = "";
			if(request.getParameter("in_CardGold")!=null){
				in_CardGold = request.getParameter("in_CardGold");
			}
			String in_MemberDays = "";
			if(request.getParameter("in_MemberDays")!=null){
				in_MemberDays = request.getParameter("in_MemberDays");
			}
			String in_UseRange = "";
			if(request.getParameter("in_UseRange")!=null){
				in_UseRange = request.getParameter("in_UseRange");
			}
			String in_SalesPerson = "";
			if(request.getParameter("in_SalesPerson")!=null){
				in_SalesPerson = request.getParameter("in_SalesPerson");
			}
			String in_ValidDate = "";
			if(request.getParameter("in_ValidDate")!=null){
				in_ValidDate = request.getParameter("in_ValidDate");
			}
			String in_NoteInfo = "";
			if(request.getParameter("in_NoteInfo")!=null){
				in_NoteInfo = request.getParameter("in_NoteInfo");
			}
			String in_CardPrefix = "";
			if(request.getParameter("in_CardPrefix")!=null){
				in_CardPrefix = request.getParameter("in_CardPrefix");
			}
			String in_CardLength = "";
			if(request.getParameter("in_CardLength")!=null){
				in_CardLength = request.getParameter("in_CardLength");
			}
/*			String in_PasswdType = "";
			if(request.getParameter("in_PasswdType")!=null){
				in_PasswdType = request.getParameter("in_PasswdType");
			}*/
			String [] ids = request.getParameterValues("in_PasswdType");
			int in_PasswdType = 0;
			for(int i=0;i<ids.length;i++){
				in_PasswdType = in_PasswdType | Integer.parseInt(ids[i]);
			}
			System.out.println("in_PasswdType:"+in_PasswdType);
			
			String in_PwdLength = "";
			if(request.getParameter("in_PwdLength")!=null){
				in_PwdLength = request.getParameter("in_PwdLength");
			}
			String datePrefix = "";
			String realPrefix = "";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
			String [] ss = in_CardTypeID.split(",");
			Date today = Calendar.getInstance().getTime();
			String currenttime = sdf.format(today);
			datePrefix = currenttime + ss[0];
			System.out.println("datePrefix:"+datePrefix);
			if(in_CardPrefix==""){
				realPrefix = datePrefix;
			}
			else{
				realPrefix = in_CardPrefix+datePrefix;
			}
			CardDAO dao=new CardDAO(ds);
			System.out.println("in_CardLength:"+in_CardLength+"realPrefix.length():"+realPrefix.length());
			int cardRndNum = (Integer.parseInt((in_CardLength))-(Integer)(realPrefix.length()));
			String serialNo = "";
			String serialPwd = "";
			List<CardViewDTO> list = new ArrayList<CardViewDTO>();
			String str = "";
			for(int i=1;i<=Integer.parseInt(in_Count);i++){
				CardViewDTO dto = new CardViewDTO();
				serialNo = realPrefix + dao.GetRandomNum(cardRndNum);
				serialPwd = dao.GetRandomType(in_PasswdType,Integer.parseInt(in_PwdLength));
				str += serialNo+","+serialPwd+"/";
				dto.setSerialNo(serialNo);
				dto.setSerialPwd(serialPwd);
				list.add(dto);
			}
			
			char[] strChar=str.toCharArray();
			String result="";
			for(int i=0;i<strChar.length;i++){
				result +=Integer.toBinaryString(strChar[i])+ "";
			}
			System.out.println("result:"+result);
			String adminName = "";
			if(request.getSession().getAttribute("username")!=null){
				adminName = String.valueOf(request.getSession().getAttribute("username"));
			}
			
			CardViewDTO dto = dao.queryMemberOrder(ss[0]);
			System.out.println(adminName+","+ss[0]+","+in_CardPrice+","+in_CardGold+","+in_Count+","+request.getRemoteAddr()+","+in_NoteInfo+","+result);
			String[] params = new String[] { adminName,ss[0],in_CardPrice,in_CardGold,in_Count,request.getRemoteAddr(),in_NoteInfo,str};
			String buildID = dao.BuildStreamAdd(params);
			Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
			if(in_ValidDate==""){
				cal.add(Calendar.DAY_OF_MONTH, 3600);
			}
			else{
				cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(in_ValidDate));
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String Validate = format.format(cal.getTime());

			if(!buildID.equals("0")){
				for(int i = 0;i<Integer.parseInt(in_Count);i++){
					String[] paramscard = new String[]{list.get(i).getSerialNo(),EncryptionMD5.encryption_MD5(list.get(i).getSerialPwd()).toUpperCase(),
							String.valueOf(buildID),ss[0],in_CardPrice,in_CardGold,String.valueOf(dto.getMemberOrder()),in_MemberDays,
							String.valueOf(dto.getUserRight()),String.valueOf(dto.getServiceRight()),in_UseRange,in_SalesPerson,Validate};
					dao.LivcardAdd(paramscard);
				}
				request.setAttribute("msg", "恭喜,充值卡成功生成"+in_Count+"张");
				return mapping.findForward("create");
			}
			else{
				request.setAttribute("msg", "数据库操作异常!");
				return mapping.findForward("createFailure");
			}
			
		}
		else if("list".equals(action)){

			CardDAO dao=new CardDAO(ds);
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
			String where=" BuildID in (select BuildID from LivcardAssociator where SalesPerson ='')";
			
			String termId = request.getParameter("selectOne");
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
				termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
			}
			List<CardViewDTO> list = null;
			
			if(""==termWord||"".equals(termWord)||"".equals(termId)){
				list = dao.GetRecordByPage(curPage, pageSize,where);
			}
			
			
			if(list.size()!=0){
				int totalPage = dao.getTotalPage();
				request.setAttribute("cardlist", list);
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageList", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				}
			return mapping.findForward("cardList");
		}
		
		else if("cardInfo".equals(action)){

			CardDAO dao=new CardDAO(ds);
			int buildID = 0;
			if(request.getParameter("buildID")!=null){
				buildID = Integer.parseInt(request.getParameter("buildID"));
			}
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
			int state = 0;
			if(request.getParameter("state")!=null){
				state = Integer.parseInt(request.getParameter("state"));
			}
			
			String where="buildID="+buildID;
			switch (state){
			 case 0:
				 where = where;break;
             case 1:
                 where = where + " And ApplyDate is not null";break;
             case 2:
             	 where = where + " And ApplyDate is null";break;
             case 3:
             	 where = where + " And Nullity = 1";break;
             case 4:
             	 where = where + " And Nullity = 0";break;
			}
			System.out.println("state:"+state+" where:"+where);
			List<CardViewDTO> list = null;
			list = dao.GetAssocitorByPage(curPage, pageSize,where);
			
			if(list.size()!=0){
				int totalPage = dao.getTotalPage();
				request.setAttribute("cardlist", list);
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageList", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				}
			return mapping.findForward("cardInfo");
		}
		
		else if("cardRecord".equals(action)){

			CardDAO dao=new CardDAO(ds);
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			String where="1=1";
/*			int userID = 0;
			if(request.getParameter("userID")!=null){
				    userID = Integer.parseInt(request.getParameter("userID"));
				    where = where + " and userID="+userID;
				}*/
			String startTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				where = where + "and ApplyDate>='"+startTime+"'";
			}
			String endTime = "";
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + "and ApplyDate<='"+endTime+"'";
			}
			
			String termId = request.getParameter("selectOne");
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=30;
			
			List<CardViewDTO> list = null;
			if("accounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索用户的判断");
				where = where + " and Accounts like '%"+termWord+"%'";
				list = dao.GetCardRecordByPage(curPage, pageSize,where);
			}
			
			if("gameID".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=2");
				System.out.println("进入搜索游戏编号的判断");
				where = where + " and gameID ="+termWord+"";
				list = dao.GetCardRecordByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				list = dao.GetCardRecordByPage(curPage, pageSize,where);
			}
			
			if(list.size()!=0){
				int totalPage = dao.getTotalPage();
				request.setAttribute("cardlist", list);
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageList", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				}
			return mapping.findForward("cardRecord");
		}
		
		
		else if("onlineOrder".equals(action)){

			CardDAO dao=new CardDAO(ds);
			int curPage = 1;
			String curPagestr = request.getParameter("curPage");
			if (curPagestr == null) {
				curPage = 1;
			} else {
				curPage = Integer.parseInt(curPagestr);
			}
			String where="1=1";
			String startTime = "";
			if(request.getParameter("startTime")!=null&&request.getParameter("startTime")!=""){
				startTime = request.getParameter("startTime");
				where = where + "and ApplyDate>='"+startTime+"'";
			}
			String endTime = "";
			if(request.getParameter("endTime")!=null&&request.getParameter("endTime")!=""){
				endTime = request.getParameter("endTime");
				where = where + "and ApplyDate<='"+endTime+"'";
			}
			
			String termId = request.getParameter("selectOne");
			String termWord ="";
			if(request.getParameter("termOne")!=null){
				termWord=request.getParameter("termOne");
			    termWord=new String(termWord.getBytes("ISO_8859_1"),"UTF-8");
				System.out.println(termWord+"termId:"+termId);
			}
			if (curPage < 1)
				curPage = 1;
			int pageSize=30;
			
			List<CardViewDTO> list = null;
			if("accounts".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=1");
				System.out.println("进入搜索用户的判断");
				where = where + " and Accounts like '%"+termWord+"%'";
				list = dao.GetOnlineOrderByPage(curPage, pageSize,where);
			}
			
			if("gameID".equals(termId)&&!"".equals(termWord)){
				System.out.println("termId=2");
				System.out.println("进入搜索游戏编号的判断");
				where = where + " and gameID ="+termWord+"";
				list = dao.GetOnlineOrderByPage(curPage, pageSize,where);
			}
			if("onlineID".equals(termId)&&!"".equals(termWord)){
				where = where + " and onlineID ="+termWord+"";
				list = dao.GetOnlineOrderByPage(curPage, pageSize,where);
			}
			if("".equals(termId)||"".equals(termWord)){
				list = dao.GetOnlineOrderByPage(curPage, pageSize,where);
			}
			
			if(list.size()!=0){
				int totalPage = dao.getTotalPage();
				request.setAttribute("cardlist", list);
				if(curPage>dao.getTotalPage())curPage=dao.getTotalPage();
				PageDTO pdto = new PageDTO();
				pdto.setCurPage(curPage);
				pdto.setTotalPage(totalPage);
				pdto.setTotalRecord(dao.getRcordCount());
				pdto.setPageSize(pageSize);
				request.setAttribute("page", pdto);
				request.setAttribute("pageList", pdto);
				request.setAttribute("pageIndex", curPage);
				request.setAttribute("pageSize", pageSize);
			}
			else{
				request.setAttribute("returnInfo", dao.returnInfo());
				}
			return mapping.findForward("onlineOrder");
		}
		
		else if("updateNullity".equals(action)){  //充值卡禁用-还原
			System.out.println(">>>>>>>>>>>数据库："+request.getParameter("checkbox"));
			String [] ids = request.getParameterValues("checkbox");
			int type = 0;
			if(request.getParameter("type")!=null){
				type = Integer.parseInt(request.getParameter("type"));
			}
			CardDAO dao = new CardDAO(ds);
			for(int i=0;i<ids.length;i++){
				dao.updateNullity(ids[i],type);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "操作成功!");
			return mapping.findForward("updateNullity");
		}
		
		else if("delete".equals(action)){
			System.out.println(">>>>>>>>>>>数据库："+request.getParameter("checkbox"));
			String [] ids = request.getParameterValues("checkbox");
			CardDAO dao = new CardDAO(ds);
			for(int i=0;i<ids.length;i++){
				dao.delete(ids[i]);
				System.out.println(ids[i]);
			}
			request.setAttribute("msg", "删除成功!");
			return mapping.findForward("delete");
		}
		
		else if("addBeizhu".equals(action)){
			String ids = request.getParameter("checkboxss");
			String [] idss = ids.split(",");
			String beizhu = "";
			if(request.getParameter("beizhu")!=null){
				beizhu = request.getParameter("beizhu");
				//beizhu= new String(beizhu.getBytes("ISO_8859_1"),"UTF-8");
			}
			
			CardDAO dao = new CardDAO(ds);
			for(int i=0;i<idss.length;i++){
				System.out.println("ids[i]="+idss[i]+"&beizhu="+beizhu);
				dao.addBeizhu(idss[i],beizhu);
				System.out.println(idss[i]);
			}
			request.setAttribute("msg", "备注成功!");
			return mapping.findForward("addBeizhu");
		}
		
		else if("excel".equals(action)){
			String where = "";
			String ids = request.getParameter("buildID");
/*			File file_dir = new File("E:\\card\\");
			if (!file_dir.exists()){
				file_dir.mkdir();
			}*/
			SimpleDateFormat f=new SimpleDateFormat("yyyyMMdd");
			Date d=Calendar.getInstance().getTime();
			String targetName="充值卡统计"+f.format(d)+ids+".txt";
			where = "buildID in ("+ids+")";
			@SuppressWarnings("unused")
			CardDAO dao = new CardDAO(ds);
			
			dao.excel(targetName,where,ids,response);
			//dao.download(targetName, response,targetName);
			return null;
			
		}

		return mapping.findForward("index");
	}
	

}

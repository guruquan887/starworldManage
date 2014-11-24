package com.doowal.hk798.admin;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class DataCountAction extends DispatchAction{

	
	public ActionForward usercount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			 {
		if(request.getSession().getAttribute("USER")==null){
			return mapping.findForward("index");
		}
			DataSource ds = this.getDataSource(request, "QPTreasureDB");
			DataCountDAO dao = new DataCountDAO(ds);
			
			//最近5天的统计数据
			String[] params = new String[] {"0",""};
			String[] params1 = new String[] {"1",""};
			String[] params2 = new String[] {"2",""};
			String[] params3 = new String[] {"3",""};
			String[] params4 = new String[] {"4",""};
			String[] result = dao.countDate1(params);
			String[] result1 = dao.countDate(params1);
			String[] result2 = dao.countDate(params2);
			String[] result3 = dao.countDate(params3);
			String[] result4 = dao.countDate(params4);
			
			int currYear, currMonth, currDate;
			Calendar calendar = Calendar.getInstance();
			currYear = calendar.get(Calendar.YEAR);
			currMonth = calendar.get(Calendar.MONTH) + 1;
			currDate = calendar.get(Calendar.DATE);
			
		   if (currMonth==1&&currDate==1) {//是一月一号
			    System.out.println("--------1111---------");
			    request.setAttribute("lastDay", (currYear-1)+"-"+12+"-"+31);
				request.setAttribute("lastDay1", (currYear-1)+"-"+12+"-"+30);
				request.setAttribute("lastDay2", (currYear-1)+"-"+12+"-"+29);
				request.setAttribute("lastDay3", (currYear-1)+"-"+12+"-"+28);
			    //System.out.println("昨天是："+(currYear-1)+"-"+12+"-"+31);
			    //System.out.println("前天是："+(currYear-1)+"-"+12+"-"+(31-1));
			   }
			   else if (currMonth==1&&currDate==2) {//是一月一号
			    System.out.println("--------222---------");
			    request.setAttribute("lastDay", (currYear)+"-"+currMonth+"-"+(currDate-1));
				request.setAttribute("lastDay1", (currYear-1)+"-"+12+"-"+31);
				request.setAttribute("lastDay2", (currYear-1)+"-"+12+"-"+30);
				request.setAttribute("lastDay3", (currYear-1)+"-"+12+"-"+29);
			    //System.out.println("昨天是："+(currYear)+"-"+currMonth+"-"+(currDate-1));
			    //System.out.println("前天是："+(currYear-1)+"-"+12+"-"+31);
			   }
			   else if (currMonth>1&&currDate>2) {
			    System.out.println("--------333---------");
			    request.setAttribute("lastDay", (currYear)+"-"+currMonth+"-"+(currDate-1));
				request.setAttribute("lastDay1", (currYear)+"-"+currMonth+"-"+(currDate-2));
				request.setAttribute("lastDay2", (currYear)+"-"+currMonth+"-"+(currDate-3));
				request.setAttribute("lastDay3", (currYear)+"-"+currMonth+"-"+(currDate-4));
			    //System.out.println("昨天是："+(currYear)+"-"+currMonth+"-"+(currDate-1));
			    //System.out.println("前天是："+(currYear-1)+"-"+currMonth+"-"+(currDate-2));
			   }
			   else if (currMonth>1&&currDate==2) {
			    System.out.println("--------4444---------");
			    request.setAttribute("lastDay", (currYear)+"-"+currMonth+"-"+(currDate-1));
				request.setAttribute("lastDay1", (currYear)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)));
				request.setAttribute("lastDay2", (currYear)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)-1));
				request.setAttribute("lastDay3", (currYear)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)-2));
			    //System.out.println("昨天是："+(currYear)+"-"+currMonth+"-"+(currDate-1));
			    //System.out.println("前天是："+(currYear-1)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)));
			   }
			   else if (currMonth>1&&currDate==1) {
			    System.out.println("--------5555---------");
			    request.setAttribute("lastDay", (currYear)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)));
				request.setAttribute("lastDay1", (currYear)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)-1));
				request.setAttribute("lastDay2", (currYear)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)-2));
				request.setAttribute("lastDay3", (currYear)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)-3));
			    //System.out.println("昨天是："+(currYear)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)));
			    //System.out.println("前天是："+(currYear-1)+"-"+(currMonth-1)+"-"+(dao.getLastDayOfUpMonth(currYear,currMonth,currDate)-1));
			   }
			   else{
				request.setAttribute("lastDay", (currYear) + "-" + currMonth + "-"+ (currDate-1));
				request.setAttribute("lastDay1", (currYear) + "-" + currMonth + "-"+ (currDate-2));
				request.setAttribute("lastDay2", (currYear) + "-" + currMonth + "-"+ (currDate-3));
				request.setAttribute("lastDay3", (currYear) + "-" + currMonth + "-"+ (currDate-4));
			   }
			request.setAttribute("countuser", result);
			request.setAttribute("countuser1", result1);
			request.setAttribute("countuser2", result2);
			request.setAttribute("countuser3", result3);
			request.setAttribute("countuser4", result4);
			long countTotal0 = Long.parseLong(result[0])+Long.parseLong(result1[0])+Long.parseLong(result2[0])+Long.parseLong(result3[0])+Long.parseLong(result4[0]);
			long countTotal1 = Long.parseLong(result[1])+Long.parseLong(result1[1])+Long.parseLong(result2[1])+Long.parseLong(result3[1])+Long.parseLong(result4[1]);
			long countTotal2 = Long.parseLong(result[2])+Long.parseLong(result1[2])+Long.parseLong(result2[2])+Long.parseLong(result3[2])+Long.parseLong(result4[2]);
			long countTotal3 = Long.parseLong(result[3])+Long.parseLong(result1[3])+Long.parseLong(result2[3])+Long.parseLong(result3[3])+Long.parseLong(result4[3]);
			long countTotal4 = Long.parseLong(result[4])+Long.parseLong(result1[4])+Long.parseLong(result2[4])+Long.parseLong(result3[4])+Long.parseLong(result4[4]);
			long countTotal5 = Long.parseLong(result[5])+Long.parseLong(result1[5])+Long.parseLong(result2[5])+Long.parseLong(result3[5])+Long.parseLong(result4[5]);
			long countTotal6 = Long.parseLong(result[6])+Long.parseLong(result1[6])+Long.parseLong(result2[6])+Long.parseLong(result3[6])+Long.parseLong(result4[6]);
			long countTotal7 = Long.parseLong(result[7])+Long.parseLong(result1[7])+Long.parseLong(result2[7])+Long.parseLong(result3[7])+Long.parseLong(result4[7]);
			long countTotal8 = Long.parseLong(result[8])+Long.parseLong(result1[8])+Long.parseLong(result2[8])+Long.parseLong(result3[8])+Long.parseLong(result4[8]);
			long countTotal9 = Long.parseLong(result[9])+Long.parseLong(result1[9])+Long.parseLong(result2[9])+Long.parseLong(result3[9])+Long.parseLong(result4[9]);
			long countTotal10 = Long.parseLong(result[10])+Long.parseLong(result1[10])+Long.parseLong(result2[10])+Long.parseLong(result3[10])+Long.parseLong(result4[10]);
			long countTotal11 = Long.parseLong(result[11])+Long.parseLong(result1[11])+Long.parseLong(result2[11])+Long.parseLong(result3[11])+Long.parseLong(result4[11]);
			long countTotal12 = Long.parseLong(result[12])+Long.parseLong(result1[12])+Long.parseLong(result2[12])+Long.parseLong(result3[12])+Long.parseLong(result4[12]);
			long countTotal13 = Long.parseLong(result[13])+Long.parseLong(result1[13])+Long.parseLong(result2[13])+Long.parseLong(result3[13])+Long.parseLong(result4[13]);
			long countTotal14 = Long.parseLong(result[14])+Long.parseLong(result1[14])+Long.parseLong(result2[14])+Long.parseLong(result3[14])+Long.parseLong(result4[14]);
			request.setAttribute("countTotal0", countTotal0);
			request.setAttribute("countTotal1", countTotal1);
			request.setAttribute("countTotal2", countTotal2);
			request.setAttribute("countTotal3", countTotal3);
			request.setAttribute("countTotal4", countTotal4);
			request.setAttribute("countTotal5", countTotal5);
			request.setAttribute("countTotal6", countTotal6);
			request.setAttribute("countTotal7", countTotal7);
			request.setAttribute("countTotal8", countTotal8);
			request.setAttribute("countTotal9", countTotal9);
			request.setAttribute("countTotal10", countTotal10);
			request.setAttribute("countTotal11", countTotal11);
			request.setAttribute("countTotal12", countTotal12);
			request.setAttribute("countTotal13", countTotal13);
			request.setAttribute("countTotal14", countTotal14);
		return mapping.findForward("countuser");
	}
	

}

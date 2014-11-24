package com.keno8.struts.action.admin;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import javax.sql.DataSource;


import com.keno8.struts.dao.DataCountDAO;
import com.keno8.struts.dto.DataCountDTO;

public class MyTask extends TimerTask {
	//private Connection con;
	private DataSource ds;
	

	public static long runTime = 1;
	
	
	    public MyTask(DataSource ds) {
		//super();
		this.ds = ds;
	}

		public void run() 
	      {
			//WebContext wctx = WebContextFactory.get();
	    	//DataSource ds = (DataSource) wctx.getServletContext().getAttribute("Keno8");
	        System.out.println("*******定时器RUN" + (runTime++) + "*********");
	        Date d = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("kk");//其中yyyy-MM-dd是你要表示的格式
			//可以任意组合，不限个数和次序；具体表示为：MM-month,dd-day,yyyy-year;kk-hour,mm-minute,ss-second;
			String str=sdf.format(d);
			if(str.equals("09")){
				DataCountDAO dao = new DataCountDAO(ds);
				DataCountDTO dto = new DataCountDTO();
			    DataCountDTO dto1 = dao.countDate(dto);
			}
	   }
}

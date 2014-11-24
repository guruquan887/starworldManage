package com.keno8.struts.action.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.keno8.struts.dao.DataCountDAO;
import com.keno8.struts.dto.DataCountDTO;

public class RealTimeMonitoringTimer {
	
//	HttpServletRequest request = null;
//	HttpServletResponse response = null;
	boolean flag = false;
	List list = new ArrayList();
	Timer timer = new Timer();
//	
//	public RealTimeMonitoringTimer(HttpServletRequest request,HttpServletResponse response){
//		this.request = request;
//		this.response = response;
//	}
	
	/**
	 * �?��执行
	 */
	public void startCheck(){
		
		timer.schedule(new Check(), 1000, 3000);//1秒后�?���?��，每�?秒检查一�?
		
	}

	/**
	 * �?��是否有异常信�?
	 * @author Administrator
	 *
	 */
	class Check extends java.util.TimerTask{
		
		@Override
		public void run() {
			WebContext wctx = WebContextFactory.get();
			DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
					"C");
			Connection con;
			try {
				con = ds.getConnection();
				DataCountDAO mg = new DataCountDAO(ds);
				DataCountDTO dto = new DataCountDTO();
				DataCountDTO dto1 = mg.countDate(dto);
				System.out.println("执行任务");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}



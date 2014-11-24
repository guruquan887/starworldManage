package com.keno8.struts.action.admin;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

public class SessionListener implements HttpSessionListener {
	
	private static int activeSessions = 1; 
	private TimerManager tm = null;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("************running in listener*************");
		ServletContext cs=arg0.getSession().getServletContext();
		DataSource ds = (DataSource) cs.getAttribute("Keno8");
		tm = new TimerManager(5000,ds);
		activeSessions++;
		System.out.println("****" + activeSessions + "****");

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		if(activeSessions > 0) 
		   {
		    activeSessions--;
		    System.out.println("*************"+activeSessions+"****************");
		   }
		   if(tm != null)
		   {
		    tm.destoryTimer();
		   }
		}

	}


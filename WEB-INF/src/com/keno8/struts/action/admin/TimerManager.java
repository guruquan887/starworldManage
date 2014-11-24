package com.keno8.struts.action.admin;

import java.util.Timer;

import javax.sql.DataSource;


public class TimerManager {
	
	private DataSource ds;
    Timer t = null;
    public TimerManager(long delay,DataSource ds) 
    {
    	this.ds=ds;
        this.time(delay);
    }

    public void time(long delay)
    { 
     t= new Timer();
     MyTask mt = new MyTask(ds);
     t.schedule(mt,delay,60000*50);
    }
    public void destoryTimer()
    {
     t.cancel();
    }


}

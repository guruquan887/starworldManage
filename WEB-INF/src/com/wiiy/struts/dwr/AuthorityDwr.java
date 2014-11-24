package com.wiiy.struts.dwr;


import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;


public class AuthorityDwr {

	public String SetContantFirst(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"Keno8");
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		//r = t.SetContantFirst(fid,userid);
		r = t.init(fid, userid);
		//System.out.println("R=" + r);
		return r;
	}  

	public String SetContantSecond(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"Keno8");
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.SetContantSecond(fid,userid);
		//System.out.println("R=" + r);
		return r;
	}  

	public String SetContantThird(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"org.apache.struts.action.DATA_SOURCE");
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.SetContantThird(fid,userid);
		//System.out.println("R=" + r);
		return r;
	}  

	public String SetContantFourth(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"org.apache.struts.action.DATA_SOURCE");
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.SetContantFourth(fid,userid);
		//System.out.println("R=" + r);
		return r;
	}  

	public String SetContantFifth(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"org.apache.struts.action.DATA_SOURCE");
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.SetContantFifth(fid,userid);
		//System.out.println("R=" + r);
		return r;
	}  

	public String ReleaseAuthority(int id,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"Keno8");
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.ReleaseAuthority(id,userid);
		//System.out.println("R=" + r);
		return r;
	}   	

	public String GetAuthority(int id,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"Keno8");
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.GetAuthority(id,userid);
		//System.out.println("R=" + r);
		return r;
	} 
}

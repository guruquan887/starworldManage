package com.doowal.hk798.dwr;


import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class AuthorityDwr {

	public String SetContantFirst(int fid,int userid) {
		

		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPPlatformManagerDB");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.init(fid, userid);
		return r;
	}  

	public String SetContantSecond(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPPlatformManagerDB");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.SetContantSecond(fid,userid);
		return r;
	}  

	public String SetContantThird(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"org.apache.struts.action.DATA_SOURCE");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.SetContantThird(fid,userid);
		return r;
	}  

	public String SetContantFourth(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"org.apache.struts.action.DATA_SOURCE");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.SetContantFourth(fid,userid);
		return r;
	}  

	public String SetContantFifth(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"org.apache.struts.action.DATA_SOURCE");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.SetContantFifth(fid,userid);
		return r;
	}  

	public String ReleaseAuthority(int id,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPPlatformManagerDB");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.ReleaseAuthority(id,userid);
		return r;
	}   	

	public String GetAuthority(int id,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPPlatformManagerDB");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		AuthorityDb t = new AuthorityDb(ds);
		r = t.GetAuthority(id,userid);
		return r;
	} 
}

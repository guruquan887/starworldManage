package com.doowal.hk798.dwr;


import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;


public class RoleAuthorityDwr {

	public String SetContantFirst(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPPlatformManagerDB");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		RoleAuthorityDb t = new RoleAuthorityDb(ds);
		r = t.SetContantFirst(fid,userid);
		return r;
	}  

	public String SetContantSecond(int fid,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPPlatformManagerDB");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		RoleAuthorityDb t = new RoleAuthorityDb(ds);
		r = t.SetContantSecond(fid,userid);
		return r;
	}  

	public String ReleaseAuthority(int id,int userid) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPPlatformManagerDB");
		@SuppressWarnings("unused")
		HttpSession s = wctx.getSession();
		String r = null;
		RoleAuthorityDb t = new RoleAuthorityDb(ds);
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
		RoleAuthorityDb t = new RoleAuthorityDb(ds);
		r = t.GetAuthority(id,userid);
		return r;
	} 
}

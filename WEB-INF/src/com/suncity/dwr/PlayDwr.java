package com.suncity.dwr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class PlayDwr {
	
	
	public List<PlayDTO> getPlayNumber() {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPTreasureDB");
		HttpSession s = wctx.getSession();
		List<PlayDTO> r = new ArrayList<PlayDTO>();
		PlayDb t = new PlayDb(ds);
		r = t.getPlayNumber();
		s.setAttribute("playNumber", r);

		return r;
	}
	
	public void updateSessionTime(String userID) {
		WebContext wctx = WebContextFactory.get();
		DataSource ds = (DataSource) wctx.getServletContext().getAttribute(
				"QPTreasureDB");
		PlayDb t = new PlayDb(ds);
		t.updateSessionTime(userID);

	}

}

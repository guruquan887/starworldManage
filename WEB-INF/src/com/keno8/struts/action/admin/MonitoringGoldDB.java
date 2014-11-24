package com.keno8.struts.action.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;

public class MonitoringGoldDB extends Action{
	
	
	private Connection conn = null;
	private PreparedStatement psta = null;
	private ResultSet rs = null;
	HttpServletRequest request;
	
	
	protected PreparedStatement getPst(){
		return this.psta;
	}
	
	protected ResultSet getRst(){
		return this.rs;
	}
	
	protected void close(){
		try {
			if (this.rs != null) {
				this.rs.close();
			}
			if (this.psta != null) {
				this.psta.close();
			}
			if (this.conn != null) {
				this.conn.close();
			}
		} catch (SQLException e) {
			try {
				if (this.rs != null) {
					this.rs.close();
				}
				if (this.psta != null) {
					this.psta.close();
				}
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException ex) {
			}
		}
		
	}

}

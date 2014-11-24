package com.keno8.struts.action.gameUser.mark;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class BaseDAO {
	
	public void closeDBObject(Connection con,ResultSet rs,Statement st,
			  CallableStatement callstmt,PreparedStatement ps){
		try{
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(st!=null){
				st.close();st=null;
			}
			if(callstmt!=null){
				callstmt.close();callstmt=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public String RandomNum(int length) {
		Random r = new Random();
		String s = "";
		String str = "0123456789";
		for (int i = 0; i < length; i++) {
			int t = ((r.nextInt() % 10) + 10) % 10;
			s += str.substring(t, t + 1);
		}
		return s;
	}
	
}

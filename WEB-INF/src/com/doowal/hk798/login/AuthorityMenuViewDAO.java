package com.doowal.hk798.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class AuthorityMenuViewDAO {
	public final static String SQL_ROLE_MENU = "select * from AuthorityMenuView where userid=? and parentId=? order by xh asc";

	private DataSource ds;
	
	public AuthorityMenuViewDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public List<AuthorityMenuViewDTO> select(int userid,int parentId){
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 List<AuthorityMenuViewDTO> list = new ArrayList<AuthorityMenuViewDTO>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_ROLE_MENU);
			ps.setInt(1, userid);
			ps.setInt(2, parentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				AuthorityMenuViewDTO dto = new AuthorityMenuViewDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserid(userid);
				dto.setMenuid(rs.getInt("menuid"));
				dto.setMenuName(rs.getString("menuName"));
				dto.setMenuPath(rs.getString("menuPath"));
				dto.setParentId(parentId);
				dto.setInsertdiv(rs.getBoolean("insertdiv"));
				list.add(dto);
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();con=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(rs!=null){
					rs.close();rs=null;
				}
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			}
			 catch (SQLException e1) {
					e1.printStackTrace();
			 }
		} 
		return list;
	}
	
	
}

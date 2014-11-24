package com.keno8.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.keno8.struts.dto.AdminDTO;
import com.keno8.struts.dto.AuthorityMenuViewDTO;



public class AuthorityMenuViewDAO {
	public final static String SQL_ROLE_MENU = "select * from authorityMenuView where userid=? and parentId=? order by xh asc";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public AuthorityMenuViewDAO(Connection con) {
		super();
		this.con = con;
	}
	
	
	
	public List<AuthorityMenuViewDTO> select(int userid,int parentId){
		List<AuthorityMenuViewDTO> list = new ArrayList<AuthorityMenuViewDTO>();
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public void update(String ip,String userName){
		try {
			AdminDTO dto = new AdminDTO();
			ps = con.prepareStatement("update admin set loginTime=getDate(),loginIP=? where userName=?");
			ps.setString(1, ip);
			ps.setString(2, userName);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

package com.keno8.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.keno8.struts.dto.RelationDTO;

public class RelationDAO {
	public final static String SQL_ROLE_MENU = "select * from relation,menu where relation.roleId=? and menu.parentId=? and relation.menuId=menu.Id";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public RelationDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public List<RelationDTO> select(int roleId,int parentId){
		List<RelationDTO> list = new ArrayList<RelationDTO>();
		try {
			ps = con.prepareStatement(SQL_ROLE_MENU);
			ps.setInt(1, roleId);
			ps.setInt(2, parentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				RelationDTO dto = new RelationDTO();
				dto.setRoleId(roleId);
				dto.setMenuId(rs.getInt("menuId"));
				dto.setMenuName(rs.getString("menuName"));
				dto.setMenuPath(rs.getString("menuPath"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	public List<RelationDTO> select(int roleId){
		List<RelationDTO> list = new ArrayList<RelationDTO>();
		try {
			ps=con.prepareStatement("select * from relation,menu where relation.roleId=? and relation.menuId=menu.Id order by relation.roleId");
			ps.setInt(1, roleId);
			rs=ps.executeQuery();
			while(rs.next()){
				RelationDTO dto = new RelationDTO();
				dto.setRoleId(roleId);
				dto.setMenuId(rs.getInt("menuId"));
				dto.setMenuName(rs.getString("menuName"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	public List<RelationDTO> selectNO(int roleId){
		List<RelationDTO> list = new ArrayList<RelationDTO>();
		try {
			ps=con.prepareStatement("select * from menu where menu.Id not in(select menu.Id from menu,relation where relation.roleId=? and menu.Id=relation.powerId)");
			ps.setInt(1, roleId);
			rs=ps.executeQuery();
			while(rs.next()){
				RelationDTO dto = new RelationDTO();
				dto.setRoleId(roleId);
				dto.setMenuId(rs.getInt("menuId"));
				dto.setMenuName(rs.getString("menuName"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	public void add(int roleId,int menuId){
		try {
			ps=con.prepareStatement("insert into relation values(?,?)");
			ps.setInt(1,roleId );
			ps.setInt(2, menuId);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void del(int  roleId,int menuId){
		try {
			ps=con.prepareStatement("delete from relation where roleId=? and menuId=?");
			ps.setInt(1, roleId);
			ps.setInt(2,menuId);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

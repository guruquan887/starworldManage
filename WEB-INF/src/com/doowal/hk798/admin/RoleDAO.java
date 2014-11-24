package com.doowal.hk798.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class RoleDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DataSource ds =null;;
	public RoleDAO(DataSource ds) {
		this.ds = ds;
	}

	public List<RoleDTO> sel(int id){
		List<RoleDTO> list = new ArrayList<RoleDTO>();
		
		try {
			con=ds.getConnection();
			ps = con.prepareStatement("select * from SunCityManage.dbo.role where id >="+id);
			rs = ps.executeQuery();
			while(rs.next()){
				RoleDTO dto = new RoleDTO();
				dto.setId(rs.getInt("id"));
				dto.setRoleName(rs.getString("roleName"));
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
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public List<RoleDTO> select(int roleId){
		List<RoleDTO> list = new ArrayList<RoleDTO>();
		String sql = "select * from Base_Roles where roleId>"+roleId;
		try {
			con=ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				RoleDTO dto = new RoleDTO();
				dto.setId(rs.getInt("RoleID"));
				dto.setRoleName(rs.getString("roleName"));
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
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public void copyuthority(int userid,int roleid){
		
		try {
			con=ds.getConnection();
			Statement st=con.createStatement();
			String sql="select * from roleAuthority where roleid="+roleid;
			System.out.println(sql);
			rs=st.executeQuery(sql);
			List<String> t=new ArrayList<String>();
			while(rs.next()){
				int menuid=rs.getInt("menuid");
				t.add(String.valueOf(menuid));
			}
			for (String menuid: t) {
				sql="insert into Authority(userid,menuid) values("+userid+","+menuid+")";
				System.out.println(sql);
				st.executeUpdate(sql);
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
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
	}

}

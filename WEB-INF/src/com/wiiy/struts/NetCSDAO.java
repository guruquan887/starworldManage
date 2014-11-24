package com.wiiy.struts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class NetCSDAO {
	private DataSource ds;

	public NetCSDAO(DataSource ds) {
		this.ds = ds;
	}

	public NetCSDTO getById(int id) {
		NetCSDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from GameExchangePreferences where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new NetCSDTO();
			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setSzAdvertise(rs.getString("szAdvertise"));
				dto.setSzAdvertiseDl(rs.getString("szAdvertiseDl"));
				dto.setSzAdvertisePh(rs.getString("szAdvertisePh"));
				dto.setSzAdvertiseGl(rs.getString("szAdvertiseGl"));
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
		return dto;
	}
	
	public boolean update(NetCSDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		Boolean r=false;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update GameExchangePreferences set szAdvertise=?,szAdvertiseDl=?,szAdvertisePh=?,szAdvertiseGl=? where id=?");
			ps.setString(1, dto.getSzAdvertise());
			ps.setString(2, dto.getSzAdvertiseDl());
			ps.setString(3, dto.getSzAdvertisePh());
			ps.setString(4, dto.getSzAdvertiseGl());
			ps.setInt(5, dto.getId());
			ps.execute();
			r=true;
			
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();con=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try{
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
		return r;
	}
	

}

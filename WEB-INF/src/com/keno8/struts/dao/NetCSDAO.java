package com.keno8.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.keno8.struts.dto.NetCSDTO;

public class NetCSDAO {
	private DataSource ds;
	//private Connection con;
	//private ResultSet rs;
	//private PreparedStatement ps;
	
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
				dto.setTitle(rs.getString("title"));
				dto.setUrl(rs.getString("url"));
				dto.setYeepayRate(rs.getLong("yeepayRate"));
				dto.setGoldRateScore(rs.getLong("goldRateScore"));
				dto.setRegGold(rs.getLong("regGold"));
				dto.setRegScore(rs.getLong("regScore"));
				dto.setGameTax(rs.getFloat("gameTax"));
				dto.setXtss(rs.getFloat("xtss"));
				dto.setOnLineTime(rs.getInt("onLineTime"));
				dto.setOnLineTimeGold(rs.getLong("onLineTimeGold"));
				dto.setUponLineTimeGold(rs.getLong("uponLineTimeGold"));
				dto.setAdminEmail(rs.getString("adminEmail"));
				dto.setLinkMan(rs.getString("linkMan"));
				dto.setRecordInfo(rs.getString("recordInfo"));
				dto.setBeishu(rs.getDouble("beishu"));
				dto.setFanben(rs.getDouble("fanben"));
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
			//con.setAutoCommit(true);
			con = ds.getConnection();
			ps = con.prepareStatement("update GameExchangePreferences set title=?,url=?,yeepayRate=?,regGold=?,gameTax=?,adminEmail=?,linkMan=?,recordInfo=?,regScore=?,beishu=?,fanben=? where id=?");
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getUrl());
			ps.setLong(3, dto.getYeepayRate());
			ps.setLong(4, dto.getRegGold());
			ps.setFloat(5, dto.getGameTax());
			ps.setString(6, dto.getAdminEmail());
			ps.setString(7, dto.getLinkMan());
			ps.setString(8, dto.getRecordInfo());
			ps.setLong(9, dto.getRegScore());
			ps.setDouble(10, dto.getBeishu());
			ps.setDouble(11, dto.getFanben());
			ps.setInt(12, dto.getId());
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

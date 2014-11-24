package com.wiiy.spreader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class SpreaderNetsDAO {
	
	
	private DataSource ds;

	//private Connection con;
	//private ResultSet rs;
	//private PreparedStatement ps;
	

	public SpreaderNetsDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public SpreaderNetsDTO getById(int userID) {
		SpreaderNetsDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from RegAccountsInfo where userID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			dto = new SpreaderNetsDTO();
			if (rs.next()) {
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setGameTax(rs.getFloat("gameTax"));
				//dto.setOnLineTime(rs.getInt("onLineTime"));
				//dto.setUponLineTimeGold(rs.getLong("uponLineTimeGold"));
				//dto.setSsfh(rs.getFloat("ssfh"));
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

	public Spreader getDataById(int userID) {
		Spreader dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from tcDetailRecord where userid=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			dto = new Spreader();
			if (rs.next()) {
				dto.setUserId(rs.getInt("userid"));
				dto.setAccounts(rs.getString("sxaccounts"));
				dto.setGxjinbi(rs.getDouble("tcScore"));
				dto.setXxLostSum(rs.getDouble("sumPureScore"));
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
	
	public boolean updateUserGameTax(SpreaderNetsDTO dto) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("update RegAccountsInfo set gameTax=? where userID=?");
			ps.setFloat(1, dto.getGameTax());
			ps.setInt(2, dto.getUserID());
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
	
	public boolean DeleteDownLine(Spreader dto) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			System.out.println("修改sql:update RegAccountsInfo set SpreaderName='' where userID="+dto.getUserId());
			ps = con.prepareStatement("update RegAccountsInfo set SpreaderName='' where userID=?");
			ps.setInt(1, dto.getUserId());
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
	
	public boolean updateDownLineTcRecord(Spreader dto) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			System.out.println("修改sql:update tcRecord set tcScore=tcScore-"+dto.getGxjinbi()+",xxSumScore=xxSumScore-"+dto.getXxLostSum()+" where accounts="+dto.getAccounts());
			ps = con.prepareStatement("update tcRecord set tcScore=tcScore-"+dto.getGxjinbi()+",xxSumScore=xxSumScore-"+dto.getXxLostSum()+" where accounts='"+dto.getAccounts()+"'");

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
	
	public boolean DeleteDownLineRecord(Spreader dto) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			System.out.println("删除sql:delete from tcDetailRecord where userid ="+dto.getUserId());
			ps = con.prepareStatement("delete from tcDetailRecord where userid =?");
			ps.setInt(1,dto.getUserId());
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
	
	
	public float querySsfh(int userID){
		float ssfh = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from RegAccountsInfo where userID = ?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				ssfh = rs.getFloat("ssfh");
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
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return ssfh;
	}
	

}

package com.keno8.struts.dao;

import java.sql.*;
import java.util.*;

import com.keno8.struts.dto.MonitorUserRecordDTO;



public class MonitorUserRecordDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int pageCount;
	private int recordCount;

	public MonitorUserRecordDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public int getRcordCount() {
		return recordCount;
	}
	
	
	public List<MonitorUserRecordDTO> getAllMonitorUserRecord(int pageindex,int pageSize,String where,String orderBy) {
		List<MonitorUserRecordDTO> list = new ArrayList<MonitorUserRecordDTO>();
		try {
			
			Statement stat=null;
			ResultSet rs=null;
			/*
			
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con.setAutoCommit(false); // Setup the call. 
			CallableStatement toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "GameMonitorUserRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				MonitorUserRecordDTO dto = new MonitorUserRecordDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setGameID(rs.getInt("gameID"));
				dto.setIscore(rs.getLong("iscore"));
				dto.setOscore(rs.getLong("oscore"));
				dto.setIoscore(rs.getLong("ioscore"));
				dto.setRecordTime(rs.getString("recordTime").substring(0, 19));
				list.add(dto);
				
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			toesUp.close();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public MonitorUserRecordDTO getById(int userID) {
		MonitorUserRecordDTO dto = null;
		try {
			ps = con.prepareStatement("select * from GameMonitorUserRecordView where userID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			dto = new MonitorUserRecordDTO();
			if (rs.next()) {
				dto.setUserID(rs.getInt("UserID"));
				dto.setAccounts(rs.getString("Accounts"));
				dto.setGameID(rs.getInt("GameID"));
				dto.setIoscore(rs.getLong("ioscore"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public String update(MonitorUserRecordDTO dto) {
		String msg = "";
		long iscore = 0;
		long oscore = 0;
		long ioscore = 0;
		String sql = "select * from QPTreasureDB.dbo.CheckGoldView where userID="+dto.getUserID();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				iscore = rs.getLong("iscore");
				oscore = rs.getLong("oscore");
				ioscore = iscore - oscore;
				System.out.println("差额为："+ioscore);
			}
			
			sql = "update QPTreasureDB.dbo.GoldRecorde set score = score+"+ioscore+" where userID="+dto.getUserID();
			ps = con.prepareStatement(sql);
			ps.execute();
			msg = "允许已成功!";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String updatedelete(MonitorUserRecordDTO dto) {
		String msg = "";
		long iscore = 0;
		long oscore = 0;
		long ioscore = 0;
		String sql = "select * from QPTreasureDB.dbo.CheckGoldView where userID="+dto.getUserID();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				iscore = rs.getLong("iscore");
				oscore = rs.getLong("oscore");
				ioscore = iscore - oscore;
				System.out.println("差额为："+ioscore);
			}
			
			sql = "update QPTreasureDB.dbo.GoldRecorde set score = score+"+ioscore+" where userID="+dto.getUserID();
			ps = con.prepareStatement(sql);
			ps.execute();
			
			
			sql = "delete  from MonitorUserRecord where userID="+dto.getUserID();
			ps = con.prepareStatement(sql);
			ps.execute();
			
			msg = "允许已成功!";
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	public int getTotalPage() {
		return pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	

}

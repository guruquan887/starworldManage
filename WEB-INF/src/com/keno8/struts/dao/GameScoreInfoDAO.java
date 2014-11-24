package com.keno8.struts.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.dto.GameUserDTO;


public class GameScoreInfoDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public GameScoreInfoDAO(DataSource ds) {
		this.ds = ds;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public int getRcordCount() {
		return recordCount;
	}
	
	public List<GameUserDTO> GetRecordByPage(int pageindex,int pageSize,String orderby) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs=null;
		try {
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
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "RegAccountsInfo");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, "");//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setNickName(rs.getString("nickName"));
				dto.setScore(rs.getDouble("score"));
				dto.setRegisterDate(rs.getString("registerDate"));
				dto.setInsureScore(rs.getLong("insureScore"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	public List<GameUserDTO> GetRecordByPage(int pageindex,int pageSize) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs=null;
		try {
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "RegAccountsInfo");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "userID desc");//排序字段
			toesUp.setString(4, "");//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setNickName(rs.getString("nickName"));
				dto.setScore(rs.getDouble("score"));
				dto.setInsureScore(rs.getDouble("insureScore"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(true);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	public List<GameUserDTO> GetRecordByPageSelect(int pageindex,int pageSize,String where,String orderby) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs=null;
		try {
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
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "RegAccountsInfo");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setNickName(rs.getString("nickName"));
				dto.setScore(rs.getDouble("score"));
				dto.setRegisterDate(rs.getString("registerDate"));
				dto.setInsureScore(rs.getDouble("insureScore"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			toesUp.close();
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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

	public int getTotalPage() {
		return pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	

}

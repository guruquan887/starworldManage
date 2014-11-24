package com.keno8.struts.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.dto.GameDhScoreDTO;

public class GameDhScoreDAO {

	private int pageCount;
	private int recordCount;
	private DataSource ds;
	
	public GameDhScoreDAO(DataSource ds) {
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
	
	public int getTotalPage() {
		return pageCount;
	}
	
	
	public List<GameDhScoreDTO> GetRecordDetailsByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameDhScoreDTO> list = new ArrayList<GameDhScoreDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
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
			toesUp.setString(1, "UserDhScore_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameDhScoreDTO dto = new GameDhScoreDTO();
				int userID = rs.getInt("UserID");
				dto.setUserID(userID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setNickName(rs.getString("nickName"));
				String t1=rs.getString("createTime");
				if(t1!=null&&t1.length()>19){
					t1=t1.substring(0,19);
				}
				dto.setCreateTime(t1);
				dto.setBeforeMoney(rs.getLong("beforeMoney"));
				dto.setAfterMoney(rs.getLong("afterMoney"));
				dto.setDhScore(rs.getLong("diffMoney"));
				list.add(dto);
			}
			
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
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
	
	public List<GameDhScoreDTO> GetRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameDhScoreDTO> list = new ArrayList<GameDhScoreDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
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
			toesUp.setString(1, "UserDhZongScore_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameDhScoreDTO dto = new GameDhScoreDTO();
				int userID = rs.getInt("UserID");
				dto.setUserID(userID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setNickName(rs.getString("nickName"));
				dto.setScore(rs.getLong("score"));
				dto.setDhScore(rs.getLong("dhScore"));
				list.add(dto);
			}
			
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
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


}

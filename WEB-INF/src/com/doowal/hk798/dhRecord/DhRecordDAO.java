package com.doowal.hk798.dhRecord;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.doowal.hk798.gameUser.BaseDAO;

public class DhRecordDAO extends BaseDAO{
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	
	
	public DhRecordDAO(DataSource ds) {
		this.ds = ds;
	}
	public int getTotalPage() {
		return pageCount;
	}
	public int getRcordCount() {
		return recordCount;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public String updateDhRecord(String beizhu,String userID,String express_ID){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update NNGameManage.dbo.DhRecord set beizhu=?,state=1 where userID=? and express_ID=?");
			ps.setString(1, beizhu);
			ps.setString(2, userID);
			ps.setString(3, express_ID);
			ps.execute();
			msg = "更新成功!";
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, null, null, null, ps);
		}
		return msg;
	}
	
	public DhRecordDTO queryDhRecord(String userID,String express_ID){
		DhRecordDTO dto = new DhRecordDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from NNGameManage.dbo.DhRecordList_View where userID=? and express_ID=?");
			ps.setString(1, userID);
			ps.setString(2, express_ID);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setUserID(rs.getInt("userID"));
				dto.setTypeID(rs.getInt("typeID"));
				dto.setTypeName(rs.getString("typeName"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setDhAmount(rs.getLong("dhAmount"));
				dto.setMallID(rs.getInt("mallID"));
				dto.setMallName(rs.getString("mallName"));
				dto.setGenerateTime(rs.getString("generateTime").substring(0, 19));
				dto.setExpress_ID(rs.getString("express_ID"));
				dto.setCancelType(rs.getInt("CancelType"));
				dto.setState(rs.getInt("state"));
				int state = rs.getInt("state");
				dto.setDhCount(rs.getInt("dhCount"));
				if(state==0){
					dto.setStateName("待发货");
				}
				else if(state==1){
					dto.setStateName("已发货");
				}
				else if(state==2){
					dto.setStateName("已取消");
				}
				dto.setIp(rs.getString("ip"));
				dto.setZip(rs.getString("zip"));
				dto.setAddress(rs.getString("address"));
				dto.setTelphone(rs.getString("telphone"));
				dto.setRealName(rs.getString("realName"));
				dto.setBeizhu(rs.getString("beizhu"));
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, null, null, null, ps);
		}
		return dto;
	}
	
	public String deleteDhRecord(int userID,String express_ID){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from NNGameManage.dbo.DhRecord where userID=? and express_ID=?");
			ps.setInt(1, userID);
			ps.setString(2, express_ID);
			ps.execute();
			msg = "删除成功!";
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, null, null, null, ps);
		}
		return msg;
	}
	
	public void cancel(String express_ID) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		long dhAmount = 0;
		int userID = 0;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			String sql = "select DhAmount,userID from NNGameManage.dbo.DhRecord where express_ID = '"+express_ID + "'";
			stmt= con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				dhAmount = rs.getLong("DhAmount");
				userID = rs.getInt("userID");
			 }
			
			sql = "update QPTreasureDB.dbo.GameScoreInfo set insureScore = insureScore + "+dhAmount +",ExchangeMoney = ExchangeMoney-"+dhAmount+" where userID = "+userID;
			ps = con.prepareStatement(sql);
			ps.execute();
			
			ps = con.prepareStatement("update NNGameManage.dbo.DhRecord set state=2,cancelType=1 where express_ID=?");
			ps.setString(1, express_ID);
			ps.execute();
			
			con.commit();
		    con.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		
	}
	
	//查找取消用户的重复次数
	public int queryCancelType(int userID,String express_ID){
		DhRecordDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cancelType = 0;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from NNGameManage.dbo.DhRecord where userID="+userID+" and express_ID='"+express_ID+"'");
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new DhRecordDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setCancelType(rs.getInt("cancelType"));
				cancelType = rs.getInt("cancelType");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return cancelType;
	}
	
	public List<DhRecordDTO> GetRecordByPage(int pageindex,int pageSize,String where) {
		List<DhRecordDTO> list = new ArrayList<DhRecordDTO>();
		Connection conn = null;
		try {
			
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
			conn = ds.getConnection();
			conn.setAutoCommit(false); // Setup the call. 
			CallableStatement toesUp = conn.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "DhRecordList_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "generateTime desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			
			while(rs.next()){
				DhRecordDTO dto = new DhRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserID(rs.getInt("userID"));
				dto.setTypeID(rs.getInt("typeID"));
				dto.setTypeName(rs.getString("typeName"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setDhAmount(rs.getLong("dhAmount"));
				dto.setMallID(rs.getInt("mallID"));
				dto.setMallName(rs.getString("mallName"));
				dto.setGenerateTime(rs.getString("generateTime").substring(0, 19));
				dto.setExpress_ID(rs.getString("express_ID"));
				dto.setCancelType(rs.getInt("CancelType"));
				dto.setState(rs.getInt("state"));
				int state = rs.getInt("state");
				if(state==0){
					dto.setStateName("待发货");
				}
				else if(state==1){
					dto.setStateName("已发货");
				}
				else if(state==2){
					dto.setStateName("已取消");
				}
				dto.setIp(rs.getString("ip"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			toesUp.close();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		finally{
			this.closeDBObject(conn, null, null, null, null);
		}
		return list;
	}
	
	
	//魅力兑换管理
	public List<DhRecordDTO> GetRecordPresentByPage(int pageindex,int pageSize,String where) {
		List<DhRecordDTO> list = new ArrayList<DhRecordDTO>();
		Connection conn = null;
		try {
			
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
			conn = ds.getConnection();
			conn.setAutoCommit(false); // Setup the call. 
			CallableStatement toesUp = conn.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "RecordConvertPresentView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "collectDate desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			
			while(rs.next()){
				DhRecordDTO dto = new DhRecordDTO();
				dto.setAccounts(rs.getString("accounts"));
				dto.setKindName(rs.getString("kindName"));
				dto.setServerName(rs.getString("serverName"));
				dto.setCurInsureScore(rs.getLong("CurInsureScore"));
				dto.setCurPresent(rs.getInt("CurPresent"));
				dto.setConvertPresent(rs.getInt("convertPresent"));
				dto.setConvertRate(rs.getInt("ConvertRate"));
				int isPlaza = rs.getInt("IsGamePlaza");
				if(isPlaza==0){
					dto.setIsGamePlaza("大厅");
				}
				else{
					dto.setIsGamePlaza("网页");
				}
				dto.setClientIP(rs.getString("ClientIP"));
				dto.setCollectDate(rs.getString("CollectDate"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			toesUp.close();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		finally{
			this.closeDBObject(conn, null, null, null, null);
		}
		return list;
	}
	
	//奖牌兑换管理
	public List<DhRecordDTO> GetRecordUserMedalByPage(int pageindex,int pageSize,String where) {
		List<DhRecordDTO> list = new ArrayList<DhRecordDTO>();
		Connection conn = null;
		try {
			
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
			conn = ds.getConnection();
			conn.setAutoCommit(false); // Setup the call. 
			CallableStatement toesUp = conn.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "RecordConvertUserMedalView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "collectDate desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			
			while(rs.next()){
				DhRecordDTO dto = new DhRecordDTO();
				dto.setAccounts(rs.getString("accounts"));
				dto.setCurInsureScore(rs.getLong("CurInsureScore"));
				
				dto.setCurUserMedal(rs.getInt("CurUserMedal"));
				dto.setConvertUserMedal(rs.getInt("ConvertUserMedal"));
				dto.setConvertRate(rs.getInt("ConvertRate"));
				int isPlaza = rs.getInt("IsGamePlaza");
				if(isPlaza==0){
					dto.setIsGamePlaza("大厅");
				}
				else{
					dto.setIsGamePlaza("网页");
				}
				dto.setClientIP(rs.getString("ClientIP"));
				dto.setCollectDate(rs.getString("CollectDate"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			toesUp.close();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		finally{
			this.closeDBObject(conn, null, null, null, null);
		}
		return list;
	}
	
}

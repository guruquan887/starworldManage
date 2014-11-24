package com.doowal.hk798.gameUser;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class GameScoreInfoDAO extends BaseDAO{

	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public GameScoreInfoDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public int getRcordCount() {
		return recordCount;
	}
	
	
	public int getTotalPage() {
		return pageCount;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	/*=====================================游戏输赢记录======================================*/
	public List<GameScoreInfoDTO> GetRecordDetailByPage(int pageindex,int pageSize,String where) {
		List<GameScoreInfoDTO> list = new ArrayList<GameScoreInfoDTO>();
		Connection con = null;
		ResultSet rs=null;
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "GameScoreGoldRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "insertTime desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameScoreInfoDTO dto = new GameScoreInfoDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setInsertTime(rs.getString("insertTime").substring(0, 19));
				dto.setKindName(rs.getString("kindName"));
				dto.setServerName(rs.getString("serverName"));
				dto.setUserCount(rs.getInt("userCount"));
				dto.setAndroidCount(rs.getInt("androidCount"));
				dto.setWaste(rs.getLong("waste"));
				dto.setRevenue(rs.getLong("revenue"));
				dto.setUsermedal(rs.getInt("UserMedal"));
				dto.setStartTime(rs.getString("startTime").substring(0, 19));
				dto.setConcludeTime(rs.getString("ConcludeTime").substring(0, 19));
				dto.setList(queryDraw(rs.getInt("drawID")));
				dto.setAccounts(rs.getString("accounts"));
				dto.setIsAndroid(rs.getInt("isAndroid"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
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
				if(toesUp!=null){
					toesUp.close();toesUp=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
		    }
		}
		return list;
	}
	
	public List<GameScoreInfoDTO> queryDraw(int drawID){
		List<GameScoreInfoDTO> list = new ArrayList<GameScoreInfoDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from GameUserGoldRecordInfoView where drawID=?");
			ps.setInt(1, drawID);
			rs = ps.executeQuery();
			while(rs.next()){
				GameScoreInfoDTO dto = new GameScoreInfoDTO();
				dto.setAccounts(rs.getString("accounts"));
				//int android = rs.getInt("IsAndroid");
				dto.setIsAndroid(rs.getInt("IsAndroid"));
				/*if(android==0){
					dto.setIsAndroid("否");
				}
				else{
					dto.setIsAndroid("是");
				}*/
				dto.setChairID(rs.getInt("chairID"));
			    dto.setScore(rs.getLong("score"));
			    dto.setGrade(rs.getLong("grade"));
			    dto.setRevenue(rs.getLong("userscoreRevenue"));
			    dto.setUsermedal(rs.getInt("userscoreMedal"));
				dto.setPlayTimeCount(rs.getInt("playTimeCount"));
				dto.setInsertTime(rs.getString("userscoreInsertTime").substring(0, 19));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	/*===========================钱庄输赢记录=====================================*/
	public List<GameScoreInfoDTO> GetBankDetailByPage(int pageindex,int pageSize,String where) {
		List<GameScoreInfoDTO> list = new ArrayList<GameScoreInfoDTO>();
		Connection con = null;
		ResultSet rs=null;
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "gameScoreRecordDetailView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "generateTime desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameScoreInfoDTO dto = new GameScoreInfoDTO();
				dto.setUserID(rs.getInt("userID"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
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
				if(toesUp!=null){
					toesUp.close();toesUp=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
		    }
		}
		return list;
	}
	
	public Long sum(String where){
		
		long totalScore = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select sum(score) from RecordDrawScore where "+where;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
			    totalScore = rs.getLong(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return totalScore;
	}
	
	public GameScoreInfoDTO getById(int userID){
		
		GameScoreInfoDTO dto = new GameScoreInfoDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from gamescoreRecord where userID=? order by winCount desc");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setUserID(rs.getInt("userID"));
				double winCount = rs.getInt("WinCount");
				double lostCount = rs.getInt("LostCount");
				double drawCount = rs.getInt("DrawCount");
				double fleeCount = rs.getInt("FleeCount");
				
				double wingl = winCount/(winCount+lostCount+drawCount+fleeCount);
				
				double fleegl = fleeCount/(winCount+lostCount+drawCount+fleeCount);
				NumberFormat nf  =  NumberFormat.getPercentInstance();
				nf.setMinimumFractionDigits( 1 );
				String win  =  nf.format(wingl);
				String flee = nf.format(fleegl);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	/*===========================用户钱庄记录===============================*/
	public List<GameBankRecordDTO> GetBankRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameBankRecordDTO> list = new ArrayList<GameBankRecordDTO>();
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
			toesUp.setString(1, "BankRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameBankRecordDTO dto = new GameBankRecordDTO();
				dto.setSourceUserID(rs.getInt("sourceUserID"));
				dto.setTargetUserID(rs.getInt("targetUserID"));
				dto.setSourceAccounts(rs.getString("sourceAccounts"));
				dto.setTargetAccounts(rs.getString("targetAccounts"));
				int tradeType = rs.getInt("TradeType");
				if(tradeType==1){
					dto.setTradeType("存");
				}
				else if(tradeType==2){
					dto.setTradeType("取");
				}
				else{
					dto.setTradeType("转账");
				}
				dto.setSourceGold(rs.getLong("sourceGold"));
				dto.setSourceBank(rs.getLong("sourceBank"));
				dto.setTargetGold(rs.getLong("targetGold"));
				dto.setTargetBank(rs.getLong("targetBank"));
				dto.setSwapScore(rs.getLong("swapScore"));
				dto.setSwapRevenue(rs.getLong("Revenue"));
				dto.setCollectDate(rs.getString("collectDate"));
				dto.setClientIP(rs.getString("clientIP"));
				int isPlaza = rs.getInt("IsGamePlaza");
				if(isPlaza==0){
					dto.setIsGamePlaza("大厅");
				}
				else if(isPlaza==1){
					dto.setIsGamePlaza("网页");
				}
				else{
					dto.setIsGamePlaza("后台");
				}
				dto.setKindName(rs.getString("kindName"));
				dto.setServerName(rs.getString("serverName"));
				dto.setCollectNote(rs.getString("collectNote"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, null);
		}
		return list;
	}
	
	
	public List<GameBankRecordDTO> GetProxyBankRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameBankRecordDTO> list = new ArrayList<GameBankRecordDTO>();
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
			toesUp.setString(1, "BankProxyRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameBankRecordDTO dto = new GameBankRecordDTO();
				dto.setSourceUserID(rs.getInt("sourceUserID"));
				dto.setTargetUserID(rs.getInt("targetUserID"));
				dto.setSourceAccounts(rs.getString("sourceAccounts"));
				dto.setTargetAccounts(rs.getString("targetAccounts"));
				int tradeType = rs.getInt("TradeType");
				if(tradeType==1){
					dto.setTradeType("存");
				}
				else if(tradeType==2){
					dto.setTradeType("取");
				}
				else{
					dto.setTradeType("转账");
				}
				dto.setSourceGold(rs.getLong("sourceGold"));
				dto.setSourceBank(rs.getLong("sourceBank"));
				dto.setTargetGold(rs.getLong("targetGold"));
				dto.setTargetBank(rs.getLong("targetBank"));
				dto.setSwapScore(rs.getLong("swapScore"));
				dto.setSwapRevenue(rs.getLong("Revenue"));
				dto.setCollectDate(rs.getString("collectDate"));
				dto.setClientIP(rs.getString("clientIP"));
				int isPlaza = rs.getInt("IsGamePlaza");
				if(isPlaza==0){
					dto.setIsGamePlaza("大厅");
				}
				else if(isPlaza==1){
					dto.setIsGamePlaza("网页");
				}
				else{
					dto.setIsGamePlaza("后台");
				}
				dto.setKindName(rs.getString("kindName"));
				dto.setServerName(rs.getString("serverName"));
				dto.setCollectNote(rs.getString("collectNote"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, null);
		}
		return list;
	}
	
	/*===========================用户充值记录===============================*/
	public List<GameBankRecordDTO> GetRechargeRecordByPage(int pageindex,int pageSize,String orderby,String where,int userID11) {
		List<GameBankRecordDTO> list = new ArrayList<GameBankRecordDTO>();
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
			toesUp.setString(1, "GameOtherRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameBankRecordDTO dto = new GameBankRecordDTO();
				int userID = rs.getInt("UserID");
				dto.setUserID(userID);
				dto.setGameID(rs.getInt("gameID"));   //游戏ID
				dto.setAccounts(rs.getString("accounts")); //用户
				dto.setAmount(rs.getString("winScore"));  //金额
				dto.setSjamount(rs.getString("LoseScore")); //实际金额
				dto.setRevenue(rs.getLong("tax"));  //税收
				dto.setTranAccounts(rs.getString("tranAccounts"));//交易用户
				dto.setGenerateTime(rs.getString("generateTime").substring(0, 19));//交易时间
				dto.setTypeName(rs.getString("remarks"));//类型
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, null);
		}
		return list;
	}
	
	
	/*===========================用户游戏进出记录===============================*/
	public List<GameBankRecordDTO> GetUserInOutRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameBankRecordDTO> list = new ArrayList<GameBankRecordDTO>();
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
			toesUp.setString(1, "RecordUserInOutView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameBankRecordDTO dto = new GameBankRecordDTO();
				int userID = rs.getInt("UserID");
				dto.setUserID(userID);
				dto.setEnterTime(rs.getString("enterTime"));
				dto.setEnterClientIP(rs.getString("enterClientIP"));
				dto.setEnterMachine(rs.getString("enterMachine"));
				dto.setKindName(rs.getString("kindName"));
				dto.setServerName(rs.getString("serverName"));
				dto.setEnterScore(rs.getLong("enterScore"));
				dto.setEnterUserMedal(rs.getInt("enterUserMedal"));
				dto.setEnterLoveliness(rs.getInt("enterLoveliness"));
				dto.setLeaveTime(rs.getString("LeaveTime"));
				dto.setLeaveClientIP(rs.getString("LeaveClientIP"));
				dto.setLeaveReason(rs.getString("LeaveReason"));
				dto.setScore(rs.getLong("score"));
				dto.setLoveliness(rs.getInt("Loveliness"));
				dto.setExperience(rs.getInt("Experience"));
				dto.setRevenue(rs.getLong("Revenue"));
				dto.setPlayTimeCount(rs.getInt("PlayTimeCount"));
				dto.setOnLineTimeCount(rs.getInt("OnLineTimeCount"));
				dto.setWinCount(rs.getInt("winCount"));
				dto.setLostCount(rs.getInt("lostCount"));
				dto.setDrawCount(rs.getInt("drawCount"));
				dto.setFleeCount(rs.getInt("fleeCount"));
				dto.setTotalCount(rs.getInt("winCount")+rs.getInt("lostCount")+rs.getInt("drawCount")+rs.getInt("fleeCount"));
				
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, null);
		}
		return list;
	}
	
	/*===========================用户游戏记录===============================*/
	public List<GameBankRecordDTO> GetUserDrawRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameBankRecordDTO> list = new ArrayList<GameBankRecordDTO>();
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
			toesUp.setString(1, "RecordDrawInfoView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameBankRecordDTO dto = new GameBankRecordDTO();
				//int userID = rs.getInt("UserID");
				dto.setDrawID(rs.getInt("drawID"));
				//dto.setUserID(userID);
				dto.setKindName(rs.getString("kindName"));
				dto.setServerName(rs.getString("serverName"));
				dto.setTableID(rs.getInt("tableID"));
				dto.setUserCount(rs.getInt("userCount"));
				dto.setAndroidCount(rs.getInt("androidCount"));
				dto.setUserMedal(rs.getInt("userMedal"));
				dto.setWaste(rs.getLong("waste"));
				dto.setRevenue(rs.getLong("revenue"));
				dto.setStartTime(rs.getString("startTime").substring(0, 19));
				dto.setConcludeTime(rs.getString("concludeTime").substring(0, 19));
				dto.setInsertTime(rs.getString("insertTime").substring(0, 19));
				//dto.setBureauID(rs.getInt("bureauID"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, null);
		}
		return list;
	}
	
	public List<GameBankRecordDTO> GetUserScoreRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameBankRecordDTO> list = new ArrayList<GameBankRecordDTO>();
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
			toesUp.setString(1, "GameUserWinlostView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameBankRecordDTO dto = new GameBankRecordDTO();
				int userID = rs.getInt("UserID");
				dto.setUserID(userID);
				dto.setKindName(rs.getString("kindName"));
				dto.setServerName(rs.getString("serverName"));
				dto.setTableID(rs.getInt("tableID"));
				//dto.setUserCount(rs.getInt("userCount"));
				//dto.setAndroidCount(rs.getInt("androidCount"));
				dto.setUserMedal(rs.getInt("userMedal"));
				dto.setWaste(rs.getLong("score"));
				dto.setRevenue(rs.getLong("revenue"));
				dto.setStartTime(rs.getString("startTime").substring(0, 19));
				dto.setConcludeTime(rs.getString("concludeTime").substring(0, 19));
				dto.setInsertTime(rs.getString("insertTime").substring(0, 19));
				dto.setBureauID(rs.getInt("bureauID"));
				dto.setAccounts(rs.getString("accounts"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, null);
		}
		return list;
	}

}

package com.doowal.hk798.bbtj;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class GameUserBBTJDAO extends BaseDAO{
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	
	public GameUserBBTJDAO(DataSource ds) {
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
	
	public GameUserBBTJDTO sum(){
		GameUserBBTJDTO dto = new GameUserBBTJDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select sum(totalScore) as totalScore,sum(winlostScore) as incomeScore,sum(revenue) as revenue,sum(CardGold) as rechargeMoney,sum(ZSGold) as GiftScore,sum(InSwapScore)-sum(OutSwapScore) as transferScore from GameUserBBTJ_View");
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setGiftScore(rs.getLong("giftScore"));
				dto.setIncomeScore(rs.getLong("incomeScore"));
				dto.setRevenue(rs.getLong("revenue"));
				dto.setTransferScore(rs.getLong("transferScore"));
				dto.setRechargeMoney(rs.getLong("rechargeMoney"));
				dto.setTotalScore(rs.getLong("totalScore"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public List<GameUserBBTJDTO> GetRecordByPage(int pageindex,int pageSize,String orderBy,String where) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "GameUserBBTJ_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setUserID(rs.getInt("UserID"));
				dto.setGameID(rs.getInt("GameID"));
				dto.setAccounts(rs.getString("Accounts")); //用户名
				dto.setRegAccounts(rs.getString("NickName")); //用户昵称
				dto.setTotalScore(rs.getLong("totalScore"));//用户总银子
				dto.setIncomeScore(rs.getLong("winlostScore")); //输赢金额
				dto.setRechargeMoney(rs.getLong("CardGold")); //充值金额
				dto.setGiftScore(rs.getLong("zsGold"));  //赠送金额
				dto.setTransferScore(rs.getLong("InSwapScore")-rs.getLong("OutSwapScore"));  //转账金额
				dto.setRevenue(rs.getLong("revenue")); //个人税收
				
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
			}
			 catch (SQLException e1) {
					e1.printStackTrace();
			 }
		}
		return list;
	}
	
	public List<GameUserBBTJDTO> GetUserRecordByPage(int pageindex,int pageSize,String orderBy,String where) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "UserGoldRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setUserID(rs.getInt("UserID"));
				dto.setChairID(rs.getInt("chairID"));
				dto.setDrawID(rs.getInt("drawID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setPlayTimeCount(rs.getInt("playTimeCount"));
				dto.setRevenue(rs.getLong("revenue"));
				dto.setScore(rs.getLong("score"));
				dto.setUserMedal(rs.getInt("Usermedal"));
				dto.setInsertTime(rs.getString("insertTime").substring(0, 19));
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
			}
			 catch (SQLException e1) {
					e1.printStackTrace();
			 }
		}
		return list;
	}
	
	public List<GameUserBBTJDTO> GetzsRecordByPage(int pageindex,int pageSize,String orderBy,String where) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "RecordZSGrantView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setUserID(rs.getInt("UserID"));
				dto.setClientIP(rs.getString("ClientIP"));
				dto.setCollectDate(rs.getString("CollectDate").substring(0, 19));
				dto.setAccounts(rs.getString("accounts"));
				dto.setCurGold(rs.getLong("curGold"));
				dto.setAddGold(rs.getLong("AddGold"));
				dto.setUserName(rs.getString("userName"));
				dto.setReason(rs.getString("reason"));
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
			}
			 catch (SQLException e1) {
					e1.printStackTrace();
			 }
		}
		return list;
	}
	
	public List<GameUserBBTJDTO> GetolRecordByPage(int pageindex,int pageSize,String orderBy,String where) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "GoldOLRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setUserID(rs.getInt("UserID"));
				dto.setGameID(rs.getInt("gameID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setOperAccounts(rs.getString("operAccounts"));
				dto.setShareName(rs.getString("shareName"));
				dto.setCardName(rs.getString("cardName"));
				dto.setSerialID(rs.getString("serialID"));
				dto.setCardPrice(rs.getDouble("CardPrice"));
				dto.setCardGold(rs.getLong("cardGold"));
				dto.setBeforeGold(rs.getLong("beforeGold"));
				dto.setCardTotal(rs.getInt("cardTotal"));
				dto.setOrderAmount(rs.getDouble("OrderAmount"));
				dto.setPayAmount(rs.getDouble("PayAmount"));
				dto.setIpAddress(rs.getString("IPAddress"));
				dto.setApplyDate(rs.getString("ApplyDate").substring(0, 19));
				
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
			}
			 catch (SQLException e1) {
					e1.printStackTrace();
			 }
		}
		return list;
	}
	
	
	public List<GameUserBBTJDTO> GetZZRecordByPage(int pageindex,int pageSize,String orderBy,String where) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "UserRecordInsureView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setCollectDate(rs.getString("CollectDate").substring(0, 19));
				int isGamePlaza = rs.getInt("IsGamePlaza");
				if(isGamePlaza==0){
					dto.setKindName(rs.getString("kindName"));
					dto.setServerName(rs.getString("serverName"));
				}
				else{
					dto.setKindName("网站");
					dto.setServerName("网站");
				}
				dto.setSourceAccounts(rs.getString("sourceAccounts"));
				dto.setTargetAccounts(rs.getString("targetAccounts"));
				dto.setSourceBank(rs.getLong("sourceBank"));
				dto.setSourceGold(rs.getLong("sourceGold"));
				dto.setTargetBank(rs.getLong("targetBank"));
				dto.setTargetGold(rs.getLong("targetGold"));
				dto.setSwapScore(rs.getLong("swapScore"));
				dto.setRevenue(rs.getLong("revenue"));
				dto.setClientIP(rs.getString("clientIP"));
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
			}
			 catch (SQLException e1) {
					e1.printStackTrace();
			 }
		}
		return list;
	}
	
	public List<GameUserBBTJDTO> GetSpreaderRecordByPage(int pageindex,int pageSize,String orderBy,String where) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "SpreaderZongTCView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				//dto.setCollectDate(rs.getString("CollectDate").substring(0, 19));
				dto.setScore(rs.getLong("xxScore"));
				dto.setAccounts(rs.getString("accounts"));
				//dto.setCollectNote(rs.getString("collectNote"));
				//dto.setInsureScore(rs.getLong("insureScore"));
				dto.setGameID(rs.getInt("GameID"));
				dto.setUserID(rs.getInt("userID"));
				dto.setChildrenID(rs.getInt("childrenID"));
				dto.setTax(rs.getInt("tax"));
				/*double a = rs.getInt("jsTax")*0.01;
				System.out.println("a:"+a);
				long sxScore = (long)((rs.getLong("score")/a))-rs.getLong("score");*/
				dto.setSxScore(rs.getLong("score"));
				int typeID = rs.getInt("typeID");
				if(typeID==1){
					dto.setTypeName("注册");
				}
				else if(typeID==2){
					dto.setTypeName("游戏在线时长");
				}
				else if(typeID==3){
					dto.setTypeName("充值赠送");
				}
				else if(typeID==4){
					dto.setTypeName("结算赠送");
				}
				else if(typeID==8){
					dto.setTypeName("股份占成");
				}
				else if(typeID==9){
					dto.setTypeName("佣金返水");
				}
				else if(typeID==10){
					dto.setTypeName("税收提成");
				}
				list.add(dto);
				if(list.size()>pageSize){
					if(pageindex*pageSize<list.size()){
						list = list.subList(pageindex*pageSize-pageSize, pageindex*pageSize);
					}
					else{
						list = list.subList(pageindex*pageSize-pageSize, list.size());
					}
				}
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
	
	
	public List<GameUserBBTJDTO> GetSpreaderDetailByPage(int pageindex,int pageSize,String orderBy,String where) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "SpreaderTCInfoView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setCollectDate(rs.getString("CollectDate").substring(0, 19));
				dto.setScore(rs.getLong("Score"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setGameID(rs.getInt("GameID"));
				dto.setUserID(rs.getInt("userID"));
				dto.setChildrenID(rs.getInt("childrenID"));
				dto.setTax(rs.getInt("tax"));
				dto.setCollectNote(rs.getString("collectNote"));
				int typeID = rs.getInt("typeID");
				if(typeID==1){
					dto.setTypeName("注册");
				}
				else if(typeID==2){
					dto.setTypeName("游戏在线时长");
				}
				else if(typeID==3){
					dto.setTypeName("充值赠送");
				}
				else if(typeID==4){
					dto.setTypeName("结算赠送");
				}
				else if(typeID==8){
					dto.setTypeName("股份占成");
				}
				else if(typeID==9){
					dto.setTypeName("佣金返水");
				}
				else if(typeID==10){
					dto.setTypeName("税收提成");
				}
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
	
	
	public GameUserBBTJDTO sumSpreaderTCRecord(int pageindex,int pageSize,String orderBy,String where){
		GameUserBBTJDTO dto = new GameUserBBTJDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "";
			sql ="SELECT SUM(Score) AS totalScore,SUM(xxScore) AS totalXxScore FROM QPTreasureDB.dbo.SpreaderZongTCView where "+where;
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setTotalScore(rs.getLong("totalScore"));
				dto.setTotalXxScore(rs.getLong("totalXxScore"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public GameUserBBTJDTO sumSpreaderSJRecord(int pageindex,int pageSize,String orderBy,String where){
		GameUserBBTJDTO dto = new GameUserBBTJDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "";
			sql ="SELECT SUM(Score) AS totalScore FROM QPTreasureDB.dbo.SpreaderTCInfoView where "+where;
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setTotalScore(rs.getLong("totalScore"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
}

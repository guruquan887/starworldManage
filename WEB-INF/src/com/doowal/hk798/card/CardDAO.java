package com.doowal.hk798.card;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.doowal.hk798.gameUser.BaseDAO;

import beartool.MD5;

public class CardDAO extends BaseDAO{
	private DataSource ds;
//	private Connection con;
//	private ResultSet rs;
//	private PreparedStatement ps;
	private int pageCount;
	private int recordCount;

	
	public CardDAO(DataSource ds) {
		super();
		this.ds = ds;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	public List<CardViewDTO> getCardType(){
		List<CardViewDTO> list = new ArrayList<CardViewDTO>();
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			con = ds.getConnection();
			 ps = con.prepareStatement("SELECT CardTypeID,CardName,CardGold,MemberDays,CardPrice FROM GlobalLivcard(nolock) ORDER BY CardTypeID");
			 rs = ps.executeQuery();
			 
			 while(rs.next()){
				 CardViewDTO dto = new CardViewDTO();
				 dto.setCardName(rs.getString("cardName"));
				 dto.setCardGold(rs.getLong("cardGold"));
				 dto.setMemberDays(rs.getInt("memberDays"));
				 dto.setCardPrice(rs.getLong("cardPrice"));
				 dto.setCardTypeID(rs.getInt("cardTypeID"));
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
				if(rs!=null){
					 rs.close();rs=null;
				 }
				 if(ps!=null){
					 ps.close();ps=null;
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
	
	 public String querySalesPerson(int buildID){
		 String salesPerson = "";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			con = ds.getConnection();
			 ps = con.prepareStatement("select SalesPerson from LivcardAssociator  where BuildID=?");
			 ps.setInt(1, buildID);
			 rs = ps.executeQuery();
			 if(rs.next()){
				 salesPerson = rs.getString("SalesPerson");
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
				if(rs!=null){
					rs.close();rs=null;
				}
				if(ps!=null){
				    ps.close();ps=null;
				}
				if(con!=null){
				    con.close();con=null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		 return salesPerson;
	 }

	public List<CardViewDTO> GetRecordByPage(int pageindex, int pageSize,
			String where) {
		List<CardViewDTO> list = new ArrayList<CardViewDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			//
			// Create Procedure [dbo].[GetRecordByPage2005]
			// @TableName varchar(50), --表名
			// @Fields varchar(5000) = '*', --字段名(全部字段为*)
			// @OrderField varchar(5000), --排序字段(必须!支持多字段)
			// @sqlWhere varchar(5000) = Null,--条件语句(不用加where)
			// @pageSize int, --每页多少条记录
			// @pageIndex int = 1 , --指定当前为第几页
			// @TotalPage int output, --返回总页数
			// @totalRecord int output
			//
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con
					.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "LivcardBuildStreamView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "BuildID desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				CardViewDTO dto = new CardViewDTO();
				dto.setBuildID(rs.getInt("buildID"));
				dto.setBuildDate(rs.getString("BuildDate").substring(0, 19));
				dto.setAdminName(rs.getString("AdminName"));
				dto.setSalesPerson(querySalesPerson(rs.getInt("buildID")));
				dto.setCardName(rs.getString("cardName"));
				dto.setBuildCount(rs.getInt("buildCount"));
				dto.setCardPrice(rs.getLong("cardPrice"));
				dto.setCardTotalPrice(rs.getInt("buildCount")*rs.getLong("cardPrice"));
				dto.setCardGold(rs.getLong("cardGold"));
				dto.setBuildAddr(rs.getString("BuildAddr"));
				dto.setDownloadCount(rs.getInt("DownloadCount"));
				dto.setNoteInfo(rs.getString("noteInfo"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	public List<CardViewDTO> GetAssocitorByPage(int pageindex, int pageSize,
			String where) {
		List<CardViewDTO> list = new ArrayList<CardViewDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			//
			// Create Procedure [dbo].[GetRecordByPage2005]
			// @TableName varchar(50), --表名
			// @Fields varchar(5000) = '*', --字段名(全部字段为*)
			// @OrderField varchar(5000), --排序字段(必须!支持多字段)
			// @sqlWhere varchar(5000) = Null,--条件语句(不用加where)
			// @pageSize int, --每页多少条记录
			// @pageIndex int = 1 , --指定当前为第几页
			// @TotalPage int output, --返回总页数
			// @totalRecord int output
			//
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con
					.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "LivcardAssociatorView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "BuildID desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				CardViewDTO dto = new CardViewDTO();
				dto.setCardID(rs.getInt("cardID"));
				dto.setSerialID(rs.getString("serialID"));
				dto.setBuildID(rs.getInt("buildID"));
				dto.setValidDate(rs.getString("validDate").substring(0, 19));
				dto.setBuildDate(rs.getString("BuildDate").substring(0, 19));
				//dto.setAdminName(rs.getString("AdminName"));
				dto.setSalesPerson(querySalesPerson(rs.getInt("buildID")));
				dto.setCardName(rs.getString("cardName"));
				//dto.setBuildCount(rs.getInt("buildCount"));
				dto.setCardPrice(rs.getLong("cardPrice"));
				dto.setMemberOrder(rs.getInt("memberOrder"));
				dto.setMemberDays(rs.getInt("memberDays"));
				//dto.setCardTotalPrice(rs.getInt("buildCount")*rs.getLong("cardPrice"));
				dto.setCardGold(rs.getLong("cardGold"));
				//dto.setBuildAddr(rs.getString("BuildAddr"));
				//dto.setDownloadCount(rs.getInt("DownloadCount"));
				//dto.setNoteInfo(rs.getString("noteInfo"));
				dto.setSalesPerson(rs.getString("SalesPerson"));
				dto.setUseRange(rs.getInt("useRange"));
				dto.setNullity(rs.getInt("Nullity"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	public List<CardViewDTO> GetCardRecordByPage(int pageindex, int pageSize,
			String where) {
		List<CardViewDTO> list = new ArrayList<CardViewDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			//
			// Create Procedure [dbo].[GetRecordByPage2005]
			// @TableName varchar(50), --表名
			// @Fields varchar(5000) = '*', --字段名(全部字段为*)
			// @OrderField varchar(5000), --排序字段(必须!支持多字段)
			// @sqlWhere varchar(5000) = Null,--条件语句(不用加where)
			// @pageSize int, --每页多少条记录
			// @pageIndex int = 1 , --指定当前为第几页
			// @TotalPage int output, --返回总页数
			// @totalRecord int output
			//
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "CardRecordInfoView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "ApplyDate desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				CardViewDTO dto = new CardViewDTO();
				dto.setApplyDate(rs.getString("applyDate").substring(0, 19));
				dto.setShareName(rs.getString("shareName"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setGameID(rs.getInt("gameID"));
				dto.setSerialID(rs.getString("serialID"));
				dto.setOrderID(rs.getString("orderID"));
				dto.setCardName(rs.getString("cardName"));
				dto.setCardPrice(rs.getLong("cardPrice"));
				dto.setCardGold(rs.getLong("cardGold"));
				dto.setBeforeGold(rs.getLong("beforeGold"));
				dto.setCardTotal(rs.getInt("cardTotal"));
				dto.setOrderAmount(rs.getLong("orderAmount"));
				dto.setPayAmount(rs.getLong("payAmount"));
				dto.setIpAddress(rs.getString("IPAddress"));
				dto.setOperAccounts(rs.getString("operAccounts"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	public List<CardViewDTO> GetOnlineOrderByPage(int pageindex, int pageSize,String where) {
		List<CardViewDTO> list = new ArrayList<CardViewDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			//
			// Create Procedure [dbo].[GetRecordByPage2005]
			// @TableName varchar(50), --表名
			// @Fields varchar(5000) = '*', --字段名(全部字段为*)
			// @OrderField varchar(5000), --排序字段(必须!支持多字段)
			// @sqlWhere varchar(5000) = Null,--条件语句(不用加where)
			// @pageSize int, --每页多少条记录
			// @pageIndex int = 1 , --指定当前为第几页
			// @TotalPage int output, --返回总页数
			// @totalRecord int output
			//
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "OnlineOrderView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "ApplyDate desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				CardViewDTO dto = new CardViewDTO();
				dto.setOnlineID(rs.getInt("onlineID"));
				dto.setApplyDate(rs.getString("applyDate").substring(0, 19));
				dto.setShareName(rs.getString("shareName"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setGameID(rs.getInt("gameID"));
				//dto.setSerialID(rs.getString("serialID"));
				dto.setOrderID(rs.getString("orderID"));
				dto.setCardName(rs.getString("cardName"));
				dto.setCardPrice(rs.getLong("cardPrice"));
				dto.setCardGold(rs.getLong("cardGold"));
				//dto.setBeforeGold(rs.getLong("beforeGold"));
				dto.setCardTotal(rs.getInt("cardTotal"));
				dto.setOrderAmount(rs.getLong("orderAmount"));
				dto.setPayAmount(rs.getLong("payAmount"));
				dto.setIpAddress(rs.getString("IPAddress"));
				dto.setOperAccounts(rs.getString("operAccounts"));
				dto.setOrderStatus(rs.getInt("orderStatus"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	public List<CardViewDTO> GetRecordByPageDealUser(int pageindex, int pageSize,String where) {
		List<CardViewDTO> list = new ArrayList<CardViewDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			//

			// Create Procedure [dbo].[GetRecordByPage2005]
			// @TableName varchar(50), --表名
			// @Fields varchar(5000) = '*', --字段名(全部字段为*)
			// @OrderField varchar(5000), --排序字段(必须!支持多字段)
			// @sqlWhere varchar(5000) = Null,--条件语句(不用加where)
			// @pageSize int, --每页多少条记录
			// @pageIndex int = 1 , --指定当前为第几页
			// @TotalPage int output, --返回总页数
			// @totalRecord int output
			//
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GameCardViews");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "id desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				CardViewDTO dto = new CardViewDTO();
				dto.setId(rs.getInt("id"));
				dto.setCardNo(rs.getString("cardNo"));
				dto.setCardTypeID(rs.getInt("cardTypeID"));
				dto.setBatchNo(rs.getInt("batchNo"));
				dto.setNullity(rs.getInt("nullity"));
				dto.setUserName(rs.getString("username"));
				String t = rs.getString("createDate");
				if (t == null)
					t = "";
				if (t.length() > 19)
					t = t.substring(0, 19);
				dto.setCreateDate(t);
				t = rs.getString("UseDate");
				if (t == null)
					t = "";
				if (t.length() > 19)
					t = t.substring(0, 19);
				dto.setUseDate(t);
				dto.setAccounts(rs.getString("accounts"));
				dto.setCardTitle(rs.getString("cardTitle"));
				dto.setScore(rs.getInt("score"));
				dto.setOverDate(rs.getInt("overDate"));
				dto.setIsPresent(rs.getInt("isPresent"));
				dto.setMemberOrder(rs.getInt("memberOrder"));
				dto.setCardPassword(rs.getString("cardPassword"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	public List<CardViewDTO> GetRecordByPagejsUser(int pageindex, int pageSize,String where) {
		List<CardViewDTO> list = new ArrayList<CardViewDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			//
			// Create Procedure [dbo].[GetRecordByPage2005]
			// @TableName varchar(50), --表名
			// @Fields varchar(5000) = '*', --字段名(全部字段为*)
			// @OrderField varchar(5000), --排序字段(必须!支持多字段)
			// @sqlWhere varchar(5000) = Null,--条件语句(不用加where)
			// @pageSize int, --每页多少条记录
			// @pageIndex int = 1 , --指定当前为第几页
			// @TotalPage int output, --返回总页数
			// @totalRecord int output
			//
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GameCardViews");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "id desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, 10);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				CardViewDTO dto = new CardViewDTO();
				dto.setId(rs.getInt("id"));
				dto.setCardNo(rs.getString("cardNo"));
				dto.setCardTypeID(rs.getInt("cardTypeID"));
				dto.setBatchNo(rs.getInt("batchNo"));
				dto.setNullity(rs.getInt("nullity"));
				dto.setUserName(rs.getString("username"));
				String t = rs.getString("createDate");
				if (t == null)
					t = "";
				if (t.length() > 19)
					t = t.substring(0, 19);
				dto.setCreateDate(t);
				t = rs.getString("UseDate");
				if (t == null)
					t = "";
				if (t.length() > 19)
					t = t.substring(0, 19);
				dto.setUseDate(t);
				dto.setAccounts(rs.getString("accounts"));
				dto.setCardTitle(rs.getString("cardTitle"));
				dto.setScore(rs.getInt("score"));
				dto.setOverDate(rs.getInt("overDate"));
				dto.setIsPresent(rs.getInt("isPresent"));
				dto.setMemberOrder(rs.getInt("memberOrder"));
				dto.setCardPassword(rs.getString("cardPassword"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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

	public int getTotalPage() {
		return pageCount;
	}

	public int getRcordCount() {
		return recordCount;
	}

	public int CreateCardType(String cardName, int score, int present,
			int memberOrder, int overDays) {
		int cardID = -1;
		Connection con = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			//con.setAutoCommit(true);
			//con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call WEB_GameCardTypeInsert(?,?,?,?,?,?) }");
			toesUp.setString(1, cardName);//
			toesUp.setInt(2, score);// 全部字段为*
			toesUp.setInt(3, overDays);// 排序字段
			toesUp.setInt(4, present);// 条件语句(不用加where)
			toesUp.setInt(5, memberOrder);// 每页多少条记录
			toesUp.registerOutParameter(6, Types.INTEGER);
			toesUp.execute();
			toesUp.getMoreResults();
			cardID = toesUp.getInt(6);

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
		return cardID;
	}

	public int CreateCard(int cardTypeId, int batchNo, int addNum) {
		int cardID = -1;
		Connection con = null;
		CallableStatement toesUp = null;
		try {
			int currentRecord = 0; // 当前生成序号

			String cardNo = ""; // 卡号
			String cardPass = ""; // 卡密明文
			String encryCardPass = ""; // 卡密密文
			int cardPassLen = 8; // 卡密长度 8
			List<String> cardsPassList = new ArrayList<String>(); // 卡密表
			List<String> cardsList = new ArrayList<String>(); // 卡
			String tmpCardPass = RandomNum(cardPassLen);
			Boolean isHas = true; // 临时卡密是否已经存在

			// 卡密生成
			for (int startCardPassId = 1; startCardPassId <= addNum; startCardPassId++) {
				while (isHas) {
					if (!cardsPassList.contains(tmpCardPass)) {
						cardsPassList.add(tmpCardPass);
						isHas = false;
					} else
						tmpCardPass = RandomNum(cardPassLen);
				}
				isHas = true;
			}
			Date d = Calendar.getInstance().getTime();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

			String tmpCardPrefix = sf.format(d); // 临时卡号前缀
			String tmpCardNoMantissa = RandomNum(cardPassLen); // 临时卡号尾数
			isHas = true; // 临时卡号是否已经存在

			// 卡号生成
			for (int startCardId = 1; startCardId <= addNum; startCardId++) {
				while (isHas) {
					if (!cardsList.contains(tmpCardNoMantissa)) {
						cardsList.add(tmpCardNoMantissa);
						isHas = false;
					} else
						tmpCardNoMantissa = RandomNum(cardPassLen);
				}
				isHas = true;
			}

			// 入库操作
			for (int startDb = 0; startDb < addNum; startDb++) {
				cardNo = tmpCardPrefix + cardsList.get(startDb);
				cardPass = cardsPassList.get(startDb);
				MD5 md5 = new MD5();

				// encryCardPass = Utils.Encrypt(cardPass);
				encryCardPass = md5.getMD5ofStr(cardPass).toLowerCase();
				try {
					System.out
							.println("cardNo:" + cardNo + "," + encryCardPass
									+ "," + cardPass + "," + cardTypeId + ","
									+ batchNo);
//					 currentRecord = CreateCardInsert(cardNo, encryCardPass,
//					 cardPass, cardTypeId, batchNo);
					con = ds.getConnection();
					//con.setAutoCommit(false); // Setup the call.
					toesUp = con.prepareCall("{  call WEB_GameCardInsert(?,?,?,?,?) }");
					toesUp.setString(1, cardNo);//
					toesUp.setString(2, encryCardPass);// 全部字段为*
					toesUp.setString(3, cardPass);// 排序字段
					toesUp.setInt(4, cardTypeId);// 条件语句(不用加where)
					toesUp.setInt(5, batchNo);// 每页多少条记录
					toesUp.execute();
					if(toesUp!=null){
						toesUp.close();toesUp=null;
					}
					if(con!=null){
						con.close();con=null;
					}

				} catch (Exception e) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardID;
	}

	public String RandomNum(int length) {

		Random r = new Random();
		String s = "";
		String str = "0123456789";

		for (int i = 0; i < length; i++) {
			int t = ((r.nextInt() % 10) + 10) % 10;
			s += str.substring(t, t + 1);
		}

		return s;
	}
	
	public void delete(String id) {
		Connection con = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			//con.setAutoCommit(false);
			toesUp = con.prepareCall("{  call WEB_GameCardDelete(?) }");
			toesUp.setString(1, id);
			toesUp.execute();
			toesUp.getMoreResults();
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
	}
	
	public void updateNullity(String id,int type) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			//con.setAutoCommit(false);
			ps = con.prepareStatement("Update LivcardAssociator Set Nullity=? where cardID=?");
			ps.setInt(1, type);
			ps.setString(2, id);
			ps.execute();
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
	}
	
	public boolean addBeizhu(String ids,String beizhu){
		boolean r = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update QPTreasureDB.dbo.GameCardNoInfo set beizhu = ? where id = ?");
			ps.setString(1, beizhu);
			ps.setString(2, ids);
			ps.execute();
			r = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public CardViewDTO queryMemberOrder(String cardTypeID){
		CardViewDTO dto = new CardViewDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from GlobalLivcard where CardTypeID=?");
			ps.setString(1, cardTypeID);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setMemberOrder(rs.getInt("memberOrder"));
				dto.setUserRight(rs.getInt("UserRight"));
				dto.setServiceRight(rs.getInt("ServiceRight"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public String BuildStreamAdd(String[] params) {
		String buildID = "";
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call WSP_PM_BuildStreamAdd (?,?,?,?,?,?,?,?)}";
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
				if(i==params.length-1){
					byte[] bt = new byte[params[i].length()]; 
					bt = params[i].toString().getBytes();
					ByteArrayInputStream  iis = new ByteArrayInputStream(bt);
					callstmt.setBinaryStream(i + 1, iis,bt.length);

				}
			}
			rs = callstmt.executeQuery();
			if(rs.next()){
				buildID = rs.getString(1);
			}
			System.out.println(">>>>>>buildID:"+buildID);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return buildID;
	}
	
	public boolean LivcardAdd(String[] params) {
		boolean r = false;
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call WSP_PM_LivcardAdd (?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			callstmt.execute();
			r = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return r;
	}
	
	/*====================================导出Excel================================*/
	public Boolean excel(String targetfile,String where,String ids,HttpServletResponse res){
		Boolean r=false;
		Statement stat=null;
		ResultSet rs=null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try 
		{ 
			
			con = ds.getConnection();
			sql = "update LivcardBuildStream set DownLoadCount=DownLoadCount+1 Where BuildID=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ids);
			ps.execute();
			
			int cardTypeID = 0;
			int cardCount = 0;
			String[] arrlist = new String[]{};
			stat=con.createStatement();
			sql="select * from LivcardBuildStream where " +where;
			System.out.println(sql);
			rs = stat.executeQuery(sql);
			while (rs.next()){  
				cardTypeID = rs.getInt("cardTypeID");
				cardCount = rs.getInt("buildCount");
				InputStream in = rs.getBinaryStream("BuildCardPacket");
		        StringBuilder sb = new StringBuilder();       
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in)); 
		        String line = null;
		        while ((line = reader.readLine()) != null) {       
	                 sb.append(line + "\n");       
	             }       

				String str= new String(sb.toString().getBytes(),"UTF-8");
				//str = str.replaceAll(" ", "");
				System.out.println("str:"+str);
				arrlist = str.split("/");
			}
			
			String cardName = "";
			sql = "SELECT CardName FROM GlobalLivcard(nolock)  WHERE CardTypeID="+cardTypeID;
			stat=con.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()){  
				cardName = rs.getString("cardName");
			}
			
/*			String cardInfo = "第"+ids+"批次("+cardName+")"+" "+cardCount+"张"+"\t";
			cardInfo = cardInfo+ "======================================"+"\t";
			cardInfo = cardInfo+ "卡号,密码"+"\t";
			for(int i=0;i<arrlist.length;i++){
				cardInfo = cardInfo+ arrlist[i]+"\t";
			}*/
			
			res.setContentType("text/plain;charset=UTF-8");
			res.setHeader("Content-disposition", "attachment; filename="+ java.net.URLEncoder.encode(targetfile, "UTF-8"));
			BufferedWriter bWriter = new BufferedWriter(res.getWriter());
			
			bWriter.write("第"+ids+"批次("+cardName+")"+" "+cardCount+"张" + "\r\n");
			bWriter.write("======================================" + "\r\n");
			bWriter.write("卡号,密码" + "\r\n");
			for(int i=0;i<arrlist.length;i++){
				bWriter.write(arrlist[i] + "\r\n");
			}
			
		/*	ServletOutputStream sout = res.getOutputStream();  
			InputStream in = rs.getBinaryStream(1);  
			byte b[] = new byte[0x7a120];  
			for(int i = in.read(b); i != -1;)  {  
				sout.write(b);   //将缓冲区的输入输出到页面  in.read(b);  }  
			}*/
			
			bWriter.flush();
			bWriter.close();
			res.getWriter().close();

			
			r=true;
			if(rs!=null){
				rs.close();
			}
			if(stat!=null){
				stat.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					rs = null;
				}
			} catch (Exception e1) {
			}
		}
		return r;
	}
	
	public HttpServletResponse download(String path, HttpServletResponse response,String targetName) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            @SuppressWarnings("unused")
			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            targetName = new String(targetName.getBytes("UTF-8"),"ISO_8859_1"); 
            response.addHeader("Content-Disposition", "attachment;filename=" + targetName);//new String(targetName.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
	
	public String GetRandomNum(int cardLength){
		int i = 0;
		System.out.println("RandomNumLength:"+cardLength);
		String rValue = "";
		for(i =1 ;i<=cardLength;i++){
			rValue = rValue + GetRand(1,9);
		}
		System.out.println("GetRandomNum;Rvalue:"+rValue);
		return rValue;
	}
	
	public int GetRand(int min,int max){
		int rValue = 0;
		rValue = ((int)((max - min + 1) * Math.random()) + min);
		System.out.println("GetRand;Rvalue:"+rValue);
		return rValue;
		
	}
	
	public String GetRandomType(int pwdType,int pwdLength){
		int i = 0;
		String rValue = "";
		String strInfo = "";
		if(pwdType==1){
			strInfo = "123456789";
		}
		else if(pwdType==2){
			strInfo = "abcdefghijklmnopqrstuvwxyz";
		}
		else if(pwdType==3){
			strInfo = "123456789abcdefghijklmnopqrstuvwxyz";
		}
		else if(pwdType==4){
			strInfo = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}
		else if(pwdType==5){
			strInfo = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}
		else if(pwdType==6){
			strInfo = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}
		else if(pwdType==7){
			strInfo = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}
		else{
			strInfo = "123456789";
		}
		System.out.println("pwdLength:"+pwdLength);
		for(i=0;i<pwdLength;i++){
			int iss = GetRand(1,strInfo.length());
			if(iss>strInfo.length()){
				iss = strInfo.length()-2;
			}
			rValue = rValue+strInfo.substring(iss-1,iss);
		}
		System.out.println("GetRandomType;Rvalue:"+rValue);
		return rValue;
	}
	
}

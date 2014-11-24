package com.doowal.struts.single;

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


public class NoteDAO {
	
	
	private DataSource ds;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int pageCount;
	private int recordCount;
	
	public NoteDAO(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	public String queryRoomName(int roomId){
		String sql = "select * from QPServerInfoDB.dbo.GameKindItem where kindID="+roomId;
		Connection con = null;
		ResultSet rs = null;
		String roomName = "";
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			if(rs.next()){
				GameOpenCloseDTO gameKind = new GameOpenCloseDTO();
				gameKind.setGameID(rs.getInt("kindID"));
				gameKind.setDisplayName(rs.getString("kindName"));
//				gameKind.setGameopenclose(rs.getInt("gameOpenClose"));
				roomName = rs.getString("kindName");
			}
			
			if(rs!=null){
				rs.close();rs=null;
			}
			if(con!=null){
				con.close();con=null;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			try {
				if(rs!=null){
					rs.close();rs=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return roomName;
	}
	
	public Boolean checkExist(String peroidnum){
		Boolean r=false;
		try {
			con = ds.getConnection();
			Statement st = con.createStatement();
			String sql="select * from gamePeroidnumResult where peroidnum='"+peroidnum+"'";
			
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				r=true;
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public List<NoteDTO> GetRecordPetRealTimeByPage(int pageindex,int pageSize,String orderby,String where) {
		List<NoteDTO> list = new ArrayList<NoteDTO>();
		CallableStatement toesUp = null;
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
			con = ds.getConnection(); 
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?)}"); 
			toesUp.setString(1, "RecordPetBureauView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				NoteDTO dto = new NoteDTO();
				dto.setBureauID(rs.getInt("bureauID"));
				dto.setServerName(rs.getString("serverName"));
				dto.setTableID(rs.getInt("serverID"));
				dto.setWinAreas(rs.getString("winAreas"));
				dto.setPrevWinAreas(rs.getString("prevWinAreas"));
				dto.setCardData(rs.getString("cardData"));
				dto.setScore(rs.getLong("score"));
				dto.setLeaveDataTime(rs.getString("LeaveDataTime").substring(0, 19));
				int status = rs.getInt("status");
				dto.setStatus(status);
				dto.setBetCount(rs.getInt("betCount"));
				
				NoteDTO dto1 = sumBet(rs.getInt("bureauID"));
				dto.setzScore(dto1.getzScore());
				dto.setxScore(dto1.getxScore());
				if(status==0){
					dto.setStatusName("等待中");
				}
				else if(status==1){
					dto.setStatusName("投注中");
				}
				else if(status==2){
					dto.setStatusName("派彩中");
				}
				else{
					dto.setStatusName("已结束");
				}
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(toesUp!=null){
				toesUp.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
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
	
	public NoteDTO sumBet(int bureauID){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		NoteDTO dto = new NoteDTO();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select sum(score) from RecordPetRealTime where area='庄' and bureauID=?");
			ps.setInt(1, bureauID);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setzScore(rs.getLong(1));
			}
			
			ps = con.prepareStatement("select sum(score) from RecordPetRealTime where area='闲' and bureauID=?");
			ps.setInt(1, bureauID);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setxScore(rs.getLong(1));
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
		return dto;
	}
	
	public List<NoteDTO> GetRecordByPage(int pageindex,int pageSize,String where,int num) {
		List<NoteDTO> list = new ArrayList<NoteDTO>();
		CallableStatement toesUp = null;
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
			con = ds.getConnection(); 
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?)}"); 
			toesUp.setString(1, "JSZD_GameRecord_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "betSerial desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				NoteDTO dto = new NoteDTO();
				dto.setRoomId(rs.getInt("kindID"));
				dto.setPeroidnum(rs.getString("betSerial"));
				dto.setTotalGoldBet(rs.getLong("totalGoldBet"));
				dto.setCountNumber(rs.getInt("countNumber"));
//				dto.setCreateTime(rs.getString("generateTime").substring(0, 19));
				dto.setCreateTime(rs.getString("generateTime"));
				String [] color = new String[9];
				if(rs.getLong("totalGoldBet")>=num){
					color[0] = "#CC3300";
				}
				dto.setColor(color);
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(toesUp!=null){
				toesUp.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
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
	
	public List<NoteDTO> GetRecordByPage3D(int pageindex,int pageSize,String where,int num) {
		List<NoteDTO> list = new ArrayList<NoteDTO>();
		CallableStatement toesUp = null;
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
			con = ds.getConnection(); 
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?)}"); 
			toesUp.setString(1, "TotalGameRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "peroidnum desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				NoteDTO dto = new NoteDTO();
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				int roomId = rs.getInt("roomId");
				String peroidnum = rs.getString("peroidnum");
				String [] count = new String[27];
				dto = count3DShiShiLe(ds,peroidnum,roomId,num);
				dto.setCount(count);
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(toesUp!=null){
				toesUp.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
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
	
	
	public NoteDTO GetAll3DRecord(int roomId,String peroidnum,int num){
		NoteDTO dto = null;
		try {
			con = ds.getConnection();
			String sql = "select * from gamePeroidnumResult where roomId="+roomId+" and peroidnum='"+peroidnum+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new NoteDTO();
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				String [] count = new String[28];
				dto = count3DShiShiLe(ds,peroidnum,roomId,num);
			}
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public NoteDTO count3DShiShiLe(DataSource ds,String peroidnum,int roomId,int num){
		NoteDTO dto = new NoteDTO();
		String [] arr = new String[28];
		String sql = "";
		try {
			con = ds.getConnection();
			for(int r=0;r<28;r++){
				if(r==0){
					sql = "select sum(convert(bigint,totalGoldBet)) from GamePickRecord where peroidnum='"+peroidnum+"' and roomId="+roomId;
				}
				else{
				    sql = "select sum(convert(bigint,totalGoldBet)) from GamePickRecord where type="+r+"and peroidnum='"+peroidnum+"' and roomId="+roomId;
				}
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					if(!"".equals(String.valueOf(rs.getLong(1)))){
						arr[r] = String.valueOf(rs.getLong(1));
					}
					else{
						arr[r] = "无下注银子";
					}
				}
			}
			dto.setCount(arr);
			String [] color = new String[28]; 
			for(int i = 0;i<28;i++){
				if(Long.parseLong(arr[i])>num){
					color[i] = "#CC6600";
				}
				else{
					color[i] = "";
				}
			}
			dto.setColor(color);
/*			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
			*/
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			}catch (SQLException e1) {
				e1.printStackTrace();
				}
		}
		return dto;
		
	}
	
	public List<NoteDTO> queryRoomID(){
		List<NoteDTO> list = new ArrayList<NoteDTO>();
		String sql = "select * from QPServerInfoDB.dbo.GameKindItem";
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				NoteDTO gameKind = new NoteDTO();
				gameKind.setRoomId(rs.getInt("kindID"));
				gameKind.setRoomName(rs.getString("kindName"));
				list.add(gameKind);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public String queryRoomID(int roomId){
		String roomName = "";
		String sql = "select * from QPServerInfoDB.dbo.GameKindItem where kindID="+roomId;
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			if(rs.next()){
				roomName = rs.getString("kindName");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return roomName;
	}
	
	public String queryMaxPeroidnum(int roomId){
		String peroidnum = "";
		String sql = "select max(peroidnum) from gamePeroidnumResult where roomId="+roomId;
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			if(rs.next()){
				peroidnum = rs.getString(1);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peroidnum;
	}
	
	
	
	public int getTotalPage() {
		return pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	
	public Boolean Delete(int roomId,String peroidnum) {
		Boolean r=false;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from gamePeroidnumResult where roomId=? and peroidnum=?");
			GameUserDTO dto = new GameUserDTO();
			ps.setInt(1, roomId);
			ps.setString(2, peroidnum);
			ps.execute();
			r=true;
			
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	
	public List<NoteDTO> GetRecordDetails(int pageindex,int pageSize,String where) {
		List<NoteDTO> list = new ArrayList<NoteDTO>();
		CallableStatement toesUp = null;
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
			con = ds.getConnection(); 
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?)}"); 
			toesUp.setString(1, "JSZD_GameBetRecord_View");//表名视图
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "accounts desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				NoteDTO dto = new NoteDTO();
				dto.setRoomId(rs.getInt("kindId"));
				dto.setRoomName(rs.getString("kindName"));
				dto.setPeroidnum(rs.getString("betSerial"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setGoldBet(rs.getLong("betGold"));
				dto.setBetGameArea(rs.getString("gameAreaName"));
				String createTime ="";
				String createTime1 ="";
				if(null != rs.getString("generateTime") && !"".equals(rs.getString("generateTime"))){
					createTime = rs.getString("generateTime").substring(0, 19);
					}
				if(null != rs.getString("generateTime1") && !"".equals(rs.getString("generateTime1"))){
					createTime1 = rs.getString("generateTime1").substring(0, 19);
					}
				dto.setCreateTime(createTime);
				dto.setCreateTime1(createTime1);
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(toesUp!=null){
				toesUp.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
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
	
	
}
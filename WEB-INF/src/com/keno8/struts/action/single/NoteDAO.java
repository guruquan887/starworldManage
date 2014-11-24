package com.keno8.struts.action.single;

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

import com.keno8.struts.action.gameopenclose.GameOpenCloseDTO;
import com.keno8.struts.dto.GameUserDTO;

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
		String sql = "select * from rooms where ID="+roomId;
		Connection con = null;
		ResultSet rs = null;
		String roomName = "";
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			if(rs.next()){
				GameOpenCloseDTO gameKind = new GameOpenCloseDTO();
				gameKind.setGameID(rs.getInt("ID"));
				gameKind.setDisplayName(rs.getString("displayName"));
				gameKind.setGameopenclose(rs.getInt("gameOpenClose"));
				roomName = rs.getString("displayName");
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
	
	public List<NoteDTO> GetRecordByPage(int pageindex,int pageSize,String where,int num,String orderBy) {
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
			toesUp.setString(1, "TotalGameRecord_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
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
				dto.setTotalGoldBet(rs.getLong("totalGoldBet"));
				dto.setTotalSingle(rs.getLong("totalSingle"));
				dto.setTotalPairs(rs.getLong("totalPairs"));
				dto.setTotalLarge(rs.getLong("totalLarge"));
				dto.setTotalSmall(rs.getLong("totalSmall"));
				dto.setTotalSerpent(rs.getLong("totalSerpent"));
				dto.setTotalPeace(rs.getLong("totalPeace"));
				dto.setTotalTiger(rs.getLong("totalTiger"));
				dto.setTotalRate(rs.getLong("totalRate"));
				dto.setCreateTime(rs.getString("createTime").substring(0, 19));
				String [] color = new String[9];
				if(rs.getLong("totalGoldBet")>=num){
					color[0] = "#CC3300";
				}
				if(rs.getLong("totalSingle")>=num){
					color[1] = "#CC3300";
				}
				if(rs.getLong("totalPairs")>=num){
					color[2] = "#CC3300";
				}
				if(rs.getLong("totalLarge")>=num){
					color[3] = "#CC3300";
				}
				if(rs.getLong("totalSmall")>=num){
					color[4] = "#CC3300";
				}
				if(rs.getLong("totalSerpent")>=num){
					color[5] = "#CC3300";
				}
				if(rs.getLong("totalPeace")>=num){
					color[6] = "#CC3300";
				}
				if(rs.getLong("totalTiger")>=num){
					color[7] = "#CC3300";
				}
				if(rs.getLong("totalRate")>=num){
					color[8] = "#CC3300";
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
	
	public List queryRoomID(){
		List<NoteDTO> list = new ArrayList<NoteDTO>();
		String sql = "select * from rooms";
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				NoteDTO gameKind = new NoteDTO();
				gameKind.setRoomId(rs.getInt("ID"));
				gameKind.setRoomName(rs.getString("displayName"));
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
		String sql = "select * from rooms where ID="+roomId;
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			if(rs.next()){
				roomName = rs.getString("displayName");
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
	
	public List queryPeroidnum(int roomId){
		List<NoteDTO> list = new ArrayList<NoteDTO>();
		String sql = "select top 10 * from gamePeroidnumResult where roomId="+roomId+" order by peroidnum desc";
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				NoteDTO dto = new NoteDTO();
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				list.add(dto);
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
			toesUp.setString(1, "gameKeno8Details");//表名视图
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "createTime desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				NoteDTO dto = new NoteDTO();
				dto.setRoomId(rs.getInt("roomId"));
				dto.setRoomName(rs.getString("displayName"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setGoldBet(rs.getLong("totalGoldBet"));
				dto.setSingle(rs.getLong("single"));
				dto.setPairs(rs.getLong("pairs"));
				dto.setLarge(rs.getLong("large"));
				dto.setSmall(rs.getLong("small"));
				dto.setSerpent(rs.getLong("serpent"));
				dto.setPeace(rs.getLong("peace"));
				dto.setTiger(rs.getLong("tiger"));
				dto.setRate(rs.getLong("rate"));
				dto.setCreateTime(rs.getString("createTime").substring(0, 19));
				dto.setCreateTime1(rs.getString("createTime1").substring(0, 19));
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
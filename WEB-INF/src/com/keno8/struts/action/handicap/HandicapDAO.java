package com.keno8.struts.action.handicap;

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

public class HandicapDAO {
	
	
	private DataSource ds;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int pageCount;
	private int recordCount;
	
	public HandicapDAO(DataSource ds) {
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
	
	public Boolean checkExist(String peroidnum,int roomId){
		Boolean r=false;
		try {
			con = ds.getConnection();
			Statement st = con.createStatement();
			String sql="select * from gamePeroidnumResult where peroidnum='"+peroidnum+"' and roomId="+roomId;
			
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
	
	public List<HandicapDTO> GetRecordByPage(int pageindex,int pageSize,String where,String orderBy) {
		List<HandicapDTO> list = new ArrayList<HandicapDTO>();
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
			//con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?)}"); 
			toesUp.setString(1, "gamePeroidnumResult");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				HandicapDTO dto = new HandicapDTO();
				dto.setId(rs.getInt("id"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setOpenHandTime(rs.getString("createTime").substring(0, 19));
				//dto.setOpenLotteryTime(rs.getString("openTime").substring(0,19));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setRetroType(rs.getInt("retroType"));
				dto.setLotteryType(rs.getInt("lotteryType"));
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
	
	@SuppressWarnings("unchecked")
	public List queryRoomID(){
		List<HandicapDTO> list = new ArrayList<HandicapDTO>();
		String sql = "select * from rooms";
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				HandicapDTO gameKind = new HandicapDTO();
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
	
	public void add(HandicapDTO dto) {

		try {
			con = ds.getConnection();
			ps = con.prepareStatement("insert into gamePeroidnumResult(roomId,peroidnum,awardnum,createTime,openTime,retroType,lotteryType) values(?,?,'0',?,?,1,1)");
            ps.setInt(1, dto.getRoomId());
            ps.setString(2, dto.getPeroidnum());
            ps.setString(3, dto.getOpenHandTime());
            ps.setString(4, dto.getOpenLotteryTime());
			ps.execute();
			System.out.println("插入成功!");
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
			try {
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
	}

}

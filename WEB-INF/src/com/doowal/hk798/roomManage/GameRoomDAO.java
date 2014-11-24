package com.doowal.hk798.roomManage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.doowal.hk798.gameUser.BaseDAO;

public class GameRoomDAO extends BaseDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public GameRoomDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public int getPageCount() {
		return pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}
	
	public List<GameRoomDTO> getGameRoomList(){
		
		List<GameRoomDTO> list = new ArrayList<GameRoomDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				String sql = "select * from QPPlatformDB.dbo.GameRoomInfo where kindID in(104,105,1001)";
				System.out.println(" 执行sql:"+sql);
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					GameRoomDTO dto = new GameRoomDTO();
					dto.setRoomID(rs.getInt("serverID"));
					dto.setRoomName(rs.getString("serverName"));
					list.add(dto);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				this.closeDBObject(con, rs, null, null, ps);
			}
		return list;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("抱歉，没有找到相符的房间信息。");
		return str.toString();
	}
	
	public List<GameRoomDTO> getGameRoomDTOList(int pageindex,int pageSize,String where,String orderby){
		
		List<GameRoomDTO> list = new ArrayList<GameRoomDTO>();
		GameRoomDTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			
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
			//con.setAutoCommit(false); // Setup the call. 
			
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "ServerRoom");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby);//排序字段
			toesUp.setString(4,where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			
			while(rs.next()){
				dto = new GameRoomDTO();
				dto.setRoomID(rs.getInt("serverID"));
				dto.setRoomName(rs.getString("roomName"));
				dto.setRoomState(rs.getInt("roomState"));
				int state = rs.getInt("roomState");
				if(state == 1){
					dto.setRoomStateName("已关闭");
					dto.setRoomStateCss("colorblue");
				}else if(state == 0){
					dto.setRoomStateName("已开启");
					dto.setRoomStateCss("colorgreen");
				}
				dto.setTax(rs.getDouble("tax"));
				dto.setAndroidUserScore(rs.getString("androidUserScore"));
				dto.setMaximum(rs.getInt("maximum"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, toesUp, null);
		}
		
		return list;
	}
	

//	public List<GameRoomDTO> getGameRoomDTOList(){
//		
//		List<GameRoomDTO> list = new ArrayList<GameRoomDTO>();
//		GameRoomDTO dto = null;
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//			try {
//				String sql = "select * from QPTreasureDB.dbo.ServerRoom";
//				System.out.println(" 执行sql:"+sql);
//				con = ds.getConnection();
//				ps = con.prepareStatement(sql);
//				rs = ps.executeQuery();
//				while (rs.next()) {
//						dto = new GameRoomDTO();
//						dto.setRoomID(rs.getInt("serverID"));
//						dto.setRoomName(rs.getString("roomName"));
//						dto.setRoomState(rs.getInt("roomState"));
//						int state = rs.getInt("roomState");
//						if(state == 1){
//							dto.setRoomStateName("已开启");
//							dto.setRoomStateCss("green");
//						}else if(state == 0){
//							dto.setRoomStateName("已关闭");
//							dto.setRoomStateCss("red");
//						}
//						dto.setTax(rs.getDouble("tax"));
//						dto.setAndroidUserScore(rs.getString("androidUserScore"));
//						dto.setMaximum(rs.getInt("maximum"));
//						list.add(dto);
//					}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			finally{
//				this.closeDBObject(con, rs, null, null, ps);
//			}
//	
//			return list ;
//	}
	
	public GameRoomDTO getGameRoomDTO(String id){
		
		GameRoomDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				String sql = "select * from QPTreasureDB.dbo.ServerRoom where serverID ='" + id+"'";
				System.out.println(" 执行sql:"+sql);
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					    dto = new GameRoomDTO();
						dto.setRoomID(rs.getInt("serverID"));
						dto.setRoomName(rs.getString("roomName"));
						dto.setRoomState(rs.getInt("roomState"));
						int state = rs.getInt("roomState");
						if(state == 1){
							dto.setRoomStateName("已开启");
							dto.setRoomStateCss("green");
						}else if(state == 0){
							dto.setRoomStateName("已关闭");
							dto.setRoomStateCss("red");
						}
						dto.setTax(rs.getDouble("tax"));
						dto.setAndroidUserScore(rs.getString("androidUserScore"));
						dto.setMaximum(rs.getInt("maximum"));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				this.closeDBObject(con, rs, null, null, ps);
			}
		return dto;
	}
	
	public boolean update(GameRoomDTO dto){
		
		boolean bo = false;
		Connection con = null;
		PreparedStatement ps = null;
			try {
				String sql = "update QPTreasureDB.dbo.ServerRoom set roomName='"+dto.getRoomName()+"', androidUserScore="+dto.getAndroidUserScore()
				+", maximum ="+dto.getMaximum()+", tax="+dto.getTax()+", roomState ="+dto.getRoomState()+" where serverID ="+dto.getRoomID();
				System.out.println(" 执行sql:"+sql);
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.execute();
				bo = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				this.closeDBObject(con, null, null, null, ps);
			}
		return bo;
	}
	
	public String updateState(int roomID,int roomState){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update QPTreasureDB.dbo.ServerRoom set roomState=? where serverID=?");
			ps.setInt(1, roomState);
			ps.setInt(2, roomID);
			ps.execute();
			msg = "操作成功!";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, null, null, null, ps);
		}
		return msg;
	}

	

}

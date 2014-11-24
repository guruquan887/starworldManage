package com.keno8.struts.action.gameopenclose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class GameOpenCloseDAO {
	private DataSource ds;
	//private Connection con;
	//private ResultSet rs;
	//private PreparedStatement ps;
	
	public GameOpenCloseDAO(DataSource ds) {
		this.ds = ds;
	}

	public List queryDataRoomID(){
		List<GameOpenCloseDTO> list = new ArrayList<GameOpenCloseDTO>();
		String sql = "select * from rooms";
		Connection con = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				GameOpenCloseDTO gameKind = new GameOpenCloseDTO();
				gameKind.setGameID(rs.getInt("ID"));
				gameKind.setDisplayName(rs.getString("displayName"));
				gameKind.setHandicap(rs.getInt("handicap"));
				int handicap = rs.getInt("handicap");
				if(handicap==0){
					gameKind.setHandicapName("游戏服务器自动获取状态");
				}
				else{
					gameKind.setHandicapName("游戏后台录入手动获取状态");
				}
				list.add(gameKind);
			}
			
			if(rs!=null){
				rs.close();
			}
			if(con!=null){
				con.close();
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
	
	public List queryRoomID(){
		List<GameOpenCloseDTO> list = new ArrayList<GameOpenCloseDTO>();
		String sql = "select * from rooms";
		Connection con = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				GameOpenCloseDTO gameKind = new GameOpenCloseDTO();
				gameKind.setGameID(rs.getInt("ID"));
				gameKind.setDisplayName(rs.getString("displayName"));
				gameKind.setGameopenclose(rs.getInt("gameOpenClose"));
				gameKind.setDataInter(rs.getString("dataInter"));
				int gameopenclose = rs.getInt("gameOpenClose");
				if(gameopenclose==0){
					gameKind.setGameopencloseName("游戏开启状态");
				}
				else{
					gameKind.setGameopencloseName("游戏关闭状态");
				}
				list.add(gameKind);
			}
			
			if(rs!=null){
				rs.close();
			}
			if(con!=null){
				con.close();
			}
		}catch(SQLException ex){
			ex.printStackTrace();
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
	
	public void ChangeState(int gameID,int targetState) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update rooms set gameOpenClose=? where ID=?");
			ps.setInt(1, targetState);
			ps.setInt(2, gameID);
			ps.execute();
			
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public void updateAddress(int gameID,String dataInter) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update rooms set dataInter=? where ID=?");
			ps.setString(1, dataInter);
			ps.setInt(2, gameID);
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
	
	public void ChangeData(int gameID,int dataState) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update rooms set handicap=? where ID=?");
			ps.setInt(1, dataState);
			ps.setInt(2, gameID);
			ps.execute();
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
}
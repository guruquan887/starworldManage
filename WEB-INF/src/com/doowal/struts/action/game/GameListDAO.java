package com.doowal.struts.action.game;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class GameListDAO {
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public GameListDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public GameListDTO getProduceById(String id){
		GameListDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				String sql = "select * from QPPlatformManagerDB.dbo.gameList where id =" + id;
				System.out.println(" 执行sql:"+sql);
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					    dto = new GameListDTO();
					    dto.setId(rs.getInt("id"));
					    dto.setGameName(rs.getString("gameName"));
					    dto.setGameDes(rs.getString("gameDes"));
					    dto.setGamephoto2(rs.getString("gamephoto2"));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return dto;
		
	}

	public List<GameListDTO> GetRecordByPage(int pageindex,int pageSize,String where) {
		List<GameListDTO> list = new ArrayList<GameListDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
	    ResultSet rs = null;
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
			toesUp.setString(1, "gameList");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "id desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameListDTO dto = new GameListDTO();
				dto.setId(rs.getInt("id"));
				dto.setGameName(rs.getString("gameName"));
				dto.setGameDes(rs.getString("gameDes"));
				dto.setGamephoto2(rs.getString("gamephoto2"));
				dto.setGameType(rs.getInt("gameType"));
				dto.setGameUrl(rs.getString("gameUrl"));
				dto.setIsRec(rs.getInt("isRec"));
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

	public int getTotalPage() {
		return pageCount;
	}
	
	public int getRcordCount() {
		return recordCount;
	}
	
	public GameListDTO preUpdate(int id){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GameListDTO dto = new GameListDTO();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from gameList where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setGameName(rs.getString("gameName"));
				dto.setGameDes(rs.getString("gameDes"));
				dto.setGameUrl(rs.getString("gameUrl"));
				dto.setGamephoto2(rs.getString("gamephoto2"));
				dto.setGameType(rs.getInt("gameType"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return  dto;
	}
	
	public void update(GameListDTO dto){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update gameList set gameName=?,gameDes=?,gameUrl=?,gamephoto2=?,gameType=? where id=?");
			ps.setString(1, dto.getGameName());
			ps.setString(2, dto.getGameDes());
			ps.setString(3, dto.getGameUrl());
			ps.setString(4, dto.getGamephoto2());
			ps.setInt(5, dto.getGameType());
			ps.setInt(6, dto.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String updateIsRec(int id,int isRec){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update gameList set isRec=? where id=?");
			ps.setInt(1, isRec);
			ps.setInt(2, id);
			ps.execute();
			msg = "操作成功！";
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	
	

	public void add(GameListDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("insert into gameList(gameName,gameDes,gamephoto2,gameUrl,gameType) values(?,?,?,?,?)");
			ps.setString(1, dto.getGameName());
			ps.setString(2, dto.getGameDes());
			ps.setString(3, dto.getGamephoto2());
			ps.setString(4, dto.getGameUrl());
			ps.setInt(5, dto.getGameType());
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
	
	public void delete(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from gameList where id = ?");
			ps.setString(1, id);
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
	

}

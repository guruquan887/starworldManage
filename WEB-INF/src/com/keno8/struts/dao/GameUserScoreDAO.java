package com.keno8.struts.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.keno8.struts.dto.GameUserScoreDTO;

public class GameUserScoreDAO {
	private int pageCount;
	private int recordCount;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private final String SQL_SELECT="select * from gameuserRank order by score desc";

	public GameUserScoreDAO(Connection con) {
		super();
		this.con = con;
	}
	public List<GameUserScoreDTO> GetRecordByPage(int pageindex,int pageSize) {
		List<GameUserScoreDTO> list = new ArrayList<GameUserScoreDTO>();
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
			con.setAutoCommit(false); // Setup the call. 
			CallableStatement toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "gameuserRank");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "score desc");//排序字段
			toesUp.setString(4, "");//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserScoreDTO dto = new GameUserScoreDTO();
				dto.setAccounts(rs.getString("accounts"));
				dto.setRegAccounts(rs.getString("regAccounts"));
				dto.setScore(rs.getInt("score"));
				dto.setGameLogonTimes(rs.getInt("gameLogonTimes"));
				dto.setLastLogonIP(rs.getString("lastLogonIP"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			toesUp.close();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalPage() {
		return pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public List<GameUserScoreDTO> findAll(int curPage){
		List<GameUserScoreDTO> list = new ArrayList<GameUserScoreDTO>();
		try {
			ps = con.prepareStatement(SQL_SELECT,rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_READ_ONLY);
			rs =ps.executeQuery();
			rs.absolute((curPage-1)*10+1);
			rs.previous();
			int count = 0;
			while(rs.next()&&count<10){
				count++;
				GameUserScoreDTO dto = new GameUserScoreDTO();
				
				int id = 1;
				id++;
				dto.setUserID(id);
				dto.setAccounts(rs.getString("accounts"));
				dto.setRegAccounts(rs.getString("regAccounts"));
				dto.setScore(rs.getInt("score"));
				dto.setGameLogonTimes(rs.getInt("gameLogonTimes"));
				dto.setLastLogonIP(rs.getString("lastLogonIP"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	


}

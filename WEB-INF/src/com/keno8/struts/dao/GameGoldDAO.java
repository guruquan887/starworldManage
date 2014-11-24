package com.keno8.struts.dao;

import java.sql.*;

import com.keno8.struts.dto.GameGoldInfoDTO;


public class GameGoldDAO {

	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	
	

	public GameGoldDAO(Connection con) {
		super();
		this.con = con;
	}
	
	
	public GameGoldInfoDTO getById(int id) {
		GameGoldInfoDTO dto = null;
		try {
			ps = con.prepareStatement("select * from AccountsGameScoreTreasure where userID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new GameGoldInfoDTO();
			if (rs.next()) {
				dto.setUserID(rs.getInt("userID"));
				dto.setGameID(rs.getInt("gameID"));
				dto.setAccounts(rs.getString("Accounts"));
				dto.setScore(rs.getLong("Score"));
				dto.setJifen(rs.getLong("jifen"));
				dto.setInsureScore(rs.getLong("insureScore"));
				dto.setKindId(rs.getInt("KindID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public GameGoldInfoDTO getByIdinsure(int id) {
		GameGoldInfoDTO dto = null;
		try {
			ps = con.prepareStatement("select * from GameInsureScoreView where userID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new GameGoldInfoDTO();
			if (rs.next()) {
				dto.setUserID(rs.getInt("userID"));
				dto.setGameID(rs.getInt("gameID"));
				dto.setAccounts(rs.getString("Accounts"));
				dto.setInsureScore(rs.getLong("insureScore"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public GameGoldInfoDTO getById(int id,int kindID) {
		GameGoldInfoDTO dto = null;
		try {
			ps = con.prepareStatement("select * from AccountsGameScoreTreasure where userID=? and kindID=?");
			ps.setInt(1, id);
			ps.setInt(2, kindID);
			rs = ps.executeQuery();
			dto = new GameGoldInfoDTO();
			if (rs.next()) {
				dto.setUserID(rs.getInt("userID"));
				dto.setGameID(rs.getInt("gameID"));
				dto.setAccounts(rs.getString("Accounts"));
				dto.setInsureScore(rs.getLong("insureScore"));
				dto.setScore(rs.getLong("Score"));
				dto.setJifen(rs.getLong("jifen"));
				dto.setKindId(kindID);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public GameGoldInfoDTO getByIdjifen(int id) {
		GameGoldInfoDTO dto = null;
		try {
			ps = con.prepareStatement("select * from AccountsGamejifenTreasure where userID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new GameGoldInfoDTO();
			if (rs.next()) {
				dto.setUserID(rs.getInt("userID"));
				dto.setGameID(rs.getInt("gameID"));
				dto.setAccounts(rs.getString("Accounts"));
				dto.setScore(rs.getLong("Score"));
				//dto.setJifen(rs.getLong("jifen"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public void update(GameGoldInfoDTO dto) {
		
		try {
			con.setAutoCommit(true);
			ps = con.prepareStatement("update GameScoreInfo set Score=? where userID=? and kindID=?");
			ps.setLong(1, dto.getScore());
			ps.setInt(2, dto.getUserID());
			ps.setInt(3, dto.getKindId());
			ps.execute();
			
			ps = con.prepareStatement("update GameUserInsureScore set insureScore=? where userID=?");
			ps.setLong(1, dto.getInsureScore());
			ps.setInt(2, dto.getUserID());
			ps.execute();
			
			ps = con.prepareStatement("update QPGameScoreDB.dbo.GameScoreInfo set score=? where userID=?");
			ps.setLong(1, dto.getJifen());
			ps.setInt(2, dto.getUserID());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateinsureScore(long insureScore,int userID) {
		
		try {
			con.setAutoCommit(true);
			ps = con.prepareStatement("update GameUserInsureScore set insureScore=? where userID=?");
			ps.setLong(1, insureScore);
			ps.setInt(2, userID);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatejifen(GameGoldInfoDTO dto) {
		try {
			con.setAutoCommit(true);
			ps = con.prepareStatement("update GameScoreInfo set Score=? where userID=?");
			ps.setLong(1, dto.getScore());
			ps.setInt(2, dto.getUserID());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

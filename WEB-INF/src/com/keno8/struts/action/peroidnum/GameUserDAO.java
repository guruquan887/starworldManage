package com.keno8.struts.action.peroidnum;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class GameUserDAO {
	private DataSource ds;
	
	public GameUserDAO(DataSource ds) {
		this.ds = ds;
	}

	public GameUser Login(String userName,String password ){
		GameUser result=null;
		String sql="select * from RegAccountsInfo where accounts='"+userName+"' and loginPass='"+password+"'";
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result=new GameUser();
				result.setUserID(rs.getInt("userID"));
				result.setAccount(rs.getString("accounts"));
				result.setNickName(rs.getString("nickName"));
				result.setSex(rs.getInt("sex"));
				result.setScore(rs.getDouble("score"));
				result.setInsureScore(rs.getInt("insureScore"));
				result.setMemberOrder(rs.getInt("MemberOrder"));
				result.setType(rs.getString("type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public GameUser getUserByUserId(int userId){
		GameUser result = null;
		String sql = "select * from RegAccountsInfo where UserID=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				result=new GameUser();
				result.setUserID(rs.getInt("userID"));
				result.setAccount(rs.getString("accounts"));
				result.setNickName(rs.getString("nickName"));
				result.setSex(rs.getInt("sex"));
				result.setScore(rs.getDouble("score"));
				result.setInsureScore(rs.getInt("insureScore"));
				result.setMemberOrder(rs.getInt("MemberOrder"));
				result.setType(rs.getString("type"));
				result.setCunScore(rs.getDouble("cunScore"));
				result.setFreezeScore(rs.getDouble("freezeScore"));
				result.setExperience(rs.getDouble("experience"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result ;
	}
	
	public void updateGameUser(GameUser dto,Connection con){
		//Connection con=null;
		PreparedStatement pstmt=null;
		String sql= "update RegAccountsInfo set accounts=?,nickName=?,sex=?, score=?,insureScore=?,memberOrder=? where UserID = ?";
		try {
			//con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getAccount());
			pstmt.setString(2, dto.getNickName());
			pstmt.setInt(3, dto.getSex());
			pstmt.setDouble(4, dto.getScore());
			pstmt.setInt(5, dto.getInsureScore());
			pstmt.setInt(6, dto.getMemberOrder());
			pstmt.setInt(7, dto.getUserID());
			pstmt.executeUpdate();
			
			
			if(pstmt!=null){
				pstmt.close();pstmt=null;
			}
//			if(con!=null){
//				con.close();con=null;
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			if(pstmt!=null){
				pstmt.close();pstmt=null;
			}
//			if(con!=null){
//				con.close();con=null;
//				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	}
	
}

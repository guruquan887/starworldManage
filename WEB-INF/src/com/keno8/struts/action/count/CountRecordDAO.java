package com.keno8.struts.action.count;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountRecordDAO {
	private DataSource ds;
	//private Connection con;
	//private ResultSet rs;
	//private PreparedStatement ps;
	
	public CountRecordDAO(DataSource ds) {
		this.ds = ds;
	}

	public Long countRecord(int roomId,String where){   //开奖管理下的报表统计
		String sql = "select sum(totalGoldBet) from GameRecordView where roomId="+roomId+where;
		long sumGold = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			System.out.println(sql);
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				sumGold = rs.getLong(1);
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
		return sumGold;
	}
	
	public Long count3DRecord(int roomId,String where){   //开奖管理下的报表统计
		String sql = "select sum(convert(bigint,totalGoldBet)) from gamePickRecord where roomId="+roomId+where;
		long sumGold = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			System.out.println(sql);
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				sumGold = rs.getLong(1);
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
		return sumGold;
	}
	
	@SuppressWarnings("unchecked")
	public List queryRoomID(){
		List<CountRecordDTO> list = new ArrayList<CountRecordDTO>();
		String sql = "select * from rooms";
		Connection con = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				CountRecordDTO dto = new CountRecordDTO();
				dto.setRoomId(rs.getInt("ID"));
				dto.setRoomName(rs.getString("roomName"));
				dto.setDisplayName(rs.getString("displayName"));
				list.add(dto);
			}
			
			if(rs!=null){
				rs.close();rs=null;
			}
			if(con!=null){
				con.close();con=null;
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
	


}
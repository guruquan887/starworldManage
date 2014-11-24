package com.suncity.dwr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


public class PlayDb {
	
	private DataSource ds;

	public PlayDb(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	public void close(Connection con, Statement st,PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<RoomDTO> getPlays(){
		List<RoomDTO> list = new ArrayList<RoomDTO>();
		RoomDTO dto = null;
		Connection con = null;
		Statement st = null; 
		ResultSet rs = null;
		String sql = "select  kindId,kindName  from  QPServerInfoDB.dbo.GameKindItem where kindId <> 0";
		try {
			con = ds.getConnection();
			st = con.createStatement();			
			System.out.println(" sql=" + sql);
			rs = st.executeQuery(sql);
			while(rs.next()){
				dto = new RoomDTO();
				dto.setKindID(rs.getInt("kindID"));
				dto.setKindName(rs.getString("kindName"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.close(con, st,null, rs);
		}
		return list;
	}
	
	public List<PlayDTO> getPlayNumber(){
		List<PlayDTO> list = new ArrayList<PlayDTO>();
		PlayDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select  count(roomId) number,roomid  from  QPTreasureDB.dbo.GameAreaPlay where roomID in " +
				"(select kindId from QPServerInfoDB.dbo.GameKindItem) group by roomid";
		try {
			con = ds.getConnection();
			System.out.println("sql=" + sql);
			pstmt = con.prepareStatement(sql);		
			rs = pstmt.executeQuery();
			while(rs.next()){	
				dto = new PlayDTO();
				dto.setKindID(rs.getInt("roomID"));
				dto.setPlayTotalNumbers(rs.getInt("number"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.close(con, null,pstmt, rs);
		}
		return list;
	}
	
	public void updateSessionTime(String userID){
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update SunCityManage.dbo.admin set sessionTime=getDate() where id="+userID;
		try {
			System.out.println("sql:"+sql);
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.execute();
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.close(con, null,ps, null);
		}
	}

}

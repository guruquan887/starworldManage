package com.doowal.hk798.roomManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.doowal.hk798.gameUser.BaseDAO;

public class DZGameRoomDAO extends BaseDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public DZGameRoomDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public int getPageCount() {
		return pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}
	
	public List<DZGameRoomDTO> getGameRoomList(){
		
		List<DZGameRoomDTO> list = new ArrayList<DZGameRoomDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				String sql = "SELECT g_rooms.*,g_games.`islock` AS isfree FROM dbgame88.g_rooms LEFT OUTER JOIN g_games ON g_rooms.`gametype`=g_games.`gametype` WHERE g_rooms.gameclass2='桌上游戏'";
				System.out.println(" 执行sql:"+sql);
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					DZGameRoomDTO dto = new DZGameRoomDTO();
					dto.setGametype(rs.getInt("gametype"));
					dto.setGamename(rs.getString("gamename"));
					dto.setGamecode(rs.getInt("gamecode"));
					dto.setIslock(rs.getInt("islock"));
					dto.setRound_maxbet(rs.getInt("Round_maxbet"));
					dto.setLine_maxbet(rs.getInt("Line_maxbet"));
					dto.setSingle_maxbet(rs.getInt("Single_maxbet"));
					dto.setLimit_minbet(rs.getInt("Limit_minbet"));
					dto.setBonus_maxbet(rs.getInt("Bonus_maxbet"));
					dto.setBonus_minbet(rs.getInt("Bonus_minbet"));
					dto.setIslock(rs.getInt("isfree"));
					

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
	
	public DZGameRoomDTO getDZGameRoomDTO(String id){
		
		DZGameRoomDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				String sql = "select * from dbgame88.g_rooms where gametype ='" + id+"'";
				System.out.println(" 执行sql:"+sql);
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					    dto = new DZGameRoomDTO();
					    dto.setGametype(rs.getInt("gametype"));
						dto.setGamename(rs.getString("gamename"));
						dto.setGamecode(rs.getInt("gamecode"));
						dto.setIslock(rs.getInt("islock"));
						dto.setRound_maxbet(rs.getInt("Round_maxbet"));
						dto.setLine_maxbet(rs.getInt("Line_maxbet"));
						dto.setSingle_maxbet(rs.getInt("Single_maxbet"));
						dto.setLimit_minbet(rs.getInt("Limit_minbet"));
						dto.setBonus_maxbet(rs.getInt("Bonus_maxbet"));
						dto.setBonus_minbet(rs.getInt("Bonus_minbet"));
						
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				this.closeDBObject(con, rs, null, null, ps);
			}
		return dto;
	}
	
	public boolean update(DZGameRoomDTO dto){
		
		boolean bo = false;
		Connection con = null;
		PreparedStatement ps = null;
			try {
				String sql = "update dbgame88.g_rooms set round_maxbet="+dto.getRound_maxbet()+", line_maxbet="+dto.getLine_maxbet()
				+", single_maxbet ="+dto.getSingle_maxbet()+", limit_minbet="+dto.getLimit_minbet()+", bonus_maxbet ="+dto.getBonus_maxbet()+",bonus_minbet="+dto.getBonus_minbet()+" where gametype ="+dto.getGametype();
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
	
	public String updateState(int gametype,int islock){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update dbgame88.g_games set islock=? where gametype=?");
			ps.setInt(1, islock);
			ps.setInt(2, gametype);
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

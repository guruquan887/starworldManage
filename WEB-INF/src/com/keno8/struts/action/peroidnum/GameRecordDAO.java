package com.keno8.struts.action.peroidnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.keno8.struts.dto.GameRecordDTO;

public class GameRecordDAO {
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	
	public GameRecordDAO(DataSource ds) {
		this.ds = ds;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public GameRecordDTO getARecordForUser(int userId, int roomId,
			String peroidnum) {
		GameRecordDTO dto = null;// new GameRecordDAO();
		
		String sql = "select * from gameRecord where userId=? and roomId = ?  and peroidnum=? ";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Connection conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, roomId);
			pstmt.setString(3, peroidnum);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				dto = new GameRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setGameRuleId(rs.getInt("gameRuleId"));
				dto.setTotalGoldBet(rs.getFloat("totalGoldBet"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setSingle(rs.getInt("single"));
				dto.setSerpent(rs.getInt("serpent"));
				dto.setLarge(rs.getInt("large"));
				dto.setPeace(rs.getInt("peace"));
				dto.setTiger(rs.getInt("tiger"));
				dto.setSmall(rs.getInt("small"));
				dto.setPairs(rs.getInt("pairs"));
				dto.setRate(rs.getInt("rate"));
				dto.setRateNumber(rs.getString("rateNumber"));
				dto.setJiesuan(rs.getString("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				rs = null;
				pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public void insertGameRecord(GameRecordDTO dto) {
		
		String sql = "insert into gameRecord(userId,roomId,peroidnum,gameRuleId,totalGoldBet,single,serpent,large,peace,tiger,small,pairs,rate,rateNumber,firstMoney,lastMoney) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getRoomId());
			pstmt.setString(3, dto.getPeroidnum());
			pstmt.setInt(4, dto.getGameRuleId());
			pstmt.setDouble(5, dto.getTotalGoldBet());
			pstmt.setInt(6, dto.getSingle());
			pstmt.setInt(7, dto.getSerpent());
			pstmt.setInt(8, dto.getLarge());
			pstmt.setInt(9, dto.getPeace());
			pstmt.setInt(10, dto.getTiger());
			pstmt.setInt(11, dto.getSmall());
			pstmt.setInt(12, dto.getPairs());
			pstmt.setInt(13, dto.getRate());
			pstmt.setString(14, dto.getRateNumber());
			pstmt.setString(15, dto.getFirstMoney());
			pstmt.setString(16, dto.getLastMoney());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void updateGameRecord(GameRecordDTO dto,Connection con) {
		//Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update gameRecord set userId=?,roomId=?,peroidnum=?,gameRuleId=?,totalGoldBet=?,createTime=?,single=?,serpent=?,large=?,peace=?,tiger=?,small=?,pairs=?,rate=?,rateNumber=?,jiesuan=?,firstMoney=?,lastMoney=?,winGold=?,pureGold=?,rebate=? where id=?";
		try {
			//con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getRoomId());
			pstmt.setString(3, dto.getPeroidnum());
			pstmt.setInt(4, dto.getGameRuleId());
			pstmt.setDouble(5, dto.getTotalGoldBet());
			pstmt.setString(6, dto.getCreateTime());
			pstmt.setInt(7, dto.getSingle());
			pstmt.setInt(8, dto.getSerpent());
			pstmt.setInt(9, dto.getLarge());
			pstmt.setInt(10, dto.getPeace());
			pstmt.setInt(11, dto.getTiger());
			pstmt.setInt(12, dto.getSmall());
			pstmt.setInt(13, dto.getPairs());
			pstmt.setInt(14, dto.getRate());
			pstmt.setString(15, dto.getRateNumber());
			pstmt.setString(16, dto.getJiesuan());
			pstmt.setString(17, dto.getFirstMoney());
			pstmt.setString(18, dto.getLastMoney());
			pstmt.setDouble(19, dto.getWinGold());
			pstmt.setDouble(20, dto.getPureGold());
			pstmt.setDouble(21, dto.getRebate());
			pstmt.setInt(22, dto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();pstmt = null;
//				if (con != null)
//					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<GameRecordDTO> getGameRecords(int roomId,
			String peroidnum, String jiesuan) {
		ArrayList<GameRecordDTO> list = new ArrayList<GameRecordDTO>();
		String sql = "select * from gameRecord where  roomId = ?  and peroidnum=?  and jiesuan = ?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Connection conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, peroidnum);
			pstmt.setString(3, jiesuan);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GameRecordDTO dto = new GameRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setGameRuleId(rs.getInt("gameRuleId"));
				dto.setTotalGoldBet(rs.getFloat("totalGoldBet"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setSingle(rs.getInt("single"));
				dto.setSerpent(rs.getInt("serpent"));
				dto.setLarge(rs.getInt("large"));
				dto.setPeace(rs.getInt("peace"));
				dto.setTiger(rs.getInt("tiger"));
				dto.setSmall(rs.getInt("small"));
				dto.setPairs(rs.getInt("pairs"));
				dto.setRate(rs.getInt("rate"));
				dto.setRateNumber(rs.getString("rateNumber"));
				dto.setJiesuan(rs.getString("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				rs = null;
				pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<GameRecordDTO> getGameRecords(int roomId, String peroidnum) {
		ArrayList<GameRecordDTO> list = new ArrayList<GameRecordDTO>();
		String sql = "select * from gameRecord where  roomId = ?  and peroidnum=? ";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Connection conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, peroidnum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GameRecordDTO dto = new GameRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setGameRuleId(rs.getInt("gameRuleId"));
				dto.setTotalGoldBet(rs.getFloat("totalGoldBet"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setSingle(rs.getInt("single"));
				dto.setSerpent(rs.getInt("serpent"));
				dto.setLarge(rs.getInt("large"));
				dto.setPeace(rs.getInt("peace"));
				dto.setTiger(rs.getInt("tiger"));
				dto.setSmall(rs.getInt("small"));
				dto.setPairs(rs.getInt("pairs"));
				dto.setRate(rs.getInt("rate"));
				dto.setRateNumber(rs.getString("rateNumber"));
				dto.setJiesuan(rs.getString("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				rs = null;
				pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<GameRecordDTO> getGameRecords(String jiesuan) {

		ArrayList<GameRecordDTO> list = new ArrayList<GameRecordDTO>();
		String sql = "select * from gameRecord where  jiesuan = ? ";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Connection conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jiesuan);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GameRecordDTO dto = new GameRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setGameRuleId(rs.getInt("gameRuleId"));
				dto.setTotalGoldBet(rs.getFloat("totalGoldBet"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setSingle(rs.getInt("single"));
				dto.setSerpent(rs.getInt("serpent"));
				dto.setLarge(rs.getInt("large"));
				dto.setPeace(rs.getInt("peace"));
				dto.setTiger(rs.getInt("tiger"));
				dto.setSmall(rs.getInt("small"));
				dto.setPairs(rs.getInt("pairs"));
				dto.setRate(rs.getInt("rate"));
				dto.setRateNumber(rs.getString("rateNumber"));
				dto.setJiesuan(rs.getString("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				rs = null;
				pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
	//分页存储过程！！！，外面封装where条件 
/*	public ArrayList<GameRecordDTO> getPageGameRecords(String pageIndex,
			String limit, String where) {
		ArrayList<GameRecordDTO> list = new ArrayList<GameRecordDTO>();
		CallableStatement pro = null;
		ResultSet rs = null;
		try {
			Connection conn = ds.getConnection();
			conn.setAutoCommit(false);
			pro = conn.prepareCall("{ call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			pro.setString(1, "gameRecord");
			pro.setString(2, "*");
			pro.setString(3, "createTime desc");
			pro.setString(4, where);
			pro.setString(5, limit);
			pro.setString(6, pageIndex);
			pro.registerOutParameter(7, Types.INTEGER);
			pro.registerOutParameter(8, Types.INTEGER);
			rs = pro.executeQuery();
			while (rs.next()) {
				GameRecordDTO dto = new GameRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setGameRuleId(rs.getInt("gameRuleId"));
				dto.setTotalGoldBet(rs.getFloat("totalGoldBet"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setSingle(rs.getInt("single"));
				dto.setSerpent(rs.getInt("serpent"));
				dto.setLarge(rs.getInt("large"));
				dto.setPeace(rs.getInt("peace"));
				dto.setTiger(rs.getInt("tiger"));
				dto.setSmall(rs.getInt("small"));
				dto.setPairs(rs.getInt("pairs"));
				dto.setRate(rs.getInt("rate"));
				dto.setRateNumber(rs.getString("rateNumber"));
				dto.setJiesuan(rs.getString("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				list.add(dto);
			}
			pro.getMoreResults();
			pageCount = pro.getInt(7);
			recordCount = pro.getInt(8);
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pro != null)
					pro.close();
			 catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
}
}*/

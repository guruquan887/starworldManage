package com.keno8.struts.action.peroidnum;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;

public class GamePickRecordDAO {
	private int pageCount = 0;
	private int recordCount = 0;
	private DataSource ds;
	
	public GamePickRecordDAO(DataSource ds) {
		this.ds = ds;
	}

	public void insertGamePickRecord(GamePickRecordDTO dto) {
		Connection conn = null;
		String sql = "insert into gamePickRecord(userId,roomId,peroidnum,totalGoldBet,type,hundred,ten,one,jiesuan,firstMoney,lastMoney,multiple) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getRoomId());
			pstmt.setString(3, dto.getPeroidnum());
			pstmt.setString(4, dto.getTotalGoldBet());
			pstmt.setInt(5, dto.getType());
			pstmt.setString(6, dto.getHundred());
			pstmt.setString(7, dto.getTen());
			pstmt.setString(8, dto.getOne());
			pstmt.setInt(9, dto.getJiesuan());
			pstmt.setString(10, dto.getFirstMoney());
			pstmt.setString(11, dto.getLastMoney());
			pstmt.setInt(12, dto.getMultiple());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateGamePickRecord(GamePickRecordDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update gamePickRecord set userId=?,roomId=?,peroidnum=?,totalGoldBet=?,type=?,hundred=?,ten=?,one=?,jiesuan=?,firstMoney=?,lastMoney=?,pureMoney=?,multiple=? where id = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getRoomId());
			pstmt.setString(3, dto.getPeroidnum());
			pstmt.setString(4, dto.getTotalGoldBet());
			pstmt.setInt(5, dto.getType());
			pstmt.setString(6, dto.getHundred());
			pstmt.setString(7, dto.getTen());
			pstmt.setString(8, dto.getOne());
			pstmt.setInt(9, dto.getJiesuan());
			pstmt.setString(10, dto.getFirstMoney());
			pstmt.setString(11, dto.getLastMoney());
			pstmt.setString(12, dto.getPureMoney());
			pstmt.setInt(13, dto.getMultiple());
			pstmt.setInt(14, dto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<GamePickRecordDTO> getGamePickRecord(int roomId,
			String peroidnum, int jiesuan,String orderBy) {
		ArrayList<GamePickRecordDTO> list = new ArrayList<GamePickRecordDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from gamePickRecord where roomId = " + roomId
				+ " and peroidnum='" + peroidnum + "' and jiesuan=" + jiesuan
				+ " "+orderBy;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				GamePickRecordDTO dto = new GamePickRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setTotalGoldBet(rs.getString("totalGoldBet"));
				dto.setType(rs.getInt("type"));
				dto.setHundred(rs.getString("hundred"));
				dto.setTen(rs.getString("ten"));
				dto.setOne(rs.getString("one"));
				dto.setJiesuan(rs.getInt("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setPureMoney(rs.getString("pureMoney"));
				dto.setMultiple(rs.getInt("multiple"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<GamePickRecordDTO> getGamePickRecord(int userId,int roomId,
			String peroidnum, int jiesuan) {
		ArrayList<GamePickRecordDTO> list = new ArrayList<GamePickRecordDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from gamePickRecord where userId = "+userId+" and roomId = " + roomId
				+ " and peroidnum='" + peroidnum + "' and jiesuan=" + jiesuan
				+ "";

		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				GamePickRecordDTO dto = new GamePickRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setTotalGoldBet(rs.getString("totalGoldBet"));
				dto.setType(rs.getInt("type"));
				dto.setHundred(rs.getString("hundred"));
				dto.setTen(rs.getString("ten"));
				dto.setOne(rs.getString("one"));
				dto.setJiesuan(rs.getInt("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setPureMoney(rs.getString("pureMoney"));
				dto.setMultiple(rs.getInt("multiple"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	public ArrayList<GamePickRecordDTO> getGamePickRecord(int userId,int roomId,
			String peroidnum) {
		ArrayList<GamePickRecordDTO> list = new ArrayList<GamePickRecordDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from gamePickRecord where userId = "+userId+" and roomId = " + roomId
				+ " and peroidnum='" + peroidnum + "'  ";
				
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				GamePickRecordDTO dto = new GamePickRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setTotalGoldBet(rs.getString("totalGoldBet"));
				dto.setType(rs.getInt("type"));
				dto.setHundred(rs.getString("hundred"));
				dto.setTen(rs.getString("ten"));
				dto.setOne(rs.getString("one"));
				dto.setJiesuan(rs.getInt("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setPureMoney(rs.getString("pureMoney"));
				dto.setMultiple(rs.getInt("multiple"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<GamePickRecordDTO> getGamePickRecord(int jiesuan) {
		ArrayList<GamePickRecordDTO> list = new ArrayList<GamePickRecordDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from gamePickRecord where  jiesuan=" + jiesuan;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				GamePickRecordDTO dto = new GamePickRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setTotalGoldBet(rs.getString("totalGoldBet"));
				dto.setType(rs.getInt("type"));
				dto.setHundred(rs.getString("hundred"));
				dto.setTen(rs.getString("ten"));
				dto.setOne(rs.getString("one"));
				dto.setJiesuan(rs.getInt("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setPureMoney(rs.getString("pureMoney"));
				dto.setMultiple(rs.getInt("multiple"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<GamePickRecordDTO> getGamePickRecord(int userId,int roomId,
			int multiple,String peroidnum,int jiesuan) {
		ArrayList<GamePickRecordDTO> list = new ArrayList<GamePickRecordDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from gamePickRecord where userId = "+userId+" and roomId = " + roomId
				+ " and peroidnum='" + peroidnum + "' and  multiple =  "+multiple+" and jiesuan="+jiesuan;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				GamePickRecordDTO dto = new GamePickRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setTotalGoldBet(rs.getString("totalGoldBet"));
				dto.setType(rs.getInt("type"));
				dto.setHundred(rs.getString("hundred"));
				dto.setTen(rs.getString("ten"));
				dto.setOne(rs.getString("one"));
				dto.setJiesuan(rs.getInt("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setPureMoney(rs.getString("pureMoney"));
				dto.setMultiple(rs.getInt("multiple"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<GamePickRecordDTO> getPageGamePickRecords(String pageIndex,
			String limit, String where) {
		ArrayList<GamePickRecordDTO> list = new ArrayList<GamePickRecordDTO>();
		Connection conn = null;
		CallableStatement pro = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pro = conn.prepareCall("{ call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			pro.setString(1, "gamePickRecord");
			pro.setString(2, "*");
			pro.setString(3, "createTime desc");
			pro.setString(4, where);
			pro.setString(5, limit);
			pro.setString(6, pageIndex);
			pro.registerOutParameter(7, Types.INTEGER);
			pro.registerOutParameter(8, Types.INTEGER);
			rs = pro.executeQuery();
			while (rs.next()) {
				GamePickRecordDTO dto = new GamePickRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setTotalGoldBet(rs.getString("totalGoldBet"));
				dto.setType(rs.getInt("type"));
				dto.setHundred(rs.getString("hundred"));
				dto.setTen(rs.getString("ten"));
				dto.setOne(rs.getString("one"));
				dto.setJiesuan(rs.getInt("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setPureMoney(rs.getString("pureMoney"));
				dto.setMultiple(rs.getInt("multiple"));
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
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
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
	
}

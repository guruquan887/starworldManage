package com.doowal.hk798.game;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.doowal.hk798.gameUser.BaseDAO;

public class GameSystemDAO extends BaseDAO{
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	
	public GameSystemDAO(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	public int getTotalPage() {
		return pageCount;
	}
	
	public int getRcordCount() {
		return recordCount;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public boolean delAll(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("delete from DataBaseInfo where DBInfoID=?");
			ps.setString(1, userID);
			ps.execute();
			
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	
	public List<GameSystemDTO> GetRecordByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "DataBaseInfo");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "DBInfoID desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setDbAddr(rs.getString("DBAddr"));
				dto.setDbInfoID(rs.getInt("DBInfoID"));
				dto.setDbPort(rs.getInt("DBPort"));
				dto.setDbUser(rs.getString("DBUser"));
				dto.setDbPassword(rs.getString("DBPassword"));
				dto.setMachineID(rs.getString("machineID"));
				dto.setInformation(rs.getString("Information"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	//游戏模块管理
	public List<GameSystemDTO> GetRecordGameGameItemByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GameGameItem");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "GameID asc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setGameID(rs.getInt("gameID"));
				dto.setGameName(rs.getString("gameName"));
				dto.setDataBaseAddr(rs.getString("DataBaseAddr"));
				dto.setDataBaseName(rs.getString("DataBaseName"));
				dto.setServerVersion(longToIP(rs.getInt("serverVersion")));
				dto.setClientVersion(longToIP(rs.getInt("clientVersion")));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	public GameSystemDTO GetGameItemById(int param) {
		GameSystemDTO dto = new GameSystemDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from QPPlatformDB.dbo.GameGameItem where gameID=?");
			ps.setInt(1, param);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setGameID(rs.getInt("gameID"));
				dto.setGameName(rs.getString("gameName"));
				dto.setDataBaseAddr(rs.getString("DataBaseAddr"));
				dto.setDataBaseName(rs.getString("DataBaseName"));
				dto.setServerVersion(longToIP(rs.getInt("serverVersion")));
				dto.setClientVersion(longToIP(rs.getInt("clientVersion")));
				dto.setServerDLLName(rs.getString("serverDLLName"));
				dto.setClientExeName(rs.getString("clientExeName"));
				dto.setSuporType(rs.getInt("SuporType"));
			}
			if(rs!=null){
				rs.close();rs=null;
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
				if(con!=null){
					con.close();con=null;
				}
			}
			 catch (SQLException e1) {
					e1.printStackTrace();
			 }
		}
		return dto;
	}
	
    public static String longToIP(long longIp) {  
        StringBuffer sb = new StringBuffer("");  
        //直接右移24位  
        sb.append(String.valueOf((longIp >>> 24)));  
        sb.append(".");  
        //将高8位置0，然后右移16位  
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));  
        sb.append(".");  
        //将高16位置0，然后右移8位  
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));  
        sb.append(".");  
        //将高24位置0  
        sb.append(String.valueOf((longIp & 0x000000FF)));  
        return sb.toString();  
    }  
	
	//类型管理
	public List<GameSystemDTO> GetRecordGameTypeItemByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GameTypeItem");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "TypeID asc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setTypeID(rs.getInt("typeID"));
				dto.setJoinID(rs.getInt("joinID"));
				dto.setSortID(rs.getInt("sortID"));
				dto.setTypeName(rs.getString("typeName"));
				int nullity = rs.getInt("nullity");
				if(nullity==1){
					dto.setNullityName("禁用");
				}
				else{
					dto.setNullityName("启用");
				}
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	//游戏管理
	public List<GameSystemDTO> GetRecordGameKindItemByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GameKindItemView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "KindID asc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setKindID(rs.getInt("kindID"));
				dto.setTypeID(rs.getInt("typeID"));
				dto.setJoinID(rs.getInt("joinID"));
				dto.setSortID(rs.getInt("sortID"));
				dto.setTypeName(rs.getString("typeName"));
				int nullity = rs.getInt("nullity");
				if(nullity==1){
					dto.setNullityName("禁用");
				}
				else{
					dto.setNullityName("启用");
				}
				dto.setProcessName(rs.getString("processName"));
				dto.setKindName(rs.getString("kindName"));
				dto.setGameruleURL(rs.getString("GameRuleUrl"));
				dto.setDownloadURL(rs.getString("DownLoadUrl"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	//节点管理
	public List<GameSystemDTO> GetRecordGameNodeItemByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GameNodeItemView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "NodeID asc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setKindID(rs.getInt("kindID"));
				dto.setJoinID(rs.getInt("joinID"));
				dto.setSortID(rs.getInt("sortID"));
				dto.setNodeName(rs.getString("NodeName"));
				int nullity = rs.getInt("nullity");
				if(nullity==1){
					dto.setNullityName("禁用");
				}
				else{
					dto.setNullityName("启用");
				}
				dto.setKindName(rs.getString("kindName"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	//自定义页管理
	public List<GameSystemDTO> GetRecordGamePageItemByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GamePageItemView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "PageID asc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setPageID(rs.getInt("pageID"));
				dto.setDisplayName(rs.getString("DisplayName"));
				dto.setResponseUrl(rs.getString("ResponseUrl"));
				dto.setKindName(rs.getString("kindName"));
				dto.setNodeName(rs.getString("nodeName"));
				dto.setOperateType(rs.getInt("operateType"));
				int nullity = rs.getInt("nullity");
				dto.setSortID(rs.getInt("sortID"));
				if(nullity==1){
					dto.setNullityName("禁用");
				}
				else{
					dto.setNullityName("启用");
				}
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	//道具管理
	public List<GameSystemDTO> GetRecordgamePropertyByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GameProperty");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "ID asc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setId(rs.getInt("ID"));
				dto.setName(rs.getString("name"));
				dto.setCash(rs.getLong("cash"));
				dto.setGold(rs.getLong("gold"));
				dto.setDisCount(rs.getInt("disCount"));
				dto.setRecvLoveLiness(rs.getLong("RecvLoveLiness"));
				dto.setSendLoveLiness(rs.getLong("SendLoveLiness"));
				
				int nullity = rs.getInt("nullity");
				if(nullity==1){
					dto.setNullityName("禁用");
				}
				else{
					dto.setNullityName("启用");
				}
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	//系统消息
	public List<GameSystemDTO> GetRecordSystemMessageByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "SystemMessage");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "ID asc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setId(rs.getInt("ID"));
				dto.setServerRange(rs.getString("ServerRange"));
				dto.setMessageString(rs.getString("MessageString"));
				dto.setStartTime(rs.getString("startTime"));
				dto.setConcludeTime(rs.getString("ConcludeTime"));
				dto.setTimeRate(rs.getInt("TimeRate"));
				
				int nullity = rs.getInt("nullity");
				if(nullity==1){
					dto.setNullityName("禁用");
				}
				else{
					dto.setNullityName("启用");
				}
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	
	//房间管理
	public List<GameSystemDTO> GetRecordGameRoomByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GameRoomInfoView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "revenueRatio asc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setServerID(rs.getInt("serverID"));
				dto.setServerName(rs.getString("serverName"));
				dto.setKindName(rs.getString("kindName"));
				dto.setGameName(rs.getString("gameName"));
				dto.setNodeName(rs.getString("nodeName"));
				dto.setSortID(rs.getInt("sortID"));
				dto.setTableCount(rs.getInt("tableCount"));
				dto.setServerType(rs.getInt("serverType"));
				dto.setServerPort(rs.getInt("serverPort"));
				dto.setDataBaseName(rs.getString("DataBaseName"));
				dto.setDataBaseAddr(rs.getString("DataBaseAddr"));
				dto.setCellScore(rs.getLong("cellScore"));
				dto.setRevenueRatio(rs.getInt("RevenueRatio"));
				dto.setRestrictScore(rs.getLong("RestrictScore"));
				dto.setMinTableScore(rs.getLong("MinTableScore"));
				dto.setMinEnterScore(rs.getLong("MinEnterScore"));
				dto.setMaxEnterScore(rs.getLong("MaxEnterScore"));
				dto.setMinEnterMember(rs.getInt("MinEnterMember"));
				dto.setMaxEnterMember(rs.getInt("MaxEnterMember"));
				dto.setMaxPlayer(rs.getInt("maxPlayer"));
				dto.setServerRule(rs.getInt("serverRule"));
				dto.setServiceMachine(rs.getString("ServiceMachine"));
				dto.setCreateDateTime(rs.getString("CreateDateTime"));
				dto.setModifyDateTime(rs.getString("ModifyDateTime"));
				
				
				int nullity = rs.getInt("nullity");
				if(nullity==1){
					dto.setNullityName("禁用");
				}
				else{
					dto.setNullityName("启用");
				}
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	//泡点设置
	public List<GameSystemDTO> GetRecordGlobalPlayByPage(int pageindex, int pageSize,
			String where) {
		List<GameSystemDTO> list = new ArrayList<GameSystemDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "GlobalPlayPresentView");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "CollectDate desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			while (rs.next()) {
				GameSystemDTO dto = new GameSystemDTO();
				dto.setServerID(rs.getInt("serverID"));
				int serverID = rs.getInt("serverID");
				if(serverID==-1){
					dto.setServerName("银子房间");
				}
				else if(serverID==-2){
					dto.setServerName("积分房间");
				}
				else{
					dto.setServerName(rs.getString("serverName"));
				}
				
				dto.setPresentMember(rs.getString("presentMember"));
				dto.setMaxDatePresent(rs.getInt("MaxDatePresent"));
				dto.setMaxPresent(rs.getInt("MaxPresent"));
				dto.setCellPlayPresnet(rs.getInt("CellPlayPresnet"));
				dto.setCellPlayTime(rs.getInt("CellPlayTime"));
				dto.setStartPlayTime(rs.getInt("StartPlayTime"));
				dto.setCellOnlinePresent(rs.getInt("CellOnlinePresent"));
				dto.setCellOnlineTime(rs.getInt("CellOnlineTime"));
				dto.setStartOnlineTime(rs.getInt("StartOnlineTime"));
				int isPlayPresent = rs.getInt("isPlayPresent");
				if(isPlayPresent==0){
					dto.setIsPlayPresentName("失效");
				}
				else{
					dto.setIsPlayPresentName("生效");
				}
				int isOnlinePresent = rs.getInt("IsOnlinePresent");
				if(isOnlinePresent==0){
					dto.setIsOnlinePresentName("失效");
				}
				else{
					dto.setIsOnlinePresentName("生效");
				}
				dto.setCollectDate(rs.getString("CollectDate"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	public void add(GameSystemDTO dto){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("insert into DataBaseInfo(DBAddr,DBPort,DBUser,DBPassword,MachineID,Information) values(?,?,?,?,?,?)");
			ps.setString(1, dto.getDbAddr());
			ps.setInt(2, dto.getDbPort());
			ps.setString(3, dto.getDbUser());
			ps.setString(4, dto.getDbPassword());
			ps.setString(5, dto.getMachineID());
			ps.setString(6, dto.getInformation());
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}

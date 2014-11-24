package com.doowal.struts.single;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class Note2DAO extends BaseDAO {

	private DataSource ds;
	private int pageCount;
	private int recordCount;
	
	public Note2DAO(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	public int getTotalPage() {
		return pageCount;
	}
	
	public int getRecordCount() {
		return recordCount;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public List<Note2DTO> getServerInfo(){
		
		List<Note2DTO> list = new ArrayList<Note2DTO>();
		Note2DTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select serverID,roomName from QPTreasureDB.dbo.serverRoom where ServerID in(2081,2082,2083,2084) order by serverID";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				dto = new Note2DTO();
				dto.setServerID(rs.getString("serverID"));
				dto.setObjectName(rs.getString("roomName"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
		
	}
	
	public String[] getBetSerial(){
		
		String [] str = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT count(distinct(betSerial)) betSerial FROM QPTreasureDB.dbo.NOTE_Bet_View ";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int count = 0;
			if(rs.next()){
			  count = rs.getInt("betSerial");
			}
			sql = "SELECT distinct(betSerial) FROM QPTreasureDB.dbo.NOTE_Bet_View order by betSerial desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int i = 0;
			str = new String[count];
			while(rs.next()){
				str[i] = rs.getString("betSerial");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return str;
		
	}
	
	public List<Note2DTO> getBetSerialAndID(){
		
		List<Note2DTO> list = new ArrayList<Note2DTO>();
		Note2DTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		String sql = " SELECT betSerial,severID,gameArea,gameAreaName,betGold,generateTime,stationNo,kindID FROM" +
//		" QPTreasureDB.dbo.Today_Bet_View ORDER BY generateTime DESC";
		String sql = " SELECT betSerial,severID FROM QPTreasureDB.dbo.NOTE_Bet_View ORDER BY generateTime DESC";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				dto = new Note2DTO();
				dto.setBetSerial(rs.getString("betSerial"));
				dto.setServerID(rs.getString("severID"));
//				dto.setBets(rs.getString("betGold"));
//				dto.setCreateTime(rs.getString("generateTime"));
//				dto.setTableNo(rs.getString("stationNo"));
//				dto.setKindID(rs.getString("kindID"));
//				dto.setGameArea(rs.getString("gameArea"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
		
	}
	
	public List<Note2DTO> getBetSerialInfo(String betSerial,String severID){
		
		List<Note2DTO> list = new ArrayList<Note2DTO>();
		Note2DTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select betSerial,severID,gameAreaName,gameArea,sum(betGold) totalBetGold from" +
				" QPTreasureDB.dbo.NOTE_Bet_View where betSerial ='"+betSerial
				+"' and severID ="+severID+" group by betSerial,severID,gameAreaName,gameArea";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				dto = new Note2DTO();
				dto.setBetSerial(rs.getString("betSerial"));
				dto.setServerID(rs.getString("severID"));
				dto.setBetGold(rs.getString("totalBetGold"));
				dto.setGameArea(rs.getString("gameArea"));
				dto.setGameAreaName(rs.getString("gameAreaName"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
		
	}
	
	public List<Note2DTO> getBetSerialInfo_ZC(String betSerial,String severID){
		
		List<Note2DTO> list = new ArrayList<Note2DTO>();
		Note2DTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select betSerial,severID,gameAreaName,gameArea,sum(betGold*sxZC/100) totalBetGold from" +
				" QPTreasureDB.dbo.NOTE_Bet_View_ZC where betSerial ='"+betSerial
				+"' and severID ="+severID+" group by betSerial,severID,gameAreaName,gameArea";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				dto = new Note2DTO();
				dto.setBetSerial(rs.getString("betSerial"));
				dto.setServerID(rs.getString("severID"));
				dto.setBetGold(rs.getString("totalBetGold"));
				dto.setGameArea(rs.getString("gameArea"));
				dto.setGameAreaName(rs.getString("gameAreaName"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
		
	}
	
	public List<Note2DTO> getPlayInfo(String betSerial,int isZC){
		
		List<Note2DTO> list = new ArrayList<Note2DTO>();
		Note2DTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from QPTreasureDB.dbo.NOTE_PlayInfo_View where betSerial='"+betSerial+"'";
		if(isZC > 0 ){
			sql=" select * from QPTreasureDB.dbo.NOTE_PlayInfo_View_ZC where betSerial='"+betSerial+"'";
		}
		try {
			System.out.println(" sql:"+sql);
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				dto = new Note2DTO();
				dto.setServerID(rs.getString("severID"));
				dto.setGameArea(rs.getString("gameArea"));
				dto.setTotalGold(NoteCommon.DoubleFormat(rs.getString("totalGold")));
				dto.setBets(rs.getString("counts"));
				dto.setBetSerial(rs.getString("betSerial"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
		
	}

	
	public String getTotalGold(String betSerial){
		
		String gold = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select sum(totalGold) totalGold from QPTreasureDB.dbo.NOTE_PlayInfo_View where betSerial='"+betSerial+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				gold = rs.getString("totalGold");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return gold;
		
	}
	
	public List<Note2DTO> getPlayList(String kindID,int pageStart, int pageEnd,List<Note2DTO> playInfo){//获得规则玩法
		
		List<Note2DTO> list = new ArrayList<Note2DTO>();
		Note2DTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select * from(select ROW_NUMBER() Over(order by gameAreaID) as rowId, * from QPTreasureDB.dbo.GameAreaPlay "+
		"where roomID = "+kindID+") newtable where rowId between "+pageStart+" and "+pageEnd;
		try {
			//System.out.println(" sql:"+sql);
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				dto = new Note2DTO();
				int i = 0;
				String gameAreaID = rs.getString("gameAreaID");
				if(playInfo.size() > 0){
					//System.out.println("~"+gameAreaID+"--"+playInfo.size());
					while(true){
						//System.out.println("````````````i:"+i);
						if(gameAreaID.equals(playInfo.get(i).getGameArea())){
						//System.out.println("~"+gameAreaID+"````"+playInfo.get(i).getGameArea());
						dto.setBets(playInfo.get(i).getBets());
						dto.setTotalGold(playInfo.get(i).getTotalGold());
						dto.setBetSerial(playInfo.get(i).getBetSerial());
						 break;
						}
						i++;
						if(i >= playInfo.size()){
							break;
						}
					}
				}
				dto.setObjectName(rs.getString("gameAreaName"));
				dto.setGameArea(gameAreaID);
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
		
	}
	
	
	public List<Note2DTO> getRecordList(int pageindex,int pageSize,String where,String orderby){
		
		List<Note2DTO> list = new ArrayList<Note2DTO>();
		Note2DTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			con = ds.getConnection();
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
			//con.setAutoCommit(false); // Setup the call. 
			
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "NOTE_TodayBetRecord_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby);//排序字段
			toesUp.setString(4,where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			
			while(rs.next()){
				dto = new Note2DTO();
				dto.setBetGold(rs.getString("betGold"));
				dto.setBetSerial(rs.getString("betSerial"));
				dto.setCreateTime(rs.getString("generateTime").substring(0,19));
				dto.setGameAreaName(rs.getString("gameAreaName"));
				dto.setGameArea(rs.getString("gameArea"));
				dto.setAccounts(rs.getString("Accounts"));
				dto.setIntoAccount(rs.getFloat("sxZC")/100+"");//占成
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, toesUp, null);
		}
		
		return list;
	}

	
	
	
	
	
}

package com.manage.struts.videoFileNameResult;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.manage.struts.video.RoomDTO;
import com.manage.struts.video.VideoDTO;

public class GameResultManageDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public GameResultManageDAO(DataSource ds) {
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
	
	public String updateResult(int serverID,int orderType,int playID,String betSerial,String hgName,String VideoFileName){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		int vid = 0;
		String table = "";
		String fileName = "";
		if(orderType==1){       //庄赢 ZY
			result = "ZY";
		}
		
		else if(orderType==2){  //闲赢 XY
			result = "XY";
		}
		
		else if(orderType==3){  //平局 HJ
			result = "HJ";
		}
		if(serverID==2081){
			table = "BJLPlayList1";
		}
		else if(serverID==2082){
			table = "BJLPlayList2";
		}
		else if(serverID==2083){
			table = "BJLPlayList3";
		}
		else if(serverID==2084){
			table = "BJLPlayList4";
		}
		String sql = "select * from SunCityManage.dbo.BJLVideo where videoType=1 and resultOfWinLost='"+result+"' and hgName='"+hgName+"'";
		System.out.println("查询视频列表的id和文件名称"+sql);
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				vid = rs.getInt("ID"); //查询到需要修改结果的视频ID
				fileName = rs.getString("fileName");
			}
			
			sql = "update SunCityManage.dbo."+table+" set Vid="+vid+" where vid="+playID;  //修改播放视频列表结果
			System.out.println("修改结果的sql语句为："+sql);
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPTreasureDB.dbo.VideoFileNameList set VideoFileName='"+fileName+"' where VideoFileName='"+VideoFileName+"' and ServerID="+serverID; //修改播放的文件记录列表
			System.out.println("修改文件记录的sql语句为："+sql);
			ps = con.prepareStatement(sql);
			ps.execute();
			
			msg = "恭喜，修改结果成功!";
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
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
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return msg;
	}
	
	public List<RoomDTO> getRoom(){
		List<RoomDTO> list = new ArrayList<RoomDTO>() ;
			Connection con = null ;
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try {
				con = ds.getConnection();
				//String sql = "select * from QPTreasureDB.dbo.ServerRoom where kindID=4 order by ServerID asc";
				String sql = "select * from SunCityManage.dbo.rooms where type=1";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					RoomDTO dto = new RoomDTO();
					dto.setRoomId(rs.getInt("roomID"));
					dto.setRoomName(rs.getString("RoomName"));
					list.add(dto);
				}
				
				if(rs!=null){
					rs.close();rs=null;
				}
				if(ps!=null){
					ps.close();ps=null;
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
					if(ps!=null){
						ps.close();ps=null;
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
	
	
	
	public List<GameResultManageDTO> ListByPage(String where, int pageindex, int pageSize,String orderBy,int roomID) {
		List<GameResultManageDTO> list = null;
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		String table_View = "";
		if(roomID==1){
			table_View = "BJLPlayList1_View";
		}
		else if(roomID==2){
			table_View = "BJLPlayList2_View";
		}
		else if(roomID==3){
			table_View = "BJLPlayList3_View";
		}
		else if(roomID==4){
			table_View = "BJLPlayList4_View";
		}
		int playID = queryID(table_View);
		try {
			/*
			 * 
			 * Create Procedure [dbo].[GetRecordByPage2005] @TableName
			 * varchar(50), --表名 @Fields varchar(5000) = '*', --字段名(全部字段为*)
			 * @OrderField varchar(5000), --排序字段(必须!支持多字段) @sqlWhere
			 * varchar(5000) = Null,--条件语句(不用加where) @pageSize int, --每页多少条记录
			 * @pageIndex int = 1 , --指定当前为第几页 @TotalPage int output, --返回总页数
			 * @totalRecord int output
			 * 
			 */
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, table_View);// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, orderBy+" desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();
			list = new ArrayList<GameResultManageDTO>();
			
			while (rs.next()) {
				GameResultManageDTO dto = new GameResultManageDTO();
				int id = rs.getInt("id");
				dto.setId(rs.getInt("id"));
				dto.setFileName(rs.getString("fileName"));
				dto.setVideoName(rs.getString("videoName"));
				dto.setHgName(rs.getString("hgName"));
				dto.setVideoType(rs.getInt("videoType"));
				dto.setResult(rs.getString("result"));
				String resultofWinLost = rs.getString("resultOfWinLost");
				if("ZY".equals(resultofWinLost)){
					dto.setResultOfWinLost("庄赢");
					dto.setGameCss("red");
				}
				else if("XY".equals(resultofWinLost)){
					dto.setResultOfWinLost("闲赢");
					dto.setGameCss("green");
				}
				else if("HJ".equals(resultofWinLost)){
					dto.setResultOfWinLost("和局");
					dto.setGameCss("blue");
				}
				else{
					dto.setResultOfWinLost("等待视频");
					dto.setGameCss("orange");
				}
				dto.setBetSerial(rs.getString("betSerial"));
				String createTime = "";
				if(rs.getString("createTime")!=null && rs.getString("createTime")!=""){
					createTime = rs.getString("createTime").substring(0, 19);
				}
				dto.setRoomID(rs.getInt("roomID"));
				dto.setRoomName(rs.getString("roomName"));
				dto.setCreateTime(createTime);
				dto.setXh(rs.getInt("xh"));
/*				long pgold = queryGold(ds,rs.getString("betSerial"),0);视图没有的情况下使用这个
				long xgold = queryGold(ds,rs.getString("betSerial"),2);
				long zgold = queryGold(ds,rs.getString("betSerial"),1);*/  
				
				dto.setXgold(rs.getLong("xGold"));
				dto.setZgold(rs.getLong("zGold"));
				dto.setPgold(rs.getLong("pGold"));
				dto.setOpenCount(rs.getString("openCount"));
				int gameState = rs.getInt("State");
				dto.setGameState(gameState);
				if(id>playID-1){
					dto.setGameStateName("未播放");
					dto.setGameStateCss("#0000FF");
				}
				else if(id==playID-1){
					dto.setGameStateName("正在播放");
					dto.setGameStateCss("#FF0000");
				}
				else if(id<playID-1){
					dto.setGameStateName("已播放");
					dto.setGameStateCss("#999999");
				}
/*				if(gameState==0){
					dto.setGameStateName("等待开奖");
				}
				else{
					dto.setGameStateName("已开奖");
				}*/
				
//				dto.setServerID(rs.getInt("serverID"));
//				dto.setPlayID(rs.getInt("playID"));

				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (toesUp != null) {
				toesUp.close();
				toesUp = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (toesUp != null) {
					toesUp.close();
					toesUp = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return list;
	}
	
	public int queryID(String table){
		int id = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select top 1 * from "+table +" order by state,id asc";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
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
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return id;
	}
	
	
	public long queryGold(DataSource ds,String betSerial,int type){
		long gold = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		int gameArea = 0;
		if(type==0){
			gameArea = 1002; //平家
		}
		else if(type==1){
			gameArea = 1003; //庄家
		}
		else if(type==2){
			gameArea = 1001; //闲家
		}
		try {
			con = ds.getConnection();
			sql = "select sum(betgold) from QPTreasureDB.dbo.GameBetRecord where betSerial='"+betSerial+"' and gameArea="+gameArea;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				gold = rs.getLong(1);
			}
			
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
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
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			}
			 catch (SQLException e1) {
					e1.printStackTrace();
			 }
		}
		return gold;
	}
	
	

}

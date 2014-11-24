package com.keno8.struts.action.gameUser.mark;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.action.count.CountRecordDTO;
import com.keno8.struts.dto.GameRecordDTO;

public class GameUserBBTJDAO extends BaseDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public GameUserBBTJDAO(DataSource ds) {
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
	public Long count3DRecord(int roomId,String where){   //开奖管理下的报表统计
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps =null;
		String sql = "select * from gamePickRecord where roomId="+roomId+where;
		long sumGold = 0;
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
		} catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return sumGold;
	}
	public Long countRecord(int roomId,String where){   //开奖管理下的报表统计
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps =null;
		String sql = "select * from GameRecordView where roomId="+roomId+where;
		long sumGold = 0;
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
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return sumGold;
	}
	public GameUserBBTJDTO GetBySum() {
		GameUserBBTJDTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		//String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,sum(ck)as ck,sum(qk)as qk,sum(score)as score,sum(rebate) as rebate from BBTJ_UserDoByTime ";
		String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,sum(ck)as ck,sum(qk)as qk,sum(score)as score,sum(rebate) as rebate from BBTJ_UserDo_View ";
		System.out.println("报表统计汇总-sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new GameUserBBTJDTO();
				dto.setCk(rs.getDouble("ck"));
				dto.setQk(rs.getDouble("qk"));
				dto.setScore(rs.getDouble("score"));
				dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
				dto.setWinlostMoney(rs.getDouble("winlostMoney"));
				dto.setRebate(rs.getDouble("rebate"));
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public GameUserBBTJDTO GetBySum(int pageindex,int pageSize,String where,String orderby,double score) {
		GameUserBBTJDTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		//String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,sum(ck)as ck,sum(qk)as qk,score,sum(rebate) as rebate from BBTJ_UserDoByTime_View where "+where+" group by score;
		String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,sum(ck)as ck,sum(qk)as qk,sum(score) as score,sum(rebate) as rebate from BBTJ_UserDoByTime_View where "+where;
		System.out.println("报表统计汇总 byTime-sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new GameUserBBTJDTO();
				dto.setCk(rs.getDouble("ck"));
				dto.setQk(rs.getDouble("qk"));
				dto.setScore(score);
				dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
				dto.setWinlostMoney(rs.getDouble("winlostMoney"));
				dto.setRebate(rs.getDouble("rebate"));
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public GameUserBBTJDTO GetBySumOfDay() {
		GameUserBBTJDTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		//String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,sum(ck)as ck,sum(qk)as qk,sum(score)as score,sum(rebate) as rebate from BBTJ_UserDoByTime ";
		String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,sum(ck)as ck,sum(qk)as qk,sum(score)as score,sum(rebate) as rebate from BBTJ_UserDoByTime_View_InRoomID ";
		System.out.println("当日报表汇总-sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new GameUserBBTJDTO();
				dto.setCk(rs.getDouble("ck"));
				dto.setQk(rs.getDouble("qk"));
				dto.setScore(rs.getDouble("score"));
				dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
				dto.setWinlostMoney(rs.getDouble("winlostMoney"));
				dto.setRebate(rs.getDouble("rebate"));
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public GameUserBBTJDTO GetBySumOfDay(int pageindex,int pageSize,String where,String orderby,double nowScore) {
		GameUserBBTJDTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		//String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,sum(ck)as ck,sum(qk)as qk,sum(score)as score,sum(rebate) as rebate from BBTJ_UserDoByTime where "+where;
		String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,sum(ck)as ck,sum(qk)as qk,sum(score) as score,sum(rebate) as rebate from BBTJ_UserDoByTime_View_InRoomID where "+where;
		System.out.println("当日报表汇总 byTime-汇总sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new GameUserBBTJDTO();
				dto.setCk(rs.getDouble("ck"));
				dto.setQk(rs.getDouble("qk"));
				dto.setScore(nowScore);
				dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
				dto.setWinlostMoney(rs.getDouble("winlostMoney"));
				dto.setRebate(rs.getDouble("rebate"));
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public List<GameUserBBTJDTO> GetByTime(int pageindex,int pageSize,String where,String orderby,List<GameUserBBTJDTO> scorelist ) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		//String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,userId,sum(ck)as ck,sum(qk)as qk,sum(score)as score,sum(rebate) as rebate,nickName,accounts from BBTJ_UserDoByTime where "+where+"  group by userId,nickName,accounts order by "+orderby+" desc";
		String sql ="select Sum(totalGoldXZ) as totalGoldXZ,sum(winLostMoney) as winLostMoney,userId,sum(ck)as ck,sum(qk)as qk,sum(score)as score,sum(rebate) as rebate,nickName,accounts from BBTJ_UserDoByTime_View where "+where+"  group by userId,nickName,accounts order by "+orderby+" desc";
		System.out.println("GetByTime sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				for(int i = 0; i<scorelist.size();i++){
//				System.out.println("scorelist大小:"+scorelist.size()+"scorelist:"+scorelist.get(i).getUserID()+",userID:"+rs.getInt("userID"));
				if(rs.getInt("userID") == scorelist.get(i).getUserID()){
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setNickName(rs.getString("nickName"));
				dto.setCk(rs.getDouble("ck"));
				dto.setQk(rs.getDouble("qk"));
				dto.setScore(scorelist.get(i).getScore());
				dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
				dto.setWinlostMoney(rs.getDouble("winlostMoney"));
				dto.setRebate(rs.getDouble("rebate"));
				list.add(dto);
				}
				}
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	
	
	public List<GameUserBBTJDTO> GetByPage(int pageindex,int pageSize,String where,String orderby) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
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
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "BBTJ_UserDo_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			int i = 1;
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setNickName(rs.getString("nickName"));
				dto.setCk(rs.getDouble("ck"));
				dto.setQk(rs.getDouble("qk"));
				dto.setScore(rs.getDouble("score"));
				dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
				dto.setWinlostMoney(rs.getDouble("winlostMoney"));
				dto.setRebate(rs.getDouble("rebate"));
				
				double qk = rs.getDouble("qk");
				double ck = rs.getDouble("ck");
				double winlostMoney = rs.getDouble("winlostMoney");
				double rebate = Double.parseDouble(rs.getString("rebate"));
				double score = rs.getDouble("score");                      //实际金额
				double score2 = ck-qk+winlostMoney+rebate;                 //应有金额
				double score3 = Math.round((score2-score)*100)/100;
				
				double pureGold = -score3;
				if(score3 != 0){
					System.out.println(i+">>>userID:"+rs.getInt("userID")+"用户名："+rs.getString("accounts")+" ,应有金额=存款"+ck+"-取款"+qk+"+输赢("+winlostMoney+")+返水"+rebate+"="+new BigDecimal(score2).setScale(2, BigDecimal.ROUND_HALF_UP)+",实际金额："+score+".实际金额-应有金额="+score3+",pureGold:"+pureGold);
				}else{
					System.out.println("---------------"+i+">>>userID:"+rs.getInt("userID")+"用户名："+rs.getString("accounts")+" ,应有金额=存款"+ck+"-取款"+qk+"+输赢("+winlostMoney+")+返水"+rebate+"="+new BigDecimal(score2).setScale(2, BigDecimal.ROUND_HALF_UP)+",实际金额："+score+".实际金额-应有金额="+score3);
					
				}
				i++;
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	
	public List<GameUserBBTJDTO> GetDayByPage(int pageindex,int pageSize,String where,String orderby,List<GameUserBBTJDTO> listOfScoreByYesterday) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
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
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			//toesUp.setString(1, "BBTJ_UserDoByTime");//表名 
			toesUp.setString(1, "BBTJ_UserDoByTime_View_InRoomID");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			
			while(rs.next()){
				if(listOfScoreByYesterday.size() > 0){
					for(int i = 0; i<listOfScoreByYesterday.size();i++){
					if(rs.getInt("userID") == listOfScoreByYesterday.get(i).getUserID()){
//				    System.out.println("........"+listOfScoreByYesterday.get(i).getUserID()+"-----------");
					GameUserBBTJDTO dto = new GameUserBBTJDTO();
					dto.setUserID(rs.getInt("userID"));
					dto.setAccounts(rs.getString("accounts"));
					dto.setNickName(rs.getString("nickName"));
					dto.setScore(rs.getDouble("score"));
					dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
					dto.setWinlostMoney(rs.getDouble("winlostMoney"));
					dto.setCreateTime(rs.getString("createTime"));
					dto.setCk(rs.getDouble("ck"));
					dto.setQk(rs.getDouble("qk"));
					dto.setRebate(rs.getDouble("rebate"));
					dto.setYesterdayGold(listOfScoreByYesterday.get(i).getYesterdayGold());
					dto.setYesterdayGoldName(listOfScoreByYesterday.get(i).getYesterdayGoldName());
	//				dto.setPeroidnum(rs.getString("peroidnum"));
					
					list.add(dto);
					break;
					}else{
					       if(i==(listOfScoreByYesterday.size()-1)){
//						       System.out.println(i+","+listOfScoreByYesterday.size()+">>>>>"+rs.getInt("userID")+"--"+listOfScoreByYesterday.get(0).getYesterdayGoldName());
						       GameUserBBTJDTO dto = new GameUserBBTJDTO();
								dto.setUserID(rs.getInt("userID"));
								dto.setAccounts(rs.getString("accounts"));
								dto.setNickName(rs.getString("nickName"));
								dto.setScore(rs.getDouble("score"));
								dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
								dto.setWinlostMoney(rs.getDouble("winlostMoney"));
								dto.setCreateTime(rs.getString("createTime"));
								dto.setCk(rs.getDouble("ck"));
								dto.setQk(rs.getDouble("qk"));
								dto.setRebate(rs.getDouble("rebate"));
								dto.setYesterdayGold(0);
								dto.setYesterdayGoldName(listOfScoreByYesterday.get(0).getYesterdayGoldName());
								list.add(dto);
					       }
					     }
					}
				}else{
						GameUserBBTJDTO dto = new GameUserBBTJDTO();
						dto.setUserID(rs.getInt("userID"));
						dto.setAccounts(rs.getString("accounts"));
						dto.setNickName(rs.getString("nickName"));
						dto.setScore(rs.getDouble("score"));
						dto.setTotalGoldXZ(rs.getDouble("totalGoldXZ"));
						dto.setWinlostMoney(rs.getDouble("winlostMoney"));
						dto.setCreateTime(rs.getString("createTime"));
						dto.setCk(rs.getDouble("ck"));
						dto.setQk(rs.getDouble("qk"));
						dto.setRebate(rs.getDouble("rebate"));
						dto.setYesterdayGold(0);
						dto.setYesterdayGoldName("未记录数据");
						
						list.add(dto);
				}
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	
	public List<CountRecordDTO> queryRoomID(){
		Connection con = null;
		ResultSet rs = null;
		List<CountRecordDTO> list = new ArrayList<CountRecordDTO>();
		String sql = "select * from rooms";
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
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, null);
		}
		return list;
	}
	
	//快乐8 游戏记录
	public List<GameRecordDTO> GetDetailOfGameRecordByPage(int pageindex,int pageSize,String where,String orderby) {
		List<GameRecordDTO> list = new ArrayList<GameRecordDTO>();
		Connection con = null;
		ResultSet rs=null;
		CallableStatement toesUp = null;
		try {
			
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
			
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
		    toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "gameKeno8Details");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameRecordDTO dto = new GameRecordDTO();
				dto.setUserId(rs.getInt("userId"));
				dto.setAccounts(rs.getString("accounts"));
				String createTime = rs.getString("createTime");
				createTime = createTime.substring(0, 19);
				dto.setCreateTime(createTime);//下注时间
				String time1 = rs.getString("createTime1");
				if(time1==null){
					time1="";
				}
				else if(time1.length()>19){
					time1 = time1.substring(0, 19);
				}
				dto.setCreateTime1(time1);//开奖时间
				dto.setWinLostMoney(rs.getString("winLostMoney"));  //输赢
				dto.setRoomDisPlayName(rs.getString("displayName"));
				dto.setPeroidnum(rs.getString("peroidnum"));    //期数
				dto.setTotalGoldBet(rs.getDouble("totalGoldBet")); //下注金额
				dto.setLarge(rs.getInt("large"));
				dto.setPairs(rs.getInt("pairs"));
				dto.setPeace(rs.getInt("peace"));
				dto.setSmall(rs.getInt("small"));
				dto.setSerpent(rs.getInt("serpent"));
				dto.setSingle(rs.getInt("single"));
				dto.setTiger(rs.getInt("tiger"));
				dto.setRate(rs.getInt("rate"));
				
				BigDecimal b = new BigDecimal(rs.getString("lastMoney"));  
				String f1 = String.valueOf(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());  
				dto.setLastMoney(f1);
				//dto.setLastGold(rs.getLong("lastMoney"));
				String rateNumber = rs.getString("rateNumber");
				if(rateNumber != null){
				 rateNumber = rateNumber.replaceAll("&", " ");
				}
				dto.setRateNumber(rateNumber);
				dto.setRebate(rs.getDouble("rebate"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	
	
	public void setByTotalScore() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from BBTJ_UserDo_View";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int i = 1;
			while(rs.next()){
				
				double qk = rs.getDouble("qk");
				double ck = rs.getDouble("ck");
				double winlostMoney = rs.getDouble("winlostMoney");
				double rebate = Double.parseDouble(rs.getString("rebate"));
				double score = rs.getDouble("score");                  //实际金额
				double score2 = ck-qk+winlostMoney+rebate;             //应有金额
				double score3 = Math.round((score2-score)*100)/100;
				double pureGold = -score3;
				
				if(score3 != 0){

					System.out.println(i+">>>userID:"+rs.getInt("userID")+"用户名："+rs.getString("accounts"));
					GameRecordDTO dto = new GameRecordDTO();
					dto.setUserId(rs.getInt("userId"));
					dto.setRoomId(0);
					dto.setPeroidnum("0");
					dto.setGameRuleId(0);
					dto.setTotalGoldBet(0); //下注
					dto.setPureGold(pureGold);         //输赢
					dto.setRebate(0);                   //返水
					this.insertGameRecord(dto);
				}
				i++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		
	}
	
	public List<GameUserBBTJDTO> queryScore(){
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from BBTJ_UserDo_View";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	GameUserBBTJDTO dto = new GameUserBBTJDTO();
            	dto.setScore(rs.getDouble("score"));
            	dto.setUserID(rs.getInt("userId"));
            	list.add(dto);
            }
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		
		return list;
	}
	
	public double GetScore(String userName){
		double score = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select sum(score) as score from BBTJ_UserDo_View where  accounts like '%"+userName+"%'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	score = rs.getDouble("score");
            }
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return score;
	}
	public double GetScoreByID(int userId){
		double score = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select score from BBTJ_UserDo_View where  userId="+userId;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	score = rs.getDouble("score");
            }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return score;
	}
	
	public double GetSumOfScoreByDay(String where){
		double score = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select Sum(score) as score from BBTJ_UserDoByTime_View_InRoomID where "+where+" and totalGoldXZ <> 0";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	score = rs.getDouble("score");
            }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return score;
	}
	
	public double GetSumOfScoreByYesterday(String where){
		double score = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select Sum(score) as score from moneyRecord_View where "+where+"+"+1;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	score = rs.getDouble("curmoney");
            }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return score;
	 }
	public List<GameUserBBTJDTO> GetListOfScoreByYesterday(String where){
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from moneyRecord_View where "+where+"+"+1;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	GameUserBBTJDTO dto = new GameUserBBTJDTO();
            	dto.setYesterdayGold(rs.getDouble("curmoney"));
            	dto.setUserID(rs.getInt("userId"));
            	String name = rs.getString("createTime").substring(0,10);
            	dto.setYesterdayGoldName(name);
            	list.add(dto);
            }
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return list;
	 }
	
	
    public void insertGameRecord(GameRecordDTO dto) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
//		String sql = "insert into gameRecord(userId,roomId,peroidnum,gameRuleId,totalGoldBet,single,serpent,large,peace,tiger,small,pairs,rate,rateNumber,firstMoney,lastMoney) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	String sql = "insert into gameRecord(userId,roomId,peroidnum,gameRuleId,totalGoldBet,pureGold,rebate) values(?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getRoomId());
			pstmt.setString(3, dto.getPeroidnum());
			pstmt.setInt(4, dto.getGameRuleId());
			pstmt.setDouble(5, dto.getTotalGoldBet());
			pstmt.setDouble(6, dto.getPureGold());
			pstmt.setDouble(7, dto.getRebate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDBObject(conn, null, null, null, pstmt);
		}
	}
	

}

package com.doowal.hk798.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.sql.DataSource;


public class DataCountDAO {
	private DataSource ds;

	public DataCountDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public static int getLastDayOfUpMonth(int year,int month,int date) {
		   Calendar calendar = new GregorianCalendar(year,month,date);
		   calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		   calendar.add(Calendar.MONTH,-1);//月增减1天 
		   calendar.add(Calendar.DAY_OF_MONTH,-1);//日期倒数一日,既得到本月最后一天 
		   //System.out.println("上个月的最后一天是："+calendar.get(Calendar.DATE)+"号");
		   return calendar.get(Calendar.DATE);
		}

	public String[] countDate(String[] params){
		String[] result = new String[15];
		String sql = "{ call JAVA_PW_DataCount (?,?)}";
		Connection con = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			rs = callstmt.executeQuery();
			while (rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				result[3] = rs.getString(4);
				result[4] = rs.getString(5);
				result[5] = rs.getString(6);
				result[6] = rs.getString(7);
				result[7] = rs.getString(8);
				result[8] = rs.getString(9);
				result[9] = rs.getString(10);
				result[10] = rs.getString(11);
				result[11] = rs.getString(12);
				result[12] = rs.getString(13);
				result[13] = rs.getString(14);
				result[14] = rs.getString(15);
			}
			//今天报表
			/*String sql = "select sum(cardGold)from ShareDetailInfo where datediff(day,[ApplyDate],getdate())=0";//充值卡
			
			sql = "select sum(jine) from BankPay where state=1 and datediff(day,[rp_PayDate],getdate())=0"; //网银充值
			
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0"; //机器人输赢
			
			sql = "select count(*) from QPAccountsDB.dbo.AccountsProxy where datediff(day,[CreateTime],getdate())=0";//新增代理

			sql = "select count(*) from QPAccountsDB.dbo.AccountsInfo where datediff(day,[RegisterDate],getdate())=0";//新增会员
			
			sql = "select GameTotalScore,BankTotalScore from GameScoreDayView where datediff(day,[CreateTime],getdate())=0";//游戏总银子，钱庄总银子
*/			
/*			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=104";//百人牛牛
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=105";//百人二张
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=1001";//新百家乐
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=2023";//德州
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=1005";//梭哈
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=2019";//牛牛
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=2025";//双扣
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=3116";//斗地主
*/			
			if(rs!=null){
				rs.close();rs=null;
			}
			if(callstmt!=null){
				callstmt.close();callstmt=null;
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
				if(callstmt!=null){
					callstmt.close();callstmt=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public long sumScore(){
		long score = 0;
		String sql = "select sum(score) as score from QPTreasureDB.dbo.GameScoreInfo where userID not in (select userID from QPTreasureDB.dbo.AndroidManager) and userID not in(1,2)";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				score = rs.getLong("score");
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
			try {
				if(rs!=null){
					rs.close();rs=null;
				}
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return score;
	}
	
	public long sumInsureScore(){
		long InsureScore = 0;
		String sql = "select sum(InsureScore) as InsureScore from QPTreasureDB.dbo.GameScoreInfo where userID not in (select userID from QPTreasureDB.dbo.AndroidManager) and userID not in(1,2)";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				InsureScore = rs.getLong("InsureScore");
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
			try {
				if(rs!=null){
					rs.close();rs=null;
				}
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return InsureScore;
	}
	
	public String[] countDate1(String[] params){
		String[] result = new String[15];
		String sql = "{ call JAVA_PW_DataCount (?,?)}";
		Connection con = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			rs = callstmt.executeQuery();
			while (rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				result[3] = rs.getString(4);
				result[4] = rs.getString(5);
				result[5] = String.valueOf(sumScore());//游戏总金币
				result[6] = String.valueOf(sumInsureScore()); //钱庄总金币
				/*result[5] = rs.getString(6);
				result[6] = rs.getString(7);*/
				result[7] = rs.getString(8);
				result[8] = rs.getString(9);
				result[9] = rs.getString(10);
				result[10] = rs.getString(11);
				result[11] = rs.getString(12);
				result[12] = rs.getString(13);
				result[13] = rs.getString(14);
				result[14] = rs.getString(15);
			}
			//今天报表
			/*String sql = "select sum(cardGold)from ShareDetailInfo where datediff(day,[ApplyDate],getdate())=0";//充值卡
			
			sql = "select sum(jine) from BankPay where state=1 and datediff(day,[rp_PayDate],getdate())=0"; //网银充值
			
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0"; //机器人输赢
			
			sql = "select count(*) from QPAccountsDB.dbo.AccountsProxy where datediff(day,[CreateTime],getdate())=0";//新增代理

			sql = "select count(*) from QPAccountsDB.dbo.AccountsInfo where datediff(day,[RegisterDate],getdate())=0";//新增会员
			
			sql = "select GameTotalScore,BankTotalScore from GameScoreDayView where datediff(day,[CreateTime],getdate())=0";//游戏总银子，钱庄总银子
*/			
/*			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=104";//百人牛牛
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=105";//百人二张
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=1001";//新百家乐
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=2023";//德州
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=1005";//梭哈
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=2019";//牛牛
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=2025";//双扣
			sql = "select sum(waste) from RecordDrawInfo where datediff(day,[insertTime],getdate())=0 and kindId=3116";//斗地主
*/			
			if(rs!=null){
				rs.close();rs=null;
			}
			if(callstmt!=null){
				callstmt.close();callstmt=null;
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
				if(callstmt!=null){
					callstmt.close();callstmt=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	

}

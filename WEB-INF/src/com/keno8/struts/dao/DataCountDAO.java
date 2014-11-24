package com.keno8.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import com.keno8.struts.dto.DataCountDTO;


public class DataCountDAO {
	private DataSource ds;
	
	//private ResultSet rs;
	//private PreparedStatement ps;

	public DataCountDAO(DataSource ds) {
		//super();
		this.ds = ds;
	}
	
	



	public DataCountDTO countDate(DataCountDTO dto){
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			Date d = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("kk");//其中yyyy-MM-dd是你要表示的格式
			//可以任意组合，不限个数和次序；具体表示为：MM-month,dd-day,yyyy-year;kk-hour,mm-minute,ss-second;
			String str=sdf.format(d);
			long sumScore = 0;
			long score = 0;
			long lishiSumScore = 0;
			long lishiScore = 0;
			long lishiInsureScore = 0;
			int lishiCount = 0;
			long lishiCunGold = 0;
			long lishiQuGold = 0;
			long lishiUserScore= 0;
			if(str.equals("09")){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>进入历史记录");
				ps = con.prepareStatement("select sum(Score) from RegAccountsInfo");//历史总银子
				rs = ps.executeQuery();
				if(rs.next()){
				dto.setLishiScore(rs.getLong(1));
				lishiScore = rs.getLong(1);
				}
				
				ps = con.prepareStatement("select sum(insureScore) from RegAccountsInfo");//历史总钱庄银子
				rs = ps.executeQuery();
				if(rs.next()){
				dto.setLishiInsureScore(rs.getLong(1));
				lishiInsureScore = rs.getLong(1);
				}
				dto.setLishiSumScore(dto.getLishiScore()+dto.getLishiInsureScore());
				lishiSumScore = dto.getLishiScore()+dto.getLishiInsureScore();
				
				ps = con.prepareStatement("select count(*) from RegAccountsInfo"); //历史总注册人数
				rs = ps.executeQuery();
				if(rs.next()){
				dto.setLishiCount(rs.getInt(1));
				lishiCount = rs.getInt(1);
				}
				
				ps = con.prepareStatement("select sum(jine) from bankpay where state=1");//历史存款总额
				rs = ps.executeQuery();
				if(rs.next()){
					lishiCunGold = rs.getLong(1);
				}
				
				ps = con.prepareStatement("select sum(amount) from drawAmountApply where applytype=1");//历史取款总额
				rs = ps.executeQuery();
				if(rs.next()){
					lishiQuGold = rs.getLong(1);
				}
				
				ps = con.prepareStatement("update GameExchangePreferences set lishiSumScore=?,lishiScore=?,lishiInsureScore=?,lishiCount=?,lishiCunGold=?,lishiQuGold=? where id =1");
				ps.setLong(1, lishiSumScore);
				ps.setLong(2, lishiScore);
				ps.setLong(3, lishiInsureScore);
				ps.setInt(4, lishiCount);
				ps.setLong(5, lishiCunGold);
				ps.setLong(6, lishiQuGold);
				ps.execute();
				}
			
			
			ps = con.prepareStatement("select * from GameExchangePreferences where id = 1");//总银子
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setLishiSumScore(rs.getLong("lishiSumScore"));
				dto.setLishiInsureScore(rs.getLong("lishiInsureScore"));
				dto.setLishiScore(rs.getLong("LishiScore"));
				dto.setLishiCount(rs.getInt("LishiCount"));
				dto.setLishiCunGold(rs.getLong("lishiCunGold"));
				dto.setLishiQuGold(rs.getLong("lishiQuGold"));
			}
			
			ps = con.prepareStatement("select count(*) from RegAccountsInfo"); //总注册人数
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setCount(rs.getInt(1));
			}

			ps = con.prepareStatement("select sum(Score) from RegAccountsInfo");//总游戏银子
			rs = ps.executeQuery();
			if(rs.next()){
			dto.setScore(rs.getLong(1));
			score = rs.getLong(1);
			}

			ps = con.prepareStatement("select sum(insureScore) from RegAccountsInfo");//总钱庄银子
			rs = ps.executeQuery();
			if(rs.next()){
			dto.setInsureScore(rs.getLong(1));
			}
			dto.setSumScore(dto.getScore()+dto.getInsureScore()); //总银子
			
			
			ps = con.prepareStatement("select sum(jine) from bankpay where state=1");//存款总额
			rs = ps.executeQuery();
			if(rs.next()){
			dto.setCunGold(rs.getLong(1));
			}
			
			ps = con.prepareStatement("select sum(amount) from drawAmountApply where applytype=1");//取款总额
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setQuGold(rs.getLong(1));
			}

			ps = con.prepareStatement("select count(*) from RegAccountsInfo where RegisterDate>=dateadd(day,1-day(getdate()),convert(varchar,getdate(),112))"+   
  "and RegisterDate<dateadd(month,1,dateadd(day,1-day(getdate()),convert(varchar,getdate(),112)))");//本月注册人数
			rs = ps.executeQuery();
			if(rs.next()){
			dto.setMonthRegisterUser(rs.getInt(1));
			}
			
			ps = con.prepareStatement("select count(*) from RegAccountsInfo where RegisterDate>=dateadd(day,2-datepart(weekday,getdate()),convert(varchar,getdate(),112))"+   
  "and RegisterDate<dateadd(day,9-datepart(weekday,getdate()),convert(varchar,getdate(),112)) ");//本周注册人数
						rs = ps.executeQuery();
						if(rs.next()){
						dto.setWeekRegisterrUser(rs.getInt(1));
						
						}
						
						dto.setChaerSumScore(dto.getSumScore()-dto.getLishiSumScore()); //差额总银子
						dto.setChaerScore(dto.getScore()-dto.getLishiScore());
						dto.setChaerInsureScore(dto.getInsureScore()-dto.getLishiInsureScore());
						dto.setChaerLishiCount(dto.getCount()-dto.getLishiCount());
						dto.setChaerLishiCunGold(dto.getCunGold()-dto.getLishiCunGold());
						dto.setChaerLishiQuGold(dto.getQuGold()-dto.getLishiQuGold());
						
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
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}
		return dto;
	}
	
	public Long queryYeepayRate(){
		long yeepayRate =0;
		int id = 1;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from GameExchangePreferences where id=?";
		try{
			con=ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				yeepayRate = rs.getLong("yeepayRate");
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
		}catch(Exception ex){
			ex.printStackTrace();
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
			}
			catch(Exception ex1){
				ex1.printStackTrace();
			}
			
		}
		return yeepayRate;
	}
	
/*	public DataCountDTO countcall(DataCountDTO dto){
		try {
			ps = con.prepareStatement("select sum(WebLogonSuccess),sum(WebRegisterSuccess),sum(GameLogonSuccess),sum(GameRegisterSuccess) from SystemStreamInfo");
			rs = ps.executeQuery();
			if(rs.next()){
			dto.setWebLogonSuccess(rs.getInt(1));
			dto.setWebRegisterSuccess(rs.getInt(2));
			dto.setGameLogonSuccess(rs.getInt(3));
			dto.setGameRegisterSuccess(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}*/

}
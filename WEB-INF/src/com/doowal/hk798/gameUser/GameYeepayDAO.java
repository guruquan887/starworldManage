package com.doowal.hk798.gameUser;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class GameYeepayDAO extends BaseDAO{
	
	private int pageCount;
	private int recordCount;
	private DataSource ds;

	public GameYeepayDAO(DataSource ds) {
		this.ds = ds;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public Boolean updateyeepay(String dh,long jiner) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			int userID = 0;
			ps = con.prepareStatement("select * from NNGameManage.dbo.bankpay where dh=?");
			ps.setString(1, dh);
			rs = ps.executeQuery();
			
			if(rs.next()){
				userID = rs.getInt("userid");
			}
			
			int yeepayRate = 0;
			ps = con.prepareStatement("select * from QPTreasureDB.dbo.GameExchangePreferences where id=1");
			rs = ps.executeQuery();
			if(rs.next()){
				yeepayRate = rs.getInt("yeepayRate");
			}
			
			long score = jiner * yeepayRate;
			
			ps = con.prepareStatement("update QPTreasureDB.dbo.GameScoreInfo set insureScore=insureScore+?,RechargeMoney=RechargeMoney+? where userID=?");
			ps.setLong(1, score);
			ps.setLong(2, score);
			ps.setInt(3, userID);
			ps.execute();
			
			ps = con.prepareStatement("update NNGameManage.dbo.bankpay set state=1,r3_Amt=? where dh=?");
			ps.setLong(1, jiner);
			ps.setString(2, dh);
			ps.execute();
			
			ps = con.prepareStatement("insert into QPTreasureDB.dbo.GameOtherRecord(WinUserId,WinScore,RecordTypeID,Remarks) values (?,?,3,'网银充值')");
			ps.setInt(1, userID);
			ps.setLong(2, score);
			ps.execute();
			r=true;
			
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
			this.closeDBObject(con, rs, null, null, ps);
		}
		return r;
	}
	
	public Boolean deleteyeepay(String dh) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from NNGameManage.dbo.bankpay where dh=?");
			ps.setString(1, dh);
			ps.execute();
			r=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public int queryUserID(String accounts) {
		int userid = 0;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from QPGameUserDB.dbo.AccountsInfo where accounts=?");
			ps.setString(1, accounts);
			rs = ps.executeQuery();
			if (rs.next()) {
				userid = rs.getInt("userID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return userid;
	}
	
	public int add(String dh,int userid,long r3_Amt) {
		int r = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update QPTreasureDB.dbo.GameScoreInfo set insureScore=insureScore+?,RechargeMoney=RechargeMoney+? where userID=?");
			ps.setLong(1, r3_Amt);
			ps.setLong(2, r3_Amt);
			ps.setInt(3, userid);
			ps.execute();
			
			ps = con.prepareStatement("insert into NNGameManage.dbo.bankpay(dh,userid,userid2,jine,state,r3_Amt) values(?,?,?,?,1,?)");
			ps.setString(1, dh);
			ps.setInt(2, userid);
			ps.setInt(3, userid);
			ps.setFloat(4, r3_Amt);
			ps.setFloat(5, r3_Amt);
			ps.execute();
			r = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public Boolean checkExist(String accounts){
		Boolean r=false;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql="select * from QPGameUserDB.dbo.AccountsInfo where accounts='"+accounts+"'";
			rs = st.executeQuery(sql);
			if(rs.next()){
				r=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, st, null, null);
		}
		return r;
	}
	
	
	public List<GameYeepayDTO> GetRecordByPage(int pageindex,int pageSize,String where) {
		List<GameYeepayDTO> list = new ArrayList<GameYeepayDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
		try {
			
			ResultSet rs=null;
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
			toesUp.setString(1, "UserbankpayView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "xdtime desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			//l=new ArrayList();
			while(rs.next()){
				GameYeepayDTO dto = new GameYeepayDTO();
				dto.setId(rs.getInt("id"));
				String userid = rs.getString("userid");
				dto.setUsername(rs.getString("accounts"));
				dto.setUsername1(rs.getString("username1"));
				String userid1 = rs.getString("userid2");
				
				dto.setDh(rs.getString("dh"));
				dto.setXdtime(rs.getString("xdtime").substring(0, 19));
				dto.setR2_TrxId(rs.getString("r2_TrxId"));
				dto.setR3_Amt(rs.getLong("r3_Amt"));
				dto.setJine(rs.getLong("jine"));
				int state=rs.getInt("state");
				dto.setTelphone(rs.getString("telphone"));
				dto.setState(state);
				if(0==state){
					dto.setStateName("未充值成功");
				}
				else if(1==state){
					dto.setStateName("已充值成功");
				}
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(true);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
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
	
	
	
	public int getTotalPage() {
		return pageCount;
	}
	public int getRcordCount() {
		return recordCount;
	}
	

}

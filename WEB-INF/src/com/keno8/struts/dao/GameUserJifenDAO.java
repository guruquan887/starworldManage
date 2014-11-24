package com.keno8.struts.dao;

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

import beartool.MD5;

import com.keno8.struts.dto.GameUserDTO;

public class GameUserJifenDAO {
	private int pageCount;
	private int recordCount;
	private DataSource ds;

	public GameUserJifenDAO(DataSource ds) {
		this.ds = ds;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}

	public List<GameUserDTO> select(){
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from AccountsInfo");
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserDTO dto  = new GameUserDTO();
				dto.setUserID(rs.getInt("UserID"));
				dto.setAccounts(rs.getString("Accounts"));
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
	
	public List<GameUserDTO> GetRecordByPage(int pageindex,int pageSize,String orderby) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
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
			toesUp.setString(1, "RegAccountsInfo");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, "");//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				int userID = rs.getInt("UserID");
				dto.setUserID(userID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setNickName(rs.getString("nickName"));
				int t = rs.getInt("sex");
				dto.setSex(t);
				if(t==0)dto.setSexName("男");
				else dto.setSexName("女");
				dto.setScore(rs.getLong("score"));
				dto.setInsureScore(rs.getLong("insureScore"));
				if(rs.getString("telphone")!=null){
					dto.setTelphone("暂无!");
				}
				else{
					dto.setTelphone(rs.getString("telphone"));
				}
				if(rs.getString("email")!=null){
					dto.setEmail("暂无!");
				}
				else{
					dto.setEmail(rs.getString("email"));
				}
				String t1=rs.getString("RegisterDate");
				if(t1!=null&&t1.length()>19){
					t1=t1.substring(0,19);
				}
				dto.setRegisterDate(t1);
				dto.setRegisterIP(rs.getString("registerIP"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
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
	
	public List<GameUserDTO> selectByName(int pageindex,int pageSize,String where,String orderby) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
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
			con.setAutoCommit(false);
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "RegAccountsInfo");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				int userID = rs.getInt("UserID");
				dto.setUserID(userID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setNickName(rs.getString("nickName"));
				int t = rs.getInt("sex");
				dto.setSex(t);
				if(t==0)dto.setSexName("男");
				else dto.setSexName("女");
				dto.setScore(rs.getLong("score"));
				dto.setInsureScore(rs.getLong("insureScore"));
				String t1=rs.getString("RegisterDate");
				if(t1!=null&&t1.length()>19){
					t1=t1.substring(0,19);
				}
				dto.setRegisterDate(t1);
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(true);
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
	
	public GameUserDTO getById(int userID) {
		GameUserDTO dto = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from RegAccountsInfo where userID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new GameUserDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("Accounts"));
				dto.setNickName(rs.getString("nickName"));
				dto.setSex(rs.getInt("Sex"));
				dto.setLoginPass(rs.getString("LoginPass"));
				dto.setEmail(rs.getString("email"));
				dto.setScore(rs.getLong("score"));
				dto.setInsureScore(rs.getLong("insureScore"));
				dto.setTelphone(rs.getString("telphone"));
				dto.setMmLogonPass(rs.getString("mmLogonPass"));
				dto.setRegisterDate(rs.getString("registerDate").substring(0,19));
				dto.setRegisterIP(rs.getString("registerIP"));
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
			}catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return dto;
	}
	
	public int queryUserID(String accounts) {
		int userid = 0;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from RegAccountsInfo where accounts=?");
			ps.setString(1, accounts);
			rs = ps.executeQuery();
			if (rs.next()) {
				userid = rs.getInt("userID");
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
			}catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return userid;
	}
	
	
	public void update(GameUserDTO dto) {
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update RegAccountsInfo set Accounts=?,nickName=?,LoginPass=?,mmLogonPass=?,sex=?,Email=?,telphone=?,score=?,insureScore=? where UserID=?");
			ps.setString(1, dto.getAccounts());
			ps.setString(2, dto.getNickName());
			MD5 md5=new MD5();
			String LoginPass = dto.getLoginPass();
			if(LoginPass!=null&&!"".equals(LoginPass)){
				String logonPass1 = md5.getMD5ofStr(LoginPass).toLowerCase();
				ps.setString(3, logonPass1);
			}
			ps.setString(4, LoginPass);
			ps.setInt(5, dto.getSex());
			ps.setString(6, dto.getEmail());
			ps.setString(7, dto.getTelphone());
			ps.setDouble(8, dto.getScore());
			ps.setDouble(9, dto.getInsureScore());
			ps.setInt(10, dto.getUserID());
			ps.execute();
			System.out.println("update success!!!!!!!!!!!!!");
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
			}catch (SQLException e1) {
					e1.printStackTrace();
			}
		}
	}
	
	public Boolean checkExist(String accounts){
		Boolean r=false;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql="select * from RegAccountsInfo where accounts='"+accounts+"'";
			rs = st.executeQuery(sql);
			if(rs.next()){
				r=true;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(st!=null){
				st.close();st=null;
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
				if(st!=null){
					st.close();st=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return r;
	}
	
	public int add(String dh,int userid,float r3_Amt) {
		int r = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update RegAccountsInfo set score = score + ? where userID=?");
			ps.setFloat(1, r3_Amt);
			ps.setInt(2, userid);
			ps.execute();
			
			ps = con.prepareStatement("insert into bankpay(dh,userid,userid2,jine,state,r3_Amt) values(?,?,?,?,1,?)");
			ps.setString(1, dh);
			ps.setInt(2, userid);
			ps.setInt(3, userid);
			ps.setFloat(4, r3_Amt);
			ps.setFloat(5, r3_Amt);
			ps.execute();
			r = 1;
			System.out.println("add success!!!!!!!!!!!!!");
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();con=null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try{
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
		return r;
	}
	
	public void updateyeepay(String dh,float jiner) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			int userID = 0;
			ps = con.prepareStatement("select * from bankpay where dh=?");
			ps.setString(1, dh);
			rs = ps.executeQuery();
			
			if(rs.next()){
				userID = rs.getInt("userid");
			}
			
			ps = con.prepareStatement("update RegAccountsInfo set score=score+? where userID=?");
			ps.setFloat(1, jiner);
			ps.setInt(2, userID);
			ps.execute();
			
			ps = con.prepareStatement("update bankpay set state=1 where dh=?");
			ps.setString(1, dh);
			ps.execute();
			System.out.println(">>>>>>>更新成功!");
			
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
	}
	
	
	public void updateGold(GameUserDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update RegAccountsInfo set score=? where UserID=?");
			ps.setDouble(1, dto.getScore());
			ps.setInt(2, dto.getUserID());
			ps.execute();
			System.out.println("update success!!!!!!!!!!!!!");
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();con=null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try{
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
	}
	
	public Boolean DeleteGameUser(int gameUserID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from RegAccountsInfo where UserID=?");
			GameUserDTO dto = new GameUserDTO();
			ps.setInt(1, gameUserID);
			ps.execute();
			r=true;
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();con=null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try{
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
		return r;
	}
	
	public Boolean deleteyeepay(String dh) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from bankpay where dh=?");
			GameUserDTO dto = new GameUserDTO();
			ps.setString(1, dh);
			ps.execute();
			r=true;
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();con=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try{
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
		return r;
	}
	

}

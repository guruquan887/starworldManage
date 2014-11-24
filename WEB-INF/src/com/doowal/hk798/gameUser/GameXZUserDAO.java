package com.doowal.hk798.gameUser;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;


public class GameXZUserDAO extends BaseDAO{
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	
	public GameXZUserDAO(DataSource ds) {
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
	
	/*==========================保留用户名=========================*/
	public List<GameXZUserDTO> GetConfineRecordByPage(int curPage,int pageSize,String orderby,String where) {
		List<GameXZUserDTO> list = new ArrayList<GameXZUserDTO>();
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "ConfineContent");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, curPage);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameXZUserDTO dto = new GameXZUserDTO();
				dto.setString(rs.getString("String"));
				dto.setCollectDate(rs.getString("CollectDate").substring(0, 19));
				if(rs.getString("EnjoinOverDate")!=null){
					dto.setEnjoinOverDate(rs.getString("EnjoinOverDate").substring(0, 19));
				}
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
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
	
	/*==========================限制地址=========================*/
	public List<GameXZUserDTO> GetAddressRecordByPage(int curPage,int pageSize,String orderby,String where) {
		List<GameXZUserDTO> list = new ArrayList<GameXZUserDTO>();
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "ConfineAddress");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, curPage);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameXZUserDTO dto = new GameXZUserDTO();
				dto.setAddrString(rs.getString("addrString"));
				dto.setEnjoinLogon(rs.getInt("EnjoinLogon"));
				dto.setEnjoinRegister(rs.getInt("EnjoinRegister"));
				dto.setCollectDate(rs.getString("CollectDate").substring(0, 19));
				dto.setEnjoinOverDate(rs.getString("EnjoinOverDate").substring(0, 19));
				dto.setCollectNote(rs.getString("CollectNote"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
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
	
	/*==========================限制机器=========================*/
	public List<GameXZUserDTO> GetMachineRecordByPage(int curPage,int pageSize,String orderby,String where) {
		List<GameXZUserDTO> list = new ArrayList<GameXZUserDTO>();
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
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "ConfineMachine");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, curPage);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameXZUserDTO dto = new GameXZUserDTO();
				dto.setAddrString(rs.getString("MachineSerial"));
				dto.setEnjoinLogon(rs.getInt("EnjoinLogon"));
				dto.setEnjoinRegister(rs.getInt("EnjoinRegister"));
				dto.setCollectDate(rs.getString("CollectDate").substring(0, 19));
				dto.setEnjoinOverDate(rs.getString("EnjoinOverDate").substring(0, 19));
				dto.setCollectNote(rs.getString("CollectNote"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
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
	
	public int queryString(String account){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from ConfineContent where String=?");
			ps.setString(1, account);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int queryAddress(String account){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from ConfineAddress where AddrString=?");
			ps.setString(1, account);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int queryMachine(String account){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from ConfineMachine where MachineSerial=?");
			ps.setString(1, account);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public void addgameUserXZ(String in_string,String in_enjoinOverDate){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			if(in_enjoinOverDate==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cd = Calendar.getInstance();
				cd.add(Calendar.YEAR, 100); //增加一百年
				String dt2 = sdf.format(cd.getTime());
				sql = "insert into ConfineContent(String,EnjoinOverDate,CollectDate) values('"+in_string+"','"+dt2+"',getDate())";
			}
			else{
				sql = "insert into ConfineContent(String,EnjoinOverDate,CollectDate) values('"+in_string+"','"+in_enjoinOverDate+"',getDate())";
			}
			
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addconfineAddress(String in_string,int in_EnjoinLogon,int in_EnjoinRegister,String in_enjoinOverDate,String in_CollectNote){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			if(in_enjoinOverDate==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cd = Calendar.getInstance();
				cd.add(Calendar.YEAR, 100); //增加一百年
				String dt2 = sdf.format(cd.getTime());
				sql = "insert into ConfineAddress(AddrString,EnjoinLogon,EnjoinRegister,EnjoinOverDate,CollectDate,CollectNote) values('"+in_string+"',"+in_EnjoinLogon+","+in_EnjoinRegister+",'"+dt2+"',getDate(),'"+in_CollectNote+"')";
			}
			else{
				sql = "insert into ConfineAddress(AddrString,EnjoinLogon,EnjoinRegister,EnjoinOverDate,CollectDate,CollectNote) values('"+in_string+"',"+in_EnjoinLogon+","+in_EnjoinRegister+",'"+in_enjoinOverDate+"',getDate(),'"+in_CollectNote+"')";
			}
			
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addconfineMachine(String in_string,int in_EnjoinLogon,int in_EnjoinRegister,String in_enjoinOverDate,String in_CollectNote){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			if(in_enjoinOverDate==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cd = Calendar.getInstance();
				cd.add(Calendar.YEAR, 100); //增加一百年
				String dt2 = sdf.format(cd.getTime());
				sql = "insert into ConfineMachine(MachineSerial,EnjoinLogon,EnjoinRegister,EnjoinOverDate,CollectDate,CollectNote) values('"+in_string+"',"+in_EnjoinLogon+","+in_EnjoinRegister+",'"+dt2+"',getDate(),'"+in_CollectNote+"')";
			}
			else{
				sql = "insert into ConfineMachine(MachineSerial,EnjoinLogon,EnjoinRegister,EnjoinOverDate,CollectDate,CollectNote) values('"+in_string+"',"+in_EnjoinLogon+","+in_EnjoinRegister+",'"+in_enjoinOverDate+"',getDate(),'"+in_CollectNote+"')";
			}
			
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean delAll(String string) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("delete from ConfineContent where String=?");
			ps.setString(1, string);
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
	
	public boolean delAllAddress(String string) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("delete from ConfineAddress where AddrString=?");
			ps.setString(1, string);
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
	
	public boolean delAllMachine(String string) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("delete from ConfineMachine where MachineSerial = ?");
			ps.setString(1, string);
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
	
}

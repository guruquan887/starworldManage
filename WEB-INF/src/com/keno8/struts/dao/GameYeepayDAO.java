package com.keno8.struts.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.dto.GameYeepayDTO;


public class GameYeepayDAO {
	
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
				int userid = rs.getInt("userid");
				String username = queryUsername(userid);
				dto.setUsername(username);
				
				int userid1 = rs.getInt("userid2");
				String username1 = queryUsername(userid1);
				dto.setUsername1(username1);
				
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
	
	public List<GameYeepayDTO> selectByName(int pageindex,int pageSize,String where) {
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
			con =ds.getConnection();
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
				int userid = rs.getInt("userid");
				String username = queryUsername(userid);
				dto.setUsername(username);
				
				int userid1 = rs.getInt("userid2");
				String username1 = queryUsername(userid1);
				dto.setUsername1(username1);
				dto.setJine(rs.getLong("jine"));
				dto.setDh(rs.getString("dh"));
				dto.setXdtime(rs.getString("xdtime"));
				dto.setR2_TrxId(rs.getString("r2_TrxId"));
				dto.setTelphone(rs.getString("telphone"));
				dto.setR3_Amt(rs.getLong("r3_Amt"));
				int state=rs.getInt("state");
				dto.setState(state);
				if(0==state){
					dto.setStateName("未充值");
				}
				else if(1==state){
					dto.setStateName("已充值");
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
	
	public String queryUsername(int userid) {
		String username = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select accounts from UserbankpayView where userid=?");
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				username = rs.getString(1);
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
		} catch (Exception ex) {
			ex.printStackTrace();
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
			catch (Exception ex1) {
				ex1.printStackTrace();
			}
		} 
		return username;
	}

}

package com.doowal.hk798.admin;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class OperateLogsDAO {
	
	private int pageCount;
	private int recordCount;
	private DataSource ds;
	
	
	public OperateLogsDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	public void addAgentLogs(OperateDTO dto){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into QPTreasureDB.dbo.UserOperateLogs(OperateUserID,OperateDetails,OperateIP) values(?,?,?)";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getUserID());
			ps.setString(2, dto.getOperateDetails());
			ps.setString(3, dto.getOperateIP());
			ps.execute();
			System.out.println("增加日志文件成功!");
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
	
	public void addLogs(OperateDTO dto){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into QPPlatformManagerDB.dbo.operatelogs(operateName,operateDetails,operateIP) values(?,?,?)";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getOperateName());
			ps.setString(2, dto.getOperateDetails());
			ps.setString(3, dto.getOperateIP());
			ps.execute();
			System.out.println("增加日志文件成功!");
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
	
	public int delete(String operateName){
		 int r = 0;
		 String sql = "delete from operatelogs ";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
			r = 1;
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
		 return r;
	 }
	
	public List<OperateDTO> GetRecordByPage(int pageindex,int pageSize,String where,String orderby) {
		List<OperateDTO> list = new ArrayList<OperateDTO>();
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
			toesUp.setString(1, "operatelogs");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				OperateDTO dto = new OperateDTO();
				dto.setId(rs.getInt("id"));
				dto.setOperateName(rs.getString("operateName"));
				dto.setOperateDetails(rs.getString("operateDetails"));
				dto.setOperateIP(rs.getString("operateIP"));
				if(rs.getString("operateTime")!=null){
					dto.setOperateTime(rs.getString("operateTime").substring(0, 19));
				}
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
	
	public int getTotalPage() {
		return pageCount;
	}
	public int getRcordCount() {
		return recordCount;
	}
	
}

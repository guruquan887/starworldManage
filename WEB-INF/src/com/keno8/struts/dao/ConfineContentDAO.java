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

import com.keno8.struts.dto.ConfineContentDTO;

public class ConfineContentDAO {
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	
	public ConfineContentDAO(DataSource ds) {
		this.ds = ds;
	}

	public List<ConfineContentDTO> GetRecordByPage(int pageindex,int pageSize) {
		List<ConfineContentDTO> list = new ArrayList<ConfineContentDTO>();
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
			con  =ds.getConnection();
			//con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "ConfineContent");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "id desc");//排序字段
			toesUp.setString(4, "");//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				ConfineContentDTO dto = new ConfineContentDTO();
				dto.setId(rs.getInt("id"));
				dto.setString(rs.getString("String"));
				dto.setCollectDate(rs.getString("CollectDate").substring(0,19));
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
	public int getRecordCount() {
		return recordCount;
	}
	
	public void add(ConfineContentDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("insert into ConfineContent(String,CollectDate) values(?,getDate())");
			ps.setString(1, dto.getString());
			ps.execute();
			
			
			ps = con.prepareStatement("insert into RegAccountsInfo(accounts,loginPass,nickName,sex,insureScore,RegisterDate,RegisterIP) values(?,'123456',?,0,0,getDate(),?)");
			ps.setString(1, dto.getString());
			ps.setString(2, dto.getString());
			ps.setString(3, "127.0.0.1");
			ps.execute();
			
			System.out.println("add successful!!!!!!!!!!!!!!");
			
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
	
	public Boolean DeleteGameScore(int id) {
		Boolean r = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from ConfineContent where id=?");
			ps.setInt(1, id);
			ps.execute();
			r = true;
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

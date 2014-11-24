package com.doowal.hk798.web;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.doowal.hk798.gameUser.BaseDAO;

public class WebContentDAO extends BaseDAO{
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public WebContentDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public List<WebContentDTO> GetRecordByPage(int pageindex,int pageSize,String where) {
		List<WebContentDTO> list = new ArrayList<WebContentDTO>();
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
			toesUp.setString(1, "adTypeView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "id desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				WebContentDTO dto = new WebContentDTO();
				dto.setId(rs.getInt("id"));
				dto.setAdTitle(rs.getString("adTitle"));
				dto.setAdLink(rs.getString("adLink"));
				dto.setAdSynopsis(rs.getString("adSynopsis"));
				dto.setAdTypeName(rs.getString("adTypeName"));
				
				int state=rs.getInt("state");
				dto.setState(state);
				if(0==state){
					dto.setStateName("未发布");
				}
				else if(1==state){
					dto.setStateName("已发布");
				}
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, null);
		}
		return list ;
	}
	
	public int getTotalPage() {
		return pageCount;
	}
	public int getRcordCount() {
		return recordCount;
	}
	
	public List<WebContentDTO> select(){
		List<WebContentDTO> list = new ArrayList<WebContentDTO>();
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from advertisementType");
			rs = ps.executeQuery();
			while(rs.next()){
				WebContentDTO dto  = new WebContentDTO();
				dto.setId(rs.getInt("id"));
				dto.setAdTypeName(rs.getString("adTypeName"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}

}

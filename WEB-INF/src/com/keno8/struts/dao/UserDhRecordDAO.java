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

import com.keno8.struts.dto.AdminDTO;
import com.keno8.struts.dto.UserDhRecordDTO;

public class UserDhRecordDAO {
	
	private DataSource ds;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private int pageCount;
	private int recordCount;
	
	public UserDhRecordDAO(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public void delete(int userID,String express_ID){
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement("delete from UserItemsRecord where userID=? and express_ID=?");
			ps.setInt(1, userID);
			ps.setString(2, express_ID);
			ps.execute();
			
			System.out.println("delete successful!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<UserDhRecordDTO> GetRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<UserDhRecordDTO> list = new ArrayList<UserDhRecordDTO>();
		try {
			con = ds.getConnection();
			Statement stat=null;
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
			con.setAutoCommit(false); // Setup the call. 
			CallableStatement toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "UserItemsRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				UserDhRecordDTO dto = new UserDhRecordDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("account"));
				dto.setCsAmount(rs.getLong("csAmount"));
				dto.setDhAmount(rs.getLong("dhAmount"));
				dto.setDhTime(rs.getString("dhTime").substring(0, 19));
				dto.setExpress_ID(rs.getString("express_ID"));
				dto.setExpress_Name(rs.getString("express_Name"));
				dto.setMallName(rs.getString("mallName"));
				dto.setRecipient(rs.getString("recipient"));
				dto.setPostalCode(rs.getString("postalCode"));
				dto.setEMail(rs.getString("eMail"));
				dto.setRecAddress(rs.getString("recAddress"));
				dto.setState(rs.getInt("state"));
				int state = rs.getInt("state");
				String stateName = "";
				if(state==0){
					stateName = "已下单";
				}
				else{
					stateName = "已发货";
				}
				dto.setStateName(stateName);
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			toesUp.close();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getTotalPage() {
		return pageCount;
	}
	public int getRecordCount(){
		return recordCount;
	}
	
	public UserDhRecordDTO getDetails(int userID,String express_ID){
		
		UserDhRecordDTO dto = new UserDhRecordDTO();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from UserItemsRecordView where userID="+userID+" and express_ID='"+express_ID+"'");
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("account"));
				dto.setDhAmount(rs.getLong("dhAmount"));
				dto.setDhTime(rs.getString("dhTime").substring(0, 19));
				dto.setMallName(rs.getString("mallName"));
				dto.setPostalCode(rs.getString("postalCode"));
				dto.setRecAddress(rs.getString("recAddress"));
				dto.setRecipient(rs.getString("recipient"));
				dto.setImagePath(rs.getString("imagePath"));
				int state = rs.getInt("state");
				String stateName = "";
				if(state==0){
					stateName = "已下单";
				}
				else{
					stateName = "已发货";
				}
				dto.setStateName(stateName);
				dto.setTelphone(rs.getString("telphone"));
				dto.setUserID(rs.getInt("userID"));
				dto.setExpress_ID(rs.getString("express_ID"));
				dto.setExpress_IDNO(rs.getString("express_IDNO"));
				dto.setExpress_Name(rs.getString("express_Name"));
				dto.setExpress_BeiZhu(rs.getString("express_BeiZhu"));
				
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public void update(int userID,String express_ID,UserDhRecordDTO dto){
		try {
			con = ds.getConnection();
			String sql = "update UserItemsRecord set express_IDNO='"+dto.getExpress_IDNO()+"',express_Name='"+dto.getExpress_Name()+"',express_BeiZhu='"+dto.getExpress_BeiZhu()+"',state=1 where userID="+userID+" and express_ID='"+express_ID+"'";
			
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
			System.out.println(">>>>>>>>>>>>>发货成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
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
		}
	}
	
}

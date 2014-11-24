package com.keno8.struts.action.article;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


public class NewsTypeDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int pageCount;
	private int recordCount;

	public NewsTypeDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public List<NewsTypeDTO> select(){
		List<NewsTypeDTO> list = new ArrayList<NewsTypeDTO>();
		try {
			ps = con.prepareStatement("select * from class");
			rs = ps.executeQuery();
			while(rs.next()){
				NewsTypeDTO dto  = new NewsTypeDTO();
				dto.setId(rs.getInt("id"));
				dto.setClassname(rs.getString("classname"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<NewsTypeDTO> GetRecordByPage(int pageindex,int pageSize) {
		List<NewsTypeDTO> list = new ArrayList<NewsTypeDTO>();
		try {
			
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
			toesUp.setString(1, "class");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "id desc");//排序字段
			toesUp.setString(4, "");//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs= toesUp.executeQuery();
			//l=new ArrayList();
			while(rs.next()){
				NewsTypeDTO dto = new NewsTypeDTO();
				dto.setId(rs.getInt("id"));
				dto.setClasscode(rs.getInt("classcode"));
				dto.setClassname(rs.getString("classname"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			toesUp.close();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getTotalPage() {
		return pageCount;
	}
	public int getRcordCount() {
		return recordCount;
	}
	
	public void add(NewsTypeDTO dto){
		try {
			con.setAutoCommit(true);
			ps = con.prepareStatement("insert into class(classname) values(?)");
			ps.setString(1,dto.getClassname());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			ps = con.prepareStatement("delete from class where id=?");
			ps.setInt(1, id);
			System.out.println("delete successful!!!!!!");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public NewsTypeDTO getById(int id){
		NewsTypeDTO dto = null;
		try {
			ps = con.prepareStatement("select * from class where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new NewsTypeDTO();
			if(rs.next()){
				dto.setId(rs.getInt("id"));
				String classname = rs.getString("classname");
				classname = new String(classname.getBytes("ISO_8859_1"),"UTF-8");
				dto.setClassname(classname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public void update(NewsTypeDTO dto) {
		try {
			con.setAutoCommit(true);
			ps = con.prepareStatement("update class set classname=? where id = ?");
			ps.setString(1, dto.getClassname());
			ps.setInt(2, dto.getId());
			ps.execute();
			System.out.println("update success...........");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

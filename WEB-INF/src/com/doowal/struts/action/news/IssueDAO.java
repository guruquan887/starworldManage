package com.doowal.struts.action.news;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class IssueDAO {
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	private final String SQL_ADD = "insert into News(Subject,OnTop,OnTopAll,IsElite,IsHot,IsLock,IsLinks,LinkUrl,Body,ClassID,IssueDate,IssueIP) values(?,?,?,?,?,?,?,?,?,?,getdate(),?)";
	private final String SQL_ADD1 = "insert into news(classcode,newstitle,newsinfo,newsdate,image) values(?,?,?,getdate(),?)";
	private final String SQL_FINDALL = "select a.*,b.classname from news as a left outer join class as b on a.classcode=b.classcode";
	private final String SQL_SELECTALL_NEWSCLASSVIEW = "select a.*,b.classname from news as a left outer join class as b on a.classcode=b.classcode";
	private final String SQL_UPDATE = "update news set newstitle=?,newsdate=getdate(),classcode=?,newsinfo=?,state=1 where id=?";
	private final String SQL_UPDATE1 = "update news set newstitle=?,newsdate=getdate(),classcode=?,newsinfo=?,state=1 where id=?";
	private final String SQL_UPDATE2 = "update news set newstitle=?,newscu=?,newsinfo=? where id=?";
	private final String SQL_UPDATE3 = "update news set newstitle=?,newsdate=getdate(),newscu=?,newszz=?,newsinfo=?,state=1 where id=?";
	private final String SQL_CHANGESTATE = "update news set state=? where id=?";
	private final String SQL_DELETE = "delete from news where newsID=?";


	public IssueDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}

	public List<IssueDTO> GetRecordByPage(int pageindex,int pageSize,String where) {
		List<IssueDTO> list = new ArrayList<IssueDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
	    ResultSet rs = null;
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
			toesUp.setString(1, "GameIssueInfo");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "CollectDate desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				IssueDTO dto = new IssueDTO();
				dto.setIssueID(rs.getInt("issueID"));
				dto.setIssueTitle(rs.getString("issueTitle"));
				dto.setIssueContent(rs.getString("issueContent"));
				dto.setNullity(rs.getInt("nullity"));
				dto.setCollectDate(rs.getString("collectDate").substring(0, 19));
				dto.setModifyDate(rs.getString("modifyDate").substring(0, 19));
				
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
	
	public List<IssueDTO> GetRecordGamerulesByPage(int pageindex,int pageSize,String where) {
		List<IssueDTO> list = new ArrayList<IssueDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
	    ResultSet rs = null;
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
			toesUp.setString(1, "GameRulesInfo");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "CollectDate desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				IssueDTO dto = new IssueDTO();
				dto.setKindID(rs.getInt("kindID"));
				dto.setKindName(rs.getString("kindName"));
				dto.setNullity(rs.getInt("nullity"));
				dto.setCollectDate(rs.getString("collectDate").substring(0, 19));
				dto.setModifyDate(rs.getString("modifyDate").substring(0, 19));
				
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

	public int getTotalPage() {
		return pageCount;
	}
	
	public int getRcordCount() {
		return recordCount;
	}

	public void add(IssueDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("insert into GameIssueInfo(IssueTitle,IssueContent,Nullity,CollectDate,ModifyDate) values(?,?,?,getdate(),getdate())");
			ps.setString(1, dto.getIssueTitle());
			ps.setString(2, dto.getIssueContent());
			ps.setInt(3, dto.getNullity());
			
			ps.execute();
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
	

	public IssueDTO getById(int id) {
		IssueDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from GameIssueInfo where IssueID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new IssueDTO();
			if (rs.next()) {
				dto.setIssueID(rs.getInt("issueID"));
				dto.setIssueTitle(rs.getString("issueTitle"));
				dto.setIssueContent(rs.getString("issueContent"));
				dto.setNullity(rs.getInt("nullity"));
				dto.setCollectDate(rs.getString("collectDate"));
				dto.setModifyDate(rs.getString("ModifyDate"));
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
		return dto;
	}
	


	public void update(IssueDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("update GameIssueInfo set IssueTitle=?,IssueContent=?,Nullity=?,ModifyDate=getdate() where IssueID=?");
			ps.setString(1, dto.getIssueTitle());
			ps.setString(2, dto.getIssueContent());
			ps.setInt(3, dto.getNullity());
			ps.setInt(4, dto.getIssueID());
			ps.execute();
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
	

	public void ChangeState(String issueId,int targetState) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update GameIssueInfo set Nullity=? where issueID=?");
			ps.setInt(1, targetState);
			ps.setString(2, issueId);
			ps.execute();
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
	
	public Boolean DeleteNews(String issueID) {
		Connection con = null;
		PreparedStatement ps = null;
		Boolean r=false;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from GameIssueInfo where issueID=?");
			ps.setString(1, issueID);
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
	
	public IssueDTO getDetail(int id){
		IssueDTO dto = new IssueDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select a.*,b.classname from news as a left outer join class as b on a.classcode=b.classcode where a.id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
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
		return dto;
	}
}

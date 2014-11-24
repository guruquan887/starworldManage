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


public class NewsDAO {
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


	public NewsDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}

	public List<NewsDTO> GetRecordByPage(int pageindex,int pageSize,String where) {
		List<NewsDTO> list = new ArrayList<NewsDTO>();
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
			toesUp.setString(1, "NewsView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "issueDate desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				NewsDTO dto = new NewsDTO();
				dto.setNewsID(rs.getInt("NewsID"));
				dto.setClassID(rs.getInt("classID"));
				dto.setPopID(rs.getInt("popID"));
				dto.setIsHot(rs.getInt("isHot"));
				dto.setIsLinks(rs.getInt("isLinks"));
				dto.setIsLock(rs.getInt("isLock"));
				dto.setIssueDate(rs.getString("issueDate").substring(0, 19));
				dto.setOnTopAll(rs.getInt("onTopAll"));
				dto.setOnTop(rs.getInt("onTop"));
				dto.setIsElite(rs.getInt("isElite"));
				dto.setSubject(rs.getString("subject"));
				dto.setBody(rs.getString("body"));
				dto.setUsername(rs.getString("username"));
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

	public void add(NewsDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("insert into News(Subject,OnTop,OnTopAll,IsElite,IsHot,IsLock,IsLinks,LinkUrl,Body,ClassID,IssueDate,IssueIP) values(?,?,?,?,?,?,?,?,?,?,getdate(),?)");
			ps.setString(1, dto.getSubject());
			ps.setInt(2, dto.getOnTop());
			ps.setInt(3, dto.getOnTopAll());
			ps.setInt(4, dto.getIsElite());
			ps.setInt(5, dto.getIsHot());
			ps.setInt(6, dto.getIsLock());
			ps.setInt(7, dto.getIsLinks());
			ps.setString(8, dto.getLinkUrl());
			ps.setString(9, dto.getBody());
			ps.setInt(10, dto.getClassID());
			ps.setString(11, dto.getIssueIP());
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
	
	public void add1(NewsDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement(SQL_ADD1);
			ps.setString(1, dto.getClasscode());
			ps.setString(2, dto.getNewstitle());
			ps.setString(3, dto.getNewsinfo());
			ps.setString(4, dto.getImage());
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
	
	

	public NewsDTO getById(int id) {
		NewsDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from news where newsID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new NewsDTO();
			if (rs.next()) {
				dto.setNewsID(rs.getInt("NewsID"));
				dto.setClassID(rs.getInt("classID"));
				dto.setPopID(rs.getInt("popID"));
				dto.setIsHot(rs.getInt("isHot"));
				dto.setLinkUrl(rs.getString("linkUrl"));
				dto.setIsLinks(rs.getInt("isLinks"));
				dto.setIsLock(rs.getInt("isLock"));
				dto.setIssueDate(rs.getString("issueDate").substring(0, 19));
				dto.setOnTopAll(rs.getInt("onTopAll"));
				dto.setOnTop(rs.getInt("onTop"));
				dto.setIsElite(rs.getInt("isElite"));
				dto.setSubject(rs.getString("subject"));
				dto.setBody(rs.getString("body"));
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
	
	public NewsDTO getById1(int id) {
		NewsDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from news where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new NewsDTO();
			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setNewstitle(rs.getString("newstitle"));
				dto.setNewsdate(rs.getString("newsdate"));
				dto.setClasscode(rs.getString("classcode"));
				/*dto.setNewscu(rs.getString("newscu"));
				dto.setNewszz(rs.getString("newszz"));*/
				dto.setNewsinfo(rs.getString("newsinfo"));
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


	public void update(NewsDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("update news set Subject=?,IssueDate=getdate(),LastModifyDate=getdate(),OnTop=?,OnTopAll=?,IsElite=?,IsHot=?,IsLock=?,IsLinks=?,LinkUrl=?,Body=?,ClassID=?,IssueIP=? where newsID=?");
			ps.setString(1, dto.getSubject());
			ps.setInt(2, dto.getOnTop());
			ps.setInt(3, dto.getOnTopAll());
			ps.setInt(4, dto.getIsElite());
			ps.setInt(5, dto.getIsHot());
			ps.setInt(6, dto.getIsLock());
			ps.setInt(7, dto.getIsLinks());
			ps.setString(8, dto.getLinkUrl());
			ps.setString(9, dto.getBody());
			ps.setInt(10, dto.getClassID());
			ps.setString(11, dto.getIssueIP());
			ps.setInt(12, dto.getNewsID());
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
	
	public void update1(NewsDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement(SQL_UPDATE1);
			ps.setString(1, dto.getNewstitle());
			ps.setString(2, dto.getClasscode());
			ps.setString(3, dto.getNewsinfo());
			ps.setInt(4, dto.getId());
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
	
	public void update2(NewsDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement(SQL_UPDATE2);
			ps.setString(1, dto.getNewstitle());
			//ps.setString(2, dto.getNewscu());
			ps.setString(3, dto.getNewsinfo());
			ps.setInt(4, dto.getId());
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
	
	public void update3(NewsDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement(SQL_UPDATE3);
			ps.setString(1, dto.getNewstitle());
			/*ps.setString(2, dto.getNewscu());
			ps.setString(3, dto.getNewszz());*/
			ps.setString(4, dto.getNewsinfo());
			ps.setInt(5, dto.getId());
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
	
	public void update4(NewsDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("update news set newstitle=?,newsdate=getdate(),newszz=?,newsinfo=?,state=1 where id=?");
			ps.setString(1, dto.getNewstitle());
			//ps.setString(2, dto.getNewszz());
			ps.setString(3, dto.getNewsinfo());
			ps.setInt(4, dto.getId());
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
	public void update5(NewsDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("update news set newstitle=?,newsdate=getdate(),newsinfo=?,state=1 where id=?");
			ps.setString(1, dto.getNewstitle());
			ps.setString(2, dto.getNewsinfo());
			ps.setInt(3, dto.getId());
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
	

	public void ChangeState(String newsid,int targetState) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update news set IsLock=? where newsID=?");
			ps.setInt(1, targetState);
			ps.setString(2, newsid);
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
	
	public Boolean DeleteNews(String newsID) {
		Connection con = null;
		PreparedStatement ps = null;
		Boolean r=false;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_DELETE);
			NewsDTO dto = new NewsDTO();
			ps.setString(1, newsID);
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
	
	public NewsDTO getDetail(int id){
		NewsDTO dto = new NewsDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select a.*,b.classname from news as a left outer join class as b on a.classcode=b.classcode where a.id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setNewsinfo(rs.getString("newsinfo"));
				/*dto.setNewscu(rs.getString("newscu"));
				dto.setNewszz(rs.getString("newszz"));*/
				dto.setNewstitle(rs.getString("newstitle"));
				dto.setClassname(rs.getString("classname"));
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

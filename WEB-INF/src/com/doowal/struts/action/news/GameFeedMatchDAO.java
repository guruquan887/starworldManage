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


public class GameFeedMatchDAO {
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


	public GameFeedMatchDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}

	
	public List<GameFeedMatchDTO> GetRecordGameMatchByPage(int pageindex,int pageSize,String where) {
		List<GameFeedMatchDTO> list = new ArrayList<GameFeedMatchDTO>();
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
			toesUp.setString(1, "GameMatchInfo");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "CollectDate desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameFeedMatchDTO dto = new GameFeedMatchDTO();
				dto.setMatchID(rs.getInt("matchID"));
				dto.setMatchTitle(rs.getString("MatchTitle"));
				dto.setMatchStatus(rs.getInt("MatchStatus"));
				dto.setApplyBeginDate(rs.getString("ApplyBeginDate").substring(0, 19));
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
	
	public List<GameFeedMatchDTO> GetRecordGameFeedBackByPage(int pageindex,int pageSize,String where) {
		List<GameFeedMatchDTO> list = new ArrayList<GameFeedMatchDTO>();
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
			toesUp.setString(1, "GameFeedBackInfoView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "FeedbackDate desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameFeedMatchDTO dto = new GameFeedMatchDTO();
				dto.setFeedBackID(rs.getInt("FeedBackID"));
				dto.setFeedBackTitle(rs.getString("FeedBackTitle"));
				dto.setFeedBackDate(rs.getString("FeedBackDate"));
				dto.setUsername(rs.getString("Username"));
				dto.setViewCount(rs.getInt("ViewCount"));
				dto.setClientIP(rs.getString("clientIP"));
				dto.setRevertDate(rs.getString("RevertDate"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setNullity(rs.getInt("nullity"));
				
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
	
	public List<GameFeedMatchDTO> GetRecordNoticeByPage(int pageindex,int pageSize,String where) {
		List<GameFeedMatchDTO> list = new ArrayList<GameFeedMatchDTO>();
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
			toesUp.setString(1, "Notice");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "StartDate desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameFeedMatchDTO dto = new GameFeedMatchDTO();
				dto.setNoticeID(rs.getInt("NoticeID"));
				dto.setSubject(rs.getString("subject"));
				dto.setStartDate(rs.getString("StartDate"));
				dto.setOverDate(rs.getString("overDate"));
				dto.setHeight(rs.getInt("Height"));
				dto.setWidth(rs.getInt("width"));
				dto.setNullity(rs.getInt("nullity"));
				
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

	public void add(GameFeedMatchDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("insert into GameIssueInfo(IssueTitle,IssueContent,Nullity,CollectDate,ModifyDate) values(?,?,?,getdate(),getdate())");
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
	

	public GameFeedMatchDTO getById(int id) {
		GameFeedMatchDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from GameIssueInfo where IssueID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new GameFeedMatchDTO();
			if (rs.next()) {
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
	


	public void update(GameFeedMatchDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("update GameIssueInfo set IssueTitle=?,IssueContent=?,Nullity=?,ModifyDate=getdate() where IssueID=?");
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
	
	public GameFeedMatchDTO getDetail(int id){
		GameFeedMatchDTO dto = new GameFeedMatchDTO();
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

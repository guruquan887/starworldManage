package com.doowal.staworld.advertisement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class AdvertisementDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	private static final String SQL_ADD = "insert into advertisement(adTypeId,adTitle,adSynopsis,adLink,adImage) values(?,?,?,?,?)";
	private static final String SQL_DEL = "delete from advertisement where id=?";
	private static final String SQL_UPDATE = "update advertisement set adTitle=?,adLink=?,adSynopsis=?,adImage=?,adTypeId=? where id = ?";
	private static final String SQL_CHANGESTATE = "update advertisement set state=? where id=?";
	
	public AdvertisementDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public List<AdvertisementDTO> GetRecordByPage(int pageindex,int pageSize,String where) {
		List<AdvertisementDTO> list = new ArrayList<AdvertisementDTO>();
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
			toesUp.setString(1, "AdTypeView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "id desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 10);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				AdvertisementDTO dto = new AdvertisementDTO();
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
			} catch (SQLException e1) {
				e1.printStackTrace();
		    }
		}
		return list ;
	}
	
	public int getTotalPage() {
		return pageCount;
	}
	public int getRcordCount() {
		return recordCount;
	}

	public void add(AdvertisementDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement(SQL_ADD);
			ps.setInt(1, dto.getAdTypeId());
			ps.setString(2, dto.getAdTitle());
			ps.setString(3, dto.getAdSynopsis());
			ps.setString(4, dto.getAdLink());
			ps.setString(5, dto.getAdImage());
			ps.execute();
			System.out.println("Add successful!!!!!!!!!!!");
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
			} catch (SQLException e1) {
				e1.printStackTrace();
		    }
		}
	}

	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_DEL);
			ps.setInt(1, id);
			System.out.println("delete successful!!!!!!");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
		    }
		}
	}

	public AdvertisementDTO getById(int id) {
		AdvertisementDTO dto = null;
		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select a.*,b.adTypeName from advertisement a left outer join advertisementType b on a.adTypeId=b.id where a.id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new AdvertisementDTO();
			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setAdTitle(rs.getString("adTitle"));
				dto.setAdImage(rs.getString("adImage"));
				dto.setAdLink(rs.getString("adLink"));
				dto.setAdSynopsis(rs.getString("adSynopsis"));
				//dto.setAdNo(rs.getInt("adNo"));
				dto.setState(rs.getInt("state"));
				dto.setAdTypeId(rs.getInt("adTypeId"));
				dto.setAdTypeName(rs.getString("adTypeName"));

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
			} catch (SQLException e1) {
				e1.printStackTrace();
		    }
		}
		return dto;
	}

	public void update(AdvertisementDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_UPDATE);
			ps.setString(1, dto.getAdTitle());
			ps.setString(2, dto.getAdLink());
			ps.setString(3, dto.getAdSynopsis());
			ps.setString(4, dto.getAdImage());
			ps.setInt(5, dto.getAdTypeId());	
			ps.setInt(6, dto.getId());
			ps.execute();
			System.out.println("update success!!!!!!!!!!!!!");
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
			} catch (SQLException e1) {
				e1.printStackTrace();
		    }
		}
	}
	
	public AdvertisementDTO getDetail(int id){
		AdvertisementDTO dto = new AdvertisementDTO();
		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select a.*,b.adTypeName from advertisement as a left outer join advertisementType as b on a.adTypeId=b.id where a.id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setAdTitle(rs.getString("adTitle"));
				dto.setAdLink(rs.getString("adLink"));
				dto.setAdNo(rs.getInt("adNo"));
				int state = rs.getInt("state");
				if(state==0){
					dto.setStateName("未发布");
				}
				else{
					dto.setStateName("已发布");
				}
				dto.setAdImage(rs.getString("adImage"));
				dto.setAdSynopsis(rs.getString("adSynopsis"));
				dto.setAdTypeName(rs.getString("adTypeName"));
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
			} catch (SQLException e1) {
				e1.printStackTrace();
		    }
		}
		return dto;
	}
	
	public void ChangeState(int adid,int targetState) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_CHANGESTATE);
			ps.setInt(1, targetState);
			ps.setInt(2, adid);
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
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}

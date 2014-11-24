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

import com.keno8.struts.dto.GameShopCateTypeDTO;


public class GameShopCateTypeDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	
	public GameShopCateTypeDAO(DataSource ds) {
		this.ds = ds;
	}

	public List<GameShopCateTypeDTO> GetRecordByPage(int pageindex,int pageSize,String where){
		List<GameShopCateTypeDTO> list = new ArrayList<GameShopCateTypeDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		try {
			con  =ds.getConnection();
			con.setAutoCommit(false);
			toesUp = con.prepareCall("{call GetRecordByPage2005(?,?,?,?,?,?,?,?)}");
			toesUp.setString(1, "ItemsType");
			toesUp.setString(2, "*");
			toesUp.setString(3, "typeID desc");
			toesUp.setString(4, where);
			toesUp.setInt(5, 10);
			toesUp.setInt(6, pageindex);
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();
			while(rs.next()){
				GameShopCateTypeDTO dto = new GameShopCateTypeDTO();
				dto.setTypeID(rs.getInt("typeID"));
				dto.setTypeName(rs.getString("typeName"));
				dto.setDescript(rs.getString("descript"));
				dto.setNote(rs.getString("note"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
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
	
	public int getTotalPage(){
		return pageCount;
	}
	public int getRecordCount(){
		return recordCount;
	}
	
	public List<GameShopCateTypeDTO> select(){
		List<GameShopCateTypeDTO> list = new ArrayList<GameShopCateTypeDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from ItemsType");
			rs = ps.executeQuery();
			while(rs.next()){
				GameShopCateTypeDTO dto = new GameShopCateTypeDTO();
				dto.setTypeID(rs.getInt("typeID"));
				dto.setTypeName(rs.getString("typeName"));
				dto.setDescript(rs.getString("descript"));
				dto.setNote(rs.getString("note"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void add(GameShopCateTypeDTO dto){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("insert into ItemsType values(?,?,?)");
			ps.setString(1, dto.getTypeName());
			ps.setString(2, "暂时无");
			ps.setString(3, "暂时无");
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
	
	public Boolean DeleteGameshoptype(int gameshoptypeid){
		Boolean b = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("delete from ItemsType where typeID = ?");
			ps.setInt(1, gameshoptypeid);
			ps.execute();
			b = true;
			System.out.println("delete successful............");
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
		return b;		
	}
	
	public GameShopCateTypeDTO getById(int id){
		GameShopCateTypeDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from ItemsType where typeID = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new GameShopCateTypeDTO();
			if(rs.next()){
				dto.setTypeID(rs.getInt("typeID"));
				dto.setTypeName(rs.getString("typeName"));
				dto.setDescript(rs.getString("descript"));
				dto.setNote(rs.getString("note"));
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
	
	public void update(GameShopCateTypeDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			//con.setAutoCommit(true);
			ps = con.prepareStatement("update ItemsType set typeName=?,descript=? where typeID = ?");
			ps.setString(1, dto.getTypeName());
			ps.setString(2, dto.getDescript());
			ps.setInt(3, dto.getTypeID());
			ps.execute();
			System.out.println("update success...........");
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
	
}

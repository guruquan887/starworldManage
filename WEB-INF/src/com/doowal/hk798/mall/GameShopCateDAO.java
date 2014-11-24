package com.doowal.hk798.mall;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class GameShopCateDAO {
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	private final String SQL_DELETE = "delete from MallItems where id=?";
	
	public GameShopCateDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public List<GameShopCateDTO> GetRecordByPage(int pageindex,int pageSize,String where){
		List<GameShopCateDTO> list = new ArrayList<GameShopCateDTO>();
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			toesUp = con.prepareCall("{ call GetRecordByPage2005(?,?,?,?,?,?,?,?)}");
			toesUp.setString(1, "MallItemsView");
			toesUp.setString(2, "*");
			toesUp.setString(3, "generateTime desc");
			toesUp.setString(4,where);
			toesUp.setInt(5, 10);
			toesUp.setInt(6,pageindex);
			toesUp.registerOutParameter(7,Types.INTEGER);
			toesUp.registerOutParameter(8,Types.INTEGER);
			rs = toesUp.executeQuery();
			while(rs.next()){
				GameShopCateDTO dto = new GameShopCateDTO();
				dto.setId(rs.getInt("id"));
				dto.setMallName(rs.getString("mallName"));
				dto.setPrice_gold(rs.getLong("price_gold"));
				dto.setPrice_score(rs.getLong("price_score"));
				dto.setVipPrice(rs.getLong("vipPrice"));
				dto.setDhCount(rs.getInt("dhCount"));
				dto.setCount(rs.getInt("count"));
				dto.setImagePath(rs.getString("imagePath"));
				dto.setTypeName(rs.getString("typeName"));
				dto.setPublish(rs.getInt("publish")); //发布
				dto.setRecom(rs.getInt("recom")); //热门
				dto.setPop(rs.getInt("pop"));  //人气
				dto.setNewMall(rs.getInt("newMall")); //新品
				
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
	
	public void updateState(String id,int radio){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			if(radio==0){
				sql = "delete from NNGameManage.dbo.MallItems where id = "+id;
			}
			else if(radio==1){
				sql = "update NNGameManage.dbo.MallItems set publish=1 where id = "+id;
			}
			else if(radio==2){
				sql = "update NNGameManage.dbo.MallItems set publish=0 where id = "+id;
			}
			else if(radio==3){
				sql = "update NNGameManage.dbo.MallItems set newMall=1 where id = "+id;
			}
			else if(radio==4){
				sql = "update NNGameManage.dbo.MallItems set newMall=0 where id = "+id;
			}
			else if(radio==5){
				sql = "update NNGameManage.dbo.MallItems set pop=1 where id = "+id;
			}
			else if(radio==6){
				sql = "update NNGameManage.dbo.MallItems set pop=0 where id = "+id;
			}
			else if(radio==7){
				sql = "update NNGameManage.dbo.MallItems set recom=1 where id = "+id;
			}
			else if(radio==8){
				sql = "update NNGameManage.dbo.MallItems set recom=0 where id = "+id;
			}
			ps = con.prepareStatement(sql);
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
	
	public void add(GameShopCateDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			ps = con.prepareStatement("insert into MallItems(mallName,descript,price_gold,vipPrice,imagePath,typeId,count) values(?,?,?,?,?,?,?)");
            ps.setString(1, dto.getMallName());
            ps.setString(2, dto.getDescript());
            ps.setDouble(3, dto.getPrice_gold());
            ps.setDouble(4, dto.getVipPrice());
            ps.setString(5, dto.getImagePath());
            ps.setInt(6, dto.getTypeID());
            ps.setInt(7, dto.getCount());
			ps.execute();
			System.out.println("增加商品成功!");
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
	
	public Boolean DeleteGameshop(int gameshopid) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_DELETE);
			ps.setInt(1, gameshopid);
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
	
	public GameShopCateDTO getById(int id){
		GameShopCateDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from MallItems where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new GameShopCateDTO();
			if(rs.next()){
				dto.setId(id);
				dto.setMallName(rs.getString("mallName"));
				dto.setDescript(rs.getString("descript"));
				dto.setPrice_gold(rs.getLong("price_gold"));
				dto.setPrice_score(rs.getLong("price_score"));
				dto.setImagePath(rs.getString("imagePath"));
				dto.setVipPrice(rs.getLong("vipPrice"));
				dto.setCount(rs.getInt("count"));
				dto.setTypeID(rs.getInt("typeID"));
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
	
	
	public void update(GameShopCateDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			//con.setAutoCommit(true);
			ps = con.prepareStatement("update MallItems set MallName=?,price_gold=?,vipPrice=?,descript=?,imagePath=?,typeID=?,count=? where id=?");
			ps.setString(1, dto.getMallName());
			ps.setDouble(2, dto.getPrice_gold());
			ps.setDouble(3, dto.getVipPrice());
			ps.setString(4, dto.getDescript());
			ps.setString(5, dto.getImagePath());	
			ps.setInt(6, dto.getTypeID());
			ps.setInt(7, dto.getCount());
			ps.setInt(8, dto.getId());
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
	
	public GameShopCateDTO getDetail(int id){
		GameShopCateDTO dto = new GameShopCateDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from MallItemsView where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setMallName(rs.getString("mallName"));
				dto.setPrice_gold(rs.getLong("price_gold"));
				dto.setPrice_score(rs.getLong("price_score"));
				dto.setVipPrice(rs.getLong("vipPrice"));
				dto.setDhCount(rs.getInt("dhCount"));
				dto.setCount(rs.getInt("count"));
				dto.setImagePath(rs.getString("imagePath"));
				dto.setDescript(rs.getString("descript"));
				dto.setTypeName(rs.getString("typeName"));
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
	
	public int checkMall(String mallName){
		int count = 0;
		String sql = "select count(*) from MallItems where mallName='"+mallName+"'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
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
		return count;
	}
}

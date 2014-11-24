package com.doowal.hk798.gameUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DrawAmountDAO {
	private int pageCount;
	private int recordCount;
	private DataSource ds;
	private static final String SQL_ADD = "insert into admin(userName,password,realName,phone,address,roleId,parent,parents) values(?,?,?,?,?,?,?,?)";
	private static final String SQL_DEL = "delete from admin where id=?";
	private static final String SQL_UPDATE = "update admin set password=?,phone=?,realName=?,address=?,roleId=? where id=?";
	
	
	public DrawAmountDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}

	public List<DrawAmountDTO> GetRecordByPage(int pageindex,int pageSize,String where,String orderby) {
		List<DrawAmountDTO> list = new ArrayList<DrawAmountDTO>();
		Connection con =null;
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
			con = ds.getConnection();
			//con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "drawAmountApplyView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				DrawAmountDTO dto = new DrawAmountDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setAmount(rs.getLong("applyGold"));
				dto.setBank(rs.getString("bankName"));
				dto.setRealRMB(rs.getDouble("realRMB"));
				dto.setBankaccount(rs.getString("cardNumber"));
				dto.setBankaddress(rs.getString("bankAddress"));
				dto.setBankholdername(rs.getString("realName"));
				dto.setApplytype(rs.getInt("status"));
				int applytype = rs.getInt("status");
				if(applytype==0){
					dto.setApplystate("取消申请");
				}
				else if(applytype==1){
					dto.setApplystate("申请中");
				}
				else if(applytype==2){
					dto.setApplystate("审核中");
				}
				else if(applytype==3){
					dto.setApplystate("审核不通过");
				}
				else{
					dto.setApplystate("已打款");
				}
				dto.setPhone(rs.getString("cellPhone"));
				dto.setDrawdate(rs.getString("applydate").substring(0, 19));
				/*dto.setType(rs.getInt("type"));
				int type = rs.getInt("type");
				if(type==1){
					dto.setTypeName("普通");
				}
				else{
					dto.setTypeName("快速");
				}*/
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(false);
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
	
	public List<DrawAmountDTO> selectByName(int pageindex,int pageSize,String where,String orderby) {
		List<DrawAmountDTO> list = new ArrayList<DrawAmountDTO>();
		Connection con =null;
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
			toesUp.setString(1, "drawAmountApplyView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				DrawAmountDTO dto = new DrawAmountDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setAmount(rs.getLong("amount"));
				dto.setBank(rs.getString("bank"));
				dto.setBankid(rs.getString("bankid"));
				dto.setBankaccount(rs.getString("bankaccount"));
				dto.setBankaddress(rs.getString("bankaddress"));
				dto.setBankholdername(rs.getString("bankholdername"));
				dto.setApplytype(rs.getInt("applytype"));
				dto.setPhone(rs.getString("phone"));
				dto.setApplystate(rs.getString("applystate"));
				dto.setExpress_ID(rs.getString("express_ID"));
				dto.setDrawdate(rs.getString("drawdate").substring(0, 19));
				dto.setType(rs.getInt("type"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(false);
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
	public int getRcordCount() {
		return recordCount;
	}

	public Boolean DeleteDrawRecord(int userID,String express_ID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			ps = con.prepareStatement("delete from drawAmountApply where UserID=? and express_ID=?");
			ps.setInt(1, userID);
			ps.setString(2, express_ID);
			ps.execute();
			r=true;
			
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return r;
	}
	
	public void dealSH(String express_ID) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update drawAmountApply set applytype=2,cancelType=2 where express_ID=?");
			ps.setString(1, express_ID);
			ps.execute();
			System.out.println("提交到审核成功!");
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(ps!=null){
					ps.close();ps=null;
				}
				if(con!=null){
					con.close();
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
	
	public void dealCancel(String express_ID,int userID) {  //用户取消
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			double amount = 0;
			String sql = "select * from QPTreasureDB.dbo.drawAmountApply where userID="+userID+" and express_ID='"+express_ID+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				amount = rs.getDouble("amount");
			}
			
			sql = "update QPTreasureDB.dbo.GameScoreInfo set insureScore = insureScore + "+amount+",ExchangeMoney=ExchangeMoney-"+amount+" where userID="+userID;
			ps = con.prepareStatement(sql);
			ps.execute();
			
			ps = con.prepareStatement("update QPTreasureDB.dbo.drawAmountApply set applytype=2,cancelType=1 where express_ID=?");
			ps.setString(1, express_ID);
			ps.execute();
			System.out.println("执行取消操作!");
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();
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
					con.close();
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public DrawAmountDTO getDealDraw(int userID,String express_ID){
		DrawAmountDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from drawAmountApplyView where userID="+userID+" and express_ID='"+express_ID+"'");
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new DrawAmountDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setAmount(rs.getLong("amount"));
				dto.setExpress_ID(rs.getString("express_ID"));
				dto.setDrawdate(rs.getString("drawdate").substring(0, 19));
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();
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
					con.close();
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dto;
	}

	public String updateDealDraw(int userID,String express_ID) {
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		try {
/*		long amount = 0;
		String sql = "select * from drawAmountApply where userID="+userID+" and express_ID='"+express_ID+"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()){
			amount = rs.getLong("amount");
		}
		
		sql = "update RegAccountsInfo set score = score - "+amount+" where userID="+userID;
		ps = con.prepareStatement(sql);
		ps.execute();*/
		con = ds.getConnection();
		ps = con.prepareStatement("update QPTreasureDB.dbo.drawAmountApply set applytype=1,cancelType=3 where userID=? and express_ID=?");
		ps.setInt(1, userID);
		ps.setString(2, express_ID);
		ps.execute();
		msg = "恭喜，打款成功!";
		if(ps!=null){
			ps.close();ps=null;
		}
		if(con!=null){
			con.close();
		}
			} 
			catch (SQLException e) {
				e.printStackTrace();
				msg = "操作失败，请联系客服!";
				try{
					if(ps!=null){
						ps.close();ps=null;
					}
					if(con!=null){
						con.close();
					}
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
			return msg;
	}
	
	public void updateTuihuanDraw(String drawBeizhu,int userID,String express_ID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			double amount = 0;
			String sql = "select * from drawAmountApply where userID="+userID+" and express_ID='"+express_ID+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				amount = rs.getDouble("amount");
			}
			
			sql = "update RegAccountsInfo set score = score + "+amount+" where userID="+userID;
			ps = con.prepareStatement(sql);
			ps.execute();
			
			ps = con.prepareStatement("update drawAmountApply set applytype=6,drawBeizhu=? where userID=? and express_ID=?");
			ps.setString(1, drawBeizhu);
			ps.setInt(2, userID);
			ps.setString(3, express_ID);
			ps.execute();
			System.out.println("退款成功!");
			
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();
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
					con.close();
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	//查找取消用户的重复次数
	public int queryCancelType(int userID,String express_ID){
		DrawAmountDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cancelType = 0;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from drawAmountApplyView where userID="+userID+" and express_ID='"+express_ID+"'");
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new DrawAmountDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setCancelType(rs.getInt("cancelType"));
				cancelType = rs.getInt("cancelType");
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
		return cancelType;
	}
}

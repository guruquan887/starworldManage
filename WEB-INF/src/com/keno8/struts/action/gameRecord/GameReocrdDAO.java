package com.keno8.struts.action.gameRecord;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.doowal.hk798.gameUser.BaseDAO;



public class GameReocrdDAO extends BaseDAO{
	private int pageCount;
	private int recordCount;
	private DataSource ds;

	public GameReocrdDAO(DataSource ds) {
		this.ds = ds;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}

	public List<GameRecordDTO> GetRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameRecordDTO> list = new ArrayList<GameRecordDTO>();
		Connection con = null;
		ResultSet rs = null;
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
			toesUp.setString(1, "GameRecordView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameRecordDTO dto = new GameRecordDTO();
				dto.setAccounts(rs.getString("accounts"));
				dto.setBetGold(rs.getDouble("betGold"));
				dto.setBetGameArea(rs.getString("gameAreaName"));
				dto.setBetRate(rs.getFloat("betRate"));
				dto.setBetSerial(rs.getString("betSerial"));
				dto.setBetResult(rs.getDouble("betResult"));
				String createTime ="";
				String createTime1 ="";
				if(null != rs.getString("generateTime") && !"".equals(rs.getString("generateTime"))){
					createTime = rs.getString("generateTime").substring(0, 19);
					}
				if(null != rs.getString("generateTime1") && !"".equals(rs.getString("generateTime1"))){
					createTime1 = rs.getString("generateTime1").substring(0, 19);
					}
				dto.setCreateTime(createTime);
				dto.setCreateTime1(createTime1);
				dto.setWinArea(rs.getString("gameResult"));
				dto.setKindName(rs.getString("kindName"));
				dto.setAccounts(rs.getString("accounts"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
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
	
	
}

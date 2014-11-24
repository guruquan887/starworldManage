package com.wiiy.spreader;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.action.gameUser.mark.BaseDAO;

public class SpreaderDao extends BaseDAO{
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public SpreaderDao(DataSource ds) {
		this.ds = ds;
	}

	public List<Spreader> pageSpreader(String where, int pageindex, int pagesize) {
		List<Spreader> list = new ArrayList<Spreader>();
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		try {
			/*
			 * Create Procedure [dbo].[GetRecordByPage2005] @TableName
			 * varchar(50), --表名 @Fields varchar(5000) = '*', --字段名(全部字段为*)
			 * @OrderField varchar(5000), --排序字段(必须!支持多字段) @sqlWhere
			 * varchar(5000) = Null,--条件语句(不用加where) @pageSize int, --每页多少条记录
			 * @pageIndex int = 1 , --指定当前为第几页 @TotalPage int output, --返回总页数
			 * @totalRecord int output
			 */
			con = ds.getConnection();
			con.setAutoCommit(false);
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "SepreaderInfo_View1");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, " number desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pagesize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();
			Spreader dto = null;
			while (rs.next()) {
				dto = new Spreader();
				dto.setAccounts(rs.getString("accounts"));
				dto.setUserId(rs.getInt("userID"));
//				dto.setSpreaderName(rs.getString("spreaderName"));
				dto.setNumber(rs.getInt("number"));
				dto.setIncome(rs.getDouble("tcScore"));
//				dto.setGxjinbi(rs.getDouble("gxGold"));//贡献银子
				dto.setScore(rs.getDouble("xxScore"));
				dto.setXxLostSum(rs.getDouble("xxSumScore"));

				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(true);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	
	
	public List<Spreader> pageSpreaderOfXX(String where, int pageindex, int pagesize) {
		List<Spreader> list = new ArrayList<Spreader>();
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		try {
			/*
			 * Create Procedure [dbo].[GetRecordByPage2005] @TableName
			 * varchar(50), --表名 @Fields varchar(5000) = '*', --字段名(全部字段为*)
			 * @OrderField varchar(5000), --排序字段(必须!支持多字段) @sqlWhere
			 * varchar(5000) = Null,--条件语句(不用加where) @pageSize int, --每页多少条记录
			 * @pageIndex int = 1 , --指定当前为第几页 @TotalPage int output, --返回总页数
			 * @totalRecord int output
			 */
			con = ds.getConnection();
			con.setAutoCommit(false);
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "SpreaderXXInfo_View1");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, " number desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pagesize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();
			Spreader dto = null;
			while (rs.next()) {
				dto = new Spreader();
				dto.setAccounts(rs.getString("accounts"));
				dto.setUserId(rs.getInt("userID"));
				dto.setSpreaderName(rs.getString("topAccounts"));
				dto.setNumber(rs.getInt("number"));
				dto.setGxjinbi(rs.getDouble("gxGold"));//贡献银子
				dto.setLostSum(rs.getDouble("sumPureScore"));
				
				dto.setScore(rs.getDouble("xxScore"));
				dto.setIncome(rs.getDouble("xxgxGold"));
				dto.setXxLostSum(rs.getDouble("xxsumPuerScore"));

				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(true);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	
	public List<Spreader> pageSpreaderXXDetailRecord(String where, int pageindex, int pagesize) {
		List<Spreader> list = new ArrayList<Spreader>();
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		try {
			/*
			 * Create Procedure [dbo].[GetRecordByPage2005] @TableName
			 * varchar(50), --表名 @Fields varchar(5000) = '*', --字段名(全部字段为*)
			 * @OrderField varchar(5000), --排序字段(必须!支持多字段) @sqlWhere
			 * varchar(5000) = Null,--条件语句(不用加where) @pageSize int, --每页多少条记录
			 * @pageIndex int = 1 , --指定当前为第几页 @TotalPage int output, --返回总页数
			 * @totalRecord int output
			 */
			con = ds.getConnection();
			con.setAutoCommit(false);
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "SepreaderDetailInfo_View");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, " createTime desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pagesize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();
			Spreader dto = null;
			while (rs.next()) {
				dto = new Spreader();
				dto.setAccounts(rs.getString("accounts"));
				dto.setUserId(rs.getInt("userID"));
				dto.setSpreaderName(rs.getString("topAccounts"));
				dto.setGxjinbi(rs.getDouble("gxGold"));//贡献银子
				dto.setLostSum(rs.getDouble("sumPureScore"));
				dto.setCreateTime(rs.getString("createTime").substring(0,19));

				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(true);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	

	public int getTotalPage() {
		return pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

}

package com.manage.struts.video;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

public class SBVideoDAO {

	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public SBVideoDAO(DataSource ds) {
		this.ds = ds;
	}

	public int getTotalPage() {
		return pageCount;
	}

	public int getRcordCount() {
		return recordCount;
	}
	
	public List<SBVideoDTO> select(){
		List<SBVideoDTO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from rooms");
			rs = ps.executeQuery();
			while(rs.next()){
				SBVideoDTO dto = new SBVideoDTO();
				dto.setRoomID(rs.getInt("roomID"));
				dto.setRoomName(rs.getString("roomName"));
				list.add(dto);
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
		
		return list;
	}

	public Boolean checkFileIsExist(String bjl_fileName) {
		Boolean r = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String sql = "select * from SBVideo where fileName ='"
					+ bjl_fileName + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);

			if (rs.next()) {
				r = true;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (st != null) {
					st.close();
					st = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return r;
	}

	public Boolean checkFileIsExistByID(int videoID) {
		Boolean r = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String sql = "select * from SBVideo where id ='" + videoID + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);

			if (rs.next()) {
				r = true;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (st != null) {
					st.close();
					st = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return r;
	}

	static public String RandomNum(int length) {
		Random r = new Random();
		String s = "";
		String str = "0123456789";
		for (int i = 0; i < length; i++) {
			int t = ((r.nextInt() % 10) + 10) % 10;
			s += str.substring(t, t + 1);
		}
		return s;
	}

	// 添加骰宝 视频文件
	public int add(SBVideoDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int r = 0;
		String spResult = "";
		try {
			if(dto.getResultAnd()%2==0){
				spResult = "双";
			}
			else if(dto.getResultAnd()%2!=0){
				spResult = "单";
			}
			if(dto.getResultAnd()>10){
				spResult = spResult + ",大";
			}
			else if(dto.getResultAnd()<11){
				spResult = spResult + ",小";
			}
			con = ds.getConnection();
			String sql = "insert into SBVideo(videoName,fileName,videolength,resultTime,startTime,endTime,result1,result2,result3,hgName,hg2Name,result,resultOfWinLost)"
					+ "values('"
					+ dto.getVideoName()
					+ "','"
					+ dto.getFileName()
					+ "',"
					+ dto.getVideolength()
					+ ","
					+ dto.getResultTime()
					+ ","
					+ dto.getStartTime()
					+ ","
					+ dto.getEndTime()
					+ ","
					+ dto.getResult1()
					+ ","
					+ dto.getResult2()
					+ ","
					+ dto.getResult3()
					+ ",'"
					+ dto.getHgName()
					+ "','"
					+ dto.getHg2Name()
					+ "',"
					+ dto.getResultAnd()
					+ ",'"
					+ spResult
					+ "')";
			System.out.println("视频文件添加 sql语句:" + sql);
			ps = con.prepareStatement(sql);
			ps.execute();
			r = 1;

			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return r;
	}

	// where条件 查询视频
	public List<SBVideoDTO> ListByPage(String where, int pageindex, int pageSize) {
		List<SBVideoDTO> list = null;
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		try {
			/*
			 * 
			 * Create Procedure [dbo].[GetRecordByPage2005] @TableName
			 * varchar(50), --表名 @Fields varchar(5000) = '*', --字段名(全部字段为*)
			 * @OrderField varchar(5000), --排序字段(必须!支持多字段) @sqlWhere
			 * varchar(5000) = Null,--条件语句(不用加where) @pageSize int, --每页多少条记录
			 * @pageIndex int = 1 , --指定当前为第几页 @TotalPage int output, --返回总页数
			 * @totalRecord int output
			 * 
			 */
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call.
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
			toesUp.setString(1, "SBVideo");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "id");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();
			list = new ArrayList<SBVideoDTO>();
			
			while (rs.next()) {
				SBVideoDTO dto = new SBVideoDTO();
				dto.setFileName(rs.getString("fileName"));
				dto.setVideoName(rs.getString("videoName"));
				dto.setVideoID(rs.getInt("id"));
				dto.setHgName(rs.getString("hgName"));
				dto.setResultTime(rs.getInt("resultTime"));
				dto.setStartTime(rs.getInt("startTime"));
				dto.setEndTime(rs.getInt("endTime"));
				dto.setVideoType(rs.getInt("videoType"));
				dto.setResult1(rs.getString("result1"));
				dto.setResult2(rs.getString("result2"));
				dto.setResult3(rs.getString("result3"));
				dto.setResult(dto.getResult1()+","+dto.getResult2()+","+dto.getResult3());
				dto.setResultOfWinLost(rs.getString("resultOfWinLost"));
				dto.setVideolength(rs.getInt("videoLength"));

				list.add(dto);
			}
			System.out.println(list.size() + "<<<<<搜索，获得视频实体个数");
			toesUp.getMoreResults();
			pageCount = toesUp.getInt(7);
			recordCount = toesUp.getInt(8);
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (toesUp != null) {
				toesUp.close();
				toesUp = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (toesUp != null) {
					toesUp.close();
					toesUp = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return list;
	}

	// 通过ID 删除视频记录
	public int delete(int id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int r = 0;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "delete from SBVideo where id=" + id;
			r = st.executeUpdate(sql);

			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (st != null) {
					st.close();
					st = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return r;
	}

	public int update(SBVideoDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;

		try {
			con = ds.getConnection();
			String sql = "update SBVideo set videoName=?,fileName=?,videolength=?,resultTime=?,result1=?,result2=?,result3=?,hgName=?,hg2Name=?,startTime=?,endTime=?,result=? where id =?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getVideoName());
            ps.setString(2, dto.getFileName());
            ps.setInt(3, dto.getVideolength());
            ps.setInt(4, dto.getResultTime());
            ps.setString(5, dto.getResult1());
            ps.setString(6, dto.getResult2());
            ps.setString(7, dto.getResult3());
            ps.setString(8, dto.getHgName());
            ps.setString(9, dto.getHg2Name());
            ps.setInt(10, dto.getStartTime());
            ps.setInt(11, dto.getEndTime());
            ps.setInt(12, dto.getResultAnd());
            ps.setInt(13, dto.getVideoID());
			System.out.println("视频文件修改 sql语句:" + sql);
		    ps.execute();
		    i = ps.getUpdateCount();
			System.out.println("update success!!!!!!!!!!!!!");
			
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return i;
	}

	// 通过视频ID 获得视频实体
	public SBVideoDTO getVideoByID(int id) {
		SBVideoDTO r = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "select * from SBVideo where id='" + id + "'";

			rs = st.executeQuery(sql);
			if (rs.next()) {
				r = new SBVideoDTO();
				r.setVideoID(rs.getInt("id"));
				r.setVideoName(rs.getString("videoName"));
				r.setFileName(rs.getString("fileName"));
				r.setHgName(rs.getString("hgName"));
				r.setHg2Name(rs.getString("hg2Name"));
				r.setVideolength(rs.getInt("videolength"));
				r.setResult1(rs.getString("result1"));
				r.setResult2(rs.getString("result2"));
				r.setResult3(rs.getString("result3"));
				r.setResultTime(rs.getInt("resultTime"));
				r.setStartTime(rs.getInt("startTime"));
				r.setEndTime(rs.getInt("endTime"));
				
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (st != null) {
					st.close();
					st = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return r;
	}

}

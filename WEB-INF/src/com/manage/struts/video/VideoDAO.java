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

public class VideoDAO {

	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public VideoDAO(DataSource ds) {
		this.ds = ds;
	}

	public int getTotalPage() {
		return pageCount;
	}

	public int getRcordCount() {
		return recordCount;
	}
	
	public List<VideoDTO> select(){
		List<VideoDTO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from rooms");
			rs = ps.executeQuery();
			while(rs.next()){
				VideoDTO dto = new VideoDTO();
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
			String sql = "select * from BJLVideo where fileName ='"
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
			String sql = "select * from BJLVideo where id ='" + videoID + "'";
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

	// 添加百家乐 视频文件
	public int add(VideoDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int r = 0;
		try {
			// Date d = Calendar.getInstance().getTime();
			// SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			//
			// String number = sf.format(d);
			// String number_Last = RandomNum(4);
			// number = number + number_Last;
			//			
			// dto.setVideoName(number);

			con = ds.getConnection();
			String sql = "insert into BJLVideo(videoName,fileName,result,videolength,result1Time,result1,result2Time,result2,result3Time,result3,result4Time,result4,result5Time,result5,result6Time,result6,hgName,hg2Name,videoType,resultOfWinLost,roomType)"
					+ "values('"
					+ dto.getVideoName()
					+ "','"
					+ dto.getFileName()
					+ "','"
					+ dto.getResult()
					+ "',"
					+ dto.getVideolength()
					+ ","
					+ dto.getResult1Time()
					+ ",'"
					+ dto.getResult1()
					+ "',"
					+ dto.getResult2Time()
					+ ",'"
					+ dto.getResult2()
					+ "',"
					+ dto.getResult3Time()
					+ ",'"
					+ dto.getResult3()
					+ "',"
					+ dto.getResult4Time()
					+ ",'"
					+ dto.getResult4()
					+ "',"
					+ dto.getResult5Time()
					+ ",'"
					+ dto.getResult5()
					+ "',"
					+ dto.getResult6Time()
					+ ",'"
					+ dto.getResult6()
					+ "','"
					+ dto.getHgName()
					+ "','"
					+ dto.getHg2Name()
					+ "',"
					+ dto.getVideoType()
					+ ",'"
					+ dto.getResultOfWinLost()
					+ "','"
					+ dto.getRoomType()
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
	public List<VideoDTO> ListByPage(String where, int pageindex, int pageSize) {
		List<VideoDTO> list = null;
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
			toesUp.setString(1, "BJLVideo");// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "id");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();
			list = new ArrayList<VideoDTO>();
			
			while (rs.next()) {
				VideoDTO dto = new VideoDTO();
				dto.setFileName(rs.getString("fileName"));
				dto.setVideoName(rs.getString("videoName"));
				dto.setVideoID(rs.getInt("id"));
				dto.setHgName(rs.getString("hgName"));
				dto.setVideoType(rs.getInt("videoType"));
				dto.setResult(rs.getString("result"));
//				System.out.println(rs.getString("resultOfWinLost")+"<<<<<<<<<resultOfWinLost");
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
			String sql = "delete from BJLVideo where id=" + id;
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

	public int update(VideoDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;

		try {
			con = ds.getConnection();
			String sql = "update BJLVideo set videoName=?,fileName=?,result=?,videolength=?,result1Time=?,result1=?,result2Time=?,result2=?,result3Time=?,result3=?,result4Time=?,result4=?,result5Time=?,result5=?,result6Time=?,result6=?,hgName=?,hg2Name=?,videoType=?,resultOfWinLost=? where id =?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getVideoName());
            ps.setString(2, dto.getFileName());
            ps.setString(3, dto.getResult());
            ps.setInt(4, dto.getVideolength());
            ps.setInt(5, dto.getResult1Time());
            ps.setString(6, dto.getResult1());
            ps.setInt(7, dto.getResult2Time());
            ps.setString(8, dto.getResult2());
            ps.setInt(9, dto.getResult3Time());
            ps.setString(10, dto.getResult3());
            ps.setInt(11, dto.getResult4Time());
            ps.setString(12, dto.getResult4());
            ps.setInt(13, dto.getResult5Time());
            ps.setString(14, dto.getResult5());
            ps.setInt(15, dto.getResult6Time());
            ps.setString(16, dto.getResult6());
            ps.setString(17, dto.getHgName());
            ps.setString(18, dto.getHg2Name());
            ps.setInt(19, dto.getVideoType());
            ps.setString(20, dto.getResultOfWinLost());
            ps.setInt(21, dto.getVideoID());
            
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
	public VideoDTO getVideoByID(int id) {
		VideoDTO r = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "select * from BJLVideo where id='" + id + "'";

			rs = st.executeQuery(sql);
			if (rs.next()) {
				r = new VideoDTO();
				r.setVideoID(rs.getInt("id"));
				r.setVideoName(rs.getString("videoName"));
				r.setFileName(rs.getString("fileName"));
				r.setHgName(rs.getString("hgName"));
				r.setHg2Name(rs.getString("hg2Name"));
				r.setVideoType(rs.getInt("videoType"));
				r.setResult(rs.getString("result"));
				
				r.setResult1(rs.getString("result1"));
				r.setResult2(rs.getString("result2"));
				r.setResult3(rs.getString("result3"));
				r.setResult4(rs.getString("result4"));
				r.setResult5(rs.getString("result5"));
				r.setResult6(rs.getString("result6"));
				
				r.setResult1Time(rs.getInt("result1Time"));
				r.setResult2Time(rs.getInt("result2Time"));
				r.setResult3Time(rs.getInt("result3Time"));
				r.setResult4Time(rs.getInt("result4Time"));
				r.setResult5Time(rs.getInt("result5Time"));
				r.setResult6Time(rs.getInt("result6Time"));
				r.setRoomType(rs.getInt("roomType"));
				r.setResultOfWinLost(rs.getString("resultOfWinLost"));
				r.setVideolength(rs.getInt("videolength"));
				
				int selectresult1Type = Integer.parseInt(rs.getString("result1").substring(0,1));
				String selectresult1Points = rs.getString("result1").substring(1,2);
				String selectresult1ZX = rs.getString("result1").substring(2,3);
				r.setSelectresult1Type(selectresult1Type);
                r.setSelectresult1Points(selectresult1Points);
                r.setSelectresult1ZX(selectresult1ZX);
                
                int selectresult2Type = Integer.parseInt(rs.getString("result2").substring(0,1));
				String selectresult2Points = rs.getString("result2").substring(1,2);
				String selectresult2ZX = rs.getString("result2").substring(2,3);
			    r.setSelectresult2Type(selectresult2Type);
	            r.setSelectresult2Points(selectresult2Points);
	            r.setSelectresult2ZX(selectresult2ZX);
	            
	            int selectresult3Type = Integer.parseInt(rs.getString("result3").substring(0,1));
				String selectresult3Points = rs.getString("result3").substring(1,2);
				String selectresult3ZX = rs.getString("result3").substring(2,3);
			    r.setSelectresult3Type(selectresult3Type);
	            r.setSelectresult3Points(selectresult3Points);
	            r.setSelectresult3ZX(selectresult3ZX);
	            
	            int selectresult4Type = Integer.parseInt(rs.getString("result4").substring(0,1));
				String selectresult4Points = rs.getString("result4").substring(1,2);
				String selectresult4ZX = rs.getString("result4").substring(2,3);
			    r.setSelectresult4Type(selectresult4Type);
	            r.setSelectresult4Points(selectresult4Points);
	            r.setSelectresult4ZX(selectresult4ZX);
	            
	            int selectresult5Type = Integer.parseInt(rs.getString("result5").substring(0,1));
				String selectresult5Points = rs.getString("result5").substring(1,2);
				String selectresult5ZX = rs.getString("result5").substring(2,3);
			    r.setSelectresult5Type(selectresult5Type);
	            r.setSelectresult5Points(selectresult5Points);
	            r.setSelectresult5ZX(selectresult5ZX);
	            
	            int selectresult6Type = Integer.parseInt(rs.getString("result6").substring(0,1));
				String selectresult6Points = rs.getString("result6").substring(1,2);
				String selectresult6ZX = rs.getString("result6").substring(2,3);
			    r.setSelectresult6Type(selectresult6Type);
	            r.setSelectresult6Points(selectresult6Points);
	            r.setSelectresult6ZX(selectresult6ZX);
				

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

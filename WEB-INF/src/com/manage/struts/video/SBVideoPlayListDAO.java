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


public class SBVideoPlayListDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public SBVideoPlayListDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public int getTotalPage() {
		return pageCount;
	}

	public int getRcordCount() {
		return recordCount;
	}
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public int maxNum(int roomType){  //统计某一房间类型的视频个数
		int maxID = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select max(id) from SBVideo where roomType=?");
			ps.setInt(1, roomType);
			rs = ps.executeQuery();
			if(rs.next()){
				maxID = rs.getInt(1);
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
		return maxID;
		
	}
	
	public int getVideoID(int vid){  //统计某一房间类型的视频个数
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from SBVideo where id=?");
			ps.setInt(1, vid);
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
	
	public int countNum(int roomType){  //统计某一房间类型的视频个数
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from SBVideo where roomType=?");
			ps.setInt(1, roomType);
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
	
	public int SumVlength(int roomType){   //统计某一房间类型的秒数
		int sumVlength = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select sum(videoLength) from SBVideo where roomType=?");
			ps.setInt(1, roomType);
			rs = ps.executeQuery();
			if(rs.next()){
				sumVlength = rs.getInt(1);
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
		return sumVlength;
		
	}
	
	public int countRoom(){
		int count = 0;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from rooms");
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
	
	public List<RoomDTO> getRoom(){
		
		List<RoomDTO> list = new ArrayList<RoomDTO>() ;
			Connection con = null ;
			PreparedStatement ps = null ;
			ResultSet rs = null ;
			try {
				con = ds.getConnection();
				String sql = "select * from rooms ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					RoomDTO dto = new RoomDTO();
					dto.setRoomId(rs.getInt("roomId"));
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
	
	
	public String createList(){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		int xh = 1;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "truncate table SBPlayList1";
			ps = con.prepareStatement(sql);
			ps.execute();
			System.out.println("清除播放列表数据成功");
			
			Random random = new Random();
			int maxCount = maxNum(5);
			int videoCount = countNum(5);//获得骰宝视频总个数
			int sumVlength = SumVlength(5);//计算视频总长度时间
			
			int vlength = random.nextInt(7200);
		    if(vlength <= 3200){
		    	vlength = vlength+3600;
		    } 
		    if(vlength > 3200 && vlength <= 3600){
		    	vlength = vlength + 3000;
		    }
		    int avg = sumVlength / videoCount;   //视频平均长度
			avg = vlength / avg;  //计算出视频循环播放的个数
			    
			    if(avg%2!=0){
			    	avg = avg + 1;  //奇偶造型
			    }
			System.out.println("videoCount:"+videoCount+"  sumVlength:"+sumVlength+"  vlength:"+vlength+"  avg："+avg);
			int vid = 0;
			int eid = 0;
			for(int i=0;i<=avg;i++){
				vid = random.nextInt(maxCount)+1;
				eid = getVideoID(vid);
				if(eid!=0){
					sql = "insert into SBPlayList1 (Vid,state,xh,createTime) values(?,0,?,getDate())";
					ps1 = con.prepareStatement(sql);
					ps1.setInt(1, vid);
					ps1.setInt(2, xh);
					ps1.execute();
					++xh;
				}
			}
		    
			msg = "播放列表生成成功!";
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
		return msg;
	}
	
	
/*	public String[] create(Connection con,ResultSet rs,String sql,String []s,String []d,int i,int j,String m,int k,int count){
		String [] cc = null;
		PreparedStatement ps1 = null;
		ResultSet rs1= null;
		PreparedStatement ps2 = null;
		ResultSet rs2= null;
		PreparedStatement ps3 = null;
		ResultSet rs3= null;
		
		try {
			sql="select count(*) from BJLVideo where videotype ="+rs.getInt("videoType") +"and hgName ='"+rs.getString("hgName")+"'";
			ps1 = con.prepareStatement(sql);
			rs1 = ps1.executeQuery();
			if(rs1.next()){
				count = rs1.getInt(1);
			}
			
			if(rs.getInt("videoType") == 1){
				sql = "select hgName,videoName,videotype from BJLVideo where videotype ="+rs.getInt("videoType") +"and hgName ='"+rs.getString("hgName") +"' group by hgName,videoName,videotype";
				ps2 = con.prepareStatement(sql);
				rs2 = ps2.executeQuery();
				s = new String[count];
				while(rs2.next()){
					System.out.println("s+++videoName="+rs2.getString("videoName"));
					s[i]= rs2.getString("videoName");
					i++;
				}
			}else{
				sql = "select hgName,videoName,videotype from BJLVideo where videotype ="+rs.getInt("videoType") +"and hgName ='"+rs.getString("hgName") +"' group by hgName,videoName,videotype";
				ps3 = con.prepareStatement(sql);
				rs3 = ps3.executeQuery();
				d = new String[count];
				while(rs3.next()){
					System.out.println("d+++videoName="+rs3.getString("videoName"));
					d[j]= rs3.getString("videoName");
					j++;
				}
			}
			if(null != s){
               cc = s;
               System.out.println("s--cc:"+Arrays.toString(cc));
			}
			if(null != d){
			   cc = d;
			   System.out.println("d--cc:"+Arrays.toString(cc));
			}
			if(null != s && null != d){
				RanNumber.sortArray(s,d);
				s = null;
				d = null;
				i = 0 ;
				j = 0 ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cc;
		
	}*/
	
	public List<VideoDTO> countList(){
		List<VideoDTO> list = new ArrayList<VideoDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			//select hgName,count(hgName) from BJLVideo group by hgName
			String sql = "select hgName,count(videotype)as count,videotype from BJLVideo group by hgName,videotype";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				VideoDTO dto = new VideoDTO();
				dto.setHgName(rs.getString("hgName"));
				dto.setCount(rs.getInt(2));
				dto.setVideoType(rs.getInt("videoType"));
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
	
	// where条件 查询播放列表中视频记录
	public List<VideoPlayListDTO> ListByPage(String where, int pageindex, int pageSize) {
		List<VideoPlayListDTO> list = null;
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		String view = "SBVideoListView_22081";
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
			toesUp.setString(1, view);// 表名
			toesUp.setString(2, "*");// 全部字段为*
			toesUp.setString(3, "xh,createTime desc");// 排序字段
			toesUp.setString(4, where);// 条件语句(不用加where)
			toesUp.setInt(5, pageSize);// 每页多少条记录
			toesUp.setInt(6, pageindex);// 指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER);
			toesUp.registerOutParameter(8, Types.INTEGER);
			rs = toesUp.executeQuery();

			list = new ArrayList<VideoPlayListDTO>();
			while (rs.next()) {
				VideoPlayListDTO dto = new VideoPlayListDTO();

				dto.setFileName(rs.getString("fileName"));
				dto.setVideoName(rs.getString("videoName"));
				dto.setVideoID(rs.getInt("vid"));
				dto.setHgName(rs.getString("hgName"));
				dto.setVideoType(rs.getInt("videoType"));
				dto.setResult(rs.getString("result1")+","+rs.getString("result2")+","+rs.getString("result3"));
				dto.setState(rs.getInt("state"));
				dto.setPlayID(rs.getInt("id"));
				dto.setVideolength(rs.getInt("videolength"));
				dto.setXh(rs.getInt("xh"));
				dto.setStartTime(rs.getInt("startTime"));
				dto.setEndTime(rs.getInt("endTime"));
				dto.setResultTime(rs.getInt("resultTime"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setResultAnd(rs.getInt("result"));

				list.add(dto);
			}
//			System.out.println(list.size() + "<<<<<搜索，获得视频播放列表中 实体个数");
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
				e1.printStackTrace();
			}
		}
		return list;
	}
	
	// 通过ID 删除播放列表中视频记录
	public int delete(int id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int r = 0;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "delete from SBPlayList1 where id=" + id;
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
				e1.printStackTrace();
			}
		}
		return r;
	}
	
	// 通过ID 修改被插播视频文件的序号[xh]与播放状态[state---0,未播放]
	public int updateXH(int playListID,int beforeXh) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int r = 0;
		try {
			con = ds.getConnection();
			String sql = "update SBPlayList1 set state = 0 , xh = ? ,createTime = getdate() where id= ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, beforeXh);
			ps.setInt(2, playListID);
			ps.execute();
			r = ps.getUpdateCount();
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
		return r;
	}
	
	public int addXH( int videoID,int beforeXh) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int r = 0;
		try {
			con = ds.getConnection();
			String sql = "update SBPlayList1 (vid,state,xh)values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, videoID);
			ps.setInt(2, 0);
			ps.setInt(3, beforeXh);
			ps.execute();
			r = ps.getUpdateCount();
			System.out.println("addXH success!!!!!!!!!!!!!");

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
	
	// 替播 更新一条新记录 序号[xh]与播放状态[state---0,未播放]
	public int updatePlay( int videoID,int beforeXh,int roomID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int r = 0;
		String table = "";
		if(roomID==1){
			table = "SBPlayList1";
		}
		else if(roomID==2){
			table = "SBPlayList2";
		}
		else if(roomID==3){
			table = "SBPlayList3";
		}
		else if(roomID==4){
			table = "SBPlayList4";
		}
		try {
			con = ds.getConnection();
			String sql = "update "+table+" set Vid="+videoID+" where id="+beforeXh;
			System.out.println("替播sql语句为:"+sql);
			ps = con.prepareStatement(sql);
			ps.execute();
			r = ps.getUpdateCount();
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
		return r;
	}
	
	// 获取 正在播放视频 在记录中的第几条
	public int getNum() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int r = 0;
		try {
			String sql = "SELECT COUNT(*) FROM SBPlayList1 WHERE (select xh from SBPlayList1 where state=2)>=xh";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				r = rs.getInt(1);
			}
			System.out.println("播放视频 所在行数为："+r);
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
	

}

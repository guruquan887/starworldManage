package com.manage.struts.video;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;


public class VideoPlayListDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public VideoPlayListDAO(DataSource ds) {
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
	
	public int countNum(int roomType){  //统计某一房间类型的视频个数
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from BJLVideo where roomType=?");
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
			ps = con.prepareStatement("select sum(videoLength) from BJLVideo where roomType=?");
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
			ps = con.prepareStatement("select count(*) from rooms where type=1");
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
				String sql = "select * from rooms where type=1";
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
	
	
	public String createList(int roomID){
		String msg = "";
		
		String[] sss = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1= null;
		PreparedStatement ps2 = null;
		ResultSet rs2= null;
		PreparedStatement ps3 = null;
		ResultSet rs3= null;
		PreparedStatement ps4 = null;
		ResultSet rs4 = null;
		PreparedStatement ps5 = null;
		PreparedStatement ps6 = null;
		PreparedStatement ps7 = null;
		ResultSet rs7 = null;
		PreparedStatement ps8 = null;
		PreparedStatement ps9 = null;
		ResultSet rs9 = null;
		PreparedStatement ps10 = null;
		ResultSet rs10 = null;
		PreparedStatement ps11 = null;
		//ResultSet rs11 = null;
		PreparedStatement ps12 = null;
		ResultSet rs12 = null;
		PreparedStatement ps13 = null;
		
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "select * from rooms where type=1";
			ps9 = con.prepareStatement(sql);
			rs9 = ps9.executeQuery();
			int countRoom = countRoom(); //获得房间个数
			int [] rooms = new int[countRoom];
			int ii = 0;
			while(rs9.next()){
				
				rooms[ii] = rs9.getInt("roomId");
				ii++;
			}
			System.out.println(">>>>>>>>>>>>>>>>>rooms[0]"+rooms[0]+"rooms[1]"+rooms[1]+"for循环的长度："+rooms.length);
			Random random = new Random();
			
			//for(int n=1; n<= rooms.length;n++){
				
		    //int videoCount = countNum(rooms[n-1]);  //某一房间类型的视频个数
		    //int sumVlength = SumVlength(rooms[n-1]);  //视频总长度秒数
		    int videoCount = countNum(roomID);
		    int sumVlength = SumVlength(roomID);
			
		    if(videoCount!=0){   //判断某一房间的视频个数，为0则不执行插入播放列表操作
		    
		    
		    int avg = sumVlength / videoCount;   //视频平均长度
		    
			int vlength = random.nextInt(7200);
		    if(vlength <= 3200){
		    	vlength = vlength+3600;
		    } 
		    if(vlength > 3200 && vlength <= 3600){
		    	vlength = vlength + 3000;
		    }
		    avg = vlength / avg;  //计算出视频循环播放的个数
		    
		    if(avg%2!=0){
		    	avg = avg + 1;  //奇偶造型
		    }
		    
		    System.out.println("房间"+roomID+"视频个数："+videoCount+"视频总长度："+sumVlength+"随机生成时间为："+vlength+"播放个数："+avg);
		    
			sql = "truncate table BJLPlayList"+roomID;
			ps6 = con.prepareStatement(sql);
			ps6.execute();
			System.out.println("清除播放列表数据成功");
			
			sql = "select hgName,videoType,roomType from BJLVideo where roomType= "+roomID+" group by hgName,videoType,roomType ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			String []s = null;
			String []d = null;
			int i = 0;
			int j = 0;
			String m = "";	
			int k = 0;
			int count = 0;
			int xh = 0;
			while (rs.next()) {
				k++;
				System.out.println("m:"+m+","+rs.getString("hgName"));
				if( "".equals(m) || rs.getString("hgName").equals(m)){
				sql="select count(*) from BJLVideo where videotype ="+rs.getInt("videoType") +" and hgName ='"+rs.getString("hgName")+"' and roomType=" + roomID ;
				System.out.println(sql);
				ps1 = con.prepareStatement(sql);
				rs1 = ps1.executeQuery();
				if(rs1.next()){
					count = rs1.getInt(1);
					//count = count - 1;
				}
				System.out.println("荷官名称："+rs.getString("hgName")+" 视频类型："+rs.getInt("videoType")+" 数量为:"+count);
				
				if(rs.getInt("videoType") == 1){
					sql = "select hgName,videoName,videotype,roomType from BJLVideo where videotype ="+rs.getInt("videoType") +"and hgName ='"+rs.getString("hgName") +"' and roomType="+roomID+" group by hgName,videoName,videotype,roomType";
					ps2 = con.prepareStatement(sql);
					rs2 = ps2.executeQuery();
					s = new String[count];
					while(rs2.next()){
						//System.out.println("s+++videoName="+rs2.getString("videoName"));
						s[i]= rs2.getString("videoName");
						i++;
					}
				}else if(rs.getInt("videoType") == 0){
					sql = "select hgName,videoName,videotype from BJLVideo where videotype ="+rs.getInt("videoType") +"and hgName ='"+rs.getString("hgName") +"' and roomType="+roomID+" group by hgName,videoName,videotype,roomType";
					ps3 = con.prepareStatement(sql);
					rs3 = ps3.executeQuery();
					d = new String[count];
					while(rs3.next()){
						//System.out.println("d+++videoName="+rs3.getString("videoName"));
						d[j]= rs3.getString("videoName");
						j++;
					}
				}
				if(null != s && null != d){
					sss = RanNumber.sortArray(s,d,avg);
					s = null;
					d = null;
					i = 0 ;
					j = 0 ;
					
					m = rs.getString("hgName");  //获得荷官名称
					//int num = 6;   //测试专用
					int num = random.nextInt(10)+100;//随即生成洗牌视频随机数
					if(num%2==0){
						num = num + 1;
					}
					System.out.println("随即开出洗牌视频生成随机数num="+num);
					System.out.println("循环插入数据---");
					for(int a = 0;a < sss.length; a++){
					//	System.out.println("================"+sss[0]);
						int vid = 0;
						sql = "select * from BJLVideo where videoName='"+sss[a]+"'"; //第一段是开牌视频
						ps4 = con.prepareStatement(sql);
						rs4 = ps4.executeQuery();
						if(rs4.next()){
							vid = rs4.getInt("id");
						}
						
						++xh;  //增加播放序号
						int iid = 0;
						
						//添加黄牌或红牌开牌、洗牌视频
						if(xh>num){
						if(xh%num==0){
							sql = "select top(1) * from BJLVideo where hgName='"+m+"' and videoType=3 and roomType="+roomID;
							ps12 = con.prepareStatement(sql);
							rs12 = ps12.executeQuery();
							if(rs12.next()){
								iid = rs12.getInt("id");
								}
							sql = "insert into BJLPlayList"+roomID+" (Vid,state,xh,createTime) values(?,0,?,getDate())";
							ps13 = con.prepareStatement(sql);
							ps13.setInt(1, iid);
							ps13.setInt(2, xh);
							ps13.execute();
							//++xh;
							}
						}
						
						sql = "insert into BJLPlayList"+roomID+" (Vid,state,xh,createTime) values(?,0,?,getDate())";
						ps5 = con.prepareStatement(sql);
						ps5.setInt(1, vid);
						ps5.setInt(2, xh);
						ps5.execute();
						
					}
				}
				 
				}else{    //进入换荷官人else判断中
					//----------------------------
					int id = 0;
					
					//添加换人
					System.out.println("添加换人--");
					sql = "select top(1) * from BJLVideo where hgName='"+m+"' and videoType=2 and roomType="+roomID;	
					ps7 = con.prepareStatement(sql);
					rs7 = ps7.executeQuery();
					if(rs7.next()){
						id = rs7.getInt("id");
					}
					
					++xh;
					sql = "insert into BJLPlayList"+roomID+" (Vid,state,xh,createTime) values(?,0,?,getDate())";
					ps8 = con.prepareStatement(sql);
					ps8.setInt(1, id);
					ps8.setInt(2, xh);
					ps8.execute();
					
					
					//---------------------------
					sql="select count(*) from BJLVideo where videotype ="+rs.getInt("videoType") +"and hgName ='"+rs.getString("hgName")+"' and roomType=" + roomID ;
					ps1 = con.prepareStatement(sql);
					rs1 = ps1.executeQuery();
					if(rs1.next()){
						count = rs1.getInt(1);
					}
					
					if(rs.getInt("videoType") == 1){
						sql = "select hgName,videoName,videotype,roomType from BJLVideo where videotype ="+rs.getInt("videoType") +"and hgName ='"+rs.getString("hgName") +"' and roomType="+roomID+" group by hgName,videoName,videotype,roomType";
						ps2 = con.prepareStatement(sql);
						rs2 = ps2.executeQuery();
						s = new String[count];
						while(rs2.next()){
							//System.out.println("s+++videoName="+rs2.getString("videoName"));
							s[i]= rs2.getString("videoName");
							i++;
						}
					}else if(rs.getInt("videoType")==0){
						sql = "select hgName,videoName,videotype,roomType from BJLVideo where videotype ="+rs.getInt("videoType") +"and hgName ='"+rs.getString("hgName") +"' and roomType="+roomID+" group by hgName,videoName,videotype,roomType";
						ps3 = con.prepareStatement(sql);
						rs3 = ps3.executeQuery();
						d = new String[count];
						while(rs3.next()){
							//System.out.println("d+++videoName="+rs3.getString("videoName"));
							d[j]= rs3.getString("videoName");
							j++;
						}
						
					}
					if(null != s && null != d){
						sss = RanNumber.sortArray(s,d,avg);
						s = null;
						d = null;
						i = 0 ;
						j = 0 ;
						System.out.println("<<<<<<<<<<<<"+Arrays.toString(sss));
						}
					m = "";
					}
				}
			}
			//}
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
	public List<VideoPlayListDTO> ListByPage(String where, int pageindex, int pageSize,int roomID) {
		List<VideoPlayListDTO> list = null;
		Connection con = null;
		CallableStatement toesUp = null;
		ResultSet rs = null;
		String view = "BJLVideoListView_22081";
		if(roomID==1){
			view = "BJLVideoListView_22081";
		}
		if(roomID==2){
			view = "BJLVideoListView_22082";
		}
		if(roomID==3){
			view = "BJLVideoListView_22083";
		}
		if(roomID==4){
			view = "BJLVideoListView_22084";
		}
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
			toesUp = con
					.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }");
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
				dto.setResult(rs.getString("result"));
				dto.setState(rs.getInt("state"));
				dto.setPlayID(rs.getInt("id"));
				dto.setVideolength(rs.getInt("videolength"));
				dto.setXh(rs.getInt("xh"));
				dto.setCreateTime(rs.getString("createTime"));

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
			String sql = "delete from BJLPlayList1 where id=" + id;
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
			String sql = "update BJLPlayList1 set state = 0 , xh = ? ,createTime = getdate() where id= ?";
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
			String sql = "update BJLPlayList1 (vid,state,xh)values(?,?,?)";
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
			table = "BJLPlayList1";
		}
		else if(roomID==2){
			table = "BJLPlayList2";
		}
		else if(roomID==3){
			table = "BJLPlayList3";
		}
		else if(roomID==4){
			table = "BJLPlayList4";
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
			String sql = "SELECT COUNT(*) FROM BJLPlayList1 WHERE (select xh from BJLPlayList1 where state=2)>=xh";
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

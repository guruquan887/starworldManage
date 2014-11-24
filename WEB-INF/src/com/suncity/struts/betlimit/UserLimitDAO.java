package com.suncity.struts.betlimit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;


public class UserLimitDAO{
	private int pageCount;
	private int recordCount;
	private DataSource ds;

	public UserLimitDAO(DataSource ds) {
		this.ds = ds;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}

	public List<UserLimitDTO> select(){
		List<UserLimitDTO> list = new ArrayList<UserLimitDTO>();
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from ChipType");
			rs = ps.executeQuery();
			while(rs.next()){
				UserLimitDTO dto  = new UserLimitDTO();
				dto.setChipID(rs.getInt("chipID"));
				dto.setChipValue(rs.getInt("chipValue"));
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
	
	public List<UserLimitDTO> GetRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<UserLimitDTO> list = new ArrayList<UserLimitDTO>();
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
			toesUp.setString(1, "UserLimit");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" asc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				UserLimitDTO dto = new UserLimitDTO();
				dto.setId(rs.getInt("ID"));
				dto.setLimit_Down(rs.getInt("limit_Down"));
				dto.setLimit_Up(rs.getInt("limit_Up"));
				dto.setChip_1(rs.getInt("chip_1"));
				dto.setChip_2(rs.getInt("chip_2"));
				dto.setChip_3(rs.getInt("chip_3"));
				dto.setChip_4(rs.getInt("chip_4"));
				dto.setChip_5(rs.getInt("chip_5"));
				dto.setChip(dto.getChip_1()+","+dto.getChip_2()+","+dto.getChip_3()+","+dto.getChip_4()+","+dto.getChip_5());
				
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
	
	public String add(UserLimitDTO dto){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			sql = "insert into UserLimit(limit_Down,limit_Up,Chip_1,Chip_2,Chip_3,Chip_4,Chip_5) values(?,?,?,?,?,?,?)";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getLimit_Down());
			ps.setInt(2, dto.getLimit_Up());
			ps.setInt(3, dto.getChip_1());
			ps.setInt(4, dto.getChip_2());
			ps.setInt(5, dto.getChip_3());
			ps.setInt(6, dto.getChip_4());
			ps.setInt(7, dto.getChip_5());
			ps.execute();
			msg = "添加限红成功!";
			
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
		return msg;
	}
	
	public UserLimitDTO getById(int id) {
		UserLimitDTO dto = null;
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from UserLimit where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new UserLimitDTO();
			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setChip_1(rs.getInt("Chip_1"));
				dto.setChip_2(rs.getInt("Chip_2"));
				dto.setChip_3(rs.getInt("Chip_3"));
				dto.setChip_4(rs.getInt("Chip_4"));
				dto.setChip_5(rs.getInt("Chip_5"));
				dto.setLimit_Down(rs.getInt("limit_Down"));
				dto.setLimit_Up(rs.getInt("limit_Up"));
				
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
	
	public String update(UserLimitDTO dto){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			sql = "update UserLimit set limit_Down=?,limit_Up = ?,Chip_1=?,Chip_2=?,Chip_3=?,Chip_4=?,Chip_5=? where id = ?";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getLimit_Down());
			ps.setInt(2, dto.getLimit_Up());
			ps.setInt(3, dto.getChip_1());
			ps.setInt(4, dto.getChip_2());
			ps.setInt(5, dto.getChip_3());
			ps.setInt(6, dto.getChip_4());
			ps.setInt(7, dto.getChip_5());
			ps.setInt(8, dto.getId());
			ps.execute();
			msg = "修改限红成功!";
			
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
		return msg;
	}
	
	public List<UserLimitDTO> getRule(int start,int end,int[] id){
		List<UserLimitDTO> list = new ArrayList<UserLimitDTO>();
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql ="select * from(select ROW_NUMBER() Over(order by id) as rowId, * from " +
			"SunCityManage.dbo.UserLimit )a where rowId between "+start+" and "+end;
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				UserLimitDTO dto  = new UserLimitDTO();
				int temp = rs.getInt("id");
				for(int i = 0; i < id.length; i++){
					if(temp==id[i]){
						dto.setChecked(1);//选中
					}
				}
				dto.setChipID(temp);
				dto.setId(rs.getInt("id"));
				dto.setLimit_Down(rs.getInt("limit_Down"));
				dto.setLimit_Up(rs.getInt("limit_Up"));
				dto.setChip_1(rs.getInt("chip_1"));
				dto.setChip_2(rs.getInt("chip_2"));
				dto.setChip_3(rs.getInt("chip_3"));
				dto.setChip_4(rs.getInt("chip_4"));
				dto.setChip_5(rs.getInt("chip_5"));
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
	
	public int[] getChecked(int userID){
		int[] id = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql ="select a.userID,a.limitID,b.accounts from SunCityManage.dbo.limitRelation a left outer join " +
					"QPGameUserDB.dbo.AccountsInfo b on a.userID = b.userID where a.userID= "+userID;
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				String limitID = rs.getString("limitID");
				id = getValue(limitID);
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
		return id;
	}
	
	public static int[] getValue(String str){
		int count = 1;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ',' ) {
				count ++ ;
			}
		}
		int[] in = new int[count];
		for(int i = 0; i < in.length; i++){
			if(str.length()>0 && str.indexOf(",")< 0){
				//System.out.println("----"+str+")"+str.indexOf(","));
				in[i] = Integer.parseInt(str);
			}
			if(str.indexOf(",") > 0){
				in[i] = Integer.parseInt(str.substring(0,str.indexOf(",")));
				str = str.substring(str.indexOf(",")+1,str.length());
			}
		}
		System.out.println("-----in:"+Arrays.toString(in));
		return in;
	}
	
}

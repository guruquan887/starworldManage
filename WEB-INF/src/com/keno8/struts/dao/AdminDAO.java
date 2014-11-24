package com.keno8.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.dto.AdminDTO;


public class AdminDAO {
	private DataSource ds;

	private static final String SQL_CHECKUSER = "select * from admin,role where userName=? and password=? and admin.roleId=role.Id";
	private static final String SQL_select = "select * from admin";
	private static final String SQL_AdminRoleView_select = "select * from adminRoleView";
	private static final String SQL_ADD = "insert into admin(userName,password,realName,phone,address,roleId,parent,parents) values(?,?,?,?,?,?,?,?)";
	private static final String SQL_DEL = "delete from admin where id=?";
	private static final String SQL_UPDATE = "update admin set password=?,phone=?,realName=?,address=?,roleId=? where id=?";
	
	
	
	public AdminDAO(DataSource ds) {
		super();
		this.ds = ds;
	}
	public String bdMachine(String userName){
		String r="机器码绑定失败！";
		Statement st=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			st=con.createStatement();
			String sql="update admin set machineserial=loginMachineserial where userName='"+userName+"'";
			System.out.println(sql);
			st.execute(sql);
			r="机器码绑定成功！";
			if(rs!=null){
				rs.close();rs=null;
			}
			if(st!=null){
				st.close();st=null;
			}
			if(con!=null){
				con.close();con=null;
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return r;
	}
	public String qxMachine(String userName){
		String r="取消机器码失败！";
		Statement st=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			st=con.createStatement();
			String sql="update admin set machineserial='' where userName='"+userName+"'";
			System.out.println(sql);
			st.execute(sql);
			r="取消机器码成功！";
			if(rs!=null){
				rs.close();rs=null;
			}
			if(st!=null){
				st.close();st=null;
			}
			if(con!=null){
				con.close();con=null;
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return r;
	}
	public AdminDTO checkUser(String userName,String password,String machineSerial){
		AdminDTO dto = null;
		Statement stmt=null;
		 Connection con=null;
		 ResultSet rs=null;
		 PreparedStatement ps=null;
		try {
			con = ds.getConnection();
			stmt=con.createStatement();
			ps = con.prepareStatement(SQL_CHECKUSER);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				dto =  new AdminDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserName(rs.getString("userName"));
				dto.setPassword(rs.getString("password"));
				dto.setRoleId(rs.getInt("roleId"));
				dto.setRoleName(rs.getString("roleName"));
				dto.setParent(rs.getInt("parent"));
				dto.setParents(rs.getString("parents"));
				dto.setMachineserial(rs.getString("machineserial"));
				//dto.setLoginMachineserial(rs.getString("loginMachineserial"));
				if("".equals(dto.getMachineserial())){
					//没有限制机器码
					String sql="update admin set loginMachineserial='"+machineSerial+"' " +
							"where id="+dto.getId();
					System.out.println(sql);
					stmt.execute(sql);
					
				}
				else{
					if(machineSerial.equals(dto.getMachineserial())){
						//机器码符合邦定记录
						String sql="update admin set loginMachineserial='"+machineSerial+"' " +
								"where id="+dto.getId();
						System.out.println(sql);
						stmt.execute(sql);
					}
					else{
						System.out.println("机器码与绑定数据不符!");
						dto=null;
						
					}
				}
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(stmt!=null){
				stmt.close();stmt=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();con=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs!=null){
				rs.close();rs=null;
			}
			if(stmt!=null){
				stmt.close();stmt=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
			if(con!=null){
				con.close();con=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public AdminDTO checkUserName(String userName,String password){
		AdminDTO dto = null;
		Connection con=null;
		 ResultSet rs=null;;
		 PreparedStatement ps=null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_CHECKUSER);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				dto =  new AdminDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserName(rs.getString("userName"));
				dto.setPassword(rs.getString("password"));
				dto.setRoleId(rs.getInt("roleId"));
				dto.setRoleName(rs.getString("roleName"));
				dto.setParent(rs.getInt("parent"));
				dto.setParents(rs.getString("parents"));
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	public Boolean checkExist(String userName){
		Boolean r=false;
		Statement st=null;
		Connection con=null;
		ResultSet rs=null;;
		try {
			con=ds.getConnection();
			st = con.createStatement();
			String sql="select * from admin where userName='"+userName+"'";
			
			rs = st.executeQuery(sql);
			if(rs.next()){
				r=true;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(st!=null){
				st.close();st=null;
			}
			if(con!=null){
				con.close();con=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return r;
	}
	
	
	public List<AdminDTO> findAll(String username){
		AdminDTO dto = null;
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs=null;
		List<AdminDTO> list = null;
		String sql = "select * from adminRoleView";
		try {
			if(!"admin".equals(username)){
				sql = "select * from adminRoleView where username<>'admin'";
			}
			con=ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			list=new ArrayList<AdminDTO>();
			while(rs.next()){
				dto = new AdminDTO();
				dto.setId(rs.getInt("Id"));
				dto.setUserName(rs.getString("userName"));
				dto.setPhone(rs.getString("phone"));
				dto.setLoginIP(rs.getString("LoginIP"));
				dto.setLoginTime(rs.getString("LoginTime"));
				dto.setAddress(rs.getString("address"));
				dto.setRealName(rs.getString("realName"));
				dto.setRoleName(rs.getString("roleName"));
				dto.setRoleId(rs.getInt("roleId"));
				dto.setMachineserial(rs.getString("machineserial"));
				dto.setLoginMachineserial(rs.getString("loginMachineserial"));
				list.add(dto);
			}
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
public void add(AdminDTO dto,int parent,String parents){
	PreparedStatement ps=null;
	Connection con=null;
	ResultSet rs=null;		
		try {
			con=ds.getConnection();
			ps = con.prepareStatement(SQL_ADD);
			//userName,password,realName,phone,address,roleId,parent,parents
			ps.setString(1, dto.getUserName());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getRealName());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getAddress());
			ps.setInt(6, dto.getRoleId());
			ps.setInt(7, parent);
			ps.setString(8, parents);
			ps.execute();
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
	}
	
	public void delete(int id){
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs=null;	
		try {
			con=ds.getConnection();
			ps = con.prepareStatement(SQL_DEL);
			ps.setInt(1, id);
			System.out.println("delete successful!!");
			ps.execute();
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
	}
	
	public AdminDTO getById(int id){
		AdminDTO dto = null;
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs=null;	
		try {
			con=ds.getConnection();
			ps = con.prepareStatement("select * from admin where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new AdminDTO();
			if(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setUserName(rs.getString("userName"));
				System.out.println("打印出的用户名为="+dto.getUserName());
				dto.setPassword(rs.getString("password"));
				dto.setLoginTime(rs.getString("LoginTime"));
				dto.setLoginIP(rs.getString("loginIP"));
				dto.setRealName(rs.getString("realName"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
				dto.setRoleId(rs.getInt("roleId"));
			}
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	
	public Boolean update(AdminDTO dto){
		Boolean r=false;
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs=null;	
		try {
			//"update admin set password=?,phone=?,realName=?,address=?,roleId=? where id=?";
			con=ds.getConnection();
			ps = con.prepareStatement(SQL_UPDATE);
			ps.setString(1, dto.getPassword());
			ps.setString(2, dto.getPhone());
			ps.setString(3, dto.getRealName());
			ps.setString(4, dto.getAddress());
			ps.setInt(5, dto.getRoleId());
			ps.setInt(6, dto.getId());
			ps.execute();
			r=true;
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
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return r;
	}

}

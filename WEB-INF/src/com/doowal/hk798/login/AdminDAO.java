package com.doowal.hk798.login;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.sql.DataSource;

import com.doowal.hk798.gameUser.EncryptionMD5;

public class AdminDAO {
	private DataSource ds;
	private static final String SQL_CHECKUSER = "select * from Base_Users,Base_Roles where userName=? and password=? and Base_Users.roleId=Base_Roles.roleId";
	private static final String SQL_select = "select * from Base_Users";
	private static final String SQL_AdminRoleView_selectCSADMIN = "select * from adminRoleView";
	//private static final String SQL_AdminRoleView_selectADMIN = "select * from adminRoleView";
	private static final String SQL_ADD = "insert into Base_Users(userName,password,roleId) values(?,?,?)";
	private static final String SQL_DEL = "delete from Base_Users where userID=?";
	private static final String SQL_UPDATE = "update Base_Users set password=? where userID=?";

	public AdminDAO(DataSource ds) {
		this.ds = ds;
	}
	public AdminDTO checkUser(String userName,String password){
		AdminDTO dto = null;
		Connection con = null;
		ResultSet rs =null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_CHECKUSER);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				dto =  new AdminDTO();
				dto.setId(rs.getInt("userID"));
				dto.setUserName(rs.getString("userName"));
				dto.setRoleId(rs.getInt("roleId"));
				dto.setRoleName(rs.getString("roleName"));
				//dto.setParent(rs.getInt("parent"));
				//dto.setParents(rs.getString("parents"));
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
	public Boolean checkExist(String userName){
		Boolean r=false;
		Connection con = null;
		ResultSet rs =null;
		Statement st = null;
		
		try {
			con = ds.getConnection();
		    st = con.createStatement();
			String sql="select * from Base_Users where userName='"+userName+"'";
			
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
			try{
				if(rs!=null){
					rs.close();rs=null;
				}
				if(st!=null){
					st.close();st=null;
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
	
	
	public List<AdminDTO> findAll(int roleId,int id){
		AdminDTO dto = null;
		Connection con = null;
		ResultSet rs =null;;
		PreparedStatement ps = null;
		String SQL_AdminRoleView_selectKefu = "select * from adminRoleView where userID="+id;
	    String SQL_AdminRoleView_selectADMIN = "select * from adminRoleView where roleId>="+roleId;
		List list = new ArrayList();
		try {
			con = ds.getConnection();
			if(roleId==1){
				ps = con.prepareStatement(SQL_AdminRoleView_selectCSADMIN);
			}
			else if(roleId==2){
				ps = con.prepareStatement(SQL_AdminRoleView_selectADMIN);
			}
			else{
				ps = con.prepareStatement(SQL_AdminRoleView_selectKefu);
			}
			rs = ps.executeQuery();
			while(rs.next()){
				dto = new AdminDTO();
				dto.setId(rs.getInt("userID"));
				dto.setUserName(rs.getString("userName"));
				dto.setLoginIP(rs.getString("LastLoginIP"));
				if(rs.getString("LastLogintime")!=null){
					dto.setLoginTime(rs.getString("LastLogintime").substring(0, 19));
				}
				dto.setRoleName(rs.getString("roleName"));
				dto.setRoleId(rs.getInt("roleId"));
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
	
public void add(AdminDTO dto){
		
		Connection con = null;
		PreparedStatement ps = null;
	
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_ADD);
			ps.setString(1, dto.getUserName());
			ps.setString(2, EncryptionMD5.encryption_MD5(dto.getPassword()).toUpperCase());
			ps.setInt(3, dto.getRoleId());
		
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
	
	public void delete(int id){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_DEL);
			ps.setInt(1, id);
			System.out.println("delete successful!!");
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
	
	public AdminDTO getById(int id){
		AdminDTO dto = null;
		Connection con = null;
		ResultSet rs =null;;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from Base_Users where userID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			dto = new AdminDTO();
			if(rs.next()){
				dto.setId(rs.getInt("userID"));
				dto.setUserName(rs.getString("userName"));
				dto.setPassword(rs.getString("password"));
				dto.setLoginTime(rs.getString("LastLogintime"));
				dto.setLoginIP(rs.getString("LastLoginIP"));
/*				dto.setRealName(rs.getString("realName"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
*/				dto.setRoleId(rs.getInt("roleId"));
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
	
	public Boolean update(AdminDTO dto){
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		String password = "";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SQL_UPDATE);
			password = EncryptionMD5.encryption_MD5(dto.getPassword().trim());
			ps.setString(1, password.toUpperCase());
			ps.setInt(2, dto.getId());
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
		  		  } catch (SQLException e1) {
		  					e1.printStackTrace();
		  			 }
		}
		return r;
	}
	
	public Boolean updateTime(String ip,String username){
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update Base_Users set LastLoginIP=?,LastLogintime=getDate() where UserName=?");
			ps.setString(1,ip);
			ps.setString(2,username);
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
	
	
	public String bangdingIP(String userID,String ip){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		String msg = "";
		int count = 0;
		String sql = "select count(*) from SafeBunding where userID="+userID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			System.out.println("count:"+count);
			if(count==0){
				sql = "insert into SafeBunding(userID,bundingIP) values(?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, userID);
				ps.setString(2, ip);
			}
			else{
				sql = "update SafeBunding set bundingIP = ? where userID = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ip);
				ps.setString(2, userID);
			}
			System.out.println("insert into successful!!");
			ps.execute();
			msg = "绑定成功！";
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
	
	public String bangdingMachine(String userID,String machine){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		String msg = "";
		int count = 0;
		String sql = "select count(*) from SafeBunding where userID="+userID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			System.out.println("count:"+count);
			if(count==0){
				sql = "insert into SafeBunding(userID,bundingMachine) values(?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, userID);
				ps.setString(2, machine);
			}
			else{
				sql = "update SafeBunding set bundingMachine = ? where userID = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, machine);
				ps.setString(2, userID);
			}
			System.out.println("insert into successful!!");
			ps.execute();
			msg = "绑定成功！";
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
	
	public String qxbangdingIP(String userID){
		Connection con = null;
		PreparedStatement ps = null;
		String msg = "";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update SafeBunding set bundingIP = ? where userID = ?");
			ps.setString(1, "");
			ps.setString(2, userID);
			System.out.println("quxiao successful!!");
			ps.execute();
			msg = "取消绑定成功！";
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
	
	public String qxbangdingMachine(String userID){
		Connection con = null;
		PreparedStatement ps = null;
		String msg = "";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update SafeBunding set bundingMachine = ? where userID = ?");
			ps.setString(1, "");
			ps.setString(2, userID);
			System.out.println("quxiao successful!!");
			ps.execute();
			msg = "取消绑定成功！";
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
	
	public static String getMacAddr () {  
        try  
        {  
            Enumeration <NetworkInterface> em = NetworkInterface.getNetworkInterfaces ();  
            while (em.hasMoreElements ())  
            {  
                NetworkInterface nic = em.nextElement ();  
                System.out.println ("nic.getDisplayName ():" + nic.getDisplayName ());  
                System.out.println ("nic.getName ():" + nic.getName ());  
                byte[] b = nic.getHardwareAddress ();  
                if (b == null)  
                continue;  
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < b.length; i++)  
                {  
                	builder.append(byteHEX(b[i]));
                	builder.append("-");
                }  
                //builder.deleteCharAt(builder.length() - 1);
                return builder.toString();
            }  
        }  
        catch (SocketException e)  
        {  
            e.printStackTrace ();  
            System.exit (-1);  
        }  
        return null;  
    }
	
    public static String byteHEX (byte ib)  
    {  
        char[] Digit =  
        { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
        char[] ob = new char[2];  
        ob[0] = Digit[(ib >>> 4) & 0X0F];  
        ob[1] = Digit[ib & 0X0F];  
        String s = new String (ob); 
        System.out.println(s);
        return s;  
    } 
    
	public String getbangdingIP(String userID){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		String bundingip = "";
		String sql = "select bundingip from SafeBunding where userID="+userID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				bundingip = rs.getString(1);
			}
			System.out.println("bundingip:"+bundingip);
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
		return bundingip;
	}
	
	public String getbangdingMachine(String userID){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		String bundingMachine = "";
		String sql = "select bundingMachine from SafeBunding where userID="+userID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				bundingMachine = rs.getString(1);
			}
			System.out.println("bundingMachine:"+bundingMachine);
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
		return bundingMachine;
	}


}

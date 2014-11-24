package com.doowal.hk798.dwr;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

public class RoleAuthorityDb {
	private DataSource ds;

	public RoleAuthorityDb(DataSource ds) {
		this.ds = ds;
	}
	
	public String SetContantFirst(int fid,int userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String menuidStr = "";
		String[] menuidArr = null;
		String menuName = "";
		int id = 0;
		String r = "";
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select * from roleauthority where roleid="+userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				menuidStr += rs.getString("menuid")+"-";
			}
			menuidArr = menuidStr.split("-");
			sql = "select  *  from  menu where parentId="+fid;
			System.out.println("sql=" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("id");
				menuName = rs.getString("menuName");
				int exist = 0;
				for(int j=0;j<menuidArr.length;j++)
				{

					if(!"".equals(menuidArr[j]))
					{
						if(Integer.parseInt(menuidArr[j])==id)
						{
							exist = 1;
						}
					}
					
				}

				{
					if(exist==1)
					{
						r+="<div class=\"tdright_new\" ><a href=\"#here\" onClick=\"SetContantSecond("+id+",0);\">"+rs.getString("menuName")
						+"</a></div><div class=\"tdright_new\">" +
						"<input type=\"checkbox\" checked name=\"menu"+id+"\" id=\"menu"+id+"\"  onclick=\"changeSelectState("+id+",'"+menuName+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level2-"+id+"\" class=\"lvl2\">" +
						"</div>";
					}else
					{
						r+="<div class=\"tdright\" ><a href=\"#here\" onClick=\"SetContantSecond("+id+",0);\">"+rs.getString("menuName")
						+"</a></div><div class=\"tdright\">" +
						"<input type=\"checkbox\" name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+menuName+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level2-"+id+"\" class=\"lvl2\">" +
						"</div>";
					}
				}
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
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// TODO: handle exception
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
				e1.printStackTrace();
			}
		}
		return r;
	}    
	
	
	public String SetContantSecond(int fid,int userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String menuidStr = "";
		String[] menuidArr = null;
		String menuName = "";
		int id = 0;
		String r = "";
		int i = 0;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select * from roleauthority where roleid="+userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				menuidStr += rs.getString("menuid")+"-";
			}
			menuidArr = menuidStr.split("-");
			sql = "select  *  from  menu where parentId="+fid;
			System.out.println("sql=" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				i++;
				id = rs.getInt("id");
				menuName = rs.getString("menuName");
				int exist = 0;
				for(int j=0;j<menuidArr.length;j++)
				{
					if(!"".equals(menuidArr[j]))
					{
						if(Integer.parseInt(menuidArr[j])==id)
						{
							exist = 1;
						}
					}
				}
				/*int comfunc = 0;
				comfunc = rs.getInt("comfunc");
				if(comfunc==1)
				{
					r+="<div class=\"Left2\" ><a href=\"#here\" onClick=\"SetContantThird("+rs.getInt("id")
					+",0);\">"+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
					"<input type=\"checkbox\" disabled checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+menuName+"');\" value=\""+id+"\"></div>" +
					"<div id=\"level3-"+id+"\" class=\"lvl3\">" +
					"</div>";
				}else*/
				{
					if(exist==1)
					{
						//r+="<div class=\"Left2\" ><a href=\"#here\" onClick=\"SetContantThird("+rs.getInt("id")
						//+",0);\">"+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
						r+="<div class=\"tdright_new\" ><a href=\"#here\">"
							+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
							"<input type=\"checkbox\" checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+menuName+"');\" value=\""+id+"\"></div>" ;
							//"<div id=\"level3-"+id+"\" class=\"lvl3\">" +
							//"</div>";
					}else
					{
						//r+="<div class=\"Left2\" ><a href=\"#here\" onClick=\"SetContantThird("+rs.getInt("id")
						//+",0);\">"+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
						r+="<div class=\"tdright\" ><a href=\"#here\">"
							+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
							"<input type=\"checkbox\" name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+menuName+"');\" value=\""+id+"\"></div>" ;
							//"<div id=\"level3-"+id+"\" class=\"lvl3\">" +
							//"</div>";
					}
				}
				
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
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// TODO: handle exception
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
				e1.printStackTrace();
			}
		}
		return r;
	}  
	
	
	public String ReleaseAuthority(int id,int roleid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String[] idGlobalArr = null;
		String[] idLocalArr = null;
		String idGlobalStr = String.valueOf(id)+"-";
		String idLocalStr = "";
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select  *  from  menu where parentId="+id;
			System.out.println("sql=" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				idGlobalStr += rs.getString("id")+"-";
				idLocalStr += rs.getString("id")+"-";
			}
			idLocalArr = idLocalStr.split("-");		
			
			for(int i=0;i<6;i++)
			{
				idLocalStr = "";
				for(int j=0;j<idLocalArr.length;j++)
				{
					if(!"".equals(idLocalArr[j]))
					{
						sql = "select  *  from  menu where parentId="+idLocalArr[j];
						System.out.println("sql=" + sql);
						rs = st.executeQuery(sql);
						while (rs.next()) {
							idGlobalStr += rs.getString("id")+"-";
							idLocalStr += rs.getString("id")+"-";
						}
					}
				}
				idLocalArr = null;
				idLocalArr = idLocalStr.split("-");
			}
			idGlobalArr = idGlobalStr.split("-");
			for (int i=0;i<idGlobalArr.length;i++)
			{
				sql = "delete  from  roleauthority where menuid='"+idGlobalArr[i]+"' and roleid="+roleid;
				st.execute(sql);
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
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// TODO: handle exception
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
				e1.printStackTrace();
			}
		}
		return idGlobalStr;
	}   
	
	public String GetAuthority(int id,int roleid) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		
		String fid = "";
		String fidStr = "";
		String[] arrA = null;
		try {
			con = ds.getConnection();
			stat = con.createStatement();
			String sql = "";
			fidStr += id+"-";
			sql="select * from menu where id="+id;
			rs=stat.executeQuery(sql);
			if(rs.next()){
				fid = rs.getString("parentId");
				fidStr += fid+"-";
			}
			for(int j=0;j<6;j++)
			{
				if(!"0".equals("fid"))
				{
					sql="select * from menu where id="+fid;
					rs=stat.executeQuery(sql);
					if(rs.next()){
						fid = rs.getString("parentId");
						fidStr += fid+"-";
					}
				}
			}
			arrA = fidStr.split("-");
			for(int j=0;j<arrA.length-1;j++)
			{
				int count = 0;
				sql = "select count(*) as num from roleauthority where menuid='"+arrA[j]+"' and roleid="+roleid;
				rs = stat.executeQuery(sql);
				if(rs.next())
				{
					count = rs.getInt("num");
				}
				if(count==0)
				{
					sql = "insert into roleauthority(menuid,roleid)values('"+arrA[j]+"',"+roleid+")";
					stat.execute(sql);
				}
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}

			if (stat != null) {
				stat.close();
				stat = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// TODO: handle exception
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}
		return fidStr;
	}   
	
}

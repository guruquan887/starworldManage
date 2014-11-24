package com.doowal.hk798.dwr;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import java.util.*;
public class AuthorityDb {
	private DataSource ds;

	public AuthorityDb(DataSource ds) {
		this.ds = ds;
	}
	public AuthorityDb() {
		super();
	}
	

	public String[] run( int userid) {
		String menuidStr = "";
		String[] menuidArr = null;
		ResultSet rs = null;
		
		Connection con = null;
		Statement st = null; 
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select * from authority where userid=" + userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				menuidStr += rs.getString("menuid") + "-";
			}
			menuidArr = menuidStr.split("-");
			//sql = "select  *  from  menu where parentId=" + fid;
			//System.out.println("sql=" + sql);
			//rs = st.executeQuery(sql);
			//while(rs.next()){
				//System.out.println("AAAAAAAAAAA"+rs.getInt("id"));
			//}
			this.close(con, st, rs);
		} catch (Exception e) {
			e.printStackTrace();
			this.close(con, st, rs);
		}
		return menuidArr;
	}
	
	public String[] Agentrun( int userid) {
		String menuidStr = "";
		String[] menuidArr = null;
		ResultSet rs = null;
		
		Connection con = null;
		Statement st = null; 
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select * from AgentAuthority where userid=" + userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				menuidStr += rs.getString("menuid") + "-";
			}
			menuidArr = menuidStr.split("-");
			//sql = "select  *  from  menu where parentId=" + fid;
			//System.out.println("sql=" + sql);
			//rs = st.executeQuery(sql);
			//while(rs.next()){
				//System.out.println("AAAAAAAAAAA"+rs.getInt("id"));
			//}
			this.close(con, st, rs);
		} catch (Exception e) {
			e.printStackTrace();
			this.close(con, st, rs);
		}
		return menuidArr;
	}
   
	public List<MenuDTO> getRootMenu(int fid){
		List<MenuDTO> root=new ArrayList<MenuDTO>();
		Connection con = null;
		Statement st = null; 
		ResultSet rs = null;
		String sql = "select  *  from  menu where parentId=" + fid;
		try {
			con = ds.getConnection();
			st = con.createStatement();			
			System.out.println("sql=" + sql);
			rs = st.executeQuery(sql);
			while(rs.next()){	
				MenuDTO dt=new MenuDTO();
				dt.setId(rs.getInt("id"));
				dt.setMenuName(rs.getString("menuName"));
				root.add(dt);
			}
			this.close(con, st, rs);
		} catch (SQLException e) {
			this.close(con, st, rs);
			e.printStackTrace();
		}
		
		return root;
	}
	
	public List<MenuDTO> getAgentRootMenu(int fid){
		List<MenuDTO> root=new ArrayList<MenuDTO>();
		Connection con = null;
		Statement st = null; 
		ResultSet rs = null;
		String sql = "select  *  from  AgentMenu where parentId=" + fid;
		try {
			con = ds.getConnection();
			st = con.createStatement();			
			System.out.println("sql=" + sql);
			rs = st.executeQuery(sql);
			while(rs.next()){	
				MenuDTO dt=new MenuDTO();
				dt.setId(rs.getInt("id"));
				dt.setMenuName(rs.getString("menuName"));
				root.add(dt);
			}
			this.close(con, st, rs);
		} catch (SQLException e) {
			this.close(con, st, rs);
			e.printStackTrace();
		}
		
		return root;
	}
	public String init(int fid, int userid) {
		StringBuffer bf = new StringBuffer("<form id=\"form1\" name=\"form1\" ><table id=\"tab1\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\" width=\"100%\" align=\"center\">");
		List<MenuDTO> root=null;		
		String[] menuidArr = null;	
		Connection con = null;
		Statement st = null; 
		ResultSet rs = null;
		String menuName;
		try {
			
			root=this.getRootMenu(fid);
			if(root.size()>0){
				for (MenuDTO m : root) {
					bf.append("<tr><td class=\"tdright_new\" style=\"width:120px;\">");
					bf.append("<a href=\"javascript:;\" onclick=\"changeQx('"+m.getId()+"')\" >");
					bf.append("反选</a>  ");
					bf.append(m.getMenuName()+":");
					bf.append("</td>");
					String sql = "select  *  from  menu where parentId=" + m.getId();
					bf.append("<td class=\"tdright\" id=\"td"+m.getId()+"\">");
					try {
						con = ds.getConnection();
						st = con.createStatement();			
						System.out.println("sql=" + sql);
						rs = st.executeQuery(sql);
						int i=0;
						while(rs.next()){
							i++;
							boolean exist = false;
							menuidArr=this.run(userid);
							menuName=rs.getString("menuName");
							int id=0;
							id=rs.getInt("id");
							for (int k = 0; k < menuidArr.length; k++) {
								if (!"".equals(menuidArr[k])) {
									if (Integer.parseInt(menuidArr[k]) ==id ) {
										exist = true;
									}
								}																							
								
							}
							
							if (exist) {
								//bf.append("<td class=\"tdright\">");
								bf.append("<input type=\"checkbox\" checked name=\"menu" + id
										+ "\" id=\"menu" + id
										+ "\"  onclick=\"changeSelectState(" + id + ",'"
										+ menuName + "');\" value=\"" + id + "\">");
								bf.append(menuName+"&nbsp;&nbsp;&nbsp;");
								//bf.append("</td>");
							}else{
								//bf.append("<td class=\"tdright\">");
								bf.append("<input type=\"checkbox\"  name=\"menu" + id
										+ "\" id=\"menu" + id
										+ "\"  onclick=\"changeSelectState(" + id + ",'"
										+ menuName + "');\" value=\"" + id + "\">");
								bf.append(menuName+"&nbsp;&nbsp;&nbsp;");
								//bf.append("</td>");
							}
							
						}
						this.close(con, st, rs);
					} catch (SQLException e) {
						this.close(con, st, rs);
						e.printStackTrace();
					}
					bf.append("</td>");
					bf.append("</tr>");
				}
			}
			bf.append("</table></form>");
			System.out.println(bf.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			this.close(con, st, rs);
		}
		return bf.toString();
	}
	
	public String Agentinit(int fid, int userid) {
		StringBuffer bf = new StringBuffer("<form id=\"form1\" name=\"form1\" ><table id=\"tab1\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\" width=\"100%\" align=\"center\">");
		List<MenuDTO> root=null;		
		String[] menuidArr = null;	
		Connection con = null;
		Statement st = null; 
		ResultSet rs = null;
		String menuName;
		try {
			
			root=this.getAgentRootMenu(fid);
			if(root.size()>0){
				for (MenuDTO m : root) {
					bf.append("<tr><td class=\"tdright_new\" style=\"width:120px;\">");
					bf.append("<a href=\"javascript:;\" onclick=\"changeQx('"+m.getId()+"')\" >");
					bf.append("反选</a>  ");
					bf.append(m.getMenuName()+":");
					bf.append("</td>");
					String sql = "select  *  from  AgentMenu where parentId=" + m.getId();
					bf.append("<td class=\"tdright\" id=\"td"+m.getId()+"\">");
					try {
						con = ds.getConnection();
						st = con.createStatement();			
						System.out.println("sql=" + sql);
						rs = st.executeQuery(sql);
						int i=0;
						while(rs.next()){
							i++;
							boolean exist = false;
							menuidArr=this.Agentrun(userid);
							menuName=rs.getString("menuName");
							int id=0;
							id=rs.getInt("id");
							for (int k = 0; k < menuidArr.length; k++) {
								if (!"".equals(menuidArr[k])) {
									if (Integer.parseInt(menuidArr[k]) ==id ) {
										exist = true;
									}
								}																							
								
							}
							
							if (exist) {
								//bf.append("<td class=\"tdright\">");
								bf.append("<input type=\"checkbox\" checked name=\"menu" + id
										+ "\" id=\"menu" + id
										+ "\"  onclick=\"changeSelectState(" + id + ",'"
										+ menuName + "');\" value=\"" + id + "\">");
								bf.append(menuName+"&nbsp;&nbsp;&nbsp;");
								//bf.append("</td>");
							}else{
								//bf.append("<td class=\"tdright\">");
								bf.append("<input type=\"checkbox\"  name=\"menu" + id
										+ "\" id=\"menu" + id
										+ "\"  onclick=\"changeSelectState(" + id + ",'"
										+ menuName + "');\" value=\"" + id + "\">");
								bf.append(menuName+"&nbsp;&nbsp;&nbsp;");
								//bf.append("</td>");
							}
							
						}
						this.close(con, st, rs);
					} catch (SQLException e) {
						this.close(con, st, rs);
						e.printStackTrace();
					}
					bf.append("</td>");
					bf.append("</tr>");
				}
			}
			bf.append("</table></form>");
			System.out.println(bf.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			this.close(con, st, rs);
		}
		return bf.toString();
	}

	public void close(Connection con, Statement st, ResultSet rs) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String SetContantFirst(int fid,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
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
			sql = "select * from authority where userid="+userid;
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
						r+="<div class=\"Left1\" ><a href=\"#here\" onClick=\"SetContantSecond("+id+",0);\">"+rs.getString("menuName")
						+"</a></div><div class=\"Right1\">" +
						"<input type=\"checkbox\" checked name=\"menu"+id+"\" id=\"menu"+id+"\"  onclick=\"changeSelectState("+id+",'"+menuName+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level2-"+id+"\" class=\"lvl2\">" +
						"</div>";
					}else
					{
						r+="<div class=\"Left1\" ><a href=\"#here\" onClick=\"SetContantSecond("+id+",0);\">"+rs.getString("menuName")
						+"</a></div><div class=\"Right1\">" +
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
		@SuppressWarnings("unused")
		boolean flag = false;
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
			sql = "select * from authority where userid="+userid;
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
				{
					if(exist==1)
					{
						//r+="<div class=\"Left2\" ><a href=\"#here\" onClick=\"SetContantThird("+rs.getInt("id")
						//+",0);\">"+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
						r+="<div class=\"Left2\" ><a href=\"#here\">"
							+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
							"<input type=\"checkbox\" checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+menuName+"');\" value=\""+id+"\"></div>" ;
							//"<div id=\"level3-"+id+"\" class=\"lvl3\">" +
							//"</div>";
					}else
					{
						//r+="<div class=\"Left2\" ><a href=\"#here\" onClick=\"SetContantThird("+rs.getInt("id")
						//+",0);\">"+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
						r+="<div class=\"Left2\" ><a href=\"#here\">"
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
	
	public String SetAgentContantSecond(int fid,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
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
			sql = "select * from AgentAuthority where userid="+userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				menuidStr += rs.getString("menuid")+"-";
			}
			menuidArr = menuidStr.split("-");
			sql = "select * from AgentMenu where parentId="+fid;
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
				{
					if(exist==1)
					{
						//r+="<div class=\"Left2\" ><a href=\"#here\" onClick=\"SetContantThird("+rs.getInt("id")
						//+",0);\">"+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
						r+="<div class=\"Left2\" ><a href=\"#here\">"
							+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
							"<input type=\"checkbox\" checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+menuName+"');\" value=\""+id+"\"></div>" ;
							//"<div id=\"level3-"+id+"\" class=\"lvl3\">" +
							//"</div>";
					}else
					{
						//r+="<div class=\"Left2\" ><a href=\"#here\" onClick=\"SetContantThird("+rs.getInt("id")
						//+",0);\">"+rs.getString("menuName")+"</a></div><div class=\"Right2\">" +
						r+="<div class=\"Left2\" ><a href=\"#here\">"
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
	
	public String SetContantThird(int fid,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String menuidStr = "";
		String[] menuidArr = null;
		String cdname = "";
		int id = 0;
		String r = "";
		int i = 0;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select * from authority where userid="+userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				menuidStr += rs.getString("menuid")+"-";
			}
			menuidArr = menuidStr.split("-");
			sql = "select  *  from  tb_menuphoto where fid="+fid;
			System.out.println("sql=" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				i++;
				id = rs.getInt("id");
				cdname = rs.getString("cdname");
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
				int comfunc = 0;
				comfunc = rs.getInt("comfunc");
				if(comfunc==1)
				{
					r+="<div class=\"Left3\" ><a href=\"#here\" onClick=\"SetContantFourth("+rs.getInt("id")
					+",0);\">"+rs.getString("cdname")+"</a></div><div class=\"Right3\">" +
					"<input type=\"checkbox\" disabled checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
					"<div id=\"level4-"+id+"\" class=\"lvl4\">" +
					"</div>";
				}else
				{
					if(exist==1)
					{
						r+="<div class=\"Left3\" ><a href=\"#here\" onClick=\"SetContantFourth("+rs.getInt("id")
						+",0);\">"+rs.getString("cdname")+"</a></div><div class=\"Right3\">" +
						"<input type=\"checkbox\" checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level4-"+id+"\" class=\"lvl4\">" +
						"</div>";
					}else
					{
						r+="<div class=\"Left3\" ><a href=\"#here\" onClick=\"SetContantFourth("+rs.getInt("id")
						+",0);\">"+rs.getString("cdname")+"</a></div><div class=\"Right3\">" +
						"<input type=\"checkbox\" name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level4-"+id+"\" class=\"lvl4\">" +
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
	
	public String SetContantFourth(int fid,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String menuidStr = "";
		String[] menuidArr = null;
		String cdname = "";
		int id = 0;
		String r = "";
		int i = 0;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select * from authority where userid="+userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				menuidStr += rs.getString("menuid")+"-";
			}
			menuidArr = menuidStr.split("-");
			sql = "select  *  from  tb_menuphoto where fid="+fid;
			System.out.println("sql=" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				i++;
				id = rs.getInt("id");
				cdname = rs.getString("cdname");
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
				int comfunc = 0;
				comfunc = rs.getInt("comfunc");
				if(comfunc==1)
				{
					r+="<div class=\"Left4\" ><a href=\"#here\" onClick=\"SetContantFifth("+rs.getInt("id")
					+",0);\">"+rs.getString("cdname")+"</a></div><div class=\"Right4\">" +
					"<input type=\"checkbox\" disabled checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
					"<div id=\"level5-"+id+"\" class=\"lvl5\">" +
					"</div>";
				}else
				{
					if(exist==1)
					{
						r+="<div class=\"Left4\" ><a href=\"#here\" onClick=\"SetContantFifth("+rs.getInt("id")
						+",0);\">"+rs.getString("cdname")+"</a></div><div class=\"Right4\">" +
						"<input type=\"checkbox\" checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level5-"+id+"\" class=\"lvl5\">" +
						"</div>";
					}else
					{
						r+="<div class=\"Left4\" ><a href=\"#here\" onClick=\"SetContantFifth("+rs.getInt("id")
						+",0);\">"+rs.getString("cdname")+"</a></div><div class=\"Right4\">" +
						"<input type=\"checkbox\" name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level5-"+id+"\" class=\"lvl5\">" +
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
	
	
	public String SetContantFifth(int fid,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String menuidStr = "";
		String[] menuidArr = null;
		String cdname = "";
		int id = 0;
		String r = "";
		int i = 0;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select * from authority where userid="+userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				menuidStr += rs.getString("menuid")+"-";
			}
			menuidArr = menuidStr.split("-");
			sql = "select  *  from  tb_menuphoto where fid="+fid;
			System.out.println("sql=" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				i++;
				id = rs.getInt("id");
				cdname = rs.getString("cdname");
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
				int comfunc = 0;
				comfunc = rs.getInt("comfunc");
				if(comfunc==1)
				{
					r+="<div class=\"Left5\" ><a href=\"#here\" >"+rs.getString("cdname")+"</a></div><div class=\"Right5\">" +
					"<input type=\"checkbox\" disabled checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
					"<div id=\"level6-"+id+"\" class=\"lvl6\">" +
					"</div>";
				}else
				{
					if(exist==1)
					{
						r+="<div class=\"Left5\" ><a href=\"#here\" >"+rs.getString("cdname")+"</a></div><div class=\"Right5\">" +
						"<input type=\"checkbox\" checked name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level6-"+id+"\" class=\"lvl6\">" +
						"</div>";
					}else
					{
						r+="<div class=\"Left5\" ><a href=\"#here\" >"+rs.getString("cdname")+"</a></div><div class=\"Right5\">" +
						"<input type=\"checkbox\" name=\"menu"+id+"\" id=\"menu"+id+"\" onclick=\"changeSelectState("+id+",'"+cdname+"');\" value=\""+id+"\"></div>" +
						"<div id=\"level6-"+id+"\" class=\"lvl6\">" +
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
	
	
	public String ReleaseAuthority(int id,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String[] idGlobalArr = null;
		String[] idLocalArr = null;
		String idGlobalStr = String.valueOf(id)+"-";
		String idLocalStr = "";
		@SuppressWarnings("unused")
		String r = "";
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
				sql = "delete  from  authority where menuid='"+idGlobalArr[i]+"' and userid="+userid;
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
	
	public String GetAuthority(int id,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		
		String fid = "";
		String fidStr = "";
		String[] arrA = null;
		@SuppressWarnings("unused")
		String[] arrB = new String[10];
		@SuppressWarnings("unused")
		String r = "";
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
					sql="select * from menu where id='"+fid+"'";
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
				sql = "select count(*) as num from authority where menuid='"+arrA[j]+"' and userid="+userid;
				rs = stat.executeQuery(sql);
				if(rs.next())
				{
					count = rs.getInt("num");
				}
				if(count==0)
				{
					sql = "insert into authority(menuid,userid)values('"+arrA[j]+"',"+userid+")";
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
	
	public String ReleaseAgentAuthority(int id,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String[] idGlobalArr = null;
		String[] idLocalArr = null;
		String idGlobalStr = String.valueOf(id)+"-";
		String idLocalStr = "";
		@SuppressWarnings("unused")
		String r = "";
		try {
			con = ds.getConnection();
			st = con.createStatement();
			String sql = "";
			sql = "select  *  from  AgentMenu where parentId="+id;
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
						sql = "select  *  from  AgentMenu where parentId="+idLocalArr[j];
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
				sql = "delete  from  AgentAuthority where menuid='"+idGlobalArr[i]+"' and userid="+userid;
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
	
	public String GetAgentAuthority(int id,int userid) {
		@SuppressWarnings("unused")
		boolean flag = false;
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		
		String fid = "";
		String fidStr = "";
		String[] arrA = null;
		@SuppressWarnings("unused")
		String[] arrB = new String[10];
		@SuppressWarnings("unused")
		String r = "";
		try {
			con = ds.getConnection();
			stat = con.createStatement();
			String sql = "";
			fidStr += id+"-";
			sql="select * from AgentMenu where id="+id;
			rs=stat.executeQuery(sql);
			if(rs.next()){
				fid = rs.getString("parentId");
				fidStr += fid+"-";
			}
			for(int j=0;j<6;j++)
			{
				if(!"0".equals("fid"))
				{
					sql="select * from AgentMenu where id='"+fid+"'";
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
				sql = "select count(*) as num from AgentAuthority where menuid='"+arrA[j]+"' and userid="+userid;
				rs = stat.executeQuery(sql);
				if(rs.next())
				{
					count = rs.getInt("num");
				}
				if(count==0)
				{
					sql = "insert into AgentAuthority(menuid,userid)values('"+arrA[j]+"',"+userid+")";
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

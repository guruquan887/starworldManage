package com.keno8.struts.action.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ClassDAO {
	private DataSource ds;
	
	public ClassDAO(DataSource ds) {
		this.ds = ds;
	}


	public List<ClassDTO> select(){
		List<ClassDTO> list = new ArrayList<ClassDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from class ");
			rs = ps.executeQuery();
			while(rs.next()){
				ClassDTO dto = new ClassDTO();
				dto.setClasscode(rs.getInt("id"));
				dto.setClassname(rs.getString("classname"));
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
	
	

}

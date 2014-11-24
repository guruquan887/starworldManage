package com.keno8.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.dto.AdminDTO;
import com.keno8.struts.dto.GradeCSDTO;
import com.keno8.struts.dto.NetCSDTO;

public class GradeCSDAO {
	private DataSource ds;
	
	public GradeCSDAO(DataSource ds) {
		this.ds = ds;
	}

	public List<GradeCSDTO> getAll() {
		GradeCSDTO dto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<GradeCSDTO> list = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from gradeExpregference");
			rs = ps.executeQuery();
			list = new ArrayList<GradeCSDTO>();
			while (rs.next()) {
				dto = new GradeCSDTO();
				dto.setId(rs.getInt("id"));
				dto.setGrade(rs.getInt("grade"));
				dto.setGradeExp(rs.getLong("gradeExp"));
				dto.setRebateLV(rs.getDouble("rebateLV"));
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
	
	public boolean update(GradeCSDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		Boolean r=false;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update gradeExpregference set gradeExp=?,rebateLV=? where grade=?");
			ps.setLong(1, dto.getGradeExp());
			ps.setDouble(2, dto.getRebateLV());
			ps.setInt(3, dto.getGrade());
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
	

}

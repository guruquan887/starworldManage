package com.keno8.struts.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.keno8.struts.dto.GradeExpregferenceDTO;


public class GradeExpregferenceDAO {

	private DataSource ds;
	
	public GradeExpregferenceDAO(DataSource ds) {
		super();
		this.ds = ds;
	}

	public ArrayList<GradeExpregferenceDTO> getGradeExpregferences(){
		ArrayList<GradeExpregferenceDTO> list = new ArrayList<GradeExpregferenceDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from gradeExpregference order by grade";
		try {
			conn = ds.getConnection();
			stmt=conn.createStatement();
			rs = stmt.executeQuery(sql);
			while( rs.next() ){
				GradeExpregferenceDTO dto = new GradeExpregferenceDTO();
				dto.setId(rs.getInt("id"));
				dto.setGrade(rs.getInt("grade"));
				dto.setGradeExp(rs.getDouble("gradeExp"));
				dto.setRebateLV(rs.getDouble("rebateLV"));
				list.add(dto);
			}
			
			if(rs!=null){
				rs.close();rs=null;
			}
			if(stmt!=null){
				stmt.close();stmt=null;
			}
			if(conn!=null){
				conn.close();conn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if(rs!=null)
				rs.close();
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return list;
	}
	
	public GradeExpregferenceDTO  getGradeExpregferenceByExp(ArrayList<GradeExpregferenceDTO> list, double exp ){
		GradeExpregferenceDTO dto = null;
		if(list!=null && list.size()>0){
			int n= 0;
			while(n<list.size()){
				if(exp/list.get(n).getGradeExp()<1){
					dto = list.get(n);
					break;
				} else{
					n++;
				}
			}
			if(n>=list.size()){
				dto = list.get(list.size()-1);
			}
		}
		if(dto==null){
			dto = new GradeExpregferenceDTO();
			dto.setId(0);
			dto.setGrade(1);
			dto.setGradeExp(100000);
			dto.setRebateLV(0.005);
		}
		return dto;
	}
}

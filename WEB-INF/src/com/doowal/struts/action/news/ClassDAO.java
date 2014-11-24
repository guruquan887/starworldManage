package com.doowal.struts.action.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;


public class ClassDAO {
	
	private DataSource ds;

	public ClassDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public List<ClassDTO> select(){
		Connection con = null;
		ResultSet rs =null;;
		PreparedStatement ps = null;
		List<ClassDTO> list = new ArrayList<ClassDTO>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from class where classcode in(1,2,3)");
			rs = ps.executeQuery();
			while(rs.next()){
				ClassDTO dto = new ClassDTO();
				dto.setClasscode(rs.getString("classcode"));
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
	
	public List<ClassDTO> select1(){
		Connection con = null;
		ResultSet rs =null;;
		PreparedStatement ps = null;
		List<ClassDTO> list = new ArrayList<ClassDTO>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from class where classcode in(5,6,7)");
			rs = ps.executeQuery();
			while(rs.next()){
				ClassDTO dto = new ClassDTO();
				dto.setClasscode(rs.getString("classcode"));
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
	
	public List<ClassDTO> select3(){
		List<ClassDTO> list = new ArrayList<ClassDTO>();
		Connection con = null;
		ResultSet rs =null;;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from class where classcode=13");
			rs = ps.executeQuery();
			while(rs.next()){
				ClassDTO dto = new ClassDTO();
				dto.setClasscode(rs.getString("classcode"));
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

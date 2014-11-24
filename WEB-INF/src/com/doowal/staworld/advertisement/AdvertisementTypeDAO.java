package com.doowal.staworld.advertisement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class AdvertisementTypeDAO {
	private DataSource ds;

	
	public AdvertisementTypeDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public List<AdvertisementTypeDTO> select(){
		List<AdvertisementTypeDTO> list = new ArrayList<AdvertisementTypeDTO>();
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from AdvertisementType");
			rs = ps.executeQuery();
			while(rs.next()){
				AdvertisementTypeDTO dto  = new AdvertisementTypeDTO();
				dto.setId(rs.getInt("id"));
				dto.setAdTypeName(rs.getString("adTypeName"));
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

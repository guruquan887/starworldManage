package com.keno8.struts.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.UserGoldFlowDTO;

public class UserGoldFlowDAO {
	
	private DataSource ds;
	
	public UserGoldFlowDAO(DataSource ds) {
		this.ds = ds;
	}

	public void addUserGoldFlow(UserGoldFlowDTO dto){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into UserGoldFlow(userID,beforeMoney,afterMoney,diffMoney,beizhu) values(?,?,?,?,?)";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getUserID());
			ps.setDouble(2, dto.getBeforeMoney());
			ps.setDouble(3, dto.getAfterMoney());
			ps.setDouble(4, dto.getDiffMoney());
			ps.setString(5, dto.getBeizhu());
			ps.execute();
			System.out.println("增加用户银子流水记录成功!");
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
	
}

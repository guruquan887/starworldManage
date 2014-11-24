package com.doowal.hk798.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class NetCSDAO {
	private DataSource ds;
	
	public NetCSDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public NetCSDTO getQPAdminSiteInfo() {
		NetCSDTO dto = null;
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "select * from QPPlatformManagerDB.dbo.QPAdminSiteInfo where siteID=1";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			dto = new NetCSDTO();
			if (rs.next()) {
				dto.setSiteID(rs.getInt("siteID"));
				dto.setSiteName(rs.getString("siteName"));
				dto.setCopyRight(rs.getString("copyRight"));
				dto.setWwwName(rs.getString("wwwName"));
				dto.setAdsMsg(rs.getString("adsMsg"));
				dto.setGameMsg(rs.getString("gameMsg"));
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
			catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return dto;
	}
	
	public NetCSDTO getSystemSet(String param) {
		NetCSDTO dto = null;
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='"+param+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			dto = new NetCSDTO();
			if (rs.next()) {
				dto.setStatusName(rs.getString("StatusName"));
				dto.setStatusValue(rs.getLong("StatusValue"));
				dto.setStatusTip(rs.getString("StatusTip"));
				dto.setStatusString(rs.getString("StatusString"));
				dto.setStatusDescription(rs.getString("StatusDescription"));
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
			catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return dto;
	}
	
	public boolean updateQPAdminSiteInfo(NetCSDTO dto) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "update QPPlatformManagerDB.dbo.QPAdminSiteInfo set wwwName='"+dto.getWwwName()+"',SiteName='"+dto.getSiteName()+"',copyRight='"+dto.getCopyRight()+"',adsMsg='"+dto.getAdsMsg()+"',gameMsg='"+dto.getGameMsg()+"' where siteID=1";
			ps = con.prepareStatement(sql);
			ps.execute();
			r=true;
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
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
	
	public boolean updateset(NetCSDTO dto) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getStatusValue()+"',StatusTip='"+dto.getStatusTip()+"',StatusDescription='"+dto.getStatusDescription()+"',StatusString='"+dto.getStatusString()+"' where StatusName='"+dto.getStatusName()+"'";
			ps = con.prepareStatement(sql);
			ps.execute();
			r=true;
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
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

	public NetCSDTO getById() {
		NetCSDTO dto = null;
		Connection con = null;
		PreparedStatement ps1=null,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10,ps11,ps12 = null;
		ResultSet rs1 = null,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10,rs11,rs12 = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='EnjoinInsure'";
			ps1 = con.prepareStatement(sql);
			rs1 = ps1.executeQuery();
			dto = new NetCSDTO();
			if (rs1.next()) {
				dto.setIn_EnjoinInsure(rs1.getString("StatusValue"));
				dto.setIn_EnjoinInsureNote(rs1.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='EnjoinLogon'";
			ps2 = con.prepareStatement(sql);
			rs2 = ps2.executeQuery();
			//dto = new NetCSDTO();
			if (rs2.next()) {
				dto.setIn_EnjoinLogon(rs2.getString("StatusValue"));
				dto.setIn_EnjoinLogonNote(rs2.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='EnjoinRegister'";
			ps3 = con.prepareStatement(sql);
			rs3 = ps3.executeQuery();
			//dto = new NetCSDTO();
			if (rs3.next()) {
				dto.setIn_EnjoinRegister(rs3.getString("StatusValue"));
				dto.setIn_EnjoinRegisterNote(rs3.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='GrantIPCount'";
			ps4 = con.prepareStatement(sql);
			rs4 = ps4.executeQuery();
			//dto = new NetCSDTO();
			if (rs4.next()) {
				dto.setIn_GrantIPCount(rs4.getString("StatusValue"));
				dto.setIn_GrantIPCountNote(rs4.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='GrantScoreCount'";
			ps5 = con.prepareStatement(sql);
			rs5 = ps5.executeQuery();
			//dto = new NetCSDTO();
			if (rs5.next()) {
				dto.setIn_GrantScoreCount(rs5.getString("StatusValue"));
				dto.setIn_GrantScoreCountNote(rs5.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='RevenueRateTake'";
			ps6 = con.prepareStatement(sql);
			rs6 = ps6.executeQuery();
			//dto = new NetCSDTO();
			if (rs6.next()) {
				dto.setIn_RevenueRateTake(rs6.getString("StatusValue"));
				dto.setIn_RevenueRateTakeNote(rs6.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='RevenueRateTransfer'";
			ps7 = con.prepareStatement(sql);
			rs7 = ps7.executeQuery();
			//dto = new NetCSDTO();
			if (rs7.next()) {
				dto.setIn_RevenueRateTransfer(rs7.getString("StatusValue"));
				dto.setIn_RevenueRateTransferNote(rs7.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='BankPrerequisite'";
			ps8 = con.prepareStatement(sql);
			rs8 = ps8.executeQuery();
			//dto = new NetCSDTO();
			if (rs8.next()) {
				dto.setIn_BankPrerequisite(rs8.getString("StatusValue"));
				dto.setIn_BankPrerequisiteNote(rs8.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='TransferPrerequisite'";
			ps9 = con.prepareStatement(sql);
			rs9 = ps9.executeQuery();
			//dto = new NetCSDTO();
			if (rs9.next()) {
				dto.setIn_TransferPrerequisite(rs9.getString("StatusValue"));
				dto.setIn_TransferPrerequisiteNote(rs9.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='MedalRate'";
			ps10 = con.prepareStatement(sql);
			rs10 = ps10.executeQuery();
			//dto = new NetCSDTO();
			if (rs10.next()) {
				dto.setIn_MedalRate(rs10.getString("StatusValue"));
				dto.setIn_MedalRateNote(rs10.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='RechargeRate'";
			ps11 = con.prepareStatement(sql);
			rs11 = ps11.executeQuery();
			//dto = new NetCSDTO();
			if (rs11.next()) {
				dto.setIn_RechargeRate(rs11.getString("StatusValue"));
				dto.setIn_RechargeRateNote(rs11.getString("StatusString"));
			}
			
			sql = "select * from QPAccountsDB.dbo.SystemStatusInfo where StatusName='PlayGameScoreCount'";
			ps12 = con.prepareStatement(sql);
			rs12 = ps12.executeQuery();
			//dto = new NetCSDTO();
			if (rs12.next()) {
				dto.setIn_PlayGameScoreCount(rs12.getString("StatusValue"));
				dto.setIn_PlayGameScoreNote(rs12.getString("StatusString"));
			}
			if(rs1!=null){
				rs1.close();rs1=null;
			}
			if(ps1!=null){
				ps1.close();ps1=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(rs1!=null){
					rs1.close();rs1=null;
				}
				if(ps1!=null){
					ps1.close();ps1=null;
				}
				if(con!=null){
					con.close();con=null;
				}
			}
			catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return dto;
	}
	
	public boolean update(NetCSDTO dto) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_EnjoinInsure()+"',StatusString='"+dto.getIn_EnjoinInsureNote()+"' where StatusName='EnjoinInsure'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_EnjoinLogon()+"',StatusString='"+dto.getIn_EnjoinLogonNote()+"' where StatusName='EnjoinLogon'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_EnjoinRegister()+"',StatusString='"+dto.getIn_EnjoinRegisterNote()+"' where StatusName='EnjoinRegister'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_GrantIPCount()+"',StatusString='"+dto.getIn_GrantIPCountNote()+"' where StatusName='GrantIPCount'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_GrantScoreCount()+"',StatusString='"+dto.getIn_GrantScoreCountNote()+"' where StatusName='GrantScoreCount'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_RevenueRateTake()+"',StatusString='"+dto.getIn_RevenueRateTakeNote()+"' where StatusName='RevenueRateTake'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_RevenueRateTransfer()+"',StatusString='"+dto.getIn_RevenueRateTransferNote()+"' where StatusName='RevenueRateTransfer'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_TransferPrerequisite()+"',StatusString='"+dto.getIn_TransferPrerequisiteNote()+"' where StatusName='TransferPrerequisite'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_BankPrerequisite()+"',StatusString='"+dto.getIn_BankPrerequisiteNote()+"' where StatusName='BankPrerequisite'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_MedalRate()+"',StatusString='"+dto.getIn_MedalRateNote()+"' where StatusName='MedalRate'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_RechargeRate()+"',StatusString='"+dto.getIn_RechargeRateNote()+"' where StatusName='RechargeRate'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPAccountsDB.dbo.SystemStatusInfo set StatusValue='"+dto.getIn_PlayGameScoreCount()+"',StatusString='"+dto.getIn_PlayGameScoreNote()+"' where StatusName='PlayGameScoreCount'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			r=true;
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
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
	
}

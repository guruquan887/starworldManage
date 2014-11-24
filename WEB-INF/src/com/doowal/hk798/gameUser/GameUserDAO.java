package com.doowal.hk798.gameUser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.doowal.hk798.login.AdminDTO;


public class GameUserDAO extends BaseDAO{
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;
	
	public GameUserDAO(DataSource ds) {
		this.ds = ds;
	}

	public int getTotalPage() {
		return pageCount;
	}
	
	public int getRcordCount() {
		return recordCount;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	
	public List<AdminDTO> getAgentMenu(){
    	List<AdminDTO> list = new ArrayList<AdminDTO>();
    	String sql = "select * from QPPlatformManagerDB.dbo.[AgentMenu] order by id";
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		AdminDTO dto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new AdminDTO();
				dto.setId(rs.getInt("id"));
				list.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
    	return list;
    }
	
	public String reg(AdminDTO dto) {
		String result = "";
		String sql = "insert into QPAccountsDB.dbo.AccountsProxy(ProxyAccounts,NickName,Password,PrevProxy,IsFreeze,Brokerage,Taxrate,Winner,levelID,bankPass) values(?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getProxyAccounts());
			ps.setString(2, dto.getNickName());
			ps.setString(3, dto.getPassword());
			ps.setInt(4, dto.getPrevProxy());
			ps.setInt(5, dto.getIsFreeze());
			ps.setDouble(6, dto.getBrokerage()*0.01);
			ps.setDouble(7, dto.getTaxRate()*0.01);
			ps.setDouble(8, dto.getWinner()*0.01);
			ps.setInt(9, dto.getLevelID());
			ps.setString(10, dto.getBankPass());
			ps.execute();
			result = "添加成功！";
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.closeDBObject(con, rs, null, null, ps);
		}
		return result;
	}
	
	public AdminDTO getByProxyId(int userID) {
		AdminDTO dto = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from QPAccountsDB.dbo.AccountsProxyInfoView where proxyID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto =  new AdminDTO();
				dto.setProxyID(rs.getInt("proxyID"));
				dto.setProxyAccounts(rs.getString("proxyAccounts"));
				dto.setGold(rs.getLong("gold"));
				dto.setNickName(rs.getString("nickName"));
				dto.setBrokerage(rs.getDouble("Brokerage")*100);
				dto.setTaxRate(rs.getDouble("TaxRate")*100);
				dto.setWinner(rs.getDouble("winner")*100);
				dto.setJine(rs.getLong("jine"));
				dto.setApplyGold(rs.getLong("applyGold"));
				dto.setZzGold(rs.getLong("zzGold"));
				dto.setTelphone(rs.getString("telphone"));
				
				dto.setPrevProxy(rs.getInt("prevProxy"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	 public int InsertAgentAuthority(int userID, int menuID){
	    	int msg = 0;
	    	Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = ds.getConnection();
				ps = con.prepareStatement("insert into QPPlatformManagerDB.dbo.AgentAuthority(UserId,MenuId) values(?,?)");
				ps.setInt(1, userID);
				ps.setInt(2, menuID);
				ps.execute();
				msg = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				msg = 0;
			}finally{
				closeDBObject(con, null, null, null, ps);
			}
			return msg;
	    }
	
	 public int addAdminValues(int userID, int ruleID){
	    	int msg = 0;
	    	Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = ds.getConnection();
				ps = con.prepareStatement("insert into QPAccountsDB.dbo.relation(userID,ruleID) values(?,?)");
				ps.setInt(1, userID);
				ps.setInt(2, ruleID);
				ps.execute();
				msg = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				msg = 0;
			}finally{
				closeDBObject(con, null, null, null, ps);
			}
			return msg;
	    }
	    
	    public int delAdminValues(int userID,int ruleID){
	    	int msg = 0;
	    	Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = ds.getConnection();
				ps = con.prepareStatement("delete from QPGameUserDB.dbo.relation where userID =? and ruleID = ?");
				ps.setInt(1, userID);
				ps.setInt(2, ruleID);
				ps.execute();
				msg = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				msg = 0;
			}finally{
				closeDBObject(con, null, null, null, ps);
			}
			return msg;
	    }
	
	public List<RuleDTO> getRuleList(){
    	List<RuleDTO> list = new ArrayList<RuleDTO>();
    	String sql = "select * from QPGameUserDB.dbo.[rule] order by id";
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		RuleDTO dto = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new RuleDTO();
				dto.setRuleID(rs.getInt("id"));
				dto.setRuleName(rs.getString("ruleName"));
				dto.setRuleMark(rs.getString("mark"));
				list.add(dto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
    	return list;
    }
	
	 public int[] getAdmin(int userID) {
			int[] adminValues = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//15
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
				try {
					conn = ds.getConnection();
					st=conn.createStatement();
					String sql="select * from QPTreasureDB.dbo.R_relation_View Where userID ="+userID+" order by ruleID";
					System.out.println(sql);
					rs = st.executeQuery(sql);
					int i = 0;
					while (rs.next()) {
						adminValues[i] =rs.getInt("ruleID");
						i++;
					}
			
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					closeDBObject(conn, rs, st, null, null);
				}
			return adminValues;
		}
	
	
	public int checkMemName(String account){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from AccountsInfo where accounts=?");
			ps.setString(1, account);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
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
			}
		}
		return count;
	}
	
	public boolean qxJuser(String userID,int gameTypeID) {  //取消机器人
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			/*sql = "update QPAccountsDB.dbo.AccountsInfo set IsAndroid = 0 where userID="+userID;
			ps = con.prepareStatement(sql);
			ps.execute();*/
			if(gameTypeID==1){
				sql = "delete from QPTreasureDB.dbo.AndroidManager where userID="+userID;
				ps = con.prepareStatement(sql);
				ps.execute();
			}
			else{
				sql = "delete from QPGameScoreDB.dbo.AndroidManager where userID="+userID;
				ps = con.prepareStatement(sql);
				ps.execute();
			}
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	
	public boolean oxNew(String userID,int gameTypeID) {  //设定牛牛特殊
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			sql = "update QPAccountsDB.dbo.AccountsInfo set SpecialRight=1,winRate=70 where userID="+userID;
			ps = con.prepareStatement(sql);
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean redLine(String userID,int gameTypeID) {  //设定五张特殊
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			sql = "update QPAccountsDB.dbo.AccountsInfo set UserRight='1879048192' where userID="+userID;
			ps = con.prepareStatement(sql);
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean interType(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("update AccountsInfo set IsAndroid = 2 where userID=?");
			ps.setString(1, userID);
			ps.execute();
			con.commit();
			r = true;
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean vipType(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("update AccountsInfo set IsAndroid = 3 where userID=?");
			ps.setString(1, userID);
			ps.execute();
			con.commit();
			r = true;
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean qxvipType(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("update AccountsInfo set IsAndroid = 0 where userID=?");
			ps.setString(1, userID);
			ps.execute();
			con.commit();
			r = true;
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean qxinterType(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("update AccountsInfo set IsAndroid = 0 where userID=?");
			ps.setString(1, userID);
			ps.execute();
			con.commit();
			r = true;
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean delAll(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("delete from AccountsInfo where userID=?");
			ps.setString(1, userID);
			ps.execute();
			
			/*ps = con.prepareStatement("update AccountsInfo set spreaderID = 1 where spreaderID = ?");
			ps.setString(1, userID);
			ps.execute();*/
			
			ps = con.prepareStatement("delete from QPTreasureDB.dbo.GameScoreInfo where userID=?");
			ps.setString(1, userID);
			ps.execute();
			
			ps = con.prepareStatement("delete from QPGameScoreDB.dbo.GameScoreInfo where userID=?");
			ps.setString(1, userID);
			ps.execute();
			
			ps = con.prepareStatement("delete from QPTreasureDB.dbo.AndroidManager where userID=?");
			ps.setString(1, userID);
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean qxSpecialRight(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("update AccountsInfo set SpecialRight=0 where userID=?");
			ps.setString(1, userID);
			ps.execute();
			
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean setAndroid(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("update AccountsInfo set isAndroid=1 where userID=?");
			ps.setString(1, userID);
			ps.execute();
			
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean Freeze(String userID,int date,int minute) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(new Date());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cd = Calendar.getInstance();
		if(date==1){
			cd.add(Calendar.DATE, 1);//增加一天   
		}
		else if(date==2){
			cd.add(Calendar.DAY_OF_YEAR, 7);//增加一周   
		}
		else if(date==3){
			cd.add(Calendar.MONTH, 1);//增加一个月
		}
		else if(date==4){
			cd.add(Calendar.YEAR, 100); //增加一百年
		}
		else if(minute>0){
			cd.add(Calendar.MINUTE, date); //增加分钟
		}
		String dt2 = sdf.format(cd.getTime());
		try {
			System.out.println("dt2:"+dt2);
			con = ds.getConnection();
			con.setAutoCommit(false);
			String sql = "update AccountsInfo set Nullity = 1,NullityOverDate='"+dt2+"' where userID="+userID;
			System.out.println("sql:"+sql);
			ps = con.prepareStatement(sql);
			/*ps.setString(1, userID);
			ps.setDate(2,dt2);*/
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean FreezeAgent(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			String sql = "update AccountsProxy set isFreeze = 2 where proxyID="+userID;
			System.out.println("sql:"+sql);
			ps = con.prepareStatement(sql);
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public List<GameUserDTO> getRandomGameID(){
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select Top 10 * from QPAccountsDB.dbo.ReserveIdentifier where Distribute=0 order by GameID");
			rs = ps.executeQuery();
			
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				dto.setGameID(rs.getInt("gameID"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	public boolean UnFreeze(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("update AccountsInfo set Nullity = 0 where userID=?");
			ps.setString(1, userID);
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean UnFreezeAgent(String userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("update AccountsProxy set isFreeze = 0 where userID=?");
			ps.setString(1, userID);
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public String[] zsScore(String[] params) {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call QPRecordDB.dbo.WSP_PM_GrantTreasure (?,?,?,?,?)}";
		String[] result = new String[2];
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			callstmt.execute();
			/*while(rs.next()){
				result[0] = rs.getString(1);
				System.out.println("result[0]:"+result[0]);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return result;
	}
	
	public boolean zsjqScore(String userID,long score) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			int count = 0;
			sql = "select count(*) from QPTreasureDB.dbo.GameScoreInfo where userID="+userID;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			if(count==0){
				sql = "insert into QPTreasureDB.dbo.GameScoreInfo(UserID,Score,LastLogonIP,RegisterIP) values("+userID+","+score+",'127.0.0.1','127.0.0.1')";
			}
			else{
				sql = "update QPTreasureDB.dbo.GameScoreInfo set score=score+"+score+" where userID="+userID;
			}
			ps = con.prepareStatement(sql);
			System.out.println("sql:"+sql);
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return r;
	}
	
	public boolean zsJifen(String[] params) {
		Boolean r=false;
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call QPGameScoreDB.dbo.WSP_PM_GrantGameScore (?,?,?,?,?,?)}";
		String[] result = new String[2];
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			callstmt.execute();
			/*while(rs.next()){
				result[0] = rs.getString(1);
				System.out.println("result[0]:"+result[0]);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return r;
	}
	
	public boolean zsjqJifen(String userID,long score) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			int count = 0;
			sql = "select count(*) from QPGameScoreDB.dbo.GameScoreInfo where userID="+userID;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			if(count==0){
				sql = "insert into QPGameScoreDB.dbo.GameScoreInfo(UserID,Score,LastLogonIP,RegisterIP) values("+userID+","+score+",'127.0.0.1','127.0.0.1')";
			}
			else{
				sql = "update QPGameScoreDB.dbo.GameScoreInfo set score=score+"+score+" where userID="+userID;
			}
			ps = con.prepareStatement(sql);
			System.out.println("sql:"+sql);
			ps.execute();
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return r;
	}
	
	public boolean zsExperience(String[] params) {
		Boolean r=false;
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call QPRecordDB.dbo.WSP_PM_GrantExperience (?,?,?,?,?)}";
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			callstmt.execute();
			/*while(rs.next()){
				result[0] = rs.getString(1);
				System.out.println("result[0]:"+result[0]);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return r;
	}
	
	public boolean zsJqExperience(String[] params) {
		Boolean r=false;
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call QPRecordDB.dbo.WSP_PM_GrantExperience (?,?,?,?,?)}";
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			callstmt.execute();
			/*while(rs.next()){
				result[0] = rs.getString(1);
				System.out.println("result[0]:"+result[0]);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return r;
	}
	
	public String[] clearScore(String[] params) {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call QPGameScoreDB.dbo.WSP_PM_GrantClearScore (?,?,?,?,?)}";
		String[] result = new String[2];
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			rs = callstmt.executeQuery();
			while(rs.next()){
				result[0] = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return result;
	}
	
	public String[] clearFlee(String[] params) {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call QPGameScoreDB.dbo.WSP_PM_GrantClearFlee (?,?,?,?,?)}";
		String[] result = new String[2];
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			rs = callstmt.executeQuery();
			while(rs.next()){
				result[0] = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return result;
	}
	
	
	public String[] zsGameID(String[] params) {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement callstmt = null;
		String sql = "{ call QPRecordDB.dbo.WSP_PM_GrantGameID (?,?,?,?,?)}";
		String[] result = new String[2];
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			rs = callstmt.executeQuery();
			while(rs.next()){
				result[0] = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, callstmt, null);
		}
		return result;
	}
	
	 public void showQueryParamaters(Object[] objs) {
			StringBuffer infoSb = new StringBuffer();
			infoSb.append("Parameter for query[");
			if(objs!=null)
				for(int i=0; i<objs.length; i++){
					infoSb.append(objs[i]!=null?objs[i].toString():"").append(",");
				}
			infoSb.append("]");
		}
	
	public List<GameRoomInfo> getServerRoom(){
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<GameRoomInfo> list = new ArrayList<GameRoomInfo>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from QPPlatformDB.dbo.GameRoomInfo(nolock) ORDER BY SortID");
			rs = ps.executeQuery();
			while(rs.next()){
				GameRoomInfo dto = new GameRoomInfo();
				dto.setServerID(rs.getInt("serverID"));
				dto.setServerRoom(rs.getString("serverName"));
				dto.setKindID(rs.getInt("kindID"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	public void setupJuser(GameRoomInfo dto,int gameTypeID){
		Connection con= null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "update QPAccountsDB.dbo.AccountsInfo set IsAndroid = 1 where userID = "+dto.getUserID();
			ps = con.prepareStatement(sql);
			ps.execute();
			if(gameTypeID==1){
				sql = "insert into QPTreasureDB.dbo.AndroidManager(UserID,ServerID,MinPlayDraw,MaxPlayDraw,MinTakeScore,MaxTakeScore,MinReposeTime,MaxReposeTime,ServiceTime,ServiceGender,Nullity,AndroidNote) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			else{
				sql = "insert into QPGameScoreDB.dbo.AndroidManager(UserID,ServerID,MinPlayDraw,MaxPlayDraw,MinTakeScore,MaxTakeScore,MinReposeTime,MaxReposeTime,ServiceTime,ServiceGender,Nullity,AndroidNote) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getUserID());
			ps.setInt(2, dto.getServerID());
			ps.setInt(3, dto.getMinPlayDraw());
			ps.setInt(4, dto.getMaxPlayDraw());
			ps.setLong(5, dto.getMinTakeScore());
			ps.setLong(6, dto.getMaxTakeScore());
			ps.setInt(7, dto.getMinReposeTime());
			ps.setInt(8, dto.getMaxReposeTime());
			ps.setInt(9, dto.getServiceTime());
			ps.setInt(10, dto.getServiceGender());
			ps.setInt(11, dto.getNullity());
			ps.setString(12, dto.getAndroidNote());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, null, null, null, ps);
		}
	}
	
	public long queryCordon(){
		long cordon = 0;
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select cordon from QPTreasureDB.dbo.GameExchangePreferences where id=?");
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			if(rs.next()){
				cordon = rs.getLong(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return cordon;
	}
	
	/*====================判断保留用户是否存在=========================*/
	public int checkExit(String accounts){
		int count = 0;
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from QPAccountsDB.dbo.AccountsInfo where accounts=?");
			ps.setString(1, accounts);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return count;
	}
	
	/*============================2014-10-22增加代理用户===========================*/
	public String[] regAgent(String[] params) {
		String[] result = new String[2];
		String sql = "{ call JAVA_PW_AddProxyAccounts(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection con = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			System.out.println("长度"+params.length);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			
			rs = callstmt.executeQuery();
			while (rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
			}
			
/*			callstmt.registerOutParameter(14, Types.VARCHAR);
			callstmt.execute();
			callstmt.getMoreResults();
			result = callstmt.getString(14);*/
			
			/*if(result==-1){
				msg = "账号不存在";
			}
			else if(result==-2){
				msg = "账号不能为空";
			}
			else if(result==-3){
				msg = "密码不能为空";
			}
			else if(result==-4){
				msg = "账号已存在";
			}
			else if(result==-5){
				msg = "昵称已存在";
			}
			else if(result==-5){
				msg = "您所输入的帐号名含有限制字符串";
			}
			else{
				msg = "注册成功!";
			}*/
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.closeDBObject(con, rs, null, callstmt, null);
		}
		return result;
	}
	
	/*============================增加保留用户===========================*/
	public String[] reg(String[] params) {
		String[] result = new String[3];
		String sql = "{ call JAVA_PW_AddProxyAccounts(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection con = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			System.out.println("长度"+params.length);
			System.out.println(params[12]);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			
			rs = callstmt.executeQuery();
			while (rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				//result[2] = rs.getString(3);
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.closeDBObject(con, rs, null, callstmt, null);
		}
		return null;
	}
	
	public void addMenuID(int userID){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from QPPlatformManagerDB.dbo.AgentMenu");
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				ps = con.prepareStatement("insert into QPPlatformManagerDB.dbo.AgentAuthority values(?,?)");
				ps.setInt(1, userID);
				ps.setInt(2,id );
				ps.execute();
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
			try {
				if(rs!=null){
					rs.close();rs=null;
				}
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
		
	}
	
	
	public String[] regAccounts(String[] params) {
		String[] result = new String[3];
		String sql = "{ call JAVA_PW_RegisterUserAccounts (?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection con = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			System.out.println("长度"+params.length);
			for (int i = 0; i < params.length; i++) {
				callstmt.setString(i+1, params[i]);
			}
			
			rs = callstmt.executeQuery();
			while (rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				//result[2] = rs.getString(3);
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.closeDBObject(con, rs, null, callstmt, null);
		}
		return null;
	}
	
	/*====================================保留用户删除========================================*/
	public Boolean deleteConfine(int userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			ps = con.prepareStatement("delete from QPGameUserDB.dbo.AccountsInfo where userID=?");
			ps.setInt(1, userID);
			ps.execute();
			
			ps = con.prepareStatement("delete from QPGameUserDB.dbo.ConfineContent where userID=?");
			ps.setInt(1, userID);
			ps.execute();
			
			ps = con.prepareStatement("delete from QPTreasureDB.dbo.GameScoreInfo where userID=?");
			ps.setInt(1, userID);
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
	
	/*==========================保留用户名=========================*/
	public List<GameUserDTO> GetConfineRecordByPage(int pageindex,int pageSize) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "ConfineContentView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "UserID desc");//排序字段
			toesUp.setString(4, "");//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				dto.setUserID(rs.getInt("UserID"));
				dto.setGameID(rs.getInt("GameID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setRegisterDate(rs.getString("CollectDate").substring(0, 19));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
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
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	/*====================================卡线用户列表========================================*/
	public List<GameUserDTO> GetRecordByPage1(int pageindex,int pageSize,String where) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con =ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "AccountsLockerView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "UserID desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				dto.setUserID(rs.getInt("UserID"));
				dto.setGameID(rs.getInt("GameID"));
				dto.setAccounts(rs.getString("Accounts"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
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
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	/*====================================卡线用户删除========================================*/
	public Boolean Delete() {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			ps = con.prepareStatement("delete from QPTreasureDB.dbo.GameScoreLocker");
			ps.execute();
			
			ps = con.prepareStatement("delete from QPGameScoreDB.dbo.GameScoreLocker");
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
	
	public Boolean DeleteUserLocker(int gameshopid) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con =ds.getConnection();
			ps = con.prepareStatement("delete from QPTreasureDB.dbo.GameScoreLocker where UserID=?");
			ps.setInt(1, gameshopid);
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
	/*==============================================================================*/
	/*删除用户活动日志*/
	public Boolean DeleteLogs(int userID) {
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con =ds.getConnection();
			ps = con.prepareStatement("delete from QPTreasureDB.dbo.UserOperateLogs where operateUserID=?");
			ps.setInt(1, userID);
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
	
	public List<GameUserDTO> GetAgentByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "AccountsProxyListView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				int userID = rs.getInt("proxyID");
				dto.setUserID(userID);
				dto.setSsAccounts(rs.getString("ssAccounts"));
				dto.setAccounts(rs.getString("ProxyAccounts"));
				dto.setRegAccounts(rs.getString("nickName"));
				dto.setGold(rs.getLong("gold"));
				dto.setBrokerage(rs.getDouble("Brokerage")*100);
				dto.setTaxRate(rs.getDouble("TaxRate")*100);
				dto.setWinner(rs.getDouble("Winner")*100);
				dto.setLevelID(rs.getInt("levelID"));
				String t1=rs.getString("CreateTime");
				if(t1!=null&&t1.length()>19){
					t1=t1.substring(0,19);
				}
				dto.setPrevProxy(rs.getInt("PrevProxy"));
				dto.setCreateTime(t1);
				int isFreeze = rs.getInt("isFreeze");
				dto.setIsFreeze(isFreeze);
				//账号状态
				if(isFreeze==0){
					dto.setZhStateName("启用");
					dto.setZhStateCss("tdcenter");
				}
				else if(isFreeze==1){
					dto.setZhStateName("停用");
					dto.setZhStateCss("tdcenter_red");
				} 
				else{
					dto.setZhStateName("冻结");
					dto.setZhStateCss("tdcenter_red");
				}
				GameUserDTO dto1 = new GameUserDTO();
				dto1 = sumTax(userID);
				dto.setNumber(dto1.getNumber());
				dto.setMmnumber(dto1.getMmnumber());
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	
	public List<GameUserDTO> GetRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "AccountsInfoView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				int userID = rs.getInt("UserID");
				int gameID = rs.getInt("gameID");
				dto.setUserID(userID);
				dto.setGameID(gameID);
				dto.setKindID(rs.getInt("kindID"));
				dto.setAccounts(rs.getString("Accounts"));
				dto.setRegAccounts(rs.getString("nickName"));
				dto.setExperience(rs.getLong("experience"));
				dto.setOnLineTimeCount(rs.getInt("onlineTimeCount")/60);
				dto.setPlayTimeCount(rs.getInt("playTimeCount")/60);
				DecimalFormat a = new DecimalFormat("#,##0");
				dto.setScore(a.format(rs.getDouble("score")));
				dto.setInsureScore(a.format(rs.getDouble("insureScore")));
				dto.setTotalScore(a.format(rs.getDouble("score")+rs.getDouble("insureScore")));
				dto.setSpreaderID(rs.getString("spreaderID"));
				dto.setUserType(rs.getInt("IsAndroid"));
				dto.setIsAndroid(rs.getInt("IsAndroid"));
				dto.setMemberOrder(rs.getInt("memberOrder"));
				dto.setLoveLiness(rs.getInt("LoveLiness"));
				dto.setWinRate(rs.getInt("winRate"));
				dto.setPresent(rs.getInt("Present"));
				dto.setJifen(a.format(rs.getDouble("jifen")));
				dto.setLastLoginTime(rs.getString("LastLogonDate").substring(0, 19));
				dto.setUserRight(rs.getInt("userRight"));
				dto.setMasterRight(rs.getInt("masterRight"));
				dto.setMasterOrder(rs.getInt("masterOrder"));
				dto.setSpreaderAccounts(rs.getString("proxyAccounts"));
				int level = rs.getInt("memberOrder");
				if(level==1){
					dto.setMemberName("红钻");
				}
				if(level==2){
					dto.setMemberName("蓝钻");
				}
				if(level==3){
					dto.setMemberName("黄钻");
				}
				if(level==4){
					dto.setMemberName("紫钻");
				}
				else{
					dto.setMemberName("普通会员");
				}
				int isAndroid = rs.getInt("isAndRoid");
				if(isAndroid==0){
					dto.setAccountsType("普通");
					dto.setAccountsCss("#666666");
				}
				else if(isAndroid==2){
					dto.setAccountsType("内部");
					dto.setAccountsCss("#0000FF");
				}
				else if(isAndroid==3){
					dto.setAccountsType("优质");
					dto.setAccountsCss("#0000FF");
				}
				else if(isAndroid==-1){
					dto.setAccountsType("代理");
					dto.setAccountsCss("#FF0000");
				}
				
				long totalScore = rs.getLong("score")+rs.getLong("insureScore");
				String serverRoom = rs.getString("ServerName");
				if(serverRoom!=null){
					dto.setServerRoom(rs.getString("ServerName")+"房间游戏中");
				}
				else{
					dto.setServerRoom("未在房间游戏中");
				}
				String t1=rs.getString("RegisterDate");
				if(t1!=null&&t1.length()>19){
					t1=t1.substring(0,19);
				}
				dto.setRegisterDate(t1);
				dto.setRegisterIP(rs.getString("registerIP"));
				dto.setLastLogonIP(rs.getString("lastLogonIP"));
				dto.setGameLogonTimes(rs.getInt("GameLogonTimes"));
				int zhstate = rs.getInt("Nullity");
				dto.setZhState(zhstate);
				//账号状态
				if(zhstate==0){
					dto.setZhStateName("启用");
					dto.setZhStateCss("tdcenter");
				}
				else if(zhstate==1){
					dto.setZhStateName("停用");
					dto.setZhStateCss("tdcenter_red");
				}
				else{
					dto.setZhStateName("冻结");
					dto.setZhStateCss("tdcenter");
				}
				dto.setRegisterMobile(rs.getString("RegisterMobile"));
				dto.setMasterRight(rs.getInt("MasterRight"));//判断是否为管理账户
				dto.setUserRight(rs.getInt("userRight"));//判断是否为比赛账户
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	public List<GameUserDTO> GetJQRecordByPage(int pageindex,int pageSize,String orderby,String where,int gameTypeID) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			if(gameTypeID==1){
				toesUp.setString(1, "AndroidGoldManagerView");//表名 
			}
			else{
				toesUp.setString(1, "AndroidScoreManagerView");//表名 
			}
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby);//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				int userID = rs.getInt("UserID");
				int gameID = rs.getInt("gameID");
				dto.setUserID(userID);
				dto.setGameID(gameID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setRegAccounts(rs.getString("regAccounts"));
				DecimalFormat a = new DecimalFormat("#,##0");
				dto.setScore(a.format(rs.getDouble("score")));
				if(gameTypeID==1){
					dto.setInsureScore(a.format(rs.getDouble("insureScore")));
					dto.setTotalScore(a.format(rs.getDouble("score")+rs.getDouble("insureScore")));
				}
				dto.setWinCount(rs.getInt("winCount"));
				dto.setLoseCount(rs.getInt("lostCount"));
				dto.setServerRoom(rs.getString("serverName"));
				dto.setServiceTime(rs.getString("serviceTime"));
				dto.setRegisterIP(rs.getString("registerIP"));
				dto.setRegisterDate(rs.getString("registerDate").substring(0, 19));
				dto.setExperience(rs.getLong("experience"));
				int serviceTime = 0;
				if(rs.getString("serviceTime")!=null){
					serviceTime = Integer.parseInt(rs.getString("serviceTime"));
				}
				String serverTime = "";
				if((serviceTime & 1) == 1){
					serverTime += "0:00-1:00,";
				}
				if((serviceTime & 2) == 2){
					serverTime += "1:00-2:00,";
				}
				if((serviceTime & 4) == 4){
					serverTime += "2:00-3:00,";
				}
				if((serviceTime & 8) == 8){
					serverTime += "3:00-4:00,";
				}
				if((serviceTime & 16) == 16){
					serverTime += "4:00-5:00,";
				}
				if((serviceTime & 32) == 32){
					serverTime += "5:00-6:00,";
				}
				if((serviceTime & 64) == 64){
					serverTime += "6:00-7:00,";
				}
				if((serviceTime & 128) == 128){
					serverTime += "7:00-8:00,";
				}
				if((serviceTime & 256) == 256){
					serverTime += "8:00-9:00,";
				}
				if((serviceTime & 512) == 512){
					serverTime += "9:00-10:00,";
				}
				if((serviceTime & 1024) == 1024){
					serverTime += "10:00-11:00,";
				}
				if((serviceTime & 2048) == 2048){
					serverTime += "11:00-12:00,";
				}
				if((serviceTime & 4096) == 4096){
					serverTime += "12:00-13:00,";
				}
				if((serviceTime & 8192) == 8192){
					serverTime += "13:00-14:00,";
				}
				if((serviceTime & 16384) == 16384){
					serverTime += "14:00-15:00,";
				}
				if((serviceTime & 32768) == 32768){
					serverTime += "15:00-16:00,";
				}
				if((serviceTime & 65536) == 65536){
					serverTime += "16:00-17:00,";
				}
				if((serviceTime & 131072) == 131072){
					serverTime += "17:00-18:00,";
				}
				if((serviceTime & 262144) == 262144){
					serverTime += "18:00-19:00,";
				}
				if((serviceTime & 524288) == 524288){
					serverTime += "19:00-20:00,";
				}
				if((serviceTime & 1048576) == 1048576){
					serverTime += "20:00-21:00,";
				}
				if((serviceTime & 2097152) == 2097152){
					serverTime += "21:00-22:00,";
				}
				if((serviceTime & 4194304) == 4194304){
					serverTime += "22:00-23:00,";
				}
				if((serviceTime & 8388608) == 8388608){
					serverTime += "23:00-24:00";
				}
				dto.setServerTime(serverTime);
				
				int serviceGender = rs.getInt("serviceGender");
				if(serviceGender==1){
					dto.setJqType("相互模拟");
				}
				else if(serviceGender==2){
					dto.setJqType("被动陪打");
				}
				else if(serviceGender==4){
					dto.setJqType("主动陪打");
				}
				else if(serviceGender==7){
					dto.setJqType("相互模拟+被动陪打+主动陪打");
				}
				if(rs.getString("createDate")!=null){
					dto.setCreateDate(rs.getString("createDate").substring(0, 19));
				}
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	public List<GameUserDTO> GetGoldRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "AccountsGoldListView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				int userID = rs.getInt("UserID");
				int gameID = rs.getInt("gameID");
				dto.setUserID(userID);
				dto.setGameID(gameID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setRegAccounts(rs.getString("regAccounts"));
				dto.setUserMedal(rs.getInt("userMedal"));
				dto.setExperience(rs.getLong("experience"));
				dto.setOnLineTimeCount(rs.getInt("onlineTimeCount")/60);
				dto.setPlayTimeCount(rs.getInt("playTimeCount")/60);
				DecimalFormat a = new DecimalFormat("#,##0");
				dto.setScore(a.format(rs.getDouble("score")));
				dto.setInsureScore(a.format(rs.getDouble("insureScore")));
				dto.setTotalScore(a.format(rs.getDouble("score")+rs.getDouble("insureScore")));
				dto.setLoveLiness(rs.getInt("LoveLiness"));
				dto.setPresent(rs.getInt("Present"));
				dto.setKindName(rs.getString("kindName"));
				dto.setServerRoom(rs.getString("serverName"));
				dto.setRevenue(a.format(rs.getDouble("Revenue")));
				dto.setTotalCount(rs.getInt("WinCount")+rs.getInt("LostCount")+rs.getInt("DrawCount")+rs.getInt("FleeCount"));
				dto.setWinCount(rs.getInt("WinCount"));
				dto.setLoseCount(rs.getInt("LostCount"));
				dto.setDrawCount(rs.getInt("DrawCount"));
				dto.setFleeCount(rs.getInt("FleeCount"));
				dto.setAllLogonTimes(rs.getInt("allLogonTimes"));
				dto.setPlayTimeCount(rs.getInt("playTimeCount")/3600);
				dto.setOnLineTimeCount(rs.getInt("onLineTimeCount")/3600);
				dto.setLastLoginIP(rs.getString("lastLogonIP"));
				dto.setLastLoginTime(rs.getString("lastLogonDate").substring(0, 19));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	//积分管理
	public List<GameUserDTO> GetScoreRecordByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "AccountsScoreListView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				int userID = rs.getInt("UserID");
				int gameID = rs.getInt("gameID");
				dto.setUserID(userID);
				dto.setGameID(gameID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setRegAccounts(rs.getString("regAccounts"));
				dto.setOnLineTimeCount(rs.getInt("onlineTimeCount")/60);
				dto.setPlayTimeCount(rs.getInt("playTimeCount")/60);
				DecimalFormat a = new DecimalFormat("#,##0");
				dto.setScore(a.format(rs.getDouble("score")));
				dto.setKindName(rs.getString("kindName"));
				dto.setServerRoom(rs.getString("serverName"));
				dto.setTotalCount(rs.getInt("WinCount")+rs.getInt("LostCount")+rs.getInt("DrawCount")+rs.getInt("FleeCount"));
				dto.setWinCount(rs.getInt("WinCount"));
				dto.setLoseCount(rs.getInt("LostCount"));
				dto.setDrawCount(rs.getInt("DrawCount"));
				dto.setFleeCount(rs.getInt("FleeCount"));
				dto.setAllLogonTimes(rs.getInt("allLogonTimes"));
				dto.setPlayTimeCount(rs.getInt("playTimeCount")/3600);
				dto.setOnLineTimeCount(rs.getInt("onLineTimeCount")/3600);
				dto.setLastLoginIP(rs.getString("lastLogonIP"));
				dto.setLastLoginTime(rs.getString("lastLogonDate").substring(0, 19));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	//2014年10月22日  账户 停用/冻结
	public boolean zhFrozen(int userID,int state){
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		String agentid = "";
		String userid = "";
		int left=0,right = 0;
		String agentSql = "";
		String userSql = "";
		String sql = "select Lstatus,Rstatus from AccountsProxy where proxyID="+userID;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				left = rs.getInt("Lstatus");
				right = rs.getInt("Rstatus");
			}
			//查询下级的代理ID
			sql = "select proxyID from AccountsProxy where lstatus between "+left+" and "+right+" and isFreeze<>1 and isFreeze<>2";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				agentid += rs.getInt("proxyID")+",";
			}
			if(agentid.length()>0){
				//更新代理表的代理状态
				agentid = agentid.substring(0, agentid.length()-1);
				sql = "update AccountsProxy set IsFreeze="+state+" where proxyID in(" +agentid+")";
				ps = con.prepareStatement(sql);
				System.out.println(sql);
				ps.execute();
				
				//查询下级的会员ID
				sql = "select userID from AccountsInfo where ProxyID in ("+agentid+")";
				ps = con.prepareStatement(sql);
				System.out.println(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					userid += rs.getInt("userID")+",";
				}
				
				//更新用户表的用户状态
				if(userid.length()>0){
					userid = userid.substring(0, userid.length()-1);
					sql = "update AccountsInfo set Nullity="+state+" where userID in(" +userid+")";
					ps = con.prepareStatement(sql);
					System.out.println(sql);
					ps.execute();
				
				
				//插入数据库的方便下次使用
				agentSql = "update AccountsProxy set IsFreeze="+0+" where proxyID in(" +agentid+")";
				userSql = "update AccountsInfo set Nullity="+0+" where userID in("+userid+")";
				
				
				sql = "insert into RecordOperFreeze(OperUid,OperAgentSql,OperUserSql) values("+userID+",'"+agentSql+"','"+userSql+"')";
				ps1 = con.prepareStatement(sql);
				System.out.println(sql);
				ps1.execute();
				r = true;
				con.setAutoCommit(true);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return r;
	}
	
	//2014年10月22日  账户 启用/解冻
	public boolean zhUnFrozen(int userID,int state){
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		int Id = 0;
		String operAgentSql = "";
		String operUserSql = "";
		String sql = "select * from RecordOperFreeze where OperUid="+userID;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				Id = rs.getInt("Id");
				operAgentSql = rs.getString("operAgentSql");
				operUserSql = rs.getString("operUserSql");
			}
			if(operAgentSql.length()>0){
				ps = con.prepareStatement(operAgentSql);
				System.out.println(operAgentSql);
				ps.execute();
			}
			
			if(operUserSql.length()>0){
				ps = con.prepareStatement(operUserSql);
				System.out.println(operUserSql);
				ps.execute();
			}
			
			sql = "delete from RecordOperFreeze where Id="+Id;
			ps1 = con.prepareStatement(sql);
			System.out.println(sql);
			ps1.execute();
			
			r = true;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return r;
	}
	
	public boolean zhFrozenUser(int userID,int state){
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update AccountsInfo set nullity=? where userID=?");
			ps.setInt(1, state);
			ps.setInt(2, userID);
			ps.execute();
			r= true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public GameUserDTO getAgentById(int userID) {
		GameUserDTO dto = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from Accountsproxy where proxyID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new GameUserDTO();
				dto.setProxyID(rs.getInt("proxyID"));
				dto.setPrevProxy(rs.getInt("prevproxy"));
				dto.setProxyAccounts(rs.getString("proxyAccounts"));
				dto.setBrokerage(rs.getDouble("brokerage"));
				dto.setWinner(rs.getDouble("winner"));
				dto.setTaxRate(rs.getDouble("taxRate"));
				dto.setNickName(rs.getString("nickName"));
				dto.setGold(rs.getLong("gold"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	
	//推广参数值获取
	public GameUserDTO getSpreaderSet() {
		GameUserDTO dto = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from QPTreasureDB.dbo.GlobalSpreadInfo");
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new GameUserDTO();
				dto.setPlayTimeCount(rs.getInt("PlayTimeCount"));
				dto.setPlayTimeGrantScore(rs.getInt("PlayTimeGrantScore"));
				dto.setRegisterGrantScore(rs.getInt("RegisterGrantScore"));
				dto.setFillGrantRate(rs.getDouble("FillGrantRate"));
				dto.setBalanceRate(rs.getDouble("BalanceRate"));
				dto.setMinBalanceScore(rs.getInt("MinBalanceScore"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public void updateSpreader(GameUserDTO dto){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update QPTreasureDB.dbo.GlobalSpreadInfo set PlayTimeCount=?,PlayTimeGrantScore=?,RegisterGrantScore=?,FillGrantRate=?,BalanceRate=?,MinBalanceScore=? where ID = 1");
			ps.setInt(1, dto.getPlayTimeCount());
			ps.setInt(2, dto.getPlayTimeGrantScore());
			ps.setInt(3, dto.getRegisterGrantScore());
			ps.setDouble(4, dto.getFillGrantRate());
			ps.setDouble(5, dto.getBalanceRate());
			ps.setInt(6, dto.getMinBalanceScore());
			
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
	}
	
	public GameUserDTO getMemId(int userID){
		
		GameUserDTO dto = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from AccountsInfoView where userID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new GameUserDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setGameID(rs.getInt("gameID"));
				dto.setAccounts(rs.getString("accounts"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	/* 账户更新数据*/
	public GameUserDTO getById(int userID) {
		GameUserDTO dto = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from AccountsInfoView where userID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new GameUserDTO();
				dto.setUserID(rs.getInt("userID"));
				int gameID = rs.getInt("gameID");
				dto.setUserID(userID);
				dto.setGameID(gameID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setGender(rs.getInt("Gender"));
				dto.setRegAccounts(rs.getString("nickName"));
				dto.setExperience(rs.getLong("experience"));
				dto.setMemberOrder(rs.getInt("memberOrder"));
				dto.setLoveLiness(rs.getInt("LoveLiness"));
				dto.setPresent(rs.getInt("Present"));
				DecimalFormat a = new DecimalFormat("#,##0");
				dto.setScore(a.format(rs.getDouble("score")));
				dto.setInsureScore(a.format(rs.getDouble("insureScore")));
				int level = rs.getInt("memberOrder");
				dto.setUnderWrite(rs.getString("underWrite"));
				dto.setLastLoginTime(rs.getString("lastLogonDate").substring(0,19));
				dto.setLastLogonIP(rs.getString("lastLogonIP"));
				dto.setIsAndroid(rs.getInt("isAndroid"));
				dto.setPassportID(rs.getString("passportID"));
				dto.setQuestion1(rs.getString("question1"));
				dto.setResponse1(rs.getString("response1"));
				dto.setCompellation(rs.getString("compellation"));
				dto.setRegisterMobile(rs.getString("registerMobile"));
				dto.setSafeEmail(rs.getString("safeEmail"));
				dto.setFaceID(rs.getInt("faceID"));
				dto.setUserRight(rs.getInt("userRight"));
				dto.setMasterRight(rs.getInt("masterRight"));
				dto.setMasterOrder(rs.getInt("masterOrder"));
				dto.setSpecialRight(rs.getInt("specialRight"));
				dto.setBjlYJ(rs.getDouble("bjlYJ"));
				dto.setBjlZC(rs.getDouble("bjlZC"));
				dto.setTax(rs.getDouble("tax"));
				dto.setLevelID(rs.getInt("levelID"));
				dto.setWinRate(rs.getInt("winRate"));
				dto.setSpreaderID(rs.getString("spreaderID"));
				dto.setGiftMinute(rs.getInt("giftMinute"));
				dto.setGiftMinuteAgent(rs.getInt("giftMinuteAgent"));
				dto.setGiftScore(rs.getLong("giftScore"));
				dto.setGiftScoreAgent(rs.getLong("giftScoreAgent"));
				if(level==1){
					dto.setMemberName("红钻");
				}
				if(level==2){
					dto.setMemberName("蓝钻");
				}
				if(level==3){
					dto.setMemberName("黄钻");
				}
				if(level==4){
					dto.setMemberName("紫钻");
				}
				else{
					dto.setMemberName("普通会员");
				}
				int zhstate = rs.getInt("Nullity");
				dto.setZhState(zhstate);
				//账号状态
				if(zhstate==0){
					dto.setZhStateName("启用中");
					dto.setZhStateCss("");
				}
				else{
					dto.setZhStateName("停用中");
					dto.setZhStateCss("colorred");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	
	public GameUserDTO getById(String account) {
		GameUserDTO dto = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from AccountsInfoView where Accounts=?");
			ps.setString(1, account);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new GameUserDTO();
				dto.setUserID(rs.getInt("userID"));
				int gameID = rs.getInt("gameID");
				System.out.println("gameID:"+gameID+"&account:"+account);
				dto.setUserID(rs.getInt("userID"));
				dto.setGameID(gameID);
				dto.setAccounts(rs.getString("Accounts"));
				dto.setGender(rs.getInt("Gender"));
				dto.setRegAccounts(rs.getString("nickName"));
				dto.setExperience(rs.getLong("experience"));
				dto.setMemberOrder(rs.getInt("memberOrder"));
				dto.setLoveLiness(rs.getInt("LoveLiness"));
				dto.setPresent(rs.getInt("Present"));
				DecimalFormat a = new DecimalFormat("#,##0");
				dto.setScore(a.format(rs.getDouble("score")));
				dto.setInsureScore(a.format(rs.getDouble("insureScore")));
				int level = rs.getInt("memberOrder");
				dto.setUnderWrite(rs.getString("underWrite"));
				dto.setLastLoginTime(rs.getString("lastLogonDate").substring(0,19));
				dto.setLastLogonIP(rs.getString("lastLogonIP"));
				dto.setIsAndroid(rs.getInt("isAndroid"));
				dto.setPassportID(rs.getString("passportID"));
				dto.setQuestion1(rs.getString("question1"));
				dto.setResponse1(rs.getString("response1"));
				dto.setCompellation(rs.getString("compellation"));
				dto.setRegisterMobile(rs.getString("registerMobile"));
				dto.setSafeEmail(rs.getString("safeEmail"));
				dto.setFaceID(rs.getInt("faceID"));
				dto.setUserRight(rs.getInt("userRight"));
				dto.setMasterRight(rs.getInt("masterRight"));
				dto.setMasterOrder(rs.getInt("masterOrder"));
				dto.setSpecialRight(rs.getInt("specialRight"));
				dto.setBjlYJ(rs.getDouble("bjlYJ"));
				dto.setBjlZC(rs.getDouble("bjlZC"));
				dto.setTax(rs.getDouble("tax"));
				dto.setLevelID(rs.getInt("levelID"));
				dto.setWinRate(rs.getInt("winRate"));
				dto.setSpreaderID(rs.getString("spreaderID"));
				dto.setGiftMinute(rs.getInt("giftMinute"));
				dto.setGiftMinuteAgent(rs.getInt("giftMinuteAgent"));
				dto.setGiftScore(rs.getLong("giftScore"));
				dto.setGiftScoreAgent(rs.getLong("giftScoreAgent"));
				if(level==1){
					dto.setMemberName("红钻");
				}
				if(level==2){
					dto.setMemberName("蓝钻");
				}
				if(level==3){
					dto.setMemberName("黄钻");
				}
				if(level==4){
					dto.setMemberName("紫钻");
				}
				else{
					dto.setMemberName("普通会员");
				}
				int zhstate = rs.getInt("Nullity");
				dto.setZhState(zhstate);
				//账号状态
				if(zhstate==0){
					dto.setZhStateName("启用中");
					dto.setZhStateCss("");
				}
				else{
					dto.setZhStateName("停用中");
					dto.setZhStateCss("colorred");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public int queryAccounts(String accounts){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(*) from QPAccountsDB.dbo.AccountsInfo where accounts='"+accounts+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return count;
	}
	
	public int queryNickName(String regAccounts){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(*) from QPAccountsDB.dbo.AccountsInfo where nickName='"+regAccounts+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return count;
	}
	
	public int queryUserID(int gameID){
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userID from QPAccountsDB.dbo.AccountsInfo where gameID='"+gameID+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return count;
	}
	
	public int queryUserID(String accounts){
		int userID = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select proxyID from QPAccountsDB.dbo.AccountsProxy where ProxyAccounts='"+accounts+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				userID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return userID;
	}
	
	public void addsx(String userID,int sxUserID){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update QPAccountsDB.dbo.AccountsInfo set spreaderID="+sxUserID+" where userID='"+userID+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
	}
	
	public void giftScore(String userID,long giftScore,long giftScoreAgent,int giftMinute,int giftMinuteAgent){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update QPTreasureDB.dbo.GameScoreInfo set GiftScore="+giftScore+",giftScoreAgent="+giftScoreAgent+",giftMinute="+giftMinute+",giftMinuteAgent="+giftMinuteAgent+" where userID='"+userID+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
	}
	
	public String selectAgentAccounts(int userID){
		String ysAccounts = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select proxyAccounts from QPAccountsDB.dbo.Accountsproxy where proxyID="+userID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				ysAccounts = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return ysAccounts;
	}
	
	public String selectAccounts(int userID){
		String ysAccounts = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select accounts from QPAccountsDB.dbo.AccountsInfo where userID="+userID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				ysAccounts = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return ysAccounts;
	}
	
	public String selectNickName(int userID){
		String ysAccounts = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select NickName from QPAccountsDB.dbo.AccountsInfo where userID="+userID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				ysAccounts = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return ysAccounts;
	}
	
	public int selectGameID(String accounts){
		int  gameID = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select gameID from QPAccountsDB.dbo.AccountsInfo where accounts='"+accounts+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				gameID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return gameID;
	}
	
	public long selectGold(String gameID){
		long gold = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select score from QPGameUserDB.dbo.AccountsInfo_View where gameID="+gameID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				gold = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return gold;
	}
	
	public int selectUserID(String accounts){
		int userID = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userID from QPAccountsDB.dbo.AccountsInfo where accounts='"+accounts+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				userID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return userID;
	}
	
	public int selectUserID(int gameID){
		int userID = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userID from QPAccountsDB.dbo.AccountsInfo where gameID='"+gameID+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				userID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return userID;
	}
	
	public int selectProxyID(String accounts){
		int userID = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select proxyID from QPAccountsDB.dbo.AccountsProxy where proxyAccounts='"+accounts+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				userID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return userID;
	}
	
	public GameUserDTO getInsureById(int userID){
		GameUserDTO dto = new GameUserDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select userID,score,insureScore from QPTreasureDB.dbo.GameScoreInfo where userID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setGetScore(rs.getLong("score"));
				dto.setGetInsureScore(rs.getLong("insureScore"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public GameUserDTO getAgentInsureById(int userID){
		GameUserDTO dto = new GameUserDTO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select gold from QPAccountsDB.dbo.AccountsProxy where proxyID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setGold(rs.getLong("gold"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public boolean insertInsureCunRecord(int TargetUserID,int SourceUserID,long insureGold,String IP){
		boolean r = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		GameUserDTO targetDTO = getAgentInsureById(TargetUserID);
		GameUserDTO sourceDTO = getAgentInsureById(SourceUserID);
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			long sourGold = 0;
			sql = "select gold from AccountsProxy where proxyID="+SourceUserID;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				sourGold = rs.getLong("gold");
			}
			
			if(insureGold>sourGold){
				r = false;
			}
			else{
				
				sql = "insert into QPTreasureDB.dbo.RecordInsure(SourceUserID,SourceGold,SourceBank,TargetUserID,TargetGold,TargetBank,SwapScore,TradeType,ClientIP,IsGamePlaza) values(?,?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, SourceUserID);
				ps.setLong(2, sourceDTO.getGold());
				ps.setLong(3, 0);
				ps.setInt(4, TargetUserID);
				ps.setLong(5, targetDTO.getGold());
				ps.setLong(6, 0);
				ps.setLong(7, insureGold);
				ps.setInt(8, 1);
				ps.setString(9, IP);
				ps.setInt(10, 2);
				ps.execute();
			
			
				sql = "update QPAccountsDB.dbo.AccountsProxy set gold=gold+? where proxyID=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, insureGold);
				ps.setInt(2, TargetUserID);
				ps.execute();
				
				sql = "update QPAccountsDB.dbo.AccountsProxy set gold=gold-? where proxyID=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, insureGold);
				ps.setInt(2, SourceUserID);
				ps.execute();
				r = true;
			}
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean insertInsureQuRecord(int TargetUserID,int SourceUserID,long insureGold,long gold,String IP){
		boolean r = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		GameUserDTO targetDTO = getAgentInsureById(TargetUserID);
		GameUserDTO sourceDTO = getAgentInsureById(SourceUserID);
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			
			long sourGold = 0;
			sql = "select gold from AccountsProxy where proxyID="+SourceUserID;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				sourGold = rs.getLong("gold");
			}
			
			if(insureGold>sourGold){
				r = false;
			}
			else{
				sql = "insert into QPTreasureDB.dbo.RecordInsure(SourceUserID,SourceGold,SourceBank,TargetUserID,TargetGold,TargetBank,SwapScore,TradeType,ClientIP,IsGamePlaza) values(?,?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, SourceUserID);
				ps.setLong(2, sourceDTO.getGold());
				ps.setLong(3, 0);
				ps.setInt(4, TargetUserID);
				ps.setLong(5, targetDTO.getGold());
				ps.setLong(6, 0);
				ps.setLong(7, insureGold);
				ps.setInt(8, 2);
				ps.setString(9, IP);
				ps.setInt(10, 2);
				ps.execute();
				
				
				sql = "update QPAccountsDB.dbo.AccountsProxy set gold=gold+? where proxyID=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, insureGold);
				ps.setInt(2, TargetUserID);
				ps.execute();
				
				sql = "update QPAccountsDB.dbo.AccountsProxy set gold=gold-? where proxyID=?";
				ps = con.prepareStatement(sql);
				ps.setLong(1, insureGold);
				ps.setInt(2, SourceUserID);
				ps.execute();
				r = true;
			}
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public boolean updateAddGold(int userID,int SourceUserID,long insureGold,String IP){
		boolean r = false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		GameUserDTO targetDTO = getInsureById(userID);
		GameUserDTO sourceDTO = getInsureById(SourceUserID);
		try {
			con = ds.getConnection();
			sql = "update QPTreasureDB.dbo.GameScoreInfo set insureScore=insureScore+? where userID=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, insureGold);
			ps.setInt(2, userID);
			ps.execute();
			
			/*ps = con.prepareStatement("update QPTreasureDB.dbo.GameScoreInfo set score=score+? where userID=?");
			ps.setLong(1, gold);
			ps.setInt(2, userID);
			ps.execute();*/
			
			sql = "update QPTreasureDB.dbo.GameScoreInfo set insureScore=insureScore-? where userID=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, insureGold);
			ps.setInt(2, SourceUserID);
			ps.execute();
			
			sql = "insert into QPTreasureDB.dbo.RecordInsure(SourceUserID,SourceGold,SourceBank,TargetUserID,TargetGold,TargetBank,SwapScore,TradeType,ClientIP) values(?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, SourceUserID);
			ps.setLong(2, sourceDTO.getGetScore());
			ps.setLong(3, sourceDTO.getGetInsureScore());
			ps.setInt(4, userID);
			ps.setLong(5, targetDTO.getGetScore());
			ps.setLong(6, targetDTO.getGetInsureScore());
			ps.setLong(7, insureGold);
			ps.setInt(8, 4);
			ps.setString(9, IP);
			ps.execute();
			r = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public void updateUpGold(int TargetUserID,int SourceUserID,long insureGold,long gold,String IP){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into QPTreasureDB.dbo.RecordInsure(SourceUserID,SourceGold,SourceBank,TargetUserID,TargetGold,TargetBank,SwapScore,TradeType,ClientIP) values(?,?,?,?,?,?,?,?,?)";
		GameUserDTO targetDTO = getInsureById(TargetUserID);
		GameUserDTO sourceDTO = getInsureById(SourceUserID);
		try {
			con = ds.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, SourceUserID);
			ps.setLong(2, sourceDTO.getGetScore());
			ps.setLong(3, sourceDTO.getGetInsureScore());
			ps.setInt(4, TargetUserID);
			ps.setLong(5, targetDTO.getGetScore());
			ps.setLong(6, targetDTO.getGetInsureScore());
			ps.setLong(7, insureGold+gold);
			ps.setInt(8, 5);
			ps.setString(9, IP);
			ps.execute();
			
			sql = "update QPTreasureDB.dbo.GameScoreInfo set insureScore=insureScore+?,score=score+? where userID=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, insureGold);
			ps.setLong(2, gold);
			ps.setInt(3, TargetUserID);
			ps.execute();
			
			sql = "update QPTreasureDB.dbo.GameScoreInfo set insureScore=insureScore-?,score=score-? where userID=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, insureGold);
			ps.setLong(2, gold);
			ps.setInt(3, SourceUserID);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		
	}
	
	public void updateAccount(int userID){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("update QPGameUserDB.dbo.AccountsInfo set userType=0 where userID=?");
			ps.setInt(1, userID);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
	}
	
	
	public boolean addConfine(int userID){
		boolean r = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("insert into ConfineContent(userID,CollectDate) values(?,getDate())");
			ps.setInt(1, userID);
			ps.execute();
			r = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public int updateBJLZC(int spreaderID,double sxbjlZC){
		int count = 0;
		Connection con = null;
		PreparedStatement ps =null;
		PreparedStatement ps1 =null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "select bjlZC,userID from QPAccountsDB.dbo.AccountsInfo where spreaderID="+spreaderID;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			double bjlZC = 0;
			int userID = 0;
			//if(userID!=0){
				while(rs.next()){
					bjlZC = rs.getDouble("bjlZC");
					userID = rs.getInt("userID");
					System.out.println("userID："+userID+"&&bjlZC:"+bjlZC);
					if(bjlZC>sxbjlZC){
						sql = "update QPAccountsDB.dbo.AccountsInfo bjlZC="+sxbjlZC+" where userID="+userID;
						ps1 = con.prepareStatement(sql);
						ps1.execute();
						count=1;
					}
				}
				//updateBJLZC(userID,sxbjlZC);
			//}
			//else{
			//	System.out.println(">>>>>>进入更改下线股份");
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return count;
	}
	
	public String updateAgent(GameUserDTO dto) {
		String msg = "";
		Connection con = null;
		PreparedStatement ps =null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			String sql = "";
			System.out.println("logonPass="+dto.getLogonPass());
			if(dto.getLogonPass()!=""&&"".equals(dto.getBankPass())){
				sql = "update QPAccountsDB.dbo.Accountsproxy set nickName='"+dto.getNickName()+"',password='"+dto.getLogonPass()+
				"',brokerage="+dto.getBrokerage()+",TaxRate="+dto.getTaxRate()+",Winner="+dto.getWinner()+" where proxyID="+dto.getUserID();
			}
			if(dto.getLogonPass()!=""&&dto.getBankPass()!=""){
				sql = "update QPAccountsDB.dbo.Accountsproxy set nickName='"+dto.getNickName()+"',password='"+dto.getLogonPass()+
				"',bankPass='"+dto.getBankPass()+"',brokerage="+dto.getBrokerage()+",TaxRate="+dto.getTaxRate()+",Winner="+dto.getWinner()+" where proxyID="+dto.getUserID();
			}
			if(dto.getLogonPass()==""&&dto.getBankPass()!=""){
				sql = "update QPAccountsDB.dbo.Accountsproxy set nickName='"+dto.getNickName()+"',bankPass='"+dto.getBankPass()+
				"',brokerage="+dto.getBrokerage()+",TaxRate="+dto.getTaxRate()+",Winner="+dto.getWinner()+" where proxyID="+dto.getUserID();
			}
			if(dto.getLogonPass()==""&&dto.getBankPass()==""){
				sql = "update QPAccountsDB.dbo.Accountsproxy set nickName='"+dto.getNickName()+
				"',brokerage="+dto.getBrokerage()+",TaxRate="+dto.getTaxRate()+",Winner="+dto.getWinner()+" where proxyID="+dto.getUserID();
			}
			System.out.println("sql:"+sql);
			ps = con.prepareStatement(sql);
			ps.execute();
			
			/*if(dto.getLogonPass()!=""||dto.getBankPass()!=""){
				sql = "update QPAccountsDB.dbo.AccountsInfo set logonPass = '"+dto.getLogonPass().toUpperCase()+"',insurePass='"+dto.getBankPass().toUpperCase()+"' where accounts='"+dto.getProxyAccounts()+"'";
			}
			if(dto.getLogonPass()==""||dto.getBankPass()==""){
				sql = "";
			}
			if(dto.getLogonPass()!=""||dto.getBankPass()==""){
				sql = "update QPAccountsDB.dbo.AccountsInfo set logonPass = '"+dto.getLogonPass().toUpperCase()+"' where accounts='"+dto.getProxyAccounts()+"'";
			}
			if(dto.getLogonPass()==""||dto.getBankPass()!=""){
				sql = "update QPAccountsDB.dbo.AccountsInfo set insurePass='"+dto.getBankPass().toUpperCase()+"' where accounts='"+dto.getProxyAccounts()+"'";
			}
			ps = con.prepareStatement(sql);
			ps.execute();*/
			
			con.commit();
		    con.setAutoCommit(true);
			System.out.println("update success!!!!!!!!!!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return msg;
	}
	
	
	public String update(GameUserDTO dto) {
		String msg = "";
		Connection con = null;
		PreparedStatement ps =null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			String sql = "";
			System.out.println("logonPass="+dto.getLogonPass()+"&&insurePass="+dto.getInsurePass());
			if(dto.getLogonPass()!=""||"".equals(dto.getInsurePass())){
				sql = "update QPAccountsDB.dbo.AccountsInfo set accounts='"+dto.getAccounts()+"',nickName='"+dto.getRegAccounts()+"',logonPass='"+dto.getLogonPass()+
				"',gender="+dto.getGender()+",SpecialRight="+dto.getSpecialRight()+",winRate="+dto.getWinRate()+",underWrite='"+dto.getUnderWrite()+"',compellation='"+dto.getCompellation()+"',MasterOrder="+dto.getMasterOrder()+",UserRight="+dto.getUserRight()+",MasterRight="+dto.getMasterRight()+" where userID="+dto.getUserID();
			}
			if(dto.getInsurePass()!=""||"".equals(dto.getLogonPass())){
				sql = "update QPAccountsDB.dbo.AccountsInfo set accounts='"+dto.getAccounts()+"',nickName='"+dto.getRegAccounts()+"',insurePass='"+dto.getInsurePass()+
				"',gender="+dto.getGender()+",SpecialRight="+dto.getSpecialRight()+",winRate="+dto.getWinRate()+",underWrite='"+dto.getUnderWrite()+"',compellation='"+dto.getCompellation()+"',MasterOrder="+dto.getMasterOrder()+",UserRight="+dto.getUserRight()+",MasterRight="+dto.getMasterRight()+" where userID="+dto.getUserID();
			}
			if((dto.getLogonPass()!=""&&dto.getInsurePass()!="")){
				sql = "update QPAccountsDB.dbo.AccountsInfo set accounts='"+dto.getAccounts()+"',nickName='"+dto.getRegAccounts()+"',logonPass='"+dto.getLogonPass()+"',insurePass='"+dto.getInsurePass()+
				"',gender="+dto.getGender()+",SpecialRight="+dto.getSpecialRight()+",winRate="+dto.getWinRate()+",underWrite='"+dto.getUnderWrite()+"',compellation='"+dto.getCompellation()+"',MasterOrder="+dto.getMasterOrder()+",UserRight="+dto.getUserRight()+",MasterRight="+dto.getMasterRight()+" where userID="+dto.getUserID();
			}
			if((dto.getLogonPass()==""&&dto.getInsurePass()=="")){
				sql = "update QPAccountsDB.dbo.AccountsInfo set accounts='"+dto.getAccounts()+"',nickName='"+dto.getRegAccounts()+
				"',gender="+dto.getGender()+",SpecialRight="+dto.getSpecialRight()+",winRate="+dto.getWinRate()+",underWrite='"+dto.getUnderWrite()+"',compellation='"+dto.getCompellation()+"',MasterOrder="+dto.getMasterOrder()+",UserRight="+dto.getUserRight()+",MasterRight="+dto.getMasterRight()+" where userID="+dto.getUserID();
			}
			System.out.println("sql:"+sql);
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "update QPTreasureDB.dbo.GameScoreInfo set GiftScore="+dto.getGiftScore()+",giftScoreAgent="+dto.getGiftScoreAgent()+",giftMinute="+dto.getGiftMinute()+",giftMinuteAgent="+dto.getGiftMinuteAgent()+" where userID='"+dto.getUserID()+"'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
			msg = "修改用户信息成功!";
			con.commit();
		    con.setAutoCommit(true);
			System.out.println("update success!!!!!!!!!!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return msg;
	}
	
	public GameUserDTO sumAndroid(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GameUserDTO dto = new GameUserDTO();
		int jinbicount = 0;
		int jifencount = 0;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("select count(*) as count from QPAccountsDB.dbo.AccountsInfo where userID in(select userID from QPTreasureDB.dbo.AndroidManager)");
			rs = ps.executeQuery();
			if(rs.next()){
				jinbicount = rs.getInt(1);
			}
			
/*			ps = conn.prepareStatement("select count(*) as count from QPAccountsDB.dbo.AccountsInfo where userID in(select userID from QPGameScoreDB.dbo.AndroidManager)");
			rs = ps.executeQuery();
			if(rs.next()){
				jifencount = rs.getInt(1);
			}*/
			
			dto.setTotalAndroidNum(jinbicount+jifencount); 
			
			ps = conn.prepareStatement("select sum(score) as TotalAndroidScore from QPTreasureDB.dbo.GameScoreInfo where userID in(select userID from QPTreasureDB.dbo.AndroidManager)");
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setTotalAndroidScore(rs.getLong("TotalAndroidScore"));
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(conn, rs, null, null, ps);
		}
		return dto;
	}
	
	
	public GameUserDTO sumTax(int userID){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GameUserDTO dto = new GameUserDTO();
		int count = 0;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("select count(*) as count from AccountsProxy where prevproxy=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
				dto.setNumber(count); 
			}
			
			ps = conn.prepareStatement("select count(*) as count from AccountsInfo where proxyID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setMmnumber(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(conn, rs, null, null, ps);
		}
		return dto;
	}
	
	/* ===================删除会员用户==================== */
	public boolean deleteUser(int userID){
		Boolean r=false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("delete from AccountsInfo where userID=?");
			ps.setInt(1, userID);
			ps.execute();
			
			ps = con.prepareStatement("update AccountsInfo set spreaderID = 1 where spreaderID = ?");
			ps.setInt(1, userID);
			ps.execute();
			
			ps = con.prepareStatement("delete from QPTreasureDB.dbo.GameScoreInfo where userID=?");
			ps.setInt(1, userID);
			ps.execute();
			
			ps = con.prepareStatement("delete from QPGameScoreDB.dbo.GameScoreInfo where userID=?");
			ps.setInt(1, userID);
			ps.execute();
			
			
			r= true;
			con.commit();
		    con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeDBObject(con, null, null, null, ps);
		}
		return r;
	}
	
	public GameMasterRightDTO GetMasterRightInfo(int uid) {
		GameMasterRightDTO r=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			st=con.createStatement();
			String sql="select * from AccountsInfo where UserID="+uid;
			rs=st.executeQuery(sql);
			if(rs.next()){
				r=new GameMasterRightDTO();
				r.setUid(uid);
				r.setAccounts(rs.getString("Accounts"));
				r.setMasterRight(rs.getInt("MasterRight"));
				String right=Integer.toBinaryString(r.getMasterRight());
				String t="";
				for(int i=right.length();i<14;i++){
					t+="0";
				}
				/*#define UR_CAN_LIMIT_PLAY				0x00000001L				//允许禁止游戏
				#define UR_CAN_LIMIT_LOOKON				0x00000002L				//允许禁止旁观
				#define UR_CAN_LIMIT_WISPER				0x00000004L				//允许禁止私聊
				#define UR_CAN_LIMIT_ROOM_CHAT			0x00000008L				//允许禁止聊天
				#define UR_CAN_LIMIT_GAME_CHAT			0x00000010L				//允许禁止聊天
				#define UR_CAN_CUT_USER					0x00000020L				//允许踢出用户
				#define UR_CAN_FORBID_ACCOUNTS			0x00000040L				//允许封锁帐号
				#define UR_CAN_CONFINE_IP				0x00000080L				//允许禁止地址
				#define UR_CAN_SEE_USER_IP				0x00000100L				//允许查看地址
				#define UR_CAN_SEND_WARNING				0x00000200L				//允许发送警告
				#define UR_CAN_ISSUE_MESSAGE			0x00000400L				//允许发布消息
				#define UR_CAN_BIND_GAME				0x00000800L				//允许游戏绑定
				#define UR_CAN_BIND_GLOBAL				0x00001000L				//允许全局绑定
				#define UR_CAN_SERVER_OPTION			0x00002000L				//允许配置房间
				*/
				right=t+right;
				r.setMaster_CAN_SERVER_OPTION(right.substring(0,1));
				r.setMaster_CAN_BIND_GLOBAL(right.substring(1,2));
				r.setMaster_CAN_BIND_GAME(right.substring(2,3));
				r.setMaster_CAN_ISSUE_MESSAGE(right.substring(3,4));
				r.setMaster_CAN_SEND_WARNING(right.substring(4,5));
				r.setMaster_CAN_SEE_USER_IP(right.substring(5,6));
				r.setMaster_CAN_CONFINE_IP(right.substring(6,7));
				r.setMaster_CAN_FORBID_ACCOUNTS(right.substring(7,8));
				r.setMaster_CAN_CUT_USER(right.substring(8,9));
				r.setMaster_CAN_LIMIT_GAME_CHAT(right.substring(9,10));
				r.setMaster_CAN_LIMIT_ROOM_CHAT(right.substring(10,11));
				r.setMaster_CAN_LIMIT_WISPER(right.substring(11,12));
				r.setMaster_CAN_LIMIT_LOOKON(right.substring(12,13));
				r.setMaster_CAN_LIMIT_PLAY(right.substring(13,14));
				
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
			try {
				if(rs!=null){
					rs.close();rs=null;
				}
				if(st!=null){
					st.close();st=null;
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
	public GameMasterRightDTO SaveMasterRight(int uid,int newright) {
		GameMasterRightDTO r=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			st=con.createStatement();
			String sql="";
			if(newright>0)
				sql="update AccountsInfo set MasterOrder=1, MasterRight="+newright+" where UserID="+uid;
			else
				sql="update AccountsInfo set MasterOrder=0,MasterRight="+newright+" where UserID="+uid;
			st.executeUpdate(sql);	
				
			sql="select * from AccountsInfo where UserID="+uid;
			rs=st.executeQuery(sql);
			if(rs.next()){
				r=new GameMasterRightDTO();
				r.setUid(uid);
				r.setAccounts(rs.getString("Accounts"));
				r.setMasterRight(rs.getInt("MasterRight"));
				String right=Integer.toBinaryString(r.getMasterRight());
				String t="";
				for(int i=right.length();i<14;i++){
					t+="0";
				}
				/*#define UR_CAN_LIMIT_PLAY				0x00000001L				//允许禁止游戏
				#define UR_CAN_LIMIT_LOOKON				0x00000002L				//允许禁止旁观
				#define UR_CAN_LIMIT_WISPER				0x00000004L				//允许禁止私聊
				#define UR_CAN_LIMIT_ROOM_CHAT			0x00000008L				//允许禁止聊天
				#define UR_CAN_LIMIT_GAME_CHAT			0x00000010L				//允许禁止聊天
				#define UR_CAN_CUT_USER					0x00000020L				//允许踢出用户
				#define UR_CAN_FORBID_ACCOUNTS			0x00000040L				//允许封锁帐号
				#define UR_CAN_CONFINE_IP				0x00000080L				//允许禁止地址
				#define UR_CAN_SEE_USER_IP				0x00000100L				//允许查看地址
				#define UR_CAN_SEND_WARNING				0x00000200L				//允许发送警告
				#define UR_CAN_ISSUE_MESSAGE			0x00000400L				//允许发布消息
				#define UR_CAN_BIND_GAME				0x00000800L				//允许游戏绑定
				#define UR_CAN_BIND_GLOBAL				0x00001000L				//允许全局绑定
				#define UR_CAN_SERVER_OPTION			0x00002000L				//允许配置房间
				*/
				right=t+right;
				r.setMaster_CAN_SERVER_OPTION(right.substring(0,1));
				r.setMaster_CAN_BIND_GLOBAL(right.substring(1,2));
				r.setMaster_CAN_BIND_GAME(right.substring(2,3));
				r.setMaster_CAN_ISSUE_MESSAGE(right.substring(3,4));
				r.setMaster_CAN_SEND_WARNING(right.substring(4,5));
				r.setMaster_CAN_SEE_USER_IP(right.substring(5,6));
				r.setMaster_CAN_CONFINE_IP(right.substring(6,7));
				r.setMaster_CAN_FORBID_ACCOUNTS(right.substring(7,8));
				r.setMaster_CAN_CUT_USER(right.substring(8,9));
				r.setMaster_CAN_LIMIT_GAME_CHAT(right.substring(9,10));
				r.setMaster_CAN_LIMIT_ROOM_CHAT(right.substring(10,11));
				r.setMaster_CAN_LIMIT_WISPER(right.substring(11,12));
				r.setMaster_CAN_LIMIT_LOOKON(right.substring(12,13));
				r.setMaster_CAN_LIMIT_PLAY(right.substring(13,14));
				
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
			try {
				if(rs!=null){
					rs.close();rs=null;
				}
				if(st!=null){
					st.close();st=null;
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
	
	public Boolean UpdateMasterRight(int uid,int newright) {//
		Boolean r=false;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			st=con.createStatement();
			String sql="";
				sql="update AccountsInfo set  UserRight="+newright+" where UserID="+uid;
			
			st.executeUpdate(sql);	
				
			r=true;
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
			try {
				if(rs!=null){
					rs.close();rs=null;
				}
				if(st!=null){
					st.close();st=null;
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
	
	public Boolean UpdateGroupID(int uid,int groupid) {//
		Boolean r=false;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			st=con.createStatement();
			String sql="";
				sql="update AccountsInfo set  GroupID="+groupid+" where UserID="+uid;
			
			st.executeUpdate(sql);	
				
			r=true;
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
			try {
				if(rs!=null){
					rs.close();rs=null;
				}
				if(st!=null){
					st.close();st=null;
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
	
	
	/*==================================用户活动日志=====================================*/
	public List<GameUserDTO> GetUserLogsByPage(int pageindex,int pageSize,String orderby,String where) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "UserOperateLogs_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				int userID = rs.getInt("proxyID");
				dto.setUserID(userID);
				dto.setAccounts(rs.getString("proxyAccounts"));
				dto.setOperateDetails(rs.getString("operateDetails"));
				dto.setOperateIP(rs.getString("operateIP"));
				dto.setOperateTime(rs.getString("operateTime").substring(0, 19));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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
	
	
	public void deleteConfine(String userID) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from QPGameUserDB.dbo.ConfineContent where userID='"+userID+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
			
			sql = "delete from QPGameUserDB.dbo.AccountsInfo where userID='"+userID+"'";
			ps = con.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, null, null, null, ps);
		}
	}
	
	public List<GoldXhDTO> getLimit(int userID) {
    	List<GoldXhDTO> list = new ArrayList<GoldXhDTO>();
    	GoldXhDTO dto = null;
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    PreparedStatement ps1 = null;
	    ResultSet rs1 = null;
	    String limit = "";
	    String sql = "select * from QPAccountsDB.dbo.limitRelation where userID='"+userID+"'";
		try {
			con = ds.getConnection();
			if(userID==3){
				ps = con.prepareStatement("select * from QPAccountsDB.dbo.UserLimit");
				rs = ps.executeQuery();
				while (rs.next()) {
					dto = new GoldXhDTO();
					dto.setId(rs.getInt("id"));
					dto.setSx(rs.getString("limit_up"));
					dto.setXx(rs.getString("limit_down"));
					String c1 = rs.getString("chip_1");
					String c2 = rs.getString("chip_2");
					String c3 = rs.getString("chip_3");
					String c4 = rs.getString("chip_4");
					String c5 = rs.getString("chip_5");
					String xhNumber = c1+", "+c2+", "+c3+", "+c4+", "+c5;
					System.out.println("|"+rs.getInt("id")+"xhNumber:"+xhNumber);
					dto.setXhNumber(xhNumber);
					list.add(dto);
					}
				
			}
			else{
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					limit = rs.getString("limitID");
				}
			
			sql = "select * from QPAccountsDB.dbo.UserLimit where ID in("+limit+")";
			System.out.println("sql:"+sql);
			ps1 = con.prepareStatement(sql);
			rs1 = ps1.executeQuery();
			while (rs1.next()) {
				dto = new GoldXhDTO();
				dto.setId(rs1.getInt("id"));
				dto.setSx(rs1.getString("limit_up"));
				dto.setXx(rs1.getString("limit_down"));
				String c1 = rs1.getString("chip_1");
				String c2 = rs1.getString("chip_2");
				String c3 = rs1.getString("chip_3");
				String c4 = rs1.getString("chip_4");
				String c5 = rs1.getString("chip_5");
				String xhNumber = c1+", "+c2+", "+c3+", "+c4+", "+c5;
				System.out.println("|"+rs1.getInt("id")+"xhNumber:"+xhNumber);
				dto.setXhNumber(xhNumber);
				list.add(dto);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	public int queryMaxID(){
		int m = 0;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select max(id) from QPAccountsDB.dbo.UserLimit");
			rs = ps.executeQuery();
			if(rs.next()){
				m = rs.getInt(1);
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
		return m;
	}
	
	public int queryAndroidCount(int userID){
		int m = 0;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select count(*) from QPTreasureDB.dbo.AndroidManager where userID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				m = rs.getInt(1);
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
		return m;
	}
	
	public void addLimitRelation(String accounts,String qq){ //增加筹码类型关系
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		int userID = 0;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "select userID from QPAccountsDB.dbo.AccountsInfo where accounts='"+accounts+"'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				userID = rs.getInt(1);
			}
			
			sql = "insert into QPAccountsDB.dbo.limitRelation(limitID,userID) values(?,?)";
			ps1 = con.prepareStatement(sql);
			ps1.setString(1, qq);
			ps1.setInt(2, userID);
			ps1.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeDBObject(con, rs, null, null, ps);
		}
	}
	
	
	public long queryInsureScore(int userID){
		long insureScore = 0;
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select insureScore from QPTreasureDB.dbo.GameScoreInfo where userID=?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				insureScore = rs.getLong(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			this.closeDBObject(con, rs, null, null, ps);
		}
		return insureScore;
	}
	
	
	public Boolean excel(String targetfile,String where){
		Boolean r=false;
		Statement stat=null;
		ResultSet rs=null;
		Connection con = null;
		try 
		{ 
			//构建Workbook对象, 只读Workbook对象
			//Method 1：创建可写入的Excel工作薄
			//WritableWorkbook wwb = Workbook.createWorkbook(new File(filename));           //Method 2：将WritableWorkbook直接写入到输出流 
			OutputStream os = new FileOutputStream(targetfile);
			WritableWorkbook wwb = Workbook.createWorkbook(os);           
			//创建Excel工作表           
			WritableSheet ws = wwb.createSheet("充值卡列表", 0);
			Label labelC0 = new jxl.write.Label(0, 0, "手机号码"); 
			//Label labelC1 = new jxl.write.Label(1, 0, "充值卡卡号");
			//Label labelC2 = new jxl.write.Label(2, 0, "充值卡密码");
			//Label labelC3 = new jxl.write.Label(3, 0, "银子数量");
			//Label labelC4 = new jxl.write.Label(4, 0, "金额");
			//Label labelC4 = new jxl.write.Label(4, 0, "状态");
			ws.addCell(labelC0);
			/*ws.addCell(labelC1);
			ws.addCell(labelC2);
			ws.addCell(labelC3);
			ws.addCell(labelC4);*/
			con = ds.getConnection();
			stat=con.createStatement();
			
			String sql="select * from AccountsInfo where " +where+" order by RegisterMobile ";
			System.out.println(sql);
			rs = stat.executeQuery(sql);
			int temp_row=0;
			while (rs.next()){  
				/*
				 * 
				 * obj.setCompany(rs.getString("company"));
				obj.setWgf(rs.getFloat("wgf"));
				String start_date = rs.getString("start_date");
				start_date = start_date.substring(0, 10);
				obj.setStart_date(start_date);
				String end_date = rs.getString("end_date");
				end_date = end_date.substring(0, 10);
				obj.setEnd_date(end_date);
				obj.setFh(rs.getString("fh"));
				 * 
				 */
				temp_row ++;            
				String RegisterMobile = rs.getString("RegisterMobile"); 
				/*String cardPass = rs.getString("CardPassword");
				String score = rs.getString("Score");
				String batchNo=rs.getString("BatchNo");*/
				
				jxl.write.Label labelN0 = new jxl.write.Label(0, temp_row, RegisterMobile);            
				/*jxl.write.Label labelN1 = new jxl.write.Label(1, temp_row, cardNo);
				jxl.write.Label labelN2 = new jxl.write.Label(2, temp_row, cardPass);
				//jxl.write.Number labelN3 = new jxl.write.Number(3, temp_row, area);
				jxl.write.Label labelN3 = new jxl.write.Label(3, temp_row, score);
				jxl.write.Label labelN4 = new jxl.write.Label(4, temp_row, batchNo);*/
				ws.addCell(labelN0);
				/*ws.addCell(labelN1);
				ws.addCell(labelN2);
				ws.addCell(labelN3);
				ws.addCell(labelN4);*/
			}
			//写入Excel对象
			wwb.write();
			//关闭可写入的Excel对象
			wwb.close();
			r=true;
			if(rs!=null){
				rs.close();
			}
			if(stat!=null){
				stat.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					rs = null;
				}
			} catch (Exception e1) {
			}
		}
		return r;
	}
	
	public HttpServletResponse download(String path, HttpServletResponse response,String targetName) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            @SuppressWarnings("unused")
			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            targetName = new String(targetName.getBytes("GBK"),"ISO_8859_1"); 
            response.addHeader("Content-Disposition", "attachment;filename=" + targetName);//new String(targetName.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
	
	public List<GameUserDTO> getSpreaderInfo(int pageindex,int pageSize,String orderby,String where) {
		List<GameUserDTO> list = new ArrayList<GameUserDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			/*
			Create Procedure [dbo].[GetRecordByPage2005] 
				@TableName varchar(50),        --表名
				@Fields varchar(5000) = '*',    --字段名(全部字段为*)
				@OrderField varchar(5000),        --排序字段(必须!支持多字段)
				@sqlWhere varchar(5000) = Null,--条件语句(不用加where)
				@pageSize int,                    --每页多少条记录
				@pageIndex int = 1 ,            --指定当前为第几页
				@TotalPage int output,            --返回总页数 
				@totalRecord int output
			
			*/
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "RecordSpreaderInfoView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserDTO dto = new GameUserDTO();
				dto.setCreateDate(rs.getString("collectDate").substring(0, 19));
				dto.setScore(rs.getString("score"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setSpreaderAccounts(rs.getString("spreAccounts"));
				dto.setType(rs.getInt("typeID"));
				dto.setCollectNote(rs.getString("collectNote"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			con.setAutoCommit(false);
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
			if(con!=null){
				con.close();con=null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				if(toesUp!=null){
					toesUp.close();toesUp=null;
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

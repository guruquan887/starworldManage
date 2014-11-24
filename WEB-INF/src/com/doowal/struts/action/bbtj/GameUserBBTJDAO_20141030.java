package com.doowal.struts.action.bbtj;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class GameUserBBTJDAO_20141030 extends BaseDAO {
	
	private DataSource ds;
	private int pageCount;
	private int recordCount;

	public GameUserBBTJDAO_20141030(DataSource ds) {
		this.ds = ds;
	}
	 
	public int getTotalPage() {
		return pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	
	
	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
	}
	
	public String delBaob(String startTime,String endTime){
		String msg = "";
		Connection con = null;
		CallableStatement callstmt = null;
		String sql = "{ call GSP_GP_DelBaoB (?,?)}";
		try {
			con = ds.getConnection();
			callstmt = con.prepareCall(sql);
			callstmt.setString(1, startTime);
			callstmt.setString(2, endTime);
			callstmt.execute();
			msg = "删除成功！";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	//新程序
	public List<GameUserBBTJDTO> GetByTimeBRL(String where,String orderby) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select * from ProxyRecordBRLDrawPetView where "+where+" order by "+orderby+" desc";
		System.out.println("GetByTime sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				//for(int i = 0; i<scorelist.size();i++){
				//if(rs.getInt("proxyID") == scorelist.get(i).getProxyID()){
					dto.setProxyID(rs.getInt("proxyID"));
					dto.setAccounts(rs.getString("proxyAccounts"));
					int accountType = rs.getInt("accountType");
					dto.setAccountType(accountType);
					if(accountType==1){
						dto.setAccountTypeName("代理商");
					}
					else{
						dto.setAccountTypeName("员工");
					}
					//dto.setTzScore(scorelist.get(i).getTzScore());
					//dto.setWinlostScore(scorelist.get(i).getWinlostScore());
					//dto.setXmScore(scorelist.get(i).getXmScore());
					dto.setBrokerage(rs.getDouble("Brokerage"));
					//long xmyjScore = (long)(scorelist.get(i).getXmScore()*rs.getDouble("Brokerage"));
					//dto.setXmyjScore(xmyjScore);
					//long totalBetScore = scorelist.get(i).getWinlostScore()+xmyjScore;
					//dto.setTotalBetScore(totalBetScore);
					double winner = (rs.getDouble("winner"));
					dto.setWinner(winner);
					dto.setLevelID(rs.getInt("levelID"));
					//long SjWinnerScore = (long)(winner*totalBetScore);
					//dto.setSjWinnerScore(SjWinnerScore);
					list.add(dto);
					}
				//}
			//}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	
	public List<GameUserBBTJDTO> GetByTimeJJL(String where,String orderby) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select * from ProxyRecordJJLDrawView where "+where+" order by "+orderby+" desc";
		System.out.println("GetByTime sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				//for(int i = 0; i<scorelist.size();i++){
				//if(rs.getInt("proxyID") == scorelist.get(i).getProxyID()){
					dto.setProxyID(rs.getInt("proxyID"));
					dto.setAccounts(rs.getString("proxyAccounts"));
					int accountType = rs.getInt("accountType");
					dto.setAccountType(accountType);
					if(accountType==1){
						dto.setAccountTypeName("代理商");
					}
					else{
						dto.setAccountTypeName("员工");
					}
					//dto.setWinlostScore(scorelist.get(i).getWinlostScore());
					dto.setTaxrate(rs.getDouble("taxRate"));
					//dto.setRevenue(scorelist.get(i).getRevenue());
					//long revenueScore = (long)(scorelist.get(i).getRevenue()*rs.getDouble("taxRate"));
					//dto.setRevenueScore(revenueScore);
					//long totalBetScore = scorelist.get(i).getWinlostScore()+revenueScore;
					//dto.setTotalBetScore(totalBetScore);
					dto.setLevelID(rs.getInt("levelID"));
					double agentTaxrate = ((1 - rs.getDouble("taxRate")));
					dto.setAgentTaxrate(agentTaxrate);
					//long sjRevenueScore = (long)(agentTaxrate*totalBetScore);
					//dto.setSjRevenueScore(sjRevenueScore);
				list.add(dto);
					}
				//}
			//}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	
	public List<GameUserBBTJDTO> GetBaoBBRLTimeList(String where,List<GameUserBBTJDTO> scorelist) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="SELECT  SUM(ScoreXiMaLiang) AS ScoreXiMaLiang, SUM(ScorePet) AS ScorePet, SUM(ScoreRevenue) AS ScoreRevenue, SUM(ScoreWin) - SUM(ScoreLose) AS ScoreWinLose,"
                  +" ProxyID, GameType FROM  QPTreasureDB.dbo.RecordDrawPetDayTotal WHERE (GameType = 0)"+where+" GROUP BY ProxyID,GameType";
		System.out.println("GetByTime sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				for(int i = 0; i<scorelist.size();i++){
				
				if(scorelist.get(i).getProxyID() == rs.getInt("proxyID")){
				dto.setProxyID(rs.getInt("proxyID"));
				dto.setAccounts(scorelist.get(i).getAccounts());
				int accountType = scorelist.get(i).getAccountType();
				if(accountType==1){
					dto.setAccountTypeName("代理商");
				}
				else{
					dto.setAccountTypeName("员工");
				}
				dto.setBrokerage(scorelist.get(i).getBrokerage()*100);
				//double winner = (1 - scorelist.get(i).getWinner());
				
				BigDecimal a1 = new BigDecimal(Double.toString(1));
				BigDecimal b1 = new BigDecimal(Double.toString(scorelist.get(i).getWinner()));
				double winner = a1.subtract(b1).doubleValue();
				
				System.out.println("输赢占成："+winner);
				dto.setWinner(winner*100);
				dto.setTzScore(rs.getLong("ScorePet"));
				dto.setWinlostScore(rs.getLong("ScoreWinLose"));
				dto.setXmScore(rs.getLong("ScoreXiMaLiang"));
				
				long xmyjScore = (long)(rs.getLong("ScoreXiMaLiang")*scorelist.get(i).getBrokerage());
				dto.setXmyjScore(xmyjScore);
				long totalBetScore = rs.getLong("ScoreWinLose")+xmyjScore;
				dto.setTotalBetScore(totalBetScore);
				
				long SjWinnerScore = (long)(winner*totalBetScore);
				dto.setSjWinnerScore(SjWinnerScore);
				
				
				//统计合计
			    long totalTzScore = 0;
			    long totalWinloseScore = 0;
			    long totalXmScore = 0;
			    long totalXmYjScore = 0;
			    long totalAllBetScore = 0;
			    long totalSjWinnerScore = 0;
			    
			    
				list.add(dto);
					}
				}
				
			}
			
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	
	
	public List<GameUserBBTJDTO> GetBaoBJJLTimeList(String where,List<GameUserBBTJDTO> scorelist) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="SELECT     SUM(ScoreRevenue) AS ScoreRevenue, SUM(ScoreWin) - SUM(ScoreLose) AS ScoreWinLose, ProxyID, GameType"
			+" FROM QPTreasureDB.dbo.RecordDrawPetDayTotal WHERE (GameType = 1)"+where+" GROUP BY ProxyID, GameType";
		System.out.println("GetByTime sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				for(int i = 0; i<scorelist.size();i++){
				
				if(scorelist.get(i).getProxyID() == rs.getInt("proxyID")){
				dto.setProxyID(rs.getInt("proxyID"));
				dto.setAccounts(scorelist.get(i).getAccounts());
				int accountType = scorelist.get(i).getAccountType();
				if(accountType==1){
					dto.setAccountTypeName("代理商");
				}
				else{
					dto.setAccountTypeName("员工");
				}
				dto.setTaxrate(scorelist.get(i).getTaxrate()*100);
				//double agentTaxrate = ((1 - scorelist.get(i).getTaxrate()));
				
				BigDecimal a1 = new BigDecimal(Double.toString(1));
				BigDecimal b1 = new BigDecimal(Double.toString(scorelist.get(i).getTaxrate()));
				double agentTaxrate = a1.subtract(b1).doubleValue();
				
				dto.setAgentTaxrate(agentTaxrate*100);
				dto.setWinlostScore(rs.getLong("ScoreWinLose"));
				
				dto.setRevenue(rs.getLong("ScoreRevenue"));
				long revenueScore = (long)(rs.getLong("ScoreRevenue")*scorelist.get(i).getTaxrate());
				dto.setRevenueScore(revenueScore);
				long totalBetScore = rs.getLong("ScoreWinLose")+revenueScore;
				dto.setTotalBetScore(totalBetScore);
				
				long sjRevenueScore = (long)(agentTaxrate*totalBetScore);
				dto.setSjRevenueScore(sjRevenueScore);
				
				list.add(dto);
				
					}
				}
				
			}
			
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	public GameUserBBTJDTO GetBaoBSUMBRLList(String where,String where1) { //合计
		GameUserBBTJDTO dto = new GameUserBBTJDTO();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select sum(ScoreXiMaLiang) as ScoreXiMaLiang,sum(ScorePet) as ScorePet,QPAccountsDB.dbo.AccountsProxy.prevProxy,(sum(ScoreWin)-sum(Scorelose)) as ScoreWinLose from RecordDrawPetDayTotal" 
+" left outer join QPAccountsDB.dbo.AccountsProxy on QPAccountsDB.dbo.AccountsProxy.proxyID=RecordDrawPetDayTotal.proxyID"
+" where "+where+where1+" and GameType=0 group by prevProxy ";
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setTotalTzScore(rs.getLong("ScorePet"));
				dto.setTotalWinloseScore(rs.getLong("ScoreWinLose"));
				dto.setTotalXmScore(rs.getLong("ScoreXiMaLiang"));
				
				
/*				dto.setTzScore(rs.getLong("ScorePet"));
				dto.setWinlostScore(rs.getLong("ScoreWinLose"));
				dto.setXmScore(rs.getLong("ScoreXiMaLiang"));
				dto.setBrokerage(rs.getDouble("Brokerage")*100);
				long xmyjScore = (long)(rs.getLong("ScoreXiMaLiang")*rs.getDouble("Brokerage"));
				dto.setXmyjScore(xmyjScore);
				long totalBetScore = rs.getLong("ScoreWinLose")+xmyjScore;
				dto.setTotalBetScore(totalBetScore);
				double winner = (1 - rs.getDouble("winner"));
				dto.setWinner(winner*100);
				long SjWinnerScore = (long)(winner*totalBetScore);
				dto.setSjWinnerScore(SjWinnerScore);*/
				
		    }
			
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}	
	
	public String getKindName(int kindID){
		String kindName = "";
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select kindName from QPPlatformDB.dbo.GameKindItem where kindID="+kindID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				kindName = rs.getString("kindName");
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return kindName;
	}
	
	public GameUserBBTJDTO GetBaoBSUMJJLList(String where,String where1) { //合计
		GameUserBBTJDTO dto = new GameUserBBTJDTO();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select sum(ScoreRevenue) as ScoreRevenue,QPAccountsDB.dbo.AccountsProxy.prevProxy,(sum(ScoreWin)-sum(Scorelose)) as ScoreWinLose from RecordDrawPetDayTotal" 
+" left outer join QPAccountsDB.dbo.AccountsProxy on QPAccountsDB.dbo.AccountsProxy.proxyID=RecordDrawPetDayTotal.proxyID"
+" where "+where+where1+" and GameType=1 group by prevProxy ";
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setTotalRevenue(rs.getLong("ScoreRevenue"));
				dto.setTotalWinloseScore(rs.getLong("ScoreWinLose"));
				
		    }
			
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	//获取上级的左右树数值
	public GameUserBBTJDTO getLRStatus(int agentID){
		GameUserBBTJDTO dto = new GameUserBBTJDTO();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from QPAccountsDB.dbo.AccountsProxy where proxyID="+agentID;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setLStatus(rs.getInt("lStatus"));
				dto.setRStatus(rs.getInt("rStatus"));
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	
	//获取百人类报表统计的列表信息
	public List<GameUserBBTJDTO> GetBaoBBRLList(String where,String orderby,int agentID) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		List<GameUserBBTJDTO> list1= new ArrayList<GameUserBBTJDTO>();
		List<GameUserBBTJDTO> list2= new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "";
		int levelID = 0;
		//String sql ="select * from QPTreasureDB.dbo.ProxyRecordBRLDrawPetView where "+where;
		
		try{
			con = ds.getConnection();
			GameUserBBTJDTO lrdto = getLRStatus(agentID);
			//此sql获取下面最下级的代理信息
			sql = "select * from QPAccountsDB.dbo.AccountsProxy where levelID=(select max(levelID) from QPAccountsDB.dbo.AccountsProxy where LStatus>"+lrdto.getLStatus()+" and rStatus<"+lrdto.getRStatus()+")";
			System.out.println("GetBy百人类List sql:"+sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setProxyID(rs.getInt("proxyID"));
				dto.setAccounts(rs.getString("proxyAccounts"));
				dto.setLevelID(rs.getInt("levelID"));
				dto.setBrokerage(rs.getDouble("Brokerage"));
				dto.setTaxrate(rs.getDouble("TaxRate"));
				dto.setWinner(rs.getDouble("winner"));
				dto.setPrevProxy(rs.getInt("prevProxy"));
				list.add(dto);
			}
			
			//获得最下级的代理下面的会员输赢、投注金额的合计
			if(list.size()>0){
				for(int i = 0;i<list.size();i++){//循环此下级的多个汇总金额
					sql = "select proxyID,kindID,sum(ScoreWaste) as winlostScore ,sum(ScorePet) as tzScore,sum(ScoreXiMa) as ximaScore "+
						" from QPTreasureDB.dbo.RecordDrawPet where proxyID="+list.get(i).getProxyID()+" group by proxyID,kindID";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					if(list.get(i).getProxyID() == rs.getInt("proxyID")){
					GameUserBBTJDTO dto = new GameUserBBTJDTO();
					long winlostScore = rs.getLong("winlostScore");
					long scorePet = rs.getLong("tzScore");
					long ximaScore = rs.getLong("ximaScore");
					double Brokerage = list.get(i).getBrokerage();
					//double Taxrate = list.get(i).getTaxrate();
					double Winner = list.get(i).getWinner();
					levelID = list.get(i).getLevelID();
					dto.setProxyID(rs.getInt("proxyID"));
					BigDecimal a1 = new BigDecimal(Double.toString(1));
					BigDecimal b1 = new BigDecimal(Double.toString(Winner));
					double sjwinner = a1.subtract(b1).doubleValue();
					
					if(levelID==1){
						dto.setAccountTypeName("公司");
					}
					else if(levelID==2){
						dto.setAccountTypeName("大股东");
					}
					else if(levelID==3){
						dto.setAccountTypeName("总代理");
					}
					else if(levelID==4){
						dto.setAccountTypeName("代理");
					}
					else if(levelID==5){
						dto.setAccountTypeName("推广员");
					}  //用户级别
					dto.setAccounts(list.get(i).getAccounts());//用户名
					dto.setPrevProxy(list.get(i).getPrevProxy());//获取上级用户ID
					dto.setTzScore(scorePet);//投注金额
					dto.setWinlostScore(winlostScore);//输赢金额
					dto.setXmScore(ximaScore);//洗码量
					dto.setBrokerage(Brokerage*100);//洗码比
					long xmyjScore = (long)(ximaScore*Brokerage);
					dto.setXmyjScore(xmyjScore);//洗码佣金
					dto.setTotalScore(winlostScore+xmyjScore);//总金额
					dto.setWinner(Winner*100);//股份占成
					long sjWinnerScore = (long)(dto.getTotalScore()*sjwinner);
					dto.setSjWinnerScore(sjWinnerScore);//上交金额
					dto.setSjTzScore((long)(dto.getTzScore()*sjwinner));//上交投注金额
					dto.setSjXimaScore((long)(dto.getXmScore()*sjwinner));//上交洗码量
					
					float size = (float)dto.getSjWinnerScore()/dto.getSjTzScore()*100;
					size = Math.abs(size);
					DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
					String filesize = df.format(size);//返回的是String类型的
					dto.setGsRadio(filesize);//公司获利比
					int kindID = rs.getInt("kindID");//游戏ID
					dto.setKindId(kindID);
					String kindName = getKindName(kindID);
					dto.setKindName(kindName);
					list1.add(dto);//加入list对象中
					
						}
      				}
				}
			}
			System.out.println("list1.size():"+list1.size());
			//==================================获取最下级统计信息结束======================================
			//往上查找上级信息,一直查到公司级别的报表信息
			if(levelID!=1){
				list2 = GetProPrevBRLList(list1);
				for(int i=2;i<levelID;){
					list2 = GetProPrevBRLList(list2);
					levelID = levelID-1;
					
					System.out.println("list2.size:"+list2.size()+"&levelID:"+levelID);
				}
			}
			
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list2;
	}
	
	//公共调用方法，获取上级的报表
	public List<GameUserBBTJDTO> GetProPrevBRLList(List<GameUserBBTJDTO> list) {
		List<GameUserBBTJDTO> list1= new ArrayList<GameUserBBTJDTO>();
		List<GameUserBBTJDTO> list2= new ArrayList<GameUserBBTJDTO>();//list2代表下级直属会员对象报表
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "";
		long winlostScore = 0;
		long scorePet = 0;
		long ximaScore = 0;
		try{
			con = ds.getConnection();
			//往上查找上级信息,第一次进来汇总总代理，第二次进来汇总股东，第三次进来汇总公司；
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					
					//汇总此级别下的直属会员报表
					sql = "select proxyID,kindID,sum(ScoreWaste) as winlostScore ,sum(ScorePet) as tzScore,sum(ScoreXiMa) as ximaScore "+
					" from QPTreasureDB.dbo.RecordDrawPet where proxyID="+list.get(i).getPrevProxy()+" group by proxyID,kindID";
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						while(rs.next()){
							GameUserBBTJDTO dto = new GameUserBBTJDTO();
							//winlostScore = rs.getLong("winlostScore");
							//scorePet = rs.getLong("tzScore");
							//ximaScore = rs.getLong("ximaScore");
							dto.setProxyID(rs.getInt("proxyID"));
							dto.setKindId(rs.getInt("kindId"));
							dto.setWinlostScore(rs.getLong("winlostScore"));
							dto.setTzScore(rs.getLong("tzScore"));
							dto.setXmScore(rs.getLong("ximaScore"));
							System.out.println("proxyID:"+rs.getInt("proxyID")+"&kindID:"+dto.getKindId()+"&winlostScore:"+dto.getWinlostScore()+"&TzScore:"+dto.getTzScore()+"&XmScore:"+dto.getXmScore());
							list2.add(dto);
						}
						System.out.println(">>>>>>>>list2.size："+list2.size());
					sql = "select * from QPAccountsDB.dbo.AccountsProxy where proxyID="+list.get(i).getPrevProxy();
					System.out.println("上级查询信息："+sql);
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()){
						GameUserBBTJDTO dto = new GameUserBBTJDTO();
						System.out.println("list2.get(i).getProxyID():"+list.get(i).getPrevProxy()+"&proxyID:"+rs.getInt("proxyID"));
					if(list.get(i).getPrevProxy() == rs.getInt("proxyID")){
						System.out.println("传递进来的kindID："+list.get(i).getKindId());

						if(list2.size()>0){
							//if(list.get(i).getKindId()==list2.get(i).getKindId()){
								
								winlostScore = list.get(i).getWinlostScore()+list2.get(i).getWinlostScore();
								scorePet = list.get(i).getTzScore()+list2.get(i).getTzScore();
								ximaScore = list.get(i).getXmScore()+list2.get(i).getXmScore();
								System.out.println("=========第一个IF"+i);
							//}
						}
						else{
							winlostScore = list.get(i).getWinlostScore();
							scorePet = list.get(i).getTzScore();
							ximaScore = list.get(i).getXmScore();
							System.out.println("------------第二个IF"+i);
						}
						System.out.println("=====================================================进入循环汇总上级：winlostScore:"+winlostScore+" scorePet:"+scorePet+" ximaScore:"+ximaScore);
						double Brokerage = rs.getDouble("Brokerage");
						double Winner = rs.getDouble("winner");
						dto.setPrevProxy(rs.getInt("prevProxy"));
						int levelID = rs.getInt("levelID");
						if(levelID==1){
							dto.setAccountTypeName("公司");
						}
						else if(levelID==2){
							dto.setAccountTypeName("大股东");
						}
						else if(levelID==3){
							dto.setAccountTypeName("总代理");
						}
						else if(levelID==4){
							dto.setAccountTypeName("代理");
						}
						else if(levelID==5){
							dto.setAccountTypeName("推广员");
						}  //用户级别
						dto.setProxyID(rs.getInt("proxyID"));
						BigDecimal a1 = new BigDecimal(Double.toString(1));
						BigDecimal b1 = new BigDecimal(Double.toString(Winner));
						BigDecimal c1 = new BigDecimal(Double.toString(list.get(i).getWinner()*0.01));
						double sjwinner = a1.subtract(b1).doubleValue();
						//Winner = b1.subtract(c1).doubleValue();//获取此上级的真实股份
						dto.setAccounts(rs.getString("proxyAccounts"));//用户名
						dto.setTzScore(scorePet);//投注金额
						dto.setWinlostScore(winlostScore);//输赢金额
						dto.setXmScore(ximaScore);//洗码量
						dto.setBrokerage(Brokerage*100);//洗码比
						long xmyjScore = (long)(ximaScore*Brokerage);
						dto.setXmyjScore(xmyjScore);//洗码佣金
						dto.setTotalScore(winlostScore+xmyjScore);//总金额
						dto.setWinner(Winner*100);//股份占成
						long sjWinnerScore = (long)(dto.getTotalScore()*sjwinner);
						dto.setSjWinnerScore(sjWinnerScore);//上交金额
						dto.setSjTzScore((long)(dto.getTzScore()*sjwinner));//上交投注金额
						dto.setSjXimaScore((long)(dto.getXmScore()*sjwinner));//上交洗码量
						
						float size = (float)dto.getSjWinnerScore()/dto.getSjTzScore()*100;
						size = Math.abs(size);
						DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
						String filesize = df.format(size);//返回的是String类型的
						dto.setGsRadio(filesize);//公司获利比
						dto.setKindName(list.get(i).getKindName());
						dto.setKindId(list.get(i).getKindId());
						list1.add(dto);
						}
					}
				}
			}
	
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list1;
	}
	
	public List<GameUserBBTJDTO> GetBaoBJJLList(String where,String orderby) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select * from QPTreasureDB.dbo.ProxyRecordJJLDrawView where "+where;
		System.out.println("GetByTime sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setProxyID(rs.getInt("proxyID"));
				dto.setAccounts(rs.getString("proxyAccounts"));
				/*int accountType = rs.getInt("accountType");
				if(accountType==1){
					dto.setAccountTypeName("代理商");
				}
				else{
					dto.setAccountTypeName("员工");
				}*/
				
				int levelID = rs.getInt("levelID");
				if(levelID==1){
					dto.setAccountTypeName("公司");
				}
				else if(levelID==2){
					dto.setAccountTypeName("大股东");
				}
				else if(levelID==3){
					dto.setAccountTypeName("总代理");
				}
				else if(levelID==4){
					dto.setAccountTypeName("代理");
				}
				else if(levelID==5){
					dto.setAccountTypeName("推广员");
				}
				dto.setWinlostScore(rs.getLong("ScoreWinLose"));
				dto.setTaxrate(rs.getDouble("taxRate")*100);
				dto.setRevenue(rs.getLong("ScoreRevenue"));
				long revenueScore = (long)(rs.getLong("ScoreRevenue")*rs.getDouble("taxRate"));
				dto.setRevenueScore(revenueScore);
				long totalBetScore = rs.getLong("ScoreWinLose")+revenueScore;
				dto.setTotalBetScore(totalBetScore);
				BigDecimal a1 = new BigDecimal(Double.toString(1));
				BigDecimal b1 = new BigDecimal(Double.toString(rs.getDouble("taxRate")));
				double agentTaxrate = a1.subtract(b1).doubleValue();
				//double agentTaxrate = ((1 - rs.getDouble("taxRate")));
				dto.setAgentTaxrate(agentTaxrate*100);
				long sjRevenueScore = (long)(agentTaxrate*totalBetScore);
				dto.setSjRevenueScore(sjRevenueScore);
				dto.setLevelID(rs.getInt("levelID"));
				
				list.add(dto);
		    }
			
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	
	
	public List<GameUserBBTJDTO> GetByPage(int pageindex,int pageSize,String where,String orderby) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement toesUp = null;
		try {
			
			con = ds.getConnection();
			con.setAutoCommit(false); // Setup the call. 
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?) }"); 
			toesUp.setString(1, "BBTJ_GameZongRecord_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setRegAccounts(rs.getString("regAccounts"));
				dto.setTotalBetScore(rs.getLong("betRecord"));
				dto.setTotalGoldScore(rs.getLong("goldRecord"));
				dto.setScore(rs.getLong("score"));
				dto.setBjlZC(rs.getFloat("bjlZC"));
				dto.setBjlYJ(rs.getFloat("bjlYJ"));
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	
	public GameUserBBTJDTO GetBySum() {
		GameUserBBTJDTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select sum(betRecord) as betRecord,sum(goldRecord) as goldRecord,sum(score) as score from BBTJ_GameZongRecord_View";
		System.out.println("报表统计汇总-sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new GameUserBBTJDTO();
				dto.setScore(rs.getLong("score"));
				dto.setTotalBetScore(rs.getLong("betRecord"));
				dto.setTotalGoldScore(rs.getLong("goldRecord"));
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public GameUserBBTJDTO GetBySum(String where,String orderby) {
		GameUserBBTJDTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select sum(betRecord) as betRecord,sum(goldRecord) as goldRecord,sum(score) as score from BBTJ_GameZongRecord_View where "+where;
		System.out.println("报表统计汇总 byTime-sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new GameUserBBTJDTO();
				dto.setScore(rs.getLong("score"));
				dto.setTotalBetScore(rs.getLong("betRecord"));
				dto.setTotalGoldScore(rs.getLong("goldRecord"));
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public List<GameUserBBTJDTO> GetDayByPage(int pageindex,int pageSize,String where,String orderby,List<GameUserBBTJDTO> listOfScoreByYesterday) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
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
			//toesUp.setString(1, "BBTJ_UserDoByTime");//表名 
			toesUp.setString(1, "BBTJ_DayGetByPage_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			
			while(rs.next()){
				if(listOfScoreByYesterday.size() > 0){
					for(int i = 0; i<listOfScoreByYesterday.size();i++){
					if(rs.getInt("userID") == listOfScoreByYesterday.get(i).getUserID()){
					GameUserBBTJDTO dto = new GameUserBBTJDTO();
					dto.setUserID(rs.getInt("userID"));
					dto.setAccounts(rs.getString("accounts"));
					dto.setRegAccounts(rs.getString("regAccounts"));
					dto.setTotalBetScore(rs.getLong("betRecord"));
					dto.setTotalGoldScore(rs.getLong("goldRecord"));
					dto.setScore(rs.getLong("score"));
					dto.setYesterdayGold(listOfScoreByYesterday.get(i).getYesterdayGold());
					dto.setYesterdayGoldName(listOfScoreByYesterday.get(i).getYesterdayGoldName());
					list.add(dto);
					break;
					}else{
					       if(i==(listOfScoreByYesterday.size()-1)){
						       GameUserBBTJDTO dto = new GameUserBBTJDTO();
								dto.setUserID(rs.getInt("userID"));
								dto.setAccounts(rs.getString("accounts"));
								dto.setRegAccounts(rs.getString("regAccounts"));
								dto.setTotalBetScore(rs.getLong("betRecord"));
								dto.setTotalGoldScore(rs.getLong("goldRecord"));
								dto.setScore(rs.getLong("score"));
								dto.setYesterdayGold(0);
								dto.setYesterdayGoldName(listOfScoreByYesterday.get(0).getYesterdayGoldName());
								list.add(dto);
					       }
					     }
					}
				}else{
						GameUserBBTJDTO dto = new GameUserBBTJDTO();
						dto.setUserID(rs.getInt("userID"));
						dto.setAccounts(rs.getString("accounts"));
						dto.setRegAccounts(rs.getString("regAccounts"));
						dto.setTotalBetScore(rs.getLong("betRecord"));
						dto.setTotalGoldScore(rs.getLong("goldRecord"));
						dto.setScore(rs.getLong("score"));
						dto.setYesterdayGold(0);
						dto.setYesterdayGoldName("未记录数据");
						
						list.add(dto);
				}
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	
	public List<GameUserBBTJDTO> GetByTime(String where,String orderby,List<GameUserBBTJDTO> scorelist) {
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select sum(betRecord) as betRecord,sum(goldRecord) as goldRecord,sum(score) as score,userID,accounts from BBTJ_GameTimeRecord_View where "+where+" group by userID,accounts"+" order by "+orderby+" desc";
		System.out.println("GetByTime sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				GameUserBBTJDTO dto = new GameUserBBTJDTO();
				for(int i = 0; i<scorelist.size();i++){
				if(rs.getInt("userID") == scorelist.get(i).getUserID()){
				dto.setScore(scorelist.get(i).getScore());
				dto.setTotalBetScore(rs.getLong("betRecord"));
				dto.setTotalGoldScore(rs.getLong("goldRecord"));
				dto.setUserID(rs.getInt("userID"));
				dto.setScore(rs.getLong("score"));
				dto.setAccounts(rs.getString("accounts"));
				dto.setRegAccounts(rs.getString("regAccounts"));
				//dto.setYesterdayGold(scorelist.get(i).getYesterdayGold());
				//dto.setYesterdayGoldName(scorelist.get(i).getYesterdayGoldName());
				list.add(dto);
					}
				}
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return list;
	}
	
	public GameUserBBTJDTO GetByTimeSum(String where,String orderby,long score) {
		GameUserBBTJDTO dto = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="select sum(betRecord) as betRecord,sum(goldRecord) as goldRecord,sum(score) as score from BBTJ_GameTimeRecord_View where "+where;
		System.out.println("报表统计汇总 byTime-sql:"+sql);
		try{
		
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new GameUserBBTJDTO();
				dto.setScore(score);
				dto.setTotalBetScore(rs.getLong("betRecord"));
				dto.setTotalGoldScore(rs.getLong("goldRecord"));
			}
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(ps!=null){
				ps.close();ps=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, null, ps);
		}
		return dto;
	}
	
	public List<GameUserBBTJDTO> queryScore(){   //获得报表统计的用户银子
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from BBTJ_GameZongRecord_View";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	GameUserBBTJDTO dto = new GameUserBBTJDTO();
            	dto.setScore(rs.getLong("score"));
            	dto.setUserID(rs.getInt("userId"));
            	list.add(dto);
            }
            
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		
		return list;
	}
	
	public long GetScore(String userName){
		long score = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select sum(score) as score from BBTJ_GameZongRecord_View where  accounts like '%"+userName+"%'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	score = rs.getLong("score");
            }
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return score;
	}
	public long GetScoreByID(int userId){
		long score = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select score from BBTJ_GameZongRecord_View where userId="+userId;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	score = rs.getLong("score");
            }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return score;
	}
	
	//太阳城报表详情记录
	public List<GameRecordDTO> GetDetailOfGameRecordByPage(int pageindex,int pageSize,String where,String orderby) {
		List<GameRecordDTO> list = new ArrayList<GameRecordDTO>();
		Connection con = null;
		ResultSet rs=null;
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
			toesUp.setString(1, "BBTJ_GameBetRecordDetails_View");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderby+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, pageSize);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				GameRecordDTO dto = new GameRecordDTO();
				dto.setUserID(rs.getInt("userID"));
				dto.setAccounts(rs.getString("accounts"));
				String generateTime = rs.getString("generateTime");
				generateTime = generateTime.substring(0, 19);
				dto.setGenerateTime(generateTime);//下注时间
				String generateTime1 = rs.getString("generateTime1");//开牌时间
				if(generateTime1==null){
					generateTime1="";
				}
				else if(generateTime1.length()>19){
					generateTime1 = generateTime1.substring(0, 19);
				}
				dto.setGenerateTime1(generateTime1);
				dto.setBetGold(rs.getDouble("betGold"));
				dto.setBetRate(rs.getDouble("betRate"));
				dto.setBetSerial(rs.getString("betserial"));
				dto.setGameAreaName(rs.getString("gameAreaName"));
				dto.setBetResult(rs.getDouble("betResult"));
				dto.setKindName(rs.getString("kindName"));
				dto.setWinArea(rs.getString("winArea"));
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			
			if(con!=null){
				con.close();con=null;
			}
			if(rs!=null){
				rs.close();rs=null;
			}
			if(toesUp!=null){
				toesUp.close();toesUp=null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.closeDBObject(con, rs, null, toesUp, null);
		}
		return list;
	}
	
	//获取往天的银子额度
	public List<GameUserBBTJDTO> GetListOfScoreByYesterday(String where){
		List<GameUserBBTJDTO> list = new ArrayList<GameUserBBTJDTO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from moneyRecord_View where "+where+"+"+1;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
            while(rs.next()){
            	GameUserBBTJDTO dto = new GameUserBBTJDTO();
            	dto.setYesterdayGold(rs.getLong("curmoney"));
            	dto.setUserID(rs.getInt("userId"));
            	String name = rs.getString("generateTime").substring(0,10);
            	dto.setYesterdayGoldName(name);
            	list.add(dto);
            }
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDBObject(con, rs, null, null, ps);
		}
		return list;
	 }
	
	

}

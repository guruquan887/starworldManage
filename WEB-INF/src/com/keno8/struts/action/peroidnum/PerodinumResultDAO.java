package com.keno8.struts.action.peroidnum;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.keno8.struts.action.handicap.HandicapDAO;
import com.keno8.struts.dao.GradeExpregferenceDAO;
import com.keno8.struts.dao.OperateLogsDAO;
import com.keno8.struts.dao.UserGoldFlowDAO;
import com.keno8.struts.dto.GamePeroidnumResultDTO;
import com.keno8.struts.dto.GameRecordDTO;
import com.keno8.struts.dto.GradeExpregferenceDTO;
import com.keno8.struts.dto.OperateDTO;
import com.keno8.struts.dto.UserGoldFlowDTO;


public class PerodinumResultDAO {
	
	private DataSource ds;
	//private Connection con;
	//private PreparedStatement ps;
	//private ResultSet rs;
	private int pageCount;
	private int recordCount;
	public float RATE1 = 0;
	
	public PerodinumResultDAO(DataSource ds) {
		this.ds = ds;
	}
	
	
	public List<PeroidnumResultDTO> GetRecordByPage(int pageindex,int pageSize,String where,String orderBy) {
		List<PeroidnumResultDTO> list = new ArrayList<PeroidnumResultDTO>();
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
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?)}"); 
			toesUp.setString(1, "gamePeroidnumView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, orderBy+" desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				PeroidnumResultDTO dto = new PeroidnumResultDTO();
				dto.setId(rs.getInt("id"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setRoomName(rs.getString("roomName"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				
				dto.setRetroType(rs.getInt("retroType"));
				int roomId = rs.getInt("roomId");
				if(rs.getString("createTime")!=null){
					dto.setCreateTime(rs.getString("createTime").substring(0, 19));
				}
				String awardnum = rs.getString("awardnum");//20位开奖号码
				String [] num = new String[20];
				num = awardnum.split(",");
				if(roomId==20){
					num = sort(num);
				}
				dto.setAwardnum1(num);
				String[] color1 = new String[20];
				String color = "";
				if("?".equals(awardnum)||"0".equals(awardnum)){
					color = "";
				}
				else{
				for(int i = 0; i< 20;i++){
					if(Integer.parseInt(num[i])>0&&Integer.parseInt(num[i])<=30){
						color = "../images/b.png";
					}
					else if(Integer.parseInt(num[i])>30&&Integer.parseInt(num[i])<=60){
						color = "../images/g.png";
					}
					else if(Integer.parseInt(num[i])>60&&Integer.parseInt(num[i])<=80){
						color = "../images/r.png";
					}
					else{
						color = "";
						}
					color1[i] = color;
					}
				}
				dto.setColor(color1);
				int single = rs.getInt("single");
				int large = rs.getInt("large");
				int serpent = rs.getInt("serpent");
				int peace = rs.getInt("peace");
				int tiger = rs.getInt("tiger");
				int small = rs.getInt("small");
				int pairs = rs.getInt("pairs");
				if(single==1){
					dto.setSingleName("单");
				}
				if(pairs==1){
					dto.setSingleName("双");
				}
				if(large==1){
					dto.setLargeName("大");
				}
				if(small==1){
					dto.setLargeName("小");
				}
				if(serpent==1){
					dto.setSerpentName("龙");
				}
				if(peace==1){
					dto.setSerpentName("和");
				}
				if(tiger==1){
					dto.setSerpentName("虎");
				}
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(true);
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
	
	public static String[] sort(String[] m) 
    { 
        int intLenth = m.length; 
        /*执行intLenth次*/ 
        for (int i = intLenth-1; i >0; i--) 
        { 
            /*每执行一次，将最小的数排在后面*/ 
            for (int j = 0; j < i; j++) 
            { 
                String a = m[j]; 
                String b = m[j + 1]; 
                if (Integer.parseInt(a) > Integer.parseInt(b)) 
                { 
                    m[j] = b; 
                    m[j + 1] = a; 
                } 
            } 
        } 
        return m; 
    } 
	
	
	public List<PeroidnumResultDTO> Get3DShiShiLeRecordByPage(int pageindex,int pageSize,String where) {
		List<PeroidnumResultDTO> list = new ArrayList<PeroidnumResultDTO>();
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
			toesUp = con.prepareCall("{  call GetRecordByPage2005(?,?,?,?,?,?,?,?)}"); 
			toesUp.setString(1, "gamePeroidnumView");//表名 
			toesUp.setString(2, "*");//全部字段为*
			toesUp.setString(3, "peroidnum desc");//排序字段
			toesUp.setString(4, where);//条件语句(不用加where)
			toesUp.setInt(5, 30);//每页多少条记录
			toesUp.setInt(6, pageindex);//指定当前为第几页
			toesUp.registerOutParameter(7, Types.INTEGER); 
			toesUp.registerOutParameter(8, Types.INTEGER); 
			rs=toesUp.executeQuery();
			while(rs.next()){
				PeroidnumResultDTO dto = new PeroidnumResultDTO();
				dto.setId(rs.getInt("id"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setRoomName(rs.getString("roomName"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				String awardnum = rs.getString("awardnum");//20位开奖号码
				dto.setRetroType(rs.getInt("retroType"));
				String [] num = new String[3];
				num = awardnum.split(",");
				dto.setAwardnum1(num);
				
				String[] color1 = new String[3];
				String color = "";
				if("?".equals(awardnum)||"0".equals(awardnum)||"".equals(awardnum)){
					color = "";
				}
				else{
				for(int i = 0; i< 3;i++){
					if(Integer.parseInt(num[i])>=0&&Integer.parseInt(num[i])<=3){
						color = "../images/b.png";
					}
					else if(Integer.parseInt(num[i])>3&&Integer.parseInt(num[i])<=6){
						color = "../images/g.png";
					}
					else if(Integer.parseInt(num[i])>6&&Integer.parseInt(num[i])<=10){
						color = "../images/r.png";
					}
					else{
						color = "";
						}
					color1[i] = color;
					}
				
				//算出3D和时时乐的开奖结果   ...计算开始
				int andValueSP = GameRules.andValue(num, 1);                      // 和值单双
				int andValueLS = GameRules.andValue(num, 2);                      // 和值大小
				int hundredSP = GameRules.location(Integer.parseInt(num[0]), 1);  // 百位单双
				int hundredLS = GameRules.location(Integer.parseInt(num[0]), 2);  // 百位大小
				int tenSP = GameRules.location(Integer.parseInt(num[1]), 1);      // 十位单双
				int tenLS = GameRules.location(Integer.parseInt(num[1]), 2);      // 十位大小
				int oneSP = GameRules.location(Integer.parseInt(num[2]), 1);      // 个位单双
				int oneLS = GameRules.location(Integer.parseInt(num[2]), 2);      // 个位大小
				int special = GameRules.special(num);                             // 特殊玩法
				
				if(andValueSP==1){
					dto.setAndValueSPName("双");
				}
				else{
					dto.setAndValueSPName("单");
				}
				if(andValueLS==1){
					dto.setAndValueLSName("大");
				}
				else{
					dto.setAndValueLSName("小");
				}
				if(hundredSP==1){
					dto.setHundredSPName("双");
				}
				else{
					dto.setHundredSPName("单");
				}
				if(hundredLS==1){
					dto.setHundredLSName("大");
				}
				else{
					dto.setHundredLSName("小");
				}
				if(tenSP==1){
					dto.setTenSPName("双");
				}
				else{
					dto.setTenSPName("单");
				}
				if(tenLS==1){
					dto.setTenLSName("大");
				}
				else{
					dto.setTenLSName("小");
				}
				if(oneSP==1){
					dto.setOneSPName("双");
				}
				else{
					dto.setOneSPName("单");
				}
				if(oneLS==1){
					dto.setOneLSName("大");
				}
				else{
					dto.setOneLSName("小");
				}
				if(special==1){
					dto.setSpecialName("豹子");
				}
				else if(special==2){
					dto.setSpecialName("顺子");
				}
				else if(special==3){
					dto.setSpecialName("对子");
				}
				else if(special==4){
					dto.setSpecialName("半顺子");
				}
				else{
					dto.setSpecialName("杂六");
				}
				//...计算结果结束
				
				}
				dto.setColor(color1);
				
				list.add(dto);
			}
			toesUp.getMoreResults();
			pageCount=toesUp.getInt(7);
			recordCount=toesUp.getInt(8);
			//toesUp.close();
			//con.setAutoCommit(true);
			
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
	
	
	@SuppressWarnings("unchecked")
	public List queryRoomID(){
		List<PeroidnumResultDTO> list = new ArrayList<PeroidnumResultDTO>();
		String sql = "select * from rooms where checkType=0";
		Connection con = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				PeroidnumResultDTO gameKind = new PeroidnumResultDTO();
				gameKind.setRoomId(rs.getInt("ID"));
				gameKind.setRoomName(rs.getString("roomName"));
				gameKind.setDisplayName(rs.getString("displayName"));
				list.add(gameKind);
			}
			if(rs!=null){
				rs.close();
			}
			if(con!=null){
				con.close();
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			try {
				if(rs!=null){
					rs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List query3DRoomID(){
		List<PeroidnumResultDTO> list = new ArrayList<PeroidnumResultDTO>();
		String sql = "select * from rooms where checkType<>0";
		Connection con = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			rs = con.createStatement().executeQuery(sql);
			while(rs.next()){
				PeroidnumResultDTO gameKind = new PeroidnumResultDTO();
				gameKind.setRoomId(rs.getInt("ID"));
				gameKind.setRoomName(rs.getString("roomName"));
				gameKind.setDisplayName(rs.getString("displayName"));
				list.add(gameKind);
			}
			if(rs!=null){
				rs.close();
			}
			if(con!=null){
				con.close();
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			try {
				if(rs!=null){
					rs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int getTotalPage() {
		return pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	
	public void updateJiesuan(DataSource ds,int roomId,String peroidnum){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update gamePeroidnumResult set retroType=0,lotteryType=0 where roomId="+roomId+" and peroidnum='"+peroidnum+"'";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
			
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
	
	
	
	
	public String addResult(String awardnum,String result[],String peroidnum,int roomId){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		String sql1,sql2,sql3 = "";
		
		int single = GameRules.singleOrPairs(result);     //算出单或双
		int serpent = GameRules.serpentAndTiger(result);  //算出龙和虎
		int large = GameRules.largeOrSmall(result);       //算出大或小
		if(single==1){
			sql1 = "pairs = 1";
		}
		else{
			sql1 = "single = 1";
		}
		if(serpent==1){
			sql2 = "serpent = 1";
		}
		else if(serpent==2){
			sql2 = "peace = 1";
		}
		else {
			sql2 = "tiger = 1";
		}
		if(large==1){
			sql3 = "large = 1";
		}
		else{
			sql3 = "small = 1";
		}
		
		String sql = "update gamePeroidnumResult set awardnum='"+awardnum+"',"+sql1+","+sql2+","+sql3+",retroType=2,lotteryType=2"+" where roomId = "+roomId+" and peroidnum = '"+peroidnum+"'";
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
			msg = "开奖号码补录成功!";
			
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
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return msg;
	}
	
	public String updateResult(String awardnum,String result[],String peroidnum,int roomId){
		String msg = "";
		Connection con = null;
		PreparedStatement ps = null;
		String sql1,sql2,sql3 = "";
		
		int single = GameRules.singleOrPairs(result);     //算出单或双
		int serpent = GameRules.serpentAndTiger(result);  //算出龙和虎
		int large = GameRules.largeOrSmall(result);       //算出大或小
		if(single==1){
			sql1 = "pairs = 1";
		}
		else{
			sql1 = "single = 1";
		}
		if(serpent==1){
			sql2 = "serpent = 1";
		}
		else if(serpent==2){
			sql2 = "peace = 1";
		}
		else {
			sql2 = "tiger = 1";
		}
		if(large==1){
			sql3 = "large = 1";
		}
		else{
			sql3 = "small = 1";
		}
		
		String sql = "update gamePeroidnumResult set awardnum='"+awardnum+"',"+sql1+","+sql2+","+sql3+",retroType=2,lotteryType=2"+" where roomId = "+roomId+" and peroidnum = '"+peroidnum+"'";
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
			msg = "修改号码成功!";
			
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
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return msg;
	}
	
	public String add3DResult(String awardnum,String[] result,String peroidnum,int roomId){
		String msg = "";
		String sql = "update gamePeroidnumResult set awardnum='"+awardnum+"' where roomId = "+roomId+" and peroidnum = '"+peroidnum+"'";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
			msg = "开奖号码补录成功";
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
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return msg;
	}
	
	public String queryAwardnum(DataSource ds,int roomId,String peroidnum){
		String result = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from gamePeroidnumResult where roomId=? and peroidnum=?");
			ps.setInt(1, roomId);
			ps.setString(2, peroidnum);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getString("awardnum");
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
		return result;
	}
	
	public static ArrayList<GameRecordDTO> getGameRecords(DataSource ds,int roomId,String peroidnum,String jiesuan) {
		ArrayList<GameRecordDTO> list = new ArrayList<GameRecordDTO>();
		String sql = "select * from gameRecord where  roomId = ?  and peroidnum=?  and jiesuan = ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con  = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, roomId);
			ps.setString(2, peroidnum);
			ps.setString(3, jiesuan);
			rs = ps.executeQuery();
			while (rs.next()) {
				GameRecordDTO dto = new GameRecordDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("userId"));
				dto.setRoomId(rs.getInt("roomId"));
				dto.setPeroidnum(rs.getString("peroidnum"));
				dto.setGameRuleId(rs.getInt("gameRuleId"));
				dto.setTotalGoldBet(rs.getFloat("totalGoldBet"));
				dto.setCreateTime(rs.getString("createTime"));
				dto.setSingle(rs.getInt("single"));
				dto.setSerpent(rs.getInt("serpent"));
				dto.setLarge(rs.getInt("large"));
				dto.setPeace(rs.getInt("peace"));
				dto.setTiger(rs.getInt("tiger"));
				dto.setSmall(rs.getInt("small"));
				dto.setPairs(rs.getInt("pairs"));
				dto.setRate(rs.getInt("rate"));
				dto.setRateNumber(rs.getString("rateNumber"));
				dto.setJiesuan(rs.getString("jiesuan"));
				dto.setFirstMoney(rs.getString("firstMoney"));
				dto.setLastMoney(rs.getString("lastMoney"));
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
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
		return list;
	}
	
	public void systemGetResults(DataSource ds,int roomId,String peroidnum,String username,String ip){
		Connection con = null;
		
		String result = this.queryAwardnum(ds,roomId,peroidnum);
		String resultAward[] = result.split(",");
		PerodinumResultDAO jiesuandao = new PerodinumResultDAO(ds); 
		GamePeroidnumResultDTO pDto = new GamePeroidnumResultDTO();
		ArrayList<GameRecordDTO> list =  getGameRecords(ds,roomId,peroidnum,"0");
		if (GameRules.singleOrPairs(resultAward) != 0) {
			if (GameRules.singleOrPairs(resultAward) == 1) {
				pDto.setSingle(0);
				pDto.setPairs(1);
			} else {
				pDto.setSingle(1);
				pDto.setPairs(0);
			}
		} else {
			System.out.println("期数:" + peroidnum + "singleOrPairs有异常" + "");
		}
		if (GameRules.largeOrSmall(resultAward) != 0) {
			if (GameRules.largeOrSmall(resultAward) == 1) {
				pDto.setLarge(1);
				pDto.setSmall(0);
			} else {
				pDto.setLarge(0);
				pDto.setSmall(1);
			}
		} else {
			System.out.println("期数:" + peroidnum + "largeOrSmall有异常" + "");
		}
		if (GameRules.serpentAndTiger(resultAward) != 0) {
			if (GameRules.serpentAndTiger(resultAward) == 1) {
				pDto.setSerpent(1);
				pDto.setPeace(0);
				pDto.setTiger(0);
			} else if (GameRules.serpentAndTiger(resultAward) == 2) {
				pDto.setSerpent(0);
				pDto.setPeace(1);
				pDto.setTiger(0);
			} else {
				pDto.setSerpent(0);
				pDto.setPeace(0);
				pDto.setTiger(1);
			}
		} else {
			System.out.println("期数:" + peroidnum+ "serpentAndTiger有异常" + "");
		}
		String awardnum = "";
		for (int z = 0; z < resultAward.length; z++) {
			if (awardnum.equals("")) {
				awardnum = resultAward[z];
			} else {
				awardnum += "," + resultAward[z];
			}
		}
		pDto.setAwardnum(awardnum);

		if (list != null && list.size() > 0) {
			
			GradeExpregferenceDAO geDao = new GradeExpregferenceDAO(ds);
			ArrayList<GradeExpregferenceDTO> geList = geDao.getGradeExpregferences();
			
			for (int i = 0; i < list.size(); i++) {
				double winGold = 0;
				GameRecordDTO rDto = list.get(i);
				double singleMoney = rDto.getSingle() * pDto.getSingle()
						* ((double) 1.95);
				double serpentMoney = rDto.getSerpent() * pDto.getSerpent()
						* ((double) 2.2);
				double largeMoney = rDto.getLarge() * pDto.getLarge()
						* ((double) 1.95);
				double peaceMoney = rDto.getPeace() * pDto.getPeace()
						* ((double) 4);
				double tigerMoney = rDto.getTiger() * pDto.getTiger()
						* ((double) 2.2);
				double smallMoney = rDto.getSmall() * pDto.getSmall()
						* ((double) 1.95);
				double pairsMoney = rDto.getPairs() * pDto.getPairs()
						* ((double) 1.95);
				int m = GameRules.rate(resultAward, rDto.getRateNumber());
				double rateMoney = rDto.getRate() * m;
				//double rebate = rDto.getTotalGoldBet()*0.005;//奖励金钱/反水金额
				winGold =  (singleMoney + serpentMoney + largeMoney
						+ peaceMoney + tigerMoney + smallMoney + pairsMoney + rateMoney);
				System.out.println(">>>>>>>>>>>>>>>winGold="+winGold);
				rDto.setWinGold(winGold);
				//GameResultDTO resultDTO = new GameResultDTO();
				GameUserDAO gameUser = new GameUserDAO(ds);
				GameUser user = gameUser.getUserByUserId(rDto.getUserId());
				GameUser user1 = gameUser.getUserByUserId(rDto.getUserId());
				GameRecordDAO dao = new GameRecordDAO(ds);
				
				if (user != null) {
					
					GradeExpregferenceDTO geDto = geDao.getGradeExpregferenceByExp(geList,user.getExperience());
					double rebate = rDto.getTotalGoldBet()*geDto.getRebateLV();//Constant.rebate;//奖励金钱
					String beizhu = "";
					if(geDto.getId()<=0){
						beizhu += ",数据库经验等级数据缺少请补入 (表 GradeExpregference) ";
					}
					rDto.setWinGold(winGold);
					rDto.setPureGold(winGold - rDto.getTotalGoldBet());
					//resultDTO.setUserId(rDto.getUserId());
					user.setScore(user.getScore() + winGold +rebate);
					rDto.setRebate(rebate);
					try {
						con = ds.getConnection();
						con.setAutoCommit(false);
						rDto.setJiesuan("1");
						dao.updateGameRecord(rDto,con);// 结算，表示该记录已经结算成功
						gameUser.updateGameUser(user,con);// 修改用户的银子
						jiesuandao.updateJiesuan(ds, roomId, peroidnum);
						
						/* 写入结算日志 */
						HandicapDAO dao11 = new HandicapDAO(ds);
						String roomName = dao11.queryRoomName(roomId);
						OperateLogsDAO operateDao = new OperateLogsDAO(ds);
						OperateDTO operateDto = new OperateDTO();
						String operateDetails = "<font color='red'>"+username+"管理后台系统结算"+roomName+"游戏,期数为："+peroidnum+"修改"+user.getAccount()+"旧银子为："+user1.getScore()+"新银子为："+user.getScore()+",反水金额为："+rebate+"</font>";
						operateDto.setOperateName(username);
						operateDto.setOperateDetails(operateDetails);
						operateDto.setOperateIP(ip);
						operateDao.addLogs(operateDto);
						/*        */
						
						/* 写入用户银子流水记录*/
						UserGoldFlowDAO dao22 = new UserGoldFlowDAO(ds);
						UserGoldFlowDTO dto = new UserGoldFlowDTO();
						dto.setUserID(user.getUserID());
						dto.setBeforeMoney(user1.getScore());
						dto.setAfterMoney(user.getScore());
						dto.setDiffMoney(user.getScore()-user1.getScore());
						dto.setBeizhu("管理后台系统结算"+roomName+"游戏,期数为："+peroidnum);
						dao22.addUserGoldFlow(dto);
						/*        */
						
						con.commit();
						con.setAutoCommit(true);
						
						if(con!=null){
							con.close();con=null;
						}
						
					} catch (Exception e) {
						try {
							con.rollback();
							if(con!=null){
								con.close();con=null;
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				}
			}
		}
		else if(list.size() == 0){
			System.out.println("结算，没有人下注，修改gamePeroidResult的状态!");
			jiesuandao.updateJiesuan(ds, roomId, peroidnum);
		}
	}
	
	//传con做事物用
	public void systemGamePickRecordResult(DataSource ds,int roomId,String peroidnum){
		
		String result3D = this.queryAwardnum(ds,roomId,peroidnum);
		String result3DAward[] = result3D.split(",");
		GamePickRecordDAO pickRecordDao = new GamePickRecordDAO(ds);
		PerodinumResultDAO dao = new PerodinumResultDAO(ds);
		GameUserDAO userDao = new GameUserDAO(ds);
		if(roomId==16){
			RATE1 = (float)1.9;
		}
		else{
			RATE1 = (float)1.85;
		}
		ArrayList<GamePickRecordDTO> list = pickRecordDao.getGamePickRecord(roomId, peroidnum,0,"order by userId");
		int andValueSP = GameRules.andValue(result3DAward, 1);// 和值单双
		int andValueLS = GameRules.andValue(result3DAward, 2);// 和值大小
		int hundredSP = GameRules.location(Integer.parseInt(result3DAward[0]), 1);
		int hundredLS = GameRules.location(Integer.parseInt(result3DAward[0]), 2);
		int tenSP = GameRules.location(Integer.parseInt(result3DAward[1]), 1);
		int tenLS = GameRules.location(Integer.parseInt(result3DAward[1]), 2);
		int oneSP = GameRules.location(Integer.parseInt(result3DAward[2]), 1);
		int oneLS = GameRules.location(Integer.parseInt(result3DAward[2]), 2);
		int special = GameRules.special(result3DAward);// 特殊玩法
		Connection conn = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				GamePickRecordDTO dto = list.get(i);
				float b = 0;
				if (dto.getType() == 1) {// 1D
					if (dto.getHundred() != null
							&& dto.getHundred().equals(result3DAward[0])
							&& dto.getTen() == null && dto.getOne() == null) {
						b = Integer.parseInt(dto.getTotalGoldBet())
								* (float) 7.5;
					} else if (dto.getHundred() == null && dto.getTen() != null
							&& dto.getTen().equals(result3DAward[1])
							&& dto.getOne() == null) {
						b = Integer.parseInt(dto.getTotalGoldBet())
								* (float) 7.5;
					} else if (dto.getHundred() == null && dto.getTen() == null
							&& dto.getOne() != null
							&& dto.getOne().equals(result3DAward[2])) {
						b = Integer.parseInt(dto.getTotalGoldBet())
								* (float) 7.5;
					}
				} else if (dto.getType() == 2) {// 2D
					if (dto.getHundred() != null
							&& dto.getHundred().equals(result3DAward[0])
							&& dto.getTen() != null
							&& dto.getTen().equals(result3DAward[1])
							&& dto.getOne() == null) {
						b = Integer.parseInt(dto.getTotalGoldBet()) * 70;
					} else if (dto.getHundred() != null
							&& dto.getHundred().equals(result3DAward[0])
							&& dto.getTen() == null && dto.getOne() != null
							&& dto.getOne().equals(result3DAward[2])) {
						b = Integer.parseInt(dto.getTotalGoldBet()) * 70;
					} else if (dto.getHundred() == null && dto.getTen() != null
							&& dto.getTen().equals(result3DAward[1])
							&& dto.getOne() != null
							&& dto.getOne().equals(result3DAward[2])) {
						b = Integer.parseInt(dto.getTotalGoldBet()) * 70;
					}
				} else if (dto.getType() == 3) {// 3D 单选
					if (dto.getHundred() != null && dto.getTen() != null
							&& dto.getOne() != null
							&& dto.getHundred().equals(result3DAward[0])
							&& dto.getTen().equals(result3DAward[1])
							&& dto.getOne().equals(result3DAward[2])) {
						b = Integer.parseInt(dto.getTotalGoldBet()) * 600;
					}
				} else if (dto.getType() == 4) {// 组一
					if (dto.getHundred().equals(result3DAward[0])
							|| dto.getHundred().equals(result3DAward[1])
							|| dto.getHundred().equals(result3DAward[2])) {
						b = Integer.parseInt(dto.getTotalGoldBet())
								* (float) 3.1;
					}
				} else if (dto.getType() == 5) {// 组三
					if (dto.getHundred() != null && dto.getTen() != null
							&& dto.getOne() != null) {
						int temp = 0;
						for (int x = 0; x < result3DAward.length; x++) {
							if (dto.getHundred().equals(result3DAward[i])
									|| dto.getTen().equals(result3DAward[i])
									|| dto.getOne().equals(result3DAward[i])) {
								temp++;
							}
						}

						if (temp == 3
								&& ((dto.getHundred().equals(dto.getTen()) && !dto
										.getHundred().equals(dto.getOne()))
										|| (dto.getHundred().equals(
												dto.getOne()) && !dto
												.getHundred().equals(
														dto.getTen())) || (dto
										.getTen().equals(dto.getOne()) && !dto
										.getTen().equals(dto.getHundred())))) {
							b = Integer.parseInt(dto.getTotalGoldBet())
									* (float) 200;
						}
					}

				} else if (dto.getType() == 6) {// 组六
					if (dto.getHundred() != null && dto.getTen() != null
							&& dto.getOne() != null) {
						int temp = 0;
						for (int x = 0; x < result3DAward.length; x++) {
							if (dto.getHundred().equals(result3DAward[i])
									|| dto.getTen().equals(result3DAward[i])
									|| dto.getOne().equals(result3DAward[i])) {
								temp++;
							}
						}
						if (temp == 3 && !dto.getHundred().equals(dto.getTen())
								&& !dto.getHundred().equals(dto.getOne())
								&& !dto.getTen().equals(dto.getOne())) {
							b = Integer.parseInt(dto.getTotalGoldBet())
									* (float) 100;
						}
					}
				} else if (dto.getType() == 7) {// 和值单
					if (andValueSP == 2) {
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
					}
				} else if (dto.getType() == 8) {// 和值双
					if (andValueSP == 1) {
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
					}
				} else if (dto.getType() == 9) {// 和值大
					if (andValueLS == 1) {
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
					}

				} else if (dto.getType() == 10) {// 和值小
					if (andValueLS == 2) {
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
					}
				} else if (dto.getType() == 11) {// 百位单
					if (hundredSP == 2)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 12) {// 百位双
					if (hundredSP == 1)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 13) {// 百位大
					if (hundredLS == 1)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 14) {// 百位小
					if (hundredLS == 2)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 15) {// 十位单
					if (tenSP == 2)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 16) {// 十位双
					if (tenSP == 1)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 17) {// 十位大
					if (tenLS == 1)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 18) {// 十位小
					if (tenLS == 2)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 19) {// 个位单
					if (oneSP == 2)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 20) {// 个位双
					if (oneSP == 1)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 21) {// 个位大
					if (oneLS == 1)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 22) {// 个位小
					if (oneLS == 2)
						b = Integer.parseInt(dto.getTotalGoldBet()) * RATE1;
				} else if (dto.getType() == 23) {// 对子
					if (special == 3) { // 对子
						b = Integer.parseInt(dto.getTotalGoldBet())
								* (float) 2.8;
					}
				} else if (dto.getType() == 24) {// 杂六
					if (special == 5) {// 杂六
						b = Integer.parseInt(dto.getTotalGoldBet())
								* (float) 2.5;
					}
				} else if (dto.getType() == 25) {// 顺子
					if (special == 2) {// 顺子
						b = Integer.parseInt(dto.getTotalGoldBet()) * 13;
					}
				} else if (dto.getType() == 26) {// 半顺
					if (special == 4) {// 半顺
						b = Integer.parseInt(dto.getTotalGoldBet()) * 2;
					}
				} else if (dto.getType() == 27) {// 豹子
					if (special == 1) {// 豹子
						b = Integer.parseInt(dto.getTotalGoldBet()) * 70;
					}
				}
				try {
					conn = ds.getConnection();
					GameUser user = userDao.getUserByUserId(dto.getUserId());
					if (user != null) {
						conn.setAutoCommit(false);
						user.setScore(user.getScore() + (long) b);
						dto.setPureMoney(((long) (b - Long.parseLong(dto
								.getTotalGoldBet())))
								+ "");
						dto.setJiesuan(1);
						//userDao.updateGameUser(user);
						pickRecordDao.updateGamePickRecord(dto);
						dao.updateJiesuan(ds, roomId, peroidnum);
						conn.commit();
						conn.setAutoCommit(true);
					}
				} catch (Exception e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
}

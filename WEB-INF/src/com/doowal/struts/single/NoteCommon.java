package com.doowal.struts.single;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteCommon {

	
	public static boolean isExist(String[] s, String str){
		
		boolean bo = false;
		for(int i = 0; i < s.length; i++){
			if(str.equals(s[i])){
				bo = true;
				break;
			}
		}
		return bo ;
		
	}
	
	public static String[] value(String[] s, String str){
		
		for(int i = 0; i < s.length; i++){
			if(str.equals(s[i])){
				s[i] = null;
				break;
			}
		}
		return s ;
		
	}
	
	public static Double Average_(Double value,int geShu){

		Double Average_value = 0.00;
		
		Average_value =  ( value * 100 / geShu ) / 100 ;
		
		return Average_value ;
		
	}
	
	public static String DoubleFormat(String value){
		if("".equals(value)|| value == null){
			value = 0+"";
		}
		String str = "";
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		str = decimalFormat.format(Double.parseDouble(value));
		return str ;
	}
	
	public static List<Note2DTO> setGameList(List<Note2DTO> model, Map<String,Object> info){
		
		List<Note2DTO> list = model;
		for(int i = 0; i < model.size() ; i++){
			if( info.get(model.get(i).getServerID()) !=null ){
				List<Note2DTO> li = (List<Note2DTO>)info.get(model.get(i).getServerID());
				System.out.println("-----------li.size:"+li.size()+"--serverID:"+model.get(i).getServerID());
				if("208".equals(model.get(i).getServerID().substring(0, model.get(i).getServerID().length()-1))){
				Double totalGold = 0.00;
				for(int j = 0; j < li.size() ; j ++){
					if("1001".equals(li.get(j).getGameArea())){
						model.get(i).setXian(DoubleFormat(li.get(j).getBetGold()));
						totalGold += Double.parseDouble(li.get(j).getBetGold());
						System.out.println("1001   闲家"+li.get(j).getBetGold()+"~~~~~~~~:"+totalGold);
					}
					else if("1002".equals(li.get(j).getGameArea())){
						System.out.println("1002   平家"+li.get(j).getBetGold());
						model.get(i).setHe(DoubleFormat(li.get(j).getBetGold()));
						totalGold += Double.parseDouble(li.get(j).getBetGold());
					}
					else if("1003".equals(li.get(j).getGameArea())){
						System.out.println("1003   庄家"+li.get(j).getBetGold());
						model.get(i).setZhuang(DoubleFormat(li.get(j).getBetGold()));
						totalGold += Double.parseDouble(li.get(j).getBetGold());
					}
					else if("1004".equals(li.get(j).getGameArea())){
						System.out.println("1004   大"+li.get(j).getBetGold());
						model.get(i).setDa(DoubleFormat(li.get(j).getBetGold()));
						totalGold += Double.parseDouble(li.get(j).getBetGold());
					}
					else if("1005".equals(li.get(j).getGameArea())){
						System.out.println("1005   小"+li.get(j).getBetGold());
						model.get(i).setXiao(DoubleFormat(li.get(j).getBetGold()));
						totalGold += Double.parseDouble(li.get(j).getBetGold());
					}
//					else if("1006".equals(li.get(j).getGameArea())){
//						System.out.println("1006   百家乐待定"+li.get(j).getBetGold());
//						model.get(i).setHe(li.get(j).getBetGold());
//					}
					else if("1007".equals(li.get(j).getGameArea())){
						System.out.println("1007   闲对"+li.get(j).getBetGold());
						model.get(i).setXianDui(DoubleFormat(li.get(j).getBetGold()));
						totalGold += Double.parseDouble(li.get(j).getBetGold());
					}
					else if("1008".equals(li.get(j).getGameArea())){
						System.out.println("1008   庄对"+li.get(j).getBetGold());
						model.get(i).setZhuangDui(DoubleFormat(li.get(j).getBetGold()));
						totalGold += Double.parseDouble(li.get(j).getBetGold());
					}
//					else if("1009".equals(li.get(j).getGameArea())){
//						System.out.println("1009   闲单"+li.get(j).getBetGold());
//					}
//					else if("1010".equals(li.get(j).getGameArea())){
//						System.out.println("1010   闲双"+li.get(j).getBetGold());
//					}
//					else if("1011".equals(li.get(j).getGameArea())){
//						System.out.println("1011   闲单"+li.get(j).getBetGold());
//					}
//					else if("1012".equals(li.get(j).getGameArea())){
//						System.out.println("1012   闲双"+li.get(j).getBetGold());
//					}
					else if("1013".equals(li.get(j).getGameArea())){
						System.out.println("1013   SUPER_6"+li.get(j).getBetGold());
						model.get(i).setNumber(DoubleFormat(li.get(j).getBetGold()));
						totalGold += Double.parseDouble(li.get(j).getBetGold());
					}
					model.get(i).setAmount(DoubleFormat( String.valueOf(totalGold) ));//金额
					model.get(i).setBetSerial(li.get(j).getBetSerial()); //期数
				}
				}
				else if("209".equals(model.get(i).getServerID().substring(0, model.get(i).getServerID().length()-1))){
					System.out.println("-----------------骰宝");
					
				}
				else if("301".equals(model.get(i).getServerID().substring(0, model.get(i).getServerID().length()-1))){
					System.out.println("-----------------轮盘");
					Double totalGold = 0.00;
					for(int j = 0; j < li.size() ; j ++){
						
						
						
					//model.get(i).setAmount(DoubleFormat( String.valueOf(totalGold) ));//金额
					model.get(i).setBetSerial(li.get(j).getBetSerial()); //期数
					}
				}
			}		
		}
		return list ;
		
	}
	
	public static int pageStart(int total,int page){
		
		int pageValue = 1;
		if( total > 0 ){
			pageValue = total % 4 > 0 ? total / 4 + 1 : total / 4 ;
		}
		switch( page ){
			case 1 : pageValue = 1;break;
			case 2 : pageValue = pageValue*(page-1)+1;break;
			case 3 : pageValue = pageValue*(page-1)+1;break;
			case 4 : pageValue = pageValue*(page-1)+1;break;
		}
		return pageValue ;
		
	}
	
	public static int pageEnd(int total,int page){
		
		int pageValue = 1;
		if( total > 0 ){
			pageValue = total % 4 > 0 ? total / 4 + 1 : total / 4 ;
		}
		if( page > 0 && page < 5 ){
			pageValue = pageValue * page;
		}
		
		return pageValue ;
		
	}
	
	//--轮盘-------------------------------------
	public static Note2DTO setLunPan(List<Note2DTO> info){
		
		Note2DTO dto = new Note2DTO();
		Double totalGold = 0.00;
		Double num_0 = 0.00,red = 0.00,black = 0.00,da = 0.00,xiao =0.00,dan = 0.00,shuang = 0.00;
		Double num_1 = 0.00,num_2 = 0.00,num_3 = 0.00,num_4 = 0.00,num_5 = 0.00,num_6 = 0.00,num_7 = 0.00,num_8 = 0.00,num_9 = 0.00;
		Double num_10= 0.00,num_11= 0.00,num_12= 0.00,num_13= 0.00,num_14= 0.00,num_15= 0.00,num_16= 0.00,num_17= 0.00,num_18 = 0.00;
		Double num_19= 0.00,num_20= 0.00,num_21= 0.00,num_22= 0.00,num_23= 0.00,num_24= 0.00,num_25= 0.00,num_26= 0.00,num_27 = 0.00;
		Double num_28= 0.00,num_29= 0.00,num_30= 0.00,num_31= 0.00,num_32= 0.00,num_33= 0.00,num_34= 0.00,num_35= 0.00,num_36 = 0.00;
		Double num_1_12 = 0.00,num_13_24 = 0.00,num_25_36 = 0.00,num_1_34 = 0.00,num_2_35 = 0.00,num_3_36 = 0.00;
		
		Integer count_0 = 0,count_red = 0,count_black = 0,count_da = 0,count_xiao = 0,count_dan = 0,count_shuang = 0;
		Integer count_1 = 0,count_2 =0,count_3 = 0,count_4 = 0,count_5 = 0,count_6 = 0,count_7 = 0,count_8 = 0,count_9 = 0;
		Integer count_10= 0,count_11= 0,count_12= 0,count_13= 0,count_14= 0,count_15= 0,count_16= 0,count_17= 0,count_18 = 0;
		Integer count_19= 0,count_20= 0,count_21= 0,count_22= 0,count_23= 0,count_24= 0,count_25= 0,count_26= 0,count_27 = 0;
		Integer count_28= 0,count_29= 0,count_30= 0,count_31= 0,count_32= 0,count_33= 0,count_34= 0,count_35= 0,count_36 = 0;
		Integer count_1_12= 0,count_13_24 = 0,count_25_36 = 0,count_1_34 = 0,count_2_35 = 0,count_3_36 = 0;
		if(info.size() > 0){
			dto.setBetSerial(info.get(0).getBetSerial());
		}
		//循环开始---------------------------------
		for(int i =  0; i < info.size() ; i++){
			if("2001".equals(info.get(i).getGameArea())){
				System.out.println("2001-"+info.get(i).getTotalGold());
				num_1 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_1 = Integer.parseInt(info.get(i).getBets());
			}
			if("2002".equals(info.get(i).getGameArea())){
				System.out.println("2002-"+info.get(i).getTotalGold());
				num_2 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold());
				count_2 = Integer.parseInt(info.get(i).getBets());
			}
			if("2003".equals(info.get(i).getGameArea())){
				System.out.println("2003-"+info.get(i).getTotalGold());
				num_3 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_3 = Integer.parseInt(info.get(i).getBets());
			}
			if("2004".equals(info.get(i).getGameArea())){
				System.out.println("2004-"+info.get(i).getTotalGold());
				num_4 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_4 = Integer.parseInt(info.get(i).getBets());
			}
			if("2005".equals(info.get(i).getGameArea())){
				System.out.println("2005-"+info.get(i).getTotalGold());
				num_5 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_5 = Integer.parseInt(info.get(i).getBets());
			}
			if("2006".equals(info.get(i).getGameArea())){
				System.out.println("2006-"+info.get(i).getTotalGold());
				num_6 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_6 = Integer.parseInt(info.get(i).getBets());
			}
			if("2007".equals(info.get(i).getGameArea())){
				System.out.println("2007-"+info.get(i).getTotalGold());
				num_7 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_7 = Integer.parseInt(info.get(i).getBets());
			}
			if("2008".equals(info.get(i).getGameArea())){
				System.out.println("2008-"+info.get(i).getTotalGold());
				num_8 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_8 = Integer.parseInt(info.get(i).getBets());
			}
			if("2009".equals(info.get(i).getGameArea())){
				System.out.println("2009-"+info.get(i).getTotalGold());
				num_9 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_9 = Integer.parseInt(info.get(i).getBets());
			}
			if("2010".equals(info.get(i).getGameArea())){
				System.out.println("2010-"+info.get(i).getTotalGold());
				num_10 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_10 = Integer.parseInt(info.get(i).getBets());
			}
			if("2011".equals(info.get(i).getGameArea())){
				System.out.println("2011-"+info.get(i).getTotalGold());
				num_11 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_11 = Integer.parseInt(info.get(i).getBets());
			}
			if("2012".equals(info.get(i).getGameArea())){
				System.out.println("2012-"+info.get(i).getTotalGold());
				num_12 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_12 = Integer.parseInt(info.get(i).getBets());
			}
			if("2013".equals(info.get(i).getGameArea())){
				System.out.println("2013-"+info.get(i).getTotalGold());
				num_13 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_13 = Integer.parseInt(info.get(i).getBets());
			}
			if("2014".equals(info.get(i).getGameArea())){
				System.out.println("2014-"+info.get(i).getTotalGold());
				num_14 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_14 = Integer.parseInt(info.get(i).getBets());
			}
			if("2015".equals(info.get(i).getGameArea())){
				System.out.println("2015-"+info.get(i).getTotalGold());
				num_15 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_15 = Integer.parseInt(info.get(i).getBets());
			}
			if("2016".equals(info.get(i).getGameArea())){
				System.out.println("2016-"+info.get(i).getTotalGold());
				num_16 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_16 = Integer.parseInt(info.get(i).getBets());
			}
			if("2017".equals(info.get(i).getGameArea())){
				System.out.println("2017-"+info.get(i).getTotalGold());
				num_17 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_17 = Integer.parseInt(info.get(i).getBets());
			}
			if("2018".equals(info.get(i).getGameArea())){
				System.out.println("2018-"+info.get(i).getTotalGold());
				num_18 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_18 = Integer.parseInt(info.get(i).getBets());
			}
			if("2019".equals(info.get(i).getGameArea())){
				System.out.println("2019-"+info.get(i).getTotalGold());
				num_19 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_19 = Integer.parseInt(info.get(i).getBets());
			}
			if("2020".equals(info.get(i).getGameArea())){
				System.out.println("2020-"+info.get(i).getTotalGold());
				num_20 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_20 = Integer.parseInt(info.get(i).getBets());
			}
			if("2021".equals(info.get(i).getGameArea())){
				System.out.println("2021-"+info.get(i).getTotalGold());
				num_21 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_21 = Integer.parseInt(info.get(i).getBets());
			}
			if("2022".equals(info.get(i).getGameArea())){
				System.out.println("2022-"+info.get(i).getTotalGold());
				num_22 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_22 = Integer.parseInt(info.get(i).getBets());
			}
			if("2023".equals(info.get(i).getGameArea())){
				System.out.println("2023-"+info.get(i).getTotalGold());
				num_23 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_23 = Integer.parseInt(info.get(i).getBets());
			}
			if("2024".equals(info.get(i).getGameArea())){
				System.out.println("2024-"+info.get(i).getTotalGold());
				num_24 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_24 = Integer.parseInt(info.get(i).getBets());
			}
			if("2025".equals(info.get(i).getGameArea())){
				System.out.println("2025-"+info.get(i).getTotalGold());
				num_25 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_25 = Integer.parseInt(info.get(i).getBets());
			}
			if("2026".equals(info.get(i).getGameArea())){
				System.out.println("2026-"+info.get(i).getTotalGold());
				num_26 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_26 = Integer.parseInt(info.get(i).getBets());
			}
			if("2027".equals(info.get(i).getGameArea())){
				System.out.println("2027-"+info.get(i).getTotalGold());
				num_27 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_27 = Integer.parseInt(info.get(i).getBets());
			}
			if("2028".equals(info.get(i).getGameArea())){
				System.out.println("2028-"+info.get(i).getTotalGold());
				num_28 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_28 = Integer.parseInt(info.get(i).getBets());
			}
			if("2029".equals(info.get(i).getGameArea())){
				System.out.println("2029-"+info.get(i).getTotalGold());
				num_29 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_29 = Integer.parseInt(info.get(i).getBets());
			}
			if("2030".equals(info.get(i).getGameArea())){
				System.out.println("2030-"+info.get(i).getTotalGold());
				num_30 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_30 = Integer.parseInt(info.get(i).getBets());
			}
			if("2031".equals(info.get(i).getGameArea())){
				System.out.println("2031-"+info.get(i).getTotalGold());
				num_31 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_31 = Integer.parseInt(info.get(i).getBets());
			}
			if("2032".equals(info.get(i).getGameArea())){
				System.out.println("2032-"+info.get(i).getTotalGold());
				num_32 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_32 = Integer.parseInt(info.get(i).getBets());
			}
			if("2033".equals(info.get(i).getGameArea())){
				System.out.println("2033-"+info.get(i).getTotalGold());
				num_33 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_33 = Integer.parseInt(info.get(i).getBets());
			}
			if("2034".equals(info.get(i).getGameArea())){
				System.out.println("2034-"+info.get(i).getTotalGold());
				num_34 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_34 = Integer.parseInt(info.get(i).getBets());
			}
			if("2035".equals(info.get(i).getGameArea())){
				System.out.println("2035-"+info.get(i).getTotalGold());
				num_35 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_35 = Integer.parseInt(info.get(i).getBets());
			}
			if("2036".equals(info.get(i).getGameArea())){
				System.out.println("2036-"+info.get(i).getTotalGold());
				num_36 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_36 = Integer.parseInt(info.get(i).getBets());
			}
			if("2037".equals(info.get(i).getGameArea())){
				System.out.println("2037-"+info.get(i).getTotalGold());
				num_1_34 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_1_34 = Integer.parseInt(info.get(i).getBets());
			}
			if("2038".equals(info.get(i).getGameArea())){
				System.out.println("2038-"+info.get(i).getTotalGold());
				num_2_35 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_2_35 = Integer.parseInt(info.get(i).getBets());
			}
			if("2039".equals(info.get(i).getGameArea())){
				System.out.println("2039-"+info.get(i).getTotalGold());
				num_3_36 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_3_36 = Integer.parseInt(info.get(i).getBets());
			}
			if("2040".equals(info.get(i).getGameArea())){
				System.out.println("2040-"+info.get(i).getTotalGold());
				num_1_12 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_1_12 = Integer.parseInt(info.get(i).getBets());
			}
			if("2041".equals(info.get(i).getGameArea())){
				System.out.println("2041-"+info.get(i).getTotalGold());
				num_13_24 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_13_24 = Integer.parseInt(info.get(i).getBets());
			}
			if("2042".equals(info.get(i).getGameArea())){
				System.out.println("2042-"+info.get(i).getTotalGold());
				num_25_36 = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_25_36 = Integer.parseInt(info.get(i).getBets());
			}
			if("2043".equals(info.get(i).getGameArea())){// 1-18 小
				System.out.println("2043-小-"+info.get(i).getTotalGold());
				xiao = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_xiao = Integer.parseInt(info.get(i).getBets());
			}
			if("2044".equals(info.get(i).getGameArea())){// 18-36 大
				System.out.println("2044-大-"+info.get(i).getTotalGold());
				da = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_da = Integer.parseInt(info.get(i).getBets());
			}
			if("2045".equals(info.get(i).getGameArea())){// 单
				System.out.println("2045-单-"+info.get(i).getTotalGold());
				dan = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_dan = Integer.parseInt(info.get(i).getBets());
			}
			if("2046".equals(info.get(i).getGameArea())){// 双
				System.out.println("2046-双-"+info.get(i).getTotalGold());
				shuang = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_shuang = Integer.parseInt(info.get(i).getBets());
			}
			if("2047".equals(info.get(i).getGameArea())){// red
				System.out.println("2047-red-"+info.get(i).getTotalGold());
				red = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_red = Integer.parseInt(info.get(i).getBets());
			}
			if("2048".equals(info.get(i).getGameArea())){// black
				System.out.println("2048-black-"+info.get(i).getTotalGold());
				black = Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				count_black = Integer.parseInt(info.get(i).getBets());
			}
			if("2052".equals(info.get(i).getGameArea())){// 1-2-3-4-5-6
				
				System.out.println("2052-[1-2-3-4-5-6]-"+info.get(i).getTotalGold());
				totalGold += Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", ""));
				Double av =  Average_( Double.parseDouble(info.get(i).getTotalGold().replaceAll(",", "")),6 );
				num_1 += av;num_2 += av;num_3 += av;num_4 += av;num_5 += av;num_6 += av;
				count_1++;count_2++;count_3++;count_4++;count_5++;count_6++;
			}
			
		
		}//循环结束---------------------------------	
			
		dto.setNum_1(num_1 >0? DoubleFormat(num_1.toString()):null);dto.setCount_1(count_1 >0? count_1.toString():null);
		dto.setNum_2(num_2 >0? DoubleFormat(num_2.toString()):null);dto.setCount_2(count_2 >0? count_2.toString():null);
		dto.setNum_3(num_3 >0? DoubleFormat(num_3.toString()):null);dto.setCount_3(count_3 >0? count_3.toString():null);
		dto.setNum_4(num_4 >0? DoubleFormat(num_4.toString()):null);dto.setCount_4(count_4 >0? count_4.toString():null);
		dto.setNum_5(num_5 >0? DoubleFormat(num_5.toString()):null);dto.setCount_5(count_5 >0? count_5.toString():null);
		dto.setNum_6(num_6 >0? DoubleFormat(num_6.toString()):null);dto.setCount_6(count_6 >0? count_6.toString():null);
		dto.setNum_7(num_7 >0? DoubleFormat(num_7.toString()):null);dto.setCount_7(count_7 >0? count_7.toString():null);
		dto.setNum_8(num_8 >0? DoubleFormat(num_8.toString()):null);dto.setCount_8(count_8 >0? count_8.toString():null);
		dto.setNum_9(num_9 >0? DoubleFormat(num_9.toString()):null);dto.setCount_9(count_9 >0? count_9.toString():null);
		
		dto.setNum_10(num_10 >0? DoubleFormat(num_10.toString()):null);dto.setCount_10(count_10 >0? count_10.toString():null);
		dto.setNum_11(num_11 >0? DoubleFormat(num_11.toString()):null);dto.setCount_11(count_11 >0? count_11.toString():null);
		dto.setNum_12(num_12 >0? DoubleFormat(num_12.toString()):null);dto.setCount_12(count_12 >0? count_12.toString():null);
		dto.setNum_13(num_13 >0? DoubleFormat(num_13.toString()):null);dto.setCount_13(count_13 >0? count_13.toString():null);
		dto.setNum_14(num_14 >0? DoubleFormat(num_14.toString()):null);dto.setCount_14(count_14 >0? count_14.toString():null);
		dto.setNum_15(num_15 >0? DoubleFormat(num_15.toString()):null);dto.setCount_15(count_15 >0? count_15.toString():null);
		dto.setNum_16(num_16 >0? DoubleFormat(num_16.toString()):null);dto.setCount_16(count_16 >0? count_16.toString():null);
		dto.setNum_17(num_17 >0? DoubleFormat(num_17.toString()):null);dto.setCount_17(count_17 >0? count_17.toString():null);
		dto.setNum_18(num_18 >0? DoubleFormat(num_18.toString()):null);dto.setCount_18(count_18 >0? count_18.toString():null);
		
		dto.setNum_19(num_19 >0? DoubleFormat(num_19.toString()):null);dto.setCount_19(count_19 >0? count_19.toString():null);
		dto.setNum_20(num_20 >0? DoubleFormat(num_20.toString()):null);dto.setCount_20(count_20 >0? count_20.toString():null);
		dto.setNum_21(num_21 >0? DoubleFormat(num_21.toString()):null);dto.setCount_21(count_21 >0? count_21.toString():null);
		dto.setNum_22(num_22 >0? DoubleFormat(num_22.toString()):null);dto.setCount_22(count_22 >0? count_22.toString():null);
		dto.setNum_23(num_23 >0? DoubleFormat(num_23.toString()):null);dto.setCount_23(count_23 >0? count_23.toString():null);
		dto.setNum_24(num_24 >0? DoubleFormat(num_24.toString()):null);dto.setCount_24(count_24 >0? count_24.toString():null);
		dto.setNum_25(num_25 >0? DoubleFormat(num_25.toString()):null);dto.setCount_25(count_25 >0? count_25.toString():null);
		dto.setNum_26(num_26 >0? DoubleFormat(num_26.toString()):null);dto.setCount_26(count_26 >0? count_26.toString():null);
		dto.setNum_27(num_27 >0? DoubleFormat(num_27.toString()):null);dto.setCount_27(count_27 >0? count_27.toString():null);
		
		dto.setNum_28(num_28 >0? DoubleFormat(num_28.toString()):null);dto.setCount_28(count_28 >0? count_28.toString():null);
		dto.setNum_29(num_29 >0? DoubleFormat(num_29.toString()):null);dto.setCount_29(count_29 >0? count_29.toString():null);
		dto.setNum_30(num_30 >0? DoubleFormat(num_30.toString()):null);dto.setCount_30(count_30 >0? count_30.toString():null);
		dto.setNum_31(num_31 >0? DoubleFormat(num_31.toString()):null);dto.setCount_31(count_31 >0? count_31.toString():null);
		dto.setNum_32(num_32 >0? DoubleFormat(num_32.toString()):null);dto.setCount_32(count_32 >0? count_32.toString():null);
		dto.setNum_33(num_33 >0? DoubleFormat(num_33.toString()):null);dto.setCount_33(count_33 >0? count_33.toString():null);
		dto.setNum_34(num_34 >0? DoubleFormat(num_34.toString()):null);dto.setCount_34(count_34 >0? count_34.toString():null);
		dto.setNum_35(num_35 >0? DoubleFormat(num_35.toString()):null);dto.setCount_35(count_35 >0? count_35.toString():null);
		dto.setNum_36(num_36 >0? DoubleFormat(num_36.toString()):null);dto.setCount_36(count_36 >0? count_36.toString():null);
		
		dto.setNum_0(num_0 >0? DoubleFormat(num_0.toString()):null);dto.setCount_0(count_0 >0? count_0.toString():null);
		dto.setRed(red >0? DoubleFormat(red.toString()):null);dto.setCount_red(count_red >0? count_red.toString():null);
		dto.setBlack(black >0? DoubleFormat(black.toString()):null);dto.setCount_black(count_black >0? count_black.toString():null);
		dto.setDa(da >0? DoubleFormat(da.toString()):null);dto.setCount_da(count_da >0? count_da.toString():null);
		dto.setXiao(xiao >0? DoubleFormat(xiao.toString()):null);dto.setCount_xiao(count_xiao >0? count_xiao.toString():null);
		dto.setDan(dan >0? DoubleFormat(dan.toString()):null);dto.setCount_dan(count_dan >0? count_dan.toString():null);
		dto.setShuang(shuang >0? DoubleFormat(shuang.toString()):null);dto.setCount_shuang(count_shuang >0? count_shuang.toString():null);
		
		dto.setNum_1_12(num_1_12 >0? DoubleFormat(num_1_12.toString()):null);dto.setCount_1_12(count_1_12 >0? count_1_12.toString():null);
		dto.setNum_13_24(num_13_24 >0? DoubleFormat(num_13_24.toString()):null);dto.setCount_13_24(count_13_24 >0? count_13_24.toString():null);
		dto.setNum_25_36(num_25_36 >0? DoubleFormat(num_25_36.toString()):null);dto.setCount_25_36(count_25_36 >0? count_25_36.toString():null);
		dto.setNum_1_34(num_1_34 >0? DoubleFormat(num_1_34.toString()):null);dto.setCount_1_34(count_1_34 >0? count_1_34.toString():null);
		dto.setNum_2_35(num_2_35 >0? DoubleFormat(num_2_35.toString()):null);dto.setCount_2_35(count_2_35 >0? count_2_35.toString():null);
		dto.setNum_3_36(num_3_36 >0? DoubleFormat(num_3_36.toString()):null);dto.setCount_3_36(count_3_36 >0? count_3_36.toString():null);
		
		
		
		return dto;
		
	}
	
	
	
	
	
}

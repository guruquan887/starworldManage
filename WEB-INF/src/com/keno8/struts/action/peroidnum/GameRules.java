package com.keno8.struts.action.peroidnum;

public class GameRules {
	
	//单双玩法
	public static int singleOrPairs(String[] params) {
		if (params.length == 20) {
			int n = 0;
			for (int i = 0; i < params.length; i++) {
				n += Integer.parseInt(params[i]);
			}
			if (n > 0 && n % 2 == 0) {
				return 1;// 双
			} else if (n > 0 && n % 2 != 0) {
				return 2;// 单
			}
		}
		return 0;
	}

	// 龙虎和玩法
	public static int serpentAndTiger(String[] params) {
		if (params.length == 20) {
			int n = 0;
			for (int i = 0; i < params.length; i++) {
				if (Integer.parseInt(params[i]) < 41) {
					n++;
				}
			}
			if (n > 10) {// 龙盘
				return 1;
			} else if (n == 10) {// 和盘
				return 2;
			} else if (n < 10) {// 虎盘
				return 3;
			}
		}
		return 0;// 出错
	}

	// 大小
	public static int largeOrSmall(String[] params) {
		if (params.length == 20) {
			int n = 0;
			for (int i = 0; i < params.length; i++) {
				n += Integer.parseInt(params[i]);
			}
			System.out.println("总和为"+n);
			if (n > 810) {
				return 1;// large
			} else {
				return 2;// small
			}
		}
		return 0;// 出错
	}

	// 倍率
	/**
	 * 1: 1 3
	 * 2: 1 0 2 10 
	 * 3: 1 0 2 2 3 20 
	 * 4: 1 0 2 1 3 5 4 50 
	 * 5: 1 0 2 0 3 2 4 20 5 250
	 *
	 * 
	 */
	public static int rate(String[] params, String rateNumber) {
		if (rateNumber == null || rateNumber.equals("")) {
			return 0;
		}
		String[] rates = rateNumber.split("&");
		int n = 0;
		int m = 0;
		for (int i = 0; i < params.length; i++) {
			if (!params[i].equals("")) {
				for (int z = 0; z < rates.length; z++) {
					if (Integer.parseInt(params[i]) == Integer.parseInt(rates[z])) {
						n++;
					}
				}
			}
		}
		switch (rates.length) {
		case 1:
			if (n == 1) {
				m = 3;
			}
			break;
		case 2:
			if (n == 1) {
				m = 0;
			} else if (n == 2) {
				m = 10;
			}
			break;
		case 3:
			if (n == 1) {
				m = 0;
			} else if (n == 2) {
				m = 2;
			} else if (n == 3) {
				m = 20;
			}
			break;
		case 4:
			if (n == 1) {
				m = 0;
			} else if (n == 2) {
				m = 1;
			} else if (n == 3) {
				m = 5;
			} else if (n == 4) {
				m = 50;
			}
			break;
		case 5:
			if (n == 1) {
				m = 0;
			} else if (n == 2) {
				m = 0;
			} else if (n == 3) {
				m = 2;
			} else if (n == 4) {
				m = 20;
			} else if (n == 5) {
				m = 250;
			}
			break;
		}
		return m;
	}

	public static int andValue(String[] params, int type) {
		int n = 0;
		int temp = 0;
		for (int i = 0; i < params.length; i++) {
			n += Integer.parseInt(params[i]);
		}

		switch (type) {
		case 1:
			if (n % 2 == 0) {
				temp = 1;// 双
			} else {
				temp = 2;// 单
			}
			break;
		case 2:
			if (n > 13) {
				temp = 1;// 大
			} else {
				temp = 2;// 小
			}
			break;
		}

		return temp;
	}
	

	public static int location(int num, int type) {
		int temp = 0;
		switch (type) {
		case 1:// 单双
			if (num % 2 == 0) {
				temp = 1; // 双
			} else {
				temp = 2; // 单
			}
			break;
		case 2:// 大小
			if (num >= 5) {
				temp = 1;
			} else {
				temp = 2;
			}
			break;

		}
		return temp;
	}
	
	public static int special(String[] params){
		int temp = 1;
		int h = Integer.parseInt(params[0]);
		int t = Integer.parseInt(params[1]);
		int o = Integer.parseInt(params[2]);
		if(h==t && t==o){
			temp  = 1;//豹子
		}else if(((h+1)==t && t==(o-1)) ||(h==9 && t==0 && o==1)){//顺子
			
			temp = 2;//顺子
		}else if((h==t && t!=o) || (h==o && t!=h)  || (t==o && h!=o)){//对子
			temp = 3;
		}else if((h+1)==t && o!=(t+1)) {//半顺子
			temp = 4;
		}else {
			temp = 5 ;//杂六
		}
		return temp;
	}

}

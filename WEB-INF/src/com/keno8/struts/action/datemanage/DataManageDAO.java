package com.keno8.struts.action.datemanage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DataManageDAO {
	private DataSource ds;
	
	public DataManageDAO(DataSource ds) {
		super();
		this.ds = ds;
	}

	public String returnInfo(){
		StringBuffer str = new StringBuffer();
		str.append("没有符合条件的结果");
		return str.toString();
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
			Label labelC0 = new jxl.write.Label(0, 0, "序号"); 
			Label labelC1 = new jxl.write.Label(1, 0, "充值卡卡号");
			Label labelC2 = new jxl.write.Label(2, 0, "充值卡密码");
			Label labelC3 = new jxl.write.Label(3, 0, "银子数量");
			Label labelC4 = new jxl.write.Label(4, 0, "金额");
			//Label labelC4 = new jxl.write.Label(4, 0, "状态");
			ws.addCell(labelC0);
			ws.addCell(labelC1);
			ws.addCell(labelC2);
			ws.addCell(labelC3);
			ws.addCell(labelC4);
			stat=con.createStatement();
			
			String sql="select * from GameCardView where userID=0 order by CreateDate ";
			System.out.println(sql);
			rs = stat.executeQuery(sql);
			int temp_row=0;
			while (rs.next()){  
				temp_row ++;            
				String cardNo = rs.getString("CardNo"); 
				String cardPass = rs.getString("CardPassword");
				String score = rs.getString("Score");
				String batchNo=rs.getString("BatchNo");
				
				jxl.write.Number labelN0 = new jxl.write.Number(0, temp_row, temp_row);            
				jxl.write.Label labelN1 = new jxl.write.Label(1, temp_row, cardNo);
				jxl.write.Label labelN2 = new jxl.write.Label(2, temp_row, cardPass);
				jxl.write.Label labelN3 = new jxl.write.Label(3, temp_row, score);
				jxl.write.Label labelN4 = new jxl.write.Label(4, temp_row, batchNo);
				ws.addCell(labelN0);
				ws.addCell(labelN1);
				ws.addCell(labelN2);
				ws.addCell(labelN3);
				ws.addCell(labelN4);
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
	
	public void backup(String targetName){
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "backup database kaileba to disk='E:\\dbbackup\\"+targetName+"' with init";
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
	 
	 public int delete(String time){
		 int r = 0;
		 String sql = "{ call GSP_GP_DelRecord (?)}";
		 Connection con = null;
		 Statement callstmt = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, time);
			ps.execute();
			r = 1;
			if(rs!=null){
				rs.close();rs=null;
			}
			if(callstmt!=null){
				callstmt.close();callstmt=null;
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
				if(callstmt!=null){
					callstmt.close();callstmt=null;
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

}

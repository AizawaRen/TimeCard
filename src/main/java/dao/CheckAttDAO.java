package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import model.AttendanceData;

public class CheckAttDAO {
	
	private Connection conn;
	private PreparedStatement pStmt;
	DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");
	DateTimeFormatter mFormat = DateTimeFormatter.ofPattern("yyyy/MM");
	
	public void dbConnect() throws SQLException {
		ConnectDB cdb = new ConnectDB();
		conn = cdb.connect();
	}
	
	public Double checkTotalTime(String enumber) {
//		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		Double total = 0.0;
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date like ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, '%' + now.format(mFormat) + '%');
			ResultSet rs = pStmt.executeQuery();
			AttendanceData atd = new AttendanceData();
			
			while(rs.next()){
				if(rs.getString(3) != null) {
					LocalTime start = LocalTime.parse(rs.getString(3), tFormat);
					atd.setStartTime(start);
				}
				if(rs.getString(4) != null) {
					LocalTime finish = LocalTime.parse(rs.getString(4), tFormat);
					atd.setFinishTime(finish);
				}
				if(rs.getString(5) != null) {
					LocalTime StartBreak = LocalTime.parse(rs.getString(5), tFormat);
					atd.setStartBreakTime(StartBreak);
				}
				if(rs.getString(6) != null) {
					LocalTime FinishBreak = LocalTime.parse(rs.getString(6), tFormat);
					atd.setFinishBreakTime(FinishBreak);
				}
				if(rs.getString(5) != null && rs.getString(6) != null) {
					atd.calcBreakTime();
				}
				if(rs.getString(3) != null && rs.getString(4) != null) {
					atd.calcWorkingHours();
					Duration wt = atd.getWorkingHours();
					total += wt.toMinutes();
				}
				
			}
			return total;
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return total;
	}
	
	public String checkStart(String enumber) {
//		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString(3);
			}else {
				return null;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return null;
	}
	
	public String checkFinish(String enumber) {
//		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next() && rs.getString(4) != null) {
				return rs.getString(4);
			}else {
				return null;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return null;
	}
	public String checkStartBreak(String enumber) {
//		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next() && rs.getString(5) != null) {
				return rs.getString(5);
			}else {
				return null;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return null;
	}
	public String checkFinishBreak(String enumber) {
//		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next() && rs.getString(6) != null) {
				return rs.getString(6);
			}else {
				return null;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return null;
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import model.AttendanceData;

public class AttendanceDataDAO {

	private Connection conn;
	private PreparedStatement pStmt;
	DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	DateTimeFormatter mFormat = DateTimeFormatter.ofPattern("yyyy/MM");
	DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");
	
	public void dbConnect() throws SQLException {
		ConnectDB cdb = new ConnectDB();
		
		conn = cdb.connect();
	}
	
	public Boolean setStart(String enumber) {
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
				return false;
			} else {
				sql = "INSERT INTO attendance (enumber, work_date, start) VALUES (?, ?, ?);";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, enumber);
				pStmt.setString(2, now.format(dFormat));
				pStmt.setString(3, now.format(tFormat));
				pStmt.executeUpdate();
				return true;
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
		
		
		return false;
	}
	
	public Boolean setFinish(String enumber) {
//		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(!rs.next() || rs.getString(4) != null) {
				return false;
			}else if(rs.getString(5) != null && rs.getString(6) == null) {
				return false;
			} else {
				sql = "UPDATE attendance SET finish = ? WHERE enumber = ? and  work_date = ?;";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, now.format(tFormat));
				pStmt.setString(2, enumber);
				pStmt.setString(3, now.format(dFormat));
				pStmt.executeUpdate();
				return true;
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
		
		
		return false;
	}
	
	public Boolean setStartBreak(String enumber) {
//		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(!rs.next()) {
				return false;
			}else if(rs.getString(5) != null) {
				return false;
			} else {
				sql = "UPDATE attendance SET start_break = ? WHERE enumber = ? and work_date = ?;";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, now.format(tFormat));
				pStmt.setString(2, enumber);
				pStmt.setString(3, now.format(dFormat));
				pStmt.executeUpdate();
				return true;
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
		
		
		return false;
	}
	
	public Boolean setFinishBreak(String enumber) {
//		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(!rs.next()) {
				return false;
			}else if(rs.getString(5) == null || rs.getString(6) != null) {
				return false;
			} else {
				sql = "UPDATE attendance SET finish_break = ? WHERE enumber = ? and  work_date = ?;";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, now.format(tFormat));
				pStmt.setString(2, enumber);
				pStmt.setString(3, now.format(dFormat));
				pStmt.executeUpdate();
				return true;
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
		
		
		return false;
	}
	
	public List<AttendanceData> MonthlyList(String enumber){
		
		List<AttendanceData> ThisMonthList = new LinkedList<AttendanceData>();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		
		try{
			dbConnect();
			String sql = "SELECT * FROM Attendance WHERE enumber = ? and work_date like ?;";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, '%' + now.format(mFormat) + '%');
			ResultSet rs = pStmt.executeQuery();
	
			while(rs.next()){
				AttendanceData atd = new AttendanceData();
				atd.setWorkDate(LocalDate.parse(rs.getString(2),dFormat));
			
				if(rs.getString(3) != null) {
					LocalTime startTime = LocalTime.parse(rs.getString(3), tFormat);
					atd.setStartTime(startTime);
				}
				if(rs.getString(4) != null) {
					LocalTime finishTime = LocalTime.parse(rs.getString(4), tFormat);
					atd.setFinishTime(finishTime);
				}
				if(rs.getString(5) != null) {
					LocalTime breakStartTime = LocalTime.parse(rs.getString(5), tFormat);
					atd.setStartBreakTime(breakStartTime);
				}
				if(rs.getString(6) != null) {
					LocalTime breakFinishTime = LocalTime.parse(rs.getString(6), tFormat);
					atd.setFinishBreakTime(breakFinishTime);
				}
				if(rs.getString(5) != null && rs.getString(6) != null) {
					atd.calcBreakTime();
				}
				if(rs.getString(3) != null && rs.getString(4) != null) {
					atd.calcWorkingHours();
				}
				
				ThisMonthList.add(atd);
			 }
			
			
		}catch(SQLException e){
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
		
		return ThisMonthList;
	}
}

package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceData {
	private LocalDate workDate;
	private LocalTime startTime;
	private LocalTime finishTime;
	private LocalTime startBreakTime;
	private LocalTime finishBreakTime;
	private Duration breakTime;
	private Duration workingHours;
	
	public LocalDate getWorkDate() {
		return workDate;
	}
	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public LocalTime getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(LocalTime finishTime) {
		this.finishTime = finishTime;
	}
	
	public LocalTime getStartBreakTime() {
		return startBreakTime;
	}
	public void setStartBreakTime(LocalTime startBreakTime) {
		this.startBreakTime = startBreakTime;
	}
	
	public LocalTime getFinishBreakTime() {
		return finishBreakTime;
	}
	public void setFinishBreakTime(LocalTime finishBreakTime) {
		this.finishBreakTime = finishBreakTime;
	}
	
	public Duration getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(Duration breakTime) {
		this.breakTime = breakTime;
	}
	
	public Duration getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(Duration workingHours) {
		this.workingHours = workingHours;
	}
	
	
		
	public void calcWorkingHours() {
		Duration duration = Duration.between(startTime, finishTime);
		setWorkingHours(duration);
		if(breakTime != null) {
			duration = workingHours.minus(breakTime);
			setWorkingHours(duration);
		}
	}
	
	public void calcBreakTime() {
		Duration duration = Duration.between(startBreakTime, finishBreakTime);
		setBreakTime(duration);
	}
}

package petize;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class Class {
	
	private DayOfWeek dayOfWeek;
	private LocalTime  starts_at;
	private LocalTime ends_at;
	
	
	public Class(DayOfWeek dayOfWeek, LocalTime starts_at, LocalTime ends_at) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.starts_at = starts_at;
		this.ends_at = ends_at;
	}

	public long getDailyCourseHours() {			
		Duration result =Duration.between(getStarts_at(), getEnds_at());
		
		return result.toNanos();
	}	


	@Override
	public String toString() {
		return "Class [dayOfWeek=" + dayOfWeek + "]";
	}

	public LocalTime getStarts_at() {
		return starts_at;
	}

	public void setStarts_at(LocalTime starts_at) {
		this.starts_at = starts_at;
	}

	public LocalTime getEnds_at() {
		return ends_at;
	}

	public void setEnds_at(LocalTime ends_at) {
		this.ends_at = ends_at;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	
	
	
	

}

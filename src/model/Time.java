package model;

public class Time {
	int hours = 0;
	int minutes = 0;
	int seconds = 0;
	
	public Time(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public static Time fromString(String s) {
		if (s.equals("")) return null;
		
		try {
			String[] hoursMinutesSeconds = s.split(":");
			int hours = Integer.parseInt(hoursMinutesSeconds[0]);
			int minutes = Integer.parseInt(hoursMinutesSeconds[1]);
			int seconds = Integer.parseInt(hoursMinutesSeconds[2]);
			return new Time(hours, minutes, seconds);
		} catch (NumberFormatException e) {
			System.out.println("Badly formatted time " + s);
			return null;
		}
		
	}
}

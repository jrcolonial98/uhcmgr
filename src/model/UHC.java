package model;

import java.util.Calendar;

public class UHC {
	int id = 0;
	Calendar date = null;
	String motd = "";
	Time startTime = null;
	String winner = "";
	String seed = "";
	String version = "";
	int season = 0;
	
	public UHC() {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Calendar getDate() {
		return date;
	}
	
	public void setMotd(String motd) {
		this.motd = motd;
	}
	public String getMotd() {
		return motd;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getStartTime() {
		return startTime;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getWinner() { 
		return winner;
	}
	
	public void setSeed(String seed) {
		this.seed = seed;
	}
	public String getSeed() {
		return seed;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersion() {
		return version;
	}
	
	public void setSeason(int season) {
		this.season = season;
	}
	public int getSeason() {
		return season;
	}
	
	public String toString() {
		if (motd == null || motd == "") {
			return "UHC " + id;
		}
		else {
			return "UHC " + id + ": " + motd;
		}
	}
}

package model;

public class Kill {
	final static String FRIENDLY_FIRE = "friendlyfire";
	
	String killer = "";
	String victim = ""; 
	Time timestamp = null;
	String method = "";
	int uhc = 0;
	String comment = "";
	
	public Kill() { }
	
	public void setKiller(String killer) {
		this.killer = killer;
	}
	public String getKiller() {
		return killer;
	}
	
	public void setVictim(String victim) {
		this.victim = victim;
	}
	public String getVictim() {
		return victim;
	}
	
	public void setTimestamp(Time timestamp) {
		this.timestamp = timestamp;
	}
	public Time getTimestamp() {
		return timestamp;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMethod() {
		return method;
	}
	
	public void setUhc(int uhc) {
		this.uhc = uhc;
	}
	public int getUhc() {
		return uhc;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	
	public boolean isPvp() {
		return !killer.equals("");
	}
	public boolean isFriendlyFire() {
		return method == FRIENDLY_FIRE;
	}
}

package model;

public class Registration {
	String player = "'";
	int uhc = 0;
	String team = "";
	String comment = "";
	
	public Registration() { }
	
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getPlayer() {
		return player;
	}
	
	public void setUhc(int uhc) {
		this.uhc = uhc;
	}
	public int getUhc() {
		return uhc;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	public String getTeam() {
		return team;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
}

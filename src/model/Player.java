package model;

public class Player {
	String username = "";
	String nickname = "";
	String defaultTeam = "";
	
	public Player() { }
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}
	
	public void setDefaultTeam(String defaultTeam) {
		this.defaultTeam = defaultTeam;
	}
	public String getDefaultTeam() {
		return defaultTeam;
	}
}

package stats;

import java.util.Comparator;
import java.util.Map;

public class TeammateProfile {
	String username;
	String nickname;
	
	int gamesPlayed;
	int totalTeammates;
	
	Map<String,Integer> teams;
	Map<String,Integer> teamedWith;
	
	public TeammateProfile() { }
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getTotalTeammates() {
		return totalTeammates;
	}
	public void setTotalTeammates(int totalTeammates) {
		this.totalTeammates = totalTeammates;
	}
	
	public Map<String,Integer> getTeams() {
		return teams;
	}
	public void setTeams(Map<String,Integer> teams) {
		this.teams = teams;
	}
	
	public Map<String,Integer> getTeamedWith() {
		return teamedWith;
	}
	public void setTeamedWith(Map<String,Integer> teamedWith) {
		this.teamedWith = teamedWith;
	}
	
	public double averageTeamSize() {
		return (1 + ((double)totalTeammates / (double)gamesPlayed));
	}
	
	public String toString() {
		String s = "";
		
		s += "TEAMMATE PROFILE: " + username + "\n";
		s += gamesPlayed + " games played, " + averageTeamSize() + " average team size.\n";
		s += "\n";
		s += "Teams Played On:\n";
		for (String key : teams.keySet()) {
			s += key + " x" + teams.get(key) + "\n";
		}
		s += "\n";
		s += "Teamed With:\n";
		for (String key : teamedWith.keySet()) {
			s += key + " x" + teamedWith.get(key) + "\n";
		}
		
		return s;
	}
}

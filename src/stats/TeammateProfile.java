package stats;

import java.util.Comparator;
import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

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
	
	public static class AverageTeamSizeComparator implements Comparator<TeammateProfile> {
		public int compare(TeammateProfile o1, TeammateProfile o2) {
			double ats1 = o1.averageTeamSize();
			double ats2 = o2.averageTeamSize();
			
			if (ats1 == ats2) {
				return 0;
			}
			else if (ats1 > ats2) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	
	public String toString() {
		String s = "";
		
		s += "TEAMMATE PROFILE: " + username + "\n";
		s += gamesPlayed + " games played, " + averageTeamSize() + " average team size.\n";
		s += "\n";
		s += "Teams Played On:\n";
		List<Map.Entry<String,Integer>> sortedTeams = new ArrayList<>(teams.entrySet());
		sortedTeams.sort(Map.Entry.comparingByValue());
		Collections.reverse (sortedTeams);
		for (Map.Entry k : sortedTeams) {
			s += k.getKey() + " x" + k.getValue() + "\n";
		}
		s += "\n";
		s += "Teamed With:\n";
		List<Map.Entry<String,Integer>> sortedTeamedWith = new ArrayList<>(teamedWith.entrySet());
		sortedTeamedWith.sort(Map.Entry.comparingByValue());
		Collections.reverse (sortedTeamedWith);
		for (Map.Entry k : sortedTeamedWith) {
			s += k.getKey() + " x" + k.getValue() + "\n";
		}
		return s;
	}
}

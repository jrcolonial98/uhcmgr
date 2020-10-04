package stats;

import java.util.Comparator;
import java.util.Map;

public class PlayerProfile {
	String username;
	String nickname;
	
	int kills;
	int bowKills;
	int pvpDeaths;
	int totalDeaths;
	int gamesPlayed;
	int wins;
	
	Map<String,Integer> killed;
	Map<String,Integer> killedBy;
	
	public PlayerProfile() { }
	
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
	
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public int getBowKills() {
		return bowKills;
	}
	public void setBowKills(int bowKills) {
		this.bowKills = bowKills;
	}
	
	public int getPvpDeaths() {
		return pvpDeaths;
	}
	public void setPvpDeaths(int pvpDeaths) {
		this.pvpDeaths = pvpDeaths;
	}
	
	public int getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public Map<String,Integer> getKilled() {
		return killed;
	}
	public void setKilled(Map<String,Integer> killed) {
		this.killed = killed;
	}
	
	public Map<String,Integer> getKilledBy() {
		return killedBy;
	}
	public void setKilledBy(Map<String,Integer> killedBy) {
		this.killedBy = killedBy;
	}
	
	public double kdr() {
		if (pvpDeaths > 0) {
			return ((double)kills / (double)pvpDeaths);
		}
		return (double)kills;
	}
	public double winRate() {
		return ((double)wins / (double)gamesPlayed);
	}
	public double killsPerGame() {
		return ((double)kills / (double)gamesPlayed);
	}
	public int pveDeaths() {
		return totalDeaths - pvpDeaths;
	}
	
	public static class KdrComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			double kdr1 = o1.kdr();
			double kdr2 = o2.kdr();
			
			if (kdr1 == kdr2) {
				return 0;
			}
			else if (kdr1 > kdr2) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	public static class KillsComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			return o1.getKills() - o2.getKills();
		}
	}
	public static class BowKillsComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			return o1.getBowKills() - o2.getBowKills();
		}
	}
	public static class WinRateComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			double wr1 = o1.winRate();
			double wr2 = o2.winRate();
			
			if (wr1 == wr2) {
				return 0;
			}
			else if (wr1 > wr2) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	public static class KillsPerGameComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			double kr1 = o1.killsPerGame();
			double kr2 = o2.killsPerGame();
			
			if (kr1 == kr2) {
				return 0;
			}
			else if (kr1 > kr2) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	
	public String toString() {
		String s = "";
		
		s += "PLAYER PROFILE: " + username + "\n";
		s += gamesPlayed + " games played, " + winRate() + " win rate\n";
		s += kills + " kills, " + pvpDeaths + " PvP deaths, " + pveDeaths() + " PvE deaths, kdr = " + kdr() + "\n";
		s += "Kills per game: " + killsPerGame() + "\n\n";
		s += "Players Killed:\n";
		for (String key : killed.keySet()) {
			s += key + " x" + killed.get(key) + "\n";
		}
		s += "\n";
		s += "Players Killed By:\n";
		for (String key : killedBy.keySet()) {
			s += key + " x" + killedBy.get(key) + "\n";
		}
		
		return s;
	}
}

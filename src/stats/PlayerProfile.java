package stats;

import java.util.Comparator;
import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class PlayerProfile {
	String username;
	String nickname;
	
	int kills;
	int swordKills; // Note this is all melee: sword, axe, etc
	int bowKills; // Note this also includes crossbows
	int pvpDeaths;
	int totalDeaths;
	int gamesPlayed;
	int wins;
	
	Map<String,Integer> killed;
	Map<String,Integer> killedBy;
	Map<String,Integer> environmentKilledBy;
	
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
	
	public int getSwordKills() {
		return swordKills;
	}
	public void setSwordKills(int swordKills) {
		this.swordKills = swordKills;
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
	
	public int getLosses() {
		return gamesPlayed - wins;
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
	
	public Map<String,Integer> getEnvironmentKilledBy() {
		return environmentKilledBy;
	}
	public void setEnvironmentKilledBy(Map<String,Integer> environmentKilledBy) {
		this.environmentKilledBy = environmentKilledBy;
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
	public double killsPerLoss() {
		return ((double)kills / (double)(gamesPlayed-wins));
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
	public static class KillsPerLossComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			double kr1 = o1.killsPerLoss();
			double kr2 = o2.killsPerLoss();
			
			if (kr1 == kr2) {
				return 0;
			}
			else if (Double.isNaN(kr1) && Double.isNaN(kr2)) {
				return 0;
			}
			else if (kr1 == 1.0/0) {
				return 1;
			}
			else if (kr2 == 1.0/0) {
				return -1;
			}
			else if (Double.isNaN(kr1)) {
				return -1;
			}
			else if (Double.isNaN(kr2)) {
				return 1;
			}
			else if (kr1 > kr2) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	public static class WinsComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			return o1.getWins() - o2.getWins();
		}
	}
	public static class GamesPlayedComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			return o1.getGamesPlayed() - o2.getGamesPlayed();
		}
	}
	public static class LossesComparator implements Comparator<PlayerProfile> {
		public int compare(PlayerProfile o1, PlayerProfile o2) {
			return o1.getLosses() - o2.getLosses();
		}
	}
	
	public String toString() {
		String s = "";
		
		s += "PLAYER PROFILE: " + username + "\n";
		s += gamesPlayed + " games played, " + winRate() + " win rate\n";
		s += kills + " kills, " + pvpDeaths + " PvP deaths, " + pveDeaths() + " PvE deaths, kdr = " + kdr() + "\n";
		s += "Kills by type: Melee = " + getSwordKills() + ", Ranged = " + getBowKills() + ", Other/Unknown = " + (kills - getSwordKills() - getBowKills()) + "\n";
		s += "Kills per game: " + killsPerGame() + "\n\n";
		s += "Players Killed:\n";
		List<Map.Entry<String,Integer>> sortedKilleds = new ArrayList<>(killed.entrySet());
		sortedKilleds.sort(Map.Entry.comparingByValue());
		Collections.reverse (sortedKilleds);
		for (Map.Entry k : sortedKilleds) {
			s += k.getKey() + " x" + k.getValue() + "\n";
		}
		s += "\n";
		s += "Players Killed By:\n";
		List<Map.Entry<String,Integer>> sortedKilledBys = new ArrayList<>(killedBy.entrySet());
		sortedKilledBys.sort(Map.Entry.comparingByValue());
		Collections.reverse (sortedKilledBys);
		for (Map.Entry k : sortedKilledBys) {
			s += k.getKey() + " x" + k.getValue() + "\n";
		}
		s += "\n";
		s += "PvE Deaths:\n";
		List<Map.Entry<String,Integer>> sortedEnvironmentKilledBys = new ArrayList<>(environmentKilledBy.entrySet());
		sortedEnvironmentKilledBys.sort(Map.Entry.comparingByValue());
		Collections.reverse (sortedEnvironmentKilledBys);
		for (Map.Entry k : sortedEnvironmentKilledBys) {
			s += k.getKey() + " x" + k.getValue() + "\n";
		}
		
		return s;
	}
}

package stats;

import java.util.Comparator;

public class Performance {
	String player;
	int uhc;
	int kills;
	int totalPlayers;
	
	public Performance(String player, int uhc, int kills, int totalPlayers) {
		this.player = player;
		this.uhc = uhc;
		this.kills = kills;
		this.totalPlayers = totalPlayers;
	}
	
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
	
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getKills() {
		return kills;
	}
	
	public void setTotalPlayers(int totalPlayers) {
		this.totalPlayers = totalPlayers;
	}
	public int getTotalPlayers() {
		return totalPlayers;
	}
	
	public double fractionOfPlayersKilled() {
		return ((double)kills) / ((double)totalPlayers);
	}
	
	public String toString() {
		String s = "";
		s += player;
		s += ": ";
		s += kills;
		s += " kills (";
		s += fractionOfPlayersKilled();
		s += " of total players)\n";
		s += "UHC ";
		s += uhc;
		s += "\n";
		
		return s;
	}
	
	public static class RelativeComparator implements Comparator<Performance> {
		public int compare(Performance o1, Performance o2) {
			double pct1 = o1.fractionOfPlayersKilled();
			double pct2 = o2.fractionOfPlayersKilled();
			
			int kills1 = o1.getKills();
			int kills2 = o2.getKills();
			
			if (pct1 == pct2) {
				return kills1 - kills2;
			}
			else if (pct1 > pct2) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	public static class AbsoluteComparator implements Comparator<Performance> {
		public int compare(Performance o1, Performance o2) {
			double pct1 = o1.fractionOfPlayersKilled();
			double pct2 = o2.fractionOfPlayersKilled();
			
			int kills1 = o1.getKills();
			int kills2 = o2.getKills();
			
			if (kills1 == kills2) {
				if (pct1 == pct2) {
					return 0;
				}
				else if (pct1 > pct2) {
					return 1;
				}
				else {
					return -1;
				}
			}
			return kills1 - kills2;
		}
		
	}
}

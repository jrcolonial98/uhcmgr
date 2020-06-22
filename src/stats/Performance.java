package stats;

public class Performance implements Comparable {
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

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Performance)) {
			System.out.println("Comparing Performance with something else");
			return 0;
		}
		/*
		if (((Performance)o).getKills() == kills) {
			if (((Performance)o).fractionOfPlayersKilled() < fractionOfPlayersKilled()) {
				return -1;
			}
			else if (((Performance)o).fractionOfPlayersKilled() > fractionOfPlayersKilled()) {
				return 1;
			}
			return 0;
		}
		return ((Performance)o).getKills() - kills; // change this line to fractionOfPlayersKilled if desired
		*/
		Performance po = (Performance)o;
		int pkills = ((Performance)o).getKills();
		double ppercent = ((Performance)o).fractionOfPlayersKilled();
		
		if (ppercent == fractionOfPlayersKilled()) {
			return pkills - kills;
		}
		if (ppercent < fractionOfPlayersKilled()) {
			return -1;
		}
		else if (ppercent > fractionOfPlayersKilled()) {
			return 1;
		}
		return 0;
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
}

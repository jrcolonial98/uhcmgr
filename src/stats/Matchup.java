package stats;

import java.util.Comparator;

public class Matchup {
	String player1;
	String player2;
	int player1Score;
	int player2Score;
	
	public Matchup(String player1, String player2, int player1Score, int player2Score) {
		this.player1 = player1;
		this.player2 = player2;
		this.player1Score = player1Score;
		this.player2Score = player2Score;
	}
	
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	
	public String getPlayer2() {
		return player2;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	
	public int getPlayer1Score() {
		return player1Score;
	}
	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}
	
	public int getPlayer2Score() {
		return player2Score;
	}
	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Matchup)) {
			return false;
		}
		
		Matchup om = (Matchup)o;
		
		if (player1.equals(om.getPlayer1())) {
			if (player2.equals(om.getPlayer2())) {
				return true;
			}
			return false;
		}
		else if (player1.equals(om.getPlayer2())) {
			if (player2.equals(om.getPlayer1())) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String s = "";
		
		if (player1Score >= player2Score) {
			s += player1 + " vs. " + player2 + ": ";
			s += player1Score + " - " + player2Score;
		}
		else {
			s += player2 + " vs. " + player1 + ": ";
			s += player2Score + " - " + player1Score;
		}
		
		return s;
	}
	
	public int matchupScore() {
		return Math.abs(player1Score - player2Score);
	}
	
	public static class OneSidedComparator implements Comparator<Matchup> {
		public int compare(Matchup o1, Matchup o2) {
			if (o1.matchupScore() > o2.matchupScore()) {
				return 1;
			}
			else if (o2.matchupScore() > o1.matchupScore()) {
				return -1;
			}
			else {
				int total1 = o1.getPlayer1Score() + o1.getPlayer2Score();
				int total2 = o2.getPlayer1Score() + o2.getPlayer2Score();
				
				return total2 - total1;
			}
		}
	}
	public static class CloseComparator implements Comparator<Matchup> {
		public int compare(Matchup o1, Matchup o2) {
			final double ABS_DIFFERENCE_WEIGHT = 0.5;
			final double TOTAL_ENCOUNTERS_WEIGHT = 0.5;
			
			double abs1 = (double)(Math.abs(o1.getPlayer1Score() - o1.getPlayer2Score()));
			double abs2 = (double)(Math.abs(o2.getPlayer1Score() - o2.getPlayer2Score()));
			abs1 *= ABS_DIFFERENCE_WEIGHT;
			abs2 *= ABS_DIFFERENCE_WEIGHT;
			
			double totEnc1 = (double)(o1.getPlayer1Score() + o1.getPlayer2Score());
			double totEnc2 = (double)(o2.getPlayer1Score() + o2.getPlayer2Score());
			totEnc1 *= TOTAL_ENCOUNTERS_WEIGHT;
			totEnc2 *= TOTAL_ENCOUNTERS_WEIGHT;
			
			double final1 = abs1 + totEnc1;
			double final2 = abs2 + totEnc2;
			
			if (final1 > final2) {
				return 1;
			}
			else if (final2 > final1) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	public static class MostEncountersComparator implements Comparator<Matchup> {
		public int compare(Matchup o1, Matchup o2) {
			int total1 = o1.getPlayer1Score() + o1.getPlayer2Score();
			int total2 = o2.getPlayer1Score() + o2.getPlayer2Score();
			
			return total1 - total2;
		}
	}
}

package stats;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

import model.Kill;
import model.Player;
import model.Registration;
import model.UHC;

public class Elo {
	List<UHC> uhcs;
	List<Kill> kills;
	List<Player> players;
	List<Registration> registrations;
	
	public Elo(List<UHC> uhcs, List<Kill> kills, List<Player> players, List<Registration> registrations) {
		this.uhcs = uhcs; 
		this.kills = kills;
		this.players = players;
		this.registrations = registrations;
	}
	
	public void writeEloToFile() {
		try {
			FileWriter csvWriter = new FileWriter("eloMaster.csv");
			for(Entry<String, List<Double>> eloHistories : eloHistories().entrySet()) {
				String hString = eloHistories.getValue().toString();
				
				//System.out.println(eloHistories);
				//System.out.println(hString.substring(1,hString.length()-1));
				csvWriter.append(eloHistories.getKey());
				csvWriter.append(",");
				csvWriter.append(hString.substring(1,hString.length()-1));
				csvWriter.append("\n");
			}

			csvWriter.flush();
			csvWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to write to eloMaster.csv");
			e.printStackTrace();
		}
	}
	
	public Map<String,Integer> currentElos() {
		Map<String,Integer> elos = new HashMap<String,Integer>();
		
		for(Entry<String, List<Double>> eloHistories : eloHistories().entrySet()) {
			int currentElo = (int)(double) eloHistories.getValue().get(eloHistories.getValue().size()-1);
			elos.put(eloHistories.getKey(), currentElo);
		}
		return elos;
	}
	
	public Map<String, List<Double>> eloHistories() {
		double startElo = 1000;
		
		// key is username of player and the value is a list of his/her elo after each uhc
		// e.g. index 3 gives you the elo of that player after uhc 3
		Map<String, List<Double>> histories = new HashMap<String, List<Double>>(); 
		
		//populate allHistories
		for(Player p : players) {
			List<Double> history = new ArrayList<Double>();
			history.add(startElo);
			histories.put(p.getUsername(),history);
		}
		
		double kElo;
		double vElo;
		double[] newElos;
		
		List<Double> kHist;
		List<Double> vHist;
		int skHist;
		int svHist;
		
		String killer;
		String victim;
		
		for(Kill k : kills) {
			// skip pve and friendly fire kills
			if(k.isPvp() && !k.isFriendlyFire()) {
							
				//Fix issue where you get a null pointer error when k.getKiller or getVictim has a misspelling 
				//System.out.println(k.getKiller());
				// get usernames
				killer = k.getKiller();
				victim = k.getVictim();
							
				// get previous elos
				kHist = histories.get(killer);
				vHist = histories.get(victim);
							
				skHist = kHist.size();
				svHist = vHist.size();
							
				kElo = kHist.get(skHist-1);
				vElo = vHist.get(svHist-1);
							
				//get new elos
				newElos = playMatch(kElo, vElo);
							
				//handle updating and adding to history
				kHist = updateHistory(kHist, skHist, k.getUhc(),newElos[0]);
				vHist = updateHistory(vHist, svHist,k.getUhc(),newElos[1]);
			}
		}
		return histories;
	}
	
	private double[] playMatch(double kElo, double vElo){
		double kEloNew;
		double vEloNew;
		
		double k = 294; // max amount rating can fluctuate by is +/- 32
		
		double expDiff = Math.pow(10, (vElo-kElo)/(double)400);
		double probKillerWins = Math.pow(1+expDiff,-1);
		double pointsToKiller = (k*(1-probKillerWins));
		
		kEloNew = kElo+pointsToKiller;
		vEloNew = vElo-pointsToKiller;
		
		double[] newElos = {kEloNew, vEloNew};
		
		return newElos;
	}
	
	private List<Double> updateHistory(List<Double> vHist,int size, int uhc, double newElos ) {
		
		int diff = uhc - (size-1);
		
		//updates when kill is from the same uhc as most recent entry in history
		//copies over last elo and adds new elo when history is out of date
		if(diff == 0) {
			vHist.set(size-1,newElos);
		}
		else {
			for(int i = 0; i< diff-1;i++) {
				vHist.add(vHist.get(size-1));
			}
			vHist.add(newElos);
		}
		return vHist;
	}
}

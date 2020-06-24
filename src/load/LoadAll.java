package load;

import java.util.*; 
import model.Kill;
import model.Player;
import model.UHC;
import model.Registration;

import stats.Stats;
import stats.Performance;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class LoadAll {
	
	// TODO:
	// Write a bunch of Comparators
	// then swap them out easily and rank people by any arbitrary comparison
	
	public static void main(String[] args) {
		List<UHC> uhcs = UHCLoader.loadUHCs("src/load/uhc1.csv");
		List<Kill> kills = KillLoader.loadKills("src/load/kill1.csv");
		List<Player> players = PlayerLoader.loadPlayers("src/load/player1.csv");
		List<Registration> registrations = RegistrationLoader.loadRegistrations("src/load/registration1.csv");
		
		Stats stats = new Stats(uhcs, kills, players, registrations);
		
		//System.out.println(stats.updateElo().get("1ottsco"));
		//System.out.println(stats.updateElo());
		//System.out.println((float)1*0.8);
		for(Entry<String, List<Integer>> eloHistories : stats.updateElo().entrySet()) {
			System.out.println(eloHistories);
		}
		
		/*
		for(Kill k : kills) {
			System.out.println(k.getKiller().equals(""));
		}
		
	
		stats.printKillsAndDeathsAndKdr();
		
		stats.printPlayerProfile("XmasGoose"); //5, 8, 12
		
		List<Performance> ps = stats.allPerformances();
		
		for (int i = 0; i < 10; i++) {
			Performance p = ps.get(i);
			//System.out.println(p);
		}
		*/
	}
}

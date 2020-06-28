package load;

import model.Kill;
import model.Player;
import model.UHC;
import model.Registration;

import java.util.List;

import stats.Stats;
import stats.Elo;

public class LoadAll {
	
	// TODO:
	// Write a bunch of Comparators
	// then swap them out easily and rank people by any arbitrary comparison
	
	public static void main(String[] args) {
		List<UHC> uhcs = UHCLoader.loadUHCs("src/load/uhc3.csv");
		List<Kill> kills = KillLoader.loadKills("src/load/kill3.csv");
		List<Player> players = PlayerLoader.loadPlayers("src/load/player3.csv");
		List<Registration> registrations = RegistrationLoader.loadRegistrations("src/load/registration3.csv");
		
		Stats stats = new Stats(uhcs, kills, players, registrations);
		Elo elo = new Elo(uhcs, kills, players, registrations);
		
	
		//stats.printKillsAndDeathsAndKdr();
		
		//stats.printPlayerProfile("DemonicFish"); //5, 8, 12
		
		//List<Performance> ps = stats.allPerformances();
		
		//for (int i = 0; i < 10; i++) {
			//Performance p = ps.get(i);
			//System.out.println(p);
		//}
	}
}

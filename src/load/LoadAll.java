package load;

import model.Kill;
import model.Player;
import model.UHC;
import model.Registration;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import stats.Stats;
import stats.Elo;
import stats.Performance;
import stats.PlayerProfile;

public class LoadAll {
	
	public static void main(String[] args) {
		List<UHC> uhcs = UHCLoader.loadUHCs("src/load/uhc3.csv");
		List<Kill> kills = KillLoader.loadKills("src/load/kill3.csv");
		List<Player> players = PlayerLoader.loadPlayers("src/load/player3.csv");
		List<Registration> registrations = RegistrationLoader.loadRegistrations("src/load/registration3.csv");
		
		Stats stats = new Stats(uhcs, kills, players, registrations);
		Elo elo = new Elo(uhcs, kills, players, registrations);

		
		List<Performance> performances = stats.allPerformances();
		Comparator<Performance> c1 = new Performance.AbsoluteComparator();
		Collections.sort(performances, c1);
		
		List<PlayerProfile> profiles = stats.allPlayerProfiles();
		Comparator<PlayerProfile> c2 = new PlayerProfile.KillsComparator();
		Collections.sort(profiles, c2);
		
		for (int i = 0; i < profiles.size(); i++) {
			PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			//PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.getKills());
		}
		
		System.out.println(stats.playerProfile("Dunkersplatt"));
		
		List<Performance> playerPerformances = stats.performancesByPlayer("XmasGoose");
		
		for (Performance p : playerPerformances) {
			System.out.println(p.toString());
		}
		
		Map<String,Integer> elos = elo.currentElos();
		for (String name : elos.keySet()) {
			System.out.println(name + ": " + elos.get(name));
		}
	}
}

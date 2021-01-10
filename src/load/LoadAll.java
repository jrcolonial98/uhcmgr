package load;

import model.Kill;
import model.Player;
import model.UHC;
import model.Registration;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import stats.Stats;
import stats.Elo;
import stats.Matchup;
import stats.Performance;
import stats.PlayerProfile;

public class LoadAll {
	
	public static void main(String[] args) throws IOException {
		List<UHC> uhcs = UHCLoader.loadUHCs("src/load/uhc3.csv");
		List<Kill> kills = KillLoader.loadKills("src/load/kill3.csv");
		List<Player> players = PlayerLoader.loadPlayers("src/load/player3.csv");
		List<Registration> registrations = RegistrationLoader.loadRegistrations("src/load/registration3.csv");
		
		Stats stats = new Stats(uhcs, kills, players, registrations);
		Elo elo = new Elo(uhcs, kills, players, registrations);

		
		List<Performance> performances = stats.getAllPerformances();
		Comparator<Performance> c1 = new Performance.RelativeComparator();
		Collections.sort(performances, c1);
		
		for (int i = 0; i < 15; i++) {
			Performance p = performances.get(performances.size() - 1 - i);
			System.out.println(p.getPlayer() + ": " + p.getKills());
			System.out.println(p.fractionOfPlayersKilled() + " of players killed");
		}
		
		List<PlayerProfile> profiles = stats.getPlayerProfiles();
		Comparator<PlayerProfile> c2 = new PlayerProfile.KillsPerGameComparator();
		Collections.sort(profiles, c2);
		
		stats.writeToCsv("data.csv");
		
		for (int i = 0; i < profiles.size(); i++) {
			PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			//PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.killsPerGame());
		}
		
		
		System.out.println(stats.getPlayerProfile("XmasGoose"));
		
		List<Performance> playerPerformances = stats.performancesByPlayer("Dunkersplatt");
		for (Performance p : playerPerformances) {
			System.out.println(p.toString());
		}
		
		Map<String,Integer> killsByTeam = stats.killsByTeam();
		for (String team : killsByTeam.keySet()) {
			System.out.println(team + ": " + killsByTeam.get(team));
		}
		
		Map<String,Integer> winsByTeam = stats.winsByTeam();
		for (String team : winsByTeam.keySet()) {
			System.out.println(team + ": " + winsByTeam.get(team));
		}
		
		System.out.println();
		Map<String,Double> killAvgByTeam = stats.killAvgByTeam();
		for (String team : killAvgByTeam.keySet()) {
			System.out.println(team + ": " + killAvgByTeam.get(team));
		}
		
		List<Matchup> matchups = stats.getAllMatchups();
		Comparator<Matchup> matchupsComparator = new Matchup.MostEncountersComparator();
		Collections.sort(matchups, matchupsComparator);
		for (int i = 0; i < 18; i++) {
			Matchup m = matchups.get(matchups.size() - 1 - i);
			System.out.println(m);
		}
		
		
		
		Map<String,Integer> elos = elo.currentElos();
		for (String name : elos.keySet()) {
			System.out.println(name + ": " + elos.get(name));
		}
		
		
		
	}
}

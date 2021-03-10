package load;

import model.Kill;
import model.Player;
import model.UHC;
import model.Registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import stats.Stats;
import stats.Superlatives;
import stats.Elo;
import stats.Matchup;
import stats.Performance;
import stats.PlayerProfile;
import stats.TeammateProfile;

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
		
		System.out.println("Best Performances (by % of players killed):\n");
		
		for (int i = 0; i < 15; i++) {
			Performance p = performances.get(performances.size() - 1 - i);
			System.out.println(p.getPlayer() + ": " + p.getKills());
			System.out.println(p.fractionOfPlayersKilled() + " of players killed");
		}
		
		List<PlayerProfile> profiles = stats.getPlayerProfiles();
		Comparator<PlayerProfile> c2 = new PlayerProfile.KillsPerGameComparator();
		Collections.sort(profiles, c2);
		
		// Write out kill total across games into file
		
		stats.writeToCsv("data.csv");
		
		System.out.println("\n-----------------------------\n");
		System.out.println("Kills per Game:\n");
		
		for (int i = 0; i < profiles.size(); i++) {
			PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.killsPerGame());
		}
		
		System.out.println("\n-----------------------------\n");
		System.out.println("Kills by Team:\n");
		
		Map<String,Integer> killsByTeam = stats.killsByTeam();
		for (String team : killsByTeam.keySet()) {
			System.out.println(team + ": " + killsByTeam.get(team));
		}

		System.out.println("\n-----------------------------\n");
		System.out.println("Wins per Team:\n");

		Map<String,Integer> winsByTeam = stats.winsByTeam();
		for (String team : winsByTeam.keySet()) {
			System.out.println(team + ": " + winsByTeam.get(team));
		}
		
		System.out.println("\n-----------------------------\n");
		System.out.println("Kill Avg by Team:\n");
		
		System.out.println();
		Map<String,Double> killAvgByTeam = stats.killAvgByTeam();
		for (String team : killAvgByTeam.keySet()) {
			System.out.println(team + ": " + killAvgByTeam.get(team));
		}
		
		System.out.println("\n-----------------------------\n");
		System.out.println("Top Matchups:\n");
		
		List<Matchup> matchups = stats.getAllMatchups();
		Comparator<Matchup> matchupsComparator = new Matchup.MostEncountersComparator();
		Collections.sort(matchups, matchupsComparator);
		for (int i = 0; i < 18; i++) {
			Matchup m = matchups.get(matchups.size() - 1 - i);
			System.out.println(m);
		}
		
		System.out.println("\n-----------------------------\n");
		
		System.out.println("Kills:\n");
		
		Comparator<PlayerProfile> c2_alternate = new PlayerProfile.KillsComparator();
		Collections.sort(profiles, c2_alternate);
		
		for (int i = 0; i < profiles.size(); i++) {
			PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.getKills());
		}
		
		System.out.println("\n-----------------------------\n");
		
		System.out.println("Wins:\n");
		
		Comparator<PlayerProfile> c3 = new PlayerProfile.WinsComparator();
		Collections.sort(profiles, c3);
		
		for (int i = 0; i < profiles.size(); i++) {
			PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.getWins());
		}
		
		System.out.println("\n-----------------------------\n");
		
		System.out.println("Games Played:\n");
		
		Comparator<PlayerProfile> c4 = new PlayerProfile.GamesPlayedComparator();
		Collections.sort(profiles, c4);
		
		for (int i = 0; i < profiles.size(); i++) {
			PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.getGamesPlayed());
		}
		
		System.out.println("\n-----------------------------\n");
		
		System.out.println("Win Rate:\n");
		
		Comparator<PlayerProfile> c5 = new PlayerProfile.WinRateComparator();
		Collections.sort(profiles, c5);
		
		for (int i = 0; i < profiles.size(); i++) {
			PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.winRate());
		}
		
		System.out.println("\n-----------------------------\n");
		
		System.out.println("Kills per Loss:\n");
		
		Comparator<PlayerProfile> c7 = new PlayerProfile.KillsPerLossComparator();
		Collections.sort(profiles, c7);
		
		for (int i = 0; i < profiles.size(); i++) {
			PlayerProfile p = profiles.get(profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.killsPerLoss());
		}
		
		System.out.println("\n-----------------------------\n");
		
		System.out.println("Average Team Size:\n");
		
		List<TeammateProfile> tm_profiles = stats.getTeammateProfiles();
		Comparator<TeammateProfile> c6 = new TeammateProfile.AverageTeamSizeComparator();
		Collections.sort(tm_profiles, c6);
		
		for (int i = 0; i < tm_profiles.size(); i++) {
			TeammateProfile p = tm_profiles.get(tm_profiles.size() - 1 - i);
			System.out.println(p.getUsername() + ": " + p.averageTeamSize());
		}
		
		System.out.println("\n-----------------------------\n");
		
		System.out.println(stats.getPlayerProfile("Dunkersplatt"));
		
		List<Performance> playerPerformances2 = stats.performancesByPlayer("Dunkersplatt");
		for (Performance p : playerPerformances2) {
			System.out.println(p.toString());
		}
		
		System.out.println(stats.getTeammateProfile("Dunkersplatt"));
		
		/* Map<Integer,Integer> uniquePlayerKills = Superlatives.uniquePlayerKillsPerUhc(kills, uhcs);
		List<Entry<Integer, Integer>> list = new ArrayList<>(uniquePlayerKills.entrySet());
		list.sort(Entry.comparingByValue());
		for (Entry<Integer,Integer> entry : list) {
			System.out.println("UHC " + entry.getKey() + ": " + entry.getValue() + " unique players");
		} */
		
		
		//Map<String,Integer> elos = elo.currentElos();
		//for (String name : elos.keySet()) {
		//	System.out.println(name + ": " + elos.get(name));
		//}
		
		
		
	}
}

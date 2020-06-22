package stats;

import model.*; // TODO
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Stats {
	List<UHC> uhcs;
	List<Kill> kills;
	List<Player> players;
	List<Registration> registrations;
	
	public Stats(List<UHC> uhcs, List<Kill> kills, List<Player> players, List<Registration> registrations) {
		this.uhcs = uhcs; 
		this.kills = kills;
		this.players = players;
		this.registrations = registrations;
	}
	
	public void printKillsAndDeathsAndKdr() {
		Map<String, List<Kill>> kills = killsByPlayer();
		Map<String, List<Kill>> deaths = deathsByPlayer();
		
		for (Player player : players) {
			String un = player.getUsername();
			int numKills = 0;
			int numDeaths = 0;
			double kdr = 0.0;
			if (kills.containsKey(un)) {
				numKills = kills.get(un).size();
			}
			if (deaths.containsKey(un)) {
				numDeaths = deaths.get(un).size();
			}
			
			if (numDeaths != 0) {
				kdr = ((double)numKills) / ((double)numDeaths);
			}
			
			System.out.println(un + ": " + numKills + " kills, " + numDeaths + " deaths, kdr = " + kdr);
			System.out.println("total: " + (numKills + numDeaths));
		}
	}
	
	public void printPlayerProfile(String username) {
		List<Kill> kills = killsByPlayer().get(username);
		List<Kill> deaths = deathsByPlayer().get(username);
		
		Map<String,Integer> killed = new HashMap<String,Integer>();
		Map<String,Integer> killedBy = new HashMap<String,Integer>();
		
		int numKills = 0;
		int numDeaths = 0;
		
		if (kills != null) {
			numKills = kills.size();
			for (Kill kill : kills) {
				String victim = kill.getVictim();
				
				if (!killed.containsKey(victim)) {
					killed.put(victim, 0);
				}
				int oldVal = killed.get(victim);
				int newVal = oldVal + 1;
				killed.put(victim, newVal);
			}
		}
		if (deaths != null) {
			numDeaths = deaths.size();
			for (Kill kill : deaths) {
				String killer = kill.getKiller();
				
				if (!killedBy.containsKey(killer)) {
					killedBy.put(killer, 0);
				}
				int oldVal = killedBy.get(killer);
				int newVal = oldVal + 1;
				killedBy.put(killer, newVal);
			}
		}
		
		double kdr = 0.0;
		if (numDeaths != 0.0) {
			kdr = ((double)numKills) / ((double)numDeaths);
		}
		
		int gamesPlayed = 0;
		for (Registration registration : registrations) {
			if (registration.getPlayer().equals(username)) {
				gamesPlayed++;
			}
		}
		
		double killsPerGame = 0.0;
		if (gamesPlayed != 0) {
			killsPerGame = ((double)numKills) / ((double)gamesPlayed);
		}
		
		System.out.println("PLAYER PROFILE: " + username);
		System.out.println("" + gamesPlayed + " games played");
		System.out.println("" + numKills + " kills, " + numDeaths + " deaths, kdr = " + kdr);
		System.out.println("Kills per game: " + killsPerGame);
		System.out.println();
		System.out.println("Players Killed:");
		for (String key : killed.keySet()) {
			System.out.println(key + " x" + killed.get(key));
		}
		System.out.println();
		System.out.println("Players Killed By:");
		for (String key : killedBy.keySet()) {
			System.out.println(key + " x" + killedBy.get(key));
		}
	}
	
	public Map<String, List<Kill>> killsByPlayer() {
		Map<String, List<Kill>> map = new HashMap<String, List<Kill>>();
		
		for (Kill kill : kills) {
			String killer = kill.getKiller();
			if (!map.containsKey(killer)) {
				map.put(killer, new ArrayList<Kill>());
			}
			map.get(killer).add(kill);
		}
		
		return map;
	}
	
	public Map<String, List<Kill>> deathsByPlayer() {
		Map<String, List<Kill>> map = new HashMap<String, List<Kill>>();
		
		for (Kill kill : kills) {
			if (!kill.isPvp() || kill.isFriendlyFire()) continue;
			
			String victim = kill.getVictim();
			if (!map.containsKey(victim)) {
				map.put(victim, new ArrayList<Kill>());
			}
			map.get(victim).add(kill);
		}
		
		return map;
	}
	
	private Player playerFromString(String username) {
		for (Player player : players) {
			if (player.getUsername().equals(username)) {
				return player;
			}
		}
		return null;
	}
	
	public List<Performance> allPerformances() {
		List<Performance> performances = new ArrayList<Performance>();
		
		// TODO: make this less slow
		
		for (Registration r : registrations) {
			String player = r.getPlayer();
			int uhc = r.getUhc();
			
			int numKills = 0;
			int totalPlayers = 0;
			for (Kill k : kills) {
				if (k.getUhc() == uhc && k.getKiller().equals(player) && k.isPvp() && (!k.isFriendlyFire())) {
					numKills++;
				}
			}
			for (Registration r2 : registrations) {
				if (r2.getUhc() == uhc) {
					totalPlayers++;
				}
			}
			
			Performance p = new Performance(player, uhc, numKills, totalPlayers);
			performances.add(p);
		}
		
		Collections.sort(performances);
		
		return performances;
	}
}

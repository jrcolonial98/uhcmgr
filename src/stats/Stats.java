package stats;

import model.*; // TODO
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

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
	
	public List<PlayerProfile> allPlayerProfiles() {
		List<PlayerProfile> list = new ArrayList<PlayerProfile>();
		for (Player player : players) {
			PlayerProfile p = playerProfile(player.getUsername());
			list.add(p);
		}
		return list;
	}
	
	public PlayerProfile playerProfile(String username) {
		List<Kill> kills = killsByPlayer().get(username);
		List<Kill> deaths = deathsByPlayer().get(username);
		List<Kill> pvpDeaths = pvpDeathsByPlayer().get(username);
		
		Map<String,Integer> killed = new HashMap<String,Integer>();
		Map<String,Integer> killedBy = new HashMap<String,Integer>();
		
		int numKills = 0;
		int numBowKills = 0;
		int numPvpDeaths = 0;
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
				
				if (kill.getMethod().equals("shot")) {
					numBowKills++;
				}
			}
		}
		if (pvpDeaths != null) {
			numPvpDeaths = pvpDeaths.size();
			for (Kill kill : pvpDeaths) {
				String killer = kill.getKiller();
				
				if (!killedBy.containsKey(killer)) {
					killedBy.put(killer, 0);
				}
				int oldVal = killedBy.get(killer);
				int newVal = oldVal + 1;
				killedBy.put(killer, newVal);
			}
		}
		if (deaths != null) {
			numDeaths = deaths.size();
		}
		
		int gamesPlayed = 0;
		int wins = 0;
		for (Registration registration : registrations) {
			if (registration.getPlayer().equals(username)) {
				gamesPlayed++;
				for (UHC uhc : uhcs) {
					if (registration.getUhc() == uhc.getId()) {
						if (registration.getTeam().equals(uhc.getWinner())) {
							wins++;
						}
						break;
					}
				}
			}
		}
		
		Player player = playerFromString(username);
		String nickname = player.getNickname();
		
		PlayerProfile p = new PlayerProfile();
		p.setUsername(username);
		p.setNickname(nickname);
		p.setKills(numKills);
		p.setBowKills(numBowKills);
		p.setPvpDeaths(numPvpDeaths);
		p.setTotalDeaths(numDeaths);
		p.setGamesPlayed(gamesPlayed);
		p.setWins(wins);
		p.setKilled(killed);
		p.setKilledBy(killedBy);
		
		return p;
	}
	
	public Map<String, List<Kill>> killsByPlayer() {
		Map<String, List<Kill>> map = new HashMap<String, List<Kill>>();
		
		for (Kill kill : kills) {
			if (!kill.isPvp() || kill.isFriendlyFire()) continue;
			
			String killer = kill.getKiller();
			if (!map.containsKey(killer)) {
				map.put(killer, new ArrayList<Kill>());
			}
			map.get(killer).add(kill);
		}
		
		return map;
	}
	
	public Map<String, List<Kill>> pvpDeathsByPlayer() {
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
	
	public Map<String, List<Kill>> deathsByPlayer() {
		Map<String, List<Kill>> map = new HashMap<String, List<Kill>>();
		
		for (Kill kill : kills) {
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
		
		return performances;
	}
	
	public List<Performance> performancesByPlayer(String player) {
		List<Performance> performances = new ArrayList<Performance>();
		
		List<Performance> allPerformances = allPerformances();
		
		for (Performance p : allPerformances) {
			if (p.getPlayer().equals(player)) {
				performances.add(p);
			}
		}
		
		return performances;
	}
}


package stats;

import model.*; // TODO
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

public class Stats {
	// members passed in constructor
	List<UHC> uhcs;
	List<Kill> kills;
	List<Player> players;
	List<Registration> registrations;
	
	// members generated in constructor
	List<PlayerProfile> playerProfiles;
	List<TeammateProfile> teammateProfiles;
	List<Performance> allPerformances;
	Map<String,List<Performance>> allPerformancesByPlayer;
	List<Matchup> allMatchups;
	
	public Stats(List<UHC> uhcs, List<Kill> kills, List<Player> players, List<Registration> registrations) {
		this.uhcs = uhcs; 
		this.kills = kills;
		this.players = players;
		this.registrations = registrations;
		
		this.playerProfiles = allPlayerProfiles();
		this.teammateProfiles = allTeammateProfiles();
		this.allPerformances = allPerformances();
		this.allPerformancesByPlayer = allPerformancesByPlayer();
		this.allMatchups = allMatchups();
	}
	
	public List<PlayerProfile> getPlayerProfiles() {
		return playerProfiles;
	}
	public PlayerProfile getPlayerProfile(String username) {
		for (PlayerProfile p : playerProfiles) {
			if (p.getUsername().equals(username)) {
				return p;
			}
		}
		return null;
	}
	
	public List<TeammateProfile> getTeammateProfiles() {
		return teammateProfiles;
	}
	public TeammateProfile getTeammateProfile(String username) {
		for (TeammateProfile p : teammateProfiles) {
			if (p.getUsername().equals(username)) {
				return p;
			}
		}
		return null;
	}
	public List<Performance> getAllPerformances() {
		return allPerformances;
	}
	public Map<String,List<Performance>> getAllPerformancesByPlayer() {
		return allPerformancesByPlayer;
	}
	public List<Performance> performancesByPlayer(String player) {
		return allPerformancesByPlayer.get(player);
	}
	public List<Matchup> getAllMatchups() {
		return allMatchups;
	}
	
	private List<PlayerProfile> allPlayerProfiles() {
		List<PlayerProfile> list = new ArrayList<PlayerProfile>();
		for (Player player : players) {
			PlayerProfile p = playerProfile(player.getUsername());
			list.add(p);
		}
		return list;
	}
	
	private List<TeammateProfile> allTeammateProfiles() {
		List<TeammateProfile> list = new ArrayList<TeammateProfile>();
		for (Player player : players) {
			TeammateProfile p = teammateProfile(player.getUsername());
			list.add(p);
		}
		return list;
	}
	
	private PlayerProfile playerProfile(String username) {
		List<Kill> kills = killsByPlayer().get(username);
		List<Kill> deaths = deathsByPlayer().get(username);
		List<Kill> pvpDeaths = pvpDeathsByPlayer().get(username);
		
		Map<String,Integer> killed = new HashMap<String,Integer>();
		Map<String,Integer> killedBy = new HashMap<String,Integer>();
		Map<String,Integer> environmentKilledBy = new HashMap<String,Integer>();
		
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
			for (Kill kill : deaths) {
				String method = kill.getMethod();
				
				if (kill.isPvp()) {
					continue;
				}
				
				if (!environmentKilledBy.containsKey(method)) {
					environmentKilledBy.put(method, 0);
				}
				int oldVal = environmentKilledBy.get(method);
				int newVal = oldVal + 1;
				environmentKilledBy.put(method, newVal);
			}
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
		p.setEnvironmentKilledBy(environmentKilledBy);
		
		return p;
	}
	
	private TeammateProfile teammateProfile(String username) {	
		List<Registration> allTeams = teamsByPlayer().get(username);
		
		Map<String,Integer> teams = new HashMap<String,Integer>();
		Map<String,Integer> teamedWith = new HashMap<String,Integer>();
		
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
		
		// NOTE: This goes through registrations like O(n^2) so it could potentially be slow
		
		int totalTeammates = 0;
		for (Registration registration : registrations) {
			if (registration.getPlayer().equals(username)) {
				String team = registration.getTeam();
				if (!teams.containsKey(team)) {
					teams.put(team, 0);
				}
				int oldVal = teams.get(team);
				int newVal = oldVal + 1;
				teams.put(team, newVal);
				
				int uhc = registration.getUhc();
				
				for (Registration registration2 : registrations) {
					if (registration2.getTeam().equals(team) && registration2.getUhc() == uhc && !registration2.getPlayer().equals(username)) {
						totalTeammates++;
						String teammate = registration2.getPlayer();
						if (!teamedWith.containsKey(teammate)) {
							teamedWith.put(teammate, 0);
						}
						int oldVal2 = teamedWith.get(teammate);
						int newVal2 = oldVal2 + 1;
						teamedWith.put(teammate, newVal2);
					}
				}
			}
		}
		
		Player player = playerFromString(username);
		String nickname = player.getNickname();
		
		TeammateProfile p = new TeammateProfile();
		p.setUsername(username);
		p.setNickname(nickname);
		p.setGamesPlayed(gamesPlayed);
		p.setTeams(teams);
		p.setTeamedWith(teamedWith);
		p.setTotalTeammates(totalTeammates);
		
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
	
	public Map<String, List<Registration>> teamsByPlayer() {
		Map<String, List<Registration>> map = new HashMap<String, List<Registration>>();
		
		for (Registration registration : registrations) {
			//if (!registration.isPvp() || registration.isFriendlyFire()) continue;
			
			String team = registration.getTeam();
			if (!map.containsKey(team)) {
				map.put(team, new ArrayList<Registration>());
			}
			map.get(team).add(registration);
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
	
	private List<Performance> allPerformances() {
		List<Performance> performances = new ArrayList<Performance>();
		
		// TODO: make this less slow
		
		for (Registration r : registrations) {
			String player = r.getPlayer();
			String team = r.getTeam();
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
			
			Performance p = new Performance(player, team, uhc, numKills, totalPlayers);
			performances.add(p);
		}
		
		return performances;
	}
	
	private Map<String, List<Performance>> allPerformancesByPlayer() {
		Map<String, List<Performance>> map = new HashMap<String, List<Performance>>();
		
		List<Performance> performances = allPerformances();
		
		for (Performance p : performances) {
			String player = p.getPlayer();
			
			if (!map.containsKey(player)) {
				map.put(player, new ArrayList<Performance>());
			}
			
			map.get(player).add(p);
		}
		
		return map;
	}
	
	private List<Matchup> allMatchups() {
		List<Matchup> matchups = new ArrayList<Matchup>();
		
		for (PlayerProfile p : playerProfiles) {
			String p1 = p.getUsername();
			for (String p2 : p.getKilled().keySet()) {
				int p1Score = p.getKilled().get(p2);
				int p2Score = 0;
				if (p.getKilledBy().containsKey(p2)) {
					p2Score = p.getKilledBy().get(p2);
				}
				Matchup m = new Matchup(p1, p2, p1Score, p2Score);
				
				boolean shouldAdd = true;
				for (Matchup m2 : matchups) {
					if (m.equals(m2)) {
						shouldAdd = false;
						break;
					}
				}
				
				if (shouldAdd) {
					matchups.add(m);
				}
			}
		}
		
		return matchups;
	}
	
	public void writeToCsv(String fileName) throws IOException {
		Map<String, List<Performance>> performances = allPerformancesByPlayer();
		
		FileWriter writer = new FileWriter(fileName);
		
		List<String> header = new ArrayList<String>();
		header.add("Player");
		header.add("Team");
		for (UHC uhc : uhcs) {
			String value = "UHC " + uhc.getId();
			header.add(value);
		}
		String joinedHeader = String.join(",", header);
		writer.write(joinedHeader + "\n");
		
		for (String player : performances.keySet()) {
			List<String> values = new ArrayList<String>();
			values.add(player);
			values.add(""); // todo: team
			//values.add(""); // todo: team pic url ?
			
			int total = 0;
			int uhc = 0;
			for (Performance p : performances.get(player)) {
				uhc++;
				while (p.getUhc() > uhc) {
					values.add("" + total);
					uhc++;
				}
				total += p.getKills();
				values.add("" + total);
			}
			while (uhc < uhcs.size()) {
				uhc++;
				values.add("" + total);
			}
			
			String line = String.join(",", values);
			
			writer.write(line + "\n");
		}
		
		writer.close();
	}
	
	public Map<String,Integer> killsByTeam() {
		List<Performance> performances = allPerformances();
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		for (Performance p : performances) {
			for (Registration r : registrations) {
				if (r.getUhc() == p.getUhc() && r.getPlayer().equals(p.getPlayer())) {
					if (!map.keySet().contains(p.getTeam())) {
						map.put(p.getTeam(), 0);
					}
					int oldVal = map.get(p.getTeam());
					int newVal = oldVal + p.getKills();
					map.put(p.getTeam(), newVal);
				}
			}
		}
		
		return map;
	}
	
	public Map<String,Double> killAvgByTeam() {
		Map<String,Double> map = new HashMap<String,Double>();
		
		Map<String,Integer> killsByTeam = killsByTeam();
		
		Map<String,Integer> teamSize = new HashMap<String,Integer>();
		
		for (String team : killsByTeam.keySet()) {
			double total = (double) killsByTeam.get(team);
			int size = 0;
			for (Player p : players) {
				if (p.getDefaultTeam().equals(team)) {
					size++;
				}
			}
			double dsize = (double)size;
			double avg = total / dsize;
			map.put(team, avg);
		}
		
		return map;
	}
	
	public Map<String,Integer> winsByTeam() {
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		for (UHC uhc : uhcs) {
			String winner = uhc.getWinner();
			
			if (!map.keySet().contains(winner)) {
				map.put(winner, 0);
			}
			int oldVal = map.get(winner);
			int newVal = oldVal + 1;
			map.put(winner, newVal);
		}
		
		return map;
	}
}


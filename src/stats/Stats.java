package stats;

import model.*; // TODO
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Stats {
	List<Player> players;
	List<UHC> uhcs;
	List<Team> teams;
	List<Kill> kills;
	
	public Stats(List<Player> players, List<UHC> uhcs, List<Team> teams, List<Kill> kills) {
		this.players = players;
		this.uhcs = uhcs; 
		this.teams = teams;
		this.kills = kills;
	}
	
	public Map<Player, List<Kill>> killsByPlayer() {
		Map<Player, List<Kill>> map = new HashMap<Player, List<Kill>>();
		
		for (Kill kill : kills) {
			Player killer = kill.getKiller();
			if (!map.containsKey(killer)) {
				map.put(killer, new ArrayList<Kill>());
			}
			map.get(killer).add(kill);
		}
		
		return map;
	}
	
	public Map<Player, List<Kill>> deathsByPlayer() {
		Map<Player, List<Kill>> map = new HashMap<Player, List<Kill>>();
		
		for (Kill kill : kills) {
			if (!kill.getPvp()) continue;
			
			Player victim = kill.getVictim();
			if (!map.containsKey(victim)) {
				map.put(victim, new ArrayList<Kill>());
			}
			map.get(victim).add(kill);
		}
		
		return map;
	}
}

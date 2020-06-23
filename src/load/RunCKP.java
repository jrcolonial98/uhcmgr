// OttscoMC

package load;

import model.Kill;
import model.Player;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.*;
import java.util.Collections;

public class RunCKP {
	
	// Cumulative Kill Points
	
	public static void main (String [] args) {
		int PLAYER_VALUE_WEIGHT = 3; // lower means killing a better player is more of a big deal, high enough numbers will count all kills the same
		
		List<Player> players = PlayerLoader.loadPlayers("../src/load/player1.csv");
		List<Kill> kills = KillLoader.loadKills("../src/load/kill1.csv");
		
		Map<String, Integer> points = new HashMap<String, Integer>();
		
		for (Player player : players) {
			points.put(player.getUsername(), PLAYER_VALUE_WEIGHT);
		}
		
		for (Kill kill : kills) {
			String killer = kill.getKiller();
			String victim = kill.getVictim();
			if (points.containsKey(killer) && points.containsKey(victim) && !kill.getMethod().equals("friendlyfire")) {
				points.put(killer, points.get(killer) + points.get(victim) / PLAYER_VALUE_WEIGHT);
			}
		}
		
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		points.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		
		for (Map.Entry<String,Integer> player : sortedMap.entrySet()) {
			System.out.print(player.getKey());
			System.out.print(",");
			System.out.println(player.getValue());
		}
	}
}

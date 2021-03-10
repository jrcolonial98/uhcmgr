package stats;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Kill;
import model.UHC;

public class Superlatives {
	public static Map<Integer,Integer> uniquePlayerKillsPerUhc(List<Kill> kills, List<UHC> uhcs) {
		Map<Integer,Integer> results = new HashMap<Integer,Integer>();
		for (UHC uhc : uhcs) {
			Set<String> uniquePlayers = new HashSet<String>();
			for (Kill kill : kills) {
				if (kill.getUhc() != uhc.getId()) {
					continue;
				}
				if (kill.isFriendlyFire() || !kill.isPvp()) {
					continue;
				}
				uniquePlayers.add(kill.getKiller());
			}
			results.put(uhc.getId(), uniquePlayers.size());
		}
		return results;
	}
}

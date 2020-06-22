package load;

import model.Kill;
import model.Player;
import model.UHC;
import model.Registration;

import stats.Stats;

import java.util.List;

public class LoadAll {
	public static void main(String[] args) {
		List<UHC> uhcs = UHCLoader.loadUHCs("src/load/uhc1.csv");
		List<Kill> kills = KillLoader.loadKills("src/load/kill1.csv");
		List<Player> players = PlayerLoader.loadPlayers("src/load/player1.csv");
		List<Registration> registrations = RegistrationLoader.loadRegistrations("src/load/registration1.csv");
		
		Stats stats = new Stats(uhcs, kills, players, registrations);
		
		//stats.printKillsAndDeathsAndKdr();
		
		stats.printPlayerProfile("1ottsco"); //5, 8, 12
	}
}

package load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*; 
import model.Kill;
import model.Player;
import model.UHC;
import model.Registration;

import stats.Stats;
import stats.Performance;

import java.util.Map.Entry;

public class LoadAll {
	
	// TODO:
	// Write a bunch of Comparators
	// then swap them out easily and rank people by any arbitrary comparison
	
	public static void main(String[] args) {
		List<UHC> uhcs = UHCLoader.loadUHCs("src/load/uhc1.csv");
		List<Kill> kills = KillLoader.loadKills("src/load/kill1.csv");
		List<Player> players = PlayerLoader.loadPlayers("src/load/player1.csv");
		List<Registration> registrations = RegistrationLoader.loadRegistrations("src/load/registration1.csv");
		
		Stats stats = new Stats(uhcs, kills, players, registrations);
		
		//System.out.println(stats.updateElo().get("1ottsco"));
		//System.out.println(stats.updateElo());
		//System.out.println((float)1*0.8);
		
		/*
		try {
			FileWriter csvWriter = new FileWriter("eloMaster.csv");
			for(Entry<String, List<Double>> eloHistories : stats.updateElo().entrySet()) {
				String hString = eloHistories.getValue().toString();
				
				//System.out.println(eloHistories);
				//System.out.println(hString.substring(1,hString.length()-1));
				csvWriter.append(eloHistories.getKey());
				csvWriter.append(",");
				csvWriter.append(hString.substring(1,hString.length()-1));
				csvWriter.append("\n");
			}

			csvWriter.flush();
			csvWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to write to eloMaster.csv");
			e.printStackTrace();
		}
		*/
		ArrayList<Integer> elos = new ArrayList<Integer>();
		HashMap<Integer,String> eloName = new HashMap<Integer,String>();
		
		for(Entry<String, List<Double>> eloHistories : stats.updateElo().entrySet()) {
			int currentElo = (int)(double) eloHistories.getValue().get(eloHistories.getValue().size()-1);
			elos.add(currentElo);
			eloName.put(currentElo,eloHistories.getKey());
		}
	
		Collections.sort(elos);
		int index = elos.size()-1;
		int playerElo;
		while(index >= 0) {
			playerElo = elos.get(index);
			System.out.print(playerElo);
			System.out.print(" ");
			System.out.println(eloName.get(playerElo));
			index--; 
		}
		
		
		
		for(Kill k : kills) {
			//System.out.println(k.getKiller().equals(""));
		}
		
	
		//stats.printKillsAndDeathsAndKdr();
		
		//stats.printPlayerProfile("DemonicFish"); //5, 8, 12
		
		List<Performance> ps = stats.allPerformances();
		
		for (int i = 0; i < 10; i++) {
			//Performance p = ps.get(i);
			//System.out.println(p);
		}
	}
}

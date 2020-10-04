package parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogfileParser {
	private static final String[] PATTERNS = {
	  "was slain by",
	  "was shot by",
	  "was killed by",
	  "was pricked to death",
	  "walked into a cactus whilst trying to escape",
	  "drowned",
	  "drowned whilst trying to escape",
	  "blew up",
	  "was blown up by",
	  "was killed by [Intentional Game Design]",
	  "hit the ground too hard",
	  "fell from a high place",
	  "fell off",
	  "fell while climbing",
	  "was squashed by",
	  "went up in flames",
	  "walked into fire",
	  "burned to death",
	  "was burnt to a crisp",
	  "went off with a bang",
	  "tried to swim in lava",
	  "was struck by lightning",
	  "discovered the floor was lava",
	  "was stung by",
	  "was fireballed to death",
	  "starved to death",
	  "suffocated in a wall",
	  "was poked to death by a sweet berry bush",
	  "was killed trying to hurt",
	  "was impaled by",
	  "fell out of the world",
	  "didn't want to live in the same world as",
	  "withered away"
	};
	
	private static final String[] ANTIPATTERNS = {
			"moved too quickly!",
			"advancement",
			"[@:",
			"<",							// chat messages
			"User Authenticator",
			"lost connection",
			"left the game",
			"joined the game",
			"[main/WARN]",
			"[main/INFO]",
			"Preparing spawn area",
			"logged in with",
			"recipes",
			"advancements",
			"minecraft server",
			"Loading properties",
			"Default game type:",
			"Generating keypair",
			"Starting Minecraft server on",
			"Using epoll channel type",
			"Preparing",
			"Unable to find spawn biome",
			"Stopping",
			"Saving",
			"Time elapsed:",
			"For help, type \"help\"",
			"ThreadedAnvilChunkStorage",
			"Couldn't load function tag",
			"[Dunkersplatt: ",
			"Can't keep up!",
			"[IntenseYogurt: ",
			"[XmasGoose: ",
			"[YungSpesh: ",
			"[1ottsco: ",
			"Villager bdp[",
			"Mismatch in destroy block pos: ",
			"Fetching packet for",
			"Exception",
			"\tat ",
			"moved wrongly!",
			"*",
			"handleDisconnection()"
	};
	private static final String GAME_START = "commands from function 'start:start']";
	private static boolean gameStarted = false;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("UHC number: ");
		
		int uhc = Integer.parseInt(scanner.nextLine());
		
		System.out.println("MOTD (server description): ");
		
		String motd = scanner.nextLine();
		
		System.out.println("Enter file paths one by one, or \"done\" to stop: ");
		
		String input = "";
		List<String> fileNames = new ArrayList<String>();
		while (true) {
			input = scanner.nextLine();
			
			if (input.equals("done")) break;
			
			fileNames.add(input);
		}
		
		for (String fileName : fileNames) {
			System.out.println();
			System.out.println("Parsing " + fileName + "...");
			try {
				List<String> kills = getKills(fileName);
				System.out.println("Found " + kills.size() + " kills.");
				
				for (String kill : kills) {
					System.out.println(kill);
				}
			}
			catch (Exception e) {
				System.out.println("Failed to open, or parse file (lazy error handling)");
			}
		}
		
		scanner.close();
	}
	
	public static List<String> getKills(String fileName) throws FileNotFoundException {
		List<String> kills = new ArrayList<String>();
		
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			boolean killCandidate = true;
			
			// reset kills (from before game started) once game starts
			if (line.contains(GAME_START)) {
				//gameStarted = true;
				kills.add(line);
				//continue;
			}
			//if (!gameStarted) continue;
			
			for (String antipattern : ANTIPATTERNS) {
				if (line.contains(antipattern)) {
					killCandidate = false;
					break;
				}
			}
			
			if (killCandidate) {
				kills.add(line);
			}
		}
		
		scanner.close();
		
		return kills;
	}
}

package load;

import java.util.List;
import java.util.ArrayList;
import model.Player;

public class PlayerLoader {
	public static List<Player> loadPlayers(String filename) {
		List<Player> list = new ArrayList<Player>();
		
		CsvIterable loader = new CsvIterable(filename);
		
		for (String[] components : loader) {
			String username = components[0];
			String nickname = components[1];
			String defaultTeam = components[2];
			
			Player player = new Player();
			
			player.setUsername(username);
			
			player.setNickname(nickname);
			
			player.setDefaultTeam(defaultTeam);
			
			list.add(player);
		}
		
		loader.close();
		return list;
		
	}
}

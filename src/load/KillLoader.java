package load;

import java.util.List;
import java.util.ArrayList;
import model.Kill;
import model.Time;

public class KillLoader {
	public static List<Kill> loadKills(String filename) {
		List<Kill> list = new ArrayList<Kill>();
		
		CsvIterable loader = new CsvIterable(filename);
		
		for (String[] components : loader) {
			String killer = components[0];
			String victim = components[1];
			String uhc = components[2];
			String method = components[3];
			String timestamp = components[4];
			String comment = components[5];
			
			Kill kill = new Kill();
			
			kill.setKiller(killer);
			
			kill.setVictim(victim);
			
			kill.setUhc(Integer.parseInt(uhc));
			
			kill.setMethod(method);
			
			System.out.println(timestamp);
			kill.setTimestamp(Time.fromString(timestamp));
			
			kill.setComment(comment);
			
			list.add(kill);
		}
		
		loader.close();
		return list;
		
	}
}

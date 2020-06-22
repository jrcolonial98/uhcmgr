package load;

import java.util.List;
import java.util.ArrayList;
import model.Registration;

public class RegistrationLoader {
	public static List<Registration> loadRegistrations(String filename) {
		List<Registration> list = new ArrayList<Registration>();
		
		CsvIterable loader = new CsvIterable(filename);
		
		for (String[] components : loader) {
			String player = components[0];
			String uhc = components[1];
			String team = components[2];
			String comment = components[3];
			
			Registration registration = new Registration();
			
			registration.setPlayer(player);
			
			registration.setUhc(Integer.parseInt(uhc));
			
			registration.setTeam(team);
			
			registration.setComment(comment);
			
			list.add(registration);
		}
		
		loader.close();
		return list;
		
	}
}

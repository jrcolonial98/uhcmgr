package load;

import load.CsvIterable;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.UHC;
import model.Time;

public class UHCLoader {
	public static List<UHC> loadUHCs(String filename) {
		List<UHC> list = new ArrayList<UHC>();
		
		CsvIterable loader = new CsvIterable(filename);
		
		for (String[] components : loader) {
			String id = components[0];
			String date = components[1];
			String motd = components[2];
			String startTime = components[3];
			String winner = components[4];
			String seed = components[5];
			
			UHC uhc = new UHC();
			uhc.setId(Integer.parseInt(id));
			
			if (!date.equals("")) {
				try {
					String[] monthDayYear = date.split("/");
					int month = Integer.parseInt(monthDayYear[0]);
					int day = Integer.parseInt(monthDayYear[1]);
					int year = Integer.parseInt(monthDayYear[2]);
					Calendar formattedDate = new GregorianCalendar(year, month, day);
					uhc.setDate(formattedDate);
				} catch (NumberFormatException e) {
					System.out.println("Badly formatted date " + date);
					// no date provided, continuing.
				}
			}
			
			uhc.setMotd(motd);
			
			uhc.setStartTime(Time.fromString(startTime));
			
			uhc.setWinner(winner);
			
			uhc.setSeed(seed);
			
			list.add(uhc);
		}
		
		
		loader.close();
		return list;
		
	}
}

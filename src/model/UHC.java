package model;

import java.util.Date;

public class UHC {
	int id;
	Date date;
	String motd;
	
	public UHC(int id, Date date, String motd) {
		this.id = id;
		this.date = date;
		this.motd = motd;
	}
	public UHC(int id, Date date) {
		this(id, date, "");
	}
	
	public String toString() {
		if (motd == "") {
			return "UHC " + id;
		}
		else {
			return "UHC " + id + ": " + motd;
		}
	}
}

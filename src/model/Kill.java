package model;

public class Kill {
	Player killer;
	Player victim; 
	Time timestamp;
	KillMethod method;
	UHC uhc;
	
	public Player getKiller() {
		return killer;
	}
	public Player getVictim() {
		return victim;
	}
	public boolean getPvp() {
		return method.getPvp();
	}
}

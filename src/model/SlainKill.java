package model;

public class SlainKill implements KillMethod {
	public SlainKill() { }
	
	public String getMethod() {
		return "slain";
	}
	public boolean getPvp() {
		return true;
	}
}

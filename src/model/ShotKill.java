package model;

public class ShotKill implements KillMethod {

	@Override
	public String getMethod() {
		return "shot";
	}

	@Override
	public boolean getPvp() {
		return true;
	}

}

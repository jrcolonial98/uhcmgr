package model;

public class BurnKill implements KillMethod {

	@Override
	public String getMethod() {
		return "burned to death";
	}

	@Override
	public boolean getPvp() {
		return false;
	}

}

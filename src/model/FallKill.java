package model;

public class FallKill implements KillMethod {

	@Override
	public String getMethod() {
		return "fell to death";
	}

	@Override
	public boolean getPvp() {
		return false;
	}

}

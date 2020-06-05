package model;

public class DrownKill implements KillMethod {

	@Override
	public String getMethod() {
		return "drowned";
	}

	@Override
	public boolean getPvp() {
		return false;
	}

}

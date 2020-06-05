package model;

public class LavaKill implements KillMethod {

	@Override
	public String getMethod() {
		return "killed by lava";
	}

	@Override
	public boolean getPvp() {
		return false;
	}

}

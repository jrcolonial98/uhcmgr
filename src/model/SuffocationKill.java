package model;

public class SuffocationKill implements KillMethod {

	@Override
	public String getMethod() {
		return "suffocated";
	}

	@Override
	public boolean getPvp() {
		return false;
	}

}

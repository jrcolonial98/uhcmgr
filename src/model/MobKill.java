package model;

public class MobKill implements KillMethod {
	String mob = "mob";
	public MobKill(String mob) {
		this.mob = mob;
	}
	
	@Override
	public String getMethod() {
		return "killed by " + mob;
	}

	@Override
	public boolean getPvp() {
		return false;
	}

}

public class Pokemon {
	//This is a collection of methods dealing with things that all Pokemon have.
	private int level;
	private String [] statNames = {"Hp", "Att", "Def", "SpAtt", "SpDef", "Speed"};
	private int [] baseStats = {0, 0, 0, 0, 0, 0};
	static double [] nature = {1, 1, 1, 1, 1, 1};
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getStatNames(int a) {
		return statNames[a];
	}
	public void setAsBaseStats(int [] a) {
		baseStats = a;
	}
	public int getBaseStats(int a) {
		return baseStats[a];
	}
	public void setNature(int a, int b) {
		//This is very game specific, just know that every nature raises one stat by 10% and lowers another by 10%.
		nature[a] = 1.1;
		nature[b] = .9;
	}
	public double getNature(int a) {
		return nature[a];
	}
}
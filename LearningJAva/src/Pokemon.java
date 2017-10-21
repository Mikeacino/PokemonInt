public class Pokemon {
//Michael Carracino
//The arrays below are used for the Pokemon final equation.
	private int [] effortValue = {0, 0, 0, 0, 0, 0};
	private int [] baseStats = {0, 0, 0, 0, 0, 0};
	private int [] individualValue = {0, 0, 0, 0, 0, 0};
	private int [] currentStats = {0, 0, 0, 0, 0, 0};
	private int [] minimumStats = {0, 0, 0, 0, 0, 0};
	private int [] maximumStats = {0, 0, 0, 0, 0, 0};
	
//This next array is a double so that I can avoid the issues with integer division.
	private double [] natureMultiplier = {1, 1, 1, 1, 1, 1};
	
//The variables below are used in various parts of the program
	private int level;
	private int totalEV = 0;
	private String pokemonType = "";
	static String pokemonNature = "";
	
//These two variables below are used for my decision structures.
  int userNatureChoice = 0;
  int userPokemonChoice = 0;
	
//These arrays are used for print statements.
	private String [] statNames = {"Hp", "Att", "Def", "SpAtt", "SpDef", "Speed"};
	private String [] natureNames = {"Hardy","Bold","Modest","Calm","Timid","Lonely",
			"Docile","Mild","Gentle","Hasty","Adamant","Impish","Serious","Careful",
			"Jolly","Naughty","Lax","Rash","Bashful","Naive","Brave","Relaxed",
			"Quiet","Sassy","Quirky"};
	
//These pokemon base stats are here until I can work in a Pokemon API.
	private static int [] venusaurBaseStats = {80, 82, 83, 100, 100, 80};
	private static int [] blastoiseBaseStats = {79, 83, 100, 85, 105, 78};
	private static int [] charizardBaseStats = {78, 84, 78, 109, 85, 100};
	private static int [] pikachuBaseStats = {35, 55, 40, 50, 50, 90};
	private static int [] dragoniteBaseStats = {91, 134, 95, 100, 100, 80};
	private static int [] mewBaseStats = {100, 100, 100, 100, 100, 100};
	private static int [] tyranitarBaseStats = {100, 134, 110, 95, 100, 61};
	private static int [] miloticBaseStats = {95, 60, 79, 100, 125, 81};
	private static int [] metagrossBaseStats = {80, 135, 130, 95, 90, 70};
	private static int [] salamenceBaseStats = {95, 135, 80, 110, 80, 100};
	
//This is the default constructor for my class.
	public Pokemon() {}
	
	public Pokemon(int [] baseStats) {
	  setAsBaseStats(baseStats);
	}
	
//Get and Set for the currentStats array values.	
//In this method, 'a' is the parameter.
	public int getCurrentStat(int a) {
		return currentStats[a];
	}
	public void setCurrentStat(int a, int b) {
		this.currentStats[a] = b;
	}
	
//Get and Set for the userNatureChoice variable.
	public int getUserNatureChoice() {
    return userNatureChoice;
	}
	public void setUserNatureChoice(int a) {
	  this.userNatureChoice = a;
	}
	
//Get and Set for the userPokemonChoice variable.
	public int getUserPokemonChoice() {
	  return userPokemonChoice;
	}
	public void setUserPokemonChoice(int a) {
	  this.userPokemonChoice = a;
	}
	
//Get and Set for the Level variable.
	public int getLevel() {
		return level;
	}
	public void setLevel(int lev) {
		this.level = lev;
	}
	
//Get and Set for the baseStats array values.
	public void setAsBaseStats(int [] a) {
		this.baseStats = a;
	}
	public int getBaseStats(int a) {
		return baseStats[a];
	}
	
//Get and Set for the NatureMultiplier array values.
	public void setNatureMult(int a, int b) {
		//Every nature raises one stat by 10% and lowers another by 10%.
		this.natureMultiplier[a] = 1.1;
		this.natureMultiplier[b] = .9;
	}
	public double getNatureMult(int a) {
		return natureMultiplier[a];
	}
	
//Get and Set the EV array values
	public int getEV(int e) {
		return effortValue[e];
	}
	public void setEV(int e, int v) {
		this.effortValue[e] = v;
	}
	
//Get and Set the IV array values.
	public int getIV(int i) {
		return individualValue[i];
	}
	public void setIV(int i, int v) {
		this.individualValue[i] = v;
	}
	
//Get and Set the TotalEV variable.
	public void setTotalEV(int a) {
		this.totalEV = a;
	}
	public int getTotalEV() {
		return totalEV;
	}

//Get and Set for the PokemonType variable.
	public String getPokemonType() {
		return pokemonType;
	}
	public void setPokemonType(String s) {
	  this.pokemonType = s;
	}


	public String getStatNames(int a) {
			return statNames[a];
	}
	 public String natureNames(int a) {
	    return natureNames[a];
	}
	
//This method is used for error handling.
	public void resetAllEV(){
		for (int i = 0; i < 6; i++) {
			effortValue[i] = 0;
		}
		this.setTotalEV(0);
	}
	
	public void resetOneEV(int a) {
	  effortValue[a] = 0;
	}
	
//Get and Set for maximumStats array values
	public int getMaxStat(int a) {
	  return maximumStats[a];
	}
	public void setMaxStat(int a, int b) {
	  this.maximumStats[a] = b;
	}
	
//Get and Set for the minimumStats array values
	public int getMinStat(int a) {
    return minimumStats[a];
  }
  public void setMinStat(int a, int b) {
    this.minimumStats[a] = b;
  }

//This method calculates the minimum stats for a pokemon.
	public void calculateMinStats() { 
	  minimumStats[0] = (int) Math.floor((2*baseStats[0] + (effortValue[0]/4))*level/100 + 10 + level);
    for (int i = 1; i < 6; i++) {
      minimumStats[i] = (int) Math.floor(((2*baseStats[i] + (effortValue[i]/4))*level/100 + 5)*natureMultiplier[i]);
    }
	}
	
  // This method calculates the maximum stats for a pokemon
  public void calculateMaxStats() {
    maximumStats[0] = (int) (Math
        .floor((31 + 2 * baseStats[0] + (effortValue[0] / 4)) * level / 100)
        + 10 + level);
    for (int i = 1; i < 6; i++) {
      maximumStats[i] = (int) (Math.floor(
          (31 + 2 * baseStats[i] + (effortValue[i]/4)) * level / 100 + 5) * natureMultiplier[i]);
    }
  }

  public void solveCurrentStats() {
    // This calculates the current stats for a Pokemon.
    currentStats[0] = (int) (Math
        .floor((31 + 2 * baseStats[0] + (effortValue[0] / 4)) * level / 100)
        + 10 + level);
    for (int i = 1; i < 6; i++) {
      currentStats[i] = (int) (Math.floor(
          (31 + 2 * baseStats[i] + (effortValue[i] / 4)) * level / 100 + 5)
          * natureMultiplier[i]);
    }
  }

  public void solveForIVs() {
    // This method calculates the IVs of a Pokemon.
    individualValue[0] = ((currentStats[0] - level - 10) * (100 / level)
        - (effortValue[0] / 4) - 2 * baseStats[0]);
    for (int i = 1; i < 6; i++) {
      individualValue[i] = (int) Math
          .ceil((currentStats[i] / natureMultiplier[i] - 5) * (100 / level)
              - (effortValue[i] / 4) - 2 * baseStats[i]);
    }
  }
//This method sets the user type.
	public void choosePokemon(int choice) {
					
	}

//Pokemon nature is set in the calculateNature method.
  public String getPokemonNature() {
    return pokemonNature;
  }

//This method sets the natureMult array and the Pokemon's nature
	public void calculateNature(int natureChoice) {
		/*A pokemon's nature raises one stat by 10% and lowers another by 10%. The 
		 * HP stat is never raised or lowered, so we have a total of 25 possible natures. 
		*/
		switch (natureChoice) {
			case 1:
//This case is where the same stat is both raised and lowered, canceling out.
				pokemonNature = natureNames(0);
				break;
			case 2:
				setNatureMult(2, 1);
				pokemonNature = natureNames(1);
				break;
			case 3:
				setNatureMult(3, 1);
				pokemonNature = natureNames(2);
				break;
			case 4:
				setNatureMult(4, 1);
				pokemonNature = natureNames(3);
				break;
			case 5:
				setNatureMult(5, 1);
				pokemonNature = natureNames(4);
				break;
			case 6:
				setNatureMult(1, 2);
				pokemonNature = natureNames(5);
				break;
			case 7:
//This case is where the same stat is both raised and lowered, canceling out.
				pokemonNature = natureNames(6);
				break;
			case 8:
				setNatureMult(3, 2);
				pokemonNature = natureNames(7);
				break;
			case 9:
				setNatureMult(4, 2);
				pokemonNature = natureNames(8);
				break;
			case 10:
				setNatureMult(5, 2);
				pokemonNature = natureNames(9);
				break;
			case 11:
				setNatureMult(1, 3);
				pokemonNature = natureNames(10);
				break;
			case 12:
				setNatureMult(2, 3);
				pokemonNature = natureNames(11);
				break;
			case 13:
//This case is where the same stat is both raised and lowered, canceling out.
				pokemonNature = natureNames(12);
				break;
			case 14:
				setNatureMult(4, 3);
				pokemonNature = natureNames(13);
				break;
			case 15:
				setNatureMult(5, 3);
				pokemonNature = natureNames(14);
				break;
			case 16:
				setNatureMult(1, 4);
				pokemonNature = natureNames(15);
				break;
			case 17:
				setNatureMult(2, 4);
				pokemonNature = natureNames(16);
				break;
			case 18:
				setNatureMult(3, 4);
				pokemonNature = natureNames(17);
			case 19:
//This case is where the same stat is both raised and lowered, canceling out.
				pokemonNature = natureNames(18);
				break;
			case 20:
				setNatureMult(5, 4);
				pokemonNature = natureNames(19);
				break;
			case 21:
				setNatureMult(1, 5);
				pokemonNature = natureNames(20);
				break;
			case 22:
				setNatureMult(2, 5);
				pokemonNature = natureNames(21);
				break;
			case 23:
				setNatureMult(3, 5);
				pokemonNature = natureNames(22);
				break;
			case 24:
				setNatureMult(4, 5);
				pokemonNature = natureNames(23);
				break;
			case 25:
//This case is where the same stat is both raised and lowered, canceling out.
				pokemonNature = natureNames(24);
				break;
		}			
	}
}
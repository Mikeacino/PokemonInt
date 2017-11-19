
///////////// Michael Carracino /////////////
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Pokemon {
  // The arrays below are used for the Pokemon stat formula.
  private int[] effortValue = { 0, 0, 0, 0, 0, 0 };
  private int[] baseStats = { 0, 0, 0, 0, 0, 0 };
  private int[] individualValue = { 0, 0, 0, 0, 0, 0 };
  private int[] currentStats = { 0, 0, 0, 0, 0, 0 };
  private int[] minimumStats = { 0, 0, 0, 0, 0, 0 };
  private int[] maximumStats = { 0, 0, 0, 0, 0, 0 };

  // This next array is a double so I can avoid issues with integer division
  private double[] natureMultiplier = { 1, 1, 1, 1, 1, 1 };

  // This section is to know which pokemon the user is trying to select
  private int pokemonID = 1;

  // The variables below are used in various parts of the program
  private int level;
  private String pokemonName;
  private int totalEV = 0;
  private String pokemonSpecies;
  private String pokemonNature;

  // These two are for getting the image of the Pokemon
  private boolean isShiny = false;
  private boolean isBackwards = false;

  // This is for the pokemon description, or flavor text
  private String flavorText;

  // These two variables below are used for my decision structures.
  private int userNatureChoice = 0;
  private int userPokemonChoice = 0;
  private HashMap<Integer, String> typeMap = new HashMap<Integer, String>();

  // These arrays are used for print statements.
  private String[] statNames = { "Hp", "Att", "Def", "SpAtt", "SpDef",
      "Speed" };

  private String[] typeNameArray = { "Normal", "Fighting", "Flying", "Poison",
      "Ground", "Rock", "Bug", "Ghost", "Steel", "Fire", "Water", "Grass",
      "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy" };

  private String[] natureNames = { "Hardy", "Bold", "Modest", "Calm", "Timid",
      "Lonely", "Docile", "Mild", "Gentle", "Hasty", "Adamant", "Impish",
      "Serious", "Careful", "Jolly", "Naughty", "Lax", "Rash", "Bashful",
      "Naive", "Brave", "Relaxed", "Quiet", "Sassy", "Quirky" };

  ///////////// Constructor /////////////
  public Pokemon() {
  }

  public Pokemon(int ID) throws IOException {
    setPokemonID(ID);
    FilePokemonData(ID);
  }

  ///////////// typeList /////////////
  public String getTypeList(int a) {
    return typeMap.get(a);
  }

  public void setTypeList(int a, String b) {
    this.typeMap.put(a, b);
  }

  public int numberOfTypes() {
    return this.typeMap.size();
  }

  ///////////// isShiny /////////////
  public boolean getIsShiny() {
    return isShiny;
  }

  public void setIsShiny(boolean b) {
    this.isShiny = b;
  }

  ///////////// currentStats /////////////
  public int getCurrentStat(int a) {
    return currentStats[a];
  }

  public void setCurrentStat(int a, int b) {
    this.currentStats[a] = b;
  }

  ///////////// pokemonID /////////////
  public int getPokemonID() {
    return pokemonID;
  }

  public void setPokemonID(int pokemonID) {
    this.pokemonID = pokemonID;
  }

  ///////////// pokemonName /////////////
  public void setPokemonName(String s) {
    this.pokemonName = s;
  }

  public String getPokemonName() {
    return pokemonName;
  }

  ///////////// userNatureChoice /////////////
  public int getUserNatureChoice() {
    return userNatureChoice;
  }

  public void setUserNatureChoice(int a) {
    this.userNatureChoice = a;
  }

  ///////////// userPokemonChoice /////////////
  public int getUserPokemonChoice() {
    return userPokemonChoice;
  }

  public void setUserPokemonChoice(int a) {
    this.userPokemonChoice = a;
  }

  ///////////// level /////////////
  public int getLevel() {
    return level;
  }

  public void setLevel(int lev) {
    this.level = lev;
  }

  ///////////// baseStats /////////////
  public void setBaseStats(int position, int value) {
    this.baseStats[position] = value;
  }

  public int getBaseStats(int position) {
    return baseStats[position];
  }

  ///////////// filePokemonData /////////////
  /*
   * I wanted to move all of my file readers to one method, so I didn't have to
   * search around when I needed them
   */
  public void FilePokemonData(int ID) throws IOException {
    boolean IDFound = false;
    boolean typeFound = false;
    boolean nameFound = false;
    // boolean descriptionFound = false;
    // This variable is commented out until i can solve my issue with getting
    // the descriptions for pokemon

    // Finds the pokemon's baseStats
    FileReader statsFile = new FileReader("pokemon_stats.csv");
    BufferedReader statsReader = new BufferedReader(statsFile);
    statsReader.readLine();
    while (IDFound == false) {
      String hpLine = statsReader.readLine();
      String lineID = hpLine.substring(0, hpLine.indexOf(","));
      if (ID == Integer.parseInt(lineID)) {
        String[] tempArray1 = hpLine.split(",");
        baseStats[0] = Integer.parseInt((tempArray1[2]));
        IDFound = true;
      }
    }
    for (int i = 0; i < 5; i++) {
      String currentLine = statsReader.readLine();
      String[] tempArray2 = currentLine.split(",");
      baseStats[i + 1] = Integer.parseInt(tempArray2[2]);
    }
    statsReader.close();

    // Finds the pokemon's types.
    FileReader typeFile = new FileReader("pokemon_types.csv");
    BufferedReader typeReader = new BufferedReader(typeFile);
    typeReader.readLine();
    while (typeFound == false) {
      String type1Line = typeReader.readLine();
      String[] type1Array = type1Line.split(",");
      if (Integer.parseInt(type1Array[0]) == ID) {
        this.typeMap.put(0, typeNameArray[Integer.parseInt(type1Array[1]) - 1]);
        String type2Line = typeReader.readLine();
        if (Integer
            .parseInt(type2Line.substring(0, type2Line.indexOf(","))) == ID) {
          String[] type2Array = type2Line.split(",");
          this.typeMap.put(1,
              typeNameArray[Integer.parseInt(type2Array[1]) - 1]);
          typeFound = true;
        }
        typeFound = true;
      }
    }
    typeReader.close();

    // Finds the Pokemon's name
    FileReader nameFile = new FileReader("pokemon.csv");
    BufferedReader nameReader = new BufferedReader(nameFile);
    nameReader.readLine();
    while (nameFound == false) {
      String nameLine = nameReader.readLine();
      String[] temporaryNameArray = nameLine.split(",");
      if (Integer.parseInt(temporaryNameArray[0]) == ID) {
        pokemonName = temporaryNameArray[1];
        nameFound = true;
      }
    }
    nameReader.close();

    // Finds the pokemon's description
    // FileReader descriptionFile = new
    // FileReader("pokemon_species_flavor_text.csv");
    // BufferedReader descriptionReader = new BufferedReader(descriptionFile);
    // descriptionReader.readLine();
    // int counter = 0;
    // while (descriptionFound == false) {
    // String descriptionLine = descriptionReader.readLine();
    // String[] temporaryDescriptionArray = descriptionLine.split(",");
    // if (Integer.parseInt(temporaryDescriptionArray[0]) == ID &&
    // Integer.parseInt(temporaryDescriptionArray[1]) == 23 &&
    // Integer.parseInt(temporaryDescriptionArray[2]) == 9) {
    // this.flavorText = temporaryDescriptionArray[3];
    // descriptionFound = true;
    // }
    // }
    // descriptionReader.close();
  }

  ///////////// natureMult /////////////
  public void setNatureMult(int a, int b) {
    this.natureMultiplier[a] = 1.1;
    this.natureMultiplier[b] = .9;
  }

  public double getNatureMult(int a) {
    return natureMultiplier[a];
  }

  ///////////// effortValue /////////////
  public int getEV(int e) {
    return effortValue[e];
  }

  public void setEV(int e, int v) {
    this.effortValue[e] = v;
  }

  ///////////// individualValue /////////////
  public int getIV(int i) {
    return individualValue[i];
  }

  public void setIV(int i, int v) {
    this.individualValue[i] = v;
  }

  ///////////// totalEV /////////////
  public void setTotalEV(int a) {
    this.totalEV = a;
  }

  public int getTotalEV() {
    return totalEV;
  }

  ///////////// pokemonSpecies /////////////
  public String getPokemonType() {
    return pokemonSpecies;
  }

  public void setPokemonSpecies(String s) {
    this.pokemonSpecies = s;
  }

  ///////////// statNames /////////////
  public String getStatNames(int a) {
    return statNames[a];
  }

  ///////////// natureNames /////////////
  public String natureNames(int a) {
    return natureNames[a];
  }

  public String[] natureNamesArray() {
    return natureNames;
  }

  ///////////// resetEVs /////////////
  public void resetAllEV() {
    for (int i = 0; i < 6; i++) {
      effortValue[i] = 0;
    }
    this.setTotalEV(0);
  }

  public void resetOneEV(int a) {
    effortValue[a] = 0;
  }

  ///////////// maximumStats /////////////
  public int getMaxStat(int a) {
    return maximumStats[a];
  }

  public void setMaxStat(int a, int b) {
    this.maximumStats[a] = b;
  }

  ///////////// minimumStats /////////////
  public int getMinStat(int a) {
    return minimumStats[a];
  }

  public void setMinStat(int a, int b) {
    this.minimumStats[a] = b;
  }

  ///////////// Calculate minimumStats with known variables /////////////
  public void calculateMinStats() {
    minimumStats[0] = (int) Math.floor(
        (2 * baseStats[0] + (effortValue[0] / 4)) * level / 100 + 10 + level);
    for (int i = 1; i < 6; i++) {
      minimumStats[i] = (int) Math
          .floor(((2 * baseStats[i] + (effortValue[i] / 4)) * level / 100 + 5)
              * natureMultiplier[i]);
    }
  }

  ///////////// Calculate maximumStats with known variables /////////////
  public void calculateMaxStats() {
    maximumStats[0] = (int) (Math
        .floor((31 + 2 * baseStats[0] + (effortValue[0] / 4)) * level / 100)
        + 10 + level);
    for (int i = 1; i < 6; i++) {
      maximumStats[i] = (int) (Math.floor(
          (31 + 2 * baseStats[i] + (effortValue[i] / 4)) * level / 100 + 5)
          * natureMultiplier[i]);
    }
  }

  ///////////// Calculate the Absolute maximumStats /////////////
  public void calculateAbsoluteMaxStats() {
    maximumStats[0] = (int) (Math.floor((31 + 2 * baseStats[0] + 63)) + 110);
    for (int i = 1; i < 6; i++) {
      maximumStats[i] = (int) (Math.floor((31 + 2 * baseStats[i] + 63) + 5)
          * 1.1);
    }
  }

  ///////////// Calculate the Absolute minimumStats /////////////
  public void calculateAbsoluteMinStats() {
    minimumStats[0] = (int) Math.floor((2 * baseStats[0]) + 110);
    for (int i = 1; i < 6; i++) {
      minimumStats[i] = (int) Math.floor(((2 * baseStats[i]) + 5) * 0.9);
    }
  }

  ///////////// Solve currentStats /////////////
  public void solveCurrentStats() {
    currentStats[0] = (int) (Math.floor(
        (getIV(0) + 2 * baseStats[0] + (effortValue[0] / 4)) * level / 100) + 10
        + level);
    for (int i = 1; i < 6; i++) {
      currentStats[i] = (int) (Math.floor(
          (getIV(i) + 2 * baseStats[i] + (effortValue[i] / 4)) * level / 100
              + 5)
          * natureMultiplier[i]);
    }
  }

  ///////////// Solve individualValue /////////////
  public void solveForIVs() {
    individualValue[0] = ((currentStats[0] - level - 10) * (100 / level)
        - (effortValue[0] / 4) - 2 * baseStats[0]);
    for (int i = 1; i < 6; i++) {
      individualValue[i] = (int) Math
          .ceil((currentStats[i] / natureMultiplier[i] - 5) * (100 / level)
              - (effortValue[i] / 4) - 2 * baseStats[i]);
    }
  }

  ///////////// pokemonNature natureMult /////////////
  public String getPokemonNature() {
    return pokemonNature;
  }

  public void calculateNature(String natureChoice) {
    switch (natureChoice) {
      case "Hardy":
        // This case is where the same stat is both raised and lowered,
        // canceling out.
        pokemonNature = natureNames(0);
        break;
      case "Bold":
        setNatureMult(2, 1);
        pokemonNature = natureNames(1);
        break;
      case "Modest":
        setNatureMult(3, 1);
        pokemonNature = natureNames(2);
        break;
      case "Calm":
        setNatureMult(4, 1);
        pokemonNature = natureNames(3);
        break;
      case "Timid":
        setNatureMult(5, 1);
        pokemonNature = natureNames(4);
        break;
      case "Lonely":
        setNatureMult(1, 2);
        pokemonNature = natureNames(5);
        break;
      case "Docile":
        // This case is where the same stat is both raised and lowered,
        // canceling out.
        pokemonNature = natureNames(6);
        break;
      case "Mild":
        setNatureMult(3, 2);
        pokemonNature = natureNames(7);
        break;
      case "Gentle":
        setNatureMult(4, 2);
        pokemonNature = natureNames(8);
        break;
      case "Hasty":
        setNatureMult(5, 2);
        pokemonNature = natureNames(9);
        break;
      case "Adamant":
        setNatureMult(1, 3);
        pokemonNature = natureNames(10);
        break;
      case "Impish":
        setNatureMult(2, 3);
        pokemonNature = natureNames(11);
        break;
      case "Serious":
        // This case is where the same stat is both raised and lowered,
        // canceling out.
        pokemonNature = natureNames(12);
        break;
      case "Careful":
        setNatureMult(4, 3);
        pokemonNature = natureNames(13);
        break;
      case "Jolly":
        setNatureMult(5, 3);
        pokemonNature = natureNames(14);
        break;
      case "Naughty":
        setNatureMult(1, 4);
        pokemonNature = natureNames(15);
        break;
      case "Lax":
        setNatureMult(2, 4);
        pokemonNature = natureNames(16);
        break;
      case "Rash":
        setNatureMult(3, 4);
        pokemonNature = natureNames(17);
      case "Bashful":
        // This case is where the same stat is both raised and lowered,
        // canceling out.
        pokemonNature = natureNames(18);
        break;
      case "Naive":
        setNatureMult(5, 4);
        pokemonNature = natureNames(19);
        break;
      case "Brave":
        setNatureMult(1, 5);
        pokemonNature = natureNames(20);
        break;
      case "Relaxed":
        setNatureMult(2, 5);
        pokemonNature = natureNames(21);
        break;
      case "Quiet":
        setNatureMult(3, 5);
        pokemonNature = natureNames(22);
        break;
      case "Sassy":
        setNatureMult(4, 5);
        pokemonNature = natureNames(23);
        break;
      case "Quirky":
        // This case is where the same stat is both raised and lowered,
        // canceling out.
        pokemonNature = natureNames(24);
        break;
    }
  }

  public int[] getTypeColor(String type) {
    int[] colorArray = { 0, 0, 0 };
    switch (type) {
      case "Bug":
        colorArray[0] = 168;
        colorArray[1] = 184;
        colorArray[2] = 32;
        break;
      case "Dark":
        colorArray[0] = 112;
        colorArray[1] = 88;
        colorArray[2] = 72;
        break;
      case "Dragon":
        colorArray[0] = 112;
        colorArray[1] = 56;
        colorArray[2] = 248;
        break;
      case "Electric":
        colorArray[0] = 248;
        colorArray[1] = 208;
        colorArray[2] = 48;
        break;
      case "Fairy":
        colorArray[0] = 238;
        colorArray[1] = 153;
        colorArray[2] = 172;
        break;
      case "Fighting":
        colorArray[0] = 192;
        colorArray[1] = 48;
        colorArray[2] = 40;
        break;
      case "Fire":
        colorArray[0] = 240;
        colorArray[1] = 128;
        colorArray[2] = 48;
        break;
      case "Flying":
        colorArray[0] = 168;
        colorArray[1] = 144;
        colorArray[2] = 240;
        break;
      case "Ghost":
        colorArray[0] = 112;
        colorArray[1] = 88;
        colorArray[2] = 152;
        break;
      case "Grass":
        colorArray[0] = 120;
        colorArray[1] = 200;
        colorArray[2] = 80;
        break;
      case "Ground":
        colorArray[0] = 224;
        colorArray[1] = 192;
        colorArray[2] = 104;
        break;
      case "Ice":
        colorArray[0] = 152;
        colorArray[1] = 216;
        colorArray[2] = 216;
        break;
      case "Normal":
        colorArray[0] = 168;
        colorArray[1] = 168;
        colorArray[2] = 120;
        break;
      case "Poison":
        colorArray[0] = 160;
        colorArray[1] = 64;
        colorArray[2] = 160;
        break;
      case "Psychic":
        colorArray[0] = 248;
        colorArray[1] = 88;
        colorArray[2] = 136;
        break;
      case "Rock":
        colorArray[0] = 184;
        colorArray[1] = 160;
        colorArray[2] = 56;
        break;
      case "Steel":
        colorArray[0] = 184;
        colorArray[1] = 184;
        colorArray[2] = 208;
        break;
      case "Water":
        colorArray[0] = 104;
        colorArray[1] = 144;
        colorArray[2] = 240;
        break;
    }
    return colorArray;
  }

  public boolean getIsBackwards() {
    return isBackwards;
  }

  public void setIsBackwards(boolean isBackwards) {
    this.isBackwards = isBackwards;
  }

  public String getFlavorText() {
    return flavorText;
  }

  public void setFlavorText(String flavorText) {
    this.flavorText = flavorText;
  }
}
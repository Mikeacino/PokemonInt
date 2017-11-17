import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileAccessTester {

  public static void main(String[] args) throws IOException {
    FilePokemonData(350);
  }
  public static void FilePokemonData(int ID) throws IOException {
    boolean IDFound = false;
    boolean typeFound = false;
    boolean nameFound = false;
    
    ///////////////////  Stats  ///////////////////
    FileReader statsFile = new FileReader(
        "C:/Users/chris/OneDrive/Documents/Java "
            + "2017/Resources/pokemon_stats.csv");
    BufferedReader statsReader = new BufferedReader(statsFile);

    statsReader.readLine();
    while (IDFound == false) {
      String hpLine = statsReader.readLine();
      String lineID = hpLine.substring(0, hpLine.indexOf(","));
      if (ID == Integer.parseInt(lineID)) {
        String[] tempArray1 = hpLine.split(",");
        //baseStats[0] = Integer.parseInt((tempArray1[2]));
        System.out.println("BaseStat: " + Integer.parseInt((tempArray1[2])));
        IDFound = true;
      }
    }
    for (int i = 0; i < 5; i++) {
      String currentLine = statsReader.readLine();
      String[] tempArray2 = currentLine.split(",");
      //baseStats[i+1] = Integer.parseInt(tempArray2[2]);
      System.out.println("BaseStat: "+Integer.parseInt(tempArray2[2]));
    }
    
    ///////////////////  Pokemon Typing  ///////////////////
    FileReader typeFile = new FileReader(
        "C:/Users/chris/OneDrive/Documents/Java "
            + "2017/Resources/pokemon_types.csv");
    BufferedReader typeReader = new BufferedReader(typeFile);
    typeReader.readLine();
    while (typeFound == false) {
      String type1Line = typeReader.readLine();
      String[] type1Array = type1Line.split(",");
      if (Integer.parseInt(type1Array[0]) == ID) {
        //typeList.put(0, type1Array[1]);
        System.out.println("SpecificType: "+type1Array[1]);
        String type2Line = typeReader.readLine();
        if (Integer.parseInt(type2Line.substring(0, type2Line.indexOf(","))) == ID) {
          String[] type2Array = type2Line.split(",");
          //typeList.put(1, type2Array[1]);
          System.out.println("SpecificType: "+type2Array[1]);
          typeFound = true;
        }
        typeFound = true;
      }
    }
    ///////////////////  Pokemon Name  ///////////////////
    FileReader nameFile = new FileReader(
        "C:/Users/chris/OneDrive/Documents/Java "
            + "2017/Resources/pokemon.csv");
    BufferedReader nameReader = new BufferedReader(nameFile);
    nameReader.readLine();
    while (nameFound == false) {
      String nameLine = nameReader.readLine();
      String[] temporaryNameArray = nameLine.split(",");
      if (Integer.parseInt(temporaryNameArray[0]) == ID) {
        //pokemonName = temporaryArray3[1];
        System.out.println("Name: "+temporaryNameArray[1]);
        nameFound = true;
      }
    }
  }
  
 
}
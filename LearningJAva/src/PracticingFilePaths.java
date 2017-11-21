import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PracticingFilePaths {
  public static void main(String[] args) throws Exception {
    System.out.println("The Beginning\n");
    FileReader file = new FileReader("pokemon_species_flavor_text.csv");
    BufferedReader reader = new BufferedReader(file);
    boolean lineFound = false;
    boolean lineEnd = false;
    String PokeID = "802";
    String flavorText;
    String nextLine;
    String line = reader.readLine();
    while (lineFound == false) {
      String[] Arr = line.split(",");
      if (Arr.length >= 3) {
        if (Arr[0].equals(PokeID)) {
          if (Integer.parseInt(Arr[2]) == 9) {
            flavorText = Arr[3];
            nextLine = reader.readLine();
            flavorText = flavorText + " " + nextLine;
            while (lineEnd == false) {
              nextLine = reader.readLine();
              if (nextLine.indexOf("\"") >= 0) {
                flavorText = flavorText + " "+nextLine;
                flavorText = flavorText.substring(flavorText.indexOf("\"")+1, flavorText.length());
                flavorText = flavorText.substring(0,flavorText.indexOf("\""));
                System.out.println(flavorText);
                lineEnd = true;
                lineFound = true;
              }
              flavorText = flavorText+" "+nextLine;
            }
          }
        }
      }
      line = reader.readLine();
    }
    reader.close();
    System.out.println("\nThe End");
  }
}

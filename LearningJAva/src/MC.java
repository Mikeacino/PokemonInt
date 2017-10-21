import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MC {
//Michael Carracino 																																
/*A variable is place in memory that has a data type, but not a definite value. the 
 *value of a variable can be changed during the runtime of a program.
 *there are eight primitive data types, byte, short, int, long, float, double, 
 *boolean, and char. 
 *A byte is an 8-bit signed two's complement integer with a range of (-128, 127). 
 *A short is a 16-bit signed two's complement integer with a range of (-32768,32767).
 *An int is a 32-bit signed two's complement integer with a range of (-2^31,(2^31)-1)
 *A long is a 64-bit signed two's complement integer with a range of (-2^63,(2^63)-1)
 *A float is a single-precision 32-bitIEEE 754 floating point.
 *A double is a double-precision 64-bit IEEE 754 floating point.
 *A boolean has only two values, true or false.
 *A char is a single 16-bit Unicode character.*/  
/*A Pokemon's stats are calculated with 6 variables: IVs, EVs, level, nature, 
 * base stats, and current stats. All Pokemon have a type that is linked with their 
 * base stats, but it's not used in calculations. Each variable has limitations that
 * will be accounted for in error handling. Solving for any variable requires 
 * the other 5.*/ 
/*My print methods are where I ran into issues with integer division. In the past
 * I used only integers in this equation, but it caused miscalculations. Now I set 
 * the natureMultiplier array as a double. This causes the result of the equation to 
 * be a double. I then cast the resulting double to an int before printing it.
 * */
  public static void main(String[] args) {
    try {
      // This URL is missing data. ex: "009/", "Charizard/"
      HttpURLConnection connection = null;
      String unfinishedURL = "";
      URL url = new URL("https://pokeapi.co/api/v2/pokemon/719/");
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("User-Agent", "Mozilla/5.0");

      BufferedReader in = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
      
      JSONParser parser  = new JSONParser();
      String inputLine = in.readLine();
      
      JSONObject obj = (JSONObject) parser.parse(inputLine);
      JSONArray stat_array = (JSONArray) obj.get("stats");
      JSONObject stat = (JSONObject) stat_array.get(0); 
      JSONObject statData = (JSONObject) stat.get("stat");
      long baseStat = (long) stat.get("base_stat");
      System.out.println(obj.get("name"));  //This line prints the pokemon name.
      System.out.println(statData.get("name")); //this returns the stat name
      System.out.println(baseStat); //this line gets the value of the stat.
      
      //JSONObject statArray = (JSONObject) obj.get(0); //this line is unnecessary? 
      //System.out.println(statArray); //this is still too much raw data.
      //System.out.println(stat.get("name")); //This should return the stat name.
      //System.out.println(statData.get("base_stat")); //this returns null
      
      
      
      
      int userProgramChoice = 0;
      boolean endProgram = false;
      Scanner userInput = new Scanner(System.in);
      userProgramChoice = introduceMain(userInput); // checked
      while (endProgram == false) {
        Pokemon poke;
        switch (userProgramChoice) {
          case 1:
            poke = new Pokemon(null);
            getUserType(userInput, poke);
            getUserLevel(userInput, poke);
            getUserNature(userInput, poke);
            getUserEffortValues(userInput, poke);
            poke.calculateNature(poke.getUserNatureChoice());
            poke.choosePokemon(poke.getUserPokemonChoice());
            poke.solveCurrentStats();
            printCurrentStats(poke);
            endProgram = askToContinue(userInput, endProgram);
            break;
          case 2:
            poke = new Pokemon();
            getUserType(userInput, poke);
            poke.choosePokemon(poke.getUserPokemonChoice());
            getUserLevel(userInput, poke);
            getUserNature(userInput, poke);
            poke.calculateNature(poke.getUserNatureChoice());
            getUserEffortValues(userInput, poke);
            poke.calculateMinStats();
            poke.calculateMaxStats();
            getCurrentStats(userInput, poke);
            poke.solveForIVs();
            printIVs(poke);
            endProgram = askToContinue(userInput, endProgram);
            break;
          case 3:
            // This Battle Calculator requires an API. There is simply too much
            // data required.
            System.out.println("This section is still in progress!");
            // endProgram = askToContinue(userInput, endProgram);
            break;
          case 4:
            System.out.println("Thanks for using my program!");
            endProgram = true;
        }
      }
      userInput.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

/*This method decides which set of methods is executed. It specifically is executed
 * without an object, so unused objects are not created.*/
  public static int introduceMain(Scanner scan) {
    int choice = 0;
    boolean validInput = false;
    System.out.println("\t\tWelcome to my Pokemon Program!\nPlease choose one of the"
        + " following");
    while (validInput == false) {
      try {
        System.out.println("(1) Current stat calculator\t(2) IV calculator\n(3) "
            + "Battle Calculator\t\t(4) End Program");
        choice = scan.nextInt();
        if (choice > 0 && choice < 5) {
          validInput = true;
        }
        else {
          System.out.println("Sorry, please choose one of the following options.");
        }
      }
      catch (Exception any) {
        scan.nextLine();
        System.out.println("\nSorry, there seems to be an error.\n\tException: "+any
            +"\nLet's try that again!\n");
      }
    }
    return choice;
  }
  
//This method asks the user if they want to run the program again.  
  public static boolean askToContinue(Scanner scan, boolean endProgram) {
    String userChoice = "";
    System.out.println("Would you like to run my program again?\n(y)(n)");
    try {
      //scan.nextLine();
      userChoice = scan.nextLine();
      if (userChoice == "y"||userChoice == "yes") {
        endProgram = false;
      }
      else if (userChoice == "n"||userChoice == "no") {
        System.out.println("Thanks for using my program!");
        endProgram = true;
      }
      else {
        System.out.println("bad input 17.");
      }
    }
    catch (Exception any) {
      System.out.println("There seems to be an error, can you try that again?");
    }    
    return endProgram;
  }
  
//This section gets the Pokemon's level from the user.
  public static void getUserLevel(Scanner scan, Pokemon poke) {
    boolean validInput = false;
    int level = 0;
    while (validInput == false) {
      try {
        System.out.println("\nWhat level is your pokemon?");
        level = scan.nextInt();
        if (level > 0 && level < 101) {
          poke.setLevel(level);
          validInput = true;
        }
        else {
          System.out.println("Level has to be between 1 and 100.\nYou entered: "+
        level);
        }
      }
      catch (Exception any){
        scan.nextLine();
        System.out.println("\nSorry, there seems to be an error.\n\tException: "+any
            +"\nLet's try that again!\n");
      }
    }
  }
//This section gets the Pokemon's nature from the user.
  public static void getUserNature(Scanner scan, Pokemon poke) {
    boolean validInput = false;
    int userChoice = 0;
    while (validInput == false) {
      try {
        System.out.println("Please choose a nature for Your Pokemon!");
        System.out.println("\n(1)Hardy\t(2)Bold\t\t(3)Modest\t(4)Calm\t\t(5)Timid"
            + "\n(6)Lonely\t(7)Docile\t(8)Mild\t\t(9)Gentle\t(10)Hasty"
            + "\n(11)Adamant\t(12)Impish\t(13)Serious\t(14)Careful\t(15)Jolly"
            + "\n(16)Naughty\t(17)Lax\t\t(18)Rash\t(19)Bashful\t(20)Naive"
            + "\n(21)Brave\t(22)Relaxed\t(23)Quiet\t(24)Sassy\t(25)Quirky");
        userChoice = scan.nextInt();
        if (userChoice > 0 && userChoice < 26) {
          poke.setUserNatureChoice(userChoice);
          validInput = true;
        }
        else {
          System.out.println("Oops! Let's try choosing again!");
        }
      }
      catch (Exception any) {
        scan.nextLine();
        System.out.println("\nSorry, there seems to be an error.\n\tException: "+any
            +"\nLet's try that again!\n");
      }
    }
  }
//This section gets the Pokemon's effort values.
  public static void getUserEffortValues(Scanner scan, Pokemon poke) {
//Each stat can have a maximum of 255 EVs, and a max total of 510 EVs.
    boolean validInput = false;
    while (validInput == false) {
      try {
        for (int i = 0; i < 6; i++) {
          if (poke.getTotalEV() < 510) {
            System.out.println("\nHow many effort values are in "+poke.getStatNames(i)
              + "?");
            poke.setEV(i, scan.nextInt());
            if (poke.getEV(i) < 256 && poke.getEV(i) > -1) {
              poke.setTotalEV(poke.getTotalEV() + poke.getEV(i));
            }
            else {
              System.out.println("Oops! There seems to be an error. "
                  + "Please check your EVs.\nThere can only be 255 EVs in "
                  + "any one stat\nYou entered: " + poke.getEV(i));
              poke.resetOneEV(i);
              i = i - 1;
            }
          }
          else if (poke.getTotalEV() > 510) {
            System.out.println("Oops, there can only be a total of 510, at maximum!");
              poke.resetAllEV();
              i = -1;
          }
          else {
            validInput = true;
            break;
          }
           
        }
        validInput = true;
      }
      catch (Exception any) {
        scan.nextLine();
        System.out.println("\nWhoopsadaisy!!");
        poke.resetAllEV();
      }
    }
  }
//This method gets the Pokemon's type.
  public static void getUserType(Scanner scan, Pokemon poke) {
    boolean validInput = false;
    while (validInput == false) {
      try {
        System.out.println("\nPlease enter the name or ID number of your pokemon "
            + "below!");
        String userPokemonChoice = scan.nextLine();
        if (Integer.parseInt(userPokemonChoice) <= 0 ||Integer.parseInt(userPokemonChoice) >= 803) {
          System.out.println("Error: The ID number you entered is not a valid "
              + "number\n Please try again!");
        }
        else if(userPokemonChoice.length() >= 12|| userPokemonChoice.length() <= 2) {
          System.out.println("Error: The name you entered is not a valid pokemon "
              + "name\nPlease try again.");
        }
        else {
          //url.append(userPokemonChoice);
        }
        //poke.setUserPokemonChoice(scan.nextInt());
      }
      catch (Exception any) {
        scan.nextLine();
        System.out.println("\nWell Shucks, seems we have an errer!!!");
      }
    }
  }
//This method gets the Pokemon's current stats.
  public static void getCurrentStats(Scanner scan, Pokemon poke) {
    boolean validInput = false;
    while (validInput == false) {
      try {
        System.out.println("Please enter your Pokemon's stats.");
        for (int i = 0; i < 6; i++) {
          System.out.println(poke.getStatNames(i) + ":\t");
          System.out.println(poke.getMinStat(i)+" - "+poke.getMaxStat(i));
          int stat = scan.nextInt();
          if (stat <= poke.getMaxStat(i) && stat >= poke.getMinStat(i)) {
            poke.setCurrentStat(i, stat);
          }
          else {
            System.out.println("Sorry, the stats for a level "+poke.getLevel()+" "+
          poke.getPokemonType()+" can be: "+poke.getMinStat(i)+" - "+poke.getMaxStat(i));
            i--;
          }
        }
        validInput = true;
      }
      catch(Exception any) {
        scan.nextLine();
        System.out.println("There was an error, that happened, get over it."+any);
      }
    }
  }
//This method takes the given data and outputs the current stats of the Pokemon.
  public static void printCurrentStats(Pokemon poke) {
//This section simply prints the stats of a Pokemon.
    System.out.println("\n\t"+poke.getPokemonType()+"\nLv:"+poke.getLevel()
        +"\t\t" + poke.getPokemonNature()+"\n");
    for (int i = 0; i < 6; i++) {
      System.out.println(poke.getStatNames(i) + ":\t" + poke.getCurrentStat(i));
    }
    System.out.println("Total EVs: " + poke.getTotalEV());
  }
//This method takes the given data and outputs the individual values of the Pokemon.
  public static void printIVs(Pokemon poke) {
//This section solves the Pokemon stat equation for the IV variable. 
    for (int i = 0; i < 6; i ++) {    
      System.out.println(poke.getStatNames(i) + ":\t" + poke.getIV(i));
    }
  }  
}
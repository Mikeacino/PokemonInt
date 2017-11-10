import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MC {
  // Michael Carracino
  public static void main(String[] args) throws IOException {
    int userProgramChoice = 0;
    boolean endProgram = false;
    Scanner userInput = new Scanner(System.in);
    while (endProgram == false) {
      userProgramChoice = introduceMain(userInput); // checked
      Pokemon poke;
      switch (userProgramChoice) {
        case 1:
          poke = new Pokemon();
          getUserPokemonData(userInput, poke);
          getUserLevel(userInput, poke);
          getUserNature(userInput, poke);
          getUserEffortValues(userInput, poke);
          poke.calculateNature(poke.getUserNatureChoice());
          poke.solveCurrentStats();
          PokemonWindow1 fancy = new PokemonWindow1(poke);
          endProgram = askToContinue(userInput, endProgram);
          break;
        case 2:
          poke = new Pokemon();
          getUserPokemonData(userInput, poke);
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
          pokemonBackgroundInfo();
          endProgram = askToContinue(userInput, endProgram);
          break;
        case 4:
          System.out.println("Thanks for using my program!");
          endProgram = true;
      }
    }
    userInput.close();
  }

  /*
   * This method decides which set of methods is executed. It specifically is
   * executed without an object, so unused objects are not created.
   */
  public static int introduceMain(Scanner scan) {
    int userChoice = 0;
    boolean validInput = false;
    while (validInput == false) {
      try {
        System.out
            .println("\t\tWelcome to my Pokemon Program!\nPlease choose one of"
                + " the following");
        System.out
            .println("(1) Current stat calculator\t(2) IV calculator\n(3) "
                + "General Information\t\t(4) End Program");
        userChoice = scan.nextInt();
        if (userChoice > 0 && userChoice < 5) {
          validInput = true;
        } else {
          System.out.println("Error\nYou entered: " + userChoice + "\n");
        }
      } catch (Exception any) {
        scan.nextLine();
        System.out.println("\nSorry, there was an exception!\nException: " + any
            + "\nLet's try that again!\n");
      }
    }
    return userChoice;
  }

  public static void pokemonBackgroundInfo() {
    System.out.println(
        "Welcome to a quick introduction to the world of Pokemon.\nAs of "
            + "this intro, there are 802 Pokemon in the Sun and Moon games. \nHere "
            + "are some aspects of a Pokemon:\n\nType:\tThis is the species "
            + "of the Pokemon, e.g. Pikachu, Squirtle, Caterpie, etc.\n\nLevel:\tA "
            + "Pokemon can be between level 1 and 100.\n\nNature:\tA Pokemon can have "
            + "1 of 25 natures. A Nature will increase one stat, while decreasing\n"
            + "\tanother by 10%.\n\nStats:\tA Pokemon has 6 stats:\n\tHealth Points\n"
            + "\tAttack\n\tDefense\n\tSpecial Attack\n\tSpecialDefense\n\tSpeed\n"
            + "\nIndividual Value:\tEvery stat has a hidden number associated with it "
            + "known as an IV.\n\tAn IV can be between 0 and 31. A higher IV will "
            + "give the Pokemon a higher stat.\n\nEffort Value:\tWhenever a Pokemon "
            + "defeats another Pokemon in battle, it gains EVs\n\tdepending on the "
            + "Pokemon defeated. EVs can be applied to any stat, but only up to"
            + " 255 per\n\tstat, and 510 in total. EVs will increase the pokemon's stat"
            + " to which they are applied.");
  }

  // This method asks the user if they want to run the program again.
  public static boolean askToContinue(Scanner scan, boolean endProgram) {
    String userChoice = "";
    System.out.println("\nWould you like to run my program again?\n(y)(n)");
    try {
      scan.nextLine();
      userChoice = scan.nextLine().toString();
      if (userChoice.equals("y") || userChoice.equals("yes")) {
        endProgram = false;
      } else if (userChoice.equals("n") || userChoice.equals("no")) {
        System.out.println("Thanks for using my program!");
        endProgram = true;
      } else {
        System.out.println(
            "I'll take that as a 'no'\nYou entered: " + userChoice + "\n");
        endProgram = true;
      }
    } catch (Exception any) {
      System.out.println("\nSorry, there was an exception!\nException: " + any
          + "\nLet's try that again!\n");
    }
    return endProgram;
  }

  // This section gets the Pokemon's level from the user.
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
        } else {
          System.out.println(
              "Level has to be between 1 and 100.\nYou entered: " + level);
        }
      } catch (Exception any) {
        scan.nextLine();
        System.out.println("\nSorry, there was an exception!\nException: " + any
            + "\nLet's try that again!\n");
      }
    }
  }

  // This section gets the Pokemon's nature from the user.
  public static void getUserNature(Scanner scan, Pokemon poke) {
    boolean validInput = false;
    int userChoice = 0;
    while (validInput == false) {
      try {
        System.out.println("Please choose a nature for Your Pokemon!");
        System.out
            .println("\n(1)Hardy\t(2)Bold\t\t(3)Modest\t(4)Calm\t\t(5)Timid"
                + "\n(6)Lonely\t(7)Docile\t(8)Mild\t\t(9)Gentle\t(10)Hasty"
                + "\n(11)Adamant\t(12)Impish\t(13)Serious\t(14)Careful\t(15)Jolly"
                + "\n(16)Naughty\t(17)Lax\t\t(18)Rash\t(19)Bashful\t(20)Naive"
                + "\n(21)Brave\t(22)Relaxed\t(23)Quiet\t(24)Sassy\t(25)Quirky");
        userChoice = scan.nextInt();
        if (userChoice > 0 && userChoice < 26) {
          poke.setUserNatureChoice(userChoice);
          validInput = true;
        } else {
          System.out
              .println("Error, please enter a number between 1 and 25.\nYou"
                  + " entered: " + userChoice + "\n");
        }
      } catch (Exception any) {
        scan.nextLine();
        System.out.println("\nSorry, there was an exception!\nException: " + any
            + "\nLet's try that again!\n");
      }
    }
  }

  // This section gets the Pokemon's effort values.
  public static void getUserEffortValues(Scanner scan, Pokemon poke) {
    // Each stat can have a maximum of 255 EVs, and a max total of 510 EVs.
    boolean validInput = false;
    while (validInput == false) {
      try {
        for (int i = 0; i < 6; i++) {
          if (poke.getTotalEV() < 510) {
            System.out.println("\nHow many effort values are in "
                + poke.getStatNames(i) + "?");
            poke.setEV(i, scan.nextInt());
            if (poke.getEV(i) < 256 && poke.getEV(i) > -1) {
              poke.setTotalEV(poke.getTotalEV() + poke.getEV(i));
            } else {
              System.out.println("Oops! There seems to be an error. "
                  + "Please check your EVs.\nThere can only be 255 EVs in "
                  + "any one stat\nYou entered: " + poke.getEV(i));
              poke.resetOneEV(i);
              i = i - 1;
            }
          } else if (poke.getTotalEV() > 510) {
            System.out.println("Oops, there can only be a total of 510, at "
                + "maximum!\nYou entered: " + poke.getTotalEV());
            poke.resetAllEV();
            i = -1;
          } else {
            validInput = true;
            break;
          }

        }
        validInput = true;
      } catch (Exception any) {
        scan.nextLine();
        System.out.println("\nWhoopsadaisy!!");
        System.out.println(any);
        poke.resetAllEV();
      }
    }
  }

  // This method gets the Pokemon's type.
  public static void getUserPokemonData(Scanner scan, Pokemon poke) {
    boolean validInput = false;
    while (validInput == false) {
      try {
        int counter = 0;
        System.out
            .println("\nPlease enter the ID number of your pokemon below!");
        int userPokemonChoice = scan.nextInt();
        if (userPokemonChoice <= 0 || userPokemonChoice >= 802) {
          System.out.println("Error: The ID number you entered is not a valid "
              + "number\n Please try again!");
        } else {
          poke.setPokemonID(userPokemonChoice);
          HttpURLConnection connection = null;
          String finishedURL = "https://pokeapi.co/api/v2/pokemon/"
              + poke.getPokemonID();
          URL url = new URL(finishedURL);
          connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("GET");
          connection.setRequestProperty("User-Agent", "Mozilla/5.0");
          
          System.out.println("Getting your Pokemon's data!");
          
          BufferedReader in = new BufferedReader(
              new InputStreamReader(connection.getInputStream()));

          JSONParser parser = new JSONParser();
          String inputLine = in.readLine();

          // Parse the raw data into a JSON Object
          JSONObject obj = (JSONObject) parser.parse(inputLine);

          //This section gets the Pokemon's types
          List<?> typeList = (List) obj.get("types");

          for (int i = 0; i < typeList.size(); i++) {
            JSONObject type = (JSONObject) typeList.get(i);
            JSONObject typeData = (JSONObject) type.get("type");
            String typeName = (String) typeData.get("name");
            typeName = typeName.substring(0,1).toUpperCase()+typeName.substring(1, 
                typeName.length());
            poke.setTypeList(i, typeName);        
          }
       
          
          // This sets the pokemon name
          poke.setPokemonName((String) obj.get("name"));
          String temporaryName = poke.getPokemonName();
          System.out.println("Got " + temporaryName.substring(0,1).toUpperCase() + temporaryName.substring(1,temporaryName.length())+"'s data!");
          
          // These objects get the value of the pokemon's base stats.
          JSONArray stat_array = (JSONArray) obj.get("stats");
          for (int i = 5; i > -1; i--) {
            JSONObject stat = (JSONObject) stat_array.get(i);
            long tempBaseStat = (long) stat.get("base_stat");
            poke.setBaseStats(counter, (int) tempBaseStat);
            counter++;
          }
          validInput = true;
        }
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParseException e) {
      } catch (Exception e) {
        scan.nextLine();
        System.out.println("Exception " + e);
        System.out
            .println("Sorry, something went wrong, please try "
                + "again.");
      }
    }

  }

  // This method gets the Pokemon's current stats.
  public static void getCurrentStats(Scanner scan, Pokemon poke) {
    boolean validInput = false;
    while (validInput == false) {
      try {
        System.out.println("Please enter your Pokemon's stats.");
        for (int i = 0; i < 6; i++) {
          System.out.println(poke.getMinStat(i) + " - " + poke.getMaxStat(i));
          int stat = scan.nextInt();
          if (stat <= poke.getMaxStat(i) && stat >= poke.getMinStat(i)) {
            poke.setCurrentStat(i, stat);
          } else {
            System.out.println("Sorry, the stats for a level " + poke.getLevel()
                + " " + poke.getPokemonType() + " can be: " + poke.getMinStat(i)
                + " - " + poke.getMaxStat(i));
            i--;
          }
        }
        validInput = true;
      } catch (Exception any) {
        scan.nextLine();
        System.out
            .println("There was an error, that happened, get over it." + any);
      }
    }
  }

  // This method takes the given data and outputs the current stats of the
  // Pokemon.
  public static void printCurrentStats(Pokemon poke) {
    // This section simply prints the stats of a Pokemon.
    System.out.println("Pokemon: " + poke.getPokemonName());
    System.out.println("\n\t" + poke.getPokemonType() + "\nLv:"
        + poke.getLevel() + "\t\t" + poke.getPokemonNature() + "\n");
    for (int i = 0; i < 6; i++) {
      System.out.println(poke.getStatNames(i) + ":\t" + poke.getCurrentStat(i));
    }
    System.out.println("Total EVs: " + poke.getTotalEV());
  }

  // This method outputs the individual values of the Pokemon.
  public static void printIVs(Pokemon poke) {
    // This section solves the Pokemon stat equation for the IV variable.
    for (int i = 0; i < 6; i++) {
      System.out.println(poke.getStatNames(i) + ":\t" + poke.getIV(i));
    }
  }
}
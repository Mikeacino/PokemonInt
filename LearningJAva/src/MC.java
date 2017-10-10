import java.util.Scanner;
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
	
/*This program will calculate the value of a Pokemon's stats. Much of this is very 
 * game specific knowledge, so some of it may not be intuitive. I will try to explain
 * why I made certain choices. This program still does not take bad input in most 
 * cases. Much of this program could be cut down with the use of a Pokemon APi.*/
  
/*A Pokemon must have IVs, EVs, a level, a nature, base stats, and a type. Each 
 * attribute has limitations, but for the purpose of this program just know that all
 * of these attributes of a Pokemon are needed for an equation to find it's current 
 * stats.*/
  /*My print methods are where I ran into issues with integer division. In the past
   * I used only integers in this equation, but it caused miscalculations. Now I set 
   * the natureMultiplier array as a double. This causes the result of the equation to 
   * be a double. I then cast the resulting double to an int before printing it.
   * */
  
	public static void main(String[] args) {
	  int [] customBaseStats = {0, 0, 0, 0, 0, 0};
	  int userProgramChoice = 0;
		Scanner userInput = new Scanner(System.in);
 		userProgramChoice = introduceMain(userInput);
 		switch (userProgramChoice) {
 		  case 1:
 		    Pokemon poke1 = new Pokemon();
 		    getUserType(userInput, poke1);
 		    getUserLevel(userInput, poke1);
 		    getUserNature(userInput, poke1);
 		    getUserEffortValues(userInput, poke1);
   			poke1.calculateNature(poke1.getUserNatureChoice());
   			poke1.choosePokemon(poke1.getUserPokemonChoice());
   			poke1.solveCurrentStats();
   			printStats(poke1);
   			break;
 		  case 2:
 		    Pokemon poke2 = new Pokemon();
 		    getUserType(userInput, poke2);
 		    getUserLevel(userInput, poke2);
 		    getUserNature(userInput, poke2);
 		    getUserEffortValues(userInput, poke2);
   			getCurrentStats(userInput, poke2);
   			poke2.calculateNature(poke2.getUserNatureChoice());
   			poke2.choosePokemon(poke2.getUserPokemonChoice());
   			poke2.solveForIVs();
   			printIVs(poke2);
   			break;
 		  case 3:
 		    customBaseStats = getCustomBaseStats(userInput);
 		    Pokemon poke3 = new Pokemon(customBaseStats);
 		    getUserCustomType(userInput, poke3); 
 		    getUserLevel(userInput, poke3);
        getUserNature(userInput, poke3);
        getUserEffortValues(userInput, poke3);
        poke3.calculateNature(poke3.getUserNatureChoice());
        poke3.solveCurrentStats();
        printStats(poke3);
 		  case 4:
 		    System.out.println("Thanks for using my program!");
 		    break;
    }
    userInput.close();
	}
	
  public static int introduceMain(Scanner scan) {
    int choice = 0;
    try {
      System.out.println("\t\tWelcome to my Pokemon Program!");
      System.out.println("This program currently has two functions. Please choose "
          + "a function:\n(1)Max stat calculator\t\t(2)IV calculator\n(3)Build a "
          + "Pokemon\t\t(4)End Program");
      choice = scan.nextInt();
    }
    catch (Exception e) {
      System.out.println("Sorry, there seems to be an error.");
    }
    return choice;
  }
  
//These three methods are needed for all aspects of my program. 
//There is no case where we need to solve for these aspects of a Pokemon.
  public static void getUserLevel(Scanner scan, Pokemon poke) {
//This section gets the Pokemon's level from the user.
    System.out.println("\nWhat level is your pokemon?");
    poke.setLevel(scan.nextInt());  
  }
  public static void getUserNature(Scanner scan, Pokemon poke) {
//This section gets the Pokemon's nature from the user.
    System.out.println("Please choose a nature for Your Pokemon!");
    System.out.println("\n(1)Hardy\t(2)Bold\t\t(3)Modest\t(4)Calm\t\t(5)Timid"
        + "\n(6)Lonely\t(7)Docile\t(8)Mild\t\t(9)Gentle\t(10)Hasty"
        + "\n(11)Adamant\t(12)Impish\t(13)Serious\t(14)Careful\t(15)Jolly"
        + "\n(16)Naughty\t(17)Lax\t\t(18)Rash\t(19)Bashful\t(20)Naive"
        + "\n(21)Brave\t(22)Relaxed\t(23)Quiet\t(24)Sassy\t(25)Quirky");
    poke.setUserNatureChoice(scan.nextInt());   
  }
  public static void getUserEffortValues(Scanner scan, Pokemon poke) {
//For this next section I use a loop to get the EVs for each stat. Each stat can
//have a maximum of 255 EVs, and a max total of 510 EVs.
    for (int i = 0; i < 6; i++) {
      if (poke.getTotalEV() < 510) {
        System.out.println("\nHow many effort values are in "
            +poke.getStatNames(i) + "?");
        poke.setEV(i, scan.nextInt());
        if (poke.getEV(i) < 256) {
          poke.setTotalEV(poke.getTotalEV() + poke.getEV(i)); 
        }
        else {
          System.out.println("Oops! There seems to be an error. "
              + "Please check your EVs.\nThere can only be 255 EVs in "
              + "any one stat\nYou entered: " + poke.getEV(i));
          poke.resetEV();
          i = -1;
        }
      }
      else if (poke.getTotalEV() == 510) {
        break;
      }
      else {
        System.out.println("Oops! There seems to be an error. "
            + "Please check your EVs.\nThere can only be 510 EVs in "
            + "total.\nTotal EVs: " + poke.getTotalEV());
        poke.resetEV();
        i = -1;
      }
    }
  }
  
  public static void getUserType(Scanner scan, Pokemon poke) {
//This method gets data from the user that are essential for any calculations.
    System.out.println("\nPlease choose a pokemon below:");
//This section gets the Pokemon's type.
    System.out.println("(1)Venusaur\t(2)Blastoise\t(3)Charizard\n(4)Pikachu\t(5)"
        + "Dragonite\t(6)Mew\n(7)Tyranitar\t(8)Milotic\t(9)Metagross\n(10)"
        + "Salamence");
    poke.setUserPokemonChoice(scan.nextInt());
  }
  public static void printStats(Pokemon poke) {
//This section simply prints the stats of a Pokemon.
    System.out.println("\n\t"+poke.getPokemonName()+"\nLv:"+poke.getLevel()
        +"\t\t" + poke.getPokemonNature()+"\n");
    for (int i = 0; i < 6; i++) {
      System.out.println(poke.getStatNames(i) + ":\t" + poke.getCurrentStats(i));
    }
    System.out.println("Total EVs: " + poke.getTotalEV());
  }
  
  public static void getCurrentStats(Scanner userInput, Pokemon poke) {
    System.out.println("Please enter your Pokemon's stats.");
    for (int i = 0; i < 6; i++) {
      System.out.println(poke.getStatNames(i) + ":\t");
      poke.setCurrentStats(i,userInput.nextInt());
    }
  }
  public static void printIVs(Pokemon poke) {
//This section solves the Pokemon stat equation for the IV variable. 
    for (int i = 0; i < 6; i ++) {    
      System.out.println(poke.getStatNames(i) + ":\t" + poke.getIV(i));
    }
  }  
  
  public static int[] getCustomBaseStats(Scanner scan) {
    String [] statNames = {"Hp", "Att", "Def", "SpAtt", "SpDef", "Speed"};
    int [] baseStats = {0, 0, 0, 0, 0, 0};
    System.out.println("Please enter your Pokemon's base stats.");
    for (int i = 0; i < 6; i++) {
      System.out.println(statNames[i] + ": ");
      baseStats[i] = scan.nextInt();
    }
    return baseStats;
  }
	public static void getUserCustomType(Scanner scan, Pokemon poke) {
	  System.out.println("Please enter Your Pokemon's type!");
	  scan.nextLine();
	  poke.setPokemonType(scan.nextLine().toString());
	}
}
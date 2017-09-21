import java.util.Scanner;
public class MC {
/*Michael Carracino 
This program will calculate the value of a Pokemon's stats. Much of this is very game specific knowledge, so some of it
	may not be intuitive. I will try to explain why I made certain choices. I have yet to account for bad input in general.*/
	static Pokemon poke = new Pokemon();
	static int [] maxIndividualValue = {31, 31, 31, 31, 31, 31};
	//IVs vary between 0 and 31, for this program we only care that they be maximized, In my next program these will change.
	static int [] effortValue = {0, 0, 0, 0, 0, 0};
	//EVs can be between 0 and 255, with a maximum total of 510.
	static int userNature = 0;
	//This variable is used to determine user input. If I resolve my issues with Scanners, I can make this a local variable.
	static int userPokemon = 0;
	//Similarly This variable should also not be here, but I have trouble with Scanners. If I could make multiple scanners then
	//I could make these variables local.
	static int totalEV = 0;
	//These next variables will hopefully be replaced with an API, when I have power and Internet available >_>
	static String pokemonName = "";
	static int [] venusaurBaseStats = {80, 82, 83, 100, 100, 80};
	static int [] blastoiseBaseStats = {79, 83, 100, 85, 105, 78};
	static int [] charizardBaseStats = {78, 84, 78, 109, 85, 100};
	static int [] pikachuBaseStats = {35, 55, 40, 50, 50, 90};
	static int [] dragoniteBaseStats = {91, 134, 95, 100, 100, 80};
	static int [] mewBaseStats = {100, 100, 100, 100, 100, 100};
	static int [] tyranitarBaseStats = {100, 134, 110, 95, 100, 61};
	static int [] miloticBaseStats = {95, 60, 79, 100, 125, 81};
	static int [] metagrossBaseStats = {80, 135, 130, 95, 90, 70};
	static int [] salamenceBaseStats = {95, 135, 80, 110, 80, 100};
	public static void main(String[] args) {
		intro();
		getUserInput();
		calculateNature();
		iChooseYou();
		printStats();
	}
	public static void getUserInput() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("\nPlease choose a pokemon below:\n");
		System.out.println("(1)Venusaur\t(2)Blastoise\t(3)Charizard\n(4)Pikachu\t(5)Dragonite\t(6)Mew\n(7)Tyranitar\t"
				+ "(8)Milotic\t(9)Metagross\n(10)Salamence");
		userPokemon = userInput.nextInt();
		System.out.println("\nWhat level is your pokemon?");
		poke.setLevel(userInput.nextInt());	
		System.out.println("\nWhat is your pokemon's Nature?\n(1)  Adamant\t(2)  Bashful\t(3)  Bold\t(4)  Brave\t(5)  Calm\n"
				+ "(6)  Careful\t(7)  Docile\t(8)  Gentle\t(9)  Hardy\t(10) Hasty\n(11) Impish\t(12) Jolly\t(13) Lax\t(14) "
				+ "Lonely\t(15) Mild\n(16) Modest\t(17) Naive\t(18) Naughty\t(19) Quiet\t(20) Quirky\n(21) Rash\t(22) Relaxed\t"
				+ "(23) Sassy\t(24) Serious\t(25) Timid");
		userNature = userInput.nextInt();
		for (int i = 0; i < 6; i++) {
			if (totalEV < 510) {
				System.out.println("\nHow many effort values are in " + poke.getStatNames(i) + "?");
				effortValue[i] = userInput.nextInt();
				if (effortValue[i] < 256) {
					totalEV = totalEV + effortValue[i];	
				}
				else {
					System.out.println("Oops! There seems to be an error. Please check your EVs.\nThere can only be 255 EVs in "
							+ "any one stat\nYou entered: " + effortValue[i]);
					resetEV();
					i = -1;
				}
			}
			else if (totalEV == 510) {
				break;
			}
			else {
				System.out.println("Oops! There seems to be an error. Please check your EVs.\nThere can only be 510 EVs in "
						+ "total.\nTotal EVs: " + totalEV);
				resetEV();
				i = -1;
			}
		}
		userInput.close();
	}
	public static void resetEV(){
		for (int i = 0; i < 6; i++) {
			effortValue[i] = 0;
		}
		totalEV = 0;
	}
	public static void intro() {
		System.out.println("\t\tWelcome to my Pokemon max stat calculator!");
		System.out.println("This program currently displays the max stats for select pokemon at a given level!");
	}
	public static void iChooseYou() {
		if (userPokemon == 1) {
			pokemonName = "Venusaur";
			poke.setAsBaseStats(venusaurBaseStats);
		}
		else if (userPokemon == 2) {
			pokemonName = "Blastoise";
			poke.setAsBaseStats(blastoiseBaseStats);
		}
		else if (userPokemon == 3) {
			pokemonName = "Charizard";
			poke.setAsBaseStats(charizardBaseStats);
		}
		else if (userPokemon == 4) {
			pokemonName = "Pikachu";
			poke.setAsBaseStats(pikachuBaseStats);
		}
		else if (userPokemon == 5) {
			pokemonName = "Dragonite";
			poke.setAsBaseStats(dragoniteBaseStats);
		}
		else if (userPokemon == 6) {
			pokemonName = "Mew";
			poke.setAsBaseStats(mewBaseStats);
		}
		else if (userPokemon == 7) {
			pokemonName = "Tyranitar";
			poke.setAsBaseStats(tyranitarBaseStats);
		}
		else if (userPokemon == 8) {
			pokemonName = "Milotic";
			poke.setAsBaseStats(miloticBaseStats);
		}
		else if (userPokemon == 9) {
			pokemonName = "Metagross";
			poke.setAsBaseStats(metagrossBaseStats);
		}
		else if (userPokemon == 10) {
			pokemonName = "Salamence";
			poke.setAsBaseStats(salamenceBaseStats);
		}
	}
	public static void calculateNature() {
		//There's an error here.....probably.....somewhere....
		//This section and the associated sysout line could be made clearer if I made an array of all possible natures and 
		//looped through the array. Also then I could print the nature in the printStats() method. The issue is that I don't
		//know if that is better than the current setup in terms of memory or readability.
		if (userNature == 1) {
			poke.setNature(2, 3);
		}
		else if (userNature == 3) {
			poke.setNature(2,1);
		}
		else if (userNature == 4) {
			poke.setNature(1,5);
		}
		else if (userNature == 5) {
			poke.setNature(4,1);
		}
		else if (userNature == 6) {
			poke.setNature(4,3);
		}
		else if (userNature == 8) {
			poke.setNature(4,2);
		}
		else if (userNature == 10) {
			poke.setNature(5,2);
		}
		else if (userNature == 11) {
			poke.setNature(2,3);
		}
		else if (userNature == 12) {
			poke.setNature(5,3);
		}
		else if (userNature == 13) {
			poke.setNature(2,4);
		}
		else if (userNature == 14) {
			poke.setNature(1,2);
		}
		else if (userNature == 15) {
			poke.setNature(3,2);
		}
		else if (userNature == 16) {
			poke.setNature(3,1);
		}
		else if (userNature == 17) {
			poke.setNature(5, 4);
		}
		else if (userNature == 18) {
			poke.setNature(1,4);
		}
		else if (userNature == 19) {
			poke.setNature(3,5);
		}
		else if (userNature == 21) {
			poke.setNature(3,4);
		}
		else if (userNature == 22) {
			poke.setNature(2,5);
		}
		else if (userNature == 23) {
			poke.setNature(4,5);
		}
		else if (userNature == 25) {
			poke.setNature(5,1);
		}
		else{
		}
	}
	public static void printStats() {
		//Wow.. so this part is the formula that is used in game. No explaining will really make this part better. HP has a 
		//different equation that the other 5 stats.
		System.out.println("\nLvl: "+poke.getLevel()+"\t"+pokemonName);
		int healthPoints = ((maxIndividualValue[0] + 2*poke.getBaseStats(0) + (effortValue[0]/4))*poke.getLevel()/100) + 10 + 
				poke.getLevel();
		System.out.println(poke.getStatNames(0) + ":\t" + healthPoints);
		for (int j = 1; j < 6; j++){
			double stats = (maxIndividualValue[j] + 2*poke.getBaseStats(j) + (effortValue[j]/4))*poke.getLevel()/100 + 5;//*nature
			stats = poke.getNature(j)*stats;
			int baseStats = (int) stats;
			System.out.println(poke.getStatNames(j) + ":\t" + baseStats);
		}
		System.out.println("Total EVs: " + totalEV);
	}
}
import java.util.Scanner;

public class MCTesting {

	public static void main(String[] args) {
		/*int stat = 262;
		int level = 100;
		int baseStat = 70;
		int EV = 63;
		
		double IV = (stat/(1.1) - 5)*(100/level) - EV - 2*baseStat;
		int finalIV = (int) Math.ceil(IV);
		System.out.println(finalIV);*/
		
		Scanner userIn = new Scanner(System.in); 
		System.out.println("Enter data, 1-5");
		int newInt = userIn.nextInt();
		userIn.close();
		
		switch (newInt) {
			case 1:
				System.out.println("You chose 1");
				break;
			case 2:
				System.out.println("You chose 2");
				break;
			case 3:
				System.out.println("You chose 3");
				break;
			case 4:
				System.out.println("You chose 4");
				break;
			case 5:
				System.out.println("You chose 5");
				break;
			default:
				System.out.println("You done goofed");
				break;
		}
	}

}

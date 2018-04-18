import java.util.Random;

public class DiceRollsGame implements Game {

	@Override
	public boolean playGame(String villainName, boolean moreLucky) {
		Random rand = new Random();
		Output output = new Output();
		System.out.println("Welcome to dice rolls! Here's how it works:\n\nIn this game the you and " + villainName + " roll a 6-sided die");
		System.out.println("Whoever has the highest roll wins, ties are re-rolled\n");
		String options[] = {"Yes", "No"};
		
		while(true) {
			System.out.println("Are you ready?");
			int userChoice = output.printOptions(options);
			if (userChoice == 1) {
				int userRoll = rand.nextInt(6) + 1;
				int villainRoll = rand.nextInt(6) + 1;
				
				if (moreLucky && userRoll <= villainRoll) {userRoll = rand.nextInt(6) + 1;}
				
				if (userRoll == villainRoll) {System.out.println("It's a tie! You both rolled " + userRoll + ", try again");}
				else if (userRoll > villainRoll) {
					System.out.println("Congratulations! You beat " + villainName + "'s roll of " + villainRoll + " with a roll of " + userRoll);
					return true;
				}else {
					System.out.println("Uh oh! You lost to " + villainName + " with a roll of " + userRoll + ", who had a roll of " + villainRoll);
					return false;
				}
			}
		}
		
	}
	
//	public static void main(String[] args) {
//		DiceRollsGame diceRolls = new DiceRollsGame();
//		//Test without being lucky
//		if (diceRolls.playGame("Darius", false)) {
//			System.out.println("User won");
//		}else {System.out.println("User lost");}
//		
//		//Test with being lucky
//		if (diceRolls.playGame("Draven", true)) {
//			System.out.println("User won");
//		}else {System.out.println("User lost");}
//	}

}

package commandLineElements;
import java.util.Random;

public class GuessNumberGame implements Game {
	

	@Override
	public boolean playGame(String villainName, boolean moreLucky) {
		// TODO Auto-generated method stub
		boolean gameCompleted = false;
		Random rand = new Random();
		Output output = new Output();
		System.out.println("Welcome to the number guessing game! Here's how it works:\n\n" + villainName + " has thought of a number between 1 - 10, your job is to guess their number");
		System.out.println("You have two chances, " + villainName + " will give you a small hint if you don't get it the first time\n");
		int villainChoice = rand.nextInt(10) + 1;
		int numUserGuesses = 0;
		while(!gameCompleted) {
			System.out.println("Guess a number between 1 - 10");
			int userGuess = output.getValidInputNum(10);
			if (userGuess == villainChoice) {
				System.out.println("Congratulations! You guessed " + villainName + "'s number correctly");
				return true;
				}else {
				if (userGuess < villainChoice) {										//If the guess was too low
					if (moreLucky) {
						if (userGuess + 1 == villainChoice) {
							System.out.println("Congratulations! You got lucky!");
							return true;
							} else {System.out.println(villainName + ": higher!");}
						}
					else {System.out.println(villainName + ": higher!");} 				//Else not strictly needed but good form
				}else {																	//Else, the guess was too high
					if (moreLucky) {
						if (userGuess - 1 == villainChoice) {
							System.out.println("Congratulations! You got lucky!");
							return true;
							} System.out.println(villainName + ": lower!");
						} 
					else {System.out.println(villainName + ": lower!");}				//Maybe I should change so these don't get printed the second time
					}							
			}
			numUserGuesses++;
			if (numUserGuesses > 1) {
				System.out.println("Uh oh, you ran out of guesses!");
				gameCompleted = true;
			}
		}
		return false;
	}

//	public static void main(String[] args) {
//		GuessNumberGame guessNumbers = new GuessNumberGame();
//		//Tests without being lucky
//		if (guessNumbers.playGame("Darius", false)) {
//			System.out.println("User won");
//		}else {System.out.println("User lost");}
//		
//		//Tests with being lucky
//		if (guessNumbers.playGame("Draven", true)) {
//			System.out.println("User won");
//		}else {System.out.println("User lost");}
//	}

}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PaperScissorsRockGame implements Game {

	@Override
	public boolean playGame(String villainName, boolean moreLucky) { //Returns true if the play won

		Random rand = new Random();
		Output output = new Output();
		String[] options = {"Paper", "Scissors", "Rock"};
		
		System.out.println("Welcome to Paper Scissors Rock! Heres how to play:\nYou and " + villainName + " choose either paper, scissors or rock\n");
		System.out.println("Paper beats rock\nScissors beats paper\nRock beats scissors\nAny games tied are replayed\n");

		while(true) {
			List<PSRChoice> villainOptions = new ArrayList<PSRChoice>(Arrays.asList(PSRChoice.PAPER, PSRChoice.SCISSORS, PSRChoice.ROCK));
			System.out.println("Do you want to throw paper, scissors or rock?");
			PSRChoice userChoice = villainOptions.get(output.printOptions(options) - 1);
			
			switch (userChoice) { //If the Hero is more lucky, makes it more likely for the villain to pick the Hero's choice
				case PAPER: if (moreLucky) {villainOptions.add(PSRChoice.PAPER);} break;
				case SCISSORS: if (moreLucky) {villainOptions.add(PSRChoice.SCISSORS);} break;
				case ROCK: if (moreLucky) {villainOptions.add(PSRChoice.ROCK);} break;
				default: System.out.println("ERROR: Somehow the user managed to input something outside the viable range"); 
						 return false;
			}
			
			PSRChoice villainChoice = villainOptions.get(rand.nextInt(villainOptions.size()));
			
			if (userChoice == villainChoice) {System.out.println("It's a tie! Try again");}
			else if (userChoice == PSRChoice.PAPER) {
				if (villainChoice == PSRChoice.ROCK) {
					System.out.println("Congratulations! Paper beats rock"); 
					return true;
				} else {
					System.out.println("Uh Oh, you lost! Paper loses to scissors");
					return false;
				}
			}
			else if (userChoice == PSRChoice.SCISSORS) {
				if (villainChoice == PSRChoice.PAPER) {
					System.out.println("Congratulations! Scissors beats paper"); 
					return true;
				} else {
					System.out.println("Uh Oh, you lost! Scissors loses to rock");
					return false;
				}
			}
			else { //userChoice == PSRChoice.Rock
				if (villainChoice == PSRChoice.SCISSORS) {
					System.out.println("Congratulations! Rock beats scissors"); 
					return true;
				} else {
					System.out.println("Uh Oh, you lost! Rock loses to paper");
					return false;
				}
			}

			//return false;
		}
	}
	
	enum PSRChoice {
		PAPER,
		SCISSORS,
		ROCK;
		
	}
	
//	public static void main(String[] args) {
//		PaperScissorsRockGame psr = new PaperScissorsRockGame();
//		//Tests without being lucky
//		if (psr.playGame("Darius", false)) {
//			System.out.println("User won");
//		}else {System.out.println("User lost");}
//		
//		//Tests with being lucky
//		if (psr.playGame("Drave", true)) {
//			System.out.println("User won");
//		}else {System.out.println("User lost");}
//		
//	}

}

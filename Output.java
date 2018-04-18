import java.util.Scanner;

public class Output
	{

		public int getValidInputNum(int acceptedRange) {
			//Prior to this, options have been printed and prompt to user is printed
			Scanner scanner = new Scanner(System.in);
			int toReturn = 0; //Have to instantiate to something since method has to return an int
			boolean inputGood = false;
			while (!inputGood) {
					String userInput = scanner.next();
					try {
						int userChoice = Integer.parseInt(userInput); //Checks if input is an int
						if (userChoice > 0 && userChoice <= acceptedRange) {
							inputGood = true;
							toReturn = userChoice;
						}else {
							System.out.println("Input was not one of the options, please try again.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Input was not an integer, please try again.");
				}
			}
			System.out.println(".\n.\n.");
			//scanner.close(); --> This closes the scanner for the whole program, not just for the method
			return toReturn; //This is only done this way since the method needs to return an int
		}
		
		
		
		public int printOptions(String[] options) {
			int optionNum = 1;
			for (String option : options) {
				System.out.println("(" + optionNum + "): " + option);
				optionNum++;
			}
			System.out.println("Please enter your choice:");
			return getValidInputNum(optionNum-1);
		}
		
		public String getValidInputStr(int minSize, int maxSize) {
			Scanner scanner = new Scanner(System.in);
			boolean inputGood = false;
			String toReturn = "";
			while(!inputGood) {
				String userInput = scanner.next();
				if(userInput.length() >= minSize && userInput.length() <= maxSize) {
					toReturn = userInput;
					inputGood = true;
				}else {
					System.out.println("Name must be between " + minSize + " and " + maxSize + " characters!");
				}
			}
			System.out.println(".\n.\n.");
			//scanner.close();--> This closes the scanner for the whole program, not just for the method
			return toReturn;
		}
	}

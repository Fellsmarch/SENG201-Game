import java.util.ArrayList;
import java.util.Arrays;
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
		
		
		//Inventory stuff
		public void printPowerupInventory(ArrayList<Integer> powerups) {
			int kagesCount = 0; int infinityEdgeCount = 0; int ninjaTabiCount = 0;
			for (int powerup : powerups) {
				if (powerup == 1) {kagesCount++;}
				else if (powerup == 2) {infinityEdgeCount++;}
				else if (powerup == 3) {ninjaTabiCount++;}
			}
			if (kagesCount > 0) {System.out.println(kagesCount + " x Kage's Lucky Pick");}
			if (infinityEdgeCount > 0) {System.out.println(infinityEdgeCount + " x Infinity Edge");}
			if (ninjaTabiCount > 0) {System.out.println(ninjaTabiCount + " x Ninja Tabi");}
		}
	
		
		public void printPowerupDescriptions() {
			System.out.println("Kage's Lucky Pick: Makes the Hero more likely to win a game");
			System.out.println("Infinity Edge: Doubles your damage when attacking a Villain");
			System.out.println("Ninja Tabi: Gives the Hero a chance of dodging an attack");
		}
		
		public void printPowerupDescription(int powerup) {
			if (powerup == 1) {System.out.println("Kage's Lucky Pick: Makes the Hero more likely to win a game");}
			else if (powerup == 2) {System.out.println("Infinity Edge: Doubles your damage when attacking a Villain");}
			else if (powerup == 3) {System.out.println("Ninja Tabi: Gives the Hero a chance of dodging an attack");}
		}
		
		public void printHealingInventory(ArrayList<Integer> healingItems) {
			int minorCount = 0; int middleCount = 0; int majorCount = 0;
			for (int healingItem : healingItems) {
				if (healingItem == 1) {minorCount++;}
				else if (healingItem == 2) {middleCount++;}
				else if (healingItem == 3) {majorCount++;}
			}
			if (minorCount > 0) {System.out.println(minorCount + " x Potion of Minor Healing");}
			if (middleCount > 0) {System.out.println(middleCount + " x Potion of Healing");}
			if (majorCount > 0) {System.out.println(majorCount + " x Potion of Major Healing");}
		}
		
		public void printHealingDescriptions() {
			System.out.println("Potion of Minor healing: Heals 25% of health over 5 seconds");
			System.out.println("Potion of Medium Healing: Heals 50% of health over 7 seconds");
			System.out.println("Potion of Major Healing: Heals 100% of health over 10 seconds");
		}
		
		public void printHealingDescription(int healingItem) {
			if (healingItem == 1) {System.out.println("Potion of Minor healing: Heals 25% of health");}
			else if (healingItem == 2) {System.out.println("Potion of Medium Healing: Heals 50% of health");}
			else if (healingItem == 3) {System.out.println("Potion of Major Healing: Heals 100% of health");}
		}
		
		
		public void printInventory(Team team) {
			System.out.println("Your team's (" + team.getName() + ") inventory contains:");
			System.out.println("Powerups:");
			printPowerupInventory(team.getPowerupList());
			System.out.println("--------\nHealing Potions:");
			printHealingInventory(team.getHealingList());
			System.out.println("--------\nMoney: $" + team.getMoney());
			
		}
		
		
		public void printHeroTypes() {
				Hero[] heroes = {new ADCarryHero("AD Carry"), new CEOHero("CEO"), new DiscountShopper("Discount Shopper"), new LootHoarderHero("Loot Hoarder"),
						new LuckyHero("Lucky Charm"), new RandomHero("Random Hero", false), new SecondWind("Second Wind Hero"), new TankHero("Tank"),
						new TeamPowerupHero("Team Powerup Hero"), new Hero("Vanilla Hero")};
				ArrayList<Hero> heroList = new ArrayList<Hero>(Arrays.asList(heroes));
				Team team = new Team("Attributes Print", heroList);
				System.out.println(team);
				} 
		
		/* public static void main(String[] args) {
			Output output = new Output();
			output.printHeroTypes(); */
//			Hero hero = new Hero("Jim");
//			ArrayList<Hero> heroes = new ArrayList<Hero>();
//			heroes.add(hero);
//			Team team = new Team("Hannah's Bros", heroes);
//			team.addHealing(2); team.addHealing(1); team.addHealing(1); team.addHealing(3);
//			team.addPowerup(1); team.addPowerup(3); team.addPowerup(3);
//			output.printInventory(team);
//			team.changeMoney(300);
//			output.printInventory(team);
		}
	


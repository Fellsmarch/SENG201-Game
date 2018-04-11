import java.util.ArrayList;
import java.util.Scanner;

public class GameEnvironment
	{
		private Team team;
		private ArrayList<Villain> villains;
		private ArrayList<City> cities = new ArrayList<City>();
		private City currentCity;
		private int currentCityIndex = 0; //Could just make current city type int and always call using index
		private Building currentLocation; //Bit dubious about this, should it be type building and not deal with home? Maybe home == null
		private ArrayList<Map> mapList;
		private ArrayList<Building> buildings = new ArrayList<Building>();
		
		public void createGame(int numOfCities, String teamName, ArrayList<Hero> heroes) {
			Shop shop = new Shop();
			buildings.add(shop);
			Hospital hospital = new Hospital();
			buildings.add(hospital);
			PowerupDen powerupDen = new PowerupDen();
			buildings.add(powerupDen);
			VillainsLair villainsLair = new VillainsLair();
			buildings.add(villainsLair);
			
			generateCities(numOfCities);
			currentCity = cities.get(currentCityIndex);
			
			team = new Team(teamName, heroes);
			
			generateMaps(numOfCities);
			generateVillains(numOfCities);
		}
		
		public void generateCities(int numOfCities) {
			for (int i = 1; i <= numOfCities; i++) {
				City newCity = new City(buildings);
				cities.add(newCity);
			}
		}
		
		public void generateMaps(int numOfMaps) {

		}
		
		private void displayHeroStats() {
			//				String[] options = {;"Tank:\n     Health: 500\n     Attack:20\nect", "Healer", "Vinnie"}; //Could make this into a format for hero : herotypes herostring = Health: {0} (hero.gethealth()
			//5 Spaces   ^^^^^
		}
		
		public void generateVillains(int numOfVillains) {
			
		}
		
		public int getValidInputNum(int acceptedRange) {
			//Prior to this, options have been printed and prompt to user is printed
			Scanner scanner = new Scanner(System.in);
			int toReturn = 0; //Have to instantiate to something since method has to return an int
			boolean inputGood = false;
			//while (!inputGood) {
//				if(scanner.hasNextInt()) {
//					int userChoice = scanner.nextInt();
//					if (userChoice > 0 && userChoice <= acceptedRange) {
//						toReturn = userChoice;
//						inputGood = true;
//					}else {
//						System.out.println("Input was not one of the options, please try again.");
//					}
//				}else {
//					System.out.println("Input was not an integer, please try again.");
//					scanner.reset();
//				}
				if(scanner.hasNext()) {
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
						scanner.next();
					}
				}
			//}
			scanner.close();
			return toReturn; //This is only done this way since the method needs to return an int
		}
		
		
		
		public int printOptions(String[] options) {
			int optionNum = 1;
			for (String option : options) {
				System.out.println("(" + optionNum + "): " + option);
				optionNum++;
			}
			System.out.println("Please enter your choice:");
			return getValidInputNum(optionNum);
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
			scanner.close();
			return toReturn;
		}
		
		private ArrayList<Hero> createTeam(ArrayList<String> heroNames) {
			//Get the hero types and add them to the team --> Could make this into a method to clean it up	
			String[] heroTypes = {"AD Carry", "CEO", "Discount Shopper", "Loot Hoarder", "Lucky Charm", "Random", "Second Wind", "Tank", "Team Powerup", "Vanilla", "Display attributes"};
			ArrayList<Hero> heroes = new ArrayList<Hero>();
			for(String heroName : heroNames) {
				boolean heroTypeChosen = false;
				while(!heroTypeChosen) {
					System.out.println("What type of hero would you like " + heroName + " to be?");
					int heroType = printOptions(heroTypes);
					if(heroType == 11) {displayHeroStats();}
					else {
						switch (heroType) {
							case 1: ADCarryHero newADCarry = new ADCarryHero(heroName);
									heroes.add(newADCarry);
									heroTypeChosen = true;
									break;
							case 2: CEOHero newCEO = new CEOHero(heroName);
									heroes.add(newCEO);
									heroTypeChosen = true;
									break;
							case 3: DiscountShopper newDiscountShopper = new DiscountShopper(heroName);
									heroes.add(newDiscountShopper);
									heroTypeChosen = true;
									break;
							case 4: LootHoarderHero newLootHoarder = new LootHoarderHero(heroName);
									heroes.add(newLootHoarder);
									heroTypeChosen = true;
									break;
							case 5: LuckyHero newLucky = new LuckyHero(heroName);
									heroes.add(newLucky);
									heroTypeChosen = true;
									break;
							case 6: RandomHero newRandom = new RandomHero(heroName);
									heroes.add(newRandom);
									heroTypeChosen = true;
									break;
							case 7: SecondWind newSecondWind = new SecondWind(heroName);
									heroes.add(newSecondWind);
									heroTypeChosen = true;
									break;
							case 8: TankHero newTank = new TankHero(heroName);
									heroes.add(newTank);
									heroTypeChosen = true;
									break;
							case 9: TeamPowerupHero newTeamPowerup = new TeamPowerupHero(heroName);
									heroes.add(newTeamPowerup);
									heroTypeChosen = true;
									break;
							case 10: Hero newVanillaHero = new Hero(heroName);
									 heroes.add(newVanillaHero);
									 heroTypeChosen = true;
									 break;
						}
					}
				}
			}
			return heroes;
		}
		
		

		public static void main(String[] args)
			{
				GameEnvironment game = new GameEnvironment();
				
				System.out.println("What should the super hero team name be?");
				String teamName = game.getValidInputStr(2, 10);
				
				System.out.println("How many cities do you want to explore?");
				String[] cityOptions = {"Three cities", "Four cities", "Five cities", "Six cities"};
				int numOfCities = game.printOptions(cityOptions);
				
				System.out.println("How many heroes will be on the team?:");
				String[] numHeroOptions = {"One hero", "Two heroes", "Three heroes"};
				int numOfHeroes = game.printOptions(numHeroOptions);
				
				
				//Create get the hero names
				ArrayList<String> heroNames = new ArrayList<String>(); //For checking that hero names are not duplicates
				for(int i = 0; i < numOfHeroes; i++) {
					boolean heroNameUnique = true;
					System.out.println("What is the name of this hero?:");
					while(heroNameUnique) {
						String heroName = game.getValidInputStr(1, 1000000);
						if(heroNames.contains(heroName)) {
							heroNameUnique = false;
							System.out.println("Sorry, that name is already taken, please try again.");
						}else {
							heroNames.add(heroName);
							heroNameUnique = true;
						}
					}
				}	
				ArrayList <Hero> heroes = game.createTeam(heroNames);
				game.createGame(numOfCities, teamName, heroes);
				
				

			}

	}

package commandLineElements;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import characters.*;
import characters.HeroTypes.*;
import items.Map;

public class GameEnvironment
	{
		private Team team;
		private ArrayList<Villain> villains = new ArrayList<Villain>();
		private SuperVillain superVillain; //Needs to be added to UML
		private ArrayList<City> cities = new ArrayList<City>();
		//private City currentCity;
		//private int currentCityIndex = 0; //Could just make current city type int and always call using index --> NEED A WAY TO CHECK THAT THIS DOESN'T GO OVER THE MAX NUMBER OF CITIES
		//private Building currentLocation; //Bit dubious about this, should it be type building and not deal with home? Maybe home == null
		private ArrayList<Map> mapList = new ArrayList<Map>();;
		private ArrayList<Building> buildings = new ArrayList<Building>();
		private Output output = new Output();
		
		public void createGame(int numOfCities, String teamName, ArrayList<Hero> heroes) {
			Shop shop = new Shop();
			buildings.add(shop);
			Hospital hospital = new Hospital();
			buildings.add(hospital);
			PowerupDen powerupDen = new PowerupDen();
			buildings.add(powerupDen);
			VillainsLair villainsLair = new VillainsLair();
			buildings.add(villainsLair);
			
			generateVillains(numOfCities);
			generateCities(numOfCities);

			team = new Team(teamName, heroes);
			
			generateMaps();
			
		}
		
		public boolean runGame(){ //Returns true if the game is won
			Random rand = new Random();
			int cityNum = -1;
			boolean locChanged = false;
			for(City currentCity : cities) { //Could iterate through using i = 0 method so that on the last city we can print something
				cityNum++;
				String[] options = {"Go north", "Go east", "Go south", "Go west", "Show team stats", "Use map"};
				boolean cityCompleted = false;
				while(!cityCompleted) {
					if (locChanged && rand.nextInt(8) == 0) {
						if (rand.nextDouble() <= team.getEventChance()) {
							System.out.println("A mysterious stranger comes out of the darkness and gives you a gift!");								
							int itemType = rand.nextInt(3);
							ArrayList<Integer> possibleMaps = new ArrayList<Integer>();
							for (int map = cityNum; map < cities.size(); map++) {if (!team.hasMap(map)) {possibleMaps.add(map);}}
							if (possibleMaps.size() > 0 && itemType == 2) {
								int map = rand.nextInt(possibleMaps.size());
								if (map == cityNum) {System.out.println("You recieved a map for this city!");}
								else {System.out.println("You recieved a map for city #" + map + "!");}
								//Broken after GUI changes to team
//								team.addMap(map);
							} 
							else {
								int item = rand.nextInt(6);
								switch (item) {
								case 0: //team.addPowerup(powerup 1)
										break;
								case 1: //team.addPowerup(powerup 2)
										break;
								case 2: //team.addPowerup(powerup 3)
										break;
								case 3: //team.addHealing(minor)
										break;
								case 4: //team.addHealing(normal)
										break;
								case 5: //team.addHealing(major)
										break;
								}
							}
						}
						else {
							boolean teamHadCityMap = team.hasMap(cityNum);
							ArrayList<ArrayList<?>> removalCandidates = new ArrayList<ArrayList<?>>();
							if (team.getPowerupList().size() > 0) {removalCandidates.add(team.getPowerupList());}
							if (team.getHealingList().size() > 0) {removalCandidates.add(team.getPowerupList());}
							ArrayList<Integer> mapsToAdd = new ArrayList<Integer>();
							for (int map = cityNum; map < cities.size(); map++) {if (team.hasMap(map)) {mapsToAdd.add(map);}}
							if (removalCandidates.size() > 0) {
								System.out.println("Urf comes out of the darkness and slaps you with his spatula!");
								int itemType = rand.nextInt(removalCandidates.size());
								int item = rand.nextInt(removalCandidates.get(itemType).size());
								removalCandidates.get(itemType).remove(item);
								if (!team.hasMap(cityNum) && teamHadCityMap) {
									//Broken after GUI changes to team
//									team.removeMap(cityNum);
									System.out.println("Urf stole the map to this city!");
									options[0] = "Go north"; options[1] = "Go east"; options[2] = "Go south"; options[3] = "Go west";
								}
								else{
									System.out.println("One of your items is missing!");
								}
							}
						}
					}
					int userChoice = output.printOptions(options);
					switch (userChoice) {
						case 1: cityCompleted = currentCity.goNorth(team);
								locChanged = true;
								break;
						case 2: cityCompleted = currentCity.goEast(team); //if(currentCity.getDirectons()[1] instanceof VillainsLair]
								locChanged = true;
								break;
						case 3: cityCompleted = currentCity.goSouth(team);
								locChanged = true;
								break;
						case 4: cityCompleted = currentCity.goWest(team);
								locChanged = true;
								break;
						case 5: System.out.println(team);
								locChanged = false;
								break;
						case 6: if (true) {//team.hasMap(cityNum)) {
									String[] directions = mapList.get(cityNum).UseMap();
									options[0] = options[0] + " (" + directions[0] + ")";
									options[1] = options[1] + " (" + directions[1] + ")";
									options[2] = options[2] + " (" + directions[2] + ")";
									options[3] = options[3] + " (" + directions[3] + ")";
								}else {System.out.println("You don't have a map for this city!");}
								locChanged = false;
								break;
					}
				}
			}
			return true;
		}
		
		public void generateCities(int numOfCities) {
			for (int i = 1; i < numOfCities; i++) {
				cities.add(new City(buildings, villains.get(i)));
			}
			cities.add(new City(buildings, superVillain));
		}
		
		public void generateMaps() {
			for(City city : cities) {
				Map newMap = new Map(city);
				mapList.add(newMap);
			}
		}
		
		public void generateVillains(int numOfVillains) {
			ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(new PaperScissorsRockGame(), new GuessNumberGame(), new DiceRollsGame()));
			ArrayList<String> namesList = new ArrayList<String>(Arrays.asList("Cho'Gath - The Terror of the Void", "Kassadin - The Void Walker", "Baron Nashor", 
					"Kog'Maw - The Mouth of the Abyss", "Kha'Zix - The Voidreaver", "Malzahar - The Prophet of the Void", "Rek'Sai - The Void Burrower", 
					"Vel'Koz - The Eye of the Void"));
			ArrayList<String> tauntsList = new ArrayList<String>(Arrays.asList("Your souls will feed the Void!", "The balance of power must be preserved.", 
					"Time to feast! ", "Kill. Consume. Adapt.", "We are timeless. We demand Sacrifice.", "Creature appears to seek its maternal unit.",
					"Only by deconstruction is truth revealed."));
			Random rand = new Random();
			
			for(int i = 0; i < numOfVillains; i++) {
				int nameIndex = rand.nextInt(namesList.size());
				int tauntIndex = rand.nextInt(tauntsList.size());
				String name = namesList.get(nameIndex);
				String taunt = tauntsList.get(tauntIndex);
				if (name == "Baron Nashor") {taunt = "...";}
				Villain newVillain = new Villain(name, taunt, gameList);
				villains.add(newVillain);
				namesList.remove(nameIndex); 
				if (name != "Baron Nashor") {tauntsList.remove(tauntIndex);}
			} //System.out.println(villains.size());
			
			//Creates the super villain
			int nameIndex = rand.nextInt(namesList.size());
			int tauntIndex = rand.nextInt(tauntsList.size());
			String name = namesList.get(nameIndex); String taunt = tauntsList.get(tauntIndex);
			SuperVillain superVill = new SuperVillain(name, taunt, gameList);
			superVillain = superVill;
		}
		
		
		private ArrayList<Hero> createTeam(ArrayList<String> heroNames) {
			//Get the hero types and add them to the team --> Could make this into a method to clean it up	
			String[] heroTypes = {"AD Carry", "CEO", "Discount Shopper", "Loot Hoarder", "Lucky Charm", "Random", "Second Wind", "Tank", "Team Powerup", "Vanilla", "Display attributes"};
			ArrayList<Hero> heroes = new ArrayList<Hero>();
			for(String heroName : heroNames) {
				boolean heroTypeChosen = false;
				while(!heroTypeChosen) {
					System.out.println("What type of hero would you like " + heroName + " to be?");
					//Output output = new Output();
					int heroType = output.printOptions(heroTypes);
					//if(heroType == 11) {displayHeroStats();}
					//else {
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
									//Broken after GUI changes to team
//							case 11: output.printHeroTypes();
//									 break;
						}
					//}
				}
			}
			return heroes;
		}
		
		

		public static void main(String[] args)
			{
				Output output = new Output();
				GameEnvironment game = new GameEnvironment();
				
				System.out.println("What should the super hero team name be?");
				String teamName = output.getValidInputStr(2, 10);
				
				System.out.println("How many cities do you want to explore?");
				String[] cityOptions = {"Three cities", "Four cities", "Five cities", "Six cities"};
				int numOfCities = output.printOptions(cityOptions) + 2;
				
				System.out.println("How many heroes will be on the team?:");
				String[] numHeroOptions = {"One hero", "Two heroes", "Three heroes"};
				int numOfHeroes = output.printOptions(numHeroOptions);
				
				
				//Create get the hero names
				ArrayList<String> heroNames = new ArrayList<String>(); //For checking that hero names are not duplicates
				String[] englishHeroNum = {" first ", " second ", " third "};
				for(int i = 0; i < numOfHeroes; i++) {
					boolean heroNameUnique = false;
					System.out.println("What is the name of the" + englishHeroNum[i] + "hero?:");
					while(!heroNameUnique) {
						String heroName = output.getValidInputStr(1, 1000000);
						if(heroNames.contains(heroName)) {
							System.out.println("Sorry, that name is already taken, please try again.");
						}else {
							heroNames.add(heroName);
							heroNameUnique = true;
						}
					}
				}	
				ArrayList <Hero> heroes = game.createTeam(heroNames);
				game.createGame(numOfCities, teamName, heroes);
						
				//game.team.addMap(3); game.team.addMap(1); game.team.addMap(5);
				//System.out.println(game.team.getMapList());
				
				long startTime = System.nanoTime();
				if(game.runGame()) {
					long endTime = System.nanoTime();
					long finalTime = endTime - startTime; finalTime /= 1000000000;
					System.out.println("Congratulations on defeating " + game.superVillain.getName() + "! You took " + finalTime + " seconds to finish.");
				}
			}
	}

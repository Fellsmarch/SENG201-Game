/**
 * This class creates the team for the game, is stores the team in an ArrayList (and their inventory)
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Team
	{
		private ArrayList<Hero> heroes = new ArrayList<Hero>();									//The list of heroes in the team
		private ArrayList<Integer> powerups = new ArrayList<Integer>();							//The list of powerups the team has
		private ArrayList<Integer> healingItems = new ArrayList<Integer>();						//The list of healing items the team has
		private ArrayList<Boolean> maps = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false, false));
		//private ArrayList<Boolean> maps = new ArrayList<Boolean>();								//The list of maps the team has
		private int money = 100;																//The amount of money the team has/starts with
		private String name;																	//The teams name (chosen by the user -- 2-10 chars)
		private double goodEventChance = 0.5;													//The chance of getting a good event when an event triggers
		
		public Team(String newName, ArrayList<Hero> startingHeroes) {							//User chooses their heroes before we make the team
			name = newName;
			for (Hero hero : startingHeroes) { 													//Iterate through the list of heroes
				if (hero instanceof CEOHero) money = 500; 										//If a hero is a CEO, set the starting money to 500
				heroes.add(hero);																//Since we're iterating through here, should we maybe add this also for event chance?
																								//And any others that aren't edited by more than one hero type?
			}
			
			
		}
		
		//Getters
		public double getEventChance() {return goodEventChance;}
		public String getName() {return name;}
		public int getMoney() {return money;}
		public ArrayList<Hero> getHeroList(){return heroes;}
		public ArrayList<Integer> getPowerupList(){return powerups;}
		public ArrayList<Integer> getHealingList(){return healingItems;}

		//public ArrayList<Boolean> getMapList(){return maps;}
		public boolean hasMap(int mapNum) {return maps.get(mapNum);}
		
		//Setters
		public void changeMoney(int value) {money += value;}							//Adds or removes money -- Change is not a good word for add or remove
		public void changeEventChance(double newChance) {goodEventChance += newChance;}	//Adds or removes chances of getting a good event
		
		public void addPowerup(int powerup) {powerups.add(powerup);}
		public void removePowerup(int powerup) { //Could be boolean and return true if removal successful
			int index = powerups.indexOf(powerup);
			powerups.remove(index);
		}
		
		public void addMap(int map) {maps.set(map, true);}
		public void removeMap(int map) {maps.set(map, false);}
		
		public void addHealing(int healing) {healingItems.add(healing);}
		public void removeHealing(int healing) { //Could be boolean and return true if removal successful
			int index = healingItems.indexOf(healing);
			healingItems.remove(index);
		}
		
		
		public void addHero(Hero hero) {heroes.add(hero);} //Do we actually need this if the heroes are selected before the team is made
		public void removeHero(Hero hero) { //Could be boolean and return true if removal successful
			heroes.remove(hero);
		}
		
		public String toString() {
			String toReturn = "";
			for (Hero heroToPrint : heroes) {
				toReturn += heroToPrint.toString();
				toReturn += "\n-------------------\n\n";
			}
			return toReturn;
		}
		
//		public static void main(String[] args) { //For testing only
//			CEO ceo = new CEO();
//			Tank tank = new Tank();
//			LuckyHero lucky = new LuckyHero();
//			ArrayList<Hero> team = new ArrayList<Hero>();
//			team.add(ceo); team.add(tank);// team.add(lucky);
//			//System.out.println(team.indexOf(tank));
//			//System.out.println(team.indexOf(lucky));
//			
//			
//			
//			Team sickTeam = new Team("A-Team", team);
//			sickTeam.removeHero(tank);
//			//sickTeam.removeHero(lucky);
//			System.out.println(sickTeam.getMoney());
//			System.out.println(sickTeam.getName());
//		}
	}

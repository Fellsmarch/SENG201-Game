package characters;
/**
 * This class creates the team for the game, is stores the team in an ArrayList (and their inventory)
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

import java.util.ArrayList;
import java.util.Arrays;

import characters.HeroTypes.CEOHero;
import items.*;

public class Team
	{
		private ArrayList<Hero> heroes = new ArrayList<Hero>();									//The list of heroes in the team
		private ArrayList<Item> itemInventory = new ArrayList<Item>();
		private ArrayList<Powerup> powerupsInventory = new ArrayList<Powerup>();							//The list of powerups the team has
		private ArrayList<HealingItem> healingInventory = new ArrayList<HealingItem>();						//The list of healing items the team has
		private ArrayList<Map> maps = new ArrayList<Map>();
		//private ArrayList<Boolean> maps = new ArrayList<Boolean>();								//The list of maps the team has
		private int money = 100;																//The amount of money the team has/starts with
		private String name;																	//The teams name (chosen by the user -- 2-10 chars)
		private double goodEventChance = 0.5;													//The chance of getting a good event when an event triggers
		
		public Team(String name, ArrayList<Hero> startingHeroes, int numCities) {			//User chooses their heroes before we make the team
			this.name = name;
			
			for (Hero hero : startingHeroes) { 													//Iterate through the list of heroes
				if (hero instanceof CEOHero) {money += 500;} 									//If a hero is a CEO, set the starting money to 500
				heroes.add(hero);																//Since we're iterating through here, should we maybe add this also for event chance?
			}
		}
		
		//Getters
		public double getEventChance() {return goodEventChance;}
		public String getName() {return name;}
		public int getMoney() {return money;}
		public ArrayList<Hero> getHeroList(){return heroes;}
		public ArrayList<Item> getItemInventory() {return itemInventory;}
		public ArrayList<Powerup> getPowerupList(){return powerupsInventory;}
		public ArrayList<HealingItem> getHealingList(){return healingInventory;}

//		public boolean hasMap(int mapNum) {return maps.get(mapNum);}
		
		//Setters
		public void adjustGold(int value) {money += value;}							//Adds or removes money -- Change is not a good word for add or remove
		public void changeEventChance(double newChance) {goodEventChance += newChance;}	//Adds or removes chances of getting a good event
		public void addHero(Hero hero) {heroes.add(hero);} //Do we actually need this if the heroes are selected before the team is made
		public void removeHero(Hero hero) { //Could be boolean and return true if removal successful
			heroes.remove(hero);
		}
		
		public void addItem(Item item) {
			itemInventory.add(item);
			if (item instanceof Powerup) {powerupsInventory.add(((Powerup) item)); }
			else if (item instanceof HealingItem) {healingInventory.add(((HealingItem) item));}
			else {maps.add((Map) item);}
		}
		
		public void removeItem(Item item) {
			itemInventory.remove(item);
			if (item instanceof Powerup) {powerupsInventory.remove(item);}
			else if (item instanceof HealingItem) {healingInventory.remove(item);}
			else {maps.remove(item);}
		}
		
		public String[] getHeroNames() {
			String[] toReturn = new String[heroes.size()];
			for (int i = 0; i < heroes.size(); i++) {toReturn[i] = heroes.get(i).getName();}
			return toReturn;
		}
		
		public String toString() {
			String toReturn = "";
			for (Hero heroToPrint : heroes) {
				toReturn += heroToPrint.toString();
				toReturn += "\n-------------------\n\n";
			}
			return toReturn;
		}
	}

package characters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import characters.HeroTypes.CEOHero;
import characters.HeroTypes.DiscountShopper;
import characters.HeroTypes.RandomHero;
import items.*;

/**
 * This class creates the team for the game and stores the team in an ArrayList, as well as their inventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */
public class Team
	{
		/**
		 * An array list of Heros that the user has chosen to play
		 */
		private ArrayList<Hero> heroes = new ArrayList<Hero>();	
		
		/**
		 * An array list of items that the Team currently owns (power ups, healing items, maps)
		 */
		private ArrayList<Item> itemInventory = new ArrayList<Item>();
		
		/**
		 * An array list of solely power ups that the Team currently owns
		 */
		private ArrayList<Powerup> powerupsInventory = new ArrayList<Powerup>();
		
		/**
		 * An array list of solely healing items that the Team currently owns
		 */
		private ArrayList<HealingItem> healingInventory = new ArrayList<HealingItem>();						
		
		/**
		 * An array list of maps that the Team owns
		 */
		private ArrayList<Map> maps = new ArrayList<Map>();
		
		/**
		 * The amount of money "gold" that the Team can spend at the Shop
		 */
		private int gold = 100;			
		
		/**
		 * The Team's name, chosen by the user, (2-10 chars)
		 */
		private String name;			
		
		/**
		 * The good event versus bad event chance multiplier, applicable when a random event is triggered
		 */
		private double goodEventChance = 0.5;		
		
		/**
		 * Loot modifier, how much gold the Team receives after defeating a Villain and whether they get more or less gold
		 */
		private double lootMod = 1;
		
		/**
		 * Shop modifier, how much the shop prices are altered by
		 */
		private double shopMod = 1;
		
		/**
		 * Determines whether the "DiscountShopper" Hero Type is within the Team
		 */
		private boolean containsShopper;
		
		
		/**
		 * Creates a Team and modifies statistic of the team based on the user's choice of Heroes
		 * @param name The Team's name
		 * @param startingHeroes The ArrayList of heroes to be placed into the team
		 * @param numCities User chooses the number of cities to play
		 */
		public Team(String name, ArrayList<Hero> startingHeroes, int numCities) {
			this.name = name;
			
			containsShopper = false;
			for (Hero hero : startingHeroes) { 													//Iterate through the list of heroes
				if (hero instanceof CEOHero) {gold += 500;} 									//If a hero is a CEO, set the starting money to 500
				heroes.add(hero);																//Since we're iterating through here, should we maybe add this also for event chance?
				goodEventChance += hero.getEventChance();
				if (hero.getLootMod() < lootMod) {lootMod = hero.getLootMod();}
				if (!containsShopper && hero instanceof RandomHero) {							//Prioritizes discount shoppers over random hero and random hero over normal heroes (even if the price is higher)
					if (shopMod != 1) {															//Checking to see if there is another random hero changing the price
						if (hero.getShopMod() < shopMod) {shopMod = hero.getShopMod();}			//Prioritizes the lower price of the two random heroes
						else {shopMod = hero.getShopMod();}
					}
				}
				if (hero instanceof DiscountShopper) {
					shopMod = hero.getShopMod();
					containsShopper = true;
				}
			}
			
		}
		
		/**
		 * Gets the name of the Team
		 * @return
		 */
		public String getName() {return name;}
		
		/**
		 * Gets the good event chance for the Team
		 * @return
		 */
		public double getEventChance() {return goodEventChance;}
		
		/**
		 * Gets the shop modifier of the Team
		 * @return
		 */
		public double getShopMod() {return shopMod;}
		
		/**
		 * Gets the loot modifier of the Team
		 * @return
		 */
		public double getLootMod() {return lootMod;}
		
		/**
		 * Gets the gold/money of the Team
		 * @return
		 */
		public int getGold() {return gold;}
		
		/**
		 * Gets the list of Heroes in the Team
		 * @return
		 */
		public ArrayList<Hero> getHeroList(){return heroes;}
		
		/**
		 * Gets the list of items owned by the Team
		 * @return
		 */
		public ArrayList<Item> getItemInventory() {return itemInventory;}
		
		/**
		 * Gets the list of power ups owned by the Team
		 * @return
		 */
		public ArrayList<Powerup> getPowerupList(){return powerupsInventory;}
		
		/**
		 * Gets the list of healing items in the Team
		 * @return
		 */
		public ArrayList<HealingItem> getHealingList(){return healingInventory;}
		
		/**
		 * Gets the list of Heroes in the Team
		 * @return
		 */
		
		/**
		 * Determines whether the team has a certain city's map
		 * @return relevant city map
		 */
		public boolean hasMap(Map map) {return maps.contains(map);}
		
		/**
		 * Gets the list of Hero names
		 * @return An array of Strings of the names of the heroes in the Team
		 */
		public String[] getHeroNames() {
			String[] toReturn = new String[heroes.size()];
			for (int i = 0; i < heroes.size(); i++) {toReturn[i] = heroes.get(i).getName();}
			return toReturn;
		}
		
		/**
		 * Class creates an item comparator to compare two objects(items), regarding sorting alphabetically
		 */
		private class itemComparator implements Comparator<Item>{
			public int compare(Item one, Item two) {
				return (one.toString().compareTo(two.toString()));
			}
		}
		
		/**
		 * Gets the inventory of the Team
		 * @return String displaying that the team's inventory is empty if it is empty, the amount of Gold the team has, the items and the amount of items the team has. Sorts alphabetically
		 */
		public String getInventory() {
			String toReturn = "<html>Gold: " + gold + "<br />Inventory: <br />";
			if (itemInventory.size() < 1) {toReturn += "&ensp - &ensp Empty";}			
			else {
				Set<Item> inventorySet = new TreeSet<Item>(new itemComparator());
				for (Item item : itemInventory) {inventorySet.add(item);}
				for (Item item : inventorySet) {
					int freq = Collections.frequency(itemInventory, item);
					toReturn += "&ensp - &ensp " + item + " (x" + freq + ")<br />";
				}
			}
			return toReturn;
}

		/**
		 * Adjusts the gold that the team owns. Adds or removes gold
		 */
		public void adjustGold(int value) {gold += value;}							
		
		/**
		 * Adds an item to the Team's array list itemInventory, determines what kind of item it is and adds it to that array list as well
		 */
		public void addItem(Item item) {
			itemInventory.add(item);
			if (item instanceof Powerup) {powerupsInventory.add(((Powerup) item)); }
			else if (item instanceof HealingItem) {healingInventory.add(((HealingItem) item));}
			else {maps.add((Map) item);}
		}
		
		/**
		 * Removes an item from the array list itemInventory, determines what kind of item it is and removes it from that array list as well
		 */
		public void removeItem(Item item) {
			itemInventory.remove(item);
			if (item instanceof Powerup) {powerupsInventory.remove(item);}
			else if (item instanceof HealingItem) {healingInventory.remove(item);}
			else {maps.remove(item);}
		}
		
		/**
		 * Determines how the death/removal of a hero from a team affects the stats of the team, and changes those stats back to what they would be without that hero
		 * @return false if there are no heroes left in the Team
		 * @return true if there are still heroes in the Team
		 */
		public boolean killHero(Hero heroToKill) { 
			if (heroToKill.death()) {
				heroes.remove(heroToKill);
				goodEventChance -= heroToKill.getEventChance();
				if (heroToKill instanceof DiscountShopper) {containsShopper = false;}
				if (heroToKill.getLootMod() == lootMod || heroToKill.getShopMod() == shopMod) { 		
					for (Hero hero : heroes) {
						if (hero.getLootMod() < lootMod) {lootMod = hero.getLootMod();}
						if (!containsShopper && hero instanceof RandomHero) {							//Prioritizes discount shoppers over random hero and random hero over normal heroes (even if the price is higher)
							if (shopMod != 1) {															//Checking to see if there is another random hero changing the price
								if (hero.getShopMod() < shopMod) {shopMod = hero.getShopMod();}			//Prioritizes the lower price of the two random heroes
								else {shopMod = hero.getShopMod();}
							}
						}
						if (hero instanceof DiscountShopper) {
							shopMod = hero.getShopMod();
							containsShopper = true;
						}
					}
				}
				if (heroes.size() < 1) {return false;}
			}
			return true;
		}
	
		/**
		 * Prints out the array list of heroes
		 * @return a String representaton of the Team's heroes
		 */
		public String toString() {
			String toReturn = "";
			for (Hero heroToPrint : heroes) {
				toReturn += heroToPrint.toString();
				toReturn += "\n-------------------\n\n";
			}
			return toReturn;
		}
	}

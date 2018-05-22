package characters;
/**
 * This class creates the team for the game, is stores the team in an ArrayList (and their inventory)
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import characters.HeroTypes.CEOHero;
import characters.HeroTypes.DiscountShopper;
import characters.HeroTypes.RandomHero;
import items.*;

public class Team
	{
		private ArrayList<Hero> heroes = new ArrayList<Hero>();									//The list of heroes in the team
		private ArrayList<Item> itemInventory = new ArrayList<Item>();
		private ArrayList<Powerup> powerupsInventory = new ArrayList<Powerup>();							//The list of powerups the team has
		private ArrayList<HealingItem> healingInventory = new ArrayList<HealingItem>();						//The list of healing items the team has
		private ArrayList<Map> maps = new ArrayList<Map>();
		private int gold = 100;																	//The amount of money the team has/starts with
		private String name;																	//The teams name (chosen by the user -- 2-10 chars)
		private double goodEventChance = 0.5;													//The chance of getting a good event when an event triggers
		private double lootMod = 1;
		private double shopMod = 1;
		private boolean containsShopper;
		
		public Team(String name, ArrayList<Hero> startingHeroes, int numCities) {				//User chooses their heroes before we make the team
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
		
		//Getters
		public String getName() {return name;}
		public double getEventChance() {return goodEventChance;}
		public double getShopMod() {return shopMod;}
		public double getLootMod() {return lootMod;}
		public int getGold() {return gold;}
		public ArrayList<Hero> getHeroList(){return heroes;}
		public ArrayList<Item> getItemInventory() {return itemInventory;}
		public ArrayList<Powerup> getPowerupList(){return powerupsInventory;}
		public ArrayList<HealingItem> getHealingList(){return healingInventory;}
		public boolean hasMap(Map map) {return maps.contains(map);}
		public String[] getHeroNames() {
			String[] toReturn = new String[heroes.size()];
			for (int i = 0; i < heroes.size(); i++) {toReturn[i] = heroes.get(i).getName();}
			return toReturn;
		}
		public String getInventory() {
			String toReturn = "<html>Gold: " + gold + "<br />Inventory: <br />";
			if (itemInventory.size() < 1) {toReturn += "&ensp - &ensp Empty";}			
			else {
				Set<Item> inventorySet = new HashSet<Item>(itemInventory);
				for (Item item : inventorySet) {
					int freq = Collections.frequency(itemInventory, item);
					toReturn += "&ensp - &ensp " + item + " (x" + freq + ")<br />";
				}
			}
			return toReturn;
		}

		//Setters
		public void adjustGold(int value) {gold += value;}							//Adds or removes money -- Change is not a good word for add or remove
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

		public boolean killHero(Hero heroToKill) { //Returns true if there are still heroes in the team
			if (heroToKill.death()) {
				heroes.remove(heroToKill);
				goodEventChance -= heroToKill.getEventChance();
				if (heroToKill instanceof DiscountShopper) {containsShopper = false;}
				if (heroToKill.getLootMod() == lootMod || heroToKill.getShopMod() == shopMod) { 		//If this hero effected either lootMod or shopMod
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
	
		
		public String toString() {
			String toReturn = "";
			for (Hero heroToPrint : heroes) {
				toReturn += heroToPrint.toString();
				toReturn += "\n-------------------\n\n";
			}
			return toReturn;
		}
	}

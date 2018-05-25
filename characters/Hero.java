package characters;

import items.*;

/**
 * This class creates the class 'Hero', an object that stores all the relevant variables and methods that a player hero would possess
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */


public class Hero
	{
		/**
		 * The Hero's name
		 */
		protected String name; 						
		
		/**
		 * The Hero's maximum health
		 */
		protected int maxHealth = 100; 				
		
		/**
		 * The Hero's current health
		 */
		protected int currentHealth;
		
		/**
		 * The Hero's health recovery rate (in seconds), relevant for the time it takes to consume a healing item
		 */
		protected int recoveryRate = 5; 			
		
		/**
		 * The Hero's attack modifier (multiplies base damage by this)
		 */
		protected double attackMod = 1; 			
		
		/**
		 * The Hero's defense modifier (multiplies incoming damage by this) lower is better for less damage taken
		 */
		protected double defenseMod = 1; 			
		
		/**
		 * The Hero's shop price modifier (multiplies shop prices by this)
		 */
		protected double shopMod = 1; 				 
		
		/**
		 * Adds this number to the chance of getting a good event (versus a bad event) when an event is triggered
		 */
		protected double eventChance = 0; 	
		
		/**
		 * Multiplies all loot for the team by this number
		 */
		protected double lootMod = 1; 				
		
		/**
		 * Whether or not the Double Damage powerup is active on this hero
		 */
		private boolean powerupDamage = false; 		
		
		/**
		 * Whether or not the Change to Dodge powerup is active on this hero
		 */
		private boolean powerupDodge = false; 		
		
		/**
		 * Whether or not the Luck powerup is active on this hero
		 */
		private boolean powerupLuck = false; 		
		
		/**
		 * The Hero's type
		 */
		protected String heroType = "Wuju Bladesman";

		/**
		 * Constructor
		 * @param name the hero's name
		 */
		public Hero(String name) {
			this.name = name;
			currentHealth = maxHealth;
		}
		
		/**
		 * Constructor 
		 * @param name The hero's name
		 * @param maxHealth The hero's maximum health
		 */
		public Hero(String name, int maxHealth) {
			this.name = name;
			this.maxHealth = maxHealth;
			currentHealth = maxHealth;
		}
		
		
		/** 
	     * @return the name of the Hero
	     */
		public String getName() {return name;}
		
		/**
	     * @return the current health of the Hero
	     */
		public int getCurrentHealth() {return currentHealth;}
		
		/**
	     * @return the maximum health of the Hero
	     */
		public int getMaxHealth() {return maxHealth;}
		
		/**
	     * @return the recovery rate of the Hero
	     */
		public int getRecovery() {return recoveryRate;}
		
		/**
	     * @return the attack modifier of the Hero
	     */
		public double getAttackMod() {return attackMod;}
		
		/** 
	     * @return  the defense modifier of the Hero
	     */
		public double getDefenseMod() {return defenseMod;}
		
		/** 
	     * @return the shop price modifier of the Hero
	     */
		public double getShopMod() {return shopMod;}
		
		/**
	     * @return the good event chance modifier of the Hero
	     */
		public double getEventChance() {return eventChance;}
		
		/**
	     * @return the loot modifier of the Hero
	     */
		public double getLootMod() {return lootMod;}
		
		/** 
	     * @return an array of whether the 3 powerups are active (true) or not (false)
	     */
		public boolean[] getPowerups() { 
			boolean[] powerups = {powerupDamage, powerupDodge, powerupLuck};
			return powerups;
		}
		
		/**
		 * @param powerup The powerup to check
		 * @return whether the given powerup is active on this Hero
		 */
		public boolean powerupActive(Powerup powerup) {
			if (powerup instanceof PowerupLuck) {return powerupLuck;} 					
			else if (powerup instanceof PowerupDamage) {return powerupDamage;}
			else {return powerupDodge;}
		}
		
		/**
		 * Adds or removes health accordingly
		 * @param toAdjust The amount to adjust the health by
		 */
		public void adjustHealth(int toAdjust) {currentHealth += toAdjust; if (currentHealth > maxHealth) {currentHealth = maxHealth;}}	//This works for positive or negative health adjust
		
		/**
		 * Sets a given powerup
		 * @param powerup The powerup to set
		 * @param active The boolean to set the powerup to
		 */
		public void setPowerup(Powerup powerup, boolean active) { 
			if (powerup instanceof PowerupLuck) {powerupLuck = active;} 					
			if (powerup instanceof PowerupDamage) {powerupDamage = active;}
			if (powerup instanceof PowerupDodge) {powerupDodge = active;}
		}
		
		/**
		 * Returns true if death operations are complete and the hero is dead. 
		 * @return true if the hero has died
		 */
		public boolean death() {return true;}
		
		
		/**
		 * Gets all the Hero's attributes to display as a String
		 * @return A string that represents all of the Hero's stats/attributes
		 */
		public String toString() {
			String toReturn = "<html><b>" + name + "</b>" + ":<br />Health:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + currentHealth + "/" + maxHealth + "<br />Recovery Rate:&emsp;&emsp;" + recoveryRate
					+ " sec<br />Attack Strength:&emsp;&nbsp;" + attackMod * 100 + "%<br />Defense Modifier: &ensp;" + defenseMod * 100
					+ "%<br />Shop Price:&emsp;&emsp;&emsp;&ensp;&nbsp;" + shopMod * 100 + "%<br />Loot Modifier:&emsp;&emsp;&ensp;" + lootMod * 100 + "%<br />" + heroType;
			return toReturn;
		}
		
		/**
		 * Uses the toString() method above, but adds on the Hero's description
		 * @param userHero whether or not this is a hero for the user
		 * @return A string that represents all of the "Vanilla" Hero's attributes
		 */
		public String toString(boolean userHero) {
			String toReturn = toString() + " - The default vanilla hero";
			return toReturn;
		}

	}

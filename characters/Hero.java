package characters;

import items.*;

/**
 * This class creates the superclass 'Hero', an object that stores all the relevant variables and methods that a player hero would possess
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */


public class Hero
	{
		//Change any protected to private if the sub class does not need them to be protected
		//Should we change protected to private and call super.thing instead in the subclasses
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
		 * 
		 */
		protected String heroType = "Wuju Bladesman";

		/**
		 * Constructor
		 */
		public Hero(String name) {
			this.name = name;
			currentHealth = maxHealth;
		}
		
		/**
		 * Constructor 
		 */
		public Hero(String name, int maxHealth) {
			this.name = name;
			this.maxHealth = maxHealth;
			currentHealth = maxHealth;
		}
		
		
		/** Get the name of the Hero
	     * @return
	     */
		public String getName() {return name;}
		
		/** Get the current health of the Hero
	     * @return
	     */
		public int getCurrentHealth() {return currentHealth;}
		
		/** Get the maximum health of the Hero
	     * @return
	     */
		public int getMaxHealth() {return maxHealth;}
		
		/** Get the recovery rate of the Hero
	     * @return
	     */
		public int getRecovery() {return recoveryRate;}
		
		/** Get the attack modifier of the Hero
	     * @return
	     */
		public double getAttackMod() {return attackMod;}
		
		/** Get the defense modifier of the Hero
	     * @return
	     */
		public double getDefenseMod() {return defenseMod;}
		
		/** Get the shop price modifier of the Hero
	     * @return
	     */
		public double getShopMod() {return shopMod;}
		
		/** Get the good event chance modifier of the Hero
	     * @return
	     */
		public double getEventChance() {return eventChance;}
		
		/** Get the loot modifier of the Hero
	     * @return
	     */
		public double getLootMod() {return lootMod;}
		
		/** Gets an array of whether the 3 powerups are active (true) or not (false)
	     * @return
	     */
		public boolean[] getPowerups() { 
			boolean[] powerups = {powerupDamage, powerupDodge, powerupLuck};
			return powerups;
		}
		
		/**
		 * Gets the active Power Up
		 * @return 
		 */
		public boolean powerupActive(Powerup powerup) {
			if (powerup instanceof PowerupLuck) {return powerupLuck;} 					
			else if (powerup instanceof PowerupDamage) {return powerupDamage;}
			else {return powerupDodge;}
		}
		
		/**
		 * Sets the Hero's name
		 */
		public void setName(String newName) {name = newName;}
		
		/**
		 * Sets the Hero's health accordingly
		 */
		public void adjustHealth(int toAdjust) {currentHealth += toAdjust; if (currentHealth > maxHealth) {currentHealth = maxHealth;}}	//This works for positive or negative health adjust
		
		/**
		 * Sets the Hero's active powerup
		 */
		public void setPowerup(Powerup powerup, boolean active) { 
			if (powerup instanceof PowerupLuck) {powerupLuck = active;} 					
			if (powerup instanceof PowerupDamage) {powerupDamage = active;}
			if (powerup instanceof PowerupDodge) {powerupDodge = active;}
		}
		
		/**
		 * Returns true if death operations are complete and the hero is dead. 
		 */
		public boolean death() {return true;}		//Returns true if death operations are complete and hero is dead. This is  
		
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes
		 */
		public String toString() {
			String toReturn = "<html><b>" + name + "</b>" + ":<br />Health:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + currentHealth + "/" + maxHealth + "<br />Recovery Rate:&emsp;&emsp;" + recoveryRate
					+ " sec<br />Attack Strength:&emsp;&nbsp;" + attackMod * 100 + "%<br />Defense Modifier: &ensp;" + defenseMod * 100
					+ "%<br />Shop Price:&emsp;&emsp;&emsp;&ensp;&nbsp;" + shopMod * 100 + "%<br />Loot Modifier:&emsp;&emsp;&ensp;" + lootMod * 100 + "%<br />" + heroType;
			return toReturn;
		}
		
		/**
		 * Uses the toString() method above, but with the default attributes
		 * @return A string that represents all of the "Vanilla" Hero's attributes
		 */
		public String toString(boolean userHero) {
			String toReturn = toString() + " - The default vanilla hero";
			return toReturn;
		}

	}

package characters;

import items.*;

/**
 * 
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */


public class Hero
	{
		//These should all have this before them later: 
		
		/**
		 * The Hero's name
		 */
		//Change any protected to private if the sub class does not need them to be protected
		//Should we change protected to private and call super.thing instead in the subclasses
		protected String name; 						//The Hero's name
		protected int maxHealth = 100; 				//The Hero's health
		protected int currentHealth;
		protected int recoveryRate = 5; 			//The Hero's health recovery rate (in seconds) -- Not what we thought it was, each healing item has a set time it takes to apply
		protected double attackMod = 1; 			//The Hero's attack modifier (multiplies base damage by this)
		protected double defenseMod = 1; 			//The Hero's defense modifier (multiplies incoming damage by this) lower is better
		protected double shopMod = 1; 				//The Hero's shop price modifier (multiplies shop prices by this) 
		protected double eventChance = 0; 			//Adds this number to the chance of getting a good event (vs a bad event) when an event is triggered
		protected double lootMod = 1; 				//Multiplies all loot for the team by this number
		private boolean powerupDamage = false; 		//Whether or not the Double Damage powerup is active on this hero
		private boolean powerupDodge = false; 		//Whether or not the Change to Dodge powerup is active on this hero
		private boolean powerupLuck = false; 		//Whether or not the Luck powerup is active on this hero
		protected String heroType = "Wuju Bladesman";

		
		public Hero(String name) {
			this.name = name;
			currentHealth = maxHealth;
		}
		
		public Hero(String name, int maxHealth) {
			this.name = name;
			this.maxHealth = maxHealth;
			currentHealth = maxHealth;
		}
		
		
		//Getters for classes that don't extend Hero
		public String getName() {return name;}
		public int getCurrentHealth() {return currentHealth;}
		public int getMaxHealth() {return maxHealth;}
		public int getRecovery() {return recoveryRate;}
		public double getAttackMod() {return attackMod;}
		public double getDefenseMod() {return defenseMod;}
		public double getShopMod() {return shopMod;}
		public double getEventChance() {return eventChance;}
		public double getLootMod() {return lootMod;}
		public boolean[] getPowerups() { //Returns an array of whether the 3 powerups are active (true) or not (false)
			boolean[] powerups = {powerupDamage, powerupDodge, powerupLuck};
			return powerups;
		}
		public boolean powerupActive(Powerup powerup) {
			if (powerup instanceof PowerupLuck) {return powerupLuck;} 					
			else if (powerup instanceof PowerupDamage) {return powerupDamage;}
			else {return powerupDodge;}
		}
		
		//Setters for classes that don't extend Hero
		public void setName(String newName) {name = newName;}
		public void adjustHealth(int toAdjust) {currentHealth += toAdjust; if (currentHealth > maxHealth) {currentHealth = maxHealth;}}	//This works for positive or negative health adjust
		public void setPowerup(Powerup powerup, boolean active) { 
			if (powerup instanceof PowerupLuck) {powerupLuck = active;} 					
			if (powerup instanceof PowerupDamage) {powerupDamage = active;}
			if (powerup instanceof PowerupDodge) {powerupDodge = active;}
		}
		
		public boolean death() {return true;}		//Returns true if death operations are complete and hero is dead. This is  
		
		//GUI Version
		public String toString() {
			String toReturn = "<html><b>" + name + "</b>" + ":<br />Health:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + currentHealth + "/" + maxHealth + "<br />Recovery Rate:&emsp;&emsp;" + recoveryRate
					+ " sec<br />Attack Strength:&emsp;&nbsp;" + attackMod * 100 + "%<br />Defense Modifier: &ensp;" + defenseMod * 100
					+ "%<br />Shop Price:&emsp;&emsp;&emsp;&ensp;&nbsp;" + shopMod * 100 + "%<br />Loot Modifier:&emsp;&emsp;&ensp;" + lootMod * 100 + "%<br />" + heroType;
			return toReturn;
		}
		
		public String toString(boolean userHero) {
			String toReturn = toString() + " - The default vanilla hero";
			return toReturn;
		}

	}

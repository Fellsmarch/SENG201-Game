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
		protected double recoveryRate = 5; 			//The Hero's health recovery rate (in seconds) -- Not what we thought it was, each healing item has a set time it takes to apply
		protected double attackMod = 1; 			//The Hero's attack modifier (multiplies base damage by this)
		protected double defenseMod = 1; 			//The Hero's defense modifier (multiplies incoming damage by this)
		protected double shopPrice = 1; 			//The Hero's shop price modifier (multiplies shop prices by this) 
		protected double teamPowerupChance = 0; 	//Adds this number to the chance of affecting the whole team instead of just this hero when using a powerup
		protected double eventChance = 0; 			//Adds this number to the chance of getting a good event (vs a bad event) when an event is triggered
		protected double lootMod = 1; 				//Multiplies all loot for the team by this number
		private boolean powerupLuck = false; 		//Whether or not the Luck powerup is active on this hero
		private boolean powerupDamage = false; 		//Whether or not the Double Damage powerup is active on this hero
		private boolean powerupDodge = false; 		//Whether or not the Change to Dodge powerup is active on this hero

		
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
		public double getRecovery() {return recoveryRate;}
		public double getAttackMod() {return attackMod;}
		public double getDefenseMod() {return defenseMod;}
		public double getShopPrice() {return shopPrice;}
		public double getPowerupChance() {return teamPowerupChance;}
		public double getEventChance() {return eventChance;}
		public double getLootMod() {return lootMod;}
		public boolean[] getPowerups() { //Returns an array of whether the 3 powerups are active (true) or not (false)
			boolean[] powerups = {powerupLuck, powerupDamage, powerupDodge};
			return powerups;
		}
		
		//Setters for classes that don't extend Hero
		public void setName(String newName) {name = newName;}
		public void adjustHealth(int toAdjust) {currentHealth += toAdjust; if (currentHealth > maxHealth) {currentHealth = maxHealth;}}	//This works for positive or negative health adjust
		public void setPowerup(Powerup powerup) { 
			if (powerup instanceof PowerupLuck) {powerupLuck = true;} 					
			if (powerup instanceof PowerupDamage) {powerupDamage = true;}
			if (powerup instanceof PowerupDodge) {powerupDodge = true;}
		}
		
		public boolean death() { 		//Returns true if death operations are complete and hero is dead. This is  
			return true;				//mainly used for subclasses who may need to do certain things on death
		}
		
		//Non GUI Version
//		public String toString() {
//			String toReturn = name + ":\nHealth:           " + health + "\nRecovery Rate:    " + recoveryRate
//					+ "\nAttack Strength:  " + attackMod * 100 + "%\nDefense Modifier: " + defenseMod * 100
//					+ "%\nShop Price:       " + shopPrice * 100 + "%\nLoot Modifier:    " + lootMod * 100 + "%\n";
//			return toReturn;
//		}
		
		//GUI Version
		public String toString() {
			String toReturn = "<html><b>" + name + "</b>" + ":<br />Health:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + currentHealth + "/" + maxHealth + "<br />Recovery Rate:&emsp;&emsp;" + recoveryRate
					+ "<br />Attack Strength:&emsp;&nbsp;" + attackMod * 100 + "%<br />Defense Modifier: &ensp;" + defenseMod * 100
					+ "%<br />Shop Price:&emsp;&emsp;&emsp;&ensp;&nbsp;" + shopPrice * 100 + "%<br />Loot Modifier:&emsp;&emsp;&ensp;" + lootMod * 100 + "%<br />";
			return toReturn;
		}
		
		public String toString(boolean userHero) {
//			String toReturn = "<html><b>" + name + "</b>" + ":<br />Health:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;" + currentHealth + "/" + maxHealth + "<br />Recovery Rate:&emsp;&emsp;" + recoveryRate
//					+ "<br />Attack Strength:&emsp;&nbsp;" + attackMod * 100 + "%<br />Defense Modifier: &ensp;" + defenseMod * 100
//					+ "%<br />Shop Price:&emsp;&emsp;&emsp;&ensp;&nbsp;" + shopPrice * 100 + "%<br />Loot Modifier:&emsp;&emsp;&ensp;" + lootMod * 100 + "%<br />The default vanilla hero";
			String toReturn = toString() + "The default vanilla hero";
			return toReturn;
		}

	}

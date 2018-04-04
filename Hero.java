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
		protected String name = "Hero"; 			//The Hero's name
		protected int health = 100; 				//The Hero's health
		protected double recoveryRate = 5; 			//The Hero's health recovery rate (in seconds)
		protected double attackMod = 1; 			//The Hero's attack modifier (multiplies base damage by this)
		protected double defenseMod = 1; 			//The Hero's defense modifier (multiplies incoming damage by this)
		protected double shopPrice = 1; 			//The Hero's shop price modifier (multiplies shop prices by this) 
		protected double teamPowerupChance = 0; 	//Adds this number to the chance of affecting the whole team instead of just this hero when using a powerup
		protected double eventChance = 0; 			//Adds this number to the chance of getting a good event (vs a bad event) when an event is triggered
		protected double lootMod = 1; 				//Multiplies all loot for the team by this number
		protected boolean powerupLuck = false; 		//Whether or not the Luck powerup is active on this hero
		protected boolean powerupDamage = false; 	//Whether or not the Double Damage powerup is active on this hero
		protected boolean powerupDodge = false; 	//Whether or not the Change to Dodge powerup is active on this hero

		
		//public Hero()
		
		
		//Getters for classes that don't extend Hero
		public String getName() {return name;}
		public int getHealth() {return health;}
		public double getRecovery() {return recoveryRate;}
		public double getAttackMod() {return attackMod;}
		public double getDefenseMod() {return defenseMod;}
		public double getShopPrice() {return shopPrice;}
		public double getPowerupChance() {return teamPowerupChance;}
		public double getEventChance() {return eventChance;}
		public double getLootMod() {return lootMod;}
		public boolean[] getPowerups() {
			boolean[] powerups = {powerupLuck, powerupDamage, powerupDodge};
			return powerups;
		}
		
		//Setters for classes that don't extend Hero
		public void setName(String newName) {name = newName;}
		public void changeHealth(int toChange) {health += toChange;}//This works for positive or negative health change
		public boolean setPowerup(int powerup) { 					//Returns true if the powerup was applied successfully, and false if the hero already had the powerup applied
			if (powerup == 0 && powerupLuck == false) {				//Checking to see which powerup it is (Luck = 0, Damage = 1, Dodge = 2)
				powerupLuck = true;
				return true;
			} else if (powerup == 1 && powerupDamage == false) {
				powerupDamage = true;
				return true;
			} else if (powerup == 2 && powerupDodge == false) {
				powerupDodge = true;
				return true;
			}else {
				return false;
			}
			
		}
		
		public boolean death() { 		//Returns true if death operations are complete and hero is dead. This is  
			return true;				//mainly used for subclasses who may need to do certain things on death
		}
		
		public void printAttributes() { 	//For debugging, will later be adapted into a console then GUI output, to be shown at each city and at the beginning of the game
			System.out.println("Name = " + name);
			System.out.println("Health = " + health);
			System.out.println("Recovery = " + recoveryRate);
			System.out.println("Attack = " + attackMod);
			System.out.println("Defense = " + defenseMod);
			System.out.println("Shop price = " + shopPrice);
			System.out.println("Team powerup chance = " + teamPowerupChance);
			System.out.println("Event chance = " + eventChance);
			System.out.println("Loot modifier = " + lootMod);
			System.out.println("Powerup Luck = " + powerupLuck);
			System.out.println("Powerup Damage = " + powerupDamage);
			System.out.println("Powerup Dodge = " + powerupDodge);
			
			
		}
		
		

//		public static void main(String[] args)
//			{
//				// For testing
//				Hero test = new Hero();
//				boolean[] array = test.getPowerups();
//				for (boolean power : array) {
//					System.out.println(power);
//				}
//				//System.out.println(test.getPowerups());
//
//			}

	}

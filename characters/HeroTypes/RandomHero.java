package characters.HeroTypes;

import java.util.Random;

import characters.Hero;

/**
 * This hero type randomises many of a Hero's stats, it randomises:
 * Health 					(between 1-200)
 * Recovery Rate 			(between 0-10 seconds)
 * Shop Prices Modifier 	(between 0.5-1.5)
 * Attack Modifier 			(between 0.1-2)
 * Defense Modifier 		(between 0.1-2)
 * Loot Modifier 			(between 0.5-1.5)
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */
public class RandomHero extends Hero
	{
		/**
		 * Creates a new randomiser
		 */
		private Random randomiser = new Random();
		
		/**
		 * Constructor -- Creates a RandomHero
		 * @param name The name of the Hero
		 */
		public RandomHero(String name) {
			super(name);
			int health = randomiser.nextInt(200) + 1;
			maxHealth = health; currentHealth = health;
			recoveryRate = randomiser.nextInt(10) + 1;
			double newPrice = randomiser.nextInt(11) + 5; shopMod = newPrice / 10; //randomiser.nextDouble() generates a random number between 0.0-1.0, should change these to this
			double newAttack = randomiser.nextInt(20) + 1; attackMod = newAttack / 10;
			double newDefense = randomiser.nextInt(20) + 1; defenseMod = newDefense / 10;
			double newLoot = randomiser.nextInt(11) + 5; lootMod = newLoot / 10;
			heroType = "ARAM";
		}
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes (in this case - "???") 
		 */
		@Override
		public String toString(boolean userHero) {

				return  "<b>" + name + 
						"</b>:<br />Health:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;???/???<br />Recovery Rate:&emsp;&emsp;???<br />Attack Strength:&emsp;&nbsp;???<br />Defense Modifier:&ensp;???<br />Shop Price:&emsp;&emsp;&emsp;&ensp;&nbsp;???<br />Loot Modifier:&emsp;&emsp;&ensp;???<br />??????????????????????</html>";
		}

	}

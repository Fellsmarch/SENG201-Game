package characters.HeroTypes;
import characters.Hero;

/**
 * This hero type deals more damage to villains, but trades that for slightly less health and lower defense. A subclass of the superclass Hero
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */
public class ADCarryHero extends Hero
	{
	
	/** 
	 * Constructor -- Creates an ADCarryHero
	 * @param name The name of the hero
	 */
		public ADCarryHero(String name) {
			super(name, 80);
			recoveryRate = 10;
			attackMod = 1.8;
			defenseMod = 1.5;
			heroType = "Night Hunter";
		}
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes, alongside a description of the Hero's type
		 */
		public String toString(boolean userHero) {
			String toReturn = super.toString() + " - A high damage, low defence hero";
			return toReturn;
		}
	}

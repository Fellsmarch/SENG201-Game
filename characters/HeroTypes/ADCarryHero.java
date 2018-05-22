package characters.HeroTypes;
import characters.Hero;

/**
 * This hero type deals more damage to villains, but trades that for slightly less health and lower defense
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class ADCarryHero extends Hero
	{
		public ADCarryHero(String name) {
			super(name, 80);
			recoveryRate = 10;
			attackMod = 1.8;
			//health = 80;
			defenseMod = 1.5; //Lower is better
			heroType = "Night Hunter";
		}
		
		public String toString(boolean userHero) {
			String toReturn = super.toString() + " - A high damage, low defence hero";
			return toReturn;
		}
	}

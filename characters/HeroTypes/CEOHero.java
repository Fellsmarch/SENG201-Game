package characters.HeroTypes;
import characters.Hero;

/**
 * This hero type increases the starting money a team has, it may be better to find a way to change
 * this from inside the CEO class instead of doing it in the Team class.
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */

public class CEOHero extends Hero
	{
																	
		/** 
		 * Constructor -- Creates a CEOHero
		 * @param name The name of the hero
		 */
		public CEOHero(String name) {
			super(name);
			heroType = "Madman of Zaun";
		}
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes, alongside a description of the Hero's type
		 */
		@Override
		public String toString(boolean userHero) {
			return super.toString() + " - This hero increases the team's starting gold by 400";
		}
	}

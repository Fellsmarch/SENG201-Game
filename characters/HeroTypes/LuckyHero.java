package characters.HeroTypes;
import characters.Hero;

/**
 * This hero type increases, when an event is triggered, that the event is good/positive event
 * instead of a bad/negative one
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class LuckyHero extends Hero
	{
	
	/** 
	 * Constructor -- Creates a CEOHero
	 * @param name The name of the hero
	 */
		public LuckyHero(String name) { 
			super(name);
			eventChance = 0.25;
			heroType = "Card Master";
		}
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes, alongside a description of the Hero's type
		 */
		@Override
		public String toString(boolean userHero) {
			return super.toString() + " - This hero increases the chance of getting free items randomly whilst they are in your team";
		}
	}

package characters.HeroTypes;
import characters.Hero;

/**
 * This hero type increases the amount of money the team receives from defeating villains by 50%
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class LootHoarderHero extends Hero
	{
	
	/** 
	 * Constructor -- Creates a LootHoarderHero
	 * @param name The name of the hero
	 */
		public LootHoarderHero(String name) {
			super(name);
			lootMod = 1.5;
			heroType = "Saltwater Scourge";
		}
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes, alongside a description of the Hero's type
		 */
		@Override
		public String toString(boolean userHero) {
			return super.toString() + " - This hero increases the amount of loot gained by defeating villains by 50% whilst they are in your team";
		}
	}

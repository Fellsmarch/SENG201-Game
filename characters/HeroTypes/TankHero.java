package characters.HeroTypes;
import characters.Hero;

/**
 * This hero type acts as a tank for the team, it has increased health and defense
 * and lower recovery rate but suffers reduced attack damage as a result
 * @author Harrison Cook
 * @author Hannah Regan
 */

public class TankHero extends Hero
	{
	
	/**
	 * Constructor -- Creates a TankHero Hero
	 * @param name The name of the Hero
	 */
		public TankHero(String name) {
			super(name, 200); 
			recoveryRate = 1;
			attackMod = 0.7;
			defenseMod = 0.7;
			heroType = "Armourdillo";
		}
		
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes, alongside a description of the Hero's type
		 */
		@Override
		public String toString(boolean userHero) {
			return super.toString() + " - A high defence, low offence hero";
		}
		

	}

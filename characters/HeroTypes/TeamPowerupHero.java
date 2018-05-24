package characters.HeroTypes;
import characters.Hero;

/**
 * When a powerup is used on this hero type, there is a chance (20%) that it will be applied to each member of the team
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class TeamPowerupHero extends Hero
	{
	
	/**
	 * Constructor -- Creates a TeamPowerupHero Hero
	 * @param name The name of the Hero
	 */
		public TeamPowerupHero(String name) {
			super(name);
			heroType = "Exemplar of Demacia";
		}
		
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes, alongside a description of the Hero's type
		 */
		@Override
		public String toString(boolean userHero) {
			return super.toString() + " - When you use a powerup on this hero it has a chance to apply the powerup to everyone in the team";
		}
		
	}

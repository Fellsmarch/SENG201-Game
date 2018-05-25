package characters.HeroTypes;
import characters.Hero;

/**
 * This hero type allows the hero to die once (and only once), and resets the hero's health to 1
 * NOTE: Should we reset the health to 100 instead? Since the hero will be in the middle of a fight 
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */


public class SecondWind extends Hero
	{	
		/**
		 * A boolean that hold whether or not this Hero has had its death() method called before
		 */
		private boolean hasDied = false;
		
		/**
		 * Constructor -- Creates a SecondWind Hero
		 * @param name The name of the Hero
		 */
		public SecondWind(String name) {
			super(name);
			heroType = "Darkin";
		}
		
		/**
		 * Returns true if the death() method has been called previously, if it hasn't returns false and sets the Hero's health to 1.
		 */
		@Override
		public boolean death() {
			if (hasDied) {
				return true;
			}else {
				hasDied = true;
				this.currentHealth = 1;
				return false;
			}
		}
		
		/**
		 * Gets all the Hero's attributes to display as a printed String
		 * @return A string that represents all of the Hero's stats/attributes, alongside a description of the Hero's type
		 */
		@Override
		public String toString(boolean userHero) {
			return super.toString() + " - If this hero were to die, they stay alive with 1 health. This can only happen once";
		}
	}

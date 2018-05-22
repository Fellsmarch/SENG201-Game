package characters.HeroTypes;
import characters.Hero;

/**
 * This hero type makes buying items from shops 50% cheaper for the team
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class DiscountShopper extends Hero
	{
		public DiscountShopper(String name) {
			super(name);
			shopMod = 0.5;
			heroType = "Storedle";
		}
		
		@Override
		public String toString(boolean userHero) {
			return super.toString() + " - This hero halves the cost of items in the shop whilst they are in your team";
		}
	}

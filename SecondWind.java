/**
 * This hero type allows the hero to die once (and only once), and resets the hero's health to 1
 * NOTE: Should we reset the health to 100 instead? Since the hero will be in the middle of a fight 
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */


public class SecondWind extends Hero
	{
		private boolean hasDied = false;
		
		public SecondWind() {
			this.name = "Second Wind";
		}
		
		public boolean death() {
			if (hasDied) {
				return true;
			}else {
				hasDied = true;
				this.health = 1;
				return false;
			}
		}
	}

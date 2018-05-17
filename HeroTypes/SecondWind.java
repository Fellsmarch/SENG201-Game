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
		
		public SecondWind(String name) {
			super(name);
		}
		
		public boolean death() {
			if (hasDied) {
				return true;
			}else {
				hasDied = true;
				this.currentHealth = 1;
				return false;
			}
		}
		
		public String toString() {
			String old = super.toString();
			return old + "When " + name + " dies, they come back to life with 1 hp. This can only happen once\n";
		}
	}

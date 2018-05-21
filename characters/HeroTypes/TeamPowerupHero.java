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
		public TeamPowerupHero(String name) {
			super(name);
			teamPowerupChance = 0.2;
			heroType = "Exemplar of Demacia";
		}
//		
//		public String toString() {
//			String old = super.toString();
//			return old + "When you use a powerup on " + name + " it has a chance to apply the powerup to the whole team\n";
//		}
		
		@Override
		public String toString(boolean userHero) {
			return super.toString() + " - When you use a powerup on this hero it has a chance to apply the powerup to everyone in the team";
		}
		
	}

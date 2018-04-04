/**
 * When a powerup is used on this hero type, there is a chance (20%) that it will be applied to each member of the team
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class TeamPowerupHero extends Hero
	{
		public TeamPowerupHero() {
			this.name = "Team Powerup";
			this.teamPowerupChance = 0.2;
		}
	}

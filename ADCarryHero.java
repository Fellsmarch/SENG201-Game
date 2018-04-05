/**
 * This hero type deals more damage to villains, but trades that for slightly less health and lower defense
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class ADCarryHero extends Hero
	{
		public ADCarryHero() {
			name = "AD Carry";
			attackMod = 1.7;
			health = 80;
			defenseMod = 0.7;
		}
	}

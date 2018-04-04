/**
 * This hero type deals more damage to villains, but trades that for slightly less health and lower defense
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class DamageDealer extends Hero
	{
		public DamageDealer() {
			this.name = "AD Carry";
			this.attackMod = 1.7;
			this.health = 80;
			this.defenseMod = 0.7;
		}
	}

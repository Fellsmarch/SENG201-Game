/**
 * This hero type increases the amount of money the team receives from defeating villains by 50%
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class LootHoarderHero extends Hero
	{
		public LootHoarderHero(String name) {
			super(name);
			lootMod = 1.5;
		}
	}

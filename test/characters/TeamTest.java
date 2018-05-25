package characters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import characters.HeroTypes.ADCarryHero;
import characters.HeroTypes.DiscountShopper;
import characters.HeroTypes.SecondWind;
import items.*;

class TeamTest
	{
		private ArrayList<Hero> heroes = new ArrayList<Hero>(Arrays.asList(new Hero("Jimmy"), new DiscountShopper("Timmy"), new SecondWind("Hulk Hogan")));
		private Team team = new Team("Jimmy's Friends", heroes, 3);
		
		@Test
		void testAddItem()
			{
				PowerupDamage damage = new PowerupDamage();
				HealingItem healing = new HealingItem("Potion of Minor Healing", "Heals 25% of a Hero's total health", 25, 1);
				Map map = new Map(2);
				
				assertEquals(team.getItemInventory().size(), 0);
				
				team.addItem(damage);
				assertEquals(team.getItemInventory().size(), 1);
				assertEquals(team.getPowerupList().size(), 1);
				assertEquals(team.getHealingList().size(), 0);
				
				team.addItem(healing);
				assertEquals(team.getItemInventory().size(), 2);
				assertEquals(team.getPowerupList().size(), 1);
				assertEquals(team.getHealingList().size(), 1);
				
				team.addItem(map);
				assertEquals(team.getItemInventory().size(), 3);
				assertEquals(team.getPowerupList().size(), 1);
				assertEquals(team.getHealingList().size(), 1);
				assertTrue(team.hasMap(map));
			}
		
		@Test
		void testRemoveItem()
			{
				PowerupDamage damage = new PowerupDamage();
				HealingItem healing = new HealingItem("Potion of Minor Healing", "Heals 25% of a Hero's total health", 25, 1);
				Map map = new Map(2);
				team.addItem(damage); team.addItem(healing); team.addItem(map);
				
				team.removeItem(map);
				assertEquals(team.getItemInventory().size(), 2);
				assertEquals(team.getPowerupList().size(), 1);
				assertEquals(team.getHealingList().size(), 1);
				assertFalse(team.hasMap(map));
				
				team.removeItem(damage);
				assertEquals(team.getItemInventory().size(), 1);
				assertEquals(team.getPowerupList().size(), 0);
				assertEquals(team.getHealingList().size(), 1);
				assertFalse(team.hasMap(map));
				
				team.removeItem(healing);
				assertEquals(team.getItemInventory().size(), 0);
				assertEquals(team.getPowerupList().size(), 0);
				assertEquals(team.getHealingList().size(), 0);
				assertFalse(team.hasMap(map));
			}

		@Test
		void testAdjustGold()
			{
				int gold = team.getGold();
				
				team.adjustGold(500000);
				assertEquals(gold + 500000, team.getGold());
				
				team.adjustGold(-523);
				assertEquals(gold + 500000 - 523, team.getGold());
			}

		@Test
		void testKillHero()
			{
				assertTrue(team.killHero(team.getHeroList().get(0)));
				
				double one = 1;
				assertEquals(0.5, team.getShopMod());
				
				assertTrue(team.killHero(team.getHeroList().get(0)));
				assertEquals(one, team.getShopMod());
				
				assertTrue(team.killHero(team.getHeroList().get(0)));
				assertFalse(team.killHero(team.getHeroList().get(0)));
				
			}

	}

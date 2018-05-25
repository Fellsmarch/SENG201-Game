package characters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import items.PowerupDamage;
import items.PowerupDodge;
import items.PowerupLuck;

class HeroTest
	{
		private Hero hero = new Hero("Jimmy");

		@Test 
		void testPowerupActive() //Also tests setPowerup()
			{
				PowerupDamage powerupDamage = new PowerupDamage(); PowerupDodge powerupDodge = new PowerupDodge(); PowerupLuck powerupLuck = new PowerupLuck();
				assertFalse(hero.powerupActive(powerupDamage));
				assertFalse(hero.powerupActive(powerupDodge));
				assertFalse(hero.powerupActive(powerupLuck));
				hero.setPowerup(powerupDamage, true);
				hero.setPowerup(powerupDodge, true);
				hero.setPowerup(powerupLuck, true);
				assertTrue(hero.powerupActive(powerupDamage));
				assertTrue(hero.powerupActive(powerupDodge));
				assertTrue(hero.powerupActive(powerupLuck));
				hero.setPowerup(powerupLuck, false);
				assertTrue(hero.powerupActive(powerupDamage));
				assertTrue(hero.powerupActive(powerupDodge));
				assertFalse(hero.powerupActive(powerupLuck));
			}

		@Test
		void testAdjustHealth()
			{
				assertEquals(hero.getCurrentHealth(), 100);
				assertEquals(hero.getMaxHealth(), 100);
				
				hero.adjustHealth(20);
				assertEquals(hero.getCurrentHealth(), 100);
				assertEquals(hero.getMaxHealth(), 100);
				
				hero.adjustHealth(-37);
				assertEquals(hero.getCurrentHealth(), 63);
				assertEquals(hero.getMaxHealth(), 100);
			}



		@Test
		void testDeath()
			{
				assertTrue(hero.death());
			}

	}

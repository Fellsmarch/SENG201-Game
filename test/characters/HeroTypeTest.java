package characters;

import static org.junit.jupiter.api.Assertions.*;
import characters.HeroTypes.*;

import org.junit.jupiter.api.Test;

class HeroTypeTest
	{

		@Test
		void testCreateHero()
			{
				Hero adCarry = HeroType.ADCARRY.createHero("Jimmy");
				assertTrue(adCarry instanceof ADCarryHero);
				
				Hero ceo = HeroType.CEO.createHero("Jimmy");
				assertTrue(ceo instanceof CEOHero);
				
				Hero shopper = HeroType.DISCOUNTSHOPPER.createHero("Jimmy");
				assertTrue(shopper instanceof DiscountShopper);
				
				Hero looter = HeroType.LOOTHOARDER.createHero("Jimmy");
				assertTrue(looter instanceof LootHoarderHero);
				
				Hero lucky = HeroType.LUCKY.createHero("Jimmy");
				assertTrue(lucky instanceof LuckyHero);
				
				Hero random = HeroType.RANDOM.createHero("Jimmy");
				assertTrue(random instanceof RandomHero);
				
				Hero secondWind = HeroType.SECONDWIND.createHero("Jimmy");
				assertTrue(secondWind instanceof SecondWind);
				
				Hero tank = HeroType.TANK.createHero("Jimmy");
				assertTrue(tank instanceof TankHero);
				
				Hero powerup = HeroType.TEAMPOWERUP.createHero("Jimmy");
				assertTrue(powerup instanceof TeamPowerupHero);
			}

	}

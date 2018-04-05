import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SecondWindTest
	{

		@Test
		void testDeath()
			{
				SecondWind hero = new SecondWind();
				assertFalse(hero.death(), "Hero should not die the first time");
				assertTrue(hero.death(), "Hero should die after already dying once");
			}

	}

package characters.HeroTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SecondWindTest
	{

		@Test
		void testDeath()
			{
				SecondWind secondWind = new SecondWind("Jimmy");
				assertFalse(secondWind.death());
				assertTrue(secondWind.death());
			}

	}

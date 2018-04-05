import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HeroTest
	{

		@Test
		void testDeath()
			{
				Hero heroTest = new Hero();
				assertTrue(heroTest.death(), "Death must return true");
				//fail("Not yet implemented");
			}

		@Test
		void testPrintAttributes()
			{
				fail("Not yet implemented");
			}

	}

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class TeamTest
	{


		@Test
		void testChangeMoney()
			{
				ArrayList<Hero> heroList = new ArrayList<Hero>();
				Team myTeam = new Team("Test", heroList);
				assertEquals(myTeam.getMoney(), 100, "Starting money is not correct");
				myTeam.changeMoney(50);
				assertEquals(myTeam.getMoney(), 150, "Did not add money correctly");
				myTeam.changeMoney(-120);
				assertEquals(myTeam.getMoney(), 30, "Did not remove money correctly");
			}

		@Test
		void testChangeEventChance()
			{
				ArrayList<Hero> heroList = new ArrayList<Hero>();
				Team myTeam = new Team("Test", heroList);
				myTeam.changeEventChance(0.2);
				assertEquals(myTeam.getEventChance(), 0.7, "Good event chance not added correctly");
				
			}

		@Test
		void testAddPowerup()
			{
				ArrayList<Hero> heroList = new ArrayList<Hero>();
				Team myTeam = new Team("Test", heroList);
				ArrayList<Integer> test = new ArrayList<Integer>();
				assertEquals(myTeam.getPowerupList(), test, "Powerups do not return correctly");
				myTeam.addPowerup(1); myTeam.addPowerup(2); myTeam.addPowerup(1); myTeam.addPowerup(3);
				test.add(1); test.add(2); test.add(1); test.add(3);
				assertEquals(myTeam.getPowerupList(), test, "Powerups were not added correctly");
			}

		@Test
		void testRemovePowerup()
			{
				ArrayList<Hero> heroList = new ArrayList<Hero>();
				Team myTeam = new Team("Test", heroList);
				ArrayList<Integer> test = new ArrayList<Integer>();
				myTeam.addPowerup(1); myTeam.addPowerup(2); myTeam.addPowerup(1); myTeam.addPowerup(3);
				test.add(2); test.add(1);  test.add(3);
				myTeam.removePowerup(1);
				assertEquals(myTeam.getPowerupList(), test, "Does not remove duplicate powerups correctly");
				myTeam.removePowerup(2); test.remove(0);
				//System.out.println(test);
				assertEquals(myTeam.getPowerupList(), test, "Does not remove powerups correctly");
			}

		@Test
		void testAddHealing()
			{
				ArrayList<Hero> heroList = new ArrayList<Hero>();
				Team myTeam = new Team("Test", heroList);
				ArrayList<Integer> test = new ArrayList<Integer>();
				assertEquals(myTeam.getHealingList(), test, "Powerups do not return correctly");
				myTeam.addHealing(1); myTeam.addHealing(2); myTeam.addHealing(1); myTeam.addHealing(3);
				test.add(1); test.add(2); test.add(1); test.add(3);
				assertEquals(myTeam.getHealingList(), test, "Powerups were not added correctly");
			}

		@Test
		void testRemoveHealing()
			{
				ArrayList<Hero> heroList = new ArrayList<Hero>();
				Team myTeam = new Team("Test", heroList);
				ArrayList<Integer> test = new ArrayList<Integer>();
				myTeam.addHealing(1); myTeam.addHealing(2); myTeam.addHealing(1); myTeam.addHealing(3);
				test.add(2); test.add(1);  test.add(3);
				myTeam.removeHealing(1);
				assertEquals(myTeam.getHealingList(), test, "Does not remove duplicate healing correctly");
				myTeam.removeHealing(2); test.remove(0);
				//System.out.println(test);
				assertEquals(myTeam.getHealingList(), test, "Does not remove healing correctly");
			}

		@Test
		void testAddHero()
			{
				ArrayList<Hero> heroList = new ArrayList<Hero>();
				Team myTeam = new Team("Test", heroList);
				ArrayList<Hero> testList = new ArrayList<Hero>();
				Hero hero1 = new Hero(); Hero hero2 = new Hero();
				hero1.setName("Jeff"); hero2.setName("Bridges");
				assertEquals(myTeam.getHeroList(), testList, "Hero was not added correctly");
				myTeam.addHero(hero2); testList.add(hero2);
				assertEquals(myTeam.getHeroList(), testList, "Hero was not added correctly");
				
				
			}

		@Test
		void testRemoveHero()
			{
				Hero hero1 = new Hero(); Hero hero2 = new Hero();
				hero1.setName("Jeff"); hero2.setName("Bridges");
				ArrayList<Hero> heroList = new ArrayList<Hero>();
				heroList.add(hero1); heroList.add(hero2);
				Team myTeam = new Team("Test", heroList);
				heroList.remove(hero1); myTeam.removeHero(hero1);  //Need a way to test this directly instead of indirectly
				assertEquals(myTeam.getHeroList(), heroList, "Hero was not removed correctly");
				myTeam.removeHero(hero2); heroList.remove(hero2);
				assertEquals(myTeam.getHeroList(), heroList, "Hero was not removed correctly");
			}

	}

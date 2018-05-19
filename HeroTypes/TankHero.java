/**
 * This hero type acts as a tank for the team, it has increased health and defense
 * and lower recovery rate but suffers reduced attack damage as a result
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class TankHero extends Hero
	{
		public TankHero(String name) {
			super(name, 200); 
			//adjustHealth(100); //Which is better practice using setters on private variables or directly setting protected ones
			recoveryRate = 1;
			attackMod = 0.65;
			defenseMod = 1.3;
		}
		
		@Override
		public String toString(boolean userHero) {
			return super.toString() + "A high defence, low offence hero";
		}
		
//		public static void main(String[] args) {
//			TankHero tank = new TankHero();
//			//tank.name = "Tank";
//			System.out.println(tank.getHealth());
//			System.out.println(tank.getRecovery());
//			System.out.println(tank.getName());
//			
//			Hero hero = new Hero();
//			System.out.println(hero.name);
//			System.out.println(hero.getName());
//		}
	}

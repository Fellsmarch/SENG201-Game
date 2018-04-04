/**
 * This hero type acts as a tank for the team, it has increased health and defense
 * and lower recovery rate but suffers reduced attack damage as a result
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class Tank extends Hero
	{
		public Tank() {
			this.name = "Tank";
			this.health = 200;
			this.recoveryRate = 1;
			this.attackMod = 0.65;
			this.defenseMod = 1.3;
		}
		
//		public static void main(String[] args) {
//			Tank tank = new Tank();
//			tank.name = "Tank";
//			System.out.println(tank.getHealth());
//			System.out.println(tank.getRecovery());
//			System.out.println(tank.getName());
//			
//			Hero hero = new Hero();
//			System.out.println(hero.name);
//			System.out.println(hero.getName());
//		}
	}

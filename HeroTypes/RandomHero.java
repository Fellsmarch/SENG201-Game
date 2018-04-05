/**
 * This hero type randomises many of a Hero's stats, it randomises:
 * Health 					(between 1-200)
 * Recovery Rate 			(between 0-10 seconds)
 * Shop Prices Modifier 	(between 0.5-1.5)
 * Attack Modifier 			(between 0.1-2)
 * Defense Modifier 		(between 0.1-2)
 * Loot Modifier 			(between 0.5-1.5)
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

import java.util.Random;

public class RandomHero extends Hero
	{
		public RandomHero() {
			name = "Randomiser";
			Random randomiser = new Random();
			health = randomiser.nextInt(200) + 1;
			recoveryRate = randomiser.nextInt(11);
			double newPrice = randomiser.nextInt(11) + 5; shopPrice = newPrice / 10;
			double newAttack = randomiser.nextInt(20) + 1; attackMod = newAttack / 10;
			double newDefense = randomiser.nextInt(20) + 1; defenseMod = newDefense / 10;
			double newLoot = randomiser.nextInt(11) + 5; lootMod = newLoot / 10;
		}
		
//		public static void main(String[] args) {
//			RandomHero test = new RandomHero();
////			System.out.println(test.getHealth());
////			System.out.println(test.getRecovery());
////			System.out.println(test.getShopPrice());
//			test.printAttributes();
//			
//		}
	}

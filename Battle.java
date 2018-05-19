import java.util.ArrayList;
import java.util.Random;

public class Battle
	{
		private Team team;
		private Villain villain;
		private Hero challenger;
		private Output output = new Output();
		
		
		public Battle(Team team, Villain villain) {
			this.team = team;
			this.villain = villain;
		}
		
		public boolean startBattle() { //Returns true if the superhero team wins, false if they lose
			boolean battleOver = false;
			boolean challengerAlive;
			boolean villainDefeated = false;
			int timesVillainDefeated = 0;
			System.out.println(villain.getName() + ": " + villain.getTaunt()); //Taunts the player/Hero
			while (!battleOver) {
				//Select the challenger
				selectChallenger();
				challengerAlive = true;
				boolean[] powerups = challenger.getPowerups();
				
				//Play the game and deal with damage and death
				while (challengerAlive && !villainDefeated) {
					if (villain.chooseGame().playGame(villain.getName(), powerups[0])) { 	//Plays the game
						damageVillain(powerups[1]);
						if (villain.getHealth() < 1) { 										//If Villain dies
							villainDefeated = true;
							battleOver = true;
							System.out.println(team.getName() + " recieves " + villain.getReward() + " gold for defeating " + villain.getName());
							team.changeMoney(villain.getReward());
						}
					} 
					else {
						damageHero(powerups[2]);
						if (challenger.getHealth() < 1) { 									//If Hero dies
							if(challenger.death()) {
								challengerAlive = false;
								team.removeHero(challenger);
								challenger = null; 											//do I need this?
							}
						}
					}
					System.out.println(".\n.\n.");
				}
				if (team.getHeroList().size() < 1) {
					System.out.println(team.getName() + " has been defeated by " + villain.getName() + ", you Lose :(.");
					battleOver = true;
				}
			}
			//Resets all active powerups
			for (Hero hero : team.getHeroList()) {
				for(int powerup = 0; powerup < 3; powerup++) {
					hero.setPowerup(powerup, false);;
				}
			}
			return villainDefeated;
		}
		
		private void selectChallenger() {
			ArrayList<Hero> heroes = team.getHeroList();
			int numHeroes = heroes.size();
			String[] heroOptions = new String[numHeroes + 1];
			for (int i = 0; i < numHeroes; i++) {heroOptions[i] = heroes.get(i).getName();}
			heroOptions[numHeroes] = "Show Hero stats";
			System.out.println("Choose your challenger!");
			boolean challengerSelected = false;
			while (!challengerSelected) {
				int userChoice = output.printOptions(heroOptions);
				if (userChoice == numHeroes + 1) {System.out.println(team);}
				else {challenger = heroes.get(userChoice - 1); challengerSelected = true;}
			}
			
		}
		
		private void damageHero(boolean chanceToDodge) {
			Random rand = new Random();
			Double damage = villain.getDamage() * challenger.getDefenseMod();
			if (chanceToDodge) {
				if (rand.nextInt(5) == 0) {System.out.println(challenger.getName() + " managed to dodge " + villain.getName() + "'s attack!");} //20% chance of dodging
				else {challenger.adjustHealth(damage.intValue());}
			} else {challenger.adjustHealth(damage.intValue());}
		}
		
		private void damageVillain(boolean extraDamage) {
			Double damage = 40 * challenger.getAttackMod();
			if (extraDamage) {villain.removeHealth(damage.intValue() * 2);}
			else {villain.removeHealth(damage.intValue());}
		}
		
		
	}

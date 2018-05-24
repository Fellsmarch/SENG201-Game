package characters;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import guiElements.GameType;

/**
 * This class creates a superclass "Villain", an object that stores all the relevant variables and methods that a "Villain" would possess
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */
public class Villain
	{
		/**
		 * The Villain's name
		 */
		private String name;
		
		/**
		 * The Villain's title
		 */
		private String title;
		
		/**
		 * An array of game types that the Villain could challenge the Hero with
		 */
		private ArrayList<GameType> gameList = new ArrayList<GameType>(Arrays.asList(GameType.values()));
		
		/**
		 * The game most likely to be played by this Villain (Villain's "favourite" game)
		 */
		private GameType favouriteGame;
		
		/**
		 * Chance that the Villain's favourite game will be picked from the gameList
		 */
		private double favouriteGameChance; 
		
		/**
		 * The Villain's taunt phrase
		 */
		private String tauntPhrase;
		
		/**
		 * The damage that the Villain deals to a Hero
		 */
		protected int damage;
		
		/**
		 * The Villain's health
		 */
		protected int health;
		
		/**
		 * The amount of money received by the Team if the Team defeats the Villain
		 */
		protected int killReward;
		
		/** Creates a Villain
		 * @param nameTitle The Villain's name and title from an array
		 * @param taunt The Villain's taunt phrase
		 */
		public Villain(String[] nameTitle, String taunt) { 
			name = nameTitle[0];
			title = nameTitle[1];
			tauntPhrase = taunt;
			
			/**
			 * Assigns the size of the array list, gameList, to a variable gameListSize
			 */
			int gameListSize = gameList.size();
			
			/**
			 * Creates the randomiser
			 */
			Random randomiser = new Random();
			
			/**
			 * Uses the randomiser to assign a favourite game to a Villain
			 */
			int favGameIndex = randomiser.nextInt(gameListSize);
			favouriteGame = gameList.get(favGameIndex);
			
			/**
			 * Uses the randomiser to assign the chance that the favourite game will be picked
			 * This will mean that the number will be between probOfGame and 100
			 */
			int probOfGame = 100 / gameListSize;
			favouriteGameChance = randomiser.nextInt(100 - probOfGame) + probOfGame ; 	
			
			/**
			 * Randomises the damage dealt by the Villain
			 */
			damage = randomiser.nextInt(15) + 30; 
			
			/**
			 * Randomises the health of the Villain
			 */
			health = randomiser.nextInt(50) + 100; 
			
			/**
			 * Randomises the kill reward that the Villain grants the Team
			 */
			killReward = randomiser.nextInt(200) + 50; 
		}
		
		/**
		 * Uses the randomiser to choose the game to be played by the Villain
		 * @return Game that will be played 
		 */
		public GameType chooseGame() {
			Random randomiser = new Random();
			int toTest = randomiser.nextInt(100) + 1;
			if(toTest <= favouriteGameChance) {
				return favouriteGame;
			}else {
				gameList.remove(favouriteGame);
				int gameIndex = randomiser.nextInt(gameList.size());
				gameList.add(favouriteGame);
				return gameList.get(gameIndex);
			}
		}
		/**
		 * Gets the Villain's name
		 * @return
		 */
		public String getName() {return name;}
		
		/**
		 * Gets the Villain's title
		 * @return
		 */
		public String getTitle() {return title;}
		
		/**
		 * Gets the Villain's taunt phrase
		 * @return
		 */
		public String getTaunt() {return tauntPhrase;}
		
		/**
		 * Gets the damage done by the Villain
		 * @return
		 */
		public int getDamage() {return damage;}
		
		/**
		 * Gets the Villain's health
		 * @return
		 */
		public int getHealth() {return health;}
		
		/**
		 * Gets the amount of money received by the team if the Villain is defeated
		 * @return
		 */
		public int getReward() {return killReward;}
		
		/**
		 * Removes the Villain's health if damage is dealt and health is lost
		 * @param healthLost The health lost from the Villain is damage is dealt by the Hero
		 */
		public void removeHealth(int healthLost) {health -= healthLost;}
		
		
	
	}

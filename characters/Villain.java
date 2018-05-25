package characters;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import guiElements.GameType;

public class Villain
	{
		private String name;
		private String title;
		private ArrayList<GameType> gameList = new ArrayList<GameType>(Arrays.asList(GameType.values()));					//Could change this to array, it would make it easier to create it in the game environment
		private GameType favouriteGame;
		private double favouriteGameChance; //Could be int
		private String tauntPhrase;
		protected int damage;
		protected int health;
		protected int killReward;
		
		public Villain(String[] nameTitle, String taunt) { 
			name = nameTitle[0];
			title = nameTitle[1];
			tauntPhrase = taunt;
			
			//Getting random favourite game and favourite game chance
			int gameListSize = gameList.size();
			Random randomiser = new Random();
			int favGameIndex = randomiser.nextInt(gameListSize);
			favouriteGame = gameList.get(favGameIndex);
			int probOfGame = 100 / gameListSize;
			favouriteGameChance = randomiser.nextInt(100 - probOfGame) + probOfGame ; 	//Doing it this way means the number will be between probOfGame and 100
																						//System.out.println(favouriteGameChance);
			
			//Randomising stats --> these will need to be changed when actual values are known
			damage = randomiser.nextInt(15) + 30; //Numbers are placeholders at the moment
			health = randomiser.nextInt(50) + 100; //Numbers are placeholders at the moment
			killReward = randomiser.nextInt(200) + 50; //Numbers are placeholders at the moment
		}
		
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
		
		public String getName() {return name;}
		public String getTitle() {return title;}
		public String getTaunt() {return tauntPhrase;}
		public int getDamage() {return damage;}
		public int getHealth() {return health;}
		public int getReward() {return killReward;}
		public void removeHealth(int healthLost) {health -= healthLost;}
	}

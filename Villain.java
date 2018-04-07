//Need to add documentation and more in depth comments

import java.util.ArrayList;
import java.util.Random;

public class Villain
	{
		private String name;
		private ArrayList<Game> gameList;
															//private ArrayList<String> gameList; used for testing without a list of games
		private Game favouriteGame;
															//private String favouriteGame;
		private double favouriteGameChance; //Could be int
		private String tauntPhrase;
		protected int damage;
		protected int health;
		protected int killReward;
		
		public Villain(String newName, String taunt, ArrayList<Game> games) { 
			name = newName;
			tauntPhrase = taunt;
			gameList = games;
			
			//Getting random favourite game and favourite game chance
			int gameListSize = gameList.size();
			Random randomiser = new Random();
			int favGameIndex = randomiser.nextInt(gameListSize);
			favouriteGame = gameList.get(favGameIndex);
			int probOfGame = 100 / gameListSize;
			favouriteGameChance = randomiser.nextInt(100 - probOfGame) + probOfGame ; 	//Doing it this way means the number will be between probOfGame and 100
																						//System.out.println(favouriteGameChance);
			
			//Randomising stats --> these will need to be changed when actual values are known
			damage = randomiser.nextInt(20) + 20; //Numbers are placeholders at the moment
			health = randomiser.nextInt(300) + 100; //Numbers are placeholders at the moment
			killReward = randomiser.nextInt(500) + 50; //Numbers are placeholders at the moment
			
			
		}
		
		public Game chooseGame() {
			Random randomiser = new Random();
			int toTest = randomiser.nextInt(100) + 1;
																						//System.out.println(toTest);
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
		public String getTaunt() {return tauntPhrase;}
		public int getDamage() {return damage;}
		public int getHealth() {return health;}
		public int getReward() {return killReward;}
		public void removeHealth(int healthLost) {health -= healthLost;}
	
		
//		public static void main(String[] args) { //For testing only
//			ArrayList<String> games = new ArrayList<String>();
//			games.add("PSR"); games.add("Dice game"); games.add("Guess number");
//			Villain testVillain = new Villain("Hannah", "I am the second-best member of the team!... ;)", games);
//			System.out.println("Favourite game: " + testVillain.favouriteGame);
//			System.out.println(testVillain.chooseGame());
//			
//		}
	}

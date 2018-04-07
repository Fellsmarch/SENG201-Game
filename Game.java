
public interface Game
	{
		public boolean beMoreLucky = false; //Can't be private
		
		public boolean playGame();
		int getHeroInput(); //Can't be private
		int getVillainChoice();
		boolean checkIfWon(int heroChoice, int villainChoice);
		
	}

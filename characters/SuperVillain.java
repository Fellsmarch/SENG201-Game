package characters;
import java.util.ArrayList;
import java.util.Random;

import commandLineElements.Game;

public class SuperVillain extends Villain
	{
		public SuperVillain(String newName, String taunt, ArrayList<Game> games) {
			super(newName, taunt, games);
			Random rand = new Random();
			damage = rand.nextInt(40) + 20;			//Numbers are placeholders
			health = rand.nextInt(1000) + 500;		//Numbers are placeholders
			killReward = 1000000;					//Numbers are placeholders
		}
	}

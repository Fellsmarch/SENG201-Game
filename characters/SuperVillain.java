package characters;
import java.util.ArrayList;
import java.util.Random;

import commandLineElements.Game;

public class SuperVillain extends Villain
	{
		public SuperVillain(String[] nameTitle, String taunt, ArrayList<Game> games) {
			super(nameTitle, taunt, games);
			Random rand = new Random();
			damage = rand.nextInt(40) + 20;			//Numbers are placeholders
			health = rand.nextInt(1000) + 500;		//Numbers are placeholders
			killReward = 1000000;					//Numbers are placeholders
		}
	}

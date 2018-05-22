package characters;
import java.util.Random;


public class SuperVillain extends Villain
	{
		public SuperVillain(String[] nameTitle, String taunt) {
			super(nameTitle, taunt);
			Random rand = new Random();
			damage = rand.nextInt(40) + 20;			//Numbers are placeholders
			health = rand.nextInt(150) + 150;		//Numbers are placeholders
			killReward = 1000000;					//Numbers are placeholders
		}
	}

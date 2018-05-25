package characters;
import java.util.Random;

/**
 * This class is a subclass of the superclass "Villain", is a "stronger" Villain (has better stats)
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */
public class SuperVillain extends Villain {
	
	/**
	 * Constructor
	 * @param nameTitle The two value array containing the Villain's name and title
	 * @param taunt The taunt the villain yells when beginning a battle with the user's team
	 */
	public SuperVillain(String[] nameTitle, String taunt) {
		super(nameTitle, taunt);
		Random rand = new Random();
		damage = rand.nextInt(40) + 20;
		health = rand.nextInt(150) + 150;
		killReward = 1000000;
	}
}

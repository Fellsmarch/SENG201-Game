package characters;
import java.util.Random;

/**
 * This class is a subclass of the superclass "Villain", is a "stronger" Villain (has better stats)
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */
public class SuperVillain extends Villain {
	
	/**
	 * Constructor
	 * @param name The name of the Power Up.
	 * @param description The description of the Power Up
	 * @param price How much the Power Up costs to purchase
	 */
	public SuperVillain(String[] nameTitle, String taunt) {
		super(nameTitle, taunt);
		Random rand = new Random();
		damage = rand.nextInt(40) + 20;			//Numbers are placeholders
		health = rand.nextInt(150) + 150;		//Numbers are placeholders
		killReward = 1000000;					//Numbers are placeholders
	}
}

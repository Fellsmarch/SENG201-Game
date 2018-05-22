package items;

/**
 * This class creates the class 'PowerupLuck', a subclass of Powerup, and can be stored in the Team's array list, powerupsInventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class PowerupLuck extends Powerup {
	
	/**
	 * Constructor
	 * @param name The name of the Power Up.
	 * @param description The description of the Power Up
	 * @param price How much the Power Up costs to purchase
	 */
	public PowerupLuck() {
        super("Kage's Lucky Pick", "Increases chance of winning a game against a villain", 70);
    }

	/** 
	 * Creates a new instance of the Power Up.
	 * @return PowerUpDamage
	 */
	@Override
	public Powerup copy() {return new PowerupLuck();}
}

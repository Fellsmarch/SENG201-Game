package items;

/**
 * This class creates the class 'PowerupDodge', a subclass of Powerup, and can be stored in the Team's array list, powerupsInventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class PowerupDodge extends Powerup {
	
	/**
	 * Constructor
	 * @param name The name of the Power Up.
	 * @param description The description of the Power Up
	 * @param price How much the Power Up costs to purchase
	 */
	public PowerupDodge() {
        super("Ninja Tabi", "Chance of dodging a villain's attack", 60);
    }

	/** 
	 * Creates a new instance of the Power Up.
	 * @return PowerUpDamage
	 */
	@Override
	public PowerupDodge copy() {return new PowerupDodge();}
}

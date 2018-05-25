package items;

/**
 * This class creates the class 'Powerup' which is an object that can be stored in a Team's array list, powerupsInventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */
public abstract class Powerup extends Item {
	/** Constructor
	 * @param name The name of the Power Up.
	 * @param description The description of the Power Up
	 * @param price How much the Power Up costs to purchase
	 */
	public Powerup(String name, String description, int price){super(name, description, price);}
}

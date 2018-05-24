package items;
import characters.Hero;

/**
 * This class creates the class 'Powerup' which is an object that can be stored in a Team's array list, powerupsInventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public abstract class Powerup extends Item {
	
	/** Constructor
	 * @param name The name of the Power Up.
	 * @param description The description of the Power Up
	 * @param price How much the Power Up costs to purchase
	 */
	public Powerup(String name, String description, int price){super(name, description, price);}
	
	/** Assigns the Power Up to a hero and "uses" it.
	 * @param hero A hero for the Power Up to be applied to
	 */
	public void usePowerup(Hero hero) {hero.setPowerup(this);}
	
	/** 
	 * Creates a new instance of the Power Up.
	 */
	public abstract Powerup copy();
}

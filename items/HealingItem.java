package items;

/**
 * This class creates the class 'HealingItem' which is an object that can be stored in a Team's array list, healingInventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */

public class HealingItem extends Item {
	
	/**
	 * The number of 25% heals to do
	 */
	private int healingAmount;
	
	/** 
	 * Constructor -- HealingItem. Creates a healing item.
	 * @param name The name of the healing item.
	 * @param description The description of the item.
	 * @param price How much the item costs to purchase.
	 * @param healingAmount How much the item heals a hero.
	 */
	public HealingItem(String name, String description, int price, int healingAmount) {
		super(name, description, price);
		this.healingAmount = healingAmount;
	}
	
	/** Get the healing amount of the item
	 * @return
	 */
	public int getHealingAmount() {return this.healingAmount;}
	
}

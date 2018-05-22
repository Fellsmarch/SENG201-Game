package items;

/**
 * This class creates the class 'HealingItem' which is an object that can be stored in a Team's array list, healingInventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class HealingItem extends Item {
	
	private int healingAmount; //Number of 25% heals to do
	
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
	
	/** Creates a new instance of the item.
	 * @return HealingItem
	 */
	public HealingItem copy() {return new HealingItem(this.getName(), this.getDescription(), this.getPrice(), healingAmount);}
	
	/** Get the healing amount of the item
	 * @return
	 */
	public int getHealingAmount() {return this.healingAmount;}
	
}


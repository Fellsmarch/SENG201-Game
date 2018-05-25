package items;

/**
 * This class creates the superclass 'Item' which is an object that can be stored in a Team's array list, itemInventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */

public abstract class Item {
	
	/**
	 * The item's name
	 */
	private String name;
	
	/**
	 * The item's description
	 */
	private String description;
	
	/**
	 * How much the item costs to purchase
	 */
	private int price;
	
	/** Creates an item.
	 * @param name The item's name.
	 * @param description The item's description.
	 * @param price How much the item costs to purchase.
	 */
	public Item(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
    /** 
     * @return the name of the item
     */
    public String getName() {return name;}
    
    /** 
     * @return the description of the item
     */
    public String getDescription() {return description;}
    
    /** 
     * @return the price of the item
     */
    public int getPrice() {return price;}
    
    /** 
     * Get the description of the item
     * @return
     */
    
    /** 
     * Gets the name of the item
     * @return A string representing the name of the item
     */
    public String toString() {return name;}
    
}


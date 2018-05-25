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
     * Get the name of the item
     * @return
     */
    public String getName() {return name;}
    
    /** 
     * Get the description of the item
     * @return
     */
    public String getDescription() {return description;}
    
    /** 
     * Get the price of the item
     * @return
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


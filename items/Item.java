//package items;
//import characters.Hero;
//
//public interface Item {
//
//	
////	public boolean useItem();
////	public boolean useItem(Hero hero);
//    public Item create();
//    
//    public abstract String getName();
//    public abstract String getDescription();
//    public abstract int getPrice();
//    public abstract String toString();
//    
//}

package items;

public abstract class Item {
	
	private String name;
	private String description;
	private int price;
	
	public Item(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
    public String getName() {return name;}
    public String getDescription() {return description;}
    public int getPrice() {return price;}
    public String toString() {return name;}
    
}
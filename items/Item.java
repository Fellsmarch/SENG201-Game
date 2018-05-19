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
import characters.Hero;

public abstract class Item {
	
	private String name;
	private String description;
	private int price;
	
	public Item(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
//	public boolean useItem();
//	public boolean useItem(Hero hero);
    public abstract Item copy();
    public String getName() {return name;}
    public String getDescription() {return description;}
    public int getPrice() {return price;}
    public String toString() {return name;}
    
}

//package items;
//import characters.Hero;
//
//public abstract class Item {
//	
//	String name;
//	String description;
//	int price;
//	
//	public Item(String name, String description, int price) {
//		this.name = name;
//		this.description = description;
//		this.price = price;
//	}
//	
//	public abstract boolean useItem();
//	public abstract boolean useItem(Hero hero);
//
//    public abstract Item create();
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return this.description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getPrice() {
//        return this.price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//	
//    }
//    
//    public String toString() {
//    	return name;
//    }
//}

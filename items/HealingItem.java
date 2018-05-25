package items;

public class HealingItem extends Item {
	
	private int healingAmount; //Number of 25% heals to do
	
	// Constructor
	public HealingItem(String name, String description, int price, int healingAmount) {
		super(name, description, price);
		this.healingAmount = healingAmount;
	}
	
	public int getHealingAmount() {return this.healingAmount;}
	
}

//package items;
//
//import characters.Hero;
//
//public abstract class HealingItem extends Item {
//	
//	private int healingAmount;
//	private int healingTime;
//	
//	// Constructor
//	
//	public HealingItem(String name, String description, int price, int healingAmount, int healingTime) {
//		super(name, description, price);
//		this.name = name;
//		this.description = description;
//		this.price = price;
//		this.healingAmount = healingAmount;
//		this.healingTime = healingTime;
//	}
//	
////	public abstract boolean useItem(Hero hero);
//	
//	public int getHealingAmount() { return this.healingAmount; }
//	
//	public int getHealingTime() {	return this.healingTime;	}
//	
//	public void setHealingAmount(int healingAmount) {   this.healingAmount = healingAmount;   } 
//
//	public void setHealingTime(int healingTime) {	this.healingTime = healingTime;	}
//	
//	public String toString() {
//		return name;
//	}
//}
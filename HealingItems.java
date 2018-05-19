
public abstract class HealingItems extends Item {
	
	private int healingAmount;
	private int healingTime;
	
	// Constructor
	
	public HealingItems(String name, String description, int price, int healingAmount, int healingTime) {
		super(name, description, price);
		this.name = name;
		this.description = description;
		this.price = price;
		this.healingAmount = healingAmount;
		this.healingTime = healingTime;
	}
	
	public int getHealingAmount() { return this.healingAmount; }
	
	public int getHealingTime() {	return this.healingTime;	}
	
	public void setHealingAmount(int healingAmount) {   this.healingAmount = healingAmount;   } 

	public void setHealingTime(int healingTime) {	this.healingTime = healingTime;	}
	

	
	public boolean apply(Hero hero) {
//		if (!hero.death() && !hero.isHealing()) {
//			hero.beginHeal(this);
//			return true;
//		} else {
			return false;
//		}
			
	}
	
	public String toString() {
		return this.name;
	}

}

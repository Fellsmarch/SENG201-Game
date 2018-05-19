
public class mediumHealingItem extends HealingItems {

	public mediumHealingItem() {
		super("Potion of Medium Healing", "Heals 50% of a Hero's total health", 50, 50, 50);
	}
	
	public Item create() {
        return new mediumHealingItem();
    }

}

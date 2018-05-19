
public class minorHealingItem extends HealingItems {
	
	public minorHealingItem() {
		super("Potion of Minor Healing", "Heals 25% of a Hero's total health", 25, 25, 25); // Int values subject to change (see all healing items)
	}
	
	public Item create() {
        return new minorHealingItem();
    }

}

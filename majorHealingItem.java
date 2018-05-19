
public class majorHealingItem extends HealingItems {

	public majorHealingItem() {
		super("Potion of Major Healing", "Restores the Hero's health to Full", 100, 1, 100);
	}
	
	public boolean apply(Hero hero) {
        this.setHealingAmount(hero.getMaxHealth());
        return super.apply(hero);
    }
	
	 public Item create() {
	        return new majorHealingItem();
	    }

}


public abstract class PowerUp extends Item {
	
	public PowerUp(String name, String description, int price) {
		super(name, description, price);
	}
	
	public boolean apply(Hero hero) {
		hero.setPowerUp(this);
		return true;
	}
	
}

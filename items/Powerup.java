package items;
import characters.Hero;

public abstract class Powerup extends Item {
	public Powerup(String name, String description, int price){super(name, description, price);}
	public void usePowerup(Hero hero) {hero.setPowerup(this);}
	public abstract Powerup copy();
}



//package items; 
//import characters.Hero;
//
//public abstract class Powerup extends Item {
//	
//	public Powerup(String name, String description, int price) {
//		super(name, description, price);
//	}
//	
//	public boolean apply(Hero hero) {
//		hero.setPowerup(this);
//		return true;
//	}
//}
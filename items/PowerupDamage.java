package items;

public class PowerupDamage extends Powerup {
	public PowerupDamage() {super("Infinity Edge", "Doubles damage dealt to villain", 50);}

	@Override
	public PowerupDamage copy() {return new PowerupDamage();}
}


package items;

public class PowerupDodge extends Powerup {
	public PowerupDodge() {
        super("Ninja Tabi", "Chance of dodging a villain's attack", 60);
    }

	@Override
	public PowerupDodge copy() {return new PowerupDodge();}
}
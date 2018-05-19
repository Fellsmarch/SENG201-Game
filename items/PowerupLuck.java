package items;

public class PowerupLuck extends Powerup {
	public PowerupLuck() {
        super("Kage's Lucky Pick!", "Increases chance of winning a game", 70);
    }

	@Override
	public Powerup copy() {return new PowerupLuck();}
}

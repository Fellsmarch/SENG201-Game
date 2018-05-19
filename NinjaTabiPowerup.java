
public class NinjaTabiPowerup extends PowerUp implements Ability {
	public NinjaTabiPowerup() {
        super("Ninja Tabi - fast boots!", "Chance of dodging a villain's attack", 60);
    }

    public String description() {
        return this.getDescription();
    }

    public boolean affects(Building building) {
        return building instanceof VillainsLair;
    }

    public boolean affects(Item item) {
        return false;
    }

    public void applyTo(Building building) {
        if (this.affects(building)) {
            VillainsLair var2 = (VillainsLair)building;
        }

    }

    public void applyTo(Item item) {
    }

    public Item create() {
        return new NinjaTabiPowerup();
    }

}

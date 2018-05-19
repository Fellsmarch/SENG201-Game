
public class InfinityEdgePowerup extends PowerUp implements Ability {
	public InfinityEdgePowerup() {
        super("Infinity Edge - a sharp sword!", "Doubles damage dealt to villain", 50);
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
        return new InfinityEdgePowerup();
    }
}


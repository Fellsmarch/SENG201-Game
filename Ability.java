
public interface Ability {
	public String description();
	
	public boolean affects(Building building);
	public boolean affects(Item item);
	
	public void applyTo(Building building);
	public void applyTo(Item item);
	
}

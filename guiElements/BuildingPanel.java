package guiElements;

import javax.swing.JPanel;

/**
 * This class creates the abstract class 'BuildingPanel', extends JPanel and creates a panel specifically for displaying attributes of each building (Hospital, Powerup Den etc)
 * @author Harrison Cook
 * @author Hannah Regan
 */
@SuppressWarnings("serial")
public abstract class BuildingPanel extends JPanel {
	
	/**
	 * A toString method for displaying Strings for the BuildingPanel
	 */
	public abstract String toString();
	
	/**
	 * Updates the display accordingly
	 */
	public abstract void updateDisplays();
}

package guiElements;

import javax.swing.JPanel;

/**
 * This class creates the abstract class 'BuildingPanel', extends JPanel and creates a panel specifically for displaying attributes of each building (Hospital, Power Up Den etc)
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
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

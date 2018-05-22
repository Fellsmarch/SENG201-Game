package guiElements;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class BuildingPanel extends JPanel {
	public abstract String toString();
	public abstract void updateDisplays();
}


package guiElements;

import javax.swing.JPanel;

/**
 * This class creates the abstract class 'GamePanel', extends JPanel and creates a panel specifically for interactions regarding the games
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */
@SuppressWarnings("serial")
public abstract class GamePanel extends JPanel{
	
	/**
	 * Result from the method playGame()
	 */
	public abstract Result playGame();
	
	/**
	 * Displays the result
	 */
	public abstract void displayResult(Result result);
	
	/**
	 * String representation
	 */
	public abstract String toString();
}


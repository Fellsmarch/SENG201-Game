
package guiElements;

import javax.swing.JPanel;

/**
 * This class creates the abstract class 'GamePanel', extends JPanel and creates a panel specifically for interactions regarding the games
 * @author Harrison Cook
 * @author Hannah Regan
 */
@SuppressWarnings("serial")
public abstract class GamePanel extends JPanel{
	
	/**
	 * @return the result of the game
	 */
	public abstract Result playGame();
	
	/**
	 * Displays the result of the game to the user
	 * @param result the result to display to the user
	 */
	public abstract void displayResult(Result result);
	
	/**
	 * String representation of the game
	 */
	public abstract String toString();
}


package guiElements;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class GamePanel extends JPanel{
	public abstract Result playGame();
	public abstract void displayResult(Result result);
	public abstract String toString();
}

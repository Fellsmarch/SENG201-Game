package guiElements;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import characters.Hero;
import characters.Villain;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class GuessNumberPanel extends GamePanel {
	
	private boolean moreLucky;
	private Villain villain;
	private JProgressBar resultDisplay;
	private int guessNum = 0;
	private JToggleButton[] numberButtons;
	private int villainsNumber, userGuess;

	/**
	 * Create the panel.
	 */
	public GuessNumberPanel(Hero challenger, Villain villain, boolean moreLucky) {
		this.moreLucky = moreLucky;
		this.villain = villain;
		Random rand = new Random();
		villainsNumber = rand.nextInt(10) + 1;
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow]"));
		
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
		UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
		
		JToggleButton tgl1 = new JToggleButton("1"); JToggleButton tgl2 = new JToggleButton("2"); JToggleButton tgl3 = new JToggleButton("3");
		JToggleButton tgl4 = new JToggleButton("4"); JToggleButton tgl5 = new JToggleButton("5"); JToggleButton tgl6 = new JToggleButton("6");
		JToggleButton tgl7 = new JToggleButton("7"); JToggleButton tgl8 = new JToggleButton("8"); JToggleButton tgl9 = new JToggleButton("9");
		JToggleButton tgl10 = new JToggleButton("10");
		
		add(tgl1, "flowy,cell 0 0,grow"); add(tgl2, "flowy,cell 1 0,grow"); add(tgl3, "flowy,cell 2 0,grow"); 
		add(tgl4, "cell 0 1,grow");
		add(tgl5, "cell 1 1,grow"); add(tgl6, "cell 2 1,grow"); add(tgl7, "cell 0 2,grow"); add(tgl8, "cell 1 2,grow"); 
		add(tgl9, "cell 2 2,grow"); add(tgl10, "cell 3 2,grow");
		 resultDisplay= new JProgressBar();
		 resultDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 resultDisplay.setStringPainted(true);
		 resultDisplay.setForeground(Color.YELLOW);
		 resultDisplay.setString("Result");
		 resultDisplay.setValue(100);
		 add(resultDisplay, "cell 0 3 4 1,grow");
		
		JToggleButton[] buttons = {tgl1, tgl2, tgl3, tgl4, tgl5, tgl6, tgl7, tgl8, tgl9, tgl10};
		numberButtons = buttons;
		
		ButtonGroup numChoice = new ButtonGroup();
		for (JToggleButton button : buttons) {numChoice.add(button);}
//		numChoice.add(tgl1); numChoice.add(tgl2); numChoice.add(tgl3); numChoice.add(tgl4); numChoice.add(tgl5); 
//		numChoice.add(tgl6); numChoice.add(tgl7); numChoice.add(tgl8); numChoice.add(tgl9);
	
		
	}

	@Override
	public Result playGame() {
		for (JToggleButton button : numberButtons) {
			if (button.isSelected()) {userGuess = Integer.parseInt(button.getText()); break;}
		}
		if (userGuess == 0) {return Result.DRAW;}
		if (userGuess == villainsNumber) {return Result.WIN;}
		else if (userGuess > villainsNumber) {//Lower
			if (moreLucky && userGuess - 1 == villainsNumber) {return Result.WIN;}
			else if (guessNum < 1){
				resultDisplay.setString("Lower! - One guess left");
				guessNum++;
				return Result.DRAW;
			} else {return Result.LOSS;}
		} else {
			if (moreLucky && userGuess + 1 == villainsNumber) {return Result.WIN;}
			else if (guessNum < 1) {
				resultDisplay.setString("Higher! - One guess left");
				guessNum++;
				return Result.DRAW;
			} else {return Result.LOSS;}
		}
	}

	@Override
	public void displayResult(Result result) {
		if (userGuess == 0) {
			JOptionPane.showMessageDialog(null, "Please select a number!", "Make Choice", JOptionPane.WARNING_MESSAGE);
		}
		else {
			if (result == Result.WIN) {
				resultDisplay.setForeground(Color.GREEN);
				resultDisplay.setString("Correct! The number was " + userGuess);
			} else if (result == Result.LOSS) {
				resultDisplay.setForeground(Color.RED);
				resultDisplay.setString("Sorry! The correct number was " + villainsNumber);
			}
		}
	}

	@Override
	public String toString() {return "Guess " + villain.getName() + "'s Number";}
}

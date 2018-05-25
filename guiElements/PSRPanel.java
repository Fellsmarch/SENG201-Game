package guiElements;

import java.util.Random;

import javax.swing.ButtonGroup;

import characters.Hero;
import characters.Villain;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Font;

/**
 * This class creates the class 'PSRPanel', extends GamePanel and creates a panel for the Paper, Scissors, Rock game
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */
@SuppressWarnings("serial")
public class PSRPanel extends GamePanel{
	
	/**
	 * The user's choice (paper, scissors or rock)
	 */
	private PSRChoice heroChoice;
	/**
	 * The boolean moreLucky (will increase chances of winning the game if true)
	 */
	private boolean moreLucky;
	
	/**
	 * JProgressBar that visually displays who won the game
	 */
	private JProgressBar resultDisplay;
	
	/**
	 * Constructor -- Create the panel.
	 * @param challenger The hero chosen by the user to verse the villain
	 * @param villain The villain playing the game
	 * @param moreLucky Boolean
	 */
	public PSRPanel(Hero challenger, Villain villain, boolean moreLucky) {
		this.moreLucky = moreLucky;
		setLayout(new MigLayout("", "[grow][grow][grow]", "[][][grow]"));
		
		JToggleButton tglbtnPaper = new JToggleButton("Paper");
		tglbtnPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroChoice = PSRChoice.PAPER;
			}
		});
		add(tglbtnPaper, "cell 0 0,growx");
		
		JToggleButton tglbtnScissors = new JToggleButton("Scissors");
		tglbtnScissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroChoice = PSRChoice.SCISSORS;
			}
		});
		add(tglbtnScissors, "cell 1 0,growx");
		
		JToggleButton tglbtnRock = new JToggleButton("Rock");
		tglbtnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroChoice = PSRChoice.ROCK;
			}
		});
		add(tglbtnRock, "cell 2 0,growx");
		
		ButtonGroup psrBtnGroup = new ButtonGroup();
		psrBtnGroup.add(tglbtnPaper); psrBtnGroup.add(tglbtnScissors); psrBtnGroup.add(tglbtnRock);
		
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
		UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
		
		resultDisplay= new JProgressBar();
		resultDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resultDisplay.setStringPainted(true);
		resultDisplay.setForeground(Color.YELLOW);
		resultDisplay.setString("Result");
		resultDisplay.setValue(100);
		add(resultDisplay, "cell 0 2 3 1,grow");
 
		
		
	}
	
	/**
	 * Plays the game 
	 * @return the result of the game (who won)
	 */
	@Override
	public Result playGame() {
		PSRChoice villainChoice = getRandom();
		if (heroChoice == null) {return Result.DRAW;}
		if (villainChoice.losesTo == heroChoice.name()) {return Result.WIN;}
		else if (villainChoice.name() == heroChoice.losesTo) {
			if (moreLucky) {
				moreLucky = false; //Since each panel only get used for one game 
				return playGame();
			} else {return Result.LOSS;}
			
		}
		else {return Result.DRAW;}
	}
	
	/**
	 * Displays the result (who won)
	 */
	@Override
	public void displayResult(Result result) {
		if (heroChoice == null) {JOptionPane.showMessageDialog(null, "Please select either Paper, Scissors or Rock!", "Make Choice", JOptionPane.WARNING_MESSAGE);}
		else {
			if (result == Result.WIN) {
				resultDisplay.setForeground(Color.GREEN);
				resultDisplay.setString("You Won!");
			} else if (result == Result.LOSS) {
				resultDisplay.setForeground(Color.RED);
				resultDisplay.setString("You Lost!");
			} else {
				resultDisplay.setForeground(Color.YELLOW);
				resultDisplay.setString("It's a Draw!");
			}
		}
	}
	
	/**
	 * toString() for the Paper, Scissors, Rock game
	 * @return a String representation of Paper, Scissors, Rock game
	 */
	@Override
	public String toString() {
		return "Paper, Scissors, Rock";
	}
	
	/**
	 * Villain's "choice", randomised
	 */
	private PSRChoice getRandom() {
		Random rand = new Random();
		return PSRChoice.values()[rand.nextInt(PSRChoice.values().length)];
	}
	
	/**
	 * Enum of the options that can be chosen for this game
	 */
	private enum PSRChoice {
		
		/**
		 * Paper option, loses to scissors 
		 */
		PAPER("SCISSORS"),
		
		/**
		 * Scissors option, loses to rock
		 */
		SCISSORS("ROCK"),
		
		/**
		 * Rock option, loses to paper
		 */
		ROCK("PAPER");
		
		/**
		 * Defines what each throw loses to
		 */
		private String losesTo;
		
		/**
		 * Creates a PSRChoice
		 * @param losesTo Defines what each throw loses to
		 */
		private PSRChoice(String losesTo) {
			this.losesTo = losesTo;
		}
	
	}


}

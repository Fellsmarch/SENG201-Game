package guiElements;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JProgressBar;
import javax.swing.UIManager;

import characters.Hero;
import characters.Villain;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Random;

@SuppressWarnings("serial")
public class DiceRollsPanel extends GamePanel {

	private JProgressBar resultDisplayHero, resultDisplayVillain;
	private boolean moreLucky;
	/**
	 * Create the panel.
	 */
	public DiceRollsPanel(Hero challenger, Villain villain, boolean moreLucky) {
		this.moreLucky = moreLucky;
		setLayout(new MigLayout("", "[grow][grow][grow]", "[][grow]"));
		
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
		UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
		
		JLabel lblHeroResult = new JLabel(challenger.getName() + "'s roll:");
		lblHeroResult.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(lblHeroResult, "cell 0 0,aligny bottom");
		
		JLabel lblVillainResult = new JLabel(villain.getName() + "'s roll:");
		lblVillainResult.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(lblVillainResult, "cell 2 0,alignx center,aligny bottom");
		
		resultDisplayHero = new JProgressBar();
		resultDisplayHero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resultDisplayHero.setStringPainted(true);
		resultDisplayHero.setForeground(Color.YELLOW);
		resultDisplayHero.setString("-");
		resultDisplayHero.setValue(100);
		add(resultDisplayHero, "cell 0 1,grow");
		
		resultDisplayVillain = new JProgressBar();
		resultDisplayVillain.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resultDisplayVillain.setStringPainted(true);
		resultDisplayVillain.setForeground(Color.YELLOW);
		resultDisplayVillain.setString("-");
		resultDisplayVillain.setValue(100);
		add(resultDisplayVillain, "cell 2 1,grow");

	}

	@Override
	public Result playGame() {
		Random rand = new Random();
		Integer challengerRoll = rand.nextInt(6) + 1;
		Integer villainRoll = rand.nextInt(6) + 1;
		resultDisplayHero.setString(challengerRoll.toString());
		resultDisplayVillain.setString(villainRoll.toString());
		if (challengerRoll > villainRoll) {return Result.WIN;}
		else if (moreLucky && challengerRoll == villainRoll) {
			if (villainRoll == 6) {villainRoll = 5;}
			else if (villainRoll == 1) {challengerRoll = 2;}
			else {villainRoll--;}
			resultDisplayHero.setString(challengerRoll.toString());
			resultDisplayVillain.setString(villainRoll.toString());
			return Result.WIN;
		}
		else if (challengerRoll < villainRoll) {return Result.LOSS;}
		else {return Result.DRAW;}
	}

	@Override
	public void displayResult(Result result) {
		if (result == Result.WIN) {
			resultDisplayHero.setForeground(Color.GREEN);
			resultDisplayVillain.setForeground(Color.RED);
		} else if (result == Result.LOSS) {
			resultDisplayHero.setForeground(Color.RED);
			resultDisplayVillain.setForeground(Color.GREEN);
		} else {
			resultDisplayHero.setForeground(Color.YELLOW);
			resultDisplayVillain.setForeground(Color.YELLOW);
		}
		
	}

	@Override
	public String toString() {return "Dice rolls game";}

}

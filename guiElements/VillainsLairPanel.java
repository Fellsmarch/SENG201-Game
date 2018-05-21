package guiElements;
import javax.swing.JPanel;

import characters.Hero;
import characters.Team;
import characters.Villain;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;

import java.awt.Color;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class VillainsLairPanel extends JPanel
	{
//		private RunGamePanel parent;
		private Team team;
		private Villain villain;
		private GamePanel game;
		/**
		 * Create the panel.
		 */
		public VillainsLairPanel(Team team, Villain villain, RunGamePanel parent) { 
			this.team = team;
			this.villain = villain;
			setLayout(new MigLayout("", "[grow]", "[][][][px:n][10px:n][grow]"));
			
			JButton btnNextCity = new JButton("Next City");
			btnNextCity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.nextCity();
				}
			});
//			add(btnNextCity, "cell 0 3");
			
			JLabel lblVillainsName = new JLabel(villain.getName());
			lblVillainsName.setFont(new Font("Tahoma", Font.PLAIN, 25));
			add(lblVillainsName, "flowx,cell 0 0,alignx center");
			
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBackground(Color.BLACK);
			add(separator, "cell 0 1,growx");
			
			JButton btnPlayGame = new JButton("Play Game");
			btnPlayGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Result result = game.playGame();
					//If result == Win do damage
					//If result == Loss take damage
					game.displayResult(result);
				}
			});
			
			
			
			Hero tempHero = team.getHeroList().get(0);
			
			JComboBox comboHeroSelector = new JComboBox();
//			add(comboHeroSelector, "flowx,cell 0 2,growx");
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setForeground(Color.GRAY);
			add(separator_1, "cell 0 4,growx");
			
			
			
//			game = new PSRPanel(tempHero, villain, false);
//			game = new DiceRollsPanel(tempHero, villain, false);
			game = new GuessNumberPanel(tempHero, villain, false);
//			game.add(btnPlayGame, "cell 1 1,grow");
			game.add(btnPlayGame, "cell 0 3,grow"); game.add(btnPlayGame, "cell 2 3,grow");
			
			add(game, "cell 0 5,grow");
			
			JLabel lblGameName = new JLabel(game.toString());
			lblGameName.setFont(new Font("Tahoma", Font.PLAIN, 19));
			add(lblGameName, "cell 0 3,alignx center");
			
			JLabel lblVs = new JLabel("  vs  ");
			lblVs.setFont(new Font("Tahoma", Font.PLAIN, 20));
			add(lblVs, "cell 0 0,alignx center");
			
			JLabel lblTeamName = new JLabel(team.getName());
			lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 25));
			add(lblTeamName, "cell 0 0,alignx center");
			
			JButton btnBeginNextGame = new JButton("Begin next game");
			add(btnBeginNextGame, "cell 0 2");
//			CardLayout cardLayout = new CardLayout();
//			JPanel gameContainer = new JPanel(cardLayout);
			
			}
		
		public void taunt() {
			JOptionPane.showMessageDialog(this, villain.getName() + villain.getTitle() + ": \"" + villain.getTaunt() + "\"", "", JOptionPane.WARNING_MESSAGE);
		}
		public String toString() {return "Villains Lair";}
//		public void setParent(RunGamePanel parent) {this.parent = parent;}
	}

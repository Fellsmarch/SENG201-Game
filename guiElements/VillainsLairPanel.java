package guiElements;

import characters.Hero;
import characters.Team;
import characters.Villain;
import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;

/**
 * This creates a subclass VillainsLairPanel, extends from BuildingPanel and holds all the VillainsLair operations
 *
 * @author Harrison Cook
 * @author Hannah Regan
 */
@SuppressWarnings("serial")
public class VillainsLairPanel extends BuildingPanel
	{
		/**
		 * The team
		 */
		private Team team;
		
		/**
		 * The villain in the lair
		 */
		private Villain villain;
		
		/**
		 * The game panel for the game
		 */
		private GamePanel game;
		
		/** 
		 * The user to select a hero
		 */
		private JComboBox<String> comboHeroSelector;
		
		/**
		 * The selected hero, by the user
		 */
		private Hero selectedHero;
		
		/**
		 * Whether the game has ended or is still going
		 */
		private boolean gameFinished = false;
		
		/**
		 * JButton for the user to select to start the next game
		 */
		private JButton btnBeginNextGame;
		
		/**
		 * JLabel to Display the hero's health
		 */
		private JLabel labelDisplayHP;
		
		/**
		 * Constructor -- Create the panel.
		 * @param team, the current team
		 * @param villain, the villain that the hero will verse
		 * @param parent, the panel where the game will be run from
		 */
		public VillainsLairPanel(Team team, Villain villain, RunGamePanel parent) { 
			this.team = team;
			this.villain = villain;
			
			UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
			UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
			
			setLayout(new MigLayout("", "[470:n,grow,center]", "[][][][][px:n][10px:n][grow,center]"));
			
			JLabel lblTeamName = new JLabel(team.getName());
			lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 25));
			add(lblTeamName, "flowx,cell 0 0,alignx center");
			
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBackground(Color.BLACK);
			add(separator, "cell 0 1,growx");
			
			JProgressBar damageDisplay = new JProgressBar();
			damageDisplay.setStringPainted(true);
			damageDisplay.setValue(100);
			damageDisplay.setString("");
			add(damageDisplay, "cell 0 3,grow");
			damageDisplay.setForeground(Color.LIGHT_GRAY);
			
			JButton btnPlayGame = new JButton("Play Game");
			btnPlayGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Random rand = new Random();
					Result result = game.playGame();
					if (result != null) {
						game.displayResult(result);
						btnPlayGame.setEnabled(false);
						if (result == Result.WIN) {
							int origDamage = rand.nextInt(11) + 30;
							Double modifiedDamage = origDamage * selectedHero.getAttackMod();
							int finalDamage = modifiedDamage.intValue();
							if (selectedHero.getPowerups()[0]) {finalDamage *= 2;} 
							villain.removeHealth(finalDamage);
							if (villain.getHealth() < 1) {
								parent.nextCity(villain);
							} else {
								damageDisplay.setString(selectedHero.getName() + " dealt " + villain.getName() + " " + finalDamage + " damage");
								damageDisplay.setForeground(Color.LIGHT_GRAY);
							}

							gameFinished = true;
							btnBeginNextGame.setEnabled(true);
						}
						else if (result == Result.LOSS) {
							Double modifiedDamage = villain.getDamage() * selectedHero.getDefenseMod();
							int finalDamage = modifiedDamage.intValue();
							if (selectedHero.getPowerups()[1] && rand.nextInt(3) == 1) {
								damageDisplay.setString(selectedHero.getName() + " dodged " + villain.getName() + "'s attack!");
								damageDisplay.setForeground(Color.GREEN);
							} else {
								selectedHero.adjustHealth(-finalDamage);
								if (selectedHero.getCurrentHealth() < 1) {
									boolean gameContinue = team.killHero(selectedHero);
									if (!gameContinue) {
										JOptionPane.showMessageDialog(parent, selectedHero.getName() + " was dealt " + finalDamage +
												" damage by " + villain.getName() + " and died!\n" + team.getName() + 
												" has no heroes left! You failed to save the cities! You Lose!", "You lose!", JOptionPane.WARNING_MESSAGE);
										parent.playAgain();
									} else {
										damageDisplay.setString(selectedHero.getName() + " was dealt " + finalDamage + " damage by " + villain.getName() + " and died!");
										damageDisplay.setForeground(Color.RED);
										updateDisplays();
									}
								} else {
									updateHeroDisplay();
									damageDisplay.setString(selectedHero.getName() + " was dealt " + finalDamage + " damage by " + villain.getName());
									damageDisplay.setForeground(Color.LIGHT_GRAY);
								}
							}
							btnBeginNextGame.setEnabled(true);
							gameFinished = true;
						}
						else {btnPlayGame.setEnabled(true);}
					}
					
				}
			});
			
			
			String[] heroList = team.getHeroNames();
			DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(heroList);
			comboHeroSelector = new JComboBox<String>(heroListModel);
			comboHeroSelector.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateHeroDisplay();
				}
			});
			
			JLabel lblSelectHero = new JLabel("Select Hero:");
			add(lblSelectHero, "flowx,cell 0 2");
			add(comboHeroSelector, "cell 0 2,growx");
			
			
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setForeground(Color.GRAY);
			add(separator_1, "cell 0 5,growx");
			
			JLabel lblGameName = new JLabel();
			lblGameName.setFont(new Font("Tahoma", Font.PLAIN, 19));
			add(lblGameName, "cell 0 4,alignx center");
			
			JLabel lblVs = new JLabel("  vs  ");
			lblVs.setFont(new Font("Tahoma", Font.PLAIN, 20));
			add(lblVs, "cell 0 0,alignx center");
			
			btnBeginNextGame = new JButton("Begin next game");	
			btnBeginNextGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (gameFinished) {
						remove(game);
						validate();
						repaint();
						GameType nextGame = villain.chooseGame();
						game = nextGame.createGame(selectedHero, villain, selectedHero.getPowerups()[2]);
						if (nextGame == GameType.GUESSNUM) {game.add(btnPlayGame, "cell 3 0 1 2,grow");}
						else {game.add(btnPlayGame, "cell 1 1,grow");}
						add(game, "cell 0 6,grow");
						validate();
						repaint();
						lblGameName.setText(game.toString());
						gameFinished = false;
						btnPlayGame.setEnabled(true);
						btnBeginNextGame.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(parent, "You must play the current game before beginning the next one!", "Game not finished", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});
			
			JLabel lblHealth = new JLabel("Health: ");
			add(lblHealth, "cell 0 2");
			
			labelDisplayHP = new JLabel("");
			add(labelDisplayHP, "cell 0 2");
			add(btnBeginNextGame, "cell 0 2");
			
			updateDisplays();
			GameType nextGame = villain.chooseGame();
			game = nextGame.createGame(selectedHero, villain, selectedHero.getPowerups()[2]);
			if (nextGame == GameType.GUESSNUM) {game.add(btnPlayGame, "cell 3 0 1 2,grow");}
			else {game.add(btnPlayGame, "cell 1 1,grow");}
			add(game, "cell 0 6,grow");
			lblGameName.setText(game.toString());
			btnBeginNextGame.setEnabled(false);
						
			JLabel lblVillainsName = new JLabel(villain.getName());
			lblVillainsName.setFont(new Font("Tahoma", Font.PLAIN, 25));
			add(lblVillainsName, "cell 0 0,alignx center");
			
			}
		
		/**
		 * Presents the villain's name and taunt, taunts the hero while the game is in progress
		 */
		public void taunt() {
			JOptionPane.showMessageDialog(this, villain.getName() + villain.getTitle() + ":\n\"" + villain.getTaunt() + "\"", "", JOptionPane.WARNING_MESSAGE);	
			}
		
		/**
		 * @return the string representation of the villains lair
		 */
		public String toString() {return "Villains Lair";}
		
		/**
		 * Updates the hero's display, stats, health
		 */
		public void updateHeroDisplay() {
			selectedHero = (Hero) team.getHeroList().get(comboHeroSelector.getSelectedIndex());
			labelDisplayHP.setText(selectedHero.getCurrentHealth() + "/" + selectedHero.getMaxHealth());
		}
		
		/**
		 * Updates the display, list of heroes, calls to updateHeroDisplay()
		 */
		public void updateDisplays() {
			String[] heroList = team.getHeroNames();
			DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(heroList);
			comboHeroSelector.setModel(heroListModel);
			updateHeroDisplay();
		}
	}

package guiElements;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import characters.Hero;
import characters.Team;
import characters.HeroTypes.TeamPowerupHero;
import items.Powerup;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

/**
 * This creates a subclass PowerupDenPanel, extends from BuildingPanel and holds all the Power Up Den operations
 *
 * @author Harrison Cook
 * @author Hannah Regan
 */
@SuppressWarnings("serial")
public class PowerupDenPanel extends BuildingPanel
	{	
		/**
		 * JComboBox that holds the powerups
		 */
		private JComboBox<Powerup> comboPowerupSelector = new JComboBox<Powerup>();
		
		/**
		 * The team visiting the power up den
		 */
		private Team team;
		
		/**
		 * ComboBoxModel that defines what it contained in the ComboBox
		 */
		private DefaultComboBoxModel<Powerup> powerupModel;
		
		/**
		 * JTextPane to display the hero's stats/characteristics
		 */
		private JTextPane paneHeroStats;
		
		/**
		 * JTextPane display for the active powerups on the heroes
		 */
		private JTextPane panePowerupsActive;
		
		/**
		 * Button to use the powerup
		 */
		private JButton btnUsePowerup;
		
		/**
		 * JComboBox for the user to select the hero for the power up application
		 */
		private JComboBox<String> comboHeroSelector;
		
		/**
		 * JComboBox for when there is no powerups left in the team's inventory
		 */
		private JComboBox<String> comboPowerupEmpty = new JComboBox<String>();
		
		/**
		 * The pane that shows the selected powerup description
		 */
		private JTextPane panePowerupDescription;

		/**
		 * Constructor -- Create the panel.
		 * @param team, the Team
		 */
		public PowerupDenPanel(Team team)
			{
				this.team = team;
			setLayout(new MigLayout("", "[275px:n:275px][275px:n:275px]", "[][][][][grow]"));
			
			JLabel lblPowerupDen = new JLabel("Powerup Den");
			lblPowerupDen.setFont(new Font("Dialog", Font.BOLD, 25));
			add(lblPowerupDen, "cell 0 0 2 1,alignx center");
			
			JSeparator separator = new JSeparator();
			separator.setBackground(Color.BLACK);
			add(separator, "cell 0 1 2 1,growx");
			
			btnUsePowerup = new JButton("Use Powerup");
			btnUsePowerup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Random rand = new Random();
					Hero hero = team.getHeroList().get(comboHeroSelector.getSelectedIndex());
					Powerup powerup = (Powerup) comboPowerupSelector.getSelectedItem();
					if (hero instanceof TeamPowerupHero) {
						if (hero.powerupActive(powerup)) {
							int cont = JOptionPane.showConfirmDialog(null, hero.getName() + " already has " + powerup.getName() + 
									" applied, if you continue there is a chance " + powerup.getName() +
									" will be applied to the whole team. Even if it isn't, the powerup will be consumed.\nDo you want to continue?", "Warning", JOptionPane.YES_NO_OPTION);
							if (cont == JOptionPane.YES_OPTION) {
								if (rand.nextInt(100) < 20) {
									for (Hero ally : team.getHeroList()) {
										ally.setPowerup(powerup, true);
									}
								} else {
									hero.setPowerup(powerup, true);
								}
								team.removeItem(powerup);
								updatePowerupList();
								updateHeroDisplays();
							}
						} else {
							if (rand.nextInt(100) < 20) {
								for (Hero ally : team.getHeroList()) {
									ally.setPowerup(powerup, true);
								}
							} else {
								hero.setPowerup(powerup, true);
							}
							team.removeItem(powerup);
							updatePowerupList();
							updateHeroDisplays();
						}
						
					} else {
						if (!hero.powerupActive(powerup)) {
							hero.setPowerup(powerup, true);
							team.removeItem(powerup);
							updatePowerupList();
							updateHeroDisplays();
						} else {
							JOptionPane.showMessageDialog(null, powerup.toString() + " already active on " + hero.getName(), "Powerup Already Active", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});
			
			String[] heroList = team.getHeroNames();
			DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(heroList);
			comboHeroSelector = new JComboBox<String>(heroListModel);
			comboHeroSelector.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateHeroDisplays();
				}
			});
			add(comboHeroSelector, "cell 0 2,growx");
			add(btnUsePowerup, "cell 0 3 2 1,growx");
			comboPowerupSelector.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (comboPowerupSelector.getItemCount() > 0) {
						Powerup powerup = (Powerup) comboPowerupSelector.getSelectedItem();
						panePowerupDescription.setText(powerup.getName() + " - " + powerup.getDescription());
					} else {panePowerupDescription.setText("");}
				}
			});
			
			add(comboPowerupSelector, "cell 1 2,growx");
			
			paneHeroStats = new JTextPane();
			paneHeroStats.setEditable(false);
			paneHeroStats.setContentType("text/html");
			add(paneHeroStats, "cell 0 4,grow");
			
			panePowerupDescription = new JTextPane();
			panePowerupDescription.setEditable(false);
			add(panePowerupDescription, "flowy,cell 1 4,grow");
			
			panePowerupsActive = new JTextPane();
			panePowerupsActive.setEditable(false);
			add(panePowerupsActive, "cell 1 4,grow");
			
			comboPowerupEmpty.addItem("No powerups!");
			
			updateDisplays();
			}
		
		/**
		 * @return the string representation of the Powerup Den
		 */
		@Override
		public String toString() {return "Powerup Den";}
		
		/**
		 * Updates the powerup list of the Team
		 */
		public void updatePowerupList() {
			Powerup[] powerups = team.getPowerupList().toArray(new Powerup[team.getPowerupList().size()]);
			Set<Powerup> powerupSet = new HashSet<Powerup>(Arrays.asList(powerups));
			powerups = powerupSet.toArray(new Powerup[powerupSet.size()]);
			
			powerupModel = new DefaultComboBoxModel<Powerup>(powerups);
			comboPowerupSelector.setModel(powerupModel);
			if (powerups.length < 1) {
				remove(comboPowerupSelector);
				add(comboPowerupEmpty, "cell 1 2,growx");
				btnUsePowerup.setEnabled(false);
				repaint();
				panePowerupDescription.setText("");
			} else {
				btnUsePowerup.setEnabled(true);
				remove(comboPowerupEmpty);
				add(comboPowerupSelector, "cell 1 2,growx");
				Powerup powerup = (Powerup) comboPowerupSelector.getSelectedItem();
				panePowerupDescription.setText(powerup.getName() + " - " + powerup.getDescription());
			}
		}
		
		/**
		 * Updates the hero's active powerups
		 */
		private void updateHeroDisplays() {
			Hero selectedHero = team.getHeroList().get(comboHeroSelector.getSelectedIndex());
			paneHeroStats.setText(selectedHero.toString());
			boolean[] activePowerups = selectedHero.getPowerups();
			String[] activePowerupsStr = {"Not active", "Not active", "Not active"};
			for (int i = 0; i < 3; i++) {if (activePowerups[i]) {activePowerupsStr[i] = "Active";}}
			String toAdd = "";
			toAdd += "Infinity Edge: " + activePowerupsStr[0] + "\n";
			toAdd += "Ninja Tabi: " + activePowerupsStr[1] + "\n";
			toAdd += "Kage's Lucky Pick: " + activePowerupsStr[2] + "\n";
			panePowerupsActive.setText(toAdd);
		}
		
		
		/**
		 * Updates the display, shows the heros, calls to updatePowerupList() and updateHeroDisplays()
		 */
		@Override
		public void updateDisplays() {
			String[] heroList = team.getHeroNames();
			DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(heroList);
			comboHeroSelector.setModel(heroListModel);
			updatePowerupList();
			updateHeroDisplays();
		}
	}

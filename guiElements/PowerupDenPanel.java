package guiElements;
import javax.swing.JPanel;
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

@SuppressWarnings("serial")
public class PowerupDenPanel extends JPanel
	{
		private JComboBox<Powerup> comboPowerupSelector = new JComboBox<Powerup>();
		private Team team;
		private DefaultComboBoxModel<Powerup> powerupModel;
		private JTextPane paneHeroStats;
		private JTextPane panePowerupsActive;
		private JButton btnUsePowerup;
		private JComboBox<String> comboHeroSelector, comboPowerupEmpty = new JComboBox<String>();

		/**
		 * Create the panel.
		 */
		public PowerupDenPanel(Team team)
			{
				this.team = team;
			setLayout(new MigLayout("", "[235px:n][235px:n]", "[][][][][grow]"));
			
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
			
			//I will need to update this in case someone dies
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
			
			add(comboPowerupSelector, "cell 1 2,growx");
			
			paneHeroStats = new JTextPane();
			paneHeroStats.setEditable(false);
			paneHeroStats.setContentType("text/html");
			add(paneHeroStats, "cell 0 4,grow");
			
			panePowerupsActive = new JTextPane();
			panePowerupsActive.setEditable(false);
			add(panePowerupsActive, "cell 1 4,grow");
			
			comboPowerupEmpty.addItem("No powerups!");
			
			updatePowerupList();
			updateHeroDisplays();
			
			}
		public String toString() {return "Powerup Den";}
		public void updatePowerupList() {
			Powerup[] powerups = team.getPowerupList().toArray(new Powerup[team.getPowerupList().size()]);
			Set<Powerup> powerupSet = new HashSet<Powerup>(Arrays.asList(powerups));
			powerups = powerupSet.toArray(new Powerup[powerupSet.size()]);
			
			powerupModel = new DefaultComboBoxModel<Powerup>(powerups);
			comboPowerupSelector.setModel(powerupModel);
			if (powerups.length < 1) {
//				String[] empty = {"No powerups!"};
				remove(comboPowerupSelector);
				add(comboPowerupEmpty, "cell 1 2,growx");
				btnUsePowerup.setEnabled(false);
			} else {
				btnUsePowerup.setEnabled(true);
				remove(comboPowerupEmpty);
				add(comboPowerupSelector, "cell 1 2,growx");
			}
		}
		
		//I need to call this every time the user goes to this buildings
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
	}

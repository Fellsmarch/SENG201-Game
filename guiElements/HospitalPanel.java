package guiElements;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Timer;
import javax.swing.UIManager;

import characters.Hero;
import characters.Team;
import items.HealingItem;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JButton;

/**
 * This creates a subclass HospitalPanel, extends from BuildingPanel and holds all the Hospital operations
 *
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

@SuppressWarnings("serial")
public class HospitalPanel extends BuildingPanel
	{	
		/**
		 * The countdown timer
		 */
		private Timer timer;
		
		/**
		 * The time left before the timer finishes
		 */
		private int timeLeft;
		
		/**
		 * The amount of healing left to do
		 */
		private int healsLeft;
		
		/**
		 * The timer increments
		 */
		private int timeTick;
		
		/**
		 * JComboBox array displaying Heroes as Strings, to select Hero to view
		 */
		private JComboBox<String> comboHeroSelector;
		
		/**
		 * A pane listing the hero stats
		 */
		private JTextPane paneHeroStats;
		
		/**
		 * A pane listing the potion descriptions
		 */
		private JTextPane panePotionInfo;
		
		/**
		 * The Team 
		 */
		private Team team;
		
		/**
		 * JComboBox that stores Healing Items for the user to select
		 */
		private JComboBox<HealingItem> comboPotionSelector = new JComboBox<HealingItem>();
		
		/**
		 * The hero to use a healing item, chosen by user
		 */
		private Hero heroToHeal;
		
		/**
		 * The hero that can be selected by the user eg to view stats
		 */
		private Hero selectedHero;
		
		/**
		 * JButton to begin healing
		 */
		private JButton btnBeginHeal;
		
		/**
		 * Double that shows the progress of the healing
		 */
		private Double healProgress = 0.0;
		
		/**
		 * Boolean that determines whether the hero is in the middle of consuming a healing item
		 */
		private boolean currentlyHealing;
		
		/**
		 * Constructor -- Create the panel.
		 * @param team, The current team "at the hospital"
		 */
		public HospitalPanel(Team team)
			{
				this.team = team;
				setLayout(new MigLayout("", "[275px:n:275px][275px:n:275px]", "[][][][][grow][grow]"));
				
				JLabel lblHospital = new JLabel("Hospital");
				lblHospital.setFont(new Font("Dialog", Font.BOLD, 25));
				add(lblHospital, "cell 0 0 2 1,alignx center");
				
				JSeparator separator = new JSeparator();
				separator.setForeground(Color.BLACK);
				separator.setBackground(Color.BLACK);
				add(separator, "cell 0 1 2 1,growx");
				
				
				//I will need to update this in case someone dies
				String[] heroList = team.getHeroNames();
				DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(heroList);
				comboHeroSelector = new JComboBox<String>(heroListModel);
				comboHeroSelector.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateHealingPotions();
						updatePotionInfo();
						updateHeroDisplays();
					}
				});
				
				panePotionInfo = new JTextPane();
				panePotionInfo.setEditable(false);
				add(panePotionInfo, "cell 1 4,grow");
				
				add(comboHeroSelector, "cell 0 2,growx");
				comboPotionSelector.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updatePotionInfo();
					}
				});
				add(comboPotionSelector, "cell 1 2,growx");
				
				UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
				UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
				
				JProgressBar healProgressBar = new JProgressBar();
				healProgressBar.setStringPainted(true);
				healProgressBar.setForeground(Color.GREEN);
				
				paneHeroStats = new JTextPane();
				paneHeroStats.setEditable(false);
				paneHeroStats.setContentType("text/html");
				add(paneHeroStats, "cell 0 4 1 2,grow");
				
				
				add(healProgressBar, "flowx,cell 1 5,grow");
				
				ActionListener countDown = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Update progress bar
						Double toDivide= (double) (heroToHeal.getRecovery() * 10);
						healProgress += 25 / toDivide; // 25% / recoveryRate * 10 (to get to 1/10 of a second)
						healProgressBar.setValue(healProgress.intValue());
		
						timeTick--;
						if (timeTick <= 0) { //Every one second
							timeTick = 10;
							timeLeft--;
							if (timeLeft <= 0) { //If the recovery time has elapsed, heal
								timeLeft = heroToHeal.getRecovery();
								healsLeft--;
								healHero();
								if (healsLeft <= 0) {
									timer.stop();
									healProgressBar.setString("Heal Complete!");
									updateDisplays();
									currentlyHealing = false;
								}
							}
						}
					}};
					timer = new Timer(100, countDown); //Every 1/10 of a second
				
				
					btnBeginHeal = new JButton("Use Potion");
					btnBeginHeal.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (selectedHero.getCurrentHealth() >= selectedHero.getMaxHealth()) {
								JOptionPane.showMessageDialog(null, "You cannot use a potion on " + selectedHero.getName() + " since they are already at full health!", "Hero at full health already", JOptionPane.WARNING_MESSAGE);
							} else if(currentlyHealing) {
								JOptionPane.showMessageDialog(null, "Healing already in progress!", "Healing in progress", JOptionPane.WARNING_MESSAGE);
							} else {
								HealingItem potion = (HealingItem) comboPotionSelector.getSelectedItem();
								btnBeginHeal.setEnabled(false);
								team.removeItem(potion);
								currentlyHealing = true;
								heroToHeal = selectedHero;					//Hero to heal
								healsLeft = potion.getHealingAmount(); 		//Number of 25% heals
								timeLeft = heroToHeal.getRecovery(); 		//Recovery rate = number of seconds for one 25% heal to take
								healProgressBar.setMaximum(healsLeft * 25);	//The number of ticks to add to the progress bar (between 25-100)
								healProgressBar.setString("Heal in Progress");
								healProgress = 0.0;							//The progress made so far on the progress bar
								timeTick = 10;
								timer.start();
								updateHealingPotions();
								updatePotionInfo();
								updateHeroDisplays();
							}
						}
					});
					add(btnBeginHeal, "cell 0 3 2 1,growx");
					
					//For testing
//					JButton btnDamage = new JButton("damage");
//					btnDamage.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							team.getHeroList().get(0).adjustHealth(-13);
//							updateHeroDisplays();
//						}
//					});
//					add(btnDamage, "cell 1 4");
					updateDisplays();
			}
		
		/**
		 * The descriptions of healing items that the team currently owns
		 */
		private void updatePotionInfo() {
			if (team.getHealingList().size() > 0) {
				HealingItem potion = (HealingItem) comboPotionSelector.getSelectedItem();
				String toAdd = potion.getName();
				toAdd += "\n  - " + potion.getDescription();
				panePotionInfo.setText(toAdd);
			} else {panePotionInfo.setText("");}
			
		}
		
		/**
		 * Heals the heroToHeal 
		 */
		private void healHero() {
			Double toHeal = heroToHeal.getMaxHealth() * 0.25;
			heroToHeal.adjustHealth(toHeal.intValue());
			updateHeroDisplays();
		}
		
		/**
		 * Updates the hero stats
		 */
		private void updateHeroDisplays() { 
			selectedHero = (Hero) team.getHeroList().get(comboHeroSelector.getSelectedIndex());
		    paneHeroStats.setText(selectedHero.toString());
		}
		
		/**
		 * Updates what healing items the Team owns
		 */
		public void updateHealingPotions() {
			HealingItem[] potions = team.getHealingList().toArray(new HealingItem[team.getHealingList().size()]); //This doesn't display the number of each item they have, it will only ever display 3 items in the combobox
			Set<HealingItem> potionsSet = new HashSet<HealingItem>(Arrays.asList(potions));
			if (potionsSet.size() < 1) {
				btnBeginHeal.setEnabled(false);
				potions = new HealingItem[1];
				potions[0] = new HealingItem("No healing potions!", "No healing potions!", 0, 0);
			} else {
				potions = potionsSet.toArray(new HealingItem[potionsSet.size()]);
				btnBeginHeal.setEnabled(true);
			}
			DefaultComboBoxModel<HealingItem> potionModel = new DefaultComboBoxModel<HealingItem>(potions);
			comboPotionSelector.setModel(potionModel);
		}
		
		/**
		 * Updates all the other displays
		 */
		public void updateDisplays() {
			String[] heroList = team.getHeroNames();
			DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(heroList);
			comboHeroSelector.setModel(heroListModel);
			updateHealingPotions();
			updatePotionInfo();
			updateHeroDisplays();
		}
		
		public String toString() {return "Hospital";}

}

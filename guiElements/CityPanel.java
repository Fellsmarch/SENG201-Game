package guiElements;
/**
 * Holds all four buildings inside of it
 */
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import characters.Hero;
import characters.Team;
import characters.Villain;
import items.Item;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;

@SuppressWarnings("serial")
public class CityPanel extends JPanel
	{
		private CardLayout cardLayout = new CardLayout();
		private JPanel container = new JPanel(cardLayout);
		private JPanel[] directionList = new JPanel[4];
		private Villain villain;
		private JPanel north, east, south, west;
		private JButton btnNorth, btnEast, btnSouth, btnWest;
		private JButton btnUseMap;
		private JTextPane paneHeroStats;
		private Team team;
		private JComboBox<String> comboHeroSelector;
		private JTextPane paneTeamInventory;
		private JLabel labelCity;
		private JSeparator separator;
		
		/**
		 * Create the panel.
		 */
		public CityPanel(ArrayList<JPanel> buildings, Villain villain, Team team, String cityTitle) //ArrayList so I cna remove objects when randomizing directions
			{
				this.team = team;
				add(container);
				JPanel homeBase = new JPanel();
				container.add(homeBase, "Home Base");
				
				cardLayout.show(container, "Home Base");
				homeBase.setLayout(new MigLayout("", "[grow][grow][grow]", "[][][][][][][][grow]"));
				
				
				CardLayout buildingCards = new CardLayout();
				JPanel buildingContainer = new JPanel(buildingCards);
				JPanel goBackScreen = new JPanel();
				goBackScreen.setLayout(new BorderLayout(0, 0));
				goBackScreen.add(buildingContainer, BorderLayout.CENTER);
				container.add(goBackScreen, "Go Back Screen");
				
				
				JButton btnReturnToHome = new JButton("Return to home base");
				btnReturnToHome.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cardLayout.show(container, "Home Base");
						Random rand = new Random();
						if (rand.nextInt(8) == 0) { //Do random event
							if (rand.nextDouble() < team.getEventChance()) { //Give team a gift --> need to change team
								int itemType = rand.nextInt(3);
								Item[] chosenType =  ((ShopPanel) buildings.get(0)).getItems()[itemType];
								Item chosenItem = chosenType[rand.nextInt(chosenType.length)];
								team.addItem(chosenItem);
								JOptionPane.showMessageDialog(null, "Urf appears and graces your team with a free item: " + chosenItem.getName() + "!", "A Gift From Urf", JOptionPane.WARNING_MESSAGE);
							} else { //Steal item from team
								ArrayList<Item> inventory = team.getItemInventory();
								if (inventory.size() < 1) {
									JOptionPane.showMessageDialog(null, "Urf appears and slaps your team with his spatula! Luckily you are very poor and had nothing for Urf to steal", "A Slap From Urf", JOptionPane.WARNING_MESSAGE);
								} else {
									Item chosenItem = inventory.get(rand.nextInt(inventory.size()));
									team.removeItem(chosenItem);
									JOptionPane.showMessageDialog(null, "Urf appears and slaps your team with his spatula! You lost an item: " + chosenItem.getName() + "!", "A Slap From Urf", JOptionPane.WARNING_MESSAGE);
								}
							}
						}
					}
				});
				goBackScreen.add(btnReturnToHome, BorderLayout.SOUTH);
				
				btnNorth = new JButton("North");
				btnNorth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (north instanceof VillainsLairPanel) {
							if (beginFight()) {
								buildingCards.show(buildingContainer, "North");
								cardLayout.show(container, "Go Back Screen");
								goBackScreen.remove(btnReturnToHome);
//								btnReturnToHome.setEnabled(false);
							}
						} else {
							if (north instanceof PowerupDenPanel) {((PowerupDenPanel) north).updatePowerupList();}
							else if (north instanceof ShopPanel) {((ShopPanel) north).updateTeamInventory();}
							else if (north instanceof HospitalPanel) {((HospitalPanel) north).update();}
							buildingCards.show(buildingContainer, "North");
							cardLayout.show(container, "Go Back Screen");
						}
					}
				});
				
				labelCity = new JLabel(cityTitle);
				labelCity.setFont(new Font("Dialog", Font.BOLD, 25));
				homeBase.add(labelCity, "cell 0 0 3 1,alignx center");
				
				separator = new JSeparator();
				separator.setForeground(Color.BLACK);
				separator.setBackground(Color.BLACK);
				homeBase.add(separator, "cell 0 1 3 1,growx");
				homeBase.add(btnNorth, "cell 1 2,growx");
				
				btnEast = new JButton("East");
				btnEast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (east instanceof VillainsLairPanel) {
							if (beginFight()) {
								buildingCards.show(buildingContainer, "East");
								cardLayout.show(container, "Go Back Screen");
								goBackScreen.remove(btnReturnToHome);
//								btnReturnToHome.setEnabled(false);
							}
						} else {
							if (east instanceof PowerupDenPanel) {((PowerupDenPanel) east).updatePowerupList();}
							else if (east instanceof ShopPanel) {((ShopPanel) east).updateTeamInventory();}
							else if (east instanceof HospitalPanel) {((HospitalPanel) east).update();}
							buildingCards.show(buildingContainer, "East");
							cardLayout.show(container, "Go Back Screen");
						}
					}
				});
				homeBase.add(btnEast, "cell 2 3,growx");
				
				btnSouth = new JButton("South");
				btnSouth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (south instanceof VillainsLairPanel) {
							if (beginFight()) {
								buildingCards.show(buildingContainer, "South");
								cardLayout.show(container, "Go Back Screen");
								goBackScreen.remove(btnReturnToHome);
//								btnReturnToHome.setEnabled(false);
							}
						} else {
							if (south instanceof PowerupDenPanel) {((PowerupDenPanel) south).updatePowerupList();}
							else if (south instanceof ShopPanel) {((ShopPanel) south).updateTeamInventory();}
							else if (south instanceof HospitalPanel) {((HospitalPanel) south).update();}
							buildingCards.show(buildingContainer, "South");
							cardLayout.show(container, "Go Back Screen");
						}				
					}
				});
				homeBase.add(btnSouth, "cell 1 4,growx");
				
				btnWest = new JButton("West");
				btnWest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (west instanceof VillainsLairPanel) {
							if (beginFight()) {
								buildingCards.show(buildingContainer, "West");
								cardLayout.show(container, "Go Back Screen");
								goBackScreen.remove(btnReturnToHome);
//								btnReturnToHome.setEnabled(false);
							}
						} else {
							if (west instanceof PowerupDenPanel) {((PowerupDenPanel) west).updatePowerupList();}
							else if (west instanceof ShopPanel) {((ShopPanel) west).updateTeamInventory();}
							else if (west instanceof HospitalPanel) {((HospitalPanel) west).update();}
							buildingCards.show(buildingContainer, "West");
							cardLayout.show(container, "Go Back Screen");
						}
					}
				});
				homeBase.add(btnWest, "cell 0 3,alignx right");
				
				DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(team.getHeroNames());
				comboHeroSelector = new JComboBox<String>(heroListModel);
				comboHeroSelector.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateHeroDisplays();
					}
				});
				homeBase.add(comboHeroSelector, "cell 0 6 2 1,growx");
				
				btnUseMap = new JButton("Use map");
				btnUseMap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						useMap();
					}
				});
				homeBase.add(btnUseMap, "cell 2 6");
				
				paneHeroStats = new JTextPane();
				paneHeroStats.setContentType("text/html");
				homeBase.add(paneHeroStats, "cell 0 7 2 1,grow");
				
				paneTeamInventory = new JTextPane();
				homeBase.add(paneTeamInventory, "cell 2 7,grow");
				
				this.villain = villain;
				Random rand = new Random();
				ArrayList<Integer> elementsAdded = new ArrayList<Integer>();
				int directionIndex = 0;
				for (int i = 0; i < 4; i++) { 									//I did it this way because otherwise we'd need to duplicate the buildings list
					boolean addSuccess = false;									//so we didn't alter the original list
					while (!addSuccess) {
						int toAdd = rand.nextInt(4);
						if (!elementsAdded.contains(toAdd)) {
							elementsAdded.add(toAdd);
							directionList[directionIndex] = buildings.get(toAdd);
							directionIndex++;
							addSuccess = true;
						}
					}
				}
				north = directionList[0]; east = directionList[1];
				south = directionList[2]; west = directionList[3];
				
				buildingContainer.add(north, "North"); buildingContainer.add(east, "East");
				buildingContainer.add(south, "South"); buildingContainer.add(west, "West");
				
				updateHeroDisplays();
			}
		
		
		public void useMap() {
			btnNorth.setText(north.toString());
			btnEast.setText(east.toString());
			btnSouth.setText(south.toString());
			btnWest.setText(west.toString());
		}
		
		
		public void updateHeroDisplays() { 
			Hero selectedHero = (Hero) team.getHeroList().get(comboHeroSelector.getSelectedIndex());
		    paneHeroStats.setText(selectedHero.toString());
		}
		
		private boolean beginFight(){
			String[] options = {"Yes", "Go back"};
			int userChoice = JOptionPane.showOptionDialog(this, "Do you want to begin the fight with " + villain.getName() + villain.getTitle() + "?", "Fight Villain?", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (userChoice == 0) {
				return true;
			} else {return false;}
		}
		
		

	}

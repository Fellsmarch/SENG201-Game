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
import items.Map;

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
		private BuildingPanel[] directionList = new BuildingPanel[4];
		private Villain villain;
		private BuildingPanel north, east, south, west;
		private JButton btnNorth, btnEast, btnSouth, btnWest, btnUseMap;
		private Team team;
		private JComboBox<String> comboHeroSelector;
		private JTextPane paneTeamInventory, paneHeroStats;
		private JLabel labelCity;
		private JSeparator separator;
		
		/**
		 * Create the panel.
		 */
		public CityPanel(ArrayList<BuildingPanel> buildings, Villain villain, Team team, String cityTitle, Map map) //ArrayList so I cna remove objects when randomizing directions
			{
				this.team = team;
				add(container);
				JPanel homeBase = new JPanel();
				container.add(homeBase, "Home Base");
				
				cardLayout.show(container, "Home Base");
				homeBase.setLayout(new MigLayout("", "[150px:n][150px:n][150px:n]", "[][][grow][grow][grow][][][grow]"));
				
				
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
						updateHeroDisplays();
					}
				});
				goBackScreen.add(btnReturnToHome, BorderLayout.SOUTH);
				
				btnNorth = new JButton("North");
				btnNorth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (north instanceof VillainsLairPanel) {
							if (beginFight()) {
								north.updateDisplays();
								buildingCards.show(buildingContainer, "North");
								cardLayout.show(container, "Go Back Screen");
								goBackScreen.remove(btnReturnToHome);
//								btnReturnToHome.setEnabled(false);
							}
						} else {
							north.updateDisplays();
//							if (north instanceof PowerupDenPanel) {((PowerupDenPanel) north).updatePowerupList();}
//							else if (north instanceof ShopPanel) {((ShopPanel) north).updateTeamInventory();}
//							else if (north instanceof HospitalPanel) {((HospitalPanel) north).update();}
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
				homeBase.add(btnNorth, "cell 1 2,grow");
				
				btnEast = new JButton("East");
				btnEast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (east instanceof VillainsLairPanel) {
							if (beginFight()) {
								east.updateDisplays();
								buildingCards.show(buildingContainer, "East");
								cardLayout.show(container, "Go Back Screen");
								goBackScreen.remove(btnReturnToHome);
//								btnReturnToHome.setEnabled(false);
							}
						} else {
							east.updateDisplays();
							buildingCards.show(buildingContainer, "East");
							cardLayout.show(container, "Go Back Screen");
						}
					}
				});
				homeBase.add(btnEast, "cell 2 3,grow");
				
				btnSouth = new JButton("South");
				btnSouth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (south instanceof VillainsLairPanel) {
							if (beginFight()) {
								south.updateDisplays();
								buildingCards.show(buildingContainer, "South");
								cardLayout.show(container, "Go Back Screen");
								goBackScreen.remove(btnReturnToHome);
//								btnReturnToHome.setEnabled(false);
							}
						} else {
							south.updateDisplays();
							buildingCards.show(buildingContainer, "South");
							cardLayout.show(container, "Go Back Screen");
						}				
					}
				});
				homeBase.add(btnSouth, "cell 1 4,grow");
				
				btnWest = new JButton("West");
				btnWest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (west instanceof VillainsLairPanel) {
							if (beginFight()) {
								west.updateDisplays();
								buildingCards.show(buildingContainer, "West");
								cardLayout.show(container, "Go Back Screen");
								goBackScreen.remove(btnReturnToHome);
//								btnReturnToHome.setEnabled(false);
							}
						} else {
							west.updateDisplays();
							buildingCards.show(buildingContainer, "West");
							cardLayout.show(container, "Go Back Screen");
						}
					}
				});
				homeBase.add(btnWest, "cell 0 3,grow");
				
				DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(team.getHeroNames());
				comboHeroSelector = new JComboBox<String>(heroListModel);
				comboHeroSelector.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Hero selectedHero = (Hero) team.getHeroList().get(comboHeroSelector.getSelectedIndex());
					    paneHeroStats.setText(selectedHero.toString());
					}
				});
				homeBase.add(comboHeroSelector, "cell 0 6 2 1,growx");
				
				btnUseMap = new JButton("Use map");
				btnUseMap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (team.hasMap(map)) {
							team.removeItem(map);
							btnNorth.setText(north.toString());
							btnEast.setText(east.toString());
							btnSouth.setText(south.toString());
							btnWest.setText(west.toString());
						} else {
							JOptionPane.showMessageDialog(null, "You do not have the map for this city! Buy it from the shop", "No city map", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				homeBase.add(btnUseMap, "cell 2 6,growx");
				
				paneHeroStats = new JTextPane();
				paneHeroStats.setEditable(false);
				paneHeroStats.setContentType("text/html");
				homeBase.add(paneHeroStats, "cell 0 7 2 1,grow");
				
				paneTeamInventory = new JTextPane();
				paneTeamInventory.setEditable(false);
				paneTeamInventory.setContentType("text/html");
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
				Hero selectedHero = (Hero) team.getHeroList().get(comboHeroSelector.getSelectedIndex());
			    paneHeroStats.setText(selectedHero.toString());
				
			}
		
		
		public void updateHeroDisplays() {
			String[] heroList = team.getHeroNames();
			DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(heroList);
			comboHeroSelector.setModel(heroListModel);
		    paneTeamInventory.setText(team.getInventory());
		    repaint();
		}
		
		private boolean beginFight() {
			String[] options = {"Yes", "Go back"};
			int userChoice = JOptionPane.showOptionDialog(this, "Do you want to begin the fight with " + villain.getName() + villain.getTitle() + "?", "Fight Villain?", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (userChoice == 0) {
				return true;
			} else {return false;}
		}
		
		

	}

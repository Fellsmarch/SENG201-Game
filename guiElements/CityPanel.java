package guiElements;
/**
 * Holds all four buildings inside of it
 */
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import characters.Team;
import characters.Villain;
import commandLineElements.Building;
import commandLineElements.VillainsLair;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class CityPanel extends JPanel
	{
		private CardLayout cardLayout = new CardLayout();
		private JPanel container = new JPanel(cardLayout);
		private JPanel[] directionList = new JPanel[4];
		private Villain villain;
		private JPanel north, east, south, west;
		JButton btnNorth, btnEast, btnSouth, btnWest;
		private JButton btnUseMap;
		
		/**
		 * Create the panel.
		 */
		public CityPanel(ArrayList<JPanel> buildings, Villain villain, RunGamePanel parent) //ArrayList so I cna remove objects when randomizing directions
			{
				((VillainsLairPanel) buildings.get(1)).setParent(parent);
				add(container);
				JPanel homeBase = new JPanel();
				container.add(homeBase, "Home Base");
				
				cardLayout.show(container, "Home Base");
				homeBase.setLayout(new MigLayout("", "[][][][]", "[][][][]"));
				
				
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
							}
						} else {
							if (north instanceof PowerupDenPanel) {
								((PowerupDenPanel) north).updatePowerupList();
							}
							buildingCards.show(buildingContainer, "North");
							cardLayout.show(container, "Go Back Screen");
						}
					}
				});
				homeBase.add(btnNorth, "cell 1 0");
				
				btnEast = new JButton("East");
				btnEast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (east instanceof VillainsLairPanel) {
							if (beginFight()) {
								buildingCards.show(buildingContainer, "East");
								cardLayout.show(container, "Go Back Screen");
							}
						} else {
							if (east instanceof PowerupDenPanel) {
								((PowerupDenPanel) east).updatePowerupList();
							}
							buildingCards.show(buildingContainer, "East");
							cardLayout.show(container, "Go Back Screen");
						}
					}
				});
				homeBase.add(btnEast, "cell 2 1");
				
				btnSouth = new JButton("South");
				btnSouth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (south instanceof VillainsLairPanel) {
							if (beginFight()) {
								buildingCards.show(buildingContainer, "South");
								cardLayout.show(container, "Go Back Screen");
							}
						} else {
							if (south instanceof PowerupDenPanel) {
								((PowerupDenPanel) south).updatePowerupList();
							}
							buildingCards.show(buildingContainer, "South");
							cardLayout.show(container, "Go Back Screen");
						}				
					}
				});
				homeBase.add(btnSouth, "cell 1 2");
				
				btnWest = new JButton("West");
				btnWest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (west instanceof VillainsLairPanel) {
							if (beginFight()) {
								buildingCards.show(buildingContainer, "West");
								cardLayout.show(container, "Go Back Screen");
							}
						} else {
							if (west instanceof PowerupDenPanel) {
								((PowerupDenPanel) west).updatePowerupList();
							}
							buildingCards.show(buildingContainer, "West");
							cardLayout.show(container, "Go Back Screen");
						}
					}
				});
				homeBase.add(btnWest, "cell 0 1");
				
				btnUseMap = new JButton("Use map");
				btnUseMap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						useMap();
					}
				});
				homeBase.add(btnUseMap, "cell 3 3");
				
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
			}
		
		
		public void useMap() {
			btnNorth.setText(north.toString());
			btnEast.setText(east.toString());
			btnSouth.setText(south.toString());
			btnWest.setText(west.toString());
		}
		
		
		
//		public boolean goNorth(Team team) {
//			if(directionList[0] instanceof VillainsLair) {
//				((VillainsLair) directionList[0]).setVillain(villain);
//				return directionList[0].goTo(team);
//			} else {
//				directionList[0].goTo(team);
//				return false;
//			}
//		}
//		
//		public boolean goEast(Team team) {
//			if(directionList[1] instanceof VillainsLair) {
//				((VillainsLair) directionList[1]).setVillain(villain);
//				return directionList[1].goTo(team);
//			} else {
//				directionList[1].goTo(team);
//				return false;
//			}
//		}
//		
//		public boolean goSouth(Team team) {
//			if(directionList[2] instanceof VillainsLair) {
//				((VillainsLair) directionList[2]).setVillain(villain);
//				return directionList[2].goTo(team);
//			} else {
//				directionList[2].goTo(team);
//				return false;
//			}
//		}
//		
//		public boolean goWest(Team team) {
//			if(directionList[3] instanceof VillainsLair) {
//				((VillainsLair) directionList[3]).setVillain(villain);
//				return directionList[3].goTo(team);
//			} else {
//				directionList[3].goTo(team);
//				return false;
//			}
//		}
		
		private boolean beginFight(){
			String[] options = {"Yes", "Go back"};
			int userChoice = JOptionPane.showOptionDialog(this, "Do you want to begin the fight with " + villain.getName() + "?", "Fight Villain?", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (userChoice == 0) {
				return true;
			} else {return false;}
		}
		
		

	}

/**
 * Holds all four buildings inside of it
 */
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CityPanel extends JPanel
	{
		private Building[] directionList = new Building[4];
		private Villain villain;
		private VillainsLairPanel villainsLairPanel; //Add a continue button from run game panel?
		private ShopPanel shopPanel;
		private PowerupDenPanel powerupDenPanel;
		private HospitalPanel hospitalPanel;
		
		/**
		 * Create the panel.
		 */
		public CityPanel(ArrayList<Building> buildings, Villain villain)
			{
				this.villain = villain;
				
				JButton btnNewButton = new JButton("New button");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(beginFight());
					}
				});
				add(btnNewButton);
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
			}
		
		public boolean goNorth(Team team) {
			if(directionList[0] instanceof VillainsLair) {
				((VillainsLair) directionList[0]).setVillain(villain);
				return directionList[0].goTo(team);
			} else {
				directionList[0].goTo(team);
				return false;
			}
		}
		
		public boolean goEast(Team team) {
			if(directionList[1] instanceof VillainsLair) {
				((VillainsLair) directionList[1]).setVillain(villain);
				return directionList[1].goTo(team);
			} else {
				directionList[1].goTo(team);
				return false;
			}
		}
		
		public boolean goSouth(Team team) {
			if(directionList[2] instanceof VillainsLair) {
				((VillainsLair) directionList[2]).setVillain(villain);
				return directionList[2].goTo(team);
			} else {
				directionList[2].goTo(team);
				return false;
			}
		}
		
		public boolean goWest(Team team) {
			if(directionList[3] instanceof VillainsLair) {
				((VillainsLair) directionList[3]).setVillain(villain);
				return directionList[3].goTo(team);
			} else {
				directionList[3].goTo(team);
				return false;
			}
		}
		
		private boolean beginFight(){
			String[] options = {"Yes", "Go back"};
			int userChoice = JOptionPane.showOptionDialog(this, "Do you want to begin the fight with " + villain.getName() + "?", "Fight Villain?", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (userChoice == 0) {
				return true;
			} else {return false;}
		}
		
		

	}

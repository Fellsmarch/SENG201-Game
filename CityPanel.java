/**
 * Holds all four buildings inside of it
 */
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

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
		
		

	}

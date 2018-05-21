package guiElements;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

import characters.SuperVillain;
import characters.Team;
import characters.Villain;
import commandLineElements.Building;
import commandLineElements.DiceRollsGame;
import commandLineElements.Game;
import commandLineElements.GuessNumberGame;
import commandLineElements.PaperScissorsRockGame;
import commandLineElements.Shop;
import items.Map;

@SuppressWarnings("serial")
public class RunGamePanel extends JPanel
	{
		private CardLayout cardLayout = new CardLayout();
		private JPanel container = new JPanel(cardLayout);
		private ArrayList<Villain> villains = new ArrayList<Villain>();
		private SuperVillain superVillain; //Needs to be added to UML
		private ArrayList<CityPanel> cities = new ArrayList<CityPanel>();
		private ArrayList<JPanel> buildings;
		private Team team;
		private int currentCity = 1;
		private int numCities;

		/**
		 * Create the panel.
		 */
		public RunGamePanel(Team team, int numCities)
			{
				this.team = team;
				this.numCities = numCities;
				generateVillains(numCities);
				generateCities(numCities);
				
				for (int i = 1; i <= cities.size(); i++) {
					container.add(cities.get(i-1), "City " + i);
				}
				add(container);
				
				cardLayout.show(container, "City " + currentCity);
			} 
		
		private void generateVillains(int numOfVillains) {
			ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(new PaperScissorsRockGame(), new GuessNumberGame(), new DiceRollsGame()));
			String[] choGath = {"Cho'Gath", " - The Terror of the Void"}; String[] kassadin = {"Kassadin", " - The Void Walker"}; String[] baron = {"Baron Nashor", ""};
			String[] kogMaw = {"Kog'Maw", " - The Mouth of the Abyss"}; String[] khaZix = {"Kha'Zix", " - The Voidreaver"}; String[] rekSai = {"Rek'Sai", " - The Void Burrower"};
			String[] malzahar = {"Malzahar", " - The Prophet of the Void"}; String[] velKoz = {"Vel'Koz", " - The Eye of the Void"};
			ArrayList<String[]> namesList = new ArrayList<String[]>(Arrays.asList(choGath, kassadin, baron, kogMaw, khaZix, rekSai, malzahar, velKoz));
//			ArrayList<String> namesList = new ArrayList<String>(Arrays.asList("Cho'Gath - The Terror of the Void", "Kassadin - The Void Walker", "Baron Nashor", 
//					"Kog'Maw - The Mouth of the Abyss", "Kha'Zix - The Voidreaver", "Malzahar - The Prophet of the Void", "Rek'Sai - The Void Burrower", 
//					"Vel'Koz - The Eye of the Void"));
			ArrayList<String> tauntsList = new ArrayList<String>(Arrays.asList("Your souls will feed the Void!", "The balance of power must be preserved.", 
					"Time to feast! ", "Kill. Consume. Adapt.", "We are timeless. We demand Sacrifice.", "Creature appears to seek its maternal unit.",
					"Only by deconstruction is truth revealed."));
			Random rand = new Random();
			
			for(int i = 0; i < numOfVillains; i++) {
				int nameIndex = rand.nextInt(namesList.size());
				int tauntIndex = rand.nextInt(tauntsList.size());
				String[] nameTitle = namesList.get(nameIndex);
				String taunt = tauntsList.get(tauntIndex);
				if (nameTitle[0] == "Baron Nashor") {taunt = "...";}
				Villain newVillain = new Villain(nameTitle, taunt, gameList);
				villains.add(newVillain);
				namesList.remove(nameIndex); 
				if (nameTitle[0] != "Baron Nashor") {tauntsList.remove(tauntIndex);}
			} //System.out.println(villains.size());
			
			//Creates the super villain
			int nameIndex = rand.nextInt(namesList.size());
			int tauntIndex = rand.nextInt(tauntsList.size());
			String[] nameTitle = namesList.get(nameIndex); String taunt = tauntsList.get(tauntIndex);
			SuperVillain superVill = new SuperVillain(nameTitle, taunt, gameList);
			superVillain = superVill;
		}
		
		private void generateCities(int numCities) {
			Map[] maps = new Map[numCities];
			for (int i = 1; i <= numCities; i++) {maps[i-1] = new Map(i);}
			for (int i = 1; i < numCities; i++) { //I think this should be i = 0, (numCities - 1)
				buildings = new ArrayList<JPanel>(Arrays.asList(new ShopPanel(maps, team), new VillainsLairPanel(team, villains.get(i-1), this), new PowerupDenPanel(team), new HospitalPanel(team)));
				cities.add(new CityPanel(buildings, villains.get(i-1), team));
			}
			buildings = new ArrayList<JPanel>(Arrays.asList(new ShopPanel(maps, team), new VillainsLairPanel(team, superVillain, this), new PowerupDenPanel(team), new HospitalPanel(team)));
			cities.add(new CityPanel(buildings, superVillain, team));
		}
		
		public void nextCity() {
			currentCity++;
//			System.out.println(currentCity);
			if (currentCity <= numCities) {
				cardLayout.show(container, "City " + currentCity);
				cities.get(currentCity).updateHeroDisplays();
			}
			else {String s = "Won the game"; System.out.println(s);}
		}
	}

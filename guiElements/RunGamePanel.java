package guiElements;
import java.awt.CardLayout;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import characters.Hero;
import characters.SuperVillain;
import characters.Team;
import characters.Villain;
import items.HealingItem;
import items.Map;
import items.Powerup;
import items.PowerupDamage;
import items.PowerupDodge;
import items.PowerupLuck;

@SuppressWarnings("serial")
public class RunGamePanel extends JPanel
	{
		private CardLayout cardLayout = new CardLayout();
		private JPanel container = new JPanel(cardLayout);
		private ArrayList<Villain> villains = new ArrayList<Villain>();
		private SuperVillain superVillain; //Needs to be added to UML
		private ArrayList<CityPanel> cities = new ArrayList<CityPanel>();
		private ArrayList<BuildingPanel> buildings;
		private Team team;
		private int currentCity = 1;
		private int numCities;
		private JFrame parent;
//		private long startTime;
		private Instant startTime;

		/**
		 * Create the panel.
		 */
		public RunGamePanel(Team team, int numCities, JFrame parent)
			{
				this.team = team;
				this.numCities = numCities;
				this.parent = parent;
				generateVillains(numCities);
				generateCities(numCities);
				
				for (int i = 1; i <= cities.size(); i++) {
					container.add(cities.get(i-1), "City " + i);
				}
				add(container);
				
				
				cardLayout.show(container, "City " + currentCity);
				startTime = Instant.now();
			} 
		
		private void generateVillains(int numOfVillains) {
//			ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(new PaperScissorsRockGame(), new GuessNumberGame(), new DiceRollsGame()));
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
				Villain newVillain = new Villain(nameTitle, taunt);
				villains.add(newVillain);
				namesList.remove(nameIndex); 
				if (nameTitle[0] != "Baron Nashor") {tauntsList.remove(tauntIndex);}
			} //System.out.println(villains.size());
			
			//Creates the super villain
			int nameIndex = rand.nextInt(namesList.size());
			int tauntIndex = rand.nextInt(tauntsList.size());
			String[] nameTitle = namesList.get(nameIndex); String taunt = tauntsList.get(tauntIndex);
			SuperVillain superVill = new SuperVillain(nameTitle, taunt);
			superVillain = superVill;
		}
		
		private void generateCities(int numCities) {
			HealingItem[] healingPotions = {new HealingItem("Potion of Minor Healing", "Heals 25% of a Hero's total health", 25, 1),
					new HealingItem("Potion of Medium Healing", "Heals 50% of a Hero's total health", 50, 2),
					new HealingItem("Potion of Major Healing", "Restores the Hero's health to Full", 100, 4)};
			Powerup[] powerups = {new PowerupLuck(), new PowerupDamage(), new PowerupDodge()};
			Map[] maps = new Map[numCities];
			for (int i = 1; i <= numCities; i++) {maps[i-1] = new Map(i);}
			for (int i = 1; i < numCities; i++) { //I think this should be i = 0, (numCities - 1)
				buildings = new ArrayList<BuildingPanel>(Arrays.asList(new ShopPanel(maps, team, powerups, healingPotions), new VillainsLairPanel(team, villains.get(i-1), this), new PowerupDenPanel(team), new HospitalPanel(team)));
				cities.add(new CityPanel(buildings, villains.get(i-1), team, "City " + i, maps[i-1]));
			}
			buildings = new ArrayList<BuildingPanel>(Arrays.asList(new ShopPanel(maps, team, powerups, healingPotions), new VillainsLairPanel(team, superVillain, this), new PowerupDenPanel(team), new HospitalPanel(team)));
			cities.add(new CityPanel(buildings, superVillain, team, "Final City", maps[maps.length - 1]));
		}
		
		public void nextCity(Villain defeatedVillain) {
			currentCity++;
			if (currentCity <= numCities) {
				Double dblReward = defeatedVillain.getReward() * team.getLootMod();
				int reward = dblReward.intValue();
				team.adjustGold(reward);
				JOptionPane.showMessageDialog(this, "Congratulations! You defeated " + defeatedVillain.getName() + defeatedVillain.getTitle() + " and recieved " + reward + " gold!", "Defeated villain!", JOptionPane.INFORMATION_MESSAGE);
				for (Hero hero : team.getHeroList()) {
					hero.setPowerup(new PowerupDamage(), false);
					hero.setPowerup(new PowerupDodge(), false);
					hero.setPowerup(new PowerupLuck(), false);
				}
				cardLayout.show(container, "City " + currentCity);
				cities.get(currentCity - 1).updateHeroDisplays();
			}
			else {
				Instant endTime = Instant.now();
				Duration between = Duration.between(startTime, endTime);
				LocalTime timeElapsed = LocalTime.MIDNIGHT.plus(between);
				String time = DateTimeFormatter.ofPattern("mm:ss").format(timeElapsed);
				JOptionPane.showMessageDialog(this, "Congratulations! " + team.getName() + " defeated the Super Villain" + defeatedVillain.getName() + " and saved all the cities in " + time + "!", "You won the game!", JOptionPane.INFORMATION_MESSAGE);
				playAgain();
			}
		}
		
		public void playAgain() {
			int playAgain = JOptionPane.showConfirmDialog(this, "Would you like to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
			if (playAgain == JOptionPane.YES_OPTION) {
				parent.setVisible(false);;
				@SuppressWarnings("unused")
				MainWindow newGame = new MainWindow();
			} else {System.exit(0);}
			
		}
	}

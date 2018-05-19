
import java.util.ArrayList;

// TODO: Implement discountshopper

public class Shop implements Building {
	
	private Output output = new Output();
	
	private Team currentTeam;
	private int cost;

	@Override
	public boolean goTo(Team team) {
		
		currentTeam = team;
		
		int actionChoice;
		
		do {
			System.out.println("Hear ye, hear ye! Welcome to mah humble ol' shop. How can I help yah?");
			String[] shopOptions = {"Buy powerups", "Buy health items", "Buy maps", "View inventory", "Leave"};
			actionChoice = output.printOptions(shopOptions);
			
			switch (actionChoice) {
				case 1:
					powerupMenu();
					break;
					
				case 2:
					healingMenu();
					break;
					
				case 3:
					mapMenu();
					break;
				
				case 4:
					viewInventory();
					break;
					
				case 5:
					System.out.println("Be gone ye filthy vermin!!");
					break;
			}
			
		} while (actionChoice != 5);
		
		currentTeam = null;
		
		return false;
	}
		
		private void powerupMenu() {
			
			int powerChoice;
			
			System.out.println("Powerups to help yah in those sticky situations!");
			System.out.println("Team: " + currentTeam.getName());
			System.out.println("Powerups Owned...");
			output.printPowerupInventory(currentTeam.getPowerupList());
			System.out.println("Gold: $" + currentTeam.getMoney());
			String[] powerupOptions = {"Kage's Lucky Pick - $70", "Infinity Edge - $50", "Ninja Tabi - $60", "View Item Descriptions", "Back"};
			powerChoice = output.printOptions(powerupOptions);
			
			if (powerChoice == 4) {
				output.printPowerupDescriptions();
			}
			
			if (powerChoice == 1) { cost = -70; }
			if (powerChoice == 2) { cost = -50; }
			if (powerChoice == 3) { cost = -60; }
			
			currentTeam.changeMoney(cost);
			if (currentTeam.getMoney() < 0) {
				System.out.println("Yikes, you do not have enough gold to purchase this item.");
				currentTeam.changeMoney(Math.abs(cost));
			} else {
				currentTeam.addPowerup(powerChoice);;
				System.out.println("Your team has purchased " + powerupOptions[powerChoice-1] + ".");
				System.out.println("Your current gold is now $" + currentTeam.getMoney()); }
		}
		
		private void healingMenu() { 
			
			int healthChoice;
			
			System.out.println("If you're feeling sick, don't despair! We've got some health potions near!");
			System.out.println("Team: " + currentTeam.getName());
			System.out.println("Health items owned...");
			output.printHealingInventory(currentTeam.getHealingList());
			System.out.println("Gold: $" + currentTeam.getMoney());
			String[] healthOptions = {"Potion of Minor Healing (5s) - $25", "Potion of Medium Healing (7s) - $50", "Potion of Major Healing (10s) - $100", "View Item Descriptions", "Back"};
			healthChoice = output.printOptions(healthOptions);
			
			if (healthChoice == 4) {
				output.printHealingDescriptions();
			}
			
			if (healthChoice == 1) { cost = -25; }
			if (healthChoice == 2) { cost = -50; }
			if (healthChoice == 3) { cost = -100; }
			
			currentTeam.changeMoney(cost);
			if (currentTeam.getMoney() < 0) {
				System.out.println("Yikes, you do not have enough gold to purchase this item. Times are tough, eh?");
				currentTeam.changeMoney(Math.abs(cost));
			} else {
				currentTeam.addHealing(healthChoice);;
				System.out.println("Your team has purchased " + healthOptions[healthChoice-1] + ".");
				System.out.println("Your current gold is now $" + currentTeam.getMoney()); }
		}
			
		
		private void mapMenu() {
			
			int mapChoice;
			cost = -50;
			
			System.out.println("They say a map is the most important item you could have!");
			System.out.println("Team: " + currentTeam.getName());
			String[] mapOptions = {"Buy map - $50", "Back"};
			mapChoice = output.printOptions(mapOptions);
			
			if (mapChoice == 1) {
				currentTeam.changeMoney(cost);
				if (currentTeam.getMoney() < 0) {
					System.out.println("Yikes, you do not have enough gold to purchase this item. Times are tough, eh?");
					currentTeam.changeMoney(Math.abs(cost));
				} else {
					currentTeam.addMap(1);
					System.out.println("Your team has purchased a map. Congratulations!"); }
				}	
			
		}
		
		private void viewInventory() {
			System.out.println("Empty yer bag and let's see whether yer worth me time... *chuckles*");
			output.printPowerupInventory(currentTeam.getPowerupList());
			output.printHealingInventory(currentTeam.getHealingList());
			//System.out.println("Map: " + currentTeam.hasMap();
			System.out.println("Gold: $" + currentTeam.getMoney());
		}
		
		
	}
		

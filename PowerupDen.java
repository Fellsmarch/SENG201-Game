
public class PowerupDen implements Building
	{

	private Output output = new Output();
	
	private Team currentTeam;

	@Override
	public boolean goTo(Team team) {
		
		currentTeam = team;
		
		int actionChoice;
		
		do {
			System.out.println("Welcome to the Powerup Den! Where heroes travel near and far to become stronger and more prepared for battle!");
			String[] denOptions = {"Use Powerup", "View Powerup descriptions", "View inventory", "Leave"};
			actionChoice = output.printOptions(denOptions);
			
			switch (actionChoice) {
				case 1:
					applyPowerup();
					break;
					
				case 2:
					viewDescription();
					break;
					
				case 3:
					viewInventory();
					break;
				
				case 4:
					System.out.println("Thanks for visiting the Powerup Den, good luck with your battles!");
					break;
					
			}
			
		} while (actionChoice != 4);
		
		currentTeam = null;
		
		return false;
	}
	
	public void applyPowerup() {
		
		int powerChoice;
		
		System.out.println("Powerups to help yah in those sticky situations!");
		System.out.println("Team: " + currentTeam.getName());
		System.out.println("Powerups Owned...");
		output.printPowerupInventory(currentTeam.getPowerupList());

	}
	
	public void viewDescription() {
		output.printPowerupDescriptions();
		
	}
	
	public void viewInventory() {
		System.out.println("Empty yer bag and let's see whether yer worth me time... *chuckles*");
		output.printPowerupInventory(currentTeam.getPowerupList());
		output.printHealingInventory(currentTeam.getHealingList());
		//System.out.println("Map: " + currentTeam.hasMap();
		System.out.println("Gold: $" + currentTeam.getMoney());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}


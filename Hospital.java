
public class Hospital implements Building
	{
private Output output = new Output();
	
	private Team currentTeam;

	@Override
	public boolean goTo(Team team) {
		
		currentTeam = team;
		
		int actionChoice;
		
		do {
			System.out.println("Welcome to the hospital! Here you can use healing items that you may have purchased at the Shop!");
			String[] hospitalOptions = {"Use Healing Items", "View Healing Item descriptions", "View inventory", "Leave"};
			actionChoice = output.printOptions(hospitalOptions);
			
			switch (actionChoice) {
				case 1:
					useHealing();
					break;
					
				case 2:
					viewDescription();
					break;
				
				case 3:
					viewInventory();
					break;
				
				case 4:
					System.out.println("Thanks for visiting the Hospital, good luck with your battles!");
					break;
					
			}
			
		} while (actionChoice != 4);
		
		currentTeam = null;
		
		return false;
	}
	
	public void useHealing() {
		System.out.println("Ready to be healed!");
	}
	
	public void viewDescription() {
		output.printHealingDescriptions();
	}
	
	public void viewInventory() {
		System.out.println("Empty yer bag and let's see whether yer worth me time... *chuckles*");
		output.printPowerupInventory(currentTeam.getPowerupList());
		output.printHealingInventory(currentTeam.getHealingList());
		//System.out.println("Map: " + currentTeam.hasMap();
		System.out.println("Gold: $" + currentTeam.getMoney());
	}

	}

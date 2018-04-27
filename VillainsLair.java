
public class VillainsLair implements Building
	{
		private Output output = new Output();
		private Villain villain;

		@Override
		public boolean goTo(Team team) //Returns true if the Hero's completed the city
			{
				String[] options = {"Fight Battle", "Flee"};
				int userChoice = output.printOptions(options);
				if (userChoice == 1) {
					Battle battle = new Battle(team, villain);
					if (battle.startBattle()) {
						return true;
					} else {System.exit(0); return false;}
				}else {return false;}
			}
		
		public void setVillain(Villain villain) {this.villain = villain;}

	}

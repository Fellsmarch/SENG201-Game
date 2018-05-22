package commandLineElements;
import java.util.ArrayList;
import java.util.Arrays;

import characters.Team;
import characters.Villain;

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
//					Battle battle = new Battle(team, villain);
//					if (battle.startBattle()) {
						return true;
//					} else {System.exit(0); return false;}
				}else {return false;}
			}
		
		public void setVillain(Villain villain) {this.villain = villain;}
		
//		public static void main(String[] args) {
//			VillainsLair lair = new VillainsLair();
//			ArrayList<Game> gameList = new ArrayList<Game>(Arrays.asList(new PaperScissorsRockGame(), new GuessNumberGame(), new DiceRollsGame()));
//			Villain vill = new Villain("Jim the vill", "You're Lame!", gameList);
//			lair.setVillain(vill);
//			Team team = new Team ("Kool kids", new ArrayList<Hero>(Arrays.asList(new Hero("Tim the hero"))));
//			System.out.println(lair.goTo(team));
//		}
	}

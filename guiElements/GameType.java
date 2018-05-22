package guiElements;

import characters.*;

public enum GameType
	{
		PSR {public PSRPanel createGame(Hero hero, Villain villain, boolean moreLucky) {return new PSRPanel(hero, villain, moreLucky);}},
		DICEROLL {public DiceRollsPanel createGame(Hero hero, Villain villain, boolean moreLucky) {return new DiceRollsPanel(hero, villain, moreLucky);}},
		GUESSNUM {public GuessNumberPanel createGame(Hero hero, Villain villain, boolean moreLucky) {return new GuessNumberPanel(hero, villain, moreLucky);}};
		
		
		abstract public GamePanel createGame(Hero hero, Villain villain, boolean moreLucky);

	}

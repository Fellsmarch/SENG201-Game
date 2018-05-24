package guiElements;

import characters.*;

/**
 * This enum states the possible game type to be played and returns the appropriate panel to be displayed
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */
public enum GameType
	{
		/**
		 * Creates a game for Paper, Scissors, Rock
		 * @param hero The chosen Hero
		 * @param villain The villain that will be challenged by the hero
		 * @param moreLucky boolean
		 * @return PSRPanel
		 */
		PSR {public PSRPanel createGame(Hero hero, Villain villain, boolean moreLucky) {return new PSRPanel(hero, villain, moreLucky);}},
		
		/**
		 * Creates a game for Dice Rolls
		 * @param hero The chosen Hero
		 * @param villain The villain that will be challenged by the hero
		 * @param moreLucky boolean
		 * @return DiceRollsPanel
		 */
		DICEROLL {public DiceRollsPanel createGame(Hero hero, Villain villain, boolean moreLucky) {return new DiceRollsPanel(hero, villain, moreLucky);}},
		
		/**
		 * Creates a game for Guess Number
		 * @param hero The chosen Hero
		 * @param villain The villain that will be challenged by the hero
		 * @param moreLucky boolean
		 * @return GuessNumberPanel
		 */
		GUESSNUM {public GuessNumberPanel createGame(Hero hero, Villain villain, boolean moreLucky) {return new GuessNumberPanel(hero, villain, moreLucky);}};
		
		/**
		 * Creates the game
		 * @param hero The chosen Hero
		 * @param villain The villain that will be challenged by the hero
		 * @param moreLucky boolean
		 */
		abstract public GamePanel createGame(Hero hero, Villain villain, boolean moreLucky);

	}

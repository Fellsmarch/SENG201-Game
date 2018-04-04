/**
 * This hero type increases, when an event is triggered, that the event is good/positive event
 * instead of a bad/negative one
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class LuckyHero extends Hero
	{
		public LuckyHero() { //Chance of getting good events vs bad ones is increased
			this.name = "Lucky";
			this.eventChance = 0.25;
		}
	}

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
		public LuckyHero(String name) { //Chance of getting good events vs bad ones is increased
			super(name);
			eventChance = 0.25;
		}
		
		public String toString() {
			String old = super.toString();
			return old + name + " increases the chance of the team getting a free item instead of being robbed\n";
		}
	}

package characters;
import characters.HeroTypes.*;

/**
 * This enum states the possible Hero Types that a user can choose from
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */
public enum HeroType
	{
		/**
		 * Creates a Hero of the Hero Type "ADCARRY"
		 * @return "ADCARRY" hero
		 */
		VANILLA("Wuju Bladesman"),
		ADCARRY("Night Hunter") {
			@Override
			public ADCarryHero createHero(String name) {
				return new ADCarryHero(name);
			}
		},
		
		/**
		 * Creates a Hero of the Hero Type "CEOHero"
		 * @return "CEOHero" hero
		 */
		CEO("Madman of Zaun") {
			@Override
			public CEOHero createHero(String name) {
				return new CEOHero(name);
			}
		},
		
		/**
		 * Creates a Hero of the Hero Type "DiscountShopper"
		 * @return "DiscountShopper" hero
		 */
		DISCOUNTSHOPPER("Storedle") {
			@Override
			public DiscountShopper createHero(String name) {
				return new DiscountShopper(name);
			}
		},
		
		/**
		 * Creates a Hero of the Hero Type "LootHoarderHero"
		 * @return "LootHoarderHero" hero
		 */
		LOOTHOARDER("Saltwater Scourge") {
			@Override
			public LootHoarderHero createHero(String name) {
				return new LootHoarderHero(name);
			}
		},
		
		/**
		 * Creates a Hero of the Hero Type "LuckyHero"
		 * @return "LuckyHero" hero
		 */
		LUCKY("Card Master") {
			@Override
			public LuckyHero createHero(String name) {
				return new LuckyHero(name);
			}
		},
		
		/**
		 * Creates a Hero of the Hero Type "RandomHero"
		 * @return "RandomHero" hero
		 */
		RANDOM("ARAM") {
			@Override
			public RandomHero createHero(String name) {
				return new RandomHero(name);
			}
		},
		
		/**
		 * Creates a Hero of the Hero Type "SecondWind"
		 * @return "SecondWind" hero
		 */
		SECONDWIND("Darkin") {
			@Override
			public SecondWind createHero(String name) {
				return new SecondWind(name);
			}
		},
		
		/**
		 * Creates a Hero of the Hero Type "TankHero"
		 * @return "TankHero" hero
		 */
		TANK("Armourdillo") {
			@Override
			public TankHero createHero(String name) {
				return new TankHero(name);
			}
		},
		
		/**
		 * Creates a Hero of the Hero Type "TeamPowerupHero"
		 * @return "TeamPowerupHero" hero
		 */
		TEAMPOWERUP("Exemplar of Demacia") {
			@Override
			public TeamPowerupHero createHero(String name) {
				return new TeamPowerupHero(name);
			}
		};
		
		/**
		 * A variable for the Hero's text "title"
		 */
		private String text;
		
		/**
		 * Gets the text
		 * @return
		 */
		public String getText() {
			return text;
		}
		
		/**
		 * Creates a Hero
		 */
		public Hero createHero(String name) {
			return new Hero(name);
		}
		
		/**
		 * Constructor 
		 */
		private HeroType(String text) {
			this.text = text;
		}

		/**
		 * String of the specified text
		 * @return text as a String
		 */
		@Override
		public String toString() {
			return text;
		}
	}

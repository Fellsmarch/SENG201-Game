package characters;
import characters.HeroTypes.*;

/**
 * This enum states the possible Hero Types that a user can choose from and creates new heroes of the specified type
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */
public enum HeroType
	{
		/**
		 * Vanilla/Default Hero
		 */
		VANILLA("Wuju Bladesman"),
		
		/**
		 * The ADCarry Hero
		 */
		ADCARRY("Night Hunter") {
			/**
			 * Creates a Hero of the Hero Type "ADCARRY"
			 * @param name The new Hero's name
			 * @return a new "ADCARRY" hero
			 */
			@Override
			public ADCarryHero createHero(String name) {
				return new ADCarryHero(name);
			}
		},
		
		/**
		 * The CEO Hero
		 */
		CEO("Madman of Zaun") {
			/**
			 * Creates a Hero of the Hero Type "CEOHero"
			 * @param name The new Hero's name
			 * @return a new "CEOHero" hero
			 */
			@Override
			public CEOHero createHero(String name) {
				return new CEOHero(name);
			}
		},
		
		/**
		 * The DiscountShopper Hero
		 */
		DISCOUNTSHOPPER("Storedle") {
			/**
			 * Creates a Hero of the Hero Type "DiscountShopper"
			 * @param name The new Hero's name
			 * @return a new "DiscountShopper" hero
			 */
			@Override
			public DiscountShopper createHero(String name) {
				return new DiscountShopper(name);
			}
		},
		
		/**
		 * The LootHoarder Hero
		 */
		LOOTHOARDER("Saltwater Scourge") {
			/**
			 * Creates a Hero of the Hero Type "LootHoarderHero"
			 * @param name The new Hero's name
			 * @return a new "LootHoarderHero" hero
			 */
			@Override
			public LootHoarderHero createHero(String name) {
				return new LootHoarderHero(name);
			}
		},
		
		/**
		 * The Lucky Hero
		 */
		LUCKY("Card Master") {
			/**
			 * Creates a Hero of the Hero Type "LuckyHero"
			 * @param name The new Hero's name
			 * @return a new "LuckyHero" hero
			 */
			@Override
			public LuckyHero createHero(String name) {
				return new LuckyHero(name);
			}
		},
		
		/**
		 * The Random Hero
		 */
		RANDOM("ARAM") {
			/**
			 * Creates a Hero of the Hero Type "RandomHero"
			 * @param name The new Hero's name
			 * @return a new "RandomHero" hero
			 */
			@Override
			public RandomHero createHero(String name) {
				return new RandomHero(name);
			}
		},
		
		/**
		 * The SecondWind Hero
		 */
		SECONDWIND("Darkin") {
			/**
			 * Creates a Hero of the Hero Type "SecondWind"
			 * @param name The new Hero's name
			 * @return a new "SecondWind" hero
			 */
			@Override
			public SecondWind createHero(String name) {
				return new SecondWind(name);
			}
		},
		
		/**
		 * The Tank Hero
		 */
		TANK("Armourdillo") {
			/**
			 * Creates a Hero of the Hero Type "TankHero"
			 * @param name The new Hero's name
			 * @return a new "TankHero" hero
			 */
			@Override
			public TankHero createHero(String name) {
				return new TankHero(name);
			}
		},
		
		/**
		 * The TeamPowerup Hero
		 */
		TEAMPOWERUP("Exemplar of Demacia") {
			/**
			 * Creates a Hero of the Hero Type "TeamPowerupHero"
			 * @param name The new Hero's name
			 * @return a new "TeamPowerupHero" hero
			 */
			@Override
			public TeamPowerupHero createHero(String name) {
				return new TeamPowerupHero(name);
			}
		};
		
		/**
		 * A variable for the Hero's types name that is displayed to the user
		 */
		private String text;
		
		/**
		 * Creates a (vanilla) Hero
		* @param name The new Hero's name
		 * @return default Hero
		 */
		public Hero createHero(String name) {
			return new Hero(name);
		}
		
		/**
		 * Constructor 
		 * @param text the description of the hero tpye
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

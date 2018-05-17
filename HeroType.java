
public enum HeroType
	{
//		VANILLA("Garen"),
//		ADCARRY("Vayne"),
//		CEO("Mundo"),
//		DISCOUNTSHOPPER("Storedle"),
//		LOOTHOARDER("Gangplank"),
//		LUCKY("Twisted Fate"),
//		RANDOM("ARAM"),
//		SECONDWIND("Aatrox"),
//		TANK("Rammus"),
//		TEAMPOWERUP("Jarvan IV");
		
		VANILLA("Wuju Bladesman"),
		ADCARRY("Night Hunter") {
			@Override
			public ADCarryHero createHero(String name) {
				return new ADCarryHero(name);
			}
		},
		CEO("Madman of Zaun") {
			@Override
			public CEOHero createHero(String name) {
				return new CEOHero(name);
			}
		},
		DISCOUNTSHOPPER("Storedle") {
			@Override
			public DiscountShopper createHero(String name) {
				return new DiscountShopper(name);
			}
		},
		LOOTHOARDER("Saltwater Scourge") {
			@Override
			public LootHoarderHero createHero(String name) {
				return new LootHoarderHero(name);
			}
		},
		LUCKY("Card Master") {
			@Override
			public LuckyHero createHero(String name) {
				return new LuckyHero(name);
			}
		},
		RANDOM("ARAM") {
			@Override
			public RandomHero createHero(String name) {
				return new RandomHero(name);
			}
		},
		SECONDWIND("Darkin") {
			@Override
			public SecondWind createHero(String name) {
				return new SecondWind(name);
			}
		},
		TANK("Armourdillo") {
			@Override
			public TankHero createHero(String name) {
				return new TankHero(name);
			}
		},
		TEAMPOWERUP("Exemplar of Demacia") {
			@Override
			public TeamPowerupHero createHero(String name) {
				return new TeamPowerupHero(name);
			}
		};
		
		private String text;
		
		public String getText() {
			return text;
		}

		public Hero createHero(String name) {
			return new Hero(name);
		}
		
		//Constructor
		private HeroType(String text) {
			this.text = text;
		}

		
		@Override
		public String toString() {
			return text;
		}
	}

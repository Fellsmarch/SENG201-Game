package items;

public class Map extends Item
	{
		private int cityNum;
		
		public Map(int cityNum) {
			super("City Map #" + cityNum, "The map for city #" + cityNum, 50);
			this.cityNum = cityNum;
		}

		@Override
		public Item copy() {return this;}
		public int getCity() {return cityNum;}
	}


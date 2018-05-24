
package items;
import commandLineElements.*;

public class Map extends Item
	{
		/**
		 * The number of the relevant city
		 */
		private int cityNum;
		
		/** Constructor -- Map. Creates a map.
		 * @param name The name of the map.
		 * @param description What city the map reads for.
		 * @param price How much the map costs to purchase
		 */
		public Map(int cityNum) {
			super("City Map #" + cityNum, "The map for city #" + cityNum, 50);
			this.cityNum = cityNum;
		}

		/** 
		 * Creates a new instance of the item.
		 * @return Map
		 */
		@Override
		public Item copy() {return this;}
		
		/** Gets the number of the city
		 * @return 
		 */
		public int getCity() {return cityNum;}
	}



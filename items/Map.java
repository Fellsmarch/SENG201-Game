package items;


/**
 * This class creates the class 'Map' which is an object that can be stored in a Team's array list, healingInventory
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */

public class Map extends Item
	{
		/**
		 * The number of the relevant city
		 */
		private int cityNum;
		
		/** 
		 * Constructor -- Map. Creates a map.
		 * @param cityNum The city number for the city 
		 */
		public Map(int cityNum) {
			super("City Map #" + cityNum, "The map for city #" + cityNum, 50);
			this.cityNum = cityNum;
		}

		/**
		 * Return what number city this is
		 * @return
		 */
		public int getCity() {return cityNum;}
	}


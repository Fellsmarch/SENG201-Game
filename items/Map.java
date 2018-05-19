package items;
import commandLineElements.*;

public class Map extends Item
	{
		private int cityNum;
		
		public Map(int cityNum) {
			super("Map #" + cityNum, "The map for city #" + cityNum, 50);
			this.cityNum = cityNum;
		}

		@Override
		public Item copy() {return this;}
		public int getCity() {return cityNum;}
	}

		
		
		
		
		
		
		
		
		
		
//		private String[] directionList = new String[4];
//		
//		public Map(City city) {
//			Building[] directions = city.getDirections();
//			int index = 0;
//			for(Building building : directions) {
//				if(building instanceof Shop) {
//					directionList[index] = "Shop";
//				}
//				else if(building instanceof Hospital) {
//					directionList[index] = "Hospital";
//				}
//				else if(building instanceof PowerupDen) {
//					directionList[index] = "Powerup Den";
//				}
//				else {
//					directionList[index] = "Villain's Lair";
//				}
//				index++;
//			}
//		}
//		
//		public String[] UseMap() {return directionList;}
//	}

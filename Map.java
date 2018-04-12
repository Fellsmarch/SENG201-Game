
public class Map
	{
		private String[] directionList = new String[4];
		
		public Map(City city) {
			Building[] directions = city.getDiections();
			int index = 0;
			for(Building building : directions) {
				if(building instanceof Shop) {
					directionList[index] = "Shop";
				}
				else if(building instanceof Hospital) {
					directionList[index] = "Hospital";
				}
				else if(building instanceof PowerupDen) {
					directionList[index] = "Powerup Den";
				}
				else {
					directionList[index] = "Villain's Lair";
				}
				index++;
			}
		}
		
		public void displayMap() {
			String[] directions = {"North: ", "East:  ", "South: ", "West:  "};
			for(int i = 0; i < 4; i++) {
				System.out.println(directions[i] + directionList[i]);
			}
		}
	}

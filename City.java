import java.util.ArrayList;
import java.util.Random;

public class City
	{
		private Building directionNorth;
		private Building directionEast;
		private Building directionSouth;
		private Building directionWest;
		private Building[] directionList = {directionNorth, directionEast, directionSouth, directionWest};
		
		public City(ArrayList<Building> buildings) {
			Random rand = new Random();
			ArrayList<Integer> elementsAdded = new ArrayList<Integer>();
			int directionIndex = 0;
			for (int i = 0; i < 4; i++) { 									//I did it this way because otherwise we'd need to duplicate the buildings list
				boolean addSuccess = false;									//so we didn't alter the original list
				while (!addSuccess) {
					int toAdd = rand.nextInt(4);
					if (!elementsAdded.contains(toAdd)) {
						elementsAdded.add(toAdd);
						directionList[directionIndex] = buildings.get(toAdd);
						directionIndex++;
						addSuccess = true;
					}
				}
			}
		}
		
		public String getLayout() { //String may be changed when we transition to GUI
			return "test";
		}
		
		public void goNorth(Team team) {directionNorth.goTo(team);}
		public void goEast(Team team) {directionEast.goTo(team);}
		public void goSouth(Team team) {directionSouth.goTo(team);}
		public void goWest(Team team) {directionWest.goTo(team);}
		
		public Building[] getDiections() {return directionList;}
		

	}

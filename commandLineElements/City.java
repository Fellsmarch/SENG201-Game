import java.util.ArrayList;
import java.util.Random;

public class City
	{
//		private Building directionNorth;
//		private Building directionEast;
//		private Building directionSouth;
//		private Building directionWest;
//		private Building[] directionList = {directionNorth, directionEast, directionSouth, directionWest};
		private Building[] directionList = new Building[4];
		private Villain villain;
		
		public City(ArrayList<Building> buildings, Villain villain) {
			this.villain = villain;
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
//			System.out.println("North: " + (directionNorth instanceof VillainsLair));
//			System.out.println("East: " + (directionEast instanceof VillainsLair));
//			System.out.println("South: " + (directionSouth instanceof VillainsLair));
//			System.out.println("West: " + (directionWest instanceof VillainsLair));
		}
		
		public String getLayout() { //String may be changed when we transition to GUI
			return "test";
		}
		
		public boolean goNorth(Team team) {
			if(directionList[0] instanceof VillainsLair) {
				((VillainsLair) directionList[0]).setVillain(villain);
				return directionList[0].goTo(team);
			} else {
				directionList[0].goTo(team);
				return false;
			}
		}
		
		public boolean goEast(Team team) {
			if(directionList[1] instanceof VillainsLair) {
				((VillainsLair) directionList[1]).setVillain(villain);
				return directionList[1].goTo(team);
			} else {
				directionList[1].goTo(team);
				return false;
			}
		}
		
		public boolean goSouth(Team team) {
			if(directionList[2] instanceof VillainsLair) {
				((VillainsLair) directionList[2]).setVillain(villain);
				return directionList[2].goTo(team);
			} else {
				directionList[2].goTo(team);
				return false;
			}
		}
		
		public boolean goWest(Team team) {
			if(directionList[3] instanceof VillainsLair) {
				((VillainsLair) directionList[3]).setVillain(villain);
				return directionList[3].goTo(team);
			} else {
				directionList[3].goTo(team);
				return false;
			}
		}
		
//		public boolean goNorth(Team team) {
//			if(directionNorth instanceof VillainsLair) {
//				((VillainsLair) directionNorth).setVillain(villain);
//				return directionNorth.goTo(team);
//			} else {
//				directionNorth.goTo(team);
//				return false;
//			}
//		}
//		
//		public boolean goEast(Team team) {
//			if(directionEast instanceof VillainsLair) {
//				((VillainsLair) directionNorth).setVillain(villain);
//				return directionEast.goTo(team);
//			} else {
//				directionEast.goTo(team);
//				return false;
//			}
//		}
//		
//		public boolean goSouth(Team team) {
//			if(directionSouth instanceof VillainsLair) {
//				((VillainsLair) directionNorth).setVillain(villain);
//				return directionSouth.goTo(team);
//			} else {
//				directionSouth.goTo(team);
//				return false;
//			}
//		}
//		
//		public boolean goWest(Team team) {
//			if(directionWest instanceof VillainsLair) {
//				((VillainsLair) directionNorth).setVillain(villain);
//				return directionWest.goTo(team);
//			} else {
//				directionWest.goTo(team);
//				return false;
//			}
//		}
		
//		public void goNorth(Team team) {directionNorth.goTo(team);}
//		public void goEast(Team team) {directionEast.goTo(team);}
//		public void goSouth(Team team) {directionSouth.goTo(team);}
//		public void goWest(Team team) {directionWest.goTo(team);}
//		
//		public boolean goNorth(Team team, boolean villainsLair) {directionNorth.goTo(team);}
//		public boolean goEast(Team team, boolean villainsLair) {directionEast.goTo(team);}
//		public boolean goSouth(Team team, boolean villainsLair) {directionSouth.goTo(team);}
//		public boolean goWest(Team team, boolean villainsLair) {directionWest.goTo(team);}
		
		public Building[] getDirections() {return directionList;}
		

	}

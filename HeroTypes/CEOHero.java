/**
 * This hero type increases the starting money a team has, it may be better to find a way to change
 * this from inside the CEO class instead of doing it in the Team class.
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 * @version 0.1 04/04/2018
 */

public class CEOHero extends Hero
	{
		//private String name = "CEO";	//I don't know if this or using the constructor is a better method, also should we be 
										//using this.setName("CEO") instead so	that name can be private in the Hero super class															
		
		public CEOHero() {
			name = "CEO";
		}
	}

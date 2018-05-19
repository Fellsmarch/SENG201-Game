package guiElements;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VillainsLairPanel extends JPanel
	{
		private RunGamePanel parent;
		/**
		 * Create the panel.
		 */
		public VillainsLairPanel()
			{
			setLayout(new MigLayout("", "[]", "[]"));
			
			JButton btnNextCity = new JButton("Next City");
			btnNextCity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.nextCity();
				}
			});
			add(btnNextCity, "cell 0 0");
				
			}
		public String toString() {return "Villains Lair";}
		public void setParent(RunGamePanel parent) {this.parent = parent;}
	}

package guiElements;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import characters.Team;
import items.Powerup;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PowerupDenPanel extends JPanel
	{
		private JComboBox<Powerup> comboBox;
		private Team team;
		private DefaultComboBoxModel<Powerup> powerupModel;
		private Powerup[] powerups;

		/**
		 * Create the panel.
		 */
		public PowerupDenPanel(Team team)
			{
				this.team = team;
			setLayout(new MigLayout("", "[grow][grow]", "[][][][]"));
			
			JLabel lblPowerupDen = new JLabel("Powerup Den");
			lblPowerupDen.setFont(new Font("Dialog", Font.BOLD, 25));
			add(lblPowerupDen, "cell 0 0 2 1,alignx center");
			
			JSeparator separator = new JSeparator();
			separator.setBackground(Color.BLACK);
			add(separator, "cell 0 1 2 1,growx");
			
			powerups = team.getPowerupList().toArray(new Powerup[team.getPowerupList().size()]);
			System.out.println(powerups);
			powerupModel = new DefaultComboBoxModel<Powerup>(powerups);
			comboBox = new JComboBox<Powerup>(powerupModel);
			add(comboBox, "cell 0 2,growx");
			
			JButton btnNewButton = new JButton("New button");
			add(btnNewButton, "cell 1 2,growx");
				
			}
		public String toString() {return "Powerup Den";}
		public void updatePowerupList() {
//			System.out.println("Attempting to update");
//			System.out.println(team.getPowerupList());
			powerups = team.getPowerupList().toArray(new Powerup[team.getPowerupList().size()]);
			
			
		}
	}

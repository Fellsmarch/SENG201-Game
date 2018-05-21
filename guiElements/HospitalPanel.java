package guiElements;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import characters.Hero;
import characters.Team;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class HospitalPanel extends JPanel
	{
		private Timer timer;
		private int timeLeft;
		private int healsLeft;
		private JComboBox<String> comboHeroSelector;
		private JTextPane paneHeroStats;
		private Team team;
		private boolean currentlyHealing = false;

		/**
		 * Create the panel.
		 */
		public HospitalPanel(Team team)
			{
				this.team = team;
			setLayout(new MigLayout("", "[grow][grow]", "[][][][grow]"));
			
			JLabel lblHospital = new JLabel("Hospital");
			lblHospital.setFont(new Font("Dialog", Font.BOLD, 25));
			add(lblHospital, "cell 0 0 2 1,alignx center");
			
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBackground(Color.BLACK);
			add(separator, "cell 0 1 2 1,growx");
			
			
			
			String[] heroList = team.getHeroNames();
			DefaultComboBoxModel<String> heroListModel = new DefaultComboBoxModel<String>(heroList);
			comboHeroSelector = new JComboBox<String>(heroListModel);
			comboHeroSelector.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateHeroDisplays();
				}
			});
			add(comboHeroSelector, "cell 0 2,growx");
			
			JComboBox comboPotionSelector = new JComboBox();
			add(comboPotionSelector, "cell 1 2,growx");
			
			paneHeroStats = new JTextPane();
			paneHeroStats.setEditable(false);
			paneHeroStats.setContentType("text/html");
			add(paneHeroStats, "cell 0 3,grow");
				ActionListener countDown = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						timeLeft--;
//						lblTime.setText("Time left: " + timeLeft);
						if (timeLeft <= 0) {
							healsLeft--;
							//Do heal
							}
						if (healsLeft <= 0) {timer.stop();}
					}
				};
				
				updateHeroDisplays();
			}
		
		private void updateHeroDisplays() { 
			Hero selectedHero = (Hero)team.getHeroList().get(comboHeroSelector.getSelectedIndex());
		    paneHeroStats.setText(selectedHero.toString());
		}
		
		public String toString() {return "Hospital";}

	}

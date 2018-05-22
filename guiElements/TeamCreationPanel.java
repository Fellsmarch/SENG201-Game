package guiElements;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import characters.Hero;
import characters.HeroType;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TeamCreationPanel extends JPanel
	{
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	private JTextPane paneDisplayHeroStats;
	private JLabel lblSecondHero;
	private JLabel lblThirdHero;
	private JLabel lblFirstHero = new JLabel("Your Hero:");
	private CreateHeroPanel[] createHeroPanels;
	private int position;

		/**
		 * Create the panel.
		 */
		public TeamCreationPanel(int numHeroes)
			{
				String rows = "[center][center][center][grow,center]";
				if (numHeroes > 1) {rows = "[][]" + rows;}
				if (numHeroes > 2) {rows = "[][]" + rows;}
				position = (numHeroes - 1) * 2;
				setLayout(new MigLayout("", "[grow][][grow]", rows));
				
				add(lblFirstHero, "cell 0 0 3 1");
				
				createHeroPanels = new CreateHeroPanel[numHeroes];
				int panelPos = 1;
				for (int i = 0; i < numHeroes; i++) {
					createHeroPanels[i] = new CreateHeroPanel();
					add(createHeroPanels[i], "cell 0 " + panelPos + " 3 1,growx");
					panelPos += 2;
				}
				if (numHeroes > 1) {
					lblFirstHero.setText("First Hero:");
					lblSecondHero = new JLabel("Second Hero:");
					add(lblSecondHero, "cell 0 2 3 1");
					}
				if (numHeroes > 2) {
					lblThirdHero = new JLabel("Third Hero:");
					add(lblThirdHero, "cell 0 4 3 1");
					}
	
				JComboBox<HeroType> comboHeroSelector = new JComboBox<HeroType>();
				comboHeroSelector.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Hero hero = ((HeroType) comboHeroSelector.getSelectedItem()).createHero(comboHeroSelector.getSelectedItem().toString());
						paneDisplayHeroStats.setText(hero.toString(false));
					}
				});
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBackground(Color.BLACK);
				separator_1.setForeground(Color.BLACK);
				add(separator_1, "cell 0 " + (2 + position) + " 3 1,growx");
				
				JLabel lblViewStats = new JLabel("View stats for hero type:");
				add(lblViewStats, "flowy,cell 0 " + (3 + position) + ",alignx center,aligny top");
				comboHeroSelector.setModel(new DefaultComboBoxModel<HeroType>(HeroType.values()));
				add(comboHeroSelector, "cell 0 " + (3 + position) + ",growx,aligny top");
				
				paneDisplayHeroStats = new JTextPane();
				paneDisplayHeroStats.setBackground(UIManager.getColor("this.background"));
				paneDisplayHeroStats.setContentType("text/html");
				paneDisplayHeroStats.setText(((HeroType) comboHeroSelector.getSelectedItem()).createHero(comboHeroSelector.getSelectedItem().toString()).toString(false));
				paneDisplayHeroStats.setEditable(false);
				add(paneDisplayHeroStats, "cell 2 " + (3 + position) + ",aligny top");
				
				JSeparator separator = new JSeparator();
				separator.setOrientation(SwingConstants.VERTICAL);
				separator.setForeground(Color.BLACK);
				separator.setBackground(Color.BLACK);
				add(separator, "cell 1 " + (3 + position) + ",alignx right,growy");
			}
		
			public boolean readyToContinue() {
				boolean allInputGood = true;
				for (CreateHeroPanel panel : createHeroPanels) {
					if (panel.heroCreated()) {
						heroes.add(panel.getHero());
					} else {
						JOptionPane.showMessageDialog(null, "Cannot continue until all heroes are submitted!", "Heroes not submitted", JOptionPane.ERROR_MESSAGE);
						heroes = new ArrayList<Hero>();
						allInputGood = false;
						break;
					}
				}
				return allInputGood;
			}
			
			public ArrayList<Hero> getHeroes() {
				return heroes;
			}

	}

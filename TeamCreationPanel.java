import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class TeamCreationPanel extends JPanel
	{
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	private JTextPane textPane;
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
				String rows = "[][][][grow]";
				if (numHeroes > 1) {rows = "[][]" + rows;}
				if (numHeroes > 2) {rows = "[][]" + rows;}
				position = (numHeroes - 1) * 2;
				setLayout(new MigLayout("", "[][][]", rows));
				
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
	
	//			add(new CreateHeroPanel(), "cell 0 1 3 1,growx");
	//			add(new CreateHeroPanel(), "cell 0 3 3 1,growx");
	//			add(new CreateHeroPanel(), "cell 0 5 3 1,growx");
				
				JComboBox<HeroType> comboBox = new JComboBox<HeroType>();
				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Hero hero = ((HeroType) comboBox.getSelectedItem()).createHero(comboBox.getSelectedItem().toString());
						if (hero instanceof RandomHero) 
							 {textPane.setText(((RandomHero) hero).toString(false));}
						else {textPane.setText(hero.toString());}
					}
				});
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBackground(Color.BLACK);
				separator_1.setForeground(Color.BLACK);
				add(separator_1, "cell 0 " + (2 + position) + " 3 1,growx");
				
				JLabel lblViewStats = new JLabel("View stats for hero type:");
				add(lblViewStats, "flowy,cell 0 " + (3 + position) + ",alignx center,aligny top");
				comboBox.setModel(new DefaultComboBoxModel<HeroType>(HeroType.values()));
				add(comboBox, "cell 0 " + (3 + position) + ",aligny top");
				
				textPane = new JTextPane();
				textPane.setBackground(UIManager.getColor("this.background"));
				textPane.setContentType("text/html");
				textPane.setText(((HeroType) comboBox.getSelectedItem()).createHero(comboBox.getSelectedItem().toString()).toString());
				textPane.setEditable(false);
				add(textPane, "cell 2 " + (3 + position) + ",aligny top");
				
				JSeparator separator = new JSeparator();
				separator.setOrientation(SwingConstants.VERTICAL);
				separator.setForeground(Color.BLACK);
				separator.setBackground(Color.BLACK);
				add(separator, "cell 1 " + (3 + position) + ",alignx right,growy");
				
//				JButton btnContinue = new JButton("Continue");
//				btnContinue.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						System.out.println("Button Pressed");
//						boolean allInputGood = true;
//						for (CreateHeroPanel panel : createHeroPanels) {
//							System.out.println("In loop");
//							if (panel.heroCreated()) {
//								System.out.println("Hero Created");
//								heroes.add(panel.getHero());
//							} else {
//								JOptionPane.showMessageDialog(null, "Cannot continue until all heroes are submitted!", "Heroes not submitted", JOptionPane.ERROR_MESSAGE);
//								heroes = new ArrayList<Hero>();
//								allInputGood = false;
//								break;
//							}
//						}
//						if (allInputGood) {
//							String s = "Continue";
//						}
//					}
//				});
//				add(btnContinue, "cell 0 " + (3 + position) + ",growx,height 50:70:70");
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

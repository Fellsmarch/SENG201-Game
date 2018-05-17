import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
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

@SuppressWarnings("serial")
public class TeamCreationPanel extends JPanel
	{
	private JTextField textField;
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	private String[] heroNames;
	private JTextPane textPane;

		/**
		 * Create the panel.
		 */
		public TeamCreationPanel(int numHeroes)
			{
			setLayout(new MigLayout("", "[][][]", "[][fill][][][][][][grow]"));
			
			
			JLabel lblFirstHero = new JLabel("First Hero:");
			add(lblFirstHero, "cell 0 0 3 1");
			
			JLabel lblSecondHero = new JLabel("Second Hero:");
			add(lblSecondHero, "cell 0 2 3 1");
			
			JLabel lblThirdHero = new JLabel("Second Hero:");
			add(lblThirdHero, "cell 0 4 3 1");
			
			
			add(new CreateHeroPanel(), "cell 0 1 3 1,growx");
			add(new CreateHeroPanel(), "cell 0 3 3 1,growx");
			add(new CreateHeroPanel(), "cell 0 5 3 1,growx");
			
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
			add(separator_1, "cell 0 6 3 1,growx");
			
			JLabel lblViewStats = new JLabel("View stats for hero type:");
			add(lblViewStats, "flowy,cell 0 7,alignx center,aligny top");
			comboBox.setModel(new DefaultComboBoxModel<HeroType>(HeroType.values()));
			add(comboBox, "cell 0 7,aligny top");
			
			textPane = new JTextPane();
			textPane.setBackground(UIManager.getColor("this.background"));
			textPane.setContentType("text/html");
			textPane.setText(((HeroType) comboBox.getSelectedItem()).createHero(comboBox.getSelectedItem().toString()).toString());
			textPane.setEditable(false);
			add(textPane, "cell 2 7,aligny top");
			
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setForeground(Color.BLACK);
			separator.setBackground(Color.BLACK);
			add(separator, "cell 1 7,alignx right,growy");
			
//			JLabel lblNewLabel = new JLabel("New label");
//			add(lblNewLabel, "cell 0 0,alignx trailing");
//			
//			textField = new JTextField();
//			add(textField, "cell 1 0,growx");
//			textField.setColumns(10);
//			
//			JLabel lblNewLabel_1 = new JLabel("New label");
//			add(lblNewLabel_1, "cell 2 0,alignx trailing");
//			
//			JComboBox comboBox = new JComboBox();
//			add(comboBox, "cell 3 0,growx");
			
//			if (heroNum == 1 && !onlyHero) {lblHeroName.setText("First Hero's Name:");}
//			else if (heroNum == 2) {lblHeroName.setText("Second Hero's Name:");}
//			else if (heroNum == 3) {lblHeroName.setText("Third Hero's Name:");}
//			else {lblHeroName.setText("Hero's Name:");}
			
				
			}

	}

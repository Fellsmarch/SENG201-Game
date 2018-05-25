package guiElements;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;

import characters.Hero;
import characters.HeroType;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


/**
 * This class creates the class 'CreateHeroPanel', extends JPanel and creates a panel for Hero Selection, Hero naming etc
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */
@SuppressWarnings("serial")
public class CreateHeroPanel extends JPanel
	{
		/**
		 * Label that displays text for the name
		 */
		private JLabel lblHeroName;
		
		/**
		 * User to enter input for the name 
		 */
		private JTextField fieldHeroName;
		
		/**
		 * JComboBox array displaying Hero Types
		 */
		private JComboBox<HeroType> comboHeroType;
		
		/**
		 * Label that displays text for the type
		 */
		private JLabel lblHeroType;
		
		/**
		 * Button for confirming selection
		 */
		private JToggleButton tglbtnEnter;
		
		/**
		 * Hero names stored in an Array List that has already been chosen by the user
		 */
		private static ArrayList<String> heroNamesSeen = new ArrayList<String>();
		
		/**
		 * Hero 
		 */
		private Hero hero;
		
		/**
		 * Boolean, if the hero is created or not
		 */
		private boolean heroCreated = false;

		/**
		 * Constructor -- Creates the hero panel
		 */
		public CreateHeroPanel()
			{
				setLayout(new MigLayout("", "[50px:n][50px:n][][][]", "[]"));
				
				lblHeroName = new JLabel("Name:");
				add(lblHeroName, "cell 0 0,alignx trailing");
				
				fieldHeroName = new JTextField();
				add(fieldHeroName, "flowx,cell 1 0,growx");
				fieldHeroName.setColumns(10);
				
				lblHeroType = new JLabel("Type:");
				add(lblHeroType, "cell 2 0,alignx right");
				
				comboHeroType = new JComboBox<HeroType>();
				comboHeroType.setModel(new DefaultComboBoxModel<HeroType>(HeroType.values()));
				add(comboHeroType, "cell 3 0,width 50:175:175");
				
				tglbtnEnter = new JToggleButton("Enter");
				tglbtnEnter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = fieldHeroName.getText();
						if (tglbtnEnter.isSelected()) {
							if (name.length() > 0) {
								if (!heroNamesSeen.contains(name)) {
									heroNamesSeen.add(name);
									hero = ((HeroType) comboHeroType.getSelectedItem()).createHero(name);
									heroCreated = true;
									fieldHeroName.setEnabled(false);
									comboHeroType.setEnabled(false);
									tglbtnEnter.setText("Edit");
									fieldHeroName.select(0, 0);
								} else {
									JOptionPane.showMessageDialog(null, "Cannot have multiple heroes with the same name!", "Duplicate hero name", JOptionPane.ERROR_MESSAGE);
									tglbtnEnter.setSelected(false);
									fieldHeroName.requestFocus(); fieldHeroName.selectAll();
								}
							} else {
								JOptionPane.showMessageDialog(null, "Hero name must be at least one character!", "Hero name too short", JOptionPane.ERROR_MESSAGE);
								tglbtnEnter.setSelected(false);
							}
						} else {
							if (heroNamesSeen.contains(name)) {
								heroNamesSeen.remove(name);
							}
							
							tglbtnEnter.setText("Enter");
							heroCreated = false;
							fieldHeroName.setEnabled(true);
							comboHeroType.setEnabled(true);
							fieldHeroName.requestFocus(); fieldHeroName.selectAll();
						}
					}
				});
				add(tglbtnEnter, "cell 4 0,width 50:72:72");
				
			}
		
		/**
		 * @return the hero
		 */
		public Hero getHero() {
			return hero;
		}
		
		/**
		 * Creates the hero
		 * @return the hero created
		 */
		public boolean heroCreated() {
			return heroCreated;
		}
		
		/**
		 * Resets the heroNamesSeen array List by creating a new one
		 */
		public static void resetHeroNames() {heroNamesSeen = new ArrayList<String>();}


	}

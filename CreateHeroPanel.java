import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CreateHeroPanel extends JPanel
	{
		private JLabel lblHeroName;
		private JTextField fieldHeroName;
		private JComboBox<HeroType> comboHeroType;
		private JLabel lblHeroType;
		private JToggleButton tglbtnEnter;
		private static ArrayList<String> heroNamesSeen = new ArrayList<String>();
		private Hero hero;
		private boolean heroCreated = false;

		/**
		 * Create the panel.
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
							fieldHeroName.setEnabled(true);
							fieldHeroName.requestFocus(); fieldHeroName.selectAll();
						}
					}
				});
				add(tglbtnEnter, "cell 4 0,width 50:72:72");
				
			}
		
		public Hero getHero() {
			return hero;
		}
		
		public boolean heroCreated() {
			return heroCreated;
		}

	}

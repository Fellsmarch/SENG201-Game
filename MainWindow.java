import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class MainWindow
	{

		private JFrame frame;
		private CardLayout cardLayout = new CardLayout();
		private JPanel container = new JPanel(cardLayout);
		private JLabel lblWhatIsYour;
		private JLabel lblHowManyCities;
		private JLabel lblHowManyHeroes;
		private JTextField fieldTeamName;
		private JToggleButton tglBtnNumHeroes1, tglBtnNumHeroes2, tglBtnNumHeroes3;
		
		private ArrayList<Hero> heroes = new ArrayList<Hero>();
		private String teamName;
		private int numCities = 3; 
		private int numHeroes = 1;
		private JToggleButton tglBtnNumCities3;
		private JToggleButton tglBtnNumCities5;
		private JToggleButton tglBtnNumCities6;
		private JToggleButton tglBtnNumCities4;
		private JLabel lblNewLabel;
		private JSeparator separator;
		private JLabel labelTeamName;
		private JButton btnContinue;
		private TeamCreationPanel createHeroTeam;
		

		/**
		 * Launch the application.
		 */
		public static void main(String[] args)
			{
				EventQueue.invokeLater(new Runnable()
					{
						public void run()
							{
								try
									{
										MainWindow window = new MainWindow();
										window.frame.setVisible(true);
									} catch (Exception e)
									{
										e.printStackTrace();
									}
							}
					});
			}

		/**
		 * Create the application.
		 */
		public MainWindow()
			{
				initialize();
			}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize()
			{
				frame = new JFrame();
				frame.setBounds(100, 100, 500, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Heroes & Villains");
				frame.getContentPane().add(container);
				frame.setResizable(true);
				frame.setFont(new Font("Courier", Font.BOLD, 12));
				
				JPanel mainWindowDisplay = new JPanel();

				
				container.add(mainWindowDisplay, "Main Window");
				cardLayout.show(container, "Main Window");
				mainWindowDisplay.setLayout(new MigLayout("", "[grow][grow,fill][grow]", "[50px:n][10px:n][][10px:n][][10px:n][][grow][][grow]"));
				
				lblNewLabel = new JLabel("Heroes & Villains");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
				mainWindowDisplay.add(lblNewLabel, "cell 0 0 3 1,alignx center,aligny center");
				
				separator = new JSeparator();
				mainWindowDisplay.add(separator, "cell 0 1 3 1,growx");
				
				lblWhatIsYour = new JLabel("What is your superhero team called?");
				mainWindowDisplay.add(lblWhatIsYour, "cell 0 2 2 1");
				
				fieldTeamName = new JTextField();
				fieldTeamName.addKeyListener(new KeyAdapter() {
//					@Override
//					public void keyTyped(KeyEvent e) {
//						labelTeamName.setText(textField.getText());
//					}
					@Override
					public void keyReleased(KeyEvent e) {
						String text = fieldTeamName.getText();
						if (text.length() > 10) {
							fieldTeamName.setText(text.substring(0, 10));
							JOptionPane.showMessageDialog(frame, "Team name must be no more than 10 characters!", "Team name too long!", JOptionPane.ERROR_MESSAGE);
						}
							
						labelTeamName.setText(fieldTeamName.getText());
					}
					@Override
					public void keyPressed(KeyEvent e) {
						String text = fieldTeamName.getText();
						if (text.length() > 10) {
							fieldTeamName.setText(text.substring(0, 10));
						}
					}
				});
				mainWindowDisplay.add(fieldTeamName, "cell 2 2,growx");
				fieldTeamName.setColumns(10);
				
				lblHowManyCities = new JLabel("How many cities are you to saving?");
				mainWindowDisplay.add(lblHowManyCities, "cell 0 4 2 1");
				
				tglBtnNumCities3 = new JToggleButton("3");
				tglBtnNumCities3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						tglBtnNumCities4.setSelected(false);
//						tglBtnNumCities5.setSelected(false);
//						tglBtnNumCities6.setSelected(false);
						numCities = 3;
					}
				});
				tglBtnNumCities3.setSelected(true);
				mainWindowDisplay.add(tglBtnNumCities3, "flowx,cell 2 4,growx");
				
				tglBtnNumCities4 = new JToggleButton("4");
				tglBtnNumCities4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						tglBtnNumCities3.setSelected(false);
//						tglBtnNumCities5.setSelected(false);
//						tglBtnNumCities6.setSelected(false);
						numCities = 4;
					}
				});
				mainWindowDisplay.add(tglBtnNumCities4, "cell 2 4,growx");
				
				tglBtnNumCities5 = new JToggleButton("5");
				tglBtnNumCities5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						tglBtnNumCities4.setSelected(false);
//						tglBtnNumCities3.setSelected(false);
//						tglBtnNumCities6.setSelected(false);
						numCities = 5;
					}
				});
				mainWindowDisplay.add(tglBtnNumCities5, "cell 2 4,growx");
				
				tglBtnNumCities6 = new JToggleButton("6");
				tglBtnNumCities6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						tglBtnNumCities4.setSelected(false);
//						tglBtnNumCities5.setSelected(false);
//						tglBtnNumCities3.setSelected(false);
						numCities = 6;
					}
				});
				mainWindowDisplay.add(tglBtnNumCities6, "cell 2 4,growx");
				
				
				lblHowManyHeroes = new JLabel("How many heroes are in your team?");
				mainWindowDisplay.add(lblHowManyHeroes, "cell 0 6 2 1");
				
				tglBtnNumHeroes1 = new JToggleButton("1");
				tglBtnNumHeroes1.setSelected(true);
				tglBtnNumHeroes1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						tglBtnNumHeroes2.setSelected(false);
//						tglBtnNumHeroes3.setSelected(false);
						numHeroes = 1;
					}
				});
				mainWindowDisplay.add(tglBtnNumHeroes1, "flowx,cell 2 6,growx");
				tglBtnNumHeroes2 = new JToggleButton("2");
				tglBtnNumHeroes2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						tglBtnNumHeroes1.setSelected(false);
//						tglBtnNumHeroes3.setSelected(false);
						numHeroes = 2;
					}
				});
				mainWindowDisplay.add(tglBtnNumHeroes2, "cell 2 6,growx");
				tglBtnNumHeroes3 = new JToggleButton("3");
				tglBtnNumHeroes3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						tglBtnNumHeroes2.setSelected(false);
//						tglBtnNumHeroes1.setSelected(false);
						numHeroes = 3;
					}
				});
				mainWindowDisplay.add(tglBtnNumHeroes3, "cell 2 6,growx");
				
				
				//These button groups replaces the commented out lines in the actionPerformed
				ButtonGroup numHeroesGroup = new ButtonGroup();
				numHeroesGroup.add(tglBtnNumHeroes1); numHeroesGroup.add(tglBtnNumHeroes2); numHeroesGroup.add(tglBtnNumHeroes3);
				
				ButtonGroup numCitiesGroup = new ButtonGroup();
				numCitiesGroup.add(tglBtnNumCities3); numCitiesGroup.add(tglBtnNumCities4);
				numCitiesGroup.add(tglBtnNumCities5); numCitiesGroup.add(tglBtnNumCities6);
				
				labelTeamName = new JLabel("");
				mainWindowDisplay.add(labelTeamName, "cell 0 7");
				
				btnContinue = new JButton("");
				btnContinue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GUITimerCode timer = new GUITimerCode(4);
						container.add(timer, "Timer");
						cardLayout.show(container, "Timer");
//						if (fieldTeamName.getText().length() < 2) {
//							JOptionPane.showMessageDialog(frame, "Team name must be more than 2 characters!", "Team name too long!", JOptionPane.ERROR_MESSAGE);
//							fieldTeamName.requestFocus();
//						} else {
//							createHeroTeam = new TeamCreationPanel(numHeroes);
//							container.add(createHeroTeam, "Create Hero Team");
//							cardLayout.show(container, "Create Hero Team");
//							CreateHeroPanel hero = new CreateHeroPanel(2);
//							container.add(hero, "Hero");
//							cardLayout.show(container, "Hero");
							
							
							
//							}
						
						
					}
				});
				btnContinue.setSelectedIcon(new ImageIcon("/home/cosc/student/hgc25/Downloads/Continue clicked test.jpg"));
				btnContinue.setIcon(new ImageIcon("/home/cosc/student/hgc25/Downloads/Continue test.jpg"));
				mainWindowDisplay.add(btnContinue, "cell 0 8 3 1,width 50:150:150,alignx center,height 25:50:100");
				

				

			}

	}

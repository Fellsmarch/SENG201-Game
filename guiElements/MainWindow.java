package guiElements;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import characters.Hero;
import characters.Team;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;

/**
 * The main class, the window where all the functionality of the game takes place
 * 
 * @author Harrison Cook
 * @author Hannah Regan
 */
public class MainWindow
	{
		/**
		 * JFrame, top level window with border and title bar
		 */
		private JFrame frame;
		
		/**
		 * New card layout, specifies horizontal and vertical gaps
		 */
		private CardLayout cardLayout = new CardLayout();
		
		/** 
		 * Creates a JPanel with reference to the CardLayout
		 */
		private JPanel container = new JPanel(cardLayout);
		
		/**
		 * JLabel asking for the name of the team
		 */
		private JLabel lblWhatIsYour;
		
		/**
		 * JLabel asking how many cities the user will play
		 */
		private JLabel lblHowManyCities;
		
		/** 
		 * JLabel asking how many heroes the user will want in the Team
		 */
		private JLabel lblHowManyHeroes;
		
		/**
		 * JTextField for user to input the Team name
		 */
		private JTextField fieldTeamName;
		
		/**
		 * JToggleButton for the number of heroes the user will select from
		 */
		private JToggleButton tglBtnNumHeroes1, tglBtnNumHeroes2, tglBtnNumHeroes3;
		
		/**
		 * An array list of heroes in the team
		 */
		private ArrayList<Hero> heroes = new ArrayList<Hero>();
		
		/**
		 * The Team
		 */
		private Team team;
		
		/**
		 * The name of the Team
		 */
		private String teamName;
		
		/**
		 * The number of cities
		 */
		private int numCities = 3; 
		
		/**
		 * The number of heroes in the Team
		 */
		private int numHeroes = 1;
		
		/**
		 * JToggleButtons for the user to select the number of cities they wish to play
		 */
		private JToggleButton tglBtnNumCities3, tglBtnNumCities4, tglBtnNumCities5, tglBtnNumCities6;
		
		/**
		 * Text for the title of the game
		 */
		private JLabel lblNewLabel;
		
		/**
		 * To divide space
		 */
		private JSeparator separator;
		
		/**
		 * JButton for the user to continue if they choose
		 */
		private JButton btnContinue;
		
		/**
		 * The panel that creates the user's team of Heroes
		 */
		private TeamCreationPanel createHeroTeam;
		
		/**
		 * After procedures are complete, the teamPanel is true
		 */
		boolean teamPanel = false;
		

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
										@SuppressWarnings("unused")
										MainWindow window = new MainWindow();
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
				frame.setBounds(100, 100, 600, 380);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Heroes & Villains");
				frame.getContentPane().add(container);
				frame.setResizable(true);
				frame.setFont(new Font("Courier", Font.BOLD, 12));
				frame.setVisible(true);
				
				JPanel mainWindowDisplay = new JPanel();

				
				container.add(mainWindowDisplay, "Main Window");
				cardLayout.show(container, "Main Window");
				mainWindowDisplay.setLayout(new MigLayout("", "[grow][grow,fill][grow]", "[50px:n][10px:n][][10px:n][][10px:n][][10px:n][grow]"));
				
				lblNewLabel = new JLabel("Heroes & Villains");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
				mainWindowDisplay.add(lblNewLabel, "cell 0 0 3 1,alignx center,aligny center");
				
				separator = new JSeparator();
				mainWindowDisplay.add(separator, "cell 0 1 3 1,growx");
				
				lblWhatIsYour = new JLabel("What is your superhero team called?");
				mainWindowDisplay.add(lblWhatIsYour, "cell 0 2 2 1");
				
				fieldTeamName = new JTextField();
				fieldTeamName.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						String text = fieldTeamName.getText();
						if (text.length() > 10) {
							fieldTeamName.setText(text.substring(0, 10));
							JOptionPane.showMessageDialog(frame, "Team name must be no more than 10 characters!", "Team name too long!", JOptionPane.ERROR_MESSAGE);
						}
							
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
						numCities = 3;
					}
				});
				tglBtnNumCities3.setSelected(true);
				mainWindowDisplay.add(tglBtnNumCities3, "flowx,cell 2 4,growx");
				
				tglBtnNumCities4 = new JToggleButton("4");
				tglBtnNumCities4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						numCities = 4;
					}
				});
				mainWindowDisplay.add(tglBtnNumCities4, "cell 2 4,growx");
				
				tglBtnNumCities5 = new JToggleButton("5");
				tglBtnNumCities5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						numCities = 5;
					}
				});
				mainWindowDisplay.add(tglBtnNumCities5, "cell 2 4,growx");
				
				tglBtnNumCities6 = new JToggleButton("6");
				tglBtnNumCities6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
						numHeroes = 1;
					}
				});
				mainWindowDisplay.add(tglBtnNumHeroes1, "flowx,cell 2 6,growx");
				tglBtnNumHeroes2 = new JToggleButton("2");
				tglBtnNumHeroes2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						numHeroes = 2;
					}
				});
				mainWindowDisplay.add(tglBtnNumHeroes2, "cell 2 6,growx");
				tglBtnNumHeroes3 = new JToggleButton("3");
				tglBtnNumHeroes3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						numHeroes = 3;
					}
				});
				mainWindowDisplay.add(tglBtnNumHeroes3, "cell 2 6,growx");
				
				
				ButtonGroup numHeroesGroup = new ButtonGroup();
				numHeroesGroup.add(tglBtnNumHeroes1); numHeroesGroup.add(tglBtnNumHeroes2); numHeroesGroup.add(tglBtnNumHeroes3);
				
				ButtonGroup numCitiesGroup = new ButtonGroup();
				numCitiesGroup.add(tglBtnNumCities3); numCitiesGroup.add(tglBtnNumCities4);
				numCitiesGroup.add(tglBtnNumCities5); numCitiesGroup.add(tglBtnNumCities6);
				
				btnContinue = new JButton("Continue");
				btnContinue.setFont(new Font("Dialog", Font.BOLD, 45));
				btnContinue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!teamPanel) {
							if (fieldTeamName.getText().length() < 2) {
								JOptionPane.showMessageDialog(frame, "Team name must be more than 2 characters!", "Team name too short!", JOptionPane.ERROR_MESSAGE);
								fieldTeamName.requestFocus();
							} else {
								createHeroTeam = new TeamCreationPanel(numHeroes);
								container.add(createHeroTeam, "Create Hero Team");
								btnContinue.setText("Continue");
								btnContinue.setPreferredSize(new Dimension(40, 100));
								createHeroTeam.add(btnContinue, "cell 0 " + (3 + ((numHeroes - 1) * 2)) + ",alignx right,growx");
								teamPanel = true;
								cardLayout.show(container, "Create Hero Team");
								teamName = fieldTeamName.getText();
							}
						}
						else {
							if (createHeroTeam.readyToContinue()) {
								heroes = createHeroTeam.getHeroes();
								team = new Team(teamName, heroes, numCities);
								RunGamePanel game = new RunGamePanel(team, numCities, frame);
								container.add(game, "Game");
								cardLayout.show(container, "Game");
							}
						}
					
						
					}
				});
				mainWindowDisplay.add(btnContinue, "cell 0 8 3 1,grow");
			}
}

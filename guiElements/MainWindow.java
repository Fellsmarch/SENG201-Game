package guiElements;
import java.awt.CardLayout;
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
import characters.Villain;
import commandLineElements.DiceRollsGame;
import commandLineElements.Game;
import commandLineElements.GuessNumberGame;
import commandLineElements.PaperScissorsRockGame;
import items.Item;
import items.Map;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
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
		private Team team;
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
				frame.setBounds(100, 100, 500, 375);
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
//						GUITimerCode timer = new GUITimerCode(4);
//						container.add(timer, "Timer");
//						cardLayout.show(container, "Timer");
						if(!teamPanel) {
							if (fieldTeamName.getText().length() < 2) {
								JOptionPane.showMessageDialog(frame, "Team name must be more than 2 characters!", "Team name too long!", JOptionPane.ERROR_MESSAGE);
								fieldTeamName.requestFocus();
							} else {
								createHeroTeam = new TeamCreationPanel(numHeroes);
								container.add(createHeroTeam, "Create Hero Team");
								createHeroTeam.add(btnContinue, "cell 0 " + (3 + ((numHeroes - 1) * 2)) + ",alignx right,growy");
								teamPanel = true;
								cardLayout.show(container, "Create Hero Team");
//								CreateHeroPanel hero = new CreateHeroPanel();
//								container.add(hero, "Hero");
//								cardLayout.show(container, "Hero");
							}
						}
						else {
							if (createHeroTeam.readyToContinue()) {
								heroes = createHeroTeam.getHeroes();
								team = new Team(teamName, heroes, numCities);
								String s = "Start game";
								
//								Map[] maps = new Map[numCities];
//								for (int i = 1; i <= numCities; i++) {maps[i-1] = new Map(i);}
//								ArrayList<JPanel> buildings = new ArrayList<JPanel>(Arrays.asList(new ShopPanel(maps, team), new VillainsLairPanel(), new PowerupDenPanel(), new HospitalPanel()));							
//								ArrayList<JPanel> buildings = new ArrayList<JPanel>(Arrays.asList(new ShopPanel(maps, team), new ShopPanel(maps, team),new ShopPanel(maps, team),new ShopPanel(maps, team)));							
//								Villain villain = new Villain("Jim", "The vill", new ArrayList<Game>(Arrays.asList(new PaperScissorsRockGame(), new GuessNumberGame(), new DiceRollsGame())));
//								CityPanel city = new CityPanel(buildings, villain);
//								container.add(city, "City");
//								cardLayout.show(container, "City");
								
								RunGamePanel game = new RunGamePanel(team, numCities);
								container.add(game, "Game");
								cardLayout.show(container, "Game");
								
//								ShopPanel shop = new ShopPanel(maps, team);
//								container.add(shop, "Shop");
//								cardLayout.show(container, "Shop");
							}
						}
					
						
					}
				});
				btnContinue.setSelectedIcon(new ImageIcon("/home/cosc/student/hgc25/Downloads/Continue clicked test.jpg"));
				btnContinue.setIcon(new ImageIcon("/home/cosc/student/hgc25/Downloads/Continue test.jpg"));
				mainWindowDisplay.add(btnContinue, "cell 0 8 3 1,width 50:150:150,alignx center,height 25:50:100");
				

				

			}

	}

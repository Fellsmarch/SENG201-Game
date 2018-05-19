import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;

@SuppressWarnings("serial")
public class ShopPanel extends JPanel
	{
		
		private HealingItems[] healingPotions = {new minorHealingItem(), new mediumHealingItem(), new majorHealingItem()};
		private PowerUp[] powerups = {new InfinityEdgePowerup(), new KagesLuckyPickPowerup(), new NinjaTabiPowerup()};
		private Item[] maps; //need to change Map to implement item
		private Item[][] items = {powerups, healingPotions, maps};
		private DefaultComboBoxModel<Item> itemsModel;
		private JComboBox<Item> comboItems;
		private Team team;
		private double shopMod = 1;
		

		/**
		 * Create the panel.
		 */
		public ShopPanel(Item[] maps, Team team)
			{
				this.maps = maps; //Need to change Map to implement Item
				items[2] = maps;
				this.team = team;
				boolean containsShopper = false;
				for (Hero hero : team.getHeroList()) {
					if (hero instanceof RandomHero && !containsShopper) {shopMod = hero.getShopPrice();}
					if (hero instanceof DiscountShopper) {
						shopMod = hero.getShopPrice();
						containsShopper = true;
					}
				}

				setLayout(new MigLayout("", "[215px:n][grow]", "[][][][][][grow][]"));
				
				JLabel lblShop = new JLabel("Shop");
				lblShop.setHorizontalAlignment(SwingConstants.CENTER);
				lblShop.setFont(new Font("Dialog", Font.BOLD, 25));
				add(lblShop, "cell 0 0 2 1,alignx center");
				
				JSeparator separator = new JSeparator();
				separator.setForeground(Color.BLACK);
				separator.setBackground(Color.BLACK);
				add(separator, "cell 0 1 2 1,growx");
				
				JTextPane textPane = new JTextPane();
				textPane.setEditable(false);
				add(textPane, "cell 1 3,grow");
				textPane.setBackground(UIManager.getColor("this.background"));
				
				String[] options = {"Powerups", "Healing Potions", "Maps"};
				DefaultComboBoxModel<String> itemTypeModel = new DefaultComboBoxModel<String>(options);
				JComboBox<String> comboItemType = new JComboBox<String>(itemTypeModel);
				comboItemType.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						itemsModel = new DefaultComboBoxModel<Item>(items[comboItemType.getSelectedIndex()]);
						comboItems.setModel(itemsModel);
						Item selectedItem = (Item) comboItems.getSelectedItem();
						textPane.setText(selectedItem.getDescription() + "\nPrice: " + Integer.toString(selectedItem.getPrice()));
					}
				});
				add(comboItemType, "cell 0 2,growx");
				
				JLabel lblMoney = new JLabel("Gold: " + team.getMoney());
				add(lblMoney, "cell 0 6");
			
				
				itemsModel = new DefaultComboBoxModel<Item>(powerups);
				comboItems = new JComboBox<Item>(itemsModel);
				comboItems.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Item selectedItem = (Item) comboItems.getSelectedItem();
	//					if (shopMod == 1) {String price = Integer.toString(selectedItem.getPrice());}
	//					else {
	//						String price 
	//					}
						textPane.setText(selectedItem.getDescription() + "\nPrice: " + Integer.toString(selectedItem.getPrice()));
					}
				});
				
				JTextPane paneInventory = new JTextPane();
				paneInventory.setContentType("text/html");
				add(paneInventory, "cell 1 5,aligny top");
				paneInventory.setText(getTeamInventory());
				paneInventory.setEditable(false);
				paneInventory.setBackground(UIManager.getColor("this.background"));
				
				add(comboItems, "cell 0 3,growx");
							
				JButton btnBuyItme = new JButton("Buy Item");
				btnBuyItme.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Item selectedItem = (Item) comboItems.getSelectedItem();
						int origItemPrice = selectedItem.getPrice();
						int itemPrice = (int) (origItemPrice * shopMod);
						
						if (itemPrice > team.getMoney()) {
							JOptionPane.showMessageDialog(null, "You do not have enough gold to buy this item!");
						} else {
							team.changeMoney(-itemPrice);
							lblMoney.setText( "Gold: " + team.getMoney());
							//Add item to team
							paneInventory.setText(getTeamInventory());
							
						}
					}
				});
				add(btnBuyItme, "cell 0 4,growx,aligny top");
			

				textPane.setText(((Item) comboItems.getSelectedItem()).getDescription() + "\nPrice: " + Integer.toString(((Item) comboItems.getSelectedItem()).getPrice()));
			}
		
		public String getTeamInventory() {
			String toReturn = "<html>Inventory: <br />";
			ArrayList<Item> pwrups = new ArrayList<Item>(); //These would be pulled from the team
			ArrayList<Item> healing = new ArrayList<Item>(); //These would be pulled from the team
			ArrayList<Item> mps = new ArrayList<Item>();	//These would be pulled from the team
			ArrayList<Item> inventory = new ArrayList<Item>(pwrups);
			inventory.addAll(healing); inventory.addAll(mps);
			
			Set<Item> inventorySet = new HashSet<Item>(inventory);
			if (inventorySet.size() <= 1) {return toReturn + "&ensp - &ensp Empty";}
			else {
				for (Item item : inventorySet) {
					int freq = Collections.frequency(inventory, item);
					toReturn += "&ensp - &ensp " + item + " (x" + freq + ")<br />";
				}
				return toReturn;
			}
		}
	}

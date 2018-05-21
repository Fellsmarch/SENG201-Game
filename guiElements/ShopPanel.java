package guiElements;
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

import characters.Hero;
import characters.Team;
import characters.HeroTypes.DiscountShopper;
import characters.HeroTypes.RandomHero;
import items.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;

@SuppressWarnings("serial")
public class ShopPanel extends JPanel
	{
		
		private HealingItem[] healingPotions = {new HealingItem("Potion of Minor Healing", "Heals 25% of a Hero's total health", 25, 1),
				new HealingItem("Potion of Medium Healing", "Heals 50% of a Hero's total health", 50, 2),
				new HealingItem("Potion of Major Healing", "Restores the Hero's health to Full", 100, 4)};
		private Powerup[] powerups = {new PowerupLuck(), new PowerupDamage(), new PowerupDodge()};
		private Map[] maps; //need to change Map to implement item
		private Item[][] items = {powerups, healingPotions, maps};
		private DefaultComboBoxModel<Item> itemsModel;
		private JComboBox<Item> comboItems;
		private Team team;
		private double shopMod = 1;
		private JTextPane paneInventory;
		private JLabel lblMoney;
		

		/**
		 * Create the panel.
		 */
		public ShopPanel(Map[] maps, Team team)
			{
				this.maps = maps; //Need to change Map to implement Item
				items[2] = maps;
				this.team = team;
				boolean containsShopper = false;
				//Really need to put shop mod in the team class and deal with death in team
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
				lblShop.setFont(new Font("Tahoma", Font.BOLD, 25));
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
						textPane.setText(selectedItem.getDescription() + getPrice(selectedItem));
//						textPane.setText(selectedItem.getDescription() + "\nPrice: " + Integer.toString(selectedItem.getPrice()));
					}
				});
				add(comboItemType, "cell 0 2,growx");
				
				lblMoney = new JLabel("Gold: " + team.getMoney());
				add(lblMoney, "cell 0 6");
			
				
				itemsModel = new DefaultComboBoxModel<Item>(powerups);
				comboItems = new JComboBox<Item>(itemsModel);
				comboItems.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Item selectedItem = (Item) comboItems.getSelectedItem();
						textPane.setText(selectedItem.getDescription() + getPrice(selectedItem));
								//"\nPrice: " + Integer.toString(currentPrice) + "\n(Original Price: " + originalPrice + ")");
					}
				});
				
				paneInventory = new JTextPane();
				paneInventory.setContentType("text/html");
				add(paneInventory, "cell 1 4 1 2,aligny top");
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
							JOptionPane.showMessageDialog(null, "You do not have enough gold to buy this item!", "Insufficient Gold", JOptionPane.WARNING_MESSAGE);
						} else {
							team.adjustGold(-itemPrice);
							lblMoney.setText( "Gold: " + team.getMoney());
							team.addItem(selectedItem);
//							paneInventory.setText(getTeamInventory());
							updateTeamInventory();
							
						}
					}
				});
				add(btnBuyItme, "cell 0 4,growx,aligny top");
			

				textPane.setText(((Item) comboItems.getSelectedItem()).getDescription() + getPrice((Item) comboItems.getSelectedItem()));
//				textPane.setText(((Item) comboItems.getSelectedItem()).getDescription() + "\nPrice: " + Integer.toString(((Item) comboItems.getSelectedItem()).getPrice()));
				updateTeamInventory();
			}
		
		public String getPrice(Item selectedItem) {
			String toReturn = "\nPrice: ";
			int origPrice = selectedItem.getPrice();
			if (shopMod != 1) {
				int currPrice = (int) (origPrice * shopMod);
				toReturn += currPrice + " Gold (was " + origPrice + "!)";
			} else {toReturn += origPrice + " Gold";}
			return toReturn;
			
		}
		
		public void updateTeamInventory() {
			lblMoney.setText("Gold: " + team.getMoney());
			String toReturn = "<html>Inventory: <br />";
			ArrayList<Item> inventory = team.getItemInventory();
			if (inventory.size() < 1) {paneInventory.setText(toReturn + "&ensp - &ensp Empty");}			
			else {
				Set<Item> inventorySet = new HashSet<Item>(inventory);
				for (Item item : inventorySet) {
					int freq = Collections.frequency(inventory, item);
					toReturn += "&ensp - &ensp " + item + " (x" + freq + ")<br />";
				}
//				p1221aneInventory.setText(toReturn);
				paneInventory.setText(toReturn);
			}
		}
		public String toString() {return "Shop";}
		
		public Item[][] getItems() {return items;}
	}

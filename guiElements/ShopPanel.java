package guiElements;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import characters.Team;
import items.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;

@SuppressWarnings("serial")
public class ShopPanel extends BuildingPanel
	{
		
		private Item[][] items = new Item[3][];
		private DefaultComboBoxModel<Item> itemsModel;
		private JComboBox<Item> comboItems;
		private Team team;
		private double shopMod;
		private JTextPane paneInventory;

		/**
		 * Create the panel.
		 */
		public ShopPanel(Map[] maps, Team team, Powerup[] powerups, HealingItem[] healingPotions)
			{
				items[0] = powerups; items[1] = healingPotions; items[2] = maps;
				this.team = team;
				shopMod = team.getShopMod();
				setLayout(new MigLayout("", "[210px:n][340px:n:340px]", "[][][][][][grow][]"));
				
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
						
						if (itemPrice > team.getGold()) {
							JOptionPane.showMessageDialog(null, "You do not have enough gold to buy this item!", "Insufficient Gold", JOptionPane.WARNING_MESSAGE);
						} else {
							team.adjustGold(-itemPrice);
							team.addItem(selectedItem);
							updateDisplays();
						}
					}
				});
				add(btnBuyItme, "cell 0 4,growx,aligny top");

				textPane.setText(((Item) comboItems.getSelectedItem()).getDescription() + getPrice((Item) comboItems.getSelectedItem()));
				updateDisplays();
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
		
		public void updateDisplays() {
			paneInventory.setText(team.getInventory());
//			lblMoney.setText("Gold: " + team.getGold());
//			String toReturn = "<html>Inventory: <br />";
//			ArrayList<Item> inventory = team.getItemInventory();
//			if (inventory.size() < 1) {paneInventory.setText(toReturn + "&ensp - &ensp Empty");}			
//			else {
//				Set<Item> inventorySet = new HashSet<Item>(inventory);
//				for (Item item : inventorySet) {
//					int freq = Collections.frequency(inventory, item);
//					toReturn += "&ensp - &ensp " + item + " (x" + freq + ")<br />";
//				}
//				paneInventory.setText(toReturn);
//			}
		}
		public String toString() {return "Shop";}
		
		public Item[][] getItems() {return items;}
	}

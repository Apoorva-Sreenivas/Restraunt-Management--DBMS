
//package project;
//ManagerWindow.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class ManagerWindow extends JFrame implements ActionListener {
	public ManagerWindow() {
		setTitle("Manager Window");
		setSize(300, 300);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));// 7 rows, 1 column

		JButton inventoryBtn = new JButton("Inventory");
		inventoryBtn.addActionListener(this);
		panel.add(inventoryBtn);

		JButton supplierBtn = new JButton("Supplier");
		supplierBtn.addActionListener(this);
		panel.add(supplierBtn);

		JButton supplyOrdersBtn = new JButton("Supply Orders");
		supplyOrdersBtn.addActionListener(this);
		panel.add(supplyOrdersBtn);

		JButton staffBtn = new JButton("Staff");
		staffBtn.addActionListener(this);
		panel.add(staffBtn);

		JButton itemsBtn = new JButton("Items");
		itemsBtn.addActionListener(this);
		panel.add(itemsBtn);

		JButton itemIngredientsBtn = new JButton("Item Ingredients");
		itemIngredientsBtn.addActionListener(this);
		panel.add(itemIngredientsBtn);

		JButton ordersBtn = new JButton("Orders");
		ordersBtn.addActionListener(this);
		panel.add(ordersBtn);

		add(panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Inventory")) {
			new InventoryDisplayApp().setVisible(true);
		} else if (e.getActionCommand().equals("Supplier")) {
			new SupplierDetailsApp().setVisible(true);
		} else if (e.getActionCommand().equals("Supply Orders")) {
			new SupplyOrdersWindow().setVisible(true);
		} else if (e.getActionCommand().equals("Staff")) {
			new StaffWindow().setVisible(true);
		} else if (e.getActionCommand().equals("Items")) {
			new ItemsWindow().setVisible(true);
		} else if (e.getActionCommand().equals("Item Ingredients")) {
			new ItemIngredientsWindow().setVisible(true);
		} else if (e.getActionCommand().equals("Orders")) {
			new OrdersDisplayApp().setVisible(true);
		} else {
			// Perform actions for other buttons
			JOptionPane.showMessageDialog(this, e.getActionCommand() + " Button Clicked");
		}
	}
//
//	public static void main(String[] args) {
//		new ManagerWindow().setVisible(true);
//	}
}

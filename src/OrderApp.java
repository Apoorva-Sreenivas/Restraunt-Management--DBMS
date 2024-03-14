import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderApp extends JFrame {
	private JTextField customerIdField, staffIdField;
	private List<JTextField> itemIdFields = new ArrayList<>(), quantityFields = new ArrayList<>();
	private JPanel itemPanel;
	public static DefaultListModel<String> menuModel;
	private JList<String> menuList;

	public OrderApp() {
		// Set up the JFrame
		setTitle("Order Application");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create components
		JLabel customerIdLabel = new JLabel("Customer ID:");
		JLabel staffIdLabel = new JLabel("Staff ID:");
		JLabel itemIdLabel = new JLabel("Item ID:");
		JLabel quantityLabel = new JLabel("Quantity:");
		JLabel menu = new JLabel("MENU");

		customerIdField = new JTextField(10);
		staffIdField = new JTextField(10);

		itemIdFields = new ArrayList<>();
		quantityFields = new ArrayList<>();

		JButton orderButton = new JButton("Order");
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				placeOrder();
				dispose();
			}
		});

		JButton addItemButton = new JButton("Add Item");
		addItemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewItemRow();
			}
		});

		// Set up layout using GridBagLayout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(customerIdLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		add(customerIdField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(staffIdLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		add(staffIdField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(itemIdLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		add(quantityLabel, gbc);

		// Create a panel for items
		itemPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		add(itemPanel, gbc);

		// Add initial item row
//        addNewItemRow();
		JTextField newItemIdField = new JTextField(10);
		JTextField newQuantityField = new JTextField(10);
		itemIdFields.add(newItemIdField);
		quantityFields.add(newQuantityField);
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		itemPanel.add(newItemIdField);
		gbc1.gridx = 1;
		gbc1.gridy = 0;
		itemPanel.add(newQuantityField);
		// Order and Add Item buttons
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.SOUTH;
		add(addItemButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		add(orderButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		add(menu, gbc);

		menuModel = new DefaultListModel<>();
		menuList = new JList<>(menuModel);
		dBfunctions1.fetch_menu();

		gbc.gridx = 0;
		gbc.gridy = 6;
//        gbc.gridwidth=2;

		add(new JScrollPane(menuList), gbc);

		// Display the JFrame
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addNewItemRow() {
		JTextField newItemIdField = new JTextField(10);
		JTextField newQuantityField = new JTextField(10);

		itemIdFields.add(newItemIdField);
		quantityFields.add(newQuantityField);

		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridx = 0;
		gbc1.gridy = itemIdFields.size();
		gbc1.anchor = GridBagConstraints.EAST;
		itemPanel.add(newItemIdField, gbc1);

		gbc1.gridx = 1;
		gbc1.gridy = itemIdFields.size();
		gbc1.anchor = GridBagConstraints.EAST;
		itemPanel.add(newQuantityField, gbc1);

		// Adjust panel size
		itemPanel.setPreferredSize(new Dimension(itemPanel.getWidth(), itemPanel.getHeight() + 30));

		validate();
		repaint();
	}

	private void placeOrder() {
		int customerId = Integer.parseInt(customerIdField.getText());
		int staffId = Integer.parseInt(staffIdField.getText());
		int[] items = new int[itemIdFields.size()];
		int[] quantity = new int[itemIdFields.size()];
		// Iterate through the items
		for (int i = 0; i < itemIdFields.size(); i++) {
			String itemId = itemIdFields.get(i).getText();
			String q = quantityFields.get(i).getText();
			items[i] = Integer.parseInt(itemId);
			quantity[i] = Integer.parseInt(q);
			// Perform further processing with the order information
			// For now, just display the information in a dialog
////            String message = String.format("Order placed:\nCustomer ID: %s\nStaff ID: %s\nItem ID: %s\nQuantity: %s",
//                    customerId, staffId, itemId, quantity);
//            JOptionPane.showMessageDialog(this, message, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);

		}

		dBfunctions1.new_order(customerId, staffId, items, quantity);
		JOptionPane.showMessageDialog(this, "Order Placed", "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);

	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				new OrderApp();
//			}
//		});
//	}
}

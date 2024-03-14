import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NewIngredientApp extends JFrame {
	private JTextField idField, nameField, quantityField, expiryDateField, supplierIdField;

	public NewIngredientApp() {
		setTitle("Add New Ingredient");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel idLabel = new JLabel("Ingredient ID:");
		idField = new JTextField();
		panel.add(idLabel);
		panel.add(idField);

		JLabel nameLabel = new JLabel("Ingredient Name:");
		nameField = new JTextField();
		panel.add(nameLabel);
		panel.add(nameField);

		JLabel quantityLabel = new JLabel("Quantity:");
		quantityField = new JTextField();
		panel.add(quantityLabel);
		panel.add(quantityField);

		JLabel expiryDateLabel = new JLabel("Expiry Date (YYYY-MM-DD):");
		expiryDateField = new JTextField();
		panel.add(expiryDateLabel);
		panel.add(expiryDateField);

		JLabel supplierIdLabel = new JLabel("Supplier ID:");
		supplierIdField = new JTextField();
		panel.add(supplierIdLabel);
		panel.add(supplierIdField);

		JButton addButton = new JButton("Add Ingredient");
		addButton.addActionListener(new AddButtonListener());
		panel.add(addButton);

		getContentPane().add(panel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = Integer.parseInt(idField.getText());
			String name = nameField.getText();
			int quantity = Integer.parseInt(quantityField.getText());
			String expiryDate = expiryDateField.getText();
			int supplierId = Integer.parseInt(supplierIdField.getText());

			dBfunctions1.new_ing(id, name, quantity, expiryDate, supplierId);
			System.out.println("New Ingredient Added");
			dispose();
		}

//		public static void main(String[] args) {
//			new NewIngredientApp();
//		}
	}
}

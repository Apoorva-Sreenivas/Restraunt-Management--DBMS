import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BillApp extends JFrame {
	private JTextField orderNumberField;
	private JTable billTable;
	private JButton payButton;
	private int orderNumber;
	private ResultSet resultSet;
	public BillApp() {
		setTitle("Bill Generator");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Enter Order Number:"));
		orderNumberField = new JTextField(10);
		topPanel.add(orderNumberField);
		JButton generateButton = new JButton("Generate Bill");
		generateButton.addActionListener(new GenerateButtonListener());
		topPanel.add(generateButton);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Item ID", "Item Name", "Quantity", "Unit Price", "Total Price" });
		billTable = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(billTable);
		JPanel bottomPanel = new JPanel();
		payButton = new JButton("Pay");
		payButton.setEnabled(false);
		payButton.addActionListener(new PayButtonListener());
		bottomPanel.add(payButton);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private class GenerateButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			orderNumber = Integer.parseInt(orderNumberField.getText().trim());
			try {
				 resultSet = dBfunctions1.generate_bill(orderNumber);

				// Populate the table model with query results
				DefaultTableModel model = (DefaultTableModel) billTable.getModel();
				model.setRowCount(0); // Clear existing data
				double totalAmount = 0.0;
				while (resultSet.next()) {
					String itemId;

					itemId = resultSet.getString("item_id");

					String itemName = resultSet.getString("item_name");
					int quantity = resultSet.getInt("quantity");
					double unitPrice = resultSet.getDouble("price");
					double totalPrice = quantity * unitPrice;
					totalAmount += totalPrice;
					model.addRow(new Object[] { itemId, itemName, quantity, unitPrice, totalPrice });
					model.addRow(new Object[] { "", "", "", "Total Amount:", totalAmount });

					// Enable Pay button
					payButton.setEnabled(true);
//					resultSet.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
//							e1.printStackTrace();
				new ErrorApp(e1.getMessage());
			}
		}

		// Add the total amount row to the table

		// Close connection and statement

	}

	private class PayButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Add code to handle payment
			JOptionPane.showMessageDialog(BillApp.this, "Payment completed successfully.", "Payment",
					JOptionPane.INFORMATION_MESSAGE);
			dBfunctions1.complete_order(orderNumber);
			try {
				resultSet.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				
			}
			dispose();
		}
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(BillApp::new);
//	}
}

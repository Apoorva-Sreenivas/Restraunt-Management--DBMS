
//package project;
//SupplyDeliveryApp.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class SupplyDeliveryApp extends JFrame implements ActionListener {
	private JLabel sorderNumberLabel;
	private JTextField sorderNumberField;
	private JButton deliverButton;

	public SupplyDeliveryApp() {
		setTitle("Supply Order Delivery");
		setSize(300, 150);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));// 3 rows, 1 column

		sorderNumberLabel = new JLabel("Supply Order Number:");
		panel.add(sorderNumberLabel);

		sorderNumberField = new JTextField(10);
		panel.add(sorderNumberField);

		deliverButton = new JButton("Deliver");
		deliverButton.addActionListener(this);
		panel.add(deliverButton);

		add(panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deliverButton) {
			// Perform deliver action (for demonstration, just close the window)
			dBfunctions1.deliver_supply_order(Integer.parseInt(sorderNumberField.getText()));
			JOptionPane.showMessageDialog(this, "Order " + sorderNumberField.getText() + " Delivered");
			dispose(); // Close the delivery window
			// Show menu taking data from SQL
			// new MenuDisplay().setVisible(true);
		}
	}

//	public static void main(String[] args) {
//		new SupplyDeliveryApp().setVisible(true);
//	}
}

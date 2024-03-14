
//package project;
//DeliveryWindow.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class DeliveryWindow extends JFrame implements ActionListener {
	private JLabel orderNumberLabel;
	private JTextField orderNumberField;
	private JButton deliverButton;

	public DeliveryWindow() {
		setTitle("Delivery");
		setSize(300, 150);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));// 3 rows, 1 column

		orderNumberLabel = new JLabel("Order Number:");
		panel.add(orderNumberLabel);

		orderNumberField = new JTextField(10);
		panel.add(orderNumberField);

		deliverButton = new JButton("Deliver");
		deliverButton.addActionListener(this);
		panel.add(deliverButton);

		add(panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deliverButton) {
			// Perform deliver action (for demonstration, just close the window)
			dBfunctions1.deliver_order(Integer.parseInt(orderNumberField.getText()));
			JOptionPane.showMessageDialog(this, "Order " + orderNumberField.getText() + " Delivered");
			dispose(); // Close the delivery window
			// Show menu taking data from SQL
			// new MenuDisplay().setVisible(true);
		}
	}

//	public static void main(String[] args) {
//		new DeliveryWindow().setVisible(true);
//	}
}

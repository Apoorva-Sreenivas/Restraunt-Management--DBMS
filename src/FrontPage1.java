
//package project;
//FrontPage.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class FrontPage1 extends JFrame implements ActionListener {
	private JButton managerLoginBtn, placeOrderBtn, deliverOrderBtn, newCustomerBtn, ordersBtn, billPay;

	public FrontPage1() {
		setTitle("Front Page");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();

		panel.setLayout(new GridLayout(5, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));
//     panel.setSize(300, 300);// 5 rows, 1 column

		managerLoginBtn = new JButton("Manager Login");
		managerLoginBtn.addActionListener(this);
		panel1.add(managerLoginBtn);
//     panel.add(managerLoginBtn);

		placeOrderBtn = new JButton("Place Order");
		placeOrderBtn.addActionListener(this);
		panel.add(placeOrderBtn);

		deliverOrderBtn = new JButton("Deliver Order");
		deliverOrderBtn.addActionListener(this);
		panel.add(deliverOrderBtn);

		newCustomerBtn = new JButton("New Customer");
		newCustomerBtn.addActionListener(this);
		panel.add(newCustomerBtn);

		ordersBtn = new JButton("Orders");
		ordersBtn.addActionListener(this);
		panel.add(ordersBtn);

		billPay = new JButton("Bill and Payment");
		billPay.addActionListener(this);
		panel.add(billPay);

		getContentPane().add(panel1, BorderLayout.NORTH);
		getContentPane().add(panel, BorderLayout.CENTER);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == managerLoginBtn) {
			// Perform manager login action
			new ManagerLoginWindow().setVisible(true);
		} else if (e.getSource() == placeOrderBtn) {
			// Perform place order action
//         JOptionPane.showMessageDialog(this, "Place Order Button Clicked");
			new OrderApp().setVisible(true); // orderapp1 is trial
		} else if (e.getSource() == deliverOrderBtn) {
			// Open delivery window
			new DeliveryWindow().setVisible(true);
			new OrdersDisplayApp().setVisible(true);
		} else if (e.getSource() == newCustomerBtn) {
			// Perform new customer action
			new NewCustomerWindow().setVisible(true);
		} else if (e.getSource() == ordersBtn) {
			// Perform orders action
			new OrdersDisplayApp().setVisible(true);
		} else if (e.getSource() == billPay) {
			new BillApp().setVisible(true);
		}

	}

	public static void main(String[] args) {
		new FrontPage1();
	}
}

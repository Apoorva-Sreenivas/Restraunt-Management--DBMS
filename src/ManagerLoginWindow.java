
//package project;
//ManagerLoginWindow.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class ManagerLoginWindow extends JFrame implements ActionListener {
	private JTextField usernameField, passwordField;
	private JButton loginButton;

	public ManagerLoginWindow() {
		setTitle("Manager Login");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));// 3 rows, 1 column

		usernameField = new JTextField(10);
		panel.add(new JLabel("Username:"));
		panel.add(usernameField);

		passwordField = new JPasswordField(10);
		panel.add(new JLabel("Password:"));
		panel.add(passwordField);

		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		panel.add(loginButton);

		add(panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			// Perform login action
			// For simplicity, assume login is successful and open the manager window
			int res = dBfunctions1.validate_login(usernameField.getText(), passwordField.getText());
			if (res == 1) {
				new ManagerWindow().setVisible(true);
				dispose();
			} // Close the login window
			else
//    		 JOptionPane.showMessageDialog(null, "Invlaid Login","Hey!", JOptionPane.ERROR_MESSAGE);
				new ErrorApp("Invalid Login");
		}
	}

//	public static void main(String[] args) {
//		new ManagerLoginWindow().setVisible(true);
//	}
}

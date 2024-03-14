//package project;
//NewCustomerWindow.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;

public class NewCustomerWindow extends JFrame implements ActionListener {
 private JTextField cnameField, cphoneField;
 private JButton createButton;

 public NewCustomerWindow() {
     setTitle("New Customer");
     setSize(300, 150);
     setLayout(new BorderLayout());

     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

     JPanel panel = new JPanel();
     panel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column
     panel.setBorder(new EmptyBorder(10, 20, 10, 20));
     cnameField = new JTextField(10);
     panel.add(new JLabel("Customer Name:"));
     panel.add(cnameField);

     cphoneField = new JTextField(10);
     panel.add(new JLabel("Customer Phone:"));
     panel.add(cphoneField);

     createButton = new JButton("Create");
     createButton.addActionListener(this);
     panel.add(createButton);

     add(panel);
 }

 public void actionPerformed(ActionEvent e) {
     if (e.getSource() == createButton) {
         // Perform create action
    	 try {
			dBfunctions1.new_cutomer(cnameField.getText(),Integer.parseInt(cphoneField.getText(0, 10)));
			JOptionPane.showMessageDialog(this, "Customer Created: \nName: " + cnameField.getText() + "\nPhone: " + cphoneField.getText(0,10));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			 new ErrorApp(e1.getMessage());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			 new ErrorApp(e1.getMessage());
		}
         
         dispose(); // Close the new customer window
     }
 }

// public static void main(String[] args) {
//     new NewCustomerWindow().setVisible(true);
// }
}




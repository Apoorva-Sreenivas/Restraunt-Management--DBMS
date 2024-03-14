//package project;
//NewSupplyOrderApp.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;

public class NewSupplyOrderApp extends JFrame implements ActionListener {
 private JTextField ingId, quantity,supId;
 private JButton createButton;

 public NewSupplyOrderApp() {
     setTitle("New Supply Order");
     setSize(300, 200);
     setLayout(new BorderLayout());

     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

     JPanel panel = new JPanel();
     panel.setLayout(new GridLayout(4, 1, 10, 10)); // 3 rows, 1 column
     panel.setBorder(new EmptyBorder(10, 20, 10, 20));
     ingId = new JTextField(10);
     panel.add(new JLabel("Ingredient ID:"));
     panel.add(ingId);

     quantity = new JTextField(10);
     panel.add(new JLabel("Quantity:"));
     panel.add(quantity);

     supId = new JTextField(10);
     panel.add(new JLabel("Supplier ID:"));
     panel.add(supId);

     createButton = new JButton("Create");
     createButton.addActionListener(this);
     panel.add(createButton);

     add(panel);
 }

 public void actionPerformed(ActionEvent e) {
     if (e.getSource() == createButton) {
         // Perform create action
    	
			dBfunctions1.new_supply_order(Integer.parseInt(ingId.getText()), Integer.parseInt(quantity.getText()), Integer.parseInt(supId.getText()));
//			JOptionPane.showMessageDialog(this, "Customer Created: \nName: " + cnameField.getText() + "\nPhone: " + cphoneField.getText(0,10));
		
         dispose(); // Close the new customer window
     }
 }

// public static void main(String[] args) {
//     new NewSupplyOrderApp().setVisible(true);
// }
}




//package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrdersWindow extends JFrame {
    public OrdersWindow() {
        setTitle("Orders");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Simulated data from SQL
        String[] orders = {"Order 1", "Order 2", "Order 3", "Order 4", "Order 5"};

        // Create menu panel
        JPanel menuPanel = new JPanel(new GridLayout(orders.length, 1));
        for (String order : orders) {
            menuPanel.add(new JLabel(order));
        }

        // Add menu panel to the frame
        add(menuPanel);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new OrdersWindow().setVisible(true);
//        });
//    }
}

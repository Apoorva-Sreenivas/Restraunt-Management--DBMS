import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SupplyOrdersWindow extends JFrame {
    private JTable supplyOrderTable;
    private JButton placeOrderButton, deliverOrderButton;

    public SupplyOrdersWindow() {
        setTitle("Supply Order Details");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create table model with columns: SOrder ID, Ingredient ID, Ingredient Name, Quantity, Supplier ID, Status, Order Date
        DefaultTableModel model = new DefaultTableModel(new String[]{"Supply Order ID", "Ingredient ID", "Ingredient Name", "Quantity", "Supplier ID", "Status", "Order Date"}, 0);

        // Fetch data from SQL and populate the table model
        dBfunctions1.fetch_supply_orders(model);

        // Create JTable with the populated model
        supplyOrderTable = new JTable(model);

        // Set table properties
        supplyOrderTable.setRowHeight(30); // Set row height
        supplyOrderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        supplyOrderTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set header font
        supplyOrderTable.getTableHeader().setForeground(Color.WHITE); // Set header text color
        supplyOrderTable.getTableHeader().setBackground(Color.DARK_GRAY); // Set header background color
        supplyOrderTable.setGridColor(Color.LIGHT_GRAY); // Set grid color
        supplyOrderTable.setShowGrid(true); // Show grid lines
        supplyOrderTable.setIntercellSpacing(new Dimension(0, 0)); // Set spacing between cells to zero

        // Add JScrollPane with the table to the content pane
        JScrollPane scrollPane = new JScrollPane(supplyOrderTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(new PlaceOrderButtonListener());
        deliverOrderButton = new JButton("Deliver Order");
        deliverOrderButton.addActionListener(new DeliverOrderButtonListener());
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(deliverOrderButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    

    private class PlaceOrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add code for placing order
//            JOptionPane.showMessageDialog(SupplyOrdersWindow.this, "Order placed successfully.", "Order Placed", JOptionPane.INFORMATION_MESSAGE);
        	new NewSupplyOrderApp().setVisible(true);
        }
    }

    private class DeliverOrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add code for delivering order
//            JOptionPane.showMessageDialog(SupplyOrdersWindow.this, "Order delivered successfully.", "Order Delivered", JOptionPane.INFORMATION_MESSAGE);
        	new SupplyDeliveryApp().setVisible(true);
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(SupplyOrdersWindow::new);
//    }
}

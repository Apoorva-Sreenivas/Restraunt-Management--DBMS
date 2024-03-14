

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import javax.swing.border.Border;

public class OrdersDisplayApp extends JFrame {
    private JTable ordersTable;

    public OrdersDisplayApp() {
        setTitle("Orders Display");
        setSize(850, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create table model with columns: Order ID, Customer Name, Staff Name, Date, Status
        DefaultTableModel model = new DefaultTableModel(new String[]{"Order ID", "Customer Name", "Staff Name", "Date", "Status"}, 0);

        // Fetch data from SQL and populate the table model
        dBfunctions1.fetch_orders(model);

        // Create JTable with the populated model
        ordersTable = new JTable(model);
        ordersTable.setRowHeight(30);// Set row height
        ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent auto-resizing of columns
        ordersTable.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        ordersTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Customize header font

        // Customize column widths
        ordersTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Order ID
        ordersTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Customer Name
        ordersTable.getColumnModel().getColumn(2).setPreferredWidth(200); // Staff Name
        ordersTable.getColumnModel().getColumn(3).setPreferredWidth(150); // Date
        ordersTable.getColumnModel().getColumn(4).setPreferredWidth(150); // Status
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ordersTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }



//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new OrdersDisplayApp());
//    }
}

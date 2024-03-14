import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SupplierDetailsApp extends JFrame {
    private JTable supplierTable;

    public SupplierDetailsApp() {
        setTitle("Supplier Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create table model with columns: Supplier ID, Name, Phone, Address
        DefaultTableModel model = new DefaultTableModel(new String[]{"Supplier ID", "Name", "Phone", "Address"}, 0);

        // Fetch data from SQL and populate the table model
        dBfunctions1.fetch_supplier(model);

        // Create JTable with the populated model
        supplierTable = new JTable(model);

        // Set table properties
        supplierTable.setRowHeight(30); // Set row height
        supplierTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        supplierTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set header font
        supplierTable.getTableHeader().setForeground(Color.WHITE); // Set header text color
        supplierTable.getTableHeader().setBackground(Color.DARK_GRAY); // Set header background color
        supplierTable.setGridColor(Color.LIGHT_GRAY); // Set grid color
        supplierTable.setShowGrid(true); // Show grid lines
        supplierTable.setIntercellSpacing(new Dimension(0, 0)); // Set spacing between cells to zero

        // Add JScrollPane with the table to the content pane
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(SupplierDetailsApp::new);
//    }
}

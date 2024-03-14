import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ItemsWindow extends JFrame {
    private JTable itemTable;

    public ItemsWindow() {
        setTitle("Item Details");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create table model with columns: Item ID, Item Name, Price
        DefaultTableModel model = new DefaultTableModel(new String[]{"Item ID", "Item Name", "Price"}, 0);

        // Fetch data from SQL and populate the table model
        dBfunctions1.fetch_items(model);

        // Create JTable with the populated model
        itemTable = new JTable(model);

        // Set table properties
        itemTable.setRowHeight(30); // Set row height
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        itemTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set header font
        itemTable.getTableHeader().setForeground(Color.WHITE); // Set header text color
        itemTable.getTableHeader().setBackground(Color.DARK_GRAY); // Set header background color
        itemTable.setGridColor(Color.LIGHT_GRAY); // Set grid color
        itemTable.setShowGrid(true); // Show grid lines
        itemTable.setIntercellSpacing(new Dimension(0, 0)); // Set spacing between cells to zero

        // Add JScrollPane with the table to the content pane
        JScrollPane scrollPane = new JScrollPane(itemTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

   

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(ItemsWindow::new);
//    }
}

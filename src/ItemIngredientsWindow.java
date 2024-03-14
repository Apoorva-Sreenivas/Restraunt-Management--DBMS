import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ItemIngredientsWindow extends JFrame {
    private JTable itemIngredientsTable;

    public ItemIngredientsWindow() {
        setTitle("Item Ingredients");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table model with columns: Item ID, Item Name, Ingredient ID, Ingredient Name
        DefaultTableModel model = new DefaultTableModel(new String[]{"Item ID", "Item Name", "Ingredient ID", "Ingredient Name"}, 0);

        // Fetch data from SQL and populate the table model
       dBfunctions1.fetch_item_ingredients(model);

        // Create JTable with the populated model
        itemIngredientsTable = new JTable(model);

        // Set table properties
        itemIngredientsTable.setRowHeight(30); // Set row height
        itemIngredientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        itemIngredientsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set header font
        itemIngredientsTable.getTableHeader().setForeground(Color.WHITE); // Set header text color
        itemIngredientsTable.getTableHeader().setBackground(Color.DARK_GRAY); // Set header background color
        itemIngredientsTable.setGridColor(Color.LIGHT_GRAY); // Set grid color
        itemIngredientsTable.setShowGrid(true); // Show grid lines
        itemIngredientsTable.setIntercellSpacing(new Dimension(0, 0)); // Set spacing between cells to zero

        // Add JScrollPane with the table to the content pane
        JScrollPane scrollPane = new JScrollPane(itemIngredientsTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(ItemIngredientsWindow::new);
//    }
}

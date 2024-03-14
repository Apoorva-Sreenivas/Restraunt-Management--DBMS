import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StaffWindow extends JFrame {
    private JTable staffTable;

    public StaffWindow() {
        setTitle("Staff Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create table model with columns: Staff ID, Name, Position, Phone
        DefaultTableModel model = new DefaultTableModel(new String[]{"Staff ID", "Name", "Position", "Phone"}, 0);

        // Fetch data from SQL and populate the table model
        dBfunctions1.fetch_staff(model);

        // Create JTable with the populated model
        staffTable = new JTable(model);

        // Set table properties
        staffTable.setRowHeight(30); // Set row height
        staffTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        staffTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set header font
        staffTable.getTableHeader().setForeground(Color.WHITE); // Set header text color
        staffTable.getTableHeader().setBackground(Color.DARK_GRAY); // Set header background color
        staffTable.setGridColor(Color.LIGHT_GRAY); // Set grid color
        staffTable.setShowGrid(true); // Show grid lines
        staffTable.setIntercellSpacing(new Dimension(0, 0)); // Set spacing between cells to zero

        // Add JScrollPane with the table to the content pane
        JScrollPane scrollPane = new JScrollPane(staffTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(StaffWindow::new);
//    }
}

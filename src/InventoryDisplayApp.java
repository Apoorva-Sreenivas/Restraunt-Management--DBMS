import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InventoryDisplayApp extends JFrame implements ActionListener {
	private JTable inventoryTable;
	private JButton new_ing;

	public InventoryDisplayApp() {
		setTitle("Inventory Display");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create table model with columns: Ingredient ID, Ingredient Name, Quantity,
		// Expiry Date, Supplier ID
		DefaultTableModel model = new DefaultTableModel(
				new String[] { "Ingredient ID", "Ingredient Name", "Quantity", "Expiry Date", "Supplier ID" }, 0);

		// Fetch data from SQL and populate the table model
		dBfunctions1.fetch_inventory(model);

		// Create JTable with the populated model
		inventoryTable = new JTable(model);
		inventoryTable.setDefaultRenderer(Object.class, new QuantityCellRenderer()); // Set custom cell renderer for
																						// Quantity column
		inventoryTable.setRowHeight(30); // Set row height
		inventoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
		inventoryTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set header font
		inventoryTable.getTableHeader().setForeground(Color.BLUE); // Set header text color
		inventoryTable.getTableHeader().setBackground(Color.LIGHT_GRAY); // Set header background color
		inventoryTable.setGridColor(Color.BLACK); // Set grid color
		inventoryTable.setShowGrid(true); // Show grid lines
		inventoryTable.setIntercellSpacing(new Dimension(0, 0)); // Set spacing between cells to zero
		// Add JScrollPane with the table to the content pane
		JScrollPane scrollPane = new JScrollPane(inventoryTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel p1 = new JPanel();
		new_ing = new JButton("NEW INGREDIENT");
		new_ing.addActionListener(this);
		p1.add(new_ing);
		add(p1, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == new_ing) {
			new NewIngredientApp().setVisible(true);

		}
	}

	private class QuantityCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if (column == 2 && value instanceof Integer && (Integer) value < 10) {
				cell.setForeground(Color.RED); // Set text color to red if quantity is less than 10 and it's the
												// quantity column
			} else {
				cell.setForeground(table.getForeground()); // Reset the text color to default
			}
			return cell;
		}
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> new InventoryDisplayApp());
//	}
}

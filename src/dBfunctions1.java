import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dBfunctions1 {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/restraunt-management";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Jungkook97";
	public static Scanner in = new Scanner(System.in);

	public static int validate_login(String uname, String pass) {
		String get_pass = "select password from manager where username=(?)";
		String ret_pass = "";
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(get_pass);
			preparedStatement.setString(1, uname);
			ResultSet p1 = preparedStatement.executeQuery();
			while (p1.next()) {
				ret_pass = p1.getString(1);
				break;
			}
//			System.out.println(ret_pass);
			if (pass.equals(ret_pass))
				return 1;
			else
				return 0;
		} catch (SQLException e) {
//            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}
		return 0;
	}

	public static void new_order(int cid, int sid, int items[], int quantity[]) {
		Date date = new Date(System.currentTimeMillis());
		;
		String insert_new_order = "insert into orders (c_id,staff_id,date,status)  values(?,?,?,?)";
		String order_items = "insert into ord_items values (?,?,?)";
		String fetch_ordno = "select order_id from orders order by  order_id desc";
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(insert_new_order);

			preparedStatement.setInt(1, cid);
			preparedStatement.setInt(2, sid);
			preparedStatement.setDate(3, date);
			preparedStatement.setString(4, "P");
			preparedStatement.executeUpdate();

			PreparedStatement st1 = connection.prepareStatement(order_items);
			PreparedStatement st2 = connection.prepareStatement(fetch_ordno);
			ResultSet r = st2.executeQuery();
			int ord_no = 0;
			while (r.next()) {
				ord_no = r.getInt(1);
				break;
			}

			for (int n = 0; n < items.length; n++) {
				st1.setInt(1, ord_no);
				st1.setInt(2, items[n]);
				st1.setInt(3, quantity[n]);
				st1.executeUpdate();
			}

			// Close the PreparedStatement object and the connection to the SQL database.
			st1.close();
			preparedStatement.close();
			connection.close();
//			System.out.println("Executed1");
//	             ResultSet resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
//	            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}
	}

	public static void deliver_order(int ord_no) {
		String update_order = "update orders set status='D' where order_id=?";
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(update_order);
			preparedStatement.setInt(1, ord_no);
			preparedStatement.executeUpdate();
			update_inventory_orders(ord_no);
			connection.close();
//			System.out.println("Executed update order");
		} catch (SQLException e) {
//            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}
	}

	public static void complete_order(int ord_no) {
		String update_order = "update orders set status='C' where order_id=?";
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(update_order);
			preparedStatement.setInt(1, ord_no);
			preparedStatement.executeUpdate();
//			   update_inventory(ord_no);
			connection.close();
//			System.out.println("Executed complete order");
		} catch (SQLException e) {
//            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}
	}

	// for orders
	public static void update_inventory_orders(int ord_no) {
		String select_ing = "SELECT ii.ingredient_id,oi.quantity FROM orders o, ord_items oi, items i, item_ing ii WHERE o.order_id=? and o.order_id=oi.order_id and oi.item_id=i.item_id and i.item_id=ii.ITEM_ID";
		String update_inv = "update inventory  set quantity=quantity-? where ingredient_id=?";
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement st1 = connection.prepareStatement(select_ing);
			PreparedStatement st2 = connection.prepareStatement(update_inv);
			st1.setInt(1, ord_no);
			ResultSet r1 = st1.executeQuery();
			while (r1.next()) {
				int ing = r1.getInt(1);
				int num = r1.getInt(2);
				st2.setInt(1, num);
				st2.setInt(2, ing);
				st2.executeUpdate();
			}

			connection.close();
//			System.out.println("Executed3");
		} catch (SQLException e) {
//            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}

	}

	public static void new_cutomer(String name, int Phone) {
		String fetch_cno = "select c_id from customer order by c_id ";
		String insert_customer = "insert into customer values (?,?,?)";
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(insert_customer);
			PreparedStatement st2 = connection.prepareStatement(fetch_cno);
			ResultSet r = st2.executeQuery();
			int c_no = 0;
			while (r.next()) {
				c_no = r.getInt(1) + 1;

			}
			preparedStatement.setInt(1, c_no);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, Phone);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
//			System.out.println("Executed new cusotmer");
//	             ResultSet resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
//	            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}

	}

	public static void fetch_menu() {
		// JDBC connection parameters
//        String url = "jdbc:mysql://localhost:3306/restraunt-management";
//        String user = "root";
//        String password = "Jungkook97";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			String sql = "SELECT item_id, item_name FROM items";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int itemId = resultSet.getInt("item_id");
						String itemName = resultSet.getString("item_name");
						OrderApp.menuModel.addElement(itemId + ": " + itemName);
					}
				}
			}
		} catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
			new ErrorApp(e.getMessage());
		}
	}

	public static void fetch_orders(DefaultTableModel model) {
		// JDBC connection parameters

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			String sql = "SELECT o.order_id, c.cname, s.stname, o.date, o.status " + "FROM orders o "
					+ "JOIN customer c ON o.c_id = c.c_id "
					+ "JOIN staff s ON o.staff_id = s.staff_id order by o.order_id";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int orderId = resultSet.getInt("order_id");
						String customerName = resultSet.getString("cname");
						String staffName = resultSet.getString("stname");
						Date date = resultSet.getDate("date");
						String status = resultSet.getString("status");

						// Add a row to the table model
						model.addRow(new Object[] { orderId, customerName, staffName, date, status });
					}
				}
			}
		} catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog( "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
			new ErrorApp(e.getMessage());
		}
	}

	public static void fetch_inventory(DefaultTableModel model) {
		// JDBC connection parameters

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			String sql = "SELECT ingredient_id, ing_name, quantity, expiry, supplier_id FROM inventory";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int ingredientId = resultSet.getInt("ingredient_id");
						String ingredientName = resultSet.getString("ing_name");
						int quantity = resultSet.getInt("quantity");
						Date expiryDate = resultSet.getDate("expiry");
						int supplierId = resultSet.getInt("supplier_id");

						// Add a row to the table model
						model.addRow(new Object[] { ingredientId, ingredientName, quantity, expiryDate, supplierId });
					}
				}
			}
		} catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
			new ErrorApp(e.getMessage());
		}
	}

	public static void new_ing(int id, String name, int quantity, String expiry, int sup_id) {
		try {

			// Establish connection
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			// Create SQL statement
			String sql = "INSERT INTO inventory " + "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Set parameters
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setInt(3, quantity);
			statement.setString(4, expiry);
			statement.setInt(5, sup_id);

			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException ex) {
//            ex.printStackTrace();
			new ErrorApp(ex.getMessage());
//            JOptionPane.showMessageDialog(IngredientAddApp.this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static ResultSet generate_bill(int ord_no) {

		try {
			// JDBC connection parameters

			// Establish connection
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			// Create SQL statement
			String sql = "SELECT oi.item_id, i.item_name, oi.quantity, i.price " + "FROM ord_items oi "
					+ "JOIN items i ON oi.item_id = i.item_id " + "WHERE oi.order_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ord_no);

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

//            resultSet.close();
//            statement.close();
//            connection.close();
			return resultSet;
		} catch (SQLException ex) {
//            ex.printStackTrace();
			new ErrorApp(ex.getMessage());
//            JOptionPane.showMessageDialog(BillApp.this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public static void fetch_supplier(DefaultTableModel model) {
		// JDBC connection parameters

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			String sql = "SELECT supplier_id, name, phone, address FROM supplier";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int supplierId = resultSet.getInt("supplier_id");
						String name = resultSet.getString("name");
						String phone = resultSet.getString("phone");
						String address = resultSet.getString("address");

						// Add a row to the table model
						model.addRow(new Object[] { supplierId, name, phone, address });
					}
				}
			}
		} catch (SQLException e) {
			new ErrorApp(e.getMessage());
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void fetch_supply_orders(DefaultTableModel model) {

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			String sql = "SELECT so.sorder_id, i.ingredient_id, i.ing_name, so.quantity, so.supplier_id, so.status, so.order_date "
					+ "FROM supply_orders so " + "JOIN inventory i ON so.ingredient_id = i.ingredient_id";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int sorderId = resultSet.getInt("sorder_id");
						int ingredientId = resultSet.getInt("ingredient_id");
						String ingName = resultSet.getString("ing_name");
						int quantity = resultSet.getInt("quantity");
						int supplierId = resultSet.getInt("supplier_id");
						String status = resultSet.getString("status");
						Date orderDate = resultSet.getDate("order_date");

						// Add a row to the table model
						model.addRow(new Object[] { sorderId, ingredientId, ingName, quantity, supplierId, status,
								orderDate });
					}
				}
			}
		} catch (SQLException e) {
			new ErrorApp(e.getMessage());
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void new_supply_order(int ing_id, int quantity, int sup_id) {

		Date date = new Date(System.currentTimeMillis());
		;
		String insert_new_order = "insert into supply_orders (ingredient_id,quantity,supplier_id,status,order_date)  values(?,?,?,?,?)";

		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(insert_new_order);

			preparedStatement.setInt(1, ing_id);
			preparedStatement.setInt(2, quantity);
			preparedStatement.setInt(3, sup_id);
			preparedStatement.setDate(5, date);
			preparedStatement.setString(4, "P");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
//		            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}
	}

	public static void deliver_supply_order(int sord_no) {
		String update_order = "update supply_orders set status='D' where sorder_id=?";
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(update_order);
			preparedStatement.setInt(1, sord_no);
			preparedStatement.executeUpdate();
			update_inventory_supply(sord_no);
			connection.close();
//			System.out.println("Executed update supply order");
		} catch (SQLException e) {
//            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}
	}

	public static void update_inventory_supply(int sord_no) {
		String select_ing = "select ingredient_id,quantity from supply_orders where sorder_id=?";
		String update_inv = "update inventory  set quantity=quantity+? where ingredient_id=?";
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement st1 = connection.prepareStatement(select_ing);
			PreparedStatement st2 = connection.prepareStatement(update_inv);
			st1.setInt(1, sord_no);
			ResultSet r1 = st1.executeQuery();
			while (r1.next()) {
				int ing = r1.getInt(1);
				int num = r1.getInt(2);
				st2.setInt(1, num);
				st2.setInt(2, ing);
				st2.executeUpdate();
			}

			connection.close();
//			System.out.println("Inveroty Updated with supply order addition");
		} catch (SQLException e) {
//            e.printStackTrace();
			new ErrorApp(e.getMessage());
		}

	}

	public static void fetch_staff(DefaultTableModel model) {

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			String sql = "SELECT staff_id, stname, position, phone FROM staff";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int staffId = resultSet.getInt("staff_id");
						String name = resultSet.getString("stname");
						String position = resultSet.getString("position");
						String phone = resultSet.getString("phone");

						// Add a row to the table model
						model.addRow(new Object[] { staffId, name, position, phone });
					}
				}
			}
		} catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
			new ErrorApp(e.getMessage());
		}
	}

	public static void fetch_items(DefaultTableModel model) {

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			String sql = "SELECT item_id, item_name, price FROM items";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int itemId = resultSet.getInt("item_id");
						String itemName = resultSet.getString("item_name");
						double price = resultSet.getDouble("price");

						// Add a row to the table model
						model.addRow(new Object[] { itemId, itemName, price });
					}
				}
			}
		} catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
			new ErrorApp(e.getMessage());
		}
	}

	public static void fetch_item_ingredients(DefaultTableModel model) {

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
			String sql = "select i.item_id,i.item_name,ii.ingredient_id,inv.ing_name from items i, item_ing ii, inventory inv where i.ITEM_ID = ii.ITEM_ID and ii.INGREDIENT_ID = inv.INGREDIENT_ID";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int itemId = resultSet.getInt("item_id");
						String itemName = resultSet.getString("item_name");
						int ingredientId = resultSet.getInt("ingredient_id");
						String ingredientName = resultSet.getString("ing_name");

						// Add a row to the table model
						model.addRow(new Object[] { itemId, itemName, ingredientId, ingredientName });

					}
				}
			}
		} catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Error", JOptionPane.ERROR_MESSAGE);
			new ErrorApp(e.getMessage());
		}
	}

//	public static void main(String args[]) {
//		/*
//		 * FOR NEW ORDER System.out.
//		 * println("Enter order number,customer id , staff id and number of items ordered "
//		 * ); int ord_no = in.nextInt(); int cid = in.nextInt(); int sid = in.nextInt();
//		 * int n = in.nextInt(); int items[] = new int [n]; int quantity[] = new int[n];
//		 * System.out.println("Enter the item id and quantity ordered"); for(int
//		 * i=0;i<n;i++) { items[i]=in.nextInt(); quantity[i]=in.nextInt(); }
//		 * new_order(ord_no,cid,sid,items,quantity);
//		 */
//
//		// FOR DELIVERING ORDER AND UPDATING INVENTORY
//		System.out.println("Enter order number to be deliverd");
//		int ord_no = in.nextInt();
//		deliver_order(ord_no);
//	}
}

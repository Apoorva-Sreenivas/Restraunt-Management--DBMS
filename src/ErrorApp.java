import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ErrorApp extends JFrame {

	public ErrorApp(String x) {

		JOptionPane.showInternalMessageDialog(null, x, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
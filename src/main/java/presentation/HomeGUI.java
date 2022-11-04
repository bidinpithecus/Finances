package presentation;

import business.Finances;
import javax.swing.*;
import java.awt.*;

public class HomeGUI extends JFrame {
	private static Finances finances;

	public HomeGUI(Finances finances) {
		HomeGUI.finances = finances;
		initComponents();
	}

	public static void centerFrame(JFrame jFrame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - jFrame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - jFrame.getHeight()) / 2);
		jFrame.setLocation(x, y);
	}

	private void initComponents() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jPanel.setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Home");
		add(jPanel);
		pack();
		setSize(800, 600);
		setLocationRelativeTo(null);
		centerFrame(this);

		int padding = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(padding, padding, padding, padding);
		gbc.anchor = GridBagConstraints.WEST;
		JLabel presentation = new JLabel("Ola, " + finances.getLogged().getName());
		presentation.setFont(MyFonts.H1.getFont());
		presentation.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(presentation, gbc);

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}

}

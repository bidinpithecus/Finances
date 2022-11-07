package presentation;

import business.Finances;
import javax.swing.*;
import java.awt.*;

public class HomeGUI extends JFrame {
	private static Finances finances;

	public HomeGUI(Finances finances) {
		if (finances.isUserLogged()) {
			HomeGUI.finances = finances;
			initComponents();
		} else {
			LoginGUI loginGUI = new LoginGUI(finances);
			loginGUI.setVisible(true);
			dispose();
		}
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

		JButton newSpentButton = new JButton("New Spent");
		newSpentButton.setFont(MyFonts.H1.getFont());
		newSpentButton.setPreferredSize(new Dimension(238, 26));
		newSpentButton.setForeground(Color.WHITE);
		newSpentButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		newSpentButton.setBorderPainted(false);
		newSpentButton.addActionListener(e -> {
			NewSpentGUI newSpentGUI = new NewSpentGUI(finances);
			newSpentGUI.setVisible(true);
			dispose();
		});
		gbc.gridx++;
		gbc.gridy++;
		jPanel.add(newSpentButton, gbc);


		getContentPane().requestFocusInWindow();
		setVisible(true);
	}

}

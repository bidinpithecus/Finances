package presentation;

import javax.swing.*;
import java.awt.*;

public class ErrorMessageGUI extends JFrame {
	private static JLabel errorLabel;

	public ErrorMessageGUI(String text) {
		errorLabel = new JLabel(text);
		initComponents();
	}

	public static void centerFrame(JFrame jFrame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - jFrame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - jFrame.getHeight()) / 2);
		jFrame.setLocation(x, y);
	}

	public void initComponents() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jPanel.setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Error");
		add(jPanel);
		pack();
		setSize(400, 300);
		setLocationRelativeTo(null);
		centerFrame(this);

		int padding = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(padding, padding, padding, padding);
		gbc.anchor = GridBagConstraints.CENTER;

		errorLabel.setFont(MyFonts.H1Bold.getFont());
		errorLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(errorLabel, gbc);

		JButton checkUser = new JButton("OK");
		checkUser.setOpaque(true);
		checkUser.setFont(MyFonts.H1Bold.getFont());
		checkUser.setForeground(Color.decode(MyColors.TITLE.toString()));
		checkUser.setBackground(Color.decode(MyColors.SUBTITLE.toString()));
		checkUser.setBorderPainted(false);
		checkUser.addActionListener(e -> {
			dispose();
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(checkUser, gbc);

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}
}

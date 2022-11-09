package presentation;

import business.Finances;
import data.Spent;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

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
		JLabel presentation = new JLabel("Hello, " + finances.getLogged().getName());
		presentation.setFont(MyFonts.H1.getFont());
		presentation.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(presentation, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel activitiesLabel = new JLabel("Activities");
		activitiesLabel.setFont(MyFonts.H1.getFont());
		activitiesLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(activitiesLabel, gbc);

		JButton newSpentButton = new JButton("New Spent");
		newSpentButton.setFont(MyFonts.H2.getFont());
		newSpentButton.setPreferredSize(new Dimension(160, 26));
		newSpentButton.setForeground(Color.WHITE);
		newSpentButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		newSpentButton.setBorderPainted(false);
		newSpentButton.addActionListener(e -> {
			NewSpentGUI newSpentGUI = new NewSpentGUI(finances);
			newSpentGUI.setVisible(true);
			dispose();
		});
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(newSpentButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JLabel hrLabel = new JLabel("<html>\n" +
				"<head>\n" +
				"<style type=\"text/css\">\n" +
				"body { margin: 0; width: 300px; }\n" +
				"</style>\n" +
				"</head>\n" +
				"<body>\n" +
				"<div>\n" +
				"<hr>\n" +
				"</div>\n" +
				"</body>\n" +
				"</html>");
		hrLabel.setFont(MyFonts.H1.getFont());
		hrLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(hrLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		if (finances.listSpent().isEmpty()) {
			JLabel noActivitiesLabel = new JLabel("No activities registered");
			noActivitiesLabel.setFont(MyFonts.H1.getFont());
			noActivitiesLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
			jPanel.add(noActivitiesLabel, gbc);
		} else {
			gbc.anchor = GridBagConstraints.WEST;
			for (Spent spent : finances.listSpent()) {
				JPanel spentPanel = new JPanel();
				spentPanel.setLayout(new GridBagLayout());
				GridBagConstraints gbcSpent = new GridBagConstraints();
				setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				spentPanel.setBackground(Color.WHITE);

				JLabel titleLabel = new JLabel("Ifood");
				titleLabel.setFont(MyFonts.H2.getFont());
				titleLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				titleLabel.setBackground(Color.WHITE);
				titleLabel.setOpaque(false);
				gbcSpent.gridx = 0;
				gbcSpent.gridy++;
				spentPanel.add(titleLabel, gbcSpent);

				JLabel priceLabel = new JLabel("$12");
				priceLabel.setFont(MyFonts.H2.getFont());
				priceLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				priceLabel.setBackground(Color.WHITE);
				priceLabel.setOpaque(false);
				gbcSpent.gridx++;
				spentPanel.add(priceLabel, gbcSpent);

				gbc.gridx = 0;
				gbc.gridy++;
				jPanel.add(spentPanel, gbc);

				gbc.gridx = 0;
				gbc.gridy++;
				JLabel hrLoopLabel = new JLabel("<html>\n" +
						"<head>\n" +
						"<style type=\"text/css\">\n" +
						"body { margin: 0; width: 300px; }\n" +
						"</style>\n" +
						"</head>\n" +
						"<body>\n" +
						"<div>\n" +
						"<hr>\n" +
						"</div>\n" +
						"</body>\n" +
						"</html>");
				hrLoopLabel.setFont(MyFonts.H1.getFont());
				hrLoopLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				jPanel.add(hrLoopLabel, gbc);

			}
		}

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}

}

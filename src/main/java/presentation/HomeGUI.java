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
		gbc.gridx++;
		jPanel.add(newSpentButton, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy++;

		if (finances.listSpent().isEmpty()) {
			JLabel noActivitiesLabel = new JLabel("No activities registered");
			noActivitiesLabel.setFont(MyFonts.H1.getFont());
			noActivitiesLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
			jPanel.add(noActivitiesLabel, gbc);
		} else {
			JLabel noActivitiesLabel = new JLabel("<html><p><hr></p></html>");
			noActivitiesLabel.setFont(MyFonts.H1.getFont());
			noActivitiesLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
			jPanel.add(noActivitiesLabel, gbc);
			for (Spent spent : finances.listSpent()) {
				gbc.gridx = 0;
				gbc.gridy++;
				JButton loginButton = new JButton("<html><p>" + spent.getName() + " - $"+ spent.getValue() + " - " + spent.getDate().get(Calendar.DATE) + "/" + (spent.getDate().get(Calendar.MONTH) + 1) + "/" + spent.getDate().get(Calendar.YEAR) + "<hr></p></html>");
				loginButton.setFont(MyFonts.H3.getFont());
				loginButton.setForeground(Color.decode(MyColors.TITLE.toString()));
				loginButton.setBackground(Color.WHITE);
				loginButton.setOpaque(false);
				loginButton.setContentAreaFilled(false);
				loginButton.setBorderPainted(false);
				loginButton.setFocusPainted(false);
				loginButton.addActionListener(e -> {
					JFrame loginGUI = new LoginGUI(finances);
					loginGUI.setVisible(true);
					dispose();
				});
				jPanel.add(loginButton, gbc);
			}
		}

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}

}

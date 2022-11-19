package presentation;

import business.Finances;

import javax.swing.*;
import java.awt.*;

public class FilterGUI extends JFrame {
	private static Finances finances;

	public FilterGUI(Finances finances) {
		FilterGUI.finances = finances;
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
		setTitle("Filter");
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

		JLabel noWayBackLabel = new JLabel("Filter by");
		noWayBackLabel.setFont(MyFonts.H1Bold.getFont());
		noWayBackLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(noWayBackLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JButton categoryFilterButton = new JButton("Category");
		categoryFilterButton.setOpaque(true);
		categoryFilterButton.setFont(MyFonts.H1Bold.getFont());
		categoryFilterButton.setForeground(Color.WHITE);
		categoryFilterButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		categoryFilterButton.setBorderPainted(false);
		categoryFilterButton.setFocusPainted(false);
		categoryFilterButton.addActionListener(e -> {
			CategoryGUI categoryGUI = new CategoryGUI(finances);
			categoryGUI.setVisible(true);
			dispose();
		});
		jPanel.add(categoryFilterButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JButton monthFilterButton = new JButton("Month");
		monthFilterButton.setOpaque(true);
		monthFilterButton.setFont(MyFonts.H1Bold.getFont());
		monthFilterButton.setForeground(Color.WHITE);
		monthFilterButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		monthFilterButton.setBorderPainted(false);
		monthFilterButton.setFocusPainted(false);
		monthFilterButton.addActionListener(e -> {
			MonthGUI monthGUI = new MonthGUI(finances);
			monthGUI.setVisible(true);
			dispose();
		});
		jPanel.add(monthFilterButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JButton returnButton = new JButton("<html><p><span style=\"color: " + MyColors.BLUE + "\">Return</span></p></html>");
		returnButton.setFont(MyFonts.H3Plain.getFont());
		returnButton.setForeground(Color.decode(MyColors.TITLE.toString()));
		returnButton.setBackground(Color.WHITE);
		returnButton.setOpaque(false);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		returnButton.setFocusPainted(false);
		returnButton.addActionListener(e -> {
			HomeGUI homeGUI = new HomeGUI(finances);
			homeGUI.setVisible(true);
			dispose();
		});
		jPanel.add(returnButton, gbc);

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}
}

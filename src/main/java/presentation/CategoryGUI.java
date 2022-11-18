package presentation;

import business.Finances;
import data.Category;

import javax.swing.*;
import java.awt.*;

public class CategoryGUI extends JFrame {
	private static Finances finances;

	public CategoryGUI(Finances finances) {
		CategoryGUI.finances = finances;
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
		setTitle("Category");
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

		for (Category category : Category.values()) {
			JButton categoryButton = new JButton(category.toString());
			categoryButton.setFont(MyFonts.H1Plain.getFont());
			categoryButton.setForeground(Color.WHITE);
			categoryButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
			categoryButton.setBorderPainted(false);
			categoryButton.setFocusPainted(false);
			categoryButton.setPreferredSize(new Dimension(180, 26));
			categoryButton.addActionListener(e -> {
				HomeGUI homeGUI = new HomeGUI(finances, category);
				homeGUI.setVisible(true);
				dispose();
			});
			jPanel.add(categoryButton, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
		}

		JButton returnButton = new JButton("<html><p><span style=\"color: " + MyColors.BLUE + "\">Return</span></p></html>");
		returnButton.setFont(MyFonts.H2Plain.getFont());
		returnButton.setForeground(Color.decode(MyColors.TITLE.toString()));
		returnButton.setBackground(Color.WHITE);
		returnButton.setOpaque(false);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		returnButton.setFocusPainted(false);
		returnButton.addActionListener(e -> {
			FilterGUI filterGUI = new FilterGUI(finances);
			filterGUI.setVisible(true);
			dispose();
		});
		jPanel.add(returnButton, gbc);

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}
}

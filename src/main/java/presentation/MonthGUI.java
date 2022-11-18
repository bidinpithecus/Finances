package presentation;

import business.Finances;

import javax.swing.*;
import java.awt.*;
import java.time.Month;

public class MonthGUI extends JFrame {
	private static Finances finances;

	public MonthGUI(Finances finances) {
		MonthGUI.finances = finances;
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
		setTitle("Month");
		add(jPanel);
		pack();
		setSize(400, 600);
		setLocationRelativeTo(null);
		centerFrame(this);

		int padding = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(padding, padding, padding, padding);
		gbc.anchor = GridBagConstraints.CENTER;

		for (Month month : Month.values()) {
			JButton monthButton = new JButton(month.toString());
			monthButton.setFont(MyFonts.H1Plain.getFont());
			monthButton.setForeground(Color.WHITE);
			monthButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
			monthButton.setBorderPainted(false);
			monthButton.setFocusPainted(false);
			monthButton.setPreferredSize(new Dimension(180, 26));
			monthButton.addActionListener(e -> {
				HomeGUI homeGUI = new HomeGUI(finances, month.getValue());
				homeGUI.setVisible(true);
				dispose();			});
			jPanel.add(monthButton, gbc);

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

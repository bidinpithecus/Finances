package presentation;

import business.DateValidator;
import business.Finances;
import data.Category;
import data.Spent;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeleteSpentGUI extends JFrame {
	private static Finances finances;
	private Spent spentToBeDeleted;

	public DeleteSpentGUI(Finances finances, UUID id) {
		DeleteSpentGUI.finances = finances;
		spentToBeDeleted = finances.getSpentById(id);
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
		setTitle("Delete spent");
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

		JLabel sureLabel = new JLabel("Are you sure?");
		sureLabel.setFont(MyFonts.H1Bold.getFont());
		sureLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(sureLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy++;

		JLabel noWayBackLabel = new JLabel("There's no way back");
		noWayBackLabel.setFont(MyFonts.H2Plain.getFont());
		noWayBackLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(noWayBackLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JButton deleteButton = new JButton("Delete");
		deleteButton.setOpaque(true);
		deleteButton.setFont(MyFonts.H1Bold.getFont());
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBackground(Color.decode(MyColors.RED.toString()));
		deleteButton.setBorderPainted(false);
		deleteButton.setFocusPainted(false);
		deleteButton.addActionListener(e -> {
			if (finances.deleteSpent(spentToBeDeleted.getIndex())) {
				HomeGUI homeGUI = new HomeGUI(finances);
				homeGUI.setVisible(true);
				dispose();
			} else {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Unable to edit it!");
				errorMessageGUI.setVisible(true);
			}
		});
		jPanel.add(deleteButton, gbc);

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

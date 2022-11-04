package presentation;

import business.Finances;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

public class ChangePasswordGUI extends JFrame {
	private static Finances finances;
	private static JPasswordField passwordField;
	private static JPasswordField passwordConfirmationField;

	public ChangePasswordGUI(Finances finances) {
		ChangePasswordGUI.finances = finances;
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
		setTitle("Change your Password");
		add(jPanel);
		pack();
		setSize(600, 400);
		setLocationRelativeTo(null);
		centerFrame(this);

		int padding = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(padding, padding, padding, padding);
		gbc.anchor = GridBagConstraints.CENTER;

		JLabel registerLabel = new JLabel("Change Password");
		registerLabel.setFont(MyFonts.H1.getFont());
		registerLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(registerLabel, gbc);

		passwordField = new JPasswordField("Password", 18);
		passwordField.setPreferredSize(new Dimension(241, 26));
		passwordField.setFont(MyFonts.H2.getFont());
		passwordField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		passwordField.setEchoChar((char) 0);
		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (passwordField.getPassword().length != 0) {
					passwordField.setText("");
					passwordField.setForeground(Color.decode(MyColors.TITLE.toString()));
					passwordField.setEchoChar('*');
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (passwordField.getPassword().length == 0) {
					passwordField.setText("Password");
					passwordField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
					passwordField.setEchoChar((char) 0);
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(passwordField, gbc);

		passwordConfirmationField = new JPasswordField("Confirm password", 18);
		passwordConfirmationField.setPreferredSize(new Dimension(241, 26));
		passwordConfirmationField.setFont(MyFonts.H2.getFont());
		passwordConfirmationField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		passwordConfirmationField.setEchoChar((char) 0);
		passwordConfirmationField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (passwordConfirmationField.getPassword().length != 0) {
					passwordConfirmationField.setText("");
					passwordConfirmationField.setForeground(Color.decode(MyColors.TITLE.toString()));
					passwordConfirmationField.setEchoChar('*');
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (passwordConfirmationField.getPassword().length == 0) {
					passwordConfirmationField.setText("Confirm password");
					passwordConfirmationField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
					passwordConfirmationField.setEchoChar((char) 0);
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(passwordConfirmationField, gbc);

		JButton changeButton = new JButton("Change");
		changeButton.setFont(MyFonts.H1.getFont());
		changeButton.setPreferredSize(new Dimension(238, 26));
		changeButton.setForeground(Color.WHITE);
		changeButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		changeButton.setBorderPainted(false);
		changeButton.addActionListener(e -> {
			if (Arrays.equals(passwordField.getPassword(), passwordConfirmationField.getPassword())) {
				if (finances.changePassword(finances.getLogged().getName(), finances.getLogged().getLogin(), finances.getLogged().getPhone(), finances.getLogged().getBirthDate(), passwordField.getPassword())) {
					JFrame loginGUI = new LoginGUI(finances);
					loginGUI.setVisible(true);
					dispose();
				} else {
					ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning! User not logged");
					errorMessageGUI.setVisible(true);
				}
			} else {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Passwords diverges!");
				errorMessageGUI.setVisible(true);
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(changeButton, gbc);

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}
}

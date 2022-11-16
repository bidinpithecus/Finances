package presentation;

import business.Finances;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class LoginGUI extends JFrame {
	private static Finances finances;
	private static JTextField usernameField;
	private static JPasswordField passwordField;

	public LoginGUI(Finances finances) {
		if (finances.isUserLogged()) {
			HomeGUI homeGUI = new HomeGUI(finances);
			homeGUI.setVisible(true);
			dispose();
		} else {
			LoginGUI.finances = finances;
			initComponents();
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
		setTitle("Login");
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
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(MyFonts.H1Bold.getFont());
		usernameLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(usernameLabel, gbc);

		usernameField = new JTextField("Enter your username", 18);
		usernameField.setPreferredSize(new Dimension(241, 26));
		usernameField.setFont(MyFonts.H2Plain.getFont());
		usernameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		usernameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (usernameField.getText().equals("Enter your username")) {
					usernameField.setText("");
					usernameField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (usernameField.getText().isEmpty()) {
					usernameField.setText("Enter your username");
					usernameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(usernameField, gbc);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(MyFonts.H1Bold.getFont());
		passwordLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(passwordLabel, gbc);

		passwordField = new JPasswordField("Enter your password", 18);
		passwordField.setPreferredSize(new Dimension(241, 26));
		passwordField.setFont(MyFonts.H2Plain.getFont());
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
					passwordField.setText("Enter your password");
					passwordField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
					passwordField.setEchoChar((char) 0);
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(passwordField, gbc);

		JButton loginButton = new JButton("Login");
		loginButton.setFont(MyFonts.H1Bold.getFont());
		loginButton.setPreferredSize(new Dimension(238, 26));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		loginButton.setBorderPainted(false);
		loginButton.addActionListener(e -> {

			if (finances.login(usernameField.getText(), passwordField.getPassword())) {
				HomeGUI homeGUI = new HomeGUI(finances);
				homeGUI.setVisible(true);
				dispose();
			} else {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Login Failed!");
				errorMessageGUI.setVisible(true);
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(loginButton, gbc);

		JButton forgotPasswordButton = new JButton("<html><p>Forgot your <span style=\"color: " + MyColors.BLUE + "\">password</span><span>?</span></p></html>");
		forgotPasswordButton.setFont(MyFonts.H3Plain.getFont());
		forgotPasswordButton.setForeground(Color.decode(MyColors.TITLE.toString()));
		forgotPasswordButton.setOpaque(false);
		forgotPasswordButton.setContentAreaFilled(false);
		forgotPasswordButton.setBorderPainted(false);
		forgotPasswordButton.setFocusPainted(false);
		forgotPasswordButton.addActionListener(e -> {
			ConfirmUserGUI confirmUserGUI = new ConfirmUserGUI(finances);
			confirmUserGUI.setVisible(true);
			dispose();
		});

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(forgotPasswordButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JButton signInButton = new JButton("<html><p>Don't have an account? <span style=\"color: " + MyColors.BLUE + "\">Sign in</span></p></html>");
		signInButton.setFont(MyFonts.H3Plain.getFont());
		signInButton.setForeground(Color.decode(MyColors.TITLE.toString()));
		signInButton.setBackground(Color.WHITE);
		signInButton.setOpaque(false);
		signInButton.setContentAreaFilled(false);
		signInButton.setBorderPainted(false);
		signInButton.setFocusPainted(false);
		signInButton.addActionListener(e -> {
			RegisterGUI registerGUI = new RegisterGUI(finances);
			registerGUI.setVisible(true);
			dispose();
		});
		jPanel.add(signInButton, gbc);

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}

}

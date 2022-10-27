package presentation;

import business.Finances;
import data.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import javax.swing.*;

public class LoginGUI implements ActionListener {
	private static JTextField usernameField;
	private static JPasswordField passwordField;
	public static void centerFrame(JFrame jFrame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - jFrame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - jFrame.getHeight()) / 2);
		jFrame.setLocation(x, y);
	}

	public static void main(String[] args) {
		Font title = new Font("Consolas", Font.BOLD, 18);
		Font subTitle = new Font("Consolas", Font.PLAIN, 14);
		Font subSubTitle = new Font("Consolas", Font.PLAIN, 12);

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jPanel.setBackground(Color.WHITE);
		jFrame.setTitle("Login");
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setSize(800, 600);
		jFrame.setLocationRelativeTo(null);
		centerFrame(jFrame);

		int padding = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(padding, padding, padding, padding);
		gbc.anchor = GridBagConstraints.WEST;
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(title);
		usernameLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(usernameLabel, gbc);

		usernameField = new JTextField("Enter your username", 18);
		usernameField.setPreferredSize(new Dimension(241, 26));
		usernameField.setFont(subTitle);
		usernameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		usernameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (usernameField.getText().equals("Enter your username")) {
					usernameField.setText("");
					usernameField.setForeground(Color.BLACK);
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
		passwordLabel.setFont(title);
		passwordLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(passwordLabel, gbc);

		passwordField = new JPasswordField("Enter your password", 18);
		passwordField.setPreferredSize(new Dimension(241, 26));
		passwordField.setFont(subTitle);
		passwordField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		passwordField.setEchoChar((char) 0);
		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (passwordField.getPassword().length != 0) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
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
		loginButton.setFont(title);
		loginButton.setPreferredSize(new Dimension(238, 26));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		loginButton.setBorderPainted(false);
		loginButton.addActionListener(new LoginGUI());

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(loginButton, gbc);

		JButton forgotPassword = new JButton("<html><p>Forgot your <span style=\"color: " + MyColors.BLUE + "\">password</span><span>?</span></p></html>");
		forgotPassword.setFont(subSubTitle);
		forgotPassword.setForeground(Color.decode(MyColors.TITLE.toString()));
		forgotPassword.setBackground(Color.WHITE);
		forgotPassword.setBorderPainted(false);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(forgotPassword, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JButton signIn = new JButton("<html><p>Don't have an account? <span style=\"color: " + MyColors.BLUE + "\">Sign in</span></p></html>");
		signIn.setFont(subSubTitle);
		signIn.setForeground(Color.decode(MyColors.TITLE.toString()));
		signIn.setBackground(Color.WHITE);
		signIn.setBorderPainted(false);

		jPanel.add(signIn, gbc);

		jFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Finances finances = new Finances();

		Calendar birthDate = Calendar.getInstance();
		birthDate.set(2000, Calendar.OCTOBER, 20);

		User user = new User("Moyses Marinus", "user", "qwe123", "7366504967", birthDate);

		finances.newUser(user);

		if (finances.login(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
			JOptionPane.showMessageDialog(null, "Login Successful");
		} else {
			JOptionPane.showMessageDialog(null, "Login Failed");
		}
	}
}

package presentation;

import business.Finances;
import data.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);

		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jPanel.setBackground(new Color(64, 64, 64));
		jFrame.setTitle("Login");
		jFrame.add(jPanel);
		jFrame.setSize(1200, 600);
		centerFrame(jFrame);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setBounds(100, 8, 100, 20);
		jPanel.add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setBounds(100, 27, 193, 28);
		jPanel.add(usernameField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(100, 55, 100, 20);
		jPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(100, 75, 193, 28);
		jPanel.add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(100, 110, 90, 25);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLACK);
		loginButton.addActionListener(new LoginGUI());
		jPanel.add(loginButton);

		jFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Finances finances = new Finances();

		Calendar birthDate = Calendar.getInstance();
		birthDate.set(2000, Calendar.OCTOBER, 20);

		User user = new User("Moyses Marinus", "moysesMarinus", "qwe123", "7366504967", birthDate);

		finances.newUser(user);

		if (finances.login(usernameField.getText(), passwordField.getText())) {
			JOptionPane.showMessageDialog(null, "Login Successful");
		} else {
			JOptionPane.showMessageDialog(null, "Login Failed");
		}
	}
}

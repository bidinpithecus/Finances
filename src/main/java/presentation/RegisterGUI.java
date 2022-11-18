package presentation;

import business.DateValidator;
import business.Finances;
import data.User;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;

public class RegisterGUI extends JFrame {
	private static Finances finances;
	private static JTextField fullNameField;
	private static JTextField usernameField;
	private static JTextField phoneField;
	private static JFormattedTextField birthDateField;
	private static JPasswordField passwordField;
	private static JPasswordField passwordConfirmationField;

	public RegisterGUI(Finances finances) {
		RegisterGUI.finances = finances;
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
		setTitle("Register");
		add(jPanel);
		pack();
		setSize(800, 600);
		setLocationRelativeTo(null);
		centerFrame(this);

		int padding = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(padding, padding, padding, padding);
		gbc.anchor = GridBagConstraints.CENTER;

		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(MyFonts.H1Bold.getFont());
		registerLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(registerLabel, gbc);

		fullNameField = new JTextField("Full name", 18);
		fullNameField.setPreferredSize(new Dimension(241, 26));
		fullNameField.setFont(MyFonts.H2Plain.getFont());
		fullNameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		fullNameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (fullNameField.getText().equals("Full name")) {
					fullNameField.setText("");
					fullNameField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (fullNameField.getText().isEmpty()) {
					fullNameField.setText("Full name");
					fullNameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(fullNameField, gbc);

		usernameField = new JTextField("Username", 18);
		usernameField.setPreferredSize(new Dimension(241, 26));
		usernameField.setFont(MyFonts.H2Plain.getFont());
		usernameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		usernameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (usernameField.getText().equals("Username")) {
					usernameField.setText("");
					usernameField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (usernameField.getText().isEmpty()) {
					usernameField.setText("Username");
					usernameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(usernameField, gbc);

		phoneField = new JTextField("Phone", 18);
		phoneField.setPreferredSize(new Dimension(241, 26));
		phoneField.setFont(MyFonts.H2Plain.getFont());
		phoneField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		phoneField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH))) {
					ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Invalid character!");
					errorMessageGUI.setVisible(true);
					e.consume();
				}
			}
		});
		phoneField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (phoneField.getText().equals("Phone")) {
					phoneField.setText("");
					phoneField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (phoneField.getText().isEmpty()) {
					phoneField.setText("Phone");
					phoneField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(phoneField, gbc);

		MaskFormatter maskFormatter = null;
		try {
			maskFormatter = new MaskFormatter("##/##/####");
			maskFormatter.setPlaceholder("_");
		} catch(ParseException e) {
			e.printStackTrace();
		}

		birthDateField = new JFormattedTextField(maskFormatter);
		birthDateField.setName("Birth date");
		birthDateField.setPreferredSize(new Dimension(241, 26));
		birthDateField.setFont(MyFonts.H2Plain.getFont());
		birthDateField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		birthDateField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH))) {
					ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Invalid character!");
					errorMessageGUI.setVisible(true);
					e.consume();
				}
			}
		});

		birthDateField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (birthDateField.getText().equals("_ /  /    ")) {
					birthDateField.setText("");
					birthDateField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (birthDateField.getText().equals("_ /  /    ")) {
					birthDateField.setText("");
					birthDateField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(birthDateField, gbc);

		passwordField = new JPasswordField("Password", 18);
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
		passwordConfirmationField.setFont(MyFonts.H2Plain.getFont());
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

		JButton registerButton = new JButton("Register");
		registerButton.setFont(MyFonts.H1Bold.getFont());
		registerButton.setPreferredSize(new Dimension(238, 26));
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		registerButton.setBorderPainted(false);
		registerButton.addActionListener(e -> {
			if (Arrays.equals(passwordField.getPassword(), passwordConfirmationField.getPassword())) {
				DateValidator dateValidator = new DateValidator("dd/mm/yyyy");
				String pattern = "^[1-9]\\d*$";
				if (!phoneField.getText().matches(pattern)) {
					ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Invalid phone number!");
					errorMessageGUI.setVisible(true);
				} else {
					if (!dateValidator.isValid(birthDateField.getText())) {
						ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Invalid date!");
						errorMessageGUI.setVisible(true);
					} else {
						Calendar birthDate = Calendar.getInstance();
						String[] dateStr = birthDateField.getText().split("/");
						birthDate.set(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[0]));
						User user = new User(fullNameField.getText(), usernameField.getText(), passwordField.getPassword(), phoneField.getText(), birthDate);
						if (finances.newUser(user)) {
							LoginGUI loginGUI = new LoginGUI(finances);
							loginGUI.setVisible(true);
							dispose();
						} else {
							ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, User already exists!");
							errorMessageGUI.setVisible(true);
						}
					}
				}
			} else {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Passwords diverges!");
				errorMessageGUI.setVisible(true);
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(registerButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JButton loginButton = new JButton("<html><p>Already have an account? <span style=\"color: " + MyColors.BLUE + "\">Login</span></p></html>");
		loginButton.setFont(MyFonts.H3Plain.getFont());
		loginButton.setForeground(Color.decode(MyColors.TITLE.toString()));
		loginButton.setBackground(Color.WHITE);
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setBorderPainted(false);
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(e -> {
			LoginGUI loginGUI = new LoginGUI(finances);
			loginGUI.setVisible(true);
			dispose();
		});
		jPanel.add(loginButton, gbc);

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}
}

package presentation;

import business.DateValidator;
import business.Finances;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Calendar;

public class ConfirmUserGUI extends JFrame {
	private static Finances finances;
	private static JTextField fullNameField;
	private static JTextField usernameField;
	private static JTextField phoneField;
	private static JFormattedTextField birthDateField;

	public ConfirmUserGUI(Finances finances) {
		ConfirmUserGUI.finances = finances;
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
		setTitle("Confirm your User");
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

		JLabel registerLabel = new JLabel("Confirm that's your user");
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

		JButton checkUser = new JButton("Check");
		checkUser.setOpaque(true);
		checkUser.setFont(MyFonts.H1Bold.getFont());
		checkUser.setPreferredSize(new Dimension(238, 26));
		checkUser.setForeground(Color.WHITE);
		checkUser.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		checkUser.setBorderPainted(false);
		checkUser.addActionListener(e -> {
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
					if (finances.confirmedUser(fullNameField.getText(), usernameField.getText(), phoneField.getText(), birthDate)) {
						ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI(finances);
						dispose();
						changePasswordGUI.setVisible(true);
					} else {
						ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, User not found!");
						errorMessageGUI.setVisible(true);
					}
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(checkUser, gbc);

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
			LoginGUI loginGUI = new LoginGUI(finances);
			loginGUI.setVisible(true);
			dispose();
		});
		jPanel.add(returnButton, gbc);

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}
}

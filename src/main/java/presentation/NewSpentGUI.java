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
import java.util.UUID;

public class NewSpentGUI extends JFrame {
	private static Finances finances;
	private JTextField nameField;
	private JTextField descriptionField;
	private JFormattedTextField dateField;
	private JTextField valueField;
	private JComboBox<Category> categoryField;

	public NewSpentGUI(Finances finances) {
		NewSpentGUI.finances = finances;
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
		setTitle("New spent");
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

		JLabel registerLabel = new JLabel("Add new spent");
		registerLabel.setFont(MyFonts.H1Bold.getFont());
		registerLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(registerLabel, gbc);

		nameField = new JTextField("Name", 18);
		nameField.setPreferredSize(new Dimension(241, 26));
		nameField.setFont(MyFonts.H2Plain.getFont());
		nameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		nameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (nameField.getText().equals("Name")) {
					nameField.setText("");
					nameField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (nameField.getText().isEmpty()) {
					nameField.setText("Name");
					nameField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(nameField, gbc);

		descriptionField = new JTextField("Description", 18);
		descriptionField.setPreferredSize(new Dimension(241, 26));
		descriptionField.setFont(MyFonts.H2Plain.getFont());
		descriptionField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		descriptionField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (descriptionField.getText().equals("Description")) {
					descriptionField.setText("");
					descriptionField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (descriptionField.getText().isEmpty()) {
					descriptionField.setText("Description");
					descriptionField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(descriptionField, gbc);

		MaskFormatter maskFormatter = null;
		try {
			maskFormatter = new MaskFormatter("##/##/####");
			maskFormatter.setPlaceholder("_");
		} catch(ParseException e) {
			e.printStackTrace();
		}

		dateField = new JFormattedTextField(maskFormatter);
		dateField.setName("Date");
		dateField.setPreferredSize(new Dimension(241, 26));
		dateField.setFont(MyFonts.H2Plain.getFont());
		dateField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		dateField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH))) {
					ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Invalid character!");
					errorMessageGUI.setVisible(true);
					e.consume();
				}
			}
		});

		dateField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (dateField.getText().equals("_ /  /    ")) {
					dateField.setText("");
					dateField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (dateField.getText().equals("  /  /    ")) {
					dateField.setText("");
					dateField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(dateField, gbc);

		valueField = new JFormattedTextField("Value");
		valueField.setName("Value");
		valueField.setPreferredSize(new Dimension(241, 26));
		valueField.setFont(MyFonts.H2Plain.getFont());
		valueField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
		valueField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH) || (c == '.'))) {
					ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Invalid character!");
					errorMessageGUI.setVisible(true);
					e.consume();
				}
			}
		});

		valueField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (valueField.getText().equals("Value")) {
					valueField.setText("");
					valueField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (valueField.getText().equals("")) {
					valueField.setText("Value");
					valueField.setForeground(Color.decode(MyColors.SUBTITLE.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(valueField, gbc);

		categoryField = new JComboBox<>(Category.values());
		categoryField.setPreferredSize(new Dimension(238, 26));
		categoryField.setFont(MyFonts.H2Plain.getFont());
		categoryField.setForeground(Color.decode(MyColors.TITLE.toString()));

		categoryField.setSelectedIndex(0);
		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(categoryField, gbc);

		JButton registerButton = new JButton("Add");
		registerButton.setOpaque(true);
		registerButton.setFont(MyFonts.H1Bold.getFont());
		registerButton.setPreferredSize(new Dimension(238, 26));
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		registerButton.setBorderPainted(false);
		registerButton.addActionListener(e -> {
			Spent newSpent;
			DateValidator dateValidator = new DateValidator("dd/mm/yyyy");
			if (dateValidator.isValid(dateField.getText())) {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Invalid date!");
				errorMessageGUI.setVisible(true);
				return;
			} else {
				newSpent = new Spent();
				Calendar date = Calendar.getInstance();
				String[] dateStr = dateField.getText().split("/");
				date.set(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[0]));
				newSpent.setDate(date);
			}
			newSpent.setIndex(UUID.randomUUID());
			newSpent.setName(nameField.getText());
			newSpent.setDescription(descriptionField.getText());

			try {
				Float.parseFloat(valueField.getText());
			} catch (NumberFormatException ex) {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Invalid value!");
				errorMessageGUI.setVisible(true);
				return;
			}

			float value = Float.parseFloat(valueField.getText());
			newSpent.setValue(value);
			Object selectedItem = categoryField.getSelectedItem();
			newSpent.setCategory((Category) selectedItem);

			if (finances.newSpent(newSpent)) {
				HomeGUI homeGUI = new HomeGUI(finances);
				homeGUI.setVisible(true);
				dispose();
			} else {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Unable to add it!");
				errorMessageGUI.setVisible(true);
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(registerButton, gbc);

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

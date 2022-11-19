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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class EditSpentGUI extends JFrame {
	private static Finances finances;
	private final Spent spentToBeEdited;
	private JTextField nameField;
	private JTextField descriptionField;
	private JFormattedTextField dateField;
	private JTextField valueField;
	private JComboBox<Category> categoryField;

	public EditSpentGUI(Finances finances, UUID id) {
		EditSpentGUI.finances = finances;
		spentToBeEdited = finances.getSpentById(id);
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
		setTitle("Edit spent");
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

		JLabel editSpentLabel = new JLabel("Edit spent");
		editSpentLabel.setFont(MyFonts.H1Bold.getFont());
		editSpentLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(editSpentLabel, gbc);

		nameField = new JTextField(spentToBeEdited.getName(), 18);
		nameField.setPreferredSize(new Dimension(241, 26));
		nameField.setFont(MyFonts.H2Plain.getFont());
		nameField.setForeground(Color.decode(MyColors.SUBTITLE2.toString()));
		nameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (nameField.getText().equals(spentToBeEdited.getName())) {
					nameField.setText("");
					nameField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (nameField.getText().isEmpty()) {
					nameField.setText(spentToBeEdited.getName());
					nameField.setForeground(Color.decode(MyColors.SUBTITLE2.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(nameField, gbc);

		descriptionField = new JTextField(spentToBeEdited.getDescription(), 18);
		descriptionField.setPreferredSize(new Dimension(241, 26));
		descriptionField.setFont(MyFonts.H2Plain.getFont());
		descriptionField.setForeground(Color.decode(MyColors.SUBTITLE2.toString()));
		descriptionField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (descriptionField.getText().equals(spentToBeEdited.getDescription())) {
					descriptionField.setText("");
					descriptionField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (descriptionField.getText().isEmpty()) {
					descriptionField.setText(spentToBeEdited.getDescription());
					descriptionField.setForeground(Color.decode(MyColors.SUBTITLE2.toString()));
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

		String spentString = String.format("%02d", spentToBeEdited.getDate().get(Calendar.DATE)) + "/" + String.format("%02d", spentToBeEdited.getDate().get(Calendar.MONTH)) + "/" + String.format("%02d", spentToBeEdited.getDate().get(Calendar.YEAR));
		System.out.println(spentString);

		dateField = new JFormattedTextField(maskFormatter);
		dateField.setText(spentString);
		dateField.setPreferredSize(new Dimension(241, 26));
		dateField.setFont(MyFonts.H2Plain.getFont());
		dateField.setForeground(Color.decode(MyColors.SUBTITLE2.toString()));
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
				if (dateField.getText().equals(spentString)) {
					dateField.setText("");
					dateField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (dateField.getText().equals("_ /  /    ")) {
					dateField.setText(spentString);
					dateField.setForeground(Color.decode(MyColors.SUBTITLE2.toString()));
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(dateField, gbc);

		valueField = new JFormattedTextField(String.valueOf(spentToBeEdited.getValue()));
		valueField.setName(String.valueOf(spentToBeEdited.getValue()));
		valueField.setPreferredSize(new Dimension(241, 26));
		valueField.setFont(MyFonts.H2Plain.getFont());
		valueField.setForeground(Color.decode(MyColors.SUBTITLE2.toString()));
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
				if (valueField.getText().equals(String.valueOf(spentToBeEdited.getValue()))) {
					valueField.setText("");
					valueField.setForeground(Color.decode(MyColors.TITLE.toString()));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (valueField.getText().equals("")) {
					valueField.setText(String.valueOf(spentToBeEdited.getValue()));
					valueField.setForeground(Color.decode(MyColors.SUBTITLE2.toString()));
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
		int categoryValuesIndex = 0;
		for (Category category : Category.values()) {
			if (category.equals(spentToBeEdited.getCategory())) {
				categoryField.setSelectedIndex(categoryValuesIndex);
			}
			categoryValuesIndex++;
		}

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(categoryField, gbc);
		AtomicBoolean ableToEdit = new AtomicBoolean(true);

		JButton editButton = new JButton("Edit");
		editButton.setOpaque(true);
		editButton.setFont(MyFonts.H1Bold.getFont());
		editButton.setPreferredSize(new Dimension(238, 26));
		editButton.setForeground(Color.WHITE);
		editButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		editButton.setBorderPainted(false);
		editButton.addActionListener(e -> {
			DateValidator dateValidator = new DateValidator("dd/mm/yyyy");
			if (!dateValidator.isValid(dateField.getText())) {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Invalid date!");
				errorMessageGUI.setVisible(true);
				return;
			} else {
				Calendar date = Calendar.getInstance();
				String[] dateStr = dateField.getText().split("/");
				date.set(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[0]));
				if (!Objects.equals(spentToBeEdited.getName(), nameField.getText())) {
					ableToEdit.set(finances.editSpent(spentToBeEdited.getIndex(), 1, nameField.getText()));
				}
				if (!Objects.equals(spentToBeEdited.getDescription(), descriptionField.getText())) {
					ableToEdit.set(finances.editSpent(spentToBeEdited.getIndex(), 2, descriptionField.getText()));
				}
				if (!Objects.equals(spentToBeEdited.getDate(), date)) {
					ableToEdit.set(finances.editSpent(spentToBeEdited.getIndex(), date));
				}

				try {
					Float.parseFloat(valueField.getText());
				} catch (NumberFormatException ex) {
					ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Invalid value!");
					errorMessageGUI.setVisible(true);
					return;
				}

				float value = Float.parseFloat(valueField.getText());
				if (!Objects.equals(spentToBeEdited.getValue(), value)) {
					ableToEdit.set(finances.editSpent(spentToBeEdited.getIndex(), value));
				}
				if (!Objects.equals(spentToBeEdited.getCategory(), categoryField.getSelectedItem())) {
					ableToEdit.set(finances.editSpent(spentToBeEdited.getIndex(), (Category) categoryField.getSelectedItem()));
				}
			}
			System.out.println(finances.getSpentById(spentToBeEdited.getIndex()));
			if (ableToEdit.get()) {
				HomeGUI homeGUI = new HomeGUI(finances);
				homeGUI.setVisible(true);
				dispose();
			} else {
				ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI("Warning, Unable to edit it!");
				errorMessageGUI.setVisible(true);
			}
		});

		gbc.gridx = 0;
		gbc.gridy++;
		jPanel.add(editButton, gbc);

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

package presentation;

import business.Finances;
import data.Category;
import data.Spent;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Calendar;

public class HomeGUI extends JFrame {
	private static Finances finances;
	private List<Spent> spentsToBeListed;

	public HomeGUI(Finances finances) {
		if (finances.isUserLogged()) {
			HomeGUI.finances = finances;
			spentsToBeListed = finances.listSpent();
			initComponents();
		} else {
			LoginGUI loginGUI = new LoginGUI(finances);
			loginGUI.setVisible(true);
			dispose();
		}
	}

	public HomeGUI(Finances finances, int monthOfFilter) {
		if (finances.isUserLogged()) {
			HomeGUI.finances = finances;
			spentsToBeListed = finances.listSpent(monthOfFilter);
			initComponents();
		} else {
			LoginGUI loginGUI = new LoginGUI(finances);
			loginGUI.setVisible(true);
			dispose();
		}
	}

	public HomeGUI(Finances finances, Category category) {
		if (finances.isUserLogged()) {
			HomeGUI.finances = finances;
			spentsToBeListed = finances.listSpent(category);
			initComponents();
		} else {
			LoginGUI loginGUI = new LoginGUI(finances);
			loginGUI.setVisible(true);
			dispose();
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
		setTitle("Home");
		add(jPanel);

		JScrollPane scrollPane = new JScrollPane(jPanel);
		scrollPane.createVerticalScrollBar();

		add(scrollPane);
		pack();
		setSize(800, 600);
		setLocationRelativeTo(null);
		centerFrame(this);

		int padding = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(padding, padding, padding, padding);
		gbc.anchor = GridBagConstraints.WEST;

		JButton logoffButton = new JButton("<html><p><span style=\"color: " + MyColors.BLUE + "\">Logoff</span></p></html>");
		logoffButton.setFont(MyFonts.H3Plain.getFont());
		logoffButton.setForeground(Color.decode(MyColors.TITLE.toString()));
		logoffButton.setBackground(Color.WHITE);
		logoffButton.setOpaque(false);
		logoffButton.setContentAreaFilled(false);
		logoffButton.setBorderPainted(false);
		logoffButton.setFocusPainted(false);
		logoffButton.addActionListener(e -> {
			finances.logoff();
			LoginGUI loginGUI = new LoginGUI(finances);
			loginGUI.setVisible(true);
			dispose();
		});
		jPanel.add(logoffButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;

		JLabel presentation = new JLabel("Hello, " + finances.getLogged().getName());
		presentation.setFont(MyFonts.H1Bold.getFont());
		presentation.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(presentation, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel activitiesLabel = new JLabel("Activities");
		activitiesLabel.setFont(MyFonts.H1Bold.getFont());
		activitiesLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(activitiesLabel, gbc);

		Icon filterIcon = new ImageIcon("src/main/java/presentation/images/filter.png");
		JButton filterButton = new JButton(filterIcon);
		filterButton.setOpaque(true);
		filterButton.setMargin(new Insets(0, 0, 0, 0));
		filterButton.setBackground(Color.WHITE);
		filterButton.setBorder(null);
		filterButton.addActionListener(e -> {
			FilterGUI filterGUI = new FilterGUI(finances);
			filterGUI.setVisible(true);
			dispose();
		});
		gbc.gridy++;
		jPanel.add(filterButton, gbc);

		JButton newSpentButton = new JButton("New Spent");
		newSpentButton.setFont(MyFonts.H2Plain.getFont());
		newSpentButton.setOpaque(true);
		newSpentButton.setPreferredSize(new Dimension(160, 26));
		newSpentButton.setForeground(Color.WHITE);
		newSpentButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
		newSpentButton.setBorderPainted(false);
		newSpentButton.addActionListener(e -> {
			NewSpentGUI newSpentGUI = new NewSpentGUI(finances);
			newSpentGUI.setVisible(true);
			dispose();
		});
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(newSpentButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		JLabel hrLabel = new JLabel("""
				<html>
				<head>
				<style type="text/css">
				body { margin: 0; width: 300px; }
				</style>
				</head>
				<body>
				<div>
				<hr>
				</div>
				</body>
				</html>""");
		hrLabel.setFont(MyFonts.H1Bold.getFont());
		hrLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
		jPanel.add(hrLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		if (spentsToBeListed.isEmpty()) {
			gbc.gridx = 0;
			gbc.gridy++;
			JLabel noActivitiesLabel = new JLabel("No activities registered");
			noActivitiesLabel.setFont(MyFonts.H1Bold.getFont());
			noActivitiesLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
			jPanel.add(noActivitiesLabel, gbc);
		} else {
			gbc.anchor = GridBagConstraints.WEST;
			JButton barChartButton = new JButton("Bar chart");
			barChartButton.setOpaque(true);
			barChartButton.setFont(MyFonts.H2Plain.getFont());
			barChartButton.setPreferredSize(new Dimension(160, 26));
			barChartButton.setForeground(Color.WHITE);
			barChartButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
			barChartButton.setBorderPainted(false);
			barChartButton.addActionListener(e -> {
				BarChartGUI barChartGUI = new BarChartGUI(finances);
				barChartGUI.setVisible(true);
				dispose();
			});
			gbc.gridx = 0;
			gbc.gridy++;
			jPanel.add(barChartButton, gbc);

			JButton scatterChartButton = new JButton("Scatter chart");
			scatterChartButton.setOpaque(true);
			scatterChartButton.setFont(MyFonts.H2Plain.getFont());
			scatterChartButton.setPreferredSize(new Dimension(160, 26));
			scatterChartButton.setForeground(Color.WHITE);
			scatterChartButton.setBackground(Color.decode(MyColors.DARK_GREEN.toString()));
			scatterChartButton.setBorderPainted(false);
			scatterChartButton.addActionListener(e -> {
				ScatterChartGUI scatterChartGUIGUI = new ScatterChartGUI(finances);
				scatterChartGUIGUI.setVisible(true);
				dispose();
			});
			gbc.anchor = GridBagConstraints.EAST;
			jPanel.add(scatterChartButton, gbc);

			Icon editIcon = new ImageIcon("src/main/java/presentation/images/edit.png");
			Icon deleteIcon = new ImageIcon("src/main/java/presentation/images/delete.png");
			gbc.anchor = GridBagConstraints.WEST;
			for (Spent spent : spentsToBeListed) {
				JPanel spentPanel = new JPanel();
				spentPanel.setLayout(new GridBagLayout());
				GridBagConstraints gbcSpent = new GridBagConstraints();
				gbcSpent.insets = new Insets(padding, padding, padding, padding);
				spentPanel.setBackground(Color.WHITE);

				JLabel categoryLabel = new JLabel(spent.getCategory().toString());
				categoryLabel.setFont(MyFonts.H2Bold.getFont());
				categoryLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				categoryLabel.setBackground(Color.WHITE);
				categoryLabel.setOpaque(false);
				gbcSpent.anchor = GridBagConstraints.WEST;
				gbcSpent.gridx = 0;
				gbcSpent.gridy = 0;
				spentPanel.add(categoryLabel, gbcSpent);

				JLabel titleLabel = new JLabel(spent.getName());
				titleLabel.setFont(MyFonts.H2Plain.getFont());
				titleLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				titleLabel.setBackground(Color.WHITE);
				titleLabel.setOpaque(false);
				gbcSpent.gridy++;
				spentPanel.add(titleLabel, gbcSpent);

				JLabel dateLabel = new JLabel(spent.getDate().get(Calendar.DATE) + "/" + spent.getDate().get(Calendar.MONTH) + "/" + spent.getDate().get(Calendar.YEAR));
				dateLabel.setFont(MyFonts.H2Plain.getFont());
				dateLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				dateLabel.setBackground(Color.WHITE);
				dateLabel.setOpaque(false);
				gbcSpent.gridx++;
				spentPanel.add(dateLabel, gbcSpent);

				JLabel priceLabel = new JLabel("R$" + spent.getValue());
				priceLabel.setFont(MyFonts.H2Plain.getFont());
				priceLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				priceLabel.setBackground(Color.WHITE);
				priceLabel.setOpaque(false);
				gbcSpent.gridx++;
				spentPanel.add(priceLabel, gbcSpent);

				JLabel descriptionLabel = new JLabel(spent.getDescription());
				descriptionLabel.setFont(MyFonts.H3Plain.getFont());
				descriptionLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				descriptionLabel.setBackground(Color.WHITE);
				descriptionLabel.setOpaque(false);
				gbcSpent.gridx = 0;
				gbcSpent.gridy++;
				spentPanel.add(descriptionLabel, gbcSpent);

				gbc.gridx = 0;
				gbc.gridy++;
				jPanel.add(spentPanel, gbc);

				JPanel buttonsPanel = new JPanel();
				buttonsPanel.setLayout(new GridBagLayout());
				GridBagConstraints gbcButtons = new GridBagConstraints();
				gbcButtons.anchor = GridBagConstraints.WEST;
				gbcButtons.insets = new Insets(padding, padding, padding, padding);
				buttonsPanel.setBackground(Color.WHITE);

				JButton editButton = new JButton(editIcon);
				editButton.setOpaque(true);
				editButton.setMargin(new Insets(0, 0, 0, 0));
				editButton.setBackground(Color.decode(MyColors.MUSTARD.toString()));
				editButton.setBorder(null);
				editButton.addActionListener(e -> {
					EditSpentGUI editSpentGUI = new EditSpentGUI(finances, spent.getIndex());
					editSpentGUI.setVisible(true);
					dispose();
				});
				gbcButtons.gridx = 0;
				gbcButtons.gridy = gbcSpent.gridy + 1;
				spentPanel.add(editButton, gbcButtons);

				JButton deleteButton = new JButton(deleteIcon);
				deleteButton.setOpaque(true);
				deleteButton.setMargin(new Insets(0, 0, 0, 0));
				deleteButton.setBackground(Color.decode(MyColors.RED.toString()));
				deleteButton.setBorder(null);
				deleteButton.addActionListener(e -> {
					DeleteSpentGUI deleteSpentGUI = new DeleteSpentGUI(finances, spent.getIndex());
					deleteSpentGUI.setVisible(true);
					dispose();
				});
				gbcButtons.gridx++;
				spentPanel.add(deleteButton, gbcButtons);

				gbc.gridx = 0;
				gbc.gridy++;
				jPanel.add(buttonsPanel, gbc);

				gbc.gridx = 0;
				gbc.gridy++;
				JLabel hrLoopLabel = new JLabel("""
						<html>
						<head>
						<style type="text/css">
						body { margin: 0; width: 300px; }
						</style>
						</head>
						<body>
						<div>
						<hr>
						</div>
						</body>
						</html>""");
				hrLoopLabel.setFont(MyFonts.H1Bold.getFont());
				hrLoopLabel.setForeground(Color.decode(MyColors.TITLE.toString()));
				jPanel.add(hrLoopLabel, gbc);
			}
		}

		getContentPane().requestFocusInWindow();
		setVisible(true);
	}

}

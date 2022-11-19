package presentation;

import business.Finances;
import data.Category;
import data.Spent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.util.*;
import java.util.List;

public class BarChartGUI extends JFrame {
	private static Finances finances;
	private List<Spent> spentToBeListed;

	public BarChartGUI(Finances finances) {
		if (finances.isUserLogged()) {
			BarChartGUI.finances = finances;
			spentToBeListed = finances.listSpent();
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
		setTitle("Chart");
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

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Month month : Month.values()) {
			List<Spent> spentsOfMonth = finances.listSpent(month.getValue());
			EnumMap<Category, Boolean> categoryAlreadyAdded = new EnumMap<>(Category.class);

			for (Category category : Category.values()) {
				categoryAlreadyAdded.put(category, false);
			}

			EnumMap<Category, Float> sumOfSpentsByCategory = new EnumMap<>(Category.class);

			for (Spent spent : spentsOfMonth) {
				if (categoryAlreadyAdded.get(spent.getCategory()).equals(false)) {
					sumOfSpentsByCategory.put(spent.getCategory(), spent.getValue());
					categoryAlreadyAdded.put(spent.getCategory(), true);
				} else if (categoryAlreadyAdded.get(spent.getCategory()).equals(true)) {
					float sum = sumOfSpentsByCategory.get(spent.getCategory());
					sum += spent.getValue();
					sumOfSpentsByCategory.put(spent.getCategory(), sum);
				}
				dataset.addValue(sumOfSpentsByCategory.get(spent.getCategory()), spent.getCategory().toString(), String.valueOf(spent.getDate().get(Calendar.MONTH)));
			}
		}

		JFreeChart chart = ChartFactory.createBarChart(
				"Spents",
				"Month",
				"Price",
				dataset,
				PlotOrientation.VERTICAL,
				true,true,false
		);

		ChartPanel chartPanel = new ChartPanel(chart);
		chart.getPlot().setBackgroundPaint(Color.WHITE);
		chartPanel.setBackground(Color.WHITE);
		jPanel.add(chartPanel, gbc);
		gbc.gridy++;

		JButton returnButton = new JButton("<html><p><span style=\"color: " + MyColors.BLUE + "\">Return</span></p></html>");
		returnButton.setFont(MyFonts.H2Plain.getFont());
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

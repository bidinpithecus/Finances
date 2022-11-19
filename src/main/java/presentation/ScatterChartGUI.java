package presentation;

import business.Finances;
import data.Spent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.util.*;
import java.util.List;

public class ScatterChartGUI extends JFrame {
	private static Finances finances;
	private List<Spent> spentToBeListed;

	public ScatterChartGUI(Finances finances) {
		if (finances.isUserLogged()) {
			ScatterChartGUI.finances = finances;
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

		XYSeriesCollection dataset = new XYSeriesCollection();
		List<Spent> spentsOfMonth = null;
		Map<Integer, Float> totalSpentOfEachMonth = new HashMap<>();
		for (Month month : Month.values()) {
			spentsOfMonth = finances.listSpent(month.getValue());
			float totalSumOfMonth = 0;
			for (Spent spent : spentsOfMonth) {
				totalSumOfMonth += spent.getValue();
			}
			totalSpentOfEachMonth.put(month.getValue(), totalSumOfMonth);
			XYSeries series = new XYSeries(month.toString());
			series.add(month.getValue(), totalSpentOfEachMonth.get(month.getValue()));
			dataset.addSeries(series);
		}

		JFreeChart chart = ChartFactory.createScatterPlot(
				"Spents",
				"Month",
				"Price",
				dataset,
				PlotOrientation.VERTICAL,
				true,true,false
		);

		XYPlot plot = (XYPlot)chart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);

		ChartPanel chartPanel = new ChartPanel(chart);
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

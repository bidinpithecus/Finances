package presentation;

import business.Finances;
import data.Spent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
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

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Spent spent : spentToBeListed) {
			System.out.println(spent);
			dataset.addValue(spent.getValue(), spent.getCategory().toString(), String.valueOf(spent.getDate().get(Calendar.MONTH)));
		}

//		JFreeChart chart = ChartFactory.createScatterPlot(
//				"Spents",
//				"Month",
//				"Price"
//		);

//		ChartPanel chartPanel = new ChartPanel(chart);
//		chartPanel.setBackground(Color.WHITE);
//		jPanel.add(chartPanel, gbc);
		getContentPane().requestFocusInWindow();
		setVisible(true);
	}

}

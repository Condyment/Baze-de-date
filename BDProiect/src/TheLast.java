import java.awt.Color;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TheLast extends JPanel {
	private String url = "jdbc:mysql://localhost:3306/cantina";
	private String user = "root";
	private String pass = "qq20021995qq";
	private Connection con;
	private ArrayList<String> thedate = new ArrayList<String>();

	public TheLast() {

		setSize(200, 200);
		setLocation(859, 400);
		setLayout(null);
		time();
		theconection();
		setBackground(Color.gray);
		JFreeChart barChart = ChartFactory.createBarChart("", "Ceas", "Numaru de Oameni", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);
		barChart.setBorderPaint(Color.DARK_GRAY);
		barChart.setBackgroundPaint(Color.white);
		barChart.getPlot().setBackgroundPaint(Color.white);
		barChart.setBorderPaint(Color.white);

		BarRenderer r = (BarRenderer) barChart.getCategoryPlot().getRenderer();
		r.setSeriesPaint(0, Color.decode("#0156A5"));

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setSize(200, 200);
		chartPanel.setLocation(3, 3);
		add(chartPanel);
	
		chartPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				setSize(506, 506);
				chartPanel.setSize(500, 500);
				setLocation(710, 400);
				repaint();

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				setSize(200, 200);
				chartPanel.setSize(200, 200);
				setLocation(859, 400);
				// setLocation(852,400);
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private CategoryDataset createDataset() {

		final String t1213 = "12-13";
		final String t1314 = "13-14";
		final String t1415 = "14-15";
		final String t1516 = "15-16";
		final String t1617 = "16-17";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		String pa = thedate.get(0);
		String pa1 = thedate.get(1);
		String pa2 = thedate.get(2);
		
		String dd = pa + "-" + pa1 + "-" + "12" + " " + "12" + ":" + "00" + ":" + "00";
		String dd1 = pa + "-" + pa1 + "-" + "12" + " " + "12" + ":" + "59" + ":" + "00";
		String dd2 = pa + "-" + pa1 + "-" + "12" + " " + "13" + ":" + "00" + ":" + "00";
		String dd3 = pa + "-" + pa1 + "-" + "12" + " " + "13" + ":" + "59" + ":" + "00";
		String dd4 = pa + "-" + pa1 + "-" + "12" + " " + "14" + ":" + "00" + ":" + "00";
		String dd5 = pa + "-" + pa1 + "-" + "12" + " " + "14" + ":" + "59" + ":" + "00";
		String dd6 = pa + "-" + pa1 + "-" + "12" + " " + "15" + ":" + "00" + ":" + "00";
		String dd7 = pa + "-" + pa1 + "-" + "12" + " " + "15" + ":" + "59" + ":" + "00";
		String dd8 = pa + "-" + pa1 + "-" + "12" + " " + "16" + ":" + "00" + ":" + "00";
		String dd9 = pa + "-" + pa1 + "-" + "12" + " " + "17" + ":" + "00" + ":" + "00";

		int a = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;
		
		try {

			PreparedStatement stt = con.prepareStatement(
					"select * from comanda where Dataincarecomandatrebuiesafiegata BETWEEN ? and ? ;");
			stt.setString(1, dd);
			stt.setString(2, dd1);
			ResultSet rr = stt.executeQuery();

			while (rr.next()) {
				a++;

			}
			stt.close();
			rr.close();
			PreparedStatement stt1 = con.prepareStatement(
					"select * from comanda where Dataincarecomandatrebuiesafiegata BETWEEN ? and ? ;");
			stt1.setString(1, dd2);
			stt1.setString(2, dd3);
			ResultSet rr1 = stt1.executeQuery();

			while (rr1.next()) {
				a1++;

			}
			stt1.close();
			rr1.close();
			PreparedStatement stt2 = con.prepareStatement(
					"select * from comanda where Dataincarecomandatrebuiesafiegata BETWEEN ? and ? ;");
			stt2.setString(1, dd4);
			stt2.setString(2, dd5);
			ResultSet rr2 = stt2.executeQuery();

			while (rr2.next()) {
				a2++;

			}
			stt2.close();
			rr2.close();
			PreparedStatement stt3 = con.prepareStatement(
					"select * from comanda where Dataincarecomandatrebuiesafiegata BETWEEN ? and ? ;");
			stt3.setString(1, dd6);
			stt3.setString(2, dd7);
			ResultSet rr3 = stt3.executeQuery();

			while (rr3.next()) {
				a3++;

			}
			stt3.close();
			rr3.close();
			PreparedStatement stt4 = con.prepareStatement(
					"select * from comanda where Dataincarecomandatrebuiesafiegata BETWEEN ? and ? ;");
			stt4.setString(1, dd8);
			stt4.setString(2, dd9);
			ResultSet rr4 = stt4.executeQuery();

			while (rr4.next()) {
				a4++;

			}
			stt4.close();
			rr4.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataset.addValue(a, "Oameni", t1213);
		dataset.addValue(a1, "Oameni", t1314);
		dataset.addValue(a2, "Oameni", t1415);
		dataset.addValue(a3, "Oameni", t1516);
		dataset.addValue(a4, "Oameni", t1617);
		return dataset;
	}

	public void theconection() {
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void time() {

		Date p = new Date();
		int monthcomand = 0;

		String dataincaresefacecomanda = p.toString();
		String manym[] = dataincaresefacecomanda.split(" ");
		if (manym[1].equals("Jan")) {
			monthcomand = 1;

		} else if (manym[1].equals("Feb")) {

			monthcomand = 2;
		} else if (manym[1].equals("Mar")) {

			monthcomand = 3;
		} else if (manym[1].equals("Apr")) {

			monthcomand = 4;
		} else if (manym[1].equals("May")) {

			monthcomand = 5;
		} else if (manym[1].equals("Jun")) {

			monthcomand = 6;
		} else if (manym[1].equals("Jul")) {

			monthcomand = 7;
		} else if (manym[1].equals("Aug")) {

			monthcomand = 8;
		} else if (manym[1].equals("Sep")) {

			monthcomand = 9;
		} else if (manym[1].equals("Oct")) {

			monthcomand = 10;
		} else if (manym[1].equals("Nov")) {

			monthcomand = 11;
		} else if (manym[1].equals("Dec")) {

			monthcomand = 12;
		}

		int days1 = Integer.parseInt(manym[2]);
		int years1 = Integer.parseInt(manym[5]);
		String months = "" + monthcomand;
		String somanynames = manym[3].replaceAll(":", " ");
		String noimagination[] = somanynames.split(" ");
		int hours1 = Integer.parseInt(noimagination[0]);
		int minutes1 = Integer.parseInt(noimagination[1]);
		Random rand = new Random();
		String seconds = "0";

		String data11[] = { manym[5], months, manym[2], noimagination[0], noimagination[1], seconds };
		for (int i = 0; i <= 5; i++) {
			thedate.add(data11[i]);
		}
		
	}
}

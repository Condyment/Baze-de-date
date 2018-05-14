import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public class AddCommand extends JPanel {

	private JList<String> list1;
	private JList<String> list2;
	private JList<String> list3;
	private JList<String> list4;
	//////////////////////////////////////////////
	private Date date;
	private Date date1;
	private String todaydate;
	//////////////////////////////////////////
	private JButton btn1;

	private int array[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	////////////////////////////////////////////////////////////
	private String url = "jdbc:mysql://localhost:3306/cantina?autoReconnect=true&useSSL=false";
	private String user = "root";
	private String pass = "qq20021995qq";
	private Connection con;
	///////////////////////////////////////////
	ArrayList<Food> arraya = new ArrayList<Food>();
	ArrayList<Integer> arr = new ArrayList<Integer>();

	/////////////////////////////////////////
	private String FirstName = " ";
	private String LastName = " ";
	private int id = 0;
	private int idcomanda = 1;
	private double total = 0;
	private int ki = 0;

	public AddCommand(String FirstName, String LastName, int id) {
		setLayout(null);
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.id = id;
		btn1 = new JButton("something");
		btn1.setBounds(500, 500, 100, 100);
		btn1.addActionListener(e -> {
			
			themostimportantfunction();
			btn1.setEnabled(false);
		});
		add(btn1);
		date1 = new Date();

		filter();
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DefaultListModel<String> Luna = new DefaultListModel<>();
		for (int i = month; i <= 12; i++) {
			Luna.addElement("" + i);
		}
		DefaultListModel<String> Ziua = new DefaultListModel<>();
		for (int i = day; i <= array[month] - 1; i++) {
			Ziua.addElement("" + i);
		}
		DefaultListModel<String> Ora = new DefaultListModel<>();

		if (12 <= hour && 17 > hour) {
			for (int i = hour; i < 17; i++) {
				Ora.addElement("" + i);

			}
		} else if (12 >= hour) {
			for (int i = 12; i < 17; i++) {
				Ora.addElement("" + i);
			}

		} else {
		}

		DefaultListModel<String> Minutul = new DefaultListModel<>();
		if ((Ora.isEmpty()) == false) {///////////// it was modified
			if (hour >= 12 && hour < 17) {

				for (int i = minute; i <= 50; i = i + 10) {
					Minutul.addElement("" + i);
				}

			} else if (hour <= 12) {
				for (int i = 0; i <= 50; i = i + 10) {
					Minutul.addElement("" + i);

				}

			} else if (hour >= 17) {
				System.out.println("to late ");
			}

		}

		list1 = new JList<>(Luna);

		list1.setBounds(0, 0, 75, 350);
		list1.addListSelectionListener(e -> {

			if (list1.getSelectedIndex() == 0) {
				Ziua.removeAllElements();

				for (int i = day; i <= array[month] - 1; i++) {
					Ziua.addElement("" + i);
				}
			} else if (list1.getSelectedIndex() > 0) {
				int k = list1.getSelectedIndex() + month;

				Ziua.removeAllElements();
				for (int i = 1; i <= array[k - 1]; i++) {

					Ziua.addElement("" + i);
				}
				Ora.removeAllElements();
				for (int i = 12; i < 17; i++) {
					Ora.addElement("" + i);
				}
				Minutul.removeAllElements();
				for (int i = 0; i <= 50; i = i + 10) {
					Minutul.addElement("" + i);
				}

			}

		});
		list2 = new JList<>(Ziua);
		list2.setBounds(100, 0, 75, 600);
		list2.addListSelectionListener(e -> {
			if (list1.getSelectedIndex() == 0 && list2.getSelectedIndex() == 0) {
				Ora.removeAllElements();

				if (12 <= hour && 17 > hour) {
					for (int i = hour; i < 17; i++) {
						Ora.addElement("" + i);

					}
				} else if (12 >= hour) {
					for (int i = 12; i < 17; i++) {
						Ora.addElement("" + i);

					}
					if (Ora.isEmpty()) {
						Minutul.removeAllElements();

					}
				}

			} else {
				Ora.removeAllElements();
				for (int i = 12; i < 17; i++) {
					Ora.addElement("" + i);

				}
				Minutul.removeAllElements();
				for (int i = 0; i <= 50; i = i + 10) {
					Minutul.addElement("" + i);

				}
			}
			if (Ora.isEmpty()) {
				Minutul.removeAllElements();

			}
		});
		list3 = new JList<>(Ora);
		list3.addListSelectionListener(e -> {
			if ((list1.getSelectedIndex() == 0) && (list2.getSelectedIndex() == 0) && (list3.getSelectedIndex() == 0)) {
				Minutul.removeAllElements();

				if (hour < 12) {

					for (int i = 0; i <= 50; i = i + 10) {
						Minutul.addElement("" + i);
					}
				} else if (hour >= 12 && hour < 17) {
					for (int i = minute; i <= 50; i = i + 10) {
						Minutul.addElement("" + i);
					}
				} else if (hour >= 17) {
					System.out.println("late");
				}

			} else {
				Minutul.removeAllElements();

				for (int i = 0; i <= 50; i = i + 10) {
					Minutul.addElement("" + i);
				}

			}

		});
		list3.setBounds(200, 0, 75, 350);
		list4 = new JList<>(Minutul);
		list4.setBounds(300, 0, 75, 350);
		add(list1);
		add(list2);
		add(list3);
		add(list4);

	}

	public void themostimportantfunction() {

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
		String somanynames = manym[3].replaceAll(":", " ");
		String noimagination[] = somanynames.split(" ");
		int hours1 = Integer.parseInt(noimagination[0]);
		int minutes1 = Integer.parseInt(noimagination[1]);
		Random rand = new Random();
		int seconds = rand.nextInt(59);
		String data11 = years1 + "-" + monthcomand + "-" + days1 + " " + hours1 + ":" + minutes1 + ":" + seconds;

		String mmmo = list1.getModel().getElementAt(list1.getSelectedIndex()).toString();
		String dddy = list2.getModel().getElementAt(list2.getSelectedIndex()).toString();
		String hhhho = list3.getModel().getElementAt(list3.getSelectedIndex()).toString();
		String minnn = list4.getModel().getElementAt(list4.getSelectedIndex()).toString();
		String data22 = year + "-" + mmmo + "-" + dddy + " " + hhhho + ":" + minnn + ":" + seconds;

		try {

			PreparedStatement SS = con.prepareStatement(
					"INSERT INTO comanda(TotalCost,Dataincareafostfacutcomanda,Dataincarecomandatrebuiesafiegata, login_login_id)VALUES(?,?,?,?);");
			SS.setDouble(1, total);
			SS.setString(2, data11);
			SS.setString(3, data22);
			SS.setInt(4, id);
			SS.executeUpdate();
			SS.close();

			PreparedStatement stt = con.prepareStatement(
					"select * from comanda where login_login_id=? and Dataincareafostfacutcomanda=?;");
			stt.setInt(1, id);
			stt.setString(2, data11);
			ResultSet rr11 = stt.executeQuery();

			if (rr11.next()) {
				idcomanda = rr11.getInt("idComanda");

			}

			for (int i = 0; i < arraya.size(); i++) {
				PreparedStatement SS1 = con.prepareStatement(
						"INSERT INTO produsu(Produsu_nume,Pret,Cantitate,Comanda_idComanda)VALUES(?,?,?,?);");
				SS1.setString(1, arraya.get(i).getDenumire_articol());
				SS1.setDouble(2, arraya.get(i).getPret());
				SS1.setInt(3, arr.get(i));
				SS1.setInt(4, idcomanda);
				SS1.executeUpdate();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void filter() {

		todaydate = date1.toString();

		String manymonths[] = todaydate.split(" ");

		if (manymonths[1].equals("Jan")) {
			month = 1;

		} else if (manymonths[1].equals("Feb")) {

			month = 2;
		} else if (manymonths[1].equals("Mar")) {

			month = 3;
		} else if (manymonths[1].equals("Apr")) {

			month = 4;
		} else if (manymonths[1].equals("May")) {

			month = 5;
		} else if (manymonths[1].equals("Jun")) {

			month = 6;
		} else if (manymonths[1].equals("Jul")) {

			month = 7;
		} else if (manymonths[1].equals("Aug")) {

			month = 8;
		} else if (manymonths[1].equals("Sep")) {

			month = 9;
		} else if (manymonths[1].equals("Oct")) {

			month = 10;
		} else if (manymonths[1].equals("Nov")) {

			month = 11;
		} else if (manymonths[1].equals("Dec")) {

			month = 12;
		}
		int sare = Integer.parseInt(manymonths[5]);
		if (sare % 4 == 0) {
			array[1] = 29;

		}
		String anothertime = manymonths[3].replaceAll(":", " ");
		String hello_there[] = anothertime.split(" ");

		day = Integer.parseInt(manymonths[2]);
		hour = Integer.parseInt(hello_there[0]);
		minute = Integer.parseInt(hello_there[1]);
		year = Integer.parseInt(manymonths[5]);

		if (minute >= 50 && minute <= 59) {
			minute = 0;
			hour = hour + 1;

		}

		else if (minute >= 40 && minute <= 49) {
			minute = 50;

		} else if (minute >= 30 && minute <= 39) {
			minute = 40;
			System.out.println("wow");
		} else if (minute >= 20 && minute <= 29) {
			minute = 30;

		} else if (minute >= 10 && minute <= 19) {
			minute = 20;

		}

		else if (minute >= 0 && minute <= 9) {
			minute = 10;

		}

	}

	public void takethefood(Food g) {

		arraya.add(g);
		arr.add(g.getbuc_produs());
		double ceva = (double) g.getbuc_produs();
		double ceva1 = (double) g.getPret();
		total = total + (ceva * ceva1);

	}

}

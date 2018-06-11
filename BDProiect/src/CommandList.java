import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CommandList extends JPanel {
	private JButton b1;
	private JTable tb1;
	private JTable tb2;
	private String FirstName = " ";
	private String LastName = " ";
	private int idd = 0;
	private JScrollPane somethingpanel;
	private JScrollPane somethingpanel2;
	private JLabel numaromaneini;
	private JLabel procent;
	private String url = "jdbc:mysql://localhost:3306/cantina?autoReconnect=true&useSSL=false";
	private String user = "root";
	private String pass = "qq20021995qq";
	private Connection con;

	

	private ArrayList<String> Pretu = new ArrayList<String>();
	private ArrayList<String> trebuiesafiegata = new ArrayList<String>();
	private ArrayList<String> idComanda = new ArrayList<String>();
	private ArrayList<String> pdnume = new ArrayList<String>();
	private ArrayList<String> ppret = new ArrayList<String>();
	private ArrayList<String> pcantitate = new ArrayList<String>();
	private String comrows[][];
	// private String comrows2[][];
	private int howmanyrowsincommand = 0;
	private int sum = 0;
	String another;
	private int ddd = 0;

	public CommandList(String FirstName, String LastName, int id) {
		setLayout(null);
		connection();
		Date p = new Date();
		this.FirstName = FirstName;
		this.LastName = LastName;
		idd = id;
		dark();
		next();
		Font bItalic = new Font("Consolas", Font.BOLD, 18);
		
		
		setBackground(Color.WHITE);
		b1 = new JButton("<html><center>Remove<br>Command</center></html>");
		b1.setFont(bItalic);
		b1.setBounds(452, 350, 100, 100);
		b1.addActionListener(e -> {

			try {
				PreparedStatement stmt = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				stmt.executeUpdate();
				stmt.close();
				PreparedStatement sttt = con.prepareStatement("DELETE FROM  comanda WHERE idComanda = ?");

				sttt.setString(1, another);
				sttt.executeUpdate();
				sttt.close();
				PreparedStatement stttt = con.prepareStatement("DELETE FROM produsu WHERE Comanda_idComanda =?");
				stttt.setString(1, another);
				stttt.executeUpdate();
				stttt.close();
				PreparedStatement stmt1 = con.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				stmt1.executeUpdate();
				stmt1.close();

				remove(tb1);
				remove(somethingpanel);

				dark();
				next();
				revalidate();
				repaint();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		add(b1);
		setSize(1200, 600);
	}

	public void connection() {

		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void dark() {
		howmanyrowsincommand = 0;
		try {

			PreparedStatement stt = con.prepareStatement(
					"select * from comanda where login_login_id=? order BY Dataincarecomandatrebuiesafiegata ASC;");
			stt.setInt(1, idd);

			ResultSet rrr1 = stt.executeQuery();
			while (rrr1.next()) {

				trebuiesafiegata.add(rrr1.getString("Dataincarecomandatrebuiesafiegata"));
				idComanda.add(rrr1.getString("idComanda"));
				Pretu.add(rrr1.getString("TotalCost"));
				howmanyrowsincommand++;
				System.out.println(howmanyrowsincommand);
			}

			stt.close();
			rrr1.close();
			comrows = new String[howmanyrowsincommand][3];
			for (int i = 0; i < howmanyrowsincommand; i++) {
				for (int j = 0; j <= 2; j++) {
					comrows[i][j] = "";
				}
			}

			for (int i = 0; i < howmanyrowsincommand; i++) {
				for (int j = 0; j <= 2; j++) {
					if (j == 0) {
						comrows[i][j] = trebuiesafiegata.get(i);

					} else if (j == 1) {
						comrows[i][j] = Pretu.get(i);

					} else if (j == 2) {

						comrows[i][j] = idComanda.get(i);

					}

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void next() {
		String columns[] = { "Dataincarecomandatrebuiesafiegata", "TotalCost", "idComanda" };
		tb1 = new JTable(comrows, columns);
		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb1.getColumnModel().getColumn(2).setMinWidth(0);
		tb1.getColumnModel().getColumn(2).setMaxWidth(0);
		somethingpanel = new JScrollPane(tb1);

		somethingpanel.setBounds(0, 0, 500, 343);

		add(somethingpanel);

		tb1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {

				if (!event.getValueIsAdjusting()) {

					if (ddd == 12) {
						remove(somethingpanel2);
						remove(numaromaneini);
						remove(procent);
					}
					another = tb1.getValueAt(tb1.getSelectedRow(), 2).toString();
					System.out.println(another);
					try {
						int anumber = 0;
						String date = "";
						String date1[];
						String date2[];
						String date3[];
						int ab = 0;
						int ab1 = 0;
						PreparedStatement calc = con.prepareStatement("select * from comanda where idComanda=?");
						calc.setString(1, another);
						ResultSet calcr = calc.executeQuery();
						while (calcr.next()) {
							date = calcr.getString("Dataincarecomandatrebuiesafiegata");

						}
						date1 = date.split(" ");
						date2 = date1[0].split("-");
						date3 = date1[1].split(":");
						date3[2] = "00";
						anumber = Integer.parseInt(date3[0]);
						if (anumber >= 16) {
							ab = 16;
							ab1 = 17;

						} else if (anumber >= 15) {
							ab = 15;
							ab1 = 16;

						} else if (anumber >= 14) {

							ab = 14;
							ab1 = 15;
						} else if (anumber >= 13) {
							ab = 13;
							ab1 = 14;

						} else if (anumber >= 12) {
							ab = 12;
							ab1 = 13;
						}
						String gg1 = "" + ab;
						String gg2 = "" + ab1;
						int a = 0;
						String dateformysq11 = date1[0] + " " + gg1 + ":00:00";
						String dateformysql2 = date1[0] + " " + gg2 + ":00:00";
						PreparedStatement stt = con.prepareStatement(
								"select * from comanda where Dataincarecomandatrebuiesafiegata BETWEEN ? and ? ;");
						stt.setString(1, dateformysq11);
						stt.setString(2, dateformysql2);
						ResultSet rr = stt.executeQuery();

						while (rr.next()) {
							a++;

						}
						double k = a * 100.0 / 60.0;

						System.out.println(k);
						System.out.println(a);
						Font bItalic = new Font("Consolas", Font.BOLD, 30);
						numaromaneini = new JLabel("Numaru de oameni posibili:" + a);
						numaromaneini.setBounds(350, 475, 600, 75);
						numaromaneini.setFont(bItalic);

						procent = new JLabel("Pocentu de a sta la coada :%" + k);
						procent.setBounds(325, 525, 600, 75);
						procent.setFont(bItalic);

						add(procent);
						add(numaromaneini);
						repaint();
						stt.close();
						rr.close();

						pdnume.clear();
						ppret.clear();
						pcantitate.clear();
						int howmanyrowsincommand2 = 0;
						PreparedStatement stt2 = con
								.prepareStatement("select * from produsu where Comanda_idComanda=?");
						stt2.setString(1, another);
						ResultSet rrr2 = stt2.executeQuery();
						while (rrr2.next()) {

							pdnume.add(rrr2.getString("Produsu_nume"));
							ppret.add(rrr2.getString("Pret"));
							pcantitate.add(rrr2.getString("Cantitate"));
							howmanyrowsincommand2++;
						}

						stt2.close();
						rrr2.close();
						String comrows2[][] = new String[howmanyrowsincommand2][3];

						for (int i = 0; i < howmanyrowsincommand2; i++) {
							for (int j = 0; j <= 2; j++) {
								if (j == 0) {
									comrows2[i][j] = pdnume.get(i);

								} else if (j == 1) {
									comrows2[i][j] = ppret.get(i);

								} else if (j == 2) {

									comrows2[i][j] = pcantitate.get(i);

								}
							}
						}
						String columns1[] = { "Numele Produsului", "Pret", "Cantitate" };
						tb2 = new JTable(comrows2, columns1);
						somethingpanel2 = new JScrollPane(tb2);

						somethingpanel2.setBounds(600, 0, 500, 343);
						add(somethingpanel2);
						repaint();
						ddd = 12;

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

	}
}

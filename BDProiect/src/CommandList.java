import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CommandList extends JPanel {
	private JButton b1;
	private JTable tb1;
	// private JTable tb2;
	private String FirstName = " ";
	private String LastName = " ";
	private int idd = 0;
	private JScrollPane somethingpanel;
	private JScrollPane somethingpanel2;

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
	private String another[];

	public CommandList(String FirstName, String LastName, int id) {
		setLayout(null);
		connection();
		Date p = new Date();
		this.FirstName = FirstName;
		this.LastName = LastName;
		idd = id;
		dark();
		String columns[] = { "Dataincarecomandatrebuiesafiegata", "TotalCost", "idComanda" };
		String columns1[] = { "Numele Produsului", "Pret", "Cantitate" };

		tb1 = new JTable(comrows, columns);
		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		somethingpanel = new JScrollPane(tb1);

		somethingpanel.setBounds(0, 0, 500, 343);

		add(somethingpanel);
		another=new String[100];
		tb1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				int cc=0;
			
System.out.println(another);
another[cc]=tb1.getValueAt(tb1.getSelectedRow(), 2).toString();
				try {
					pdnume.clear();
					ppret.clear();
					pcantitate.clear();
					int howmanyrowsincommand2 = 0;
					PreparedStatement stt2 = con.prepareStatement("select * from produsu where Comanda_idComanda=?");
					stt2.setString(1,another[cc]);
					ResultSet rrr2 = stt2.executeQuery();
					while (rrr2.next()) {

						pdnume.add(rrr2.getString("Produsu_nume"));
						ppret.add(rrr2.getString("Pret"));
						pcantitate.add(rrr2.getString("Cantitate"));
						howmanyrowsincommand2++;
					}
cc++;
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

					sum = sum + howmanyrowsincommand2;

					if (sum == 2 * howmanyrowsincommand2) {

						sum = 0;
						String columns1[] = { "Numele Produsului", "Pret", "Cantitate" };
						tb1 = new JTable(comrows2, columns1);

						somethingpanel2 = new JScrollPane(tb1);
						somethingpanel2.setBounds(600, 0, 600, 600);
						add(somethingpanel2);
						
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		tb1.getColumnModel().getColumn(2).setMinWidth(100);
		tb1.getColumnModel().getColumn(2).setMaxWidth(100);
		b1 = new JButton("remove comand");
		b1.setBounds(400, 400, 100, 100);
		b1.addActionListener(e -> {

			// idkwhatthisgonabe();
			System.out.println(tb1.getSelectedRow());
		});
		add(b1);
		setSize(1200, 600);
	}

	public void idkwhatthisgonabe() {
		// System.out.println(tb1.getSelectedRow());

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
				// PreparedStatement stt2 = con.prepareStatement("select * from produsu where
				// Comanda_idComanda=?");
				// stt2.setInt(1, this.id);
			}

			comrows = new String[howmanyrowsincommand][3];

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

}

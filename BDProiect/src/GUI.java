import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GUI extends JPanel {
	private ImageIcon icon;
	private AddCommand AddComm;
	////////////////////////////////////////
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	//////////////////////////////////////
	private JButton SeeCommand;
	private JButton addd;
	private JButton remove;
	private JButton restart;
	private JButton exit;
	private JButton backtologinscreen;
	private JButton addcommand;
	private JButton backtogui;
	private JButton backtoguifromcommandlist;
	/////////////////////////////////////
	private BufferedImage background1;
	private BufferedImage background2;
	/////////////////////////////////////////
	private JTextField text;
	private JTextField bucati;
	///////////////////////////////////
	private Food food[];
	private Food food2[];
	private CommandList list;
	///////////////////////////////////////
	private int numberoffoods = 25;
	private int a = 0;
	private int zz = 0;
	private int ll = 0;
	private int rara = 0;
	private int t = 0;
	private int b = 0;
	/////////////////////////////////////////////
	ArrayList<Integer> arraya = new ArrayList<Integer>();
	////////////////////////////////////////////////////
	private String FirstName = " ";
	private String LastName = " ";
	private int id = 0;
	private int maxcommands1 = 0;
	////////////////////////////////////
	private int aa = 0;
	///////////////////////
	private String url = "jdbc:mysql://localhost:3306/cantina?autoReconnect=true&useSSL=false";
	private String user = "root";
	private String pass = "qq20021995qq";
	private Connection con;

	public GUI(String FirstName, String LastName, int id, int maxcommands) {

		setLayout(null);
		theconection();
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.id = id;
		maxcommands1 = maxcommands;
		add_image();
		instance();
		panels();
		exit = new JButton("Exit");
		exit.setBounds(902, 200, 100, 100);

		addd = new JButton("Add");
		addd.setBounds(902, 0, 100, 100);

		remove = new JButton("Remove");
		remove.setBounds(902, 100, 100, 100);

		addcommand = new JButton("addcommand");
		addcommand.setBounds(902, 300, 100, 100);

		SeeCommand = new JButton("SeeCommand");
		SeeCommand.setBounds(902, 400, 100, 100);

		if (maxcommands1 >= 20) {
			addcommand.setEnabled(false);
		}

		add(exit);
		add(addd);
		add(remove);
		add(addcommand);
		add(SeeCommand);
		exit.addActionListener(e -> {

			{

				System.exit(0);

			}

		});

		addd.addActionListener(e -> {
			if (isThisComponentFoundInJPanel(food2[a])) {

				repaint();
				food2[a].setbuc_produs();

				food2[a].show();
				panel2.add(food2[a]);
				arraya.add(a);
				filter();

			} else {
				repaint();
				food2[a].setbuc_produs();

				food2[a].show();
				panel2.add(food2[a]);
				arraya.add(a);
				filter();
				rara++;

				if (rara > 1) {

					zz = zz + 170;
				}
				if (zz == 850) {

					zz = 0;
					ll = ll + 200;

				}
				Handler1 thehand = new Handler1();
				food2[a].setBounds(zz, ll, 170, 200);
				food2[a].addActionListener(thehand);
			}

		});
		remove.addActionListener(e -> {
			for (int i = 0; i < 25; i++) {
				if (food2[i].getBackground() == Color.green) {
					food2[i].anotherthing();
					b = i;
					food2[i].setBackground(Color.white);

					panel2.remove(food2[i]);
					panel2.repaint();
					panel2.revalidate();

				}

			}
			aseaza_componenetele_panel2();

		});
		addcommand.addActionListener(e -> {
			gettingtherows();

			if (maxcommands1 >= 20) {
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f, "The command list is full");
				addcommand.setEnabled(false);
			} else {

				AddComm = new AddCommand(FirstName, LastName, id);
				JButton getbackfromaddcom = new JButton("Back to GUI");
				getbackfromaddcom.setBounds(300, 400, 100, 100);
				getbackfromaddcom.addActionListener(e1 -> {
					remove(AddComm);
					if (maxcommands1 >= 20) {
						addcommand.setEnabled(false);
					}
					for (int i = 0; i < numberoffoods; i++) {

						food[i].setEnabled(true);
						food2[i].setEnabled(true);
					}

					addd.setEnabled(true);
					remove.setEnabled(true);
					addcommand.setEnabled(true);
					addcommand.setEnabled(true);
					SeeCommand.setEnabled(true);

				});
				AddComm.add(getbackfromaddcom);
				for (int i = 0; i < 25; i++) {
					if (isThisComponentFoundInJPanel(food2[i])) {

						AddComm.takethefood(food2[i]);
						panel2.remove(food2[i]);
						panel2.repaint();
						panel2.revalidate();
						food2[i].anotherthing();
					}

				}
				AddComm.setBounds(0, 0, 700, 700);

				add(AddComm);
				add(panel1);
				add(panel2);
				for (int i = 0; i < numberoffoods; i++) {

					food[i].setEnabled(false);
					food2[i].setEnabled(false);

				}

				addd.setEnabled(false);
				remove.setEnabled(false);
				addcommand.setEnabled(false);
				addcommand.setEnabled(false);
				SeeCommand.setEnabled(false);
				repaint();
				rara = 0;
				zz = 0;
				ll = 0;
			}
		});
		SeeCommand.addActionListener(e -> {

			backtogui = new JButton("Baack");
			backtogui.setBounds(200, 0, 100, 100);

			list = new CommandList(FirstName, LastName, id);
			list.setBounds(0, 0, 500, 500);
			list.setBackground(Color.red);
			list.add(backtogui);

			backtogui.addActionListener(w -> {

				for (int i = 0; i < numberoffoods; i++) {

					food[i].setEnabled(true);
					food2[i].setEnabled(true);

				}
				addd.setEnabled(true);
				remove.setEnabled(true);
				addcommand.setEnabled(true);
				addcommand.setEnabled(true);
				SeeCommand.setEnabled(true);

				remove(list);
				repaint();
			});

			add(list);
			add(panel1);
			add(panel2);
			for (int i = 0; i < numberoffoods; i++) {

				food[i].setEnabled(false);
				food2[i].setEnabled(false);

			}

			addd.setEnabled(false);
			remove.setEnabled(false);
			addcommand.setEnabled(false);
			addcommand.setEnabled(false);
			SeeCommand.setEnabled(false);

			repaint();

		});

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// Functiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
	///////////////////////////////////////////////////////////////////////////////
	// pentru panelul principal
	@Override
	protected void paintComponent(Graphics grphcs) {

		super.paintComponent(grphcs);
		grphcs.drawImage(background1, 0, 0, this);
	}

	public void add_image() {

		try {
			background1 = ImageIO.read(new File("C:\\Users\\Marton\\eclipse-workspace\\BDProiect\\back.jpg"));
			background2 = ImageIO.read(new File("C:\\Users\\Marton\\eclipse-workspace\\BDProiect\\cooler1.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void instance() {

		food = new Food[numberoffoods];
		food2 = new Food[numberoffoods];

	}

	public void panels() {

		panel1 = new JPanel() {

			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				grphcs.drawImage(background2, 0, 0, this);

			}

		};
		panel2 = new JPanel() {
			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				grphcs.drawImage(background2, 0, 0, this);

			}
		};

		panel1.setLayout(null);
		panel2.setLayout(null);
		int oo = -1;

		{
			// citirea dataelor dintr-un fisier
			try {
				Scanner x = new Scanner(new File("very1.txt"));
				while (x.hasNext()) {
					oo++;
					String hhh = x.nextLine();

					String yyy[] = hhh.split("\\|");

					int a = Integer.parseInt(yyy[0]);
					int b = Integer.parseInt(yyy[2]);
					double c = Double.parseDouble(yyy[3]);
					// adaugarea datalor in clasele food
					food[oo] = new Food(a, yyy[1], b, c, "1.jpg");
					food2[oo] = new Food(a, yyy[1], b, c, "1.jpg");

				}
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			}

		}
		int h = -1;
		for (int t = 0; t < 1000; t = t + 200) {
			for (int z = 0; z < 850; z = z + 170) {
				h++;

				food[h].setBounds(z, t, 170, 200);

			}
		}
		for (int k = 0; k < numberoffoods; k++) {
			panel1.add(food[k]);
		}
		MouseHandler the = new MouseHandler();
		Handler thehandler = new Handler();
		for (int k = 0; k < numberoffoods; k++) {
			food[k].addActionListener(thehandler);
			food[k].addMouseListener(the);
		}

		panel1.setBounds(0, 0, 850, 1000);
		add(panel1);
		panel2.setBounds(1070, 0, 850, 1000);

		add(panel2);

	}

	// verifica daca exista componente in panel2
	boolean isThisComponentFoundInJPanel(Component c) {
		Component[] components = panel2.getComponents();
		for (Component component : components) {
			if (c == component) {
				return true;
			}
		}
		return false;
	}

	public void filter() {
		for (int i = 0; i < arraya.size(); i++) {
			for (int j = 0; j < arraya.size(); j++) {
				if (arraya.get(i) == (arraya.get(j))) {
					if (i == j) {

					} else {

						arraya.remove(j);
					}
				}

			}
		}
	}

	public void aseaza_componenetele_panel2() {
		filter();

		for (int i = 0; i < arraya.size(); i++) {

			if (arraya.get(i) == b) {
				for (int j = i; j < arraya.size(); j++) {

					boolean hue = true;

					if ((food2[arraya.get(j)].getBounds().getX() == 0)
							&& (food2[arraya.get(j)].getBounds().getY() == 200)) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();

						food2[arraya.get(j)].setBounds(680, cc - 200, 170, 200);
						hue = false;

					}
					if ((food2[arraya.get(j)].getBounds().getX() == 0)
							&& (food2[arraya.get(j)].getBounds().getY() == 400)) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();

						food2[arraya.get(j)].setBounds(680, cc - 200, 170, 200);
						hue = false;

					}
					if ((food2[arraya.get(j)].getBounds().getX() == 0)
							&& (food2[arraya.get(j)].getBounds().getY() == 600)) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();

						food2[arraya.get(j)].setBounds(680, cc - 200, 170, 200);
						hue = false;

					}
					if ((food2[arraya.get(j)].getBounds().getX() == 0)
							&& (food2[arraya.get(j)].getBounds().getY() == 800)) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();

						food2[arraya.get(j)].setBounds(680, cc - 200, 170, 200);
						hue = false;

					}

					if (food2[arraya.get(j)].getBounds().getX() == 0 && food2[arraya.get(j)].getBounds().getY() == 0) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();
						if (arraya.size() == 1) {
							rara = 0;
						}

					}

					if (food2[arraya.get(j)].getBounds().getX() == 170) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();

						food2[arraya.get(j)].setBounds(bb - 170, cc, 170, 200);

					}
					if (food2[arraya.get(j)].getBounds().getX() == 340) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();

						food2[arraya.get(j)].setBounds(bb - 170, cc, 170, 200);

					}

					if (food2[arraya.get(j)].getBounds().getX() == 510) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();
						food2[arraya.get(j)].setBounds(bb - 170, cc, 170, 200);

					}
					if (food2[arraya.get(j)].getBounds().getX() == 680) {

						int bb = (int) food2[arraya.get(j)].getBounds().getX();
						int cc = (int) food2[arraya.get(j)].getBounds().getY();
						if (hue) {
							food2[arraya.get(j)].setBounds(bb - 170, cc, 170, 200);

						}

					}

				}

			}

		}

		for (int o = 0; o <= arraya.size() - 1; o++) {
			if (arraya.get(o) == b) {

				arraya.remove(o);
			}
		}
		try {

			zz = (int) food2[arraya.get(arraya.size() - 1)].getBounds().getX();
			ll = (int) food2[arraya.get(arraya.size() - 1)].getBounds().getY();
		} catch (Exception e) {
			zz = 0;

			ll = 0;

		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Clase private
	///////////////////////////////////////////////////////////////////////////////////////////
	private class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < 25; i++) {
				if (e.getSource() == food[i]) {
					food[i].setBackground(Color.red);
					a = i;
				} else {
					for (int j = 0; j < 25; j++) {

						food[i].setBackground(Color.white);

					}

				}

			}

		}
	}

	private class Handler1 implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < 25; i++) {
				if (e.getSource() == food2[i]) {
					food2[i].setBackground(Color.green);

				} else {
					for (int j = 0; j < 25; j++) {

						food2[i].setBackground(Color.white);

					}

				}

			}

		}

	}

	private class MouseHandler extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if (SwingUtilities.isRightMouseButton(e)) {
				panel3 = new JPanel();
				for (int i = 0; i < numberoffoods; i++) {
					if (food[i] == e.getComponent()) {
						System.out.println(food[i].getDenumire_articol());
					}

				}
				JButton exitp = new JButton("x");
				exitp.setBounds(0, 0, 100, 100);

				panel3.setBackground(new Color(0, 0, 0, 65));

				panel3.setLayout(null);
				panel3.setBounds(0, 0, 1600, 1500);
				panel3.setVisible(true);
				panel3.add(exitp);
				panel3.show();
				exitp.addActionListener(we -> {
					remove(panel3);
					for (int i = 0; i < numberoffoods; i++) {
						food[i].setEnabled(true);
						food2[i].setEnabled(true);
						exit.setEnabled(true);
						addd.setEnabled(true);
						remove.setEnabled(true);
						addcommand.setEnabled(true);
					}

					repaint();
				});

				add(panel3);
				add(panel2);
				add(panel1);
				for (int i = 0; i < numberoffoods; i++) {
					food[i].setEnabled(false);
					food2[i].setEnabled(false);
					exit.setEnabled(false);
					addd.setEnabled(false);
					remove.setEnabled(false);
					addcommand.setEnabled(false);
				}
				repaint();

			}
		}

	}

	public void gettingtherows() {

		try {
			int i = 0;
			PreparedStatement SS = con.prepareStatement("select * from comanda where login_login_id=?");
			SS.setInt(1, id);
			ResultSet r1 = SS.executeQuery();
			while (r1.next()) {
				i++;

			}

			maxcommands1 = i;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void theconection() {
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}

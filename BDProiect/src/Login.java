import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame {
	// class composition
	private GUI g;
	private CommandList list;
	// jpanels
	private JPanel CreareAccPanel;
	private JPanel TheLogin;
	// jbuttons
	private JButton Login;
	private JButton Back_to_LoginScren;
	private JButton Create;
	private JButton Exit;
	private JButton createee;
	private JButton backtologinscreen;
	private JButton next;

	// JTextField
	// for login
	private JTextField forName;
	private JTextField forPassword;
	// for create
	private JTextField FName;
	private JTextField LName;
	private JTextField UserName;
	private JTextField Email;
	private JTextField Password;
	private JTextField Password1;
	private JLabel FNamel;
	private JLabel LNamel;
	private JLabel UserNamel;
	private JLabel Emaill;
	private JLabel Passwordl;
	private JLabel Password1l;
	JLabel alertthatuserandpass;
	// JLabel
	private JLabel n;
	private JLabel p;
	// Name Password User
	private String FirstName = " ";
	private String LastName = " ";
	private int id = 0;
	private int maxcommands=0;
	// mysql stuff
	private String url = "jdbc:mysql://localhost:3306/cantina?autoReconnect=true&useSSL=false";
	private String user = "root";
	private String pass = "qq20021995qq";
	private Connection con;

	public Login() {
		setLayout(null);
		// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// setLocationRelativeTo(null);
		// Login panel
		theconection();
		TheLogin = new JPanel();
		TheLogin.setLayout(null);
		// Create Panel
		CreareAccPanel = new JPanel();
		CreareAccPanel.setLayout(null);
		// Login panel
		createee = new JButton("createe");
		createee.setBounds(100, 100, 100, 100);

		// GUI panel
		backtologinscreen = new JButton("Back to the pas");
		// Login panel buttons
		Login = new JButton("Login");
		Login.setBounds(100, 300, 100, 50);
		Create = new JButton("Create Account");
		Create.setBounds(300, 300, 100, 50);
		Exit = new JButton("Exit");
		Exit.setBounds(200, 400, 100, 50);
		// Login panel textfild
		forName = new JTextField();
		forName.setBounds(150, 100, 200, 40);
		forPassword = new JTextField();
		forPassword.setBounds(150, 200, 200, 40);
		// Login panel labels
		n = new JLabel("Username");
		n.setBounds(90, 60, 100, 100);
		p = new JLabel("Password");
		p.setBounds(90, 170, 100, 100);

		// adding in the login panel
		TheLogin.add(Login);
		TheLogin.add(Create);
		TheLogin.add(Exit);
		TheLogin.add(forName);
		TheLogin.add(forPassword);
		TheLogin.add(n);
		TheLogin.add(p);

		// Create Panel buttons
		createee = new JButton("Create");
		createee.setBounds(400, 350, 100, 50);
		Back_to_LoginScren = new JButton("Back");
		Back_to_LoginScren.setBounds(100, 350, 100, 50);
		// CreatePanel jtextfield
		FName = new JTextField();
		FName.setBounds(200, 50, 200, 35);
		LName = new JTextField();
		LName.setBounds(200, 95, 200, 35);
		UserName = new JTextField();
		UserName.setBounds(200, 140, 200, 35);
		Email = new JTextField();
		Email.setBounds(200, 185, 200, 35);
		Password = new JTextField();
		Password.setBounds(200, 230, 200, 35);
		Password1 = new JTextField();
		Password1.setBounds(200, 275, 200, 35);

		// CreatePanel jlabel
		FNamel = new JLabel("First Name");
		FNamel.setBounds(130, 55, 90, 30);
		LNamel = new JLabel("Last Name");
		LNamel.setBounds(130, 100, 90, 30);
		UserNamel = new JLabel("Username");
		UserNamel.setBounds(130, 145, 90, 30);
		Emaill = new JLabel("Email");
		Emaill.setBounds(130, 190, 90, 30);
		Passwordl = new JLabel("Password");
		Passwordl.setBounds(130, 235, 90, 30);
		Password1l = new JLabel("Password");
		Password1l.setBounds(130, 280, 90, 30);
		// adding button in CreatePanel
		CreareAccPanel.add(Back_to_LoginScren);
		CreareAccPanel.add(createee);
		// adding textfield in CreatePanel
		CreareAccPanel.add(FName);
		CreareAccPanel.add(LName);
		CreareAccPanel.add(UserName);
		CreareAccPanel.add(Email);
		CreareAccPanel.add(Password);
		CreareAccPanel.add(Password1);
		// adding label in CreatePanel
		CreareAccPanel.add(FNamel);
		CreareAccPanel.add(LNamel);
		CreareAccPanel.add(UserNamel);
		CreareAccPanel.add(Emaill);
		CreareAccPanel.add(Passwordl);
		CreareAccPanel.add(Password1l);
		CreareAccPanel.setBounds(600, 0, 600, 500);
		CreareAccPanel.setVisible(true);
		CreareAccPanel.setLayout(null);
		CreareAccPanel.setBackground(java.awt.Color.red);
		/////////////////////////////////////////////
		 alertthatuserandpass = new JLabel("Username or Password wrong");
		////////////////////////////////////////
		add(CreareAccPanel);
		TheLogin.setBounds(0, 0, 1200, 1000);
		add(TheLogin);

		////////////////////////////////////
		Create.addActionListener(e -> {

			function_Create();

		});
		////////////////////////////////////////
		Login.addActionListener(e -> {
			loginaction();

		});
		////////////////////////////////////////////
		Back_to_LoginScren.addActionListener(e -> {

			inverse();

		});
		//////////////////////////////////////////////
		backtologinscreen.addActionListener(e -> {

			remove(g);
			repaint();
			forName.setText("");
			forPassword.setText("");
			 alertthatuserandpass.setText(" ");
			add(CreareAccPanel);
			add(TheLogin);
			setSize(600, 500);

		});
		createee.addActionListener(e -> {
			createaccount();

		});

		setVisible(true);
		setSize(600, 500);

	}

	// function
	public void function_Create() {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 350; i++) {
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Login.setBounds(100, 300 + i, 100, 50);
					Create.setBounds(300, 300 + i, 100, 50);
					Exit.setBounds(200, 400 + i, 100, 50);
					forName.setBounds(150 + i * -1, 100, 200, 40);
					forPassword.setBounds(150 + i * -1, 200, 200, 40);
					n.setBounds(110 + i * -1, 60, 100, 100);
					p.setBounds(90 + i * -1, 170, 100, 100);
					repaint();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 600; i++) {
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					CreareAccPanel.setBounds(600 - i, 0, 600, 500);
					repaint();
				}
			}
		});
		t1.start();
		t2.start();

	}

	// function
	public void inverse() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 350; i++) {
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Login.setBounds(100, 650 - i, 100, 50);
					Create.setBounds(300, 650 - i, 100, 50);
					Exit.setBounds(200, 750 - i, 100, 50);
					forName.setBounds(-200 + i, 100, 200, 40);
					forPassword.setBounds(-200 + i, 200, 200, 40);
					n.setBounds(-260 + i, 60, 100, 100);
					p.setBounds(-260 + i, 170, 100, 100);
					repaint();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 600; i++) {
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					CreareAccPanel.setBounds(0 + i, 0, 600, 500);
					repaint();
				}
			}
		});
		t1.start();
		t2.start();

	}

	// function
	public void loginaction() {
		int aa = 1;
		String user;
		String pass;
		int re = 0;
		int bb = 0;
		try {
			PreparedStatement stt = con.prepareStatement("select * from login where Username=? and Password=?;");
			stt.setString(1, forName.getText());
			stt.setString(2, forPassword.getText());
			ResultSet rrr1 = stt.executeQuery();

			while (rrr1.next())

			{
				bb = 1;
				re = rrr1.getInt("login_id");
				user = rrr1.getString("Username");
				FirstName = rrr1.getString("First_Name");
				LastName = rrr1.getString("Last_Name");
				pass = rrr1.getString("Password");
				if ((user.equals(forName.getText()) && (pass.equals(forPassword.getText())))) {
					id = re;
					aa = 0;

				} else {

				}

			}
			if (bb == 0) {
				forName.setText("");
				forPassword.setText("");
				
				alertthatuserandpass.setBounds(160, 230, 200, 50);
				TheLogin.add(alertthatuserandpass);
				alertthatuserandpass.setForeground(Color.red);
				repaint();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (aa == 0) {
			
			next = new JButton("Next");
			next.setBounds(400, 300, 100, 100);
			
			list = new CommandList(FirstName,LastName,id);
			list.add(next);
			list.setBounds(0, 0, 1200, 700);
			add(list);
			 gettingtherows();
			remove(TheLogin);
			remove(CreareAccPanel);
			repaint();

			next.addActionListener(e -> {
				remove(list);
				remove(next);
				repaint();

				g = new GUI(FirstName, LastName, id,maxcommands);

				add(g);
				backtologinscreen.setBounds(902, 500, 100, 100);
				g.add(backtologinscreen);

				Thread t1 = new Thread(new Runnable() {

					@Override
					public void run() {
						int framewidth = 500;
						int frameheigth = 600;
						int framewidthp = 500;
						int frameheigthp = 600;
						for (int i = 0; i <= 1320; i++) {
							try {
								Thread.sleep(4);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}

							if (framewidth <= 1080) {
								frameheigth = frameheigth + 1 * i;
								framewidth = framewidth + 1 * i;
								frameheigthp = frameheigthp + 1 * i;
								framewidthp = framewidthp + 1 * i;
								setSize(frameheigth, framewidth);
								g.setSize(frameheigthp, framewidthp);

							} else {
								frameheigth = frameheigth + 1 * i;

								frameheigthp = frameheigthp + 1 * i;

								setSize(frameheigth, framewidth);
								g.setSize(frameheigthp, framewidthp);

							}
							if (frameheigth > 1920) {

								break;
							}

						}

					}
				});

				t1.start();

				remove(TheLogin);
				remove(CreareAccPanel);
				repaint();

			});

		}
	}

	public void createaccount() {
		String sFName = FName.getText();
		String sLName = LName.getText();
		String sUserName = UserName.getText();
		String sEmail = Email.getText();
		String sPassword = Password.getText();
		String sPassword1 = Password1.getText();

		int aa = 1;
		int bb = 1;
		try {
			PreparedStatement st1 = con.prepareStatement("select * from login where Username=?");
			st1.setString(1, sUserName);
			ResultSet r1 = st1.executeQuery();
			if (r1.next()) {
				aa = 0;
			}
			PreparedStatement st2 = con.prepareStatement("select * from login where Email=?");
			st2.setString(1, sEmail);
			ResultSet r2 = st2.executeQuery();
			if (r2.next()) {
				bb = 0;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (sFName.length() > 0 && sLName.length() > 0 && sUserName.length() > 0 && sEmail.length() > 0
				&& sPassword.length() > 0 && sPassword1.length() > 0) {
			if (aa == 1) {
				if (bb == 1) {
					try {
						PreparedStatement St = con.prepareStatement("INSERT INTO login"
								+ "(Username,Password,First_Name,Last_Name,Email)VALUES(?, ?, ?, ?, ?)");
						St.setString(1, sUserName);
						St.setString(2, sPassword);
						St.setString(3, sFName);
						St.setString(4, sLName);
						St.setString(5, sEmail);
						St.executeUpdate();
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "Account was created");
						FName.setText("");
						LName.setText("");
						UserName.setText("");
						Email.setText("");
						Password.setText("");
						Password1.setText("");
						inverse();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Error");
				}
			} else {
				System.out.println("Error");
			}
		} else {

			System.out.println("Error");

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
	public void gettingtherows()
	{
		
		
		try {
			int i=0;
			PreparedStatement SS = con.prepareStatement("select * from comanda where login_login_id=?");
			SS.setInt(1, id);
			ResultSet r1 = SS.executeQuery();
			while(r1.next())
			{
				i++;
				
				
			}
			maxcommands=i;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

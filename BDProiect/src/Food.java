import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Food extends JButton {
	private int b=1;
	private int Nr_Crt;
	private String Denumire_articol;
	private int Cantitate;
	private double Pret;
	private String nume_poza;
	private ImageIcon icon;
	private BufferedImage image;
	private int buc_produs = 0;
	private String sbuc_produs;
private JPanel p;
	public Food(int Nr_Crt, String Denumire_articol, int Cantitate, double Pret, String nume_poza) {

		this.Nr_Crt = Nr_Crt;
		this.Cantitate = Cantitate;
		this.Pret = Pret;
		this.Denumire_articol = Denumire_articol;
		this.nume_poza = nume_poza;
	
		
		setText("<html><center>" +this.Denumire_articol +"<br> "+ this.Pret+"Lei" +" </center></html>");
		
		Font  bItalic = new Font("Consolas", Font.BOLD, 18);
		setFont(bItalic);
		setBackground(java.awt.Color.white);

		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawString(Denumire_articol, 0, 0);

	}

	public int getNr_Crt() {

		return Nr_Crt;

	}

	public String getDenumire_articol() {
		return Denumire_articol;
	}

	public int getCantitate() {
		return Cantitate;
	}

	public double getPret() {
		return Pret;

	}

	public void setbuc_produs() {

		buc_produs++;
		sbuc_produs = "" + buc_produs;
		
		Font  bItalic = new Font("Consolas", Font.BOLD, 18);
		setFont(bItalic);
		setText("<html><center>" +this.Denumire_articol +"<br> "+ sbuc_produs +" buc"  +" </center></html>");
	}

	public int getbuc_produs() {

		return buc_produs;

	}

	public void anotherthing() {

		buc_produs = 0;

	}
}

package ihm;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;

public class PanelGrille extends JPanel
{
	public PanelGrille(Controleur ctrl)
	{
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(new JLabel(new ImageIcon("./donnees/images/plateaubleu.gif")));
	}	
}
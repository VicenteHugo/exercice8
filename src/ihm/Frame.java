package ihm;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controleur.Controleur;

public class Frame extends JFrame
{
	private static int width  = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	private Controleur ctrl;

	private PanelGrille panelGrille;
	private PanelChoix  panelChoix ;

	public Frame(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setSize((int) (width*0.67), (int) (height*0.67));
		this.setLocation(width/2 - (int) (width/3), height/2 - (int) (height/3));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/*----------------*/
		/* Création       */
		/*----------------*/
		this.panelGrille = new PanelGrille(ctrl);
		this.panelChoix  = new PanelChoix ();

		/*----------------*/
		/* Positionnement */
		/*----------------*/
		this.add(this.panelGrille);
		this.add(this.panelChoix , BorderLayout.EAST);

		this.setVisible(true);
	}
}

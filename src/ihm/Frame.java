package ihm;

import controleur.Controleur;
import ihmgui.FrameGrille;

import java.awt.Toolkit;

public class Frame extends FrameGrille
{
	private Controleur ctrl;

	/**
	 * Constructeur de la Frame
	 * @param ctrl le controleur créant la frame
	 */
	public Frame(Controleur ctrl)
	{
		super(ctrl);

		this.ctrl = ctrl;
		
		this.setSize     ( 650, 400  );
		this.setLocation ( 200,  10  );
		this.setTitle    ( "Exemple" );
		this.setVisible  ( true      );

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./donnees/images/plateaurouge.gif"));
	}
}

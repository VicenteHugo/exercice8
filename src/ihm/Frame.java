package ihm;

import controleur.Controleur;
import ihmgui.FrameGrille;

public class Frame extends FrameGrille
{
	private Controleur ctrl;
	public Frame(Controleur ctrl)
	{
		super(ctrl);

		this.ctrl = ctrl;
		
		this.setSize     ( 650, 400  );
		this.setLocation ( 200,  10  );
		this.setTitle    ( "Exemple" );
		this.setVisible  ( true      );
	}
}

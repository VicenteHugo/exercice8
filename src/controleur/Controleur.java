package controleur;

import ihm.Frame;
import metier.Metier;

public class Controleur 
{
	private Metier metier;
	private Frame  ihm;
	
	public Controleur()
	{
		this.metier = new Metier();
		this.ihm    = new Frame(this);
	}	

	public int getTaille()
	{
		return this.metier.getTaillePlateau();
	}

	public static void main(String[] args) 
	{
		new Controleur();	
	}
}

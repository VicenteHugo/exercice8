package controleur;

import metier.Metier;

public class Controleur 
{
	private Metier metier;
	
	public Controleur()
	{
		this.metier = new Metier();
	}	

	public static void main(String[] args) 
	{
		new Controleur();	
	}
}

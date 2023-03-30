package controleur;

import ihm.Frame;
import ihmgui.Controle;
import metier.Metier;

public class Controleur extends Controle
{
	private Metier metier ;
	private Frame  frame  ;
	
	public Controleur()
	{
		this.metier = new Metier()         ;
		this.frame  = new Frame(this);
	}	

	public int getTaille()
	{
		return this.metier.getTaillePlateau();
	}

	public String getCoulDifficulte(){return this.metier.getCoulDifficulte();}

	public void niveauSuivant()
	{
		this.metier.niveauSuivant();
		this.frame.majIHM();
	}

	public void niveauPrecedent()
	{
		this.metier.niveauPrecedent();
		this.frame.majIHM();
	}


	public void reset()
	{
		this.metier.reset();
		this.frame.majIHM();
	}

	public void undo()
	{
		this.metier.undo();
		this.frame.majIHM();
	}

	public void redo()
	{
		this.metier.redo();
		this.frame.majIHM();
	}


	/* GRAPHIQUE */
	public int     setNbLigne        () { return this.metier.getTaillePlateau();}
	public int     setNbColonne      () { return this.metier.getTaillePlateau();}
	
	public int     setLargeurImg     () { return 50;                                   }
	public boolean setNumLigneColonne() { return true;                          }


	public String  setFondGrille     () { return "./donnees/images/" + this.getCoulDifficulte() + ".gif";}

	public String setBouton(int numBtn)
	{
		String lib;

		switch ( numBtn )
		{
			case 0 : lib = "Niveau Suivant";          break;
			case 1 : lib = "Niveau Précédent";        break;
			case 2 : lib = "Recommencer";             break;
			default: lib = null;                      // cette dernière ligne est importante, elle met fin à la contruction des boutons
		}

		return lib;
	}

	public String setImage ( int ligne, int colonne, int couche)
	{
		char   symbole;
		String rep = "./donnees/images/";
		String sImage=null;

		if ( couche==0)
		{
			symbole = this.metier.getSymbole (ligne, colonne);

			if      ( symbole == 'P' && this.metier.coordValide(ligne, colonne) ) sImage = rep + "pion.gif";
			else if ( symbole == 'C' && this.metier.coordValide(ligne, colonne) ) sImage = rep + "cavalier.gif";
			else if ( symbole == 'F' && this.metier.coordValide(ligne, colonne) ) sImage = rep + "fou.gif";
			else if ( symbole == 'K' && this.metier.coordValide(ligne, colonne) ) sImage = rep + "roi.gif";
			else if ( symbole == 'Q' && this.metier.coordValide(ligne, colonne) ) sImage = rep + "reine.gif";
			else if ( symbole == 'T' && this.metier.coordValide(ligne, colonne) ) sImage = rep + "tour.gif";
			else                                                                  sImage = rep + "vide30.gif";
		}

		return sImage;
	}


	/* INTERACTION */
	public void jouer (String touche)
	{
		if(touche.equals("CR-R"))this.reset();
		if(touche.equals("FL-G"))this.niveauPrecedent();
		if(touche.equals("FL-D"))this.niveauSuivant();
		if(touche.equals("CR-Z"))this.undo();
		if(touche.equals("CR-Y"))this.redo();
	}

	public void bouton (int action)
	{
		if(action == 0) this.niveauSuivant();
		if(action == 1) this.niveauPrecedent();
		if(action == 2) this.reset();
	}

	public void glisser (int ligne1, int colonne1, int ligne2, int colonne2)
	{
		if(ligne1 != ligne2 || colonne1 != colonne2)
		{
			this.metier.deplacer(this.metier.getPiece(ligne1, colonne1), ligne2, colonne2);
		}

		this.frame.majIHM();
	}
	

	public static void main(String[] args) 
	{
		new Controleur();	
	}
}

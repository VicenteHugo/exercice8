package metier.piece;

import metier.Metier;

public class Reine extends Piece
{
	private Metier metier;

	public Reine(int lig, int col, Metier metier)
	{
		super(lig, col);

		this.metier = metier;
	}

	public boolean peutDeplacer(int lig, int col)
	{
		if(this.getLig() == lig && this.getCol() != col)
			return true;
		if(this.getCol() == col && this.getLig() != lig)
			return true;

		for (int cpt = 0; cpt < this.metier.getTaillePlateau(); cpt++)
		{
			if(this.getLig() - cpt == lig && this.getCol() - cpt == col ||
		       this.getLig() + cpt == lig && this.getCol() - cpt == col ||
			   this.getLig() - cpt == lig && this.getCol() + cpt == col ||
			   this.getLig() + cpt == lig && this.getCol() + cpt == col   )
			return true;
		}
		return false;
	}

	public String getSymbole(){return "reine";}
}

package metier.piece;

import metier.Metier;

public class Fou extends Piece
{
	private Metier metier;

	public Fou(int lig, int col, Metier metier)
	{
		super(lig, col);

		this.metier = metier;
	}

	public boolean peutDeplacer(int lig, int col)
	{
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

	public char getSymbole(){return 'F';}
}

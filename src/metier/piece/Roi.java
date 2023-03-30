package metier.piece;

public class Roi extends Piece
{
	public Roi(int lig, int col)
	{
		super(lig, col);
	}

	public boolean peutDeplacer(int lig, int col)
	{
		if(this.getLig() == lig && this.getCol() == col - 1 || this.getCol() == col + 1 )
			return true;
		if(this.getCol() == col && this.getLig() == lig - 1 || this.getLig() == lig + 1)
			return true;

		return this.getLig() - 1 == lig && this.getCol() - 1 == col ||
		       this.getLig() - 1 == lig && this.getCol() + 1 == col ||
		       this.getLig() + 1 == lig && this.getCol() - 1 == col ||
		       this.getLig() + 1 == lig && this.getCol() + 1 == col;
	}

	public String getSymbole(){return "roi";}
}

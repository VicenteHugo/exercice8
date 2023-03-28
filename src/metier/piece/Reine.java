package metier.piece;

public class Reine extends Piece
{
	public Reine(int lig, int col)
	{
		super(lig, col);
	}

	public boolean peutDeplacer(int lig, int col)
	{
		if(this.getLig() == lig && this.getCol() != col)
			return true;
		if(this.getCol() == col && this.getLig() != lig)
			return true;

		return false;

		// return this.getLig() - 1 == lig && this.getCol() - 1 == col ||
		//        this.getLig() - 2 == lig && this.getCol() - 1 == col ||
		//        this.getLig() + 1 == lig && this.getCol() - 2 == col ||
		//        this.getLig() + 2 == lig && this.getCol() - 2 == col ||
		//        this.getLig() - 1 == lig && this.getCol() + 1 == col ||
		//        this.getLig() - 2 == lig && this.getCol() + 1 == col ||
		//        this.getLig() + 1 == lig && this.getCol() + 2 == col ||
		//        this.getLig() + 2 == lig && this.getCol() + 2 == col;
	}

	public char getSymbole(){return 'Q';}
}

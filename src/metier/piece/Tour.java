package metier.piece;

public class Tour extends Piece
{
	public Tour(int lig, int col)
	{
		super(lig, col);
	}

	@Override
	public boolean peutDeplacer(int lig, int col) 
	{
		if(this.getLig() == lig && this.getCol() != col)
			return true;
		if(this.getCol() == col && this.getLig() != lig)
			return true;

		return false;
	}

	@Override
	public char getSymbole() {return 'T';}
	
}

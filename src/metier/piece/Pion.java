package metier.piece;

public class Pion extends Piece
{

	public Pion(int lig, int col) 
	{
		super(lig, col);
	}

	@Override
	public boolean peutDeplacer(int lig, int col) 
	{
		if (this.getLig() + 1 == lig)
			return this.getCol()     == col ||
			       this.getCol() + 1 == col ||
				   this.getCol() - 1 == col;
		

		return false;
	}

	@Override
	public char getSymbole() {return 'P';}
		
}

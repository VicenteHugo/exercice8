package metier.piece;

public class Cavalier extends Piece
{
	public Cavalier(int lig, int col)
	{
		super(lig, col);
	}

	@Override
	public boolean peutDeplacer(int lig, int col) 
	{
		if (this.getLig() + 2 == lig || this.getLig() - 2 == lig)
			return this.getCol() - 1 == col || this.getCol() + 1 == col;
		 	
		if (this.getCol() + 2 == lig || this.getCol() - 2 == lig)
			return this.getLig() - 1 == col || this.getLig() + 1 == col;

		return false;
	}

	@Override
	public String getSymbole() {return "cavalier";}
}

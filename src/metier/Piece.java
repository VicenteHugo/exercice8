package metier;

public abstract class Piece
{
	public Piece()
	{

	}

	public void deplacer(int col, int lig)
	{
		if (this.peutDeplacer(lig, col))
		{
			this.lig = lig;
			this.col = col;
		}
	}

	public abstract boolean peutDeplacer(int lig, int col);
}
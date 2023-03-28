package metier.piece;

public abstract class Piece
{
	private int col;
	private int lig;

	
	public Piece(int col, int lig)
	{		
		this.lig = lig;
		this.col = col;
	}

	public void deplacer(int col, int lig)
	{
		if (this.peutDeplacer(lig, col))
		{
			this.lig = lig;
			this.col = col;
		}
	}

	public int getCol() {return this.col;}
	public int getLig() {return this.lig;}

	/**
	 * Permet de vérifier si une pièce peut se déplacer à la position de lig et col.
	 * @param lig, ligne sur la quelle on veut déplacer la pièce.
	 * @param col, colonne sur la quelle on veut déplacer la pièce.
	 * @return si la piece peut se deplacer
	 */
	public abstract boolean peutDeplacer(int lig, int col);

	/**
	 * Permet de connaître le symbole de la pièce.
	 * @return le charactère symbolisant la pièce.
	 */
	public abstract char    getSymbole();
}
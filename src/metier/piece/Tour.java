package metier.piece;

public class Tour extends Piece
{
	/**
	 * Constructeur de la Tour
	 * @param lig,    ligne   de la Tour.
	 * @param col,    colonne de la Tour.
	 * @param metier, permet d'obtenir la liste de pièce.
	 */
	public Tour(int lig, int col)
	{
		super(lig, col);
	}

	public Tour(Piece p)
	{
		super(p);
	}

	/**
	 * Permet de determiner si le deplacement demander est réalisable pour une Tour
	 * @param  lig, ligne   de destination.
	 * @param  col, colonne de destination.
	 * @return true si le deplacement est celui d'une Tour (forme de +) et false dans tout autre cas.
	 */
	@Override
	public boolean peutDeplacer(int ligDest, int colDest) 
	{
		return  (this.getLig() == ligDest && this.getCol() != colDest || this.getCol() == colDest && this.getLig() != ligDest && 
				(!this.autresPieces(Piece.metier.getLstPiece(), ligDest, colDest) || 
				(this.getLig() + 1 == ligDest || this.getLig() - 1 == ligDest    || 
				this.getCol() + 1 == colDest || this.getCol() - 1 == colDest))) 
				&&
				this.mange(ligDest, colDest, Piece.metier.getLstPiece()) != null;
	}

	@Override
	public char getSymbole() {return 'T';}
	
}

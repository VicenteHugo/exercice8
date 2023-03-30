package metier.piece;


public class Pion extends Piece
{
	/**
	 * Constructeur du Pion
	 * @param lig,    ligne   du Pion.
	 * @param col,    colonne du Pion.
	 * @param metier, permet d'obtenir la liste de pièce.
	 */
	public Pion(int lig, int col) 
	{
		super(lig, col);
	}

	public Pion(Piece p)
	{
		super(p);
	}

	/**
	 * Permet de determiner si le deplacement demander est réalisable pour un Pion
	 * @param  lig, ligne   de destination.
	 * @param  col, colonne de destination.
	 * @return true si le deplacement est celui d'un Pion (1 en haut ou diagonal vers le haut) et false dans tout autre cas.
	 */
	@Override
	public boolean peutDeplacer(int ligDest, int colDest) 
	{
		return (this.getLig() - 1 == ligDest &&
			   (this.getCol()     == colDest ||
			    this.getCol() + 1 == colDest ||
		  	    this.getCol() - 1 == colDest))
			   &&
			    this.mange(ligDest, colDest, Piece.metier.getLstPiece()) != null;

	}

	@Override
	public char getSymbole() {return 'P';}
		
}

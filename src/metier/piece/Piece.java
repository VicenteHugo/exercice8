package metier.piece;

import java.util.List;

import metier.Metier;

public abstract class Piece
{
	protected static Metier metier;

	private   int    lig ;
	private   int    col ;

	/**
	 * Constructeur de Piece
	 * @param lig, ligne   de la piece.
	 * @param col, colonne de la piece.
	 */
	public Piece(int lig, int col)
	{		
		this.lig    = lig ;
		this.col    = col ;
	}

	public Piece(Piece p)
	{
		this.lig = p.lig;
		this.col = p.col;
	}
	/**
	 * Méthode pour déplacer une piece
	 * @param lig, ligne   de destination.
	 * @param col, colonne de destination.
	 */
	public void deplacer(int lig, int col)
	{
		if (this.peutDeplacer(lig, col))
		{
			this.lig = lig ;
			this.col = col ;
		}
	}

	/**
	 * Méthode pour déterminer si une piece est deja à la place de notre piece
	 * @param lstPiece, liste des pieces existantes.
	 * @return la piece avec laquelle nous sommes confondus.
	 */
	public Piece estConfondu(List<Piece> lstPiece)
	{
		return mange(this.lig, this.col, lstPiece);
	}

	public Piece mange(int lig, int col, List<Piece> lstPiece)
	{
		for (Piece piece : lstPiece)
			if(piece.lig == lig && piece.col == col && piece != this)
				return piece;

		return null;
	}

	/**
	 * Permet de vérifier si un pièce vas être sur le chemin de notre pièce.
	 * @param lstPiece, liste de tout les pièce contenue sur le plateau.
	 * @param ligDest, ligne sur la quelle on veut déplacer la pièce.
	 * @param colDest, colonne sur la quelle on veut déplacer la pièce.
	 * @return si une pièce est sur le chemin la méthode retourne vrai sinon elle retourne faux
	 */
	public boolean autresPieces(List<Piece> lstPiece, int ligDest, int colDest)
	{
		int ligPas = 0;
		int colPas = 0;

		if(this.lig > ligDest) ligPas = -1;
		if(this.lig < ligDest) ligPas = 1;
		if(this.col > colDest) colPas = -1;
		if(this.col < colDest) colPas = 1;


		for(int cptLig = this.lig + ligPas, cptCol = this.col + colPas; !(cptCol == colDest && cptLig == ligDest); cptLig += ligPas, cptCol += colPas)
		{
			for (Piece p: lstPiece)
			{
				if(p.lig == cptLig && p.col == cptCol)
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Permet de connaître la colonne actuel de la pièce
	 * @return un int qui représente la colonne actuel de la pièce
	 */
	public int getCol() { return this.col ;}

	/**
	 * Permet de connaître la ligne actuel de la pièce
	 * @return un int qui représente la ligne actuel de la pièce
	 */
	public int getLig() { return this.lig ;}

	@Override
	public String toString() {
		return "" + this.getSymbole();
	}


	public static void setMetier(Metier metier)
	{
		Piece.metier = metier;
	}


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
	public abstract char getSymbole();
}
package metier;

import java.io .FileReader      ;
import java.nio.charset.Charset ;

import java.util.ArrayList ;
import java.util.List      ;
import java.util.Map       ;
import java.util.HashMap   ;
import java.util.Scanner   ;

import metier.piece.Cavalier ;
import metier.piece.Fou      ;
import metier.piece.Piece    ;
import metier.piece.Pion     ;
import metier.piece.Reine    ;
import metier.piece.Roi      ;
import metier.piece.Tour     ;

public class Metier 
{
	/**
	 * HashMap qui associe la difficulté à la couleur du plateau
	 */
	private static Map<Character, String> dicDifficuclte = new HashMap<Character, String>();

	
	/**
	 * Le niveau en cours
	 */
	private int               niveau;

	/**
	 * Le niveau de difficulté en cours
	 */
	private char              difficulte;

	/**
	 * La liste de pièce du plateau actuel
	 */
	private List<Piece>       lstPiece;

	/**
	 * Taille du plateau actuel
	 */
	private int               taillePlateau;
	
	/**
	 * Liste de tout les etat de plateau du niveau actuel
	 */
	private List<List<Piece>> lstEtat;

	/**
	 * indice de l'etat actuel
	 */
	private int               cptEtat;
	

	
	static
	{
		Metier.dicDifficuclte.put( 'D', "plateauvert"   );
		Metier.dicDifficuclte.put( 'I', "plateaubleu"   );
		Metier.dicDifficuclte.put( 'A', "plateauviolet" );
		Metier.dicDifficuclte.put( 'E', "plateaurouge"  );
	}


	/**
	 * Constructeur de Metier
	 */
	public Metier()
	{
		this.niveau   = 1;
		this.lstPiece = new ArrayList<Piece>(16);
		this.lstEtat  = new ArrayList<List<Piece>>();
		this.cptEtat  = -1;

		Piece.setMetier(this);

		this.chargerNiveau();
	}

	/**
	 * Charge le niveau, en fonction de l'attribut niveau, génère le niveau par rapport au fichier txt
	 */
	public void chargerNiveau()
	{
		try
		{
			Scanner sc = new Scanner(new FileReader("./donnees/niveaux/niveau" + this.niveau + ".txt", Charset.forName("UTF-8")));
		
			this.lstPiece.clear();
			this.lstEtat .clear();
			this.cptEtat = -1;

			this.difficulte = sc.nextLine().charAt(0);

			int cptL = 0;
			while(sc.hasNextLine())
			{
				sc.nextLine();
				
				if(sc.hasNextLine())
				{
					String   line           = sc.nextLine();
					String[] positionnement = line.split("|");
					
					for (int cptP = 2, cptC = 0; cptP < positionnement.length; cptP+=4, cptC++) 
					{
						Piece piece = null;	

						switch (positionnement[cptP].charAt(0)) 
						{
							case 'P' -> piece = new Pion     ( cptL, cptC );
							case 'C' -> piece = new Cavalier ( cptL, cptC );
							case 'T' -> piece = new Tour     ( cptL, cptC );
							case 'Q' -> piece = new Reine    ( cptL, cptC );
							case 'K' -> piece = new Roi      ( cptL, cptC );
							case 'F' -> piece = new Fou      ( cptL, cptC );
						}
	
						if(piece != null)
							this.lstPiece.add( piece );
					}
					cptL++;
				}
			}
			this.lstEtat.add( ++this.cptEtat, this.copy( this.lstPiece ));
			
			this.taillePlateau = cptL;
		}
		catch (Exception e ){ e.printStackTrace() ;}
	}

	/**
	 * Retourne la pièce à la ligne et colonne passé en paramètre
	 * @param lig la ligne   voulu
	 * @param col la colonne voulu
	 * @return la Piece à la ligne et colonne, ou null si il n'y a pas de Piece
	 */
	public Piece getPiece(int lig, int col)
	{
		if( lig * this.taillePlateau + col >= this.taillePlateau * this.taillePlateau )
			return null;
	
		for (Piece piece : lstPiece)
			if(piece.getLig() == lig && piece.getCol() == col)
				return piece;

		return null;
	}

	/**
	 * Retourne le niveau actuel
	 * @return le niveau actuel
	 */
	public int getNiveau() { return this.niveau ;}

	/**
	 * Passe au niveau suivant
	 */
	public void niveauSuivant()
	{
		this.niveau = this.niveau < 60 ? this.niveau+1 : this.niveau ;
		this.chargerNiveau();
	}

	/**
	 * Passe au niveau precedent
	 */
	public void niveauPrecedent()
	{
		this.niveau = this.niveau >  1 ? this.niveau-1 : this.niveau ;
		this.chargerNiveau();
	}

	/**
	 * Permet de réinitialiser le niveau actuel
	 */
	public void reset()
	{
		this.chargerNiveau();
	}

	/**
	 * Vas permettre de revenir une étape en arrière jusqu'a l'état initial
	 */
	public void undo()
	{
		if(cptEtat > 0)
			cptEtat--;

		lstPiece = this.copy(lstEtat.get(cptEtat));
	}

	/**
	 * Vas permettre d'aller une étape en avant jusqu'a la dernière effectuer
	 */
	public void redo()
	{
		if( cptEtat < lstEtat.size() - 1 )
			cptEtat++;

		lstPiece = this.copy(lstEtat.get( cptEtat ));
	}

	/**
	 * Méthode qui vas retourné la taille du tableau
	 * @return int qui représente la taille de tableaux (int x int)
	 */
	public int getTaillePlateau() { return this.taillePlateau ;}

	/**
	 * Méthode qui deplace une Piece vers des coordonnés
	 * @param piece Piece à deplacer
	 * @param ligDest ligne  de le destination
	 * @param colDest colone de le destination
	 */
	public void deplacer(Piece piece, int ligDest, int colDest)
	{
		if ( piece == null )
			return;


		if ( ligDest >= this.taillePlateau || colDest >= this.taillePlateau ||
		     ligDest <  0                  || colDest <  0                     )
			return;

		if ( piece.peutDeplacer( ligDest, colDest))
			 piece.deplacer( ligDest, colDest );

		Piece autrePiece;

		if ( (autrePiece = piece.estConfondu(lstPiece)) != null )
			this.lstPiece.remove( autrePiece );

		this.cptEtat++;

		try { this.lstEtat.remove(this.cptEtat); } catch ( Exception e ) {}

		this.lstEtat.add( this.cptEtat, this.copy( this.lstPiece ) );
		

		if(this.lstPiece.size() == 1)
			this.niveauSuivant();
	}

	/**
	 * Méthode qui retourne true si les coordonnés sont valide, false elles ne le sont pas
	 * @param lig ligne   des coordonnés a verifier
	 * @param col colonne des coordonnés a verifier
	 * @return true si les coordonnés sont valide, false elles ne le sont pas
	 */
	public boolean coordValide(int ligDest, int colDest)
	{
		return !( ligDest >= this.taillePlateau || colDest >= this.taillePlateau ||
                  ligDest <  0                  || colDest <  0                     );            
	}

	/**
	 * Méthode qui retourne la couleur de la difficulté actuelle
	 * @return retourne un String étant la couleur de la difficulté actuelle
	 */
	public String getCoulDifficulte()
	{
		return Metier.dicDifficuclte.get( this.difficulte );
	}

	/**
	 * Methode pour obtenir la List tout les pieces du plateau
	 * @return List de pieces
	 */
	public List<Piece> getLstPiece() { return this.lstPiece ;}

	/**
	 * Methode retournant le char symbole de la piece aux coordonnées donnés
	 * @param lig ligne   de la Piece dont le symbole est retourné
	 * @param col colonne de la Piece dont le symbole est retourné
	 * @return char étant le symbole de la Piece
	 */
	public char getSymbole(int lig, int col)
	{
		if( lig * this.taillePlateau + col >= this.taillePlateau * this.taillePlateau )
			return ' ';
		
		for ( Piece piece : lstPiece )
			if( piece.getLig() == lig && piece.getCol() == col )
				return piece.getSymbole();

		return ' ';
	}

	/**
	 * Méthode qui copie la List de piece en copiant chaque piece
	 * @param lstPiece List de piece à copier
	 * @return la copie de lstPiece avec des copies des pieces
	 */
	public List<Piece> copy(List<Piece> lstPiece)
	{
		List<Piece> lstRet = new ArrayList<Piece>();
		for ( Piece piece : lstPiece )
		{
			Piece tmp = null;
			if ( piece instanceof Cavalier ) tmp = new Cavalier (piece);
			if ( piece instanceof Fou      ) tmp = new Fou      (piece);
			if ( piece instanceof Pion     ) tmp = new Pion     (piece);
			if ( piece instanceof Reine    ) tmp = new Reine    (piece);
			if ( piece instanceof Roi      ) tmp = new Roi      (piece);
			if ( piece instanceof Tour     ) tmp = new Tour     (piece);
			
			lstRet.add( tmp );
		}

		return lstRet;
	}
}
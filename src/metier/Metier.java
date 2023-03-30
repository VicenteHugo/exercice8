package metier;

import java.io.FileReader  ;
import java.nio.charset.Charset;
import java.util.ArrayList ;
import java.util.List      ;
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
	private int               niveau        ;
	private String            difficulte    ;
	private List<Piece>       lstPiece      ;
	private int               taillePlateau ;
	private List<List<Piece>> lstEtat       ;
	private int               cptEtat       ;
	
	public Metier()
	{
		this.niveau   = 1;
		this.lstPiece = new ArrayList<Piece>();
		this.lstEtat  = new ArrayList<List<Piece>>();
		this.cptEtat  = -1;

		Piece.setMetier(this);

		this.chagerNiveau();
	}

	public void chagerNiveau()
	{
		try
		{
			Scanner sc = new Scanner(new FileReader("./donnees/niveaux/niveau" + this.niveau + ".txt", Charset.forName("UTF-8")));
		
			this.lstPiece.clear();
			this.lstEtat.clear();
			this.cptEtat = -1;

			this.difficulte = sc.nextLine();

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
							case 'P' -> piece = new Pion     ( cptL, cptC);
							case 'C' -> piece = new Cavalier ( cptL, cptC);
							case 'T' -> piece = new Tour     ( cptL, cptC);
							case 'Q' -> piece = new Reine    ( cptL, cptC);
							case 'K' -> piece = new Roi      ( cptL, cptC);
							case 'F' -> piece = new Fou      ( cptL, cptC);
						}
	
						if(piece != null)
							this.lstPiece.add(piece);
					}
					cptL++;
				}
			}
			this.lstEtat.add(this.copy(this.lstPiece));
			this.cptEtat++;
			
			this.taillePlateau = cptL;
		}
		catch(Exception e){e.printStackTrace();}
	}

	public Piece getPiece(int lig, int col)
	{
		if(lig * taillePlateau + col >= taillePlateau * taillePlateau)
			return null;
	
		for (Piece piece : lstPiece)
			if(piece.getLig() == lig && piece.getCol() == col)
				return piece;

		return null;
	}

	public void niveauSuivant()
	{
		this.niveau = this.niveau < 60 ? this.niveau+1:this.niveau;
		this.chagerNiveau();
	}

	public void niveauPrecedent()
	{
		this.niveau = this.niveau > 1 ? this.niveau-1:this.niveau;
		this.chagerNiveau();
	}

	public void reset()
	{
		if(this.niveau < 60 )
		{
			this.niveauSuivant();
			this.niveauPrecedent();
		}
		else
		{
			this.niveauPrecedent();
			this.niveauSuivant();
		}
	}

	public void undo()
	{
		if(cptEtat > 0)
			cptEtat--;

		lstPiece = lstEtat.get(cptEtat);
	}

	public void redo()
	{
		if(cptEtat < lstEtat.size() - 1)
			cptEtat++;

		lstPiece = lstEtat.get(cptEtat);
	}


	public int getTaillePlateau() {return this.taillePlateau;}


	public void deplacer(Piece piece, int lig, int col)
	{
		if(piece == null)
			return;


		if(lig >= taillePlateau || col >= taillePlateau ||
		   lig <  0             || col <  0               )
			return;

		if(piece.peutDeplacer(lig, col))
			piece.deplacer(lig, col);

		Piece autrePiece;

		if((autrePiece = piece.estConfondu(lstPiece)) != null)
			this.lstPiece.remove(autrePiece);

		this.lstEtat.add(this.copy(this.lstPiece));
		this.cptEtat++;

		if(this.lstPiece.size() == 1)
			this.niveauSuivant();
	}

	public boolean coordValide(int lig, int col)
	{
		return !(lig >= taillePlateau || col >= taillePlateau ||
                 lig <  0             || col <  0               );            
	}


	public String getDifficulte(){return this.difficulte;}

	public String getCoulDifficulte()
	{
		if(this.difficulte.equals("Débutant"     ))
			return "plateauvert"  ;

		if(this.difficulte.equals("Intermédiaire"))
			return "plateaubleu"  ;

		if(this.difficulte.equals("Avancé"       ))
			return "plateauviolet";

		return     "plateaurouge" ;
	}

	public List<Piece> getLstPiece() {return this.lstPiece;}

	public char getSymbole(int lig, int col)
	{
		if(lig * taillePlateau + col >= taillePlateau * taillePlateau)
			return ' ';
		
		for (Piece piece : lstPiece)
			if(piece.getLig() == lig && piece.getCol() == col)
				return piece.getSymbole();

		return ' ';
	}

	public List<Piece> copy(List<Piece> lstPiece)
	{
		List<Piece> lstRet = new ArrayList<Piece>();

		for (Piece piece : lstPiece)
		{
			Piece tmp = null;
			if(piece.getClass()	== Cavalier.class) tmp = new Cavalier(piece);
			if(piece.getClass()	== Fou.class     ) tmp = new Fou     (piece);
			if(piece.getClass()	== Pion.class    ) tmp = new Pion    (piece);
			if(piece.getClass()	== Reine.class   ) tmp = new Reine   (piece);
			if(piece.getClass()	== Roi.class     ) tmp = new Roi     (piece);
			if(piece.getClass()	== Tour.class    ) tmp = new Tour    (piece);
			
			lstRet.add(tmp);
		}

		return lstRet;
	}
	
}


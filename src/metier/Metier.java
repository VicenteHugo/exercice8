package metier;

import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import metier.piece.Cavalier;
import metier.piece.Fou;
import metier.piece.Piece;
import metier.piece.Pion;
import metier.piece.Reine;
import metier.piece.Roi;
import metier.piece.Tour;

public class Metier 
{
	private int         niveau;
	private String      difficulte;
	private List<Piece> lstPiece;
	private int         taillePlateau;
	
	public Metier()
	{
		this.niveau   = 1;
		this.lstPiece = new ArrayList<Piece>();

		this.chagerNiveau();
	}

	public void chagerNiveau()
	{
		try
		{
			Scanner sc = new Scanner(new FileReader("./donnees/niveaux/niveau" + this.niveau + ".txt"));
			
			this.difficulte = sc.nextLine();

			int cptL = 0;
			while(sc.hasNextLine())
			{
				sc.nextLine();
				if(sc.hasNextLine())
				{
					String   line           = sc.nextLine();
					String[] positionnement = line.split("|");
					
					for (int cptC = 2; cptC < positionnement.length; cptC+=4) 
					{
						Piece piece = null;	

						System.out.println(positionnement[cptC] + " " +cptC);
						
						switch (positionnement[cptC].charAt(0)) 
						{
							case 'P' -> piece = new Pion    (cptL, cptC);
							case 'C' -> piece = new Cavalier(cptL, cptC);
							case 'T' -> piece = new Tour    (cptL, cptC);
							case 'Q' -> piece = new Reine   (cptL, cptC, this);
							case 'K' -> piece = new Roi     (cptL, cptC);
							case 'F' -> piece = new Fou     (cptL, cptC, this);
						}
	
						if(piece != null)
							this.lstPiece.add(piece);
					}
					cptL++;
				}
			}

			this.taillePlateau = cptL;
		}
		catch(Exception e){e.printStackTrace();}
	}

	public void niveauSuivant()
	{
		this.niveau = this.niveau < 60 ? this.niveau++:this.niveau;
		this.chagerNiveau();
	}

	public void niveauPrecedent()
	{
		this.niveau = this.niveau > 0 ? this.niveau--:this.niveau;
		this.chagerNiveau();
	}

	public int getTaillePlateau() {return this.taillePlateau;}


	public void deplacer(Piece piece, int lig, int col)
	{
		if(lig >= taillePlateau || col >= taillePlateau ||
		   lig <  0             || col <  0               )
			return;

		if(piece.peutDeplacer(lig, col))
			piece.deplacer(lig, col);
	}

}


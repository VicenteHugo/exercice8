package metier;

import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import metier.piece.Cavalier;
import metier.piece.Piece;
import metier.piece.Pion;
import metier.piece.Tour;

public class Metier 
{
	private int         niveau;
	private String      difficulte;
	private List<Piece> lstPiece;
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
			Scanner sc = new Scanner(new FileReader("niveau" + this.niveau + ".txt"));
			
			this.difficulte = sc.nextLine();

			int cptL = 0;
			while(sc.hasNextLine())
			{
				sc.nextLine();
				String   line           = sc.nextLine();
				String[] positionnement = line.split("|");
				
				for (int cptC = 1; cptC < positionnement.length; cptC++) 
				{
					Piece piece = null;
					switch (positionnement[cptC].charAt(1)) 
					{
						case 'P' -> piece = new Pion    (cptL, cptC);
						case 'C' -> piece = new Cavalier(cptL, cptC);
						case 'T' -> piece = new Tour    (cptL, cptC);
						case 'Q' -> piece = new Reine   (cptL, cptC);
						case 'K' -> piece = new Roi     (cptL, cptC);
						case 'F' -> piece = new Fou     (cptL, cptC);
					}
					
					if(piece != null)
						this.lstPiece.add(piece);
				}
				cptL++;
			}
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
}

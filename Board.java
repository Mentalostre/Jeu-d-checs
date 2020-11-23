import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Board{
    private Piece[][] array;
    
    public Board(String fileName, Player white, Player black){
	int pieceType;
	int col;
	int row;
	String nextWord;
	Player owner;
	
	this.array = new Piece[8][8];
	try {
	    File file = new File(fileName);
    	    if(file.exists()==false) {
		System.err.println("Error: Cannot find file "+ fileName);
		System.exit(1);		
            } 

	    Scanner in = new Scanner(file);
	    while(in.hasNext()) {
		if ((nextWord = in.nextLine()).length()>2) {
		    pieceType = nextWord.charAt(0);
		    col = nextWord.charAt(1)-'0';
		    row = nextWord.charAt(2)-'0';
		    
		    owner = black;
		    if (pieceType >= 'a' && pieceType <= 'z')
    			owner = white;
		    switch(pieceType) {
		    case 'K' : case 'k' :  
    			{ this.addPiece(new King(col, row, owner)); break;}
		    }
	    	}	    	
	    }
	    in.close();
	}
	catch(FileNotFoundException e) {
	    System.err.println("Error file not found : "+e);
	    System.exit(1);	
	}
    }
    
    public List<Coordinates> getAllCoordinates(){
	return null;
    }
    
    public List<Piece> getPieces(Player player) {
	return null;
    }

    public List<Piece> getPieces() {

    	return null;
    }

    public void addPiece(Piece piece){
    }

    public Piece getPiece(Coordinates coordinates){
	return null;
    }

    public Piece getPiece(int x, int y){
	return null;
    }

    public void emptyCell(Coordinates coordinates){
    }
    
    public boolean isEmptyCell(Coordinates coordinates){
	return true;
    }
    
    public boolean isEmptyCell(int x, int y){
	return true;
    }
    
    public boolean sameColumnNothingBetween(Coordinates origin, Coordinates destination){
	return false;
    }
    
    public boolean sameRowNothingBetween(Coordinates origin, Coordinates destination){
	return false;
    }

    public boolean sameDiagonalNothingBetween(Coordinates origin, Coordinates destination){
	return false;
    }
}

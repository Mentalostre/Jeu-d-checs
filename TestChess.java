import java.io.*;
import java.util.Scanner;

public class TestChess{

	public static void main(String[] args) {
	    
	    boolean result;
	    /* Test de déplacements autorisés selon les regles de pièces */
	    System.out.println("authorized moves");
	    System.out.print("test 1 : ");
	    result = testAuthorizedMove("boardConfigurationFiles/FullBoard.txt", new Coordinates(0,1), new Coordinates(0,2));
	    if(result == true) System.out.println("pass"); else System.out.println("fail");
	    
	    System.out.print("test 2 : ");
	    result = testAuthorizedMove("boardConfigurationFiles/FullBoard.txt", new Coordinates(0,1), new Coordinates(0,4));
	    if(result == false) System.out.println("pass"); else System.out.println("fail");
	    
	    
	    /*  Test de déplacements jouables sur l'échiquier actuel, selon les regles du jeu */
	    System.out.println("playable moves");
	    System.out.print("test 1 : ");
	    result = testPlayableMove("boardConfigurationFiles/FullBoard.txt",new Coordinates(0,1),new Coordinates(0,2));
	    if(result == true) System.out.println("pass"); else System.out.println("fail");

	    System.out.print("test 2 : ");
	    result = testPlayableMove("boardConfigurationFiles/FullBoard.txt",new Coordinates(0,1),new Coordinates(0,3));
	    if(result == true) System.out.println("pass"); else System.out.println("fail");
	    
	    /*  Tests de la mise en echec */
	    
	    /*  Tests de la Echec et mat "isCheckMate()" */
	    
	    /*  Tests pours le calcul des points en fin de partie */
    }

    
    public static boolean testAuthorizedMove(String filename, Coordinates origin, Coordinates destination) {    			
	ChessUI ui = new ChessUI(false);
	Board testBoard = new Board(filename, new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));
	Piece testPiece = testBoard.getPiece(origin);
	if(testPiece == null) {
	    System.out.println("No Piece at :"+origin); 
	    return false;
	}
	return testPiece.isMoveAuthorized(testBoard, destination);
    }

	
    public static boolean testPlayableMove(String fileName, Coordinates origin, Coordinates destination) {    			
	ChessUI ui = new ChessUI(false);
	GameUI g = new GameUI(ui, fileName, new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));
	
	Piece testPiece = g.getBoard().getPiece(origin);
	if(testPiece == null) {
	    System.out.println("No Piece at :"+origin); 
	    return false;
	}
	return g.isMovePlayable(new Move(g.getBoard(), origin, destination));
    }

    public static boolean testIsCheck(String fileName, Player p) {    			
	ChessUI ui = new ChessUI(false);
	GameUI g = new GameUI(ui, fileName, new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));
	return g.isCheck(p);
    }

    public static boolean testIsCheckMate(String fileName, Player p) {    			
	ChessUI ui = new ChessUI(false);
	GameUI g = new GameUI(ui, fileName, new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));
	return g.isCheck(p) && g.isCheckMate(p);
    }

	
}


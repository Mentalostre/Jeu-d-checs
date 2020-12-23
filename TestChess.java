import java.util.Random;

public class TestChess{
	private final static String FullBoardTest = "boardConfigurationFiles/FullBoard.txt";
	private final static String KingTest = "boardConfigurationFiles/KingsOnly.txt";
	private final static String BishopTest = "boardConfigurationFiles/TestBishop.txt";
	private final static String RookTest = "boardConfigurationFiles/TestRook.txt";
	private final static String CheckmateTest = "boardConfigurationFiles/CheckmateTest.txt";
	private final static String CheckTest = "boardConfigurationFiles/CheckTest.txt";




	public static void main(String[] args) {
	    
	    boolean resultTest;


	    /* Test de déplacements autorisés selon les regles de pièces */
	    System.out.println("authorized moves");
	    System.out.print("test 1 : ");
	    resultTest = testAuthorizedMove(FullBoardTest, new Coordinates(0,1), new Coordinates(0,2));
	    if(resultTest == true) System.out.println("pass"); else System.out.println("fail");
	    
	    System.out.print("test 2 : ");
	    resultTest = testAuthorizedMove(FullBoardTest, new Coordinates(0,1), new Coordinates(0,4));
	    if(resultTest == false) System.out.println("pass"); else System.out.println("fail");



		/*  Test de déplacements jouables sur l'échiquier actuel, selon les regles du jeu, on ne teste pas les mouvements
		de la reine puisqu'on teste déjà le fou et la tour*/
	    System.out.println("playable moves");

	    /* Test déplacement pion*/
		System.out.println("Pion noir :");
		System.out.print("Test 1 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(0,1),new Coordinates(1,2));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(FullBoardTest,new Coordinates(0,1),new Coordinates(0,2));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = testAuthorizedMove(FullBoardTest,new Coordinates(0,1),new Coordinates(0,3));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(0,1),new Coordinates(0,4));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 5 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(0,1),new Coordinates(0,0));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");


		System.out.println("Pion blanc :");
		System.out.print("Test 1 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(0,6),new Coordinates(1,5));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(FullBoardTest,new Coordinates(0,6),new Coordinates(0,5));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = testAuthorizedMove(FullBoardTest,new Coordinates(0,6),new Coordinates(0,4));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(0,6),new Coordinates(0,3));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 5 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(0,6),new Coordinates(0,7));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");



		/* Test déplacement tour*/
		System.out.println("Tour noire :");
		System.out.print("Test 1 : ");
		resultTest = testAuthorizedMove(RookTest,new Coordinates(0,0),new Coordinates(0,7));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(RookTest,new Coordinates(0,0),new Coordinates(0,5));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = !testAuthorizedMove(RookTest,new Coordinates(0,0),new Coordinates(7,0));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(RookTest,new Coordinates(0,0),new Coordinates(1,2));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");



		System.out.println("Tour blanche :");
		System.out.print("Test 1 : ");
		resultTest = testAuthorizedMove(RookTest,new Coordinates(0,7),new Coordinates(0,0));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(RookTest,new Coordinates(0,7),new Coordinates(0,5));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = testAuthorizedMove(RookTest,new Coordinates(0,7),new Coordinates(0,0));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(RookTest,new Coordinates(0,7),new Coordinates(1,5));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");



		/* Test déplacement Cavalier*/
		System.out.println("Cavalier noir :");
		System.out.print("Test 1 : ");
		resultTest = testAuthorizedMove(FullBoardTest,new Coordinates(1,0),new Coordinates(0,2));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(FullBoardTest,new Coordinates(1,0),new Coordinates(2,2));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(1,0),new Coordinates(1,1));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(1,0),new Coordinates(2,0));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 5 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(1,0),new Coordinates(2,1));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");


		System.out.println("Cavalier blanc :");
		System.out.print("Test 1 : ");
		resultTest = testAuthorizedMove(FullBoardTest,new Coordinates(1,7),new Coordinates(0,5));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(FullBoardTest,new Coordinates(1,7),new Coordinates(2,5));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(1,7),new Coordinates(2,7));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(1,7),new Coordinates(2,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 5 : ");
		resultTest = !testAuthorizedMove(FullBoardTest,new Coordinates(1,7),new Coordinates(1,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");



		/* Test déplacement fou*/
		System.out.println("Fou noir :");
		System.out.print("Test 1 : ");
		resultTest = testAuthorizedMove(BishopTest,new Coordinates(2,0),new Coordinates(1,1));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(BishopTest,new Coordinates(2,0),new Coordinates(3,1));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = testAuthorizedMove(BishopTest,new Coordinates(2,0),new Coordinates(7,5));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(BishopTest,new Coordinates(2,0),new Coordinates(3,0));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 5 : ");
		resultTest = !testAuthorizedMove(BishopTest,new Coordinates(2,0),new Coordinates(2,1));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");


		System.out.println("Fou blanc :");
		System.out.print("Test 1 : ");
		resultTest = testAuthorizedMove(BishopTest,new Coordinates(2,7),new Coordinates(1,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(BishopTest,new Coordinates(2,7),new Coordinates(3,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = testAuthorizedMove(BishopTest,new Coordinates(2,7),new Coordinates(7,2));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(BishopTest,new Coordinates(2,7),new Coordinates(3,7));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 5 : ");
		resultTest = !testAuthorizedMove(BishopTest,new Coordinates(2,7),new Coordinates(2,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");


		/* Test déplacement Roi*/
		System.out.println("Roi noir :");
		System.out.print("Test 1 : ");
		resultTest = testAuthorizedMove(KingTest,new Coordinates(4,0),new Coordinates(3,0));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(KingTest,new Coordinates(4,0),new Coordinates(3,1));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = testAuthorizedMove(KingTest,new Coordinates(4,0),new Coordinates(4,1));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(KingTest,new Coordinates(4,0),new Coordinates(0,4));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 5 : ");
		resultTest = !testAuthorizedMove(KingTest,new Coordinates(4,0),new Coordinates(4,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");


		System.out.println("Roi blanc :");
		System.out.print("Test 1 : ");
		resultTest = testAuthorizedMove(KingTest,new Coordinates(4,7),new Coordinates(3,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 2 : ");
		resultTest = testAuthorizedMove(KingTest,new Coordinates(4,7),new Coordinates(5,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 3 : ");
		resultTest = testAuthorizedMove(KingTest,new Coordinates(4,7),new Coordinates(4,6));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 4 : ");
		resultTest = !testAuthorizedMove(KingTest,new Coordinates(4,7),new Coordinates(7,4));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");

		System.out.print("Test 5 : ");
		resultTest = !testAuthorizedMove(KingTest,new Coordinates(4,7),new Coordinates(4,0));
		if(resultTest == true) System.out.println("pass"); else System.out.println("fail");


		/*  Tests de la mise en échec */
		System.out.println("Test échec:");
		System.out.print("Test 1 : ");
		resultTest = testIsCheck(CheckTest, new Coordinates(4,5), new Coordinates(4,4));
		if(!resultTest == true) System.out.println("pass"); else System.out.println("fail");


	    
	    /*  Tests de la Echec et mat "isCheckMate()" */
		System.out.println("Test échec et mat:");
		System.out.print("Test 1 : ");
		//Move checkemateMove = new Move()
		resultTest = testIsCheckMate(CheckmateTest);
		if(!resultTest == true) System.out.println("pass"); else System.out.println("fail");


		/*  Tests pours le calcul des points en fin de partie */
    }

    
    public static boolean testAuthorizedMove(String filename, Coordinates origin, Coordinates destination) {    			
	ChessUI ui = new ChessUI();
	Board testBoard = new Board(filename, new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));
	Piece testPiece = testBoard.getPiece(origin);
	if(testPiece == null) {
	    System.out.println("No Piece at :"+origin); 
	    return false;
	}
	return testPiece.isMoveAuthorized(testBoard, destination);
    }

	
    public static boolean testPlayableMove(String fileName, Coordinates origin, Coordinates destination) {    			
	ChessUI ui = new ChessUI();
	GameUI g = new GameUI(ui, fileName, new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));
	
	Piece testPiece = g.getBoard().getPiece(origin);
	if(testPiece == null) {
	    System.out.println("No Piece at :"+origin); 
	    return false;
	}
	return g.isMovePlayable(new Move(g.getBoard(), origin, destination));
    }

    public static boolean testIsCheck(String fileName, Coordinates origin, Coordinates destination) {
	ChessUI ui = new ChessUI();
	Human whitePlayer = new Human(ui, ChessColor.BLACK);
	GameUI g = new GameUI(ui, fileName, new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));
	g.setCurrentPlayer(whitePlayer);
	g.applyMove(new Move(g.getBoard(), origin, destination));
	return g.isCheck(g.getOpponent(whitePlayer));
    }

    public static boolean testIsCheckMate(String fileName) {
	ChessUI ui = new ChessUI();
	Human whitePlayer = new Human(ui, ChessColor.WHITE);
	GameUI g = new GameUI(ui, fileName, new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));
	g.setCurrentPlayer(whitePlayer);
	return g.isCheck(g.getOpponent(whitePlayer)) && g.isCheckMate(g.getOpponent(whitePlayer));
    }



	
}


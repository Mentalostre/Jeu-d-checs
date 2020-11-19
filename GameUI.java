import java.util.Stack;

public class GameUI {
    public Board board;
    private Player white;
    private Player black;
    private Player currentPlayer;
    private ChessUI ui;
    private Stack<Move> history;
    
    public GameUI(ChessUI ui, String boardConfigFileName, Player white, Player black){
        this.board = new Board(boardConfigFileName, white, black);
        this.white = white;
        this.black = black;
        this.currentPlayer = white;
        this.ui = ui;
	this.history = new Stack<Move>();

        for(Piece p : board.getPieces())
            this.ui.placePiece(p.getType(), p.getColor(), p.getPosition());
    }

    public Board getBoard(){
	return null;
    }
    public boolean undo(){
	return true;
    }
    
    public boolean isMovePlayable(Move gameMove){
	return true;
    }
    
    public void applyMove(Move move){
    }

    public void switchPlayers(){
    }

    public Player getOpponent(Player player){
	return null;
    }

    public boolean isPrey(Piece prey){
	return false;
    }

    public boolean isCheck(Player player){
	return false;
    }

    public boolean isCheckMate(Player player){
	return false;
    }
    
    public void determineWinner(){
    }
    
    public void play(){
    }
}

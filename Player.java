import java.util.ArrayList;
import java.util.List;

public abstract class Player{
    protected ChessColor color;
    private int score;
    private King king;
    public boolean isCheck;
    public boolean isCheckMate;

    public Player(ChessColor color){
        this.color = color;
    }

    public ChessColor getColor(){
	    return color;
    }

    public int getScore(){
	    return score;
    }

    public void addToScore(int value){
        score += value;
    }
    
    public void removeFromScore(int value){
        score -= value;
    }
    
    public abstract FromTo getFromTo(GameUI gameUI);

    public Piece getKing(){
	    return king;
    }
    
    public void setKing(King king){
        this.king = king;
    }
    
    public boolean isCheckMate(Board board){
	    return isCheckMate;
    }

    public void setCheck(){
        isCheck = true;
    }

    public void unSetCheck(){
        isCheck = false;
    }
    
    public List<Move> getAllMoves(Board board) {
        List<Move> allMove = new ArrayList<Move>();
        for (Piece piece : board.getPieces(this)) {
            for (Move move : piece.getAllMoves(board)){
                allMove.add(move);
            }
        }
        return allMove;
    }


    @Override
    public String toString(){
        return String.valueOf(color);
    }
}

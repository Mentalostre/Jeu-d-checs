import java.util.ArrayList;
import java.util.List;

public abstract class Player{
    protected ChessColor color;
    private int score;
    private King king;
    public boolean isCheck;
    public boolean isCheckMate;

    public Player(ChessColor color){
    }

    public ChessColor getColor(){
	return null;
    }

    public int getScore(){
	return 0;
    }

    public void addToScore(int value){
    }
    
    public void removeFromScore(int value){
    }
    
    public abstract FromTo getFromTo();

    public Piece getKing(){
	return null;
    }
    
    public void setKing(King king){
    }
    
    public boolean isCheckMate(Board board){
	return false;
    }

    public void setCheck(){
    }

    public void unSetCheck(){
    }
    
    public List<Move> getAllMoves(Board board) {
	return null;
    }

    @Override
    public String toString(){
	return null;
    }
}

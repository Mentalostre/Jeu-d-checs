import java.util.ArrayList;
import java.util.List;

public abstract class Piece{
    protected Coordinates position;
    protected Player owner;
    
    public Piece(int x, int y, Player owner){
    }

    public enum Type {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    public void setPosition(Coordinates destination){
    }
    
    public Player getOwner(){
	return null;
    }

    public ChessColor getColor(){
	return null;
    }

    public Coordinates getPosition(){
	return null;
    }

    public int getX(){
	return 0;
    }
    
    public int getY(){
	return 0;
    }

    public List<Move> getAllMoves(Board board) {
	return null;
    }

    public boolean sameColor(Piece piece){
	return true;
    }

    public abstract boolean isMoveAuthorized(Board board, Coordinates destination);

    public abstract Type getType();
    public abstract int getValue();
    

}

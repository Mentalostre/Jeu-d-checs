import java.util.ArrayList;
import java.util.List;

public abstract class Piece{
    protected Coordinates position;
    protected Player owner;

    public Piece(int x, int y, Player owner){
        this.position = new Coordinates(x, y);
        this.owner = owner;
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
        position = destination;
    }

    public Player getOwner(){
        return owner;
    }

    public ChessColor getColor(){
        return owner.getColor();
    }

    public Coordinates getPosition(){
        return position;
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }

    public List<Move> getAllMoves(Board board) {
        List<Move> allMoves = new ArrayList<>();
        for (Coordinates coordinate : board.getAllCoordinates())
            if(this.isMoveAuthorized(board, coordinate))
                allMoves.add(new Move(board, position, coordinate));
        return allMoves;
    }

    public boolean sameColor(Piece piece){
        return this.getColor() == piece.getColor();
    }

    public abstract boolean isMoveAuthorized(Board board, Coordinates destination);

    public abstract Type getType();
    public abstract int getValue();

    public String toString() {
        return owner.toString() + " " + getType();
    }


}
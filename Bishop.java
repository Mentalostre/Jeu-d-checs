public class Bishop extends Piece {

    public Bishop(int x, int y, Player owner){
        super(x, y, owner);
    }

    @Override
    public boolean isMoveAuthorized(Board board, Coordinates destination) {
        if(getPosition() == destination) return false;
        if(!board.isEmptyCell(destination) && board.getPiece(destination).sameColor(this))
            return false;
        return board.sameDiagonalNothingBetween(getPosition(),destination);
    }

    @Override
    public Type getType() {
        return Type.BISHOP;
    }

    @Override
    public int getValue() {
        return 3;
    }
}

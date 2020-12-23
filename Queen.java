public class Queen extends Piece {
    public Queen(int x, int y, Player owner){
        super(x, y, owner);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        if (board.sameDiagonalNothingBetween(this.getPosition(), destination) ||
                board.sameRowNothingBetween(this.getPosition(), destination) ||
                board.sameColumnNothingBetween(this.getPosition(), destination))
            if (board.getPiece(destination) == null)
                return true;
            else if (!board.getPiece(destination).getOwner().equals(this.owner))
                return true;
        return false;
    }


    @Override
    public Type getType() {
        return Type.QUEEN;
    }

    @Override
    public int getValue() {
        return 9;
    }

}



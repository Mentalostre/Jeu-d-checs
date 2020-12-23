public class Pawn extends Piece {

    public Pawn(int x, int y, Player owner){
        super(x, y, owner);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination) {
        int dx = destination.getX();
        int dy = destination.getY();
        int ox = this.getX();
        int oy = this.getY();

        if (this.owner.getColor() == ChessColor.WHITE) {
            if (oy == 1 && dx - ox == 0 && (oy - dy == -1 || oy - dy == -2) && board.getPiece(destination) == null && board.sameColumnNothingBetween(getPosition(),destination))
                return true;
            if (oy > 1 && dx - ox == 0 && oy - dy == -1 && board.getPiece(destination) == null)
                return true;
            if(board.getPiece(destination) != null)
                return (oy - dy == -1 && Math.abs(dx - ox) == 1 && board.getPiece(dx, dy).owner.getColor() == ChessColor.BLACK);
        }

        if (this.owner.getColor() == ChessColor.BLACK) {
            if (oy == 6 && dx - ox == 0 && (oy - dy == 1 || oy - dy == 2) && board.getPiece(destination) == null && board.sameColumnNothingBetween(getPosition(),destination))
                return true;
            if (oy < 6 && dx - ox == 0 && oy - dy == 1 && board.getPiece(destination) == null)
                return true;
            if(board.getPiece(destination) != null)
                return oy - dy == 1 && Math.abs(dx - ox) == 1 && board.getPiece(dx, dy).owner.getColor() == ChessColor.WHITE;
        }
        return false;
    }

    @Override
    public Type getType() {
        return Type.PAWN;
    }

    @Override
    public int getValue() {
        return 1;
    }
}
public class Knight extends Piece{
    public Knight(int x, int y, Player owner){
        super(x, y, owner);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        int dx = destination.getX();
        int dy = destination.getY();
        int ox = this.getX();
        int oy = this.getY();
        if ((Math.abs(dx - ox) == 2 && Math.abs(dy - oy) == 1) || (Math.abs(dy - oy) == 2 && Math.abs(dx - ox) == 1))
            if (board.getPiece(destination) == null)
                return true;
            else if (!board.getPiece(destination).getOwner().equals(this.owner))
                return true;
        return false;
    }

    @Override
    public Type getType() {
        return Type.KNIGHT;
    }

    @Override
    public int getValue() {
        return 3;
    }

}

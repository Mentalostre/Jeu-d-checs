public class King extends Piece {

    public King(int x, int y, Player owner){
		super(x, y, owner);
		owner.setKing(this);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        if (!board.contains(destination) ||
                getPosition() == destination || !
                board.isEmptyCell(destination) && board.getPiece(destination).sameColor(this))
            return false;
        int dx = Math.abs(getPosition().getX() - destination.getX()), dy = Math.abs(getPosition().getY() - destination.getY());
        return dx <= 1 && dy <=1;
    }


    @Override
    public Type getType() {
	return Type.KING;
    }

    @Override
    public int getValue() {
	return 0;
    }
    
}

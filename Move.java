public class Move{
	final Coordinates origin;
	final Coordinates destination;
	final Piece pieceAtOrigin;
	final Piece pieceAtDestination;

	public Move(Board board, Coordinates origin, Coordinates destination) {
		this.origin = origin;
		this.destination = destination;
		this.pieceAtOrigin = board.getPiece(origin);
		this.pieceAtDestination = board.getPiece(destination);
	}

	public Move(Coordinates origin, Coordinates destination, Piece pieceAtOrigin, Piece pieceAtDestination) {
		this.origin = origin;
		this.destination = destination;
		this.pieceAtOrigin = pieceAtOrigin;
		this.pieceAtDestination = pieceAtDestination;
	}

	public Move(Board board, FromTo ft) {
		this.origin = ft.getFrom();
		this.destination = ft.getTo();
		this.pieceAtOrigin = board.getPiece(ft.getFrom());
		this.pieceAtDestination = board.getPiece(ft.getTo());
	}


	public String toString() {
		String str = pieceAtOrigin + " " + "moved from " + origin + " to " + destination ;
		if (pieceAtDestination != null)
			str += " (" + pieceAtDestination + " captured" + ")";
		return  str.toLowerCase() + "\n-----------";
	}
}


    

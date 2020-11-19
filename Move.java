public class Move{
    final Coordinates origin;
    final Coordinates destination;
    final Piece pieceAtOrigin;
    final Piece pieceAtDestination;
    
    public Move(Board board, Coordinates origin, Coordinates destination){
	this.origin = null;
	this.destination = null;
	this.pieceAtOrigin = null;
	this.pieceAtDestination = null;
    }
    
    public Move(Coordinates origin, Coordinates destination, Piece pieceAtOrigin, Piece pieceAtDestination){
	this.origin = null;
	this.destination = null;
	this.pieceAtOrigin = null;
	this.pieceAtDestination = null;
    }
    
    public Move(Board board, FromTo ft){
	this.origin = null;
	this.destination = null;
	this.pieceAtOrigin = null;
	this.pieceAtDestination = null;
    }
}
    

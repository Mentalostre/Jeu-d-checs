import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Board{
    private Piece[][] array;

    public Board(String fileName, Player white, Player black) {
		int pieceType;
		int col;
		int row;
		String nextWord;
		Player owner;

		this.array = new Piece[8][8];
		try {
			File file = new File(fileName);
			if (file.exists() == false) {
				System.err.println("Error: Cannot find file " + fileName);
				System.exit(1);
			}

			Scanner in = new Scanner(file);
			while (in.hasNext()) {
				if ((nextWord = in.nextLine()).length() > 2) {
					pieceType = nextWord.charAt(0);
					col = nextWord.charAt(1) - '0';
					row = nextWord.charAt(2) - '0';

					owner = black;
					if (pieceType >= 'a' && pieceType <= 'z')
						owner = white;
					switch (pieceType) {
						case 'K':
						case 'k':
							this.addPiece(new King(col, row, owner));
							break;

						case 'Q':
						case 'q':
							this.addPiece(new Queen(col, row, owner));
							break;

						case 'N':
						case 'n':
							this.addPiece(new Knight(col, row, owner));
							break;

						case 'B':
						case 'b':
							this.addPiece(new Bishop(col, row, owner));
							break;

						case 'R':
						case 'r':
							this.addPiece(new Rook(col, row, owner));
							break;

						case 'P':
						case 'p':
							this.addPiece(new Pawn(col, row, owner));
							break;

					}
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error file not found : " + e);
			System.exit(1);
		}
	}

    public List<Coordinates> getAllCoordinates(){
		List<Coordinates> allCoordinates = new ArrayList<Coordinates>();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				allCoordinates.add(new Coordinates(j, i));
			}
		}
		return allCoordinates;
	}


    public List<Piece> getPieces(Player player) {
		List<Piece> allPieces = this.getPieces();
		List<Piece> playerPieces = new ArrayList<Piece>();
		for (Piece piece : allPieces){
			if (piece.getOwner().equals(player))
				playerPieces.add(piece);
		}
		return playerPieces;
	}


    public List<Piece> getPieces() {
		List<Piece> allPieces = new ArrayList<>();
		for (Piece[] pieces : array) {
			for (Piece piece : pieces){
				if (piece!=null)
					allPieces.add(piece);
			}
		}
		return allPieces;
	}


    public void addPiece(Piece piece){
    	this.array[piece.getY()][piece.getX()] = piece;
    }

    public Piece getPiece(Coordinates coordinates){
		return this.array[coordinates.getY()][coordinates.getX()];
    }

    public Piece getPiece(int x, int y){
		return array[y][x];
    }

    public void emptyCell(Coordinates coordinates){
    	if(this.array[coordinates.getY()][coordinates.getX()] != null)
    		this.array[coordinates.getY()][coordinates.getX()] = null;
    }

    public boolean isEmptyCell(Coordinates coordinates){
    	if (this.array[coordinates.getY()][coordinates.getX()] != null)
			return false;

    	else
    		return true;
    }

	public boolean isEmptyCell(int x, int y){
		return getPiece(x, y) == null;
	}


	public boolean sameColumnNothingBetween(Coordinates origin, Coordinates destination){
		if (origin.equals(destination)) return false;

		// check same column
		if (origin.getX() != destination.getX()) return false;

		// check between
		if (origin.getY() - destination.getY() < -1) {
			for (int i = origin.getY() + 1; i < destination.getY(); i++) {
				if (array[i][origin.getX()] != null) {
					return false;
				}
			}
		}
		if (origin.getY() - destination.getY() > 1) {
			for (int i = destination.getY() + 1; i < origin.getY(); i++) {
				if (array[i][origin.getX()] != null) {
					return false;
				}
			}
		}
		return true;

	}


	public boolean sameRowNothingBetween(Coordinates origin, Coordinates destination){
		if (origin.equals(destination) || origin.getY() != destination.getY())
			return false;

		if (origin.getX() - destination.getX() < -1) {
			for (int i = origin.getX() + 1; i < destination.getX(); i++) {
				if (array[origin.getY()][i] != null) {
					return false;
				}
			}
		}

		if (origin.getX() - destination.getX() > 1) {
			for (int i = destination.getX() + 1; i < origin.getX(); i++) {
				if (array[origin.getY()][i] != null) {
					return false;
				}
			}
		}
		return true;

	}

	public boolean sameDiagonalNothingBetween(Coordinates origin, Coordinates destination){
		if (origin.equals(destination)) return false;

		int ox = origin.getX();
		int oy = origin.getY();
		int dx = destination.getX();
		int dy = destination.getY();

		// check same diagonal
		if (Math.abs(ox - dx) != Math.abs(oy - dy)) return false;

		// check between
		if (ox - dx > 1 && oy - dy > 1) {
			for (int i = 1; i < Math.abs(ox - dx); i++) {
				if(!isEmptyCell(ox - i, oy - i)) return false;
			}
		}
		if (ox - dx < -1 && oy - dy > 1) {
			for (int i = 1; i < Math.abs(ox - dx); i++) {
				if (!isEmptyCell(ox + i, oy - i)) return false;
			}
		}
		if (ox - dx < -1 && oy - dy < -1) {
			for (int i = 1; i < Math.abs(ox - dx); i++) {
				if (!isEmptyCell(ox + i, oy + i)) return false;
			}
		}
		if (ox - dx > 1 && oy - dy < -1) {
			for (int i = 1; i < Math.abs(ox - dx); i++) {
				if (!isEmptyCell(ox - i, oy + i)) return false;
			}
		}
		return true;
	}


	public boolean contains(Coordinates coordinates) {
		return coordinates.getX() >= 0 && coordinates.getX() < 8 && coordinates.getY() >=0 && coordinates.getY() < 8;
	}

}


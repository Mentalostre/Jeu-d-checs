import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameUI {
    public Board board;
    private Player white;
    private Player black;
    private Player currentPlayer;
    private ChessUI ui;
    private Stack<Move> history;

    public GameUI(ChessUI ui, String boardConfigFileName, Player white, Player black) {
        this.board = new Board(boardConfigFileName, white, black);
        this.white = white;
        this.black = black;
        this.currentPlayer = white;
        this.ui = ui;
        this.history = new Stack<Move>();

        for (Piece p : board.getPieces())
            this.ui.placePiece(p.getType(), p.getColor(), p.getPosition());
    }

    public Board getBoard() {
        return board;
    }

    public boolean undo() {
        if(this.history.empty()) return false;
        Move move = this.history.pop();
        board.emptyCell(move.destination);
        ui.removePiece(move.destination);
        if(move.pieceAtDestination != null){
            move.pieceAtDestination.setPosition(move.destination);
            board.addPiece(move.pieceAtDestination);
            ui.placePiece(move.pieceAtDestination.getType(), move.pieceAtDestination.getColor(), move.pieceAtDestination.getPosition());
        }
        board.emptyCell(move.origin);
        ui.removePiece(move.origin);
        move.pieceAtOrigin.setPosition(move.origin);
        board.addPiece(move.pieceAtOrigin);
        ui.placePiece(move.pieceAtOrigin.getType(), move.pieceAtOrigin.getColor(), move.pieceAtOrigin.getPosition());

        currentPlayer = move.pieceAtOrigin.getOwner();
        if(move.pieceAtDestination != null)
            currentPlayer.removeFromScore(move.pieceAtDestination.getValue());
        return true;


    }


    public boolean isMovePlayable(Move gameMove) {
        if (board.getPiece(gameMove.origin) == null) return false;
        if (gameMove.pieceAtOrigin.getOwner() != currentPlayer) return false;
        if (gameMove.pieceAtDestination != null) if(gameMove.pieceAtDestination.sameColor(gameMove.pieceAtOrigin)) return false;
        if (!gameMove.pieceAtOrigin.isMoveAuthorized(board, gameMove.destination)) return false;

        applyMove(gameMove);
        if (isCheck(currentPlayer)) {
            undo();
            return false;
        }
        undo();

        return true;
    }


    public void applyMove(Move move) {
        history.push(move);
        if (move.pieceAtDestination != null){
            currentPlayer.addToScore(board.getPiece(move.destination).getValue());
            board.emptyCell(move.destination);
        }
        move.pieceAtOrigin.setPosition(move.destination);
        board.addPiece(move.pieceAtOrigin);
        board.emptyCell(move.origin);
    }




    public void switchPlayers() {
        currentPlayer = getOpponent(currentPlayer);
    }

    public Player getOpponent(Player player){
        return player.equals(white) ? black : white;
    }


    public boolean isPrey(Piece prey) {
        List<Move> opponentMoves = getOpponent(prey.getOwner()).getAllMoves(board);
        for (Move move : opponentMoves) {
            if (prey.equals(move.pieceAtDestination))
                return true;
        }
        return false;
    }


    public boolean isCheck(Player player) {
        return isPrey(player.getKing());
    }

    public boolean isCheckMate(Player player) {
        List<Move> currentPlayerPlayableMove = new ArrayList<Move>();
        for (Move move : player.getAllMoves(board)) {
            if (isMovePlayable(move))
                currentPlayerPlayableMove.add(move);
        }
        if (currentPlayerPlayableMove.isEmpty())
            player.isCheckMate = true;

        return player.isCheckMate;
    }


    public void determineWinner() {
        if (white.isCheckMate || black.isCheckMate) {
            System.out.println(getOpponent(currentPlayer).toString() + " a gagné !");
        }

        if (white.getScore() == black.getScore()) {
            System.out.println("Pat !");
        }

    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public void play(){
        int whiteCount = 0;
        int blackCount = 0;

        while(whiteCount < 50 || blackCount < 50){
            FromTo move = currentPlayer.getFromTo(this);
            while(!isMovePlayable(new Move(board, move))){
                move = currentPlayer.getFromTo(this);
            }
            applyMove(new Move(board, move));
            ui.placePiece(board.getPiece(move.getTo()).getType(), board.getPiece(move.getTo()).owner.getColor(), move.getTo());
            ui.removePiece(move.getFrom());
            if (currentPlayer.equals(white))
                whiteCount++;
            else
                blackCount++;

            System.out.println(getOpponent(currentPlayer).toString().toLowerCase() + " to play");
            System.out.println("Score : White " + white.getScore() + " - " + "Black " + black.getScore());
            System.out.println("White moves : " + whiteCount);
            System.out.println("Black moves : " + blackCount);

            switchPlayers();

            if (isCheck(currentPlayer))
                System.out.println(currentPlayer + " is check.");

            if (isCheckMate(currentPlayer)){
                System.out.println("Checkmate, gg " + getOpponent(currentPlayer));
                break;
            }
        }
        determineWinner();
    }

}

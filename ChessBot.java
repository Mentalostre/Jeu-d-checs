import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessBot extends Player {
    ChessUI ui;


    public ChessBot(ChessUI ui, ChessColor color){
        super(color);
        this.ui = ui;
    }

    public FromTo getFromTo() {
        return null;
    }

    @Override
    public FromTo getFromTo(GameUI gameUI) {
        Board board = gameUI.getBoard();
        List<Move> allPlayableMove = new ArrayList<>();
        for (Piece piece : board.getPieces(this)) {
            for (Move move : piece.getAllMoves(board)) {
                if (gameUI.isMovePlayable(move))
                    allPlayableMove.add(move);
            }
        }


        FromTo m;
        Random random = new Random();
        Move randomMove = allPlayableMove.get(random.nextInt(allPlayableMove.size()));
        m = new FromTo(randomMove.origin.getX(), randomMove.origin.getY(), randomMove.destination.getX(), randomMove.destination.getY());
        int highestScore = 0;
        if (randomMove.pieceAtDestination != null)
            highestScore += randomMove.pieceAtDestination.getValue();
        if (gameUI.isPrey(board.getPiece(randomMove.origin)))
            highestScore += randomMove.pieceAtOrigin.getValue();
        gameUI.applyMove(randomMove);
        int pieceValue = 0;
        for(Piece piece : board.getPieces(this)){
            if (gameUI.isPrey(piece))
                pieceValue = piece.getValue();
        }
        highestScore -= pieceValue;
        if (gameUI.isCheck(gameUI.getOpponent(this)))
            highestScore += 2;

        gameUI.undo();

        for (Move move : allPlayableMove){
            int score = 0;
            if (move.pieceAtDestination != null)
                score += move.pieceAtDestination.getValue();
            if (gameUI.isPrey(board.getPiece(move.origin)))
                score += move.pieceAtOrigin.getValue();
            gameUI.applyMove(move);
            pieceValue = 0;
            for(Piece piece : board.getPieces(this)){
                if (gameUI.isPrey(piece))
                    pieceValue = piece.getValue();
            }
            score -= pieceValue;
            if (gameUI.isCheck(gameUI.getOpponent(this)))
                score += 2;

            if (score > highestScore) {
                highestScore = score;
                m = new FromTo(move.origin.getX(), move.origin.getY(), move.destination.getX(), move.destination.getY());
            }
            gameUI.undo();
        }


        return m;
    }
}

import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ChessUI extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    int cellSize;

    private ImageIcon[] chessIcons;
    private JLabel[] pieceLabels;

    JLabel movingChessPiece;
    int originXMove;
    int originYMove;
    int destXMove;
    int destYMove;

    boolean moveFreshness;

    int xAdjustment;
    int yAdjustment;


    public ChessUI(){
        this.cellSize = 60;
        Dimension boardSize = new Dimension(8 * cellSize, 8 * cellSize);

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            square.setBackground( (i + i / 8) % 2 == 1 ? Color.GRAY : Color.WHITE );
        }

        initIcons();
        initPieceLabels();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();
        setResizable(true);
        setLocationRelativeTo( null );
        setVisible(true);
    }

    /** ---------------------------------------------------------------------------------
     * Chess piece graphics **/

    private void initIcons() {
        chessIcons = new ImageIcon[] {
                new ImageIcon("./img/Chess_kdt60.png"),
                new ImageIcon("./img/Chess_qdt60.png"),
                new ImageIcon("./img/Chess_rdt60.png"),
                new ImageIcon("./img/Chess_bdt60.png"),
                new ImageIcon("./img/Chess_ndt60.png"),
                new ImageIcon("./img/Chess_pdt60.png"),
                new ImageIcon("./img/Chess_klt60.png"),
                new ImageIcon("./img/Chess_qlt60.png"),
                new ImageIcon("./img/Chess_rlt60.png"),
                new ImageIcon("./img/Chess_blt60.png"),
                new ImageIcon("./img/Chess_nlt60.png"),
                new ImageIcon("./img/Chess_plt60.png")
        };
    }

    private void initPieceLabels() {
        pieceLabels = new JLabel[64];

        for(int k = 0; k < 64; k++) {
            JLabel label = new JLabel();
            label.setVisible(false);
            label.setSize(cellSize, cellSize);
            pieceLabels[k] = label;
            JPanel panel = (JPanel)chessBoard.getComponent(k);
            panel.add(label);
        }
    }

    private JLabel getPieceLabel(int row, int column) {
        return pieceLabels[row * 8 + column];
    }

    private ImageIcon getImageIcon(Piece.Type type, ChessColor color) {
        return chessIcons[type.ordinal() + (color == ChessColor.WHITE ? Piece.Type.values().length : 0)];
    }

    /**
     * This method takes a piece of a color and places it in the specified coordinates.
     * @param type Desired type
     * @param color Desired color (should only be either Color.white or Color.black)
     * @param coord Coordinates of the piece
     */
    public void placePiece(Piece.Type type, ChessColor color, Coordinates coord) {
        getPieceLabel(coord.getY(), coord.getX()).setIcon(getImageIcon(type, color));
        getPieceLabel(coord.getY(), coord.getX()).setVisible(true);
    }

    /**
     * This method removes the piece at the specified position, if there is any.
     * @param coord Coordinates of the piece
     */
    public void removePiece(Coordinates coord) {
        getPieceLabel(coord.getY(), coord.getX()).setVisible(false);
    }

    /** ---------------------------------------------------------------------------------
     * Events management **/

    /**
     * This method waits for the player to enter a move in the UI. Calling this is blocking.
     */
    public FromTo waitForPlayerMove() {
        moveFreshness = false;

        while(! moveFreshness)
            try {
                synchronized (this.getClass()) {
                    this.getClass().wait();
                }
            }
            catch (Exception e) {
                System.err.println("ERROR : interrupted while waiting for chess move");
                System.err.println(e);
                System.exit(-1);
            }

        return new FromTo(originXMove, originYMove, destXMove, destYMove);
    }

    public void mousePressed(MouseEvent e){
        movingChessPiece = null;

        originXMove = e.getX() / cellSize;
        originYMove = e.getY() / cellSize;

        xAdjustment = - e.getX() + originXMove * cellSize;
        yAdjustment = - e.getY() + originYMove * cellSize;

        movingChessPiece = new JLabel(getPieceLabel(originYMove, originXMove).getIcon());
        movingChessPiece.setVisible(getPieceLabel(originYMove, originXMove).isVisible());

        movingChessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        if(movingChessPiece.getIcon() != null)
            movingChessPiece.setSize(movingChessPiece.getIcon().getIconWidth(), movingChessPiece.getIcon().getIconHeight());
        layeredPane.add(movingChessPiece, JLayeredPane.DRAG_LAYER);
    }

    //Move the chess piece around

    public void mouseDragged(MouseEvent me) {
        if (movingChessPiece == null) return;
        movingChessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board

    public void mouseReleased(MouseEvent e) {
        if(movingChessPiece == null) return;

        movingChessPiece.setVisible(false);

        layeredPane.remove(movingChessPiece);
        movingChessPiece = null;

        destXMove = e.getX() / cellSize;
        destYMove = e.getY() / cellSize;

        moveFreshness = true;

        synchronized (this.getClass()) {
            this.getClass().notifyAll();
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        ChessUI ui = new ChessUI();
        ui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        for(int k = 0; k < Piece.Type.values().length; k++)
            ui.placePiece(Piece.Type.values()[k], ChessColor.BLACK, new Coordinates(k, 0));

        for(int k = 0; k < Piece.Type.values().length; k++)
            ui.placePiece(Piece.Type.values()[k], ChessColor.WHITE, new Coordinates(k, 1));

        ui.pack();
        ui.setResizable(true);
        ui.setLocationRelativeTo( null );
        ui.setVisible(true);
    }
}

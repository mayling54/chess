

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
    
    // List of pieces and whether they are movable
    public final ArrayList<Piece> Bpieces;
    public final ArrayList<Piece> Wpieces;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    

    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        Bpieces = new ArrayList<Piece>();
        Wpieces = new ArrayList<Piece>();
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        for(int r=0; r<board.length; r++) {
        	for(int c=0;c<board.length;c++) {
        		if((r%2==0 && c%2==0) || (r%2==1 && c%2==1)) {
        			board[r][c]=new Square(this, true,r,c);
        			this.add(board[r][c]);
        		
        		}
        		else {
        			board[r][c]=new Square(this, false,r,c);
        			this.add(board[r][c]);
        		
        		}
        		
        	}
        }

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	 //set up the board such that the black pieces are on one side and the white pieces are on the other.
   //since we only have one kind of piece for now you need only set the same number of pieces on either side.
   //it's up to you how you wish to arrange your pieces.
   private void initializePieces() {
      
    // precondition: the checker board is already displayed
    // postcondition: the initial pieces are correctly displayed on the board


    // king
    Piece bKing = new King(false, RESOURCES_BKING_PNG);
    board[0][4].put(bKing);
    Piece wKing = new King(true, RESOURCES_WKING_PNG);
    board[7][4].put(wKing);


    // queen
    board[0][3].put(new Queen(false, RESOURCES_BQUEEN_PNG));
    board[7][3].put(new Queen(true, RESOURCES_WQUEEN_PNG));


    // pawn
    for (int i = 0; i < 8; i++) {
        board[1][i].put(new Pawn(false, RESOURCES_BPAWN_PNG));
        board[6][i].put(new Pawn(true, RESOURCES_WPAWN_PNG));
    }


    // knight
    Piece bKnight1 = new Knight(false, RESOURCES_BKNIGHT_PNG);
    board[0][1].put(bKnight1);
    Piece bKnight2 = new Knight(false, RESOURCES_BKNIGHT_PNG);
    board[0][6].put(bKnight2);
    Piece wKnight1 = new Knight(true, RESOURCES_WKNIGHT_PNG);
    board[7][1].put(wKnight1);
    Piece wKnight2 = new Knight(true, RESOURCES_WKNIGHT_PNG);
    board[7][6].put(wKnight2);


    // bishop
    Piece Bbishop1 = new Bishop(false, RESOURCES_BBISHOP_PNG);
    board[0][2].put(Bbishop1);
    Piece Bbishop2 = new Bishop(false, RESOURCES_BBISHOP_PNG);
    board[0][5].put(Bbishop2);
    Piece Wbishop1 = new Bishop(true, RESOURCES_WBISHOP_PNG);
    board[7][2].put(Wbishop1);
    Piece Wbishop2 = new Bishop(true, RESOURCES_WBISHOP_PNG);
    board[7][5].put(Wbishop2);


    // rook
    Piece Brook1 = new Rook(false, RESOURCES_BROOK_PNG);
    board[0][0].put(Brook1);
    Piece Brook2 = new Rook(false, RESOURCES_BROOK_PNG);
    board[0][7].put(Brook2);
    Piece Wrook1 = new Rook(true, RESOURCES_WROOK_PNG);
    board[7][0].put(Wrook1);
    Piece Wrook2 = new Rook(true, RESOURCES_WROOK_PNG);
    board[7][7].put(Wrook2);
}






public Square[][] getSquareArray() {
    return this.board;
}


public boolean getTurn() {
    return whiteTurn;
}


public void setCurrPiece(Piece p) {
    this.currPiece = p;
}


public Piece getCurrPiece() {
    return this.currPiece;
}


@Override
public void paintComponent(Graphics g) {
    // super.paintComponent(g);


    for (int x = 0; x < 8; x++) {
        for (int y = 0; y < 8; y++) {
            Square sq = board[y][x];
            sq.paintComponent(g);
        }
    }


    if (currPiece != null) {
        if ((currPiece.getColor() && whiteTurn)
                || (!currPiece.getColor()&& !whiteTurn)) {
            final Image i = currPiece.getImage();
            g.drawImage(i, currX, currY, null);
        }
    }
}


@Override
public void mousePressed(MouseEvent e) {
    currX = e.getX();
    currY = e.getY();


    Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));


    if (sq.isOccupied()) {
        currPiece = sq.getOccupyingPiece();
        fromMoveSquare = sq;
        if (!currPiece.getColor() && whiteTurn) {
            currPiece = null;
            return;
        }
        if (currPiece.getColor() && !whiteTurn) {
            currPiece = null;
            return;
        }
    }


    // test for getControlledSquares
    for(Square s: currPiece.getControlledSquares(board, sq)) {
        s.setSelected(true);
        }


    // test for getLegalMoves
    for(Square s: currPiece.getLegalMoves(this, sq)) {
        s.setSelected(true);
    }
   
    repaint();
   
}


// precondition - the board is initialized and contains a king of either color.
// the boolean kingColor corresponds to the color of king we wish to know the status of


// postcondition - returns true if the king is in check and false otherwise
public boolean isInCheck(boolean kingColor) {


    // initialize an arraylist to store arrays of square
    ArrayList<Square[]> controlled = new ArrayList<Square[]>();


    Square king = null;


    // Loop through the board and look for all pieces that are the opposite color of the kingColor
    // For each of those pieces store any squares it controls
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {


            if (board[i][j].isOccupied()) {
                Piece occupyingPiece = board[i][j].getOccupyingPiece();


                if(occupyingPiece.getColor() != kingColor) {
                    controlled.add(occupyingPiece.getControlledSquares(board, board[i][j]));
                }


                if(occupyingPiece instanceof King && occupyingPiece.getColor()==kingColor) {
                    king = board[i][j];
                }
            }
        }
    }


    for (int i = 0; i < controlled.size(); i++) {
        for(Square s: controlled.get(i)){
        if (s.equals(king)) return true; // King is in check
        }
    }
    return false;
}

//pre: mouse is clicked and it will be released
//post: if a piece is clicked and dropped on a legal move the piece will move there, otherwise it will return to the start
@Override
public void mouseReleased(MouseEvent e) {


    for(Square[] sq: board) {
        for(Square s: sq) {
            s.setSelected(false);
        }
    }
    Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
    boolean isLegalMove = false;


    //using currPiece
    if(currPiece!=null) {
        System.out.println("piece isn't null");
        ArrayList<Square> moves=currPiece.getLegalMoves(this, fromMoveSquare);


        if(moves.contains(endSquare)) {
            isLegalMove=true;
        }


        if(isLegalMove && currPiece.getColor()==whiteTurn) {
            // check if move puts king in check
            boolean kingInCheck = false;
            Square originalSquare = fromMoveSquare;
            Piece capturedPiece = null;

            // check if the move captures a piece
            if(endSquare.isOccupied()) {
                capturedPiece = endSquare.getOccupyingPiece();
            }

            endSquare.put(currPiece);
            fromMoveSquare.removePiece();
            whiteTurn = !whiteTurn;
            endSquare.setDisplay(true);

            // check if king is in check
            kingInCheck = isInCheck(currPiece.getColor());

            // if king is in check, undo the move
            if(kingInCheck) {
                endSquare.removePiece();
                originalSquare.put(currPiece);
                if(capturedPiece != null) {
                    endSquare.put(capturedPiece);
                }
                whiteTurn = !whiteTurn;
                originalSquare.setDisplay(true);
            }
        }
        fromMoveSquare.setDisplay(true);
    }
    currPiece = null;

    repaint();
}


@Override
public void mouseDragged(MouseEvent e) {
    currX = e.getX() - 24;
    currY = e.getY() - 24;


    repaint();
}


@Override
public void mouseMoved(MouseEvent e) {
}


@Override
public void mouseClicked(MouseEvent e) {
}


@Override
public void mouseEntered(MouseEvent e) {
}


@Override
public void mouseExited(MouseEvent e) {
}


}

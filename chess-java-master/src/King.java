
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//Towers Ball

//King Piece
public class King extends Piece {
    public King(boolean isWhite, String img_file) {
    	super(isWhite, img_file);
    }

    public String toString() {
    	return getColor() ? "White King" : "Black King";
    }
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece could move there legally.
    public Square[] getControlledSquares(Square[][] board, Square start) {
    	ArrayList<Square> squares = new ArrayList<>();
    	int x = start.getXNum();
    	int y = start.getYNum();
    	for(int i = 0; i < 8; i++)
    		for(int j = 0; j < 8; j++)
    			if((i <= x + 1 && i >= x - 1) && (j <= y + 1 && j >= y - 1) && !(x == i && y == j))
    				squares.add(board[i][j]);
    	Square[] squareArr = new Square[squares.size()];
    	for(int i = 0; i < squares.size(); i++)
    		squareArr[i] = squares.get(i);
    	return squareArr;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
    	Square[][] squares = b.getSquareArray();
    	int x = start.getXNum();
    	int y = start.getYNum();
    	ArrayList<Square> squareList = new ArrayList<>();
    	for(int i = 0; i < squares.length; i++) {
    		for(int j = 0; j < squares[i].length; j++) {
    			if(squares[i][j].isOccupied() && squares[i][j].getOccupyingPiece().getColor() == start.getOccupyingPiece().getColor())
    				continue;
    			if((i <= x + 1 && i >= x - 1) && (j <= y + 1 && j >= y - 1)) 
    				squareList.add(squares[i][j]);
    		}
    	}
    		
    	return squareList;
    }
}
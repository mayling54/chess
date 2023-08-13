//Nathaniel Binantoro

//Pawn
//Works like a standard chess Pawn but no en passant 
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Pawn extends Piece{
    
    public Pawn(boolean isWhite, String img_file) {
    	super(isWhite, img_file);
        
    }
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece could move there legally.
    //Pre-Condition: matrix of squares and one square that the piece occupies
    //Post-Condition: an array of squares that the piece controls
    public Square[] getControlledSquares(Square[][] board, Square start) {
		Square[] control=null;
    	//Black Pawns
    	if (start.getOccupyingPiece().getColor() == false) {
    		if ((start.getYNum()-1 >= 0 && start.getYNum()+ 1 <= 7) && (start.getXNum() < 7)) {
    			control = new Square[2];
    			control[0] = board[start.getXNum()+1][start.getYNum()-1];
    			control[1] = board[start.getXNum()+1][start.getYNum()+1];
    		}
    		else {
    			control = new Square[1];
    		if(start.getYNum()-1 >= 0 && (start.getXNum() < 7)) {
    			control[0] = board[start.getXNum()+1][start.getYNum()-1];
    		}
    		else if(start.getYNum()+ 1 <= 7 && start.getXNum() < 7) {
    			control[0] = board[start.getXNum()+1][start.getYNum()+1];
    		}
    		}
    	}
    	//White Pawns
    	if (start.getOccupyingPiece().getColor() == true) {
    		if(start.getXNum() > 0 && start.getYNum()-1 >= 0 && start.getYNum()+ 1 <= 7) {
    			control = new Square[2];
    			control[0] = board[start.getXNum()-1][start.getYNum()-1];
    			control[1] = board[start.getXNum()-1][start.getYNum()+1];
    		}
    		else {
    			control = new Square[1];
    		if(start.getXNum() > 0 && start.getYNum()-1 >= 0) {
    			control[0] = board[start.getXNum()-1][start.getYNum()-1];
    			
    		}
    		else if(start.getXNum() > 0 && start.getYNum()+1 <= 7) {
    			control[0] = board[start.getXNum()-1][start.getYNum()+1];
    			
    		}
    		}
    		
    	}
    	return control;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    
    //Moves like a pawn, can move forward one space, in starting position it can move one or two spaces forward, if there is an 
    //opposing piece diagonally forward to it, it can capture that piece
    //Pre-Condition: A board and the square that the piece occupies are given
    //Post-Condition: An array list of possible squares a piece can move to is returned
    public ArrayList<Square> getLegalMoves(Board b, Square start){
		ArrayList<Square> legalMoves = new ArrayList<Square>();
    	//Simple black pawn movements

		if (start.getOccupyingPiece().getColor() == false) {
			
			//From starting position
			if (start.getXNum() == 1 && !b.getSquareArray()[start.getXNum()+2][start.getYNum()].isOccupied() && !b.getSquareArray()[start.getXNum()+1][start.getYNum()].isOccupied()) {
			legalMoves.add(b.getSquareArray()[start.getXNum()+2][start.getYNum()]);
			}
			
			//For capturing pieces
			if ((start.getYNum()-1 >= 0)
			&& (b.getSquareArray()[start.getXNum()+1][start.getYNum()-1].isOccupied())) {
				if (b.getSquareArray()[start.getXNum()+1][start.getYNum()-1].getOccupyingPiece().getColor() == true) {
				legalMoves.add(b.getSquareArray()[start.getXNum()+1][start.getYNum()-1]);
				}
			}	
			if ((start.getYNum()+ 1 <= 7) 
			&& (b.getSquareArray()[start.getXNum()+1][start.getYNum()+1].isOccupied())) {
				if (b.getSquareArray()[start.getXNum()+1][start.getYNum()+1].getOccupyingPiece().getColor() == true) {
					legalMoves.add(b.getSquareArray()[start.getXNum()+1][start.getYNum()+1]);
					}				
			}
				

			
			//Moving forward
			if (!b.getSquareArray()[start.getXNum()+1][start.getYNum()].isOccupied()
				&& start.getXNum() < 7) {
				legalMoves.add(b.getSquareArray()[start.getXNum()+1][start.getYNum()]);
    		}
  		}
    	//White Pawn Movements
    	if (start.getOccupyingPiece().getColor() == true) {
    		//From starting position
    		if (start.getXNum() == 6 && (!b.getSquareArray()[start.getXNum()-2][start.getYNum()].isOccupied()) && (!b.getSquareArray()[start.getXNum()-1][start.getYNum()].isOccupied())) {
    			legalMoves.add(b.getSquareArray()[start.getXNum()-2][start.getYNum()]);
    		}
    		//Moving forward
    		if (!b.getSquareArray()[start.getXNum()-1][start.getYNum()].isOccupied()
    				&& start.getXNum() > 0) {
    			legalMoves.add(b.getSquareArray()[start.getXNum()-1][start.getYNum()]);
    		}
    		//For capturing pieces
    		if ((start.getYNum()-1 >= 0) 
    	    && (b.getSquareArray()[start.getXNum()-1][start.getYNum()-1].isOccupied())) {
    					
    				if (b.getSquareArray()[start.getXNum()-1][start.getYNum()-1].getOccupyingPiece().getColor() == false) {
    				legalMoves.add(b.getSquareArray()[start.getXNum()-1][start.getYNum()-1]);
    				}
    		}
    		if (( start.getYNum()+ 1 <= 7)
    		&& (b.getSquareArray()[start.getXNum()-1][start.getYNum()+1].isOccupied())){
    					if (b.getSquareArray()[start.getXNum()-1][start.getYNum()+1].getOccupyingPiece().getColor() == false) {
    						legalMoves.add(b.getSquareArray()[start.getXNum()-1][start.getYNum()+1]);
    						}    	
    		}
    	}
    	
		//print out array
    	return legalMoves;
    }
    public String toString() {
    	return super.toString() + " Pawn"; 

    }
}
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

//This piece can move left, right, up, and down but cannot jump like the knight
public class Rook extends Piece{
public Rook(boolean isWhite, String img_file) {
super(isWhite,img_file);
}

public String toString() {
String theColor;
boolean colorGetter = super.getColor();
if(colorGetter==false) {
theColor = "black";
}
else {
theColor = "white";
}
return "this is a " + theColor + " Rook";
}
// TO BE IMPLEMENTED!
//return a list of every square that is "controlled" by this piece. A square iscontrolled
//if the piece could move there legally.
public Square[] getControlledSquares(Square[][] board, Square start) {
	ArrayList<Square> occups = new ArrayList<Square>();
	int y = start.getXNum();
	int x = start.getYNum();
	int col = x;
	int row = y;
	System.out.println(row + " " + col);
	x++;
	while (x <= 7) {
		if (board[y][x].isOccupied()) {

				occups.add(board[y][x]);
				break;
		
		} else {
			occups.add(board[y][x]);
			x++;
		}
	}
	x = col;
	y = row;
	x--;
	while (x >= 0) {
		if (board[y][x].isOccupied()) {

				occups.add(board[y][x]);
				break;
	
		} else {
			occups.add(board[y][x]);
			x--;
		}
	}
	x = col;
	y = row;
	y++;
	while (y <= 7) {
		if (board[y][x].isOccupied()) {
				occups.add(board[y][x]);
				break;
		} else {
			occups.add(board[y][x]);
			y++;
		}
	}
	x = col;
	y = row;
	y--;
	while (y >= 0) {
		if (board[y][x].isOccupied()) {
				occups.add(board[y][x]);
				break;
		} else {
			occups.add(board[y][x]);
			y--;
		}
	}
	Square [] ret = new Square[occups.size()];
	for(int i=0; i<occups.size(); i++) {
		ret[i]= occups.get(i);
	}
	return ret;


}
//TO BE IMPLEMENTED!
//implement the move function here
//it's up to you how the piece moves, but at the very least the rules should belogical andit should never move off the board!
//returns an arraylist of squares which are legal to move to
//please note that your piece must have some sort of logic. Just being able tomove to every square on the board is not
//going to score any points.
public ArrayList<Square> getLegalMoves(Board b, Square start){
ArrayList<Square> legalMoves = new ArrayList<Square>();
int x = start.getXNum();
int y = start.getYNum();
return super.getLinearOccupations(b.getSquareArray(), x, y);
}
}
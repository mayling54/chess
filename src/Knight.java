
/*
 * Adam Greene
 * Knight
 * Standard knight piece
 * */

import java.util.ArrayList;

//you will need to implement two functions in this file.
public class Knight extends Piece {

	public Knight(boolean isWhite, String img_file) {
		super(isWhite, img_file);
	}
  
	/*
	 * pre : start is the location of the piece and the piece is a knight // post:
	 * returns and Array of all squares that the knight could move to, // regardless
	 * of what is on that piece
	 */
	@Override
	public Square[] getControlledSquares(Square[][] board, Square start) {
		Square[] controlledSquares;
		ArrayList<Square> sqrs = new ArrayList<>();

		if (isOnBoard(+2, +1, start))
			sqrs.add(board[start.getXNum() + 2][start.getYNum() + 1]);
		if (isOnBoard(+2, -1, start))
			sqrs.add(board[start.getXNum() + 2][start.getYNum() - 1]);
		if (isOnBoard(-2, +1, start))
			sqrs.add(board[start.getXNum() - 2][start.getYNum() + 1]);
		if (isOnBoard(-2, -1, start))
			sqrs.add(board[start.getXNum() - 2][start.getYNum() - 1]);
		if (isOnBoard(+1, +2, start))
			sqrs.add(board[start.getXNum() + 1][start.getYNum() + 2]);
		if (isOnBoard(+1, -2, start))
			sqrs.add(board[start.getXNum() + 1][start.getYNum() - 2]);
		if (isOnBoard(-1, +2, start))
			sqrs.add(board[start.getXNum() - 1][start.getYNum() + 2]);
		if (isOnBoard(-1, -2, start))
			sqrs.add(board[start.getXNum() - 1][start.getYNum() - 2]);

		controlledSquares = new Square[sqrs.size()];
		for (int i = 0; i < sqrs.size(); i++) {
			controlledSquares[i] = sqrs.get(i);
		}

		return controlledSquares;
	}

	/*
	 * pre : start is the square where the piece currently is, and it is a knight //
	 * post: returns an ArrayList with all squares that the knight can move to
	 */
	@Override
	public ArrayList<Square> getLegalMoves(Board b, Square start) {
		ArrayList<Square> squares = new ArrayList<Square>();

		Square sq;

		if ((sq = capturableSquare(+2, +1, b, start)) != null)
			squares.add(sq);
		if ((sq = capturableSquare(+2, -1, b, start)) != null)
			squares.add(sq);
		if ((sq = capturableSquare(-2, +1, b, start)) != null)
			squares.add(sq);
		if ((sq = capturableSquare(-2, -1, b, start)) != null)
			squares.add(sq);
		if ((sq = capturableSquare(+1, +2, b, start)) != null)
			squares.add(sq);
		if ((sq = capturableSquare(+1, -2, b, start)) != null)
			squares.add(sq);
		if ((sq = capturableSquare(-1, +2, b, start)) != null)
			squares.add(sq);
		if ((sq = capturableSquare(-1, -2, b, start)) != null)
			squares.add(sq);

		return squares;
	}

	/*
	 * pre : x and y are the relative positions from the start, and start is the //
	 * square currently containing the piece // post: returns the square if the
	 * square is on the board // and not occupied by a piece on the same team, null
	 * otherwise
	 */
	static Square capturableSquare(int x, int y, Board b, Square start) {
		if (!isOnBoard(x, y, start)) {
			return null;
		}
		x += start.getXNum();
		y += start.getYNum();
		if (b.getSquareArray()[x][y].getOccupyingPiece() == null) {
			return b.getSquareArray()[x][y];
		}
		if (b.getSquareArray()[x][y].getOccupyingPiece().getColor() == //
				start.getOccupyingPiece().getColor()) {
			return null;
		}
		return b.getSquareArray()[x][y];
	}

	/*
	 * pre : x and y are the positions relative to start, and start is the // square
	 * currently containing the piece
	 */
	static boolean isOnBoard(int x, int y, Square start) {
		x += start.getXNum();
		y += start.getYNum();
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "A " + (super.getColor() ? "white" : "black") + " Knight";
	}
}
# Java Chess Project

In February 2023,  I developed a chess game in my AP Computer Science course with starter code provided by my teacher.

Specifically, as shown in the source code, I
* utilized 2D arrays for board setup and piece initialization
* utilized ArrayLists to correctly identify all legal moves for each chess piece, accounting for the possibility of board edges, other pieces of either color, and special rules (en passant, being pinned, etc.)
* correctly calculates appropriate moves, updating the state of the game appropriately
* utilized ArrayLists to return all the squares controlled by the piece as specified by its movement rules
* utilized inheritance to properly extend Piece.java and overrides the getLegalMoves, getControlledSquares, and toStirng() functions
* utilized nested loops, instanceof, and ArrayList to identify when a specific colored king is in check

### Technology

This game is written entirely in Java. The suggested integrated development environment for this program is Eclipse IDE, which is ideal for Java development, but I used VS Code to code this game.

### Running

Compile the project into an executable .jar file by running the following ANT build script on the command line. Make sure jar-in-jar-loader.zip in this repository is in the folder.

ant -f build.xml
Then, run the executable .jar file, named chess-java.jar to play.

I ran this game on a MacBook.

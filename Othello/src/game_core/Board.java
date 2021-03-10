package game_core;
import game.Move;
import game.Piece;

public interface Board {
	int getSize(); 
	void printBoard(Piece[][] b); 
	void setupBoard();
	Piece[][] getPieceBoard(); 
	boolean isFull(Piece[][] board); 
	Piece[][] executeMove(Piece[][] b, Move m); 
	void updateBoard(Move m); 
	void setBoard(Piece[][] board); 
}

package game_core;
import java.util.ArrayList;
import game.Piece;

public interface Action {
	public char getPlayer();
	public int getX(); 
	public int getY(); 
	public void setUtility(int i); 
	public void printMove(); 	
	public ArrayList<Piece> piecesToFlip(Piece[][] current); 
	
}

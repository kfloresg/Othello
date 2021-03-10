package game_core;

public interface Player {
	void setColor(char color); 
	void setTurn(boolean turn); 
	char getColor(); 
	//boolean hasMoves(); 
	boolean isTurn(); 
}

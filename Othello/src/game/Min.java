package game;
import game_core.*; 
import java.util.ArrayList; 

public class Min implements Player{
	char color; 
	boolean isTurn; 

	
	public Min(char color) {
		this.color = color; 
		//black is first to move
		if(this.color == 'b') {
			this.isTurn = true; 
		} else {
			this.isTurn = false; 
		}
	}
	
	public void setColor(char color) {
		this.color = color; 
	}
	
	public void setTurn(boolean turn) {
		this.isTurn = turn; 
	}
	
	public char getColor() {
		return this.color; 
	}
	
	public boolean isTurn() {
		return this.isTurn; 
	}
	//validates the input
	public boolean validate(Piece[][] current, char letter, char num, int s) {
		boolean valid = false; 
		if(validLetter(letter, s) && validNum(num, s)) {
			Move m = getMove(letter, num); 
			ArrayList<Move> movelist = getAllMoves(current); 
			for(Move move : movelist) {
				if(move.isEqual(move, m)) {
					valid = true; 
					break; 
				}
			}
		}
		
		return valid; 
	}
	
	public boolean validNum(int n, int s) {
		boolean valid = false; 
		if(s == 4) {
			if(n > 0 && n < 5) {
				valid = true; 
			}
		} else if(s == 8) {
			if(n > 0 && n < 9) {
				valid = true; 
			}
		}

		return valid; 
	}
	
	public boolean validLetter(char l, int s) {
		boolean valid = false;
		
		if(s == 4) {
			switch(l) {
				case 'a': //a
					valid = true;
					break; 
				case 'b': //b
					valid = true; 
					break; 
				case 'c': //c
					valid = true; 
					break; 
				case 'd': //d
					valid = true; 
					break; 
				default: 
					System.out.println("There was an issue");
			}
			
		} else if(s == 8) {
			switch(l) {
				case 'a': //a
					valid = true;
					break; 
				case 'b': //b
					valid = true; 
					break; 
				case 'c': //c
					valid = true; 
					break; 
				case 'd': //d
					valid = true; 
					break; 
				case 'e': //e
					valid = true; 
					break; 
				case 'f': //f
					valid = true; 
					break; 
				case 'g':  //g
					valid = true; 
					break; 
				case 'h': //h
					valid = true; 
					break; 
				default: 
					System.out.println("There was an issue");
			}
		}

		return valid; 
	}
	
	//returns the appropriate move given input
	public Move getMove(char letter, char num){
		//System.out.println(letter); 
		int x = 0, y = 0;
		switch(letter) {
			case 'a': //a
				x = 0;
				break; 
			case 'b': //b
				x = 1;
				break; 
			case 'c': //c
				x = 2;
				break; 
			case 'd': //d
				x = 3;
				break; 
			case 'e': //e
				x = 4;
				break; 
			case 'f': //f
				x = 5;
				break; 
			case 'g':  //g
				x = 6;
				break; 
			case 'h': //h
				x = 7;
				break; 
			default: 
				System.out.println("There was an issue");
		}
		
		y = Character.getNumericValue(num) - 1; 
		//System.out.println(y + ", "+ x); 
		Move move = new Move(this.color, x,y); 
		
		return move; 
	}
	
	//gets all the pieces for min currently on the board
	public ArrayList<Piece> getPieces(Piece[][] current){
		ArrayList<Piece> list = new ArrayList<Piece>(); 
		int x = current.length; 
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				Piece p = current[i][j];
				if(p != null && p.color == this.color) {
					list.add(p); 
				}
			}
		}
		return list; 
	}
	
	//gets all the moves available for min on the current board
	public ArrayList<Move> getAllMoves(Piece[][] current) {
		ArrayList<Move> list = new ArrayList<Move>(); 
		ArrayList<Piece> piecelist = getPieces(current); 
		for(Piece p : piecelist) {
			ArrayList<Move> movelist = p.findValidMoves(current);
			for(Move m : movelist) {
				if(!list.contains(m)) {
					list.add(m); 
				}
			}
		}
		//this.availableMoves = list; 
		return list; 
	}
}

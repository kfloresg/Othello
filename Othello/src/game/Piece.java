package game;
import game_core.*; 
import java.util.ArrayList;

public class Piece implements Disks{
	char color;
	int[] position; 
	public int[][]  Fourweights = {{3,2,2,3}, {2, 1, 1, 2}, {2, 1, 1,2}, {3, 2, 2, 3}};
	public int[][] Eightweights = {{3,2, 2, 2, 2,2, 2, 3}, {2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 1, 1, 1, 1, 2, 2},{2, 2, 1, 1, 1, 1, 2, 2}, {2, 2, 1, 1, 1, 1, 2, 2}, {2, 2, 1, 1, 1, 1, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2}, {3,2, 2, 2, 2,2, 2, 3}}; 
	
	public Piece(char color, int x, int y) {
		this.color = color; 
		this.position = new int[2]; 
		this.position[0] = x; 
		this.position[1] = y;  
	}
	
	public void setColor(char color) {
		this.color = color; 
	}
	
	public char getColor() {
		return this.color; 
	}
	
	public void setPosition(int x, int y) {
		this.position[0] = x; 
		this.position[1] = y; 
	}
	
	public int[] getPosition() {
		return this.position; 
	}
	
	public int getX() {
		return this.position[0]; 
	}
	
	public int getY() {
		return this.position[1]; 
	}
	
	public boolean hasValidMoves(Piece[][] b) {
		ArrayList<Move> moves = findValidMoves(b); 
		if(moves.isEmpty()) {
			return false; 
		} else {
			return true; 
		}
	}
	
	public void flipPiece() {
		if(this.color == 'w') { setColor('b'); }
		else { setColor('w'); }
	}
	
	public void printPiece() {
		System.out.println("Piece of color " + this.getColor() + " at position " + this.getX() + "," + this.getY()); 
	}
	
	//returns the weight of the position
	public int getWeight(int x, int y, int s) {
		if(s == 4) {
			return Fourweights[y][x]; 
		} else {
			return Eightweights[y][x]; 
		}
		
	}
	
	//finds any possible moves for the piece given the current board configuration
	public ArrayList<Move> findValidMoves(Piece[][] currentBoard){ 
		ArrayList<Move> valid = new ArrayList<Move>(); 
		Move move = checkPlaceAbove(currentBoard);  
		if(move.utility != -99) {
			valid.add(move); 
		}
		move = checkPlaceBelow(currentBoard);
		if(move.utility != -99) {
			valid.add(move); 
		}
		move = checkPlaceRight(currentBoard);
		if(move.utility != -99) {
			valid.add(move); 
		}
		move = checkPlaceLeft(currentBoard);
		if(move.utility != -99) {
			valid.add(move); 
		}
		move = checkPlaceAboveRight(currentBoard);
		if(move.utility != -99) {
			valid.add(move); 
		}
		move = checkPlaceAboveLeft(currentBoard);
		if(move.utility != -99) {
			valid.add(move); 
		}
		move = checkPlaceBelowRight(currentBoard);
		if(move.utility != -99) {
			valid.add(move); 
		}
		move = checkPlaceBelowLeft(currentBoard);
		if(move.utility != -99) {
			valid.add(move); 
		}
		return valid; 
		/*for(Move m : this.validMoves) {
			m.printMove();
		}*/
	}
	
	/* This function calculates the utility of placing the next piece above, first it checks if
	 * the space directly above is empty if it is,the utility is zero. If the space is not empty, 
	 * checks if there is any enemy pieces between this piece and the next empty space above. 
	 * The utility would be equal to the new number of same pieces on that line. Lastly it 
	 * adds the move to the list of valid moves possible. 
	 */ 
	public Move checkPlaceAbove(Piece[][] current) {
		int utility = 0;  
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		if(y == 0) {
			utility = 0;  
		} else {
			//while the space above is not empty and is within bounds 
			while( (y-i) >= 0 && current[y - i][x] != null) { 
				Piece p = current[y-i][x]; 
				if(p.getColor() != this.getColor()) {
					utility++;  
				}
				i++; 
				
			}
		}
		
		int newY = y - i; 
		if(newY < 0) {
			utility = 0; 
		}
		
		if(utility != 0) {
			utility = utility + i;
			if(newY < 0) {
				System.out.println("There is a problem here! 1"); 
			}
			Move move = new Move(x, newY, utility, this.color);
			return move; 
		} else {
			return new Move(-99, -99, -99, this.color); 
		}
	}
	
	//same as before but for placing below 
	public Move checkPlaceBelow(Piece[][] current) {
		int utility = 0;  
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int s = current.length - 1; 
		if(y == s) {
			utility = 0;  
		} else {
			//while the space above is not empty and is within bounds 
			while( (y+i) < current.length && current[y+i][x] != null ) { 
				Piece p = current[y+i][x]; 
				if(p.getColor() != this.getColor()) {
					utility++;  
				}
				i++; 
			
			}
		}
		
		int newY = y + i; 
		if(newY >= 4) {
			utility = 0; 
		}
		
		if(utility != 0) {
			utility = utility + i;  
			if(newY >= 4) {
				System.out.println("There is a problem here! 2"); 
			}
			Move move = new Move(x, newY, utility, this.color);
			return move; 
		} else {
			return new Move(-99, -99, -99, this.color); 
		}
	
	}
	
	//checks for horizontal placements 
	public Move checkPlaceRight(Piece[][] current) {
		int utility = 0;  
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int s = current.length - 1; 
		if(x == s) {
			utility = 0;  
		} else {
			//while the space above is not empty and is within bounds 
			while ( (x+i) < current.length && current[y][x+i] != null ) { 
				Piece p = current[y][x+i]; 
				if(p.getColor() != this.getColor()) {
					utility++;  
				}
				i++; 
				
			}
		}
		
		int newX = x + i; 
		if(newX >= 4) {
			utility = 0; 
		}
		
		if(utility != 0) {
			utility = utility + i; 
			if(newX >= 4) {
				System.out.println("There is a problem here! 3"); 
			}
			Move move = new Move(newX, y, utility, this.color);
			return move; 
		} else {
			return new Move(-99, -99, -99, this.color); 
		}

	}
	
	public Move checkPlaceLeft(Piece[][] current) {
		int utility = 0;  
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		if(x == 0) {
			utility = 0;  
		} else {
			//while the space above is not empty and is within bounds 
			while((x-i) >= 0 && current[y][x-i] != null ) { 
				Piece p = current[y][x-i]; 
				if(p.getColor() != this.getColor()) {
					utility++;  
				}
				i++; 
				
			}
		}
		
		int newX = x - i; 
		if(newX < 0) {
			utility = 0; 
		}
		
		if(utility != 0) {
			utility = utility + i; 
			if(newX < 0) {
				System.out.println("There is a problem here! 4"); 
			}
			Move move = new Move(newX, y, utility, this.color);
			return move; 
		} else {
			return new Move(-99, -99, -99, this.color); 
		}
	}
	
	//diagonal checks 
	public Move checkPlaceAboveRight(Piece[][] current) {
		int utility = 0;  
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int bound = current.length - 1; 
		if(y == 0 || x == bound) {
			utility = 0;  
		} else {
			//while the space above is not empty and is within bounds 
			while((y-i) >= 0 && (x+i) < current.length && current[y - i][x + i] != null ) { 
				Piece p = current[x + i][y-i]; 
				if(p == null) {
					break; 
				}
				if(p.getColor() != this.getColor()) {
					utility++;  
				}
				i++; 
			
			}
		}
		
		int newY = y - i, newX = x + i; 
		if(newY < 0 || newX >=4) {
			utility = 0; 
		}
		
		if(utility != 0) {
			utility = utility + i; 
			if(newY >= 4 || newX >= 4) {
				System.out.println("There is a problem here! 5"); 
			}
			Move move = new Move(newX, newY, utility, this.color);
			return move; 
		} else {
			return new Move(-99, -99, -99, this.color); 
		}
	
	}
	
	public Move checkPlaceAboveLeft(Piece[][] current) {
		int utility = 0;  
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		if(y == 0 || x == 0) {
			utility = 0;  
		} else {
			//while the space above is not empty and is within bounds 
			while( (y-i) >= 0 && (x-i) >= 0  && current[y - i][x - i] != null) { 
				Piece p = current[x - i][y-i]; 
				if(p == null) {
					break; 
				}
				if(p.getColor() != this.getColor()) {
					utility++;  
				}
				i++; 
				
			}
		}
		
		int newY = y - i, newX = x - i; 
		if(newY < 0 || newX < 0) {
			utility = 0; 
		}
		
		if(utility != 0) {
			utility = utility + i; 
			if(newY < 0 || newX < 0) {
				System.out.println("There is a problem here! 6"); 
			}
			Move move = new Move(newX, newY, utility, this.color);
			return move;  
		
		} else {
			return new Move(-99, -99, -99, this.color); 
		}
		
	}
	
	public Move checkPlaceBelowRight(Piece[][] current) {
		int utility = 0;  
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int bound = current.length - 1; 
		if(y == bound || x == bound) {
			utility = 0;  
		} else {
			//while the space above is not empty and is within bounds 
			while((y+i) < current.length && (x+i) < current.length && current[y + i][x + i] != null ) { 
				Piece p = current[y + i][x+i]; 
				if(p.getColor() != this.getColor()) {
					utility++;  
				}
				i++; 
			
			}
		}
		
		int newY = y + i, newX = x + i; 
		if(newY >= 4 || newX >= 4) {
			utility = 0; 
		}
		
		if(utility != 0) {
			utility = utility + i; 
			if(newY >= 4 || newX >= 4) {
				System.out.println("There is a problem here! 7"); 
			}
			Move move = new Move(newX, newY, utility, this.color);
			return move; 
		
		} else {
			return new Move(-99, -99, -99, this.color); 
		}
		
	}
	
	public Move checkPlaceBelowLeft(Piece[][] current) {
		int utility = 0;  
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int bound = current.length - 1; 
		if(y == bound || x == 0) {
			utility = 0;  
		} else {
			//while the space above is not empty and is within bounds 
			while( (y+i) < current.length && (x-i) >= 0 ) {
				Piece p = current[y + i][ x - i]; 
				if(p == null) {
					break; 
				} else {
					if(p.getColor() != this.getColor()) {
						utility++;  
					}
					i++; 
				}
			}
		}
		
		int newY = y + i, newX = x - i; 
		if(newY >= 4 || newX < 0) {
			utility = 0; 
		}
		
		if(utility != 0) {
			utility = utility + i; 
	
			if(newY >= 4 || newX >= 4) {
				//System.out.println(y + "," + x + "   i = " + i); 
				System.out.println("There is a problem here! 8"); 
			}
			
			Move move = new Move(newX, newY, utility, this.color);
			return move;  
			
		} else {
			return new Move(-99, -99, -99, this.color); 
		}
		
	}

}

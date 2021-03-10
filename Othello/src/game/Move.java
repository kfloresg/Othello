package game;
import game_core.*; 
import java.util.ArrayList; 

public class Move implements Action {
	int x, y, utility;
	char player; 
	
	public Move(char player, int x, int y) {
		this.x = x;
		this.y = y;
		this.player = player; 
		//this.flipPieces = new ArrayList<Piece>(); 
	}
	
	public Move(int x, int y, int utility, char player) {
		this.x = x; 
		this.y = y; 
		this.utility = utility; 
		this.player = player;  
	}
	
	public char getPlayer() {
		return this.player; 
	}
	
	public int getX() {
		return this.x; 
	}
	
	public int getY() {
		return this.y; 
	}
	
	public void setUtility(int i) {
		this.utility = i; 
	}
	
	public boolean isEqual(Move m, Move m2) {
		boolean equal = false; 
		if((m.x == m2.x) && (m.y == m2.y)) {
			equal = true; 
		}
		
		return equal; 
	}
	
	public void printMove() {
		System.out.println("Place piece at " + x + "," + y + " with utility of " + utility); 
	}
	
	//returns a list of pieces that will be flipped by executing given move
	public ArrayList<Piece> piecesToFlip(Piece[][] current){
		ArrayList<Piece> toFlip = new ArrayList<Piece>(); 
		
		ArrayList<Piece> flips = checkLineAbove(current); 
		if(!flips.isEmpty()) {
			for(Piece p : flips) {
				toFlip.add(p); 
			}
		}
		flips = checkLineBelow(current); 
		if(!flips.isEmpty()) {
			for(Piece p : flips) {
				toFlip.add(p); 
			}
		}
		flips = checkLineRight(current); 
		if(!flips.isEmpty()) {
			for(Piece p : flips) {
				toFlip.add(p); 
			}
		}
		flips = checkLineLeft(current); 
		if(!flips.isEmpty()) {
			for(Piece p : flips) {
				toFlip.add(p); 
			}
		}
		flips = checkLineBelowRight(current); 
		if(!flips.isEmpty()) {
			for(Piece p : flips) {
				toFlip.add(p); 
			}
		}
		flips = checkLineBelowLeft(current); 
		if(!flips.isEmpty()) {
			for(Piece p : flips) {
				toFlip.add(p); 
			}
		}
		flips = checkLineAboveRight(current); 
		if(!flips.isEmpty()) {
			for(Piece p : flips) {
				toFlip.add(p); 
			}
		}
		flips = checkLineAboveLeft(current); 
		if(!flips.isEmpty()) {
			for(Piece p : flips) {
				toFlip.add(p); 
			}
		}
		
		return toFlip; 
	}
	
	public boolean nextIsEmpty(Piece[][] board, Piece p, int direction) {
		boolean empty = true; 
		int x = p.getX(), y = p.getY(), bound = board.length - 1;  
		
		if(direction == 1) {
			//up
			if((y-1) >= 0) {
				Piece piece = board[y-1][x];
				if(piece != null) {empty = false; }
			}
			
		} else if(direction == 2) {
			//up-right
			if((y-1) >= 0 && (x+1) <= bound) {
				Piece piece = board[y-1][x + 1];
				if(piece != null) {empty = false; }
			}
		} else if(direction == 3) {
			//right
			if((x+1) <= bound) {
	 
				Piece piece = board[y][x + 1];
			
				if(piece != null) {empty = false; }
				
			}
		} else if(direction == 4) {
			//down-right
			if((y+1) <= bound && (x+1) <= bound) {
				Piece piece = board[y+1][x + 1];
				if(piece != null) {empty = false; }
			}
			
		} else if(direction == 5) {
			//down
			if((y-1) <=  bound) {
				Piece piece = board[y-1][x];
				if(piece != null) {empty = false; }
			}
			
		} else if(direction == 6) {
			//down-left
			if((y+1) <= bound && (x-1) >= 0) {
				Piece piece = board[y+1][x - 1];
				if(piece != null) {empty = false; }
			}
			
		} else if(direction == 7) {
			//left
			if((x-1) >= 0) {
				Piece piece = board[y][x - 1];
			
				if(piece != null) {empty = false; }
				
			}
			
		} else if(direction == 8) {
			//up-left
			if((y-1) >= 0 && (x-1) >= 0) {
				Piece piece = board[y-1][x - 1];
				if(piece != null) {empty = false; }
			}
			
		}
		
		return empty; 
	}
	
	public boolean isBetween(Piece[][] board, Piece p, int direction) {
		boolean between = false; 
		int x = p.getX(), y = p.getY(), i = 1, bound = board.length -1; 
		
		if(direction == 1) {
			//up
			int diffY = y - 0; 
			while(i <= diffY) {
				if(!nextIsEmpty(board, p, 1)) {
					Piece next = board[y-i][x]; 
					if(next == null) {
						break; 
					}
					if(next.getColor() != p.color) {
						between = true; 
						break; 
					} else {
						i++; 
					}
				} else {break; }
			}

		} else if(direction == 2) {
			//up-right
			int diffY = y - 0, diffX = bound - x; 
			while(i <= diffY && i <= diffX) {
				if(!nextIsEmpty(board, p, 2)) {
					Piece next = board[y-i][x + i]; 
					if(next == null) {
						break; 
					}
					if(next.getColor() != p.color) {
						between = true; 
						break; 
					} else {
						i++; 
					}
				} else {break;}
			}
		} else if(direction == 3) {
			//right
			int diffX = bound - x; 
			while(i <= diffX) {
				if(!nextIsEmpty(board, p, 3)) {
					Piece next = board[y][x + i]; 
					if(next == null) {
						break; 
					}
					if(next.getColor() != p.color) {
						between = true; 
						break; 
					} else {
						i++; 
					}
				} else {break; }
			}
		} else if(direction == 4) {
			//down-right
			int diffY = bound - y, diffX = bound - x; 
			while(i <= diffY && i <= diffX) {
				if(!nextIsEmpty(board, p, 4)) {
					Piece next = board[y+i][x + i]; 
					if(next == null) {
						break; 
					}
					if(next.getColor() != p.color) {
						between = true; 
						break; 
					} else {
						i++; 
					}
				} else {break;}
			}
		} else if(direction == 5) {
			//down
			int diffY = bound - y; 
			while(i <= diffY) {
				if(!nextIsEmpty(board, p, 5)) {
					Piece next = board[y+i][x]; 
					if(next == null) {
						break; 
					}
					if(next.getColor() != p.color) {
						between = true; 
						break; 
					} else {
						i++; 
					}
				} else {break;}
			}
		} else if(direction == 6) {
			//down-left
			int diffY = y - 0, diffX = x - 0; 
			while(i <= diffY && i <= diffX) {
				if(!nextIsEmpty(board, p, 6)) {
					Piece next = board[y-i][x - i]; 
					if(next == null) {
						break; 
					}
					if(next.getColor() != p.color) {
						between = true; 
						break; 
					} else {
						i++; 
					}
				} else {break;}
			}
		} else if(direction == 7) {
			//left
			int diffX =  x - 0; 
			while( i <= diffX) {
				if(!nextIsEmpty(board, p, 7)) {
					Piece next = board[y][x - i]; 
					if(next == null) {
						break; 
					}
					if(next.getColor() != p.color) {
						between = true; 
						break; 
					} else {
						i++; 
					}
				} else {break;}
			}
		} else if(direction == 8) {
			//up-left
			int diffY = y - 0, diffX = x - 0; 
			while(i <= diffY && i <= diffX) {
				if(!nextIsEmpty(board, p, 8)) {
					Piece next = board[y-i][x - i]; 
					if(next == null) {
						break; 
					}
					if(next.getColor() != p.color) {
						between = true; 
						break; 
					} else {
						i++; 
					}
				} else {break;}
			} 
		}
		
		return between;  
	}
	
	//checks that there are any enemy pieces that would be flip by this move 
	public ArrayList<Piece> checkLineAbove(Piece[][] current) {
		ArrayList<Piece> flips = new ArrayList<Piece>(); 
		int x = this.getX(), y = this.getY();
		int i = 1; 
		
		if(y != 0) {
			//while the space above is not empty and is within bounds 
			while( (y-i) > 0 && current[y-i][x] != null) { 
				Piece p = current[y-i][x]; 
				if(p == null) {
					break; 
				}
				if(p.getColor() != this.player) {
					if(isBetween(current, p, 1)) {
						flips.add(p); 
					}
				}
				i++; 
			}
		}
		
		return flips; 
	}
	
	public ArrayList<Piece> checkLineBelow(Piece[][] current) {
		ArrayList<Piece> flips = new ArrayList<Piece>(); 
		
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int s = current.length - 1; 
		if(y != s) {
			//while the space above is not empty and is within bounds 
			while((y+i) < current.length && current[y + i][x] != null ) { 
				Piece p = current[y + i ][x]; 
				if(p.getColor() != this.player) {
					if(isBetween(current, p, 5)) {
						flips.add(p); 
					}
					
				}
				i++; 
			}
		}
		
		return flips; 
	}
	
	public ArrayList<Piece> checkLineRight(Piece[][] current) {
		ArrayList<Piece> flips = new ArrayList<Piece>(); 
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int s = current.length - 1; 
		if(x != s) {
			while((x+i) < current.length && current[y ][x + i] != null) { 
				Piece p = current[y][x + i]; 
			
				//p.printPiece();
				if(p.getColor() != this.player) {
					
					if(isBetween(current, p, 3)) {
					
						flips.add(p); 
					}
				}
				i++; 
			}
		}

		
		return flips; 
	}
	
	public ArrayList<Piece> checkLineLeft(Piece[][] current) {
		ArrayList<Piece> flips = new ArrayList<Piece>(); 
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		if(x != 0) {
			//while the space above is not empty and is within bounds 
			while( (x-i) >= 0 && current[y][x - i] != null) { 
				Piece p = current[y][x-i]; 
				if(p.getColor() != this.player) {
					if(isBetween(current, p, 7)) {
						flips.add(p); 
					}
				}
				i++; 
			}
		}
	
		
		return flips; 
	}
	
	//diagonal checks 
	public ArrayList<Piece> checkLineAboveRight(Piece[][] current) {
		ArrayList<Piece> flips = new ArrayList<Piece>(); 
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int bound = current.length - 1; 
		if(y > 0 && x <  bound) {
			//while the space above is not empty and is within bounds 
			while((y-i) >= 0 && (x+i) < current.length && current[y -i][x + i] != null ) { 
				Piece p = current[y - i][x +i]; 
				if(p.getColor() != this.player) {
					if(isBetween(current, p, 2)) {
						flips.add(p); 
					}
				}
				i++; 
			}
		}
	
		return flips; 
	}
	
	public ArrayList<Piece> checkLineAboveLeft(Piece[][] current) {
		ArrayList<Piece> flips = new ArrayList<Piece>(); 
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		if(y > 0 && x > 0) {
			//while the space above is not empty and is within bounds 
			while( (y-i) >= 0 && (x-i) >= 0 && current[y - i][x - i] != null) { 
				Piece p = current[y - i][x-i]; 
				if(p.getColor() != this.player) {
					if(isBetween(current, p, 8)) {
						flips.add(p); 
					}
				}
				i++; 
			}
		} 
	
		return flips; 
	}
	
	public ArrayList<Piece> checkLineBelowRight(Piece[][] current) {
		ArrayList<Piece> flips = new ArrayList<Piece>(); 
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int bound = current.length - 1; 
		if(y < bound && x < bound) {
			//while the space above is not empty and is within bounds 
			while((y+i) < current.length && (x+i) < current.length && current[y + i][x + i] != null ) { 
				Piece p = current[y + i][x+i]; 
				if(p.getColor() != this.player) {
					if(isBetween(current, p, 4)) {
						flips.add(p); 
					}
				}
				i++; 
			}
		}
 
		return flips; 
	}
	
	public ArrayList<Piece> checkLineBelowLeft(Piece[][] current) {
		ArrayList<Piece> flips = new ArrayList<Piece>(); 
		int x = this.getX(), y = this.getY(); 
		int i = 1; 
		int bound = current.length - 1; 
		if(y < bound && x > 0) {
			//while the space above is not empty and is within bounds 
			while((y+i) < current.length && (x-i) >= 0 && current[y + i][x - i] != null) { 
				Piece p = current[y+i][x - i]; 
				if(p.getColor() != this.player) {
					if(isBetween(current, p, 6)) {
						flips.add(p); 
					}
				}
				i++; 
			}
		}

		return flips; 
	}
	
}

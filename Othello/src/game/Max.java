package game;
import game_core.*; 
import java.util.ArrayList;

public class Max implements Player{
	char color; 
	boolean isTurn;   
	
	public Max(char color) {
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
	
	//gets all of player pieces currently on board
	public ArrayList<Piece> getPieces(Piece[][] current, char player){
		ArrayList<Piece> pieces = new ArrayList<Piece>(); 
		int x = current.length; 
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				Piece p = current[i][j];
				if(p != null && p.color == player) {
					pieces.add(p); 
				}
			}
		}
		return pieces; 
	}
	
	//returns all of the moves available to max 
	public ArrayList<Move> getAllMoves(Piece[][] current, char player) {
		ArrayList<Move> list = new ArrayList<Move>(); 
		ArrayList<Piece> pieces = getPieces(current, player); 
		for(Piece p : pieces) {
			ArrayList<Move> movelist = p.findValidMoves(current);
			for(Move m : movelist) {
				if(!list.contains(m)) {
					list.add(m); 
				}
			}
		}
		return list; 
	}
	
	//minimax with fixed depth cutoff  
	public Move minimaxDecision(Piece[][] current, int depth) {
		Move best = new Move(99, 99, 99, this.color); 
		int bestValue = 0; 
		 
		ArrayList<Move> movelist  = getAllMoves(current, color); 
		
		for(Move m : movelist) {
			int val = minimaxValue(current, depth, true); 
			if(val > bestValue) {
				bestValue = val; 
				best = m; 
			}
		}
		return best; 
	}
	
	public int minimaxValue(Piece[][] state, int depth, boolean maximizing) {
		if(depth == 0 || isFinished(state)) {
			return heuristic(state); 
		}
		
		char opponent = 'w'; 
		if(color == 'w') {
			opponent = 'b'; 
		}
		
		if(maximizing) {
			int maxEval = -999; 
			ArrayList<Move> movelist = getAllMoves(state, color); 
			for(Move m : movelist) {
				int eval = minimaxValue(nextState(state, m), depth - 1, false); 
				if(eval > maxEval) {
					maxEval = eval; 
				}
			}
			return maxEval; 
		} else {
			int minEval = 999; 
			ArrayList<Move> movelist = getAllMoves(state, opponent); 
			for(Move m : movelist) {
				int eval = minimaxValue(nextState(state, m), depth - 1, true);
				if(eval < minEval) {
					minEval = eval; 
				}
			}
			return minEval; 
		}
	}
	
	//minimax with alpha beta pruning 
	public Move alphabeta(Piece[][] current, int depth) {
		Move best = new Move(99, 99, 99, this.color); 
		int bestValue = -99; 
		ArrayList<Move> movelist = getAllMoves(current, color); 
		
		for(Move m : movelist) {
			int val = alphabetaValue(current, depth, -999, 999, true); 
			if(val > bestValue) {
				bestValue = val; 
				best = m; 
			}
		}
		
		return best; 
	}
	
	public int alphabetaValue(Piece[][] state, int depth, int alpha, int beta, boolean maximizing) {
		if(depth == 0 || isFinished(state)) {
			return heuristic(state); 
		}
		
		char opponent = 'w';
		if(color == 'w') {
			opponent = 'b'; 
		}
		
		if(maximizing) {
			int maxEval = -9; 
			ArrayList<Move> movelist = getAllMoves(state, color); 
			for(Move m : movelist) {
				//m.printMove();
				int eval = alphabetaValue(nextState(state, m), depth - 1, alpha, beta, false); 
				//System.out.println("eval = " + eval); 
				maxEval = max(maxEval, eval); 
				alpha = max(alpha, eval); 
				if(beta <= alpha) {
					break; 
				}
			}
			return maxEval; 
		} else {
			int minEval = 999; 
			ArrayList<Move> movelist = getAllMoves(state, opponent); 
			for(Move m : movelist) {
				int eval = alphabetaValue(nextState(state, m), alpha, beta, depth - 1, true);
				minEval = min(minEval, eval); 
				beta = min(beta, eval); 
				if(beta <= alpha) {
					break; 
				}
			}
			return minEval; 
		}
	}
	
	/* The heuristic function returns the value of the resulting board after a move 
	 * is executed. The value of the board is calculated by the number of max's pieces plus
	 * their respective weights according to the weighted board 
	*/
	
	public int heuristic(Piece[][] result) {
		int h = 0; 
		ArrayList<Piece> pieces = getPieces(result, color); 
		for(Piece p : pieces) {
			int x = p.getX(), y = p.getY(); 
			int val  = p.getWeight(x, y, result.length); 
			h = h + val; 
		}
		
		//System.out.println("Board value: " + h); 
		return h; 
	}
	
	//terminal test
	public boolean isFinished(Piece[][] state) {
		boolean finished = false; 
		
		char opponent = 'w'; 
		if(color == 'w') {
			opponent = 'b'; 
		}
		
		ArrayList<Move> maxMoves = getAllMoves(state, color); 
		ArrayList<Move> minMoves = getAllMoves(state, opponent); 
		if(maxMoves.isEmpty() || minMoves.isEmpty()) {
			finished = true; 
		} else if(isFull(state) ) {
			finished = true; 
		}
			
		return finished; 
	}
	
	public boolean isFull(Piece[][] state) {
		boolean full = true; 
		for(int i = 0; i < state.length; i++) {
			for(int j = 0; j < state.length; j++) {
				if(state[i][j] == null) {
					full = false;
					break; 
				}
			}
		}
		return full; 
	}
	
	//execute move on current board
	public Piece[][] nextState(Piece[][] current, Move move) {
		int x = move.getX(), y = move.getY();
		char color = move.getPlayer(); 
		Piece p = new Piece(color, x, y); 
		current[y][x] = p; 
	
		ArrayList<Piece> flips = move.piecesToFlip(current);

		for(Piece f : flips) {
			int flipx = f.getX(), flipy = f.getY(); 
			Piece toFlip = current[flipy][flipx]; 
			toFlip.flipPiece();
			current[flipy][flipx] = toFlip; 
		}
	
		return current; 
	}
	
	int max(int a, int b) {
		if(a > b) {
			return a; 
		} else {
			return b; 
		}
	}
	
	int min(int a, int b) {
		if(a < b) {
			return a;
		} else {
			return b;
		}
	}
	
}

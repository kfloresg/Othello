package game;
//import game_core.*; 
import java.util.ArrayList; 
import java.util.Scanner; 

public class Game {
	Max AI; 
	Min player;
	
	public Game(Max max, Min min) {
		this.AI = max; 
		this.player = min; 
	}
	
	public void playFourGame(Scanner scanner, int type, int depth) {
		Piece[][] gameboard = setFourBoard(); 
		Piece[][] tempBoard = setFourBoard(); 
		
		ArrayList<Move> movesSoFar = new ArrayList<Move>(); 
		
		printFourBoard(gameboard);
		
		while(!AI.isFinished(gameboard)) {
			if(AI.isTurn()) { 
				System.out.println("Now my turn:"); 
				if(type == 1) {
					Move m = AI.minimaxDecision(tempBoard, depth);
					m.printMove();
					movesSoFar.add(m); 
					gameboard = giveCurrentFourBoard(movesSoFar); 
				} else if (type == 2) {
					Move m = AI.alphabeta(tempBoard, depth); 	
					m.printMove();
					movesSoFar.add(m); 
					gameboard = giveCurrentFourBoard(movesSoFar); 
				}
				
				//System.out.println("This is the gameboard:"); 
				printFourBoard(gameboard); 
				switchTurn(); 
			 
			} else if (player.isTurn()) {
				System.out.println("Enter your move (column followed by the row number i.e a2): "); 
				String m = scanner.next(); 
				char c = m.charAt(0), r = m.charAt(1);
				
				Move move = player.getMove(c, r); 
				move.printMove();
				movesSoFar.add(move); 
				gameboard = giveCurrentFourBoard(movesSoFar); 
				tempBoard = giveCurrentFourBoard(movesSoFar); 
				
				//System.out.println("This is the gameboard:"); 
				printFourBoard(gameboard); 
				switchTurn(); 
			}
		}
		
		winner(gameboard); 
	}
	
	public void playEightGame(Scanner scanner, int type, int depth) {
		Piece[][] gameboard = setEightBoard(); 
		Piece[][] tempBoard = setEightBoard(); 
		
		ArrayList<Move> movesSoFar = new ArrayList<Move>(); 
		
		printFourBoard(gameboard);
		
		while(!AI.isFinished(gameboard)) {
			if(AI.isTurn()) { 
				System.out.println("Now my turn:"); 
				if(type == 1) {
					Move m = AI.minimaxDecision(tempBoard, depth);
					m.printMove();
					movesSoFar.add(m); 
					gameboard = giveCurrentEightBoard(movesSoFar); 
				} else if (type == 2) {
					Move m = AI.alphabeta(tempBoard, depth); 	
					m.printMove();
					movesSoFar.add(m); 
					gameboard = giveCurrentEightBoard(movesSoFar); 
				}
				
				tempBoard = giveCurrentEightBoard(movesSoFar); 
				printEightBoard(gameboard); 
				switchTurn(); 
				
			} else if (player.isTurn()) {
				System.out.println("Enter your move (column followed by the row number i.e a2): "); 
				String m = scanner.next(); 
				char c = m.charAt(0), r = m.charAt(1);
				
				Move move = player.getMove(c, r); 
				move.printMove();
				movesSoFar.add(move); 
				
				gameboard = giveCurrentEightBoard(movesSoFar); 
				tempBoard = giveCurrentEightBoard(movesSoFar);
				
				printEightBoard(gameboard); 
				switchTurn(); 
			}
		}
		
		winner(gameboard); 
	}
	
	public Piece[][] setFourBoard(){
		Piece[][] gameboard = new Piece[4][4];
		Piece b1 = new Piece('b', 2, 1);
		Piece b2 = new Piece('b', 1, 2); 
		Piece w1 = new Piece('w', 1, 1); 
		Piece w2 = new Piece('w', 2, 2); 
		gameboard[1][2] = b1; 
		gameboard[2][1] = b2; 
		gameboard[1][1] = w1; 
		gameboard[2][2] = w2; 
		return gameboard; 
	}
	
	public Piece[][] setEightBoard(){
		Piece[][] gameboard = new Piece[8][8]; 
		Piece b1 = new Piece('b', 4, 3);
		Piece b2 = new Piece('b', 3, 4); 
		Piece w1 = new Piece('w', 3, 3); 
		Piece w2 = new Piece('w', 4, 4);
		gameboard[3][4] = b1; 
		gameboard[4][3] = b2; 
		gameboard[3][3] = w1; 
		gameboard[4][4] = w2;
		
		return gameboard; 
	}
	
	public void switchTurn() {
		if(AI.isTurn()) {
			AI.setTurn(false);
			player.setTurn(true);
		} else {
			player.setTurn(false);
			AI.setTurn(true);
		}
	}

	public void printFourBoard(Piece[][] b) {
		char[][] board = new char[10][10];
		
		board[0][2] = 'a'; 
		board[0][4] = 'b'; 
		board[0][6] = 'c'; 
		board[0][8] = 'd'; 
		board[2][0] = '1'; 
		board[4][0] = '2'; 
		board[6][0] = '3'; 
		board[8][0] = '4'; 
		
		board[1][2] = '-'; 
		board[1][4] = '-';
		board[1][6] = '-';
		board[1][8] = '-';
		board[3][2] = '-'; 
		board[3][4] = '-';
		board[3][6] = '-';
		board[3][8] = '-';
		board[5][2] = '-'; 
		board[5][4] = '-';
		board[5][6] = '-';
		board[5][8] = '-';
		board[7][2] = '-'; 
		board[7][4] = '-';
		board[7][6] = '-';
		board[7][8] = '-';
		board[9][2] = '-'; 
		board[9][4] = '-';
		board[9][6] = '-';
		board[9][8] = '-';
		
		board[2][1] = '|'; 
		board[2][3] = '|'; 
		board[2][5] = '|'; 
		board[2][7] = '|'; 
		board[2][9] = '|'; 
		board[4][1] = '|'; 
		board[4][3] = '|'; 
		board[4][5] = '|'; 
		board[4][7] = '|'; 
		board[4][9] = '|';
		board[6][1] = '|'; 
		board[6][3] = '|'; 
		board[6][5] = '|'; 
		board[6][7] = '|'; 
		board[6][9] = '|';
		board[8][1] = '|'; 
		board[8][3] = '|'; 
		board[8][5] = '|'; 
		board[8][7] = '|'; 
		board[8][9] = '|'; 
		
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b.length; j++) {
				Piece p = b[i][j]; 
				if(p != null) {
					//p.printPiece();
					int[] position = convertPosition(p); 
					int x = position[0], y = position[1];
					//System.out.println("x = " + x + " y = " + y); 
					board[y][x] = p.getColor(); 
					//System.out.println(board[x][y]);
				}
			}
		}
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				char c = board[i][j]; 
				System.out.print(c); 
			}
			System.out.println(); 
		}
	}
	
	public void printEightBoard(Piece[][] b) {
		char[][] board = new char[18][18];
		
		board[0][2] = 'a'; 
		board[0][4] = 'b'; 
		board[0][6] = 'c'; 
		board[0][8] = 'd'; 
		board[0][10] = 'e';
		board[0][12] = 'f';
		board[0][14] = 'g'; 
		board[0][16] = 'h'; 
		
		board[2][0] = '1'; 
		board[4][0] = '2'; 
		board[6][0] = '3'; 
		board[8][0] = '4'; 
		board[10][0] = '5';
		board[12][0] = '6';
		board[14][0] = '7';
		board[16][0] = '8';
		
		board[1][2] = '-'; 
		board[1][4] = '-';
		board[1][6] = '-';
		board[1][8] = '-';
		board[1][10] = '-';
		board[1][12] = '-';
		board[1][14] = '-';
		board[1][16] = '-';
		board[3][2] = '-'; 
		board[3][4] = '-';
		board[3][6] = '-';
		board[3][8] = '-';
		board[3][10] = '-';
		board[3][12] = '-';
		board[3][14] = '-';
		board[3][16] = '-';
		board[5][2] = '-'; 
		board[5][4] = '-';
		board[5][6] = '-';
		board[5][8] = '-';
		board[5][10] = '-';
		board[5][12] = '-';
		board[5][14] = '-';
		board[5][16] = '-';
		board[7][2] = '-'; 
		board[7][4] = '-';
		board[7][6] = '-';
		board[7][8] = '-';
		board[7][10] = '-';
		board[7][12] = '-';
		board[7][14] = '-';
		board[7][16] = '-';
		board[9][2] = '-'; 
		board[9][4] = '-';
		board[9][6] = '-';
		board[9][8] = '-';
		board[9][10] = '-';
		board[9][12] = '-';
		board[9][14] = '-';
		board[9][16] = '-';
		board[11][2] = '-'; 
		board[11][4] = '-';
		board[11][6] = '-';
		board[11][8] = '-';
		board[11][10] = '-';
		board[11][12] = '-';
		board[11][14] = '-';
		board[11][16] = '-';
		board[13][2] = '-'; 
		board[13][4] = '-';
		board[13][6] = '-';
		board[13][8] = '-';
		board[13][10] = '-';
		board[13][12] = '-';
		board[13][14] = '-';
		board[13][16] = '-';
		board[15][2] = '-'; 
		board[15][4] = '-';
		board[15][6] = '-';
		board[15][8] = '-';
		board[15][10] = '-';
		board[15][12] = '-';
		board[15][14] = '-';
		board[15][16] = '-';
		board[17][2] = '-'; 
		board[17][4] = '-';
		board[17][6] = '-';
		board[17][8] = '-';
		board[17][10] = '-';
		board[17][12] = '-';
		board[17][14] = '-';
		board[17][16] = '-';
		
		board[2][1] = '|'; 
		board[2][3] = '|'; 
		board[2][5] = '|'; 
		board[2][7] = '|'; 
		board[2][9] = '|'; 
		board[2][11] = '|'; 
		board[2][13] = '|'; 
		board[2][15] = '|'; 
		board[2][17] = '|'; 
		board[4][1] = '|'; 
		board[4][3] = '|'; 
		board[4][5] = '|'; 
		board[4][7] = '|'; 
		board[4][9] = '|';
		board[4][11] = '|';
		board[4][13] = '|';
		board[4][15] = '|';
		board[4][17] = '|';
		board[6][1] = '|'; 
		board[6][3] = '|'; 
		board[6][5] = '|'; 
		board[6][7] = '|'; 
		board[6][9] = '|';
		board[6][11] = '|';
		board[6][13] = '|';
		board[6][15] = '|';
		board[6][17] = '|';
		board[8][1] = '|'; 
		board[8][3] = '|'; 
		board[8][5] = '|'; 
		board[8][7] = '|'; 
		board[8][9] = '|'; 
		board[8][11] = '|'; 
		board[8][13] = '|'; 
		board[8][15] = '|'; 
		board[8][17] = '|'; 
		board[10][1] = '|'; 
		board[10][3] = '|'; 
		board[10][5] = '|'; 
		board[10][7] = '|'; 
		board[10][9] = '|'; 
		board[10][11] = '|'; 
		board[10][13] = '|'; 
		board[10][15] = '|'; 
		board[10][17] = '|'; 
		board[12][1] = '|'; 
		board[12][3] = '|'; 
		board[12][5] = '|'; 
		board[12][7] = '|'; 
		board[12][9] = '|'; 
		board[12][11] = '|'; 
		board[12][13] = '|'; 
		board[12][15] = '|'; 
		board[12][17] = '|'; 
		board[14][1] = '|'; 
		board[14][3] = '|'; 
		board[14][5] = '|'; 
		board[14][7] = '|'; 
		board[14][9] = '|'; 
		board[14][11] = '|'; 
		board[14][13] = '|'; 
		board[14][15] = '|'; 
		board[14][17] = '|'; 
		board[16][1] = '|'; 
		board[16][3] = '|'; 
		board[16][5] = '|'; 
		board[16][7] = '|'; 
		board[16][9] = '|'; 
		board[16][11] = '|'; 
		board[16][13] = '|'; 
		board[16][15] = '|'; 
		board[16][17] = '|'; 
		
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b.length; j++) {
				Piece p = b[i][j]; 
				if(p != null) {
					//p.printPiece();
					int[] position = convertPosition(p); 
					int x = position[0], y = position[1];
					//System.out.println("x = " + x + " y = " + y); 
					board[y][x] = p.getColor(); 
					//System.out.println(board[x][y]);
				}
			}
		}
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				char c = board[i][j]; 
				System.out.print(c); 
			}
			System.out.println(); 
		}
	}
	
	/* Converts the position of a piece in the virtual game board
	 * to its position in the printed board
	 */
	public int[] convertPosition(Piece p) {
		int[] coordinate = new int[2]; 
		int x = p.getX(), x2 = 0; 
		int y = p.getY(), y2 = 0; 
		switch(x) {
			case 0: 
				x2 = 2; 
				break; 
			case 1: 
				x2 = 4; 
				break; 
			case 2: 
				x2 = 6; 
				break; 
			case 3: 
				x2 = 8; 
				break; 
			case 4: 
				x2 = 10; 
				break; 
			case 5: 
				x2 = 12; 
				break; 
			case 6: 
				x2 = 14; 
				break; 
			case 7: 
				x2 = 16; 
				break; 
		}
		switch(y) {
			case 0: 
				y2 = 2; 
				break; 
			case 1: 
				y2 = 4; 
				break; 
			case 2: 
				y2 = 6; 
				break; 
			case 3: 
				y2 = 8; 
				break; 
			case 4: 
				y2 = 10; 
				break; 
			case 5: 
				y2 = 12; 
				break; 
			case 6: 
				y2 = 14; 
				break; 
			case 7: 
				y2 = 16; 
				break; 
		}
		coordinate[0] = x2; 
		coordinate[1] = y2; 
		return coordinate; 
	}
	
	//function takes every move made so far and returns the appropriate board configuration
	//used because running minimax was applying all the hypothetical moves to the actual board
	public Piece[][] giveCurrentFourBoard(ArrayList<Move> movelist){
		Piece[][] temp = setFourBoard(); 
		
		for(Move m : movelist) {
			Piece piece = new Piece(m.player, m.x, m.y); 
			temp[m.y][m.x] = piece; 
			
			ArrayList<Piece> flips = m.piecesToFlip(temp);
			for(Piece f : flips) {
				int flipx = f.getX(), flipy = f.getY(); 
				Piece toFlip = temp[flipy][flipx]; 
				toFlip.flipPiece();
				temp[flipy][flipx] = toFlip; 
			}
		}
		
		return temp; 
	}
	
	public Piece[][] giveCurrentEightBoard(ArrayList<Move> movelist){
		Piece[][] temp = setEightBoard(); 
		
		for(Move m : movelist) {
			Piece piece = new Piece(m.player, m.x, m.y); 
			temp[m.y][m.x] = piece; 
			
			ArrayList<Piece> flips = m.piecesToFlip(temp);
			for(Piece f : flips) {
				int flipx = f.getX(), flipy = f.getY(); 
				Piece toFlip = temp[flipy][flipx]; 
				toFlip.flipPiece();
				temp[flipy][flipx] = toFlip; 
			}
		}
		
		return temp; 
	}
	
	//prints who wins the game based on who has the most pieces on the board 
	public void winner(Piece[][] endboard) {
		ArrayList<Piece> maxPieces = AI.getPieces(endboard, AI.getColor()); 
		ArrayList<Piece> minPieces = player.getPieces(endboard); 
		int maxp = maxPieces.size(); int minp = minPieces.size(); 
		if(maxp > minp) {
			System.out.println("You lose!");
		} else if (minp > maxp) {
			System.out.println("You win!"); 
		} else {
			System.out.println("Draw!"); 
		}
	}
	/*public static void main(String[] args) {
		Max max = new Max('b'); 
		Min min = new Min('w'); 
		Game test = new Game(max, min);
		
		Piece[][] board = test.setFourBoard();
		test.printFourBoard(board);
		Move best = max.minimaxDecision(board, 2); 
		best.printMove();

	} */
}

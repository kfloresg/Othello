package game;

import java.util.Scanner;

public class Main {
	
	 	public static void main(String[] args) {
		int boardsize = 0, opponentType = 0, depth = 0;
		char color = 'l', maxcolor = 'x'; 
		Scanner scanner = new Scanner(System.in);
		
		//Start questions 
		System.out.println("Welcome to my Reversi game!"); 
		System.out.println("Choose your game:");
		System.out.println("1. 4x4 board");
		System.out.println("2. 8x8 board");
		System.out.println("Your choice?");
		boardsize = scanner.nextInt(); 
		System.out.println("Choose your opponent:");
		System.out.println("1. Agent that uses minimax");
		System.out.println("2. Agent that uses minimax with alpha beta pruning"); 
		System.out.println("Your choice?"); 
		opponentType = scanner.nextInt(); 
		System.out.println("Enter the depth limit for search tree: ");
		depth = scanner.nextInt();
		System.out.println("Do you want to play Dark (b) or Light (w)? "); 
		color = scanner.next().charAt(0); 
		
		//Set up game 
		if(color == 'w') {maxcolor = 'b';}
		else if (color == 'b') {maxcolor = 'w';}
		Max max = new Max(maxcolor); 
		Min min = new Min(color); 
		Game game = new Game(max, min); 
		
		if(boardsize == 1) {
			game.playFourGame(scanner, opponentType, depth);
		} else if (boardsize == 2) {
			game.playEightGame(scanner, opponentType, depth);
		} 
		
		scanner.close(); 
		
	} 
}

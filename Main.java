/*
  David Nong-Ang, Karthik Kodeswaran, Sahib Johar
  11/8/2021
  Battle Ship 
  This is the main page for all the code of the battleship game.
*/
import java.io.*; 
import java.util.*; 

class Main{
	/* 
		Sahib, David, Karthik
		Monday November 8th
		We all started working on creating the base of the game
	*/
  ///////-------------------------------------CONSTANT VARIABLES-------------------------------------\\\\
	public static final String[] _LETTERS = {"   A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I "};
	public static final String[] _NUMBERS = {"1| ", "2| ", "3| ", "4| ", "5| ", "6| ", "7| ", "8| ", "9| "};

  ///////-------------------------------------Public Variables-------------------------------------\\\\
	// Variables
	public static String[][] board1_1 = new String[9][9];
	public static String[][] board1_2 = new String[9][9];
	public static Scanner input = new Scanner(System.in); ///////-------------------------------Scanner Class-------------------------------------\\\\
	public static String userInput = null;
	public static int menuSelect = 0;
	public static boolean menuError = false;
	public static int[] startCoord = new int[2];
	public static int[] endCoord = new int[2];
	public static int shipCounter = 0;
	public static boolean player1 = true;
	public static boolean player2 = true;
	public static int[] hitCoord = new int[2];
	public static boolean hit = false;
	public static int player1Hits = 0;
	public static int player2Hits = 0;
	public static int playerScore1 = 0;
	public static int playerScore2 = 0;
	public static String[] lineArray = null;


  public static void main(String[] args){
		int playerTurn = 1;
		String player1Name = null;
		String player2Name = null;
    String player1LName = null;
		String player2LName = null;
    String line = null; 
		
    try{
      // try catch to read and write names file for first and last names
      BufferedWriter bw = new BufferedWriter(new FileWriter("names.csv"));
		 
     ///////-------------------------------------Players input their names.-------------------------------------\\\\
      // Menu option 1
      System.out.println("Please enter player 1's first name (only letters): ");
      player1Name = input.nextLine();
      System.out.println("Please enter player 1's last name (only letters): ");
      player1LName = input.nextLine();
      System.out.println("Please enter player 2's first name (only letters): ");
      player2Name = input.nextLine();
      System.out.println("Please enter player 2's last name (only letters): ");
      player2LName = input.nextLine();
      bw.write(player1Name + "," + player1LName + "," + player2Name + "," + player2LName+ ",");
			bw.newLine();

      //while names csv is not null
      bw.close();  
      BufferedReader br = new BufferedReader(new FileReader("names.csv")); 
      line = br.readLine();
  	  while(line != null){
        lineArray = line.split(",");
        line = br.readLine();
      }
      
      br.close();  
    }catch(IOException er){
      // if I/O error occurs
      System.out.println("Invalid Name"+er.getMessage());
    }

		// Boat
		Functions.printShip(); 
  
		// Main Menu
		Functions.printMenuScreen();

		// Menu option 3
		if(menuSelect == 3)
			System.out.println("Thanks for playing!");

		// Menu option 2
		while(menuSelect == 2){
      Functions.printInfoScreen();
			menuSelect = 0;
			Functions.printMenuScreen();
		}

		while(menuSelect == 1){
			shipCounter = 0;
			
			// Empty the board
			board1_1 = new String[9][9];
			board1_2 = new String[9][9];

			// Create the board
			for(int r = 0; r < board1_1.length; r++)
				for(int c = 0; c < board1_1[r].length; c++)
					if(board1_1[r][c] == null)
						board1_1[r][c] = "⬜";
			for(int r = 0; r < board1_2.length; r++)
				for(int c = 0; c < board1_2[r].length; c++)
					if(board1_2[r][c] == null)
						board1_2[r][c] = "⬜";

			/* 
				Sahib, Karthik
				Tuesday November 9th
				We started progressing through creating the game
			*/

			// Write blank boards to files
			Functions.writeFile("board1_1.csv", board1_1);
			Functions.writeFile("board1_2.csv", board1_2);
			Functions.writeFile("board2_1.csv", board1_1);
			Functions.writeFile("board2_2.csv", board1_2);
			
			// Draw the board
			board1_1 = Functions.readFile("board1_1.csv");
			board1_2 = Functions.readFile("board1_2.csv");
			Functions.printboard(board1_1, board1_2);

      ///////-------------------------------Ship Placements for player 1-------------------------------------\\\\
			
			// Place first piece
			System.out.println("Please choose the start location of your first ship: ");
			Functions.placeStartShip();

			System.out.println("Please choose the end location of your first ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
      
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);

			// Place second piece
			System.out.println("Please choose the start location of your second ship: ");
			Functions.placeStartShip();
			
			System.out.println("Please choose the end location of your second ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
			
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);

			// Place third piece
			System.out.println("Please choose the start location of your third ship: ");
			Functions.placeStartShip();

			System.out.println("Please choose the end location of your third ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
      
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);
			
			// Place fourth piece
			System.out.println("Please choose the start location of your fourth ship: ");
			Functions.placeStartShip();

			System.out.println("Please choose the end location of your fourth ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
      
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);

			// Place fifth piece
			System.out.println("Please choose the start location of your fifth ship: ");
			Functions.placeStartShip();

			System.out.println("Please choose the end location of your fifth ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
      
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);

			// Switch to player 2's board
			Functions.switchToPlayerTwoBoard();
			
      ///////-------------------------------Ship Placements for player 2-------------------------------------\\\\
      
			// Place first piece
			System.out.println("Please choose the start location of your first ship: ");
			Functions.placeStartShip();

			System.out.println("Please choose the end location of your first ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
      
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);

			// Place second piece
			System.out.println("Please choose the start location of your second ship: ");
			Functions.placeStartShip();
			
			System.out.println("Please choose the end location of your second ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
			
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);

			// Place third piece
			System.out.println("Please choose the start location of your third ship: ");
			Functions.placeStartShip();

			System.out.println("Please choose the end location of your third ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
      
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);
			
			// Place fourth piece
			System.out.println("Please choose the start location of your fourth ship: ");
			Functions.placeStartShip();

			System.out.println("Please choose the end location of your fourth ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
      
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);

			// Place fifth piece
			System.out.println("Please choose the start location of your fifth ship: ");
			Functions.placeStartShip();

			System.out.println("Please choose the end location of your fifth ship (it must be 1 block away from the start location): ");
			Functions.placeEndShip();
      
			// Replace the white board piece with the corrosponding ship piece
			board1_2[startCoord[1]][board1_2.length - startCoord[0] - 1] = shipCounter + " ";
			board1_2[endCoord[1]][board1_2.length - endCoord[0] - 1] = shipCounter + " ";
			Functions.printboard(board1_1, board1_2);

			 /* 
			 	Sahib, Karthik
				Wednesday November 10th
				We started working on the main game loop
			*/

			// Start playing the game
			while(player1 && player2){
				if(playerTurn == 1 && player1 && player2){
					// Switch to player 1's board
					Functions.switchToPlayerOneBoard();
					
					// Run the corrosponding messages for player 1's turn
					System.out.println("Player One ");
					System.out.println();
					System.out.println("Please enter a coordinate to shoot at: ");
					Functions.playerTurn1();
					playerTurn = 2;
					board1_1 = Functions.readFile("board1_1.csv");
					board1_2 = Functions.readFile("board1_2.csv");
					Functions.printboard(board1_1, board1_2);
				}

				// Check to see if all the ships have been destroyed on either player's board
				if(player1Hits == 10)
					player2 = false;
				if(player2Hits == 10)
					player1 = false;
				if(playerTurn == 2 && player1 && player2){
					// Switch to player 1's board
					Functions.switchToPlayerTwoBoard();

					// Run the corrosponding messages for player 1's turn
					System.out.println("Player Two ");
					System.out.println();
					System.out.println("Please enter a coordinate to shoot at: ");
					Functions.playerTurn2(); 
					playerTurn = 1;
					board1_1 = Functions.readFile("board2_1.csv");
					board1_2 = Functions.readFile("board2_2.csv");
					Functions.printboard(board1_1, board1_2);
				}

				// Check to see if all the ships have been destroyed on either player's board
				if(player1Hits == 10)
					player2 = false;
				if(player2Hits == 10)
					player1 = false;
			}

			// Check to see which player won and runs the corrosponding function
			if(player1)
				Functions.printEndScreen1();
			if(player2)
				Functions.printEndScreen2();
			
			/* 
				Sahib, David, Karthik
				Thursday November 11th
				We finished the main game loop
			*/

			// Reprint main menu
			menuSelect = 0;
			Functions.printMenuScreen();

			// Exit game
			if(menuSelect == 3)
				System.out.println("Thanks for playing!");

			// Menu option 2
			while(menuSelect == 2){
				Functions.printInfoScreen();
				menuSelect = 0;
				Functions.printMenuScreen();
			}		
		}
  }//end of void main
}//end of class Main
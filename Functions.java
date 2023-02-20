/*
  David Nong-Ang, Karthik Kodeswaran, Sahib Johar
  11/8/2021
  Functions Page
  This file contains all the functions used in the main file.
*/
import java.io.*; 
import java.util.*; 

class Functions{
	/* 
		Sahib, David, Karthik
		Monday November 8th
		We started creating the game functions
	*/

  ///////-------------------------------------Function that prints out the meny screen-------------------------------------\\\\
	// The following function takes no parameters and returns no values and just prints out the menu screen. 
	public static void printMenuScreen(){
		System.out.println("Welcome to Battleship: ");
		System.out.println();
		System.out.println("1. Play");
		System.out.println("2. Information");
		System.out.println("3. Quit");
		System.out.println();
		while(Main.menuSelect < 1 || Main.menuSelect > 3){
			try{
				if(Main.menuError == true){
					System.out.println("Sorry, please enter a valid input: ");
					System.out.println();
					System.out.println("1. Play");
					System.out.println("2. Information");
					System.out.println("3. Quit");
					System.out.println();
				}
				Main.menuError = true;
				Main.userInput = Main.input.nextLine();
				Main.menuSelect = Integer.parseInt(Main.userInput);
				System.out.println();
			}catch(NumberFormatException er){
				Main.menuSelect = 0;
			}
		}
		Main.menuError = false;
	} // End of menuScreen

  ///////-------------------------------------Function that prints the info screen-------------------------------------\\\\
  // The following function takes no paramters and returns no values and prints out the information screen.
	public static void printInfoScreen(){
		System.out.println("GAME INFORMATION:");
		System.out.println("When the game starts, each player places 5 boats by typing in the start and end coordinates. They will take turns guessing the coordinates of the other player's boats until one side has no more left.");
		System.out.println("The following shows what each symbol represents in the game:");
		System.out.println(" 1⬜ - Represents an empty coordinate on the board.");
		System.out.println(" O - Represents a coordinate that has been shot at.");
		System.out.println(" X - Represents a coordinate of a ship that is destroy.");
		System.out.println(" (1-5) - Represents a coordinate with a boat.");
		System.out.println();
	} // End of printInfoScreen

  ///////-------------------------------------function that displays the boards for the game-------------------------------------\\\\
  // The following function takes two 2-D arrays as the paramters and prints a board for the game while returning no values. 
	public static void printboard(String[][] board1_1, String[][] board1_2){
		System.out.println("       Enemy Side");
		for(int i = 0; i < board1_1[0].length; i++){
			System.out.print(Main._LETTERS[i]); 
		}
		System.out.println();
		for(int r = 0; r < board1_1.length; r++){
			System.out.print(Main._NUMBERS[r]);
			for(int c = 0; c < board1_1[r].length; c++)
				System.out.print(board1_1[r][c]);
			System.out.println();
		}
		System.out.println("-----------------------");
		for(int r = board1_2.length - 1; r > -1; r--){
			System.out.print(Main._NUMBERS[r]);
			for(int c = board1_2.length - 1; c > -1; c--)
				System.out.print(board1_2[r][c]);
			System.out.println();
		}
		for(int i = 0; i < board1_2[0].length; i++){
			System.out.print(Main._LETTERS[i]); 
		}
		System.out.println();
		System.out.println("       Your Side");
		System.out.println();
	} // End of printboard;

  ///////-------------------------------------Function that is used to write the board into the file.-------------------------------------\\\\
	// The following function takes in a string and a 2-D array as the paramters and writes the array to the file listed in the string
	public static void writeFile(String fileName, String[][] board){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			for(int i = 0; i < board.length; i++){
				for(int k = 0; k < board[i].length; k++){
					bw.write(board[i][k] + ",");
				}
				bw.write("\n");
			}
			bw.close();
		}catch(IOException er){
			System.out.println("Sorry there was an error.");
		}
	} // End of writeFile;

  ///////-------------------------------------Function that reads the file and stores it into a 2D array-------------------------------------\\\\
  // The following function takes in a string as the paramter, reads through the whole file and returns it as a 2D array.
	public static String[][] readFile(String fileName){
		try{
			String line = null;
			String lineArray[] = new String[9];
			// String lineCheck = null;
			// String[] lineCheckArray = null;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String[][] board = new String[9][9];
			for(int i = 0; i < board.length; i++){
				line = br.readLine();
				lineArray = line.split(",");
				board[i] = lineArray;
			}
			br.close();
			return board;
		}catch(IOException er){
			System.out.println("Sorry there was an error.");
			return null;
		}
	} // End readFile;

	/* 
		Sahib, David, Karthik
		Tuesday November 9th
		We continued creating the game functions
	*/

  ///////-------------------------------------Function that returns the index values of the board.-------------------------------------\\\\
  // The following function takes a string as a parameter and returns index values of the 2D board array as a 1D integer array.
	public static int[] coords(String coordinates){
		HashMap <String, Integer> map = new HashMap <>();
		int[] returnCoords = {0, 0};
		String letter = coordinates.substring(0, 1);
		letter = letter.toLowerCase();
		int number = Integer.parseInt(coordinates.substring(1, 2));
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		map.put("d", 4);
		map.put("e", 5);
		map.put("f", 6);
		map.put("g", 7);
		map.put("h", 8);
		map.put("i", 9);
		returnCoords[0] = map.get(letter) - 1;
		returnCoords[1] = number - 1;
		return returnCoords;
	} // End of coords;

  ///////-------------------------------------Function that checks the placement of the input.-------------------------------------\\\\
  // The following function takes in a string as a parameter and checks if the placement is within the bounds of the 2D array and returns true if it is within the bounds of the array and false if otherwise.
	public static boolean coordInputCheck(String coordinate){
		HashMap <String, Integer> map = new HashMap <>();
		String letter = null;
		int number = 0;
		Integer check = null;
		int valid = 0;
		if(coordinate.length() != 2)
			return false;
		letter = coordinate.substring(0, 1);
		letter = letter.toLowerCase();
		try{
			number = Integer.parseInt(coordinate.substring(1, 2));
			if(number > 9 || number < 1)
				return false;
			else
				valid++;
		}catch(NumberFormatException er){
			return false;
		}
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		map.put("d", 4);
		map.put("e", 5);
		map.put("f", 6);
		map.put("g", 7);
		map.put("h", 8);
		map.put("i", 9);
		check = map.get(letter);
		if(check != null)
			valid++;
		if(valid == 2)
			return true;
		else 
			return false;
	} // End of coordInputCheck();

  ///////-------------------------------------Function that checks if the coordinates are within 1 value of another.-------------------------------------\\\\
	// The following function takes 2 integer arrays (which act as 2 sets of coordinates) as parameters and checks to see if they are within 1 value of each other to simulate being beside each other on the board. It returns a boolean value, true if they are within 1 unit radius or false if otherwise.
	public static boolean coordinateValidater(int[] startCoord, int[] endCoord){
		if((startCoord[0] - endCoord[0] == -1 || startCoord[0] - endCoord[0] == 0 || startCoord[0] - endCoord[0] == 1) && (startCoord[1] - endCoord[1] == -1 || startCoord[1] - endCoord[1] == 0 || startCoord[1] - endCoord[1] == 1)){
			return true;
		}
		else{
			return false;
		}
	}//end coordinateValidater();

  ///////-------------------------------------Function that checks the coordinates of the ships-------------------------------------\\\\
	// The following function takes 2 integers which act as coordinates and a 2D array which acts as the board as parameters and checks to see whether the player is placing a ship on top of another or not and returns false if so and true otherwise.
	public static boolean shipChecker(int index1, int index2, String[][] board){
		if(board[index1][board.length - index2 - 1].charAt(0) != '⬜'){ 
			return false;
		}
		else{
			return true;
		}
	} // End of shipChecker;

  ///////-------------------------------------Function that checks the placement of the start of ship.-------------------------------------\\\\
  // The following function has no parameters and checks whether or not the placement of the start of the ship is in a valid position or not keeps asking for a valid input if not. It returns no values.
	public static void placeStartShip(){
		Main.userInput = Main.input.nextLine();
		while(Functions.coordInputCheck(Main.userInput) == false){
			System.out.println("Sorry please enter a valid input: ");
			Main.userInput = Main.input.nextLine();
		}
		Main.startCoord = Functions.coords(Main.userInput);
		while(Functions.shipChecker(Main.startCoord[1], Main.startCoord[0], Main.board1_2) == false){
			System.out.println("Sorry, your ship cannot be placed on top of another ship. Please enter another coordinate: ");
			Main.userInput = Main.input.nextLine();
			while(Functions.coordInputCheck(Main.userInput) == false){
				System.out.println("Sorry please enter a valid input: ");
				Main.userInput = Main.input.nextLine();
			}
			Main.startCoord = Functions.coords(Main.userInput);
		}
	} // End of placeStartShip
	
   ///////-------------------------------------Function that checks the end placement of the ship.-------------------------------------\\\\
   // The following function has no paramters and checks whether or not the placement of the end of the ship is in a valid position or not keeps asking for a valid input if not. It returns no values.
	public static void placeEndShip(){
		Main.userInput = Main.input.nextLine();
		while(Functions.coordInputCheck(Main.userInput) == false){
			System.out.println("Sorry please enter a valid input: ");
			Main.userInput = Main.input.nextLine();
		}
		Main.endCoord = Functions.coords(Main.userInput);
		while(Functions.coordinateValidater(Main.startCoord, Main.endCoord) == false || (Main.startCoord[0] == Main.endCoord[0] && Main.startCoord[1] == Main.endCoord[1])){
			System.out.println("Sorry please enter a valid input: ");
			Main.userInput = Main.input.nextLine();
			while(Functions.coordInputCheck(Main.userInput) == false){
				System.out.println("Sorry please enter a valid input: ");
				Main.userInput = Main.input.nextLine();
			}
			Main.endCoord = Functions.coords(Main.userInput);
		}
		// Validate all aspects of the input
		while((Functions.shipChecker(Main.endCoord[1], Main.endCoord[0], Main.board1_2) == false) || (Functions.coordInputCheck(Main.userInput) == false) || (Functions.coordinateValidater(Main.startCoord, Main.endCoord) == false || (Main.startCoord[0] == Main.endCoord[0] && Main.startCoord[1] == Main.endCoord[1]))){
			System.out.println("Sorry, please enter a valid input: ");
			Main.userInput = Main.input.nextLine();
			if(Functions.coordInputCheck(Main.userInput))
				Main.endCoord = Functions.coords(Main.userInput);
		}
		Main.shipCounter++;
	} // End of placeEndShip

	/* 
		Sahib, David, Karthik
		Wednesday November 10th
		We continued creating the game functions
	*/

  ///////-------------------------------------Function that asks the user for thier input and checks it.-------------------------------------\\\\
	// The following function takes no parameters and returns no values. It is called whenever it is player 1's turn and asks for an input and validates it then runs a different function to determine whether or not the player hit a ship with the coordinates they entered.
	public static void playerTurn1(){
		Main.userInput = Main.input.nextLine();
		while(Functions.coordInputCheck(Main.userInput) == false){
			System.out.println("Sorry please enter a valid input: ");
			Main.userInput = Main.input.nextLine();
		}
		System.out.println();
		Main.hitCoord = Functions.coords(Main.userInput);
		hitChecker1(Main.hitCoord[0], Main.hitCoord[1], Main.board1_1);
	} // End of playerTurn
    
  ///////-------------------------------------Function that asks the user for thier input and checks it.-------------------------------------\\\\
	// The following function takes no parameters and returns no values. It is called whenever it is player 2's turn and asks for an input and validates it then runs a different function to determine whether or not the player hit a ship with the coordinates they entered.
	public static void playerTurn2(){
		Main.userInput = Main.input.nextLine();
		while(Functions.coordInputCheck(Main.userInput) == false){
			System.out.println("Sorry please enter a valid input: ");
			Main.userInput = Main.input.nextLine();
		}
		System.out.println();
		Main.hitCoord = Functions.coords(Main.userInput);
		hitChecker2(Main.hitCoord[0], Main.hitCoord[1], Main.board1_1);
	} //end of playerTurn2

  ///////-------------------------------Function that checks if the inputted coordinates does anything to the board .-------------------------------------\\\\
	// The following function takes 2 integers that act as coordinates and a 2D array which acts as the board and checks whether or not the player hit anything with the coordinates they entered in the above function. It also validates whether or not they've already shot there. It returns no values. This function only works for player 1.
	public static void hitChecker1(int index1, int index2, String[][] board1_1){
		String[][] board2_2 = new String[9][9];
		board2_2 = readFile("board2_2.csv");
		if(board2_2[index2][board1_1.length - index1 - 1].charAt(0) != '⬜'){
			if(board2_2[index2][board1_1.length - index1 - 1].equals("X ")){
				System.out.println("Sorry you've already shot here. Please enter another co-ordinate: ");
				playerTurn1();
			}
			if(board2_2[index2][board1_1.length - index1 - 1].equals("O ")){
				System.out.println("Sorry you've already shot here. Please enter another co-ordinate: ");
				playerTurn1();
			}
			else if(board2_2[index2][board1_1.length - index1 - 1].equals("1 ") || board2_2[index2][board1_1.length - index1 - 1].equals("2 ") || board2_2[index2][board1_1.length - index1 - 1].equals("3 ") || board2_2[index2][board1_1.length - index1 - 1].equals("4 ") || board2_2[index2][board1_1.length - index1 - 1].equals("5 ")){
				System.out.println("Congrats! You hit a ship.");
				System.out.println();
				board2_2[index2][board1_1.length - index1 - 1] = "X ";
				board1_1[index2][index1] = "X ";
				writeFile("board2_2.csv", board2_2);
				writeFile("board1_1.csv", board1_1);
				Main.hit = true;
				Main.player1Hits++;
        Main.playerScore1 += 100;
			}
		}		
		else if(Main.hit == false){
			System.out.println("Sadly, you missed.");
			System.out.println();
			board2_2[index2][board1_1.length - index1 - 1] = "O ";
			board1_1[index2][index1] = "O ";
			writeFile("board2_2.csv", board2_2);
			writeFile("board1_1.csv", board1_1);	
    }
		Main.hit = false;
	} // End of hitChecker1

	/* 
		Sahib, David
		Thursday November 11th
		We continued creating the game functions
	*/

  ///////-------------------------------Function that checks if the inputted coordinates does anything to the board .-------------------------------------\\\\
	// The following function takes 2 integers that act as coordinates and a 2D array which acts as the board and checks whether or not the player hit anything with the coordinates they entered in the above function. It also validates whether or not they've already shot there. It returns no values. This function only works for player 2.
  public static void hitChecker2(int index1, int index2, String[][] board2_1){
		String[][] board1_2 = new String[9][9];
		board1_2 = readFile("board1_2.csv");
		if(board1_2[index2][board2_1.length - index1 - 1].charAt(0) != '⬜'){
			if(board1_2[index2][board2_1.length - index1 - 1].equals("X ")){
				System.out.println("Sorry you've already shot here. Please enter another co-ordinate: ");
				playerTurn2();
			}
			if(board1_2[index2][board2_1.length - index1 - 1].equals("O ")){
				System.out.println("Sorry you've already shot here. Please enter another co-ordinate: ");
				playerTurn2();
			}
			else if((board1_2[index2][board2_1.length - index1 - 1].equals("1 ")) || board1_2[index2][board2_1.length - index1 - 1].equals("2 ") || board1_2[index2][board2_1.length - index1 - 1].equals("3 ") || board1_2[index2][board2_1.length - index1 - 1].equals("4 ") || board1_2[index2][board2_1.length - index1 - 1].equals("5 ")){
				System.out.println("Congrats! You hit a ship.");
				System.out.println();
				board1_2[index2][board2_1.length - index1 - 1] = "X ";
				board2_1[index2][index1] = "X ";
				writeFile("board1_2.csv", board1_2);
				writeFile("board2_1.csv", board2_1);
				Main.hit = true;
				Main.player2Hits++;
        Main.playerScore2 += 100;
			}
		}		
		else if(Main.hit == false){
			System.out.println("Sadly, you missed.");
			System.out.println();
			board1_2[index2][board2_1.length - index1 - 1] = "O ";
			board2_1[index2][index1] = "O ";
			writeFile("board1_2.csv", board1_2);
			writeFile("board2_1.csv", board2_1);	
    }
		Main.hit = false;
	} // End of hitChecker2

	/* 
		Sahib, David, Karthik
		Friday November 12th
		We finished creating the game functions
	*/

  ///////-------------------------------Function that switches the boards.-------------------------------------\\\\
	// The following function takes no parameters and returns no values. It uses the provided csv files to switch which players boards are displayed and assigns them the the corrosponding class variable. This only works to swap from player 2 to player 1.
	public static void switchToPlayerOneBoard(){
		System.out.println("You can now pass the game over to your opponent. Please press enter to continue: ");
		Main.input.nextLine();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		// Swap to player 2's board
		Functions.writeFile("board2_1.csv", Main.board1_1);
		Functions.writeFile("board2_2.csv", Main.board1_2);
		Main.board1_1 = Functions.readFile("board1_1.csv");
		Main.board1_2 = Functions.readFile("board1_2.csv");
		Functions.printboard(Main.board1_1, Main.board1_2);
		Main.shipCounter = 0;
	} // End of switchToPLayerOneBoard

  ///////-------------------------------Function that switches the boards.-------------------------------------\\\\
	// The following function takes no parameters and returns no values. It uses the provided csv files to switch which players boards are displayed and assigns them the the corrosponding class variable. This only works to swap from player 1 to player 2.
	public static void switchToPlayerTwoBoard(){
		System.out.println("You can now pass the game over to your opponent. Please press enter to continue: ");
		Main.input.nextLine();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		// Swap to player 2's board
		Functions.writeFile("board1_1.csv", Main.board1_1);
		Functions.writeFile("board1_2.csv", Main.board1_2);
		Main.board1_1 = Functions.readFile("board2_1.csv");
		Main.board1_2 = Functions.readFile("board2_2.csv");
		Functions.printboard(Main.board1_1, Main.board1_2);
		Main.shipCounter = 0;
	} // End of switchToPlayerTwoBoard

  ///////-------------------------------Function prints the boat.-------------------------------------\\\\
  // The following function has no paramaters and prints out a ship for the starting menu screen.
	public static void printShip(){
		System.out.println("		                         |----.___");
		System.out.println("                                |----.___',");
		System.out.println("              ._________________|_______________.");
		System.out.println("              |####|    |####|    |####|   |####|");
		System.out.println("              |####|    |####|    |####|    |####|       .");
		System.out.println("              |####|    |####|    |####|    |####|     /|_____.");
		System.out.println("  __          |####|    |####|    |####|    |####|   |  o  ..|");
		System.out.println("(  '.         '####|    '####|    '####|    '####|   '.  .vvv'");
		System.out.println(" '@ |          |####.    |####.    |####.    |####|    ||");
		System.out.println("  | |          '####.    '####.    '####.    '####.    ||");
		System.out.println(" /  |         /####.    /####.    /####.    /####.     |'.");
		System.out.println("|    |       '####/    '####/    '####/    '####/      |  |");
		System.out.println("|     |  .  /####|____/####|____/####|____/####|      |    |");
		System.out.println("|      |//   .#'#. .*'*. .#'#. .*'*. .#'#. .*'*.     |      |");
		System.out.println(" |     //-...#'#'#-*'*'*-#'#'#-*'*'*-#'#'#-*'*'*-...'        |");
		System.out.println("  |   //     '#'#' '*'*' '#'#' '*'*' '#'#' '*'*'             |");
		System.out.println("   './/                                                     .'");
		System.out.println("   _//'._               BATTLESHIP GAME                   _.'");
		System.out.println("  /  /   '----------------------------------------------'");
		System.out.println();
	} // End of printShip

  ///////-------------------------------Function prints the winning screen of player 1.-------------------------------------\\\\
  // The following function has no paramaters and prints out the ending screen if player 1 wins.
	public static void printEndScreen1(){
		System.out.println("                                             .");
		System.out.println("                           .                 |");
		System.out.println("                           +                 |  ");
		System.out.println("                  .        |                *+W+-*                ");
		System.out.println("     .           +y        +W+              . H                 .");
		System.out.println("  .  +y            |I.   y  |               ! H= .           .  ^");
		System.out.println("  !   \\     .     |H '. /   |  ___.        .! H  !   +--.--y !  V");
		System.out.println("  !    \\     \\  +=|H|=='.=+ | |====\\   _  '_H_H__H_. H_/=  J !  !");
		System.out.println(". !     \\'    VVV_HHH_/__'._H |  E  \\_|=|_|========|_|==|____H. ! _______.");
		System.out.println("I-H_I=I=HH_==_|I_IIIII_I_I_=HH|======.I-I-I-=======-I=I=I=I_=H|=H'===I=I/");
		System.out.println("\\                                                                      ,");
		System.out.println(" |                           PLAYER 1 WINS!!!                         /");
		System.out.println(" .___________________________________________________________________'");
    System.out.println();
		System.out.println("Congratulations " + Main.lineArray[0] + " " + Main.lineArray[1] + " You successfully destroyed " + Main.player1Hits + " layers of boats!");
    System.out.println("Player 1 Final Score: " + Main.playerScore1);
    System.out.println("Nice Try " + Main.lineArray[2] + " " + Main.lineArray[3] + " You successfully destroyed " + Main.player2Hits + " layers of boats!");
    System.out.println("Player 2 Final Score: " + Main.playerScore2);

	} // End printEndScreen1();

  ///////-------------------------------Function prints the winning screen of player 2.-------------------------------------\\\\
  // The following function has no paramaters and prints out the ending screen if player 2 wins.
	public static void printEndScreen2(){
		System.out.println("                                             .");
		System.out.println("                           .                 |");
		System.out.println("                           +                 |  ");
		System.out.println("                  .        |                *+W+-*                ");
		System.out.println("     .           +y        +W+              . H                 .");
		System.out.println("  .  +y            |I.   y  |               ! H= .           .  ^");
		System.out.println("  !   \\     .     |H '. /   |  ___.        .! H  !   +--.--y !  V");
		System.out.println("  !    \\     \\  +=|H|=='.=+ | |====\\   _  '_H_H__H_. H_/=  J !  !");
		System.out.println(". !     \\'    VVV_HHH_/__'._H |  E  \\_|=|_|========|_|==|____H. ! _______.");
		System.out.println("I-H_I=I=HH_==_|I_IIIII_I_I_=HH|======.I-I-I-=======-I=I=I=I_=H|=H'===I=I/");
		System.out.println("\\                                                                      ,");
		System.out.println(" |                           PLAYER 2 WINS!!!                         /");
		System.out.println(" .___________________________________________________________________'");
    System.out.println();
    System.out.println("Congratulations " + Main.lineArray[2] + " " + Main.lineArray[3] + " You successfully destroyed " + Main.player2Hits + " layers of boats!");
    System.out.println("Player 2 Final Score: " + Main.playerScore2);
    System.out.println("Nice Try " + Main.lineArray[0] + " " + Main.lineArray[1] + " You successfully destroyed " + Main.player1Hits + " layers of boats!");
    System.out.println("Player 1 Final Score: " + Main.playerScore1);
	} // End printEndScreen2; 
}
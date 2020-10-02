/**
 * 
 */
package edu.lewisu.cs.kylevbye.mazegame;

import java.util.ArrayList;
import edu.lewisu.cs.kylevbye.util.*;

/**
 * This class launches a game where you find hidden
 * treasure. Random rooms may heal, damage, or do nothing.
 * 
 * @author	Kyle V Bye
 * @see	Building
 * @see	RoomReader
 * @see	Room
 * @see	MazeGameManager
 * @see	edu.lewisu.cs.kylevbye.util.ConsoleUtil
 */
public class MazeGame {

	/**
	 * Program begins here.
	 * @param	args	program arguments
	 */
	public static void main(String[] args) {
		
		//	Starting sequence
		System.out.println("\nStart\n");
		
		//	Take mapFileName from user.
		String mapFileName;
		while ((mapFileName = ConsoleUtil.readLine("Input Map File: ")).equals("")) {
			System.out.println("\nPlease actually type something.\n");
		}
		System.out.println();
		
		//	Process File
		if (!RoomReader.readFile(mapFileName)) {
			
			System.err.println("File Process Failed... Terminating");
			terminate();
			return;
			
		}
		
		//	Link Rooms into a Building
		Building building = new Building(mapFileName.replace(".txt", ""));
		building.setRoomList(RoomReader.getRoomList());
		building.linkRooms(RoomReader.getMapStringList(), RoomReader.getBuildingSize());
		
		//	Set up Player and Game Manager
		Player player = new Player(askPlayerName(),100);
		MazeGameManager gameManager = new MazeGameManager(building, player);
		
		//	Play
		boolean playAgain = true;
		while(playAgain) {
			gameManager.play();
			playAgain = askPlayAgain();
		}
		
		//	End of program
		terminate();

	}
	
	/**
	 * Returns a non empty player name.
	 * @return	String representing a player's name.
	 */
	private static String askPlayerName() {
		
		String playerName = new String();
		
		while (playerName.equals(new String())) {
			playerName = ConsoleUtil.readLine("Please Enter Your Name: ");
		}
		
		return playerName;
	}
	
	/**
	 * Returns true if and only if the user types in "Y" in the input
	 * stream. Otherwise, false is returned.
	 * 
	 * @return	true/false
	 */
	private static boolean askPlayAgain() {
		
		String userInput = "";
		while (!(userInput.equals("Y") || userInput.equals("N"))) {
			userInput = ConsoleUtil.readLine("Play Again? [Y/N]-->").toUpperCase();
		}
		
		return userInput.equals("Y");
		
	}
	
	/**
	 * Prints "Terminating..." to the output stream
	 * with line feeds before and after.
	 */
	private static void terminate() {
		
		System.out.println("\nTerminating...\n");
		
	}
	

}

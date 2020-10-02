/**
 * 
 */
package edu.lewisu.cs.kylevbye.mazegame;

import edu.lewisu.cs.kylevbye.util.ConsoleUtil;

/**
 * Manages the Maze Game. 
 * @author	Kyle V Bye
 *
 */
public class MazeGameManager {
	
	///
	///	Fields
	///
	private Building building;
	private Room currentRoom;
	private Room treasureRoom;
	private Player player;
	
	///
	///	Constants
	///
	public static final String roomUserFormat = "\t%s) %s\n";
	public static final String neutralDialog = "Nothing happened upon entrance";
	public static final String[] goodDialogs = {
			"You found a brownie on the floor!",
			"You found a piece of cake on a plate!",
			"There was a gallon of Mtn. Dew! You drank some!",
			"A peaceful aura fills the room! You felt good.",
			"You rested for a while. You wake up feeling better!"
		};
	public static final String[] badDialogs = {
			"A snake flew in the room and bit you!",
			"An angry ghost came from beneath and dropped on object on you!",
			"You hear a ear piercing scream!",
			"You are starving to death!",
			"You weren't looking and stepped on broken glass!",
			"An aggressive dog bites you and leaves the room!",
			"You didn't do your homework and now your head hurts!",
			"A slightly toxic gas enters the room."
		};
	
	///
	///	Getters
	///
	public Building getBuilding() { return this.building; }
	public Room getCurrentRoom() { return this.currentRoom; }
	public Room getTreasureRoom() { return this.treasureRoom; }
	public Player getPlayer() { return this.player; }
	
	///
	///	Setters
	///
	public void setBuilding(Building buildingIn) { this.building = buildingIn; }
	public void setCurrentRoom(Room currentRoomIn) { this.currentRoom = currentRoomIn; }
	public void setTreasureRoom(Room treasureRoomIn) { this.treasureRoom = treasureRoomIn; }
	public void setPlayer(Player playerIn) { this.player = playerIn; }
	
	///
	///	Functions
	///
	/**
	 * Sets current and treasure room at random and
	 * sets all the rooms to do a reasonable amount of damage.
	 */
	public void initializeRoomBehavior() {
		
		//	Set treasure room and starting room
		currentRoom = building.getRandomRoom();
		treasureRoom = building.getRandomRoom();
		while (currentRoom.equals(treasureRoom)) { treasureRoom = building.getRandomRoom(); }
		
		//	Make all other rooms bad rooms
		//	Set all their actions to hurt this player.
		int maxDamageThreshold = (100/(building.getRoomList().size()) + 10);
		for (Room r : building.getRoomList()) {
			r.setAction(new Actionable() {
				public void performAction() {
					
					// 50-50 Chance of a cursed room.
					if (Math.random() > 0.5) {
						
						//	Bad Room!
						//	Say how you took damage and how much damage you took.
						System.out.println(badDialogs[(int)(Math.random()*(badDialogs.length))]);
						int randomDamage = (int)(Math.random()*maxDamageThreshold);
						player.takeDamage(randomDamage);
						System.out.format("You took %d damage!\n", randomDamage);
						
					}
					else {
						
						//	Good/Neutral Room! 80 - 20(ish) chance!
						//	Report if you healed or if nothing happened.
						if (Math.random()<0.2) {
							
							//	Meh!
							System.out.println(neutralDialog);
							
						}
						else {
							
							//	Good!
							System.out.println(goodDialogs[(int)(Math.random()*(goodDialogs.length))]);
							int randomGain = (int)(Math.random()*15);
							player.heal(randomGain);
							System.out.format("You gained %d health!\n", randomGain);
							
						}
						
						
					}
					
				}
				
			});
		}
		
	}
	
	/**
	 * Runs the game.
	 */
	public void play() {
		
		//	Start
		
		System.out.println("Find the Treasure Room!\n");
		
		//	The Game
		
		while (true) {
			
			//	Player Report
			System.out.println("Current Room: " + currentRoom.getName());
			System.out.println(currentRoom.getDescription());
			System.out.println("Your Health: " + player.getHealth());
			
			Room north, south, east, west;
			
			//	Process Choices
			String possibilities = new String();
			String choices = "Pick a direction[N,S,E,W]:\n";
			
			north = currentRoom.getNeighbor("N");
			east = currentRoom.getNeighbor("E");
			south = currentRoom.getNeighbor("S");
			west = currentRoom.getNeighbor("W");
			
			if (north != null) {
				possibilities += "N";
				choices += String.format(
						roomUserFormat, "N", north.getName()
						);
			}
			if (east != null) {
				possibilities += "E";
				choices += String.format(
						roomUserFormat, "E", east.getName()
						);
			}
			if (south != null) {
				possibilities += "S";
				choices += String.format(
						roomUserFormat, "S", south.getName()
						);
			}
			if (west != null) {
				possibilities += "W";
				choices += String.format(
						roomUserFormat, "W", west.getName()
						);
			}
			
			
			//	Give Choices and Choose Room
			
			String choice = new String();
			System.out.println(choices);
			while (choice.equals("") || !possibilities.contains(choice)) {
				choice = ConsoleUtil.readLine("-->").toUpperCase();
			}
			
			if (north != null && choice.equals("N")) { currentRoom = north; }
			else if (east != null && choice.equals("E")) { currentRoom = east; }
			else if (south != null && choice.equals("S")) { currentRoom = south; }
			else { currentRoom = west; }
			
			//	Skip a line
			System.out.println();
			
			//	Found treasure room	- End Game
			
			if (currentRoom.equals(treasureRoom)) {
				System.out.println("You found the treasure room! You win!");
				break;
			}
			
			//	Tell the player what happened
			//	Call performAction of room.
			
			currentRoom.performAction();
			System.out.println("Your health is now " + player.getHealth() + "!");
			
			//	Health is too low - End Game
			
			if (player.getHealth() == 0) {
				System.out.println("You died! You lost!");
				break;
			}
			
			//	Skip a line
			
			System.out.println();
			
		}
		
		//	End
		
		System.out.println("Game Over");
		
	}
	
	///
	///	Constructors
	///
	/**
	 * Initializes by calling the other Constructor:
	 * MazeGameManager(Building, Player) with the parameters
	 * being new instances.
	 * @see	MazeGameManager#MazeGameManager(Building, Player)
	 */
	public MazeGameManager() {
		this(new Building(), new Player());
	}
	
	/**
	 * Initializes player and building with the provided parameters.
	 * Initializes current and treasure room as well.
	 * @param buildingIn
	 * @param playerIn
	 * @see MazeGameManager#initializeRoomBehavior()
	 */
	public MazeGameManager(Building buildingIn, Player playerIn) {
		setBuilding(buildingIn);
		setPlayer(playerIn);
		initializeRoomBehavior();
	}

}
